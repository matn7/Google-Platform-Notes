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
*What's the existing tech stack?

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
* Each request consumes a token. If no tokens, request is dropped.

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
Hinted handoff is a mechanism to improve availability during temporary failures. When a node is temporarily unavailable, 
another node accepts writes on its behalf. Once the unavailable node recovers, the "hinted" data is handed off to it, 
ensuring eventual consistency.

**2. Explain the CAP theorem. Why is a CA system not suitable for real-world distributed systems?**
The CAP theorem states that a distributed system can only guarantee two out of three properties: Consistency, Availability, 
and Partition Tolerance. CA systems sacrifice partition tolerance, which is unacceptable because network partitions 
(communication breaks) are inevitable in real-world distributed environments.

**3. What are SSTables? How are they used in the write path of a key-value store, and what problem do they solve?**
SSTables (Sorted String Tables) are sorted lists of key-value pairs stored on disk. In the write path, data from the 
memory cache is flushed to SSTables. They provide a persistent, sorted storage format that enables efficient reads, 
especially when combined with Bloom filters.

**4. Describe how Merkle trees are used to handle permanent failures and maintain data consistency in a distributed 
key-value store.**
Merkle trees are hash trees used to efficiently detect inconsistencies between replicas. By comparing the root hashes 
of two Merkle trees, the system can quickly determine if the replicas have the same data. If not, it can traverse the 
tree to identify and synchronize only the inconsistent buckets, minimizing data transfer.

**5. Explain the concept of vector clocks and how they are used to resolve write conflicts in an eventually consistent 
key-value store.**
Vector clocks are `[server, version]` pairs associated with a data item. They track the history of updates to the data. 
By comparing vector clocks, the system can determine if two versions of data are ancestors (one descends from the other) 
or siblings (conflicting updates). This allows the system to detect and resolve conflicts, ensuring that updates are 
eventually consistent.

**6. What is a "coordinator" node in the context of a distributed key-value store, and what role does it play in read 
and write operations?**
A coordinator node acts as a proxy between the client and the key-value store cluster. For write operations, it receives 
the write request, coordinates the replication to multiple nodes, and ensures the write quorum is met. For read operations, 
it retrieves data from the necessary replicas and returns the result to the client.

**7. Explain the concept of "quorum consensus." How do the parameters N, W, and R relate to consistency and availability, 
and what are some typical configurations?**
Quorum consensus is a method to achieve consistency in distributed systems. N is the number of replicas, W is the write 
quorum (minimum number of replicas that must acknowledge a write for it to be considered successful), and R is the read 
quorum (minimum number of replicas that must respond to a read request).
* R=1, W=N: Fast reads.
* W=1, R=N: Fast writes.
* W+R > N: Strong consistency.

**8. What is a Bloom filter, and how is it used in the read path of a key-value store? What problem does it solve?**
A Bloom filter is a probabilistic data structure used to test whether an element is a member of a set. In a key-value 
store, it's used to quickly check if an SSTable might contain a specific key before actually reading the SSTable from disk. 
This avoids unnecessary disk reads, improving read performance.

**9. Describe the gossip protocol and how it's used for failure detection in a distributed key-value store.**
The gossip protocol is a decentralized method for failure detection. Each node maintains a membership list with heartbeat 
counters. Nodes periodically exchange heartbeat information with random peers. If a node's heartbeat counter doesn't increase 
for a certain period, it's considered offline, and this information is propagated through the network.

**10. What are the advantages of using consistent hashing for data partitioning in a distributed key-value store?**
Back: Consistent hashing offers several advantages:
* Automatic scaling: Servers can be added or removed without requiring a complete redistribution of data.
* Load balancing: Data is distributed evenly across servers.
* Heterogeneity: Servers with different capacities can be assigned a proportional number of virtual nodes.
* Minimal data movement: When servers are added or removed, only a small portion of the data needs to be moved.

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
Snowflake: Divides the 64-bit ID into sections:
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
* performance. Doesn't need unique ID generator.
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

## 10 - Design A Web Crawler.

**1. Core Crawling Process.**
* Concept: Basic Web Crawler Algorithm.
* Explanation: Start with seed URLs, download web pages, extract new URLs, and repeat. Scalability, robustness, politeness, 
and extensibility are key considerations.
* Components: URL Frontier, HTML Downloader, Content Parser, URL Extractor, URL Filter.

**2. URL Frontier.**
* Concept: Managing URLs to be downloaded.
* Explanation: Addresses politeness (avoiding overloading servers), prioritization (crawling important pages first), 
and freshness (recrawling updated pages). Uses front queues (prioritization) and back queues (politeness).
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
Examples: Cassandra, HBase.

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

## 14 - Design A Search Autocomplete System.
**1. Autocomplete Definition.**
Front end feature that predicts and suggests queries as the user types. Also known as typeahead or search-as-you-type.

**2. Core Requirements.**
Fast response time (<=100ms), relevance, sorted results (by popularity), scalability, high availability.

**3. Data Gathering Service.**
Collects and aggregates user input queries. Can be real-time (for up-to-the-second results) or batched (e.g., weekly).

**4. Query Service.**
Receives a search query prefix and returns the top k most frequently searched terms.

**5. Trie Data Structure.**
A tree-like data structure optimized for storing and retrieving strings based on prefixes. 
Each node represents a character or prefix.

**6. Trie Optimizations.**
Limit the maximum length of a prefix to reduce search space. Cache the top k search queries at each node for faster retrieval.

**7. Data Aggregation.**
Raw search query logs are aggregated to count query frequencies over a period (e.g., weekly).

**8. Workers.**
Asynchronous processes that build the trie data structure from aggregated data and store it in a database.

**9. Trie Cache.**
In-memory distributed cache that stores the trie for fast read access by the query service.

**10. Trie DB.**
Persistent storage for the trie data. Can be a document store (e.g., MongoDB) or a key-value store.

**11. Query Service Architecture.**
Load balancer distributes requests to API servers, which fetch data from the Trie Cache. 
Cache misses are handled by querying the Trie DB.

**12.  Front-End Optimizations.**
Use AJAX requests to avoid full page reloads. Implement browser caching to store autocomplete suggestions locally.

**13. Trie Operations.**
Create: Built from aggregated data. 
Update: Performed weekly (full trie rebuild) or by updating individual nodes (less efficient). 
Delete: Filter layer to remove unwanted suggestions.

**14. Scaling Storage (Sharding).**
Partitioning the trie data across multiple servers. 
Naive approach: shard by the first character. 
Better approach: analyze historical data for even distribution.

**15. Multi-Language Support.**
Use Unicode to store characters from different languages. Consider building separate tries for different countries/regions.

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

**12, Geospatial Indexing - Basic Idea - Core Concept.**
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


## 19 - Google Maps.

**1. Core Features.**
* Location Updates: Mobile clients send location data.
* Navigation: Finding routes from A to B.
* ETA: Estimating travel time.
* Map Rendering: Displaying the map.

**2: Non-Functional Requirements.**
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
* Producers, Consumers, Brokers, Data Storage, State Storage, Metadata Storage, Coordination Service

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
Increases availability. Each partition has multiple replicas.

**24. In-Sync Replicas (ISR).**
* Replicas that are "in-sync" with the leader. Configurable lag.

**25. Delivery Semantics.**
* At-most-once, At-least-once, Exactly-once. Trade-offs between reliability and performance.

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
* ad_id, click_minute, count (and potentially filter_id)

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
* QPS decreases as users drop off at each stage. (e.g., 300 -> 30 -> 3)

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

**7: What role does DNS play in email? What are MX records?**
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
* Data durability is 6 nines (99.9999%)

**10. What level of service availability should the system have?**
* Service availability is 4 nines (99.99%)

**11. Name the main components in the high-level design of the object storage system.**
* Load Balancer, API Service, IAM, Data Store, Metadata Store

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
(*1 Million transactions per day = ~10 TPS. Focus on correctness, not just throughput.

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

***

## 28 - Digital Wallet.

**1. Digital Wallet Basics.**
A payment platform service where users store funds for later use. 
Enables faster, fee-free transfers between users on the same platform.

**2. Core Requirement: Balance Transfer**
* The primary function is transferring balances between digital wallets.

**3. Performance Goal: TPS.**
* The system needs to support a high transaction rate (e.g., 1,000,000 TPS).

**4. Reliability.**
* Aim for high reliability (e.g., 99.99% availability).

**5. Transactions.**
* The system must ensure transactional guarantees (ACID properties).

**6. Reproducibility.**
The system should be able to reconstruct historical balances by replaying events.

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

**15. TC/C - Try Phase*.*
* The coordinator asks all databases to reserve resources for the transaction.

**16. TC/C - Confirm Phase.**
* If all "Try" operations succeed, the coordinator asks all databases to confirm the operation.

**.17: TC/C - Cancel Phase.**
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
* Security (account management, KYC, DDoS protection)

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






















