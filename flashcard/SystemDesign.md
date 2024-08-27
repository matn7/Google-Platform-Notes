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
Answer: 
Synchronous:  
a) Immediate responses are critical. 
b) Request response interactions. 
c) Performance bottleneck and reduced fault tolerance. 
Asynchronous: 
a) Real-time responses are not necessary, and you want to decouple components for scalability, reliability and fault-tolerance. 
b) Background processing, event-driven architecture. 
c) Introduce complexity in handling eventual consistency and message passing.

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
Answer: 
a) Memory management. 
b) Isolation between processes. 
c) Schedule process and their threads on CPU cores. 
d) Provide security and interface for accessing the hardware.

13) What are arguments for a higher number of partitions? 
Answer: More opportunity for concurrency.

14) Web Service W3C Definition? 
Answer: Software system designed to support interoperable machine-to-machine interaction over network. 3 Keys: 
a) Designed for machine-to-machine (or app to app) interaction. 
b) Should be interoperable, not platform dependent. 
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
Answer: Ask Questions to capture: 
a) Functional Requirements. 
b) Non-functional Requirements. 
c) System Constraints. 
Define the System APIs. Create a Software Architecture Diagram to fulfill the Functional Requirements. 
Refine the Software Architecture Diagram to address the Non-Functional Requirements.

20) System Design Software Extensibility Architecture Pattern? 
Answer: 
a) Sidecar & Ambassador. 
b) Anti-corruption Adapter pattern. 
c) Backends for Frontends pattern.

21) System Design Latency? 
Answer: Time required to send packets between two points in computer network. Measured in milliseconds. Caused by 
propagation time, waiting for free resources. The lower, the better.

22) System Design Cloud Scalability Pattern? 
Answer: 
a) Load Balancing. 
b) Pipes and Filters. 
c) Scatter Gather. 
d) Execution Orchestrator for Microservices Architecture. 
e) Choreography for Microservices Architecture.

23) System Design Cloud Performance Pattern for Data Intensive Systems? 
Answer: 
a) Map Reduce. 
b) The Saga Pattern. 
c) Transactional Outbox Pattern: Reliability in Event Driven Architecture. 
d) Materialized View Pattern: Architecting High Performance Systems. 
e) CQRS. 
f) CQRS + Materialized View. 
g) Event Sourcing Pattern.

24) What is a symmetric encryption? 
Answer: Single key for encryption and decryption.

25) Process data structures? 
Answer: Process Table, Thread Table, File Table, IPC (Inter Process Communication), I/O (Input/Output).

26) What are difference between Strategic DDD and Tactical DDD? 
Answer: Strategic DDD: Introduces boundaries from domain model. Single bounded context per each domain. What is a Domain? 
Operational area of your application (example online food ordering). Unbounded Context: Central Pattern in DDD. 
Boundary within a Domain. Tactical DDD: Implementation patterns: 
a) Entities: Core domain objects with a unique identifiers.
b) Aggregates: Group of Entity objects which always need to be in consistent state. 
c) Aggregate Root (AR).
d) Value Objects: Immutable objects without identity. Only value matters. Bring context to the value. 
e) Domain Service.
f) Application Service. 
g) Domain Event.

27) Difference between SQL and NoSQL? 
Answer: SQL table-based relational DB. Data is stored in precisely defined structures and the relationships between them 
are represented using foreign keys. Good at querying multiple tables and large data sets, as well as maintaining transaction 
consistency and security. NoSQL allows you to store data in various formats, such as documents, graphs, key-value or column.
More flexible and scalable than SQL. Good for projects with a lot of load data. Error-resistant because the data is replicated.

28) What are different Specialized Storage Paradigm? 
Answer: 
a) Blob Storage: GCS or S3. 
b) Time Series Database: Special kind of database optimized for storing and analyzing time-indexed data. Data points that 
specifically occur at a given moment in time. InfluxDB, Prometheus and Graphana. 
c) Graph Database: Social Networks Neo4j. 
d) Cypher: Graph query language that was originally developed for the Neo4j graph database. 
e) Spatial Database: Storing spacial data like locations on a map. Spatial indexes like quad-trees.

29) What is SOAP? 
Answer: Data Format: SOAP XML Request. SOAP XML Response. Transport: SOAP over MQ. SOAP over HTTP.
Service Definition: WSDL defines: Endpoint, All Operations, Request Structure, Response Structure.

30) What is Smoke Testing? 
Answer: Smoke Testing also known as build verification testing, involves running a subset of test cases to verify 
that core functionalities of a software applications are operational after a new build or release. Quick feedback on whether 
build is stable enough for more extensive testing. Identifies major issues. Prevents wasting time on comprehensive testing.

31) Set Based Consistency CQRS? 
Answer: How to check if record already exists in a database table? How to check if User already exists? Solution Lookup 
database table. Message Dispatch Interceptor: 
a) Will intercept a command before it is processed by the command handler. 
b) Use JPA to query lookup table. 
c) Event handler to persist productID and title in lookup table.

32) Service Discovery and Load Balancing in Microservices? 
Answer: Service Discovery: 
a) Simplifies service-to-service communication. 
b) Facilitates stability and fault tolerance.
c) Supports dynamic environments. 
Load Balancing: 
a) Enhances Reliability. 
b) Improves Scalability. 
c) Fault Tolerance.
Tools and Technologies: 
Service Discovery: consul, etcd, zookeeper, kubernetes. 
Load Balancing: Reverse Proxy: Nginx, HaProxy. 
Service Mesh: Istio, Linkerd. 
Load Balancers: Google Cloud LB.

33) SOAP Service Definition? 
Answer: Request / Response Format, Request Structure, Response Structure, Endpoint.

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
Answer: 
a) Error Handling. 
b) Monitoring and logging. 
c) Versioning and rollback. 
d) Testing and validation. 
e) Redundancy and Fail-over. 
f) Communication protocols. 
g) Service Discovery. 
h) Circuit Braking: Preventing cascading errors.
i) Graceful Degradation: The application works even at reduced levels.

41) Robust system, what does it mean? 
Answer: A robust system is able to operate efficiently, even in the presence of errors or other problems. 
Analogy to a 2-engine airplane. Key Features: Fault Tolerance, High Availability, Scalability, Resilience, Security, 
Efficiency, Manageability, Flexibility.

42) REST key abstraction? 
Answer: A resource has URI. A resource can have different representations: XML, JSON, HTML.

43) What are Rest API Resources - Best practices? 
Answer: 
a) Naming resources using nouns. 
b) Making a distinction between collection resources and simple resources. 
c) Giving the resources clear and meaningful names. 
d) The resource identifiers should be unique and URL friendly.

44) What are Rest API Important Concepts? 
Answer: 
a) HATEOAS. 
b) Statelessness: Scalability and High Availability. 
c) Cache-ability: Performance.
d) Named Resource: Simple Resource, Collection Resource.

45) What are Replication and Sharding? 
Answer: Replication and sharding are techniques used in DB systems, allow scaling and increase of performance. 
Replication, creating copy of data spread across nodes to assure availability and failure protection.
Copy in multiple node, each one are identical, BANKS, HOTELS, TELECOMMUNICATION. Sharding split big DB to smaller (shards) 
stored in different part of network. Each node handles only subset of data. This allows to parallel data processing and 
faster reclaim data from big databases. Big performance and scalability, merchant systems, analysis of data.

46) Ways to replaying events in Kafka? 
Answer: Create a new consumer group. It will read the events from start by default.

47) Remote Procedure Call Technologies? 
Answer: gRPC, Apache Thrift, Java Remote Method Invocation (RMI).

48) What is Redis? 
Answer: An in-memory key-value store. Does offer some persistent storage options but is typically used as a really fast 
the best effort caching solutions. Redis is also used to implement rate limiting.

49) What is Reactive Programming? 
Answer: 
a) Responsive: System responds as fast as possible. 
b) Resilient: System remains available, even if parts fail.
c) Elastic: System can deal with different level of load, for instance by using additional resources. 
d) Asynchronous communication: System uses asynchronous communication (message-driven).

50) What is a Quorum in Kafka? 
Answer: Set the minimum number of brokers to create a network, prevent split brain.

51) Describe Quality attribute Response Time?
Answer: Time between a client sending a request and receiving a response. ResponseTime = Processing Time + Waiting Time.

52) Describe Push model in Postgres?
Answer: Push model where Postgres notifies the other apps for each change.

53)  Difference between Processes and Threads? 
Answer: Process independent execution unit, contains own address space, file descriptor, memory space, virtual devices.
Threads use system resources to which belongs. In process there are multiple threads, thread can have one process.
Processes require more time and system resources to start and communicate with other processes, because requires context 
switch. Threads are more performant, requires less resources.

54) Describe Processes in Operating System? 
Answer: Programs executed on computer. Contains own resources as address space, threads, memory. Contains unique PID.
Key data structure in the context of process: process table, file table, page table.

55) Principle of service discovery in microservice architecture. Example of tools?
Answer: Enables services to find and communicate with each other. Principles of Service Discovery: 
Registration, Lookup, Heartbeats. Significance: Scalability, Load Balancing, Dynamic Updates, Resilience.
Example of industry Tools: consul, etcd, zookeeper, kubernetes, netflix eureka, dns-based (netflix ribbon)

56) What is POSIX? 
Answer: Portable OS interface. Standard how app can use system functions. Example of system functions: 
'open()', 'read()', 'write()', 'fork()', 'exec()', 'pthread_create()'. Alternative Win32.

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
Answer: Superior when it comes to query speed. Perfect choice for caching. Handling real-time big data. Data is not 
structured.

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
a) Scalability: Millions of daily users. 
b) Performance: Less than 500ms response time 99%. 
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
Answer: Functional: Verifies that the software apps functions and features work accordingly to the specified 
requirements and specifications. Consists of, Unit Testing, Integration, System, User Acceptance Testing (UAT) "what". 
Non-Functional: Evaluates performance, security, usability and reliability. Consists of: Performance, Security, 
Usability, Reliability, "how".

158) Describe the typical phases of software testing lifecycle STLC? 
Answer: Requirements Analysis, Test Planning, Test Design, Test Environment Setup, Test Execution, Defect Reporting 
and Tracking, Defect Resolution and Retesting, Test Closure.

159) What is a Dependency Inversion? 
Answer: High level modules should not depend on low level modules. Both should depend on abstraction.

160) What is Debezium? 
Answer: Open source platform for change data capture. React every time your data changes without changing the 
application that modify the data. Kafka sink connector - 'debezium/connect' docker image. 
Support for Confluent Schema Registry. Source connectors for various database. Postgres source connector 
'io.debezium.connector.postgresql.PostgresConnector'.

161) DDD Strategic Design & Common Patterns? 
Answer: 
a) Customer / Supplier Pattern: Supplier is upstream and consumer is downstream. For example uses the model of the 
order process. However, payment defines requirements for the order Process. 
b) Conformist Pattern: Bounded context simply uses a domain model for another bounded context. 
c) The anti-corruption layer: Bounded context does not directly use the domain model of the other bounded context. 
d) The Separate Way Pattern: Bounded Contexts are not related at the software level although a relation would be conceivable. 
e) The Shared Kernel Pattern: Describes a common core that is shared by multiple bounded contexts, Microservices anti-pattern. 
f) The Open Host Service Pattern: Bounded context offers a generic interface with several services, Frequently found at 
public APIs on the internet. 
g) The publishing language model: Is a domain model accessible by all bounded contexts. For example standard format like 
EDIFAC for transaction between companies.

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
Answer: Command: Express the intent to change the application's state, for example CreateProductCommand, 
UpdateProductCommand. Query: Express the desire for information, for example FindProductQuery, GetUserQuery. Event: 
Represent a notification that something relevant has happened, for example ProductCreatedEvent, ProductUpdatedEvent.

166) Describe CQRS? 
Answer: Separate read and write operations. Better performance on read part using right technology for reading, 
and preventing conflicts with update commands. Scale each part separately. Leads to eventual consistency, 
as the read part is updated asynchronously. Once the write operation is persisted, an event is stored in event-store.
Events can be replayed multiple times based on requirements to create different type of query store.

167) Context switch system calls? 
Answer: 
a) 'shed.h': Header file containing definitions of structures and functions related to process and thread scheduling. 
b) 'clone()': A system call that creates a new thread or process. 
c) 'pthread_create()': C function, creates a new thread.
d) 'pthread_yield()': C function, allows a thread to voluntarily donate CPU time to another thread waiting for execution.
e) 'sem_wait()' and 'sem_post()': C function, synchronization by using semaphores. 
f) 'mutex_lock()' and 'mutex_unlock()': C function, synchronization by using mutexes.

168) Context Switch Algorithms? 
Answer: Round-Robin: Divides CPU time to each task in order with a fixed time cycle. Priority-Based: Processor time is 
allocated based on priorities. Shortest Job First: Allocates CPU to jobs based on their length. Guaranteed Scheduling: 
Ensures that each task receives a specific amount of CPU time.

169) Containerization and its significance in microservices deployments? 
Answer: Containerization is the process of packaging an application and its dependencies, including libraries and config 
files, into a single, lightweight unit called container. Significance in Microservices: Consistency, Isolation, 
Portability, Scalability.

170) What is a Container Orchestration? 
Answer: Automated management of containerized apps, including tasks like deployment, scaling, Load Balancing 
and service discovery. Significance of Microservice Management: Deployment, Scaling, Service Discovery, Load Balancing, 
Self-Healing, Rolling Updates. Examples: Kubernetes, Docker Swarm, Amazon ECS.

171) What is a Consistent Hashing? 
Answer: A type of hashing that minimizes the number of keys that must be remapped when the hash table changes. 
Used by Load Balancer to distribute traffic, it minimizes the number of requests that will be transferred to another 
server if the existing server goes down.

172) Concept of service mesh in microservice architecture? 
Answer: A service mesh is a network of proxies (sidecars) deployed alongside microservices to handle communication, 
monitoring, and security concerns. It acts as a control plane for managing traffic and enforcing policies.
Improves communication, Load Balancing, routing and retries. Enhance observability, centralized logging, 
metrics collection, and tracing. Strengthens security with features like authentication, authorization and encryption. 
Benefits of service mesh: Traffic management, Load Balancing, circuit braking, routing, Observability, Security.
Resilience: Circuit Braking, Retries. Policy Enforcement. Tools Example: Istio.

173) Concept of event-driven architecture in the context of microservices? 
Answer: Event-driven architecture relies on the production, detection, consumption, and reaction to events, which are 
discrete occurrences of state change within a system.
Loose coupling between services. Enables real-time responsiveness. Supports scalability. Fault tolerance.

174) Concept of distributed database? 
Answer: Distributed databases are databases that store data across multiple nodes or servers, often geographically 
distributed. Each node holds a portion of the data, and these nodes work together to provide data access and redundancy. 
Enable to access data while maintaining CAP. Supports horizontal scalability. Enforce fault tolerance. Benefits of 
Distributed Databases: Scalability, High Availability, Data replication, Reduced Latency, Data Consistency: 
Thorough quorum-based systems or eventual consistency model. 
Challenges: Complexity, Latency, Consistency Trade-offs" Strong consistency across all nodes at cost of increased latency 
or decreased availability.

175) Concept of containerization in the context of microservices architecture using Kubernetes? 
Answer: Containerization involves building an application and its dependencies into a single, portable container image. 
Containers run consistently across different environments, from dev to prod. Simplifies packaging and deployment.
Ensures consistency between dev, testing and prod environments. Isolates apps, preventing conflicts between dependencies.
Benefits: Portability, Resource Efficiency, Consistency, Isolation. Kubernetes is an open-source container orchestration 
platform that automates the deployment, scaling and management of containerized apps. It provides features like load 
balancing, scaling, self-healing, and rolling updates. Containerization. Kubernetes Deployment: Management desired state 
of an app, num of replicas, resource allocation, scaling policies. Scaling: Based on resource utilization. 
Load Balancing. Rolling Updates.

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
All dependency across point to business layer, making it independent and stable. Delay implementation details of 
dependencies. Easier to test the business logic by mocking the dependencies even before deciding the implementation 
details. Replace an adapter implementation easily without touching the business logic. Independent development and 
deployment of different parts.

179) Circuit Breakers in context of microservice architecture, how contribute to the resilience? 
Answer: Circuit breakers are a design pattern used in microservices to handle failures in remote services or 
dependencies. They monitor the health of a service and, when failures or timeouts exceed a threshold, temporarily "open" 
the circuit, preventing further requests to the failing service. Enhance resilience, by preventing cascading failures.
Reduce load of failing services. Provide a graceful degradation of functionality when a service is unavailable.
Tools Example: Hystrix, resilience4j.

180) Choreography-Based Saga compensation flow? 
Answer: Initial Flow: Create Order operation. Reserve Product operation. Compensating Transaction: Cancel product 
reservation. Reject order. Initial Flow: 1,2,3,4,5,...,n. Compensating Transactions: n,...,5,4,3,2,1.

181) Choreography Pattern? 
Answer: Scalability. Helps scale a complex flow of business operations. Event Driven Architecture using choreography. 
Communication is asynchronous. Example: SAGA.

182) What is a Change Data Capture? 
Answer: Use Push methods opposed to Pulling. Push database records into target source (Kafka) by reading from 
Transaction logs. Transaction Log: Data change records (Insert / Update / Delete) made be each transaction. In Postgres 
Transaction Log is called as Write Ahead Log (WAL). Provides near real-time data with very low overhead by listening the 
Transaction Logs and sending to target source (Kafka) immediately.

183) Challenges and solutions related to maintaining data consistency?
Answer: Challenges: Data Ownership, Distributed Transactions, Data Synchronization, Data Duplication.
Solutions: Event-Driven Architecture, Saga Pattern, Caching. API Design, Idempotent Operations, Data Versioning.
Consistency Level, Distributed Tracing and Monitoring, Compensating Actions, Testing and chaos engineering.

184) CDN Solutions & Cloud Technologies? 
Answer: Cloud Technology: Cloudflare, Fastly, Akamai, Amazon Cloud Front, Google Cloud Platform CDN, Microsoft Azure 
Content Delivery Network.

185) CDN Publishing Strategy? 
Answer: Pull Strategy: Lower maintenance from our part. Everything is taken care of by the CDN provider.
Push Strategy: If our content doesn't change frequently we can simply push it once to the CDN.

186) Can you explain challenges associated with data management in microservices architecture, strategy + pattern? 
Answer: Challenges: Data Consistency, Data Ownership, Data Integrity, Data Access Pattern, Data Volume.
Strategies: Database per service, Shared Database, Event Sourcing, CQRS: Separate the command (write) and query (read) 
responsibilities into different components. Command side updates data, and query side retrieves data. 
API Composition: Service retrieve data from multiple sources and compose it into a single response before sending it to 
the client. Distribute Cache Redis. Data Versioning and Compatibility: To manage changes to data structures while 
maintaining backward compatibility for existing services. Data Governance and Ownership.

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
White-box: When you need to delve deep into the code to identify defects, security vulnerabilities, or performance 
bottlenecks.

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
Answer: Algorithm used in OS to avoid deadlocks in multi-processors systems.
Assumptions: Process has to declare max number of resources which will need. All resources assigned to process at the 
beginning, but can be released and assigned to other processes. Maintains actual lists of available and used resources. 
If process want to acquire a resource, algorithm checks whether allocation won't lead to deadlock. Simulates assign 
resources and checks.

197) What is Avro? 
Answer: Compact, fast, binary data format that relies on schemas.

198) Availability how is it obtained? 
Answer: 
a) Load Balancing: distributing the load among multiple servers. 
b) Redundancy.
c) Data Replication: copying data between several servers. 
d) Monitoring and warnings. 
e) Automatic failover.
f) Disaster Recovery: A plan to restore your system after a disaster.

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
Security, Simplified Client Interface, Load Balancing, Caching, Analytics and Monitoring.
Challenges: Service Discovery, Latency, Complexity, Security Config, Scalability, Rate Limiting. Monitoring and Logging,
API Versioning. Example, netflix eureka.

204) API Gateway Solutions & Cloud Technologies? 
Answer: Netflix Zuul. Amazon API Gateway. Google Cloud Platform API Gateway. Microsoft Azure API Management.

205) API Definition Example? 
Answer: REST API over HTTP: 
a) Identifying Entities. 
b) Mapping Entities to URIs. 
c) Defining Resources Representation. 
d) Assigning HTTP Methods to operations on resources.

206) Apache Kafka Performance? 
Answer: 
a) Partitioning: Data separated into multiple partitions. 
b) Replication: Replicating partitions across multiple nodes, improving system reliability and availability. 
c) Scalability: Scalable vertically and horizontally. You can increase the number of nodes in the cluster to increase 
throughput and processing speed. Multiple consumers within one partition. 
d) High Throughput: Utilizes memory caching, high-performance communication protocols and data processing algorithms. 
Low network overhead (data compression). Flexibility (integration with other tools).

207) Apache Kafka data formats? 
Answer: Apache Kafka transmits data in the form of messages, which are usually serialized in one of several formats.
JSON: readable, easy to process, less efficient than more optimized formats, and takes up more space.
Avro: A data serialization format that is used in big data projects such as Apache Hadoop. 
The advantage of avro is that it is very efficient and has a small size compared to other data formats. 
The disadvantage is that it is more complicated and requires more code to support. 
Protobuf: Data serialization format offered by Google. 
MessagePack: Designed to be easy to read by humans and very efficient to process by programs.
In some cases like Kafka Streams, you can also use other data formats such as Java Object Serialization or Apache Trift.

208) Apache Kafka commit offset? 
Answer: Mechanism to confirm reading of messages from the partition by consumers. Offset number of the last read message.
Confirmation of this offset is sent to the broker and stored in the so-called consumer group.
It ensures that each consumer reads the message only once. Thanks to this, when a new consumer appears, Kafka knows what 
message has already been processed. Automatic and manual committing of offsets.

209) Apache Avro? 
Answer: The data serialization system allows data to be transferred between various programming languages and computer 
systems. Avro defines a data serialization format in the form of schemas that describe the structure of the data.
Schemas are independent of the programming language. Application: distributed systems, data warehouses, stream processing 
systems, Big Data. Apache Hadoop, Apache Kafka, Apache Spark. Advantages: Compact data format. Independence from programming language.
Availability of many tools: rich library in various programming languages. In short, Avro is a system that allows you to 
move data between different programming languages and platforms, which is especially useful in distributed systems and Big Data.

210) What is AES? 
Answer: Standard for Advanced Encryption Standard. AES is a widely used encryption standard that has three symmetric-key 
algorithms (AES-128, AES-192, and AES-256). Off note AES considered to be the "gold standard" in encryption and is even 
used by the U.S. National Security Agency to encrypt top secret information.

211) Advantages and challenges of using microservices architecture in the development of distributed systems?
Answer: Advantages of microservices: Scalability, Modularity, Flexibility, Rapid Development, Fault isolation.
Challenges: Complexity, Inter-Service communication, Data Consistency, Deployment and Monitoring, Testing.

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

219) Please select the features that an API Gateway typically provides?
Answer: Rate Limiting, Protocol Translation, Monitoring, Authorization.

220) What are the benefits of making an application stateless? 
Answer: 
a) Lower memory footprint of the application. 
b) We can easily scale our system horizontally by running a group of identical application instances behind a load balancer. 
c) Seamless integration and implementation with HTTP protocol.

221) What is the main purpose of a CDN? 
Answer: To speed up the delivery of static content to end-users

222) How does the CDN solve the latency problem when the user is far from our system's data center? 
Answer: The static content is stored on the CDN's edge servers, which are physically closer to the user or are more 
strategically located in terms of network.

223) How does a CDN improve the availability of our system? 
Answer: When our data centers are experiencing degraded performance or reduced network capacity, it becomes less noticeable 
for the users since most of the traffic is going to the CDN.

224) Our security experts were notified that our system is under a DDoS (Distributed Denial of Service) Attack. 
Which options will be an effective measure to reduce our vulnerability to it? 
Answer: 
a) Incorporate a CDN. 
b) Add rate-limiting to the API Gateway. 
Incorporating a CDN will help distribute the attack across a very large number of edge servers outside our organization. 
Additionally, adding rate-limiting will limit the number of traffic our services get at a given time period, eliminating 
the possibility of one of our services getting overloaded, which may lead to a cascade of failures.

225) What is a database transaction? 
Answer: A set of operations that, for an external observer, appears as a single operation that either commits or aborts.

226) What does Consistency in the context of an ACID transaction mean? 
Answer: A transaction that was already committed will be seen by all future queries/transactions. A transaction doesn't 
violate any constraints we set on our data.

227) Durability? 
Answer: Durability guarantees that once a transaction is complete, its final state will persist and remain inside the database.
Since this rating record is missing, Durability is the property that was violated by this specific database technology.

228) Statements regarding SQL and NoSQL databases? 
Answer: NoSQL databases typically use the same data structures as most programming languages, while SQL databases do not.
SQL databases are optimized for efficient storage. NoSQL databases are optimized for efficient queries. NoSQL databases 
are easier to scale horizontally than SQL.

229) Which database operations are typically more limited in NoSQL databases than in SQL databases? 
Answer: ACID Transactions and Join operations.

230) Example of use cases for a graph database? 
Answer: Storing the data about businesses, their locations within zip codes, cities, and countries, and what customers 
have visited those businesses.

231) What tradeoff do we make by introducing indexing to a database? 
Answer: We get faster read operations at the expense of additional space and slower insert operations.

232) What are the benefits of database replication? 
Answer: Prevention of a Single Point of Failure, Higher Availability, Potential higher query throughput.

233) What is NOT a step in capturing functional requirements? 
Answer: Deciding which programming language or technology is best for this situation.

234) An API is a contract between? 
Answer: The engineers who implement the system and the clients that use the system.

235) Examples of idempotent operations? 
Answer: 
a) Delete a user by ID from the database. 
b) Update the user's phone number to 902-3232-1111. 
c) Checking the account balance of a user by ID. 
d) Multiplying a number by 0.

236) Why are idempotent operations preferable over non-idempotent operations? 
Answer: Idempotent operations allow safe retries. In a distributed system with unreliable components communicating with 
each other over an unreliable network, idempotent operations allow us to safely retry them without worrying about adverse 
effects.

237) Examples of scenarios where we need to use API Pagination? 
Answer: 
a) An API endpoint that provides a social media feed for a user. 
b) An API endpoint that provides a list of products in an online store that matches a keyword. 
c) An API endpoint that provides all the financial transactions in a user's bank account

238) What is the correct statement when comparing between REST and RPC? 
Answer: RPC looks and feels more like a local method invocation than a REST API call.A Remote Procedure Call uses Location 
Transparency and abstracts away all the complexities of the marshalling/unmarshalling of data and the management of the 
network communication between the client and the server. Therefore, a typical RPC looks like a simple method call, no 
different from any other local method.

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
These patterns are often used in combination to address complex integration requirements in a structured and maintainable
way. Understanding and implementing these patterns can help ensure that systems are integrated efficiently and can handle 
the demands of modern, distributed applications.

256) Database Indexes?
Answer: A database index is a data structure that improves the performance of database operations by providing quick access
to specific rows in a table. Indices are used to optimize the performance of 'SELECT', 'UPDATE', and 'DELETE' statements.
There are several types of indexes that can be used in a database, including:
a) B-Tree index: This is the most common type of index used in relational databases. It is a balanced tree data structure 
that allows for fast searching, insertion, and deletion of data. B-Tree index can be used for both small and large tables, 
and it's effective for both exact-match and range queries.
b) Bitmap index: This type of index uses a bitmap to represent the presence or absence of a value in a specific column.
Bitmap indexes are useful for data warehousing and business intelligence (B-I) applications, where queries often involve 
multiple conditions.
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
Merkle-trees are used in a variety of applications, such as: 
a) Cryptocurrency: Merkle trees are used in blockchain technology to ensure the integrity of transactions and to prevent 
double-spending.
b) Distributed systems: Merkle trees are used in distributed systems to efficiently verify the integrity of data that is 
stored across multiple nodes. 
c) File systems: Merkle trees are used in file systems to ensure the integrity of large 
files and to enable efficient detection of changes to the file. 
d) Cloud storage: Merkle trees are used in cloud storage to ensure the integrity of data that is stored across multiple 
servers and to enable efficient detection of changes to the data.
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

260) Design a high-frequency trading (HFT) platform.
Answer: 
a) Understand the Requirements: 
High Performance: The system must execute trades with minimal latency.
Scalability: Handle a large number of trades and data streams.
Reliability: Ensure system uptime and fault tolerance.
Security: Protect sensitive financial data.
Compliance: Adhere to financial regulations and logging.
b) Key Components:
Market Data Ingestion, Trade Execution Engine, Order Management System (OMS), Risk Management, Monitoring and Alerting.
c) System Architecture:
Market Data Ingestion: Data Sources: Connect to various exchanges and data providers.
Low Latency Feed Handlers: Process and normalize incoming data streams.
Message Queue: Use a high-performance message queue (e.g., Kafka) to decouple data ingestion from processing.
Trade Execution Engine: Order Book: Maintain real-time order books for various securities.
Matching Engine: Match buy and sell orders with minimal latency.
Smart Order Routing: Route orders to the best available market considering factors like price, latency, and volume.
Order Management System (OMS): Order Lifecycle Management: Track the state of each order from creation to completion.
Persistence: Store order data in a high-performance database (e.g., in-memory databases like Redis for speed, 
with persistent backups).
d) Risk Management:
Pre-Trade Risk Checks: Validate trades against risk parameters before execution.
Real-Time Monitoring: Continuously monitor positions and exposure.
Post-Trade Analysis: Analyze completed trades for compliance and risk evaluation.
e) Monitoring and Alerting:
Real-Time Monitoring: Use monitoring tools (e.g., Prometheus, Grafana) to track system performance and health.
Alerting: Set up alerts for anomalies or failures (e.g., using tools like PagerDuty). 
Detailed Design:
One Market Data Ingestion: Low Latency Feed Handlers: Use direct connections and co-located servers to minimize latency.
Implement efficient parsers to handle different data formats.
Message Queue: Kafka configured for low latency with appropriate partitioning and replication.
In-memory processing for immediate actions, with disk-based storage for historical data.
Trade Execution Engine: Order Book: Use data structures optimized for fast insertions and lookups, such as red-black 
trees or skip lists. Maintain separate order books for different asset classes.
Matching Engine:
Implement efficient algorithms to match orders (e.g., continuous double auction). Ensure atomicity and consistency of transactions.
Order Management System (OMS): Order Lifecycle Management: Use state machines to manage the lifecycle of each order.
Implement audit trails for compliance.
Persistence: Primary in-memory database (Redis) for speed. Secondary persistent storage (SQL/NoSQL database) for durability.
Risk Management: Pre-Trade Risk Checks: Implement checks for order size, credit limits, and regulatory compliance.
Use a rules engine for dynamic risk evaluation.
Real-Time Monitoring: Continuously update and monitor risk metrics. Implement circuit breakers to halt trading on anomalies.
Monitoring and Alerting: Real-Time Monitoring: Collect metrics from all components (CPU, memory, latency, order rates).
Visualize metrics using Grafana dashboards.
Alerting: Define thresholds and rules for alerts. Integrate with incident management tools for prompt response.
Scalability and Reliability Considerations: Load Balancing: Distribute incoming market data and order processing across 
multiple servers. Horizontal Scaling: Add more instances of feed handlers, execution engines, and databases to handle 
increased load.
Fault Tolerance: Implement redundancy at each layer to ensure high availability (example, multiple data centers).
Data Consistency: Use distributed transactions or eventual consistency models as appropriate.
Disaster Recovery: Regularly backup data and have a failover strategy. 
Security Considerations:
a) Encryption: Encrypt sensitive data both at rest and in transit.
b) Access Control: Implement fine-grained access controls and authentication mechanisms.
c) Regular Audits: Conduct regular security audits and penetration testing.
Regulatory Compliance:
a) Logging and Auditing: Maintain detailed logs of all trading activities for audit purposes.
b) Data Retention: Adhere to regulatory requirements for data retention periods.
c) Reporting: Implement automated reporting mechanisms to regulatory bodies.

261) Design a real-time fraud detection system for credit card transactions.
Answer:
Understand the Requirements:
a) Real-Time Processing: Detect fraud as transactions occur.
b) Scalability: Handle a large volume of transactions.
c) Accuracy: Minimize false positives and false negatives.
d) Latency: Ensure minimal impact on transaction processing time.
e) Flexibility: Update fraud detection models and rules easily.
Key Components: Transaction Ingestion, Feature Extraction, Fraud Detection Engine, Alerting and Reporting,
Model Training and Updating, Monitoring and Logging.
System Architecture:
a) Transaction Ingestion: Data Sources: Receive transactions from multiple sources (example, POS terminals, online transactions).
Stream Processing Framework: Use Apache Kafka or AWS Kinesis for handling incoming transaction streams.
b) Feature Extraction: Data Enrichment: Enhance transaction data with additional information (example, user profile, 
historical transactions). Real-Time Feature Engineering: Compute features relevant to fraud detection (e.g., transaction 
amount, frequency, location).
c) Fraud Detection Engine: Rules-Based Engine: Implement initial rules for simple fraud checks (example, transaction amount 
thresholds). Machine Learning Models: Use pre-trained models to identify complex fraud patterns. Decision-Making: Combine 
results from rules engine and ML models to make a final fraud decision.
d) Alerting and Reporting: Real-Time Alerts: Notify relevant parties (example, fraud analysts, customers) of suspicious 
transactions. Reporting Dashboard: Provide a dashboard for monitoring fraud detection metrics and trends.
e) Model Training and Updating: Data Collection: Collect labeled data (fraud/non-fraud) for model training.
Model Training Pipeline: Use frameworks like Apache Spark for training models on large datasets.
Continuous Improvement: Regularly update models with new data and feedback.
d) Monitoring and Logging: System Health Monitoring: Track system performance and health metrics. Transaction Logging: 
Log details of each transaction and fraud decision for auditing purposes.
Detailed Design:
a) Transaction Ingestion: Stream Processing: Use Kafka for handling high-throughput transaction streams.
Partition transactions by user or region for parallel processing.
b) Feature Extraction: Data Enrichment: Integrate with user profile databases and historical transaction databases.
Use Redis for fast lookups of user and transaction history.  
Feature Engineering: Implement feature extraction in a stream processing framework (example, Apache Flink, Spark Streaming).
Example features: average transaction amount, transaction frequency, geographical distance between transactions.
c) Fraud Detection Engine: Rules-Based Engine: Implement simple rules in a rule engine (example, Drools).
Examples: transactions over a certain amount, transactions from high-risk countries.
Machine Learning Models: Train models using historical transaction data. Use algorithms suitable for real-time scoring 
(example, logistic regression, random forests). Deploy models using a scalable serving framework (example, TensorFlow Serving).
Decision-Making: Use a scoring system to combine rule-based and model-based results. Apply thresholds to classify 
transactions as fraudulent or non-fraudulent.
d) Alerting and Reporting: Real-Time Alerts: Integrate with messaging systems (example, email, SMS) for alerts.
Use a notification service (example, AWS SNS) to send alerts. Reporting Dashboard: Build dashboards using visualization 
tools (example, Grafana, Kibana). Display metrics like number of fraudulent transactions, false positives/negatives.
e) Model Training and Updating: Data Collection: Collect labeled data from transaction logs and feedback from fraud analysts.
Model Training Pipeline: Use batch processing frameworks (example, Apache Spark) for model training. Automate feature 
engineering and model evaluation processes. Continuous Improvement: Implement A/B testing to evaluate new models.
Continuously integrate feedback and update models.
f) Monitoring and Logging: System Health Monitoring: Use monitoring tools (example, Prometheus, Grafana) to track metrics 
like latency, throughput. Set up alerts for system anomalies. Transaction Logging: Log detailed information about each 
transaction and fraud decision. Store logs in a scalable storage solution (example, Elasticsearch, AWS S3).
Scalability and Reliability Considerations:
a) Horizontal Scaling: Add more instances of stream processors and ML model servers to handle increased load.
b) Fault Tolerance: Use Kafka's replication and failover features to ensure message durability.
c) Data Consistency: Ensure consistency between transaction data, user profiles, and historical data.
d) Disaster Recovery: Implement regular backups and failover strategies.
Security Considerations: 
a) Encryption: Encrypt transaction data both at rest and in transit.
b) Access Control: Implement role-based access control for sensitive data and system components.
c) Regular Audits: Conduct security audits and penetration testing.
Regulatory Compliance:
a) Logging and Auditing: Maintain detailed logs of all transactions and fraud detection decisions.
b) Data Retention: Comply with data retention policies and regulations.
c) Reporting: Implement automated reporting for regulatory compliance.

262) Design a peer-to-peer (P2P) lending platform.
Answer:
Understand the Requirements:
a) User Management: Handle borrower and lender accounts.
b) Loan Management: Manage loan applications, approvals, and repayments.
c) Transaction Processing: Handle financial transactions securely.
d) Matching Engine: Match borrowers with lenders based on criteria.
e) Scalability: Support a growing number of users and transactions.
f) Security: Ensure data and transaction security.
g) Compliance: Adhere to financial regulations.
Key Components: User Management System, Loan Management System, Transaction Processing System, Matching Engine,
Notification System, Analytics and Reporting, Monitoring and Logging.
System Architecture:
a) User Management System: User Profiles: Store personal and financial information. Authentication and Authorization: 
Secure login and role-based access. KYC (Know Your Customer): Verify user identity for compliance.
b) Loan Management System: Loan Application: Capture loan details and borrower information.  Credit Scoring: Assess 
borrower creditworthiness. Loan Approval: Process loan applications and approvals. Repayment Schedule: Manage loan 
repayment plans.
c) Transaction Processing System: Payment Gateway Integration: Handle deposits and withdrawals. Escrow Management: Secure 
funds during loan processing. Ledger: Maintain transaction records.
d) Matching Engine: Matching Criteria: Match borrowers and lenders based on amount, interest rate, and risk profile.
Automated Matching: Automatically connect borrowers and lenders.
e) Notification System: Real-Time Notifications: Notify users of important events (example, loan approval, repayment due).
Communication Channels: Support email, SMS, and in-app notifications.
f) Analytics and Reporting: User Analytics: Track user behavior and platform usage. Financial Reports: Generate reports 
on loans, repayments, and defaults. Compliance Reporting: Ensure adherence to regulatory requirements.
g) Monitoring and Logging: System Health Monitoring: Track performance and uptime. Transaction Logging: Maintain detailed 
logs for auditing and compliance.
Detailed Design:
a) User Management System: User Profiles: Use a relational database (example, PostgreSQL) to store user data.
Ensure data encryption at rest and in transit. Authentication and Authorization: Implement OAuth 2.0 for secure 
authentication. Use JWT (JSON Web Tokens) for session management. KYC: Integrate with third-party KYC providers 
(example, Jumio, Onfido) for identity verification.
b) Loan Management System: Loan Application: Design a form to capture necessary loan details. Validate input data and 
store it in a database. Credit Scoring: Integrate with credit bureaus (example, Experian, TransUnion) to fetch credit scores.
Implement custom algorithms for assessing credit risk. Loan Approval: Define rules and workflows for loan approval.
Store loan statuses and approval decisions. Repayment Schedule: Generate repayment schedules based on loan terms.
Send reminders to borrowers for upcoming payments.
c) Transaction Processing System: Payment Gateway Integration: Use APIs from payment gateways (example, Stripe, PayPal) for 
handling transactions. Securely manage payment credentials and transaction data. Escrow Management:
Implement an escrow system to hold funds until loan disbursement. Release funds based on predefined conditions.
Ledger: Maintain an immutable ledger of all transactions. Use blockchain technology for added security and transparency 
if required.
d) Matching Engine: Matching Criteria: Define criteria such as loan amount, interest rate, and risk profile.
Store borrower and lender preferences. Automated Matching: Implement algorithms to match borrowers with lenders.
Optimize for minimal matching time and user satisfaction.
e) Notification System: Real-Time Notifications: Use a message queue (example, RabbitMQ, Kafka) to handle notification events.
Send notifications through email, SMS, or in-app messages. Communication Channels: Integrate with third-party services 
for email (example, SendGrid) and SMS (example, Twilio).
f) Analytics and Reporting: User Analytics: Collect and analyze user activity data. Use tools like Google Analytics or 
custom-built solutions. Financial Reports: Generate regular reports on loan performance, repayments, and defaults.
Use business intelligence tools (example, Tableau, Power BI). Compliance Reporting: Implement automated reporting for 
regulatory compliance. Ensure data is easily retrievable for audits.
g) Monitoring and Logging: System Health Monitoring: Use monitoring tools (example, Prometheus, Grafana) to track system 
metrics. Set up alerts for system anomalies. Transaction Logging: Log all transactions in a secure, immutable storage.
Use logging frameworks (example, ELK stack) for efficient log management.
Scalability and Reliability Considerations: Horizontal Scaling: Deploy microservices architecture to scale 
individual components independently. Load Balancing: Use load balancers (example, AWS ELB) to distribute traffic evenly.
Fault Tolerance: Implement redundancy and failover mechanisms. 
Data Consistency: Use distributed databases and ensure eventual consistency. 
Disaster Recovery: Regularly backup data and have a recovery plan.
Security Considerations: Encryption: Use TLS for data in transit and AES for data at rest.
Access Control: Implement role-based access control (RBAC). 
Regular Audits: Conduct security audits and vulnerability assessments.
Regulatory Compliance: Data Privacy: Ensure compliance with data protection laws (e.g., GDPR, CCPA).
Reporting: Automate reporting to regulatory bodies. 
Audit Trails: Maintain detailed logs for all financial transactions and user activities.

263) How to achieve data consistency in distributed systems?
Answer: Achieving data consistency in a distributed system is a complex challenge due to the nature of distributed 
computing environments. The key is to ensure that all nodes in the system see the same data at the same time, or at 
least in a way that meets the application's requirements for consistency. Here are several strategies and techniques to 
achieve data consistency in distributed systems:
a) Consistency Models: Different consistency models offer various guarantees about the visibility and ordering of updates:
Strong Consistency: Guarantees that once write is completed, all subsequent reads will reflect that write. Implementing 
strong consistency often involves techniques like:
Two-Phase Commit (2PC): Ensures all participating nodes agree on a transaction's commit or abort.
Paxos/Raft: Consensus algorithms used for ensuring agreement among a distributed set of nodes.
Distributed Transactions: Coordinating transactions across multiple nodes, often using 2PC.
Eventual Consistency: Guarantees that if no new updates are made, eventually all nodes will converge to the same value. 
Suitable for systems where absolute immediate consistency is not required.
Gossip Protocols: Nodes communicate with each other randomly to propagate data changes.
Anti-Entropy Protocols: Nodes reconcile differences to converge towards consistency.
Conflict Resolution Strategies: Use techniques like last write wins (LWW), vector clocks, or custom conflict resolution logic.
Causal Consistency: Guarantees that operations that are causally related are seen by all nodes in the same order.
Version Vectors: Track the causal relationships between updates.
Logical Clocks: Use timestamps or vector clocks to capture causal relationships.
Read/Write Quorums: Using quorum-based approaches, a read or write operation must obtain permission from a majority of nodes.
Quorum Reads/Writes: Require reads to contact a majority of replicas and writes to contact a different majority.
Tunable Consistency: Allowing clients to specify the level of consistency required for a particular operation (example in Cassandra).
b)Techniques for Data Consistency.
Replication Protocols: Ensure that all replicas of a piece of data are consistent with each other.
Synchronous Replication: Writes are replicated to all nodes before acknowledging write to the client.
Asynchronous Replication: Writes are acknowledged immediately, and replication occurs in the background.
Conflict Detection and Resolution: In eventual consistency models, conflicts may arise and need to be resolved.
Last Write Wins (LWW): The most recent write is considered authoritative.
Custom Conflict Resolution Logic: Application-specific rules to resolve conflicts.
Operational Transformation (OT) or CRDTs: Structures that allow concurrent updates without conflicts (example
Conflict-Free Replicated Data Types).
Atomicity and Isolation:
Distributed Locking: Ensure that only one node can modify a piece of data at a time (example, using ZooKeeper).
Optimistic Concurrency Control: Allow concurrent transactions but check for conflicts before committing.
Pessimistic Concurrency Control: Lock data before transaction processing to prevent conflicts.
c) CAP Theorem:
The CAP theorem states that a distributed system can provide only two of the following three guarantees at the same time:
Consistency: Every read receives the most recent write.
Availability: Every request receives a response, without guarantee that it contains the most recent write.
Partition Tolerance: The system continues to operate despite an arbitrary number of messages being dropped or delayed 
by the network.
In practice, systems must balance these trade-offs based on their specific requirements. For instance:
CP Systems: Prioritize consistency and partition tolerance (example, HBase).
AP Systems: Prioritize availability and partition tolerance (example, Cassandra).
d) Practical Examples:
Apache Kafka: Uses replication and configurable consistency settings to balance consistency and availability.
Apache Cassandra: Uses tunable consistency levels, allowing users to choose between consistency and availability.
Google Spanner: Uses TrueTime API to provide strong consistency across distributed nodes.
e) Design Patterns:
Event Sourcing: Record state changes as a sequence of events, ensuring that all events are processed in order.
Command Query Responsibility Segregation (CQRS): Separate the operations that read data from the operations that update 
data to handle consistency in a more scalable way.

264) Why do we need pub/sub in distributed systems?
Answer: Publish-subscribe (pub-sub) is a messaging pattern used in distributed systems that provides several benefits 
and solves key challenges. Here's why pub-sub is needed in distributed systems:
a) Decoupling of Components: Loose Coupling: Pub-sub decouples the components in a system, allowing publishers and 
subscribers to operate independently. Publishers don't need to know who the subscribers are, and subscribers don't need 
to know where the data comes from. Scalability: This decoupling allows the system to scale more easily, as components 
can be added or removed without significant reconfiguration.
b) Asynchronous Communication: Non-blocking Operations: Pub-sub enables asynchronous communication, meaning that 
publishers can send messages without waiting for subscribers to process them. This leads to more efficient use of 
resources and better performance. Event-driven Architecture: It supports event-driven architecture where systems react 
to events in real-time, which is crucial for responsive and interactive applications.
c) Multicast Capability: Broadcasting Messages: Pub-sub allows messages to be broadcast to multiple subscribers 
simultaneously, making it efficient for scenarios where the same message needs to reach multiple components.
Efficient Data Distribution: This reduces the need for multiple point-to-point communications, conserving bandwidth and 
reducing latency.
d) Reliability and Fault Tolerance: Message Persistence: Many pub-sub systems support message persistence, ensuring that 
messages are not lost if a subscriber is temporarily unavailable. Redundancy and Replication: Pub-sub systems often have 
built-in redundancy and replication mechanisms to ensure high availability and reliability.
e) Simplified Communication: Simpler Codebase: By abstracting the communication layer, pub-sub simplifies the codebase 
of distributed systems, making it easier to develop, maintain, and extend. Standardized Communication: It provides a 
standardized way for components to communicate, which can simplify integration and interoperability.
f) Dynamic and Flexible Systems: Dynamic Discovery: Subscribers can dynamically subscribe to topics of interest, and 
publishers can dynamically publish new types of events without affecting existing subscribers. Flexibility: This 
flexibility allows the system to adapt to changing requirements and to integrate new services or components seamlessly.
g) Improved Load Management: Load Distribution: Pub-sub systems can help distribute the load across multiple subscribers, 
balancing the processing workload and improving the overall performance of the system. Throttling and Back-pressure: 
They often include mechanisms for handling back-pressure and throttling, which help in managing load spikes and 
preventing system overload.
Examples in Real-World Systems: Event Notification Systems: Used in financial services for real-time stock price updates 
or in social media for notifications. Logging and Monitoring: Collecting logs and metrics from distributed components 
and sending them to a central monitoring system. Microservices Communication: In microservices architecture, pub-sub 
facilitates communication between services without tight coupling, promoting better service isolation and resilience.

265) Databases Normalization and Denormalization?
Answer: Normalization: Start by normalizing your database to eliminate redundancy and ensure data integrity. This process 
involves organizing data into tables to reduce data redundancy and improve data integrity.
First Normal Form (1NF): Ensure each table column contains atomic, indivisible values.
Second Normal Form (2NF): Eliminate partial dependencies; each non-key column must depend on the entire primary key.
Third Normal Form (3NF): Eliminate transitive dependencies; non-key columns should not depend on other non-key columns.
Denormalization: In some cases, to optimize read performance, denormalization might be necessary. This involves combining 
tables to reduce the number of joins, at the cost of increased redundancy and potential data anomalies.

266) Databases Choosing the Right Database?
Answer: Relational versus NoSQL: Choose the type of database that best fits your use case.
Relational Databases (RDBMS): Use for structured data with relationships, requiring ACID compliance (example, MySQL, PostgreSQL).
NoSQL Databases: Use for unstructured or semi-structured data, scalability, and flexibility (example, MongoDB, Cassandra, Redis).

267) Databases Schema Design?
Answer: Data Modeling: Create an Entity-Relationship Diagram (E-R-D) to visualize entities, attributes, and relationships.
Primary Keys: Ensure every table has a primary key that uniquely identifies each record.
Foreign Keys: Use foreign keys to maintain referential integrity between tables.

268) Databases Indexing?
Answer: Indexes: Use indexes to improve query performance. Create indexes on columns frequently used in 'WHERE' clauses, 
'JOIN' operations, and 'ORDER BY' clauses. Composite Indexes: Sometimes, a combination of columns might be needed for 
indexing to cover specific queries. Avoid Over-Indexing: Too many indexes can slow down write operations ('INSERT', 
'UPDATE', 'DELETE').

269) Databases Data Integrity?
Answer: Constraints: Use constraints (example 'NOT NULL', 'UNIQUE', 'CHECK') to enforce rules at the database level.
Transactions: Use transactions to ensure a series of operations either completely succeed or fail (ACID properties: 
Atomicity, Consistency, Isolation, Durability).

270) Databases Scalability?
Answer: Vertical Scaling: Increase the capacity of a single server (example, adding more CPU, RAM).
Horizontal Scaling: Distribute the load across multiple servers (example, sharding in NoSQL databases, database clustering).

271) Databases Backup and Recovery?
Answer: Regular Backups: Schedule regular backups to prevent data loss. Disaster Recovery Plan: Have a plan for 
recovering data in case of a failure.

272) Databases Security?
Answer: Authentication and Authorization: Ensure proper user authentication and authorization to control access.
Encryption: Encrypt sensitive data both at rest and in transit.
SQL Injection: Protect against SQL injection attacks by using prepared statements and parameterized queries.

273) Databases Performance Optimization?
Answer: Query Optimization: Write efficient queries and use query optimization tools provided by the database.
Load Balancing: Distribute database requests to balance the load and improve performance.
Caching: Implement caching strategies to reduce database load (example, using Redis or Memcached).

274) Databases Monitoring and Maintenance?
Answer: Monitoring Tools: Use monitoring tools to track database performance and health (example, slow query logs, 
performance dashboards). Regular Maintenance: Perform regular maintenance tasks such as updating statistics, reindexing, 
and purging old data.

275) Databases Monitoring and Maintenance?
Answer: Monitoring Tools: Use monitoring tools to track database performance and health (example, slow query logs, 
performance dashboards). Regular Maintenance: Perform regular maintenance tasks such as updating statistics, reindexing, 
and purging old data. 

276) Databases Compliance?
Answer: Regulatory Requirements: Ensure your database design and data handling practices comply with relevant regulations 
and standards (example, GDPR, HIPAA).

277) Can you explain concept of oauth?
Answer: OAuth (Open Authorization) is an open standard for access delegation, commonly used as a way to grant websites 
or applications limited access to a user's information without exposing the user's password. OAuth allows third-party 
services to exchange access tokens for accessing resources on behalf of a user.
a) Key Concepts of OAuth.
Resource Owner: The user who authorizes an application to access their account.
Client: The application requesting access to the user's resources.
Authorization Server: The server that issues access tokens to the client after successfully authenticating the resource 
owner and obtaining their authorization.
Resource Server: The server that hosts the protected resources, capable of accepting and responding to protected resource 
requests using access tokens.
Access Token: A token that the client uses to access the resource server on behalf of the resource owner.
Refresh Token: A token used to obtain a new access token without re-authenticating the resource owner.
b) OAuth Flow.
The OAuth flow involves several steps to ensure secure authorization:
Authorization Request: The client requests authorization from the resource owner. This is typically done via an 
authorization server, where the resource owner logs in and grants permission to the client.
Authorization Grant: The resource owner grants authorization to the client. This is usually in the form of an 
authorization code.
Authorization Code Exchange: The client exchanges the authorization code for an access token at the authorization server.
Access Token Request: The client requests an access token from the authorization server by presenting the authorization 
code and its credentials.
Access Token Response: The authorization server returns the access token (and optionally a refresh token) to the client.
Access Resource: The client uses the access token to request the protected resource from the resource server.
c) Types of OAuth Flows.
Authorization Code Grant: Used for server-side applications where the client secret can be securely stored. The client 
exchanges an authorization code for an access token.
Steps: The client directs the user to the authorization server. The user logs in and consents. The authorization server 
redirects the user back to the client with an authorization code. The client exchanges the code for an access token.
Implicit Grant: Used for client-side applications (example, single-page apps) where the client secret cannot be securely 
stored. The access token is returned directly to the client without an authorization code.
Steps: The client directs the user to the authorization server. The user logs in and consents.
The authorization server redirects the user back to the client with an access token in the URL fragment.
Resource Owner Password Credentials Grant: Used when the resource owner trusts the client with their credentials. 
The client directly obtains the user's credentials and exchanges them for an access token.
Steps: The client collects the user's credentials. The client sends the credentials to the authorization server.
The authorization server validates the credentials and returns an access token.
Client Credentials Grant: Used for machine-to-machine communication where no user is involved. The client authenticates 
itself directly with the authorization server and obtains an access token.
Steps: The client authenticates with the authorization server. The authorization server returns an access token.
d) Benefits of OAuth.
Security: Users don't need to share their passwords with third-party applications. Access tokens can be scoped to 
specific resources and have limited lifetimes.
Decentralization: OAuth supports a decentralized approach where the authorization server can be separate from the 
resource server.
Scalability: OAuth can handle complex authorization scenarios and can scale to large numbers of users and applications.
e) Common Use Cases.
Single Sign-On (SSO): Allowing users to log in to multiple applications with one set of credentials.
Third-Party API Access: Enabling applications to access APIs on behalf of the user (e.g., integrating with Google or 
Facebook APIs).
Delegated Access: Allowing applications to perform actions on behalf of the user (e.g., a calendar app accessing your 
calendar data).

278) Cache?
Answer: A cache is a small and fast memory storage that is used to temporarily store frequently accessed data.
The main purpose of a cache is to reduce the number of times that a slower memory or storage device
(such as a hard drive or a remote server) needs to be accessed.
By keeping a copy of frequently accessed data in a cache, the system can quickly retrieve the data from the cache
instead of having to access the slower memory or storage device.
Caching is a common technique used in system design to improve the performance of a system.
Caches can be implemented at various levels of a system, such as at the hardware level (e.g. CPU cache),
the operating system level (e.g. file system cache), and the application level (e.g. web application cache).
In system design, it is important to consider the size of the cache and the replacement policy used to decide which
data should be evicted from the cache when it becomes full.
Some common replacement policies include: Least Recently Used (LRU), Most Recently Used (MRU), Least Frequently Used (LFU).
Caching can also include distributed caching systems, where a group of caching servers work together to cache data and
distribute the load.
This can be beneficial in a distributed system where data needs to be quickly accessible from multiple locations.
Overall, the use of cache in system design can greatly improve the performance of a system by reducing the number of
times that slow memory or storage devices need to be accessed.

279) Distributed Hash Table?
Answer: A Distributed Hash Table (DHT) is a type of distributed system that provides a lookup service similar to a hash table.
It allows a group of nodes to efficiently and fault-tolerantly store and retrieve key-value pairs.
In a DHT, each key is associated with a unique value, and keys are distributed among the nodes in the network in a way
that allows for efficient lookups.
When a key is added to the DHT, it is assigned to a specific node, called a "successor," which is responsible for
Storing the key-value pair.
DHTs are particularly useful in distributed systems, such as peer-to-peer networks, where the nodes may join or leave
the network dynamically.
They provide a way for nodes to efficiently find the nodes responsible for storing a particular key without the need
for a centralized directory or lookup service.
Some popular DHTs are Chord, Pastry, and Kademlia.
They are used in various P2P systems like BitTorrent, BitSwap, and IPFS.
DHTs are also used in distributed databases, Cloud storage, Content delivery networks, and other distributed systems
where data needs to be accessible and retrievable from multiple locations.

280) Routers?
Answer: A router is a networking device that forwards data packets between computer networks.
It is connected to at least two networks, commonly two LANs (Local Area Networks) or WANs (Wide Area Networks),
and decides which route to send information packets based on its current understanding of the state of the networks
it is connected to.
Routers use routing tables to determine the best path for forwarding the packets, and can also perform other functions
such as network address translation (NAT) and VPN support.
Routers use protocols such as the Border Gateway Protocol (BGP) and Open Shortest Path First (OSPF) to communicate
with other routers and build a map of the network.
Routers use various algorithms to determine the best path for forwarding packets.
Some commonly used algorithms include:
a) Distance-vector algorithms: These algorithms use metrics such as hop count to determine the best path.
Examples include Routing Information Protocol (RIP) and Interior Gateway Routing Protocol (IGRP).
b) Link-state algorithms: These algorithms maintain a complete map of the network, including the state of all links,
and use this information to determine the best path. Examples include Open Shortest Path First (OSPF) and 
Intermediate System to Intermediate System (IS-IS).
c) Hybrid algorithms: These algorithms combine elements of both distance-vector and link-state algorithms.
Examples include Enhanced Interior Gateway Routing Protocol (EIGRP).
d) Path-vector algorithms: These algorithms are based on Border Gateway Protocol (BGP) and use the path information to 
determine the best path. Bellman-Ford Algorithm: It is a distance-vector algorithm which is used to find the shortest 
path from a single source node to all other nodes in a graph.
Dijkstra Algorithm: It is also a distance-vector algorithm but it is used for finding the shortest path from a single 
source node to all other nodes in a weighted graph where the edges have non-negative weights.
These are some of the algorithms that are used in routers to find paths, and the choice of algorithm depends on the
specific requirements of the network and the routing protocol being used.

281) Java Compiler?
Answer: The Java compiler is a program that translates Java source code into Java bytecode, which is a platform-independent
representation of the source code.
The Java bytecode can then be executed by the Java Virtual Machine (JVM) on any platform.
The basic steps of how the Java compiler works are:
a) Lexical analysis: The Java source code is scanned, and the compiler divides it into a sequence of tokens.
Tokens are the basic building blocks of the source code, such as keywords, operators, and identifiers.
b) Syntactic analysis: The compiler builds a tree-like structure called a parse tree from the sequence of tokens.
This parse tree represents the syntactic structure of the source code, and is used to check for grammatical
errors in the code.
c) Semantic analysis: The compiler performs semantic analysis on the parse tree, checking for semantic errors such as 
type mismatches and undefined variables.
d) Intermediate code generation: The compiler generates Java bytecode, which is a platform-independent representation of 
the source code.
e) Code optimization: The compiler performs various optimizations on the bytecode, such as removing dead code, 
constant folding, and inlining functions. 
f) Class file generation: The compiler generates a class file, which is a file that contains the bytecode and metadata 
required by the JVM to execute the code.
g) Linking: The compiler links the generated class file with any required libraries and creates an executable file.
Java compiler is a multi-pass compiler, which goes through the input source code multiple times, and at each pass,
it performs a specific task such as lexical analysis, semantic analysis, and bytecode generation.

282) Compilers?
Answer: A compiler is a program that translates source code written in a high-level programming language into machine code
that can be executed by a computer. The process of translation is known as compilation.
The basic steps of how a compiler works are:
a) Lexical analysis: The source code is scanned, and the compiler divides it into a sequence of tokens.
Tokens are the basic building blocks of the source code, such as keywords, operators, and identifiers.
b) Syntactic analysis: The compiler builds a tree-like structure called a parse tree from the sequence of tokens.
This parse tree represents the syntactic structure of the source code, and is used to check for grammatical errors in the code.
c) Semantic analysis: The compiler performs semantic analysis on the parse tree, checking for semantic errors such as 
type mismatches and undefined variables.
d) Intermediate code generation: The compiler generates an intermediate code representation of the source code, such as 
assembly language or bytecode.
e) Code optimization: The compiler performs various optimizations on the intermediate code, such as removing dead code,
constant folding, and inlining functions.
f) Code generation: The compiler generates machine code from the optimized intermediate code.
g) Linking: The compiler links the generated machine code with any required libraries and creates an executable file.
Some compilers are single pass compiler, other are multi-pass, the multi-pass compiler goes through the input source
code multiple times, and at each pass, it performs a specific task such as lexical analysis, semantic analysis, and code
generation.

283) MapReduce?
Answer: MapReduce is a programming model and an associated implementation for processing and generating large data sets.
It was developed by Google and is a core component of the Apache Hadoop project.
The key concept of MapReduce is to split a large data set into smaller chunks, which are then processed in parallel
by multiple machines.
The "Map" step applies a user-specified function to each data chunk to transform it into a set of intermediate
key-value pairs.
The "Reduce" step takes these intermediate key-value pairs and combines them to produce a smaller set of final
key-value pairs.
MapReduce is widely used in industry for big data processing, including tasks such as data mining, machine learning,
and log processing.
It is also used for processing large data sets in fields such as finance, e-commerce, and social media.
The distributed computing nature of MapReduce makes it a popular choice for processing large data sets that cannot be
handled by a single machine.

284) For loop problem?
Answer: The "for loop problem" is a term used to describe a specific type of performance issue that can occur when using
a for loop in a programming language like Java.
The problem arises when a loop iterates over a large number of elements, such as a large array or a collection,
and performs a significant amount of computation or I/O on each element.
The problem is that, as the number of elements increases, the total time spent in the loop increases linearly,
which leads to poor performance and long wait times for the operation to complete.
This can be particularly problematic in concurrent or multithreaded environments, where multiple threads may be blocked
waiting for the loop to complete.
There are several ways to solve the for loop problem:
a) Parallelize the loop: By using a parallel for-loop construct or a parallel stream API, you can divide the work of
the loop across multiple threads or processors, which can significantly speed up the loop.
b) Optimize the loop: By optimizing the operations performed within the loop, such as reducing the number of function
calls or minimizing the amount of memory allocation, you can reduce the overall time spent in the loop.
c) Use an iterator: Instead of using a for-loop, you can use an iterator, which allows you to access elements
one at a time, rather than loading all elements into memory at once.
d) Use a database or external storage: If the data is very large, it may be more efficient to store it in a database
or external storage system, and retrieve it in smaller chunks using a database cursor or pagination.
e) Use a specialized library: There are libraries available that are specifically designed to handle large data sets
and perform operations on them in a more efficient way.
f) It's worth noting that the "for loop problem" is not always a problem and can be perfectly fine for small data sets or
for operations that are not performance-critical.
g) However, for large data sets or for operations that need to be performed quickly, it can be important to consider
alternative solutions to avoid poor performance.

285) Feature sets?
Answer: A feature set is a collection of features, or variables, that are used as input for a machine learning model.
These features are chosen because they are believed to be relevant to the problem being solved, and they are used to
represent the data in a way that the model can understand and learn from.
For example, in a machine learning model that is used to predict the price of a house, the feature set might include
variables such as the number of bedrooms, the square footage of the house, the age of the house, and the neighborhood it
is located in.
The feature set is one of the most important aspects of building a machine learning model, as the quality and relevance
of the features can greatly impact the performance of the model.
Choosing the right features for a model can be a challenging task, and it often requires domain expertise and
experimentation.
Feature sets can be classified into two types:
a) Numeric Feature: They are numerical values that can be used as input for a model, such as age, salary, etc.
b) Categorical Feature: They are non-numerical values, such as name, color, etc.
Additionally, there are other types of Feature sets like:
a) Binary Feature : They take only two values (0 or 1)
b) One-Hot Encoding : This is used to represent a categorical variable with n levels as n binary variables
c) Polynomial Feature : This is used to add non-linearity to a model 
When creating a feature set, it is also important to consider the size of the feature set, as including too many
features can lead to overfitting, where the model performs well on the training data but poorly on new, unseen data.

286) Load Balancers?
Answer: Load balancers are network devices that distribute incoming network traffic across multiple servers.
They work by routing client requests to different servers based on a set of rules or algorithms.
The goal is to distribute the workload evenly across servers and ensure that no single server is overwhelmed with too
many requests.
Here's a general overview of how a load balancer works:
a) A client sends a request to the load balancer's IP address or hostname.
b) The load balancer receives the request and uses an algorithm to determine which server should handle the request.
c) The algorithm can be based on the current server load, the number of active connections, or the geographic
location of the client.
d) The load balancer routes the request to the chosen server.
e) The server processes the request and sends a response back to the load balancer.
f) The load balancer forwards the response back to the client. 
There are several types of load balancers and algorithms that can be used, such as:
a) Round-robin: it distributes requests to servers in a circular fashion, sending the first request to the first
server, the second request to the second server, and so on.
b) Least connections: it distributes requests to the server with the least number of active connections.
c) IP hash: it distributes requests to servers based on the hash value of the client's IP address.
d) Least response time: it distributes requests to the server with the lowest response time.
e) Layer 7 Load Balancing: it distributes requests based on application layer data, such as the URI or the hostname.
f) DNS Load balancing: it distributes requests based on the DNS resolution, it can be used for global load balancing.
Load balancers can also provide other features such as SSL offloading, caching, compression, and health checks to
ensure that only healthy servers are receiving traffic.
There are several types of load balancers, including:
a) Hardware load balancers: These are physical appliances that are installed on-premises. They are typically more 
expensive than software load balancers, but offer better performance and more advanced features.
Examples include F5 BIG-IP and Cisco ACE.
b) Software load balancers: These are load balancers that run on software, and can be installed on a server or in the cloud.
They are typically less expensive than hardware load balancers, but may not offer the same level of performance or features.
Examples include HAProxy, NGINX, and Amazon Elastic Load Balancer.
c) Cloud-based load balancers: These load balancers are offered by cloud providers, and are typically easy to set up and manage.
Examples include Amazon Elastic Load Balancer, Google Cloud Load Balancer, and Microsoft Azure Load Balancer.

287) Domain name servers?
Answer: A Domain Name Server (DNS) is a system that translates human-friendly domain names, such as 'www.example.com',
into the IP addresses that computers use to locate each other on the internet.
DNS servers work by maintaining a database of domain names and their corresponding IP addresses.
When a client, such as a web browser, needs to resolve a domain name to an IP address, it sends a request to a DNS server.
The DNS server then looks up the IP address associated with the domain name in its database and returns it to the client.
DNS servers are organized in a hierarchical structure, with different levels of servers responsible for different
levels of the domain name system.
At the top level, there are the root servers, which maintain a list of the top-level domains (TLDs)
such as **.com**, **.org**, and **.edu**.
Below the root servers are the TLD servers, which are responsible for maintaining a list of the domain names within a
specific TLD.
These servers are also responsible for providing the IP address of the authoritative DNS server for a specific
domain name.
Finally, at the bottom level, there are the authoritative DNS servers, which are responsible for maintaining the
records for a specific domain name and providing the IP addresses associated with that domain name.
When a client sends a request to resolve a domain name, the request is sent to the root servers, which then directs
the request to the TLD servers, and then to the authoritative DNS server for the specific domain name.
Caching DNS servers are also used to speed up the resolution process by storing recently looked-up domain names and
their corresponding IP addresses.
This way, if a client requests the same domain name again, the caching DNS server can return the IP address without
having to query the authoritative DNS server again.
It's important to note that DNS servers also have a role in providing security by making sure requests are coming from
authorized sources and also by providing additional security features like DNSSEC which ensures that the response from
the DNS server is authentic and not tampered.
There are several Linux commands that can be used to troubleshoot DNS issues:
a) 'nslookup': This command allows you to query DNS servers and retrieve information about a domain name.
For example, 'nslookup www.example.com' will return the IP address associated with the domain name.
b) 'dig': This command is similar to nslookup and allows you to query DNS servers and retrieve information about a
domain name. For example, 'dig www.example.com' will return the IP address associated with the domain name.
c) 'host': This command is similar to 'nslookup' and 'dig', it allows you to query DNS servers and retrieve information
about a domain name. For example, 'host www.example.com' will return the IP address associated with the domain name.
d) 'ping': This command allows you to test the reachability of a host by sending ICMP echo request packets and measuring
the response time. For example, 'ping www.example.com' will test the reachability of the host associated with the domain name.
e) 'traceroute': This command shows the path a packet takes to reach the destination. It's useful to troubleshoot routing issues.
For example, 'traceroute www.example.com' will show the path a packet takes to reach the host associated with the domain name.
f) 'nsupdate': This command allows you to update DNS records dynamically, useful for cases when you need to update
the IP address associated with a domain name.
g) 'resolvectl': This command is useful to check the DNS configuration and status on a Linux system. For example, 
'resolvectl query example.com' will show the DNS server the system is using to resolve the domain name.
It's important to note that these commands are just a small subset of the available commands and the specific options
available can vary depending on the Linux distribution.
It's also a good practice to check the system's log files to get additional information on what's happening,
the log files usually are in '/var/log/' directory, and you can use commands like 'tail -f /var/log/messages'
to see the log files in real-time.

288) Firewalls?
Answer: A firewall is a security device that controls incoming and outgoing network traffic based on a set of rules and other
criteria. Firewalls are typically deployed at the gateway to a network and are designed to block unauthorized access while
allowing authorized communications.
There are several different types of firewalls, including network firewalls, host-based firewalls, and application-based firewalls.
a) Network firewalls are typically hardware devices that sit at the edge of a network and control traffic based on
IP addresses and ports.
b) Host-based firewalls are software applications that run on individual hosts and control traffic based on the
application that is generating or receiving the traffic.
c) Application-based firewalls are specialized firewalls that are designed to control traffic for specific
applications, such as a web server or a database server.
To configure a firewall, you will typically need to access the firewall's management interface,
which can be a web-based interface, a command-line interface, or a combination of both.
From the management interface, you can create and edit firewall rules, view logs, and perform other tasks.
The specific steps involved in configuring a firewall will depend on the type of firewall and the vendor that
provided it, but some general steps include:
a) Defining the firewall's role and scope (e.g. perimeter firewall, internal firewall)
b) Configuring the interfaces and zones
c) Creating rules and policies to control traffic
d) Enabling logging and monitoring for security events
It's also important to regularly review and update the firewall configuration to ensure it remains aligned with the
organization's security policies and to address any security threats that may have emerged.
In Linux, firewall configuration is typically stored in the **iptables** configuration file, which is usually located
at '/etc/sysconfig/iptables' or '/etc/iptables/iptables.rules'.
This file contains the rules that are loaded into the iptables firewall when the system starts up.
Another way to persist firewall rules is using service that loads iptables rules on startup like iptables-persistent
(for Ubuntu and Debian) or firewalld (for Red Hat and CentOS) which provides a convenient way to manage firewall rules
and automatically load the rules at boot time.
In the case of firewalld, firewall rules are stored in XML files in the '/usr/lib/firewalld/zones/' directory,
and the active zone's configuration can be found in '/etc/firewalld/zone/'.
It's important to note that different Linux distributions may store firewall configuration in different locations,
so it's always a good idea to consult the documentation for your specific distribution to determine the location of the
iptables configuration file.

289) VPN?
Answer: A Virtual Private Network (VPN) is a technology that allows users to securely connect to a private network over
the Internet. It creates a secure, encrypted "tunnel" between a user's device and the VPN server, which protects the data as it
travels over the public network. When a user connects to a VPN, the user's device establishes a connection with the VPN server.
The VPN server then authenticates the user and assigns the device a new IP address from the VPN server's IP address pool.
Once the connection is established, all of the user's internet traffic is routed through the VPN server,
and the VPN server acts as a proxy for the user's internet requests.
Because the VPN server encrypts the data before it leaves the user's device, and decrypts it after it arrives
at the VPN server, any third-party that intercepts the data in transit (such as a hacker or government agency)
will only see encrypted data and not be able to read the user's data.
VPN also enables users to access content that is blocked in their geographic location, by assigning a new IP address
of a different location.
There are two main types of VPNs:Remote access VPNs and site-to-site VPNs: A remote access VPN allows individual users 
to securely connect to a private network from a remote location. While a site-to-site VPN allows multiple remote locations 
to securely connect to a central private network.

290) Multi-core, modern concurrency constraints?
Answer: Modern computers have multiple cores, which allows them to perform multiple tasks at the same time.
This is known as concurrency. However, concurrency can also introduce new challenges and constraints that need to be 
addressed in order to take full advantage of the multiple cores.
Data Race: When multiple threads are accessing and modifying the same shared data simultaneously without proper
synchronization, it can lead to unexpected and inconsistent results.
a) Deadlock: When two or more threads are blocked, waiting for each other to release a resource, it can lead to a deadlock.
Deadlocks can cause the program to hang indefinitely and can be difficult to detect and diagnose.
b) Starvation: When a thread is not able to access a shared resource because other threads are holding the resource, 
it can lead to starvation. Starvation can cause a thread to be blocked indefinitely and can lead to poor performance.
c) Priority Inversion: When a high-priority thread is blocked by a low-priority thread, it can lead to a priority inversion.
Priority inversion can cause a high-priority thread to be blocked indefinitely, leading to poor performance.
d) Memory Consistency: With multiple cores, it is possible that different cores may have different views of memory.
This can lead to unexpected behavior and can be difficult to debug.
To address these constraints, various synchronization constructs such as locks, semaphores, and monitors are used to
ensure that shared resources are accessed in a safe and predictable manner.
Furthermore, programming languages and frameworks such as Java and .NET provide built-in support for multi-threading
and concurrency, making it easier for developers to write concurrent code.
Additionally, modern processors also have built-in support for concurrency through features such as simultaneous
multithreading (SMT), which allows a single core to execute multiple threads at the same time.
It's worth noting that, while concurrency can improve performance, it is not always the best solution,
and it's important to consider the trade-offs between concurrency and performance.

291) Swap space?
Answer: Swap space is a portion of a computer's storage (such as a hard drive or SSD) that is used as virtual memory when the
physical RAM (Random Access Memory) is fully utilized. It acts as an overflow area where the operating system can
temporarily store data that is not currently being used by active processes, freeing up RAM for other tasks.
Here are some key points about swap space:
a) Virtual Memory Extension: Swap space extends the available memory beyond the physical RAM, allowing the system to
handle more applications or larger data sets simultaneously.
b) Performance Implications: While swap space can help prevent system crashes due to memory shortages, accessing data
from swap space is much slower than accessing it from RAM because disk read/write speeds are significantly lower than RAM speeds.
c) Types of Swap Space: Swap Partition: A dedicated partition on a storage device specifically reserved for swap space.
Swap File: A regular file on the filesystem that serves the same purpose as a swap partition.
d) Operating System Management: Most modern operating systems, such as Linux, Windows, and macOS, can manage swap space
automatically, but users can also configure its size and location.
e) Usage Scenarios: Swap space is particularly useful in systems with limited RAM or in scenarios where memory usage can
occasionally spike, such as during complex computations or when running memory-intensive applications.
f) Swappiness: In Linux systems, the "swappiness" parameter controls the tendency of the kernel to move data from RAM to
swap space. A lower value means the system will prefer to keep data in RAM, while a higher value means it will use swap
space more readily.
In summary, swap space is a critical component of modern operating systems that enhances memory management capabilities
by providing a backup area for data when RAM is insufficient. However, reliance on swap space can impact system performance
due to the slower nature of disk-based storage compared to RAM.

292) Memory cache?
Answer: Memory cache, also known as cache memory, is a small, high-speed memory storage that is used to temporarily hold data
that is frequently accessed by a computer's central processing unit (CPU).
The purpose of cache memory is to speed up the access time to frequently used data, by reducing the need to access
the main memory or storage devices.
There are several key concepts related to memory cache:
a) Cache Hierarchy: Memory cache is typically organized in a hierarchical fashion, with multiple levels of cache memory,
each with a different size and speed.
The lowest level of cache memory is the L1 cache, which is the smallest and fastest, followed by the L2 cache,
and so on.
b) Cache Replacement Policy: Memory cache is typically implemented using a cache replacement policy, which determines which data should be
removed from the cache when it becomes full.
The most common cache replacement policies include the least recently used (LRU) policy, which removes the
data that has been accessed least recently, and the first in, first out (FIFO) policy, which removes the data
that was added to the cache first.
c) Cache Miss: A cache miss occurs when the CPU requests data that is not present in the cache memory.
This can happen when the data has been replaced by a more recently used piece of data, or when the data
is being accessed for the first time.
d) Cache Hit: A cache hit occurs when the CPU requests data that is present in the cache memory.
This results in faster access to the data, as the CPU does not need to access the main memory or storage device.
Memory caches are widely used in many industries, such as:
a) Computer Industry: Memory caches are used in processors and computer systems to speed up the access time to frequently used data.
For example, Intel processors have a built-in L1 and L2 cache to speed up the data access.
b) Storage Industry: Memory caches are used in storage devices such as hard disk drives and solid-state drives to speed up the
access time to frequently used data. For example, many SSDs have a built-in cache memory to speed up the access time to frequently used data.
c) Network Industry: Memory caches are used in networking equipment such as routers and switches to speed up the access time to
frequently used data.
For example, many routers use a cache to store the most recently accessed web pages, reducing the need to
request the same web page multiple times.
d) Web Industry: Memory caches are used in web servers and web applications to speed up the access time to frequently used data.
For example, many web servers use caching mechanisms to store the most recently accessed web pages,
reducing the need to generate the same web page multiple times.
Overall, memory caches play a key role in improving the performance of systems by reducing the access time to
frequently used data, it's important to consider the trade-off between the size of the cache and the performance,
a bigger cache can store more data but it can also consume more resources.

293) Scheduling?
Answer: Operating system scheduling refers to the way in which the operating system allocates CPU time to different processes.
There are several scheduling algorithms that are commonly used, including:
a) First-Come, First-Served (FCFS): Processes are executed in the order in which they arrive in the ready queue.
b) Shortest Job First (SJF): Processes are executed in order of their burst time (the amount of time they need to execute).
c) Priority Scheduling: Processes are executed based on their priority level, with higher priority processes being executed before
lower priority ones.
d) Round Robin (RR): Processes are executed in a circular fashion, with each process being given a fixed time slice (quantum)
before being moved to the back of the queue.
e) Multilevel Queue: Processes are divided into different queues based on some criteria (e.g. priority, type), and different
scheduling algorithms are applied to each queue.
f) Multilevel Feedback Queue :Is an extension of the multilevel queue scheduling algorithm, but process can move between queues.

294) What resource process needs and thread needs?
Answer: A resource is any item or capability that a computer system uses, such as memory, disk space, or network bandwidth.
A process is an instance of a program that is being executed by a computer system.
Threads are a way for a process to divide itself into multiple execution units.
Each thread runs in its own context and shares the process's resources, including memory and open files.
A process can create multiple threads to perform different tasks simultaneously.
In summary, a process needs resources such as memory and disk space to execute, while threads within a process share
those resources and can run multiple tasks simultaneously.

295) Livelock and how to avoid?
Answer: A livelock is a situation in which two or more threads are actively trying to acquire a resource but none of them can
proceed because the resource is constantly being acquired and released by the other threads.
This results in the threads continuously changing their state in response to the state of the other threads,
but no progress is made.
Here are several ways to avoid livelock:
a) Prioritizing resources:
By prioritizing resources, a thread can be made to wait for a higher-priority resource instead of constantly
trying to acquire a lower-priority resource that is constantly being acquired and released by other threads.
b) Using a timeout:
A timeout can be set for acquiring a resource, so if a thread is unable to acquire the resource within a
certain period, it can move on to other tasks.
c) Preempting a resource:
A thread can be allowed to temporarily take control of a resource, even if another thread is currently
using it, to break the livelock. 
d) Breaking symmetry:Introducing randomness into the resource acquisition process can break symmetry, allowing one thread to proceed while 
the others are blocked.
e) Using a signaling mechanism: A signaling mechanism can be used to inform other threads when a resource is available, 
so they do not have to constantly check for its availability.
f) Resource hierarchy: Similar to lock hierarchy, using a resource hierarchy can help avoid livelock by ensuring that a thread always
acquires resources in a consistent order.
g) Deadlock Detection: Similarly to deadlock, livelock detection algorithms can be used to detect livelocks and take appropriate
action to resolve them.
It's important to note that, as with deadlocks, avoiding livelocks completely may not be possible, but they can be
mitigated by following the above techniques.

296) Deadlock and how to avoid?
Answer: There are several ways to avoid deadlock in multithreading:
a) Avoiding the use of nested locks: Nested locks can lead to deadlocks if the order of locking is not consistent across 
all threads.
b) Using lock timeouts: Setting a timeout for acquiring a lock can help prevent deadlocks by allowing a thread to move 
on if it is unable to acquire a lock.
c) Using a lock hierarchy: Establishing a hierarchy for locks can help prevent deadlocks by ensuring that a thread always 
acquires locks in a consistent order.
d) Avoiding circular wait: Deadlocks can occur when two or more threads are waiting for each other to release a resource.
To avoid this, ensure that a thread never waits for a resource that is held by another thread that is also
waiting for a resource held by the first thread.
e) Using a global lock: If a system has multiple locks, using a global lock can help prevent deadlocks by ensuring that all threads
acquire the global lock before attempting to acquire other locks.
f) Using non-blocking algorithm: Some algorithm such as lock-free, wait-free and compare-and-swap can help avoid deadlock 
by allowing multiple threads to access shared resources simultaneously.
g) Using a deadlock detection algorithm: Deadlock detection algorithms can be used to detect deadlocks and take appropriate 
action to resolve them.
It's important to note that avoiding deadlocks completely is not always possible, but they can be mitigated by
following the above techniques.

297) B-trees?
Answer: A B-Tree is a type of balanced tree data structure that is widely used in computer science for indexing and storage of
large amounts of data.
The "B" in B-Tree stands for balanced, which refers to the fact that the tree is designed to maintain a balance between
the number of elements in its subtrees in order to keep the height of the tree as low as possible.
B-Trees are typically used in databases, file systems, and other applications that need to store and retrieve large
amounts of data in an efficient manner.
One of the main advantages of B-Trees is that they allow for fast searching, insertion, and deletion of elements,
even when the number of elements in the tree is very large.
A B-Tree is made up of a set of nodes, each of which contains a number of keys and associated values.
The keys in a B-Tree are used to organize the elements in the tree in a way that allows for fast searching
and traversal.
Each node also contains a set of pointers to other nodes, which are used to navigate the tree.
The structure of a B-Tree is designed to keep the height of the tree as low as possible, which helps to ensure that
operations on the tree are performed quickly.
This is accomplished by ensuring that each node contains a large number of keys and by allowing for the creation of
additional nodes when the number of keys in a node exceeds a certain threshold.
B-Trees are widely used in database systems and filesystems such as NTFS, ext3, ext4 and many more.
They are also used in various NoSQL databases like MongoDB, Cassandra, and Hbase.
B-Trees are also useful in the external memory algorithms, where the size of data is too large to fit into
internal memory, B-Trees are used to store the data in an external memory and read it into the internal memory as needed.

298) AVL Trees?
Answer: 
An AVL tree is a self-balancing binary search tree.
It is a variation of the more common binary search tree, but it is more balanced, which means that the height of the
tree is always kept as small as possible.
AVL trees maintain balance by enforcing the following property:
For every node in the tree, the height of its left and right subtrees can differ by at most 1.
This is accomplished by performing rotations on the tree when a node becomes unbalanced.
There are four types of rotations in AVL tree:
Left-Left Rotation (LL Rotation).
Right-Right Rotation (RR Rotation).
Left-Right Rotation (LR Rotation).
Right-Left Rotation (RL Rotation).

299) Red Black Trees?
Answer: A Red-Black Tree is a type of self-balancing binary search tree, where each node has an extra bit that represents
the color of the node: red or black.
The tree is designed to maintain balance in such a way that the height of the tree is always 'O(log n)' where n is
the number of nodes in the tree.
The balance is achieved by enforcing a set of properties, known as the red-black properties:
Every node is either red or black. The root is black. Every leaf (NIL) is black.
If a node is red, then both its children are black. For each node, all simple paths from the node to descendant leaves 
contain the same number of black nodes. The main advantage of the Red-Black Tree is its ability to keep the height of 
the tree balanced, which results in faster search, insertion and deletion operations.
The Java collections framework includes several classes that use balanced tree algorithms, such as:
a) 'java.util.TreeMap':
This class implements the SortedMap interface, which stores its elements in a red-black tree.
The elements are ordered by their natural ordering or by a Comparator provided at the time of creation.
This class provides guaranteed 'log(n)' time cost for the basic operations like get, put and remove.
b) 'java.util.concurrent.ConcurrentSkipListMap':
This class is an implementation of the ConcurrentNavigableMap interface and stores its elements in a skip list. 
A skip list is a data structure that is similar to a balanced tree and provides guaranteed 'log(n)' time cost
for the basic operations like get, put and remove.
c) 'java.util.concurrent.ConcurrentHashMap':
This class is an implementation of the ConcurrentMap interface and uses a technique called lock striping
to achieve high concurrency.
It uses a balanced tree data structure called red-black tree to store the elements when the size of the map grows.
In addition to the HashMap class, which uses a technique called open addressing to store its elements and does not
use a balanced tree algorithm.
It uses an array as the underlying data structure and can have a worst-case time complexity of 'O(n)' for certain
operations like put and get.
It's worth noting that the actual implementation of these classes can change between different versions of the
Java API and other libraries, but the basic principles and algorithms remain the same.

300) Can you explain the different levels of testing (unit, integration, system, and acceptance)? 
Answer: 
a) Unit Testing. Definition:Unit testing involves testing individual components or functions of the software in isolation. 
The goal is to validate that each unit of the software performs as expected.
Key Points:
Scope: Smallest testable parts of the application, such as functions, methods, or classes.
Performed By: Developers during the development phase.
Tools: JUnit, NUnit, pytest, etc.
Benefits: Catches bugs early, simplifies debugging, ensures code correctness, and facilitates code refactoring.
Example: Testing a function that calculates the sum of two numbers to ensure it returns the correct result.
b) Integration Testing. Definition: Integration testing focuses on verifying the interactions and data flow between 
integrated units or components. It ensures that combined parts of the application work together as intended.
Key Points:
Scope: Groups of units or modules that are integrated to perform a set of functions.
Performed By: Developers or testers after unit testing.
Approaches: Top-down, bottom-up, sandwich (hybrid), big bang.
Tools: JUnit, NUnit, pytest (for integration testing), or specialized integration testing tools like Postman for API testing.
Benefits: Identifies issues with interfaces, interactions, and dependencies between modules.
Example: Testing the interaction between a user authentication module and a database module. 
c) System Testing. Definition: System testing involves testing the complete and integrated software application to evaluate 
the system's compliance with the specified requirements. It is performed in an environment that closely mirrors production.
Key Points:
Scope: Entire system, including end-to-end scenarios.
Performed By: Independent testing team.
Types: Functional testing, performance testing, security testing, usability testing, etc.
Tools: Selenium, LoadRunner, JMeter, etc.
Benefits: Validates the entire system’s functionality, performance, and reliability.
Example: Testing an e-commerce application to ensure it handles user registrations, product searches, and transactions correctly. 
d) Acceptance Testing. Definition: Acceptance testing is conducted to determine whether the system meets the business 
requirements and is ready for deployment. It is often the final level of testing before the software goes live.
Key Points:
Scope: Entire system, with a focus on business requirements and user needs.
Performed By: End users, clients, or a QA team on behalf of users.
Types: User Acceptance Testing (UAT), Operational Acceptance Testing (OAT), Contract Acceptance Testing.
Tools: May use the same tools as system testing, along with specific UAT tools like TestRail.
Benefits: Ensures the software meets business needs, reduces the risk of post-release issues, and validates user satisfaction.
Example: End users testing a new feature in a CRM system to ensure it meets their workflow requirements.
Summary. Unit Testing: Tests individual components for correctness.
Integration Testing: Tests interactions between integrated units.
System Testing: Tests the complete system for overall functionality and performance.
Acceptance Testing: Validates the system against business requirements and user expectations.
Each level of testing plays a crucial role in ensuring the quality and reliability of the software, ultimately leading 
to a robust and user-friendly product.

301) How do you ensure that your test cases are comprehensive?
Answer: 
a) Requirements Analysis. Understand Requirements: Thoroughly analyze the functional and non-functional requirements. 
Ensure all aspects of the requirements are understood.
Traceability Matrix: Create a traceability matrix to map test cases to requirements. This ensures all requirements 
are covered by test cases.
b) Test Design Techniques. Equivalence Partitioning: Divide input data into equivalent partitions that can be tested with 
a single test case, reducing the number of test cases while maintaining coverage.
Boundary Value Analysis: Focus on the boundaries of input ranges, as these are common sources of errors.
Decision Table Testing: Use decision tables to cover combinations of inputs and their corresponding outputs.
State Transition Testing: Test different states and transitions, especially for systems with finite states.
Use Case Testing: Develop test cases based on user stories or use cases to ensure end-to-end scenarios are covered. 
c) Coverage Analysis.
Code Coverage Tools: Use code coverage tools to measure the percentage of code executed by your test cases (e.g., line, 
branch, method coverage).
Requirement Coverage: Ensure each requirement, user story, and acceptance criterion is covered by at least one test case.
d) Reviewing and Refining Test Cases.
Peer Reviews: Have test cases reviewed by peers to identify missing scenarios or edge cases.
Walkthroughs: Conduct walkthroughs with stakeholders to validate that test cases align with requirements and expectations.
Refinement: Continuously refine test cases based on feedback from reviews, defect reports, and changes in requirements.
e) Negative and Edge Case Testing.
Negative Testing: Develop test cases that include invalid inputs, incorrect data, and erroneous user actions to ensure 
the system handles them gracefully.
Edge Cases: Identify and test edge cases that occur at the extreme ends of input ranges or system states.
f) Test Case Prioritization.
Risk-Based Testing: Prioritize test cases based on the risk and impact of potential failures. Focus on high-risk areas 
for more intensive testing.
Critical Path Testing: Identify and prioritize test cases for the critical paths in the application where failures would 
have the most significant impact.
g) Automation.
Automated Testing: Automate repetitive and regression test cases to ensure they are consistently executed and cover 
various scenarios without human error.
Continuous Integration: Integrate automated tests into the CI/CD pipeline to ensure tests are run frequently, and any 
issues are detected early.
h) Use of Test Data.
Varied Test Data: Use a variety of test data to cover different scenarios, including edge cases, typical cases, and error cases.
Data-Driven Testing: Implement data-driven testing where test cases are executed with multiple sets of data to validate 
the application's behavior under different conditions.
i) Monitoring and Metrics/
Defect Analysis: Analyze defects found during testing to identify any gaps in test coverage and create additional test 
cases to cover these gaps.
Test Metrics: Track metrics such as test coverage, defect density, and test execution trends to gauge the effectiveness 
and comprehensiveness of your test cases.
j) Continuous Improvement.
Feedback Loop: Use feedback from production issues, user feedback, and test results to continuously improve and expand your test cases.
Learning and Adaptation: Stay updated with the latest testing techniques, tools, and best practices to enhance your 
testing approach continually. By employing these strategies, you can ensure that your test cases are comprehensive, 
thoroughly covering the application's functionality and reducing the likelihood of defects reaching production.

302) Explain the different phases of the SDLC?
Answer: a) Planning. Objective: Define project scope, objectives, resources, timeline, and feasibility.
Outcome: Project plan and feasibility study.
b) Requirements Analysis. Objective: Gather and analyze business and user requirements.
Outcome: Requirement specifications document (functional and non-functional).
c) Design. Objective: Create the architecture and design of the software.
Outcome: System architecture, detailed design documents, and prototypes.
d) Implementation (Coding). Objective: Develop and code the software based on design specifications.
Outcome: Source code and executable software components.
e) Testing. Objective: Verify that the software works as intended and meets requirements.
Outcome: Test plans, test cases, bug reports, and validated software.
f) Deployment. Objective: Install and configure the software in the production environment.
Outcome: Deployed software and deployment documentation.
g) Maintenance. Objective: Provide ongoing support and make necessary updates or improvements.
Outcome: Updated software, maintenance logs, and support documentation.
These phases ensure a systematic approach to software development, improving the quality and reliability of the final product.

303) What are the key principles of Continuous Integration/Continuous Deployment (CI/CD)?
Answer:
The key principles of Continuous Integration/Continuous Deployment (CI/CD) are:
Continuous Integration (CI).
a) Frequent Commits: Developers integrate code into a shared repository multiple times a day.
b) Automated Builds: Every commit triggers an automated build to ensure the code compiles and functions correctly.
c) Automated Testing: Automated tests run with each build to detect defects early.
d) Immediate Feedback: Developers receive instant feedback on their code, allowing for quick identification and resolution of issues.
e) Consistent Environment: Use of consistent environments for development, testing, and production to avoid configuration issues.
Continuous Deployment (CD)
a) Automated Deployment: Automated tools deploy the application to production without human intervention.
b) Continuous Delivery: Code is always in a deployable state, and updates are automatically delivered to users.
c) Rollback Mechanisms: Ability to quickly roll back changes if something goes wrong in production.
d) Monitoring and Logging: Continuous monitoring and logging to track application performance and detect issues in real-time.
e) Incremental Updates: Deploy small, incremental changes to reduce risk and simplify troubleshooting.
These principles help streamline the development process, ensure high code quality, and accelerate the delivery of software to users.

304) What is Software Development Life Cycle (SDLC)?
Answer:
The software development lifecycle (SDLC) is the cost-effective and time-efficient process that development team use to
design and build high-quality software. Steps:
a) Requirement gathering and planning.
b) Design.
c) Development.
d) Testing.
e) Deployment.
f) Maintenance.

305) Explain different types of SQL Joins?
Answer: SQL Joins are used to combine rows from two or more tables based on a related column between them:
a) INNER JOIN: Returns only the rows where there is a match in both tables on the specified condition.
b) LEFT JOIN: Returns all the rows from the left table and the matching rows from the right table. If there is no match,
NULL values are returned from columns from the right table.
c) RIGHT JOIN: Returns all the rows from the right table and the matching rows from the left table. If there is no match,
NULL values are returned from columns from the left table.
d) FULL JOIN: Returns all the rows when there is a match in either the left or right table. If there is no match, NULL
values are returned for columns from the table without a match.

306) What are the primary key and foreign key in SQL?
Answer: The primary key and foreign key are define relationships between tables in a relational database. A primary key
is a column in a table that uniquely identifies each record in that table. A foreign key in a column or set of columns
in a table that refers to the primary key or another table.

307) What is the difference between SQL and NoSQL databases?
Answer: SQL databases: MySQL, PostgreSQL and Oracle. NoSQL databases: MongoDB, Cassandra, CouchDB and Redis.
The differences: SQL databases are relational databases, which means they use structured schema and organize data into 
tables with rows and columns. NoSQL databases are non-relational and can store unstructured or semi-structured data.
They don't require a fixed schema, and data can be stored in various formats like key-value pairs, document, and graphs.
Scaling vertically (adding more resources to a single server) is the traditional way of scaling SQL databases. NoSQL
databases are designed to scale horizontally by adding more servers to the database.
SQL databases follow the ACID properties (Atomicity, Consistency, Isolation, Durability), ensuring transactions are
reliable and consistent. NoSQL databases often sacrifice strict consistency in favor of availability and partition
tolerance, following AP.

308) What is the difference between 'git fetch' and 'git pull'?
Answer: 'git fetch' is used to get the latest changes from remote repository without merging them into your local
repository changes. 'git pull' is used to get the latest changes from the remote repository and immediately merges them.
It is a combination of two commands 'git fetch' and 'git merge'.

309) How does the RestAssured library work? Give me an example?
Answer: RestAssured is a popular Java library designed for simplifying the testing of REST APIs. It provides a behavioral
driven style for constructing expensive and readable API tests.

310) What is the request body / payload in API?
Answer: The request body, also known as the payload, is part of an API request where data is sent from the client to the
server. The common formats are JSON and XML.

311) What is the difference between the query parameter and the path parameter?
Answer: The path parameter is part of the URL path itself. The query parameter is key-value pair data that can include
together with our request to pass data to the server. They are appended to the end of the URL after '?' symbol and are
separated by '&'.

312) What is the query parameter in API?
Answer: The query parameters are key-value pair data structure that we include together with our request to pass data to
server. They are appended to the end of the URL after '?' symbol and separated by '&'.

313) How does authentication work in API?
Answer: 
a) Basic Authentication: The client includes a username and password in the request header using the "Authorization"
header, with the credentials base-64 encoded.
b) Token-base Authorization: Users authenticate with a server to obtain a token. The token is then included in the request
headers for subsequent API calls. For example Bearer Token and OAuth (Open Authorization).
c) Certificate-based Authentication: Clients present a digital certificate during the authentication process. The server
validates the certificate to ensure the client identity.

314) What status code responses do you know?
Answer: 
a) 1xx: Informational response. Informational responses are used to indicate that task is in progress.
b) 2xx: Success.
c) 3xx: Redirected.
d) 4xx: Client error.
e) 5xx: Server error. A valid request was made by the client but the server failed to complete the task?
For example: 200 OK, 201 Created, 404 Not Found, 403 Forbidden.

315) What is difference between PUT and PATCH?
Answer: PUT means replace the entire resource with given data, while PATCH means replace only specified fields.

316) What API methods do you know?
Answer: 
a) GET method: reads data.
b) POST method: saves data.
c) PUT method: edits data.
d) DELETE method: deletes data.
e) PATCH method: can be used to partially update specified data.
f) OPTIONS method: request information from the endpoint.
g) HEAD method: retrieves only the header of a response without the response body.
h) CONNECT method: establishes a network connection to a resource, typically used for SSL/TLS tunneling.
i) TRACE method: the web server will respond to requests that us the TRACE method by echoing in the responses tha exact
request that was received for diagnostic purposes.

317) What is the difference between SOAP and REST API?
Answer: SOAP (Simple Object Access Protocol) and REST (Representational State Transfer) are two different architectural
styled for designing a web services.
a) SOAP: uses XML and the message format where REST can use multiple formats, but JSON is more commonly used.
b) REST is inherently stateless where SOAP can be stateful or stateless, however, often relies on session and
maintains state.
SOAP may be more suitable for scenarios where strict standards and security are essential, while REST is often preferred
for its simplicity, flexibility and efficiency in web and mobile apps.

318) How do tou do API testing?
Answer: 
a) Analyze API documentation.
b) Send request.
c) Validate response: Verify status code, Verify response body (if applicable).

319) What is HTTP and HTTPS?
Answer: HTTP (Hyper Text Transfer Protocol) and HTTPS (Hyper Text Transfer Protocol Secure).
HTTP and HTTPS are protocol used for communication between a client (such as a web browser) and a server over the internet.
They define how data transferred and formatted during the exchange information. The key differences between HTTP and
HTTPS are related to security. HTTPS adds a layer of encryption, making it more secure.

320) What is API?
Answer: API stands for Application Programming Interface. It is a bridge between data layer and app client. Also, APIs 
define how different software components should interact, allowing them to communicate with each other.
REST (Representational State Transfer) and SOAP (Simple Object Access Protocol) are common architectural style of 
Web APIs.

321) What's a singleton design pattern and why you might need to use it in your framework.
Answer: Singleton is a creational design pattern that lets you ensure that a class has only one instance, while providing
a global access point to this instance. We need singleton to have one instance of Driver during the execution.

322) What kind of reports do you generate from your framework?
Answer: My framework generates maven-cucumber-reporting (cucumber reports) reports.

323) How do you manage test data in your framework?
Answer: The majority of my test data exists in cucumber scenarios. If test data is not suitable for cucumber scenario,
I keep it under resources' folder. For some instances I use external storage like S3 buckets.

324) How do you manage running in different environments?
Answer: I got the value from maven system properties and use it to differentiate environment url.

325) How do you manage credentials in your framework?
Answer: I manage credentials by using Maven System Properties.
'mvn test -Dusername=john_dow@gmail.com -Dpassword=12345a'.

326) How does your code run as part of CI and what is your CI strategy?
Answer: We have two different automated suits we run as part of the CI process. First is a smoke suite. We maintain a small
set of critical test which we run for every release. In Dev and for every changes to make sure new changes will not break
core features and have quick feedback. This Jenkins Job is part of the dev pipeline and triggered after the Dev environment
deployment. Second is the regression unit suite with all our automated tests. We run this suite in Jenkins manually against
the Test environment before each release as part of the regression test execution.

327) What is Continuous Integration?
Answer: CI is a DevOps software development practice where developers regularly merge their code changes into a central 
repository, after which automated build and tests are run. CI most often refers to the build or integration stage of the
software release process and entails both an automation component and a cultural components (learning to integrate 
frequently). The key goals of CI are to find and address bugs quicker, improve software quality, and reduce the time it 
takes to validate and release new software updates.

328) How can you pass value from maven command to your code?
Answer: Maven command with System Property: 'mvn test -Denv=PROD'.

329) What Maven Lifecycle do you know?
Answer: There are three built-in build lifecycles: default, clean and site. The default lifecycle handles your project
deployment, the clean lifecycle handles project cleaning, while the site lifecycle handles the creation of your project's
website.
a) Clean lifecycle. 'clean' remove all files generated by the previous build.
b) Default lifecycle. 'validate' - validate the project is correct and all necessary information is available.
'compile' - compile the source code of the project. 'test' - test, the compiled source code by using a suitable testing
framework. 'package' - take the compiled code and package it in its distributable format, such as JAR. 'verify' - run
any checks on results of integration tests to ensure quality criteria are met.
c) Site lifecycle. 'site' - generate the project's site documentation.

330) What is Maven? And what rule does it play in your framework?
Answer: Maven is a build process automation and project management tool for Java projects. Maven automates the build 
process, including tasks such as compiling source code, running tests, packaging binaries, and creating artifacts.
Maven follows a convention over configuration approach, defining a standard project structure. This makes it easier for
developers to understand and contribute to projects, as they where to find source code, resources and configuration files.
Maven manages project dependencies, retrieving and resolving them from remote repositories.

331) What is the difference between soft and hard assert?
Answer: A hard assert is a traditional assertion mechanism that stops the test execution immediately upon encountering the
first assertion failures. But soft assert allows the test execution to continue even if assertion fails. All assertions
are executed, and the results are collected in the end.

332) Why do you need JUnit / TestNG in your framework?
Answer: My framework is a Java - based framework with Maven, Selenium, Cucumber, and JUnit tools. The main role of JUnit
is to assert tests and provide a run engine.

333) What are JUnit and TestNG?
Answer: JUnit and TestNG are both frameworks for Java programming language, designed to facilitate the development of
automated tests and simplify the testing process.
JUnit was primarily created to use for unit testing in Java.
TestNG is a testing framework that extends JUnit and introduces new features.

334) How would you run only smoke test scenarios or only regression test scenarios in Cucumber?
Answer: We can use Cucumber tags. 'mvn test -Dcucumber.options="--tag @smoke"'.

335) How does parametrized step work in Cucumber?
Answer: You can pass values to the step definition method from your scenario step. Numbers are parametrized by default.
Strings between double quotes are parametrized. You can use Data Tables to pass multiple values.

336) What are the Hooks in Cucumber?
Answer: Hooks are blocks of code that can run at various points of the Cucumber execution cycle. They are typically used
for setup and teardown of the environment before and after each scenario.
Scenario hooks: @Before, @After.
Step hooks: @BeforeStep, @AfterStep.

337) What is the Background in Cucumber?
Answer: We can create the Background steps which run before each scenario, but after Hook. We can use Background steps
to avoid duplication. It is good practice to keep it short.

338) What is Scenario Outline in Cucumber?
Answer: The scenario outline keyword can be used to run the same scenario multiple times, with different combinations
of values.

339) What is the Step Definition?
Answer: A Step Definition is a method with an expression that links it to one or more Cucumber Steps. When Step is
executed, it will look for a matching step definition to execute.

340) What are the Feature, Scenario and Steps keywords?
Answer: Feature keyword is to provide a high-level description of a software feature and to group related scenarios.
Scenario (example) keyword illustrates a business rule for your test. It consists of a list of steps.
Steps: Given, When, Then, And, and But. They represent the actual steps of your test case. Cucumber executes each step
one by one in the sequence.

341) What does the runner class do? Can you describe what is inside?
Answer: It is execution point for Cucumber. It connects Feature files with their Step Definitions.

342) What are the main components of Cucumber?
Answer: Feature files. That's where we write our tests as Cucumber Scenarios with steps. 
Step Definitions: The implementation of steps with actual automation script.
Runner: It is execution point for Cucumber. It connects Feature files with their Step Definitions.

343) What is Gherkin language?
Answer: It is a language we use to write Cucumber Scenarios.

344) What are the benefits of using Cucumber?
Answer: The code becomes reusable once the framework matures. Easier understanding of the test logic from readable 
Scenarios.

345) What is Cucumber in test automation?
Answer: Cucumber is a tool for running automated tests, written in plain language. Cucumber was built to support
Behavior-Driven-Development (BDD).

346) What is SauceLabs and BrowserStack?
Answer: SauceLabs and BrowserStack are both cloud-based platforms that provide infrastructure for testing web and mobile
apps across different browsers, operating systems, and devices. They offer tools and services that enable dev and qa
teams to test their apps in different environments to ensure compatibility and functionality. They provide a platform
that offers a cloud-based Selenium grid for automated testing of web and mobile apps.

347) What is Selenium Grid?
Answer: Selenium Grid allows the execution of WebDrivers script on remote machines by routing commands sent by the client
to remote browser instances. Grid aims to: Provide an easy way to run tests in parallel on multiple machines. Allows
testing on different browser versions. Enable cross-platform library.

348) What is Page Object Model (POM)?
Answer: POM is a design pattern in Selenium that creates an object repository for storing web elements. In POM, we have
each web page of an app as a class. Each class will contain only corresponding web page elements and methods to work
with this page. It helps reduce code duplication.

349) What does Actions class do in Selenium?
Answer: Actions class provides an ability to handle keyboards events, mouse events, and special actions. Some common 
actions: Drag and Drop, Mouse hover, Double click, Right click.

350) How do you type text in an input box in Selenium?
Answer: We do it by using sendKeys(text) method.

351) How do you work with popups in Selenium?
Answer: We use Alert class to work with alert based popups in Selenium. We can accept, dismiss, getText, and sendKeys
using Alert class. We need to switchTo alert. However, we need to wait for alert to be present (explicit wait).

352) How do you work with UI dropdown in Selenium?
Answer: When dropdown is based on select tag then we can use select class to work with it.

353) How do you work with iFrames in Selenium?
Answer: Switch to iFrame first by using switchTo() method. You can switch by index, id and name. Once you completed your
task in the iFrame, you can switch back by using default content.

354) How do you work with multiple Windows in Selenium?
Answer: Each window has unique window handle (sort od ID of the window). We can read these window handles via Selenium
and Switch between windows.

355) What do you check if element exist in the DOM?
Answer: We can try to locate this element and catch an exception if it fails. We use findElements method to check if
element in the DOM. If it returns empty list then element doesn't exist in the DOM.

356) What's the difference between findElement and findElements?
Answer: findElement method returns a single WebElement. It throws NoSuchElement exception if element doesn't exist. 
findElements method returns the list of WebElements. It returns empty list if elements don't exist.

357) What is the difference between implicit and explicit waits?
Answer: Implicit wait is a global wait. We need to declare it once at the beginning of the execution. It gets applied
to all element locators. We use explicit wait in special cases where we need to await special event to occur, like:
elements appears, element disappears etc.

358) What is fluent wait in Selenium?
Answer: It is used to wait for certain events. We can configure pooling time. We can configure specific exceptions to
be ignored.

359) What's the main disadvantages of implicit wait?
Answer: Since it applies to all element locators, it can slow down overall execution time.

360) How do you handle dynamic elements?
Answer: By using xpath's contains method by finding a static part of the element. Another way is by finding a static 
element and building my path from that element in my dynamic element.

361) What is the difference between xpath and css?
Answer: The main differences: CSS is faster than xpath. Xpath has more features to build complex path. It has many
useful methods. For example: finding via text, text contains, and text equals. Xpath can traverse the DOM in any
direction (upwards, downwards) where CSS only downwards.

362) What's the difference between absolute and relative paths?
Answer: The absolute path starts at the root of the HTML and uses the Single Slash to navigate to the target element.
The relative path can start from any place in the HTML and uses double slash to navigate to target element. The relative
path is more widely used than the absolute path.

363) Which Selenium locator do you prefer?
Answer: I prefer ID and name when they are unique. The next preferable locator is CSS, the if I need to write more complex
locators I will work with xpath.

364) What kind of locators do you know in Selenium?
Answer: ID, Name, Class name, xpath, css, link text and partial link text, tag name.

365) What is Document Object Model (DOM) in the UI?
Answer: DOM is an object representation of HTML. We can say it is an object model of the HTML document. The HTML and DOM
are in synced. MOD - Model Object Document.

366) What are the advantages and disadvantages of Selenium?
Answer: The advantages: 
a) Cross-browser compatibility. Selenium supports multiple browsers, making it great fit for testing web apps. 
b) Open Source: Selenium is free to use and has a large community of users and developers.
c) Extensive Ecosystem: Selenium offers a wide range of tools and frameworks for different testing needs.
The disadvantages:
a) It is not suitable for testing desktop apps.
b) Selenium primarily focuses on web apps and has limited support for mobile app testing.

367) How do you do regression test in your project?
Answer: The big portion of our regression suite is automated for our project, and we execute them in Jenkins before each
release. The rest of the not automated test, we execute manually.

368) What do you do when your automated suite is failed?
Answer: First, we check if the automated test failed because of the automation script itself of it failed because of
unexpected behavior from apps. Usually it is easy to look into screenshots or video if framework produce them.
If it is an issue with the script itself, I open the ticket with the details of failure and in the next sprint lifecycle
we will fix it. Meanwhile, I will test this manually and attach the details in overall test automation run reports.
If it is an issue with app, then I will reproduce it manually and open defect ticket.

369) How do you determine which test to automate?
Answer: The test should be part of the regression suite because we automate to reduce manual effort for regression suite.
The test should be automat-able. The tools we use can automate this test. The test is repeatable.

370) How do you create test data for your app?
Answer: Asked Supplier for an example. Generate our own to check basic processing (in acceptance test).

371) Tell me about your latest defect you discovered?
Answer: Malformed response from Supplier cause our app returned error instead xml payload (missing namespace in response).
I noticed that our deployment tool didn't show change ticket if they were placed at the same minute (UI issue).

372) What is a performance testing?
Answer: Performance testing is a non-functional testing that determines how the stability, speed, scalability and 
responsiveness of an app. Some examples: processing speed, data transfer velocity, network bandwidth usage, maximum
concurrent users, memory utilization, workload efficiency and latency times.

373) What are the black and white box testing?
Answer: The black box testing is when we test without a knowledge of how app works internally (code structure,
implementation details). However, we know the requirements and what our app should do.
The white box testing is when we know the internal code structure and implementation details, and we do our testing by
reviewing them and looking for potential defects. We need to have corresponding technical skills to perform a white box
testing.

374) What kind of different testing techniques do you know and use it?
Answer: There are a bunch of different test types. Let's focus on most used once.
a) Positive testing: It determines that your app works as expected.
b) Negative testing: It ensures that your app can handle invalid input unexpected user behavior.
c) Equivalence partitioning: It is testing techniques that divides input data of a unit into partitions of equivalent
data from which test cases can be derived.
d) Boundary value testing: It is testing technique for the edge inputs. The behavior at the edge if the equivalence
partitions is more likely to be incorrect than the behavior within partition.
e) Ad hoc testing. It is an informal or unstructured software testing type that aims to break the testing process in
order to find possible defects.

375) What is a smoke test?
Answer: The smoke test is initial testing to reveal simple failures. They are a subset of test cases that cover the most
important functionality. From my experience, it is useful when automated to catch issues earlier in the development.
For example, it can be integrated with developers CI pipeline and catch the issues before even going to QA environment.

376) How do you determine which test case should be part of regression suite?
Answer: Test case should be part of the important functionality. It should be a repeatable test case.

377) What is a regression suite and why do we need it?
Answer: The regression suite is a collection of test scenarios that address the various functionality that are important
to the software. We typically create a regression suite from existing tests that have already been executed in previous 
release cycles. The reason, we execute test is that new changes can break existing working functionalities, so before
releasing new changes to our customers, we need to execute our regression suite.

378) What is Requirements Traceability Matrix (RTM) in testing?
Answer: RTM is a document that maps and traces user requirement with test cases. The main purpose of RTM is to validate
that all requirements are checked via test cases such that no functionality is unchecked during software testing.

379) What is a defect life cycle?
Answer: It is a specific stage that defect goes through from opening to resolution:
a) Defect is raised and assigned for a fix.
b) Fixed.
c) Retest.
d) If the defect is still there, reopen and start with step1.
e) Otherwise coded.

380) What is a software testing life cycle STLC for your team?
Answer: Usually each team adopts own STLC that works best for them. STLC is a testing process that has specific steps to
be executed in a sequence to ensure that the quality goals have been met. My team's phases of STLC:
a) Requirement (prod specs) analysis.
b) Crafting and reviewing a test plan.
c) QA handoff.
d) Execution / Testing.
e) QA sign-off.

381) What is a test case and what does an excellent test case include?
Answer: A test case describes steps to perform a test for specific factor or functionality. A good test case includes
detailed steps, test data, expected results, test environment, and all the prerequisites to perform a test.

382) What is a test plan and what does a good test plan include?
Answer: A test plan is a detailed document that includes the entrance criteria, the scope of testing, the test strategies,
objectives, schedule, deadlines, resources required to complete the testing, and exit criteria. It is the point of the
reference based on which testing activities are executed and coordinated among the QA Team. The test plan is shared with
Business Analytics, Project Manager, Developers to offer transparency into testing activities so that all stakeholders know
how the software will be tested. The ultimate goal of the test planning process is communicating (not recording) the
software test team's intent, its expectations and its understanding of the testing that's being performed.

383) What is a defect?
Answer: The defect is when the actual result doesn't match with the expected result. Usually, we have our expected
results from the product specification document includes all the aspects of the product. For example, if software is
difficult to understand, hard to use, slow, or just not right and it wasn't mentioned in product specification, it is
a valid defect.

384) Mock Functionality?
Answer:
a) Use mocks, mock method.
b) given/willReturn and when/then methods.
c) then/should and verify methods.
d) ArgumentMatcher and willThrow.
e) Argument Captor.
f) willDoNothing, willAnswer, willCallRealMethod methods.
g) @InjectMock, @Mock, and @Captor annotations.
h) Spy objects.

385) FIRST principle?
Answer: Fast - Unit tests run fast. Independent - Unit tests are independent. Repeatable - Unit tests are repeatable.
Self-Validating: Unit tests validates itself. Thorough & Timely: Cover edge cases.

386) JUnit 5?
Answer: JUnit5 = JUnit Platform + JUnit Jupiter + JUnit Vintage.
JUnit Platform: The JUnit Platform serves as a foundation for launching testing frameworks in the JVM.
JUnit Jupiter: Is the combination of the new programming model and extension model for writing tests and extensions in
JUnit5. 
JUnit Vintage: TestEngine for running JUnit3 and JUnit4 based tests on the platform.

387) Test Driven Development (TDD)?
Answer: Test driven development life cycle (Red, Green, Refactor, Repeat).
a) Red: Write Unit test that fails.
b) Green: Write Application code to make Unit Test pass.
c) Refactor: Clean up. Improve Unit test and Application code.
d) Repeat: Repeat above steps until all your functionality is implemented.

388) Mocking Frameworks?
Answer: The Java ecosystem includes a number of Mocking frameworks.
Features: Minimize hand-coding of mocks... leverage annotations. Set expectations for mock responses. Verify the calls
to methods including the number of calls. Programmatic support for throwing exceptions.
Spring-boot-starter-test: Transitive Dependency for Mocking.

389) Mocks with Mockito and Spring Boot?
Answer: The technique of using test doubles is known as "mocking". Benefits of Mocking: Allows us to test a given class
in isolation. Test interaction between given class and its dependencies. Minimizes configuration/availability of 
dependencies. For example DAO, DB, REST API etc.: We can mock the DAO to give a response. We can mock a REST API to
give a response.

390) Spring Boot support for unit testing?
Answer: What do you need for Spring Boot unit testing? Access to the Spring Application Context. Support for Spring
dependency injection. Retrieve data from Spring 'application.properties'. Mock object support for web, data, REST APIs.
@SpringBootTest: Loads the application context. Support for Spring dependency injection. You can access data from Spring
'application.properties'.

391) Testing using Reflection?
Answer: Need to access non-public fields. Read the field's value. Set the field's value. Invoke non-public methods.
Testing legacy code... can't change code due to corporate policy. In general, testing non-public fields and methods is
controversial, use sparingly.

392) MockBean Annotation?
Answer: Instead of using Mockito: '@Mock' and '@InjectMock'. Use Spring Boot support: '@MockBean' and '@Autowired'.
'@MockBean': Includes Mockito '@Mock' functionality. Also adds mock bean to Spring ApplicationContext. If existing bean
is there, the mock bean will replace it. This mocking the mock bean available for injections with '@Autowired'.
Use Spring Boot '@MockBean' when you need to inject mocks AND inject regular beans from app context.

393) Parametrized Tests?
Answer: JUnit provides '@ParametrizedTest'. Run a test multiple times and provide different parameter values. 
'@ParametrizedTest' source of values? '@ValueSource', '@CsvSource', '@CsvFileSource', '@EnumSource', '@MethodSource'.

394) Vertical Scaling Means?
Answer: Scaling a system by upgrading to more powerful machines.
Vertical scaling means using one server and just making that server more and more powerful as your traffic grows. 
This can result in a single point of failure and has an upper bound of how far you can scale. But for small, 
non-critical systems it may be a simple and cost-effective choice.

395) Horizontal Scaling requires?
Answer: Stateless servers where any server can handle any request.
In a horizontally scalable system, every server should be "stateless" and assume that any request could have been 
handled by itself or by any other server.

396) A database host that is replicated to a backup that's always ready to take over is an example of?
Answer: Warm standby.
When dealing with monolithic relational database hosts, replication is a common backup technique. However scalable 
systems generally rely on distributed key/value data stores, or more generally object stores, AKA "NoSQL" - as these can 
be horizontally scaled with backups on each shard.

397) Which NoSQL database has no master node?
Answer: Cassandra.
Cassandra consists of nodes where any node can serve as the master. It accomplishes this at the expense of consistency.

398) A downside of denormalized data is?
Answer: Handling updates can be expensive.
Updates in a denormalized table can involve iterating through every row, looking for copies of data that must be updated. 
They also take up more space.

399) Distributed "NoSQL" databases with a master node that distributes transactions fall on which side of the CAP triangle?
Answer: Consistency and Partition Tolerance (sacrificing Availability).
Single-master designs favor consistency and partition tolerance. Although in principle availability it what's given up, 
in practice modern NoSQL databases have highly redundant master nodes that can quickly replace themselves in the event 
of failure.

400) When designing a cache that discards the data accessed the longest time ago, which eviction policy is appropriate?
Answer: LRU.
A "least recently used" policy is most commonly used, and evicts data that hasn't been accessed in the longest amount 
of time once memory for the cache fills up.

401) If you need to minimize client latency for retrieving static data around the world at any cost, you would use:
Answer: A CDN.
Content Delivery Networks (CDN's) are aimed at the problem of global traffic and minimizing latency due to long network 
hops. Load balancers and caching technologies such as Redis can also be parts of low-latency designs, but are not 
specifically for the problem of global traffic.

403) An SLA of 100ms at "three nines" means?
Answer: My system will respond in less than 100 mn 99.9% of the time.

404) In HDFS, the server responsible for coordinating requests is called the?
Answer: Name node.
In the Hadoop Distributed File System, the name node coordinates how files are broken into blocks, and where those blocks 
are stored. In high availability settings, multiple name nodes may be present for failover.


NEW SECTION - Microservice Architecture Patterns

1) Design Patterns!
Answer: A set of best practices / solutions / structured approach for problems in Software Design. OOP / Functional /
Cloud / Integration / Resilience / Event Driven Architecture.

2) Integration Patterns?
Answer: Gateway Aggregator Pattern, Scatter Gather Pattern, Orchestrator Pattern (parallel workflow), Orchestrator
Pattern (sequential workflow), Splitter Pattern.

3) Resilient Pattern?
Answer: Timeout Pattern, Retry Pattern, Circuit Breaker Pattern, Rate Limiter Pattern, Bulkhead Pattern.

4) Scatter Gather Pattern?
Answer: aggregator. It broadcasts the message to all the recipients and then collects the response. Use case:
Flight App (Google Flight), collecting flights from Frontier Airlines, Delta, United.

5) Orchestrator Pattern?
Answer: Aggregator + additional business logic to provide a workflow.

6) Orchestrator Pattern (Chained / Sequential workflow)?
Answer: Chained Pattern. There is no special aggregator. Any service can assume the role of aggregation.
Pros: Easy to implement. Cons: Increased Latency. Very difficult to debug. Very Difficult to maintain/implement change
in requirements. Better use Orchestrator For Chained Workflows: Orchestrator to provide a chained workflow.
Pros: Easy to debug. Easy to maintain / implement change in requirements. Cons: Increased Latency.

7) Resilience?
Answer: Reactive systems should be responsive. Resilience = Responsiveness under failure.

8) Timeout Pattern?
Answer: One of the simplest resilience patterns to implement. Treat the request as failed if the response was not received
within the given timeout.

9) Retry Pattern?
Answer: Transient failure. Anything could happen in the network. Recover from Transient failures. It might increase the
overall response time. Do not forget to set Timeout. Do not retry for 4xx error.

10) Circuit Breaker Terms?
Answer: 
CLOSED: Dependent service is UP. All requests are sent. 
OPEN: Dependent service is DOWN. Requests are NOT sent.
HALF OPEN: Dependent service is MIGHT BE UP??. Only few requests are sent to check.

11) Circuit Breaker?
Answer: To allow the client service to operate normally when the upstream service is not healthy. You can use it along 
with Retry + Timeout. Resilience4j: Spring, reactor support. Rate limiter, Bulkhead etc. Config using yaml / Overriding
via Bean.

12) Rate Limiter Pattern?
Answer: 
Server Side Rate Limiter: To limit the number of requests being served by a server node. To protect system resources from
overload. Very Important for CPU intensive tasks.
Client Side Rate Limiter: To limit the number of requests sent by client to the server. To reduce the cost / respect the 
contract.

13) Distributed Rate Limiter?
Answer: Do not get confused this (resilience) rate-limiter with your application feature. Guest user: 5 per second.
Standard user: 50 per second. Prime user: 500 per second. Performance !!.

14) Server Side Rate Limiter?
Answer: To limit the number of requests being served by a server node.

15) Client Side Rate Limiter?
Answer: To limit the upstream calls.

16) RateLimiter vs Bulkhead Pattern?
Answer: RateLimiter: Number of requests per time window. Reject other calls.
Bulkhead: Number of concurrent calls. Queue other calls.


NEW SECTION - Kafka Event Driven Microservices With Java + Spring

1) Need for Event Driven Architecture / Kafka?
Answer: Synchronous complex communication. One service performance might affect another. Downstream services need to be 
aware of upstream services, request/response data structures. Not flexible (Introducing new services in the workflow). 
Upstream Services have to be available. We might lose the request.

2) Kafka?
Answer: Distributed event streaming platform. Key features: Highly available, Horizontally scalable, Ingest large volume
of data, High throughput, Low latency, Fault tolerance.
Open source distributed event streaming platform. Capturing any events in real time and storing for later retrieval /
processing events in real time.

3) Event?
Answer: Anything that happened. For example: sam liked a tweet (likes-event), sam placed an order (order-event).
Also known as: Records, Messages.

4) Topic?
Answer: It is a way to organizing data within a Kafka CLuster. Similar to a Table in a RDBMS. Similar to a Message Queue.
We can have thousands of topics! For example: user-clicks, taxi-location, order-event.

5) Kafka Cluster?
Answer: Kafka Server:
a) Broker (read + write).
b) Controller (managing the cluster).
c) Broker + Controller.

6) Bootstrap-server?
Answer: A kafka cluster can have N number of servers. A set of servers can act like a bootstrap-servers to provide initial
metadata.














