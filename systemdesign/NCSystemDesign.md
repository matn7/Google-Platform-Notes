## Design a Rate Limiter — System Design Notes.

**1. What is a Rate Limiter?**

* A rate limiter controls how many requests a client can make during a given period of time.
* Example:
  * Maximum 100 requests/minute per user.
  * Request #1–100 → accepted.
  * Request #101 within the same limit → rejected.
* Once enough time passes, the user can make requests again.

**Why do we need it?**
* Prevent abuse / spam.
* Protect servers from overload.
* Reduce effects of DDoS-like traffic.
* Control API usage.
* Prevent a single user from consuming disproportionate resources.
* Enforce API tiers:
  * Free → 100 requests/min.
  * Premium → 1,000 requests/min.
* The basic decision is therefore:
```
Incoming Request
        ↓
    Rate Limiter
    ↙     ↘
ALLOW     DENY
  ↓
Server
```

**2. Requirements.**

* In a system-design interview, start by clarifying requirements.

**Functional requirements.**
* The system should:
  * Identify the client/user making the request.
  * Track how many requests they have made.
  * Determine whether the request exceeds their configured limit.
  * Allow requests below the limit.
  * Reject requests above the limit.
  * Potentially support different limits for different APIs/users.
* For example:
```
User A → 100 requests / minute
User B → 100 requests / minute

POST /login → 5 requests / minute
GET /feed  → 100 requests / minute
```

**Non-functional requirements.**
* The limiter should have:
* Low latency  Every request potentially passes through it.
* High availability: If the rate limiter goes down, it shouldn't necessarily take the entire service down.
* Scalability: It may need to handle millions of requests.
* Accuracy: Limits should be enforced reasonably consistently.
* Distributed operationL Multiple application servers need to share rate-limit state.

**3. Where Does the Rate Limiter Live?**

* A basic architecture might be:
```
Client
    ↓
Load Balancer
    ↓
Rate Limiter
    ↓
Application Servers
    ↓
Database
```
* The rate limiter sits before expensive application processing.
* Why?
* If an abusive request reaches:
```
Application
↓
Database
↓
External APIs
```
* before being rejected, we've already wasted resources.
* Better:
```
    Request
        ↓
    Rate Limiter
        ↓
    Allowed?
    ↙       ↘
Yes         No
↓           ↓
App       HTTP 429
```

**4. API Behaviour.**

* When the request is allowed:
```
HTTP 200
```
* or whatever response the actual endpoint returns.
* When the user exceeds the limit:
```
HTTP 429 Too Many Requests
```
* The API may also return headers describing the limit.
* Conceptually:
```
X-RateLimit-Limit: 100
X-RateLimit-Remaining: 0
Retry-After: 30
```
* The important interview point:
> 429 = Too Many Requests

**5. Naive Solution — Counter.**

* The simplest implementation is a counter.
* Suppose:
```
Limit = 100 requests/minute
```
* Store:
```
user_id → request_count
```
* Example:
```
Alice → 54
Bob   → 98
John  → 100
```
* When John sends another request:
```
100 >= limit
→ reject
```
* At the end of the minute, reset the counter.
* This leads to the Fixed Window Counter algorithm.

**6. Fixed Window Counter.**

* Divide time into fixed windows.
* Example:
```
Limit = 5 requests/minute
```
* Then:
```
12:00:00 ───────── 12:00:59
window #1

12:01:00 ───────── 12:01:59
window #2
```
* For each user:
```
(user_id, window) → count
```
* Example:
```
("user123", "12:00") → 4
```
* Next request:
```
count = 4
limit = 5

4 < 5
→ allow
→ count = 5
```
* Next:
```
5 >= 5
→ reject
```

**Advantages:**
* Very simple.
* Fast.
* Low memory usage.
* Easy to implement using Redis.

**Problem: boundary bursts.**
Suppose the limit is:
```
100 requests/minute
```
* A user sends:
```
100 requests at 12:00:59
```
* Then:
```
100 requests at 12:01:01
```
* Technically both windows are valid.
* But the server received:
```
200 requests in ~2 seconds
```
* So fixed windows can allow large bursts around window boundaries.

**7. Sliding Window.**

* Instead of asking:
> How many requests happened during this fixed minute?
* Ask:
> How many requests happened during the previous 60 seconds?
* Example:
```
Current time: 12:01:30
```
* Relevant window:
```
12:00:30 ───────────── 12:01:30
```
* We could store timestamps:
```
user123:
[
    12:00:45,
    12:00:51,
    12:01:03,
    12:01:12
]
```
* For each request:
  * Get current timestamp.
  * Remove timestamps older than the window.
  * Count remaining timestamps.
  * Compare count against limit.
  * If allowed, insert the new timestamp.
* Conceptually:
```
requests = timestamps from last 60 sec
if len(requests) >= limit:
    reject
else:
    add(current_timestamp)
    allow
```

**Advantages:**
* More accurate.
* Avoids the major fixed-window boundary problem.

**Disadvantages:**
* More memory.
* More computational work.
* Potentially need to store many timestamps.

**8. Token Bucket.**
* Another very important rate-limiting algorithm.
* Imagine each user has a bucket containing tokens.
* Example:
```
Bucket capacity = 10 tokens
Refill rate     = 1 token/sec
```
* Each request consumes:
```
1 token
```
* So:
```
Request
    ↓
Token available?
    ↙          ↘
  Yes          No
   ↓            ↓
consume      reject
token
    ↓
allow
```
* Suppose:
```
Bucket = 10
```
* User immediately sends 10 requests:
```
10 → 9 → 8 → ... → 1 → 0
```
* Those requests are allowed.
* Request #11:
```
0 tokens
→ reject
```
* After one second:
```
+1 token
```
* Another request can proceed.

**Why Token Bucket is useful.**
* It allows controlled bursts.
* If a user hasn't made requests for a while:
```
bucket fills up
```
* Then they can briefly make several requests at once.
* But sustained traffic is controlled by:
```
refill rate
```

**Important parameters.**
* Remember these two:
```
Bucket capacity → maximum burst size
Refill rate     → sustained request rate
```

**9. Leaky Bucket.**
* Another algorithm you should recognize.
* Imagine requests entering a bucket:
```
Requests
↓ ↓ ↓ ↓
┌─────────┐
│ Bucket  │
└────┬────┘
     ↓
constant rate
```
* Requests leave at a fixed rate.
* Example:
```
Incoming traffic:
||||||||||||||||

Processed traffic:
|   |   |   |   |
```

**Advantages.**
* Smooth traffic.
* Prevents sudden bursts from reaching downstream services.

**Disadvantage.**
* Bursty legitimate traffic may have to wait or be discarded.
* Quick distinction:
```
Token Bucket
→ controls permission to send requests
→ allows bursts

Leaky Bucket
→ smooths outgoing request rate
→ less bursty
```

**10. Which Algorithm Should You Choose?**

* There isn't one universally correct answer.
* For an interview, explain the trade-off.

| Algorithm      | 	Advantage                | 	Disadvantage                  |
|----------------|---------------------------|--------------------------------|
| Fixed Window   | 	Simple + fast            | 	Boundary bursts               |
| Sliding Window | 	More accurate            | 	More memory/work              |
| Token Bucket   | 	Allows controlled bursts | 	More logic/state              |
| Leaky Bucket   | 	Smooth traffic           | 	Bursts may be delayed/dropped |

* A strong default answer is often:
> Token Bucket, because it supports bursts while controlling the long-term request rate.
* But the requirements determine the best choice.

**11. Where Do We Store the Counters?**

* A single application server could store:
```
HashMap<UserId, RateLimit>
```
* But this fails in a distributed architecture.
* Imagine:
```
             Load Balancer
             /     |     \
            /      |      \
        Server A Server B Server C
```
* Each server has its own counter.
* Suppose:
```
Limit = 100
```
* User makes:
```
100 → Server A
100 → Server B
100 → Server C
```
* The user could effectively make:
```
300 requests
```
* because each server sees only its own state.
* Therefore we need shared rate-limit state.

**12. Redis.**

* A common solution is Redis.
* Architecture:
```
                Client
                   ↓
             Load Balancer
                   ↓
          ┌────────┼────────┐
          ↓        ↓        ↓
        App 1    App 2    App 3
          \        |        /
           \       |       /
                 Redis
                   ↓
          Rate-limit state
```
* Redis is a good fit because it provides:
  * Very fast reads/writes.
  * In-memory storage.
  * Atomic operations.
  * TTL / expiration.
  * Increment operations.
  * Distributed access.
* Example key:
```
rate_limit:user123
```
* Value:
```
57
```
* Expiration:
```
60 seconds
```
* Conceptually:
```
INCR rate_limit:user123
EXPIRE rate_limit:user123 60
```
* Once TTL expires:
```
key disappears
```
* which naturally resets the rate limit.

**13. Race Conditions.**

* Consider:
```
Limit = 100
Current count = 99
```
* Two servers receive requests simultaneously.
* Server A:
```
read count → 99
```
* Server B:
```
read count → 99
```
* Both conclude:
```
99 < 100
→ allow
```
* Now the effective count becomes:
```
101
```
* This is a race condition.
* Therefore operations such as:
```
read
check
increment
```
* need to be atomic.
* Redis helps here through atomic commands and, for more complicated logic, mechanisms such as transactions/scripts.
* Interview keyword:
> Atomic operation.

**14. Scaling Redis.**

* Eventually one Redis instance may not be enough.
* We can partition rate-limit state across multiple Redis nodes.
* Example:
```
User ID
    ↓
hash(user_id)
    ↓
Redis shard
```
* Architecture:
```
                Rate Limiter
                     ↓
               hash(user_id)
             /       |       \
            ↓        ↓        ↓
        Redis 1   Redis 2   Redis 3
```
* Example:
```
Users A–H → Redis 1
Users I–P → Redis 2
Users Q–Z → Redis 3
```
* In practice we'd use hashing rather than alphabetical ranges.
* The goal is to distribute:
  * Memory usage.
  * Read/write traffic.
  * CPU/network load.
* across multiple nodes.

**15. What Is the Rate-Limit Key?**

* Rate limiting doesn't necessarily have to be per user.
* Possible keys include:
```
user_id
IP_address
API_key
endpoint
organization_id
```
* Or combinations:
```
user_id + endpoint
```
* Example:
```
rate_limit:user123:/login
```
* This allows rules such as:
```
/login
5 requests/min

/search
100 requests/min

/feed
500 requests/min
```

**16. Configuration.**

* We don't want limits hardcoded throughout the application.
* Instead maintain rate-limit rules/configuration.
* Example:
```
/login:
5 requests/min

/search:
100 requests/min

/upload:
20 requests/hour
```
* The rate limiter loads these rules and applies the appropriate policy.
* Conceptually:
```
              Rate Limiter
               ↙       ↘
       Rule Config     Redis
           ↓             ↓
     What is limit?   Current usage
```
* Then:
```
configured limit
+
current usage
↓
allow / reject
```

**17. High-Level Final Architecture.**

* A good interview diagram might look like:
```
                    Clients
                       ↓
                Load Balancer
                       ↓
                 API Gateway
                       ↓
                 Rate Limiter
                 /          \
                ↓            ↓
        Rule/Config Store   Redis
                ↓            ↓
            limits       counters/tokens
                 \          /
                  \        /
                 Allow/Deny
                     ↓
             Application Servers
                     ↓
                  Database
```
* The flow is:
```
1. Client sends request.
2. Identify client.
3. Determine applicable rate-limit rule.
4. Look up rate-limit state in Redis.
5. Atomically update/check state.
6. Below limit → allow.
7. Above limit → HTTP 429.
8. Allowed request → application server.
```

**18. Availability — What if Redis Goes Down?**

* This is an excellent interview follow-up.
* There are two possible philosophies.

**Fail open.**
```
Rate limiter unavailable
↓
Allow request
```

**Advantages:**
* Main application remains available.

**Disadvantage:**
* Temporary loss of rate-limit protection.

**Useful when:**
```
Availability > strict rate limiting
```

**Fail closed.**
```
Rate limiter unavailable
↓
Reject request
```

**Advantages:**
* Protects downstream systems.

**Disadvantage:**
* Rate limiter failure effectively causes an application outage.

**Useful for:**
* security-sensitive endpoints.
* expensive operations.
* strict quotas.

* This is a business/product decision, not simply a technical one.

**19. Local vs Global Rate Limiting.**

* Another useful optimization is local rate limiting.
* Instead of every request going:
```
App → Redis → App
```
* we could maintain some state locally.
```
Server
↓
Local limiter/cache
↓
Redis
```
* This reduces Redis traffic and latency.
* But now consistency becomes harder.

**So there's a trade-off:**
```
Centralized Redis
→ more accurate
→ more network traffic

Local counters
→ faster
→ less accurate globally
```
* This is a common system-design pattern:
> Strong accuracy vs performance/scalability.

**20. Rate Limiter vs Load Balancer.**

* Don't confuse them.

**Load balancer.**
* Answers:
> Which server should receive this request?
```
Request
↓
Load Balancer
↙    ↓    ↘
S1    S2    S3
```

**Rate limiter.**
* Answers:
> Should this request be allowed at all?
```
Request
↓
Rate Limiter
↙        ↘
allow    reject
```
* They solve different problems.

**21. Important Numbers to Estimate.**

* System-design interviews may ask for rough capacity calculations.
* Suppose:
```
10 million users

Average:
10 requests/user/day
```
* Total:
```
100 million requests/day
```
* Average requests per second:
```
100,000,000
───────────
86,400

≈ 1,157 requests/sec
```
* But traffic isn't uniform.
* Peak could be:
```
5–10× average
```
* So perhaps:
```
~10,000 requests/sec
```
* The exact number isn't the point.
* You're showing that architecture decisions should follow from scale estimates.

**22. Interview Trade-Offs to Mention*.*

* These are especially worth remembering.

**Accuracy vs performance.**
```
Sliding window
→ accurate
→ expensive

Fixed window
→ cheap
→ less accurate
```

**Availability vs strict enforcement.**
```
Redis failure

fail open
→ available but unprotected

fail closed
→ protected but unavailable
```

**Centralized vs local state.**
```
Central Redis
→ consistent
→ network overhead

Local limiter
→ fast
→ inconsistent
```

**Burst tolerance.**
```
Token bucket
→ bursts allowed

Leaky bucket
→ traffic smoothed
```

**23. Interview Follow-Up Questions**

* Be prepared for questions like:
* Why do we need rate limiting?
* Would you rate-limit by user ID, API key, or IP address?
* Where would you place the rate limiter?
* Why use Redis?
* What happens if Redis fails?
* How do multiple servers share counters?
* How do you prevent race conditions?
* How would you shard Redis?
* Which algorithm would you choose?
* Why token bucket instead of fixed window?
* How do you support different limits for different APIs?
* How would you handle anonymous users?
* How would you handle millions of users?
* What HTTP status code should rejected requests return?
  * 429 Too Many Requests.
* Should the system fail open or fail closed?
* How accurate does the limiter need to be?

**24. ⭐ The Interview Version to Memorize.**

* If an interviewer says:
> “Design a rate limiter.”
* A strong thought process is:
```
1. Clarify requirements
   ↓
2. Define rate-limit rules
   e.g. 100 req/min/user
   ↓
3. Identify client
   user ID / API key / IP
   ↓
4. Choose algorithm
   Token Bucket
   ↓
5. Put limiter before application
   ↓
6. Store distributed state in Redis
   ↓
7. Use atomic operations
   ↓
8. Reject excess traffic with HTTP 429
   ↓
9. Scale Redis using sharding/replication
   ↓
10. Discuss failures + trade-offs
```
* The core architecture to keep in your head is:
```
Client
    ↓
Load Balancer / API Gateway
    ↓
Rate Limiter ─────→ Rules
    ↓
Redis
    ↓
Allowed?
↙     ↘
YES     NO
↓       ↓
App     429
```
* And the key concepts worth being able to explain without notes are:
  * Rate Limiter → Token Bucket → Redis → Atomic Operations → Distributed State → Sharding → HTTP 429 → Fail Open/Closed 
  → Accuracy vs Performance.
* That gives you the backbone of a strong rate-limiter system-design answer.

## Design TinyURL — System Design Notes

**1. What Are We Designing?**

* TinyURL is a URL shortening service.
* We want to convert a potentially very long URL:
```
https://www.example.com/products/electronics/phones?id=12345
```
* into something short:
```
tinyurl.com/aB3x9K
```
* When someone visits the short URL:
```
tinyurl.com/aB3x9K
```
* the system redirects them to:
```
https://www.example.com/products/electronics/phones?id=12345
```
* So fundamentally we're storing a mapping:
```
short_code → original_url

aB3x9K → https://www.example.com/...
```
* The core system has two operations:
```
CREATE short URL

long URL
↓
TinyURL
↓
short URL


REDIRECT

short URL
↓
TinyURL
↓
original URL
```

**2. Requirements.**

* As always in a system-design interview, start by clarifying requirements.

**Functional requirements.**
* The service should:
  * Accept a long URL.
  * Generate a unique short URL.
  * Store the mapping.
  * Redirect users from the short URL to the original URL.
  * Optionally support:
    * Custom aliases.
    * Expiration dates.
    * Analytics/click counts.
* For the basic interview problem, focus on:
```
Create short URL
Redirect short URL
```

**Non-functional requirements.**
* We want:
* Low latency: Redirects should be extremely fast.
* High availability: Short links should almost always work.
* Scalability: Potentially billions of URLs.
* Durability: URL mappings cannot randomly disappear.
* Uniqueness: Two different URLs shouldn't accidentally overwrite each other.

**3. API Design.**

* We basically need two endpoints.

**Create a short URL.**
* Conceptually:
```
POST /urls
```
* Request:
```json
{
  "url": "https://example.com/very/long/url"
}
```
* Response:
```json
{
  "short_url": "https://tinyurl.com/aB3x9K"
}
```

**Redirect.**
```
GET /aB3x9K
```
* Server looks up:
```
aB3x9K
↓
https://example.com/very/long/url
```
* and responds with an HTTP redirect.
* So:
```
GET /aB3x9K
    ↓
Lookup short code
    ↓
Find original URL
    ↓
HTTP Redirect
    ↓
example.com/very/long/url
```

**4. HTTP Redirects — 301 vs 302.**

* The service does not normally download the destination webpage itself.
* Instead, it tells the browser:
> Go to this other URL.
* Conceptually:
```
Browser
    │
    │ GET tinyurl.com/aB3x9K
    ↓
TinyURL
    │
    │ Redirect → example.com
    ↓
Browser
    │
    │ GET example.com
    ↓
Destination Server
```
* Two HTTP codes are worth knowing.

**301 — Permanent Redirect.**
* The browser/CDN may cache the redirect.
* Advantage:
  * Less traffic reaches TinyURL.
* Disadvantage:
  * Harder to track every click.
  * Changing the destination later can be problematic because redirects may be cached.

**302 — Temporary Redirect.**
* Browser is more likely to return to TinyURL each time.
* Advantage:
  * Better analytics.
  * Greater control over redirects.
* Disadvantage:
  * More traffic reaches our servers.

* This gives us a classic trade-off:
```
301
→ more caching
→ less server traffic

302
→ more requests to TinyURL
→ better analytics/control
```

**5. Database Model.**

* The core data model is extremely simple.
* We need something like:

| short_code  | 	original_url            |
|-------------|--------------------------|
| aB3x9K      | 	https://example.com/... |
| X91kdA      | 	https://google.com/...  |
| 8zK2Lm      | 	https://panda.io/...    |

* Conceptually:
```
short_code → long_url
```
* For example:
```
{
  short_code: "aB3x9K",
  original_url: "https://example.com/...",
  created_at: "...",
  expires_at: "..."
}
```

* The important lookup is:
```
WHERE short_code = ?
```
* Therefore:
```
short_code should be indexed / used as the lookup key.
```

**6. Read-Heavy System.**

* This is an important observation.
* Usually:
```
CREATE operations < REDIRECT operations
```
* One person might create a link once:
```
Create:
1 request
```
* But that link could be opened:
```
100
10,000
1,000,000+
```
* times.
* Therefore TinyURL is generally a read-heavy system.
* That immediately suggests:
```
Caching will be very useful.
```
* We'll come back to that.

**7. The Central Problem: Generating Short Codes.**

* This is probably the most interesting part of the design.
* We need to transform something into:
```
aB3x9K
```
* The short code must be:
  * Short.
  * Unique.
  * Easy to generate.
  * Scalable.
* A common solution is:
```
Base62 encoding
```

**8. Base62.**
* Base62 uses:
```
0-9
a-z
A-Z
```
* That's:
```
10 digits
+
26 lowercase letters
+
26 uppercase letters
=
62 characters
```
* So every position has:
```
62 possibilities
```
* If the short code has 6 characters:
```
XXXXXX
```
* we have:
```
62^6
```
* possible values.
* That's approximately:
```
56.8 billion
```
* possible short codes.
* With 7 characters:
```
62^7 ≈ 3.5 trillion
```
* So even a very short code gives us an enormous key space.

**9. Why Base62 Instead of Base10?**

* Suppose we assign:
```
ID = 125839203
```
* We could expose:
```
tinyurl.com/125839203
```
* But that's relatively long.
* Instead convert the number into Base62:
```
125839203
↓
Base62
↓
8xK2P
```
* Now:
```
tinyurl.com/8xK2P
```
* is much shorter.
* Same idea as representing numbers in different bases:
```
Binary       → Base 2
Decimal      → Base 10
Hexadecimal  → Base 16
Base62       → Base 62
```

**10. Approach #1 — Auto-Incrementing ID + Base62.**

* One straightforward design:
```
Database generates ID
  ↓
123456789
  ↓
Base62 encode
  ↓
8M0kX
```
* Store:
```
8M0kX → original URL
```
* Advantages:
  * Guaranteed unique IDs.
  * No collisions.
  * Simple.
* But there's a scaling problem.
* If one central database generates:
```
1
2
3
4
5
...
```
* every URL creation depends on that database.
* At huge scale:
```
Many servers
  ↓
Central ID generator
```
* can become a bottleneck.

**11. Approach #2 — Random Short Codes.**

* Another option:
* Generate:
```
random Base62 string
```
* For example:
```
aZ8xP1
```
* Then check:
```
Does aZ8xP1 already exist?
```
* If not:
```
store it
```
* If yes:
```
generate another
```
* Flow:
```
Generate random code
    ↓
Check database
    ↓
Exists?
↙          ↘
YES          NO
↓            ↓
retry        store
```

**Advantage.**
* Easy to generate independently on many servers.
* No central sequence required.

**Disadvantage.**
* Collisions are possible.

**12. Collision Problem.**

* Suppose:
```
URL A
↓
random()
↓
abc123
```
* But:
```
abc123
```
* already belongs to another URL.
* We cannot overwrite it.
* Therefore:
```
generate code
    ↓
try database insert
    ↓
collision?
↙        ↘
yes       no
↓         ↓
retry    success
```
* A good database design can enforce:
```
UNIQUE(short_code)
```
* So the database itself prevents accidental duplicates.
* With a sufficiently large Base62 space, collisions remain manageable as long as utilization isn't too high.

**13. Hashing the Original URL.**

* Another possible idea:
```
long URL
↓
hash()
↓
hash value
↓
take first N characters
```
* For example:
```
https://example.com/...
      ↓
    SHA-256
      ↓
    7ad89023....
      ↓
    7ad890
```
* But there is still a problem:
```
Different inputs can produce the same shortened prefix.
```
* So:
```
URL A → abc123
URL B → abc123
```
* is possible.
* Therefore hashing does not magically eliminate collisions.
* We still need collision handling.

**14. Which Approach Is Best?**

* Typical choices:
```
Sequential ID + Base62
```
* or:
```
Random Base62 key
```
* Trade-off:

| Approach      | 	Advantage              | 	Disadvantage                 |
|---------------|-------------------------|-------------------------------|
| Sequential ID | 	No collisions          | 	ID generation can bottleneck |
| Random Base62 | 	Distributed generation | 	Collisions possible          |
| Hash URL      | 	Deterministic          | 	Collisions still possible    |

* For an interview, choosing random Base62 IDs with collision detection is perfectly reasonable.
* Another good design is a dedicated distributed ID generator followed by Base62 encoding.
* The important part is explaining the trade-off.

**15. Basic Architecture.**

* Our first architecture might be:
```
                    Clients
                       ↓
                Load Balancer
                       ↓
             Application Servers
                 ↙          ↘
                ↓            ↓
        URL Creation      Redirect
                \            /
                 \          /
                   Database
```
* Creation:
```
POST long URL
↓
Application Server
↓
Generate short code
↓
Store mapping
↓
Return short URL
```
* Redirect:
```
GET /abc123
↓
Application Server
↓
Database lookup
↓
original URL
↓
302 Redirect
```

**16. Database Choice — SQL or NoSQL?**

* What does our data look like?
* Basically:
```
key → value
abc123 → https://...

Our main operation is:
get(short_code)
```
* We don't need many:
```
joins
relationships
complex queries
```
* So a distributed key-value / NoSQL database is a natural fit.
* Conceptually:
```
Key:
abc123

Value:
{
  url: "https://...",
  created_at: ...
}
```
* Possible technologies in a real system could include distributed key-value databases.
* But don't get obsessed with naming products in the interview.
* The important reasoning is:
  * Our access pattern is primarily key-value lookup by short code.

**17. Scaling the Database.**

* Suppose we have billions of URLs.
* One database server isn't enough.
* We can shard by the short code.
* For example:
```
hash(short_code)
    ↓
choose shard
```
* Architecture:
```
               Application
                    ↓
             hash(short_code)
             /      |      \
            ↓       ↓       ↓
         DB #1    DB #2    DB #3
```
* For:
```
abc123
```
* we compute:
```
hash("abc123") % number_of_shards
```
* and determine where the URL lives.
* This distributes:
  * Storage.
  * Reads.
  * Writes.
* across multiple machines.

**18. Replication.**

* Sharding handles:
> How do we distribute the data?
* Replication handles:
> What happens if a database machine dies?
* For example:
```
        Shard 1
       /       \
      ↓         ↓
Primary     Replica
```
* If the primary fails, a replica can potentially take over.
* Replication also helps with read scalability:
```
            Primary
          ↙    ↓    ↘
      Replica Replica Replica
```
* Since TinyURL is read-heavy, replicas can be especially useful.

**19. Caching.**

* Remember:
> Reads >> Writes
* Popular URLs may receive enormous traffic.
* Suppose:
```
tinyurl.com/abc123
```
* goes viral.
* Without cache:
```
Request
↓
Application
↓
Database
```
* every single time.
* That's wasteful.
* Instead:
```
Request
  ↓
Application
  ↓
Cache
  ↙    ↘
hit   miss
  ↓      ↓
URL   Database
  ↓
Cache

```
* So the redirect path becomes:
```
  GET /abc123
    ↓
  Cache lookup
    ↓
  Found?
  ↙         ↘
YES        NO
  ↓          ↓
redirect   Database
  ↓
save cache
  ↓
redirect
```

**20. What Should Be Cached?**

* We cache:
```
short_code → original_url
```
* Example:
```
abc123 → https://example.com/...
```
* Popular URLs stay in cache.
* This dramatically reduces database load.
* A distributed cache such as Redis is a natural choice.

**21. Cache Eviction.**

* Cache memory is limited.
* We cannot necessarily store billions of URLs in RAM.
* So we may use an eviction policy such as:
> LRU — Least Recently Used
* Meaning:
```
popular URLs
→ remain cached

rarely accessed URLs
→ eventually removed
```
* This works particularly well because TinyURL traffic is likely uneven:
```
Some URLs → millions of clicks

Most URLs → very few clicks
```

**22. CDN Possibility.**

* We can potentially push redirects even closer to users.
```
User
↓
CDN / Edge
↓
TinyURL backend
```
* For extremely popular URLs, redirects could potentially be cached at the edge.
* Benefits:
  * Lower latency.
  * Less backend traffic.
  * Geographic distribution.
* But again remember the earlier trade-off:
```
Heavy redirect caching
  ↓
Less traffic reaches backend
  ↓
Harder to collect exact click analytics
```

**23. Handling Expiration.**

* Maybe links should expire.
* Database record:
```
{
  short_code: "abc123",
  original_url: "...",
  created_at: ...,
  expires_at: ...
}
```
* On redirect:
```
GET /abc123
  ↓
Find record
  ↓
Expired?
↙       ↘
YES      NO
↓        ↓
404     redirect
```
* Expired records can later be removed asynchronously.
* We don't necessarily need to synchronously delete them the exact second they expire.

**24. Duplicate Long URLs.**

* Suppose:
```
Alice:
https://google.com
```
* creates:
```
tinyurl.com/abc123
```
* Later Bob submits:
```
https://google.com
```
* Should Bob receive:
```
abc123
```
* or a new code:
```
xY91Kz
```
* Both designs are possible.

**Deduplicate.**
```
same long URL
→ same short URL
```
* Requires something like:
```
long_url → short_code
```
* lookup.
* Advantage:
  * Saves storage.
* Disadvantage:
  * Additional index/storage/complexity.

**Don't deduplicate.**

* Every create request generates a new short URL.
* Advantage:
  * Simpler.
  * Users can have independent links/analytics/expiration.
* This is another good requirement to clarify with the interviewer.

**25. Custom Short URLs.**

* Maybe users want:
```
tinyurl.com/panda
```
* instead of:
```
tinyurl.com/x7K2Pa
```
* Then creation becomes:
```
POST /urls

{
  "url": "https://panda.io",
  "alias": "panda"
}
```
* We need to check:
```
Does "panda" already exist?
```
* If yes:
```
reject
```
* If no:
```
store
```
* Again, database uniqueness constraints are useful.

**26. Hot Keys.**

* Suppose a celebrity posts:
```
tinyurl.com/abc123
```
* and millions of users click it.
* Now:
```
abc123
```
* becomes a hot key.
* A single database partition could become overloaded.
* Caching helps enormously:
```
                 Requests
              ↙    ↓    ↘
          Cache Cache Cache
                ↓
              Database
```
* Most traffic should ideally be served before reaching persistent storage.
* This is one reason caching is so important for URL shorteners.

**27. Analytics.**

* If requirements include analytics, we might track:
```
number of clicks
country
device
timestamp
referrer
```
* A bad design would be:
```
redirect request
  ↓
update database click counter
  ↓
redirect user
```
* because analytics now increases redirect latency.
* Instead:
```
Redirect Request
      ↓
Get destination
      ↓
Publish click event ─────→ Message Queue
      ↓                       ↓
Redirect user             Analytics workers
                              ↓
                          Analytics DB
```
* The key idea:
> Analytics doesn't need to block the redirect.
* This is asynchronous processing.

**28. Why a Message Queue?**

* Suppose we get:
```
100,000 redirects/sec
```
* We don't want every redirect waiting for analytics writes.
* Instead:
```
Redirect Service
    ↓
Message Queue
    ↓
Analytics Consumers
    ↓
Analytics Database
```
* Benefits:
  * Redirect remains fast.
  * Traffic spikes can be buffered.
  * Analytics processing can scale independently.
* This is a useful follow-up if the interviewer adds analytics.

**29. Availability.**

* What happens if one application server dies?
* Use multiple servers:
```
              Load Balancer
             /      |      \
            ↓       ↓       ↓
         App 1    App 2    App 3
```
* Application servers should ideally be stateless.
* They don't permanently store:
```
abc123 → URL
```
* locally.
* That mapping belongs in:
```
Cache / Database
```
* Therefore any server can process any request.
* This makes horizontal scaling much easier.

**30. Full High-Level Architecture.**

* A more complete design might look like:
```
                         Clients
                            ↓
                     Load Balancer
                            ↓
                  Application Servers
                    ↙             ↘
                   ↓               ↓
              Create URL        Redirect
                   ↓               ↓
             ID Generator        Cache
                   ↓             ↙    ↘
              Base62 Code      HIT    MISS
                   ↓            ↓       ↓
                Database      Redirect Database
                   ↓                    ↓
            Sharded/Replicated        Cache
                                        ↓
                                     Redirect
```
* Optional analytics:
```
                     Redirect Service
                           ↓
                    Message Queue
                           ↓
                  Analytics Workers
                           ↓
                    Analytics DB
```

**31. Capacity Estimation.**

* Let's say the interviewer gives us:
```
100 million new URLs/month
```
* That's roughly:
```
100,000,000
───────────
30 × 86,400
≈ 39 writes/sec average
```
* Surprisingly small.
* But redirects might be:
```
100 × more frequent
```
* giving roughly:
```
~3,900 reads/sec average
```
* And peak traffic might be several times higher.
* The exact numbers aren't as important as recognizing:
> URL shortening is usually read-heavy.

**32. Storage Estimation.**

* Suppose we store:
```
short code     ~ 10 bytes
long URL       ~ 500 bytes average
metadata       ~ 100 bytes
```
* Very roughly:
```
~600 bytes / URL
```
* For:
```
1 billion URLs
```
* that's approximately:
```
600 GB
```
* before considering:
  * Indexes.
  * Replication.
  * Database overhead.
  * Backups.
* Again, system-design estimation is about order of magnitude, not exact numbers.

**33. Base62 Capacity — Worth Remembering.**

* This is one of the few pieces of math worth remembering.
* For an `n` character code:
```
possible URLs = 62^n
```
* Approximately:
```
6 characters → 56.8 billion

7 characters → 3.5 trillion

8 characters → 218 trillion
```
* Therefore even relatively short identifiers give us a huge namespace.

**34. Security / Abuse.**

* A real URL shortener has additional problems.
* Attackers could create URLs pointing to:
  * Phishing sites.
  * Malware.
  * Spam.
* We may therefore need:
```
URL submitted
    ↓
Validation / Abuse Detection
    ↓
Generate short URL

```
* We may also apply the rate limiter from the previous system-design problem:
```
Client
↓
Rate Limiter
↓
Create TinyURL

```
* For example:
```
100 URL creations/hour/user
```
* This is a good example of system-design components composing together.

**35. Sequential IDs Can Leak Information.**

* Suppose URLs are:
```
tinyurl.com/10001
tinyurl.com/10002
tinyurl.com/10003
```
* Someone can infer:
```
10003 - 10001 = 2
```
* and possibly estimate how many URLs are being created.
* They can also enumerate:
```
10001
10002
10003
10004
...
```
* Random identifiers make enumeration harder:
```
a8Zk21
X91LmQ
2pa8Kz
```
* So random IDs can provide a useful practical advantage.

**36. SQL vs NoSQL Trade-Off.**

**SQL.**
* Advantages:
  * Strong constraints.
  * Transactions.
  * Easy uniqueness enforcement.
* Could work perfectly well at moderate scale.

**NoSQL / Key-Value.**
* Advantages:
  * Natural access pattern.
  * Easy horizontal scaling.
  * Very fast key lookup.
* Our dominant operation is:
```
short_code → URL
```
* so a key-value model maps nicely to the problem.
* Don't say:
```
"TinyURL must use NoSQL."
```
* Say:
```
"Because our dominant access pattern is a simple lookup by short code and we expect large horizontal scale, 
a distributed key-value store is a natural choice."
```
* That's much stronger interview reasoning.

**37. Important Trade-Offs.**

* Sequential vs Random IDs.
```
Sequential
→ guaranteed unique
→ central coordination may be needed

Random
→ distributed generation
→ collisions need handling
```

**301 vs 302.**
```
301
→ more caching
→ lower server load

302
→ better analytics/control
→ higher server load
```

**SQL vs NoSQL.**
```
SQL
→ stronger relational features

NoSQL / KV
→ simple lookup + horizontal scaling
```

**Cache vs Database.**
```
Cache
→ fast
→ limited memory / not primary durability

Database
→ persistent
→ slower
```

**Synchronous vs Asynchronous Analytics.**
```
Synchronous
→ immediate
→ slows redirects

Async / Queue
→ fast redirects
→ analytics slightly delayed
```

**38. Interview Follow-Up Questions**

**Be prepared to answer:**
* How do you generate a unique short URL?
* Why Base62?
* How many URLs can a 6-character Base62 code represent?
* What happens if two generated IDs collide?
* Why not just hash the original URL?
* Would you use SQL or NoSQL?
* What should the database key be?
* How would you shard the database?
* Why is caching particularly effective here?
* What happens when a URL becomes extremely popular?
* Would you use a 301 or 302 redirect?
* How do URLs expire?
* Should the same long URL always produce the same short URL?
* How would custom aliases work?
* How would you implement analytics?
* Why should analytics be asynchronous?
* How would you prevent malicious links?
* What happens if the database fails?
* How do you scale to billions of URLs?

**39. ⭐ Interview Version to Memorize.**

* If the interviewer says:
```
"Design TinyURL."
```

* Think through the problem in roughly this order:
```
1. Clarify requirements
   ↓
2. Define APIs
   POST /urls
   GET /{short_code}
   ↓
3. Estimate scale
   Reads >> Writes
   ↓
4. Design data model
   short_code → long_url
   ↓
5. Generate IDs
   Random / distributed ID
   ↓
6. Base62 encode
   ↓
7. Store mapping
   ↓
8. Redirect with 301/302
   ↓
9. Add cache
   ↓
10. Shard + replicate DB
    ↓
11. Handle collisions
    ↓
12. Discuss failures/trade-offs
    ↓
13. Optional:
    analytics / expiration / custom URLs
```

**40. ⭐ Architecture to Keep in Your Head.**

* Try to be able to draw this from memory:
```
                     CLIENT
                        ↓
                 LOAD BALANCER
                        ↓
                APPLICATION SERVERS
                  ↙             ↘
                 ↓               ↓
             CREATE            REDIRECT
                 ↓               ↓
          ID GENERATOR         CACHE
                 ↓            ↙     ↘
              BASE62         HIT    MISS
                 ↓            ↓       ↓
              DATABASE     REDIRECT DATABASE
                                      ↓
                                    CACHE
                                      ↓
                                   REDIRECT
```
* With analytics:
```
Redirect
│
├────────────→ User gets redirected
│
└────────────→ Message Queue
                    ↓
                Analytics Worker
                    ↓
                Analytics DB
```

**41. ⭐ The 10 Things I'd Memorize.**

**If you're reviewing shortly before an interview, concentrate on these:**
* TinyURL is primarily a short_code → long_url mapping.
* Reads greatly outnumber writes.
* Use Base62 to produce compact identifiers.
* 62^6 ≈ 57 billion possible 6-character codes.
* IDs can be sequential/distributed or random.
* Random generation requires collision handling.
* Use a persistent database as the source of truth.
* Cache popular mappings because redirects are read-heavy.
* Shard by short-code hash when the dataset becomes huge.
* Keep analytics asynchronous using a queue so redirects stay fast.

**And know this flow cold:**
```
GET tinyurl.com/abc123
  ↓
Load Balancer
  ↓
Application
  ↓
Cache
↙       ↘
HIT       MISS
↓          ↓
URL       Database
↓          ↓
302 ←──── Cache result
↓
Original Website
```
* That's the mental model I'd use for this interview question.

***

## Design Twitter — System Design Interview Notes.

## 1. Bullet-Point Overview

- **Core product features**
  - Users can create tweets/posts.
  - Users can follow and unfollow other users.
  - Users can view a personalized home timeline/feed.
  - Users can view a user's profile timeline.
  - The home feed should show recent posts from accounts a user follows.

- **Key non-functional requirements**
  - Very low latency for reading the home feed.
  - High availability: users should almost always be able to read/post.
  - Massive horizontal scalability.
  - Eventual consistency is usually acceptable for feeds, follower counts, and similar derived data.
  - Durable storage for tweets and follow relationships.

- **Typical system-design observation**
  - Twitter-like systems are extremely **read heavy**.
  - A tweet may be written once but read by thousands or millions of followers.
  - The difficult part is therefore not storing a tweet; it is distributing and retrieving feeds efficiently.

- **Main APIs**
  - `POST /tweets` — create a tweet.
  - `GET /feed` — retrieve the current user's home feed.
  - `POST /follow/{user_id}` — follow another user.
  - `DELETE /follow/{user_id}` — unfollow another user.
  - `GET /users/{id}/tweets` — retrieve a user's own tweets.

- **Core data models**
  - `User(user_id, profile_data, ...)`
  - `Tweet(tweet_id, author_id, text/media, created_at, ...)`
  - `Follow(follower_id, followee_id)`
  - Optionally, a precomputed `FeedEntry(user_id, tweet_id, rank/timestamp)`.

- **Basic architecture**
  - Client → Load Balancer/API Gateway → stateless application services.
  - Tweet service persists tweets.
  - Social graph/follow service stores follower relationships.
  - Feed service builds or retrieves timelines.
  - Cache stores hot tweets, profiles, and feed pages.
  - Queue/event stream distributes new-tweet events asynchronously.

- **Main design decision: how to build the home timeline**
  - **Fan-out on read**: build the feed when the user requests it.
  - **Fan-out on write**: push a new tweet into followers' feed caches when it is created.
  - Large systems often use a **hybrid** of the two.

- **Fan-out on read / pull model**
  - User requests home feed.
  - Fetch accounts the user follows.
  - Fetch recent tweets for those accounts.
  - Merge and sort them.
  - Return the newest/top results.
  - Pros: cheap writes, simple handling of celebrities.
  - Cons: expensive reads and potentially high latency.

- **Fan-out on write / push model**
  - User creates a tweet.
  - Store the tweet once in the tweet database.
  - Publish a tweet-created event.
  - Workers find the author's followers.
  - Insert the tweet ID into each follower's precomputed feed/timeline cache.
  - Pros: feed reads become extremely fast.
  - Cons: a single celebrity tweet may require millions of feed updates.

- **Celebrity / hot-user problem**
  - A celebrity may have tens of millions of followers.
  - Pushing one tweet to every follower is extremely expensive.
  - Common solution: do not fully fan out celebrity tweets on write.
  - Instead, pull celebrity tweets at read time and merge them with the user's precomputed feed.

- **Hybrid feed strategy**
  - Normal accounts → fan-out on write.
  - Extremely high-follower accounts → fan-out on read.
  - At read time, merge:
    - precomputed ordinary-user feed entries
    - recent tweets from celebrity accounts
  - This balances read latency and write amplification.

- **Feed ordering**
  - Simplest version: reverse chronological order.
  - Production systems may rank by relevance rather than time only.
  - Ranking may use engagement, relationship strength, recency, and ML signals.
  - For an interview, chronological ordering is usually a good starting assumption unless ranking is required.

- **Caching**
  - Cache hot tweets and user profiles.
  - Cache the first page of each user's home feed.
  - Store tweet IDs in feed caches rather than duplicating full tweet bodies.
  - Hydrate IDs into full tweet objects when responding.
  - This reduces duplication and makes updates/deletions easier.

- **Databases and partitioning**
  - Tweet storage can be sharded by `tweet_id` or `author_id`, depending on access patterns.
  - Social graph storage is naturally keyed by user ID.
  - Feed storage/cache is naturally partitioned by recipient `user_id`.
  - Avoid one global database bottleneck.

- **Tweet ID generation**
  - IDs must be globally unique across many servers.
  - A distributed ID generator can embed timestamp + machine/worker information + sequence number.
  - Time-sortable IDs are useful because feeds frequently need newest-first ordering.

- **Asynchronous processing**
  - A successful tweet creation should not synchronously wait for millions of follower-feed updates.
  - Store the tweet, publish an event, acknowledge the request, then let workers fan it out.
  - Queue/stream absorbs spikes and decouples tweet creation from feed distribution.

- **Consistency trade-offs**
  - Tweet creation itself should be durable.
  - A tweet appearing in every follower's feed immediately is less critical.
  - Eventual consistency is usually acceptable for feed propagation.
  - Deletion may likewise propagate asynchronously to caches/feed materializations.

- **High availability**
  - Application servers should be stateless and horizontally scalable.
  - Databases need replication and failover.
  - Queue/stream should be replicated/durable.
  - Cache misses should fall back to persistent storage when possible.

- **Common interview bottlenecks**
  - Celebrity fan-out.
  - Hot tweets/hot partitions.
  - Expensive feed merges.
  - Huge follower/following lists.
  - Cache invalidation.
  - Queue backlog after traffic spikes.
  - Global ID generation.

- **Core trade-off to remember**
  - Fan-out on write → expensive writes, cheap reads.
  - Fan-out on read → cheap writes, expensive reads.
  - Twitter-scale architecture → typically hybrid.

---

# 2. Detailed Study Notes

## Problem Definition

We are designing a simplified Twitter-like system. The essential workflows are:

```text
POST A TWEET
User → API → Tweet Service → Persistent Storage
                         ↓
                    Event/Queue
                         ↓
                    Feed Workers

READ HOME FEED
User → API → Feed Service → Feed Cache/Store → Tweet Cache/DB → Response
```

The crucial system-design challenge is the **home timeline**. Saving one tweet is straightforward. Serving a personalized feed to hundreds of millions of users while keeping latency low is much harder.

---

## Functional Requirements

Start the interview by narrowing scope. A reasonable first version supports:

- Create a tweet.
- Follow another user.
- Unfollow another user.
- Retrieve the user's home timeline.
- Retrieve a user's own tweets.

Optional features should normally be postponed unless requested:

- Likes and retweets.
- Comments/replies.
- Search.
- Trending topics.
- Notifications.
- DMs.
- Ads.
- Full recommendation/ranking system.

This keeps the interview focused on the hardest architectural problem: **timeline generation**.

---

## Non-Functional Requirements

### Low read latency

Users refresh feeds frequently, so home-timeline reads should normally complete very quickly.

### High availability

The read path is core product functionality. It is usually preferable to return a slightly stale feed rather than no feed at all.

### Durability

Once a tweet is successfully created, it should not disappear due to a single server failure.

### Scalability

The architecture must scale horizontally because both the number of users and the number of timeline reads can become enormous.

### Eventual consistency

The system does not normally need every follower to see a new tweet at exactly the same millisecond. A small propagation delay is acceptable, allowing much more scalable asynchronous processing.

---

## API Sketch

### Create tweet

```http
POST /tweets
```

Request:

```json
{
  "text": "Hello world"
}
```

Response:

```json
{
  "tweet_id": "...",
  "created_at": "..."
}
```

### Get home feed

```http
GET /feed?cursor=...
```

Use **cursor-based pagination** rather than offsets for large, rapidly changing feeds.

### Follow

```http
POST /users/{user_id}/follow
```

### Unfollow

```http
DELETE /users/{user_id}/follow
```

---

## Data Model

A simplified relational-style view is:

```text
User
----
user_id
profile_data

Tweet
-----
tweet_id
author_id
content
created_at

Follow
------
follower_id
followee_id

FeedEntry  (optional precomputed timeline)
---------
user_id
tweet_id
created_at / ranking_score
```

The actual physical storage may be NoSQL or distributed key-value storage. The logical entities remain useful regardless of technology.

---

# 3. Why Feed Generation Is Hard

Assume Alice follows 500 people.

A naive request-time implementation is:

```text
GET Alice's feed
       ↓
Fetch Alice's 500 followees
       ↓
Fetch recent tweets from each
       ↓
Merge all tweet streams
       ↓
Sort newest → oldest
       ↓
Return top 20
```

This is **fan-out on read**.

The problem is that this work repeats every time Alice refreshes her feed. With millions of users refreshing frequently, the read cost becomes enormous.

This motivates precomputing timelines.

---

# 4. Fan-Out on Read

## Flow

```text
User requests feed
       ↓
Get following list
       ↓
Fetch recent tweets for each followed account
       ↓
K-way merge / rank
       ↓
Return first page
```

## Advantages

- Creating a tweet is cheap.
- No need to update millions of follower feeds.
- Works naturally for celebrity accounts.
- Less duplicated feed state.

## Disadvantages

- Home-feed requests are expensive.
- Requires many reads and a merge operation.
- Latency worsens for users following many accounts.
- Heavy read traffic can overload tweet storage.

The fundamental trade-off:

```text
CHEAP WRITE
   ↓
EXPENSIVE READ
```

---

# 5. Fan-Out on Write

Instead of calculating the timeline when someone asks for it, calculate much of it when a tweet is created.

## Flow

```text
Alice posts tweet T1
        ↓
Persist T1
        ↓
Publish "TweetCreated(T1, Alice)"
        ↓
Feed workers
        ↓
Fetch Alice's followers
        ↓
Add T1 to each follower's feed
```

Then Bob's read looks like:

```text
Bob requests feed
       ↓
Lookup Bob's precomputed timeline
       ↓
Return tweet IDs
       ↓
Hydrate tweets
       ↓
Response
```

## Advantages

- Very cheap and predictable home-feed reads.
- Excellent user-facing read latency.
- Works especially well when ordinary users have relatively small follower counts.

## Disadvantages

- Write amplification.
- Feed materialization consumes substantial storage/cache space.
- Large follower counts make publishing expensive.
- New-tweet events can create huge asynchronous workloads.

Trade-off:

```text
EXPENSIVE WRITE
   ↓
CHEAP READ
```

---

# 6. The Celebrity Problem

Suppose an account has 50 million followers.

A pure fan-out-on-write design creates roughly:

```text
1 tweet
   ↓
50,000,000 timeline insertions
```

That is wasteful and can create large queue backlogs.

This is why a production-scale system should not treat all authors equally.

---

# 7. Hybrid Fan-Out Strategy

A strong interview answer is:

```text
Normal author
     ↓
Fan-out on write
     ↓
Followers' feeds precomputed

Celebrity author
     ↓
Do NOT push to every follower
     ↓
Store recent celebrity tweets
     ↓
Merge at read time
```

When Bob opens the feed:

```text
                  Bob
                   ↓
             Feed Service
              ↙        ↘
             ↓          ↓
Precomputed feed    Celebrity tweets
             \          /
              \        /
                 Merge
                   ↓
               Rank/sort
                   ↓
                Response
```

This hybrid model gives us:

- fast reads for most content,
- manageable write amplification,
- better handling of extremely popular accounts.

This is one of the most important concepts to remember from a Twitter system-design interview.

---

# 8. Feed Cache Design

Instead of storing complete tweet objects repeatedly in every timeline, store references:

```text
user:123:feed
[
  tweet_981,
  tweet_975,
  tweet_962,
  ...
]
```

Then hydrate them:

```text
Tweet IDs
   ↓
Tweet Cache
 ↙        ↘
HIT       MISS
 ↓         ↓
Tweet    Tweet DB
           ↓
         Cache
```

Why store IDs rather than full tweets in every feed?

- Less duplicated data.
- Tweet updates/deletions are easier to propagate.
- Cache memory usage is lower.
- A shared tweet cache can serve many users.

Usually only a bounded number of feed entries needs to be precomputed, for example the newest few hundred or thousand IDs, rather than an infinite history.

---

# 9. Posting a Tweet — Detailed Write Path

```text
Client
  ↓
Load Balancer / API Gateway
  ↓
Tweet Service
  ↓
Generate globally unique Tweet ID
  ↓
Persist Tweet
  ↓
Publish TweetCreated event
  ↓
Return success to client
```

Separately:

```text
TweetCreated event
       ↓
Message Queue / Event Stream
       ↓
Fan-Out Workers
       ↓
Follower Graph
       ↓
Feed Cache / Feed Store
```

The user should not have to wait for the entire fan-out operation.

That gives a key design principle:

> Keep the synchronous request path small; push expensive secondary work into asynchronous workers.

---

# 10. Why Use a Queue / Event Stream?

Without a queue:

```text
POST tweet
   ↓
write tweet
   ↓
update 500,000 timelines
   ↓
return response
```

This would have terrible latency and poor reliability.

With a queue:

```text
POST tweet
   ↓
write tweet
   ↓
publish event
   ↓
return response
```

Workers can then process follower feeds independently.

Benefits:

- absorbs traffic spikes,
- decouples services,
- allows retries,
- lets fan-out workers scale independently,
- keeps tweet creation responsive.

---

# 11. Distributed Tweet IDs

One application server cannot safely generate a simple local counter if thousands of servers are creating tweets concurrently.

We need globally unique IDs.

A useful design is a time-sortable distributed ID:

```text
| timestamp | worker/machine ID | sequence |
```

Benefits:

- globally unique,
- generated without one central database counter,
- approximately sortable by creation time,
- helpful for pagination and chronological feeds.

The exact bit allocation is implementation-specific; the interview concept matters more than exact numbers.

---

# 12. Social Graph Storage

The follow relationship forms a directed graph:

```text
Alice → Bob
```

means Alice follows Bob.

Useful access patterns include:

```text
Who does Alice follow?
Alice → [Bob, Carol, Dan, ...]

Who follows Bob?
Bob → [Alice, Eve, Frank, ...]
```

The second access pattern is particularly important for fan-out on write because workers need the author's follower list.

At large scale, follower lists need partitioning and possibly caching.

---

# 13. Partitioning / Sharding

## Tweet storage

Possible choices:

### Shard by author ID

```text
hash(author_id) → shard
```

Advantages:

- retrieving an author's timeline is easy.

Potential problem:

- celebrity authors can create hot partitions.

### Shard by tweet ID

```text
hash(tweet_id) → shard
```

Advantages:

- more even distribution.

Potential problem:

- retrieving all tweets for one author may require an index or additional structure.

The best partition key follows the dominant access pattern and load distribution.

## Feed storage

A natural partition is:

```text
hash(user_id) → feed shard
```

because home-feed reads are user-specific.

---

# 14. Pagination

Do not use simple page offsets such as:

```text
?page=5000
```

for a continuously changing feed.

Prefer a cursor:

```text
GET /feed?before_tweet_id=123456
```

or a timestamp/ranking cursor.

Why?

- offsets become inconsistent when new posts are inserted,
- large offsets can be expensive,
- cursors naturally continue from the last item seen.

---

# 15. Ranking

A simple interview system can return:

```text
newest tweet first
```

This makes the design easier to explain.

A more realistic feed can use a ranking service:

```text
Candidate tweets
       ↓
Ranking service
       ↓
score(tweet, user)
       ↓
Sorted feed
```

Possible signals:

- recency,
- relationship strength,
- likes/retweets/replies,
- user interests,
- predicted engagement,
- content quality/safety signals.

Unless the interviewer asks for recommendation algorithms, treat ranking as a separate downstream component rather than designing the ML model in depth.

---

# 16. Read Path

A practical home-feed read might look like:

```text
Client
  ↓
API Gateway
  ↓
Feed Service
  ↓
Feed Cache
  ↓
Precomputed Tweet IDs
  ↓
Fetch recent tweets from celebrity follows
  ↓
Merge / rank
  ↓
Tweet Cache
  ↓
Tweet DB for cache misses
  ↓
Return feed
```

If the precomputed feed cache is missing, the system may rebuild from persistent feed storage or temporarily use fan-out-on-read logic.

---

# 17. Cache Strategy

Good cache candidates:

- first page of home feeds,
- hot tweet objects,
- user profiles,
- follower/following lists,
- celebrity recent tweets.

Cache is especially effective because user behavior is not uniform. A small fraction of tweets/users can receive a disproportionately large fraction of reads.

Potential eviction policy:

```text
LRU / LFU-style policy
```

Exact cache technology matters less than the architectural reasoning.

---

# 18. Hot Keys and Hot Partitions

A viral tweet can be requested millions of times.

Without caching:

```text
Millions of requests
       ↓
Same database key
```

This creates a hot key.

Mitigations:

- distributed caching,
- replication,
- CDN/edge caching for public/static media,
- request coalescing,
- partitioning related workloads.

Celebrity follower lists can similarly become hot data and should be cached/partitioned appropriately.

---

# 19. Media

Tweets may contain photos or videos.

Do not store large media blobs directly in the main tweet database if avoidable.

Typical design:

```text
Client uploads media
       ↓
Object Storage
       ↓
Returns media URL / object key
       ↓
Tweet stores metadata + media reference
       ↓
CDN serves media to readers
```

This keeps the tweet database focused on metadata while object storage/CDN handles large binary content.

---

# 20. Deletes

A delete is more complicated when feed entries have been copied to millions of timelines.

Possible approach:

```text
Delete request
     ↓
Mark/remove tweet in source of truth
     ↓
Publish TweetDeleted event
     ↓
Asynchronously remove/invalidate cached feed entries
```

Even if stale feed IDs remain temporarily, hydration can recognize that the underlying tweet no longer exists and omit it.

This is another advantage of storing **tweet IDs** in timelines instead of duplicating the full tweet body everywhere.

---

# 21. Follow / Unfollow Behavior

When Alice follows Bob, what should happen?

Option A:

- Only future Bob tweets enter Alice's feed.

Option B:

- Backfill some of Bob's recent tweets into Alice's feed.

A practical design often asynchronously backfills a small recent window.

When Alice unfollows Bob:

- future fan-out stops,
- existing cached entries may be lazily filtered or asynchronously removed.

Strict instantaneous cleanup is usually unnecessary.

---

# 22. Failure Handling

## Feed worker fails

Messages should remain/retry from the queue. Processing should ideally be idempotent so duplicate event delivery does not corrupt feeds.

## Cache fails

Fallback to persistent storage or rebuild the feed, accepting higher latency.

## Tweet database node fails

Use replication/failover.

## Fan-out is delayed

Serve an older feed temporarily. Eventual consistency is usually preferable to making the whole service unavailable.

---

# 23. Idempotency

Queues can deliver the same event more than once.

Suppose:

```text
TweetCreated(T1)
```

is processed twice.

Feed insertion should not create duplicate visible entries.

Therefore fan-out operations should be idempotent, for example by uniquely identifying feed entries with the tweet ID.

This is a strong distributed-systems point to mention in a more advanced interview.

---

# 24. Backpressure

Imagine a celebrity posts during a major event and the fan-out queue grows rapidly.

The architecture should tolerate a backlog:

```text
Tweet events
     ↓↓↓↓↓
Message Queue
     ↓
Fan-Out Workers
```

We can:

- add more workers,
- batch updates,
- prioritize newer/important events,
- switch high-follower users to pull-based delivery,
- accept temporary propagation delay.

The queue protects the synchronous tweet-writing path from downstream overload.

---

# 25. Capacity Reasoning

You do not need exact Twitter numbers in an interview. Use reasonable assumptions supplied by the interviewer.

Example:

```text
100M daily active users
10 feed reads / user / day
```

That implies roughly:

```text
1B feed reads/day
```

Average requests per second:

```text
1,000,000,000 / 86,400 ≈ 11,600 reads/sec
```

Peak traffic can be many times higher.

If users post much less frequently than they read, this reinforces the central conclusion:

```text
READ TRAFFIC >> WRITE TRAFFIC
```

and explains why precomputation/caching is valuable.

---

# 26. Final Architecture

```text
                              CLIENTS
                                 ↓
                         LOAD BALANCER / API
                                 ↓
                ┌────────────────┴────────────────┐
                ↓                                 ↓
          TWEET SERVICE                      FEED SERVICE
                ↓                                 ↓
       DISTRIBUTED ID GEN                    FEED CACHE
                ↓                                 ↓
          TWEET DATABASE                 PRECOMPUTED IDs
                ↓                                 ↓
         TweetCreated Event              CELEBRITY TWEETS
                ↓                                 ↓
          QUEUE / STREAM                    MERGE / RANK
                ↓                                 ↓
         FAN-OUT WORKERS                    TWEET CACHE
                ↓                                 ↓
          SOCIAL GRAPH              cache miss → TWEET DB
                ↓                                 ↓
           FEED STORE                         RESPONSE
```

For media:

```text
Tweet Service → Object Storage → CDN → Readers
```

---

# 27. Important Trade-Offs

## Fan-out on write vs fan-out on read

```text
Fan-out on write
→ expensive writes
→ fast reads

Fan-out on read
→ cheap writes
→ expensive reads

Hybrid
→ push normal users
→ pull celebrities
```

## Strong vs eventual consistency

```text
Strong consistency
→ harder/slower at massive scale

Eventual consistency
→ scalable
→ follower may see tweet after a short delay
```

## Full objects vs tweet IDs in feed

```text
Full objects
→ fewer hydration lookups
→ lots of duplicated data

Tweet IDs
→ compact feeds
→ requires tweet hydration/cache
```

## Chronological vs ranked feed

```text
Chronological
→ simple and deterministic

Ranked
→ potentially better engagement
→ additional computation and ML complexity
```

---

# 28. Common Interview Follow-Up Questions

Be ready for questions such as:

- How would you generate a user's home timeline?
- Why is fan-out on write attractive?
- What happens when a celebrity with 50 million followers tweets?
- Why use a hybrid push/pull strategy?
- How do you store the social graph?
- How would you shard tweets?
- How would you generate globally unique tweet IDs?
- How do you keep feed reads fast?
- What data would you cache?
- How do you handle a viral tweet?
- Why should fan-out happen asynchronously?
- What happens if the queue is delayed?
- How do you paginate the feed?
- How do you support deletion?
- What happens after follow/unfollow?
- What consistency model do you need?
- How would you add ranking?
- Where would photos/videos be stored?

---

# 29. Interview Flow to Memorize

If the interviewer says:

> **Design Twitter.**

A strong sequence is:

```text
1. Clarify scope
   tweets + follows + home feed
        ↓
2. Define APIs
        ↓
3. Estimate traffic
   emphasize reads >> writes
        ↓
4. Define Tweet + Follow data models
        ↓
5. Design basic read/write services
        ↓
6. Identify home-feed bottleneck
        ↓
7. Compare fan-out on read vs fan-out on write
        ↓
8. Choose hybrid fan-out
   push ordinary users, pull celebrities
        ↓
9. Add feed/tweet caches
        ↓
10. Add queue + fan-out workers
        ↓
11. Shard databases / feed stores
        ↓
12. Add distributed tweet IDs
        ↓
13. Discuss availability + eventual consistency
        ↓
14. Handle hot users / viral tweets / deletion
```

---

# 30. The Architecture to Keep in Your Head

```text
                         User posts tweet
                               ↓
                         Tweet Service
                               ↓
                         Tweet Database
                               ↓
                         Event Queue
                               ↓
                       Fan-Out Workers
                               ↓
                        Social Graph
                               ↓
                    Followers' Feed Cache


User opens feed
      ↓
 Feed Service
      ↓
Feed Cache ───────────────┐
      ↓                   │
normal-user tweet IDs     │
                          ↓
                 celebrity recent tweets
                          │
               ┌──────────┘
               ↓
          Merge / Rank
               ↓
          Tweet Cache
               ↓
        Full tweet objects
               ↓
             User
```

---

# 31. Ten Things to Memorize

1. Twitter is strongly **read-heavy**.
2. The hard problem is the **home timeline**, not basic tweet storage.
3. **Fan-out on read** means cheap writes but expensive reads.
4. **Fan-out on write** means expensive writes but fast reads.
5. Use a **hybrid approach**: push ordinary accounts, pull celebrity accounts.
6. Use an **asynchronous queue/event stream** for fan-out.
7. Store **tweet IDs in feed caches**, then hydrate full tweets from cache/database.
8. Use **globally unique, preferably time-sortable distributed tweet IDs**.
9. Accept **eventual consistency** for timeline propagation.
10. Cache aggressively and plan for **hot users, hot tweets, and hot partitions**.

A one-sentence interview summary:

> Persist each tweet once, asynchronously precompute timelines for ordinary authors, dynamically merge celebrity content at read time, and use caches, queues, partitioned storage, and eventual consistency to keep the system scalable and the read path fast.

---

***

# Design Discord --- System Design Interview Notes

## 1. Bullet-Point Overview

-   **Goal:** design a Discord-like real-time text-chat system organized
    into servers/guilds and channels.
-   **Core features:** join servers/channels, send messages, receive new
    messages in real time, and retrieve message history.
-   **Key non-functional requirements:** low latency, durable messages,
    high availability, horizontal scalability, and sensible ordering
    within a channel.
-   **Main entities:** `User`, `Guild`, `GuildMember`, `Channel`, and
    `Message`.
-   **HTTP APIs:** use endpoints such as
    `POST /channels/{channel_id}/messages` for sending and
    `GET /channels/{channel_id}/messages?before={message_id}&limit=50`
    for history.
-   **WebSockets:** maintain persistent connections so servers can
    immediately push new messages and other live events to clients.
-   **API vs gateway servers:** ordinary API servers can be stateless;
    WebSocket gateway servers are stateful because they own active
    client connections.
-   **Message lifecycle:** authenticate → check permissions/rate limits
    → create ID → persist → acknowledge sender → publish event → fan out
    → push through gateways.
-   **Durability rule:** persist a message before claiming durable
    success to the sender.
-   **Real-time fan-out:** use pub/sub or an event bus to decouple
    message persistence from delivery to connected clients.
-   **Message storage:** optimize around the main query: "give me the
    newest messages for channel X."
-   **Partitioning:** partition messages by channel, but add a time
    bucket for large/old channels so partitions do not grow forever.
-   **Ordering:** use a distributed, roughly time-sortable ID such as a
    Snowflake-style ID.
-   **Pagination:** use cursor-based pagination (`before=<message_id>`)
    instead of large numeric offsets.
-   **Caching:** cache guild/channel metadata, permissions, recent
    messages, and transient session/presence information.
-   **Attachments:** store large files in object storage and serve them
    through a CDN; keep only metadata/references in messages.
-   **Offline users:** durable history lets reconnecting clients fetch
    what they missed; mobile notifications are a separate path.
-   **Presence/typing:** treat them as transient and eventually
    consistent; losing a typing event is acceptable, losing an
    acknowledged message is not.
-   **Failure handling:** gateways can fail, clients reconnect/resume,
    retries may duplicate events, and IDs enable deduplication.
-   **Backpressure:** slow clients need bounded buffers; low-value
    events can be dropped and pathological connections can
    reconnect/catch up.
-   **Hot channels:** mitigate with caching, bucketed partitions,
    request coalescing, distributed fan-out, and isolation of unusually
    large communities.
-   **Rate limiting:** protect message sending, reconnects, and API
    calls against spam and abuse.
-   **Core trade-off:** stronger durability/ordering for messages,
    weaker consistency for ephemeral state.

------------------------------------------------------------------------

## 2. Requirements

### Functional

-   Users join guilds/servers.
-   Guilds contain channels.
-   Authorized users send messages to channels.
-   Online users receive messages in near real time.
-   Users load recent and older channel history.
-   Permissions determine who can read/send.

Optional follow-ups: - reactions and edits, - attachments, - presence, -
typing indicators, - notifications, - search, - voice/video.

### Non-functional

-   Low message-delivery latency.
-   Durable message history.
-   High availability.
-   Massive concurrent-connection support.
-   Horizontal scalability.
-   Efficient chronological history retrieval.
-   Ordering primarily **within a channel**, not globally across
    Discord.

------------------------------------------------------------------------

## 3. API and Protocol Design

### Send a message

``` http
POST /channels/{channel_id}/messages
```

Conceptual request:

``` json
{
  "content": "Hello!"
}
```

### Read history

``` http
GET /channels/{channel_id}/messages?before={message_id}&limit=50
```

Cursor pagination is preferable to `offset=100000` because it remains
stable while new messages arrive and maps naturally to ordered message
IDs.

### Real-time events

Keep a WebSocket connection open for events such as:

``` text
MESSAGE_CREATE
MESSAGE_UPDATE
MESSAGE_DELETE
TYPING_START
PRESENCE_UPDATE
```

Think of the split as:

``` text
HTTP/REST  → commands, CRUD, history
WebSocket  → server-pushed live events
```

------------------------------------------------------------------------

## 4. Why WebSockets?

Polling looks like:

``` text
Client → "anything new?" → Server
Client → "anything new?" → Server
Client → "anything new?" → Server
```

Most requests may return nothing.

With a WebSocket:

``` text
Client <====================> Gateway
        persistent connection

Gateway ── new event ────────> Client
```

This gives low-latency push, but introduces the operational challenge of
maintaining millions of long-lived connections.

------------------------------------------------------------------------

## 5. High-Level Architecture

``` text
                         CLIENTS
                            │
             ┌──────────────┴──────────────┐
             │                             │
           HTTP                         WebSocket
             │                             │
       LOAD BALANCER                   GATEWAYS
             │                             │
        API SERVERS                        │
             │                             │
        CHAT SERVICE                       │
             │                             │
     ┌───────┼─────────┐                   │
     ▼       ▼         ▼                   │
 Message DB Cache   Event Bus ─────────────┘
                      │
                real-time fan-out
```

Important separation:

``` text
API server
→ mostly stateless

Gateway server
→ stateful
→ owns active WebSocket connections
```

------------------------------------------------------------------------

## 6. End-to-End Message Flow

Suppose Alice sends `hello` in `#general`.

1.  Alice sends the message to the API/chat service.
2.  Authenticate Alice.
3.  Verify membership and channel permissions.
4.  Apply rate limiting/moderation if required.
5.  Generate a unique message ID.
6.  Persist the message in durable storage.
7.  Acknowledge successful creation to Alice.
8.  Publish a `MESSAGE_CREATE` event.
9.  Route that event to relevant WebSocket gateways.
10. Gateways push it to connected users.

``` text
Alice
  │
  ▼
Chat Service
  │
  ├─ validate
  ├─ permission check
  ├─ generate ID
  ▼
Message DB
  │
  │ write succeeds
  ▼
Chat Service ───────────────> ACK Alice
  │
  ▼
Event Bus / Pub-Sub
  ├────────> Gateway A ─────> clients
  ├────────> Gateway B ─────> clients
  └────────> Gateway C ─────> clients
```

The two important paths solve different problems:

``` text
Message database → durable history
Gateway/event bus → low-latency live delivery
```

------------------------------------------------------------------------

## 7. Message Storage

The dominant read is usually:

> Give me the newest N messages in channel X.

A distributed wide-column/key-oriented store is a natural large-scale
option.

Conceptual schema:

``` text
Messages
-----------------------------------
channel_id
bucket
message_id
author_id
content
created_at

partition key: (channel_id, bucket)
ordering key:  message_id
```

Why channel-based partitioning? - History is normally fetched per
channel. - Related messages can be retrieved efficiently.

Why add a bucket? - A single popular channel can exist for years. - One
unbounded partition becomes huge and hot. - Time buckets divide that
history into manageable partitions.

``` text
(channel_42, bucket_1) → older messages
(channel_42, bucket_2) → newer messages
(channel_42, bucket_3) → current messages
```

This demonstrates **query-driven data modeling**: design storage around
how the application actually reads data.

------------------------------------------------------------------------

## 8. Snowflake-Style Message IDs

A distributed ID can conceptually contain:

``` text
timestamp | worker/machine | sequence
```

Useful properties: - globally unique, - generated independently on
multiple servers, - approximately chronological, - usable as a
pagination cursor.

This avoids requiring one central auto-increment database sequence for
every message.

------------------------------------------------------------------------

## 9. Fan-Out and Pub/Sub

One message may need to reach many connected users.

Avoid making the sender wait while one server synchronously loops
through thousands of recipients.

Instead:

``` text
persist
   ↓
publish event
   ↓
event bus / pub-sub
   ↓
relevant gateways
   ↓
connected clients
```

For giant communities: - distribute fan-out, - target
connected/subscribed users rather than every registered member, -
isolate exceptionally hot guilds/channels, - let gateway capacity scale
separately from message-storage capacity.

------------------------------------------------------------------------

## 10. Reconnection and Offline Users

Connections routinely disappear because of: - mobile sleep, - Wi-Fi
changes, - gateway restarts, - temporary network loss.

Recovery flow:

``` text
disconnect
   ↓
reconnect
   ↓
authenticate / resume session
   ↓
supply last known sequence/message cursor
   ↓
fetch missed events/messages
   ↓
resume live WebSocket stream
```

The durable message database is the ultimate source for missed chat
history.

Mobile push notifications can be a separate subsystem for users without
an active connection.

------------------------------------------------------------------------

## 11. Delivery Semantics and Deduplication

Exactly-once delivery through an entire distributed system is expensive
and difficult.

A practical model is:

``` text
at-least-once delivery
+
unique IDs
+
deduplication
```

Example:

``` text
Client receives message 12345
Gateway retries message 12345
Client already knows 12345
→ ignore duplicate
```

Retries therefore become safe.

------------------------------------------------------------------------

## 12. Caching

Useful cache candidates: - guild metadata, - channel metadata, - roles
and permissions, - recent messages, - active-session routing, -
presence.

History path:

``` text
GET recent messages
       ↓
     Cache
    ↙     ↘
  HIT     MISS
   ↓        ↓
return     DB
            ↓
          cache
            ↓
          return
```

Popular channels can become **hot keys**. Request coalescing can combine
many identical history requests into one database read.

Cache is an optimization, not the durable source of truth for messages.

------------------------------------------------------------------------

## 13. Attachments

Do not store large image/video bytes directly in the primary message
table.

``` text
Client
  │
  ├── text + metadata ──> Message DB
  │
  └── media ────────────> Object Storage ──> CDN
```

The message stores an attachment reference.

Benefits: - smaller message records, - cheaper scalable binary
storage, - CDN handles high-bandwidth downloads, - database remains
focused on chat-history queries.

------------------------------------------------------------------------

## 14. Presence and Typing

Presence and typing indicators differ from messages.

``` text
Message
→ durable
→ important not to lose

Typing indicator
→ temporary
→ okay to lose

Presence
→ temporary
→ can tolerate slight staleness
```

They are good candidates for: - gateway memory, - distributed caches, -
pub/sub, - TTL-based state.

For very large guilds, avoid broadcasting every presence change to every
member.

------------------------------------------------------------------------

## 15. Failure Handling

### Gateway failure

``` text
Gateway dies
   ↓
connections close
   ↓
clients reconnect elsewhere
   ↓
resume / catch up
```

Use heartbeats to detect dead connections and randomized backoff/jitter
to prevent a reconnect storm.

### Message service fails after persistence

The durable message remains stored. Event publication/delivery can be
retried or recovered.

### Duplicate events

Give events/messages unique IDs and make consumers idempotent.

### Slow client

Do not allow unlimited per-connection buffering.

Possible responses: - bounded buffers, - backpressure, - drop low-value
transient events, - disconnect and let the client resume later.

### Database unavailable

Do not falsely tell users a durable message succeeded if it has not been
safely persisted. Depending on requirements, reject the write or buffer
it in durable infrastructure.

------------------------------------------------------------------------

## 16. Hot Channels

A massive channel can concentrate reads/writes on one partition or
service.

Possible mitigations: - time-bucket message partitions, - caching recent
history, - request coalescing, - partitioned event infrastructure, -
distributed gateway fan-out, - isolate unusually hot channels, -
autoscale affected services.

A key lesson is that **even a sharded system can have hotspots** when
user traffic is highly uneven.

------------------------------------------------------------------------

## 17. Consistency Trade-Offs

Not every feature needs the same guarantees.

### Messages

Prioritize: - durability, - reliable per-channel ordering, - predictable
history.

### Presence / typing

Accept: - eventual consistency, - temporary staleness, - occasional
dropped events.

Interview principle:

> Spend stronger consistency and durability where the user experience
> actually requires them.

------------------------------------------------------------------------

## 18. Scaling Each Layer

``` text
More HTTP traffic
→ add stateless API servers

More connected users
→ add gateway servers

More live events
→ partition/scale event bus

More message history
→ partition + replicate database

More media
→ object storage + CDN
```

Possible partitioning strategies: - gateway/session assignment by
user/session, - message storage by `(channel_id, time_bucket)`, - event
streams by guild/channel when ordering matters.

------------------------------------------------------------------------

## 19. Important Trade-Offs

### WebSockets vs polling

``` text
WebSocket
→ low-latency push
→ persistent-connection complexity

Polling
→ simple request model
→ wasteful + higher latency
```

### Stateless API vs stateful gateway

``` text
API
→ easy horizontal scaling

Gateway
→ knows active sockets
→ needs connection/session recovery
```

### Synchronous vs asynchronous fan-out

``` text
Synchronous
→ simpler
→ sender path grows with recipient count

Async pub/sub
→ scalable + decoupled
→ more distributed-system complexity
```

### One channel partition vs bucketed partitions

``` text
one partition/channel
→ simple history lookup
→ grows forever / hotspot risk

channel + time bucket
→ bounded partitions
→ slightly more query logic
```

### Strong vs eventual consistency

``` text
messages
→ stronger durability/order

typing/presence
→ eventual consistency is sufficient
```

------------------------------------------------------------------------

## 20. Interview Follow-Up Questions

Be ready to answer:

-   Why use WebSockets rather than polling?
-   Why separate API servers and gateway servers?
-   How does a message reach users connected to different gateway
    machines?
-   When should the sender receive an ACK?
-   How do you prevent acknowledged-message loss?
-   How do you handle duplicate events?
-   How do users catch up after reconnecting?
-   How do you maintain ordering within a channel?
-   Why use Snowflake-style IDs?
-   Why partition messages by channel?
-   Why introduce time buckets?
-   Why use cursor pagination?
-   How do you handle a channel with hundreds of thousands of members?
-   How do you scale millions of WebSocket connections?
-   What happens when a gateway fails?
-   How do you prevent reconnect storms?
-   Where should attachments be stored?
-   Where should presence state live?
-   What data can be eventually consistent?
-   How would rate limiting work?
-   How would voice/video change the architecture?

------------------------------------------------------------------------

## 21. Interview Version to Memorize

When asked **"Design Discord"**, follow this sequence:

``` text
1. Clarify scope
   → text channels + history + real-time delivery

2. Requirements
   → low latency
   → durable messages
   → high availability

3. APIs
   → POST message
   → GET channel history

4. Real-time layer
   → WebSocket gateways

5. Data model
   → channel + time bucket
   → time-sortable message IDs

6. Send flow
   → authenticate
   → permissions/rate limit
   → persist
   → ACK
   → publish
   → fan out

7. History flow
   → cache
   → distributed message DB
   → cursor pagination

8. Scaling
   → API servers
   → gateway shards
   → pub/sub
   → partitioned storage

9. Reliability
   → heartbeat
   → reconnect/resume
   → retry
   → dedup
   → backpressure

10. Follow-ups
    → attachments
    → presence
    → giant guilds
    → voice/video
```

------------------------------------------------------------------------

## 22. Architecture to Keep in Your Head

``` text
                         CLIENT
                           │
             ┌─────────────┴─────────────┐
             │                           │
           HTTP                       WebSocket
             │                           │
       Load Balancer                 Gateways
             │                           ▲
        API Servers                      │
             │                           │
        Chat Service                     │
         │       │                       │
         ▼       ▼                       │
    Message DB  Event Bus ───────────────┘
         ▲
         │
       Cache

Media:
Client → Object Storage → CDN
```

And know this message path cold:

``` text
send
→ validate
→ generate ID
→ persist
→ ACK sender
→ publish event
→ route to gateways
→ push over WebSocket
```

------------------------------------------------------------------------

## 23. 60-Second Revision

**Discord = durable channel history + real-time event delivery.**

Key concepts:

1.  **WebSocket gateways** hold persistent client connections.
2.  **Stateless APIs/chat services** handle business logic.
3.  **Persist before durable ACK.**
4.  **Pub/Sub** decouples message storage from fan-out.
5.  Store messages by **channel + time bucket**.
6.  Use **Snowflake-style IDs** for unique, sortable message IDs.
7.  Use **cursor pagination** for history.
8.  Cache hot/recent data.
9.  Store media in **object storage + CDN**.
10. Treat messages as durable; presence/typing as transient.
11. Design for reconnects, retries, deduplication, backpressure, and hot
    channels.

If you can explain the send flow, history flow, WebSocket layer, storage
partitioning, and failure behavior, you have the core of the **Design
Discord** system-design interview question.

***

# Design YouTube --- System Design Interview Notes

## 1. Quick Overview --- Bullet Points

### Goal

Design the core of a YouTube-like video platform where users can:

-   upload videos,
-   watch/stream videos,
-   browse video metadata,
-   support very large files and very high read traffic.

For an interview, prioritize the **upload + processing + playback**
pipeline. Features such as comments, subscriptions, recommendations,
ads, and live streaming can be treated as follow-ups unless explicitly
required.

### Core functional requirements

-   Upload a video.
-   Process uploaded video into playable formats.
-   Store video metadata.
-   Watch/stream a video.
-   Support different resolutions/bitrates.
-   Optionally show upload/processing status.
-   Optionally support thumbnails, likes, comments, subscriptions, and
    view counts.

### Core non-functional requirements

-   **High availability** for playback.
-   **Low startup latency** when a user presses Play.
-   **Durability** --- uploaded videos must not disappear.
-   **Massive scalability** --- potentially billions of videos and
    users.
-   **High bandwidth efficiency** --- video files are much larger than
    ordinary API payloads.
-   **Global delivery** --- users should stream from infrastructure
    close to them.
-   **Eventual consistency is acceptable** for many secondary features
    such as view counts.

### Core entities

``` text
User
Video
VideoMetadata
VideoAsset / Rendition
Upload
```

A video record may contain:

``` text
video_id
owner_id
title
description
status
created_at
duration
thumbnail
```

The actual video bytes should **not** live in the normal metadata
database.

### Main architectural idea

``` text
                    ┌── Metadata DB
                    │
Client → API Service
   │
   └── Video Upload → Object Storage
                           │
                           ▼
                    Processing Queue
                           │
                           ▼
                       Workers
                    / transcoding \
                           │
                           ▼
                     Object Storage
                           │
                           ▼
                          CDN
                           │
                           ▼
                        Viewer
```

The system has two major paths:

``` text
WRITE PATH → upload + process
READ PATH  → stream through CDN
```

------------------------------------------------------------------------

## 2. Start by Clarifying Scope

"YouTube" is far too large to design completely in one interview.

A good initial scope is:

1.  users upload prerecorded videos,
2.  the platform processes those videos,
3.  viewers stream them,
4.  video metadata is retrievable,
5.  playback supports multiple qualities.

Possible follow-ups:

-   recommendations,
-   search,
-   subscriptions,
-   comments,
-   likes,
-   view counts,
-   ads,
-   live streaming.

A strong interview answer explicitly narrows the problem before
designing it.

------------------------------------------------------------------------

## 3. Why Video Is Different

A normal web API may return:

``` text
a few KB
```

A video may be:

``` text
hundreds of MB
or
multiple GB
```

Therefore it is a bad idea to route every video byte through ordinary
application servers.

Bad:

``` text
Client
  ↓
API Server
  ↓
Application Server
  ↓
Video Storage
```

The application layer becomes a bandwidth bottleneck.

Better:

``` text
Client ───────────────> Object Storage
   │
   └── control/API ───> Application Service
```

and for playback:

``` text
Viewer → CDN → Object Storage
```

Application servers handle **control-plane logic**; storage/CDN
infrastructure handles the heavy **data plane**.

------------------------------------------------------------------------

## 4. API Design

Conceptually, we need APIs for:

### Start an upload

``` http
POST /videos
```

Request:

``` json
{
  "title": "System Design",
  "description": "..."
}
```

Response could contain:

``` json
{
  "video_id": "123",
  "upload_url": "..."
}
```

The upload URL can be a temporary/presigned URL allowing the client to
upload directly to object storage.

### Get video metadata

``` http
GET /videos/{video_id}
```

Returns metadata such as:

``` json
{
  "video_id": "123",
  "title": "System Design",
  "status": "READY",
  "thumbnail": "...",
  "playback_url": "..."
}
```

### Stream video

The application generally does not return the entire video file itself.

Instead it provides or resolves a playback manifest/URL that points
toward CDN-backed video segments.

------------------------------------------------------------------------

## 5. Direct-to-Object-Storage Upload

A scalable upload flow:

``` text
1. Client asks API to create upload
2. API authenticates user
3. API creates video metadata / video_id
4. API returns temporary upload URL
5. Client uploads video directly to object storage
6. Upload completion triggers processing
```

Architecture:

``` text
Client
  │
  │ 1. create upload
  ▼
API Service
  │
  │ 2. signed/presigned URL
  ▼
Client
  │
  │ 3. large video bytes
  ▼
Object Storage
```

Advantages:

-   application servers do not proxy GB-sized files,
-   upload bandwidth scales with storage infrastructure,
-   API servers remain focused on business logic,
-   fewer expensive network hops.

------------------------------------------------------------------------

## 6. Resumable / Multipart Uploads

Large uploads can take minutes or hours.

If a 5 GB upload fails at 99%, restarting from zero is terrible UX.

Use multipart/resumable uploads:

``` text
Video
 ├── chunk 1
 ├── chunk 2
 ├── chunk 3
 └── chunk N
```

Flow:

``` text
split into chunks
      ↓
upload independently
      ↓
retry failed chunks
      ↓
complete multipart upload
```

Benefits:

-   retries affect only failed pieces,
-   chunks can upload in parallel,
-   interrupted uploads can resume,
-   large-file reliability improves significantly.

------------------------------------------------------------------------

## 7. Raw Video Storage

The initially uploaded file is the **source/original**.

Store it in durable object/blob storage.

Why object storage?

-   designed for large binary objects,
-   high durability,
-   cheap relative to database storage,
-   practically unlimited horizontal capacity,
-   integrates naturally with CDNs and processing pipelines.

Do not store video bytes as database BLOBs in the main metadata database
unless scale is tiny.

Use the database for:

``` text
video_id → metadata
```

Use object storage for:

``` text
video_id → large binary assets
```

------------------------------------------------------------------------

## 8. Why Transcoding Is Necessary

Users upload videos in different:

-   codecs,
-   resolutions,
-   containers,
-   bitrates,
-   frame rates.

A raw 4K upload may also be inappropriate for a user watching on a phone
over a slow network.

Therefore the platform **transcodes** the original into multiple
renditions.

Example:

``` text
Original
   │
   ├── 2160p
   ├── 1440p
   ├── 1080p
   ├── 720p
   ├── 480p
   └── 360p
```

Each rendition may have an appropriate bitrate/codec.

This enables **adaptive bitrate streaming**.

------------------------------------------------------------------------

## 9. Asynchronous Video Processing

Transcoding is CPU/GPU intensive and can take a long time.

Do not perform it synchronously inside:

``` text
POST /videos
```

Instead:

``` text
Upload completed
      ↓
Processing Queue
      ↓
Transcoding Workers
      ↓
Processed Renditions
      ↓
Object Storage
```

The API can quickly respond while background workers process the video.

Video status may transition through:

``` text
UPLOADING
    ↓
PROCESSING
    ↓
READY
```

or:

``` text
PROCESSING
    ↓
FAILED
```

------------------------------------------------------------------------

## 10. Why a Queue?

Suppose a celebrity uploads a video while thousands of other uploads
arrive.

Without a queue:

``` text
uploads → directly invoke workers
```

workers may become overwhelmed.

With a queue:

``` text
Uploads
   ↓
Queue
   ↓
Worker Pool
```

The queue provides:

-   buffering,
-   retry support,
-   decoupling,
-   backpressure,
-   independent worker scaling.

If uploads temporarily exceed processing capacity, jobs wait rather than
crashing the system.

------------------------------------------------------------------------

## 11. Processing Pipeline

Processing may involve multiple stages:

``` text
Original Upload
      ↓
Validation
      ↓
Metadata Extraction
      ↓
Transcoding
      ↓
Segmenting
      ↓
Thumbnail Generation
      ↓
Store Renditions
      ↓
Update status = READY
```

Some stages can execute in parallel.

For example:

``` text
               Original
              /    |    \
             ↓     ↓     ↓
          1080p   720p   480p
             \     |     /
              processed assets
```

A workflow/orchestration layer can track dependencies and retries.

------------------------------------------------------------------------

## 12. Adaptive Bitrate Streaming

Instead of delivering one enormous file, modern streaming typically
divides video into small segments.

Conceptually:

``` text
Video
 ├── segment 1
 ├── segment 2
 ├── segment 3
 └── ...
```

And multiple qualities exist:

``` text
1080p:
  seg1 seg2 seg3 ...

720p:
  seg1 seg2 seg3 ...

480p:
  seg1 seg2 seg3 ...
```

A manifest describes the available streams.

The player chooses quality dynamically.

Example:

``` text
fast connection
→ 1080p

network slows
→ 720p

network slows further
→ 480p
```

This is **adaptive bitrate streaming (ABR)**.

Common industry protocols include HLS and MPEG-DASH.

------------------------------------------------------------------------

## 13. Why Segment Videos?

Segmenting provides several advantages:

-   playback can begin before the entire video downloads,
-   player downloads only what it needs,
-   quality can change between segments,
-   CDN caching works efficiently,
-   seeking does not require downloading the entire file.

Instead of:

``` text
download 2 GB video
then play
```

we can:

``` text
download next few seconds
→ play
→ download more
→ continue
```

------------------------------------------------------------------------

## 14. CDN

A CDN is one of the most important components in the design.

Without CDN:

``` text
Viewer in Europe
      ↓
Origin storage in US
      ↓
every segment travels globally
```

With CDN:

``` text
Viewer
  ↓
Nearby CDN Edge
  ↓
cached video segment
```

If the edge does not have the segment:

``` text
Viewer
  ↓
CDN
  │ MISS
  ↓
Origin Object Storage
  ↓
CDN caches segment
  ↓
Viewer
```

Benefits:

-   lower playback latency,
-   lower origin bandwidth,
-   reduced load on central infrastructure,
-   global scalability,
-   popular videos become especially cheap to serve from edge caches.

------------------------------------------------------------------------

## 15. Read Path --- Watching a Video

A simplified playback flow:

``` text
Viewer
  │
  │ GET video metadata
  ▼
API Service
  │
  ▼
Metadata DB

Viewer receives playback/manifest information
  │
  ▼
CDN
  │
  ├── cache hit → segment returned
  │
  └── cache miss
          ↓
      Object Storage
          ↓
        CDN
          ↓
       Viewer
```

The API is involved in authorization/metadata.

The CDN handles the expensive video bytes.

------------------------------------------------------------------------

## 16. Write Path --- Uploading a Video

Memorize this path:

``` text
Client
  │
  │ create upload
  ▼
API Service
  │
  │ upload URL
  ▼
Client
  │
  │ multipart upload
  ▼
Object Storage
  │
  ▼
Processing Queue
  │
  ▼
Transcoding Workers
  │
  ├── multiple resolutions
  ├── segments
  └── thumbnails
  │
  ▼
Processed Object Storage
  │
  ▼
Metadata DB: READY
```

This is the heart of the YouTube design.

------------------------------------------------------------------------

## 17. Metadata Database

Video metadata is much smaller than the video itself.

Example:

``` text
Video
--------------------------------
video_id
user_id
title
description
created_at
status
duration
thumbnail_url
visibility
```

Typical operations:

``` text
video_id → metadata
user_id → uploaded videos
```

SQL or NoSQL can both be valid depending on scale and requirements.

Do not choose a database merely because "YouTube is big."

Explain the access patterns and trade-offs.

------------------------------------------------------------------------

## 18. Video IDs

Each video needs a globally unique ID.

A single central auto-increment sequence may eventually become a
coordination bottleneck.

Options include:

-   UUID-like random IDs,
-   Snowflake-style distributed IDs,
-   dedicated distributed ID generation.

Properties we care about:

-   uniqueness,
-   scalable generation,
-   URL-safe representation,
-   optionally rough time ordering.

------------------------------------------------------------------------

## 19. Storage Estimation

Video storage dominates the system.

Suppose:

``` text
1 million uploads/day
average original = 500 MB
```

Raw uploads alone:

``` text
1,000,000 × 500 MB
≈ 500 TB/day
```

And transcoded copies increase this further.

If we keep:

``` text
original
1080p
720p
480p
360p
```

storage can easily become multiple times the original upload size.

The point of the estimate is not exact arithmetic.

The interview insight is:

> Video storage and bandwidth dominate YouTube's infrastructure costs.

------------------------------------------------------------------------

## 20. Bandwidth Estimation

Playback traffic can dwarf upload traffic.

Suppose one video is uploaded once but watched:

``` text
10 million times
```

That gives:

``` text
1 write
vs
10,000,000 reads/streams
```

Therefore YouTube is strongly **read-heavy in bandwidth**.

This is why CDN caching is critical.

------------------------------------------------------------------------

## 21. Hot / Viral Videos

A viral video can suddenly receive millions of requests.

Without a CDN:

``` text
millions of viewers
       ↓
origin storage
```

creates enormous load.

With CDN:

``` text
             Origin
               │
        ┌──────┼──────┐
        ▼      ▼      ▼
      Edge   Edge    Edge
      /|\     /|\     /|\
   viewers viewers viewers
```

Popular segments are replicated naturally across edge caches.

A viral video is therefore a classic **hot object** problem where CDN
caching is extremely effective.

------------------------------------------------------------------------

## 22. Cache Strategy

Possible cache layers:

### Metadata cache

Cache frequently accessed:

``` text
video_id → title/status/thumbnail/etc.
```

### CDN cache

Cache:

``` text
video segments
thumbnails
manifests
```

These caches solve different problems:

``` text
Redis/memory cache
→ small metadata

CDN
→ huge static media
```

------------------------------------------------------------------------

## 23. View Counts

Updating the primary database for every view synchronously is expensive.

Bad:

``` text
every playback
   ↓
UPDATE videos SET views = views + 1
```

A viral video creates one extremely hot database row.

Better:

``` text
Playback event
     ↓
Event Stream / Queue
     ↓
Aggregation Workers
     ↓
batched counters
     ↓
Analytics / Metadata Store
```

View counts can usually be **eventually consistent**.

The displayed number does not need to change atomically for every
individual view.

------------------------------------------------------------------------

## 24. Likes, Comments, and Subscriptions

These can be separate services.

``` text
                   Video Service
                        │
       ┌────────────────┼────────────────┐
       ▼                ▼                ▼
 Comment Service   Like Service   Subscription Service
```

Separating high-level domains allows them to:

-   scale independently,
-   use different storage models,
-   fail independently,
-   evolve independently.

Do not over-split into microservices too early in the interview;
introduce them when scale or requirements justify the separation.

------------------------------------------------------------------------

## 25. Search and Recommendations

These are major systems by themselves.

### Search

A separate search/indexing pipeline can consume video metadata and
create a search index.

``` text
Video metadata
      ↓
Indexing pipeline
      ↓
Search index
```

### Recommendations

Recommendation systems may consume:

``` text
watch history
likes
subscriptions
clicks
watch time
video metadata
```

This is generally outside the core upload/playback design unless the
interviewer asks for it.

------------------------------------------------------------------------

## 26. Failure Handling

### Upload fails halfway

Use resumable/multipart uploads.

``` text
successful chunks remain
failed chunks retry
```

### Transcoding worker crashes

Queue/job remains retryable.

Workers should ideally be **idempotent** so retrying a job does not
corrupt output.

### One rendition fails

Retry only the failed work if possible.

### CDN misses

Fetch from origin storage and populate the edge cache.

### Metadata service fails

Use replicated/stateless API services and cache where appropriate.

### Object storage temporarily fails

Playback may continue for cached CDN segments, but uncached content can
fail. Durable replicated object storage reduces this risk.

------------------------------------------------------------------------

## 27. Idempotency

Retries happen frequently in distributed systems.

Imagine:

``` text
worker transcodes 720p
      ↓
network timeout
      ↓
job retried
```

The retry should not create uncontrolled duplicate assets.

Use deterministic output paths or job IDs:

``` text
/video/123/720p/segment_001
```

and make processing operations safe to retry.

The same principle can apply to upload-finalization requests.

------------------------------------------------------------------------

## 28. Security

Uploads should be validated.

Possible concerns:

-   unauthorized uploads,
-   malicious files,
-   illegal/abusive content,
-   private/unlisted video access,
-   signed URL expiration.

Access path for private content may require authorization before issuing
short-lived playback URLs/tokens.

Object storage should not necessarily expose unrestricted permanent
public URLs.

------------------------------------------------------------------------

## 29. Data Durability

Videos are expensive and often impossible for the platform to recreate
if the original upload disappears.

Use durable object storage with replication.

Metadata databases should also use:

-   replication,
-   backups,
-   failover.

An important distinction:

``` text
Cache
→ disposable

Metadata DB
→ durable

Object Storage
→ durable source of video assets
```

------------------------------------------------------------------------

## 30. Geographic Distribution

For a global platform:

``` text
Users
 ↓
regional APIs
 ↓
global metadata/storage infrastructure

Video playback
 ↓
nearby CDN edge
```

The highest-volume data --- video bytes --- should travel from locations
near viewers whenever possible.

Metadata and control-plane operations are far smaller and can tolerate
more centralized coordination than video delivery.

------------------------------------------------------------------------

## 31. Upload vs Playback Optimization

These two paths have very different goals.

### Upload path

Optimize for:

-   reliability,
-   resumability,
-   durability,
-   asynchronous processing.

### Playback path

Optimize for:

-   low startup latency,
-   high throughput,
-   nearby delivery,
-   adaptive quality,
-   caching.

This is a useful interview observation:

> Don't force the read and write paths through the same architecture
> when their workloads are fundamentally different.

------------------------------------------------------------------------

## 32. Important Trade-Offs

### Direct upload vs API-proxied upload

``` text
API proxy
→ simple authorization path
→ API becomes bandwidth bottleneck

Direct object-storage upload
→ scalable
→ requires temporary signed upload authorization
```

### Synchronous vs asynchronous transcoding

``` text
Synchronous
→ simple conceptually
→ terrible latency / resource usage

Asynchronous queue
→ scalable + retryable
→ more infrastructure
```

### One video quality vs many

``` text
one rendition
→ cheaper processing/storage
→ poor experience across devices/networks

multiple renditions
→ more storage/compute
→ adaptive playback
```

### Origin-only vs CDN

``` text
origin only
→ simpler
→ high latency + bandwidth bottleneck

CDN
→ fast global playback
→ cache/invalidation complexity + cost
```

### Strong vs eventual consistency

``` text
video upload state
→ needs reliable state transitions

view count
→ eventual consistency is fine
```

------------------------------------------------------------------------

## 33. High-Level Final Architecture

``` text
                              CLIENTS
                                 │
                ┌────────────────┴────────────────┐
                │                                 │
              UPLOAD                           PLAYBACK
                │                                 │
                ▼                                 ▼
          API / Upload Service              Metadata API
                │                                 │
       signed upload URL                    Metadata Cache
                │                                 │
                ▼                                 ▼
             CLIENT                           Metadata DB
                │
                │ large multipart upload
                ▼
         RAW OBJECT STORAGE
                │
                ▼
         PROCESSING QUEUE
                │
         ┌──────┴───────┐
         ▼              ▼
    Transcoders    Thumbnail Workers
         │
         ▼
  PROCESSED OBJECT STORAGE
         │
         ▼
        CDN  <────────────────────────────── Viewer
         │
         └── cache miss → origin storage

Playback/View Events
         │
         ▼
    Event Stream
         │
         ▼
 Analytics / Counter Workers
         │
         ▼
 Analytics Store
```

------------------------------------------------------------------------

## 34. Interview Follow-Up Questions

Be prepared for:

-   Why shouldn't video bytes pass through application servers?
-   Why use object storage?
-   Why use presigned/signed upload URLs?
-   How do you support very large uploads?
-   What happens if an upload is interrupted?
-   Why is transcoding asynchronous?
-   Why do we need multiple resolutions?
-   What is adaptive bitrate streaming?
-   Why split videos into segments?
-   What does the playback manifest do?
-   Why is a CDN critical?
-   What happens on a CDN cache miss?
-   How do you handle a viral video?
-   Where should video metadata live?
-   How would you generate video IDs?
-   How do you retry failed transcoding?
-   How do you avoid duplicate processing?
-   How would you count views at massive scale?
-   Which data can be eventually consistent?
-   How would you handle private videos?
-   How would you add comments/search/recommendations?
-   How would live streaming change the architecture?

------------------------------------------------------------------------

## 35. Interview Version to Memorize

When asked **"Design YouTube"**, walk through:

``` text
1. Clarify scope
   → upload prerecorded video
   → process video
   → stream video

2. Requirements
   → durability
   → low playback latency
   → global scale

3. Separate metadata from video bytes
   → DB for metadata
   → object storage for media

4. Upload flow
   → create video
   → signed URL
   → multipart direct upload

5. Processing
   → queue
   → transcoding workers
   → multiple resolutions
   → segments + thumbnails

6. Playback
   → manifest
   → CDN
   → adaptive bitrate streaming

7. Scaling
   → stateless APIs
   → object storage
   → worker pools
   → CDN

8. Reliability
   → resumable uploads
   → retries
   → idempotent processing
   → replicated storage

9. Secondary features
   → async view counts
   → comments
   → search
   → recommendations
```

------------------------------------------------------------------------

## 36. Architecture to Keep in Your Head

``` text
UPLOAD:

Client
  ↓
API ──> Metadata DB
  ↓
signed URL
  ↓
Object Storage
  ↓
Queue
  ↓
Transcoders
  ↓
Processed Storage


PLAYBACK:

Viewer
  ↓
Metadata API
  ↓
manifest
  ↓
CDN
  ↓
video segments
```

The most important distinction:

``` text
CONTROL PLANE
API + metadata
→ small requests

DATA PLANE
uploads + video segments
→ huge bandwidth
→ object storage + CDN
```

------------------------------------------------------------------------

## 37. The 12 Things to Know Cold

1.  **Do not proxy huge video files through normal application
    servers.**
2.  Upload directly to **object storage** using temporary authorization.
3.  Use **multipart/resumable uploads** for large files.
4.  Store video bytes separately from **metadata**.
5.  Process videos **asynchronously**.
6.  A **queue** buffers work and decouples uploads from transcoding.
7.  **Transcode** into several resolutions/bitrates.
8.  Split renditions into **segments**.
9.  Use **adaptive bitrate streaming** for changing network conditions.
10. Serve video through a **CDN**, not directly from the application.
11. Make processing **retryable/idempotent**.
12. Treat high-volume counters such as views as
    **asynchronous/eventually consistent**.

------------------------------------------------------------------------

## 38. 60-Second Revision

**YouTube = upload pipeline + processing pipeline + global playback
pipeline.**

``` text
UPLOAD
Client
→ signed upload URL
→ object storage
→ processing queue
→ transcoding workers
→ multiple renditions
→ segmented output
→ object storage

PLAYBACK
Viewer
→ metadata/manifest
→ nearby CDN
→ video segments
→ adaptive bitrate player
```

Key reasoning:

``` text
large files
→ object storage

expensive processing
→ async workers + queue

different networks/devices
→ multiple renditions + ABR

global read-heavy bandwidth
→ CDN

huge view-event volume
→ asynchronous aggregation
```

If you can clearly explain **direct uploads, object storage, asynchronous transcoding, segmentation, adaptive bitrate streaming, and
CDN-backed playback**, you have the core of the Design YouTube interview problem.

***

# Design Google Drive --- System Design Interview Notes

## 1. Bullet-Point Overview

-   **Goal:** design cloud file storage with upload, download, folders,
    versions, sharing, and multi-device synchronization.
-   **Core split:** store structured **metadata** in a database and
    large **file bytes** in object/blob storage.
-   **Requirements:** durability, high availability, horizontal scale,
    efficient bandwidth use, secure access, and quick synchronization.
-   **Main entities:** `User`, `File`, `Folder`, `FileVersion`, `Chunk`,
    and `Permission`.
-   **Large files:** use chunked, multipart, and resumable uploads so
    failures do not restart a multi-GB transfer.
-   **Direct transfer:** at scale, clients should upload/download large
    bytes directly to/from object storage using temporary authorization
    rather than proxying all bytes through application servers.
-   **Versioning:** treat updates as new versions and keep a pointer to
    the current version.
-   **Chunking:** a file version can reference multiple chunks, enabling
    partial retries and potentially delta synchronization.
-   **Content hashes:** identify/verify chunks and can support
    deduplication.
-   **Delta sync:** if only some chunks changed, transfer those chunks
    rather than the entire file.
-   **Synchronization:** maintain a durable change log; devices keep a
    cursor indicating the last processed change.
-   **Notifications:** push tells a device that something changed; the
    durable change log remains the source of truth.
-   **Offline clients:** reconnect and request all changes after their
    saved cursor.
-   **Conflicts:** include the base version in an update and
    reject/detect the update if the current server version has advanced.
-   **Concurrency:** optimistic concurrency control works well because
    devices may edit offline for long periods.
-   **Sharing:** metadata/permission services enforce
    owner/editor/viewer access.
-   **Deletes:** use logical deletion/trash first, then background
    physical cleanup.
-   **Garbage collection:** remove chunks only when no live file/version
    references them.
-   **Caching:** cache metadata/folder listings carefully; authorization
    cache invalidation is security-sensitive.
-   **Durability:** replicate metadata and use highly durable
    replicated/erasure-coded object storage.
-   **Idempotency:** upload completion and metadata commits must
    tolerate retries without creating duplicate logical versions.
-   **Main interview insight:** Google Drive is **durable blob storage +
    metadata/versioning + synchronization**.

------------------------------------------------------------------------

## 2. Scope and Requirements

A Google Drive-like product includes many features. For an interview,
focus first on:

1.  upload/download arbitrary files,
2.  organize files/folders,
3.  update and version files,
4.  synchronize across devices,
5.  optionally share files.

Google Docs-style simultaneous collaborative text editing is a separate
problem unless explicitly requested.

### Functional requirements

-   Upload/download files.
-   Rename/move/delete files.
-   List folders.
-   Update files.
-   Synchronize changes.
-   Keep version history.
-   Share with other users.

### Non-functional requirements

-   Extremely high durability.
-   High availability.
-   Massive storage scalability.
-   Efficient large-file transfer.
-   Fast sync propagation.
-   Secure authorization.
-   Devices eventually converge on the same state.

------------------------------------------------------------------------

## 3. Data Model

Conceptual metadata:

``` text
File
--------------------------------
file_id
owner_id
parent_folder_id
name
size
current_version
created_at
updated_at
deleted
```

Versions:

``` text
FileVersion
--------------------------------
file_id
version_id
created_at
size
content_hash / manifest_reference
```

Permissions:

``` text
FilePermission
--------------------------------
file_id
user_id
role
```

Roles might include:

``` text
OWNER
EDITOR
VIEWER
```

A version can reference a chunk manifest:

``` text
Version 42
→ Chunk A
→ Chunk B
→ Chunk C
```

------------------------------------------------------------------------

## 4. Metadata vs File Bytes

This is the central storage decision.

``` text
Metadata DB
→ filename
→ owner
→ folder
→ current version
→ permissions
→ timestamps

Object Storage
→ actual file/chunk bytes
```

Why?

Metadata is small, structured, and queryable. Files may be gigabytes and
require cheap, durable, horizontally scalable binary storage.

Do not make the normal metadata database responsible for
petabytes/exabytes of file BLOBs.

------------------------------------------------------------------------

## 5. Basic APIs

Conceptually:

``` http
POST /files
```

Creates metadata/upload session.

``` http
GET /files/{file_id}
```

Gets metadata/download information after authorization.

``` http
PATCH /files/{file_id}
```

Renames/moves/updates metadata.

``` http
DELETE /files/{file_id}
```

Moves to trash/logically deletes.

``` http
GET /folders/{folder_id}/files
```

Lists a folder.

Synchronization:

``` http
GET /changes?cursor={last_cursor}
```

returns changes after the client's last synchronized position.

------------------------------------------------------------------------

## 6. Chunked and Resumable Uploads

Large files should be split:

``` text
File
 ├── Chunk 1
 ├── Chunk 2
 ├── Chunk 3
 └── Chunk N
```

A client creates an upload session:

``` text
upload_id = abc123
```

The system tracks completed chunks:

``` text
Chunk 1 ✓
Chunk 2 ✓
Chunk 3 ✗
Chunk 4 ✓
```

After a network failure, only missing chunks are retried.

Benefits: - resumability, - partial retries, - parallel transfer, -
better reliability for huge files.

------------------------------------------------------------------------

## 7. Direct-to-Object-Storage Transfer

Avoid:

``` text
Client → API Server → Object Storage
```

for every large byte at huge scale.

Prefer:

``` text
1. Client → API: create upload
2. API authenticates and creates upload session
3. API returns temporary signed upload authorization
4. Client → Object Storage: chunks
5. Client/service finalizes upload
6. Metadata points to completed content
```

The application controls authorization while the storage system handles
the bandwidth.

The same idea can be used for downloads.

------------------------------------------------------------------------

## 8. Chunk Size Trade-Off

Smaller chunks: - finer retry granularity, - better delta
synchronization/deduplication, - more requests and metadata.

Larger chunks: - fewer requests, - smaller manifests, - more
retransmission when a small part changes.

There is no universally correct size; explain the trade-off.

------------------------------------------------------------------------

## 9. Content Hashing and Deduplication

Calculate a content hash for each chunk:

``` text
chunk bytes
   ↓
hash()
   ↓
chunk_hash
```

Uses: - integrity verification, - detecting identical chunks, - finding
changed chunks, - optional deduplication.

With deduplication:

``` text
same hash
   ↓
one physical chunk
   ↓
multiple logical references
```

This saves storage but complicates reference tracking, privacy, and
garbage collection.

Treat it as an optimization, not a mandatory first step.

------------------------------------------------------------------------

## 10. File Versioning

Do not blindly overwrite the only file copy.

``` text
File 123

Version 1 → A B C
Version 2 → A B D
Version 3 → A E D
```

Metadata:

``` text
file_id = 123
current_version = 3
```

Advantages: - restore previous versions, - safer updates, - conflict
detection, - chunks can be reused between versions.

Only expose a new version as current once its required content is safely
stored.

------------------------------------------------------------------------

## 11. Delta Synchronization

Suppose:

``` text
Old version:
A B C D

New version:
A B X D
```

Only chunk `X` needs uploading/downloading.

Without delta sync, a tiny modification to a 2 GB file might require
retransferring 2 GB.

With chunk-level synchronization:

``` text
small change
→ changed chunks only
```

This saves bandwidth at the cost of more hashing, manifests, and client
complexity.

------------------------------------------------------------------------

## 12. Synchronization Across Devices

Alice has:

``` text
Laptop
Phone
Tablet
```

Laptop changes a file.

A robust architecture:

``` text
Laptop update
     ↓
Metadata Service
     ↓
Durable Change Log
     ↓
Notification Service
     ↓
Phone / Tablet
     ↓
GET changes after cursor
     ↓
download changed content
```

The notification is only a hint:

> Something changed --- synchronize.

The **change log** is the authoritative catch-up mechanism.

------------------------------------------------------------------------

## 13. Change Log and Cursor

Example:

``` text
1001 → file A updated
1002 → file B deleted
1003 → file C renamed
1004 → file A updated
```

A device stores:

``` text
last_cursor = 1002
```

After reconnecting:

``` text
GET changes?cursor=1002
```

returns:

``` text
1003
1004
```

This works even if notifications were lost or the device was offline for
days.

------------------------------------------------------------------------

## 14. Conflict Detection

Suppose both laptop and phone start from:

``` text
Version 10
```

Laptop uploads Version 11.

Later, the offline phone tries to upload an edit based on Version 10.

Request contains:

``` text
base_version = 10
```

but server has:

``` text
current_version = 11
```

Therefore:

``` text
base_version != current_version
→ conflict
```

Possible policies: - reject and refresh, - create a conflict copy, -
merge when the file format permits it.

For arbitrary binary files, automatic merging may be impossible.

------------------------------------------------------------------------

## 15. Optimistic Concurrency

Instead of locking a file while someone edits offline, use optimistic
concurrency.

Conceptually:

``` text
UPDATE file
SET current_version = 11
WHERE file_id = 123
AND current_version = 10
```

If the condition fails:

``` text
another update won
→ detect conflict
```

This is well suited to long-lived/offline editing.

------------------------------------------------------------------------

## 16. Download Flow

``` text
Client
  ↓
Metadata/API Service
  ├── authenticate
  ├── authorize
  └── resolve current version
  ↓
temporary download authorization
  ↓
Object Storage / CDN
  ↓
file bytes
```

Application servers remain on the control path rather than the heavy
data path.

------------------------------------------------------------------------

## 17. Folders

A simple hierarchy uses:

``` text
parent_folder_id
```

Example:

``` text
Root
 ├── Documents
 │    ├── report.pdf
 │    └── notes.txt
 └── Photos
      └── image.jpg
```

Listing a folder means efficiently finding:

``` text
objects WHERE parent_id = X
```

Metadata needs indexes around: - object/file ID, - owner, - parent
folder.

------------------------------------------------------------------------

## 18. Sharing and Permissions

Example:

``` text
File 123
Alice → OWNER
Bob   → EDITOR
Carol → VIEWER
```

Sharing modes could include: - private, - organization-only, -
anyone-with-link, - public.

Every read/write/download operation must enforce authorization.

Permissions belong in the metadata/control plane. Short-lived signed
storage URLs can be issued only after authorization succeeds.

------------------------------------------------------------------------

## 19. Deletes, Trash, and Garbage Collection

Prefer logical deletion first:

``` text
DELETE
  ↓
move to trash / mark deleted
  ↓
sync deletion
  ↓
retention period
  ↓
background physical cleanup
```

Versioning/deduplication means a chunk may have multiple references.

``` text
Chunk A
 ├── File 1 Version 2
 ├── File 1 Version 3
 └── File 9 Version 1
```

Never delete it just because one version disappeared.

Use reference tracking or mark-and-sweep-style garbage collection to
remove only unreferenced content.

------------------------------------------------------------------------

## 20. Metadata Database and Sharding

Metadata is structured and transactional, so a relational model is a
reasonable starting point.

At large scale, shard it.

Possible strategies:

``` text
owner_id
```

or:

``` text
hash(file_id)
```

Trade-off:

``` text
shard by owner
→ user's data often colocated
→ sharing can cross shards

shard by file ID
→ balanced distribution
→ user/folder listings need additional indexing
```

The interview goal is to explain the access pattern, not insist on one
technology.

------------------------------------------------------------------------

## 21. Caching

Cache candidates: - file metadata, - folder listings, - permissions, -
shared-file metadata.

But remember:

``` text
Cache
→ optimization

Metadata DB
→ authoritative structured state

Object Storage
→ authoritative file bytes
```

Permission caching requires especially careful invalidation because
stale authorization is a security problem.

------------------------------------------------------------------------

## 22. Durability and Availability

Cloud storage's core promise is that user files survive machine
failures.

Use: - durable object storage, - replication or erasure coding across
failure domains, - replicated metadata databases, - backups, - failover.

Notifications can fail without losing data because clients can recover
from the durable change log.

------------------------------------------------------------------------

## 23. Idempotency

Retries are normal.

Suppose:

``` text
finalize upload
```

succeeds, but the response is lost.

The client retries.

Without idempotency, the retry might create another version.

Use identifiers such as:

``` text
upload_id
request_id
version_id
```

so repeated requests produce the same logical result.

------------------------------------------------------------------------

## 24. Failure Scenarios

### Upload connection fails

Resume from uploaded chunks.

### One chunk fails

Retry only that chunk.

### Bytes upload successfully but metadata commit fails

Content may temporarily be unreferenced. Retry the commit; background
garbage collection can later clean abandoned content.

### Notification service fails

The update remains durable. Devices later query changes after their
cursors.

### Device is offline

On reconnect, request all changes after the saved cursor.

### Concurrent edits

Compare the client's base version with the current version and surface a
conflict.

### Storage machine fails

Replication/erasure coding should keep data available.

------------------------------------------------------------------------

## 25. Security

Important protections: - authentication, - per-file/folder
authorization, - encryption in transit, - encryption at rest, -
short-lived upload/download tokens, - audit logs for permission
changes, - upload validation where appropriate.

A signed storage URL should be: - short-lived, - scoped to the correct
object, - scoped to the intended operation.

------------------------------------------------------------------------

## 26. Capacity Thinking

Suppose:

``` text
100 million users
× 10 GB average
= 1 billion GB
≈ 1 exabyte
```

before: - replication, - old versions, - metadata overhead.

This immediately tells you: - ordinary database BLOB storage is
inappropriate, - blob/object storage must scale horizontally, - version
retention and deduplication materially affect cost.

Bandwidth is also huge, so file-transfer infrastructure must scale
separately from metadata APIs.

------------------------------------------------------------------------

## 27. High-Level Architecture

``` text
                              CLIENTS
                                 │
                   ┌─────────────┴─────────────┐
                   │                           │
             METADATA / SYNC               FILE DATA
                   │                           │
                   ▼                           ▼
              API GATEWAY             Upload / Download
                   │                   Authorization
                   ▼                           │
           METADATA SERVICE                   │
             │       │                        │
             ▼       ▼                        ▼
        Metadata DB Cache               Object Storage
             │                              ▲
             ▼                              │
         Change Log                         │
             │                              │
             ▼                              │
     Notification Service                   │
             │                              │
             ▼                              │
          Devices ─── changed chunks ───────┘
```

Optional:

``` text
Object Storage → CDN → frequent/public downloads
```

Background:

``` text
deleted versions / abandoned chunks
              ↓
      Garbage Collector
              ↓
       physical cleanup
```

------------------------------------------------------------------------

## 28. Important Trade-Offs

### Whole file vs chunks

``` text
Whole file
→ simple
→ poor resumability/delta sync

Chunks
→ resumable + delta sync + optional dedup
→ more metadata/complexity
```

### Polling vs push

``` text
Polling
→ reliable conceptually
→ wasteful

Push
→ low latency
→ can be lost

Practical design
→ push + durable change-log cursor
```

### Overwrite vs versions

``` text
Overwrite
→ simple
→ conflict/data-loss risk

Versioning
→ restore + conflict detection
→ more storage
```

### API proxy vs direct storage transfer

``` text
API proxy
→ simpler
→ bandwidth bottleneck

Direct storage
→ scalable
→ temporary authorization complexity
```

### Strong vs eventual consistency

``` text
current file version
→ stronger consistency

notifications
→ eventual consistency
```

------------------------------------------------------------------------

## 29. Interview Follow-Up Questions

Be ready for:

-   Why separate metadata from file bytes?
-   Why use object storage?
-   How would you upload a 100 GB file?
-   How do resumable uploads work?
-   Why chunk files?
-   How do you choose chunk size?
-   How does delta synchronization work?
-   What are chunk hashes used for?
-   How could deduplication work?
-   How does file versioning work?
-   How do two devices avoid overwriting each other?
-   Why use optimistic concurrency?
-   How do devices learn that something changed?
-   Why isn't push alone sufficient?
-   What is a change-log cursor?
-   How does an offline device catch up?
-   How would you shard metadata?
-   How do shared files complicate sharding?
-   How do permissions work?
-   How do trash/deletion semantics work?
-   How do you garbage-collect chunks safely?
-   Why must upload finalization be idempotent?
-   How do you guarantee file durability?
-   When is eventual consistency acceptable?
-   When would a CDN help?

------------------------------------------------------------------------

## 30. Interview Version to Memorize

When asked **"Design Google Drive"**:

``` text
1. Clarify scope
   → upload/download
   → folders
   → sync
   → versions

2. Separate storage
   → metadata DB
   → object storage

3. Upload
   → upload session
   → chunks
   → resumable/direct transfer
   → finalize

4. Versioning
   → immutable versions
   → current_version pointer

5. Synchronization
   → durable change log
   → push notification
   → cursor catch-up

6. Conflicts
   → base_version
   → optimistic concurrency

7. Efficiency
   → changed chunks only
   → hashes/dedup optionally

8. Scale
   → shard metadata
   → scalable object storage
   → metadata cache

9. Reliability
   → replication
   → retries/idempotency
   → garbage collection

10. Security
    → permissions
    → signed transfer URLs
    → encryption
```

------------------------------------------------------------------------

## 31. Architecture to Keep in Your Head

``` text
                    CLIENT
                      │
             ┌────────┴────────┐
             │                 │
          Metadata           File Bytes
             │                 │
             ▼                 ▼
       Metadata Service    Object Storage
             │
             ▼
        Metadata DB
             │
             ▼
         Change Log
             │
             ▼
      Notification Service
             │
             ▼
        Other Devices
```

Synchronization:

``` text
Device A modifies file
        ↓
upload changed chunks
        ↓
create new version
        ↓
commit metadata
        ↓
append change event
        ↓
notify Device B
        ↓
Device B requests changes after cursor
        ↓
download changed chunks
        ↓
devices converge
```

------------------------------------------------------------------------

## 32. The 12 Things to Know Cold

1.  **Separate metadata from file bytes.**
2.  Store file contents in durable **object/blob storage**.
3.  Use **chunked/resumable uploads** for large files.
4.  At scale, transfer large bytes directly between clients and storage.
5.  Use **versions** instead of blindly overwriting the only copy.
6.  Send a **base version** with updates to detect conflicts.
7.  Use **optimistic concurrency control**.
8.  Maintain a durable **change log** for synchronization.
9.  Push notifications trigger sync; they are **not the source of
    truth**.
10. Devices save a **cursor** and catch up after being offline.
11. Chunk hashes enable integrity checks and can support **delta
    sync/deduplication**.
12. Make retries **idempotent** and safely garbage-collect unreferenced
    data.

------------------------------------------------------------------------

## 33. 60-Second Revision

**Google Drive = durable blob storage + metadata/versioning +
synchronization.**

``` text
UPLOAD
Client
→ upload session
→ split into chunks
→ object storage
→ create file version
→ commit metadata

SYNC
metadata change
→ durable change log
→ notification
→ other device asks for changes after cursor
→ download changed chunks

DOWNLOAD
client
→ permission/metadata check
→ authorized object-storage/CDN transfer
```

Core reasoning:

``` text
large file bytes
→ object storage

unreliable/large transfers
→ chunks + resumability

updates
→ immutable versions + current-version pointer

multiple devices
→ change log + cursor + notification

concurrent writers
→ optimistic concurrency + conflict detection

small changes
→ delta/chunk synchronization

retries
→ idempotency
```

If you can explain **metadata vs blob storage, chunked uploads,
versioning, change-log synchronization, conflict detection, and delta
sync**, you have the core of the Design Google Drive interview problem.

***

# Design Google Maps --- System Design Interview Notes

## 1. Bullet-Point Overview

-   **Goal:** design map display, routing, navigation, and traffic-aware
    ETA.
-   Narrow scope first; Street View, transit, reviews, satellite
    imagery, and full place discovery can be follow-ups.
-   Model roads as a **directed weighted graph**.
-   **Nodes** represent intersections; **edges** represent road
    segments.
-   Edge weights should usually model **estimated travel time**, not
    only distance.
-   **Dijkstra** is the shortest-path foundation; **A\*** adds a
    destination heuristic to reduce exploration.
-   Planet-scale routing also needs **geographic partitioning,
    hierarchy, and precomputation**.
-   Long routes can use local streets near endpoints and a smaller
    major-road/highway graph in between.
-   Use spatial indexing such as **geohash, quadtree, R-tree, or S2-like
    cells** to locate nearby geographic data.
-   Render maps as **tiles** at multiple zoom levels so clients fetch
    only the visible area.
-   Serve tiles through a **CDN** because map geometry is highly
    cacheable.
-   Separate relatively static **road topology** from dynamic **traffic
    data**.
-   Traffic changes road-segment travel-time weights without rebuilding
    the whole graph.
-   Aggregate GPS/sensor observations through a streaming pipeline to
    estimate road speeds.
-   Use **map matching** to associate noisy GPS coordinates with likely
    road segments.
-   During navigation, track progress locally and reroute only after a
    meaningful deviation.
-   Place search uses a search index plus geographic filtering/ranking.
-   Dense cities are **hot geographic regions**; replicate/subdivide
    them instead of assuming equal load per region.
-   If live traffic fails, fall back to recent/historical/default speeds
    rather than losing routing.
-   Core mental model: **geospatial indexing + graph routing + map
    tiles/CDN + dynamic traffic weights**.

------------------------------------------------------------------------

## 2. Requirements

### Functional

-   Display maps for a viewport.
-   Resolve/search locations.
-   Calculate directions from origin to destination.
-   Return route geometry and turn instructions.
-   Estimate travel time.
-   Track navigation progress.
-   Reroute when necessary.
-   Incorporate current traffic.

### Non-functional

-   Low route latency.
-   High availability.
-   Global scalability.
-   Reasonable route/ETA accuracy.
-   Fresh traffic information.
-   Efficient mobile bandwidth.
-   Graceful degradation when dynamic data is stale.

------------------------------------------------------------------------

## 3. Road Network as a Graph

``` text
Intersection → Node
Road segment → Edge
```

Example:

``` text
A ----5---- B
|           |
2           3
|           |
C ----4---- D
```

An edge can store:

``` text
road_id
start_node
end_node
distance
speed_limit
road_type
one_way
toll
restrictions
geometry
```

Roads are often directed:

``` text
A ───────> B
```

Even a two-way road can have different costs:

``` text
A → B = 2 min
B → A = 7 min
```

because traffic can differ by direction.

------------------------------------------------------------------------

## 4. Routing --- Dijkstra and A\*

The routing problem is:

> Find the minimum-cost path from origin S to destination T.

Dijkstra works with non-negative edge weights but may explore broadly.

A\* prioritizes:

``` text
cost_so_far
+
estimated_remaining_cost
```

A geographic heuristic such as straight-line distance helps guide search
toward the destination.

``` text
Dijkstra → broad exploration
A*       → destination-directed exploration
```

Important: **A\* alone does not solve planet-scale routing.** The graph
must also be partitioned and/or hierarchically preprocessed.

------------------------------------------------------------------------

## 5. Hierarchical Routing

Roads naturally have importance levels:

``` text
local streets
   ↓
arterials
   ↓
highways
   ↓
major highways
```

A long trip can be treated conceptually as:

``` text
Origin
  ↓
local graph
  ↓
major-road/highway graph
  ↓
local graph
  ↓
Destination
```

This prevents the routing engine from examining every residential street
between distant cities.

Offline preprocessing can calculate useful shortcuts/connectivity so
online requests search much smaller structures.

------------------------------------------------------------------------

## 6. Geographic Partitioning

Do not put the entire world road graph on one server.

``` text
World
 ├── Region A
 ├── Region B
 ├── Region C
 └── Region D
```

Each region stores a subset of the graph.

A cross-region route uses higher-level boundary/highway connections.

Benefits: - less memory per routing worker, - smaller search space, -
independent scaling, - smaller failure domains.

Challenge: routes frequently cross partition boundaries, so partitions
need known connections to neighboring/high-level graph data.

------------------------------------------------------------------------

## 7. Spatial Indexes

Queries such as:

``` text
Which roads are near this coordinate?
Which places are in this viewport?
```

cannot scan all geographic objects.

Use a spatial hierarchy/index:

``` text
(latitude, longitude)
        ↓
cell / region
        ↓
small candidate set
```

Common concepts: - geohash, - quadtree, - R-tree, - S2-like cells.

### Geohash intuition

Nearby locations often share prefixes:

``` text
u2yh...
u2yh...
```

Longer prefixes represent smaller areas.

### Quadtree intuition

``` text
┌───────────────┐
│       │       │
│   1   │   2   │
│───────┼───────│
│   3   │   4   │
│       │       │
└───────────────┘
```

Dense areas can subdivide further, which is useful because Manhattan and
a desert should not receive identical spatial granularity.

------------------------------------------------------------------------

## 8. Map Tiles

The client should not download the entire world's map geometry.

Split map data into tiles:

``` text
┌────┬────┬────┐
│ T1 │ T2 │ T3 │
├────┼────┼────┤
│ T4 │ T5 │ T6 │
├────┼────┼────┤
│ T7 │ T8 │ T9 │
└────┴────┴────┘
```

Conceptual API:

``` http
GET /tiles/{zoom}/{x}/{y}
```

The client requests only tiles intersecting the viewport.

Higher zoom levels provide more detailed/smaller geographic areas.

Tiles can be raster images or vector data.

------------------------------------------------------------------------

## 9. CDN for Tiles

Tiles are excellent cache candidates.

``` text
Client
  ↓
CDN
  ├── HIT → return tile
  └── MISS
        ↓
    Tile Storage
        ↓
       CDN
        ↓
      Client
```

Benefits: - low latency, - reduced origin traffic, - global
distribution, - excellent reuse for popular regions.

Static road/map geometry changes much less frequently than user traffic,
so it can be cached aggressively.

------------------------------------------------------------------------

## 10. Static vs Dynamic Data

Separate:

### Relatively static

-   roads,
-   intersections,
-   geometry,
-   names,
-   speed limits,
-   turn restrictions.

### Dynamic

-   congestion,
-   closures,
-   incidents,
-   temporary restrictions.

Conceptually:

``` text
Static Road Graph
       +
Traffic Overlay
       ↓
Effective Routing Costs
```

This avoids rebuilding the graph whenever traffic changes.

------------------------------------------------------------------------

## 11. Traffic-Aware Routing

For driving, use estimated travel time:

``` text
edge_weight = estimated_travel_time
```

Example:

``` text
10 km highway

normal speed: 100 km/h
→ ~6 min

traffic speed: 20 km/h
→ ~30 min
```

A physically longer alternative can therefore become the fastest route.

------------------------------------------------------------------------

## 12. Traffic Data Pipeline

Possible sources: - aggregated device movement, - road sensors, - public
feeds, - incidents, - historical traffic.

Architecture:

``` text
GPS / Sensor Events
        ↓
     Ingestion
        ↓
   Stream / Queue
        ↓
   Aggregators
        ↓
Road-Segment Speeds
        ↓
   Traffic Store
        ↓
 Routing Service
```

Traffic is a streaming/aggregation workload and should scale separately
from map-tile and routing workloads.

------------------------------------------------------------------------

## 13. Map Matching

GPS is noisy.

A coordinate may appear beside the road even though the user is driving
on it.

Map matching estimates the most likely road segment using information
such as: - coordinate, - previous position, - heading, - speed, - nearby
road candidates.

``` text
GPS sample
   ↓
spatial lookup
   ↓
candidate roads
   ↓
most likely road segment
```

This improves navigation progress, traffic estimates, and rerouting.

------------------------------------------------------------------------

## 14. Navigation Flow

Initial route:

``` text
Client
  ↓
origin + destination
  ↓
Routing Service
  ├── map endpoints to graph
  ├── load relevant graph
  ├── apply traffic costs
  └── calculate route
  ↓
route geometry + instructions + ETA
```

During navigation:

``` text
GPS update
   ↓
map match
   ↓
compare with route
   ↓
on route?
 /       \
yes       no
│          │
continue   reroute
```

Do not reroute for tiny deviations because GPS noise would cause
excessive recalculation.

------------------------------------------------------------------------

## 15. Client vs Server

### Server

-   global graph data,
-   expensive route computation,
-   traffic aggregation,
-   place search,
-   route alternatives.

### Client

-   map rendering,
-   nearby tile caching,
-   GPS collection,
-   instruction display,
-   route-progress tracking,
-   deciding when a meaningful reroute request is needed.

This keeps navigation responsive without a server request for every
meter traveled.

------------------------------------------------------------------------

## 16. ETA

Basic ETA:

``` text
ETA = sum(travel time of route edges)
```

Real estimates may incorporate: - live traffic, - historical traffic by
time/day, - road class, - intersections, - incidents, - weather.

For the interview, the important part is where these signals influence
edge travel-time estimates.

------------------------------------------------------------------------

## 17. Place Search and Geocoding

Place search may rank candidates by:

``` text
text relevance
+
distance
+
popularity
```

Place record:

``` text
place_id
name
address
lat
lng
category
metadata
```

A search index handles text; a spatial index narrows geographic
candidates.

Geocoding:

``` text
address → latitude/longitude
```

Reverse geocoding:

``` text
latitude/longitude → address
```

Routing ultimately needs coordinates/graph nodes, not free-form address
text.

------------------------------------------------------------------------

## 18. Hot Geographic Regions

Load is uneven:

``` text
Tokyo / London / NYC
→ huge request volume

rural area
→ much lower volume
```

Mitigations: - subdivide dense regions, - replicate hot graph/index
partitions, - cache aggressively, - autoscale services, - use adaptive
spatial partitioning.

This is why fixed equal-size partitions may be insufficient.

------------------------------------------------------------------------

## 19. Cross-Region Routes

For a long trip:

``` text
Origin local graph
       ↓
regional / highway graph
       ↓
cross-region connections
       ↓
destination local graph
```

The high-level graph handles long-distance travel while detailed local
graphs handle endpoint streets.

This dramatically reduces search work.

------------------------------------------------------------------------

## 20. Precomputation

Road topology changes slowly enough to precompute: - graph
connectivity, - hierarchy/shortcuts, - major-road structures, - map
tiles, - indexes.

At request time combine:

``` text
precomputed graph structures
+
origin/destination
+
current traffic overlay
        ↓
fast route
```

Move expensive stable computation offline whenever possible.

------------------------------------------------------------------------

## 21. Map Data Updates

Roads change:

``` text
new road
closure
new turn restriction
speed-limit change
```

Pipeline:

``` text
Map Data Sources
      ↓
Validation / Processing
      ↓
Versioned Map Dataset
      ├──> Routing Graph
      └──> Tile Generation
```

Versioning enables controlled rollout and rollback.

------------------------------------------------------------------------

## 22. Caching

Good candidates: - map tiles, - place metadata, - stable road graph
partitions, - precomputed routing structures, - popular search
results, - some route components.

Exact route caching is harder because results depend on: - precise
endpoints, - traffic, - route preferences.

Cache stable inputs aggressively and layer dynamic traffic on top.

------------------------------------------------------------------------

## 23. Failure Handling

### Live traffic unavailable

Use recent/historical/default travel times.

### Tile origin unavailable

CDN may still serve cached tiles.

### Routing worker fails

Use stateless replicated workers behind a load balancer.

### Regional graph server fails

Use replicated regional data.

### GPS temporarily unavailable

Route can remain displayed; live progress degrades until location
returns.

Principle:

> Slightly stale traffic is usually preferable to no route.

------------------------------------------------------------------------

## 24. Consistency and Freshness

Different data needs different guarantees:

``` text
Road topology
→ durable + versioned

Map tiles
→ aggressively cached

Traffic
→ dynamic + eventually consistent

GPS
→ fresh + ephemeral
```

Do not impose the same consistency model on all data.

------------------------------------------------------------------------

## 25. Privacy

Location data is sensitive.

A production design should: - authenticate requests, - encrypt
traffic, - minimize unnecessary collection, - aggregate/anonymize
traffic observations where appropriate, - use retention policies, -
avoid unnecessarily coupling traffic measurements to user identity.

Mention this when discussing crowdsourced traffic.

------------------------------------------------------------------------

## 26. Capacity Thinking

Google Maps has several distinct high-scale workloads:

``` text
tile requests
route requests
place searches
traffic events
road graph storage
tile storage
```

They scale differently:

``` text
Tiles
→ CDN/cache/bandwidth heavy

Routing
→ CPU + graph-search heavy

Traffic
→ streaming/aggregation heavy

Search
→ index/query heavy
```

Design them as independently scalable subsystems.

------------------------------------------------------------------------

## 27. High-Level Architecture

``` text
                              CLIENTS
                                 │
             ┌───────────────────┼───────────────────┐
             │                   │                   │
             ▼                   ▼                   ▼
         MAP TILES            ROUTING            SEARCH
             │                   │                   │
             ▼                   ▼                   ▼
            CDN           Routing Service      Search Service
             │             │          │              │
             ▼             ▼          ▼              ▼
       Tile Storage    Road Graph   Traffic       Place Index
                                     Store
```

Traffic:

``` text
Device / Sensor Events
         ↓
      Ingestion
         ↓
   Stream / Queue
         ↓
    Aggregators
         ↓
 Road Segment Speeds
         ↓
    Traffic Store
         ↓
   Routing Service
```

Offline map-data pipeline:

``` text
Map Data Sources
       ↓
Validation / Processing
       ↓
Versioned Road Graph
       ├──> Routing Storage
       └──> Tile Generation → Tile Storage/CDN
```

------------------------------------------------------------------------

## 28. Important Trade-Offs

### Dijkstra vs A\*

``` text
Dijkstra
→ general shortest path
→ broader exploration

A*
→ destination heuristic
→ typically explores less
```

### Static vs dynamic weights

``` text
Static
→ simple and stable
→ weaker ETA

Traffic-aware
→ better route/ETA
→ streaming and freshness complexity
```

### Global graph vs partitions

``` text
One global graph
→ simple abstraction
→ not scalable on one machine

Geographic partitions
→ scalable
→ cross-region coordination
```

### Uniform vs adaptive spatial partitioning

``` text
Uniform
→ simpler
→ dense cities become hot

Adaptive
→ handles uneven density
→ more complex
```

### Server-heavy vs client-heavy navigation

``` text
Server handles every update
→ centralized
→ excessive round trips

Client handles rendering/progress
→ responsive
→ more client logic
```

------------------------------------------------------------------------

## 29. Interview Follow-Up Questions

Be ready for:

-   How do you model roads?
-   Why use a directed weighted graph?
-   What should edge weights represent?
-   Dijkstra vs A*: why A*?
-   Why is A\* insufficient by itself at world scale?
-   What is hierarchical routing?
-   How do you partition the road graph?
-   How do cross-region routes work?
-   What is a geohash/quadtree?
-   How do you find nearby roads or places?
-   Why use map tiles?
-   Why use a CDN?
-   How is traffic incorporated into routes?
-   How would you aggregate traffic observations?
-   What is map matching?
-   How do you handle noisy GPS?
-   When should the client reroute?
-   How is ETA calculated?
-   How do you handle hot cities?
-   What routing/map information can be precomputed?
-   What happens when live traffic is unavailable?
-   How do you roll out road-map updates?
-   How would offline maps work?
-   How do you protect location privacy?

------------------------------------------------------------------------

## 30. Interview Version to Memorize

When asked **"Design Google Maps"**:

``` text
1. Clarify scope
   → map
   → route
   → navigation
   → traffic

2. Road model
   → directed weighted graph
   → intersections = nodes
   → road segments = edges

3. Routing
   → A*
   → travel-time weights

4. Scale
   → geographic partitions
   → hierarchical/highway graph
   → preprocessing

5. Rendering
   → hierarchical map tiles
   → CDN

6. Spatial lookup
   → geohash/quadtree-like index

7. Traffic
   → GPS/sensor events
   → streaming aggregation
   → dynamic edge weights

8. Navigation
   → GPS
   → map matching
   → progress
   → rerouting

9. Reliability
   → replicated static graph
   → cached tiles
   → fallback traffic estimates
```

------------------------------------------------------------------------

## 31. Architecture to Keep in Your Head

``` text
                         CLIENT
                           │
          ┌────────────────┼────────────────┐
          │                │                │
          ▼                ▼                ▼
       Tiles            Route            Search
          │                │                │
         CDN         Routing Service   Place Index
                           │
                    ┌──────┴──────┐
                    ▼             ▼
                Road Graph     Traffic
```

Navigation:

``` text
GPS
 ↓
map matching
 ↓
route position
 ↓
off route?
 /     \
no     yes
│       │
go on  reroute
```

------------------------------------------------------------------------

## 32. The 12 Things to Know Cold

1.  Roads form a **directed weighted graph**.
2.  Nodes = intersections; edges = road segments.
3.  Driving edge weights should represent **travel time**.
4.  **A\*** reduces search using a destination heuristic.
5.  Global routing also needs **hierarchy/precomputation**.
6.  Partition map/road data **geographically**.
7.  Use **spatial indexes** for nearby-data queries.
8.  Render maps as hierarchical **tiles**.
9.  Serve tiles through a **CDN**.
10. Apply **dynamic traffic weights** over a relatively static graph.
11. Use **map matching** for noisy GPS.
12. If traffic fails, fall back to recent/historical/static costs.

------------------------------------------------------------------------

## 33. 60-Second Revision

**Google Maps = geospatial indexing + graph routing + map tiles + live
traffic.**

``` text
MAP
viewport
→ tile IDs
→ CDN
→ map tiles

ROUTE
origin/destination
→ graph nodes
→ A* + hierarchy
→ traffic-aware weights
→ route + ETA

NAVIGATION
GPS
→ map matching
→ route progress
→ reroute

TRAFFIC
GPS/sensors
→ stream
→ aggregate by road segment
→ travel-time estimates
→ routing
```

Core reasoning:

``` text
world-scale road network
→ geographic partitioning

fast shortest path
→ A* + hierarchy/precomputation

mobile map display
→ tiles + CDN

changing congestion
→ dynamic edge-weight overlay

noisy location
→ map matching

long-distance route
→ local graph + high-level graph + local graph
```

If you can explain \*\*the road graph, A\*, geographic partitioning,
hierarchical routing, tiles/CDN, spatial indexes, traffic aggregation,
and map matching\*\*, you have the core of the Design Google Maps
system-design problem.

***

# Design a Key-Value Store --- System Design Interview Notes

## 1. Quick Overview --- Bullet Points

-   A **key-value store** is a non-relational database that maps a
    unique key to an opaque value.
-   Core operations are usually:
  -   `PUT(key, value)`
  -   `GET(key)`
  -   optionally `DELETE(key)`.
-   Start with a single-machine hash table, then explain why large
    datasets require a **distributed key-value store**.
-   Important requirements:
  -   low latency,
  -   horizontal scalability,
  -   high availability,
  -   durability,
  -   fault tolerance,
  -   configurable/tunable consistency.
-   **CAP theorem:** because network partitions are unavoidable,
    distributed systems must make trade-offs between consistency and
    availability during a partition.
-   **Partitioning:** distribute keys across machines so storage and
    traffic scale horizontally.
-   **Consistent hashing:** minimizes how many keys move when nodes are
    added or removed.
-   **Virtual nodes:** give each physical node multiple positions on the
    hash ring to improve balance and support heterogeneous server
    capacities.
-   **Replication:** store each key on multiple nodes so a machine
    failure does not make data unavailable.
-   **Replication factor `N`:** number of replicas storing a key.
-   **Write quorum `W`:** number of replicas that must acknowledge a
    write.
-   **Read quorum `R`:** number of replicas consulted/required for a
    read.
-   Larger `R`/`W` can improve consistency but increase latency or
    reduce availability.
-   **Eventual consistency:** replicas may temporarily disagree but
    converge when updates propagate.
-   **Versioning:** attach version information to values to detect
    concurrent/conflicting writes.
-   **Vector clocks/version vectors:** one conceptual mechanism for
    detecting whether versions are causally related or conflicting.
-   **Conflict resolution:** may happen automatically, through
    last-write-wins, or at the application/client layer depending on
    requirements.
-   **Failure detection:** distributed nodes need to determine which
    peers appear alive/unavailable.
-   **Gossip protocol:** nodes exchange membership/failure information
    without relying on one central coordinator.
-   **Temporary failure:** use mechanisms such as hinted handoff/sloppy
    quorum so another node can temporarily accept replicas.
-   **Permanent failure:** repair missing/outdated replicas using
    synchronization techniques such as Merkle trees.
-   **Write path:** commonly append to a durable commit/WAL log, update
    an in-memory structure, then flush immutable sorted files to disk.
-   **Read path:** check memory/cache first, then locate relevant disk
    files; Bloom filters can avoid unnecessary disk reads.
-   **SSTables:** immutable sorted on-disk files commonly associated
    with LSM-tree storage engines.
-   **Compaction:** merges SSTables, removes obsolete
    versions/tombstones, and controls read/storage amplification.
-   **Bloom filter:** probabilistically tells us that a key is
    definitely absent or possibly present.
-   **Tombstone:** a deletion marker that propagates through
    replicas/storage files before old data can be physically removed.
-   Key interview insight: a distributed key-value store is
    fundamentally about **partitioning + replication + consistency +
    failure handling + an efficient storage engine**.

------------------------------------------------------------------------

## 2. Requirements

### Functional requirements

At minimum:

``` text
PUT(key, value)
GET(key)
```

Often also:

``` text
DELETE(key)
```

Possible extensions: - TTL/expiration, - conditional writes, - batch
operations, - configurable consistency levels.

Values are normally treated as opaque bytes/objects. The key-value store
does not need to understand the internal structure of every value.

### Non-functional requirements

Typical interview goals:

-   very low read/write latency,
-   large total dataset,
-   high availability,
-   horizontal scalability,
-   automatic node addition/removal,
-   durability,
-   fault tolerance,
-   tunable consistency.

Before choosing architecture, clarify whether the workload prioritizes:

``` text
consistency
or
availability
```

and:

``` text
read latency
or
write latency
```

------------------------------------------------------------------------

## 3. Start with a Single Server

The simplest implementation is:

``` text
key → hash table → value
```

Example:

``` text
{
    "user:123": {...},
    "session:abc": {...}
}
```

Average lookup:

``` text
O(1)
```

An in-memory hash table is extremely fast.

But one machine has limited: - RAM, - disk, - CPU, - network bandwidth.

Possible temporary optimizations: - compression, - keep hot data in
memory, - move cold data to disk.

Eventually, one server is insufficient.

That leads to a distributed design.

------------------------------------------------------------------------

## 4. Distributed Key-Value Store

Conceptually:

``` text
                 Client
                   │
                   ▼
             Coordinator
                   │
          ┌────────┼────────┐
          ▼        ▼        ▼
        Node A   Node B   Node C
```

The coordinator does not necessarily need to be a special permanent
machine.

Any node may accept a request and route it to the nodes responsible for
the key.

A decentralized architecture avoids creating a single point of failure.

------------------------------------------------------------------------

## 5. CAP Theorem

CAP describes three properties:

``` text
C = Consistency
A = Availability
P = Partition tolerance
```

### Consistency

Every read observes the latest accepted write, according to the system's
consistency model.

### Availability

Every request to a healthy/reachable part of the system receives a
response.

### Partition tolerance

The system continues operating despite network communication failures
between groups of nodes.

In real distributed systems:

``` text
network partitions can happen
→ P is unavoidable
```

So during a partition the important trade-off becomes:

``` text
CP
→ favor consistency
→ some requests may fail/block

AP
→ favor availability
→ some reads may temporarily be stale
```

The correct choice depends on product requirements.

------------------------------------------------------------------------

## 6. Data Partitioning

A single node cannot store every key.

We need:

``` text
key
 ↓
partition function
 ↓
responsible node
```

Naive approach:

``` text
hash(key) % number_of_servers
```

Example:

``` text
hash("user:123") % 4
→ server 2
```

Problem:

If the number of servers changes:

``` text
% 4
→ % 5
```

many keys map to different servers.

This creates massive data movement.

------------------------------------------------------------------------

## 7. Consistent Hashing

Consistent hashing maps both: - nodes, - keys

onto a logical ring.

``` text
          Node A
       /          \
    keys          Node B
     |              |
    Node D        keys
       \          /
          Node C
```

To find a key's owner:

``` text
hash(key)
   ↓
position on ring
   ↓
walk clockwise
   ↓
first node
```

Main advantage:

When a node is added or removed, only a fraction of keys need to move.

``` text
normal modulo hashing
→ huge remapping

consistent hashing
→ localized remapping
```

This makes horizontal scaling much easier.

------------------------------------------------------------------------

## 8. Virtual Nodes

With one position per physical server, data distribution may be uneven.

Instead:

``` text
Server A
→ A1
→ A2
→ A3

Server B
→ B1
→ B2
→ B3
```

Each physical server owns many **virtual nodes** on the ring.

Benefits: - better load distribution, - easier rebalancing, - servers
with more capacity can own more virtual nodes, - failed-node ranges can
be spread among multiple surviving machines.

------------------------------------------------------------------------

## 9. Replication

Partitioning solves capacity.

It does not solve failure.

If:

``` text
Key K → Node A
```

and Node A dies, the key becomes unavailable.

So replicate data.

For replication factor:

``` text
N = 3
```

a key might exist on:

``` text
Node A
Node B
Node C
```

Prefer replicas across independent failure domains such as different
racks or availability zones.

Replication improves: - availability, - durability, - fault tolerance.

But it introduces:

``` text
replica consistency
```

as a new problem.

------------------------------------------------------------------------

## 10. Quorums

Define:

``` text
N = number of replicas
W = write acknowledgements required
R = read responses required
```

Example:

``` text
N = 3
W = 2
R = 2
```

Write:

``` text
Client
  ↓
Coordinator
  ├── Replica A ✓
  ├── Replica B ✓
  └── Replica C ...
```

Once `W=2` replicas acknowledge, the write can succeed.

Read:

``` text
Coordinator
  ├── Replica A
  └── Replica B
```

Compare returned versions and select/reconcile the newest valid value.

General intuition:

``` text
larger W
→ stronger write durability/consistency
→ higher write latency

larger R
→ more confidence in fresh reads
→ higher read latency
```

A common quorum relationship discussed in interviews is:

``` text
R + W > N
```

which creates overlap between read and write quorums under the assumed
quorum model.

But consistency guarantees also depend on the replication protocol and
failure model---not just the arithmetic.

------------------------------------------------------------------------

## 11. Tunable Consistency

Different applications want different trade-offs.

### Fast writes

``` text
W = 1
```

Write succeeds after one replica.

Pros: - low write latency, - better availability.

Cons: - other replicas may temporarily be stale.

### Stronger write acknowledgement

``` text
W = majority
```

Pros: - more replicas have accepted the write.

Cons: - slower, - more sensitive to replica failures.

### Fast reads

``` text
R = 1
```

Pros: - low read latency.

Cons: - selected replica may be stale in an eventually consistent
design.

The store can expose consistency levels to callers.

------------------------------------------------------------------------

## 12. Eventual Consistency

With asynchronous replication:

``` text
PUT K=42
```

may initially produce:

``` text
Replica A → 42
Replica B → 42
Replica C → old value
```

After propagation/repair:

``` text
Replica A → 42
Replica B → 42
Replica C → 42
```

This is eventual consistency:

> If no new updates occur, replicas eventually converge.

It can provide high availability and low latency, but clients may
temporarily observe stale values.

------------------------------------------------------------------------

## 13. Concurrent Writes

Suppose two disconnected replicas accept writes:

``` text
Replica A:
K = "red"

Replica B:
K = "blue"
```

When connectivity returns, neither update may clearly be "after" the
other.

This is a conflict.

We need version information to distinguish:

``` text
older version
newer version
concurrent versions
```

------------------------------------------------------------------------

## 14. Versioning and Vector Clocks

A simple timestamp can be problematic because clocks across machines are
not perfectly synchronized.

A conceptual vector clock/version vector records logical progress per
writer/node.

Example:

``` text
Version A:
[A:2, B:1]

Version B:
[A:2, B:2]
```

B causally follows A because its counters dominate.

But:

``` text
[A:3, B:1]
[A:2, B:2]
```

may represent concurrent branches.

The system can then recognize:

``` text
conflict
```

rather than silently assuming one write happened after the other.

------------------------------------------------------------------------

## 15. Conflict Resolution

Possible approaches:

### Last-write-wins

``` text
latest timestamp/version
→ winner
```

Simple, but may discard valid concurrent updates.

### Application/client resolution

Return multiple conflicting versions:

``` text
["red", "blue"]
```

and let application logic merge them.

Useful when the application understands the value semantics better than
the storage system.

### Automatic merge

Possible for certain data types, but not for arbitrary opaque values.

Interview point:

> Conflict detection and conflict resolution are different problems.

------------------------------------------------------------------------

## 16. Failure Detection

Nodes need to know which peers appear unavailable.

A centralized health monitor can become a single point of failure.

A decentralized design can use **gossip**.

Conceptually:

``` text
A tells B what A knows
B tells C what B knows
C tells D ...
```

Nodes periodically exchange: - membership, - heartbeat/version
information, - suspected failures.

Eventually the cluster converges on a similar view of membership.

One missed heartbeat should not immediately mean:

``` text
node permanently dead
```

because networks can be temporarily slow.

------------------------------------------------------------------------

## 17. Temporary Failures

Suppose Node B owns a replica but is temporarily unreachable.

Instead of failing the write, an availability-oriented design can
temporarily store B's replica elsewhere.

Conceptually:

``` text
intended:
A, B, C

B unavailable

temporary:
A, C, D
```

Node D keeps a hint:

``` text
"this copy belongs to B"
```

When B returns:

``` text
D → transfer data → B
```

This pattern is commonly called **hinted handoff**.

It favors availability during temporary failures.

------------------------------------------------------------------------

## 18. Permanent Failures and Replica Repair

A node may permanently lose data.

Replicas must compare datasets and repair missing/outdated ranges.

Comparing every key directly is expensive.

A **Merkle tree** summarizes data hierarchically using hashes.

``` text
              Root Hash
              /       \
          Hash A      Hash B
          /   \       /   \
        ...   ...   ...   ...
```

If root hashes differ:

``` text
compare child hashes
```

Continue downward only through differing branches.

This efficiently identifies which ranges require synchronization.

------------------------------------------------------------------------

## 19. Write Path

A common disk-backed key-value storage engine uses an LSM-style write
path.

``` text
PUT(key, value)
      ↓
Commit / Write-Ahead Log
      ↓
Memtable
      ↓
Memtable becomes full
      ↓
Flush
      ↓
SSTable on disk
```

### Step 1 --- WAL / commit log

Append the write to durable sequential storage.

Purpose:

``` text
crash recovery
```

### Step 2 --- Memtable

Update an in-memory sorted structure.

Reads can immediately see recent writes.

### Step 3 --- Flush

When the memtable reaches a threshold:

``` text
memtable
→ immutable
→ write SSTable
```

Sequential disk writes are efficient.

------------------------------------------------------------------------

## 20. SSTables

SSTable means roughly:

``` text
Sorted String Table
```

It is an immutable sorted file containing key-value entries.

Example:

``` text
apple   → ...
banana  → ...
car     → ...
dog     → ...
```

Over time a node may have:

``` text
Memtable
SSTable 5
SSTable 4
SSTable 3
...
```

Because SSTables are immutable, updates create newer entries rather than
modifying old files in place.

This requires compaction later.

------------------------------------------------------------------------

## 21. Compaction

Without compaction:

``` text
K=1 in SSTable A
K=2 in SSTable B
K=3 in SSTable C
```

Old versions remain on disk.

Compaction:

``` text
SSTable A
SSTable B
SSTable C
     ↓
 merge
     ↓
new compacted SSTable
```

During compaction: - keep newer versions, - discard obsolete values when
safe, - remove expired/tombstoned data when safe, - reorganize files.

Benefits: - lower disk usage, - fewer files to search, - improved read
performance.

Costs: - background disk I/O, - CPU, - write amplification.

------------------------------------------------------------------------

## 22. Read Path

Conceptual read:

``` text
GET(key)
   ↓
Memtable?
   │
   ├── yes → return
   │
   └── no
        ↓
Cache?
        ↓
Relevant SSTables
        ↓
find newest valid version
        ↓
return
```

Searching every SSTable would be expensive.

This is where: - indexes, - Bloom filters, - block caches

help.

------------------------------------------------------------------------

## 23. Bloom Filters

A Bloom filter answers:

``` text
"Could this SSTable contain key K?"
```

Results:

``` text
definitely not
or
possibly yes
```

It can have:

``` text
false positives
```

but not false negatives under normal correct operation.

So:

``` text
Bloom says NO
→ skip disk file

Bloom says MAYBE
→ inspect file/index
```

This prevents many unnecessary disk reads.

------------------------------------------------------------------------

## 24. Deletes and Tombstones

In an immutable LSM-style store, deletion cannot simply erase every old
copy immediately.

Instead:

``` text
DELETE(K)
```

creates:

``` text
K → TOMBSTONE
```

The tombstone is replicated and written like another version.

Reads encountering the newest tombstone treat the key as deleted.

Later, compaction can physically remove: - the tombstone, - older
values,

once it is safe to know replicas/files no longer need the deletion
marker.

Removing tombstones too early can accidentally resurrect old data.

------------------------------------------------------------------------

## 25. Read Repair

Suppose a read returns:

``` text
Replica A → version 5
Replica B → version 5
Replica C → version 3
```

The coordinator can return version 5 and asynchronously repair C:

``` text
Replica C
version 3
   ↓
version 5
```

This is **read repair**.

It gradually fixes stale replicas during normal reads.

Background anti-entropy repair can handle data that is rarely read.

------------------------------------------------------------------------

## 26. Coordinator

A client can contact any cluster node.

That node temporarily acts as coordinator:

``` text
Client
  ↓
Node X / Coordinator
  │
  ├── determine partition
  ├── identify replicas
  ├── send read/write
  ├── collect quorum
  └── resolve versions
```

This does not necessarily imply a permanent central coordinator.

A decentralized cluster avoids a single global bottleneck.

------------------------------------------------------------------------

## 27. High-Level Architecture

``` text
                           CLIENTS
                              │
                              ▼
                    ANY CLUSTER NODE
                       (Coordinator)
                              │
                 hash(key) / ring lookup
                              │
               ┌──────────────┼──────────────┐
               ▼              ▼              ▼
             Node A         Node B         Node C
               │              │              │
               │<──── replication/quorum ───>│
               │              │              │
               ▼              ▼              ▼
          Storage Engine Storage Engine Storage Engine

Each node:
┌──────────────────────────────────┐
│ Client/coordinator responsibilities│
│ Consistent-hash ownership         │
│ Replication                       │
│ Gossip/failure detection          │
│ Version/conflict handling         │
│ Repair                            │
│                                  │
│ WAL → Memtable → SSTables         │
│          ↓          ↓             │
│       Cache    Bloom Filters      │
│               + Compaction       │
└──────────────────────────────────┘
```

------------------------------------------------------------------------

## 28. End-to-End Write

Example:

``` text
PUT("user:42", value)
```

Flow:

``` text
1. Client contacts Node X.
2. X hashes the key.
3. X identifies replicas A, B, C.
4. Write is sent to replicas.
5. Each replica:
      append WAL
      update memtable
6. Coordinator waits for W acknowledgements.
7. Success returned to client.
8. Remaining replication may complete asynchronously.
9. Memtables eventually flush to SSTables.
10. Background compaction reorganizes SSTables.
```

If one replica is temporarily unavailable, an AP-style system may use
hinted handoff/sloppy quorum behavior.

------------------------------------------------------------------------

## 29. End-to-End Read

Example:

``` text
GET("user:42")
```

Flow:

``` text
1. Client contacts coordinator.
2. Coordinator hashes key.
3. Identify replicas.
4. Query enough replicas for configured R.
5. Compare returned versions.
6. Resolve newest/non-conflicting value.
7. Return value.
8. Repair stale replicas if needed.
```

Inside each replica:

``` text
memtable/cache
     ↓
Bloom filters
     ↓
SSTable indexes/files
     ↓
newest value/tombstone
```

------------------------------------------------------------------------

## 30. Adding a Node

Suppose the cluster grows:

``` text
A B C
```

to:

``` text
A B C D
```

With consistent hashing:

``` text
D joins ring
```

Only ranges now owned by D move from neighboring nodes.

Virtual nodes spread this migration across the cluster.

During rebalancing: - continue serving traffic, - stream relevant
partitions, - update membership, - avoid overwhelming network/disk.

------------------------------------------------------------------------

## 31. Removing or Losing a Node

If B disappears:

``` text
A B C D
  ↓
A   C D
```

consistent hashing reassigns B's ranges.

Replication ensures data remains available while replacement replicas
are created.

Virtual nodes help spread the lost ranges across multiple surviving
nodes.

------------------------------------------------------------------------

## 32. Hot Keys

Even perfect partition balance does not guarantee traffic balance.

One key might receive:

``` text
1,000,000 GETs/sec
```

while neighboring keys receive almost nothing.

Possible mitigations: - caching, - read replicas, - request
coalescing, - application-level key splitting for suitable data, -
special hot-key detection.

Important distinction:

``` text
balanced storage
≠
balanced traffic
```

------------------------------------------------------------------------

## 33. Durability vs Latency

When should a write be acknowledged?

### Before durable WAL

Very fast, but:

``` text
machine crashes
→ acknowledged data may disappear
```

### After WAL is durable

Safer:

``` text
append WAL
fsync/group commit
then ACK
```

but increases latency.

Batching/group commit can amortize disk synchronization cost.

The durability contract must be explicit.

------------------------------------------------------------------------

## 34. LSM Tree Trade-Off

LSM-style storage is attractive for write-heavy key-value stores.

Advantages: - sequential writes, - high write throughput, - immutable
SSTables, - efficient batching.

Costs: - compaction, - read amplification, - write amplification, -
space amplification.

A B-tree-based engine may offer different read/write trade-offs.

The interview answer should connect the storage engine choice to
workload requirements.

------------------------------------------------------------------------

## 35. Caching

A node may cache frequently read values/blocks.

``` text
GET
 ↓
Cache
 ├── HIT → return
 └── MISS → storage engine
```

Possible policies: - LRU, - LFU, - size-aware eviction.

Caching improves latency but creates: - memory pressure, -
invalidation/freshness questions, - hot-key considerations.

The durable store remains the source of truth.

------------------------------------------------------------------------

## 36. TTL / Expiration

Optional key-value stores may support:

``` text
PUT(key, value, ttl=3600)
```

Expiration can be handled with: - timestamps on records, - lazy deletion
on reads, - background cleanup, - compaction.

A key can be logically expired before its bytes are physically removed.

------------------------------------------------------------------------

## 37. Observability

Important metrics: - GET/PUT latency percentiles, - request
throughput, - error rate, - replica lag, - unavailable nodes, - quorum
failures, - disk usage, - compaction backlog, - cache hit rate, -
Bloom-filter effectiveness, - hot partitions/keys, - repair backlog.

Distributed storage problems often appear first as tail-latency
problems.

------------------------------------------------------------------------

## 38. Important Trade-Offs

### Consistency vs availability

``` text
CP
→ stronger consistency during partitions
→ reject/block some requests

AP
→ continue serving
→ stale/conflicting data possible
```

### Replication factor

``` text
larger N
→ more fault tolerance
→ more storage/network cost
```

### Read quorum

``` text
larger R
→ fresher/more validated reads
→ higher latency
```

### Write quorum

``` text
larger W
→ stronger acknowledgement
→ higher latency/lower availability
```

### LSM vs in-place tree

``` text
LSM
→ write optimized
→ compaction/read amplification

B-tree family
→ stronger point/range read characteristics
→ more random/in-place write cost
```

### Conflict resolution

``` text
last-write-wins
→ simple
→ may lose concurrent updates

client/application merge
→ preserves conflicts
→ more application complexity
```

------------------------------------------------------------------------

## 39. Failure Scenarios

### One replica unavailable

If quorum still succeeds:

``` text
continue serving
```

and repair later.

### Network partition

Apply the chosen consistency/availability policy.

### Coordinator dies

Client retries through another node.

### Node permanently dies

Re-replicate its ranges from surviving replicas.

### Replica is stale

Use read repair/background anti-entropy.

### Crash before memtable flush

Recover from WAL.

### Compaction crashes

Use immutable input files and atomic metadata/file replacement patterns
so the previous valid state remains recoverable.

------------------------------------------------------------------------

## 40. Interview Follow-Up Questions

Be ready for:

-   What operations does the key-value store support?
-   Why can't one server handle everything?
-   What does CAP actually mean during a network partition?
-   CP vs AP: which would you choose and why?
-   Why use consistent hashing?
-   Why not `hash(key) % N`?
-   What are virtual nodes?
-   How is data replicated?
-   What are `N`, `R`, and `W`?
-   What changes when `R` or `W` increases?
-   What is eventual consistency?
-   How do you detect concurrent writes?
-   What is a vector clock/version vector?
-   How are conflicts resolved?
-   How do nodes detect failures?
-   What is gossip?
-   What is hinted handoff?
-   How do replicas repair after permanent failure?
-   What is a Merkle tree used for?
-   What happens during a write?
-   Why use a WAL?
-   What is a memtable?
-   What is an SSTable?
-   Why is compaction required?
-   What is a Bloom filter?
-   How does a read work?
-   How do deletes work with immutable SSTables?
-   What is a tombstone?
-   What is read repair?
-   How do you add/remove nodes?
-   How do you handle hot keys?
-   What happens if the coordinator fails?
-   When is a write considered durable?

------------------------------------------------------------------------

## 41. Interview Version to Memorize

When asked **"Design a Key-Value Store"**, walk through:

``` text
1. Requirements
   → GET / PUT / DELETE
   → low latency
   → high availability
   → large scale

2. Single node
   → hash map
   → explain capacity limit

3. CAP
   → partitions are unavoidable
   → choose consistency/availability behavior

4. Partitioning
   → consistent hashing
   → virtual nodes

5. Replication
   → N replicas
   → cross-failure-domain placement

6. Consistency
   → R / W quorums
   → eventual vs stronger consistency

7. Conflicts
   → versioning
   → vector clocks / resolution

8. Failure handling
   → gossip
   → hinted handoff
   → repair / Merkle trees

9. Storage engine
   → WAL
   → memtable
   → SSTables
   → Bloom filters
   → compaction

10. Walk through
    → complete PUT
    → complete GET

11. Scale/follow-ups
    → node rebalancing
    → hot keys
    → TTL
    → observability
```

------------------------------------------------------------------------

## 42. Architecture to Keep in Your Head

``` text
                       Client
                         │
                         ▼
                   Coordinator
                         │
                    hash(key)
                         │
              ┌──────────┼──────────┐
              ▼          ▼          ▼
           Replica A  Replica B  Replica C
              │          │          │
              └──── quorum/repair ──┘

Per replica:

PUT:
WAL
 ↓
Memtable
 ↓
SSTable
 ↓
Compaction

GET:
Memtable / Cache
 ↓
Bloom Filters
 ↓
SSTables
 ↓
Value
```

------------------------------------------------------------------------

## 43. The 15 Things to Know Cold

1.  A KV store exposes **GET/PUT** and usually **DELETE**.
2.  A single in-memory hash table is fast but cannot scale indefinitely.
3.  **CAP** becomes important once data is distributed.
4.  Use **consistent hashing** to partition keys with limited remapping.
5.  Use **virtual nodes** for better load distribution.
6.  **Replicate** keys across multiple failure domains.
7.  Understand replication factor **N** and read/write quorums **R/W**.
8.  Know the latency/availability/consistency effects of changing `R`
    and `W`.
9.  **Eventual consistency** allows replicas to temporarily diverge.
10. Use **versioning** to detect stale or concurrent values.
11. **Gossip** can distribute cluster membership/failure information.
12. Temporary failures can use **hinted handoff**; permanent divergence
    requires repair.
13. An LSM-style write path is **WAL → memtable → SSTable**.
14. Reads use **memory/cache + Bloom filters + SSTables**.
15. **Compaction, tombstones, and repair** are essential background
    maintenance.

------------------------------------------------------------------------

## 44. 60-Second Revision

**Distributed KV store = partition + replicate + reconcile + persist.**

``` text
KEY DISTRIBUTION
key
→ hash
→ consistent-hash ring
→ responsible replicas

WRITE
PUT
→ coordinator
→ replicas
→ WAL
→ memtable
→ wait for W
→ ACK
→ later flush to SSTables

READ
GET
→ coordinator
→ query R replicas
→ compare versions
→ resolve value
→ repair stale replicas

FAILURES
gossip
→ detect unavailable node
→ hinted handoff for temporary failure
→ replica/Merkle repair for divergence

DISK
WAL
→ memtable
→ SSTables
→ Bloom filters
→ compaction
```

Core reasoning:

``` text
more data
→ partition

node failures
→ replication

network partitions
→ CAP trade-off

replicas disagree
→ quorum + versioning + repair

high write throughput
→ WAL + memtable + SSTables

many SSTables
→ Bloom filters + compaction
```

If you can clearly explain **consistent hashing, replication, quorums,
CAP, versioning, failure recovery, and the WAL/memtable/SSTable
read-write paths**, you have the core of the Design a Key-Value Store
interview problem.

***

# Design a Distributed Message Queue --- System Design Interview Notes

## 1. Quick Overview --- Bullet Points

-   A **message queue** decouples producers from consumers by storing
    messages until consumers process them.
-   Core actors:
  -   **Producer** --- publishes messages.
  -   **Broker** --- receives, stores, and serves messages.
  -   **Consumer** --- reads/processes messages.
-   Core requirements:
  -   high throughput,
  -   horizontal scalability,
  -   durability,
  -   fault tolerance,
  -   ordered delivery where required,
  -   configurable delivery guarantees,
  -   backpressure handling.
-   A queue is useful when producers and consumers:
  -   run at different speeds,
  -   should fail independently,
  -   need asynchronous processing.
-   A distributed queue is typically divided into **topics/queues** and
    **partitions**.
-   **Partitioning** allows a topic to scale across multiple brokers.
-   A message key can determine the partition, allowing related messages
    to stay ordered.
-   Ordering is normally guaranteed **within a partition**, not globally
    across all partitions.
-   Each partition is commonly implemented as an **append-only log**.
-   Messages receive monotonically increasing **offsets** within a
    partition.
-   Consumers track their current offset instead of requiring the broker
    to delete each message immediately after consumption.
-   This log-based model allows:
  -   replay,
  -   multiple independent consumers,
  -   efficient sequential disk I/O.
-   **Consumer groups** allow multiple consumers to share the work of a
    topic.
-   Within one consumer group, each partition should normally be
    processed by only one consumer at a time.
-   More partitions increase parallelism.
-   Having more consumers than partitions in one group provides no
    additional partition-level processing parallelism.
-   **Replication** stores partition copies on multiple brokers.
-   A partition typically has a **leader** and one or more
    followers/replicas.
-   Producers and consumers interact with the leader; replicas copy the
    log.
-   If the leader fails, an up-to-date replica can be promoted.
-   **Acknowledgements** control durability/latency trade-offs:
  -   ACK after leader write is faster,
  -   ACK after multiple replicas is safer.
-   Delivery semantics:
  -   **at-most-once** --- messages may be lost but are not retried.
  -   **at-least-once** --- messages are retried, so duplicates are
      possible.
  -   **exactly-once** --- difficult; usually requires idempotency,
      deduplication, and/or transactions.
-   At-least-once is a common practical default.
-   Consumers should often be **idempotent** because retries can cause
    duplicate processing.
-   **Consumer offsets** must be committed carefully:
  -   commit before processing → risk message loss,
  -   commit after processing → risk duplicate processing.
-   **Backpressure:** when consumers are slower than producers, messages
    accumulate in the queue instead of immediately overwhelming
    consumers.
-   Queue capacity is still finite; monitor lag, disk usage, and
    retention.
-   **Retention** determines how long consumed messages remain
    available.
-   Retaining consumed messages enables replay and new consumer groups.
-   **Batching** improves throughput for both producers and consumers.
-   **Compression** reduces network/storage cost at the expense of CPU.
-   **Pull-based consumption** lets consumers control their processing
    rate and naturally supports backpressure.
-   **Push-based consumption** can reduce latency but risks overwhelming
    slow consumers.
-   **Dead-letter queues (DLQs)** isolate messages that repeatedly fail
    processing.
-   **Retry queues/backoff** prevent poison messages from creating tight
    retry loops.
-   **Idempotency keys/message IDs** help deduplicate producer retries
    and consumer side effects.
-   **Hot partitions** occur when one key or partition receives
    disproportionate traffic.
-   Key interview insight: a distributed message queue is fundamentally
    an **ordered partitioned log + replication + consumer offsets +
    delivery semantics + backpressure**.

------------------------------------------------------------------------

## 2. Why Do We Need a Message Queue?

Without a queue:

``` text
Producer
   │
   ▼
Consumer
```

The producer depends directly on the consumer.

Problems: - consumer may be unavailable, - consumer may be slow, -
traffic spikes can overwhelm it, - producer latency includes consumer
processing time.

With a queue:

``` text
Producer
   │
   ▼
Message Queue
   │
   ▼
Consumer
```

The producer can publish quickly and continue.

The consumer processes messages at its own pace.

This is **temporal decoupling**.

------------------------------------------------------------------------

## 3. Example Use Cases

Message queues are useful for:

``` text
Order created
   ↓
Queue
   ├── Payment worker
   ├── Email worker
   └── Analytics worker
```

Other examples: - background jobs, - event-driven microservices, -
log/event ingestion, - notifications, - image/video processing, -
analytics pipelines, - asynchronous workflows.

------------------------------------------------------------------------

## 4. Functional Requirements

A basic system should support:

### Publish

``` text
produce(topic, message)
```

### Consume

``` text
consume(topic)
```

or:

``` text
poll(topic, partition, offset)
```

### Acknowledge / commit

Consumer records:

``` text
"I successfully processed through offset X"
```

### Topic management

Possibly: - create topic, - configure partitions, - retention, -
replication factor.

------------------------------------------------------------------------

## 5. Non-Functional Requirements

Typical interview requirements:

-   high write throughput,
-   high read throughput,
-   low publish latency,
-   durable messages,
-   high availability,
-   horizontal scaling,
-   ordered messages where needed,
-   support consumer failures,
-   support traffic spikes,
-   configurable delivery semantics.

Clarify:

``` text
Do we need strict global ordering?
```

Usually the answer should be:

``` text
No.
Ordering within a partition is sufficient.
```

Global ordering severely limits scalability.

------------------------------------------------------------------------

## 6. Basic Queue Design

Simplest version:

``` text
Producer
   │
   ▼
Queue Server
   │
   ▼
Consumer
```

Internally:

``` text
Queue
┌───────────────────────────┐
│ M1 │ M2 │ M3 │ M4 │ M5   │
└───────────────────────────┘
```

Producer appends.

Consumer reads from the front.

This works on one machine but eventually hits: - storage limits, -
network limits, - CPU limits, - availability problems.

So we distribute the queue.

------------------------------------------------------------------------

## 7. Topics and Partitions

A distributed queue can organize messages into topics:

``` text
Topic: orders
```

Then divide the topic:

``` text
orders
 ├── Partition 0
 ├── Partition 1
 └── Partition 2
```

Each partition is an ordered sequence:

``` text
Partition 0:
M0 → M1 → M2 → M3
```

Partitions can live on different brokers:

``` text
Broker A → Partition 0
Broker B → Partition 1
Broker C → Partition 2
```

This allows horizontal scaling.

------------------------------------------------------------------------

## 8. Why Partition?

Suppose one broker can handle:

``` text
100k messages/sec
```

but the topic receives:

``` text
1M messages/sec
```

One broker is insufficient.

Partition the topic across 10 brokers:

``` text
P0 → Broker 1
P1 → Broker 2
...
P9 → Broker 10
```

Now throughput can scale horizontally.

Partitioning provides: - storage distribution, - network distribution, -
consumer parallelism.

------------------------------------------------------------------------

## 9. Partition Selection

A producer must decide:

``` text
Which partition receives this message?
```

Options:

### Round robin

``` text
M1 → P0
M2 → P1
M3 → P2
M4 → P0
```

Good for load balancing.

### Hash by message key

``` text
partition =
hash(message_key) % partition_count
```

Example:

``` text
key = user_id
```

All events for the same user go to the same partition.

This preserves ordering for that key.

------------------------------------------------------------------------

## 10. Ordering

A scalable queue generally guarantees:

``` text
ordering within a partition
```

not:

``` text
global ordering across the entire topic
```

Example:

``` text
P0: A1 → A2 → A3
P1: B1 → B2 → B3
```

We know:

``` text
A1 before A2 before A3
```

but cannot necessarily say whether:

``` text
A2 happened before B2
```

from queue position alone.

If strict ordering is needed for one entity:

``` text
hash(entity_id)
→ same partition
```

------------------------------------------------------------------------

## 11. Partition as an Append-Only Log

Instead of deleting a message immediately after consumption, store
messages in a log:

``` text
Offset:
0   1   2   3   4   5
│   │   │   │   │   │
▼   ▼   ▼   ▼   ▼   ▼
M0  M1  M2  M3  M4  M5
```

Producer:

``` text
append to end
```

Consumer:

``` text
read from offset
```

Advantages: - sequential writes, - replay, - multiple consumer groups, -
simpler persistence, - batching.

------------------------------------------------------------------------

## 12. Offsets

Each message in a partition gets an offset.

Example:

``` text
Partition 4

offset 100 → message A
offset 101 → message B
offset 102 → message C
```

Consumer tracks:

``` text
next_offset = 103
```

This means the queue does not need to remove a message merely because
one consumer processed it.

Different consumers/groups can have different positions:

``` text
Group A → offset 103
Group B → offset 90
```

------------------------------------------------------------------------

## 13. Consumer Groups

Suppose we have:

``` text
Topic
P0 P1 P2 P3
```

and consumer group:

``` text
Group: order-workers

Consumer A
Consumer B
```

Assignment:

``` text
Consumer A → P0, P1
Consumer B → P2, P3
```

Each partition is processed by one consumer in the group.

This provides parallel processing while preserving partition ordering.

------------------------------------------------------------------------

## 14. Consumer Parallelism

If:

``` text
4 partitions
```

then a consumer group can have up to roughly:

``` text
4 actively assigned consumers
```

Example:

``` text
P0 → C1
P1 → C2
P2 → C3
P3 → C4
```

Adding:

``` text
C5
```

does not increase partition-level parallelism unless partitions are
added.

Important relationship:

``` text
partitions
→ upper bound on consumer parallelism per group
```

------------------------------------------------------------------------

## 15. Multiple Consumer Groups

Different applications may consume the same topic independently.

``` text
Topic: orders
       │
       ├── Group A: payment
       ├── Group B: email
       └── Group C: analytics
```

Each group maintains its own offsets.

Therefore the same message can be processed by: - payment, - email, -
analytics,

without duplicating the stored message per consumer.

------------------------------------------------------------------------

## 16. Pull vs Push Consumers

### Push model

Broker sends:

``` text
Broker → Consumer
```

Advantages: - low delivery latency.

Problems: - broker may overwhelm slow consumers, - complicated flow
control.

### Pull model

Consumer asks:

``` text
Consumer → "give me messages after offset 100"
```

Advantages: - consumer controls pace, - natural backpressure, - easier
batching, - consumers can pause.

Many log-based distributed queues favor pull.

------------------------------------------------------------------------

## 17. Backpressure

Suppose:

``` text
Producer rate = 100k msg/sec
Consumer rate = 60k msg/sec
```

Difference:

``` text
40k msg/sec
```

accumulates in the queue.

This is useful:

``` text
traffic spike
→ queue absorbs burst
→ consumers catch up later
```

But the queue is not infinite.

Monitor:

``` text
consumer lag
disk usage
retention limits
oldest unprocessed message
```

If lag grows indefinitely, more consumer capacity or architectural
changes are required.

------------------------------------------------------------------------

## 18. Consumer Lag

If the latest offset is:

``` text
1,000,000
```

and consumer committed:

``` text
900,000
```

then lag is:

``` text
100,000 messages
```

Lag is one of the most important operational metrics.

Large lag can mean: - consumer is too slow, - consumer failed, - traffic
spike, - downstream dependency is slow.

------------------------------------------------------------------------

## 19. Durability

Messages should survive broker crashes.

A partition is persisted to disk.

Basic write:

``` text
Producer
   ↓
Broker
   ↓
append log
   ↓
disk
```

But one disk/server can still fail.

Therefore use replication.

------------------------------------------------------------------------

## 20. Replication

For replication factor:

``` text
RF = 3
```

partition P0 might have:

``` text
Broker A → P0 leader
Broker B → P0 replica
Broker C → P0 replica
```

The leader receives writes.

Followers replicate:

``` text
Producer
   ↓
P0 Leader
   ├──> Replica B
   └──> Replica C
```

Replication provides: - fault tolerance, - durability, - failover.

------------------------------------------------------------------------

## 21. Partition Leader

Each partition can have one leader.

Example:

``` text
P0 leader → Broker A
P1 leader → Broker B
P2 leader → Broker C
```

This distributes leadership/load across brokers.

Producer sends P0 writes to Broker A.

Consumers read P0 from its leader, depending on the system design.

Followers keep copies.

If A fails:

``` text
Broker B
→ promoted as new P0 leader
```

------------------------------------------------------------------------

## 22. Leader Election and Metadata

Clients need to know:

``` text
Which broker owns/leads partition P?
```

A metadata/control plane tracks: - brokers, - topics, - partitions, -
leaders, - replica assignments.

Conceptually:

``` text
Metadata Service / Controller
            │
            ├── P0 leader = A
            ├── P1 leader = B
            └── P2 leader = C
```

Clients can cache this metadata and refresh after failures/topology
changes.

The control plane itself must be highly available.

------------------------------------------------------------------------

## 23. Producer Acknowledgements

Durability depends on when the broker acknowledges.

### ACK after leader memory only

Very fast, but weak durability.

### ACK after leader durable log

Safer.

### ACK after multiple replicas

``` text
Leader ✓
Replica B ✓
Replica C ✓
```

Stronger fault tolerance but higher latency.

Trade-off:

``` text
more acknowledgements
→ higher durability
→ higher latency / potentially lower availability
```

------------------------------------------------------------------------

## 24. Delivery Semantics

Three common terms:

### At-most-once

``` text
0 or 1 delivery
```

A message may be lost.

Usually achieved by advancing/acknowledging before processing or
avoiding retries.

### At-least-once

``` text
1 or more deliveries
```

No intentional loss after accepted durable publication, but duplicates
can occur.

Common practical choice.

### Exactly-once

``` text
exactly one logical effect
```

Much harder.

Requires coordination such as: - producer idempotency, -
deduplication, - transactional writes, - idempotent consumers, - atomic
offset/output handling.

Do not casually claim that a distributed queue provides magical
exactly-once processing.

------------------------------------------------------------------------

## 25. At-Least-Once Example

Consumer reads:

``` text
message 42
```

Then:

``` text
1. process message
2. database update succeeds
3. consumer crashes before offset commit
```

After restart:

``` text
message 42
```

is read again.

Therefore the database update may happen twice.

Solution:

``` text
consumer operation should be idempotent
```

or deduplicate by:

``` text
message_id
```

------------------------------------------------------------------------

## 26. Offset Commit Timing

### Commit before processing

``` text
read M
commit offset
process M
```

If consumer crashes during processing:

``` text
M may be lost
```

This resembles at-most-once behavior.

### Commit after processing

``` text
read M
process M
commit offset
```

If consumer crashes after processing but before commit:

``` text
M will be retried
```

This gives at-least-once behavior.

This trade-off is a classic interview question.

------------------------------------------------------------------------

## 27. Idempotent Consumers

Suppose a payment event is delivered twice.

Bad:

``` text
charge card twice
```

Better:

``` text
message_id = abc123
```

Consumer stores:

``` text
processed_ids
```

Before side effect:

``` text
if abc123 already processed:
    skip
```

Even better, when possible, make deduplication and the business state
update atomic in the same transactional datastore.

------------------------------------------------------------------------

## 28. Idempotent Producers

Producer retries can also create duplicates.

Example:

``` text
Producer sends M
Broker stores M
ACK is lost
Producer retries M
```

Without deduplication:

``` text
M
M
```

Use: - producer ID, - sequence number, - message ID, - broker-side
deduplication window.

Conceptually:

``` text
(producer_id, sequence)
```

identifies a unique producer message.

------------------------------------------------------------------------

## 29. Batching

Instead of:

``` text
send 1 message
wait
send 1 message
wait
```

batch:

``` text
M1 M2 M3 ... M100
        ↓
one network request
```

Benefits: - fewer syscalls, - fewer network round trips, - better
compression, - higher sequential disk throughput.

Trade-off: - waiting to fill a batch adds latency.

Configuration balances:

``` text
throughput
vs
latency
```

------------------------------------------------------------------------

## 30. Compression

Message batches can be compressed.

Benefits: - less network bandwidth, - less disk space.

Cost: - CPU for compression/decompression.

Compression works especially well on batches because similar messages
often compress efficiently together.

------------------------------------------------------------------------

## 31. Retention

Log-based queues do not need to delete a message immediately after
consumption.

Retention might be:

``` text
24 hours
7 days
30 days
```

or based on disk size.

Benefits: - replay, - recover broken consumers, - add new consumer
groups, - debugging/auditing.

Example:

``` text
Consumer offset reset:
1,000,000
→
900,000
```

Then messages can be replayed.

------------------------------------------------------------------------

## 32. Log Segments

A partition log should not be one infinitely growing file.

Split it:

``` text
Partition 0
 ├── segment 000
 ├── segment 001
 ├── segment 002
 └── segment 003
```

Each segment contains a range of offsets.

Benefits: - easier retention deletion, - easier indexing, - manageable
files, - easier replication.

When an old segment expires:

``` text
delete entire segment
```

instead of deleting individual messages.

------------------------------------------------------------------------

## 33. Indexes

Messages are stored sequentially, but consumers request:

``` text
start from offset X
```

Maintain a sparse index:

``` text
offset → file position
```

Then the broker can seek near the requested message without scanning the
whole log.

Because offsets and records are ordered, the index does not need one
entry for every message.

------------------------------------------------------------------------

## 34. Sequential Disk I/O

Append-only logs are efficient because:

``` text
writes go to end
```

rather than random disk positions.

Modern disks and operating systems can achieve high throughput with: -
sequential writes, - page cache, - batching, - zero-copy techniques.

Important interview insight:

> Disk-backed queues can still be extremely fast when their access
> pattern is sequential.

------------------------------------------------------------------------

## 35. Consumer Rebalancing

Suppose:

``` text
P0 → C1
P1 → C1
P2 → C2
P3 → C2
```

Then C3 joins.

The group rebalances:

``` text
P0 → C1
P1 → C2
P2 → C3
P3 → one of C1/C2/C3
```

If a consumer dies, its partitions are reassigned.

During rebalance: - processing may pause briefly, - ownership must be
coordinated, - duplicate processing can occur around failures if offsets
are not committed carefully.

------------------------------------------------------------------------

## 36. Retry Handling

A consumer may fail because a downstream service is temporarily
unavailable.

Naive retry:

``` text
fail
retry immediately
fail
retry immediately
...
```

can create a retry storm.

Better:

``` text
retry 1 → wait 1s
retry 2 → wait 5s
retry 3 → wait 30s
...
```

Use exponential backoff and/or retry topics/queues.

------------------------------------------------------------------------

## 37. Dead-Letter Queue

Some messages repeatedly fail because they are malformed or impossible
to process.

These are **poison messages**.

After a retry threshold:

``` text
Main Queue
   ↓
failed repeatedly
   ↓
Dead-Letter Queue
```

DLQ allows: - normal processing to continue, - inspection, -
debugging, - manual or automated reprocessing.

------------------------------------------------------------------------

## 38. Hot Partitions

Hash partitioning can still produce uneven traffic.

Example:

``` text
partition key = celebrity_user_id
```

Millions of events for one key:

``` text
all → same partition
```

That partition becomes the bottleneck.

Possible mitigations: - choose a better partition key, - split very hot
keys when ordering permits, - increase partitions for broader traffic, -
isolate high-volume tenants, - application-level sharding.

Important:

``` text
more partitions
```

does not fix a single key that must remain strictly ordered in one
partition.

Ordering and scalability can conflict.

------------------------------------------------------------------------

## 39. Producer Flow

Example:

``` text
publish(topic="orders", key=user_42, message=M)
```

Flow:

``` text
1. Producer gets topic metadata.
2. Hash key to partition P3.
3. Find P3 leader.
4. Add message to local batch.
5. Send batch to leader.
6. Leader appends records to log.
7. Followers replicate.
8. Required replicas acknowledge.
9. Leader ACKs producer.
```

If ACK is lost:

``` text
producer may retry
```

so producer idempotency/deduplication matters.

------------------------------------------------------------------------

## 40. Consumer Flow

Example:

``` text
Group = payment-workers
```

Flow:

``` text
1. Consumer joins group.
2. Partitions are assigned.
3. Consumer polls P3 from offset 100.
4. Broker returns a batch.
5. Consumer processes messages.
6. Consumer commits offset 120.
7. Next poll starts after 120.
```

On crash before commit:

``` text
some messages may be replayed
```

which is expected under at-least-once delivery.

------------------------------------------------------------------------

## 41. Broker Failure

Suppose:

``` text
P0 leader = Broker A
followers = B, C
```

A fails.

Control plane detects failure:

``` text
A unavailable
    ↓
choose in-sync replica
    ↓
B becomes leader
    ↓
metadata updated
    ↓
clients refresh routing
```

A replica that is too far behind should generally not be promoted if
doing so would lose acknowledged messages.

------------------------------------------------------------------------

## 42. Replication Lag

Followers may lag behind the leader:

``` text
Leader offset = 10,000
Follower B = 9,999
Follower C = 9,100
```

C is significantly behind.

Track an **in-sync replica set** or equivalent concept.

A durable ACK policy can require replication to enough sufficiently
caught-up replicas.

This reduces the chance of acknowledged data disappearing after leader
failure.

------------------------------------------------------------------------

## 43. Control Plane vs Data Plane

### Data plane

Handles high-volume operations:

``` text
produce
consume
replicate logs
```

### Control plane

Handles lower-volume coordination:

``` text
topic metadata
partition assignments
leader election
broker membership
consumer-group coordination
```

Separating these responsibilities makes the architecture easier to
reason about.

------------------------------------------------------------------------

## 44. Scaling Brokers

To add capacity:

``` text
add broker
   ↓
move some partition replicas
   ↓
rebalance leaders
```

Unlike key-value consistent hashing, queue partition movement is often
managed explicitly because partitions are large ordered logs.

Rebalancing should be throttled because moving terabytes of logs can
consume network and disk bandwidth.

------------------------------------------------------------------------

## 45. Storage Capacity

Suppose:

``` text
1 million messages/sec
average = 1 KB
```

Raw ingress:

``` text
~1 GB/sec
```

Per day:

``` text
~86 TB/day
```

With replication factor 3:

``` text
~258 TB/day
```

before compression/metadata.

This immediately shows why: - retention matters, - compression
matters, - sequential storage matters, - partitions must be distributed.

The exact numbers are less important than demonstrating capacity
reasoning.

------------------------------------------------------------------------

## 46. High-Level Architecture

``` text
                             PRODUCERS
                                 │
                       topic + key + message
                                 │
                                 ▼
                         METADATA LOOKUP
                                 │
                          choose partition
                                 │
              ┌──────────────────┼──────────────────┐
              ▼                  ▼                  ▼
           BROKER A           BROKER B           BROKER C
          P0 Leader          P1 Leader          P2 Leader
          P1 Replica         P2 Replica         P0 Replica
              │                  │                  │
              └────── partition replication ───────┘
                                 │
                                 ▼
                          CONSUMER GROUPS
                       ┌─────────┼─────────┐
                       ▼         ▼         ▼
                      C1        C2        C3
```

Per partition:

``` text
offset
  0      1      2      3      4
  │      │      │      │      │
  ▼      ▼      ▼      ▼      ▼
┌────┬────┬────┬────┬────┐
│ M0 │ M1 │ M2 │ M3 │ M4 │  → append
└────┴────┴────┴────┴────┘
```

------------------------------------------------------------------------

## 47. Important Trade-Offs

### One partition vs many

``` text
one
→ simple ordering
→ low scalability

many
→ parallelism + throughput
→ no global ordering
```

### Push vs pull

``` text
push
→ lower delivery delay
→ backpressure complexity

pull
→ consumer controls rate
→ natural batching/backpressure
```

### ACK leader vs replicas

``` text
leader only
→ lower latency
→ weaker durability

multiple replicas
→ stronger durability
→ higher latency
```

### Commit before vs after processing

``` text
before
→ possible loss
→ fewer duplicates

after
→ possible duplicates
→ safer against loss
```

### Short vs long retention

``` text
short
→ less storage
→ limited replay

long
→ replay/recovery
→ more storage cost
```

### Ordering vs parallelism

``` text
fewer partitions
→ stronger/broader ordering
→ less concurrency

more partitions
→ more concurrency
→ ordering only within each partition
```

------------------------------------------------------------------------

## 48. Failure Scenarios

### Producer times out after successful write

Retry may duplicate the message.

Use idempotent producer/message IDs.

### Consumer crashes before offset commit

Messages replay.

Use idempotent consumer logic.

### Consumer commits too early then crashes

Message can be skipped/lost from that consumer group's perspective.

### Partition leader fails

Promote an in-sync replica.

### Follower falls behind

Remove it from the eligible in-sync set until caught up.

### Entire broker fails

Other replicas continue serving; control plane redistributes leadership.

### Consumer is too slow

Lag grows; scale consumer group if partition parallelism allows.

### Poison message

Retry with backoff, then send to DLQ.

### Disk nearly full

Retention/segment cleanup, capacity expansion, throttling, and alerting
are required.

------------------------------------------------------------------------

## 49. Observability

Monitor:

``` text
producer throughput
consumer throughput
publish latency
fetch latency
consumer lag
broker disk usage
partition size
replication lag
under-replicated partitions
leader election count
failed publishes
retry rate
DLQ volume
rebalance frequency
network throughput
```

Especially important:

``` text
consumer lag
```

because it directly tells you whether processing keeps up with incoming
traffic.

------------------------------------------------------------------------

## 50. Interview Follow-Up Questions

Be ready for:

-   Why use a message queue?
-   What problem does asynchronous messaging solve?
-   Queue vs direct service call?
-   What is a topic?
-   Why partition a topic?
-   How is a partition selected?
-   How do you preserve ordering for one user/order/entity?
-   Why can't we guarantee global ordering easily?
-   Why use an append-only log?
-   What is an offset?
-   Why keep consumed messages?
-   What is a consumer group?
-   How do partitions limit consumer parallelism?
-   Pull vs push consumption?
-   What is backpressure?
-   What is consumer lag?
-   How is a partition replicated?
-   What does the leader do?
-   How does leader failover work?
-   When should a producer receive an ACK?
-   At-most-once vs at-least-once vs exactly-once?
-   Why can at-least-once create duplicates?
-   When should consumers commit offsets?
-   How do idempotent consumers work?
-   How do producer retries create duplicates?
-   Why batch messages?
-   Why compress batches?
-   How does retention work?
-   Why split logs into segments?
-   How do we seek to an offset efficiently?
-   How does consumer rebalancing work?
-   What is a dead-letter queue?
-   How do retry queues/backoff work?
-   What is a hot partition?
-   How do you scale brokers?
-   What metrics would you monitor?

------------------------------------------------------------------------

## 51. Interview Version to Memorize

When asked **"Design a Distributed Message Queue"**, walk through:

``` text
1. Requirements
   → produce
   → consume
   → durable
   → scalable
   → ordered where needed

2. Basic abstraction
   → producer
   → broker
   → consumer

3. Partitioning
   → topic → partitions
   → key hashes to partition
   → ordering per partition

4. Storage
   → append-only log
   → offsets
   → log segments + index

5. Consumers
   → consumer groups
   → partition assignment
   → offsets
   → pull/batching

6. Replication
   → partition leader
   → follower replicas
   → leader failover

7. Delivery semantics
   → at-most-once
   → at-least-once
   → exactly-once challenges

8. Reliability
   → idempotent producer
   → idempotent consumer
   → retries
   → DLQ

9. Backpressure
   → consumer lag
   → retention
   → scale consumers

10. Scale
    → more partitions
    → more brokers
    → rebalance
    → hot partition handling
```

------------------------------------------------------------------------

## 52. Architecture to Keep in Your Head

``` text
Producer
   │
   │ hash(message_key)
   ▼
Partition Leader
   │
   ├────> Replica
   └────> Replica
   │
   ▼
Append-Only Log
   │
   │ offsets
   ▼
Consumer Group
   ├── Consumer A → P0
   ├── Consumer B → P1
   └── Consumer C → P2
```

Producer:

``` text
message
→ partition
→ leader
→ append
→ replicate
→ ACK
```

Consumer:

``` text
poll from offset
→ process
→ commit offset
→ poll again
```

------------------------------------------------------------------------

## 53. The 15 Things to Know Cold

1.  A message queue **decouples producers and consumers**.
2.  A topic is divided into **partitions** for scale.
3.  A partition is an **ordered append-only log**.
4.  Messages receive sequential **offsets** inside a partition.
5.  Ordering is generally guaranteed **per partition**, not globally.
6.  Hash a message key to keep related events in the same partition.
7.  **Consumer groups** distribute partitions among consumers.
8.  Number of partitions limits active consumer parallelism in one
    group.
9.  Replicate partitions across brokers for durability/availability.
10. A partition **leader** coordinates writes and replicas copy the log.
11. Producer ACK policy trades **latency for durability**.
12. **At-least-once** delivery means consumers must expect duplicates.
13. Offset commit timing determines the loss-vs-duplicate trade-off.
14. **Backpressure and consumer lag** are central operational concepts.
15. Use **batching, retention, retries, idempotency, and DLQs** to make
    the system practical.

------------------------------------------------------------------------

## 54. 60-Second Revision

**Distributed message queue = partitioned replicated logs + consumer
offsets.**

``` text
PRODUCE
message
→ choose partition
→ leader
→ append log
→ replicate
→ ACK

STORE
partition
→ ordered log
→ offsets
→ segments
→ retention

CONSUME
consumer group
→ partition assignment
→ poll from offset
→ process
→ commit offset

FAILURE
leader dies
→ promote replica

consumer dies
→ rebalance partitions

processing fails
→ retry/backoff
→ DLQ

SCALE
more throughput
→ more partitions
→ more brokers
→ more consumers
```

Core reasoning:

``` text
producer/consumer coupling
→ queue

high throughput
→ partitions + batching

ordering
→ per partition

durability
→ replicated logs

consumer recovery
→ offsets

traffic spikes
→ buffering/backpressure

duplicate delivery
→ idempotency

bad messages
→ retries + DLQ
```

If you can clearly explain **partitioned logs, offsets, consumer groups,
replication, ACKs, delivery semantics, idempotency, and backpressure**,
you have the core of the Design Distributed Message Queue interview
problem.


