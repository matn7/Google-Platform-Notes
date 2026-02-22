# System Design.

## 2 - Scale From Zero To Millions Of Users.

**1. Single Server Setup.**
* All components (web app, DB, cache) on one server. 
* Simple to start.

**2. Request Flow.**
* User -> DNS (domain to IP) -> Web Server -> Response (HTML/JSON).

**3. Web App Traffic.**
* Server-side (logic) + Client-side (presentation).

**4. Mobile App Traffic.**
* HTTP + JSON (API responses).

**5. Separating Web/Mobile and DB Servers.**
* Allows independent scaling of web tier and data tier.

**6. Relational Databases (SQL).**
* Tables, rows, JOIN operations, e.g., MySQL, PostgreSQL.

**7. Non-Relational Databases (NoSQL).**
* Key-value, graph, column, document stores. 
* No JOINs. e.g., CouchDB, DynamoDB.

**8. When to Use NoSQL.**
* Low latency, unstructured data, serialization needs, massive data.

**9. Vertical Scaling (Scale Up).**
* Add more power (CPU, RAM) to a server. 
* Simple, but limited.

**10. Horizontal Scaling (Scale Out).**
* Add more servers to the resource pool. 
* Preferred for large scale.

**11. Load Balancer.**
* Distributes traffic evenly across web servers.

**12. Load Balancer Benefits.**
* Failover, redundancy, handles rapid traffic growth.

**13. Private vs Public IPs.**
* Public: accessible over the internet. 
* Private: Internal server communication.

**14. Database Replication.**
* Master (write) / Slave (read) setup.

**15. Database Replication Advantages.**
* Performance, reliability, high availability.

**16. Handling DB Failures.**
* Slave DB promoted to master; new slave replaces old one.

**17. Cache.**
* Temporary storage for frequently accessed data.

**18. Cache Tier Benefits.**
* Performance, reduced DB load, independent scaling.

**19. Read-Through Cache.**
* Web server checks cache, if miss -> query DB, store in cache, return to client.

**20. Cache Considerations.**
* When to use, expiration policy, consistency, failure mitigation, eviction policy.

**21. Content Delivery Network (CDN).**
* Network of geographically dispersed servers for static content.

**22. CDN Benefits.**
* Reduced load times, lower latency for users.

**23. CDN Workflow.**
* User requests -> CDN -> (if not cached) Origin -> CDN -> User.

**24. CDN Considerations.**
* Cost, cache expiry, CDN fallback, invalidating files.

**25. Stateful vs. Stateless Web Tier.**
* Stateful: Server remembers client data. 
* Stateless: No client data stored.

**26. Stateful Architecture Issues.**
* Sticky sessions, overhead, difficult scaling, failure handling.

**27. Stateless Architecture.**
* Requests to any server, shared data store. Simpler, robust, scalable.

**28. Data Centers.**
* GeoDNS routing to closest data center.

**29. Multi-Data Center Challenges.**
* Traffic redirection, data synchronization, testing/deployment.

**30. Message Queue.**
* Asynchronous communication, decoupling. 
* Producers (publish) / Consumers (subscribe).

**31. Logging, Metrics, Automation.**
* Essential for large systems. 
* Logging: identify errors.
* Metrics: business insights & system health. 
* Automation: improve productivity.

**32. Database Scaling: Vertical.**
* Adding more resources (CPU, RAM) to a single server. 
* Limited by hardware, single point of failure risk.

**33. Database Scaling: Horizontal (Sharding).**
* Adding more servers, splitting data into shards.

**34. Sharding Key.**
* Determines data distribution across shards. 
* Even distribution is crucial.

**35. Sharding Challenges.**
* Resharding, celebrity problem (hotspot keys), joins & de-normalization.

**36. Consistent Hashing.**
* A technique used in resharding to minimize data movement.

## 3 - Back-of-the-envelope Estimation.

**1. Back-of-the-Envelope Estimation.**
* Approximating system capacity/performance using thought experiments & common performance numbers. 
* Goal: quickly evaluate design feasibility.

**2. Power of Two.**
* Essential for data volume calculations. 
* Know KB, MB, GB, TB, PB. (Kilobyte, Megabyte, Gigabyte, Terabyte, Petabyte).
* Each step is a factor of 1024 (approximately 1000).

**3. Key Latency Numbers.**
* Understand relative speeds: L1 cache < Memory < Network < Disk.
* Prioritize memory access. 
* Minimize disk seeks & network calls. 
* Compression helps!

**4. Latency Examples (Fast).**
* L1 cache reference (0.5 ns), L2 cache reference (7 ns), Main memory reference (100 ns), Compress 1KB (10 µs).

**5. Latency Examples (Slow).**
* Disk seek (10 ms), Read 1MB from network (10 ms), Read 1MB from disk (30 ms), CA to Netherlands round trip (150 ms).

**6. Availability.**
* System's ability to be continuously operational. 
* Measured in "nines" (percentage of uptime). 
* Higher nines = less downtime.

**7. SLA.**
* Service Level Agreement. 
* Contract defining guaranteed uptime. 
* Cloud providers often target 99.9% or higher.

**8. Availability vs. Downtime.**
* 99% = ~3.65 days downtime/year. 
* 99.99% = ~52.6 minutes downtime/year. 
* 99.999% = ~5.26 minutes downtime/year.

**9. Estimation Tips.**
* Round numbers. 
* Write down assumptions. 
* Label units (KB, MB, etc.). 
* Focus on the problem-solving process, not perfect accuracy.

**10. Common Estimations.**
* QPS (Queries Per Second), Peak QPS, Storage requirements, Cache size, Number of servers needed. 

**11. Importance of Assumptions.**
* Clearly state all assumptions before starting calculations. 
* These assumptions drastically impact the final estimations. 
* Justify your assumptions when possible. 
* Interviewers are interested in your reasoning.

**12. Media Storage Estimation Nuances.**
* When estimating media storage, consider factors beyond average size: compression techniques, different media types 
(photos vs. videos), potential for future growth, and redundancy/backups.

***

## 4 - A Framework For System Design Interviews.

**1. What's the real goal of a system design interview?**
* It's not about perfection! 
* It's about demonstrating your problem-solving process, collaboration skills, ability to handle ambiguity, and defend design choices.

**2. Why is asking clarifying questions SO important?**
* Avoid designing the wrong system! 
* Understand requirements, make assumptions explicit, and show you can gather necessary information. 

**3. Give 3 examples of questions to clarify requirements.**
* What specific features are we building?
* How many users? Anticipated scale?
* What's the existing tech stack?

**4. What should you include in a high-level design proposal?**
* Key components (clients, APIs, servers, databases, caches, etc.) in a box diagram. 
* Consider a few use cases.

**5. Why do back-of-the-envelope calculations?**
* To evaluate if your design fits the scale constraints. 
* Communicate with the interviewer before diving into calculations.

**6. What's the focus of the "deep dive" step?**
* Prioritize and explore key components. 
* The interviewer might guide you to focus on specific areas (performance, bottlenecks, specific components).

**7. Why is time management crucial?**
* System design problems are broad. 
* Avoid getting bogged down in unnecessary details. 
* Focus on demonstrating your abilities.

**8. What's a good way to start the wrap-up?**
* Identify potential system bottlenecks and discuss possible improvements. 
* Never claim your design is perfect!

**9. List 3 "DOs" for system design interviews.**
* Ask clarifying questions.
* Communicate your thought process.
* Bounce ideas off the interviewer.

**10. List 3 "DON'Ts" for system design interviews.**
* Don't jump into a solution without clarifying.
* Don't get stuck without asking for hints.
* Don't think in silence – communicate!

**11. What is a major "red flag" to avoid?**
* Over-engineering! 
* Be aware of tradeoffs and the compounding costs of unnecessary complexity.

**12. Why is communication so vital throughout the interview?**
* It allows the interviewer to understand your thought process, provide feedback, and collaborate effectively. 
* Treat the interviewer as a teammate.

**13. What should you discuss regarding system failures?**
* Briefly touch on error cases (server failure, network loss) and how the system would handle them.

**14. What considerations should you make for future growth?**
* Briefly discuss how the design would adapt to handle the next level of scale (e.g., from 1 million to 10 million users).

***

## 5 - Design A Rate Limiter.

**1. What is a Rate Limiter?**
* Def: Controls traffic rate, limiting requests over a period.
* Purpose: Prevent DoS, reduce costs, avoid server overload.

**2. Client-side vs. Server-side Rate Limiting.**
* Client-side: Unreliable (easy to forge requests).
* Server-side: More reliable, implemented at API server or gateway.

**3. Rate Limiter Middleware.**
* Sits between clients and API servers.
* Throttles excess requests, returns HTTP 429 error.

**4. API Gateway.**
* Managed service for rate limiting, SSL, auth, etc.
* Good option if already using microservices.

**5. Token Bucket Algorithm.**
* Bucket with pre-defined capacity; tokens added periodically.
* Each request consumes a token. 
* If no tokens, request is dropped.

**6. Token Bucket Parameters.**
* Bucket size: Max tokens in bucket.
* Refill rate: Tokens added per time unit.

**7. Leaking Bucket Algorithm.**
* Requests processed at a fixed rate (FIFO queue).
* If queue full, requests are dropped.

**8. Leaking Bucket Parameters.**
* Bucket size: Queue size.
* Outflow rate: Requests processed per time unit.

**9. Fixed Window Counter Algorithm.**
* Divides timeline into fixed windows with counters.
* Increments counter per request; drops requests exceeding threshold.

**10. Fixed Window Issue.**
* Traffic spikes at window edges allow more requests than quota.

**11. Sliding Window Log Algorithm.**
* Tracks request timestamps in a log (e.g., Redis sorted set).
* Removes outdated timestamps; rejects if log size exceeds limit.

**12. Sliding Window Log: Memory Use.**
* Can consume a lot of memory because it stores all timestamps.

**13. Sliding Window Counter Algorithm.**
* Hybrid approach combining fixed window and sliding log.
* Approximates rate based on previous and current windows.

**14. High-Level Architecture.**
* Counter to track requests (user, IP, etc.).
* In-memory cache (e.g., Redis) for speed and expiration.

**15. Redis Commands.**
* INCR: Increments counter.
* EXPIRE: Sets timeout for counter.

**16. Rate Limiting Rules.**
* Configurable rules based on domain, descriptors (key-value pairs).
* Example: Max 5 marketing messages per day.

**17. Exceeding Rate Limit.**
* Return HTTP 429 (Too Many Requests).
* Enqueue requests for later processing (optional).

**18. Rate Limiter Headers.**
* X-Ratelimit-Remaining: Requests remaining in window.
* X-Ratelimit-Limit: Max requests per window.
* X-Ratelimit-Retry-After: Seconds to wait before retrying.

**19. Distributed Rate Limiting Challenges.**
* Race conditions (concurrent counter updates).
* Synchronization issues (multiple rate limiter servers).

**20. Distributed Rate Limiting Solutions.**
* Race Conditions: Lua scripts or Redis sorted sets for atomic operations.
* Synchronization: Centralized data store (e.g., Redis).

**21. Performance Optimization: Multi-Data Center.**
* Use multiple data centers/edge servers to reduce latency for geographically diverse users.
* Route traffic to the closest edge server.

**22. Performance Optimization: Consistency.**
* Use eventual consistency for synchronizing rate limiter data across regions.
* Trade immediate consistency for higher availability and lower latency.

**23. Monitoring Rate Limiter Effectiveness.**
* Track metrics to ensure the algorithm and rules are working as intended.
* Examples: Request rejection rate, impact on valid user traffic.

**24. Hard vs. Soft Rate Limiting.**
* Hard: Strict enforcement; requests never exceed the limit.
* Soft: Allows temporary bursts above the limit.

**25. Client Best Practices (Avoiding Rate Limits).**
* Use client-side caching.
* Understand API limits and avoid exceeding them.
* Implement exception handling and retry logic with backoff.

***

## 6 - Design Consistent Hashing.

**1. Problem with Rehashing.**
* Traditional `hash(key) % N` leads to massive key redistribution when servers are added/removed, causing cache misses.

**2. Consistent Hashing Goal.**
* Minimize the number of keys that need to be remapped when the hash table (server pool) is resized.

**3. Hash Space.**
* The range of possible output values from the hash function (e.g., 0 to 2^160 - 1 for SHA-1).

**4. Hash Ring.**
* Visualizing the hash space as a circle, where the maximum hash value wraps around to the minimum value.

**5. Mapping Servers.**
* Servers are mapped onto the hash ring using a hash function (f(server IP or name)).

**6. Mapping Keys.**
* Keys are also mapped onto the hash ring using the same hash function.

**7. Server Lookup.**
* To find the server for a key, move clockwise around the ring from the key's position until you encounter a server.

**8. Adding a Server.**
* Only keys in the range between the new server and the previous server on the ring need to be redistributed.

**9. Removing a Server.**
* Only keys in the range between the removed server and the previous server on the ring need to be redistributed.

**10. Uneven Partition Sizes.**
* Basic consistent hashing can lead to uneven distribution of hash space (partitions) among servers.

**11. Non-Uniform Key Distribution.**
* Basic consistent hashing doesn't guarantee keys are evenly distributed across servers. 
* Some servers might get most of the keys.

**12. Virtual Nodes (Replicas).**
* Each physical server is represented by multiple virtual nodes on the hash ring.

**13. Purpose of Virtual Nodes.**
* Virtual nodes help to distribute keys more evenly and reduce the impact of server additions/removals.

**14. Mapping with Virtual Nodes.**
* To find a server for a key, go clockwise to find the first virtual node, then map that virtual node back to its 
physical server.

**15. Standard Deviation.**
* Increasing the number of virtual nodes reduces the standard deviation of key distribution, leading to a more 
balanced load.

**16. Tradeoff of Virtual Nodes.**
* More virtual nodes lead to better distribution but require more storage space for virtual node data.

**17. Finding Affected Keys (Add Server).**
* When adding a server, redistribute keys in the range from the new server (clockwise) to the previous server.

**18. Finding Affected Keys (Remove Server).**
* When removing a server, redistribute keys in the range from the removed server (clockwise) to the previous server to 
the next server.

**19. Benefits of Consistent Hashing.**
* Minimized key redistribution, horizontal scalability, and mitigation of hotspot key problems.

**20. Hash Function Choice.**
* Consistent hashing relies on a uniformly distributed hash function (like SHA-1) to map keys and servers to the ring. 
* The quality of the hash function directly impacts the evenness of distribution.

**21. Data Migration.**
* When keys are redistributed (due to adding or removing servers), a data migration process is required to move the actual 
data to the correct servers. 
* This process should be handled efficiently to minimize downtime.

**22. Monitoring and Tuning.**
* A consistent hashing system should be monitored to track key distribution, server load, and identify potential hotspots. 
* The number of virtual nodes can be tuned to optimize performance.

**23. Considerations for Real-World Implementation.**
* Real-world implementations need to consider factors like network latency, server capacity, and data replication for 
fault tolerance in addition to the core consistent hashing algorithm.

***

## 7 - Design a Key - Value Store.

**1. What is "hinted handoff" and why is it used in distributed key-value stores?**
* Hinted handoff is a mechanism to improve availability during temporary failures. 
* When a node is temporarily unavailable, another node accepts writes on its behalf. 
* Once the unavailable node recovers, the "hinted" data is handed off to it, ensuring eventual consistency.

**2. Explain the CAP theorem. Why is a CA system not suitable for real-world distributed systems?**
* The CAP theorem states that a distributed system can only guarantee two out of three properties: 
  * Consistency.
  * Availability. 
  * Partition Tolerance. 
* CA systems sacrifice partition tolerance, which is unacceptable because network partitions 
(communication breaks) are inevitable in real-world distributed environments.

**3. What are SSTables? How are they used in the write path of a key-value store, and what problem do they solve?**
* SSTables (Sorted String Tables) are sorted lists of key-value pairs stored on disk. 
* In the write path, data from the memory cache is flushed to SSTables. 
* They provide a persistent, sorted storage format that enables efficient reads, 
especially when combined with Bloom filters.

**4. Describe how Merkle trees are used to handle permanent failures and maintain data consistency in a distributed 
key-value store?**
* Merkle trees are hash trees used to efficiently detect inconsistencies between replicas. 
* By comparing the root hashes of two Merkle trees, the system can quickly determine if the replicas have the same data. 
* If not, it can traverse the tree to identify and synchronize only the inconsistent buckets, minimizing data transfer.

**5. Explain the concept of vector clocks and how they are used to resolve write conflicts in an eventually consistent 
key-value store?**
* Vector clocks are `[server, version]` pairs associated with a data item. 
* They track the history of updates to the data.
* By comparing vector clocks, the system can determine if two versions of data are ancestors (one descends from the other) 
or siblings (conflicting updates). 
* This allows the system to detect and resolve conflicts, ensuring that updates are eventually consistent.

**6. What is a "coordinator" node in the context of a distributed key-value store, and what role does it play in read 
and write operations?**
* A coordinator node acts as a proxy between the client and the key-value store cluster. 
* For write operations, it receives the write request, coordinates the replication to multiple nodes, and ensures the 
write quorum is met. 
* For read operations, it retrieves data from the necessary replicas and returns the result to the client.

**7. Explain the concept of "quorum consensus." How do the parameters N, W, and R relate to consistency and availability, 
and what are some typical configurations?**
* Quorum consensus is a method to achieve consistency in distributed systems. 
* N is the number of replicas, W is the write quorum (minimum number of replicas that must acknowledge a write for it to 
be considered successful), and R is the read quorum (minimum number of replicas that must respond to a read request).
  * R=1, W=N: Fast reads.
  * W=1, R=N: Fast writes.
  * W+R > N: Strong consistency.

**8. What is a Bloom filter, and how is it used in the read path of a key-value store? What problem does it solve?**
* A Bloom filter is a probabilistic data structure used to test whether an element is a member of a set. 
* In a key-value store, it's used to quickly check if an SSTable might contain a specific key before actually reading 
the SSTable from disk. 
* This avoids unnecessary disk reads, improving read performance.

**9. Describe the gossip protocol and how it's used for failure detection in a distributed key-value store.**
* The gossip protocol is a decentralized method for failure detection. 
* Each node maintains a membership list with heartbeat counters. 
* Nodes periodically exchange heartbeat information with random peers. 
* If a node's heartbeat counter doesn't increase for a certain period, it's considered offline, and this information is 
propagated through the network.

**10. What are the advantages of using consistent hashing for data partitioning in a distributed key-value store?**
* Consistent hashing offers several advantages:
  * Automatic scaling: Servers can be added or removed without requiring a complete redistribution of data.
  * Load balancing: Data is distributed evenly across servers.
  * Heterogeneity: Servers with different capacities can be assigned a proportional number of virtual nodes.
  * Minimal data movement: When servers are added or removed, only a small portion of the data needs to be moved.


**11. What is a key-value store?**
* A non-relational database that stores data as key-value pairs. 
* Keys are unique identifiers, values can be any data type.

**12. What are the two main operations a key-value store should support?**
* `put(key, value)` to insert/update data, and `get(key)` to retrieve data.

**13. What does the CAP theorem state?**
* It's impossible for a distributed system to simultaneously guarantee Consistency, Availability, and Partition Tolerance. 
* You must choose a tradeoff.

**14. Define Consistency in the context of distributed systems.**
* All clients see the same data at the same time, regardless of which node they connect to.

**15. Define Availability in the context of distributed systems.**
* Every client request receives a response, even if some nodes are down.

**16. Define Partition Tolerance in the context of distributed systems.**
* The system continues to operate despite network partitions (communication breaks between nodes).

**17. What's the difference between CP and AP systems?**
* CP systems prioritize Consistency and Partition Tolerance (sacrifice Availability). 
* AP systems prioritize Availability and Partition Tolerance (sacrifice Consistency).

**18. Why is data partitioning necessary in a distributed key-value store?**
* To distribute data across multiple servers when the dataset is too large for a single server.

**19. What is consistent hashing and why is it useful for data partitioning?**
* A technique that distributes data across servers in a way that minimizes data movement when servers are added or removed.

**20. Why is data replication used in key-value stores?**
* To improve availability and reliability by storing multiple copies of data on different servers.

**21. What is quorum consensus? Define N, W, and R.**
* A method to achieve consistency in replicated data. 
* N = total replicas, W = write quorum (minimum ACKs for a successful write), R = read quorum (minimum responses for a successful read).

**22. How do you guarantee strong consistency using quorums?**
* Ensure that W + R > N. 
* This guarantees that at least one node involved in a read has the latest data.

**23. What is eventual consistency?**
* A consistency model where updates are propagated to all replicas eventually, but there might be a delay during which 
data is inconsistent.

**24. Why is versioning important in eventually consistent systems?**
* To detect and resolve conflicts that arise when concurrent writes occur on different replicas.

**25. What are vector clocks and how are they used?**
* A mechanism for versioning data in distributed systems. 
* They track the history of updates and help detect conflicts between different versions of data.

**26. Why is failure detection important?**
* To identify unavailable nodes in the system so that appropriate actions can be taken (e.g., failover, hinted handoff).

**27. What is the gossip protocol and how does it work?**
* A decentralized failure detection mechanism where nodes periodically exchange membership information with each other.

**28.What is sloppy quorum?**
* Instead of enforcing the quorum requirement, the system chooses the first W healthy servers for writes and first 
R healthy servers for reads on the hash ring. 
* Offline servers are ignored.

**29. What is hinted handoff and how does it improve availability?**
* A mechanism where, if a node is temporarily unavailable, another node accepts writes on its behalf and forwards them 
when the node recovers.

**30. What are Merkle trees used for in key-value stores?**
* To efficiently detect and repair inconsistencies between replicas. 
* They allow for comparing data without transferring the entire dataset.

**31. What is the role of a coordinator node in a distributed key-value store?**
* The coordinator acts as a proxy between the client and the data nodes. 
* It's responsible for routing requests, coordinating reads/writes, and handling consistency.

**32. Describe the typical write path in a key-value store.**
* Write to Commit Log (for durability). 
* Write to Memory Cache.
* Asynchronously flush data from Memory Cache to SSTables on disk.

**33. What are SSTables?**
* Sorted String Tables. 
* They are immutable, sorted files on disk that store key-value pairs. 
* New writes don't modify existing SSTables; instead, new SSTables are created.

**34. Describe the typical read path in a key-value store.**
* Check Memory Cache. 
* If not found, check Bloom Filter to identify relevant SSTables. 
* Read data from SSTables on disk.

**35. What are Bloom filters and why are they used in the read path?**
* Probabilistic data structures used to efficiently test whether an element is a member of a set. 
* In key-value stores, they help quickly determine if an SSTable might contain a specific key, 
avoiding unnecessary disk reads.

***

## 8 - Design A Unique ID Generator In Distributed Systems.

**1. Problem Definition.**
* Problem: Generate unique, sortable 64-bit numerical IDs in a distributed system at a rate of 10,000+ per second.
* Why it's tricky: Traditional auto-incrementing DB keys don't work well in distributed environments due to scaling and 
coordination issues.
* Requirements: Uniqueness, numerical, 64-bit, sortable by date/time, high throughput.

**2. Multi-Master Replication & UUID.**
* Multi-Master Replication: Uses multiple DBs with auto-increment, incrementing by k (number of servers).
  * Cons: Hard to scale across data centers, IDs not time-ordered across servers, difficult to add/remove servers.
* UUID: 128-bit universally unique identifier.
  * Cons: Too large (128-bit vs. 64-bit requirement), not time-ordered, non-numeric.

**3. Ticket Server.**
* Ticket Server: Centralized auto-increment server.
* Pros: Simple to implement, numeric IDs.
* Cons: Single point of failure. Multiple ticket servers introduce data synchronization challenges.

**4. Snowflake Approach - ID Structure.**
* Snowflake: Divides the 64-bit ID into sections:
  * 1 bit: Sign bit (always 0).
  * 41 bits: Timestamp (milliseconds since epoch).
  * 5 bits: Datacenter ID.
  * 5 bits: Machine ID.
  * 12 bits: Sequence number (incremented per ID generated in a millisecond, resets every millisecond).

**5. Snowflake - Key Concepts.**
* Timestamp: Provides time-based sorting. 41 bits allows for ~69 years of operation from a custom epoch.
* Sequence Number: Handles multiple IDs generated on the same machine within the same millisecond (up to 4096 IDs).
* Clock Synchronization: Important consideration to ensure consistent timestamp generation across servers (NTP is a common solution).
* High Availability: ID generator is a critical system and needs to be highly available.

**6. Why can't traditional database auto-increment be used in distributed systems for unique ID generation?**
* Single DB server not large enough. 
* Generating unique IDs across multiple DBs with minimal delay is challenging.

**7. List the key requirements for the unique ID generator.**
* Unique, numerical, 64-bit, ordered by date, generate > 10,000 IDs/second.

**8. How does multi-master replication work for ID generation?**
* Each DB server increments IDs by k (number of DB servers).

**9. What are the drawbacks of multi-master replication?**
* Hard to scale across data centers, IDs not time-ordered across servers, scaling issues when adding/removing servers.

**10. What is a UUID?**
* A 128-bit number with a very low probability of collision.

**11. What are the advantages of using UUIDs?**
* Simple to generate, no coordination needed, easy to scale.

**12. What are the disadvantages of using UUIDs for this scenario?**
* 128-bit (requirement is 64-bit).
* Not time-ordered.
* Non-numeric.

**13. What is a ticket server?**
* A centralized server with auto-increment feature.

**14. What are the advantages of using a ticket server?**
* Numeric IDs, easy to implement for small/medium scale.

**15. What are the disadvantages of using a ticket server?**
* Single point of failure, complex data synchronization with multiple ticket servers.

**16. What is the core idea behind the Snowflake approach?**
* Divide the ID into different sections.

**17. What are the sections of the 64-bit ID in the Snowflake design?**
* Sign bit, Timestamp, Datacenter ID, Machine ID, Sequence Number.

**18. What is the purpose of the sign bit?**
* Reserved for future use (always 0 in this design).

**19. What is the timestamp section used for?**
* Stores milliseconds since the epoch, ensures IDs are sortable by time.

**20. What do Datacenter and Machine IDs represent?**
* Datacenter ID identifies the datacenter, Machine ID identifies the machine within the datacenter.

**21. What is the sequence number used for?**
* Incremented for each ID generated in the same millisecond on the same server.

**22. What is the maximum lifetime of the ID generator with a 41-bit timestamp?**
* Approximately 69 years.

**23. How many IDs can a machine generate per millisecond with a 12-bit sequence number?**
* "4096".

**24. What is the clock synchronization problem?**
* Ensuring all ID generation servers have the same clock, especially in multi-core/multi-machine scenarios.

**25. Why is high availability important for an ID generator?**
* It's a mission-critical system; downtime affects many other services.

**26. What is the purpose of the epoch time in the Snowflake design?**
* It's the starting point for the timestamp. 
* Using a custom epoch close to the current date extends the usable lifetime of the ID generator before the timestamp overflows.

**27. Why might you want to adjust the bit lengths of the different sections (timestamp, datacenter ID, machine ID, sequence number)?**
* To optimize for different use cases. 
* For example, fewer sequence number bits and more timestamp bits for low-concurrency, long-term applications.

**28. When are Datacenter and Machine IDs typically assigned, and why is it important to be careful when changing them?**
* Assigned at startup. 
* Changing them can lead to ID conflicts if not done carefully.

**29. What are some alternatives to Network Time Protocol (NTP) for clock synchronization?**
* While NTP is the most popular, other options include Precision Time Protocol (PTP) and solutions based on atomic clocks 
for higher accuracy. 
* (Note: The chapter mentions NTP but doesn't go into alternatives, but it's a good point to consider).

***

## 9 - Design A URL Shortener.

**1. What are the essential components of a URL shortening system, and how do they interact?**
* API Layer: Handles incoming requests for shortening and redirecting URLs.
* Data Store (Database): Persistently stores the mappings between short URLs and long URLs.
* Cache: Temporarily stores frequently accessed URL mappings for faster retrieval.
* Hash Function/ID Generator: Generates unique short URLs from long URLs or unique IDs.
* Load Balancer: Distributes incoming traffic across multiple web servers.

**2. What are the key scalability challenges in designing a URL shortener, and how can they be addressed?**
* High Traffic: Use load balancing, caching, and a scalable database.
* Large Data Volume: Employ database sharding and replication.
* Unique ID Generation: Use a distributed unique ID generator.
* Read-Heavy Workload: Implement caching to reduce database load.

**3. What are the trade-offs between using "Hash + Collision Resolution" and "Base 62 Conversion" for generating short URLs?**
* Hash + Collision Resolution: Fixed-length short URLs, but requires collision detection and resolution, which can impact 
performance. Doesn't need unique ID generator.
* Base 62 Conversion: Short URL length varies with ID, but guarantees uniqueness (no collisions). Depends on a unique 
ID generator. Easier to figure out the next available short URL, which can be a security concern.

**4. Summarize the end-to-end process of shortening a URL, from the initial request to storing the mapping.**
* Receive long URL via API.
* Check if long URL exists in the database. If so, return existing short URL.
* If not, generate a unique ID.
* Convert the ID to a short URL using Base 62.
* Store the long URL, short URL, and ID in the database.
* Return the short URL to the user.

**5. Describe the end-to-end process of redirecting a short URL to its original long URL.**
* User clicks on a short URL.
* Request is routed to a web server via a load balancer.
* Web server checks if the short URL is in the cache. If so, return the long URL (using a 301 or 302 redirect).
* If not in the cache, query the database for the long URL.
* If found, store the mapping in the cache and return the long URL (using a 301 or 302 redirect).
* If not found in the database, return an error.

**6. What are the primary use cases for a URL shortener?**
* URL shortening (long URL -> short URL), URL redirection (short URL -> long URL), High availability/scalability.

**7. What are the two main API endpoints needed?**
* POST `api/v1/data/shorten` (for creating short URLs) and GET `api/v1/shortUrl` (for redirecting).

**8. What's the difference between 301 and 302 redirects, and when would you use each?**
* 301 is permanent (browser caches). 
* 302 is temporary (always hits the URL shortener). 
* Use 301 to reduce server load, 302 for analytics.

**9. What's a basic data model for storing URL mappings?**
* A table with columns for id (primary key), shortURL, and longURL.

**10. What are the key requirements for a hash function in a URL shortener?**
* Must map each longURL to one hashValue, and each hashValue must be mappable back to the original longURL.

**11. How do you determine the appropriate length for the shortened URL (hash value)?**
* Calculate based on the number of URLs the system needs to support (e.g., 62^n >= total URLs).

**12. What characters are typically allowed in a shortened URL?**
* Numbers (0-9), lowercase letters (a-z), and uppercase letters (A-Z). 
* (Total of 62 characters).

**13. Explain the "hash + collision resolution" approach.**
* Use a standard hash function, take the first n characters, and if a collision occurs, append a string and retry.

**14. What are bloom filters and how can they improve performance?**
* Space-efficient, probabilistic data structure used to test whether an element is a member of a set. 
* It helps to reduce the number of DB queries to check for collisions.

**15. Explain the "base 62 conversion" approach.**
* Convert a unique ID (usually an incrementing integer) into a base-62 representation to create the short URL.

**16. How are numbers mapped to characters in Base 62?**
* 0-9 map to themselves, 10-35 map to a-z, and 36-61 map to A-Z.

**17. Which approach ("hash + collision resolution" vs "base 62 conversion") results in fixed-length short URLs?**
* "Hash + collision resolution" provides fixed length, while "base 62 conversion" does not.

**18. Why is a unique ID generator important for the base 62 conversion method?**
* It provides a unique input for the base 62 conversion, ensuring no collisions.

**19. Describe the steps in the URL shortening process.**
* Check if longURL exists in DB -> If yes, return shortURL -> If no, generate unique ID -> Convert ID to shortURL (base 62) -> Save mapping to DB.

**20. Describe the steps in the URL redirecting process.**
* User clicks shortURL -> Load balancer directs to web server -> Check cache -> If in cache, return longURL ->
If not, check DB -> Return longURL (or error if not found).

**21. Why is caching important in a URL shortener?**
* Improves performance by storing frequently accessed shortURL-longURL mappings, reducing database load.

**22. Why is rate limiting important?**
* Prevents abuse by limiting the number of requests from a single IP address or user within a given time frame.

**23. How can the web server tier be scaled?**
* Horizontally, by adding more web servers behind a load balancer. 
* The web tier is stateless.

**24. What are common techniques for scaling the database?**
* Replication and sharding.

**25. What kind of analytics can be collected for a URL shortener?**
* Click count, time of clicks, source of clicks, etc.

**26. How do you estimate the storage requirements for a URL shortener over a period of time?**
* (Number of URLs) * (Average URL Length) = Total Storage. 
* Remember to factor in the time period (e.g., 10 years).

**27. What are the advantages of using the "Hash + Collision Resolution" method?**
* Fixed short URL length, doesn't necessarily need a unique ID generator.

**28. What are the disadvantages of using the "Hash + Collision Resolution" method?**
* Collision is possible and needs to be resolved, making it more complex.

**29. What are the advantages of using the "Base 62 Conversion" method?**
* Collision is not possible because ID is unique.

**30. What are the disadvantages of using the "Base 62 Conversion" method?**
* Short URL length is not fixed. 
* It goes up with the ID. 
* Exposes a possible security concern of being able to guess/crawl URLs if IDs are sequential.

***

## 10 - Design A Web Crawler.

**1. Core Crawling Process.**
* Concept: Basic Web Crawler Algorithm.
* Explanation: Start with seed URLs, download web pages, extract new URLs, and repeat. 
* Scalability, robustness, politeness, and extensibility are key considerations.
* Components: URL Frontier, HTML Downloader, Content Parser, URL Extractor, URL Filter.

**2. URL Frontier.**
* Concept: Managing URLs to be downloaded.
* Explanation: Addresses politeness (avoiding overloading servers), prioritization (crawling important pages first), 
and freshness (recrawling updated pages). 
* Uses front queues (prioritization) and back queues (politeness).
* Tech: FIFO queues, mapping tables (host to queue), prioritizers, hybrid storage (memory buffer + disk).

**3. Politeness and Prioritization.**
* Concept: Ensuring responsible and efficient crawling.
* Politeness: Download one page at a time from the same host, with a delay. Achieved using queues per host.
* Prioritization: Prioritize URLs based on PageRank, website traffic, update frequency.

**4. HTML Downloader & Robustness.**
* Concept: Downloading web pages efficiently and reliably.
* HTML Downloader: Uses DNS Resolver, respects robots.txt, optimizes performance with distributed crawling, DNS caching, 
locality, and short timeouts.
* Robustness: Achieved through consistent hashing, saving crawl states, exception handling, and data validation.

**5. Avoiding Problematic Content.**
* Concept: Identifying and preventing issues during crawling.
* Redundant Content: Use hashes/checksums to detect duplicate pages.
* Spider Traps: Limit URL length, monitor website behavior.
* Data Noise: Filter out advertisements, spam, etc.

**6. What is a web crawler?**
* A program (robot/spider) that systematically browses the web, discovering and indexing content. 
* Used by search engines, web archiving, data mining, and web monitoring.

**7. What are the 3 basic steps of a web crawler?**
* Download web pages from a set of URLs.
* Extract URLs from those pages. 
* Add new URLs to the download list and repeat.

**8. Why is scalability important for a web crawler?**
* The web is vast (billions of pages). 
* Crawlers must be efficient and use parallelization to handle the load.

**9. What does robustness mean in the context of web crawlers?**
* Handling bad HTML, unresponsive servers, crashes, and malicious links gracefully without failing.

**10. Why should a web crawler be "polite"?**
* Avoid making too many requests to a single website in a short time to prevent overwhelming the server 
(avoid being seen as a DOS attack).

**11. What is extensibility in web crawler design?**
* The system should be flexible to support new content types (e.g., images, PDFs) with minimal changes.

**12. What are seed URLs?**
* The starting point URLs for the crawling process. 
* Choosing good seeds is important for broad coverage.

**13. What is the URL Frontier?**
* A data structure that stores URLs to be downloaded. 
* It manages politeness, prioritization, and freshness.

**14. What is the role of the HTML Downloader?**
* Downloads web pages from the internet, given URLs from the URL Frontier.

**15. Why is a DNS Resolver needed?**
* Translates URLs (domain names) into IP addresses, which are necessary to connect to web servers.

**16. What does the Content Parser do?**
* Parses and validates downloaded HTML pages to ensure they are well-formed and don't cause issues.

**17. What is the purpose of the "Content Seen?" component?**
* Detects duplicate content to avoid storing the same information multiple times, saving storage space and processing time.

**18. What is the URL Extractor responsible for?**
* Parses HTML pages and extracts all the links (URLs) found within them.

**19. What is the URL Filter's job?**
* Excludes certain content types, file extensions, error links, and URLs from blacklisted sites.

**20. What is the "URL Seen?" component used for?**
* Keeps track of URLs that have already been visited or are in the Frontier to avoid processing the same URL multiple times.

**21. Which search algorithm (DFS or BFS) is generally preferred for web crawling and why?**
* BFS (Breadth-First Search) is preferred because DFS can go too deep and get stuck.

**22. What is robots.txt and why is it important?**
* A file that tells crawlers which parts of a website they are allowed to access. 
* Crawlers should respect these rules.

**23. How can politeness be implemented in a web crawler?**
* By using a queue router and mapping table to ensure that each queue only contains URLs from the same host, 
and worker threads download one page at a time from the same host with a delay.

**24. How can URLs be prioritized in a web crawler?**
* Based on usefulness, measured by PageRank, website traffic, update frequency, etc.

**25. How can duplicate content be detected efficiently?**
* By comparing hash values of web pages instead of comparing the content character by character.

**26. What is "freshness" in the context of web crawling, and why is it important?**
* Keeping the crawled data up-to-date by periodically re-crawling pages, especially important ones, based on their update history.

**27. What are spider traps, and how can they be avoided?**
* Web pages that cause crawlers to enter infinite loops. 
* Can be avoided by setting a maximum URL length and manually excluding problematic websites.

**28. What is distributed crawling, and why is it used?**
* Distributing the crawl workload across multiple servers, each with multiple threads, to achieve high performance and scalability.

**29. What is "data noise" in web crawling?**
* Contents with little or no value, such as advertisements, code snippets, and spam URLs, which should be excluded.

***

## 11 - Design A Notification System.

**1. What are the main types of notifications supported, and what 3rd party services are commonly used for each?**
* Types: Push Notifications (iOS & Android), SMS, Email.
* iOS Push: APNs (Apple Push Notification Service).
* Android Push: FCM (Firebase Cloud Messaging).
* SMS: Twilio, Nexmo.
* Email: Sendgrid, Mailchimp.

**2. Describe the key components of the improved high-level architecture for a notification system.**
* Services (1 to N): Trigger notifications via APIs.
* Notification Servers: API endpoints, validation, data retrieval, enqueue messages.
* Cache: Store user/device info, templates.
* DB: Store user, notification, settings data.
* Message Queues: Decouple components, buffer messages (separate queues per notification type).
* Workers: Process messages from queues, send to 3rd party services.

**3. How does the notification system ensure reliability and prevent data loss?**
* Persistence: Store notification data in a database (Notification Log).
* Retry Mechanism: If sending fails, retry from the message queue.
* Deduplication: Implement logic to discard duplicate notifications based on event ID.

**4.  List important additional components and considerations for a robust notification system.**
* Notification Templates: Preformatted notifications for consistency and efficiency.
* Notification Settings: User preferences for notification types (opt-in/out).
* Rate Limiting: Prevent overwhelming users with too many notifications.
* Security: AppKey/appSecret for authenticated API access.
* Monitoring: Track queued notifications, system health.
* Event Tracking: Track open rates, click rates, engagement for analytics.

**5. What key features were added to the updated notification system design?**
* Authentication: Verify clients sending notifications.
* Rate Limiting: Control notification frequency.
* Retry Mechanism: Handle sending failures.
* Notification Templates: Streamline notification creation.
* Monitoring & Tracking: System health and analytics.

**6. Design A Notification System.**
* Key considerations for building a system to send push notifications, SMS, and emails. 
* Focus on scalability, reliability, and extensibility.

**7. Notification Types.**
* Push (iOS/Android), SMS, Email. 
* Each has unique delivery mechanisms (APNs, FCM, SMS Gateways, Email Services).

**8. Real-time vs. Soft Real-time.**
* Aim for fast delivery, but accept slight delays under heavy load.

**9. Contact Info Gathering.**
* Collect device tokens, phone numbers, and emails during app install/signup. 
* Store securely in a database.

**10. User and Device Tables.**
* User table stores user_id, email, phone number. 
* Device table stores device_token, user_id. 
* One user can have multiple devices.

**11. High-Level Architecture.**
* Services -> Notification System -> Third-Party Services (APNs, FCM, SMS, Email) -> User Devices.

**12. Single Point of Failure (SPOF).**
* Initial design had a single notification server, creating a SPOF.

**13. Scalability Issues.**
* The initial design was hard to scale due to a single server handling all notification types.

**14. Performance Bottleneck.**
* A single server can become overloaded during peak hours due to resource-intensive tasks.

**15. Improved Architecture.**
* Services -> Notification Servers -> Message Queues -> Workers -> Third-Party Services -> User Devices.

**16. Notification Servers.**
* Provide APIs, validate data, fetch user/device info, and enqueue notifications.

**17. Message Queues.**
* Decouple components, buffer high volumes, and isolate notification types (iOS, Android, SMS, Email).

**18. Workers.**
* Pull notification events from queues and send them to third-party services.

**19. Data Loss Prevention.**
* Persist notification data in a database (Notification Log) and implement a retry mechanism.

**20. Deduplication.**
* Implement a dedupe mechanism using event IDs to reduce duplicate notifications.

**21. Notification Templates.**
* Preformatted notifications with customizable parameters to ensure consistency and save time.

**22. Notification Settings.**
* Allow users to opt-in/out of notification types (push, email, SMS). 
* Store settings in a database.

**23. Rate Limiting.**
* Limit the number of notifications a user receives to avoid overwhelming them.

**24. Security.**
* Use appKey/appSecret pairs to authenticate clients sending push notifications.

**25. Monitoring & Tracking.**
* Track key metrics like open rate, click rate, and queue length. 
* Monitor system health and performance.

**26. Extensibility.**
* Design the system to easily plug in or unplug third-party services. 
* Consider alternatives for different markets (e.g., FCM vs. JPush in China).

**27. API Design.**
* Design APIs for services to send notifications. 
* Include parameters for recipient, sender, subject, and content. 
* Secure APIs to prevent spam.

**28. Caching.**
* Cache user info, device info, and notification templates to reduce database load and improve performance.

***

## 12 - Design A News Feed System.

**1. Core Functionality & APIs.**
* Concept: News feed systems deliver a constantly updating stream of content (posts, media, etc.) from followed users/entities.
* Key Features: Publishing posts, retrieving personalized news feeds.
* APIs:
  * POST `/v1/me/feed`: Publishes a new post (content, auth_token).
  * GET `/v1/me/feed`: Retrieves news feed (auth_token).

**2. Feed Publishing Flow.**
* Concept: How posts are created and distributed.
* Components:
  * Web Servers: Authentication, rate limiting.
  * Post Service: Persists post data (DB + Cache).
  * Fanout Service: Distributes post to friends' feeds.
  * Notification Service: Alerts users to new content.

**3. Fanout Strategies.**
* Concept: Methods for distributing posts to friends' news feeds.
* Fanout on Write (Push):
  * Pros: Fast read times.
  * Cons: Hotkey problem (users with many friends), wasted resources for inactive users.
* Fanout on Read (Pull):
  * Pros: Efficient for inactive users, avoids hotkey.
  * Cons: Slower read times.
* Hybrid Approach: Push for most users, pull for high-follower users (celebrities). Consistent hashing helps distribute load.

**4. News Feed Retrieval.**
* Concept: How news feeds are generated and delivered to users.
* Process:
  * Request to `/v1/me/feed`.
  * Load balancer directs to web servers.
  * News Feed Service fetches post IDs from News Feed Cache.
  * Hydration: Retrieves full user and post data from User Cache and Post Cache.
  * Returns fully formed news feed (JSON).
  * CDN delivers media content.

**5. Caching Strategy.**
* Concept: Caching is crucial for performance.
* Cache Layers:
  * News Feed: Post IDs in the feed.
  * Content: Post data (hot vs. normal).
  * Social Graph: User relationships (followers/following).
  * Action: User interactions (likes, replies).
  * Counters: Like counts, reply counts, etc.

**6. Key Technologies.**
* Concept: The technologies used to build a scalable and efficient news feed system.
* Core Components & Technologies:
* Load Balancers: Distribute traffic across web servers.
* Web Servers: Handle requests, authentication, rate limiting.
* Caching (Redis, Memcached): Multiple layers for fast data retrieval (news feed IDs, post content, user data, social graph).
* Databases:
  * Post DB (SQL or NoSQL): Stores post data.
  * Graph DB (Neo4j): Manages user relationships (friends, followers).
  * User DB (SQL or NoSQL): Stores user information.
* Message Queue (Kafka, RabbitMQ): Asynchronous task processing for fanout.
* CDN (Content Delivery Network): Stores and delivers media content (images, videos).
* Consistent Hashing: Distributes data and requests evenly to mitigate hotkey problems.
* APIs: RESTful APIs (HTTP).

**7. News Feed Definition?**
* What is a news feed? (constantly updating list of stories).
* Examples: Facebook, Instagram, Twitter
* Key content: status updates, photos, videos, links, etc.

**8. Core Features.**
* Publishing posts.
* Seeing friends' posts.
* Reverse chronological order (for simplicity).

**9. Key Questions to Ask.**
* Mobile, web, or both?
* Important features?
* Sorting order?
* Number of friends?
* Traffic volume (DAU)?
* Content types (text, images, videos)?

**10. High-Level Design - Feed Publishing.**
* User posts -> Load Balancer -> Web Servers.
* Web Servers -> Post Service, Fanout Service, Notification Service.

**11. High-Level Design - News Feed Building.**
* User requests feed -> Load Balancer -> Web Servers -> News Feed Service -> News Feed Cache.

**12. Feed Publishing API.**
* POST `/v1/me/feed`.
* Params: content, auth_token.

**13. News Feed Retrieval API.**
* GET `/v1/me/feed`.
* Params: auth_token.

**14. Post Service.**
* Persists posts in database and cache (Post Cache, Post DB).

**15. Fanout Service.**
* Pushes new content to friends' news feeds.
* Newsfeed data stored in cache for fast retrieval.

**16. Notification Service.**
* Informs friends of new content.
* Sends push notifications.

**17. Web Servers (Feed Publishing).**
* Authentication (auth_token).
* Rate limiting (prevent spam).

**18. Fanout on Write (Push Model).**
* News feed pre-computed at write time.
* Pros: Real-time, fast fetching.
* Cons: Hotkey problem, wasted resources for inactive users.

**19. Fanout on Read (Pull Model).**
* News feed generated at read time (on-demand).
* Pros: Efficient for inactive users, no hotkey problem.
* Cons: Slower fetching.

**20. Hybrid Fanout Approach.**
* Push for most users.
* Pull for celebrities/users with many followers.
* Consistent hashing to mitigate hotkey problem.

**21. Fanout Service Details.**
* Fetch friend IDs (Graph DB).
* Get friend info (User Cache).
* Send to Message Queue.
* Fanout Workers update News Feed Cache.

**22. News Feed Cache (Fanout).**
* Stores `<post_id, user_id>` mapping.
* Limits memory usage with configurable limit.

**23. News Feed Retrieval Details.**
* User requests feed (`/v1/me/feed`).
* Web servers call News Feed Service.
* News Feed Service gets post IDs from cache.
* Fetches user/post objects from caches.

**24. CDN for Media Content.**
* Images, videos stored in CDN for fast retrieval.

**25. Cache Architecture Layers.**
* News Feed, Content, Social Graph, Action, Counters.

**26. Scaling Considerations.**
* Database scaling (vertical/horizontal, SQL/NoSQL, replication, sharding).
* Stateless web tier.
* Caching.
* Multiple data centers.
* Message queues/
* Monitoring (QPS, latency).

**27. Graph Database.**
* Used for managing friend relationships and recommendations.
* Why? Efficiently handles complex relationship queries.

**28. Message Queue.**
* Used for asynchronous communication between services (e.g., Fanout Service and Fanout Workers).
* Why? Decouples services, improves reliability, and handles bursts of traffic.

**29. Consistency Models.**
* Important consideration when scaling the database.
* Examples: eventual consistency, strong consistency.
* Trade-offs between consistency and performance.

**30. Hotkey Problem.**
* Occurs when a single key (e.g., a celebrity user) receives a disproportionate amount of traffic.
* Mitigation: Consistent hashing, caching, and fanout on read for high-traffic users.

***

## 13 - Design A Chat System.
**1. Core Requirements.**
* 1-on-1 chat.
* Group chat (max 100 users).
* Online presence.
* Multiple device support.
* Push notifications.
* 50 Million Daily Active Users.

**2. Communication Protocol.**
* WebSocket: Preferred for bidirectional, persistent connections between clients and the chat service. Enables real-time updates.
* HTTP: Suitable for stateless operations like login, signup, and profile management.

**3. System Architecture - Stateless Services.**
* Handle user authentication, profile management, group management, etc.
* Sit behind a load balancer.
* Include service discovery (e.g., ZooKeeper) to locate appropriate chat servers for clients.

**4. System Architecture - Stateful Service.**
* Chat Service: The core stateful component. Maintains persistent WebSocket connections with clients.
* Requires efficient connection management.

**5. System Architecture - Third-Party.**
* Push Notifications: Crucial for informing users of new messages when the app is not active.

**6. Key Components.**
* Chat Servers: Send and receive messages.
* Presence Servers: Manage online/offline status.
* API Servers: Handle user-related operations (login, signup, etc.).
* Notification Servers: Send push notifications.
* Key-Value Store: Persist chat history.

**7. Data Storage.**
* Relational Databases: For generic data (user profiles, settings, friend lists).
* Key-Value Stores (NoSQL): For chat history. Offer scalability, low latency, and handle large data volumes well. 
* Examples: Cassandra, HBase.

**8. Message IDs.**
* Ensure message ordering.
* Requirements: Unique IDs, sortable by time.
* Options: Snowflake, local sequence number generators.

**9. Online Presence Implementation.**
* Heartbeat mechanism: Clients periodically send heartbeat events to presence servers.
* Absence of heartbeat within a defined interval indicates offline status.
* Publish-subscribe model for broadcasting status changes to friends.

**10. Scalability Considerations.**
* Load balancing to distribute traffic.
* Horizontal scaling of chat servers and databases.
* Caching to reduce latency and load on servers.
* Service discovery for dynamic server selection.

**11. Requirements.**
* Question: 1-on-1 or group chat?
* Mobile, web, or both?
* Scale (DAU)?
* Group member limit?
* Features (attachments, text-only)?
* Message size limit?
* End-to-end encryption?
* Chat history storage duration?

**12. Core Chat Service Functions.**
* Receive messages from clients.
* Route messages to recipients.
* Store messages for offline users.

**13. Communication Protocols - HTTP.**
* Good for sender side (client-initiated).
* Keep-alive header for persistent connections.
* Efficient for sending messages.

**14. Communication Protocols - Polling.**
* Client periodically asks the server for new messages.
* Inefficient due to wasted resources when no new messages.

**15. Communication Protocols - Long Polling.**
* Client holds connection open until new message or timeout.
* Drawbacks: Load balancing issues, difficulty detecting disconnections, inefficiency for infrequent chatters.

**16. Communication Protocols - WebSockets.**
* Bidirectional, persistent connection.
* Starts as HTTP, "upgraded" to WebSocket.
* Efficient for real-time updates.
* Uses ports 80/443 (firewall friendly).

**17. High-Level Architecture - Stateless Services.**
* Traditional request/response services (login, signup, profile).
* Behind a load balancer.
* Example: Service discovery (Zookeeper).

**18. High-Level Architecture - Stateful Service.**
* Chat service.
* Maintains persistent connections with clients.
* Requires efficient connection management.

**19. High-Level Architecture - Third-Party Integration.**
* Push notifications (crucial for chat apps).

**20. Scalability Considerations.**
* Single server is a bad idea (single point of failure).
* Start with single server for simplicity, but plan for scaling.

**21. System Components.**
* Chat servers: Send/receive messages.
* Presence servers: Online/offline status.
* API servers: User management, etc.
* Notification servers: Push notifications.
* Key-value store: Chat history.

**22. Storage - Data Types.**
* Generic data (user profiles): Relational databases.
* Chat history: Key-value stores.

**23. Storage - Chat History Read/Write.**
* Enormous data volume.
* Recent chats accessed frequently.
* Random access needed for search, etc.
* Read/write ratio ~1:1.

**24. Storage - Key-Value Store Benefits.**
* Horizontal scaling.
* Low latency.
* Handles long tail data well.
* Used by other chat apps (Facebook, Discord).

**25. Data Models - 1-on-1 Message Table.**
* Columns: message_id, message_from, message_to, content, created_at.
* Primary key: message_id (for message sequence).

**26. Data Models - Group Chat Message Table.**
* Columns: channel_id, message_id, user_id, content, created_at.
* Composite primary key: (channel_id, message_id).
* channel_id is the partition key.

**27. Message ID Generation.**
* Requirements: Unique, sortable by time.
* Options: Auto-increment (not NoSQL), Snowflake (global), local sequence number generator.

**28. Service Discovery (Zookeeper).**
* Registers chat servers.
* Chooses best server for client (location, capacity).

**29. Online Presence - Heartbeat Mechanism.**
* Client sends periodic heartbeat events.
* Server considers user online if heartbeat received within a time window.
* Avoids frequent status changes due to brief disconnects.

**30. Online Presence - Status Fanout.**
* Presence servers use publish-subscribe.
* Each friend pair maintains a channel.
* Status changes published to relevant channels.

**31. Message Synchronization Across Multiple Devices.**
* Each device maintains cur_max_message_id.
* New messages: recipient ID matches user, message ID > cur_max_message_id.
* Each device fetches new messages independently.

**32. Small Group Chat Flow.**
* Message copied to each group member's message sync queue (inbox).
* Simplifies message sync, each client checks own inbox.
* Scales well for small groups (e.g., < 500 members).

***

## 14 - Design A Search Autocomplete System.

**1. Autocomplete Definition.**
* Front end feature that predicts and suggests queries as the user types. 
* Also known as typeahead or search-as-you-type.

**2. Core Requirements.**
* Fast response time (<=100ms), relevance, sorted results (by popularity), scalability, high availability.

**3. Data Gathering Service.**
* Collects and aggregates user input queries. 
* Can be real-time (for up-to-the-second results) or batched (e.g., weekly).

**4. Query Service.**
* Receives a search query prefix and returns the top k most frequently searched terms.

**5. Trie Data Structure.**
* A tree-like data structure optimized for storing and retrieving strings based on prefixes. 
* Each node represents a character or prefix.

**6. Trie Optimizations.**
* Limit the maximum length of a prefix to reduce search space. 
* Cache the top k search queries at each node for faster retrieval.

**7. Data Aggregation.**
* Raw search query logs are aggregated to count query frequencies over a period (e.g., weekly).

**8. Workers.**
* Asynchronous processes that build the trie data structure from aggregated data and store it in a database.

**9. Trie Cache.**
* In-memory distributed cache that stores the trie for fast read access by the query service.

**10. Trie DB.**
* Persistent storage for the trie data. Can be a document store (e.g., MongoDB) or a key-value store.

**11. Query Service Architecture.**
* Load balancer distributes requests to API servers, which fetch data from the Trie Cache. 
* Cache misses are handled by querying the Trie DB.

**12. Front-End Optimizations.**
* Use AJAX requests to avoid full page reloads. 
* Implement browser caching to store autocomplete suggestions locally.

**13. Trie Operations.**
* Create: Built from aggregated data. 
* Update: Performed weekly (full trie rebuild) or by updating individual nodes (less efficient). 
* Delete: Filter layer to remove unwanted suggestions.

**14. Scaling Storage (Sharding).**
* Partitioning the trie data across multiple servers. 
* Naive approach: shard by the first character. 
* Better approach: analyze historical data for even distribution.

**15. Multi-Language Support.**
* Use Unicode to store characters from different languages. 
* Consider building separate tries for different countries/regions.

**16. What is the core goal of a search autocomplete system?**
* To provide fast, relevant, and sorted suggestions as a user types a search query.

**17. List the key functional requirements.**
* Fast response time, relevant suggestions, sorted results (by popularity), scalability, high availability.

**18. What is the purpose of the data gathering service?**
* To collect and aggregate user search queries for building the autocomplete index.

**19. What is the role of the query service?**
* To receive a search query prefix and return the top k most frequent suggestions.

**20. What data structure is commonly used for autocomplete and why?**
* Trie (prefix tree). Efficient for prefix-based search and storage of strings.

**21. What does the root node in a trie represent?**
* An empty string.

**22. What does each node in a trie store?**
* A character and, in the optimized version, frequency information and top k suggestions.

**23. What is the time complexity to find top k queries in a trie (optimized)?**
* O(1) - constant time, after optimizations like caching top queries at each node.

**24. What are the two main optimizations for a trie in an autocomplete system?**
* Limit the max length of a prefix. 
* Cache top search queries at each node.

**25. Why limit the maximum length of a search prefix?**
* Because users rarely type very long queries, and it improves search time complexity.

**26. Why cache the top k search queries at each node in the trie?**
* To avoid traversing the entire trie for each query, significantly improving response time.

**27. What is the purpose of analytics logs in the data gathering service?**
* To store raw, unindexed data about search queries with timestamps.

**28. What do aggregators do in the data gathering service?**
* They process and aggregate the raw data from analytics logs into a more usable format (e.g., weekly query frequencies).

**29. What is the role of workers in the data gathering service?**
* To build the trie data structure from the aggregated data and store it in the Trie DB.

**30. What are the two options for storing the trie data persistently?**
* Document store (e.g., MongoDB) for serialized trie snapshots. 
* Key-value store, mapping trie nodes to key-value pairs.

**31. What is the purpose of the Trie Cache?**
* To store the trie in memory for fast read access by the query service.

**32. How does using AJAX improve the user experience?**
* By allowing the browser to send/receive requests/responses without refreshing the whole page.

**33. Why use browser caching for autocomplete suggestions?**
* Because suggestions often don't change rapidly, caching reduces server load and improves response time.

**34. Why is sharding necessary, and how can it be implemented?**
* Sharding distributes the trie across multiple servers to handle large datasets. 
* Sharding can be based on the first character of the query, but a shard map manager is better for even distribution.

**35. How can the system be extended to support multiple languages?**
* By storing Unicode characters in the trie nodes and potentially building different tries for different countries, 
possibly stored in CDNs.

**36. Why is data sampling important in a large-scale autocomplete system?**
* Logging every search query requires significant processing power and storage. 
* Data sampling reduces the load by only logging a fraction of the requests.

**37. What is the recommended approach for updating the trie and why?**
* Rebuild the trie weekly using aggregated data. 
* This is more efficient than updating individual nodes frequently.

**38. Why is updating individual trie nodes directly generally avoided?**
* It's a slow operation. 
* When a node is updated, all its ancestors up to the root must also be updated to maintain the correct top queries.

**39. How are hateful or inappropriate autocomplete suggestions handled?**
* A filter layer is added in front of the Trie Cache to filter out unwanted suggestions based on defined rules. 
* The suggestions are then removed asynchronously from the database.

**40. What is the purpose of the Shard Map Manager?**
* The Shard Map Manager maintains a lookup database to identify which shard a query should be stored on, enabling more 
even data distribution across shards.

***

## 15 - Design YouTube.

**1. Overview.**
* Topic: Designing a video streaming service like YouTube.
* Core Features: Video upload, video streaming.
* Key Considerations: Scalability, availability, cost, international support, various client devices.

**2. High-Level Architecture.**
* Components: Client, CDN, API Servers.
* Client: Access via web, mobile, smart TV.
* CDN: Stores and streams videos.
* API Servers: Handles everything else (metadata, user accounts, recommendations, etc.).

**3. Video Upload Flow.**
* Process: Upload video file, update video metadata (in parallel).
* Components: Load balancer, API servers, Metadata DB, Metadata Cache, Original Storage, Transcoding Servers, 
Transcoded Storage, CDN, Completion Queue, Completion Handler.

**4. Video Streaming Flow.**
* Process: Stream videos from CDN to client.
* Key Tech: Streaming protocols (MPEG-DASH, HLS, etc.) for adaptive bitrate streaming.

**5. Video Transcoding.**
* Purpose: Convert videos to different formats, resolutions, and bitrates for compatibility and adaptive streaming.
* Benefits: Reduces storage, supports various devices, adapts to network conditions.
* Elements: Containers (e.g., .mp4, .mov), Codecs (e.g., H.264, VP9).

**6. DAG Model for Transcoding.**
* Concept: Directed Acyclic Graph for defining video processing tasks in stages.
* Benefits: Flexibility, parallelism, supports custom processing pipelines (watermarks, thumbnails, etc.).
* Example Tasks: Inspection, encoding, thumbnail generation, watermarking.

**7. Transcoding Architecture Components.**
* Preprocessor: Splits video, generates DAG, caches data.
* DAG Scheduler: Splits DAG into tasks, puts tasks in resource manager.
* Resource Manager: Manages resource allocation (task queue, worker queue, running queue, task scheduler).
* Task Workers: Execute tasks defined in DAG.
* Temporary Storage: Stores intermediate data.

**8. Speed Optimization - Parallel Uploads.**
* Technique: Split videos into smaller chunks (GOP alignment).
* Benefits: Faster uploads, resumable uploads.

**9. Speed Optimization - Upload Centers.**
* Technique: Geographically distributed upload centers (using CDN).
* Benefits: Reduced latency for uploads.

**10. Speed Optimization - Parallelism.**
* Technique: Loosely coupled system with message queues.
* Benefits: Increased parallelism, reduced dependencies between modules.

**11. Safety Optimization - Pre-signed URLs.**
* Technique: Use pre-signed URLs for uploads.
* Benefits: Secure uploads, authorized access only.

**12. Safety Optimization - Video Protection.**
* Techniques: DRM systems (FairPlay, Widevine, PlayReady), AES encryption, visual watermarking.
* Benefits: Copyright protection, prevents unauthorized access.

**13. Cost Optimization - CDN Usage.**
* Technique: Serve only popular videos from CDN.
* Benefits: Reduced CDN costs.
* Considerations: Long-tail distribution of video views.

**14. Cost Optimization - Other Strategies.**
* Techniques: On-demand encoding for less popular content, regional CDN distribution, building own CDN or partnering 
with ISPs.

**15. Error Handling.**
* Types: Recoverable (retry), Non-recoverable (stop task).
* Strategies: Retries, replicas, failover mechanisms.
* Examples: Upload errors, transcoding errors, server failures.

**16. Core Functionality.**
* Front-End: Mobile apps, web browsers, smart TVs.
* Functionality: Video upload, video streaming.

**17. CDN (Content Delivery Network).**
* Purpose: Stores and streams videos to users globally with low latency.
* Benefit: Reduces load on origin servers, improves user experience.
* Example: Amazon CloudFront, Akamai.

**18. Blob Storage.**
* Purpose: Stores original and transcoded video files.
* Definition: Stores unstructured binary data (videos, images).
* Note: Scalable and cost-effective for large media files.

**19. API Servers.**
* Purpose: Handles all requests except video streaming.
* Examples: User authentication, metadata updates, feed recommendations, generating upload URLs.
* Architecture: Behind a load balancer for even distribution of traffic.

**20. Metadata Database.**
* Purpose: Stores video metadata (URL, size, resolution, user info, etc.).
* Requirements: Sharded and replicated for performance and high availability.

**21. Video Transcoding (Encoding).**
* Purpose: Converts videos into multiple formats, resolutions, and bitrates.
* Benefit: Ensures compatibility across devices and network conditions.
* Key Codecs: H.264, VP9, HEVC.
* Containers: .mp4, .mov, .avi.

**22. Streaming Protocols.**
* Purpose: Standardized way to control data transfer for video streaming.
* Examples: MPEG-DASH, Apple HLS, Microsoft Smooth Streaming, Adobe HDS.

**23. Directed Acyclic Graph (DAG).**
* Purpose: Model for video processing pipelines.
* Benefit: Enables flexibility and parallelism in video transcoding.
* Nodes: Represent tasks (inspection, encoding, thumbnail generation, watermarking).

**24. Preprocessor.**
* Purpose: Splits video streams, generates DAGs, caches data.
* Functions: Video splitting by GOP alignment, DAG generation from configuration files, data caching for reliability.

**25. Resource Manager.**
* Purpose: Manages resource allocation for video transcoding.
* Components: Task queue, worker queue, running queue, task scheduler.

**26. Task Workers.**
* Purpose: Execute tasks defined in the DAG (watermarking, encoding, thumbnail generation, merging).

**27. Message Queues.**
* Purpose: Decouple system components and enable parallelism.
* Benefit: Improves system responsiveness and throughput.

**28. Pre-signed URLs.**
* Purpose: Secure video uploads.
* Process: Client requests a pre-signed URL from API servers, then uploads the video directly to storage using the URL.

**29. DRM & Video Protection.**
* Purpose: Protect copyrighted videos.
* Methods: Digital Rights Management (DRM) systems, AES encryption, visual watermarking.

**30. CDN Cost Optimization.**
* Strategies: Serve popular videos from CDN, other videos from video servers, encode less popular content on-demand,
  regional CDN distribution, build own CDN or partner with ISPs.

***

## 16 - Design Google Drive.

**1. Core Functionality.**
* Concept: Cloud storage and file synchronization service.
* Key Features: Upload, download, sync, revision history, sharing, notifications.

**2. Non-Functional Requirements.**
* Focus: Reliability, fast sync, bandwidth efficiency, scalability, high availability.

**3. Initial Single Server Design.**
* Components: Web server, MySQL database, local file storage.
* Limitation: Scalability bottleneck.

**4. API Design.**
* Upload: Simple & Resumable (for large files).
* Download: Path-based retrieval.
* Revisions: List revisions by path and limit.
* Security: HTTPS, SSL for data transfer.

**5. Scaling - Sharding.**
* Technique: Sharding data across multiple storage servers.
* Example: Sharding based on user_id.

**6. Cloud Storage - S3.**
* Solution: Leverage Amazon S3 for scalability, availability, and durability.
* Replication: Same-region and cross-region replication for redundancy.

**7. System Decoupling.**
* Components: Load balancer, multiple web servers, separate metadata database, S3 file storage.

**8.  Sync Conflicts.**
* Resolution: First version processed wins. Conflicted user gets both versions (local and server) to merge or override.

**9. High-Level Architecture.**
* Key Components: User, Load Balancer, API Servers, Metadata Cache, Metadata DB, Block Servers, Cloud Storage, Cold Storage, 
Notification Service, Offline Backup Queue.

**10. Block Servers - Optimization.**
* Delta Sync: Only transfer modified blocks.
* Compression: Reduce data size.
* Encryption: Secure data in transit and at rest.

**11. Block Server - File Handling.**
* Process: Split file into blocks, compress, encrypt, upload to cloud storage.

**12. Consistency.**
* Requirement: Strong consistency for metadata.
* Solution: Invalidate caches on database writes.
* Database Choice: Relational databases (ACID properties).

**13. Metadata Database Schema.**
* Key Tables: User, Device, Namespace, File, File_Version, Block.
* Purpose: Store metadata about users, files, versions, and blocks.

**14. Upload Flow.**
* Parallel Requests: Add file metadata and upload file content.
* Status Updates: Track file upload status in Metadata DB.
* Notifications: Inform clients about file changes.

**15. Download Flow.**
* Trigger: Notification service or offline cache.
* Process: Request metadata, download blocks, reconstruct file.

**16. Notification Service.**
* Purpose: Inform clients of file changes.
* Technology: Long Polling (chosen over WebSockets).

**17. Storage Space Optimization.**
* De-duplication: Eliminate redundant data blocks.
* Intelligent Backup: Limit versions, prioritize valuable versions.
* Cold Storage: Move infrequently used data to cheaper storage.

**18. Failure Handling - General.**
* Strategy: Redundancy and replication are key.
* Monitoring: Heartbeats for load balancers.

**19. Failure Handling - Specific Components.**
* Load Balancer: Failover to secondary.
* Block Server: Other servers pick up jobs.
* Cloud Storage: Cross-region replication.
* API Server: Load balancer redirects traffic.
* Metadata Cache: Replicated cache servers.
* Metadata DB: Master-slave replication, promote slave on master failure.
* Notification Service: Clients reconnect on server failure.
* Offline Backup Queue: Replicated queues, consumers re-subscribe.

**20. Alternative Design Considerations.**
* Direct Upload to Cloud Storage: Faster uploads, but complex client-side implementation and security concerns.
* Presence Service: Separate online/offline logic for other services to use.

**21. Amazon S3.**
* Object storage service for storing files (documents, photos, videos, etc.).
* Provides scalability, data availability, security, and performance.
* Supports same-region and cross-region replication for redundancy and availability.
* Used in Google Drive to store file blocks.

**22. Load Balancer.**
* Distributes network traffic evenly across multiple API servers.
* Ensures high availability by redirecting traffic away from failed servers.
* Improves scalability by allowing easy addition/removal of API servers.

**23. API Servers.**
* Handle user authentication.
* Manage user profiles.
* Update file metadata.
* Interact with metadata cache, metadata database, block servers, and cloud storage.

**24. Metadata Database.**
* Stores metadata about users, files, blocks, and versions.
* Does not store the actual file content (that's in cloud storage).
* Relational databases are preferred because ACID properties are natively supported.

**25. Metadata Cache.**
* Caches frequently accessed metadata for faster retrieval.
* Improves performance by reducing the load on the metadata database.
* Needs a strategy to maintain consistency with the database (e.g., invalidation on write).

**26. Block Servers.**
* Process files for upload.
* Split files into blocks.
* Compress blocks to reduce storage space.
* Encrypt blocks for security.
* Implement delta sync to only upload modified blocks.

**27. Notification Service.**
* Informs clients about file changes (additions, edits, deletions).
* Uses long polling to maintain persistent connections with clients.
* Alerts clients to pull the latest data when changes occur.

**28. Long Polling.**
* A technique used by the Notification Service.
* Clients maintain a persistent connection to the server.
* The server holds the connection open until there's new data to send.
* Once data is sent (or a timeout occurs), the client immediately opens a new connection.

**29. Delta Sync.**
* A technique used by Block Servers to optimize file uploads.
* Only modified blocks of a file are uploaded, instead of the entire file.
* Reduces bandwidth consumption.

**30. Data Deduplication.**
* A technique to save storage space.
* Identical data blocks (same hash value) are stored only once.
* Reduces redundancy and overall storage costs.

***

## 17 - Proximity Service.

**1. Problem Definition - Proximity Service Goal.**
* Design a service to find nearby businesses (restaurants, hotels, etc.) based on user location (lat/long) and a specified radius.

**2. Functional Requirements - Core Features.**
* Return businesses within a radius.
* Business data updates (add/delete/edit) are not real-time.
* View detailed business information.

**3. Non-Functional Requirements - Key Considerations.**
* Low latency.
* Data privacy (GDPR, CCPA).
* High availability & scalability.

**4. API Design - Search - GET `/v1/search/nearby`.**
* Params: latitude, longitude, radius (optional).
* Response: total, businesses (array of business objects).

**5. API Design - Business - Business APIs.**
* GET `/v1/businesses/{:id}` (details).
* POST `/v1/businesses` (add).
* PUT `/v1/businesses/{:id}` (update).
* DELETE `/v1/businesses/{:id}` (delete).

**6. Data Model - Read/Write Ratio - Traffic Patterns.**
* Read-heavy (searching, viewing details). 
* Writes (add/edit/delete) are infrequent.

**7. Data Model - Business Table - Business Table Schema.**
* `business_id` (PK), `address`, `city`, `state`, `country`, `latitude`, `longitude`.

**8. High-Level Design - System Components.**
* Load Balancer.
* Location-Based Service (LBS).
* Business Service.
* Database Cluster (Primary-Secondary).

**9. LBS Characteristics - LBS Details.**
* Read-heavy, high QPS, stateless (easy to scale)

**10. Business Service Characteristics - Business Service Details.**
* Handles both read (view details) and write (add/edit/delete) requests.

**11. Database Cluster Setup - DB Architecture.**
* Primary-secondary setup. Primary handles writes, replicas handle reads. Replication delay is acceptable.

**12. Geospatial Indexing - Basic Idea - Core Concept.**
* Divide the map into smaller areas and build indexes for fast search.

**13. Geospatial Indexing - Options - Indexing Techniques.**
* Hash (Even Grid, Geohash), Tree (Quadtree, Google S2, R-Tree)

**14. Geohash - Key Idea - Geohash.**
* Reduces 2D (lat/long) data to a 1D string. Recursive division into quadrants.

**15. Geohash - Boundary Issues - Geohash Limitations.**
* Locations close geographically may have very different geohashes (no shared prefix). Solution: Check neighboring geohashes.

**16. Quadtree - Key Idea - Quadtree.**
* Recursive subdivision of space into four quadrants until a criterion is met (e.g., max # of businesses in a grid). 
* In-memory data structure.

**17.  Quadtree - Operational Considerations - Quadtree Challenges.**
* Build time at server startup. Incremental rebuilds or blue/green deployment.

**18. Database Scaling - Scaling Strategy.**
* Business table: Shard by business_id.
* Geospatial index: Replicas for read scaling (if the dataset fits in memory).

**19. Caching Strategy - Caching.**
* Cache Key: Geohash.
* Cache Value: List of business IDs in the grid, Business object.
* Redis.

**20. Deployment - Deployment Strategy.**
* Multiple regions and availability zones for low latency, high availability, and data privacy compliance.

**21. What is Geohash?**
* A geospatial indexing technique that reduces 2D latitude/longitude coordinates into a 1D string.
* Works by recursively dividing the world into smaller grids.
* Uses base32 representation.
* Commonly used in various applications due to its simplicity.
* Has boundary issues that need to be addressed (locations close but with different prefixes).

**22. What is a Quadtree?**
* A tree data structure where each internal node has four children, corresponding to four quadrants of a space.
* Used to partition a 2D space by recursively subdividing it into quadrants.
* Subdivision continues until a criterion is met (e.g., max number of businesses in a grid).
* In-memory data structure (not a database solution).
* Can dynamically adjust grid size based on population density.

**23. What is Google S2?**
* A geometry library that maps a sphere to a 1D index based on the Hilbert curve.
* The Hilbert curve preserves locality, meaning points close on the curve are close in 1D space.
* Good for geofencing (defining virtual perimeters).
* Uses a Region Cover algorithm for flexible cell sizes.
* More complex than Geohash or Quadtree.

**24. What is Redis used for in this context?**
* In-memory data store used for caching.
* Caches:
  * List of business IDs for a given geohash.
  * Business objects (business info).
  * Fast retrieval of frequently accessed data.
  * Deployed in clusters across regions for high availability and low latency.

**25. What is MySQL used for in this context?**
* Stores business data and geospatial index data.
* Primary-secondary setup (primary handles writes, replicas handle reads).
* Business table is sharded by business ID.
* Geospatial index table is scaled using read replicas.

**26. What is the role of the Load Balancer?**
* Distributes incoming traffic across multiple services (LBS, Business Service).
* Routes API calls based on URL paths.
* Provides a single DNS entry point.

**27. What are the key API endpoints?**
* GET `/v1/search/nearby`: Returns businesses based on location and radius.
  * Parameters: latitude, longitude, radius.
* GET `/v1/businesses/{:id}`: Returns detailed information about a business.
* POST `/v1/businesses`: Adds a business.
* PUT `/v1/businesses/{:id}`: Updates a business.
* DELETE `/v1/businesses/{:id}`: Deletes a business.

***

## 18 - Nearby Friends.

**1. Functional Requirement.** 
* Display nearby friends with distance and timestamp.

**2. Non-Functional Requirement.**
* Low latency, eventual consistency, reliability.

**3. Location Update Frequency.** 
* 30 seconds (tradeoff between accuracy and load).

**4. Concurrent Users.** 
* Estimated 10 million. 

**5. QPS.** 
* ~334,000 location updates per second.

**6. Load Balancer.** 
* Distributes traffic to API and WebSocket servers.

**7. RESTful API Servers.** 
* Handles user management, friend requests, etc.

**8. WebSocket Servers.** 
* Manages real-time location updates and persistent connections.

**9. Redis Location Cache.** 
* Stores current user locations with TTL for activity management.

**10. User Database.**
* Stores user profiles and friend relationships.

**11. Location History Database.** 
* Stores historical location data (e.g., Cassandra).

**12. Redis Pub/Sub.** 
* Message bus for broadcasting location updates to friends.

**13. Periodic Location Update Flow.** 
* Client -> Load Balancer -> WebSocket Server -> Cache/DB -> Pub/Sub.

**14. API Design.** 
* Location updates, client initialization, friend subscription/unsubscription.

**15. Data Model.** 
* Key-value cache (user_id -> location), location history database (user_id, location, timestamp).

**16. Scaling WebSocket Servers.** 
* Auto-scaling with connection draining.

**17. Scaling Redis Cache.** 
* Sharding based on user ID.

**18. Distributed Redis Pub/Sub.** 
* Sharding channels across multiple servers, service discovery (etcd, Zookeeper).

**19. Consistent Hashing.** 
* Used to distribute pub/sub channels across servers.

**20. Erlang/BEAM/OTP (Alternative).** 
* Highly concurrent environment for managing user connections and location updates.

**21. WebSocket.**
* Purpose: Real-time, bidirectional communication between clients (mobile apps) and the server.
* Key Features: Persistent connection, low latency, full-duplex communication.
* Usage in "Nearby Friends":
  * Mobile clients send location updates to the backend.
  * Backend pushes location updates of nearby friends to clients.
  * Handles initial handshake and client initialization.

**22. Redis (Location Cache).**
* Purpose: In-memory data store for caching the most recent location of active users.
* Key Features: Fast read/write operations, TTL (Time-To-Live) support for automatic data expiration.
* Usage in "Nearby Friends":
  * Stores user_id as key and {latitude, longitude, timestamp} as value.
  * TTL is used to automatically remove inactive users from the cache.
  * Sharded to handle high update QPS (Queries Per Second).

**23. Redis Pub/Sub.**
* Purpose: Message broker for routing location updates from one user to their online friends.
* Key Features: Lightweight channels, publish-subscribe pattern, supports many channels.
* Usage in "Nearby Friends":
  * Each user has their own channel.
  * WebSocket servers publish location updates to user channels.
  * Friends subscribe to these channels to receive updates.
  * Distributed cluster to handle high message volume.
  
**24. Cassandra (Location History Database).**
* Purpose: Stores historical location data for users.
* Key Features: Horizontally scalable, handles heavy write workloads, fault-tolerant.
* Usage in "Nearby Friends":
  * Stores user_id, latitude, longitude, and timestamp.
  * Sharded by user_id for even load distribution.

**25. Load Balancer.**
* Purpose: Distributes incoming traffic across multiple servers.
* Key Features: Distributes traffic evenly, handles auto-scaling, health checks.
* Usage in "Nearby Friends":
  * Sits in front of RESTful API servers and WebSocket servers.
  * Distributes traffic to available servers.
  * Handles draining connections during server removal.

**26. Service Discovery (etcd/Zookeeper).**
* Purpose: Provides a centralized registry for service configuration and discovery.
* Key Features: Key-value store, watch/notification mechanism for updates.
* Usage in "Nearby Friends":
  * Stores the hash ring configuration for the distributed Redis Pub/Sub cluster.
  * WebSocket servers subscribe to updates to the hash ring.

**27. Erlang/BEAM/OTP.**
* Purpose: Alternative to Redis Pub/Sub for routing location updates.
* Key Features: Lightweight processes, concurrency, fault-tolerance, distributed computing.
* Usage in "Nearby Friends":
  * Model each user as an Erlang process.
  * User processes subscribe to updates from friend processes.
  * Forms a mesh network for efficient routing.

***

## 19 - Google Maps.

**1. Core Features.**
* Location Updates: Mobile clients send location data.
* Navigation: Finding routes from A to B.
* ETA: Estimating travel time.
* Map Rendering: Displaying the map.

**2. Non-Functional Requirements.**
* Accuracy: Correct directions are critical.
* Smoothness: Fluid map rendering on mobile.
* Data Usage: Minimize data transfer on mobile.
* Battery: Conserve battery life on mobile devices.
* Scalability & Availability: Handle a large user base reliably.

**3. Latitude and Longitude.**
* Lat (Latitude): North/South position.
* Long (Longitude): East/West position.

**4. Map Projection.**
* 3D to 2D: Translating the globe to a flat map.
* Distortion: All projections distort geometry.
* Web Mercator: Google Maps uses a modified Mercator projection.

**5. Geocoding.**
* Addresses to Coordinates: Converting "1600 Amphitheatre..." to lat/long.
* Reverse Geocoding: Coordinates back to addresses.

**6. Geohashing.**
* Encoding Geographic Areas: Short strings represent regions.
* Recursive Subdivision: Grids divided into sub-grids.
* Map Tiling: Used for dividing the map into manageable tiles.

**7. Map Tiling.**
* Breaking up the world map into smaller images.
* Client downloads relevant tiles based on location and zoom level.

**8. Routing Data as a Graph.**
* Nodes: Intersections on roads.
* Edges: The roads themselves.
* Pathfinding: Algorithms (Dijkstra's, A*) operate on this graph.

**9. Routing Tiles.**
* Dividing the road network into grids.
* Each tile is a smaller graph.
* Improves memory usage and pathfinding performance.

**10. Hierarchical Routing Tiles.**
* Multiple levels of detail (local roads, arterial roads, highways).
* Enables efficient routing at different scales.

**11. Storage Estimation.**
* Map Tiles: ~100 PB (Petabytes) for all zoom levels.
* Road Data: TBs (Terabytes).

**12. Server Throughput Estimation.**
* High QPS (Queries Per Second): Millions of location updates.
* Batching: Grouping updates to reduce QPS.

**13. High-Level Design - Location Service.**
* Records user location updates.
* Batching on the client to reduce traffic.

**14. High-Level Design - Navigation Service.**
* Finds routes.
* Considers origin and destination.

**15. High-Level Design - Map Rendering.**
* Fetching map tiles on-demand.
* CDN (Content Delivery Network) for caching and fast delivery.

**16. CDN vs. Dynamic Tile Generation.**
* Pre-generated Tiles + CDN: Scalable, cacheable.
* Dynamic Generation: Huge server load, hard to cache.

**17. Data Model - Routing Tiles.**
* Graph data (nodes/edges) stored in object storage (e.g., S3).
* Organized by geohashes for fast lookup.

**18. Data Model - User Location Data.**
* Cassandra: Database for high write volume.
* (user_id, timestamp) as key.

**19. Data Model - Precomputed Map Tiles.**
* Images stored on a CDN, backed by cloud storage (e.g. S3).

**20. Adaptive ETA and Rerouting.**
* Continuously updating ETAs based on traffic.
* Server pushes updates to clients (WebSockets).

**21. What is Geohashing? How is it used in Google Maps?**
* Geohashing is a spatial indexing technique that divides the Earth into a grid and assigns a short string (geohash) 
to each grid cell. 
* In Google Maps, it's used to:
  * Identify and retrieve map tiles efficiently.
  * Organize and store routing tiles.

**22. What is a CDN? Why is it important for map rendering?**
* A CDN is a distributed network of servers that caches content closer to users. 
* It's crucial for map rendering because:
  * Map tiles are pre-generated and static, making them highly cacheable.
  * Reduces latency by serving tiles from the nearest POP (Point of Presence).
  * Lowers the load on the origin server.

**23. What is Cassandra? Why is it suitable for the Location Service?**
* Cassandra is a NoSQL, distributed database designed for high write throughput and scalability. 
* It's a good fit for the Location Service because:
  * Handles the massive volume of location updates from users.
  * Provides horizontal scalability to accommodate growth.
  * Offers high availability.

**24. What is Kafka? How is it used with location data?**
* Kafka is a distributed streaming platform used for building real-time data pipelines and streaming applications. 
* In Google Maps, it:
  * Ingests the stream of user location updates.
  * Enables other services (traffic updates, routing tile processing, analytics) to consume this data in near real-time.

**25. What is Redis? How is it used in the Geocoding Service?**
* Redis is an in-memory data structure store, often used as a cache or message broker. 
* In the Geocoding Service:
  * Stores geocoding data (places and their lat/lng coordinates).
  * Provides fast reads for frequent geocoding lookups.

**26. What is object storage? How is it used for routing tiles?**
* Object storage is a service that stores data as objects, often used for unstructured data.
* For routing tiles:
  * Stores routing tiles (graph data of road networks) as binary files.
  * Provides a cost-effective and scalable storage solution.
  * Tiles are organized by geohashes for fast lookup.

**27. What are WebSockets? How are they used for adaptive ETA and rerouting?**
* WebSockets provide full-duplex communication channels over a single TCP connection. 
* For adaptive ETA and rerouting:
  * Enables the server to push real-time updates (traffic changes, rerouting suggestions) to the client.
  * Supports bi-directional communication, which might be required for features such as last-mile delivery.

***

## 20 - Distributed Message Queue.

**1. Message Queue Benefits.**
* Decoupling, Scalability, Availability, Performance.

**2. Messaging Models.**
* Point-to-Point (one consumer) vs. Publish-Subscribe (multiple consumers).

**3. Topics.**
* Categories for organizing messages. Each topic has a unique name.

**4. Partitions.**
* Divide topics for scalability. Messages evenly distributed.

**5. Brokers.**
* Servers that hold partitions. Distributing partitions across brokers enables scalability.

**6. Offsets.**
* Position of a message in a partition. Used for ordering.

**7. Consumer Groups.**
* Set of consumers working together to consume messages from topics.

**8. Consumer Group & Messaging Models.**
* Consumer groups can simulate point-to-point within a publish-subscribe system.

**9. High-Level Architecture Components.**
* Producers, Consumers, Brokers, Data Storage, State Storage, Metadata Storage, Coordination Service.

**10. Data Storage Choice.**
* Write-Ahead Log (WAL) is preferred for sequential access.

**11. WAL Segments.**
* Divide WAL into segments. Active segment for writes, inactive for reads.

**12. Message Data Structure - Key.**
* Used to determine the partition for the message.

**13. Message Data Structure - Value.**
* The payload of the message.

**14. Batching.**
* Improves throughput by grouping messages. Tradeoff with latency.

**15. Producer Flow - Routing Layer.**
* Directs messages to the correct broker (leader replica). Can be integrated into producer.

**16. Producer Buffer.**
* Buffers messages for batching, increasing throughput.

**17. Consumer Flow - Pull vs. Push.**
* Pull model preferred: Consumers control consumption rate.

**18. Consumer Coordinator.**
* A broker responsible for managing consumer groups and rebalancing.

**19. Consumer Rebalancing.**
* Reassigning partitions to consumers when consumers join/leave/crash.

**20. State Storage.**
* Stores partition-to-consumer mappings and last consumed offsets.

**21. Metadata Storage.**
* Stores topic configurations, partition counts, replica distributions.

**22. ZooKeeper.**
* Used for metadata storage, state storage, and coordination (leader election).

**23. Replication.**
* Increases availability. Each partition has multiple replicas.

**24. In-Sync Replicas (ISR).**
* Replicas that are "in-sync" with the leader. Configurable lag.

**25. Delivery Semantics.**
* At-most-once, At-least-once, Exactly-once. Trade-offs between reliability and performance.

**26. What is a Message Queue?**
* A service that facilitates asynchronous communication between producers and consumers. 
* Decouples systems, improves scalability/availability, and enhances performance.

**27. What is Apache Kafka?**
* A distributed event streaming platform (though blurring the lines with message queues). 
* Known for high throughput, fault tolerance, and persistence.
* Uses topics, partitions, and brokers.

**28. What is Apache Pulsar?**
* Another distributed event streaming platform, often compared to Kafka. 
* Can also be used as a general-purpose message queue.

**29. What is RabbitMQ?**
* A popular message broker that implements the Advanced Message Queuing Protocol (AMQP). 
* Focuses on flexible messaging rather than high-throughput stream processing, but is adding streaming features.

**30. What is RocketMQ?**
* A distributed messaging and streaming platform originally developed by Alibaba. 
* Known for its financial-grade reliability and strong consistency.

**31. What is Apache ZooKeeper used for in a message queue?**
* A centralized service for maintaining configuration information, naming, providing distributed synchronization, 
and group services. 
* Used for metadata storage, state storage, and coordination service (e.g., leader election).

**32. What is etcd?**
* A distributed key-value store that provides a reliable way to store data across a cluster of machines. 
* Similar to ZooKeeper, often used for service discovery, configuration management, and leader election.

**33. What is AMQP?**
* Advanced Message Queuing Protocol. 
* An open standard application layer protocol for message-oriented middleware. 
* Defines how messages are routed and delivered between brokers and clients.

***

## 21 - Metrics Monitoring and Alerting System.

**1. What is the primary goal of a metrics monitoring and alerting system?**
* To provide visibility into infrastructure health, ensuring high availability and reliability.

**2. Name the five fundamental components of a metrics monitoring system.**
* Data Collection, Data Transmission, Data Storage, Alerting, Visualization.

**3. How is metrics data typically structured?**
* As time series data: metric name, tags/labels (key-value pairs), and an array of timestamped values.

**4. Describe the typical data access pattern for a metrics monitoring system.**
* Write-heavy (constant ingestion of new data), with spiky read loads (bursts of queries for visualization/alerting).

**5. Why are general-purpose databases (SQL or NoSQL) often not ideal for metrics storage?**
* They aren't optimized for time-series operations, tagging/labeling, or constant heavy writes. 
* Time-series databases are usually a better choice.

**6. Give examples of time-series databases.**
* InfluxDB, Prometheus, OpenTSDB, Amazon Timestream.

**7. What are the two primary models for collecting metrics data?**
* Pull (collectors fetch data) and Push (agents send data).

**8. How does the pull model work?**
* Metrics collectors periodically request metrics from application servers, often using a `/metrics` endpoint.

**9. How does the push model work?**
* Agents on servers proactively send metrics to the metrics collector.

**10. What role does service discovery play in a pull-based system?**
* It allows metrics collectors to dynamically discover and track available service endpoints. Examples: etcd, Zookeeper

**11. Why is consistent hashing important when using multiple collectors?**
* To distribute the load and ensure each metrics source is handled by only one collector, avoiding duplicate data.

**12. What component can be added to the metrics transmission pipeline to prevent data loss?**
* A queuing system like Kafka.

**13. What are the advantages of using Kafka in the metrics pipeline?**
* Reliability, scalability, decoupling of services, and prevention of data loss during database outages.

**14. How can Kafka's partitioning mechanism be used to scale the system?**
* By partitioning metrics data by metric names or tags/labels.

**15. Where can metrics be aggregated?**
* Collection agent (client-side), ingestion pipeline, or query side.

**16. What is the purpose of a query service?**
* To decouple time-series databases from visualization and alerting systems, providing flexibility.

**17. Why is caching important in the query service?**
* To reduce the load on the time-series database and improve query performance.

**18. How can data encoding and compression help with space optimization?**
* By significantly reducing the size of the data stored. Delta encoding is one example.

**19. What is downsampling and why is it used?**
* Converting high-resolution data to low-resolution, to reduce storage space for older data.

**20. What are the key responsibilities of the alerting system?**
* Filter, merge, and dedupe alerts; access control; retry failed notifications; and manage alert states.

**21. What are the considerations when deciding whether to build or buy an alerting or visualization system?**
* Off-the-shelf systems often offer tight integration with time-series databases and notification channels. 
* Building your own requires significant justification, especially for senior roles.

**22. How do pull and push models differ in terms of data authenticity, and how can the push model be secured?**
* Pull model collects data from pre-defined servers, guaranteeing authenticity. 
* Push model requires whitelisting servers or authentication to prevent malicious data.

**23. What is a Time-Series Database (TSDB)? Why use one?**
* A database optimized for storing and retrieving time-series data (data indexed by time).
* Designed for high write throughput and efficient querying of data over time ranges.
* Key features: data compression, retention policies, aggregation functions.
* Examples: InfluxDB, Prometheus, OpenTSDB, Amazon Timestream, MetricsDB

**24. What is InfluxDB? What are its key features?**
* A popular open-source time-series database.
* Features:
  * Custom query language (Flux) optimized for time-series analysis.
  * Scalable architecture for handling large volumes of data.
  * Built-in functions for data aggregation and analysis.
  * Supports tags/labels for efficient data filtering and querying.

**25. What is Prometheus? How does it collect metrics?**
* An open-source monitoring solution and time-series database.
* Uses a pull model for collecting metrics: it scrapes metrics from target endpoints over HTTP.
* PromQL: its powerful query language.
* Designed for operational monitoring and alerting.

**26. What is Kafka? Why is it used in a metrics pipeline?**
* A distributed streaming platform.
* Used as a message queue to decouple metrics collection from processing and storage.
* Provides high throughput, fault tolerance, and scalability.
* Allows for buffering of metrics data, preventing data loss during outages.

**27. What are etcd and Zookeeper? What role do they play?**
* Distributed key-value stores used for service discovery.
* Services register their availability, and other components (like metrics collectors) can query them to find available services.
* Enable dynamic configuration and automatic discovery of new or removed services.

**28. What is Grafana? What is it used for?**
* A popular open-source data visualization and monitoring tool.
* Connects to various data sources (including TSDBs) to create dashboards and visualizations.
* Allows users to create custom graphs, charts, and alerts based on metrics data.

**29. Explain the difference between pull and push models for metrics collection.**
* Pull: Metrics collector actively requests (pulls) metrics from services. (e.g., Prometheus).
  * Pros: Easy debugging, health checks.
  * Cons: Requires accessible endpoints, can be complex with firewalls.
* Push: Services actively send (push) metrics to the collector. (e.g., CloudWatch, Graphite)
  * Pros: Works well for short-lived jobs, simpler network setup.
  * Cons: Requires agents, potential data authenticity issues.

**30. What are the key components and functions of an alerting system?**
* Components: Rule configuration, alert manager, alert store, notification channels.
* Functions:
  * Evaluate metrics against predefined rules.
  * Deduplicate and merge alerts.
  * Manage alert states (inactive, pending, firing, resolved).
  * Send notifications via email, SMS, PagerDuty, etc.

***

## 22 - Ad Click Event Aggregation.

**1. What is Real-Time Bidding (RTB)? Why is it important for online advertising?**
* RTB is the process of buying/selling ad inventory in real-time. Crucial for measurability and data-driven decisions in digital advertising. 

**2. Name two key metrics that rely on aggregated ad click data.**
* Click-Through Rate (CTR) and Conversion Rate (CVR).

**3. List the core functional requirements of an ad click event aggregation system.**
* Aggregate click counts by ad ID, return top N clicked ads, support filtering.

**4. What are the key non-functional requirements for this system?**
* Correctness, handling delayed/duplicate events, robustness, low latency.

**5. What is the estimated data volume for ad click events at a large scale?**
* 1 billion events/day, potentially 50,000 QPS at peak, 100GB daily storage.

**6. Describe the API endpoint for retrieving aggregated click counts for a specific ad.**
* GET `/v1/ads/{:ad_id}/aggregated_count`.

**7. Describe the API endpoint for retrieving the top N most clicked ads.**
* GET `/v1/ads/popular_ads`.

**8. What type of information is stored in raw data?**
* ad_id, click_timestamp, user_id, ip, country.

**9. What information is stored in aggregated data?**
* ad_id, click_minute, count (and potentially filter_id).

**10. What are the pros and cons of storing only raw data?**
* Pros: Full dataset, supports filtering/recalculation. 
* Cons: Huge storage, slow queries.

**11. What type of database is suitable for storing raw ad click data, and why?**
* NoSQL databases (e.g., Cassandra, InfluxDB) are often preferred due to their write optimization and time-series capabilities.

**12. Describe the high-level architecture of the ad click aggregation system.**
* Log Watcher -> Message Queue -> Aggregation Service -> Message Queue -> Database Writer -> Database.

**13. Why is asynchronous processing important in this system?**
* Decouples producers and consumers, allows independent scaling, improves fault tolerance.

**14. Explain how MapReduce is used in the aggregation service.**
* Map nodes filter/transform data, Aggregate nodes count events, Reduce nodes combine results.

**15. What are the key differences between streaming and batch processing?**
* Streaming: near real-time, unbounded input. 
* Batch: offline, bounded input.

**16. What are the advantages of Kappa architecture over Lambda architecture?**
* Kappa simplifies the system by using a single processing path (streaming) for both real-time and historical data.

**17. What are the trade-offs between using event time and processing time for aggregation?**
* Event time: more accurate, but handles delayed events. 
* Processing time: simpler, but less accurate with delays.

**18. What is a watermark, and how does it help with delayed events?**
* A watermark is an extended aggregation window that allows slightly delayed events to be included in the correct window.

**19. What delivery guarantee is most important for ad click aggregation, and why?**
* Exactly-once, because data accuracy is crucial for billing and reporting.

**20. How can you scale the message queue, aggregation service, and database independently?**
* Message queue: add partitions/brokers/consumers. 
* Aggregation service: add nodes/threads. 
* Database: horizontal scaling (e.g., Cassandra).

**21. What are some common causes of data duplication in an ad click aggregation system?**
* Client-side resends, server outages during processing.

**22. What is a "hotspot" in the context of ad click aggregation, and how can it be mitigated?**
* A hotspot is a shard or service receiving disproportionately more data (e.g., due to a very popular ad). 
* Mitigation strategies include allocating more resources to process popular ads.

**23. What are some important metrics to monitor in an ad click aggregation system, and why is reconciliation important?**
* Latency, message queue size, system resource utilization. 
* Reconciliation (comparing data sets) ensures data integrity and correctness, especially for billing purposes. 

**24. What is Kafka and why is it used in this system?**
* Kafka is a distributed message queue used to decouple producers (log watchers, aggregation services) and consumers 
(aggregation services, database writers). 
* It provides asynchronous processing, allowing independent scaling and fault tolerance. 
* It also helps achieve end-to-end exactly-once semantics.

**25. What is Cassandra and why is it a suitable database choice here?**
* Cassandra is a NoSQL database optimized for write-heavy workloads and time-range queries. 
* It's horizontally scalable, making it suitable for storing both raw and aggregated ad click data.

**26. Explain the role of MapReduce in the aggregation service.**
* MapReduce is a programming model used to process and aggregate large datasets in parallel. 
* In this system, it's used to break down the aggregation task into smaller, manageable units 
(Map, Aggregate, Reduce nodes) for distributed processing.

**27. What is Apache Flink and how does it relate to stream processing?**
* Flink is a stream processing framework that can be used for real-time data aggregation. 
* The chapter mentions it as an example of a streaming system and a component in Kappa architecture.

**28. What is Apache Hadoop YARN and how can it be used to scale the aggregation service?**
* YARN is a resource management platform used to manage and allocate computing resources in a Hadoop cluster. 
* Aggregation service nodes can be deployed on YARN to scale the system by adding more computing resources.

**29. What role do HDFS or S3 play in data deduplication?**
* HDFS (Hadoop Distributed File System) or S3 (Amazon Simple Storage Service) can be used as external file storage to 
record offsets for data consumption, helping to prevent data duplication in case of server outages.

**30. What are ClickHouse and Druid, and why are they mentioned as alternatives?**
* ClickHouse and Druid are OLAP (Online Analytical Processing) databases optimized for fast query performance on large 
datasets. 
* They are mentioned as alternative solutions for ad click data aggregation and analysis.

***

## 23 - Hotel Reservation System.

**1. Design a hotel reservation system for a chain (e.g., Marriott) with 5,000 hotels and 1 million rooms.**
* Key features: Hotel/room details, reservations, admin panel, overbooking.

**2. What are some important questions to ask the interviewer to clarify the scope?**
* Payment timing, booking channels (website/app only?), cancellation policy, overbooking policy, focus areas.

**3. What are the main non-functional requirements?**
* High concurrency (peak seasons), moderate latency (few seconds acceptable).

**4.  Why is back-of-the-envelope estimation important?**
* Helps understand system scale, estimate traffic (TPS/QPS), and identify potential bottlenecks. 

**5. How does the QPS change across the booking funnel (detail page -> booking page -> reservation)?**
* QPS decreases as users drop off at each stage. (e.g., 300 -> 30 -> 3).

**6. Give examples of Hotel-related APIs.**
* GET `/v1/hotels/ID` (details). 
* POST `/v1/hotels` (add). 
* PUT `/v1/hotels/ID` (update). 
* DELETE `/v1/hotels/ID` (delete). 
* (Admin only for POST/PUT/DELETE).

**7. Give examples of Room-related APIs.**
* GET `/v1/hotels/ID/rooms/ID` (details). 
* POST `/v1/hotels/ID/rooms` (add). 
* PUT `/v1/hotels/ID/rooms/ID` (update).
* DELETE `/v1/hotels/ID/rooms/ID` (delete). 
* (Admin only for POST/PUT/DELETE).

**8. Give examples of Reservation-related APIs.**
* GET `/v1/reservations` (history).
* GET `/v1/reservations/ID` (details).
* POST `/v1/reservations` (new).
* DELETE `/v1/reservations/ID` (cancel).

**9. What parameters are needed for the POST `/v1/reservations` API?**
* startDate, endDate, hotelID, roomID, reservationID (idempotency key).

**10. Why is reservationID important in the POST `/v1/reservations` API?**
* Acts as an idempotency key to prevent double booking.

**11. What are the common data access patterns for this system?**
* View hotel info, find available rooms (date range), record reservation, lookup reservation history.

**12. Why choose a relational database (e.g., MySQL) for this system?**
* Read-heavy, ACID properties (critical for reservations), easy to model data relationships.

**13. What are the possible statuses for a reservation?**
* Pending, paid, refunded, canceled, rejected. 

**14. What's the problem with reserving a specific room_id directly?**
* Hotels reserve types of rooms (e.g., "Queen"), not specific room numbers until check-in.

**15. Why use a microservice architecture?**
* Scalability, independent deployments, fault isolation.

**16. Name the key microservices in the hotel reservation system.**
* Hotel Service, Rate Service, Reservation Service, Payment Service, Hotel Management Service.

**17. What is the role of CDN?**
* Cache static assets (images, JS, etc.) for faster load times, reducing origin server load and improving user experience.

**18. What is the role of API Gateway?**
* Rate limiting, authentication, routing requests to appropriate services.

**19. What are Internal APIs?**
* APIs for authorized hotel staff, often protected by VPN.

**20. How does the reservation API change with room types?**
* roomID is replaced by roomTypeID and roomCount in the request parameters.

**21. What are the key tables in the improved database schema?**
* room, room_type_rate, reservation, room_type_inventory.

**22. What is the purpose of the room_type_inventory table?**
* Stores inventory data for each hotel, room type, and date. Tracks total_inventory and total_reserved.

**23. What is the primary key of the room_type_inventory table?**
* (hotel_id, room_type_id, date) - composite key.

**24. How is overbooking implemented in the inventory check?**
* `if (total_reserved + ${numberOfRoomsToReserve}) <= 110% * total_inventory`.

**25. What is a good sharding key for the database?**
* hotel_id - most queries filter by hotel.
* Sharding distributes data and load, improving scalability.
 
**26. How can the client prevent double booking?**
* Gray out, hide, or disable the "submit" button after the first click. (Not reliable alone).

**27. How do idempotent APIs prevent double booking?**
* Use a unique reservation_id as a primary key. Subsequent requests with the same ID will violate the unique constraint.

**28. What concurrency problem can occur when multiple users book the last room?**
* Race condition - multiple users might see the room available and try to book it simultaneously.

**29. What are the solutions to the concurrency problem?**
* Pessimistic locking, optimistic locking, database constraints.

**30. How does optimistic locking work?**
* Add a "version" column. Update increments version. Transaction fails if version doesn't match.

**31. Besides sharding, what other techniques can improve database scalability?**
* Read replicas (offload read traffic), connection pooling (reduce connection overhead), and caching.

**32. What are the pros and cons of using database constraints for concurrency?**
* Pros: Easy to implement, good for low contention. 
* Cons: High failure rate under heavy contention, limited version control, not supported by all databases.

**33. What are some strategies for keeping the cache consistent with the database?**
* Write-through (update cache synchronously), write-back (update cache asynchronously), TTL-based invalidation.

**34. What happens if the cache is inconsistent with the database?**
* Users might see outdated information. The database remains the source of truth, so validation at the database level is crucial.

**35. What is the Saga pattern and how does it address data consistency in microservices?**
* A sequence of local transactions with compensating transactions to undo changes if one fails, ensuring eventual consistency.

**36. Relational Databases.**
* Purpose: Data storage for structured data (hotels, rooms, reservations).
* ACID Properties: Atomicity, Consistency, Isolation, Durability - crucial for reliable transactions.
* Use Case: Suitable for read-heavy workloads with less frequent writes. 
* Clear data structure and relationships.
* Considerations: Can be scaled using techniques like replication and sharding.

**37. NoSQL Databases.**
* Purpose: Optimized for writes.
* ACID Properties: Not guaranteed.
* Use Case: Not suitable for hotel reservation system.

**38. RESTful APIs.**
* Purpose: Standard way to design APIs for web services. Uses HTTP methods (GET, POST, PUT, DELETE).
* Example: POST `/v1/reservations` (Make a new reservation)
* Benefits: Simple, widely adopted, easy to understand.

**39. CDN (Content Delivery Network).**
* Purpose: Caches static assets (images, JS, CSS) to reduce load times for users.
* How it works: Distributes content across multiple servers geographically closer to users.
* Benefits: Improves website performance and user experience.

**40. API Gateway.**
* Purpose: Central point of entry for all API requests.
* Functions:
  * Routing requests to appropriate microservices.
  * Authentication and authorization.
  * Rate limiting.
  * Benefits: Simplifies client requests, improves security, and manages traffic.

**41. gRPC.**
* Purpose: High-performance Remote Procedure Call (RPC) framework for inter-service communication.
* Benefits: Efficient, supports multiple languages, and uses protocol buffers for serialization.

**42. Redis.**
* Purpose: In-memory data store used for caching.
* Features:
  * Fast read/write speeds.
  * TTL (Time-To-Live) for automatic data expiration.
  * LRU (Least Recently Used) eviction policy.
  * Use Case: Caching hotel inventory data for quick retrieval.

**43. Debezium.**
* Purpose: Change Data Capture (CDC) tool.
* How it works: Captures database changes in real-time and streams them to other systems (e.g., Redis).
* Benefits: Keeps cache data synchronized with the database.

**44. Two-Phase Commit (2PC).**
* Purpose: Distributed transaction protocol to ensure atomicity across multiple nodes.
* How it works: Guarantees that either all nodes commit or all nodes rollback.
* Cons: Blocking protocol, not performant, single node failure blocks progress.

**45. Saga Pattern.**
* Purpose: Manages data consistency across microservices.
* How it works: Sequence of local transactions; each transaction updates and publishes a message to trigger the next transaction.
* Compensation: If a transaction fails, compensating transactions undo previous changes.
* Consistency: Relies on eventual consistency.

***

## 24 - Distributed Email Service.

**1. Key features of a distributed email service.**
* Sending/receiving emails, fetching all emails, filtering (read/unread), searching (subject, sender, body), anti-spam/virus.

**2. What are the key non-functional requirements for an email service?**
* Reliability (no data loss), Availability (replication, fault tolerance), Scalability (handle growth), 
Flexibility/Extensibility (new features, custom protocols).

**3. Why are back-of-the-envelope calculations important for email service design?**
* To estimate scale (users, emails, storage), identify challenges (e.g., need for distributed database). 
* Example metrics: emails per day, attachment storage.

**4.  What is SMTP?**
* Simple Mail Transfer Protocol. Standard for sending emails between mail servers.

**5. What is POP?**
* Post Office Protocol. 
* Used for retrieving emails and downloading them to a local client. 
* Emails are typically deleted from the server after download.

**6. What is IMAP?**
* Internet Mail Access Protocol. 
* Used for retrieving emails. 
* Emails remain on the server, allowing access from multiple devices.

**7. What role does DNS play in email? What are MX records?**
* DNS is used to find the mail exchanger (MX) record for the recipient's domain. 
* MX records specify the mail servers responsible for accepting emails for that domain. 
* Priority numbers determine the order of mail server preference.

**8. How are attachments handled in email? What is MIME?**
* Attachments are sent along with the email message, often using Base64 encoding. 
* MIME (Multipurpose Internet Mail Extension) is a specification that allows attachments to be sent over the internet.

**9. Briefly describe the architecture of a traditional mail server.**
* Client (e.g., Outlook) -> SMTP server (sender) -> SMTP server (recipient) -> Mail storage -> IMAP/POP server -> Client (recipient).

**10. What is Maildir?**
* A common directory structure for storing email messages on a traditional mail server. 
* Each email is stored as a separate file.

**11. Give examples of common webmail APIs.**
* POST `/v1/messages` (send).
* GET `/v1/folders` (get folders).
* GET `/v1/folders/{folder_id}/messages` (get messages in folder).
* GET `/v1/messages/{message_id}` (get specific message).

**12. What are the key components of a distributed mail server architecture?**
* Webmail, Web servers, Real-time servers, Metadata database, Attachment store, Distributed cache, Search store.

**13. What is the role of real-time servers? How do WebSockets fit in?**
* Real-time servers push new email updates to clients. 
* WebSockets provide persistent connections for real-time communication.

**14. What is a good choice for an attachment store and why?**
* Object stores like Amazon S3. 
* Scalable, suitable for large files. 
* NoSQL column-family databases like Cassandra are not ideal due to blob size limitations and caching issues.

**15. Briefly describe the email sending flow.**
* Client -> Load Balancer -> Web Server (validation) -> Message Queue -> SMTP Outgoing Workers (spam/virus check) 
-> Storage -> Recipient mail server.

**16. Briefly describe the email receiving flow.**
* Incoming Email -> SMTP Load Balancer -> SMTP Servers -> Incoming Email Queue -> Mail Processing Workers (spam/virus check) 
-> Storage, Cache, Object Store -> Real-time Servers (if online) or Storage (if offline) -> Web Servers (for offline users).

**17. What are the key characteristics of email metadata?**
* Small headers, infrequent access to body, user-specific operations, data recency matters, high reliability required.

**18. How might you model email data in a NoSQL database?**
* Tables like `folders_by_user`, `emails_by_folder`, `emails_by_user`, `attachments`, `read_emails`, and `unread_emails`. 
* Use composite partition keys and clustering keys for efficient queries.

**19. What factors influence email deliverability?**
* Dedicated IPs, email classification, sender reputation, spammer banning, feedback processing (bounces/complaints), 
email authentication (SPF, DKIM, DMARC).

**20. What are the two main options for implementing email search?**
* Elasticsearch or a custom search solution. 
* Consider scalability, system complexity, data consistency, and development effort when choosing.

**21. Why are message queues important in a distributed email service?**
* Decouple components (e.g., web servers and SMTP workers), enable asynchronous processing, 
provide buffering during traffic surges, and allow independent scaling of services.

**22. What is exponential backoff and why is it useful in email sending?**
* A retry strategy where the delay between retry attempts increases exponentially. 
* Useful when a recipient's mail server is unavailable, preventing overwhelming the server with repeated requests.

**23. What is denormalization and why is it used in NoSQL database design for email services?**
* Duplicating data across multiple tables to optimize read performance. 
* For example, creating separate read_emails and unread_emails tables to avoid filtering the emails_by_folder table. 
* Improves read speed at the cost of increased storage and write complexity.

**24. What is an LSM (Log-Structured Merge Tree) and why is it relevant to email search?**
* A data structure that optimizes write performance by using sequential writes. 
* New data is initially written to an in-memory cache (Level 0) and then periodically merged to lower levels on disk. 
* Useful for building indexes in a custom search engine, especially given the write-heavy nature of email indexing.

**25. Why is a multi-data center setup important for a distributed email service?**
* Improves availability and fault tolerance. 
* Data is replicated across multiple data centers, allowing users to access their email even if one data center is 
unavailable due to network partitions or other failures. 
* Users connect to the data center that is physically closest to them.

**26. SMTP (Simple Mail Transfer Protocol).**
* The standard protocol for sending emails between mail servers.
* Role in Email System: Used to transfer emails from the sender's mail server to the recipient's mail server.

**27. POP (Post Office Protocol).**
* A protocol for retrieving emails from a mail server to a local client. 
* Downloads emails and typically deletes them from the server.
* Role in Email System: Allows users to download emails to a single device.

**28. IMAP (Internet Mail Access Protocol).**
* A protocol for accessing emails on a mail server. 
* Keeps emails on the server and synchronizes across multiple devices.
* Role in Email System: Enables users to access emails from multiple devices, keeping them stored on the server.

**29. DNS (Domain Name System) - MX Records.**
* DNS is a hierarchical and decentralized naming system for computers, services, or any resource connected to the 
Internet or a private network. 
* MX records are a type of DNS record that specifies the mail server responsible for accepting email messages on behalf 
of a recipient's domain.
* Role in Email System: DNS servers, specifically MX records, are queried to find the correct mail server to deliver 
an email to a specific domain.

**30. MIME (Multipurpose Internet Mail Extension).**
* A standard that allows attachments and non-text content to be sent via email.
* Role in Email System: Enables the inclusion of attachments like images, videos, and documents in emails.

**31. HTTP/HTTPS (Hypertext Transfer Protocol Secure).**
* The foundation of data communication on the web. 
* HTTPS is the secure version of HTTP, providing encrypted communication.
* Role in Email System: Used for webmail interfaces and APIs, allowing clients to interact with email servers securely.

**32. WebSocket.**
* A communication protocol that provides full-duplex communication channels over a single TCP connection.
* Role in Email System: Enables real-time updates and notifications for webmail clients.

**33. Redis.**
* An in-memory data structure store, often used as a cache, message broker, and database.
* Role in Email System: Used as a distributed cache to store recent emails for faster retrieval.

**34. S3 (Amazon Simple Storage Service).**
* A scalable object storage service for storing large files.
* Role in Email System: Used to store email attachments.

**35. Elasticsearch.**
* A distributed, RESTful search and analytics engine capable of solving a growing number of use cases.
* Role in Email System: Used for indexing and searching emails, providing full-text search capabilities.

**36. Kafka.**
* A distributed, fault-tolerant, high-throughput streaming platform.
* Role in Email System: Used as a message queue to decouple services that trigger reindexing from services that perform 
reindexing.

**37. Log-Structured Merge Tree (LSM Tree).**
* A data structure used in databases that optimizes for write-heavy operations by using sequential writes.
* Role in Email System: Can be used to structure the index data on disk for a custom search engine, 
optimizing write performance.

**38. Sender Policy Framework (SPF).**
* An email authentication method designed to prevent spammers from sending messages on behalf of your domain.
* Role in Email System: Helps to improve email deliverability by verifying the sender's domain.

**39. DomainKeys Identified Mail (DKIM).**
* An email authentication method that uses a digital signature to verify the sender and prevent email spoofing.
* Role in Email System: Enhances email security and deliverability by confirming the email's authenticity.

**40. Domain-based Message Authentication, Reporting & Conformance (DMARC).**
* An email authentication protocol that builds on SPF and DKIM to provide a policy for handling emails that fail 
authentication checks.
* Role in Email System: Provides a framework for email senders to indicate that their messages are protected by SPF 
and/or DKIM, and tells receivers what to do if neither of those authentication methods passes.

***

## 25 - S3-like Object Storage.

**1. What are the three main categories of storage systems?**
* Block Storage, File Storage, Object Storage.

**2. Describe Block Storage.**
* Presents raw blocks to the server. 
* Flexible, used by databases and VMs. 
* Can be physically attached or network-attached.

**3. Describe File Storage.**
* Built on block storage. 
* Stores data as files in a hierarchy. 
* Simpler for general-purpose file sharing.

**4. Describe Object Storage.**
* Sacrifices performance for durability, scale, and low cost. 
* Stores data as objects in a flat structure. 
* Accessed via RESTful API.

**5. What does it mean that objects in object storage are immutable?**
* Objects can be deleted or replaced, but not modified in place.

**6. What is a bucket in object storage?**
* A logical container for objects. 
* Bucket names are globally unique.

**7. What is an object in object storage?**
* An individual piece of data stored in a bucket, containing data (payload) and metadata.

**8. What is object versioning?**
* Keeping multiple variants of an object in the same bucket, allowing for recovery of deleted or overwritten objects.

**9. What level of data durability should the system have?**
* Data durability is 6 nines (99.9999%).

**10. What level of service availability should the system have?**
* Service availability is 4 nines (99.99%).

**11. Name the main components in the high-level design of the object storage system.**
* Load Balancer, API Service, IAM, Data Store, Metadata Store.

**12. What is the role of the API Service?**
* Orchestrates calls to IAM, Metadata Service, and Data Store. 
* It's stateless and horizontally scalable.

**13. What is the role of the Data Store?**
* Stores and retrieves the actual object data, using object IDs (UUIDs).

**14. What is the role of the Metadata Store?**
* Stores metadata about the objects (e.g., object ID, bucket, name, version).

**15. What is the role of the Data Routing Service?**
* Provides APIs to access the data node cluster, queries the placement service, and routes data.

**16. What is the role of the Placement Service?**
* Determines which data nodes should store an object, maintains a virtual cluster map, and monitors data node health.

**17. What is the role of the Data Node?**
* Stores the actual object data and ensures reliability through replication.

**18.  What are the two main methods for ensuring data durability in the Data Store?**
* Replication (making multiple copies) and Erasure Coding (splitting data into chunks with parity).

**19. What is multipart upload and why is it used?**
* Slicing large objects into smaller parts for independent uploading, improving reliability and speed.

**20. What is the purpose of garbage collection in object storage?**
* Reclaiming storage space from deleted objects, orphaned data, and corrupted data.

**21. What are the consistency trade-offs in the Data Store?**
* Strong consistency (wait for all replicas) = high latency. 
* Eventual consistency (primary only) = lower latency, potential data loss.

**22. How does a Data Node locate an object by UUID?**
* Uses an object_mapping table that stores the file_name, start_offset, and object_size for each object_id.

**23. What is the recommended sharding strategy for the object metadata table, and why?**
* Shard by hash of (bucket_name, object_name) to distribute load evenly and efficiently support URI-based queries.

**24. Why are checksums used, and where are they stored?**
* To verify data integrity and detect corruption. 
* Checksums are appended to each object and to the end of each data file.

**25. What are failure domains, and why are they important for durability?**
* Physical or logical sections of the environment that can fail independently (e.g., node, rack, AZ). 
* Replicating data across failure domains increases resilience.

**26. Block Storage.**
* Oldest storage type (1960s).
* Treats storage as raw blocks.
* Most flexible; used for filesystems, databases, VMs.
* High performance, high cost.
* Examples: HDDs, SSDs, SAN (Fibre Channel, iSCSI).

**27. File Storage.**
* Built on block storage.
* Organizes data into files and directories (hierarchical).
* General-purpose storage, easy file sharing.
* Medium performance, medium cost.
* Examples: NFS, SMB/CIFS.

**28. Object Storage.**
* Newer storage type.
* Stores data as objects in a flat namespace.
* Sacrifices performance for scalability, durability, and low cost.
* Ideal for "cold" data, archives, backups, unstructured data.
* Access via RESTful APIs.
* Examples: AWS S3, Azure Blob Storage, Google Cloud Storage.

**29. RESTful API.**
* Architectural style for building web services.
* Uses standard HTTP methods (GET, PUT, POST, DELETE) to access and manipulate resources.
* Object storage uses RESTful APIs for accessing buckets and objects.

**30. Bucket.**
* A logical container for objects in object storage.
* Has a globally unique name.
* Must create a bucket before uploading data to object storage.

**31. Object.**
* Fundamental unit of storage in object storage.
* Contains data (payload) and metadata.
* Metadata is key-value pairs describing the object.

**32. Versioning.**
* Keeps multiple versions of an object in the same bucket.
* Enabled at the bucket level.
* Allows recovery from accidental deletion or overwrites.

**33. UUID.**
* Universally Unique Identifier.
* 128-bit number used to uniquely identify objects in the data store.
* Generated by the data routing service.

**34. Consistent Hashing.**
* A technique for distributing data across a cluster of servers.
* Used by the placement service to determine which data nodes should store an object.
* Provides a deterministic lookup that survives the addition or removal of nodes.

**35. Paxos/Raft.**
* Consensus protocols used to ensure that a distributed system can agree on a single value, even in the presence of failures.
* Used by the placement service to maintain a consistent view of the virtual cluster map.

**36. Erasure Coding.**
* A data protection method that breaks data into fragments, expands and encodes it with redundant data pieces, 
and stores the data across different locations or storage media.
* Offers higher durability and lower storage costs compared to replication, but with increased complexity and slower 
access speeds.

**37. Checksum.**
* A small-sized block of data used to detect data errors.
* Generated by a checksum algorithm (e.g., MD5).
* Used to verify data integrity during transmission and storage.

**38. MD5.**
* A widely used cryptographic hash function producing a 128-bit hash value.
* Used as a checksum algorithm to verify data integrity.

**39. Sharding.**
* A database partitioning technique that splits a large database into smaller, more manageable pieces.
* Used to scale the object table in the metadata store.
* Sharding key: hash of (bucket_name, object_name).

**40. TIMEUUID.**
* A version 1 UUID whose timestamp is determined from the current system time.
* Used for the object_version column in the object table when versioning is enabled.

**41. Multipart Upload.**
* A process for uploading large objects in smaller parts.
* Improves reliability and allows for parallel uploads.
* Requires an uploadID to track the upload and ETags to verify parts.

**42. Garbage Collection.**
* The process of automatically reclaiming storage space that is no longer used.
* Handles lazy object deletion, orphan data, and corrupted data.
* Uses compaction to clean up deleted objects.

***

## 26 - Real-time Gaming Leaderboard.

**1. What are the primary functional requirements of a real-time gaming leaderboard?**
* Display top N players (e.g., top 10).
* Show a specific user's rank.
* Optionally, display players within a certain range of a user's rank (e.g., 4 above and below).

**2. What are the key non-functional requirements for a real-time gaming leaderboard?**
* Real-time or near real-time score updates.
* Scalability to handle a large number of users.
* High availability and reliability.

**3. Describe the purpose and parameters of the POST /v1/scores API.**
* Purpose: Updates a user's score on the leaderboard.
* Parameters: user_id, points.
* Internal API, called by game servers.

**4. What is the purpose of the GET /v1/scores API, and what type of information does it return?**
* Purpose: Fetches the top N players from the leaderboard.
* Returns: User ID, username, rank, and score for each of the top players.

**5. Describe the purpose and parameters of the GET /v1/scores/{user_id} API.**
* Purpose: Retrieves the rank of a specific user.
* Parameters: user_id (in the URL).
* Returns: User ID, score, and rank of the specified user.

**6. Briefly describe the high-level architecture of the leaderboard system.**
* Game service: Handles game logic and win validation.
* Leaderboard service: Manages leaderboard data and provides APIs.
* Leaderboard store: Database or cache to store leaderboard data.

**7. Why is it not a good idea to allow the client to directly update the leaderboard score?**
* Security risk. 
* Clients can be compromised or malicious and can manipulate scores. 
* Score updates should be server-side.

**8. How could you implement a leaderboard using a relational database (RDS) like MySQL?**
* Create a table with user_id and score columns.
* Update scores using INSERT or UPDATE statements.
* Fetch leaderboard using SELECT with ORDER BY score DESC.

**9. What are the limitations of using a relational database for a real-time leaderboard with millions of users?**
* Sorting millions of rows for ranking is slow.
* Table scans are inefficient for finding a specific user's rank.
* Not suitable for real-time updates and queries at scale.

**10. What is a Redis Sorted Set, and why is it a good fit for a leaderboard?**
* A data structure that stores members with associated scores, sorted by score.
* Efficient for ranking, retrieving top N members, and updating scores in real-time.

**11. Explain the purpose of the ZADD and ZINCRBY Redis commands in the context of a leaderboard.**
* ZADD: Adds a new member with a score to the sorted set, or updates the score if the member already exists.
* ZINCRBY: Increments the score of a member in the sorted set.

**12. Explain the purpose of the ZRANGE and ZREVRANGE Redis commands. Which one is more suitable for leaderboards?**
* ZRANGE: Returns a range of members from the sorted set, sorted in ascending order.
* ZREVRANGE: Returns a range of members from the sorted set, sorted in descending order. More suitable for leaderboards.

**13. Explain the purpose of the ZRANK and ZREVRANK Redis commands. Which one is more suitable for leaderboards?**
* ZRANK: Returns the rank of a member in the sorted set, with scores sorted in ascending order.
* ZREVRANK: Returns the rank of a member in the sorted set, with scores sorted in descending order. More suitable for leaderboards.

**14. What are some considerations for Redis persistence in a leaderboard system?**
* Redis supports persistence, but restarting from disk can be slow.
* Use a read replica for failover and attach a new replica if the main instance fails.
* Persist data in a relational database (like MySQL) as a backup.

**15. How can AWS Lambda be used in a leaderboard system?**
* Implement API endpoints as Lambda functions.
* Lambda functions can interact with Redis and MySQL.
* Provides auto-scaling and serverless architecture.

**16. What are the two main approaches to sharding Redis for a very large leaderboard?**
* Fixed Partitioning: Divide the leaderboard based on score ranges.
* Hash Partitioning: Use Redis Cluster to automatically shard data across nodes.

**17. Explain how fixed partitioning works for sharding a Redis leaderboard.**
* Divide the overall score range into fixed ranges (e.g., 1-100, 101-200).
* Each shard stores scores within a specific range.
* Requires application logic to determine the correct shard for a user.

**18. Explain how hash partitioning (Redis Cluster) works for sharding a Redis leaderboard.**
* Redis Cluster automatically distributes data across multiple nodes.
* Uses hash slots (16384 slots).
* Keys are assigned to hash slots based on CRC16(key) % 16384.
* Requires gathering top N players from each shard and sorting in the application.

**19. How could you implement a leaderboard using DynamoDB?**
* Use a global secondary index with game_name#year-month as the partition key and score as the sort key.
* Use write sharding to avoid hot partitions.

**20. Why is write sharding important in DynamoDB for a leaderboard, and how does it work?**
* Prevents hot partitions by distributing writes across multiple partitions.
* Append a partition number (e.g., user_id % number_of_partitions) to the partition key.
* Requires querying all partitions and merging results for reads.

**21. Relational Databases (e.g., MySQL).**
* Uses tables with rows and columns.
* Good for simple leaderboards with few users.
* Slow for real-time ranking with millions of users due to table scans and sorting.
* Not ideal for frequently changing data.

**22. Redis.**
* In-memory data store for fast reads and writes.
* Uses key-value pairs.
* Supports sorted sets, which are ideal for leaderboards.
* Can be used with persistence, read replicas, and promotion in case of main instance failure.

**23. Redis Sorted Sets.**
* Data type that stores members with associated scores.
* Members are unique, but scores can repeat.
* Sorted in ascending order by score.
* Implemented using a hash table and a skip list for fast operations (O(log n)).
* Commands: ZADD, ZINCRBY, ZRANGE, ZREVRANGE, ZRANK, ZREVRANK.

**24. Skip List.**
* Probabilistic data structure that uses multiple levels of linked lists to allow fast searching
* Enables efficient searching, insertion, and deletion in sorted sets.
* Uses multiple levels of indexes to skip nodes.

**25. Apache Kafka.**
* Distributed streaming platform.
* Not explicitly used in the primary design, but mentioned as an option.
* Useful when game scores are consumed by multiple services (e.g., analytics, notifications).
* Enables decoupling of services.

**26. AWS Lambda.**
* Serverless computing platform.
* Runs code without managing servers.
* Scales automatically based on traffic.
* Can be used to execute Redis commands and interact with databases.

**27. Amazon DynamoDB.**
* Fully managed NoSQL database.
* Offers reliable performance and scalability.
* Can use global secondary indexes for efficient data access.
* Requires careful design of partition keys to avoid hot partitions.

**28. NoSQL Databases.**
* Alternative to relational databases.
* Optimized for writes and sorting within partitions.
* Examples: DynamoDB, Cassandra, MongoDB.
* Good for large-scale leaderboards.

**29. Redis Cluster.**
* Provides a way to shard data automatically across multiple Redis nodes.
* Uses hash slots (16384 slots).
* Allows adding and removing nodes without redistributing all keys.
* Requires gathering and sorting data from each shard to get the top N results.

***

### 27 - Payment System.

**1. Payment System Definition.**
* A system for settling financial transactions by transferring monetary value. 
* Includes institutions, rules, tech, etc.

**2. Core Functional Requirements.**
* Pay-in flow (receiving money from customers).
* Pay-out flow (sending money to sellers).

**3. Non-Functional Requirements.**
* Reliability & fault tolerance (handle failed payments).
* Reconciliation (ensure data consistency).

**4. TPS Estimation.**
* 1 Million transactions per day = ~10 TPS. Focus on correctness, not just throughput.

**5. Pay-In Flow Overview.**
* Buyer places order -> Money to e-commerce bank account.

**6. Pay-Out Flow Overview.**
* Money from e-commerce bank account -> Seller's bank account. 

**7. Key Pay-In Components.**
* Payment Service, Payment Executor, PSP, Ledger, Wallet.

**8. Payment Service Responsibilities.**
* Accepts payment events, risk checks (AML/CFT), coordinates payment process.

**9. Payment Executor Responsibilities.**
* Executes payment orders via a PSP.

**10. PSP Role.**
* Moves money between accounts (e.g., buyer's credit card to e-commerce platform).

**11. Ledger.**
* Financial record of transactions (debits/credits). Crucial for analysis.

**12. Wallet.**
* Keeps track of merchant account balances.

**13. Payment Service API - POST /v1/payments.**
* Executes a payment event. 
* Includes buyer info, checkout ID, credit card info, payment orders.

**14. Payment Service API - GET /v1/payments/{:id}.**
* Returns the status of a single payment order.

**15. Data Model - Payment Event Table.**
* checkout_id (PK), buyer_info, seller_info, credit_card_info, is_payment_done.

**16. Data Model - Payment Order Table.**
* payment_order_id (PK), buyer_account, amount, currency, checkout_id (FK), payment_order_status, ledger_updated, 
wallet_updated.

**17. Double-Entry System.**
* Every transaction recorded in two accounts (debit and credit) with the same amount. 
* Sum of all entries = 0.

**18. Hosted Payment Page.**
* PSP provides the payment page (widget/iframe) to capture card details directly. 
* Avoids storing sensitive data.

**19. Reconciliation.**
* Comparing states among services (Ledger, Wallet, PSP) to ensure agreement. 
* Last line of defense.

**20. Reconciliation - Settlement Files.**
* PSPs/Banks send daily settlement files with balances and transactions.

**21. Handling Payment Processing Delays.**
* PSP may require human review or extra authentication (3D Secure). 
* Use webhooks or polling for status updates.

**22. Communication - Synchronous vs. Asynchronous.**
* Sync (HTTP): Simpler, but less scalable/resilient. 
* Async (Queues/Kafka): More complex, but better for scale.

**23. Asynchronous Communication - Single Receiver.**
* Each message processed by one service (e.g., message queue).

**24. Asynchronous Communication - Multiple Receivers.**
* Each message processed by multiple services (e.g., Kafka for analytics, billing).

**25. Handling Failed Payments - Retry Queue.**
* For transient/retryable errors.

**26. Handling Failed Payments - Dead Letter Queue.**
* For messages that fail repeatedly. Requires investigation.

**27. Exactly-Once Delivery.**
* Crucial to avoid double charges. 
* Implemented with at-least-once (retry) and at-most-once (idempotency).

**28. Idempotency.**
* Operations can be applied multiple times without changing the result beyond the initial application. 

**29. Idempotency Key.**
* Unique value generated by the client (UUID). 
* Used to identify duplicate requests.

**30. Payment Security.**
* HTTPS, encryption, SSL, rate limiting, tokenization, PCI DSS, fraud detection (AVS, CVV, etc.).

**31. Card Schemes.**
* Organizations that process credit card transactions (Visa, MasterCard, Amex, Discover). 
* Complex ecosystem.

**32. Amount Data Type.**
* Use "String" for amount instead of "Double" to avoid rounding errors and handle very large/small numbers.

**33. Payment Order Status.**
* Enum: NOT_STARTED, EXECUTING, SUCCESS, FAILED. 
* Tracks the lifecycle of a payment.

**34. Nonce.**
* A unique identifier (UUID) used to ensure exactly-once registration of a payment order with a PSP.

**35. Webhooks.**
* URLs registered with PSPs to receive asynchronous payment status updates.

**36. Retry Strategies.**
* Immediate, Fixed Intervals, Incremental Intervals, Exponential Backoff, Cancel. 
* Choose based on error type.

**37. 429 Too Many Requests.**
* HTTP status code returned when multiple concurrent requests are detected with the same idempotency key.

**38. Database Selection.**
* For payment systems, prioritize proven stability, supporting tools, and DBA availability over raw performance. 
* Traditional relational databases with ACID are often preferred.

**39. Monitoring, Alerting, and Debugging Tools.**
* Essential for a payment system. 
* Monitor key metrics, alert on anomalies, and provide tools for debugging failed transactions.

**40. Currency Exchange.**
* Important consideration for international payment systems.

**41. What is a PSP?**
* A third-party that moves money from account A to account B. 
* Examples: Stripe, PayPal, Adyen, Square, Braintree. 
* They handle the complexities of connecting to banks and card schemes.

**42. What are card schemes?**
* Organizations that process credit card transactions. 
* Examples: Visa, MasterCard, Discover. 
* They have complex ecosystems and rules.

**43. What is a Ledger in a payment system?**
* A financial record of all payment transactions. 
* Crucial for post-payment analysis (revenue calculation, forecasting). 
* Uses the double-entry system.

**44. What is a Wallet in a payment system?**
* Keeps track of the account balance of merchants and can record user payment history.

**45. What is a RESTful API and how is it used in the payment service?**
* Used for communication with the payment service. 
* Key endpoints: POST `/v1/payments` (execute payment) and GET `/v1/payments/{:id}` (get payment status). 
* Uses JSON for data exchange.

**46. What is a Nonce?**
* A unique identifier, often a UUID, used to ensure exactly-once registration of a payment order with a PSP. 
* It helps prevent duplicate payments.

**47. What are webhooks used for in payment processing?**
* Asynchronous mechanism for PSPs to notify the payment service about payment status updates. 
* The payment service registers a URL with the PSP, and the PSP sends a notification to that URL when the payment status changes.

**48. What is Reconciliation in a payment system?**
* A process that periodically compares the states of related services (internal and external) to ensure consistency. 
* It's a last line of defense against data inconsistencies. 
* Settlement files from PSPs/banks are compared with the ledger.

**49. What are Retry and Dead Letter Queues used for?**
* Mechanisms for handling failed payments. 
* Retry queue holds retryable errors (transient issues). 
* Dead letter queue holds messages that have repeatedly failed and require investigation.

**50. What is Idempotency and why is it important?**
* The property of an operation that can be applied multiple times without changing the result beyond the initial application. 
* Crucial for preventing double charges. Achieved using idempotency keys.

**51. What is an Idempotency Key?**
* A unique value generated by the client for each request, used to ensure idempotency. 
* If the same key is received twice, the server knows it's a retry and doesn't reprocess the request.

**52. What is Exponential Backoff?**
* A retry strategy where the waiting time between retries increases exponentially after each failure. 
* Helps avoid overwhelming the system during temporary outages.

**53. What is the double-entry system?**
* Every transaction is recorded into two separate ledger accounts with the same amount. 
* One account is debited and the other is credited with the same amount

***

## 28 - Digital Wallet.

**1. Digital Wallet Basics.**
* A payment platform service where users store funds for later use. 
* Enables faster, fee-free transfers between users on the same platform.

**2. Core Requirement: Balance Transfer.**
* The primary function is transferring balances between digital wallets.

**3. Performance Goal: TPS.**
* The system needs to support a high transaction rate (e.g., 1,000,000 TPS).

**4. Reliability.**
* Aim for high reliability (e.g., 99.99% availability).

**5. Transactions.**
* The system must ensure transactional guarantees (ACID properties).

**6. Reproducibility.**
* The system should be able to reconstruct historical balances by replaying events.

**7. API Endpoint.**
* POST `/v1/wallet/balance_transfer`: Used to initiate balance transfers.

**8. API Parameters.**
* from_account, to_account, amount, currency, transaction_id: Key details for a transfer request.

**9. In-Memory Sharding.**
* Distribute account balances across multiple Redis nodes for scalability.

**10. Zookeeper.**
* Used to store sharding information for Redis nodes in the in-memory solution.

**11. Wallet Service.**
* A stateless service that receives, validates, and executes transfer commands.

**12. Distributed Transactions.**
* Ensuring atomicity when updates span multiple database nodes.

**13. Two-Phase Commit (2PC).**
* A distributed transaction protocol involving a prepare and commit phase. 
* Can be slow and has a single point of failure (coordinator).

**14. Try-Confirm/Cancel (TC/C).**
* A compensating transaction approach where resources are reserved, then either confirmed or cancelled.

**15. TC/C - Try Phase.**
* The coordinator asks all databases to reserve resources for the transaction.

**16. TC/C - Confirm Phase.**
* If all "Try" operations succeed, the coordinator asks all databases to confirm the operation.

**17. TC/C - Cancel Phase.**
* If any "Try" operation fails, the coordinator asks all databases to cancel the operation, undoing any changes.

**18. Phase Status Table.**
* Stores the progress of a TC/C transaction to handle wallet service restarts.

**19. Saga Pattern.**
* A distributed transaction management approach where a series of local transactions are executed. 
* If one fails, compensating transactions are used to rollback.

**20. Event Sourcing.**
* A design pattern where all state changes are recorded as a sequence of events.

**21. Command vs. Event.**
* A command is a request to perform an action, while an event is a record of a state change that has occurred.

**22. State Machine.**
* Validates commands, generates events, and applies events to update the state.

**23. Reproducibility (Event Sourcing).**
* Reconstructing historical states by replaying events from the beginning.

**24. CQRS.**
* Command Query Responsibility Segregation - separating read and write operations.

**25. Raft Consensus.**
* A consensus algorithm used to replicate the event list across multiple nodes for reliability.

**26. File-Based Event Sourcing.**
* Storing commands, events, and state in local files (e.g., using mmap, SQLite, RocksDB) for high performance.

**27. Snapshotting.**
* Periodically saving the current state to a file (snapshot) to speed up reproducibility.

**28. Pull vs. Push (CQRS).**
* Pull: Clients periodically poll for updates. 
* Push: The system pushes updates to clients in real-time.

**29. Distributed Event Sourcing.**
* Sharding data and using distributed transactions (TC/C or Saga) to scale the event sourcing architecture.

**30. Redis.**
* An in-memory data structure store, often used as a cache or message broker.
* Role: Initial design used Redis for fast account balance storage, but it lacked durability and transactional guarantees.

**31. Zookeeper.**
* A centralized service for maintaining configuration information, naming, providing distributed synchronization, 
and group services.
* Role: Used to store sharding information for the Redis cluster, allowing the wallet service to locate the correct 
Redis node for each account.

**32. Relational Databases (e.g., MySQL, PostgreSQL).**
* Databases that organize data into tables with rows and columns, supporting ACID transactions.
* Role: Replaced Redis to provide transactional guarantees for balance transfers, ensuring atomicity.

**33. Two-Phase Commit (2PC).**
* A distributed transaction protocol that ensures all participating nodes either commit or abort a transaction.
* Role: A low-level solution to ensure atomicity across multiple database nodes, but it suffers from performance issues 
and can be a single point of failure.

**34. Try-Confirm/Cancel (TC/C).**
* A compensating transaction pattern where resources are reserved in the "Try" phase, confirmed if all "Try" operations 
succeed, or canceled to revert changes.
* Role: A high-level solution for distributed transactions, implemented in the application layer, offering database 
independence but requiring manual management of transaction details.

**35. Saga.**
* A distributed transaction management approach where a long-lived transaction is divided into a sequence of smaller, 
local transactions.
* Role: An alternative to TC/C, particularly suitable for microservices, where operations are executed in a linear order, 
and compensating transactions are used for rollback.

**36. Apache Kafka.**
* A distributed streaming platform used for building real-time data pipelines and streaming applications.
* Role: Used as a command queue in the event sourcing architecture, ensuring commands are processed in FIFO order.

**37. Event Sourcing.**
* An architectural pattern where all changes to application state are stored as a sequence of events.
* Role: Provides reproducibility, auditability, and a reliable source of truth for account balances and transactions.

**38. Command Query Responsibility Segregation (CQRS).**
* A pattern that separates read and write operations, allowing for optimized data models for each.
* Role: Used with event sourcing to create read-only state machines for querying account balances and other views of the data.

**39. mmap (Memory Mapping).**
* A technique that maps a file or device into memory, allowing it to be accessed as if it were an array in memory.
* Role: Used to optimize file-based command, event, and state storage, providing fast sequential read and write operations.

**40. SQLite/RocksDB.**
* Embedded file-based databases. 
* SQLite is a relational database, while RocksDB is a key-value store.
* Role: Used for local state storage, with RocksDB's LSM tree structure optimizing write performance.

**41. Apache Hadoop (HDFS).**
* A distributed file system designed to store and process large datasets.
* Role: Used for storing snapshots of the system state, enabling faster recovery and auditability.

**42. Raft.**
* A consensus algorithm that ensures data is replicated consistently across multiple nodes.
* Role: Provides high reliability by replicating the event list across multiple nodes, ensuring no data loss and 
maintaining data order.

**43. Reverse Proxy.**
* A server that sits in front of one or more backend servers, forwarding client requests to those servers.
* Role: Used to provide a synchronous interface to the asynchronous event sourcing system, pushing real-time status 
updates to clients.

***

## 29 - Stock Exchange.

**1. What is the primary function of a stock exchange?**
* To efficiently match buyers and sellers of securities (stocks, etc.). Modern exchanges are electronic systems.

**2. Name two types of clients that interact with a stock exchange.**
* Retail clients (through brokers) and Institutional clients (direct, high-volume).

**3. Define a limit order.**
* An order to buy or sell at a specific price. It may not be executed immediately.

**4. Define a market order.**
* An order to buy or sell immediately at the best available price. Guarantees execution but not price.

**5. What information does L1 market data contain?**
* Best bid price, best ask price, and quantities. 
* L2 includes more price levels. 
* L3 shows price levels and queued quantity at each level.

**6. What information does a candlestick chart display?**
* Open, close, high, and low prices for a specific time interval.

**7. What is the FIX protocol used for?**
* A standard protocol for exchanging securities transaction information.

**8. List three key non-functional requirements for a stock exchange.**
* Availability (99.99%+), Fault Tolerance, Low Latency (millisecond level). 
* Security (account management, KYC, DDoS protection).

**9. Name the main components in the trading flow critical path.**
* Client Gateway -> Order Manager -> Sequencer -> Matching Engine.

**10. What are the main responsibilities of the Client Gateway?**
* Input validation, rate limiting, authentication, and normalization of orders.

**11. What are the key functions of the Order Manager?**
* Risk checks, wallet verification (sufficient funds), and order state management.

**12. What are the primary responsibilities of the Matching Engine?**
* Maintain order books, match buy/sell orders, and distribute execution stream.

**13. What is the role of the Sequencer?**
* Assigns sequence IDs to incoming orders and outgoing executions to ensure determinism and enable fast recovery.

**14. What does the Market Data Publisher do?**
* Builds order books and candlestick charts from the stream of executions.

**15. What is the purpose of the Reporting flow?**
* Collects data for trading history, tax reporting, compliance, and settlements.

**16. What data structure is commonly used for an order book, and why?**
* A combination of a HashMap and doubly-linked list to allow for O(1) time complexity for adding, canceling, and matching orders.

**17. How do exchanges minimize latency?**
* By reducing network hops (single server design) and using in-memory data structures.

**18. What is mmap and how is it used in exchange design?**
* A UNIX system call that maps a file into memory, enabling high-performance inter-process communication without disk I/O.

**19. What is event sourcing, and why is it useful in an exchange?**
* Storing an immutable log of all state-changing events instead of current state. 
* Enables auditability, replayability, and easier recovery.

**20. How do exchanges achieve high availability?**
* Redundant instances (hot-warm), fast failover mechanisms, and data replication (e.g., using Raft).

**21. What are the two types of determinism important in exchange design?**
* Functional determinism (replaying events in the same order yields the same results) and Latency determinism 
(consistent, low latency for all trades, measured by 99th percentile latency).

**22. What is an application loop in the context of a low-latency exchange?**
* A single-threaded loop that continuously polls for tasks, pinned to a specific CPU core to minimize context switching 
and lock contention.

**23. What are ring buffers, and how are they used in market data processing?**
* Fixed-size, pre-allocated queues used to efficiently manage and process market data with minimal overhead 
(no object creation/deletion, lock-free).

**24. Why is it important to ensure fair distribution of market data?**
* To prevent clients from gaining an unfair advantage by receiving data earlier than others. 
* Techniques like multicast are used.

**25. What is colocation, and why do exchanges offer it?**
* Placing clients' servers in the same data center as the exchange to minimize network latency. 
* It's a paid service that provides a competitive edge.

**26. FIX Protocol.**
* Vendor-neutral communication protocol for securities transactions. 
* Used for exchanging info between brokers and exchanges. 
* Example: `8=FIX.4.2 | 9=176 | 35=8 | 49=PHLX`.
* Think of it as a standard language for trading systems to talk to each other.

**27. mmap.**
* A POSIX-compliant UNIX system call that maps a file or device into memory. 
* Enables high-performance shared memory between processes, especially when used with `/dev/shm` (memory-backed file system).
* Key for low-latency communication within a single server, avoiding network overhead.

**28. Ring Buffer.**
* Fixed-size, circular queue where the head connects to the tail. 
* Used in Market Data Publisher (MDP) for efficient data handling. 
* Pre-allocated memory avoids object creation/deletion overhead.
* Optimized for continuous data production and consumption with minimal overhead.

**29. Reliable UDP (Multicast).**
* Used for distributing market data to multiple subscribers simultaneously. 
* Multicast sends data from one source to a group of hosts. 
* Reliable UDP adds mechanisms for retransmission to ensure data delivery.
* Ensures fair and timely market data distribution.

**30. Raft.**
* A consensus algorithm used for leader election and maintaining consistency across distributed systems. 
* Ensures that if the primary instance goes down, one of the backup instances can take over and continue operations 
without data loss.
* Important for fault tolerance and high availability.

**31. HdrHistogram.**
* Used to measure latency determinism, specifically the 99th percentile latency. 
* Helps ensure stable performance across almost all trades.
* Important for identifying and addressing large latency fluctuations.




















