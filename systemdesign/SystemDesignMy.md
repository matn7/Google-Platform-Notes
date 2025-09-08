# System Design my notes

## Q & A.

**1. Scalability Patterns?**
* a) Load Balancing.
* b) Pipes and Filters.
* c) Execution Orchestration.
* d) Choreography pattern.

**2. Performance Pattern?**
* a) Map Reduce.
* b) Saga Pattern.
* c) Transactional Outbox Pattern.
* d) Materialized View Pattern.
* e) CQRS.
* f) CQRS + Materialized View.
* g) Event Sourcing.

**3. Problems of a centralized System?**
* Performance and storage - limited vertical scalability.
* Single point of failure.
* High latency - poor user experience.
* Security and privacy.

**4. Which issues Distributed Systems solves?**
* Horizontal scalability allows the system to grow and shrink on demand.

**5. What is a Node?**
* node is a process running on a dedicated machine (comes from graph theory).

**6. What is a cluster?**
* Collection of computers / nodes connected to each other.

**7. Challenges of master-worker architecture?**
* a) Automatic and System Leader Election is not a trivial task to solve, even among people.
* b) Arriving at an agreement on a leader in a large cluster of nodes ie even harder.
* c) By default each node knows only about itself. Service registry and discovery is required.
* d) Failure Detection mechanism is necessary to trigger automatic leader election in a cluster.

**8. Master-Worker Coordination Solution?**
* a) Implement distributed algorithms for consensus and fail-over from scratch.
* b) Apache Zookeeper - High Performance Distributed System Coordination Service.

**9. Apache Zookeeper?**
* A high performance coordination service designed specifically for distributed systems.
* Popular technology used by many companies and projects (Kafka, Hadoop, HBase, etc...).
* Provides an abstraction layer for higher level distributed algorithms.

**10. What makes zookeeper a good solution?**
* a) Is a distributed system itself that provides us high availability and reliability.
* b) Typically runs in a cluster of an odd number of nodes higher than 3.
* c) Uses redundancy to allow failures and stay functional.

**11. Znodes' Properties.**
* Hybrid between a file and a directory.
  * Znodes can store any data inside (like a file).
  * Znodes can have children znodes (like a directory).

**12. Znodes Types.**
* Two types of znodes:
  * Persistent - persists between sessions.
  * Ephemeral - is deleted when the session ends.

**13. Issues and considerations with transactional outbox pattern?**
* Duplicate event - at least delivery semantics, idempotent operations.
* No support for transaction - additional field to original doc.
* Ordering of events - sequence ID which is always higher than prev message.

**14. Issues and considerations with Materialized View?**
* Additional space for Materialized View. Space for performance, cost for performance.
* Where to share Materialized View?
  * The same db.
  * Separate read-optimized db (in memory cache).

**15. What is a role of the Shuffle stage in the execution of Map Reduce Computation?**
* Sort and aggregate the intermediate key/value pairs emitted by the Map workers by key, to produce a list of
(key, values) for the Reduce workers.

**16. What problem does the Saga Pattern Solve?**
* Performing a distributed data transaction among multiple microservices and databases.

**17. CQRS - Issues/Drawbacks?**
* Eventual consistency between command DB and the query DB.
* Overhead and complexity.

**18. Event Sourcing Benefits?**
* Visualization.
* Auditing.
* Corrections.
* High Write Performance.

**19. Software Extensibility Architecture Pattern?**
* Sidecar and Ambassador Pattern.
* Anti-Corruption Adapter Pattern.
* Backends for Frontends Pattern.

**20. Reliability, Error Handling and Recovery Software Architecture Pattern?**
* Throttling and Rate Limiting Pattern.
* Retry Pattern.
* Circuit Breaker.
* Dead Letter Queue (DLQ).

**21. Retry Storm?**
* A situation that can cause unrecoverable cascading failures in the system.

**22. Event Driven Architecture Benefits?**
* Decoupling producers from consumers.
* Greater scalability.
* Asynchronous communication.

**23. Production Testing Steps?**
* Measure Baseline.
* Construct a Hypothesis.
* Failure Injection.
* Monitoring.
* Document findings.
* Restore to the original state.
* After testing fixing issues.

**24. Core principles for Microservice Boundaries?**
* Cohesion.
* SRP (Single Responsibility Principle).
* Loose Coupling.

**25. Split Monolith to Microservices?**
* Splitting by App layer.
* Splitting by tech boundaries.
* Splitting by Minimum Size.

**26. Microservices Principles and Best Practices?**
** Database per microservice.
Don't repeat yourself.

**27. Downside of DB per microservice?**
* Added latency.
* Eventual Consistency.
* No "Join" Operations.
* No support for transactions.

**28. Load Balancer vs. API Gateway?**
* Load Balancer:
  * Little performance overhead.
  * Health checks.
  * Different routing algorithms.
* API Gateway:
  * Throttling.
  * Monitoring.
  * API Versioning & Management.
  * Protocol / Data translation.

**29. What is an Event?**
* Fact, Action, State Change.
* Always immutable.
* Can be stored indefinitely.

**30. Difference between Request-Response vs Event-Driven?**
* Synchronous vs Asynchronous.
* Inversion of Control (Request Response - sender must know Receivers APIs).
* Loose Coupling.

**31. Event Delivery Pattern?**
* Event Streaming.
* Pub/Sub.

**32. Event Driven Architecture use cases?**
* Fire and Forget.
* Reliable Delivery.
* Infinite Stream of Events.
* Anomaly Detection.
* Broadcasting.
* Buffering.

**33. CQRS use-cases.**
* Separation of concerns.
* Single Responsibility Principle (SRP).
* Higher Performance.
* Higher Scalability.
* Joining data from different sources.

**34. Three pillars of Observability?**
* Distributed logging.
* Metrics.
* Distributed Tracing.

**35. Distributed logging - best practices?**
* Centralized System.
* Predefined Structure / Schema.
* Logg Level / Log Severity.
* CorrelationId.
* Adding Contextual information.

**36. 5 Golden Signals?**
* Traffic - Amount of demand being placed on our system per unit of time.
* Errors.
* Latency - time it takes for a service to process a request.
* Saturation - how overloaded / full a service / resource is.
* Utilization - how busy a resource is, 0 - 100 %.

**37. Containers in Production? Benefit?**
* Portability between environments.
* Faster deployment / startup.
* Lower infrastructure costs.

**38. System Design - Step-by-step process.**
* Gathering Functional Requirements:
  * How our system behaves.
  * What our system needs to do doesn't need to do.
  * What parts are given to us, we need design our self.
* Gathering non-functional requirements.
  * Ensure our system has the "right qualities", and produces desired behavior.
  * Scalability, high availability, performance.
* Defining System's APIs & Sequence of Events.
* Designing for Functional Requirements.
  * Architecture style, flow of network requests, data we are going to store.
* Addressing Non-Functional Requirements.
* Adding additional components.
* Eliminating: SPoF, performance bottleneck.
* Optimizing critical parts of the system.
* Using non-trivial / specialized DS, Algorithms.

**39. Scalability Example?**
* Billions of active users (~1-2 Billion).
* Hundreds of millions (100-500 million DAU).
* Each user uploads ~1 image / day.
* Each image size ~2 MB.
* Data Processing Volume: ~1 PB / day.

**40. Availability Example?**
* 99.99%.

**41. Performance Example?**
* Response time < 500 ms at 99 percentile.
* Timeline / News Feed Lead Time < 1000 ms at 99 percentile.

**42. Event Driven Model - Benefits?**
* Decoupling - of the Driver App from the rest of the system.
* Event Sourcing Pattern - Trace back the location of the driver through events.

**43. Typical steps to improve, scalability and availability?**
* Load Balancing.
* Sharding / Partitioning.
* Replication.

**44. Bloom Filter?**
* Reduce the number of database queries.
* Reduce costs.
* Improve user login performance.

**45. System Design Tips?**
* Don't panic.
* Communicate Efficiently to Clarify the Requirements.
* Document your Design.
* Discuss Trade-Offs.

**46. LSM Tree?**
* LSM Log Structured Merge Tree, write optimized.

**47. PACEL?**
Trade off between Latency and Consistency. Event when there is no partition in network.

**48. Concurrent Writes?**
* Least Write Wins - Cassandra.
* Vector Clock 0 Dynamo.

**49. Basics of REST?**
* URI.
* Stateless.
* Rest API Versioning.
* Pagination.

**50. Scaling Databases?**
* Cold Standby (periodic backups).
* Warm Standby (replication).
* Hot Standby (writing simultaneously).

**51. De-normalizing?**
* Normalized Data: Less storage space, more lookups, updates in one place.
* Denormalized Data: More storage place, one lookup, updates are hard.

**52. Tip of proposing DBs.**
* Be sure to understand requirements about scale, consistency and availability before proposing a specific database solution.

**53. How can we scale system to support millions of users?**
* Keep Web Tier stateless.
* Build redundancy at every tier.
* Cache data as much as you can.
* Support multi data centers.
* Host static asset in CDN.
* Scale your data ties by sharding.
* Monitor your system and use automation tools.

**54. Performance Optimization?**
* Multi data=center setup.
* Synchronize data with eventual consistency model.

**55. Consistent Hashing Salves data partition problems?**
* Distribute data across multiple servers evenly.
* Minimize data movement when needs are added or removed.
* Automatic scaling, heterogeneity: Servers with higher capacity are assigned with more virtual nodes.  

**56. Inconsistency Resolution?**
* Replication gives high availability. Versioning and Vector Clocks are used to solve inconsistency problems.
* Versioning: Treating each data modification as a new immutable version of data.
* A Vector Clock: Is a "server, version" pair associated with data item. It can be used to check if one version proceeds,
succeeds, or in conflict with others.

**57. Failure detection?**
* By gossip protocol.

**58. Hinted Handoff?**
* If a server is unavailable due to network or server failure, another server will process the requests temporarily.
When the down server is up, changes will be pushed back to achieve data consistency.

**59. Handling permanent failures?**
* Implement an anti-entropy protocol to keep replicas in sync.
* Anti-entropy involves comparing each piece of data on replicas and updating each replica to the newest version. A Merkle
Tree is used for inconsistency detection and minimizing the amount of data transferred.

**60. Problem - Technique.**
* Ability to store big data - Use consistent hashing to spread load across servers.
* High Availability Reads - Data replication, Multi-datacenter setup.
* High Availability Writes - Versioning and Conflict resolution with Vector Clock.
* Dataset Partition - Consistent Hashing.
* Incremental Scalability: Consistent Hashing.
* Heterogeneity - Consistent Hashing.
* Handling Temporary Failures - Sloppy quorum and Hinted Handoff.
* Tunable Consistency - Quorum Consensus.
* Handling Permanent Failures - Merkle Tree.
* Handling Data Center outage - Cross data-center replication.

**61. Bloom Filter?**
* Is a space efficient probabilistic technique to test if element is a member of dataset.

**62. Strategies to Scale Database?**
* Indexing - Analyze the query patterns of your app and create right index B-tree Index (Range Query).
* Materialized View - Pre compute complex query results and store them for faster access.
* De-normalization - Reduce complex joins to improve query performance.
* Vertical Scaling - Boost DB server by adding more (CPU, RAM, Storage).
* DB Caching - Store frequently accessed data in a faster storage layer.
* Replication - Create Replicas of your primary db on different servers from scaling the records.
* Sharding - Load resources that the page will need before they are needed.

**63. 7 ways to 10x your API Performance?**
* Caching.
* Connection Pool.
* N + 1 query.
* Pagination.
* JSON Serializer.
* Compression.
* Async logging.

**64. Top 5 API Architecture Style?**
* SOAP.
* REST-ful.
* GraphQL.
* gRPC.
* Web Socket.
* Web Hook.

**65. Questions to ask to gather requirements?**
* What specific feature are we going to build?
* How many users does a product have?
* How fast does a company anticipate to scale? What are anticipation scales in 3 months, 6 months, and a year?
* What is company's technology stack? What existing services you might leverage to simplify the design?

**66. Commonly asked back-of-the-envelope estimations?**
* QPS, peak QPS, storage, cache, number of servers etc.

**67. Questions to get started.**
* What specific features are we going to build?
* How many users does a product have?
* How fast does the company anticipate to scale up? What are the estimated scales in 3 months, 6 months, and a year?

**68. Consistent Hashing?**
* Vertical Nodes.

**69. Inconsistency Resolution?**
* Versioning.
* Vector clock.

**70. Handling temporary failures?**
* Hinted handoff.

**71. Anti-entropy permanent failures?**
* Merkle tree.

**72. Efficient way to find out which SSTable contains the key?**
* Bloom filter - efficiently check possibility of existence.

**73. Pagination.**
* Return reduced list of queried records.

**74. Primary Secondary DB setup?**
* Setup for high availability.

**75. Databases that support high writes?**
* NoSQL key value databases or column oriented databases - Cassandra.

**76. Offset.**
* Position of a message in partition.

**77. A consumer group.**
* Is a set of consumers, working together to consumer messages from topics.

**78. Metrics data - time series.**
* Open TSDB, Influx DB, Prometheus.

**79. Concurrency issues.**
* Optimistic locking.
* Pessimistic locking.
* Database constraints.

**80. Data inconsistency (Distributed Transactions).**
* Two phase commit (2PC) - inconsistency / reconciliation.
* Saga.

**81. Steps.**
* Understand the problem and design scope:
  * Narrow scope.
  * Non functional requirements.
  * Back of the envelope estimation.
* Prepare high level design.
* Design deep dive.

**82. At least once?**
* Retry.

**83. At most once?**
* Idempotency.

**84. Consistent Hashing?**
* When shard exhaustion happens, it requires updating the sharding function and moving data around.

**85. Security.**
* Least Privileges.
* Patching.
* Encryption at rest and transit.
* SAST tools.
* Too much details in error messages.
* Firewall.
* Weak passwords / 2FA.
* Integrity check CRC.
* Trusted / Repos.
* Monitoring / Systems / Adjust.
* Network isolation.
* Sanitize input data.
* White listing.

**86. Failure Detection.**
* Gossip / heartbeats.













