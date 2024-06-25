System Design

1) Zookeeper & Schema Registry? 
Answer: Zookeeper: Manage cluster, store metadata. Schema registry: Stores versioned history of all schemas by ID.
Producer: Send schema to schema registry and get schema ID. Consumer: Retrieve schema by ID. Data is serialized/deserialized 
with registered schema.

2) Why is replication across multiple Kafka Servers needed in production environment? 
Answer: To compensate for the failure of any one.

3) White-Box Testing? 
Answer: Involves testing the internal structure, code and logic of a software application. Usage: It is used to ensure 
that code is working correctly and efficiently, focusing on code coverage and path analysis.
Advantage: Can identify complex logic errors, security vulnerabilities, and code optimization opportunities. Can provide 
a higher level of coverage and uncover hidden defects. Disadvantages: Testers needs programming knowledge.

4) Which options will increase the throughput on a Kafka producer? 
Answer: Increase batch size. Setting a compression type as snoppy. Increase linger-ms.

5) Where to fire an event? Microservice architecture? 
Answer: In Application Service. Domain Layer should not know about how to fire the event.

6) When to choose synchronous vs asynchronous communication? 
Answer: Synchronous:  a) Immediate responses are critical. b) Request response interactions. c) Performance bottleneck 
and reduced fault tolerance. Asynchronous: a) Real-time responses are not necessary, and you want to decouple components 
for scalability, reliability and fault-tolerance. b) Background processing, event-driven architecture. c) Introduce 
complexity in handling eventual consistency and message passing.

7) What is a Microservice? 
Answer: A Web Service. Small and Responsible for one thing (Search, Password Reset, Email Verification). Configured 
to work in the Cloud and is easily scalable.

8) What is a measure for performance? 
Answer: Latency, Graceful degradation, Throughput, Response Time, Short tail latency (narrow latency distribution),
Instructions per cycle.

9) What is a maximum number of consumers that can work on Kafka topic? 
Answer: Maximum number of consumers that can work on Kafka is equal to a partition number. The additional consumer will 
be idle won't do any job.

10) What does Thrashing mean in the context of computer science? 
Answer: A situation when the OS is spending more time managing resources than allowing the application to run their code.
Typically thrashing is associated with exhausting the physical memory due to virtual memory overuse, which leads to a high 
number of page faults. This can happen if we run too many processes, allocate too many threads or allocate too much memory.

11) What are the factors that influence the technical Micro Service? 
Answer: Communication, Resilience, New Microservices. Operations: Logs, Metrics, Configuration, Deployment.

12) What are some roles of OS? 
Answer: a) Memory management. b) Isolation between processes. c) Schedule process and their threads on CPU cores. 
d) Provide security and interface for accessing the hardware.

13) What are arguments for a higher number of partitions? 
Answer: More opportunity for concurrency.

14) Web Service W3C Definition? 
Answer: Software system designed to support interoperable machine-to-machine interaction over network. 3 Keys: 
a) Designed for machine-to-machine (or app to app) interaction. b) Should be interoperable, not platform dependent. 
c) Should allow communication over a network.

15) We have a computer with a single core? 
Answer: We can achieve concurrency using multiple threads. We can achieve concurrency using multiple processes. 
We can only achieve parallelism with multiple cores, but by multitasking, the OS can achieve concurrency using just one 
core by the means of multithreading.

16) What are Virtual Machines? 
Answer: Virtual Machines are simulated computers that run on the same physical hardware. For the OS and app, virtual 
machines look exactly like a physical server.

17) Test case? 
Answer: A test case is a detailed and documented set of instructions or steps that a tester follows to execute a specific 
test scenario and evaluate whether a software app behaves as expected. Test Case ID, Test Case Title / Description,
Test Objective, Preconditions, Test steps, Expected Results, Test Data, Test Environment, Execution Status, Actual Result,
Attachment.

18) What is TCP? 
Answer: Transport layer in OSI. Reliable connection, ordered sending of data. Functions, flow control, error control, 
repeat of data. Reliable way of sending data. Bytes stream, segments (header, data).

19) System Design step-by-step process? 
Answer: First: Ask Questions to capture: a) Functional Requirements. b) Non-functional Requirements. c) System Constraints. 
Second: Define the System APIs. Third: Create a Software Architecture Diagram to fulfill the Functional Requirements. 
Fourth: Refine the Software Architecture Diagram to address the Non-Functional Requirements.

20) System Design Software Extensibility Architecture Pattern? 
Answer: a) Sidecar & Ambassador. b) Anti-corruption Adapter pattern. c) Backends for Frontends pattern.

21) System Design Latency? 
Answer: Time required to send packets between two points in computer network. Measured in MS. Caused by propagation time, 
waiting for free resources. The lower, the better.

22) System Design Cloud Scalability Pattern? 
Answer: a) Load Balancing. b) Pipes and Filters. c) Scatter Gather. d) Execution Orchestrator for Microservices Architecture. 
e) Choreography for Microservices Architecture.

23) System Design Cloud Performance Pattern for Data Intensive Systems? 
Answer: a) Map Reduce. b) The Saga Pattern. c) Transactional Outbox Pattern: Reliability in Event Driven Architecture. 
d) Materialized View Pattern: Architecting High Performance Systems. e) CQRS. f) CQRS + Materialized View. 
g) Event Sourcing Pattern.

24) What is a symmetric encryption? 
Answer: Single key for encryption and decryption.

25) Process data structures? 
Answer: Process Table, Thread Table, File Table, IPC (Inter Process Communication), I/O (Input/Output).

26) What are difference between Strategic DDD and Tactical DDD? 
Answer: Strategic DDD: Introduces boundaries from domain model. Single bounded context per each domain. What is a Domain? 
Operational area of your application. e.g. online food ordering. Unbounded Context: Central Pattern in DDD. Boundary 
within a Domain. Tactical DDD: Implementation patterns: a) Entities: Core domain objects with a unique identifiers.
b) Aggregates: Group of Entity objects which always need to be in consistent state. c) Aggregate Root (AR).
d) Value Objects: Immutable objects without identity. Only value matters. Bring context to the value. e) Domain Service.
f) Application Service. g) Domain Event.

27) Difference between SQL and NoSQL? 
Answer: SQL table-based relational DB. Data is stored in precisely defined structures and the relationships between them 
are represented using foreign keys. Good at querying multiple tables and large data sets, as well as maintaining transaction 
consistency and security. NoSQL allows you to store data in various formats, such as documents, graphs, key-value or column.
More flexible and scalable than SQL. Good for projects with a lot of load data. Error-resistant because the data is replicated.

28) What are different Specialized Storage Paradigm? 
Answer: a) Blob Storage: GCS or S3. b) Time Series Database: Special kind of database optimized for storing and analyzing 
time-indexed data. Data points that specifically occur at a given moment in time. InfluxDB, Prometheus and Graphana. 
c) Graph Database: Social Networks Neo4j. d) Cypher: Graph query language that was originally developed for the Neo4j 
graph database. e) Spatial Database: Storing spacial data like locations on a map. Spatial indexes like quadtrees.

29) What is SOAP? 
Answer: Data Format: SOAP XML Request. SOAP XML Response. Transport: SOAP over MQ. SOAP over HTTP 
Service Definition: WSDL defines: Endpoint, All Operations, Request Structure, Response Structure.

30) What is Smoke Testing? 
Answer: Smoke Testing also known as build verification testing, involves running a subset of test cases to verify 
that core functionalities of a software applications are operational after a new build or release. Quick feedback on whether 
build is stable enough for more extensive testing. Identifies major issues. Prevents wasting time on comprehensive testing.

31) Set Based Consistency CQRS? 
Answer: How to check if record already exists in a database table? How to check if User already exists? Solution Lookup database table.
Message Dispatch Interceptor: a) Will intercept a command before it is processed by the command handler. b) Use JPA to query 
lookup table. c) Event handler to persist productID and title in lookup table.

32) Service Discovery and LB in Microservices? 
Answer: Service Discovery: a) Simplifies service-to-service communication. b) Facilitates stability and fault tolerance.
c) Supports dynamic environments. Load Balancing: a) Enhances Reliability. b) Improves Scalability. c) Fault Tolerance.
Tools and Technologies: Service Discovery: consul, etcd, zookeeper, kubernetes. Load Balancing: Reverse Proxy: Nginx, HaProxy. 
Service Mesh: Istio, Linkerd/ Load Balancers: Google Cloud LB

33) SOAP Service Definition? 
Answer: Request / Response Format. Request Structure. Response Structure. Endpoint.

34) What are Self-Contained Systems (SCS)? 
Answer: SCS is a type of microservice that specifies elements of macro architecture.

35) What is Security Testing? 
Answer: Security testing encompasses a range of testing techniques designed to assess the confidentiality, integrity, 
authentication, authorization and availability of software applications. Risk Mitigation. Compliance: PCI DSS, GDPR. 
Customer Trust.

36) What is a role of Schema Registry in Kafka? 
Answer: Forces producer and consumer to use a valid schema.

37) Scatter Gather Pattern Use Cases? 
Answer: Scalability pattern. Use Cases: Search Services, Hospitality Services.

38) Example of Scalable Unstructured Data Storage Cloud and Open Source Solutions? 
Answer: Amazon S3, Google Cloud Storage, Azure Blob Storage.

39) Use cases for SAGA Pattern? 
Answer: Distributed long-running transactions across services. Used for Long Lived Transactions (LLT). Chain of local ACID 
transactions to finalise a long-running transaction across service. Different kinds: Choreography-Based, Orchestration-Based. 
ACID Transactions: Guarantees that a database will be in consistent state after running a group of operations. 
Outbox: Help to use of local ACID transactions to let consistent (eventual) distributed transactions.

40) Robustness ways to ensure? 
Answer: a) Error Handling. b) Monitoring and logging. c) Versioning and rollback. d) Testing and validation. 
e) Redundancy and Failover. f) Communication protocols. g) Service Discovery. h) Circuit Braking: Preventing cascading errors.
i) Graceful Degradation: The application works even at reduced levels.

41) Robust system, what does it mean? 
Answer: A robust system is able to operate efficiently, even in the presence of errors or other problems. 
Analogy to a 2-engine airplane. Key Features: Fault Tolerance, High Availability, Scalability, Resilience, Security, 
Efficiency, Manageability, Flexibility.

42) REST key abstraction? 
Answer: A resource has an URI. A resource can have different representations: XML, JSON, HTML.

43) What are Rest API Resources - Best practices? 
Answer: a) Naming resources using nouns. b) Making a distinction between collection resources and simple resources. 
c) Giving the resources clear and meaningful names. d) The resource identifiers should be unique and URL friendly.

44) What are Rest API Important Concepts? 
Answer: a) HATEOAS. b) Statelessness: Scalability and High Availability. c) Cacheability: Performance.d) Named Resource: 
Simple Resource, Collection Resource.

45) What are Replication and Sharding? 
Answer: Replication and sharding are techniques used in DB systems, allow scaling and increase of performance. 
Replication, creating copy of data spread across nodes to assure availability and failure protection.
Copy in multiple node, each one are identical, BANKS, HOTELS, TELECOMMUNICATION. Sharding split big DB to smaller (shards) 
stored in different part of network. Each node handles only subset of data. This allows to parallel data processing and 
faster reclaim data from big databases. Big performance and scalability, merchant systems, analysis of data.

46) Ways to replaying events in Kafka? 
Answer: Create a new consumer group. It will read the events from start by default.

47) Remote Procedure Call Technologies? 
Answer: gRPC. Apache Thrift. Java Remote Method Invocation (RMI).

48) What is Redis? 
Answer: An in-memory key-value store. Does offer some persistent storage options but is typically used as a really fast 
the best effort caching solutions. Redis is also used to implement rate limiting.

49) What is Reactive Programming? 
Answer: a) Responsive: System responds as fast as possible. b) Resilient: System remains available, even if parts fail.
c) Elastic: System can deal with different level of load, for instance by using additional resources. d) Asynchronous 
communication: System uses asynchronous communication (message-driven).

50) What is a Quorum in Kafka? 
Answer: Set the minimum number of brokers to create a network, prevent split brain.

51) Describe Quality attribute Response Time?
Answer: Time between a client sending a request and receiving a response. ResponseTime = Processing Time + Waiting Time.

52) Describe Push model in Postgres?
Answer: Push model where Postgres notifies the other apps for each change.

53)  Difference between Processes and Threads? 
Answer: Process independent execution unit, contains own address space, file descriptor, memory space, virtual devices.
Threads use system resources to which belongs. In process there are multiple threads, thread can have one process.
Processes require more time and system resources to start and communicate with other processes, because requires context switch.
Threads are more performant, requires less resources.

54) Describe Processes in Operating System? 
Answer: Programs executed on computer. Contains own resources as address space, threads, memory. Contains unique PID.
Key data structure in the context of process: process table, file table, page table.

55) Principle of service discovery in microservice architecture. Example of tools?
Answer: Enables services to find and communicate with each other. Principles of Service Discovery: 
Registration, Lookup, Heartbeats. Significance: Scalability, Load Balancing, Dynamic Updates, Resilience.
Example of industry Tools: consul, etcd, zookeeper, kubernetes, netflix eureka, dns-based (netflix ribbon)

56) What is POSIX? 
Answer: Portable OS interface. Standard how app can use system functions. Example of system functions: 
open(), read(), write(), fork(), exec(), pthread_create(). Alternative Win32.

57) Popular Service Discovery Tools and Techniques? 
Answer: 
a) Consul: By Hashicorp. It provides a distributed service registry and health checking for services. 
b) etcd: Distributed key-value store. It's a part of Kubernetes ecosystem. 
c) Zookeeper: Distributed coordination service. It offers hierarchical namespace and is known for its strong consistency. 
d) Eureka: Netflix Eureka is a service registry and discovery server. 
e) Kubernetes: An orchestration platform, includes built-in service discovery features through DNS and environment variables.

58) Describe Pipes and Filters pattern? 
Answer: Scalability pattern. Problem Solved: Tight Coupling, Hardware Restrictions, Low Scalability. 
Benefits: Use different programming languages, High Scalability, High Throughput.
Use Cases: Digital advertisements business, IoT data processing, Ingest / process Video and Audio.

59) Describe Peer-to-Peer Network? 
Answer: A collection of machines referred to as peers that divide a workload between themselves to presumably complete 
the workload faster than would otherwise be possible. Peer-to-Peer networks often used in file-distribution systems.
Gossip protocol: When a set of machines talk to each other in uncoordinated manner in a cluster to spread information 
through a system, without requiring a central source of data.

60) What is Page Cache? 
Answer: Consist of physical pages in RAM, corresponds to physical blocks on DISC.

61) Describe Outbox Pattern? 
Answer: It is used to add a strong consistency between local acid transactions and event publishing operation.
It relies on keeping the domain events in local database to be published later. Two ways to implement publishing part 
and pulling the outbox table and CDC (change data capture). It makes SAGA pattern consistent when combined with SAGA.
Outbox: Help use of local ACID transactions to let consistent (eventual) distributed transactions.
It will complete SAGA in a safe and consistent way. Persist events in local database automatically with ACID transactions.
Read the events and publish: 
a) Pulling Outbox Table: Pull the events with scheduler. 
b) Change Data Capture: Listen transaction logs. Keep track of SAGA and order status in Outbox Table. 
Ensure idempotency: Do not consume same data. Optimistic locks and BS Constraints: Prevent Data Corruption.

62) Describe OS Threads? 
Answer: Subprocesses executed as part of process. Used to parallel work, application becomes more responsive, increased 
performance. Able to synchronize work using mutexes or semaphores.

63) Describe OS livelock? 
Answer: Threads or processes constantly change states and are not able to finish tasks. Simultaneously use resources.
How to avoid: Avoid similar or identical tasks by multiple processes simultaneously (semaphores, synchronize).
Use delay strategy. Algorithms to detect and resolve conflicts. Avoid uncoordinated use of resources.

64) Describe OS Kernel? 
Answer: Main part of OS, manage resource, interface to use resource. Manage memory. Manage processes - assign resources, 
processor time, memory, I/O. Manage systems resources. Security control. Interface to resources. Handle interruptions: 
Like key press, end of execution of app. Energy management - hibernation.

65) Describe Operating Systems Virtualization? 
Answer: Starting multiple VMs on one physical computer. Virtualization mechanism: 
a) Virtual machines. 
b) Containerization.
c) Based on file system. 
d) Hardware.

66) What is OpenID Connect?
Answer: For authentication of user by using an ID token.

67) Describe OAuth 2? 
Answer: For delegated authorization by using an access token. Terminology:
Resource Owner, Client, Authorization Server, Resource Server, Authorization Grant, Redirect Url, Response type,
Access Token, OpenID token.

68) What are NoSQL use cases? 
Answer: Superior when it comes to query speed. Perfect choice for caching. Handling real-time big data. Data is not structured.

69) NoSQL Solutions? 
Answer: 
a) Key / Value Store: Examples Redis, Aerospike, Amazon DynamoDB. 
b) Document Store: Examples Cassandra, MongoDB. 
c) Graph Database: Examples Amazon Neptune, Neo4j

70) Non-Functional Requirements tools? 
Answer: 
a) Scalability: Load Balancing, Database Sharding, API Gateway. 
b) Performance: CDN, Caching, Database Indexing. 
c) High Availability: Database Replication, Redundancy, Message Broker, Multi Data Center Deployment. 
d) Availability over Consistency: Choosing / Configuring AP Databases. 
e) Durability: Replication and Backups.

71) Non-functional requirements example? 
Answer: 
a) Scalability: millions of daily users. 
b) Performance: less than 500ms response time 99%. 
c) Fault Tolerance / High Availability: 99.9% (3 nines) time available. 
d) Availability + Partition Tolerance: AP over CP.  
e) Durability.

72) Microservices, strategies and patterns to ensure high availability and fault tolerance? 
Answer: Strategies and Patterns: 
a) Redundancy and Replication. 
b) Circuit Breaker Pattern: Tools example Netflix Hystrix, resilience4j. 
c) Retry Mechanism. 
d) Health Checks and Monitoring: Tools example  Prometheus, Graphana. 
e) Stateless Services. 
f) Distributed Tracing: Tools example Zipkin. 
g) Graceful Degradation. 
h) Chaos Engineering - Tools example Netflix Chaos Monkey.

73) Microservices, micro and macro architecture decisions? 
Answer: 
a) Macro or Micro: Programming Language, Database, Look and Feel, Documentation. 
b) Micro Architecture: Continuous Delivery Pipeline, Authorization, Tests of Microservice in Isolation. 
c) Macro Architecture: Communication protocol, Authentication, Integration Tests.

74) What are Microservices trade-offs? 
Answer: Robustness is the goal. Robustness does not matter. 

75) What is Microservices scalability requirements? 
Answer: Stateless.

76) How to achieve Microservices Robustness? 
Answer: To achieve resilience, microservices can cache values and use them in case of a problem. Alternatively, there 
might be a fallback to a simplified algorithm. Without resilience, the availability of a microservice system might be a problem.

77) Describe Microservices? 
Answer: Independent development and deployment by different teams. Easy for scale for a specific service. 
Better fault tolerance. Enables to use different technology and languages for different services. 
Clean & Hexagonal Architectures: Isolate the domain logic from outside dependencies.

78) Microservice stronger isolation? 
Answer: Simplified CD, Scaling Deployment, Sustainable Deployment, Free Technology Choice, Robustness, 
Independent Scaling, Security, Replacing Legacy System.

79) Message Brokers Solutions & Cloud Technologies? 
Answer: Apache Kafka, Rabbit MQ, Amazon Simple Query Service (SQS), GCP Pub / Sub and Cloud Tasks, 
Microsoft Azure: Service BUS, Event Hub, Event Grid.

80) What is Megabyte (Mb)? 
Answer: 10 to the power of 6 or 1000000 bytes.

81) Mebibyte (Mi)? 
Answer: 2 to the power of 20 or 1048676 bytes.

82) Describe Map Reduce Pattern? 
Answer: Performance Pattern. Dominant Factors: Parallelism, Distribution of data, Scheduling execution, 
Result Aggregation, Handling & recovering from errors. A popular framework for processing very large datasets in distributed 
setting efficiently, quickly, and in fault-tolerant manner. Contains of 3 main steps:
a) Map: run map function on chunks and transform chunks into intermediate key-values. 
b) Shuffle: which recognizes the intermediate key-value pairs such that pairs of the same key routed to the same machine 
in the final step. 
c) Reduce: which runs a reduce function on the newly shuffled key-value pairs and transform them into more meaningful data.
Processing and analysis of large set of data like logs or financial data. Generating reports and statistics based on data set.
Sentiment analysis in Social Network. Fraud detection w financial transactions. Processing spatial data.
Example of technologies: Apache Hadoop, Spark, Google Cloud Dataflow.

83) Managing API Documentation and Versioning Challenges?
Answer: Documentation Tools: Swagger, OpenAPI. Clear Guidelines. 
Using Versioning Strategies: URI, header. Documentation as Code. Testing and Validation. Deprecation Policies. Feedback Loop. 
Collaboration. Educational Resources.

84) What is Load Testing? 
Answer: How software application performs under expected and extreme load conditions. Ensure performance. 
Identifies performance bottleneck. Assist in capacity planning and scaling infrastructure resources.
User Experience, Risk Mitigation, Resource Optimization.
Performance metrics: 
a) Response Time. 
b) Throughput. 
c) Error Rate. 
d) CPU and memory utilization.

85) Load Balancing Solutions & Cloud Technologies? 
Answer: HAProxy, NGINX. Cloud Based LB Solutions:AWS Elastic LB (ELB), GCP Cloud LB, Microsoft Azure LB.
GSLB Solutions: Amazon Route 53, Google Cloud Platform LB & Cloud DNS, Azure Traffic Manager.

86) Load Balancing Implementation Considerations? 
Answer: 
a) Round Robin: For stateless applications. 
b) Sticky Session / Session Affinity. 
c) Least Connection: Task associated with long term connections (SQL, LDAP).

87) Leader Algorithm? 
Answer: Consensus Algorithm: A type of complex algorithm used to have multiple entities agree on a single data value, 
like who the "leader" is amongst a group of machines. Paxos, Raft. Tools used to implement / perform leader election. 
etcd, zookeeper.

88) Latency and Throughput? 
Answer: Latency and throughput are the two most important measures of the performance of a system.
Latency: Time required to send packet from one computer to other in computer network. Measured in 'm-s'. 
Check inter-node propagation time. 
Throughput: Quantity of data, which can be send by network in a time. Measured in 'b-p-s', 'k-b-p-s'. 
Connection throughput limitation, use of resource be app or quality of connection.

89) Key-Value Store? 
Answer: A key-value store is a flexible NoSQL database that's often used for caching and dynamic configuration. 
Popular options include DynamoDB, Etcd, Redis and Zookeeper. 

90) Key differences between microservice and monolithic architecture? 
Answer: Key Differences: Size and complexity, Scalability, Development and Deployment, Technology Stack. 
Advantages of microservices: Scalability, Flexibility, Resilience, Technology Diversity, Easy Maintenance, 
Continuous Deployment, Decentralization. 
Challenges: Complexity, Communication, Communication overhead, Data management, 
Testing, Operational Overhead, Security, Service Discovery.

91) Kafka what are arguments for a lower number of partitions? 
Answer: A Lesser memory consumed on the client. It takes less time to find a leader for each partition if the node fails.

92) What are Kafka Topics? 
Answer: A topic is a named set of records. Producers send records to a topic and consumers receive them from a topic.
Holds data/events. Immutable & Append only. Kafka Topic: Consists of one or more partitions to hold data/events.

93) What is a Kafka Template? 
Answer: A Thread-safe template for executing high level producer operations.

94) Why Kafka is Fast? 
Answer: Kafka is fast relies on disk caching and memory mapped files. Memory mapped files: Contains the contents 
of a file in Virtual Memory. Lower I/O latency than using a direct disk access.

95) Kafka snoppy compression? 
Answer: Arranges compression ratio for higher compress/decompress speed.

96) Kafka scalability ordering? 
Answer: Kafka is scalable: Naturally scale by partitions. Ordering: Guaranteed by partition. Preserve ordering:
Use partitioning strategy to insert related data into same partition.

97) Kafka Resilience? 
Answer: Relies on disk and use replicas. Resiliency by replication factor.

98) Kafka Replication Factor? 
Answer: Replicate the data of partitions on different brokers.

99) Kafka Replication? 
Answer: Partitions can be distributed over servers. The partitions can also be replicated so that the data is stored 
on several servers, meaning Kafka can be made fail-safe. If one server crashes or loses data, other replicas still exists.

100) Kafka Records? 
Answer: Kafka organizes data in records. This is what other MOMs called "messages". Contain the transported data as a value. 
Records have a key and a timestamp.

101) Kafka Polling? 
Answer: The consumers poll the data, meaning the fetch new data and process it. Consumer can decide for themselves 
when they process the records.

102) Kafka Persistence? 
Answer: Kafka is a mixture of messaging system and data storage solution. By default retention for records is seven days.

103) Kafka Partitions? 
Answer: Topics are divided into partitions. Partitions allow strong guarantees concerning the order of records, but also 
parallel processing. For each partition, the order of the records is preserved. There is no guarantee of order across partitions.
Across partitions, processing can be parallel. Basically, a partition is just a file to which records are appended.

104) Kafka partition key? 
Answer: Set the target partition for a message.

105) Kafka Log compaction? 
Answer: All records with the same key are removed, except for the last once. Only very last update to the order remains 
available in Kafka.

106) Kafka linger ms? 
Answer: Add a delay in case of light load.

107) Kafka Leader and Follower? 
Answer: The replication is implemented in such a way that one leader writes and the remaining replicas write as follower. 
The Producer writes directly to the leader.

108) Kafka High Availability? 
Answer: Eliminates single point of failure.

109) Kafka consumer groups? 
Answer: Consumers are organized in consumer groups where each partition sends records to exactly one consumer in the 
consumer group. One consumer can be responsible for several partitions.

110) Kafka Consumer basics? 
Answer: Kafka relies on logs which are known as partitions. Kafka producers write to the end of a specific partition 
and kafka consumers read the logs starting from beginning while holding an offset to remember where to left.
Kafka distributes partitions to consumers based on the consumer group identifier. Each partition is assigned to single 
consumer in a consumer group. Different consumer groups can read the data from the same partition?
Set partition number equal to consumer number. Set poll timeout to a value not too big to prevent blocking indefinitely, 
not too small to prevent CPU stale. Delivery semantics: at least once, at most once, exactly once. A partition can have 
only one consumer. A consumer can read multiple-partitions. Kafka Consumer Concurrency Level: Threads, Processes, Machines.

111) Kafka Commit? 
Answer: Kafka stores the offset of each partition. This offset indicated which record in the partition the consumer 
read and processed last. When consumers have processed a record, they commit a new offset.

112) Kafka Challenges? 
Answer: The configuration is often not easy. If Kafka is used as event storage, the records must contain all the data 
that all clients need. This is often not easy to implement because of bounded context.

113) Kafka Benefits? 
Answer: Can store records permanently, which facilitates use of event sourcing. Approaches like Avro for solving schema 
evolution. Can guarantee delivery to a consumer, distribute work among several consumers. 
Can guarantee the delivery of messages. The Consumer commits the records it has successfully processed. If an error occur, 
it does not commit the record and tries to process it again, (retry).

114) Kafka batch size? 
Answer: Tune for higher throughput.

115) Kafka basics? 
Answer: Immutable, append only logs. Fast, resilient, scalable, high throughput. Relies on file system for storing and 
caching messages. Resilient and fault-tolerant by replication. Disk caching, memory mapped files instead of GC eligible memory.
Scale by partition. Ordered inside partitions. As an event-store: A great match for event-driven microservices.

116) Kafka Avro as data format? 
Answer: Avro is a binary protocol, but also offers a JSON-based representation. Avro support schema, meaning that each 
dataset is saved or sent together with its schema. Schema evolution, new fields can be added when default values are defined 
so that the old data can be converted into the new schema by using the default value.

117) Kafka Architecture? 
Answer: Kafka brokers: Servers run in a cluster. Topics: Logical data unit that holds multiple partition. 
Partitions: Smallest storage unit that holds subset of records. Producers: Writes to end of a specific partition. 
Consumers: Reads from a partition using an offset. Replication: Resilient and fault-tolerant. 
Scaling: Partition strategy. Immutable append-only event logs.

118) Kafka APIs? 
Answer: The producer API Servers to send data. The consumer API servers to receive data. Streams API servers to transform the data.

119) Kafka and Programming on Kafka? 
Answer: KafkaTemplate: Is a Spring component that wraps a Kafka Producer and provides method to easily produce data on Kafka.
To create a Kafka Consumer with Spring, using a KafkaListener on a simple method is enough. If my Kafka topics has n partition, 
I cannot use more than n threads to consume the data on topics. Kafka Consumer has a max poll records property that limits 
the number of records returned for a single poll.

120) Kafka advantages? 
Answer: High throughput. Low latency. Replication and can scale. Stores message history (approaches like event sourcing 
are possible).

121) Kafka AdminClient? 
Answer: Manage and inspect brokers, topics and configurations.

122) Kafka Acknowledgements acks? 
Answer: all: wait ack from all replicas. 1: wait only current brokers ack. 0: no ack.

123) How to ensure high performance in distributed systems? 
Answer: Load distribution (partitioning, replication and scaling). Minimizing network processing.
Node processing optimization. Optimizing communication between nodes. Monitoring and diagnostics.

124) How to simplify distributed systems? 
Answer: Avoid making the system too complicated. Using standard protocols. Using a microservice architecture. 
Implementation of a modular design. Automation: installation, testing and monitoring. Appropriate documentation. 
A simple data model. Use of a simple programming language.

125) How is context switch invoked by OS and hardware? 
Answer: Many processors have special context-switching instructions, such as the CALL, RET, JMP, or INT instructions,
which allow control to be transferred from one process to another. Processors also have special registers that store 
process state information, such as instruction counters, register status, and flags. In interrupt-based OS'es, 
context switching can be triggered by a hardware interrupt, such as from Real Time Clock, which triggers a program interrupt. 
Others are thread-based.

126) What is IP protocol? 
Answer: Network layer. Packets. Split data on packets, assign them network address, and send to receivers. 
Stateless (no info about previous operations). Connectionless.

127) IP Packets? 
Answer: Contains of header and user data. Source / destination address, protocol version, packet length, packet ID, 
flags, TTL, load type (TCP/UDP), checksum.

128) Data Integrity - methods of ensuring? 
Answer: Data Validation. Data Replication. Backups. Data encryption. Data consistency: Ensuring that data is the same 
between servers, using techniques such as two-phased-commit or a distributed consensus algorithm. Data Audit. Error 
detection and correction, checksums or correction codes. Access control.

129) Independent System Architecture Principles? 
Answer: Principle #1: The system must be divided into modules.
Principle 
#2: Two separate levels of architectural decisions: Macro Architecture: Concerns for all modules. 
Micro Architecture: Decisions which can be made differently for each individual module.
Principle #3: Modules must be separate process / containers / VMs.
Principle #4: Standardized integration & communication.
Principle #5: Standardized metadata. Metadata for tracing and authentication must be standardized. 
Such metadata must be transferred between the microservices. 
Principle #6: Independent continuous delivery pipeline.
Principle #7: Operations should be standardized.
Principle #8: Standardized interface - JSON payload using HTTP.
Principle #9: Modules have to be resilient.

130) In Kafka-based system, N consumers exists and M partitions exists. What is possible? 
Answer: N (consumers) greater than M (partitions) not possible. Multiple consumers cannot read from the same partition.

131) Importance of test-driven-development TDD? 
Answer: Importance: Quality Assurance, Specification, Rapid Feedback, Refactoring Safety.
TDD workflow: Write a Test, Write Code, Run Test, Refactor Code, Repeat. 
Benefits of TDD: Early issue detection, Improving Design, Documentation, Confidence. 
Challenges: Initial learning curve, Time-consuming, Maintenance, Overemphasis on Testing.

132) Importance of API Gateway in microservices architecture? 
Answer: Centralized Entry Point handles: Routing, Load Balancing, Authentication and Authorization, Rate Limiting. 
Example: Amazon API Gateway.

133) Idempotent Operations? 
Answer: An operation doesn't have any additional effect on the result if it is performed more than once.

134) How Service Discovery Facilitates Communication? 
Answer: Dynamic Addressing, Load Balancing, Health Checking, Failure Recovery.

135) Hexagonal Architecture, Primary Adapters, Secondary Adapters? 
Answer: Primary Adapters: Implement the input ports to execute the use cases. 
Secondary Adapters: Implement the output ports and called by business logic to complete external tasks. 
Dependency Injection: Inject the implementation of parts at runtime. 
Ports & Adapters: Separate insides (domain layer) and outsides (infrastructure layer). 
The principle is to isolate the domain from any dependency, such as UI, Data Layer or even a framework like Spring. 
Domain Layer: Should be the most independent and stable component of a system.

136) What is a Hadoop? 
Answer: A framework for processing large data sets on a cluster of computers. The two main modules were 
Hadoop Distributed File System (HDFS) and MapReduce. HDFS distributed file system. It divides files into blocks 
and replicates them across different nodes in the cluster to ensure reliability and performance.
MapReduce processing distributed data in a Hadoop cluster. Hadoop used to analyze large data sets, logs, financial data.

137) Functional versus Regression testing? 
Answer: Functional Testing: Tests the application against the functional requirements and specifications.
Regression Testing: Aims to identify and detect any unintended side effects or new defects that may have been introduced 
by the changes. 

138) Exponential Back Off Policy? 
Answer: Increase wait time for each retry attempt.

139) Exploratory Testing? 
Answer: Exploratory testing is an unscripted testing approach where testers simultaneously design, execute and adapt tests 
based on their understanding of the software. Encourage critical thinking.

140) Explain various methods or protocols that can be used for inter-service communication? 
Answer: 
a) HTTP/Restful API: Simple, easy to implement, compatibility are required.
b) gRCP: Micro in different programing languages. Internal communication within private network.
c) Message Queues: RabbitMQ, Apache Kafka. Asynchronous communication.
d) Graph QL: Query language for APIs that enables clients to request only the data they need.
e) WebSocket: Full-duplex, bi-directional communication channel over a single, long-lived connection, usage Chat, gaming.
f) Service Mesh (Istio, Linkerd): Dedicated infrastructure layer for handling service-to-service communication. 
Manage and secure communication in a complex micro-services ecosystem.

141) Explain the concept of distributed transactions and the two-phase commit (2PC) protocol?
Answer: Distributed Transactions: Multiple operations across distributed systems or microservices, where each operation 
is a part of larger transaction. In distributed transactions, either all the operations within transaction are 
successfully completed, or none of them are. This ensures data consistency across services.
Two phase commit (2PC) protocol: Is a distributed transaction management protocol used to ensure that distributed 
transactions are either committed or rolled back consistently across multiple services or databases.
Two phase commit (2PC) protocol phases: 
a) Prepare Phase: Coordinator asks all participants if they are ready to commit. When agrees, they respond with "prepared" message. 
b) Commit Phase: If all participants respond positively in the 
prepare phase, coordinator sends a "commit" message to all participants. If any participant response negatively or not at all, 
coordinator sends rollback message to undo transactions. Data consistency across services or databases is critical. 
Complex business process. 
Challenges: Performance Overhead. Blocking. Single Point of Failure. Complexity. Resource Locking. 
Limited Scalability. 
Tools Example: Java Atomikos, @Transactional Annotation.

142) Explain the concept of circuit breakers in microservices, and how do they contribute to system resilience?
Answer: Enhance fault tolerance and resilience. Can help prevent cascading failures and improve system stability.
States: Closed State, Open State, Half-Open State. Improve fault tolerance and prevent cascading failures when 
interacting with remote services. They provide a mechanism to handle service degradation gracefully and protect the overall 
system's reliability.

143) Execution Orchestrator Pattern? 
Answer: Scalability Pattern. One centralized service, acting as a brain. The only service aware of the: 
Context and Execution Step. Responsibility for handling issues and retries.

144) Eventual Consistency in Distributed Systems? 
Answer: Allows for temporary inconsistencies between copies of data, but guarantees that those will be resolved. 
Availability and Partition Tolerance are crucial. Ensuring Data Consistency: 
a) Use Idempotent Operations. 
b) Implement Data 
c) Versioning. 
d) Conflict resolution strategies "least write wins", "merge". 
e) Asynchronous Communication. 
f) CQRS Pattern. 
g) Event Sourcing. 
h) Monitoring and Alerting. 
i) Testing. 
j) Documentation and tracing.

145) Eventual consistency? 
Answer: A consistency model used in distributed computing to achieve high availability that informally guarantees that, 
if no new updates are made to a given data item, eventually all accesses to that item will return the last updated value.

146) Event Store? 
Answer: Store data as immutable events.

147) Event Driven Architecture? 
Answer: An event is an immutable statement of a fact or change.
Fact Events Examples: User clicking on a digital ad. Item is being added to a shopping cart.

148) ETL Extract, Transform, Load? 
Answer: A term referring to the process of transferring and processing data from various sources.
ETL pipeline is a sequence of stages, data retrieval, transformation and processing, loading them into the target system.
Extract: extracting data from source, Apache NiFi, Kafka Connect. 
Transform: processing according to Apache Spark, Apache Beam rules. 
Load: transferring data to the target system. Tools example: Apache Kafka, Amazon S3, Elastic-Search. 

149) Etcd Database? 
Answer: Etcd is a strongly consistent and highly available key-value store that often used to implement leader election in system.

150) Domain Driven Design DDD? 
Answer: Bounded Context: Each domain model is valid only in bounded-context. For example order process, 
payment and shipping are bounded contexts. Bounded context: Entities, Aggregates, ValueObjects, Domain Services, 
Application Services and Domain Event. Mapping business domain and bounded context for the domain model.

151) Domain Driven Design? 
Answer: Domain model: Conceptual model of the domain that defines the behavior and data of a system.
Domain Logic: In the center of the software. Independent of other dependencies.

152) DNS Queries?
Answer: Human Readable to IP address.
Commands: nslookup, dig.
Process: Query, DNS check cache, Searching: 
a) Send to root DNS for domain. 
b) Send to one of services responsible for domain. 
c) Receive IP address from one of servers.
DNS save in cache. Return address to client.

153) Distributed tracing in microservices architecture and why it's valuable for monitoring and troubleshooting? 
Answer: Distributed tracing involves tracking requests or transactions as they move through various microservices 
in a distributed system. Each service records timing information and context data, allowing for the reconstruction 
of request paths and the identification of performance bottlenecks. Example, Jageor.

154) Distributed File System? 
Answer: A Distributed File System is an abstraction over a (usually large) cluster of machines that allows them to 
act like one large file system. The two most popular implementation of a DFS is the Google File System (GFS) 
and the Hadoop Distributed File System (HDFS). Typically, DFSs take care of the classic availability and replication 
guarantees that can be tricky to obtain in a distributed-system setting. The overarching idea is that files split into 
chunks of a certain size (4MB or 64MB for instance), and those chunks shared across a large cluster of machines.
A central control plane is in charge of deciding where each chunk resides, routing reads to the right nodes, 
and handling communication between machines. Different DFS implementations have slightly different APIs and semantics, 
but they achieve the same common goal: extremely large-scale persistent storage.

155) Differences between synchronous and asynchronous communication in microservices? 
Answer: Synchronous: Client sends a request and waits for a response before continuing communication.
Suitability, Client requires immediate response, and can tolerate latency. Querying DB. 
When strong consistency is essential. Asynchronous: Client sends a request and continues its operation without waiting 
for an immediate response. Suitability: Low-latency is critical. Long-running tasks, background processing, event-driven 
and decoupling services. Scalability, responsiveness and fault tolerance are priorities.
Latency Tolerance. Complexity. Strong vs Eventual consistency. Message Brokers. Error Handling. Scalability.
Complex Interactions: Choreography or Saga pattern. Event-Driven Architecture.

156) Difference between smoke testing and sanity testing? 
Answer: Smoke Testing: It's a high-level, shallow test that checks if the app can start and perform fundamental 
operations without major issues. Sanity Testing: It's performed to ensure that the recent changes have not adversely 
affected the related functionality. Differences: Purpose, Scope, Timing, Depth, Pass/Fail Criteria.

157) Difference between functional and non-functional testing? 
Answer: Functional: Verifies that the software apps functions and features work accordingly to the specified requirements 
and specifications. Consists of, Unit Testing, Integration, System, User Acceptance Testing (UAT) "what". 
Non-Functional: Evaluates performance, security, usability and reliability. 
Consists of: Performance, Security, Usability, Reliability "how".

158) Describe the typical phases of software testing lifecycle STLC? 
Answer: Requirements Analysis, Test Planning, Test Design, Test Environment Setup, Test Execution, Defect Reporting and Tracking,
Defect Resolution and Retesting, Test Closure.

159) What is a Dependency Inversion? 
Answer: High level modules should not depend on low level modules. Both should depend on abstraction.

160) What is Debezium? 
Answer: Open source platform for change data capture. React every time your data changes without changing the 
application that modify the data. Kafka sink connector - 'debezium/connect' docker image. 
Support for Confluent Schema Registry. Source connectors for various database. Postgres source connector 
'io.debezium.connector.postgresql.PostgresConnector'.

161) DDD Strategic Design & Common Patterns? 
Answer: Customer / Supplier Pattern: Supplier is upstream and consumer is downstream. For example uses the model of the 
order process. However, payment defines requirements for the order Process. Conformist Pattern: Bounded context simply 
uses a domain model for another bounded context. The anti-corruption layer: Bounded context does not directly use the domain 
model of the other bounded context. The Separate Way Pattern: Bounded Contexts are not related at the software level although 
a relation would be conceivable. The Shared Kernel Pattern: Describes a common core that is shared by multiple bounded contexts, 
Microservices anti-pattern. The Open Host Service Pattern: Bounded context offers a generic interface with several services, 
Frequently found at public APIs on the internet. The publishing language model: Is a domain model accessible by all bounded contexts. 
For example standard format like EDIFAC for transaction between companies.

162) Detailed testing plan? 
Answer: Requirements Review. Risk Assessment. Test Planning. Testing Types: Unit, Integration, System, Safety, 
Performance, Security, Usability. Setup Test Environment. Test Data and Scenarios. CI and Continuous Testing.
Traceability. Safety critical verification. Todos and Practices. Docs and Reporting. Certification.
Test execution and Validation. Risk Mitigation. Post Deployment Monitoring.

163) Data Durability? 
Answer: Data replication. Backups. RAID Storage: Data stored on several disks and can survive disk failure. 
Data Durability Guarantees: Such as write-ahead logging or snapshotting. Error detection and correction: checksums or 
error-correction codes. Data composition: two-phase-commit or distributed consensus algorithm. Durable message queues. 
Cloud solutions.

164) Differences between CRUD and CQRS?
Answer: 
a) Responsibility: CRUD: read + write together (Data Access Layer). CQRS: segregated responsibility Commands Command 
layer, Queries Query Layer. 
b) Operations: CRUD: 4 create, read, update, delete. CQRS: commands change state, queries read.
c) Data Model: CRUD: one model. CQRS: split model, DB optimized for writing or reading.
d) Scalability: CRUD: all operations scale together. CQRS: independent scaling.
e) Events: CRUD: no natural mechanism for tracking changes. CQRS: Event Store.

165) CQRS Type of Messages? 
Answer: Command: Express the intent to change the application's state, for example CreateProductCommand, UpdateProductCommand.
Query: Express the desire for information, for example FindProductQuery, GetUserQuery. Event: Represent a notification 
that something relevant has happened, for example ProductCreatedEvent, ProductUpdatedEvent.

166) Describe CQRS? 
Answer: Separate read and write operations. Better performance on read part using right technology for reading, 
and preventing conflicts with update commands. Scale each part separately. Leads to eventual consistency, 
as the read part is updated asynchronously. Once the write operation is persisted, an event is stored in event-store.
Events can be replayed multiple times based on requirements to create different type of query store.

167) Context switch system calls? 
Answer: shed.h: Header file containing definitions of structures and functions related to process and thread scheduling.
clone(): A system call that creates a new thread or process. pthread_create(): C function, creates a new thread.
pthread_yield(): C function, allows a thread to voluntarily donate CPU time to another thread waiting for execution. 
sem_wait() and sem_post(): C function, synchronization by using semaphores. mutex_lock() and mutex_unlock(): C function, 
synchronization by using mutexes.

168) Context Switch Algorithms? 
Answer: Round-Robin: Divides CPU time to each task in order with a fixed time cycle. Priority-Based: Processor time is 
allocated based on priorities. Shortest Job First: Allocates CPU to jobs based on their length. Guaranteed Scheduling: 
Ensures that each task receives a specific amount of CPU time.

169) Containerization and its significance in microservices deployments? 
Answer: Containerization is the process of packaging an application and its dependencies, including libraries and config 
files, into a single, lightweight unit called container. Significance in Microservices: Consistency, Isolation, Portability, Scalability.

170) What is a Container Orchestration? 
Answer: Automated management of containerized apps, including tasks like deployment, scaling, Load Balancing and service discovery.
Significance of Microservice Management: Deployment. Scaling. Service Discovery. Load Balancing. Self-Healing. Rolling Updates.
Examples: Kubernetes, Docker Swarm, Amazon ECS.

171) What is a Consistent Hashing? 
Answer: A type of hashing that minimizes the number of keys that must be remapped when the hash table changes. 
Used by LB to distribute traffic, it minimizes the number of requests that will be transferred to another server if 
the existing server goes down.

172) Concept of service mesh in microservice architecture? 
Answer: A service mesh is a network of proxies (sidecars) deployed alongside microservices to handle communication, 
monitoring, and security concerns. It acts as a control plane for managing traffic and enforcing policies.
Improves communication, LB, routing and retries. Enhance observability, centralized logging, metrics collection, and tracing.
Strengthens security with features like authentication, authorization and encryption. Benefits of service mesh: 
Traffic management: Load Balancing, circuit braking, routing. Observability. Security. Resilience: Circuit Braking, Retries. 
Policy Enforcement. Tools Example: Istio.

173) Concept of event-driven architecture in the context of microservices? 
Answer: Event-driven architecture relies on the production, detection, consumption, and reaction to events, 
which are discrete occurrences of state change within a system.
Loose coupling between services. Enables real-time responsiveness. Supports scalability. Fault tolerance.

174) Concept of distributed database? 
Answer: Distributed databases are databases that store data across multiple nodes or servers, 
often geographically distributed. Each node holds a portion of the data, and these nodes work together to provide data 
access and redundancy. Enable to access data while maintaining CAP. Supports horizontal scalability. Enforce fault tolerance. 
Benefits of Distributed Databases: Scalability. High Availability: Data replication. Reduced Latency.
Data Consistency: Thorough quorum-based systems or eventual consistency model. Challenges: Complexity. Latency. 
Consistency Trade-offs: Strong consistency across all nodes at cost of increased latency or decreased availability.

175) Concept of containerization in the context of microservices architecture using Kubernetes? 
Answer: Containerization involves building an application and its dependencies into a single, portable container image. 
Containers run consistently across different environments, from dev to prod. Simplifies packaging and deployment.
Ensures consistency between dev, testing and prod environments. Isolates apps, preventing conflicts between dependencies.
Benefits: Portability. Resource Efficiency. Consistency. Isolation. Kubernetes is an open-source container orchestration 
platform that automates the deployment, scaling and management of containerized apps. It provides features like load balancing, 
scaling, self-healing, and rolling updates. Containerization. Kubernetes Deployment: Management desired state of an app, 
num of replicas, resource allocation, scaling policies. Scaling: Based on resource utilization. Load Balancing. Rolling Updates.

176) Common Service Discovery Methods and Tools? 
Answer: DNS-Based Service Discovery: Netflix Eureka, Spring Cloud Netflix. Key-Value Stores: consul, etcd, zookeeper. 
API Gateway or Reverse Proxy: Nginx, HAProxy, Cloud based API gateway. Sidecar Proxies: Envoy, Linkrd, Istio. 
Cloud-Based Service Discovery: AWS ELB, Google Cloud Load Balancing.

177) Command Query Responsibility Segregation (CQRS)? 
Answer: Separate read and write operations. Better performance on read part using right technology for reading, 
and preventing conflicts with update commands. Scale each part separately. Leads to eventual consistency.
Eventual Consistency: The data will reach to Read Store with a small delay.

178) Clean Architecture? 
Answer: Separation of concerns by dividing the software into different layers. Use Dependency Injection and Polymorphism 
to create a dependency rule to make the domain layer most independent and stable layer. Source code dependencies can 
only point inwards, towards to domain layer. Independent of framework, UI, Database and any external agency.
Testable domain layer without requiring any external element. Define Entities and Use Cases for domain logic.
Hexagonal & Onion Architecture. Leaves as many options open as possible for as long as possible.
All dependency across point to business layer, making it independent and stable. Delay implementation details of dependencies.
Easier to test the business logic by mocking the dependencies even before deciding the implementation details.
Replace an adapter implementation easily without touching the business logic. Independent development and deployment of different parts.

179) Circuit Breakers in context of microservice architecture, how contribute to the resilience? 
Answer: Circuit breakers are a design pattern used in microservices to handle failures in remote services or dependencies.
They monitor the health of a service and, when failures or timeouts exceed a threshold, temporarily "open" the circuit, 
preventing further requests to the failing service. Enhance resilience, by preventing cascading failures.
Reduce load of failing services. Provide a graceful degradation of functionality when a service is unavailable.
Tools Example: Hystrix, resilience4j.

180) Choreography-Based Saga compensation flow? 
Answer: Initial Flow: Create Order operation. Reserve Product operation. Compensating Transaction: Cancel product reservation. 
Reject order. Initial Flow: 1,2,3,4,5,...,n. Compensating Transactions: n,...,5,4,3,2,1.

181) Choreography Pattern? 
Answer: Scalability. Helps scale a complex flow of business operations. Event Driven Architecture using choreography. 
Communication is asynchronous. Example: SAGA.

182) What is a Change Data Capture? 
Answer: Use Push methods opposed to Pulling. Push database records into target source (Kafka) by reading from Transaction logs. 
Transaction Log: Data change records (Insert / Update / Delete) made be each transaction. In Postgres Transaction 
Log is called as Write Ahead Log (WAL). Provides near real-time data with very low overhead by listening the Transaction 
Logs and sending to target source (Kafka) immediately.

183) Challenges and solutions related to maintaining data consistency?
Answer: Challenges: Data Ownership. Distributed Transactions. Data Synchronization. Data Duplication.
Solutions: Event-Driven Architecture. Saga Pattern. Caching. API Design. Idempotent Operations. Data Versioning.
Consistency Level. Distributed Tracing and Monitoring. Compensating Actions. Testing and chaos engineering.

184) CDN Solutions & Cloud Technologies? 
Answer: Cloud Technology: Cloudflare, Fastly, Akamai, Amazon Cloud Front, Google Cloud Platform CDN, Microsoft Azure 
Content Delivery Network.

185) CDN Publishing Strategy? 
Answer: Pull Strategy: Lower maintenance from our part. Everything is taken care of by the CDN provider.
Push Strategy: If our content doesn't change frequently we can simply push it once to the CDN.

186) Can you explain challenges associated with data management in microservices architecture, strategy + pattern? 
Answer: Challenges: Data Consistency, Data Ownership, Data Integrity, Data Access Pattern, Data Volume.
Strategies: Database per service. Shared Database. Event Sourcing. CQRS: Separate the command (write) and query (read) 
responsibilities into different components. Command side updates data, and query side retrieves data. 
API Composition: Service retrieve data from multiple sources and compose it into a single response before sending it to the client. 
Distribute Cache Redis. Data Versioning and Compatibility: To manage changes to data structures while maintaining 
backward compatibility for existing services. Data Governance and Ownership.

187) What is Caching? 
Answer: Hardware or software that stores data. They are used to store responses to network requests, 
but also the results of long computations. Data may be out of date (permanently). The DB has been updated but 
the feature has not. Eviction policy: LRU: Least Recently Used. LFU: Least Frequently Used. FIFO: First In First Out.
CDN (content delivery network): Servers scattered around the world means that the delay will be low.
Also called PoP (Point of Presence). Example, Cloud Flare.

188) Business Logic in Clean Architecture? 
Answer: Entities: Has the core business logic with the critical business rules. 
Use Cases: Coordinates Entities and includes application specific business rules.

189) What is a Bridge network? 
Answer: Creates a network to connect with hostnames.

190) Bounded Event DDD? 
Answer: Domain events: For the communication between bounded context. Represents something that happened in the domain. 
For example shopping cart. This event is triggered by bounded context order process, and it is received by the bounded 
contexts shipping and payment to initiate shipping and invoicing of the order.

191) Boundary Testing and its importance in software testing? 
Answer: Defect identification: Boundary conditions are often where software defects, such as overflow, 
out-of-memory errors, or unexpected behavior. Robustness: Application handle extreme inputs gracefully. Security.

192) Boundary Testing? 
Answer: Involves testing values that are on the boundary or edge of valid input ranges or conditions.
It aims to uncover issues such as off-by-one errors, buffer overflow, and unexpected behavior near limits.
To achieve: Accuracy, Security, Quality.

193) Black-Box vs White-Box testing, when use? 
Answer: Black-Box: In the early stages of testing to validate that the software meets its functional requirements.
White-box: When you need to delve deep into the code to identify defects, security vulnerabilities, or performance bottlenecks.

194) Black-Box Testing Example? 
Answer: Testing a web apps login page without knowing the underlying code. Testers provide various inputs 
(valid and invalid username/passwords) and observe how system responds.

195) Black-Box Testing? 
Answer: Focuses on testing the functionality of a software app without any knowledge of its internal code or structure.
Usage: Used to validate that the software meets its specified requirements and functions correctly from end-user perspective.
Advantages: Testers don't need knowledge of internal code, making it suitable for non-developers.
Disadvantages: It may not uncover certain types of defects that require knowledge of code.
Test cases may be redundant if requirements are not well-documented.
Test by deploying a microservice and checking its log output and metrics.
This ensures that deployment, logging, and monitoring all conform to the defined macro architecture.
Such test is called a black-box-test, which checks the behavior of the microservice from the outside.

196) Describe a Bankers Algorithm? 
Answer: Algorithm used in OS to avoid deadlocks in a multi-processors systems.
Assumptions: Process has to declare max number of resources which will need. All resources assigned to process at the 
beginning, but can be released and assigned to other processes. Maintains actual lists of available and used resources. 
If process want to acquire a resource, algorithm checks whether allocation won't lead to deadlock. Simulates assign 
resources and checks.

197) What is Avro? 
Answer: Compact, fast, binary data format that relies on schemas.

198) Availability how is it obtained? 
Answer: Load Balancing: distributing the load among multiple servers. Redundancy.
Data Replication: copying data between several servers. Monitoring and warnings. Automatic failover.
Disaster Recovery: A plan to restore your system after a disaster.

199) Automated Testing in software development and its advantages? 
Answer: Advantages: Efficiency, Consistency, Regression Testing, Coverage, Continuous Integration.

200) Asynchronous Microservice? 
Answer: A service is asynchronous if: It does not make a request to other microservices while processing requests.
It makes a request to other microservices while processing requests and does not wait for the result.

201) Describe Distributed architecture? 
Answer: Many devices and machines connected via a network. Multiple devices work together to perform a single task or 
set of tasks, sharing resources and information among themselves. Client-server architecture. Peer-to-Peer Architecture. 
Microservice architecture. Cloud-based architecture. Distributed architecture allows for better scalability, reliability, 
availability and fault tolerance, the load distributed among devices and resources can be shared. Easier to manage and update. 
Challenges like network latency, network errors, data consistency, security.

202) API versioning in microservices architecture? 
Answer: API versioning is a practice of defining and managing multiple versions of an API, allowing clients to interact 
with specific versions and ensuring backward compatibility. Facilitates the introduction of changes.
Allows clients to choose the API Versions. API versioning strategies: URI Versioning, Header Versioning, 
Query Params Versioning, Media Type Versioning.

203) API Gateways (Definition + Challenges)? 
Answer: Central entry point for managing and exposing API (Reverse Proxy).
Security. Simplified Client Interface. Load Balancing. Caching. Analytics and Monitoring.
Challenges: Service Discovery. Latency. Complexity. Security Config. Scalability. Rate Limiting. Monitoring and Logging.
API Versioning. Example, netflix eureka

204) API Gateway Solutions & Cloud Technologies? 
Answer: Netflix Zuul. Amazon API Gateway. Google Cloud Platform API Gateway. Microsoft Azure API Management.

205) API Definition Example? 
Answer: REST API over HTTP: Identifying Entities. Mapping Entities to URIs. Defining Resources Representation. 
Assigning HTTP Methods to operations on resources.

206) Apache Kafka Performance? 
Answer: Partitioning: Data separated into multiple partitions. Replication: Replicating partitions across multiple nodes, 
improving system reliability and availability. Scalability: Scalable vertically and horizontally. You can increase the 
number of nodes in the cluster to increase throughput and processing speed. Multiple consumers within one partition. 
High Throughput: Utilizes memory caching, high-performance communication protocols and data processing algorithms. 
Low network overhead (data compression). Flexibility (integration with other tools).

207) Apache Kafka data formats? 
Answer: Apache Kafka transmits data in the form of messages, which are usually serialized in one of several formats.
JSON: readable, easy to process, less efficient than more optimized formats, and takes up more space.
Avro: A data serialization format that is used in big data projects such as Apache Hadoop. 
The advantage of avro is that it is very efficient and has a small size compared to other data formats. 
The disadvantage is that it is more complicated and requires more code to support. Protobuf: Data serialization format 
offered by Google. MessagePack: Designed to be easy to read by humans and very efficient to process by programs.
In some cases like Kafka Streams, you can also use other data formats such as Java Object Serialization or Apache Trift.

208) Apache Kafka commit offset? 
Answer: Mechanism to confirm reading of messages from the partition by consumers. Offset number of the last read message.
Confirmation of this offset is sent to the broker and stored in the so-called consumer group.
It ensures that each consumer reads the message only once. Thanks to this, when a new consumer appears, Kafka knows what 
message has already been processed. Automatic and manual committing of offsets.

209) Apache Avro? 
Answer: The data serialization system allows data to be transferred between various programming languages and computer systems.
Avro defines a data serialization format in the form of schemas that describe the structure of the data.
Schemas are independent of the programming language. Application: distributed systems, data warehouses, stream processing 
systems, Big Data. Apache Hadoop, Apache Kafka, Apache Spark. Advantages: Compact data format. Independence from programming language.
Availability of many tools: rich library in various programming languages. In short, Avro is a system that allows you to 
move data between different programming languages and platforms, which is especially useful in distributed systems and Big Data.

210) What is AES? 
Answer: Standard for Advanced Encryption Standard. AES is a widely used encryption standard that has three symmetric-key 
algorithms (AES-128, AES-192, and AES-256). Off note AES considered to be the "gold standard" in encryption and is even 
used by the U.S. National Security Agency to encrypt top secret information.

211) Advantages and challenges of using microservices architecture in the development of distributed systems?
Answer: Advantages of microservices: Scalability. Modularity. Flexibility. Rapid Development. Fault isolation.
Challenges: Complexity. Inter-Service communication. Data Consistency. Deployment and Monitoring. Testing.

212) What are some of the purposes of a message broker? 
Answer: Temporarily/permanently store messages between a producer and a consumer Decouple the producers from the consumers.
Provides buffering capabilities to absorb sudden traffic spikes.

213) What is the primary purpose of a load balancer in a system? 
Answer: Distribute traffic among a group of servers running the same service.

214) What are some potential drawbacks of DNS-based load balancing? 
Answer: It exposes server IP addresses to clients. It does not actively monitor the health of servers.

215) What is the key difference between hardware load balancers and software load balancers? 
Answer: Software load balancers run on general-purpose computers, while hardware load balancers are dedicated devices.

216) What is the main benefit of Global Server Load Balancers (GSLB) regarding system performance? 
Answer: They provide intelligent routing based on various criteria, including geographical location and server load in a 
particular region.

217) What is the role of a Global Server Load Balancer (GSLB) in disaster recovery situations? 
Answer: It routes users to different locations for higher availability.

218) What is the most correct statement regarding an API Gateway? 
Answer: An API Gateway routes user requests to different microservices based on the request's API.

219) Please select the features that an API Gateway typically provides. 
Answer: Rate Limiting, Protocol Translation, Monitoring, Authorization.

220) What are the benefits of making an application stateless? 
Answer: a) Lower memory footprint of the application. b) We can easily scale our system horizontally by running a group 
of identical application instances behind a load balancer. c) Seamless integration and implementation with HTTP protocol.

221) What is the main purpose of a CDN? 
Answer: To speed up the delivery of static content to end-users

222) How does the CDN solve the latency problem when the user is far from our system's data center? 
Answer: The static content is stored on the CDN's edge servers, which are physically closer to the user or are more 
strategically located in terms of network.

223) How does a CDN improve the availability of our system? 
Answer: When our data centers are experiencing degraded performance or reduced network capacity, 
it becomes less noticeable for the users since most of the traffic is going to the CDN.

224) Our security experts were notified that our system is under a DDoS (Distributed Denial of Service) Attack. 
Which options will be an effective measure to reduce our vulnerability to it? 
Answer: a) Incorporate a CDN. b) Add rate-limiting to the API Gateway. Incorporating a CDN will help distribute the 
attack across a very large number of edge servers outside our organization. Additionally, adding rate-limiting will 
limit the number of traffic our services get at a given time period, eliminating the possibility of one of our services 
getting overloaded, which may lead to a cascade of failures.

225) What is a database transaction? 
Answer: A set of operations that, for an external observer, appears as a single operation that either commits or aborts.

226) What does Consistency in the context of an ACID transaction mean? 
Answer: A transaction that was already committed will be seen by all future queries/transactions. A transaction doesn't 
violate any constraints we set on our data.

227) Durability? 
Answer: Durability guarantees that once a transaction is complete, its final state will persist and remain inside the database.
Since this rating record is missing, Durability is the property that was violated by this specific database technology.

228) statements regarding SQL and NoSQL databases? 
Answer: NoSQL databases typically use the same data structures as most programming languages, while SQL databases do not.
SQL databases are optimized for efficient storage. NoSQL databases are optimized for efficient queries. NoSQL databases 
are easier to scale horizontally than SQL.

229) Which database operations are typically more limited in NoSQL databases than in SQL databases? 
Answer: ACID Transactions. Join operations.

230) Example of use cases for a graph database? 
Answer: Storing the data about businesses, their locations within zip codes, cities, and countries, and what customers 
have visited those businesses.

231) What tradeoff do we make by introducing indexing to a database? 
Answer: We get faster read operations at the expense of additional space and slower insert operations.

232) What are the benefits of database replication? 
Answer: Prevention of a Single Point of Failure. Higher Availability. Potential higher query throughput.

233) What is NOT a step in capturing functional requirements? 
Answer: Deciding which programming language or technology is best for this situation.

234) An API is a contract between? 
Answer: The engineers who implement the system and the clients that use the system

235) Examples of idempotent operations? 
Answer: a) Delete a user by ID from the database. b) Update the user's phone number to 902-3232-1111. c) Checking the 
account balance of a user by ID. d) Multiplying a number by 0.

236) Why are idempotent operations preferable over non-idempotent operations? 
Answer: Idempotent operations allow safe retries. In a distributed system with unreliable components communicating with 
each other over an unreliable network, idempotent operations allow us to safely retry them without worrying about adverse effects.

237) Examples of scenarios where we need to use API Pagination? 
Answer: a) An API endpoint that provides a social media feed for a user. b) An API endpoint that provides a list of 
products in an online store that matches a keyword. c) An API endpoint that provides all the financial transactions in a 
user's bank account

238) What is the correct statement when comparing between REST and RPC? 
Answer: RPC looks and feels more like a local method invocation than a REST API call.A Remote Procedure Call uses Location 
Transparency and abstracts away all the complexities of the marshaling/unmarshaling of data and the management of the network 
communication between the client and the server. Therefore, a typical RPC looks like a simple method call, no different 
from any other local method.

239) Statements regarding RPC invocations when compared to local method invocations. 
Answer: RPC calls are generally slower than local method invocations. RPC calls are generally less reliable than local 
method invocations. RPC invocations abstract away a network call to a remote service, requiring the data to be encoded 
in a binary format and waiting for the packages to travel through the network. Therefore, RPC invocations are less 
reliable and slower than a local method call.

240) Statement about REST API? 
Answer: REST is a set of architectural constraints and best practices for defining APIs for the web.

241) One of the core principles of a RESTful API is statelessness. What quality attribute does this principle 
provide to our system? 
Answer: High Scalability. When our application is stateless, we can run multiple instances of it, which allows us to 
scale our system horizontally.

242) One of the core principles of a RESTful API is cacheability? 
Answer: The server needs to implicitly or explicitly define a response as either cacheable or not cacheable by the client.
In a REST API, each response needs to be marked as cacheable or not cacheable so the client knows whether it is safe to 
cache that response. When using HTTP to implement a REST API, any GET requests are cacheable by default. Requests using 
the POST method are not cacheable by default but can be made cacheable. DELETE and PUT methods are used for deleting and 
updating a resource, respectively, and are not cacheable.

243) What does MTBF stand for? 
Answer: Mean Time Between Failures.

244) What does MTTR stand for? 
Answer: Mean Time To Recovery.

245) According to the definition of fault tolerance, what happens during a failure at a "fault-tolerant" system? 
Answer: The system will continue to operate at the same or a reduced level of performance.

246) What are the core principles of fault tolerance? 
Answer: Failure Prevention. Failure Detection. Recovery from Failure.

247) Which is an example of spatial redundancy in the context of failure prevention? 
Answer: Running a service as a group of identical instances on different computers

248) What is an example of a Time Redundancy in the context of fault tolerance? 
Answer: Retrying a failed request multiple times.

249) What is one drawback of synchronous communication between services? 
Answer: It may result in high latency for long-running operations.

250) What is one problem of synchronous communication solved by asynchronous, event-driven architecture? 
Answer: Synchronous communication requires both the sender and receiver to maintain an active connection. And be available 
at the same time. Asynchronous, Event-Driven architecture doesn't.

251) What is the difference between Multi-Tier Architecture and Multi-Layer Architecture? 
Answer: Multi-Tier architecture involves the physical distribution of components across different runtime units running 
on different services. In a Multi-Layer Architecture, the code is organized in separate logical units for better 
modularity but runs as a single runtime unit.

252) Why is placing business logic in the Presentation Tier an Anti-Pattern? 
Answer: It is accessible (and is modifiable) by the user in the web browser. If shipped as a mobile or desktop app, 
it can also be easily disassembled and reverse-engineered.

253) What is not a property of an event in an event-driven architecture? 
Answer: Events map 1 to 1 to a row or record in a database. Events don't have to be mapped to records in a database.
For example, a user leaving a review and a rating for an online course or a product can be stored as 2 separate records 
by 2 different services and databases.

254) Describe the benefits of the CQRS Pattern in Microservices. 
Answer: In systems with complex business logic, where the operations to update data are significantly different from the 
operations to read data, CQRS allows for a cleaner separation of concerns by separating the Command part of the system 
and the Query part of our system into 2 separate services. When the user request requires data from 2 different 
microservices to be joined, we can use the CQRS pattern to maintain the joined data in a read-only service.
Using CQRS, we can optimize our system for both read and write operations, which is otherwise very hard to achieve.

255) Integration Design Patterns.
Answer:Integration design patterns are crucial for creating robust and scalable software architectures that allow different systems, 
applications, or services to work together seamlessly. These patterns help manage complexities, improve maintainability, 
and ensure reliable communication between integrated systems. Here are some key integration design patterns:
a) Message Patterns:Message: Basic unit of data passed between systems. Command Message: Encodes a command for the receiver to process.
Document Message: Contains a document that is intended to be processed by the receiver. Event Message: Notifies a receiver 
about something that has happened.
b) Messaging Channels: Point-to-Point Channel: Ensures that only one receiver will get a particular message.
Publish-Subscribe Channel: Allows multiple receivers to receive messages from a single channel. Dead Letter Channel: 
Handles messages that cannot be processed by their intended receiver. Invalid Message Channel: Deals with messages that 
do not conform to the expected format. 
c) Message Routing: Content-Based Router: Routes messages to different channels based on their content.
Message Filter: Removes certain messages from a channel based on specific criteria.
Recipient List: Sends a copy of a message to a list of recipients. Splitter: Breaks a single message into multiple smaller 
messages. Aggregator: Combines multiple messages into a single message.
d) Message Transformation: Message Translator: Converts a message from one format to another.
Envelope Wrapper: Adds or removes an envelope to/from a message. Content Enricher: Adds data to a message. 
Content Filter: Removes data from a message.
e) Message Endpoints: Message Endpoint: Interface for the application to send or receive messages.
Polling Consumer: Checks for messages at regular intervals. Event-Driven Consumer: Listens for messages and processes 
them as they arrive. Competing Consumers: Multiple consumers compete to process messages from the same channel.
Message Dispatcher: Controls the routing of messages to different endpoints.
f) System Integration Patterns: Shared Database: Multiple applications access the same database for integration.
Remote Procedure Invocation (RPI): Direct calls to remote services, akin to invoking a method in local code.
File Transfer: Exchanging data via files placed in shared locations. Messaging: Using message queues or topics to exchange 
information asynchronously. RESTful Services: Exposing services through HTTP using REST principles. SOAP Services: 
Using SOAP protocol for exchanging structured information.
g) Enterprise Service Bus (ESB) Patterns: Service Orchestration: Coordinating multiple services into a single workflow.
Service Choreography: Services working together without a central coordinator. Service Proxy: Acting as an intermediary 
for service requests. Message Broker: Decoupling the sender and receiver, managing message routing and transformation.
h) Security and Reliability Patterns: Message Authentication: Ensuring that the sender of a message is who they claim to be.
Message Encryption: Protecting the message content from unauthorized access. Guaranteed Delivery: Ensuring that a message 
is delivered at least once. Idempotent Receiver: Ensuring that a receiver can process the same message multiple times 
without side effects. Transactional Messaging: Ensuring a series of message operations complete successfully or not at all.
i) Batch Processing Patterns: Batch Sequential Processing: Processing data in large chunks at a scheduled time.
Batch Event Processing: Processing data in response to certain events. Bulkhead: Isolating resources in a system to 
prevent cascading failures. 
j) Monitoring and Management Patterns: Health Check: Regularly checking the health of services and components.
Circuit Breaker: Preventing repeated attempts to access a service that is known to be failing. Throttling: Controlling 
the rate at which services are called to avoid overloading. Log Aggregation: Collecting and aggregating logs from different 
parts of the system. 
These patterns are often used in combination to address complex integration requirements in a 
structured and maintainable way. Understanding and implementing these patterns can help ensure that systems are integrated 
efficiently and can handle the demands of modern, distributed applications.

256) Database Indexes?
Answer: A database index is a data structure that improves the performance of database operations by providing quick access
to specific rows in a table. Indices are used to optimize the performance of `SELECT`, `UPDATE`, and `DELETE` statements.
There are several types of indexes that can be used in a database, including:
a) B-Tree index: This is the most common type of index used in relational databases. It is a balanced tree data structure 
that allows for fast searching, insertion, and deletion of data. B-Tree index can be used for both small and large tables, 
and it's effective for both exact-match and range queries.
b) Bitmap index: This type of index uses a bitmap to represent the presence or absence of a value in a specific column.
Bitmap indexes are useful for data warehousing and business intelligence (BI) applications, where queries often involve multiple conditions.
c) Hash index: This type of index uses a hash function to map the values in a column to a specific location in the index.
Hash indexes are useful for exact-match queries, but they are not suitable for range queries.
d) Clustered index: This type of index determines the physical order of the data in the table. A table can have only one 
clustered index, which is usually created on the primary key of the table.
e) Non-clustered index: This type of index does not determine the physical order of the data in the table. A table can have 
multiple non-clustered indexes.
It's worth noting that, while indexes can improve query performance, they also have a cost. Indexes take up disk space, 
they need to be updated when the data in the table changes, and they can slow down data modification operations.
Therefore, it's important to choose the right type of index and to use them judiciously.

257) Bloom filter? 
Answer: A Bloom filter is a space-efficient probabilistic data structure that is used to test whether an element is a member 
of a set or not. It was invented by Burton Howard Bloom in 1970. A Bloom filter is essentially a bit array of size m, 
with all bits initially set to 0. To add an element to the filter, several hash functions are applied to the element to 
produce k different bit positions in the array. The bits at these positions are then set to 1. To check if an element is 
in the filter, the same hash functions are applied to the element and the bits at the resulting positions are checked.
If all the bits are 1, it indicates that the element is probably in the filter. If any of the bits are 0, it indicates 
that the element is definitely not in the filter. The key idea behind a Bloom filter is that multiple hash functions are 
used to map an element to multiple positions in the bit array. This allows for a high probability of a 1-bit collision, 
resulting in a false positive, but it also ensures that a 0-bit collision, resulting in a false negative, is highly unlikely.
The parameters of a Bloom filter, such as the size of the bit array, the number of hash functions, and the number of 
elements in the filter, affect the false positive rate of the filter. The false positive rate can be controlled by adjusting 
the size of the bit array and the number of hash functions. Bloom filters are used in a variety of applications such as:
a) Network routers to check if a packet has already been seen before. b) Web browsers to check if a URL has already been visited.
c) Distributed systems to check if a key is present in a distributed hash table.
Bloom filters have the advantages of being space-efficient and having a constant time complexity for both adding and 
checking elements, but they also have the drawback of having a non-zero false positive rate.

258) Merkle tree?
Answer: A Merkle tree (also known as a hash tree) is a data structure that is used to efficiently verify the integrity of
large amounts of data. It is a type of binary tree, where each leaf node represents a data block, and each non-leaf node is a hash
of its child nodes. The root node of the tree represents the hash of the entire data set. The process of creating a Merkle 
tree begins by breaking the data set into smaller blocks and then computing the hash of each block.
These hashes are then organized into a binary tree, with each leaf node containing the hash of a data block, and each 
non-leaf node containing the hash of its child nodes. This structure allows for a very efficient way to verify the integrity 
of the data set, because a change in any single data block will result in a change to the hash at the root of the tree.
Merkle trees are used in a variety of applications, such as: a) Cryptocurrency: Merkle trees are used in blockchain 
technology to ensure the integrity of transactions and to prevent double-spending.
b) Distributed systems: Merkle trees are used in distributed systems to efficiently verify the integrity of data that is 
stored across multiple nodes. c) File systems: Merkle trees are used in file systems to ensure the integrity of large 
files and to enable efficient detection of changes to the file. d) Cloud storage: Merkle trees are used in cloud storage 
to ensure the integrity of data that is stored across multiple servers and to enable efficient detection of changes to the data.
In summary, Merkle Trees are used to efficiently verify the integrity of large amounts of data, it works by breaking the 
data into smaller blocks, computing the hash of each block, and then organizing these hashes into a binary tree. The root 
node of the tree represents the hash of the entire data set. This structure allows for a very efficient way to verify the 
integrity of the data set, because a change in any single data block will result in a change to the hash at the root of the tree.

259) Dining Philosopher?
The Dining Philosophers problem is a classic problem in the field of concurrent programming and computer science.
It was first introduced by Edsger Dijkstra in 1965 as a way to illustrate the challenges of concurrent programming
and the use of semaphores to solve synchronization problems. The problem describes a scenario where five philosophers 
are sitting at a round table with five plates of spaghetti. Each philosopher has a fork to their left and right, and they 
spend their time thinking and eating. In order to eat, a philosopher must pick up the fork on their left and the fork on 
their right. The problem is to design a protocol that ensures that the philosophers can eat without getting into a deadlock or
a starvation state. If any philosopher is unable to acquire both forks, they will be unable to eat and the others will 
also be blocked. There are several solutions to the Dining Philosophers problem, one of them is to use semaphores to control 
access to the forks. Each fork can be represented by a semaphore, and a philosopher can acquire a fork by waiting on the 
semaphore. When a philosopher is done eating, they release the semaphore, allowing another philosopher to acquire the fork.
Another solution is using the "Chandy/Misra" solution where each philosopher is given a unique priority and philosopher 
with the lowest number has the first chance to pick up the fork, this solution also solves the problem of Deadlock and starvation.
The Dining Philosophers problem is often used as a teaching tool to demonstrate the importance of proper synchronization 
in concurrent programming and the challenges of designing correct and efficient concurrent algorithms.











 


































