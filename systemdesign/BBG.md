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













