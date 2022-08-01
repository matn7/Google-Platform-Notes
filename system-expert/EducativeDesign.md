## GFS

### How Does GFS scale?

- GFS scales by adding ChunkServers to the cluster.
- The master gradually moves data from existing ChunkServers to newly added servers.

### How does GFS ensure fault-tolerance and reliability of data?

- Replication: GFS replicates data to multiple nodes for reliability and fault tolerance.
- Operation log: For fault tolerance and to handle master crash, all metadata changes written to the disk onto
an operation log.
- Checkpointing: Master's state periodically serialized to disk and then replicated, so that on recovery, a master
may load the checkpoint into memory, reply any subsequent operations from the log, and be available again very
quickly.

### How does GFS manage high availability?

- Each chunk replicated onto multiple ChunkServers to ensure high availability.
- To ensure maximum data availability, master distributed replicas on different racks, so that clients can still
read or write in case of a rack failure.
- To handle master server crash, GFS stores the checkpoint in a compact B-tree-like form that can be directly mapped
into memory and used for namespace lookup without extra parsing.

### How does GFS perform master failover?

- GFS relies on an external monitoring infrastructure to detect the master failure and switch the traffic to a backup
master server.

### What benefits does GFS get from a single master?

- The benefit of having a single, centralized master is that it has a global view of the file-system, and hence, it can
make optimum management decisions, for example, related to chunk placement and replication.

### What problems does GFS face by having a single master?

- As the number of files grows, it becomes impossible to store all files' metadata in a single master's RAM.
- Secondly, as the number of clients grows, the single master becomes a performance bottleneck as it does not have
enough CPU power to serve them.

### How does GFS ensure file data integrity?

- GFS uses checksumming to detect the corruption of stored data.
- Each chunk is broken down into 64 KB blocks, and each block has a corresponding 32-bit checksum.
- Like other metadata, checksums are kept in memory and stored persistently with logging, separate from file data.
- ChunkServer verifies the checksum of data blocks that overlap the read/write range before performing the operation.

### How does GFS decouple control flow from data flow, and what benefits does it provide?

- Client communicates with the master for all metadata-related operations like creating or deleting files,
looking up files, etc., but all data transfer happen directly between the client and ChunkServers.
- Minimizes the master involvement in reads and writes.
- While performing write operation, GFS separates control flow from data flow to ensure data consistency. 

### How does lazy space allocation help GFS?

- Each chunk replica is stored as a plain Linux file on a ChunkServer.
- GFS does not allocate the whole 64 MB of disk space when creating a chunk.
- Instead, as the client appends data, the CunkServer, lazily extends the chunk.
- This lazy space allocation avoids wasting space due to the internal fragmentation.
- Internal fragmentation refers to having unused partitions of the 64 MB chunk.
- For example, if we allocate a 64 MB chunk and only fill up 20 MB, the remaining space is unused.

### Can GFS handle small files efficiently?

- GFS can store small files efficiently as it lazily extends chunks based on the data size.
- GFS has a large chunk size (64MB), which can have its disadvantage while reading.
- Since a small file will have one or a few chunks, the ChunkServers storing these chunks can become hotspots if a lot
of clients are accessing the same file.
- To handle this scenario, GFS stores such files with a higher replication factor and also adds a random delay in the
start times of the applications accessing these files.

### What are reference counts in context of snapshotting?

- Reference counts are part of the implementation of copy-on-write for snapshots.
- Snapshots in GFS are initially zero-copy.
- This means that when GFS creates a snapshot, it does not copy the chunks' data.
- Instead, it increases the reference counter of each chunk.
- Later, when a client makes a request to write to one of these chunks, the master notices its reference count is 
greater than one.
- At this point, the master asks each ChunkServer holding the replica to make a copy of the chunk so that the client
can update the copy.
- It makes creating a snapshot inexpensive, as GFS is not making the copy of the data.
- It delays the copy until it is absolutely required, hoping that not all chunks will be modified, and hence, GFS
can avoid making some copies.

### Two primaries chunks?

```
Let’s assume C1 is the primary replica ChunkServer of a chunk, and there is a network partition between the master 
and C1. When the master notices this, it will designate some other ChunkServer as primary, say C2. 
Since C1 did not actually fail, are there now two primaries for the same chunk?
```

- If we have two primaries, both maight apply different updates to the same chunk, making the chunk inconsistent.
- To prevent this, GFS uses leases.
- C1 knows to stop being primary when its lease expires.
- The master will not grant a lease to C2 until a previous lease to C1 expires.
- Hence, C2 will not start acting as primary until after C1 stops.

### How does GFS handle data consistency?

- Master guarantees data consistency by ensuring the order of mutations on all replicas and using chunk version
numbers.
- If a replica has an incorrect version, it is garbage collected.
- GFS guarantees at-least-once write for writers.
- This means that the records could be written more than once.
- It is responsibility of the readers to deal with these duplicate chunks.
- This is achieved by having checksums and serial numbers in the chunks, which help readers to filter and discard
duplicate data.
- GFS does not guarantee strong consistency.
- GFS preferred performance and simplicity of design over data correctness.

### As GFS preferred performance and simplicity over correctness, how did it work for GFS?

- GFS optimizes for MapReduce applications, which need high performance for large files and are fine to deal
with duplicate records or inconsistent reads.
- GFS would not be a good fit for systems requiring consistent data, for example, stockbroker or bank transactions.

### Quiz

- In GFS, files are divided into **Fixed size** chunks.
- To detect data corruption, GFS compares the contents of chunks on multiple ChunkServers:
    - False. Instead GFS uses checksum.
- File naming (or renaming) operation is atomic.
    - True. All metadata operations are atomic.
- To collect ChunkServer's state, GFS master sends regular **HeartBeat** messages to each ChunkServer.
- Does GFS uses time-based leases to reduce the network traffic>
    - True. 
        - For write operations, the primary replica, after getting the lease from master, becomes responsible for
        maintaining a consistent mutation across replicas until the lease expires.
        - This reduces the network traffic as the lease is valid for a specific time.
- Which two strategies are used to keep GFS highly available?
    - Fast Recovery and Chunk Replication.
- Random writes into files in GFS are.
    - Less Frequent.    
- Distributing replicas onto different racks causes following side effect.
    - Slow writes.
- Distributing replicas onto different racks has following benefits.
    - Faster reads.
    - Better reliability.
- What does a ChunkServer store?
    - Chunk data (one file per chunk).
    - Chunk metadata (version number, checksum).  

***

## BigTable

### How does BigTable scale?

- BigTable breaks a table into a number of Tablets, where each Tablet contains data associated with a contiguous 
range of rows.
- Initially, each table consists of just one table.
- As the table grows, it is split into multiple Tablets.
- BigTable master is responsible for assigning Tablets to Tablet servers.
- Tablet servers are responsible for handling read and write requests to the Tablets that they are assigned as well as
splitting Tablets that have grown too large.
- Any newly added Tablet server is assigned Tablets either from the pool of unassigned Tablets or from other Tablet
servers.

### How does BigTable ensure fault-tolerance and reliability of data?

- BigTable uses GFS, which replicates data to multiple ChunkServers for reliability and fault tolerance.
- BigTable master is responsible for detecting Tablet server failures.
- It does this by periodically checking the status of the Chubby lock against each Tablet server.
- When the master finds out that a Tablet server has gone dead, it reassigns the tablets of the failing Tablet server.

### How does BigTable ensure durability?

- BigTable ensures durability by replicating data across multiple replicas through GFS, which stores the data on the disk.

### What BigTable components can become Single Point of Failure (SPOF), and how does BigTable handle them?

- BigTable master: 
    - BigTable uses Chubby to ensure that there is only one active master.
    - If Chubby becomes unavailable for an extended period, BigTable will also become unavailable.
    - BigTable is not involved in the core task of mapping tablets onto underlying files in GFS 
    (this is handled by Tablet servers).
    - This means that BigTable clients do not need to communicate with the master at all.
    - This significantly reduces the load on the master and enables clients to read data even when the master is
    not available.
- Chubby master:
    - Chubby becomes unavailable if its master dies.
    - Chubby uses PAXOS to elect a new master from other replicas.
    - The new master loads the system state from the database and the transaction log before serving requests.
- GFS master:
    - GFS cannot work without its master.
    - GFS relies on an external monitoring infrastructure to detect the master failure and switch the traffic to
    the backup master server.
    - GFS uses Operation Log and Checkpointing to save the current master state.
    - A newely elected master may load the checkpoint into memory, reply any subsequent operations from 
    the Operation Log, and start serving the traffic.
        
### Does BigTable become unavailable if its master is down?

- The master is responsible for all metadata operations and other control operations like monitoring and load balancing
Tablet servers.
- Therefore, if the master is down, the cluster will become unhealthy, and clients will not be able to perform and 
schema changes.
- During this time, clients can still access data as the data transfer happens directly between clients 
and Tablet servers. 

### How does BigTable perform load-balancing of Tablet servers?

- BigTable master is responsible for assigning Tablets to Tablet servers and thus maintains a global view of the system.
- Also, the master periodically asks Tablet servers about their current load and based on this information, moves
Tablets around to balance the load between Tablet servers.

### How does BigTable manage the separation between the data and control flow?

- BigTable clients talk to the Master for all metadata operations, wheras all data access happens directly between
the Clients and the Tablet servers.

### Quiz

- In terms of CAP theorem, BigTable is **CP** system.
- BigTable is indexed by a unique key which is made up of.
    - Row key, Column Key, and timestamp.
- How does BigTable split a table to store on to multiple servers?
    -  By rows, which a contiguous set of rows placed on different servers.
- Why is choosing a good row key important in BigTable?
    - To take advantage of the locality of adjacent rows. BigTable keeps its data sorted by the row key. 
    Rows are stored next to each other in a tablet - except occasionally when have to jump to the next Tablet.
- What is the unit of distribution and load-balancing in BigTable?
    - Tablet.
- What are the different compaction strategies that are used in BigTable?
    - Converting MemTables to SSTables.
    - Merging some SSTables and MemTables into a new SSTable.
    - Merging all SSTables into a single SSTable.
- What are the responsibility of the master server?
    - Handling metadata operations.
    - Garbage collection of underlying files in GFS.
    - Assigning Tablets to Tablet servers.
- BigTable uses which of the following schemes to enhance read performance?
    - Bloom filters.
    - Locality Group.
    - Cache.
- How can we describe BigTable's MemTable?
    - It stores recently committed changes to BigTable stored on a Tablet server.
- What is the use of Metadata Tablets?
    - To enable clients to find the tablet server responsible for a row.

***

## Kafka

### How does Kafka scale its brokers, topics, producers and consumers?

- Topics: Kafka scales topics by spreading them over many partitions. Each partition can be placed on a separate broker.
- Producers: By having multiple partitions of a topic, Kafka producers can publish a huge number of messages to that topic.
Multiple producers can publish messages on the same topic.
- Consumers: Kafka scales consumers by partition such that each consumer gets its share of partitions.
A consumer can have more than one partition, but a partition can only be used by one consumer in a consumer group at a
time.
- Broker: Adding or removing brokers is very easy. Whenever a new broker is added or removed, Kafka rebalances topic
partitions. Kafka tries to distribute leaders and followers of a partition on separate brokers.

### How does Kafka ensure fault-tolerance and reliability of controller broker, topic partition leader and ZooKeeper?

- Broker & Partition: Kafka is designed in such a way that a broker failure is detectable by ZooKeeper and other brokers
in the cluster. Because each topic can have multiple followers running on different brokers, the cluster can recover from
any broker failures and continue to work without any disruption of service.  
- Controller & Leader: Whenever a controller broker or a partition leader stops, ZooKeeper detects the failure and initiates
a new leader election.
- ZooKeeper: Since all the critical information is stored in the ZooKeeper, and it replicates this data across its cluster,
ZooKeeper's failure does not affect the state of the Kafka cluster. Kafka will restore the state, once the ZooKeeper
restarts after failure.

### What message-delivery guarantees does Kafka provide to producers?

- Async: Producer sends a message to Kafka and does not wait for an acknowledgment from the server.
- Committed to Leader: Producer waits for an acknowledgment from the partition leader.
= Committed to Leader and Quorum: Producer waits for an acknowledgment from the partition leader and the full set of
in-sync replicas.

### What message-delivery guarantees does Kafka provide to consumers?

- At-most-once: Messages may be lost but are never redelivered.
- At-least-once: Messages are never lost but may be redelivered.
- Exactly-once: Each message is delivered once and only once.

### How does Kafka ensure consistency as far as message delivery is concerned?

- To ensure data consistency, leader brokers never return messages which have not been replicated to a minimum set of
in-sync replicas (ISR).
- Brokers keep track of the **high-water mark offset**, which is the largest offset that a minimum set of ISRs of a
particular partition share.

### How does Kafka ensure durability?

- Kafka ensures durability by replicating messages across multiple brokers, as well as storing them on the disk.

### How Kafka manages high throughput for consumers?

- By using consumer group, consumers can be parallelized, so that multiple consumers can read from multiple partitions 
on a topic, allowing a very high message processing throughput.

### How does Kafka manage high availability?

- Each topic partition can have multiple replicas (called followers).
- If the leader of a partition stops, a follower can take over to make the partition available.
- If a broker dies, Kafka distributes its partitions to other available brokers.
- If the controller broker dies, ZooKeeper initiates a leader election to choose a new controller broker.

### How does Kafka throttle its producers and consumers?

- Kafka throttles its clients using **quotas**, which are byte-rate thresholds defined per client-ID.
- A client-ID logically identifies an application making a request.
- A single client-ID can span multiple producers and consumers instances.
- The quota is applied for all instances as a single entity.
- When the broker calculates that a client has exceeded its quota, it slows the client down by holding back the response
to the client for enough time to keep the client under the quota.

### How does Kafka perform failover for consumers within a consumer group?

- If a consumer in a consumer group dies, the partitions assigned to that consumer are distributed to the remaining
consumers in that group.

### How does Kafka perform failover for brokers?

- If a broker dies, Kafka divides up the leadership of its topic partitions to the remaining brokers in the cluster.

### Quiz

- In Kafka, messages are published to.
    - A topic
- Once a message is written to Kafka, it can be changed.
    - Never
- How many brokers will be marked as leaders of a partition?
    - One
- What is the default partition strategy for producers without using a key?
    - Round-robin
- Within a partition, a message is uniquely identified by its.
    - Offset
- Can two consumers from two different consumer groups consume the same message?
    - Yes
- Which of the following statement is true about a Kafka topic?
    - Multiple consumers can subscribe to the messages written to a topic.
- Does each consumer group have its own offset for every partition of a topic?
    - Yes. For each consumer group, Kafka stores the current offset for every partition of a topic.
- Which statement about consumer group is not true?
    - Within a consumer group, Kafka ensures that each consumer receives at least one partition of a topic.
    Kafka **does not** guarantee that each consumer receives at least one partition of a topic within a consumer group.
    For example, when the number of consumers exceeds the number of partitions in a topic, all new consumers 
    **wait in idle mode** until an existing consumer unsubscribes from that partition.
- What is the optimum number of consumers within a consumer group?
    - Equal to the number of partitions of the topic they are consuming.
- Does Kafka guarantee that messages with the same 'key' are written to the same partition?
    - Yes    
- Kafka can use the following criteria to delete old messages.
    - Time-based limits
    - Size-based limits
    - Compaction

***

## Dynamo

### How does Dynamo scale?

- Dynamo uses **consistent hashing** to scale. 
- Furthermore, with the help of Vnodes, adding nodes to the cluster is quite easy. 
- For distributing the load, any newly added node receives Vnodes from existing nodes.

### How does Dynamo ensure fault-tolerance and reliability of data?

- Dynamo replicates data to multiple nodes for reliability and fault-tolerance.

### How does Dynamo provide an "always writable" experience?

- While writing data, if the required node is unreachable, another node can accept writes on its behalf.
- The write is then kept in a local buffer and sent out once the destination node is healthy again.
- This process is called **Hinted handoff**, and it makes Dynamo "always writable" even in the extreme case where only
a single node is alive, write requests will still get accepted and eventually processed.

### How does Dynamo ensure durability?

- Dynamo ensures durability by replicating data across multiple replicas, as well as storing them on the disk.

### How can Dynamo be configured for faster reads or faster writes?

- Dynamo uses a consistency protocol similar to quorum systems:
    - If `R` is the minimum number of nodes that must participate in a successful read operation.
    - `W` is the same as `R`, except it's for write operation.
    - Then `R + W > N` yields a quorum-like system, where `N` is the total number of replicas.
- If `N = 3`, we can have the following configurations:
    - For faster writes, we can use `R = 3`, `W = 1`. This also means that read will be slower, and data is not very durable.
    - For faster reads, we can use `R = 1`, `W = 3`. This also means that writes will be slower, but data will be durable.

### How does Dynamo manage high availability?

- Using **sloppy quorum** and **hinted handoff**, Dynamo is always available for writes even if the first `N` nodes in the
preference list are not healthy.

### How does Dynamo perform anti-entropy?

- Dynamo uses **Merkle trees** to compare replicas of a data.
- The principal advantage of using the Merkle tree is that each branch of the tree can be compared independently 
without requiring nodes to download the entire tree or the whole data set.
- Merkle trees minimize the amount of data that needs to be transferred for synchronization and reduce the number of
disk reads performed during the anti-entropy process.

### Given a key, how do nodes find out which node is responsible for managing key?

- Dynamo uses **gossip protocol** which enables each node to keep track of state information about the other nodes in
the cluster, like which nodes are reachable, what key ranges they are responsible for, etc.
- Nodes share state information with each other to stay in-sync.
- Gossip protocol is a peer-to-peer communication mechanism in which nodes periodically exchange state information about
themselves and about other nodes they know about.
- Each node initiates a gossip round every second to exchange state information about itself and other nodes with one
other random node.
- This means that any new events will eventually propagate through the system, and all nodes quickly learn about all other
nodes in a cluster.

### Quiz

- In CAP theorem, Dynamo is **AP (Available and Partition Tolerant)** system.
- Amazon Dynamo is called a zero-hop DHT because:
    - Clients know the complete set of servers and can directly contact the server that holds the data they need.
- Does Dynamo always write data to `N` nodes in the preference list?
    - False. Dynamo replicates writes to a sloppy quorum of other nodes in the system (which are the first N healthy
    nodes from the preference list, instead of a strict majority quorum).
- How does Dynamo resolve conflicts between different versions of data?
    - Vector clock
- What strategies can be used to resolve data conflicts?
    - Last write wins
    - Conflict-free replicated data types (CRDTs).
    - Vector clock  
- How does Dynamo handle temporary node failures?
    - Hinted handoff
    - Sloppy quorum
- What are the benefits of Vnodes in Dynamo?
    - Vnodes help spread the load more evenly across the physical nodes on the cluster. This speeds up the rebalancing 
    process after adding or removing nodes.
    - Vnodes make it easier to maintain a cluster containing heterogeneous machines.
    - Use of Vnodes decreases the probability of hotspots, as Vnodes help assign smaller ranges to each physical node.
- What is optimistic replication in Dynamo?
    - Replicas may diverge and are not guaranteed to be identical all the times. Dynamo employs an eventually
    consistent model.
-  Dynamo uses Vector clock to:
    - Identify conflicting versions of the same object stored on different servers.
- Dynamo optimizes key lookups via:
    - Storing the table of all nodes in the system at each node. Clients and nodes have the full list of nodes in the system.
    This enables `O(1)` lookup without the need to forward a quary to another node.
    
***

## Casandra

### How does Cassandra scale?

- Casandra uses **consistent hashing**, and with the help of **Vnodes**, adding nodes to the cluster is quite easy.
- When a new node is added, it receives many Vnodes from the existing nodes to maintain a balanced cluster.

### How does Cassandra ensure fault-tolerance and reliability of data?

- Cassandra replicates data to multiple nodes for reliability and fault-tolerance.

### What write consistency levels does Cassandra provide to its clients?

- For write operations, the consistency level specifies how many replica nodes must respond for the write to be
reported as successful to the client.
- Cassandra offers different write consistency levels: **One**, **Two**, **Three**, **Quorum**, **ALL**, **Local_quorum**,
**Each_quorum**, and **Any**.

### What read consistency level does Cassandra provide to its clients?

- The consistency level for read queries specifies how many replica nodes must respond to a read request before returning
the data.
- For example, for a read request with a consistency level of quorum and replication factor of three, the coordinator
waits for successful replies from at least two nodes.
- For read requests, Cassandra has the same consistency levels as that of write operations except **Each_quorum**.

### How does Cassandra ensure strong sata consistency?

- To achieve strong consistency in Cassandra: `R + W > RF => strong consistency`.
- In this equation, `R`, `W` and `RF` are the read replica count, the write replica count, and the replication factor 
respectively.
- All clients 'reads' will see the most recent 'write' in this scenario, and we will have strong consistency.
    
### How does Cassandra ensure durability?

- Cassandra ensures durability by replicating data across multiple replicas, as well as storing them on disk.

### How does Cassandra manage high availability?

- Each piece of data is replicated to multiple nodes to ensure high availability.
- The replication factor is the number of nodes that will receive the copy of the same data.
- This means, if a cluster has a replication factor of 3, each row will be stored on three different nodes.
- In this case, the data will still be available even if nodes fail.
    
### What is the use of Bloom Filter in Cassandra?

- On a read request, before doing any I/O, Bloom filter is used to check whether the requested data exist in an SSTable. 
    
### How is an SSTable stored on the disk?

- SSTable consist of mainly two files:
    - Index file (Bloom filter & Key offset pairs).
    - Data file (actual columns data).
    
### Quiz

- In CAP theorem terms, Cassandra is **AP (Available and Partition Tolerant)** system.
- Read repair ALWAYS happend when the consistency level is set to.
    - All
- Nodes can only gossip with soecific other nodes in the cluster.
    - False
- Cassandra automatically assigns tokens to Vnodes.
    - True
- Snith is used for.
    - Determining each node's rack and datacenter.
- When does a client acknowledge the write?
    - After writing to the commit log and MemTable
- Which of the following are stored and sorted by the clustering columns?
    - SSTable
    - MemTable
- Which of the following structures are accessed from the disk?
    - SSTable
    - Partition index
- The partition summary.
    - Stores the byte offset into the partition index.
- All tombstones are discarded during compaction.
    - False
- With Cassandra consistency level means a majority of the replicas in each datacenter must respond?
    - EACH_QUORUM
- What is the role of the partitioner?
    - To Hash the partition key to determine the partition token.
    
***

## Chubby

### How does Chubby ensure fault tolerance and reliability of data?

- Fault tolerance in Chubby is achieved through data replication and maintaining a transaction log for the master.
- **Checkpointing**: For recovery in case of failure, all database transactions are stored in a transaction log.
- As this transaction log can become very large over time, every few hours, the master of each Chubby cell writes a 
snapshot of its database to a GFS server in a different building for dault tolerance.

### How does Chubby ensure high availability in case of master failure?

- When a master fails, Chubby uses PAXOS to elect a new master.
- The new master loads the system state from the database and the transaction log/snapshot before serving requests.

### How does Chubby ensure file data integrity?

- Chubby uses a 64-bit checksum to detect if a file replica is different.

### What happens when a Chubby replica fails permanently?

- If a replica fails and does not recover for a few hours, a simple replacement system allocates a new server from a
free pool of free machines and starts the Chubby binary on it.
- It then updates the DNS tables, replacing the IP address of the failed replica with that of the new one.
- The current master polls the DNS periodically and eventually notices the change.
- It then updates the list of the cell's members in the cell's database.
- This list is kept consistent across all the members via the normal replication protocol.
- In the meantime, the new replica obtains a recent copy of the database from a combination of backups stored on file
servers and updates from active replicas.
- Once the new replica has processed a request that the current master is waiting to commit, the replica is permitted 
to vote in the elections for a new master.

### How does clients find the master using Chubby?

- All Chubby replicas are stored in DNS.
- Client contacts DNS to know the listed Chubby replicas.
- Client then calls any server directly in the Chubby cell via RPC.
- If that replica is not the master, it returns the address of the current master.
- Once the master is located, the client maintains a session with it and sends all requests to it until it indicates that
it is not the master anymore or stops responding.

### During cache invalidation, while the master is waiting for acknowledgments from clients, can other clients still read the file?

- During the time the master is waiting for the acknowledgments from clients, the file is treated as 'unreachable'; this
means that the clients can still read the file but will not cache it.
- This approach ensures that reads always get processed without any delay.
- To handle a client who bombards Chubby to read an 'uncached' file, an alternative design could be to block all read
requests during invalidations, or a hybrid scheme can be implemented combining the two above-mentioned approaches.

### How does Chubby avoid cyclic dependency if GFS cluster depends upon Chubby electing its master?

- The master of each Chubby cell writes a snapshot of its database to a GFS server in a different building.
- The use of a separate building ensures that the backup will survive building damage, and the backups introduce no cyclic
dependencies in the system.
- A GFS cell in same building potentially might rely on the Chubby cell for electing its master.

### Quiz

- Chubby is typically deployed as a set of 3-5 servers to provide.
    - Fault tolerance in case of failure.
- The design of Google Chubby can best be described as.
    - One active master with multiple replicas as backups that do not process client requests.
- What is Chubby's fault-tolerance model?
    - Active-passive. Only one server processes requests and propagates state to replica.
- Chubby clients maintain a constant session with Chubby cell using.
    - KeepAlive
- Which of the following operation in not possible in Chubby?
    - Appending to a file.
- Which of the following statement best describes a client's interaction with Chubby?
    - A client contacts the Chubby master which handles all client requests.
- Which of the following is not a property of Chubby?
    - Uses load balancing across all replicas to respond to multiple clients requests.
- What is the use of 'Sequencer' in Chubby?
    - To handle receiving messages out of order.
- Chubby does not support.
    - Partial file reads. Chubby was designed to store small files (e.g., configuration data), provide locking, and
    notifications. Since files are small, file I/O is only entire file reads & writes.

***

## HDFS

### How does HDFS scale?

- HDFS scales by adding DataNodes in the cluster.
- HDFS allows DataNodes to be added to a running cluster and offers tools to manually rebalance the data blocks when
cluster nodes are added, which can be done without shutting the file system down.
- Since NameNode stores all metadata in memory (for faster operations), HDFS is limited by how much memory the
NameNode has.
- This limitation is handled through HDFS Federation, which was introduced in the 2.x release.
- HDFS Federation allows a cluster to scale by adding NameNodes, each of which manages a portion of the filesystem
namespace.

### How does HDFS ensure fault-tolerance and reliability of data?

- Fault tolerance in HDFS is achieved through data replication and maintaining a metadata transactions log for the 
NameNode.
- **Replication:** HDFS replicates data to multiple nodes for reliability and fault tolerance. By default, each block has
three replicas.
- **EditLog:** For fault tolerance and in the event of NameNode crash, all metadata changes are written to the disk onto
an edit log. This EditLog can also be replicated on a remote filesystem (e.g., NFS) or a secondary NameNode.
- **FsImage:** The NameNode state is periodically serialized to disk and then replicated, so that on recovery, a NameNode
may load the checkpoint into memory, replay and subsequent operations from the edit log, and be available again very quickly.

### How does HDFS manage high availability?

- Each block is replicated onto multiple DataNode to ensure high availability. By default, HDFS makes three replicas of
each block.
- To ensure maximum data availability. NameNode distributed replicas on different racks, so that clients can still
read or write in case of a rack failure.
- **HDFS High Availability:** To achieve high availability in case of a NameNode failure, Hadoop, in its 2.0 release,
added support for HDFS High Availability (HA).
- In this implementation, there are two (or more) NameNodes in an active-standby configuration with a hot standby(s).
- At any point in time, exactly one of the NameNode is in an active state, and the others are in standby state.
- The active NameNode is responsible for all client operations in the cluster, while the standby is simply acting as a 
follower of the active, maintaining enough state to provide a fast failover when required.

### How does HDFS perform NameNode failover?

- Older releases of HDFS require manual intervention for NameNode failover.
- After Hadoop 2.0, ZooKeeper is used to automatically failover in case of a NameNode crash.
- The ZKFailoverController (ZKFC) is a ZooKeeper client that runs on each NameNode (active and standbys) and is responsible
for coordinating with the Zookeeper and also monitoring and managing the state of the NameNode.

### How does HDFS ensure file data integrity?

- Data Integrity refers to ensuring the correctness of the data.
- The data received by a client from a DataNode could be corrupted.
- This corruption might happen due to problems in a storage device,  network faults, or the software itself.
- HDFS uses checksum to verify the contents of files.
- When a client creates an HDFS file, it computes a checksum of each block of the file and stores these checksums 
in a separate hidden file in the same HDFS namespaces.
- HDFS client, when retrieving file contents, first verifies that the data received from each DataNaode matches the checksum
stored in the associated checksum file.
- And if not, the client can opt to retrieve that block from another replica.

### What is the use of Eraser Coding (EC) in HDFS?

- HDFS, by default, stores three copies of each block, resulting in a 200% overhead (to store two extra copies) in storage
space and other resources (e.g., network bandwidth).
- Compared to this replication scheme, EC provides the same level of fault-tolerance with much less storage space.
- In a typical EC setup, the storage overhead iis no more than 50%.
- This fundamentally doubles the storage space capacity by bringing down the replication factor from 3x to 1.5x.
- Under EC, data is broken down into fragments, expanded, encoded with redundant data pieces, and stored across different
DataNode.
- If, at some point, data is lost on a DataNode due to corruption, etc., then it can be reconstructed using the other
fragments stored on other DataNodes.
- Although EC is more CPU intensive, it greatly reduces the storage needed for reliability storing a large data set.

### Can HDFS handle small files efficiently?

- Because of its high-throughput design, HDFS lacks the ability to support the efficient random reading of small files.
- HDFS is designed to be used with large block sizes (128MB and larger).
- It is meant to take large files (hundreds of megabytes, gigabytes, or terabytes) and divide them into blocks, 
which can then be fed into MapReduce jobs for parallel processing.
- HDFS is inefficient when the actual file size are small (in the kilobyte range).
- Having a large number of small files places additional stress on the NameNode, which has to maintain metadata for all
the files in the file system.
- Additionally, when there are a large number of files, there will be a lot of seeks on the disks ad frequent hopping
from data node to data node will be done, increasing the file read/write time.
- Typically, HDFS users combine many small files into larger ones using techniques such as sequence files.
- A sequence file can be understood as a container of binary key-value pair, where the file name is the key, and the
file contents are the value.

### How does HDFS handle DatNode failuers?

- NameNode receives Heartbeat and BlockReport from each DataNode.
- Heartbeat receipt implies that the DataNode is alive and functioning correctly, and the BlockReport contains
 a list of all blocks on a DataNode.
 - When NameNode observes that DataNode has not sent a heartbeat message after a certain amount of time, the DataNode is 
 marked as dead.
 - The NameNode replicates the blocks of the dead node to another DataNode.
 - Hence, NameNode can easily handle DataNode failure.

### How does HDFS separate control flow from data flow?

- HDFS is designed in such a way that clients never read or write data through the NameNode.
- Instead, a client asks the NameNode which DataNode it should contact for reading a block.
- The client then contacts those DataNode to access the data directly.
- Besides that, all communication between NameNode and DataNode, e.g., DataNode registration, BlockReport, Heartbeat,
etc., is initiated by the DataNode, and responded to by the NameNode.

### Quiz

- In HDFS, files are divided into **Fixed size** blocks.
- Random writes into files in HDFS are.
    - Not possible
- What kind of consistency model does HDFS offer?
    - Strong consistency
- Does a file in HDFS smaller that a single block occupy a block's full storage?
    - No
- What is the purpose of secondary NameNode?
    - Secondary NN periodically merges the EditLog and FsImage and sends the merged image to primary NN.
- HDFS is designed after GFS but does not support.
    - Concurrent appends. Multiple clients cannot write into an HDFS file at the same time.
- What is the default replication policy in HDFS?
    - No DataNode will contain more than one replica of any block.
    - If there are enough racks available, no rack will contain more than two replicas of the same block.







