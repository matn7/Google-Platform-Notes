# Distributed Message Queue

## Background
There are many different kinds, there's been a lot of evolution over the years.
Traditional message queue, something like Rabbit MQ, which started as sort of one-to-one mapping.
You produced messages on one side and then consumed them on the other side. So it was just one system creating messages
adding them to the queue and then one consumer taking those messages. So kind of how a queue data structure works in code.
Pub Sub pattern which is also a pattern that can be applied to code but in terms of distributed systems, there are also 
helpful and have additional complexity with them. 
At a high level we have Publishers, and we have Subscribers as Pub Sub name implies, but these are actually part of our
application, so we could have some servers possible they are payment transactions servers. Every time we receive a
payment we have like our app servers that do something with that payment information. Maybe those server's can't
process the payment immediately. So we want to process them asynchronously, because if we can't handle the traffic of like
the payments, but we keep receiving payments then eventually our servers might miss some of the payments of we might crash
and not be able to process them. We definitely don't want this to happen. If it takes a few extra seconds to process them 
asynchronously that won't be the end of the world. So what we do instead, every time we receive a payment, we send them
to Publ Sub message queue system. These topics are sort of entities within the Pub Sub system. So first of all, all the 
messages that we get will actually be persisted here. So in some type of persistent storage, they will be stored so that
we definitely will not lose them and then eventually when the different servers also part of our app, the subscribers
that will be listening to these topics and the messages from the topics will eventually end up with subscribers and
maybe will be the ones that actually process the payment, but the subscribers will get those messages as needed if they 
can't process them all immediately. It's ok because the messages can still be stored within the Pub Sub system with topics.
One topic can be for payment and another for something else like log data, fraud detection systems. Maybe we want both
of subscribers to get fraud information. One of Subscribers could receive messages from both topics, one of them will
only receive messages from the topic it needed. This obviously adds some decoupling to our system where the Publishers
don't need to know about the subscribers. They don't need to know how many subscribers exists that kind of information
is encapsulated within the pub sub system. This allows for additional scaling. In between the topics and the subscribers 
are actually another entity called a Subscription.

## Functional Requirements
We definitely want to support fan-out. With our distributed message queue, we want the same message to be able to be
received by multiple Subscribers (Consumers). We talked about subscribers, but there's another term that's pretty much 
equivalent that's used called consumers. And when we talked about Publishers, there's another term for it called 
producers. This type of terminology is much more commonly used with other types of message queues, like Kafka.
Kafka actually technically is not a message queue. It's more for event streaming but the lines have sort of blurred 
between message queue and event streaming systems, like Kafka. They really support a lot of the same functionality.
Kafka does support fan-out. It does support persistent storage messages can be retained in Kafka for a certain period of
time which brings to the next functional requirements that we want which is being able to retain messages. But in our 
case we are going to simplify and only retain the messages until they are delivered, and also we want to support at least
once delivery. Why that is important is because we want every single message that's received by our pub sub system to
definitely deliver that message, at least once. Because if we can't guarantee that, that means messages are going to be
coming into our systems and then some of them are never going to be leaving, some of those messages that are going to
be lost, and we definitely don't want that to happen. That's kind of one of the points behind using a message queue.
There's other types of delivery with message queues, things like at most once delivery, why that would be important is
because we don't want the same message to sent multiple times, yop don't want end up duplicate messages. With payment
that could be important because you don't want the same payment to be processed multiple times. So you want to guarantee
at most once delivery, but if we have at most once delivery but not at least once delivery that brings us to a problem
where the message might get dropped. So there's also a third type called exactly once delivery, which is as the name
implies every message will be received and I get sent out exactly once, we can guarantee that every message will be 
delivered at exactly once, so we won't end up with duplicate messages and won't end up with messages that get dropped, but 
we are not going to be supporting this just for Simplicity. It's actually a pretty complicated problem to solve and
there's a lot of trade-offs that can be made when trying to design something like this. Stick with at least once delivery
because that's probably the most important that's mainly it for functional requirements.

## Non-Functional Requirements
System needs to be Scalable. Ideally we should be able to horizontally scale our message queue if we add more and more 
Publishers and Subscribers. We should be able to scale out our system to be able to handle the traffic and as we get more
and more messages we could have a lot of messages at the same time. We do want persistent storage. We do not want messages
to be lost. We also want to be able to have high Throughput.

## High-Level Design
At the high level. Publishers are servers that our application owns, but they are not necessarily a port of the message
queue that we're designing. But they are very important part because the message queue doesn't do anything if we don't
have a Publishers and Subscribers. Now, first of all, Publishers are going to be sending messages into the message queue
and then the subscribers are going to be receiving messages from the message queue and somewhere between, the messages
are going to be stored to make sure that we never lose them. So at a high level we know we are going to have some servers
that are part of message queue called Publish Forwarders, and another layer of servers called Subscriber Forwarders.
The idea is simple as Publishers create messages, they will send the messages into our Pub Sub system, and they'll be
received by the publishing forwarders depending on the number of messages that we're sending and producing we may need to
scale the publish forwarders. But remember the same message produced by publisher, might get sent to multiple Subscribers.
So it is important to be able to independently scale the subscriber forwarders, which will be sending them messages to
subscribers. Maybe the same messages needs to get sent to a bunch of subscribers. So we need to scale subscribers more
than we scale the publish forwarders. We could have the opposite case where we have a bunch of Publishers. But all the
messages are getting sent to a single subscribers. In this case we need to scale Publish Forwarders more. As messages
come into the publish forwarders, those messages will then be stored somewhere possibly a database which stores all of the
messages and as soon as a message is stored by any of the Publish Forwarders, we know that the message should never get
lost at this point. So typically what's done is the Pub Sub system will then send a message back to that Publisher telling
it that the message you just sent me has been stored. Basically one of the Publish Forwarders the some one, would send the
acknowledgement to the Publisher, your message has been stored. You don not have to worry about it anymore. You don't have
to try to send it to me again because if it never receives the acknowledgement then the Publisher definitely doesn't want
the message to get lost. This is all part fo the Fault Tolerance that the message queue should provide for us. But after the
publisher receives the ack, then it can send different messages. If it has any it can continue sending messages, maybe 
different Publishers will be sending messages. And generally this is done over HTTP. We won't really go into security
aspect of it. But probably we wouldn't want anybody to just be able to send a message into our system for obvious reasons.
Once those messages are stored Subscriber Forwarders can read that data and then send it to their relevant subscribers.
And then once a subscriber has seen a message, then they will actually send an acknowledgement back to the Pub Sub system.
And once a particular message has been read by all of the subscribers then we probably don't need to store that message
anymore if that message has been acked by every relevant subscriber, then we can probably remove that message from 
Storage. This will ensure that we support at least once delivery because if we guarantee that everyone has already seen
the message then we know we are safe to delete it, and we won't end up taking an infinite amount of storage.

## Design Details
We might need a secondary data storage system and this could be for metadata specifically we might want to store information
on how many subscribers we have. There is concept of Topics in a message queue, and we've kind of looked at this from the 
point of just a single topic a topic is a way to organize messages. So when we get sent messages into our Pub Sub system, 
we may have a single topic for payment. For example all payment information will get sent to this topic, and it makes sense
that we would want to store messages of the same topic in a similar way. So if we ended up having very, very large amount
of messages, we'd probably want to Shard this based on topic if possible, but even a single topic may have too many messages,
but also there is this concept of a Subscription. So when messages are created and sent by Publishers, they will go to a
topic they could go to a multiple topics if the publisher decides that, but they will be organized based on a topic. Now 
when messages are actually consumer and received by subscribers, they will go through a subscription. One of the major 
things about it is that it's used to Fan-out information from a topic, to fan-out the messages. So if we wanted in this
case two Subscribers, to receive messages from the payment topic, we could create two subscriptions of for this same topic.
So we have two subscriptions for that topic and each of these subscribers, each of these servers listens to a single
subscriptions. So this is a way to Fan out the messages and also this is a way to guarantee that each message is received
by both of the subscriptions, and both of the subscribers. Maybe one of server process the payment and one of them is for
fraud detection. We definitely don't want messages to only be received by one of them. We want both of them to receive it,
and this is a way to do that. These topics and subscriptions are sort of abstracted but that all that information will be
stored in the metadata database. We'll store which topics exists and which subscriptions exists and this will allow us 
from this side when a message has been acknowledged, we can go to our metadata database, and see that a certain topic
the payment topic. It has two subscriptions on it and we can record. One of the subscriptions has already seen the message,
but another subscription has not seen the message. It hasn't sent the acknowledgement yet. So then at that point we would
know we can't delete that message yet. But maybe as soon as the other server sends the acknowledgement then we see that
both of the subscriptions on that topic have seen that particular message. Then we could go to our messages database and 
then remove that message it doesn't need to be stored anymore, because all relevant subscribers have already seen it now.
Ideally we'd also want the messages to be first in first out, we would ideally like to have ordering with the messages.
So in the same order that messages are received from Publishers and then stored into our system is the same order that we'd
like to deliver Messages. At a high level the Publishers can create some type of ID which is tied to the timestamp that 
those messages were received and those messages can be stored in our database using that. Since we don't need to do complex
joins. We can probably just stick with NoSQL database for storing the messages because the message is typically like a
JSON object anyway. But probably a relational database would work just as well here. A reason to go with SQL database in
this case would be for the ACID properties. If we possibly store the messages and auto increment the ID then we can kind
of be sure that the messages will be stored in the order that they will receive. In that case the isolation property of
ACID would be important because we will be having multiple forwarders concurrently writing here, and we would want to
write those messages in an isolated way.
There are other policies we can have for retention. We could design our systems such that we can store messages even if
every subscriber has received those messages we could set a certain policy like 7 day policy, even after every subscriber
has acknowledged them, or we could set a seven-day policy were even if some subscribers have not acknowledged them.
We will still delete the message anyway. We can have some flexibility and real message system queue systems including
pub subs and real message queue systems generally support this functionality. Another open question that remains is what
about replication of our messages when we take a message, and we just store it one time in a single database, and them we
send the acknowledgement to the publisher. We guarantee we are going to deliver this message or at least it will never
be lost. How can we make that guarantee? We're just storing it in a single database. What if this database crashes, what
if the data center explodes? The data is definitely going to be lost. Probably we should replicate this among additional
database instances, but how many times, should we replicate it? Probably at least three is a good rule of thumb especially
if these three are located in different regions of the world. Another question remains. When should we send the acknowledgements
to the Publisher if we take that message, and we store it in just a single database, and we know we're eventually going to
replicate it. What if other two crash or something happens at what point we can safely tell the Publisher? We acknowledge
that we received the message, and it's been stored. Should we store it a single time? Well, we should definitely start at
least once but should we wait until it's been stored in two replicas or should we wait until it's been stored in all three
replicas, because this is a trade-off between latency and fault tolerance. If we only store it in a single replica before 
sending the acknowledgement, then we are lowering the latency, but that's also not as good for fault tolerance. Because
we have not stored it. We have not replicated that yet. So at some point the database could have crashed or been exploded
or something caught fire, and then we didn't replicate it. But we still sent the acknowledgement. That is not good. 
We can increase latency, we can wait a little bit until that message has been stored at least two times that's good for
fault tolerance as well because we know we replicated it in two different databases, or we could wait even longer on average 
until it's been replicated three times. This is just a trade off. Depending on the throughput of our system, and what we're
trying to optimize for, we might be willing to be flexible on this number. Now one last thing is Pull vs Push based
delivery, when Subscribers receive messages we can do it two different ways. We can make it pull based which means thet the
subscriber will actually be pulling messages from our pub sub system. The Subscriber will be one initiating the request it
will say. I'm ready to precess more messages. Let me make a request. Let's see if there's any messages ready. One obvious
downside is what if there aren't any messages ready? Then this subscriber just made a request for no reason, and it doesn't
even know when the next message will be ready. It could not be ready for hours. What's going to happen in the meantime?
Depending on the cadence that this subscriber makes requests on the frequency. It could be every five seconds. It makes 
a request, that's going to be a lot of wasted requests, or we make it a lot slower. We could make it 30 seconds for, and
then the Subscriber will send a new request. The downside of this is that is may increase latency. There might be a new
message here, but we won't see it for another 29 seconds because we have to wait. Obviously this is slower, but the benefit
would be that the Subscriber can actually process data when it sends the request. This might be more suitable for 
Batch processing. Push delivery the messages will be sent from the Subscribing forwarder to the Subscriber. As soon as the
message is ready. Now the downside here is that what if the subscriber is not ready? Well, that is fine. The Subscriber 
forwarder will send the message. It will never receive an acknowledgement from the Subscriber. Therefore, maybe
10 seconds later, it will try to resend the data. The downside here is that if it just keeps resending the message and 
this Subscriber is not ready. There's also a lot of wasted time and if too much data is sent, it could end up overloading
this subscribers. Thought they'll probably never crash. It's just that they'll just keep getting sent messages, and they
can't really do anything with them until they're done processing the current messages that they're on. Now the good thing
about this is it can minimize the latency. If we're going for stream based processing where we want the messages to be
sent to the subscribers as soon as they're ready. This is the best thing, and generally it's not like you have to decide
on one most messages queue systems will support both pull and push based message delivery. 
If you have a persistent data, you definitely end up needing a database type system. It's worth mentioning that Kafka which
is an event processing message queue. It ends up storing messages in something called Write Ahead Log.
In between Publishers and our Pub Sub system, there will be generally a load balancer because these Publishers could be 
located all around the world, and we might want to load balance them and send their request to the nearest Publishing
forwarder, and similarly with the subscriber forwarders and the Subscribers as well. Ideally, we'd have some load balancing.
To store information about which subscribers which Subscriptions we've created, how many subscribers we have which topics
we have in like the relationship between them. We might want to use a really highly available key value store like Zookeeper
or etcd, and even more importantly with this metadata. We probably want another service like a control place or a coordinator
service to manage that, like manage the state of the Subscribing forwarders the coordination between  assigning
Subscribers and Forwarders and manage the health of our messages' database. It can determine how to Shard them and partition
them as we need to and possibly this will also manage any requests we receive to update the state of our message queue.
If we want to change it from pull based to push based and other things like that. 
Kafka is a very powerful modern message broker.


# Distributed Key - Value Store

## Background

A database can be a relational which is typically SQL databases. NoSQL databases though are a lot more flexible. There's
a lot of things that can go into this. Of course, there can be graph database. There can be document database like 
mongodb. There can be more traditional key-value stores like the original Dynamo. Dynamo DB is kind of hybrid between
document databases and key-value stores. There can be wide column stores like Cassandra which kind of also fits into the
key-value definition at least at a high level. There's a lot of complexity within Cassandra. We could even be talking
about something like zookeeper which isn't a traditional database, but it's used for coordination. It's considered a key
value store. We could even be talking about redis which is an in-memory key-value store, but this can be flushed to disk
which in some ways does make it a database but let;s say we want to kind of focus on a more traditional document database
or key-value store like Dynamo DB or even Cassandra.

## Functional Requirements

We want to be able to support a get operation given some key. We should be able to get the value we want to support but
where we are given a key. We want to map it to some value whether it's a document or list of values or something like
that. And we also want to be able to support deleting an object from our key value store.

## Non-Functional Requirements

We want this to be durable that goes without saying if we're designing a database, let's say we do not need to support
acid and that kind of also goes with the fact that we're using NoSQL databases. So we do not need to have acid 
properties. Well,we do need to have durability. Of course, that's the D but mainly consistency does not make too much sense
in the context of NoSQL databases. We do not really have relations and foreign key constraints and things like that. So
that is easy. But Atomic transactions, we will not need to worry about those as well. Isolation remember with SQL databases
isolation means that if two transactions are happening at the same time, they will appear as if they happened in a particular
order. So why that's important is we won't get conflicts with SQL. We can lock a value like if this transaction is going
that row that this transaction is running on maybe locked and then once this transaction is completed then the next transaction
can run on that same piece of data. We will not run into conflicts, but with NoSQL databases conflicts are a thing. And
lastly we need our database to be scalable. That is also a very important part of NoSQL databases, generally scale is one
of the big important things that you can see that we are gonna to have to touch upon replication which is a way to handle
scale. As well as Sharding, horizontal partitioning.

## High-Level Design

First of all how we are actually going to be storing an indexing data. We know what SQL databases they generally use 
B-trees or B plus trees and even some NoSQL databases use this as well. Other indexing strategy that NoSQL databases 
sometimes use, for example Cassandra which uses LSM trees. This is basically a way to optimize for writes for very quick
writes, but not necessarily quick reads. And another thing that's very important to cover is replication. When we're talking
about scaling our NoSQL databases we of course have to replicate data. We can't just hit a single node and expect to be 
able to store all of our data, but replication is not easy. There's a lot of problems that you run into. We are going to 
have to consider the CAP theorem, and even more than that when we are talking about our replication strategy.
When it comes to dealing with scale not just high traffic but maybe a large amount of data. We can't expect all of our
data to be stored on a single machine. We might have to split it out and then maybe put half of our data on one node and
another half on another node. That's going to be horizontal partitioning. Don't forget about vertical partitioning which 
is less powerful. Vertical partitioning is basically we actually keep all of our data on a single machine, but maybe our
table if we're thinking of things like with SQ you have a table, but you actually split that table not like where
half that data gets sent to one machine and then half of it gets sent to another machine, but we actually split like
table itself gets a little but smaller that can make queries faster, and it's a bit easier than horizontal partitioning.
But obviously it is less powerful. It's analogous to horizontal scaling and vertical scaling where you can just take a
single machine and then add more resources. It is easy thing to do, but there's limitations. With horizontal scaling you 
can infinitely scale things if you have a good strategy. When we talk about horizontal partitioning we are talking about
sharding and that's why horizontal partitioning is just a lot more common to be talked about than vertical partitioning
even though sharding is not easy to implement, but we will be talking about some the challenges that we will run into.
Also, we want to be able to cover what happens when a node fails, we know that's going to happen. When you have many
machines you can't expect all of them to be running forever. If a machine goes down we have to have a strategy for
recovering and how our system is going to behave if something like that happens. And lastly we talked about how if we
don't have isolation with writes, then we may end up with conflicts. We have to have a way to resolve those conflicts,
and have our system be able to handle concurrent writes.

## Design-Details

How we are going to be storing an indexing our data, and we're going to be using a LSM tree which stands for Log Structured
Merge tree. This is used by databases like Cassandra which can allow this to optimize for writes fir very quick writes.
The biggest reason is that writes are batched in memory. What does that mean? If we have a client our client, our client is
most likely be a server, is sending requests to our database system. That writes is actually not going to be immediately
written to disk, that writes is actually going to be written to memory, in what is called a Mem-Table. Periodically as those
writes are written to memory, eventually they're going to be flushed to disc in what is called SS-Table. You can see why on
average as let's say we write 100 records to our database system and if all 100 of those go to memory, that's going to
be pretty fast to write and maybe after a hundred messages our memory will filled up, and then we flush that to disk in a
SS-Table. You can see why this is one way to improve speed. But what is SS-Table? As we write data, this is a key-value store.
Every key is what's important, every key is what identifies the data itself. So when we store that data, ideally we should
be able to sort it based on the key-value because when we want to read data, we want to be able to find it within the 
SS-Table. So in SS-Table actually stands for Sorted Strings table because it is sorted based on the key-value of the data
that we're storing. When we search for it, we can do something like binary search. That what's going on at a high level, and
with Cassandra the recommended SS-Table size is to keep it within the hundreds of megabytes. A 100 or 200 megabytes but to
it that small, and we can additionally create more and more SS-Tables. It doesn't mean that our database is limited to a
hundred 100 megabytes that would of course be a problem, but we can have more SS-Tables. Ideally keeping them small maybe 
within the key-value that we have, we'd be able to immediately check the entire range of values in sorted strings table,
does not have the value we're looking for. We do not even need to run binary search on this one at all. We could look at 
the next SS-Table, something like that. Now that's going on with LSM tree at a high level. That's why it's generally faster
for writes, but reads actually can be a problem, because in the worst case we have to search through a bunch of SS-Tables,
and possibly another problem that you might have identified if we're initially writing to memory and then batching that,
then flushing it to disk, what happen if our system crashes, then all the writes that were written to memory that 
weren't flushed those would go away, and this problem is solved by taking every write and also writing it to a transaction
log. Now imagine if we want to actually update a value if we want this to be write optimized. Update a value you have to
read that value first. So if we're trying to optimize this for writes, but not necessarily for reads. If we have to read 
the value anyway to update it then that's kind of a problem. So how we can get around this with LSM trees. When we want
to update data, we just create a new copy of that data. Well not a copy of it but don't overwrite the existing data. The
old value actually is still stored in some SS-Table somewhere, because we will have many of these we may not need to find
that value though. We'll just immediately write the new updated value to memory and then eventually it'll be flushed to
disk somewhere, can be to a new SS-Table and that old value still remains in the previous SS-Table. So you can see that 
this is another way to optimize for writes. But if we are storing old stale values, then that is going to continue to take
up disk space and even when you delete values, you don't actually overwrite the value in an SS-Table, because SS-Table
are immutable. No data can be deleted or overwritten. If we wanted to delete a record, we would create a new value for it
and then Mark that value as deleted. So we'd say this key value whatever, it happens to be something that is now mark
for deletion. That means if we ever read that value, we will say that well this value shouldn't exist anymore. So that's
what we're going to tell the client. The value doesn't exist. Even though it probably is still stored somewhere on disk.
This is called a tombstone to delete something in this way without really deleting it. There are some optimization
strategies where there is clean up done where the SS-Table are merged and compacted, where if we have duplicate values
those duplicate values will be removed if we have values that are deleted those will also be removed. LSM trees are commonly 
used in NoSQL databases like Cassandra. That we mentioned as well as Google Big Table, which Cassandra followed a lot of
the design principle, that Big-Table and AWS Dynamo, set out in their white papers. 
Next let's talk about Replication, because this not only provides us the ability to actually scale, but this also provides
us some Fault Tolerance. If we have data that's replicated across multiple machines. We are less likely to lose all of that
data, even if the machine was destroyed. And especially if we can replicate across multiple countries and multiple regions
and zones, that would be even better for fault tolerance. But let's not forget that with replication there can be problems
namely with data consistency. What happens if one replica does not have the most up-to-date data that was written to
another replica. Well to review, to better understand these problems. We have CAP theorem, which tells us that if there
is a partition in the network meaning that for some reason, two nodes are not able to communicate with each other.
Originally, they are both up to date, but maybe one of the nodes gets written a new record. Now we changed one record.
One node has updated value but for some reason can't copy data over to the other node. They become inconsistent. And if
somebody were to read data from this node, they would not get the most up-to-date value. What we could choose to do at this
point is say well not updated node should not be accepting any read requests and that would basically lower our availability,
because not every node on our system is able to respond to requests. Or, we could say, yes allow this node to respond
even though it's am inconsistent State, because we know eventually it will get written to most up-to-date value. This is
the trade-off with CAP theorem and additionally with the extension (PACELC), even if there's not a partition between those
nodes if they are located somewhere else in the world. We know that to take any updates to this node and then send them
to other nodes is going to take time. So maybe once that write comes in one node, what we can say is don't allow this
node to respond to requests until we've sent that data, and it might take 50 milliseconds. So for 50 milliseconds, other
node would not be available for reads. But could do something different. In this case, we are favoring consistency. That's
the choice we have, even if there's not a partition in between two nodes. What we could also do is favor latency, which 
would allow us to immediately read from not updated node, and we do not have to wait an extra 50 milliseconds for that
data to be copied. In this case, we are lowering the latency to be as low as possible. Even if we get an out of date, stale
value. Maybe 50 milliseconds later, we'll get the most up-to-date value if we send another request, after updated value
has been copied over new node. That's what PACELC theorem states, that there is a trade-off between latency and consistency.
Even if there is not a partition in our Network and many databases including Cassandra choose to lower the latency. 
This is kind of going after eventual consistency model, which usually makes sense because we want our database to have low
latency and to be able to handle as much traffic as possible. We don't want our nodes to not be available just because they
don't have the most up-to-date value though. It does depend on what type of application you're designing. Sometimes, you do
need most up-to-data value. With replication there's also a concept of which one of nodes is the Leader and which one is
the Follower. We just have a single leader then all writes are going to go to that node, and then it's going to become a
bottleneck. But to be able to scale even higher we can have a leader-leader replication approach, and what Cassandra does
is leaderless replication. Suppose we have three nodes, if we are given a write request any of this three nodes would be
able to handle that. So it is sort of a very decentralized approach. This is also pretty good for optimizing writes.
But again we run into a problem of consistency. What if one node has a newer value, but other nodes do not yet have that
value one way to resolve this is to talk about this concept of quorums. In our system we have N nodes in this case N is
equal to three. We have two types of quorums. We have a read Quorum and write Quorum. A write Quorum is basically the 
minimum number of nodes in our system that have to agree upon a write transaction before it's accepted. So for example,
take W equal one, that means we have a quorum of one that means if we wrote something, and it only was written to a single 
node eventually we know that the data would become consistent. Ideally we don't know exactly how long that would take. But 
even if the data is written just to a single node. We consider the data to have been written but this obviously lowers the
consistency, the lower this value is the lower the consistency of our system, because if we get a read from not updated
replica, we will not have the most up-to-date value. What happens if we go the opposite route and set W equal to the number
of nodes. Right now we have it set to the smallest value possible, which is one. We can't really set it to zero that 
wouldn't make a lot of sense in this context. But if we set this to three, we're basically saying that data has to be 
written to every single replica before it's accepted. Now the downside of this probably is going to be as we discussed
earlier, that this is going to take time. So this is going to increase the latency of our system. But it's also going to
result in a very strongly consistent system, because if every single replica is consistent, then we are in a very consistent
state. There are some database systems including Cassandra, which we've been discussing mostly up until this point,
because there's a lot of interesting design decisions as well as Cassandra being open source. There is this concept 
of Tunable Consistency. With Cassandra, you are allowed to set the Quorum value. You can optimize Cassandra to be strongly
consistent, or you can optimize it to be highly available and lower the latency. So if we allow the Quorum values to be
set we are also allowing for tunable consistency. Also, it's worth mentioning that R stands for the read Quorum. And  
similarly R equal to one means that at least one node has to agree upon a read value before it is accepted. If we set our
R to three, then all three nodes would have to agree on the value that is read. Otherwise, the transaction would be
rejected, or we would just be waiting for all three of the nodes to agree. MongoDB is also another open source database
system, that allows for tunable consistency. When it comes to Partitioning for maximum scalability, we of course want to
use horizontal scaling. So we do want to Shard this based on some Shard key whether it is specified. Maybe we can design
our system to allow users to specify what ths Shard Key is. MongoDB allows for this and many other database systems. 
We could also have an automatic Shard key to allow for splitting up the data. Witch sharding we will be breaking up our
data. So our data will be split across multiple nodes not only replicated. W know that our system is going to replicate the
same data on every single node, but data is going to be split. So a third of our data could be one each partition. Our
system should be dynamic, meaning that we will be able to increase the number of partitions and decrease the number of
partitions as our data grows, and a good way to be able to handle this is using consistent hashing, because we know
that will minimize the amount of data that we have to move between partitions as we increase the number of partitions or
decrease the number of partitions. Think of our data nodes lying on a ring. Values around this ring are 360. Any key value
that we get for a data record that we're storing is going to be hashed using some hashing algorithm, and it's going to end
up being a value between 1 through 360. Basically an integer, and that integer will correspond to somewhere on grid.  
Every value will correspond to somewhere on this ring, and determined which part of ring ends up it will go clockwise to
the nearest node. What happen if one of nodes goes down. The benefit off consistent hashing is that all of are still
going to correct nodes, but all values between working node and failed nodes will go to nearest partition clockwise. Problem
that now one partition has two / thirds of the data. Our data has become skewed. It's disproportionately on a single
partition, and we would run into similar problems, if we added some partitions. A good way to get around this is to use
Virtual Nodes, even though we know we only really have three physical nodes. We will create a bunch of virtual nodes.
The main benefit of Virtual Nodes is that if one of these nodes goes down, in theory they should be fewer data
disproportionately going to a single node. Basically taking Virtual Nodes and randomly placing them, can be a strategy to
mitigate a single node getting too much data. This strategy is actually used in a lot of systems including Cassandra
Dynamo, and even Vitess which actually runs on top of My-SQL. Virtual Nodes are a very common thing in the industry to use
consistent hashing in this way. Now, let's briefly talk about Node Failures. What happens if one of our nodes in the system
goes down. And first of all, how do we even detect if a node has gone down? There's many different ways to do this in the
industry in many different ways that different database systems have handled it, one relatively simple way, is to actually
use some external service like Zookeeper. So we'll actually be storing it in a centralized location. So possibly every single
node could send a heartbeat to zookeeper. Every maybe 10 seconds or 30 seconds. It could send some indication to our
zookeeper key value store, which would indicate that this node is still alive. It's still functioning properly. And then
maybe we can have some centralized control plane, which allows us to handle when a node goes down or our control plane will
be able to handle that in some way and possibly redirect traffic to another node. More decentralized way, that Cassandra does
it is to use something called gossip protocol, where we don't have a centralized control plane or even a single leader
node. All of these nodes can act as the coordinator (the control plane). But at a high level what gossip protocol would do
is every single node, would have a member list. Like in this case we have three nodes. One node B could keep track off
member list of nodes A and C. So A and C would have to record a timestamp of these sent a heartbeat. What would happen is
all of these nodes would possibly send a heartbeat to a couple of neighbors or just a few different nodes, and then those
nodes would propagate the heartbeat across the entire network of nodes, of our entire database system. B eventually will
get up-to-date timestamp or heartbeat of A and C, and then A and C can be keep track of other nodes as well. But the whole
idea is that the work is being divided among all the nodes, and possibly after a while is one node sees that other node 
has not sent a heartbeat in some predefined amount of time. Maybe that a threshold is one minute after one minute one of
node has not sent a heartbeat. That means that node must have gone down, but it's not enough for a single node to be able
to verify that one node has gone down, because what if this node isn't the node that went down. Maybe other node has go down. 
That is why it hasn't received a heartbeat from first node, because this node has been cut off from the entire network. 
To detect if a node has gone down, maybe another node somewhere is also keeping track of node heartbeat and sees that
node has not sent a heartbeat in a while. So these two nodes can independently verify that node has not sent a heartbeat.
Therefore, it must have gone down. The reason we divide the work like this, is because alternatively we could have it so
that every single node in our system is communicating with every other node. This is growing N squared, so it's not very
efficient. That's why we don't want to have all these communications because we could have thousands nodes. Do we really
want every node to be communicating and getting sent a heartbeat from every single other node? That would be very inefficient.
That's why we take a shortcut and use gossip protocol and that is what Casandra uses. H-Base is another No-SQL database
and this actually uses a centralized approach using Zookeeper. Now let talk what would we do if a node actually goes down.
There is concept of Hinted Handoff, where if we have two nodes, originally both of them are accepting traffic and accepting
writes and then communicating this writes between each other. If one node goes down all writes going to down partition
will possibly be directed to another partition, at least until failed node comes back up, and when it does come back up
all writes that we originally sent would get flushed back to other instance. Lastly touch on how we would be able to handle
concurrent writes. The simple way that Cassandra does things is that the last write will always win. What Cassandra does
is keep track of data itself and timestamp. Values with the most up-to-date value will always win. When we read form out
system, we would expect value to be with the biggest timestamp. We would resolve conflict that way. This is the simplest,
most intuitive way to handle concurrent-writes. There is another way that other database systems use, which is more complicated.
The original Dynamo used something called Vector Clocks which makes things more complicated but use a similar concept of
versions of the same value. A Vector-Clock is basically a pair of values. So for a particular node, we have a version for
every single value. At a high level, instead of maintaining timestamps, we have versions. Vector clock can get pretty 
abstract, and the resolution though using these Vector Clocks. There is no simple way, typically with Dynamo, the client
was expected to handle the resolution. So there's not like a straightforward way. That's why for our design, prefer least
write wins. Even though it is not perfect, it is more simple and Vector Clocks are definitely more abstract.


# Design Google Maps

## Background
Most apps like Uber and Lyft use internally a service like Google Maps, to handle coordination, because these systems are
so complicated. You wouldn't want every single application that needs some type off mapping or navigation to have to 
implement their own from scratch. So they use a service like Google Maps usually. At it's core Google Maps is about being
able to travel from point A to point B, whether you're going to be walking or driving, most likely it's about navigating
from one place on the earth to another place. Usually some trip will be maybe 30 minutes if it's a short drive or a walk,
but a few hours like 4 hours, or it could even be a cross-country trip, but if that's the case, you probably won't get it 
done in a single day, but it's important that our service makes this route as short as possible, but maybe distance isn't 
the only thing, we want a short distance of 10 kilometers, but a 15-kilometer journey is actually faster because 
10 kilometers trip has more traffic. Or maybe the longer route we're able to take a highway, so we can drive faster. 
And of course we'll be using a phone to handle this. We're probably not going to bring our entire computer that we could
but most commonly a phone is what we'll be using. An this phone will support a GCP so that we do have our location tracked
,and we can dynamically update the route as we get closed to our destination. And one other thing that is worth mentioning 
is this concept of Geocoding. Humans think in terms of physical addresses. Google's address is something like 1600 amphit.
That would actually be mapped somewhere on the globe, which is a sphere as we know and we kind of think of coordinates on
this sphere. In terms of longitude and latitude and this kind of makes up our coordinate system. Tou can think of the 
longitude and longitude being some type of decimal. And these values correspond to some position on our grid which is a
sphere. When a user wants to navigate somewhere, they're going to give some source, which is the starting position which is
as address and then some destination which is also an address, but we know that we can actually convert those nto integers
which we're going be using internally.

## Functional Requirements
There is a lot of complexity with Google Maps. So we're going to keep things simple. We want to be able to navigate from 
a source to a destination that is user specified. And this should give us a reasonably good route. It doesn't necessarily
have to be the fastest but based on the traffic information. We should try our best to find the fastest route that we can.
We should track the users location because of course as the user is looking at their route on their phone, they should see 
updates in real time ideally, so as they get closer to their destination, they're opn their phone the route should reflect 
that. We should be able to give an ETA (Estimate Time Arrival). Basically an estimate from our side using the traffic data
that we have available and most importantly we are going to assume that we already have the map data. We don't actually have 
to build that we assume that we already have that map data, and it's something on the order of terabytes. Maybe 5 terabytes
and this kind of gives us all the data of the roads and how they're connected together, and all physical addresses on the
earth.

## Non-Functional Requirements
We are going to assume that we are dealing with a pretty massive scale. Google Maps has about a billion daily active users.
So we are dealing with scale but the most important thing is accuracy. We definitely don't want to give an incorrect route
but assuming that we already have the map data that's not really going to be the problem. Of course, we want to have as 
good availability and reliability as possible. But there's nothing super unique about those non-functional requirements.
Latency is somewhat tolerated when it comes to generating the route. It's okey if it takes us a few seconds to generate the
route, these ultimately after the user gives the source and destination. It's not like they are going immediately start driving
one second later. If they have to wait five seconds, are they even going to notice, probably not. But when it comes to real-time
updates. Updating the user's location, we probably want that to be fast at least if they're traveling pretty quickly.
As you're driving you want to hear the voice assistant say turn right in 500 meters. You want to know before you missed
the turn. Good thing is that we do not need to worry about the latency in that case, because user location updates at least
client side is something that we want to have low latency.

## High-Level Design
Spatial indexing. It's about querying spatial areas like Maps as quickly as possible. Oversimplify things, lets think of
it as an X-Y coordinate system, which we are more used to than latitude, longitude. The idea is the same even though this 
is two-dimensional grid, and we know that the Earth is three-dimensional but the surface is two-dimensional. So we're 
going to think of it in these terms. What would happen if we tried to store spatial data, this ia a map and we kind of 
break it up into a little pieces. What would happen if we tried to store this in a relational database, or even a
non-relational database? We would want to query still based on some key. We could index based on the coordinates. 
The map of the world. We could index it based on the X and Y coordinates, but what exactly does that mean like if I give
you exact X-Y coordinates. Let's say 100 and 50. This is my coordinate and this corresponds to some spot, just one point.
When we are looking for a route, we want a little piece of the world. We don't just care about a single point, we care 
about two points, and we care about connecting them together, and iff we give a very precise point, most likely it's not
going to be this simple a longitude latitude could go out to multiple decimal places. So are we going to store exact point
for every single decimal places? We don't need to do that. One concept when dealing with spatial areas and Maps is to 
break up the entire space into these so-called tiles, which at their core are just breaking it up into squares. Why do
we choose squares? Well what would happen if we didn't do that? What would happen if we choose long skinny rectangle?
Technically that works but most likely when you're at a position you think of thinks in a let's say one-mile radius, you
may want to travel within that one-mile radius. So it's kind of like a circle, but it's a bit complicated to break up this
space with circles. So to estimate that we use squares, and the good thing about using squares is we can do it recursively
which is even better than using these rectangles. Not many areas or shaped like a long skinny rectangle, and when we start 
to think of things in terms of squares, it gets a bit better. We can break this up recursively actually. So this was the
entire world, we now have four quadrants, and we can break up each of these as well. So right top could be divided into  
four portions, and then each of these portions could get broken down further and further until maybe each of these little
squares is just a one-by-one mile of kilometer square. This is a common indexing technique that's used, it is used in
data structures like quad-trees, and it's also used in something called GeoHashing, which conceptually is very similar to
a Quadtree. Geohashing here would basically mean, each of these quadrants has a label. When we take one portion and then 
start to subdivide it into four more pieces, each of them starts with "0-0" for top left quadrant. We can recursively
keep dividing this, conceptually it is pretty similar to a quadtree, but the good thing about Geo hashing is you actually
don't need a data structure. You can just do an algorithm. Conceptually we are given some physical address and assuming
we have a way to map physical address to probably using a GPS coordinates or if we have like a source and destination in
physical address. We could have some mapping with that to the longitude, latitude and then using that longitude latitude
which could be a very specific point. It could be one little point in quadrant. And we're not necessarily storing every
single decimal place in a database or data storage solution and not that we would need to anyway, if we're storing a map.
We probably want not just that point but we all area around the person, so we would use a spatial index like a quadtree or
maybe geohashing to very quickly find all the information. Quadrants in map because this is how locations will be represented.
Two cities will be represented as nodes. And then we have a bunch of nodes in between them. This is kind of a graph, we
might be storing a graph database for example, but to index that graph and to index the portions of that graph we are using
spatial index like a quadtree or geohash. And so when we use the longitude-latitude of a user to query our database, we'd
get a square with maybe a one-by-one kilometer radius and that well give use all the nearby roads, for the person and
assuming they gave us a source and a destination. Source is in other quadrants than destination, and we want to find
the shortest paths between these, we would run a graph algorithm like the shortest path, Dijkstras algorithm. We can 
conceptually start in one quadrant and run Dijkstras, but we do not need to load the entire map of the entire Earth into
memory, to run that algorithm. So what we would probably do is just have this tile and Dijkstras is based on breadth first
search. So we'd go n all directions. And then from this one tile we'd want to look at the neighboring tiles until eventually
get the shortest path. Whatever is the first path that reaches our destination that would be what we return to the user.
So you can imagine that spatial indexing is going to make this faster just because of the nature of the data we're storing.
It is not exactly relational data. It's not like we're doing a bunch of complex joins or anything like that. So spatial
indexing is one very key concept of being able to find these tiles quickly and then to Traverse the tiles. The tiles 
themselves will probably store a graph based database, at least that's one possible solution. And also it's worth mentioning
that spatial indexing is actually something that's supported the exact implementation can vary, but it's actually supported
by many database management systems. Both cloud based like Dynamo-DB, open source like PostgreSQL, and it's something that's
pretty common.

## Design Details
We have two services one is the route service which is what's first going to be used to actually generate the fastest route,
which could be either the shortest route or it could be the shortest route taking traffic into account. But the ultimate
thing is that this will return a route to the user which will then display on their device, and then they can use it,
this is really the core functionality of Google Maps. This is the most important thing, because you don't really need to
dynamically update the user's location because you don't actually need a location service though. We are going to be
tracking the user, and we're actually mainly going to be using this data for traffic information. So that if we have a 
billion users that will give us quite a lot of information on which roads have a lot of traffic at a certain point in 
time, and we can use historical data as well. But Google Maps actually has an offline mode you may or not have used it.
But basically once you have that route, you don't need an internet connection. You just need the GPS in your phone and that
will tell you where you are in terms of your route. Like how close you are from your destination and that will actually
be sufficient. So that's what we're going to mainly be focusing on, the route service and let's assume that our route 
service is able to hit the graph database and remember we already have all route data there, we don't have to worry about
generating that, we're just choosing to store it in a graph database because that is one efficient way to store it to be
able to determine the shortest paths and this caching layer in between is not super important, but there might be some
common routes between major cities major metro areas. Maybe that will make it faster to generate certain routes or maybe
portions of certain routes. But this isn't really the key component in this design, and one way that we could actually
Shard Graph database, is based on the first few digits of a geohash for example, because we just need similar close 
together tiles grouped together in Shard. So if all tiles fo quadrant were on the same shard that would be fine. If we
really need to generate a super long route. That probably won't be the biggest deal if it takes 20 seconds to generate
this type of Route wouldn't be the end of the world. If you're traveling, from one country from another, most likely we 
just take a flight for that. Anyway, you're probably would not need Google Maps but Google Maps but alternatively each tile
could have all the route information, and we could actually store all information for each tile in a NoSQL database like
a document store, and each tile would have to be small enough so that it could be stored in a document, but then each tile
will reference all of its neighboring tile, so that we can group them together, and the way we would be able to index that
possibly we could use an in-memory quad tree, and that quad tree could be stored in a caching layer while we wouldn't 
probably call it a caching layer, but we definitely store it in memory because the size of that quad tree isn't really
the major data we're storing because the routes themselves are going to be store in the nosql database. That's kind of
an alternative, graph database here makes enough sense. Quad tree would only help us in searching for the key of every
single tile. Once we have that key, just use it as like a hash map key in the nosql database. When you have a key you
can instantly find the document assuming that we're using something like Dynamo DB which is sort of a document database,
and a key value store. Now, let's take a look at our location service. All location data is going to need to be written
to storage, but we don't need to write it to our graph database. We're actually going to have a separate store for this
and most likely we would use a nosql database, something that can handle a lot of writes like time series data because 
that's kind of what it is. Common solution for a problem like this is to use something like Cassandra, which can be optimized 
for a heavy writes. So we'll be able to have historical data o which roads have how much traffic at each point in time, like
each day, each hour stuff like that will possibly be helpful for our route service to not only find the shortest route, but
also be able to find the fastest route based on the amount of traffic, but because of the massive amount of data that 
we're going to be writing, we would not directly read from this. We would probably have a caching layer in between, and we
probably wouldn't even want to actually read the data hear itself. We would probably want to take the location data maybe
in real time that would be ideal and then have some type of stream processing between. At a high level we could have a
message queue, like stream processing Kafka would be pretty helpful. AWS they have something similar which is called AWS
Kinesis, but basically we would be feeding the data into another data store solution, and likely we'd be doing some processing
in between, but basically we would aggregate the traffic data in some storage solution so that our routing service not only 
has the route information, and is able to determine the shortest path based on the graph, but also based on the traffic.
So at a high level you can think of the routing service is getting the graph which could look like a network of nodes.
And initially, we just have some weights which tell us the distance, but taking the traffic data into account, maybe the
weights of every single edge actually change. Maybe there's a lot of traffic, so we bump one edge, but in case of lower
traffic in other edge is decreased. This is why traffic information would augment our graph which would augment the
shortest path, Son now let's go into the details, even though we kind of already discussed many of them. One thing worth
mentioning is the location data of every user. What would the schema of something like that look like. Well, we'd want to
have the used ID and periodically, maybe every few seconds we'd want to send the location update of the user as they are
moving. Maybe if they're stationary we can somehow detect that on the client side, and not send any data, or maybe we will
still choose to maybe that tells us that they are stuck in traffic. We would send the user ID. We'd send the timestamp of 
course, because that's relevant, that tells us how fast they're moving as well, and we'd send the longitude, latitude but
basically their location data, which we can intelligently do something with that. We can encode that into some type of 
index for fast retrieval. If we want to send Real Time updates we can use pooling every few seconds, we can send a request
from the client to the location service or maybe a better thing would be to have websockets here for real-time updates, and
we can also listen possibly for real-time updates to maybe traffic conditions changes and there's a faster route for us. 
Maybe the routing service could intelligently determine that, maybe we'd have another service separate from the route service.
Because this is used to actually generate a route. The location service could actually send information back which is very
much possible with web sockets. Let's say we give a source and a destination to the route service. We generate a route. 
It happens to be the fastest route based on traffic conditions. But what exactly are we going to be doing with a route? 
This is basically the instructions every turn that we have to take every freeway exit and stuff like that to get to our
destination. But assuming we're going on something like a one-hour drive the instructions themselves don't really give us
a picture, do they? Picture is the actual map itself when you're going on a drive, you have that map in front of you, and
you're probably using it though. Sometimes maybe you just listen to the Voice instructions. Maybe that's good enough for you, 
but we probably want to be able to support this. So when we think about tiles. We don't just mean the routes themselves, 
but we actually have a picture of the map. But where is that picture going to be rendered? Where's it going be generated,
and does it even need to be generated? Because I mean isn't that the whole point of a map that it's static or if it does
change it obviously is going change for everybody. So this static map is also going to be a picture, so it's image data. 
What tells us we can use Content Delivery Network to store this image and not just one image. Actually we're probably going
to be having many images if we just had a single image for the entire world, and we sent it back to a user, that would be
very large image. We definitely don't want to do that. We are going to break it up into pieces we can do it the same way we
did with spatial indexing so to actually fetched those images the user will make a request to the CDN which is actually 
pulling images from an object store. It makes sense to have a CDN, because users in a certain area of the world are going
to be using maps from that area. They're going to be needing routes for that area and most likely this should be pull-based
not push-based from the object store. How do we index this based on the location because CDNs don't really have that type
of logic built into them. How do we know which images to fetch so that we get the route that we need? Well, let's suppose
that when we generate the route in the first place, and that actually does give us all references to the images that we want.
The images themselves are encoded maybe the URL to fetch one of the map images. One of the tiles is encoded, so it could 
be something like "my image 0010", and this String here is the geohash encoding. That encoding is not build by the CDN,
but maybe it's fetched form the route service or a different service and that's what allows the user to know how ot at
least allows the user's device to know which requests to make to fetch the correct tile image, and then that image can be
rendered on the user's device, and it's still okay. The CDN still does speed things up for use because the route service 
will not really have to send much data back. It may have to make a query to a quad tree, or maybe we'll have to run geohashing
but the data itself that it sends back to the user will be a small amount of data, but when the user makes a request to
the CDN, it will be sent an image back which images are much larger than string though. We want to make sure that images
are stored on a CDN so that they get to the user as quickly as possible. This definitely solves most of the core things that
we were looking for in terms of latency. That wasn't really our priority, but we did design this in such a way that we
will get relatively good latency with how we cant stored our data which is really going to be the bottleneck and the routes
themselves should be pretty quick because we will be able to generate traffic data and this is extendable we could have
actually focused on this location service tracking portion, and generating traffic data, and we could do a lot more stuff
with this and gone into more details and in terms of scalability, we kind of emitted the fact that we'll be having load
balancers in between all of these services and a bit on how we would scale the nosql database for example, and the traffic data.
Uber using hexagons for spatial indexing.


# Design Google Drive

## Background
With service like Google Drive, you can upload files, you can download those files, you can remove or delete files, this
is a core functionality, but beyond this you can do a lot more, you can edit files, you can share files with other people
and kind of manage the permissions for every file like who is allowed to download the file, who is allowed to edit the 
file, remove the file. You can also store things in hierarchy with folders to actually like organize and group your
content. Problem are going to be related to scale that Google Drive has to handle.

## Functional Requirements
We want to mainly focus on the upload and download case and removing files. We will talk a bit about editing, but editing
is the most complicated among the features, being able to edit a file. We will not focus on permissions aspect of it, like
sharing files, but we will be supporting folders, and organizing things like in a hierarchy.

## Non-Functional Requirements
200 million total users, which is probably small for Google Drive. There's probably over like a billion but in terms of 
daily active users, let's say that we have something like 50 million daily active users, and every single user is going to
be given 16 gigabytes of a free storage, which is exactly what Google Drive offers. They are subsets of users that actually
pay for additional storage, like some people might be paying for a terabyte of data or even a petabyte. Since, Google Drive
is supposed to be a user-friendly product like for consumers not for like developers necessarily. Since people which are 
storing more than 15 gigabytes will form small group, we can just assume that average user is going to have 15 gigabytes.
And in addition most people aren't actually going to be taking up all 15 gigabytes. When you get to the total amount of
storage that we're going to be taking up it is 3000 petabytes of data (200 million users times 15 gigabytes each).
What is also worth mentioning that every file that somebody uploads to their 15 gigabytes of storage. We really cannot
allow that data to be deleted. So it's pretty safe to assume that every file that's going to be uploaded is going to need
to be replicated and most likely not just in a single zone because what if there's a natural disaster, and one of the data
center is impacted. We probably want to replicate this around the world, multiple times, at least three times.
3000 petabytes is a lot, but we're probably going to be needing to sort even more that. In terms of throughput let's say
that the average person uploads two files per day, and on average each one is like 10 megabytes, but that might be even
higher especially if they're going something like a text file or a pdf, if it is like an image, maybe 10 megabytes are like 
videos. Read to write ratio iss two to one. It is going to be two reads for every write. So every file that's uploaded.
Let's say there's two files per day 10 megabytes each. There's going to be four files that are downloaded 10 megabytes
per each. Now in the context of Google Drive throughput is probably not the biggest problem, even latency isn't 
necessarily the biggest problem if it takes, a couple of extra seconds for a file to download. That's really not what
we're trying to get out here. This isn't like we're watching YouTube videos where you want to have a really user-friendly
experience. What's probably most important in the context of Google Drive is that first of all, it's always available.
You'd never want to try uploading or downloading a file, and it could be really important file that you're trying to download.
You would never want it to not be available to you. Because at that point you'd probably even feel safe uploading data
to Google Drive. You'd rater distort on your computer. So availability probably the biggest most important requirement
here non-functionally in addition to reliability because obviously you could upload a file and then one day it's just not
there anymore. That would be a problem.

## High-Level Design
What kind of data model, and how we're going to be storing the data. We know of course, we're going be storing two things
the actual files themselves and then probably a lot of metadata. Things like user metadata as well as metadata about these
files. Now because these files could be anything, if it's just a text file like a string, technically we could store
that in a database if we wanted to like a relational database or nosql database, but we could obviously be having images.
Videos like pretty much any format of file. So we need something much more flexible. Now the metadata of course can just
go in a database whether it's SQL or nosql probably does not matter. NoSql we will not to be needing a lot of joins. It's
going to be mainly, a key value store. That's how we're going to use it. This could be something like mongo db or aws
dynamo db. NoSQL probably will not be a bottleneck. We know we are going be storing massive amounts of data and that
amount of daya is probably not going to be stored in nosql. So how we want to store these files? We're probably not going
to be using a regular database, but we do need this data to be persistent. So the two options that might come to mind for
you is a distributed file system, something like hadoop distribute file system. And another option would be an object store.
Let's compare these two. I think there are both viable options as long as you can defend you design decisions. But thi is
kind of the major decision when it comes to Google Drive, off course how we're actually storing files. 
First of all a file system is like on your local computer is stored in a hierarchy. So going back to data structures  
and algorithms, the file system can be kind of thought as a tree not necessarily a binary tree, because uou can have a
folder. Basically file systems store things in a hierarchy. Since Google Drive supports folders, you would think that
hierarchy is going to be helpful. Which it would be and one problem would be actually scaling this file system. We could
use a distribute file system where we have multiple nodes kind of storing all that information, and we scale it.
This can definitely be much more complicated that is a downside compared to an object store, and it's less scalable, and
with an object store the scalability comes kind of for free, especially if we use like an external service like AWS S3
or Google Cloud Storage or Azure, which has blob store. Another benefit of a file system is that you can actually modify
files like you can, actually edit a file you can't really do that in an object store files are immutable, you can delete a
file and then create a new one, but you can't really edit an existing file. But since we're not really focusing on editing
files, that won't be super important for us. To actually like go in depth into and there are a potential workarounds,
especially if we were talking about small like text-based files. You could just kind of load the file and then store it
in memory somewhere and then edit it like that. You don't actually have to edit the file on the object store. And if it
is something like a video or an image, maybe we wouldn't even support editing in the first place. Now just to briefly
wrap up an object store is more of a flat data structure, even though in AWS S3. You can have folders, those folders are
actually an illusion. They are just in the name. So like you could have some files like in bin directory. There is no 
hierarchy, it can kind of be thought as a hash map where the ID or kind of the unique name of every file is like the
identifier and then when you use that name, you kind of see the individual file, you can download the individual file.
So we will be going with an object store not to mention object stores kind of provide that reliability and availability
for us like multi-regional replication and availability. At a high level we will have application service. If a user uploads
a file, they will hit the application server. Of course, we could load balance this, it wouldn't really matter which 
application server the user hit, because ultimately the application server is going to just upload the file and store it in
an object store. As a file is uploaded, we also need to store metadata about that file. Like we need definitely have 
a reference to that file because when a user wants to read a file, they will be hitting the application server. We will be
reading which file they wanted to read. We'll get the Metadata for it, and will return like the actual file itself from
Object Store to the user, and we would most likely just need a regular key value store, something like a nosql database
would probably work. We can definitely have a caching layer as well. But most likely it's not like there's going to be
a single file that's going to have that many reads. So caching isn't really the most significant part of this architecture,
at least in the context of this problem.

## Design Details
At high level this problem is straightforward, but there's a lot of details we could possibly discuss one of the first
thing is typically an object storage. We've talked about like media files, like images and videos, and generally you don't
really need permission control on those especially if you're going to be distributing them over like a CDN Network. In this
case, since these are kind of personnel files that people are storing. They expect that only they will have access to them.
And even though we're not talking about sharing files and permissions. I think it's worth mentioning that you can configure
object store, to not be accessible publicly. Like typically a user's browser would be making the request for like an image
that's stored on object store, which is maybe distributed on a CDN. But in this case we would probably want to restrict
access to our Object Store only to our own app servers. So if a user is trying to access a file, our app server will be
able to determine if they have access to it in the first place probably using key value store or some authentication token
from the user and only then would it directly read the file and then respond with the file. Now we talked about having such
a massive amount of data that we're actually going to be storing for the files while an object store can probably still 
handle the scale like S3 could probably handle the scale, but it would cost a lot of money especially if we're giving this
away for free. So one technique that is called Block Level Storage. What it does is as a user uploads a file that file
is actually broken up into blocks, and the block size is typically fixed. It could be on the order of megabytes something
like 4 megabytes might be reasonable. So every file is going to be broken up into four megabyte chunks, and those chunks
are going to be stored in object store. And so when a user now wants to actually read that same file, we have to reassemble
it using these smaller blocks and how we would know to reassemble it, and how to do so would probably be based on data
that we're storing in our key-value store. So we could have a document saying this is my file name ABC and, it belongs
to this particular user. So we would have like a user ID, and then we would have like the references to all blocks, and
then we could reassemble an individual file using all of those blocks. Now why are we doing it this way? One advantage to
this, is that if we break data up into smaller blocks. It's possible for us to do something called deduplication. That
means for example for an individual user if they're uploading multiple files, and we are breaking every file up into blocks,
but we noticed that for one file one of ths block is identical to another files block as well. And since when a user
fetches a file, all we really need to do is reassemble that file. So if we ever have noticed any duplicate blocks for a
single user, we will not store that. This not restore the same data would save us megabytes, but on a higher scale this
would probably save us more data if some users are duplicating a lot off data. Now, what would be even better is instead
of doing this on a user level we could do this on a global level for every single user. That means if I'm uploading a file
that somebody else has already uploaded to Google Drive. We don't really need to store the exact same file multiple times.
And also we don't need to store the same block multiple times either. Even if the entire file is not identical to another
file, if blocks are identical, we don't need to duplicate any blocks. SO doing this on a global level would probably
save a lot of storage which most importantly would save us a lot of money from the perspective of a Google Drive.
Now without Block Level Storage, when a user deletes a file we probably would have deleted all individual blocks.
We would have deleted the entire file, but if we're doing global de-duplication, we probably don't ever want to delete
any block. We probably don't need to because it could be used by another user. So if we delete every single block but
one of these blocks was actually referenced by another user's file. We wouldn't want to delete that because then we would
be able to reassemble their file. Now It's hard to quantify exactly how much space this would save us, but at a high level
this is kind of technique that's actually used in industry. Worth mentioning if we did this on a user level not on a
global level then maybe for every single block in our Key Value store, we would store a count of every single block like
if one block is stored two times, and then a user deletes a file then we decrement that count, now that block is only
being stored one time, and if it;s being stored zero times, at that point, we probably can delete it from our Object Store.
Now an important question is how do you actually know if a block is being stored multiple times, you would in the worst
case have to go through entire Object Store, and actually read the content of every single block. Is there an easier way
to do so, well maybe if we can create an ID based on the content of every single block, a unique ID so that, if another user
uploads the same Block in a different file, when we create the ID from that block we get the same ID, and then we would 
know it's already been stored, because that ID is probably being stored in our key value store, and that ID is what's
being used to reference the Block in our Object Store. It is called Content Addressable Storage. So we basically take the
content of something and then use the content to create an address, so we can kind of identify duplicates easier that way,
and we can easily search for that individual block. This is probably how we would want to store a reference to every block
in our key value store anyway. This is also a reason why editing these blocks is going to be tricky, and why we are not
really focusing on thet, because if you create a hash based on the content of something, but then you edit the content,
then you have top recreate a hash. That why blocks are immutable, to not mess up with others content. Another thing
worth mentioning and this is a small details is with this Block Level storage deduplication. If somebody is uploading a file
and then that upload messes up halfway through, you're uploading 10 gigabytes, but only 5 gigabytes we're uploaded, and
then you try to upload the next five gigabytes. Well, the good thing is since we're breaking everything up into blocks.
We would notice that those first five gigabytes are already uploaded. We would not need to restore those in our objects
store from we'd figure that out by our app server reading from the key-value store. Now a question we haven't addressed
yet what about folders? Should we store the folders in object storage? Or alternatively we don't actually have to store 
them here at all, because they don't really take up any data blocks or anything like that. We can kind of just store
metadata for all our folders in the key-value store, and that's probably what I would prefer, so we can have that hierarchy
in our key-value store, we can for every single folder have a reference to all of its children. And of course every folder
is going to be owned by a user like there's no data blocks or deduplication in terms of folders or anything like that,
and every folder would have like a reference to every file that the user is storing. That's all stored in key-value store.
And then that file will have information or reference to all data blocks, and that's how we can build those individual
files. Now, what about going from a file back up to the folder? Well, maybe every single file document has a reference to
the folder. So it kind of having two-way pointers, a pointer form the folder to the file, and from the file to the folder,
and we can have nested folders as well having pointers, I think kind of addresses this problem. And another thing when a 
file is deleted from Object Store. We actually never delete the data blocks from the object store because technically they
could still be referenced by some other users and for us to determine if a block is referenced by any user we would in the 
worst case potentially have to scan through the entire key-value store. So every time a user deletes file, we don't really
want to have to do all of that work, but we could still have a separate service, something like a garbage collection.
Which doesn't run synchronously every time a request is made, but asynchronously let's say maybe every one hour, or
periodically it will scan through the key-value store, find any data blocks that are not referenced anymore. And then 
delete all of those data blocks from the object store. This is kind of another optimization to save space. We kind of get
the best of both worlds with having like de-duplication, but also getting rid of data blocks, that are no longer being
used. Now another possible optimization we could make when it comes to reliability or at least we could describe further
is we talked about we would have a load balancer before out application serwer. Now what happens if load balancer goes down?
Well ideally we would have like another load balancer to take its place, and then redirect user traffic there. But how
do you know when a oad balancer goes down? Well, there's concept called heartbeat. What the load balancer that's active 
would do, is it would continuously send a heartbeat to some data store a common one is called zookeeper. It's not exactly
like a database. It's highly available key value store, similar to what we're using in design. But in diagram we would
use something like Dynamo DB, but whereas zookeeper is more commonly used to coordinate distributed systems. It's highly
available. It's highly fault-tolerant, and it's specialized solution for using heartbeats, and coordinating a lot of
distributed systems including hadoop, spark, kafka. Zookeeper is kind of service you would use with your load balancer
to know if load balancer goes down, and the second backup load balancer would be reading from zookeeper. Other thing we 
could explore, how would you want to handle being edit files, because since you can't edit blocks an objects and files
on an object store, we would probably need to handle that logic elsewhere, and then maybe add new blocks for new data,
that we wrote to a file.

