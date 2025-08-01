# System Design my notes

## Q & A.

1. Scalability Patterns?
   * a) Load Balancing.
   * b) Pipes and Filters.
   * c) Execution Orchestration.
   * d) Choreography pattern.
2. Performance Pattern?
   * a) Map Reduce.
   * b) Saga Pattern.
   * c) Transactional Outbox Pattern.
   * d) Materialized View Pattern.
   * e) CQRS.
   * f) CQRS + Materialized View.
   * g) Event Sourcing.
3. Problems of a centralized System?
   * Performance and storage - limited vertical scalability.
   * Single point of failure.
   * High latency - poor user experience.
   * Security and privacy.
4. Which issues Distributed Systems solves?
   * Horizontal scalability allows the system to grow and shrink on demand.
5. What is a Node?
   * node is a process running on a dedicated machine (comes from graph theory).
6. What is a cluster?
   * Collection of computers / nodes connected to each other.
7. Challenges of master-worker architecture?
   * a) Automatic and System Leader Election is not a trivial task top solve, even among people.
   * b) Arriving at an agreement on a leader in a large cluster of nodes ie even harder.
   * c) By default each node knows only about itself. Service registry and discovery is required.
   * d) Failure Detection mechanism is necessary to trigger automatic leader election in a cluster.
8. Master-Worker Coordination Solution?
   * a) Implement distributed algorithms for consensus and fail-over from scratch.
   * b) Apache Zookeeper - High Performance Distributed System Coordination Service.
9. Apache Zookeeper?
   * A high performance coordination service designed specifically for distributed systems.
   * Popular technology used by many companies and projects (Kafka, Hadoop, HBase, etc...).
   * Provides an abstraction layer for higher level distributed algorithms
10. What makes zookeeper a good solution?
    * a) Is a distributed system itself that provides us high availability and reliability.
    * b) Typically runs in a cluster of an odd number of nodes higher than 3.
    * c) Uses redundancy to allow failures and stay functional.
11. Znodes' Properties.
    * Hybrid between a file and a directory.
      * Znodes can store any data inside (like a file).
      * Znodes can have children znodes (like a directory).
12. Znodes Types.
    * Two types of znodes:
      * Persistent - persists between sessions.
      * Ephemeral - is deleted when the session ends.
13. Issues and considerations with transactional outbox pattern?
    * Duplicate event - at least delivery semantics, idempotent operations.
    * No support for transaction - additional field to original doc.
    * Ordering of events - sequence ID which is always higher than prev message.
14. Issues and considerations with Materialized View?
    * Additional space for Materialized View. Space for performance, cost for performance.
    * Where to share Materialized View?
      * The same db.
      * Separate read-optimized db (in memory cache).
15. What is a role of the Shuffle stage in the execution of Map Reduce Computation?
    * Sort and aggregate the intermediate key/value pairs emitted by the Map workers by key, to produce a list of
    (key, values) for the Reduce workers.
16. What problem does the Saga Pattern Solve?
    * Performing a distributed data transaction among multiple microservices and databases.
17. CQRS - Issues/Drawbacks?
    * Eventual consistency between command DB and the query DB.
    * Overhead and complexity.