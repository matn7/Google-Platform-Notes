# Designing Data-Intensive Applications (2nd Ed.) — Original Study Guide

## Trade-offs in Data System Architecture

**1. What is a data-intensive application?**
* A data-intensive application is one whose primary challenges involve storing, retrieving, processing, and transporting
data efficiently. 
* The performance and reliability of these systems depend more on data management than on computational algorithms.

**2. What goals should architects consider when designing a data system?**
* Common goals include:
  - Reliability.
  - Scalability.
  - Maintainability.
  - Performance.
  - Cost efficiency.
  - Security.
  - Simplicity of operation.
* Improving one goal often requires compromising another.

**3. Why are trade-offs unavoidable in system architecture?**
* Resources such as CPU, memory, storage, bandwidth, and engineering time are limited. 
* Optimizing one aspect (for example, faster reads) usually increases costs elsewhere (such as slower writes or additional storage).

**4. What is scalability?**
* Scalability is the ability of a system to maintain acceptable performance as workload grows by adding or reallocating 
resources.
* It is about **handling growth**, not simply supporting many users.

**5. Why is understanding the workload important?**
* Architecture should reflect how the system will actually be used.
* Important questions include:
  - Are reads more common than writes?
  - Is traffic steady or bursty?
  - How much data is stored?
  - What response time is expected?

**6. What characteristics define a workload?**
* Typical workload characteristics include:
  - Read/write ratio.
  - Query complexity.
  - Data volume.
  - Request rate.
  - Traffic spikes.
  - Access patterns.
  - Latency requirements.

**7. What is latency?**
* Latency measures how long one operation takes from request to completion.
* Examples:
  - Database query time.
  - API response time.
  - File read time.

**8. What is throughput?**
* Throughput measures how much work a system completes during a period of time.
* Examples:
  - Requests per second.
  - Transactions per second.
  - Messages processed per minute.

**9. How are latency and throughput different?**
* Latency measures the speed of one request.
* Throughput measures the total amount of completed work.
* A system may have:
  - Low latency but limited throughput.
  - High throughput with higher latency due to batching.

**10. Why is average latency not enough?**
* Average values hide slow requests.
* Percentiles (such as P95 or P99) reveal how slow the worst-performing requests are, which often has the greatest 
impact on user experience.

**11. What is tail latency?**
* Tail latency refers to the slowest fraction of requests.
* Example:
  - P99 latency is the response time below which 99% of requests complete.
* Tail latency is especially important in distributed systems because one slow component can delay an entire operation.

**12. What is reliability?**
* Reliability is the ability of a system to continue producing correct results despite failures or unexpected conditions.
* Failures may involve:
  - Hardware.
  - Software.
  - Networks.
  - Human error.

**13. What is fault tolerance?**
* Fault tolerance is the ability to continue operating after one or more components fail.
* Common techniques include:
  - Replication.
  - Automatic failover.
  - Retries.
  - Redundant hardware.

**14. What is availability?**
* Availability measures whether users can successfully access and use a system when needed.
* A highly available system minimizes downtime through redundancy and recovery mechanisms.

**15. What is maintainability?**
* Maintainability is the ease with which engineers can understand, modify, test, deploy, and operate a system over time.

**16. What design practices improve maintainability?**
* Examples include:
  - Modular architecture.
  - Clear interfaces.
  - Automation.
  - Good documentation.
  - Monitoring and observability.
  - Consistent coding standards.

**17. Why is complexity one of the biggest risks in architecture?**
* Excessive complexity increases:
  - Bugs.
  - Operational mistakes.
  - Development time.
  - Maintenance costs.
  - Difficulty onboarding new engineers.
* Reducing unnecessary complexity improves long-term sustainability.

**18. What role do abstractions play in system design?**
* Abstractions hide implementation details behind simpler interfaces, allowing components to evolve independently 
and reducing cognitive load for developers.

**19. Why are distributed systems more challenging than single-machine systems?**
* Distributed systems introduce additional concerns such as:
  - Network delays.
  - Partial failures.
  - Data replication.
  - Coordination.
  - Inconsistent views of state.
* These issues do not occur—or are much simpler—within a single process.

**20. Why should architectural decisions be driven by measurement?**
* Intuition is often misleading.
* Useful metrics include:
  - Latency percentiles.
  - Throughput.
  - Error rate.
  - CPU utilization.
  - Memory usage.
  - Disk I/O.
  - Network bandwidth.
* Measure first, optimize second.

**21. What is horizontal scaling?**
* Horizontal scaling increases capacity by adding more machines.
* Advantages:
  - Better fault tolerance.
  - Incremental growth.
  - Potentially lower cost.
* Challenges:
  - Coordination.
  - Replication.
  - Partitioning.
  - Network communication.

**22. What is vertical scaling?**
* Vertical scaling improves capacity by upgrading a single machine (more CPU, RAM, storage, or faster hardware).
* Advantages:
  - Simpler architecture.
  - Easier management.
* Limitations:
  - Hardware ceilings.
  - Potentially higher costs.
  - Single point of failure.

**23. When is horizontal scaling preferable to vertical scaling?**
* Horizontal scaling is often preferred when:
  - Workload continues to grow.
  - High availability is required.
  - Downtime must be minimized.
  - Single-machine limits are reached.

**24. Why is observability important?**
* Observability enables engineers to understand system behavior using metrics, logs, traces, and alerts. 
* It helps diagnose problems, validate assumptions, and improve performance.

**25. What is the key architectural mindset?**
* There is no universally optimal architecture.
* Effective system design begins by understanding the problem, identifying constraints, measuring performance, 
and selecting trade-offs that best satisfy the application's goals.

### Chapter Summary.

**Core Principles.**
- Data-intensive applications are primarily concerned with managing data.
- Every architectural decision involves trade-offs.
- Understanding the workload is essential before choosing technologies.
- Reliability, scalability, and maintainability are foundational qualities.
- Latency and throughput describe different aspects of performance.
- Tail latency often matters more than average latency.
- Distributed systems introduce unique coordination and failure challenges.
- Observability and measurement should guide optimization efforts.

### Memory Checklist.

**After studying this chapter, you should be able to answer:**
- [ ] What is a data-intensive application?
- [ ] What is an architectural trade-off?
- [ ] What makes a system scalable?
- [ ] Why is workload analysis important?
- [ ] Difference between latency and throughput?
- [ ] Why are latency percentiles more informative than averages?
- [ ] Difference between reliability and availability?
- [ ] What is fault tolerance?
- [ ] Why are distributed systems inherently more complex?
- [ ] What are horizontal and vertical scaling?
- [ ] Why is observability essential?
- [ ] Why should optimization be driven by measurement?

### Looking Ahead.

**The concepts introduced here underpin many later topics in distributed systems:**

| This Chapter    | Later Topics                                 |
|-----------------|----------------------------------------------|
| Trade-offs      | CAP theorem, consistency models              |
| Scalability     | Partitioning and sharding                    |
| Reliability     | Replication and fault recovery               |
| Latency         | Caching, indexes, query optimization         |
| Throughput      | Streaming, batching, asynchronous processing |
| Maintainability | Data models, APIs, schema evolution          |
| Observability   | Monitoring, tracing, debugging               |

* Understanding these foundational ideas will make later chapters on replication, transactions, distributed coordination, 
and stream processing much easier to grasp.

***

## Defining Nonfunctional Requirements.

**1. What are nonfunctional requirements (NFRs)?**
* Nonfunctional requirements describe **how well** a system should operate rather than **what** it should do. 
* They define qualities such as performance, reliability, scalability, security, and maintainability.

**2. How do functional and nonfunctional requirements differ?**
- **Functional requirements:** Specify system behavior and features.
- **Nonfunctional requirements:** Specify quality attributes and operational expectations.
* Example:
  - Functional: "Users can upload photos."
  - Nonfunctional: "Photo uploads complete within 2 seconds."

**3. Why are NFRs important?**
* NFRs guide architectural decisions, influence technology choices, define success criteria, and help prevent systems 
that work correctly but fail under real-world conditions.

**4. Why should NFRs be measurable?**
* Requirements like "fast" or "highly available" are subjective. 
* Measurable targets make them testable.
* Examples:
  - P99 latency < 200 ms.
  - 99.95% availability.
  - 5,000 requests/sec.

**5. What are common categories of NFRs?**
- Performance.
- Scalability.
- Reliability.
- Availability.
- Maintainability.
- Security.
- Durability.
- Cost efficiency.
- Observability.

**6. What is a Service Level Objective (SLO)?**
* An SLO is a measurable target for a system's behavior, such as uptime or latency, used to evaluate whether the system 
is meeting expectations.

**7. What is a Service Level Indicator (SLI)?**
* An SLI is the actual measurement used to evaluate an SLO.
* Examples:
  - Response time.
  - Error rate.
  - Availability.
  - Successful request percentage.

**8. Why should architects prioritize requirements?**
* No architecture can optimize every quality simultaneously. 
* Prioritization helps determine which trade-offs are acceptable.

**9. Why do business goals influence architecture?**
* Architecture exists to support business objectives. 
* The same technical solution may be inappropriate if business priorities differ (e.g., minimizing cost versus 
maximizing responsiveness).

**10. What is performance?**
* Performance describes how efficiently a system completes work. 
* It is commonly evaluated using latency, throughput, and resource utilization.

**11. What is scalability?**
* Scalability measures whether a system can maintain acceptable performance as workload increases through additional 
resources or architectural changes.

**12. Why isn't designing for maximum scale always the best choice?**
* Supporting extreme scale often increases cost and complexity. 
* Systems should be designed for expected growth rather than hypothetical maximums.

**13. What is reliability?**
* Reliability is the ability to consistently produce correct results despite failures, unexpected inputs, or changing 
operating conditions.

**14. What is durability?**
* Durability ensures that committed data survives failures such as crashes or power loss.
* Common techniques include:
  - Persistent storage.
  - Replication.
  - Backups.
  - Write-ahead logging.

**15. What is availability?**
* Availability measures the percentage of time a system is operational and accessible to users.
* Higher availability usually requires redundancy and automatic recovery.

**16. What is maintainability?**
* Maintainability measures how easily developers can understand, modify, test, deploy, and operate the system throughout 
its lifetime.

**17. What role does observability play?**
* Observability provides insight into system behavior through metrics, logs, traces, dashboards, and alerts, 
enabling faster diagnosis and continuous improvement.

**18. Why is security considered a nonfunctional requirement?**
* Security protects confidentiality, integrity, and availability of data while supporting authentication, authorization, 
* auditing, and compliance.

**19. Why should NFRs be considered early in system design?**
* Many architectural decisions are difficult or expensive to change later. 
* Defining NFRs early helps avoid costly redesigns.

**20. What trade-offs commonly arise between NFRs?**
* Examples include:
  - Performance vs cost.
  - Availability vs consistency.
  - Simplicity vs flexibility.
  - Security vs usability.
  - Scalability vs operational complexity.

**21. How do workload characteristics influence NFRs?**
* Different workloads emphasize different qualities.
* Examples:
  - Read-heavy systems prioritize read latency.
  - Analytics systems prioritize throughput.
  - Financial systems prioritize correctness and durability.

**22. Why should NFRs evolve over time?**
* As user behavior, traffic, regulations, and business goals change, quality expectations should also be reviewed and updated.

**23. How can teams validate that NFRs are met?**
* By using:
  - Load testing.
  - Stress testing.
  - Failure injection.
  - Monitoring.
  - Capacity planning.
  - Performance benchmarking.

**24. Why is capacity planning important?**
* Capacity planning estimates future resource needs based on expected growth, helping avoid both outages and unnecessary 
infrastructure costs.

**25. What is the key takeaway from defining nonfunctional requirements?**
* Clear, measurable, and prioritized nonfunctional requirements provide the foundation for architectural decisions. 
* They define what "success" means operationally and guide trade-offs throughout the system's lifecycle.

### Chapter Summary.

**Core Principles.**
- Nonfunctional requirements describe system quality attributes.
- Effective NFRs are measurable and testable.
- Business priorities determine which qualities matter most.
- Every quality attribute involves trade-offs.
- Architecture should be driven by explicit operational goals.
- Monitoring and testing are essential for validating NFRs.
- NFRs should evolve alongside the system and its workload.

### Memory Checklist.

**After studying this chapter, you should be able to answer:**

- [ ] What are nonfunctional requirements?
- [ ] How do they differ from functional requirements?
- [ ] Why must NFRs be measurable?
- [ ] What are SLIs and SLOs?
- [ ] Why are trade-offs unavoidable?
- [ ] Difference between reliability, durability, and availability?
- [ ] Why is maintainability important?
- [ ] How does workload influence NFRs?
- [ ] Why is observability essential?
- [ ] How can teams validate NFRs?

### Connections to Other Topics.

| This Chapter      | Related Concepts                          |
|-------------------|-------------------------------------------|
| Performance       | Query optimization, caching, indexing     |
| Scalability       | Sharding, partitioning, load balancing    |
| Reliability       | Replication, consensus, failure recovery  |
| Durability        | Logging, snapshots, backups               |
| Availability      | Redundancy, failover, distributed systems |
| Security          | Authentication, authorization, encryption |
| Observability     | Metrics, tracing, alerting                |
| Capacity Planning | Elastic scaling, cloud infrastructure     |

### Interview Practice.

1. Why should architects define nonfunctional requirements before selecting technologies?
2. How would you make a requirement like "the system should be fast" measurable?
3. Explain the difference between reliability, availability, and durability.
4. Give examples of trade-offs between performance, cost, and scalability.
5. What metrics would you monitor to verify that a production system meets its performance goals?

***

## Data Models and Query Languages.

**1. What is a data model?**
* A data model defines how data is structured, related, constrained, and manipulated within a system. 
* It provides the abstraction that applications use to interact with stored data.

**2. Why are data models important?**
* Data models determine:
  - How naturally data can be represented.
  - Which queries are efficient.
  - How easily schemas evolve.
  - How applications interact with storage.
  - Long-term maintainability.

**3. What are the major categories of data models?**
* Common categories include:
  - Relational.
  - Document.
  - Key-value.
  - Wide-column.
  - Graph.
  - Time-series (specialized).
  - Vector (specialized for AI/search).
* Each is optimized for different workloads.

**4. What is the relational data model?**
* The relational model stores data in tables consisting of rows and columns. 
* Relationships are represented using keys, and queries are typically expressed declaratively using SQL.

**5. What are the strengths of relational databases?**
- Strong consistency.
- Mature query optimization.
- Powerful joins.
- Declarative querying.
- Data integrity through constraints.
- Well-understood transactional behavior.

**6. What is a document data model?**
* A document model stores records as self-contained documents (often JSON-like) that naturally represent hierarchical 
or nested data.

**7. When is a document database a good fit?**
* When:
  - Data is naturally hierarchical.
  - Documents are mostly accessed together.
  - Schemas evolve frequently.
  - Joins are uncommon.

**8. What are the trade-offs between relational and document models?**
* Relational databases excel at highly connected data and joins.
* Document databases simplify hierarchical data and schema evolution but may duplicate information and make 
cross-document relationships harder to manage.

**9. What is schema-on-write?**
* Schema-on-write validates data before it is stored. 
* Every record must conform to a predefined structure.
* This approach is common in relational databases.

**10. What is schema-on-read?**
* Schema-on-read interprets data when it is retrieved rather than enforcing a strict structure during writes.
* This provides flexibility for evolving data formats.

**11. What is denormalization?**
* Denormalization intentionally duplicates data to improve read performance by reducing joins or lookups.
* The trade-off is increased storage and more complex updates.

**12. What is normalization?**
* Normalization organizes data into related tables to minimize duplication and maintain consistency.
* It simplifies updates but often requires joins during reads.

**13. What is a key-value data model?**
* A key-value store maps unique keys to values.
* It offers simple, fast lookups but supports limited querying beyond key-based access.

**14. What is a graph data model?**
* Graph databases represent data as nodes connected by relationships (edges), making them effective for highly 
interconnected data such as social networks or recommendation systems.

**15. When are graph databases preferable?**
* When queries frequently traverse relationships, such as:
  - Social networks.
  - Fraud detection.
  - Knowledge graphs.
  - Network topology.
  - Recommendation systems.

**16. What is a declarative query language?**
* A declarative language specifies **what** result is desired rather than **how** to compute it.
* The database optimizer determines the execution strategy.
* Example: SQL.

**17. What is an imperative query approach?**
* An imperative approach explicitly describes each processing step.
* Developers control the execution logic rather than relying on the database optimizer.

**18. Why are declarative query languages valuable?**
* They provide:
  - Simpler code.
  - Better optimization opportunities.
  - Hardware independence.
  - Easier maintenance.
  - More readable queries.

**19. What is query optimization?**
* Query optimization is the process of selecting an efficient execution plan for a query using available indexes, 
statistics, and access methods.

**20. Why do indexes matter?**
* Indexes speed up data retrieval by organizing data for efficient searching.
* Benefits:
  - Faster reads.
  - Lower query latency.
* Costs:
  - Additional storage.
  - Slower writes.
  - Maintenance overhead.

**21. Why is choosing the right data model important?**
* The data model affects:
  - Application complexity.
  - Performance.
  - Scalability.
  - Flexibility.
  - Ease of future changes.
* Changing models later can be expensive.

**22. Can multiple data models be used together?**
* Yes.
* Many systems use different databases for different purposes (polyglot persistence), selecting the most appropriate 
model for each workload.

**23. What is polyglot persistence?**
* Polyglot persistence is the practice of using multiple database technologies within the same system because no single 
database is optimal for every problem.

**24. What factors influence data model selection?**
* Consider:
  - Data relationships.
  - Query patterns.
  - Update frequency.
  - Scalability needs.
  - Consistency requirements.
  - Schema evolution.
  - Operational complexity.

**25. What is the key lesson of this chapter?**
* No data model is universally superior. 
* The best choice depends on the application's data, access patterns, consistency needs, and expected evolution.

## Chapter Summary.

### Core Principles.

- Data models shape how applications represent and access information.
- Different models optimize different workloads.
- Relational and document models solve different classes of problems.
- Normalization reduces redundancy; denormalization improves read performance.
- Declarative query languages allow databases to optimize execution.
- Indexes improve read performance but increase write cost.
- Polyglot persistence is common in modern architectures.

### Comparison Table.

| Model       | Best For                          | Strengths                        | Trade-offs                           |
|-------------|-----------------------------------|----------------------------------|--------------------------------------|
| Relational  | Structured business data          | Joins, consistency, SQL          | Rigid schema, joins may be expensive |
| Document    | Hierarchical records              | Flexible schema, nested data     | Limited joins, possible duplication  |
| Key-Value   | Fast lookups                      | Simplicity, performance          | Limited query capabilities           |
| Graph       | Connected data                    | Efficient relationship traversal | Less suited for tabular analytics    |
| Wide-Column | Large-scale distributed workloads | Horizontal scalability           | More complex data modeling           |

### Memory Checklist.

**After studying this chapter, you should be able to answer:**
- [ ] What is a data model?
- [ ] Why are data models important?
- [ ] Relational vs document databases?
- [ ] Schema-on-write vs schema-on-read?
- [ ] Normalization vs denormalization?
- [ ] What is a key-value store?
- [ ] When should you use a graph database?
- [ ] Declarative vs imperative queries?
- [ ] Why are indexes useful?
- [ ] What is polyglot persistence?
- [ ] How do query patterns influence data model selection?

### Connections to Other Topics.

| This Chapter         | Related Concepts                        |
|----------------------|-----------------------------------------|
| Relational model     | Transactions, ACID                      |
| Document model       | Schema evolution, JSON APIs             |
| Graph model          | Recommendation systems, fraud detection |
| Indexes              | Storage engines, query execution        |
| Declarative queries  | Query optimization                      |
| Denormalization      | Caching, materialized views             |
| Polyglot persistence | Microservices, distributed systems      |
| Schema design        | Data evolution and migrations           |

### Interview Practice.

1. How would you choose between a relational and a document database for a new application?
2. Explain normalization and denormalization with examples.
3. Why are declarative query languages generally preferred over imperative approaches?
4. When would a graph database outperform a relational database?
5. What are the trade-offs of adding indexes?
6. What is polyglot persistence, and when is it appropriate?
7. How do query patterns influence data model selection?
8. What factors should you consider before changing a system's data model?

***

## Chapter 4 – Storage and Retrieval

**1. Why is the storage engine one of the most important parts of a database?**
* The storage engine determines how data is written, read, indexed, cached, and recovered after failures. 
* It has a direct impact on throughput, latency, scalability, and durability.

**2. What does a database ultimately manage?**
* A database is fundamentally a system that organizes bytes on persistent storage so they can be efficiently written, 
found, updated, and recovered.

**3. What are the core responsibilities of a storage engine?**
* Store data reliably.
* Retrieve data efficiently.
* Support updates and deletes.
* Recover after crashes.
* Maintain indexes.
* Optimize disk and memory usage.

**4. What are the two dominant storage engine designs?**
1. Log-Structured Merge Trees (LSM Trees).
2. B-Tree (page-oriented storage).
* Each makes different trade-offs between reads, writes, and storage efficiency.

**5. Why is sequential I/O generally faster than random I/O?**
* Sequential operations minimize seek overhead and allow storage devices to process data continuously. 
* Random I/O requires jumping between locations, reducing throughput.

**6. What is the central idea behind append-only storage?**
* Instead of modifying existing data, every update is appended to the end of the log. 
* Old versions remain until background cleanup removes them.

**7. What are the benefits of append-only writes?**
* High write throughput.
* Simpler recovery.
* Reduced corruption risk.
* Better sequential disk access.
* Historical versions remain temporarily available.

**8. What major problem appears if data is only appended?**
* Old versions accumulate indefinitely, wasting storage and making reads slower.

**9. What is compaction?**
* A background process that merges files while removing obsolete, overwritten, or deleted records.

**10. Why perform compaction?**
* To:
  * Reclaim storage.
  * Reduce duplicate records.
  * Improve read performance.
  * Decrease the number of files.

**11. What is an SSTable?**
* A Sorted String Table is an immutable file whose keys are stored in sorted order, making searching and merging efficient.

**12. Why are SSTables immutable?**
* Immutability simplifies concurrency, recovery, replication, and compaction because files never change after creation.

**13. Why use a MemTable?**
* Writing into memory is much faster than disk. 
* The MemTable accumulates sorted writes before flushing them to disk.

**14. Describe the LSM write path.**
1. Write to WAL.
2. Insert into MemTable.
3. MemTable fills.
4. Flush to SSTable.
5. Background compaction.

**15. Why write to the WAL before memory?**
* If power fails before flushing the MemTable, the WAL can replay recent operations during recovery.

**16. How does an LSM database find a key?**
* Search:
  1. MemTable.
  2. Newest SSTable.
  3. Progressively older SSTables.
* The newest value wins.

**17. Why use Bloom filters?**
* They quickly determine whether a file definitely does not contain a key, avoiding unnecessary disk reads.

**18. Can Bloom filters return incorrect results?**
* They may produce false positives but never false negatives.

**19. Why keep keys sorted?**
* Sorting enables:
  * Binary search.
  * Efficient range scans.
  * Efficient merges.
  * Smaller indexes.

**20. When are LSM Trees the best choice?**
* When workloads involve:
  * Frequent inserts.
  * High write throughput.
  * Log ingestion.
  * Event streaming.
  * Time-series data.

**21. What are common LSM drawbacks?**
* Expensive compactions.
* Read amplification.
* Write amplification.
* Temporary latency spikes.

**22. What is a B-Tree?**
* A balanced search tree storing keys inside fixed-size pages optimized for efficient lookup and updates.

**23. Why divide storage into pages?**
* Reading entire pages minimizes disk operations and aligns with operating system caching.

**24. How does a B-Tree locate a record?**
* Start at the root and follow child pointers until reaching the leaf containing the desired key.

**25. How are updates performed?**
* The page containing the key is modified in place. 
* If full, the page splits to preserve balance.

**26. Why must B-Trees remain balanced?**
* Balance guarantees lookup complexity remains approximately `O(log n)`.

**27. Why do page splits occur?**
* When a page becomes full, it divides into two pages and the parent index is updated.

**28. Why are B-Tree writes considered random?**
* Updates may occur anywhere in the file instead of only at the end.

**29. What workloads favor B-Trees?**
* Predictable latency.
* Point lookups.
* Transactional workloads.
* Mixed read/write applications.

**30. What disadvantages do B-Trees have?**
* Random disk writes.
* Fragmentation.
* Page management complexity.
* Higher update cost.

**31. What is a primary index?**
* An index built on the primary key that uniquely identifies each record.

**32. What is a secondary index?**
* An additional index on non-primary attributes to accelerate alternative query patterns.

**33. Clustered vs Non-Clustered What is the difference?**
**Clustered index.**
* Data stored in index order.

**Non-clustered index.**
* Index stores pointers to data elsewhere.

**34. When is a hash index useful?**
* For exact key lookups.
* It is generally poor for range queries because hash values are unordered.

**35. Why are B-Trees excellent for range queries?**
* Neighboring keys are physically adjacent, allowing efficient sequential traversal.

**36. What is read amplification?**
* One logical read requires accessing multiple physical files or pages.
* Lower is better.

**37. What is write amplification?**
* Writing one logical record causes multiple physical writes due to page rewrites or compaction.

**38. What is space amplification?**
* The database temporarily consumes more storage than the logical dataset because duplicate or obsolete data exists.

**39. How should you choose between storage engines?**
* Choose **LSM Trees** when:
  * Writes dominate.
  * Ingestion is continuous.
  * Storage efficiency matters.
* Choose **B-Trees** when:
  * Reads dominate.
  * Latency must be predictable.
  * Transactional workloads are common.

**40. What are the biggest lessons from this chapter?**
* Storage engines determine database performance.
* Sequential writes are generally more efficient than random writes.
* LSM Trees trade background compaction for fast writes.
* B-Trees trade random updates for predictable reads.
* WAL ensures durability.
* Bloom filters reduce unnecessary disk access.
* Read, write, and space amplification are fundamental trade-offs.
* There is no universally superior storage engine—selection depends on workload characteristics.

### One-Minute Mental Model
* If you can remember only one diagram from this chapter, remember this:
```
                 Storage Engines
                       │
          ┌────────────┴────────────┐
          │                         │
      LSM Tree                  B-Tree
          │                         │
 Append-only log             In-place updates
          │                         │
 Sequential writes          Random page writes
          │                         │
   MemTable + SSTables       Fixed-size pages
          │                         │
 Background compaction      Page split/merge
          │                         │
 Excellent writes          Excellent reads
          │                         │
 Higher read amp           Predictable latency
```

***

### Chapter 5 – Encoding and Evolution.

**1. Why do applications encode data before storing or transmitting it?**
* Encoding converts in-memory objects into a portable representation that can be stored on disk or transmitted over a network. 
* Another application or a future version of the same application can later reconstruct the data.

**2. What is serialization and deserialization?**
- **Serialization (encoding):** Convert an object into bytes.
- **Deserialization (decoding):** Reconstruct an object from bytes.

**3. Why can't an application simply write its memory to disk?**
* Memory layouts depend on programming language, compiler, CPU architecture, and runtime implementation. 
* They are not portable between systems.

**4. What is a schema?**
* A schema defines the structure, field names, types, and constraints of encoded data so producers and consumers 
interpret data consistently.

**5. Why must schemas evolve?**
* Applications change over time by:
  - Adding fields.
  - Removing fields.
  - Renaming fields.
  - Changing business requirements.
* Systems must continue working during these changes.

**6. What is schema evolution?**
* Schema evolution is the ability to change a data format while maintaining compatibility with existing data and applications.

**7. What is backward compatibility?**
* New software can read data written by older software.
* Example:
  * Version 2 reads Version 1 data.

**8. What is forward compatibility?**
* Older software can safely read data produced by newer software, often by ignoring fields it doesn't understand.

**9. Why is compatibility critical in distributed systems?**
* Different services are often upgraded independently.
* Multiple versions may run simultaneously, making compatibility essential for uninterrupted operation.

**10. What are common serialization formats?**
- JSON.
- XML.
- CSV.
- Protocol Buffers.
- Avro.
- Thrift.

**11. Why is JSON widely used?**
* Because it is:
  - Human-readable.
  - Language-independent.
  - Easy to debug.
  - Supported by nearly every platform.

**12. What limitations does JSON have?**
- Verbose.
- Larger messages.
- Weak typing.
- Ambiguous numeric types.
- Slower parsing.

**13. Why use binary formats instead of text formats?**
* Binary formats are usually:
  - Smaller.
  - Faster to parse.
  - Strongly typed.
  - More efficient for networking.

**14. What is the main idea behind Protocol Buffers?**
* A schema defines numbered fields, allowing compact binary encoding and compatibility across versions.

**15. Why are field numbers important in Protocol Buffers?**
* Field numbers—not names—identify data in the binary format, allowing field names to change without affecting compatibility.

**16. Why should deleted field numbers remain reserved?**
* Reusing them could cause old data to be interpreted incorrectly.

**17. How does Avro differ from Protocol Buffers?**
* Avro stores minimal type information in the encoded data and relies on schemas known by both sender and receiver.

**18. What is the writer schema?**
* The schema used when data was originally encoded.

**19. What is the reader schema?**
* The schema expected by the application reading the data.

**20. How does Avro support schema evolution?**
* It compares the writer schema and reader schema, automatically matching fields and applying defaults when appropriate.

**21. Why are default values important?**
* When new fields are added, older data won't contain them. 
* Defaults allow the reader to create valid objects.

**22. Why are optional fields useful?**
* They allow older producers to omit fields while newer consumers still process the data.

**23. How should applications handle unknown fields?**
* They should ignore them instead of failing, improving forward compatibility.

**24. Is removing a field always safe?**
* No. 
* It is safe only when no active consumers depend on that field.

**25. Why is changing a field's type risky?**
* Different applications may interpret the data differently, causing decoding failures or incorrect results.

**26. What is a data contract?**
* An agreement between producers and consumers describing the format and meaning of exchanged data.

**27. Why must APIs evolve carefully?**
* Clients may not upgrade immediately, so breaking changes can disrupt existing integrations.

**28. How is schema evolution different for databases?**
* Old records remain stored for years, so newer software must often read multiple historical schema versions.

**29. What is a rolling upgrade?**
* Replacing application instances gradually while old and new versions run simultaneously.

**30. Why is compatibility essential during rolling upgrades?**
* Because different versions exchange data before the upgrade is complete.

**31. What is Remote Procedure Call (RPC)?**
* A mechanism where one program invokes a function running in another process or machine as if it were local.

**32. Why is RPC fundamentally different from local function calls?**
* Remote calls involve:
  - Network latency.
  - Partial failures.
  - Retries.
  - Serialization.
  - Version compatibility.

**33. Why do many distributed systems prefer messaging?**
* Messaging decouples producers and consumers, improving scalability and resilience.

**34. What is version tolerance?**
* The ability of software to correctly process data from multiple schema versions.

**35. What is self-describing data?**
* Data that includes enough metadata (such as field names or schema identifiers) to help decode itself.

**36. How do binary formats improve typing?**
* Schemas explicitly define field types, reducing ambiguity during decoding.

**37. What changes are usually safe?**
* Generally safe:
  - Add optional fields.
  - Provide defaults.
  - Ignore unknown fields.
  - Reserve deleted identifiers.

**38. Which schema changes are most dangerous?**
- Removing required fields.
- Changing field meaning.
- Reusing identifiers.
- Incompatible type changes.

**39. How should you choose a serialization format?**
* Consider:
  - Performance.
  - Interoperability.
  - Schema support.
  - Tooling.
  - Readability.
  - Long-term compatibility.

**40. What are the key lessons from this chapter?**
- Data must be encoded before storage or transmission.
- Schemas define the structure of encoded data.
- Schemas inevitably evolve over time.
- Backward and forward compatibility enable independent deployments.
- Binary formats often provide better efficiency and stronger schema support than text formats.
- Good schema design minimizes breaking changes and supports long-lived distributed systems.

### One-Minute Mental Model.

```text
                Application Object
                       │
                Serialize (Encode)
                       │
        ┌──────────────┼──────────────┐
        │              │              │
      JSON         Protobuf        Avro
        │              │              │
     Store / Send Across Network
                       │
                Deserialize (Decode)
                       │
              Application Object

           Schema Evolution

Old Producer ─────────────► New Consumer
        (Backward Compatible)

New Producer ─────────────► Old Consumer
         (Forward Compatible)

Goal:
Allow systems running different software versions
to exchange data safely during upgrades.
```

***

## Chapter 6 – Replication.

**1. What is replication in a distributed system?**
* Replication is the practice of maintaining multiple copies of the same data on different machines. 
* It improves availability, fault tolerance, read scalability, and disaster recovery.

**2. What problems does replication solve?**
* Replication helps:
  - survive machine failures.
  - increase read throughput.
  - reduce latency for geographically distributed users.
  - improve availability during maintenance.

**3. How is replication different from partitioning?**
- **Replication** stores the same data on multiple nodes.
- **Partitioning** distributes different subsets of data across nodes.
- Many distributed databases use both techniques together.

**4. What is primary-replica replication?**
* One node (the primary) accepts writes. 
* Replica nodes receive updates from the primary and apply them locally.

**5. What responsibilities does the primary node have?**
* The primary:
  - Processes writes.
  - Determines update order.
  - Records changes.
  - Distributes updates to replicas.

**6. What do replica nodes do?**
* Replicas:
  - Receive updates.
  - Apply changes.
  - Answer read requests.
  - Take over if promoted after failure.

**7. What is synchronous replication?**
* The primary waits until one or more replicas acknowledge a write before confirming success to the client.

**8. Why use synchronous replication?**
* It reduces the chance of data loss because replicas confirm they have stored the write before it is acknowledged.

**9. What are the disadvantages of synchronous replication?**
- Higher write latency.
- Slower performance.
- Reduced availability if replicas become unreachable.

**10. What is asynchronous replication?**
* The primary acknowledges writes immediately and sends updates to replicas afterward.

**11. Why is asynchronous replication popular?**
* It provides:
  - Low write latency.
  - High throughput.
  - Better availability during network issues.

**12. What is the main risk of asynchronous replication?**
* Recent writes may be lost if the primary fails before replicas receive them.

**13. What is replication lag?**
* The delay between a write on the primary and the moment replicas apply that write.

**14. What can increase replication lag?**
- Network delays.
- Overloaded replicas.
- Slow disks.
- Long-running transactions.
- High write volume.

**15. How does replication improve read performance?**
* Read requests can be distributed among multiple replicas, reducing load on the primary.

**16. What is read-after-write consistency?**
* A client should immediately see its own successful write in subsequent reads.

**17. Why can reading from replicas be problematic?**
* If replication lag exists, replicas may return stale data.

**18. How can systems provide read-after-write consistency?**
* Examples include:
  - Reading from the primary after writes.
  - Waiting until replicas catch up.
  - Directing a user's requests to a sufficiently up-to-date replica.

**19. What are monotonic reads?**
* A client should never observe data moving backward in time across successive reads.

**20. What are consistent prefix reads?**
* Readers should observe writes in the same order they occurred, preventing impossible event sequences.

**21. What is statement-based replication?**
* The primary sends executed database statements for replicas to run.

**22. Why can statement-based replication be unreliable?**
* Some statements produce different results depending on execution time, randomness, or external state.

**23. What is WAL replication?**
* Replicas replay the storage engine's write-ahead log to reproduce the same changes.

**24. Why is WAL replication efficient?**
* It closely mirrors storage changes, reducing translation overhead and preserving write order.

**25. What is logical replication?**
* Replication transmits logical row changes rather than storage engine internals.

**26. Why choose logical replication?**
* It enables:
  - Selective replication.
  - Schema evolution.
  - Version upgrades.
  - Integration with downstream systems.

**27. What is Change Data Capture?**
* CDC records database changes so other systems can react to inserts, updates, and deletes.

**28. Where is CDC commonly used?**
- Analytics pipelines.
- Search indexing.
- Caches.
- Event streaming.
- Data warehouses.

**29. What happens when the primary fails?**
* A replica is promoted to become the new primary, and clients redirect writes to it.

**30. Why is failover difficult?**
* Challenges include:
  - Detecting failures.
  - Avoiding split-brain.
  - Preventing lost writes.
  - Redirecting clients safely.

**31. What is split-brain?**
* Two nodes both believe they are the primary and accept conflicting writes.

**32. What is multi-leader replication?**
* Multiple nodes accept writes independently and replicate updates to one another.

**33. When is multi-leader replication useful?**
- Geographically distributed systems.
- Collaborative editing.
- Occasionally connected applications.

**34. Why is conflict resolution necessary in multi-leader systems?**
* Different leaders may update the same data concurrently, creating conflicting versions.

**35. What is leaderless replication?**
* Any replica can accept reads or writes. 
* Clients coordinate requests across multiple replicas.

**36. What is a quorum?**
* A quorum is the minimum number of replicas that must participate in a read or write operation to satisfy consistency 
requirements.

**37. How do read and write quorums improve consistency?**
* If read and write quorums overlap, reads are more likely to observe the latest completed write.

**38. What is eventual consistency?**
* If updates stop, all replicas will eventually converge to the same state.

**39. How should replication strategies be selected?**
* Consider:
  - Latency requirements.
  - Write throughput.
  - Availability goals.
  - Geographic distribution.
  - Acceptable consistency guarantees.

**40. What are the most important lessons about replication?**
- Replication improves availability, scalability, and fault tolerance.
- Primary-replica replication is the most common architecture.
- Synchronous replication favors durability; asynchronous replication favors performance.
- Replication lag can lead to stale reads.
- Read-after-write, monotonic reads, and consistent prefix reads improve user experience.
- WAL, logical replication, and CDC each have different trade-offs.
- Multi-leader and leaderless replication increase write availability but require conflict management.
- Every replication strategy balances consistency, availability, and performance.

### One-Minute Mental Model.

```text
                  Client
                     │
               Write Request
                     │
              +--------------+
              |   Primary    |
              +--------------+
                     │
          Replicate Updates
          ┌──────────┴──────────┐
          │                     │
   +--------------+      +--------------+
   |  Replica A   |      |  Replica B   |
   +--------------+      +--------------+
          │                     │
        Reads                Reads

Replication Models
────────────────────────────────────────────

Primary-Replica
  ✔ Simple
  ✔ Common
  ✔ Easy conflict handling

Multi-Leader
  ✔ Multiple writers
  ✔ Better regional writes
  ✘ Conflict resolution required

Leaderless
  ✔ High availability
  ✔ Quorum-based
  ✘ More complex consistency model

Trade-offs
────────────────────────────────────────────
Synchronous → Stronger durability, higher latency
Asynchronous → Better performance, possible lag
```

***

## Chapter 7 – Sharding (Partitioning).

**1. What is sharding?**
* Sharding (also called partitioning) divides a large dataset into smaller, independent subsets called **shards**, 
which are stored across multiple machines.
* Goals:
  - Scale storage.
  - Scale throughput.
  - Reduce load per server.

**2. Why can't we simply keep adding hardware to one database server?**
* A single server eventually reaches limits in:
  - CPU.
  - Memory.
  - Disk capacity.
  - Network bandwidth.
* Sharding allows horizontal scaling by distributing data across many servers.

**3. How does sharding differ from replication?**
**Replication:**
- Multiple copies of the same data.
- Improves availability and read scalability.

**Sharding:**
- Different subsets of data.
- Improves storage capacity and write scalability.

* Large systems commonly use both.

**4. What characteristics should a shard key have?**
* A good shard key:
  - Distributes data evenly.
  - Avoids hotspots.
  - Matches query patterns.
  - Rarely changes.

**5. What is the difference between horizontal and vertical partitioning?**
**Horizontal partitioning:**
- Split rows across machines.

**Vertical partitioning:**
- Split columns or services by functionality.

* Sharding usually refers to horizontal partitioning.

**6. How does range-based sharding work?**
* Each shard owns a range of key values.
* Example:
```
Shard A: 1–999
Shard B: 1000–1999
Shard C: 2000–2999
```

**7. What are the advantages of range-based sharding?**
- Efficient range queries.
- Simple implementation.
- Ordered data.
- Good for time-series workloads.

**8. What problems can occur with range sharding?**
* If new data falls into one range, a single shard may receive nearly all writes, creating a hotspot.

**9. How does hash-based sharding work?**
* A hash function transforms the shard key into a value that determines which shard stores the record.

**10. Why use hash-based sharding?**
* It distributes data more evenly and reduces the likelihood of hotspots.

**11. What is the major downside of hash-based sharding?**
* Range queries become inefficient because neighboring keys are likely stored on different shards.

**12. What is directory-based sharding?**
* A lookup service maps each key (or key range) to the shard responsible for it.

**13. Why use a shard directory?**
* It allows flexible data placement and easier shard migration without changing the shard key.

**14. What challenges does a shard directory introduce?**
- Additional infrastructure.
- Potential bottleneck.
- Need for high availability.
- Cache invalidation complexity.

**15. What is a hotspot?**
* A hotspot occurs when one shard receives significantly more traffic than others, becoming the system bottleneck.

**16. What commonly causes hotspots?**
- Sequential IDs.
- Current timestamps.
- Popular users.
- Viral content.
- Uneven key distribution.

**17. Why is load balancing important?**
* Balanced shards maximize hardware utilization and prevent individual nodes from becoming overloaded.

**18. What is shard rebalancing?**
* Moving data between shards to restore an even distribution after capacity changes or growth.

**19. When should data be rebalanced?**
- New servers added.
- Existing servers removed.
- Uneven data growth.
- Persistent hotspots.

**20. What should a good rebalancing process achieve?**
- Move minimal data.
- Avoid downtime.
- Keep load balanced.
- Preserve availability.

**21. What problem does consistent hashing solve?**
* It minimizes the amount of data that must move when nodes are added or removed.

**22. Why use virtual nodes?**
* Each physical server owns multiple positions in the hash space, improving load distribution and simplifying scaling.

**23. How does a client know which shard to contact?**
* Possible approaches:
  - Client-side routing.
  - Router/coordinator node.
  - Directory service.

**24. What is a scatter/gather query?**
* A request sent to multiple or all shards, with results combined before returning to the client.

**25. Why are scatter/gather queries expensive?**
* They increase:
  - Network traffic.
  - Latency.
  - CPU usage.
  - Coordination overhead.

**26. Why are cross-shard operations difficult?**
* Data lives on different machines, requiring distributed coordination and communication.

**27. Why are joins across shards expensive?**
* Rows must be transferred across the network before the join can be performed.

**28. Why are distributed transactions more complex?**
* Multiple shards must coordinate commits, increasing latency and the chance of failures.

**29. Why are secondary indexes harder in sharded databases?**
* The indexed attribute may not match the shard key, requiring coordination across shards.

**30. What is a local secondary index?**
* Each shard maintains its own index for the data it stores.

**31. What is a global secondary index?**
* An index spanning all shards, enabling efficient lookups but requiring synchronization across the cluster.

**32. What should influence shard key selection?**
* Consider:
  - Data distribution.
  - Query patterns.
  - Growth rate.
  - Access frequency.
  - Future scalability.

**33. Why should shard keys rarely change?**
* Changing a shard key often requires moving data to another shard, which is expensive and disruptive.

**34. What is data locality?**
* Keeping related data together to reduce cross-shard communication and improve performance.

**35. Why shard by geographic region?**
* Benefits include:
  - Lower latency.
  - Compliance with regional regulations.
  - Reduced cross-region traffic.

**36. What new challenges does sharding introduce?**
- Operational complexity.
- Data movement.
- Distributed queries.
- Monitoring.
- Failure handling.

**37. What metrics should be monitored?**
- Storage utilization.
- CPU.
- Memory.
- Network.
- Query latency.
- Request rate.
- Hotspots.

**38. How should a sharding strategy handle growth?**
* It should allow:
  - Adding nodes.
  - Rebalancing efficiently.
  - Minimal downtime.
  - Predictable performance.

**39. What trade-offs exist when choosing a sharding strategy?**

| Strategy  | Strength           | Weakness             |
|-----------|--------------------|----------------------|
| Range     | Fast range scans   | Hotspots             |
| Hash      | Even distribution  | Poor range queries   |
| Directory | Flexible placement | Extra infrastructure |

**40. What are the key lessons about sharding?**
- Sharding enables horizontal scaling by dividing data across machines.
- A well-chosen shard key is critical for performance.
- Range, hash, and directory-based sharding each involve trade-offs.
- Rebalancing and hotspot mitigation are ongoing operational concerns.
- Cross-shard queries, joins, indexes, and transactions add complexity.
- Good sharding design balances scalability, performance, and maintainability.

### One-Minute Mental Model.

```text
                 Client
                    │
             Route by Shard Key
                    │
      ┌─────────────┼─────────────┐
      │             │             │
   Shard A       Shard B       Shard C
   Users A-H     Users I-P     Users Q-Z

Partitioning Strategies
────────────────────────────────────

Range
A ─────► Z
✔ Range queries
✘ Hotspots

Hash
hash(key) → shard
✔ Even distribution
✘ Poor range scans

Directory
Lookup table → shard
✔ Flexible placement
✘ Extra coordination

Key Ideas
────────────────────────────────────

• Choose a stable shard key.
• Balance load across shards.
• Minimize cross-shard operations.
• Plan for growth and rebalancing.
• Replication + Sharding are complementary.
```

***

## Chapter 8 – Transactions.

**1. What is a transaction?**
* A transaction is a sequence of operations treated as a single logical unit of work. 
* It either completes successfully as a whole or has no effect at all.

**2. What problems do transactions solve?**
* Transactions protect data from becoming inconsistent when failures or concurrent operations occur.
* They simplify application development by providing correctness guarantees.

**3. What does ACID stand for?**
- Atomicity.
- Consistency.
- Isolation.
- Durability.

* These properties describe the guarantees a transaction system aims to provide.

**4. What is atomicity?**
* Atomicity means all operations in a transaction succeed together, or all are rolled back.
* There are no partial updates.

**5. What does consistency mean in ACID?**
* A transaction should preserve the application's rules and invariants, moving the database from one valid state to another.
* Consistency is largely the responsibility of application logic plus database constraints.

**6. What is isolation?**
* Isolation ensures concurrent transactions do not interfere in ways that produce incorrect results.
* Each transaction behaves as though it executed alone, according to the chosen isolation level.

**7. What is durability?**
* Once a transaction commits, its changes survive crashes and power failures through persistent storage and recovery 
mechanisms.

**8. What happens when a transaction commits?**
* The database permanently records the transaction's changes and makes them visible according to its consistency model.

**9. What is rollback?**
* Rollback discards all changes made by a transaction that cannot complete successfully.

**10. Why is concurrency challenging?**
* Multiple users may access or modify the same data simultaneously, leading to conflicts if not coordinated.

**11. What is a race condition?**
* A race condition occurs when the final result depends on the unpredictable timing of concurrent operations.

**12. What is a lost update?**
* Two transactions modify the same value, and one update overwrites the other without incorporating it.

**13. What is a dirty read?**
* A transaction reads data written by another transaction that has not yet committed.
* If the writer rolls back, the reader has observed invalid data.

**14. What is a dirty write?**
* One transaction overwrites data modified by another transaction before it commits.
* Most databases prevent dirty writes.

**15. What is a non-repeatable read?**
* A transaction reads the same row twice and gets different values because another committed transaction modified it.

**16. What is a phantom read?**
* Repeating a query returns a different set of rows because another transaction inserted or deleted matching rows.

**17. Why do databases provide multiple isolation levels?**
* Higher isolation provides stronger correctness but often reduces concurrency and performance.

**18. What does Read Committed guarantee?**
* Transactions only read committed data, preventing dirty reads while allowing other anomalies.

**19. What does Repeatable Read guarantee?**
* Rows already read remain stable during the transaction, reducing non-repeatable reads.

**20. What is Serializable isolation?**
* The strongest isolation level.
* Concurrent transactions produce results equivalent to some sequential execution.

**21. Why isn't Serializable always used?**
* It can reduce throughput because more coordination, validation, or blocking is required.

**22. What is locking?**
* Locking temporarily prevents conflicting operations from accessing the same data simultaneously.

**23. What is a shared lock?**
* Multiple transactions may read simultaneously, but writes are blocked.

**24. What is an exclusive lock?**
* Only one transaction may modify the protected data.
* Other readers or writers wait.

**25. What is a deadlock?**
* Two or more transactions wait forever because each holds a lock needed by another.

**26. How are deadlocks resolved?**
* The database detects the cycle and aborts one transaction so the others can continue.

**27. What is optimistic concurrency control?**
* Transactions proceed without locking and validate for conflicts before committing.

**28. When is optimistic concurrency effective?**
* When conflicts are rare and most transactions complete without interference.

**29. What is pessimistic concurrency control?**
* Transactions acquire locks before modifying data, assuming conflicts are likely.

**30. What is MVCC?**
* MVCC stores multiple versions of data so readers can access a consistent snapshot without blocking writers.

**31. Why is MVCC widely used?**
* It increases concurrency by allowing reads and writes to proceed simultaneously in many situations.

**32. What is snapshot isolation?**
* Each transaction reads a consistent snapshot of the database taken at the transaction's start.

**33. What is write skew?**
* Two concurrent transactions read the same data, make different decisions, and update different rows, violating a business rule.

**34. What is read skew?**
* A transaction observes data from different points in time, producing an inconsistent view.

**35. Why can long-running transactions be problematic?**
* They retain locks or old versions longer, increasing resource usage and reducing concurrency.

**36. What is a distributed transaction?**
* A transaction spanning multiple databases, shards, or services that must coordinate commits.

**37. What is Two-Phase Commit?**
* A protocol that asks participants to prepare first, then either commit or abort together.

**38. Why are distributed transactions difficult?**
* They increase latency, coordination costs, and sensitivity to network failures and unavailable participants.

**39. How should an isolation level be selected?**
* Balance:
  - Correctness requirements.
  - Acceptable anomalies.
  - Latency.
  - Throughput.
  - Workload characteristics.
* Use the weakest level that still preserves application correctness.

**40. What are the key lessons from this chapter?**
- Transactions simplify reasoning about concurrent operations.
- ACID describes important transactional guarantees.
- Isolation prevents many concurrency anomalies but comes with performance trade-offs.
- MVCC enables high read concurrency.
- Locking and optimistic techniques represent different approaches to conflict management.
- Serializable isolation offers the strongest guarantees but may reduce throughput.
- Distributed transactions provide atomicity across systems but are operationally expensive.

### One-Minute Mental Model.

```text
                Transaction

      BEGIN
         │
   Read / Write Data
         │
   Concurrency Control
         │
 ┌──────────────┬──────────────┐
 │              │              │
Locking       MVCC      Optimistic CC
 │              │              │
Prevent      Snapshot      Validate
Conflicts     Reads       at Commit
         │
      COMMIT
         │
   Changes Become Durable
         │
     or ROLLBACK

Common Anomalies
────────────────────────────────

Lost Update
Two writers overwrite each other.

Dirty Read
Read uncommitted data.

Non-Repeatable Read
Same row changes during a transaction.

Phantom Read
Query returns different rows.

Write Skew
Concurrent updates violate an invariant.

Trade-offs
────────────────────────────────

Higher Isolation
✔ Stronger correctness
✘ Lower concurrency

Lower Isolation
✔ Better performance
✘ More anomalies
```

***

## Chapter 9 – The Trouble with Distributed Systems.

**1. Why are distributed systems fundamentally harder than single-node systems?**
* Distributed systems must cope with uncertainty:
  - Machines fail independently.
  - Networks are unreliable.
  - Messages can be delayed or lost.
  - Clocks are imperfect.
  - Components cannot instantly know the state of other components.

**2. What is a partial failure?**
* A partial failure occurs when some components fail while others continue operating.
* Unlike a single computer crash, the overall system remains partially functional, making diagnosis and recovery more complex.

**3. Why shouldn't distributed systems assume reliable communication?**
* Networks may experience:
  - Packet loss.
  - Delays.
  - Congestion.
  - Reordering.
  - Temporary disconnections.
* Applications must tolerate these conditions.

**4. What is network latency?**
* Latency is the time required for a message to travel from sender to receiver.
* It varies depending on distance, routing, congestion, and hardware.

**5. Why is variable network latency difficult to handle?**
* A delayed response may look identical to a failed machine.
* Without additional information, it's impossible to know whether a node is slow or unavailable.

**6. Why do systems use timeouts?**
* A timeout limits how long a process waits for a response before assuming something has gone wrong.

**7. Why is choosing a timeout difficult?**
* If too short:
  - Healthy nodes appear failed.
* If too long:
  - Failures are detected slowly.
* Timeouts balance responsiveness and false alarms.

**8. What is a network partition?**
* A partition occurs when groups of machines cannot communicate, even though they continue running.
* Each side may believe the other has failed.

**9. Why are network partitions challenging?**
* Applications must decide whether to:
  - Continue serving requests with incomplete information.
  - Reject requests until communication is restored.

**10. How can a node fail?**
* Examples include:
  - Power loss.
  - Hardware failure.
  - Software crash.
  - Resource exhaustion.
  - Forced restart.

**11. Why is it difficult to distinguish a crashed node from a slow node?**
* Both may fail to respond before a timeout expires.
* Observers cannot reliably determine the cause from the network alone.

**12. What is failure detection?**
* Failure detection estimates whether another node is functioning.
* It is based on observations and timeouts rather than certainty.

**13. Why is perfect failure detection impossible?**
* Because message delays are unpredictable, a healthy node may simply respond later than expected.

**14. What is a Byzantine failure?**
* A component behaves arbitrarily or maliciously, sending incorrect or inconsistent information instead of simply stopping.

**15. When should systems consider Byzantine failures?**
* They are uncommon in most internal data centers but important in environments involving untrusted participants, 
such as some blockchain systems.

**16. What is a common misconception about distributed systems?**
* Assuming that:
  - The network is reliable.
  - Latency is zero.
  - Bandwidth is unlimited.
  - Topology never changes.
* These assumptions rarely hold in practice.

**17. Why do distributed systems use clocks?**
* Clocks help:
  - Order events.
  - Measure durations.
  - Expire data.
  - Coordinate operations.
* However, clocks are imperfect.

**18. What is a wall clock?**
* A wall clock represents real-world calendar time.
* It may jump forward or backward due to synchronization adjustments.

**19. What is a monotonic clock?**
* A monotonic clock only moves forward.
* It is preferred for measuring elapsed time.

**20. What is clock drift?**
* Different machines' clocks naturally diverge over time due to hardware differences.

**21. How are clocks synchronized?**
* Protocols periodically adjust clocks using reference time sources.
* Synchronization improves accuracy but never guarantees perfect agreement.

**22. Why are timestamps unreliable for ordering events?**
* Two machines may assign timestamps that do not reflect the true order of events due to clock skew.

**23. What is clock skew?**
* Clock skew is the difference between two machines' current clock values.

**24. What is logical time?**
* Logical time orders events according to causality rather than physical timestamps.

**25. What does "happens-before" mean?**
* If one event causally influences another, the first is said to happen before the second.

**26. What are concurrent events?**
* Two events are concurrent if neither causally depends on the other.

**27. Why is event ordering important?**
* Correct ordering ensures systems interpret updates consistently and avoid conflicting states.

**28. What is an idempotent operation?**
* Repeating the operation produces the same result as performing it once.
* Example:
  * Setting a value to 10 is idempotent.
  * Incrementing by 1 is not.

**29. Why design idempotent operations?**
* Retries become safe because duplicate requests do not corrupt data.

**30. Why are retries common?**
* Temporary failures often disappear after a short delay.
* Retries improve reliability when operations are safe to repeat.

**31. Why must distributed systems handle duplicate messages?**
* Networks or clients may resend requests after timeouts, even if the original request succeeded.

**32. Why is exactly-once delivery difficult?**
* Failures make it impossible to know whether a lost acknowledgment means the operation failed or only the response was lost.
* Many systems instead combine retries with idempotent processing.

**33. Why is consensus difficult?**
* Nodes must agree despite failures, message delays, and incomplete information.

**34. What trade-off often appears during failures?**
* Systems may choose between:
  - Continuing to serve requests with uncertainty.
  - Pausing operations until confidence is restored.

**35. What mindset should developers adopt?**
* Assume failures will happen and design systems that continue operating or recover gracefully.

**36. Why is monitoring especially important in distributed systems?**
* Failures are often partial and difficult to reproduce.
* Monitoring helps detect abnormal latency, retries, partitions, and unhealthy nodes.

**37. Why intentionally test failures?**
* Simulating crashes, delays, and partitions validates that systems recover as expected before real incidents occur.

**38. What practices improve distributed system reliability?**
- Use retries carefully.
- Design idempotent APIs.
- Set realistic timeouts.
- Monitor latency.
- Expect partial failures.
- Avoid relying solely on clocks.

**39. What is the key mental model for distributed systems?**
* A distributed system is a collection of independent computers communicating over an unreliable network with imperfect 
information.
* Design decisions should account for uncertainty.

**40. What are the biggest lessons from this chapter?**
- Distributed systems operate under uncertainty.
- Partial failures are normal, not exceptional.
- Networks are unreliable and latency is variable.
- Failure detection is probabilistic, not certain.
- Physical clocks are imperfect and should not be trusted for event ordering.
- Logical ordering is often more reliable than timestamps.
- Idempotency and retries are essential techniques for building resilient systems.
- Good distributed systems assume failures and recover gracefully rather than expecting perfect infrastructure.

### One-Minute Mental Model.

```text
            Distributed System

      Node A ───── Network ───── Node B
         │                           │
         │                           │
      Can Fail                  Can Fail
         │                           │
         └────────────┬──────────────┘
                      │
               Uncertainty

Problems
──────────────────────────────────

• Partial failures
• Lost messages
• Delayed messages
• Duplicate messages
• Network partitions
• Clock skew
• Variable latency

Design Techniques
──────────────────────────────────

Retries
        ↓
Idempotent Operations
        ↓
Timeouts
        ↓
Monitoring
        ↓
Graceful Recovery

Clock Types
──────────────────────────────────

Wall Clock
✔ Calendar time
✘ May jump

Monotonic Clock
✔ Measures duration
✔ Never moves backward

Core Lesson
──────────────────────────────────

Never assume:
• the network is reliable
• clocks are perfectly synchronized
• machines always respond
• failures are obvious

Instead, design systems that remain correct despite uncertainty.
```

***

## Chapter 10 – Consistency and Consensus.

**1. What does consistency mean in distributed systems?**
* Consistency describes the guarantees a system provides about what different clients observe when reading shared data after writes occur.

**2. Why is consistency harder in distributed systems than on a single machine?**
* Because data is replicated across multiple machines that communicate over unreliable networks, making it impossible 
* for every node to have perfect, instantaneous knowledge of all updates.

**3. What is consensus?**
* Consensus is the process by which multiple nodes agree on a single value or decision despite failures and unreliable 
communication.

**4. What problems require consensus?**
* Consensus enables systems to:
  - Elect a leader.
  - Commit distributed transactions.
  - Coordinate configuration changes.
  - Maintain replicated logs.
  - Avoid conflicting decisions.

**5. What properties should a consensus algorithm provide?**
* A correct consensus algorithm should ensure:
  - Only one value is chosen.
  - All healthy participants eventually agree.
  - Once decided, the value cannot change.

**6. What is the difference between safety and liveness?**
**Safety.**
- Nothing incorrect ever happens.

**Liveness.**
- Something useful eventually happens.

* Distributed algorithms aim to satisfy both.

**7. What makes consensus fundamentally difficult?**
* Nodes may:
  - Crash.
  - Become unreachable.
  - Experience delayed messages.
  - Disagree about failures.
* The algorithm must still reach a correct decision.

**8. What does the FLP impossibility result tell us?**
* In a fully asynchronous system where even one process may fail, no deterministic algorithm can guarantee both 
termination and correctness under all conditions.

**9. How do real systems cope with the FLP result?**
* They make practical assumptions, such as using timeouts and assuming periods of stable communication, while accepting 
that progress may temporarily stop during severe failures.

**10. Why do many distributed systems elect a leader?**
* A leader coordinates updates, reducing conflicts and simplifying replication.

**11. What responsibilities does a leader typically have?**
* The leader:
  - Accepts writes.
  - Orders operations.
  - Replicates updates.
  - Coordinates membership changes.

**12. What is split-brain?**
* Two or more nodes simultaneously believe they are the leader, leading to conflicting updates.

**13. How do consensus algorithms prevent split-brain?**
* They ensure only one leader can obtain a majority of votes before serving as leader.

**14. What is a quorum?**
* A quorum is the minimum number of nodes required to make a decision.

**15. Why are majority quorums common?**
* Any two majority quorums overlap, ensuring they always share at least one node that knows the latest decision.

**16. How many votes are needed in a five-node cluster?**
* A majority is three votes.

**17. What happens if the leader fails?**
* The remaining nodes elect a new leader using the consensus protocol.

**18. What are fencing tokens?**
* Monotonically increasing identifiers proving which leader is the newest, preventing old leaders from issuing stale commands.

**19. What is an epoch (or term)?**
* A monotonically increasing number identifying a leadership period.
* Every new leader begins a new term.

**20. Why do many consensus algorithms maintain a replicated log?**
* A replicated log ensures every node executes the same operations in the same order.

**21. What is a deterministic state machine?**
* A system where identical inputs processed in identical order always produce identical outputs.

**22. How does state machine replication work?**
* Consensus establishes one ordered log of commands, and every replica executes that log in sequence.

**23. Why is the order of log entries important?**
* Different execution orders may produce different database states.
* Consensus establishes one agreed ordering.

**24. What is linearizability?**
* Each operation appears to take effect atomically at a single instant between its invocation and completion.

**25. When is linearizability valuable?**
* When applications require the latest completed write to be immediately visible to all clients.

**26. Why isn't linearizability always used?**
* It often increases latency and reduces availability because stronger coordination is required.

**27. How does sequential consistency differ from linearizability?**
* Sequential consistency preserves one global operation order but does not require that order to match real-world time.

**28. How are consensus and replication related?**
* Replication copies data.
* Consensus ensures replicas agree on the order of updates.

**29. What is Raft?**
* Raft is a consensus algorithm designed to be easier to understand while providing strong consistency through 
leader-based log replication.

**30. What is Paxos?**
* Paxos is a foundational consensus algorithm that guarantees safety despite failures but is widely regarded as difficult 
to understand and implement.

**31. Why do systems use coordination services like ZooKeeper?**
* They provide reliable primitives such as:
  - Leader election.
  - Configuration storage.
  - Distributed locks.
  - Membership management.

**32. Why are membership changes difficult?**
* Adding or removing nodes changes the quorum size and must itself be coordinated safely.

**33. Why are distributed locks difficult?**
* Failures may leave lock ownership uncertain, requiring careful coordination to avoid multiple owners.

**34. What is a lease?**
* A lease is a lock with an expiration time, reducing the chance of permanent lock ownership after failures.

**35. How do consensus systems behave during network partitions?**
* Only the partition containing a majority quorum can continue making progress.
* Minority partitions reject writes.

**36. How does consensus relate to the CAP theorem?**
* Consensus systems typically prioritize consistency over availability during partitions by refusing conflicting updates.

**37. What trade-offs does consensus introduce?**
* Correctness.
* Coordination.
* Strong consistency.
* Costs:
  - Latency.
  - Reduced availability during failures.
  - Operational complexity.

**38. Where is consensus commonly used?**
- Distributed databases.
- Configuration services.
- Service discovery.
- Metadata management.
- Leader election.

**39. What is the best mental model for consensus?**
* Consensus is about ensuring multiple independent computers behave as though they share a single agreed sequence 
of decisions.

**40. What are the key lessons from this chapter?**
- Consistency determines what clients observe.
- Consensus enables distributed nodes to agree despite failures.
- Safety and liveness are complementary goals.
- Majority quorums prevent conflicting decisions.
- Replicated logs allow deterministic state machine replication.
- Linearizability provides strong consistency but increases coordination costs.
- Consensus underpins leader election, replication, and distributed coordination.
- Strong consistency always involves trade-offs between latency, availability, and complexity.

### One-Minute Mental Model.
```text
             Distributed Cluster

          Node A
             │
          Node B
             │
          Node C
             │
          Node D
             │
          Node E

         Consensus Algorithm
                 │
         Majority Quorum (3/5)
                 │
          Elect One Leader
                 │
      Leader Orders All Commands
                 │
        Replicated Log Entries
                 │
    Every Replica Executes in Order
                 │
       Same Commands + Same Order
                 │
          Same Final Database

----------------------------------------

Core Concepts

Replication
✔ Copies data

Consensus
✔ Agrees on update order

Linearizability
✔ Latest completed write is visible

Quorum
✔ Majority agreement required

Leader Election
✔ Exactly one active leader

Safety
✔ Never produce conflicting decisions

Liveness
✔ Eventually make progress

----------------------------------------

Trade-offs

Stronger Consistency
    ↑
More Coordination
    ↑
Higher Latency
    ↓
Lower Availability During Partitions

Weaker Consistency
    ↓
Less Coordination
    ↓
Lower Latency
    ↑
Higher Availability
```

### Quick Comparison Cheat Sheet.

| Concept                   | Purpose                                         | Example                         |
|---------------------------|-------------------------------------------------|---------------------------------|
| Replication               | Copy data                                       | Primary → Replicas              |
| Consensus                 | Agree on decisions                              | Leader election                 |
| Quorum                    | Prevent conflicting decisions                   | 3 of 5 nodes                    |
| Replicated Log            | Same operation order                            | Raft log                        |
| Linearizability           | Latest write immediately visible                | Strong consistency              |
| Sequential Consistency    | Same operation order, not necessarily real time | Shared memory model             |
| Lease                     | Time-limited ownership                          | Distributed lock                |
| Epoch / Term              | Identify leadership generation                  | Raft term number                |
| Fencing Token             | Prevent stale leaders                           | Storage write token             |
| State Machine Replication | Keep replicas identical                         | Deterministic command execution |

### Interview Review Questions.
1. Why is **consensus** harder than simple replication?
2. What is the difference between **safety** and **liveness**?
3. Why do **majority quorums** prevent split-brain?
4. How is a **replicated log** related to a deterministic state machine?
5. When would you choose **linearizability**, and what does it cost?
6. Why do consensus systems stop accepting writes on a **minority partition**?
7. What roles do **terms**, **epochs**, and **fencing tokens** play in preventing stale leaders?
8. Why are **membership changes** themselves a consensus problem?

***

## Chapter 11 – Batch Processing.

**1. What is batch processing?**
* Batch processing executes computations over a finite dataset. 
* Data is collected first, then processed as a complete job rather than one record at a time.
* Typical goals include:
  - Analytics.
  - Reporting.
  - Data transformation.
  - Machine learning.
  - ETL pipelines.

**2. How does batch processing differ from online processing?**
**Batch.**
- Processes bounded datasets.
- Prioritizes throughput.
- Results available later.

**Online.**
- Processes requests immediately.
- Prioritizes low latency.
- Results returned instantly.

**3. Why is batch processing useful?**
* It efficiently handles very large datasets where immediate results are unnecessary.
* Examples:
  - Nightly reports.
  - Billing.
  - Recommendation model training.
  - Log analysis.

**4. Why is throughput more important than latency in batch systems?**
* Batch jobs often process millions or billions of records. 
* Completing the entire workload efficiently matters more than responding quickly to any single record.

**5. What is data locality?**
* Moving computation to where data is stored instead of moving large datasets across the network.
* Benefits:
  - Lower network traffic.
  - Faster execution.
  - Better scalability.

**6. What is a shared-nothing architecture?**
* Each machine has its own CPU, memory, and storage, minimizing contention and allowing independent scaling.

**7. Why partition datasets for batch processing?**
* Independent partitions can be processed in parallel, dramatically reducing total execution time.

**8. What is an embarrassingly parallel problem?**
* A computation where tasks can execute independently without coordinating with one another.
* Examples:
  - Image resizing.
  - Log parsing.
  - Checksum calculation.

**9. What is the main idea behind MapReduce?**
* Split work into two phases:
1. Map transforms input into intermediate key-value pairs.
2. Reduce aggregates values with the same key.

**10. What happens during the Map phase?**
* Each input record is processed independently to produce zero or more intermediate key-value pairs.

**11. What happens during the Reduce phase?**
* All values sharing the same key are grouped and processed together to produce the final result.

**12. What is the shuffle phase?**
* The system redistributes intermediate data so that all records with the same key arrive at the same reducer.

**13. Why is the shuffle phase often the slowest part of MapReduce?**
* It involves:
  - Network communication.
  - Disk I/O.
  - Sorting.
  - Synchronization across machines.

**14. How do batch frameworks tolerate failures?**
* Failed tasks are rerun because computations are deterministic and based on immutable input data.

**15. Why is deterministic processing important?**
* Running the same task again should produce the same result, making retries safe.

**16. Why do batch systems prefer immutable input files?**
* Immutable data simplifies recovery, caching, reproducibility, and parallel execution.

**17. Why do many batch systems resemble functional programming?**
* They emphasize:
  - Immutable data.
  - Pure transformations.
  - Deterministic behavior.
  - Parallel execution.

**18. What does ETL stand for?**
- Extract.
- Transform.
- Load.

* A common workflow for preparing data for analytics or warehousing.

**19. What is a data pipeline?**
* A sequence of processing steps where the output of one stage becomes the input of the next.

**20. Why are workflow schedulers needed?**
* They manage dependencies between jobs and ensure tasks execute in the correct order.

**21. What is a DAG in batch processing?**
* A Directed Acyclic Graph representing task dependencies.
* A task begins only after its prerequisites complete.

**22. Why are DAGs more flexible than simple MapReduce?**
* They allow complex workflows with multiple stages, branches, and merges instead of only one map and one reduce step.

**23. Why are intermediate results often written to disk?**
* They allow failed stages to restart without repeating the entire computation.

**24. Why is sorting fundamental in many batch systems?**
* Sorting groups related records together, enabling efficient aggregation, joins, and duplicate removal.

**25. Why use external sorting?**
* Large datasets exceed memory capacity, so sorting combines in-memory operations with temporary disk storage.

**26. Why are joins common in batch processing?**
* They combine related information from multiple datasets to produce richer analytical results.

**27. What is aggregation?**
* Combining multiple records into summary information.
* Examples:
  - COUNT.
  - SUM.
  - AVG.
  - MAX.

**28. What is incremental batch processing?**
* Processing only newly added or modified data instead of recomputing everything.

**29. When is a full recomputation appropriate?**
* When correctness is more important than speed or when incremental logic becomes overly complex.

**30. How is batch processing used in machine learning?**
* It prepares training datasets, computes features, trains models, and evaluates results over large historical datasets.

**31. What is a batch view?**
* A precomputed dataset derived from raw data, optimized for later querying.

**32. What is data lineage?**
* The record of how a dataset was produced, including its inputs and transformation steps.

**33. Why is reproducibility valuable?**
* It allows the same computation to be rerun later, producing identical results for debugging or auditing.

**34. Why do batch systems schedule resources?**
* To efficiently share cluster resources among many users and jobs while maximizing utilization.

**35. What features do most distributed batch frameworks share?**
- Task scheduling.
- Automatic retries.
- Parallel execution.
- Fault tolerance.
- Distributed storage.
- Dependency management.

**36. What are the main advantages and disadvantages of batch processing?**
* Advantages:
  - High throughput.
  - Scalable.
  - Fault tolerant.
* Disadvantages:
  - High latency.
  - Delayed results.
  - Unsuitable for real-time applications.

**37. Which workloads are poor candidates for batch processing?**
* Applications requiring:
  - Real-time responses.
  - Interactive queries.
  - Live monitoring.
  - Immediate notifications.

**38. What is the key mental model for batch systems?**
* Bring computation to the data, process partitions independently, then combine the results efficiently.

**39. What principles guide scalable batch system design?**
- Partition data.
- Exploit parallelism.
- Prefer immutable data.
- Retry failed tasks.
- Minimize network movement.
- Build deterministic computations.

**40. What are the biggest lessons from batch processing?**
- Batch processing optimizes throughput over latency.
- Partitioning enables massive parallelism.
- Data locality reduces network overhead.
- MapReduce popularized distributed batch computation through map, shuffle, and reduce phases.
- DAG-based systems generalize batch workflows beyond MapReduce.
- Immutable inputs and deterministic computation make retries simple and reliable.
- Batch systems are ideal for analytics, ETL, reporting, and large-scale machine learning.

### One-Minute Mental Model.

```text
                Raw Dataset
                     │
          Partition Into Chunks
                     │
     ┌────────┬────────┬────────┐
     │        │        │        │
   Worker   Worker   Worker   Worker
     │        │        │        │
   Map()    Map()    Map()    Map()
     │        │        │        │
     └────────┴────────┴────────┘
              Shuffle
                 │
      Group Records by Key
                 │
     ┌────────┬────────┬────────┐
     │        │        │        │
 Reduce()  Reduce() Reduce() Reduce()
     │        │        │        │
     └────────┴────────┴────────┘
            Final Output

----------------------------------------

Key Ideas

✔ Throughput > Latency
✔ Immutable Input
✔ Parallel Execution
✔ Retry Failed Tasks
✔ Data Locality
✔ Deterministic Processing

----------------------------------------

Typical Pipeline

Extract
   ↓
Transform
   ↓
Aggregate
   ↓
Join
   ↓
Sort
   ↓
Load
   ↓
Analytics / ML / Reports

----------------------------------------

Choose Batch Processing When

• Data is finite
• Results can wait
• Maximum throughput matters
• Processing large historical datasets
• Building reports or ML models
```

### Quick Comparison Cheat Sheet.

| Concept             | Purpose                         | Key Benefit                |
|---------------------|---------------------------------|----------------------------|
| Batch Processing    | Process bounded datasets        | High throughput            |
| Map                 | Transform records independently | Parallelism                |
| Shuffle             | Group identical keys            | Enables aggregation        |
| Reduce              | Combine grouped records         | Summaries and analytics    |
| Data Locality       | Execute near storage            | Less network traffic       |
| DAG                 | Model workflow dependencies     | Flexible pipelines         |
| ETL                 | Prepare analytical data         | Clean, structured datasets |
| Immutable Input     | Never modify source data        | Safe retries               |
| Deterministic Tasks | Same input → same output        | Fault tolerance            |
| Data Lineage        | Track transformations           | Auditing and debugging     |

### Interview Review Questions
1. Why does **batch processing** optimize for throughput instead of latency?
2. What are the responsibilities of the **map**, **shuffle**, and **reduce** phases?
3. Why is **data locality** important in distributed analytics?
4. How do immutable inputs make **fault tolerance** simpler?
5. Why is the **shuffle phase** often the most expensive part of a distributed batch job?
6. When should you choose a **full recomputation** instead of incremental processing?
7. How do **DAG-based workflows** improve on classic MapReduce?
8. Which workloads are good candidates for **batch processing**, and which require **stream processing** instead?

***

## Chapter 12 – Stream Processing.

**1. What is stream processing?**
* Stream processing continuously processes events as they arrive rather than waiting for a complete dataset.
* Typical goals include:
  - Real-time analytics.
  - Fraud detection.
  - Monitoring.
  - Alerting.
  - Event-driven applications.

**2. How does stream processing differ from batch processing?**
**Batch**
- Processes finite datasets.
- High throughput.
- Delayed results.

**Stream**
- Processes unbounded event streams.
- Low latency.
- Continuous results.

**3. What is an event?**
* An event is an immutable record describing something that happened at a particular point in time.
* Examples:
  - User logged in.
  - Order placed.
  - Sensor reading.
  - Payment completed.

**4. Why are events usually immutable?**
* Immutable events:
  - Preserve history.
  - Simplify debugging.
  - Support replay.
  - Enable auditing.
  - Improve fault recovery.

**5. What is an event stream?**
* An ordered sequence of events that applications continuously publish and consume.
* Streams may be:
  - Append-only.
  - Durable.
  - Replayable.

**6. Who are event producers?**
* Applications or services that publish events to a stream.
* Examples:
  - Web applications.
  - IoT devices.
  - Databases.
  - Mobile apps.

**7. What do event consumers do?**
* Consumers subscribe to streams and process events independently.
* Multiple consumers can process the same stream for different purposes.

**8. What is a message broker?**
* A system that receives, stores, and distributes events between producers and consumers.
* It decouples senders from receivers.

**9. Why introduce a message broker instead of direct communication?**
* A broker provides:
  - Buffering.
  - Durability.
  - Scalability.
  - Asynchronous communication.
  - Fan-out to multiple consumers.

**10. What is the publish-subscribe model?**
* Publishers send events to topics.
* Subscribers receive events from topics without knowing who produced them.

**11. Why is event ordering important?**
* Many computations depend on processing events in the correct sequence.
* Incorrect ordering can produce inconsistent results.

**12. Why partition event streams?**
* Partitioning enables parallel processing while preserving ordering within each partition.

**13. What is a consumer group?**
* A set of consumers that cooperatively process a stream.
* Each event is processed by one member of the group, enabling horizontal scaling.

**14. What is an offset?**
* An offset identifies the position of an event within a partition.
* Consumers store offsets to track progress and resume after failures.

**15. What does replaying a stream mean?**
* Reprocessing historical events from stored logs.
* Replay supports:
  - Bug fixes.
  - Rebuilding state.
  - Testing.
  - Recovery.

**16. What is stateful stream processing?**
* Processing that maintains information across events.
* Example:
  * Counting purchases per customer throughout the day.

**17. What is stateless processing?**
* Each event is processed independently without remembering previous events.
* Example:
  * Converting temperatures from Celsius to Fahrenheit.

**18. What is the difference?**
* **Event Time:**
  - When the event actually occurred.
* **Processing Time:**
  - When the system processed it.
* These may differ due to delays.

**19. Why use event time instead of processing time?**
* It produces more accurate analytics when events arrive late or out of order.

**20. What is a late event?**
* An event that arrives significantly after its event timestamp.

**21. Why do events arrive out of order?**
* Because of:
  - Network delays.
  - Retries.
  - Batching.
  - Multiple producers.

**22. What is a watermark?**
* A watermark estimates that no earlier events are expected beyond a certain event time.
* It helps determine when results can be finalized.

**23. Why are windows used?**
* Infinite streams must be divided into finite subsets before aggregation.

**24. What is a tumbling window?**
* Fixed-size, non-overlapping windows.
* Example:
  * Every 5 minutes.

**25. What is a sliding window?**
* Windows overlap.
* Example:
  * Every minute calculate the previous 10 minutes.

**26. What is a session window?**
* Groups events separated by periods of user inactivity.
* Useful for analyzing user behavior.

**27. What aggregations are common in stream processing?**
* Examples include:
  - Count.
  - Sum.
  - Average.
  - Minimum.
  - Maximum.
  - Distinct count.

**28. Can streams be joined?**
* Yes.
* Applications can join:
  - Stream-to-stream.
  - Stream-to-table.
  - Stream-to-reference data.

**29. What does exactly-once processing mean?**
* Each logical event affects the application's state exactly once, even if retries occur internally.

**30. What is at-least-once delivery?**
* Events may be delivered multiple times.
* Consumers must handle duplicates safely.

**31. What is at-most-once delivery?**
* Events are never duplicated but may be lost if failures occur.

**32. Why are idempotent consumers valuable?**
* Processing duplicate events produces the same final result, simplifying retries and recovery.

**33. What is checkpointing?**
* Periodically saving application state so processing can resume after failures.

**34. How do stream processors recover from failures?**
* By restoring checkpoints and replaying events from stored offsets.

**35. What is event sourcing?**
* A design pattern where application state is reconstructed by replaying the complete history of events.

**36. What is CQRS?**
* Command Query Responsibility Segregation separates write models from read models.
* Streams often update specialized read models asynchronously.

**37. What advantages and disadvantages does stream processing have?**
* Advantages:
  - Low latency.
  - Real-time insights.
  - Continuous processing.
* Disadvantages:
  - Higher complexity.
  - State management.
  - Event ordering challenges.
  - Difficult debugging.

**38. Where is stream processing commonly used?**
- Fraud detection.
- Monitoring.
- Recommendation systems.
- IoT analytics.
- Log processing.
- Financial trading.
- Personalization.

**39. What principles guide scalable stream processing?**
- Treat events as immutable.
- Partition streams for parallelism.
- Design idempotent consumers.
- Prefer event time for analytics.
- Store offsets and checkpoints.
- Expect late and duplicate events.

**40. What are the key lessons from stream processing?**
- Streams process unbounded data continuously.
- Events are immutable facts.
- Brokers decouple producers and consumers.
- Stateful processing enables rich analytics.
- Event time produces more accurate results than processing time.
- Watermarks and windows make infinite streams analyzable.
- Checkpointing and replay provide fault tolerance.
- Exactly-once semantics require careful coordination across processing and storage.

### One-Minute Mental Model.

```text
             Event Producers
   ┌──────────┬──────────┬──────────┐
   │          │          │
 Web App   Mobile App   Sensors
   │          │          │
   └──────────┴──────────┘
              │
        Message Broker
              │
      Topic / Event Stream
              │
   ┌──────────┼──────────┐
   │          │          │
Consumer   Consumer   Consumer
Group A    Group B    Group C
   │          │          │
Analytics Alerts Search Index

----------------------------------------

Stream Characteristics

✔ Infinite stream
✔ Immutable events
✔ Ordered per partition
✔ Replayable
✔ Durable

----------------------------------------

Time Concepts

Event Time
    │
    ▼
When the event occurred

Processing Time
    │
    ▼
When the system processed it

Watermarks estimate when enough event-time data has arrived.

----------------------------------------

State Management

Events
   │
Process
   │
Update State
   │
Checkpoint
   │
Failure?
   │
Restore State
   │
Replay Events
   │
Continue Processing

----------------------------------------

Delivery Guarantees

At-Most-Once
✔ Fast
✘ Possible data loss

At-Least-Once
✔ No data loss
✘ Duplicates possible

Exactly-Once
✔ Correct final state
✘ More coordination and complexity

----------------------------------------

Choose Stream Processing When

• Results are needed immediately
• Data arrives continuously
• Low latency matters
• Applications react to events
• Real-time analytics or monitoring is required
```

### Quick Comparison Cheat Sheet.

| Concept        | Purpose                      | Key Benefit                 |
|----------------|------------------------------|-----------------------------|
| Event          | Immutable fact               | Replay and auditability     |
| Event Stream   | Ordered sequence of events   | Continuous processing       |
| Message Broker | Decouple producers/consumers | Scalability                 |
| Consumer Group | Parallel processing          | Horizontal scaling          |
| Offset         | Track read progress          | Recovery                    |
| Window         | Bound infinite streams       | Aggregation                 |
| Watermark      | Handle late events           | Accurate event-time results |
| Checkpoint     | Save processing state        | Fault tolerance             |
| Event Sourcing | Persist event history        | Rebuild state               |
| CQRS           | Separate reads and writes    | Optimized query models      |

---

# Interview Review Questions

Try answering these without looking:

1. Why is an **event** treated as an immutable fact?
2. What is the difference between **event time** and **processing time**?
3. Why are **watermarks** necessary for event-time processing?
4. How do **tumbling**, **sliding**, and **session windows** differ?
5. What roles do **offsets** and **checkpoints** play in recovery?
6. Why is **idempotent processing** important with at-least-once delivery?
7. What trade-offs exist between **at-most-once**, **at-least-once**, and **exactly-once** semantics?
8. How do **event sourcing** and **CQRS** leverage streams to build resilient systems?

***

## Chapter 13 – A Philosophy of Streaming Systems.

**1. What is the central philosophy of streaming systems?**
* Treat data as a continuous stream of immutable events rather than as mutable state. 
* Applications react to events as they occur, and current state is derived from event history.

**2. Why can events serve as the source of truth?**
* Events represent facts that happened. 
* Since they are immutable, they provide a complete historical record from which current state can always be reconstructed.

**3. What is the difference between state and events?**
- **Event:** Something that happened (immutable).
- **State:** The current result after applying many events.

* State changes over time, while events do not.

**4. Why are immutable events beneficial?**
* They enable:
  - Replay.
  - Auditing.
  - Debugging.
  - Reproducibility.
  - Temporal analysis.
  - Easier fault recovery.

**5. What is an event log?**
* An append-only sequence of events stored in the order they were produced.
* Applications can replay the log to rebuild state.

**6. Why is replay a powerful capability?**
* Replay allows systems to:
  - Recover after failures.
  - Rebuild projections.
  - Fix application bugs.
  - Migrate to new processing logic without changing the original data.

**7. What is derived state?**
* State computed from one or more event streams.
* Examples:
  - Account balances.
  - Inventory levels.
  - Recommendation models.
  - Search indexes.

**8. What is a materialized view?**
* A precomputed representation of data optimized for a specific query or use case.
* It can always be rebuilt from the underlying event stream.

**9. Why create multiple materialized views?**
* Different applications have different query requirements.
* One event stream can generate many specialized views.

**10. Why separate the source of truth from read models?**
* Because storage focuses on preserving facts, while read models optimize performance for specific queries.

**11. What is an event-driven architecture?**
* Components communicate by publishing and consuming events instead of invoking each other directly.

**12. What advantages does event-driven architecture provide?**
- Loose coupling.
- Scalability.
- Independent deployment.
- Asynchronous communication.
- Easier system evolution.

**13. Why is loose coupling valuable?**
* Producers do not need to know who consumes events.
* New consumers can be added without modifying producers.

**14. Why are streams a good integration mechanism?**
* Multiple independent services can subscribe to the same events, reducing direct dependencies between systems.

**15. What is event sourcing?**
* A design pattern where every state change is stored as an event, and application state is reconstructed by replaying 
those events.

**16. How does CQRS complement event sourcing?**
* Commands produce events, while separate read models consume those events to answer queries efficiently.

**17. Why maintain separate read models?**
* Different queries often require different data structures.
* Specialized projections improve performance and simplify querying.

**18. What is a dataflow system?**
* A system where data continuously moves through transformations rather than being repeatedly queried from a central database.

**19. What is a streaming pipeline?**
* A sequence of transformations where each stage consumes events, processes them, and emits new events.

**20. When is stateless processing appropriate?**
* When each event can be processed independently without remembering previous events.

**21. When is stateful processing required?**
* When processing depends on previous events.
* Examples:
  - Counting.
  - Session tracking.
  - Windowed aggregation.

**22. Why is time central to streaming systems?**
* Events occur over time, and many computations depend on event ordering and temporal relationships.

**23. Why prefer event time over processing time?**
* It produces results that reflect when events actually happened rather than when they happened to arrive.

**24. Why is reprocessing valuable?**
* New business rules can be applied to historical data without recollecting it.

**25. How do event logs simplify bug fixes?**
* After fixing processing logic, replay historical events to regenerate correct outputs.

**26. Why preserve the full event history?**
* Historical events enable:
  - Trend analysis.
  - Auditing.
  - Compliance.
  - Debugging.
  - Machine learning.

**27. Why must event schemas evolve carefully?**
* Old events remain stored indefinitely, so new software must continue understanding historical formats.

**28. Why is backward compatibility especially important for events?**
* Stored event logs may outlive multiple application versions.

**29. Why should stream processors be idempotent?**
* Duplicate events may occur due to retries.
* Processing them repeatedly should produce the same final state.

**30. Why is exactly-once processing difficult?**
* It requires coordination between message delivery, state updates, and output generation to avoid duplicates or lost work.

**31. Why do streaming systems often embrace eventual consistency?**
* Read models update asynchronously.
* Temporary differences are acceptable as long as all projections eventually converge.

**32. How do streams support independent service evolution?**
* Consumers process events independently, allowing services to evolve without tightly coordinated deployments.

**33. How do streaming systems recover from failures?**
* Restore application state from checkpoints and replay missed events from the log.

**34. Why do streaming architectures scale well?**
* Streams can be partitioned so multiple processors handle different subsets of events in parallel.

**35. How are batch and streaming related?**
* Both perform data transformations.
* Batch processes bounded datasets; streaming processes unbounded datasets continuously.
* Many modern systems unify both under a common programming model.

**36. What is unified dataflow?**
* A programming model where the same logical operations work for both historical (batch) and live (streaming) data.

**37. What trade-offs come with streaming architectures?**
* Advantages:
  - Scalability.
  - Replayability.
  - Decoupling.
  - Flexibility.
* Challenges:
  - Operational complexity.
  - State management.
  - Event ordering.
  - Schema evolution.

**38. Which applications benefit most from streaming-first architectures?**
- Recommendation engines.
- Fraud detection.
- IoT platforms.
- Financial systems.
- Monitoring.
- Personalization.
- Analytics.

**39. What principles define a streaming-first architecture?**
- Store immutable events.
- Derive state from events.
- Build specialized read models.
- Embrace replay.
- Design for eventual consistency.
- Prefer loose coupling.

**40. What are the biggest lessons from this chapter?**
- Events are durable facts; state is a derived view.
- Immutable logs simplify recovery, auditing, and evolution.
- Event-driven architectures decouple producers from consumers.
- Materialized views optimize queries while remaining disposable.
- Replay enables rebuilding systems with improved logic.
- Streaming and batch are complementary dataflow models rather than competing approaches.
- Treating data as an immutable stream encourages scalable, resilient, and evolvable systems.

### One-Minute Mental Model.

```text
                Real-World Events
                       │
        ┌──────────────┼──────────────┐
        │              │              │
 User Action      Payment        Sensor Data
        │              │              │
        └──────────────┴──────────────┘
                       │
                 Append-Only Log
                       │
          Source of Truth (Immutable)
                       │
      ┌────────┬────────┬────────┐
      │        │        │        │
 Analytics  Search   Billing   ML Model
 Projection Projection Projection
      │        │        │        │
 Materialized Views / Derived State

-------------------------------------------------

Key Philosophy

Immutable Events
        ↓
Append-Only Log
        ↓
Replay Anytime
        ↓
Rebuild State
        ↓
Multiple Specialized Views

-------------------------------------------------

State = f(Events)

Current State
      =
Replay(Event₁, Event₂, Event₃, ... Eventₙ)

-------------------------------------------------

Streaming Principles

✔ Events are facts.
✔ State is temporary.
✔ Views are disposable.
✔ Replay is powerful.
✔ Services are loosely coupled.
✔ Data flows continuously.
```

### Quick Comparison Cheat Sheet.

| Concept              | Purpose                           | Key Insight                |
|----------------------|-----------------------------------|----------------------------|
| Event                | Immutable fact                    | Never changes              |
| State                | Current system condition          | Derived from events        |
| Event Log            | Append-only history               | Source of truth            |
| Replay               | Reprocess events                  | Recovery and evolution     |
| Materialized View    | Optimized read model              | Disposable and rebuildable |
| Event Sourcing       | Persist every change as an event  | Complete audit trail       |
| CQRS                 | Separate writes from reads        | Specialized models         |
| Dataflow             | Continuous transformations        | Reactive architecture      |
| Eventual Consistency | Asynchronous convergence          | Better scalability         |
| Unified Dataflow     | One model for batch and streaming | Simpler architecture       |

### Interview Review Questions.
1. Why is an **immutable event log** often a better source of truth than storing only the latest state?
2. How does **derived state** differ from **source data**?
3. Why are **materialized views** considered disposable?
4. How does **event replay** support debugging, recovery, and new features?
5. What are the benefits of **loosely coupled, event-driven services** compared with synchronous request chains?
6. How do **event sourcing** and **CQRS** complement each other?
7. Why do streaming systems often accept **eventual consistency**?
8. In what sense are **batch** and **streaming** two forms of the same dataflow model?

### Final Mental Model.

* Remember this equation:

```text
Traditional Thinking

Current State
      ↓
Modify State
      ↓
Overwrite Old State

-----------------------------------------

Streaming Thinking

Events (Facts)
      ↓
Append to Log
      ↓
Replay Events
      ↓
Derive State
      ↓
Build Any Number of Views

State is temporary.
Events are forever.
```

***

## Chapter 14 – Doing the Right Thing.

**1. What does "doing the right thing" mean when designing data systems?**
* It means considering not only technical correctness but also the effects of systems on people, organizations, and society. 
* A technically successful system can still cause harm if it ignores ethics, privacy, fairness, or accountability.

**2. Why should software engineers think about ethics?**
* Technical decisions determine how information is collected, stored, shared, and used. 
* These choices directly affect users' rights, safety, opportunities, and trust.

**3. Who is responsible for the behavior of a data-intensive system?**
* Responsibility is shared among:
  - Engineers.
  - Architects.
  - Product managers.
  - Business leaders.
  - Operators.
  - Organizations.
* Ethical outcomes are rarely the result of a single decision.

**4. Why should collected data be treated carefully?**
* Data can reveal sensitive information about individuals. 
* Even seemingly harmless data may become identifying when combined with other datasets.

**5. What is data minimization?**
* Collect only the data necessary for a specific purpose and retain it only as long as needed.
* Benefits include:
  - Lower privacy risk.
  - Reduced compliance burden.
  - Smaller attack surface.

**6. What is purpose limitation?**
* Data collected for one legitimate purpose should not automatically be reused for unrelated purposes without 
appropriate justification or consent.

**7. What does privacy mean in data systems?**
* Privacy means giving individuals reasonable control over how information about them is collected, stored, processed, 
and shared.

**8. What is the difference between security and privacy?**
* **Security:**
  - Protects data from unauthorized access.
* **Privacy:**
  - Governs whether the data should be collected or used in the first place.
* Security supports privacy but does not guarantee it.

**9. What is confidentiality?**
* Ensuring information is accessible only to authorized people and systems.

**10. What does data integrity mean?**
* Data remains accurate, complete, and protected from unauthorized modification.

**11. Why is availability also an ethical concern?**
* Systems that people depend on for healthcare, finance, communication, or public services can cause real harm when 
unavailable.

**12. Why is transparency important?**
* People should understand:
  - What data is collected.
  - Why it is collected.
  - How it is used.
  - Who has access.
* Transparency builds trust.

**13. What is accountability?**
* Organizations should be able to explain, justify, and take responsibility for system behavior and decisions.

**14. Why maintain audit logs?**
* Audit logs help investigate:
  - Security incidents.
  - Operational failures.
  - Regulatory compliance.
  - Unexpected behavior.

**15. Why should fairness matter in data systems?**
* Automated decisions can unintentionally disadvantage individuals or groups if they rely on biased data or assumptions.

**16. Where can bias enter a system?**
* Bias may originate from:
  - Historical data.
  - Sampling methods.
  - Labels.
  - Algorithms.
  - Evaluation metrics.
  - Deployment practices.

**17. What is a harmful feedback loop?**
* A system's outputs influence future inputs, reinforcing existing patterns or biases over time.

**18. Why is explainability valuable?**
* Users and operators should understand why important decisions were made, especially when decisions affect rights 
or opportunities.

**19. Why should humans remain involved in some automated systems?**
* Some decisions require judgment, context, or ethical reasoning beyond automated rules.

**20. Why is meaningful consent important?**
* Individuals should understand what they are agreeing to and have genuine choices whenever practical.

**21. Why is "data stewardship" often a better concept than "data ownership"?**
* Organizations are custodians of users' data and have a responsibility to protect and use it appropriately rather than 
treating it as unrestricted property.

**22. What is the principle of least privilege?**
* Grant each user or service only the minimum permissions needed to perform its tasks.

**23. What is defense in depth?**
* Protect systems using multiple independent layers of security rather than relying on a single control.

**24. Why is encryption important?**
* Encryption protects data both:
  - At rest.
  - In transit.
* Reducing the impact of unauthorized access.

**25. Why monitor production systems?**
* Monitoring helps detect:
  - Failures.
  - Attacks.
  - Misuse.
  - Abnormal behavior.
* Before they cause widespread harm.

**26. Why prepare for incidents in advance?**
* Security breaches and failures are inevitable. 
* A well-prepared response reduces impact and recovery time.

**27. Why avoid storing data indefinitely?**
* Long-term storage increases:
  - Privacy risks.
  - Legal exposure.
  - Security risks.
  - Operational costs.

**28. Why can deleting data be challenging?**
* Copies may exist in:
  - Backups.
  - Replicas.
  - Caches.
  - Logs.
  - Downstream systems.
* Deletion often requires coordinated processes.

**29. Why must engineers understand legal requirements?**
* Laws governing privacy, security, and data protection influence how systems must be designed and operated.

**30. Why is trust a competitive advantage?**
* Users are more willing to adopt systems that consistently protect their data and behave predictably.

**31. Why is resilience an ethical consideration?**
* Reliable systems prevent harm caused by outages, data loss, or prolonged service disruption.

**32. How does operational excellence contribute to ethics?**
* Good monitoring, testing, backups, disaster recovery, and maintenance reduce the likelihood of failures affecting users.

**33. Why should architects think beyond immediate requirements?**
* Design decisions about data storage and processing may affect users for many years.

**34. Why do ethical trade-offs exist?**
* Improving one property (such as personalization) may reduce another (such as privacy). 
* Responsible design balances competing goals transparently.

**35. Why document system behavior?**
* Documentation improves:
  - Maintainability.
  - Accountability.
  - Onboarding.
  - Incident investigation.

**36. Why involve diverse perspectives in system design?**
* Different backgrounds help identify risks, assumptions, and unintended consequences that homogeneous teams might overlook.

**37. What principles characterize responsible engineering?**
- Respect users.
- Minimize harm.
- Protect privacy.
- Communicate transparently.
- Build reliable systems.
- Continuously improve.

**38. Why isn't technical excellence alone sufficient?**
* A system may be highly scalable, available, and efficient while still producing harmful outcomes if ethical 
considerations are ignored.

**39. What is the best mental model for ethical system design?**
* Every technical decision affects people. 
* Design choices should optimize both system quality and human outcomes.

**40. What are the biggest lessons from this chapter?**
- Engineering decisions have social consequences.
- Privacy begins with collecting less data.
- Security, transparency, and accountability build trust.
- Fairness requires awareness of bias and feedback loops.
- Reliable systems are an ethical responsibility.
- Organizations should act as careful stewards of user data.
- Long-term thinking is essential because data often outlives software.
- Responsible engineering combines technical excellence with ethical judgment.

### One-Minute Mental Model.

```text
              User
                │
         Shares Personal Data
                │
        ┌───────┴────────┐
        │                │
   Technical Goals   Ethical Goals
        │                │
 Scalability       Privacy
 Performance       Fairness
 Availability      Transparency
 Consistency       Accountability
 Security          User Trust
        │                │
        └───────┬────────┘
                │
      Responsible System Design
                │
      Protect People and Data

----------------------------------------------------

Good Engineering Means

✔ Secure Systems
✔ Reliable Systems
✔ Transparent Systems
✔ Fair Systems
✔ Privacy-Aware Systems
✔ Well-Monitored Systems

----------------------------------------------------

Core Principles

Collect Less
        ↓
Protect It
        ↓
Explain Its Use
        ↓
Limit Access
        ↓
Delete When No Longer Needed

----------------------------------------------------

Think Beyond Technology

Every Design Decision

      ↓

Changes How People

• interact
• trust
• work
• communicate
• access opportunities

Technology shapes society.
```

### Quick Comparison Cheat Sheet.

| Concept           | Purpose                            | Key Takeaway                                     |
|-------------------|------------------------------------|--------------------------------------------------|
| Privacy           | Control information use            | Collect only what you need                       |
| Security          | Prevent unauthorized access        | Protect confidentiality, integrity, availability |
| Transparency      | Explain system behavior            | Build trust                                      |
| Accountability    | Accept responsibility              | Enable auditing and review                       |
| Fairness          | Reduce unjust outcomes             | Monitor bias continuously                        |
| Data Minimization | Reduce collected data              | Lower privacy and security risks                 |
| Least Privilege   | Limit permissions                  | Reduce attack surface                            |
| Encryption        | Protect stored/transmitted data    | Mitigate breaches                                |
| Audit Logs        | Record important actions           | Support investigations                           |
| Resilience        | Continue operating during failures | Protect users from harm                          |

### Interview Review Questions.
1. Why is **privacy** more than just encrypting data?
2. How do **data minimization** and **purpose limitation** reduce risk?
3. What is the difference between **security**, **privacy**, and **fairness**?
4. Why should organizations view themselves as **data stewards** rather than unrestricted owners?
5. How can **feedback loops** amplify bias in automated systems?
6. Why are **audit logs** essential for accountability?
7. What role does **least privilege** play in reducing security risk?
8. Why is **technical excellence** not sufficient without ethical consideration?

### Final Mental Model.

* Remember this progression:

```text
Traditional View

Build Reliable Software
        ↓
Ship Product
        ↓
Done

-----------------------------------------

Responsible Engineering

Build Reliable Software
        ↓
Protect User Data
        ↓
Respect Privacy
        ↓
Design for Fairness
        ↓
Be Transparent
        ↓
Monitor and Improve
        ↓
Accept Accountability

-----------------------------------------

A Great System Is

Technically Excellent
          +
Ethically Responsible

Both are necessary.
```

