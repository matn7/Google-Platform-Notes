# Google Cloud Professional Cloud Developer — Practice Questions.

# Week 1 — Cloud-Native Architecture & Application Design

Focus: Compute platforms, scalability, reliability, event-driven architecture, and application design.

## Section 1: Compute Services & Architecture.

**1. What are the key differences between Cloud Run, Google Kubernetes Engine (GKE), Cloud Functions, and App Engine? 
In which scenarios would you choose each service?**

Google Cloud provides several compute platforms that differ in their level of abstraction, operational control, and workload requirements.

**Cloud Run** is a fully managed platform for running containerized applications without managing servers or Kubernetes clusters. 
It supports automatic scaling, including scale-to-zero for eligible workloads. 
It is suitable for REST APIs, microservices, event-driven applications, and batch processing through Cloud Run Jobs.

**Google Kubernetes Engine (GKE)** is a managed Kubernetes service that provides greater control over container orchestration, 
networking, scaling, and workload configuration. 
It is particularly useful for complex microservice architectures and workloads requiring Kubernetes-specific capabilities.
GKE Autopilot reduces operational overhead compared with Standard mode.

**Cloud Functions (Cloud Run functions)** provides a function-oriented serverless model for executing code in response 
to HTTP requests or events, such as Pub/Sub messages or Cloud Storage changes. 
It is well suited for focused event handlers and integrations.

**App Engine** is a fully managed application platform that supports deployment using supported runtimes. 
Its Standard environment offers a more constrained runtime with automatic scaling, while Flexible runs applications 
in containers on managed Compute Engine VMs.

**In summary**, I would choose Cloud Run for containerized APIs and microservices with minimal operational overhead, 
GKE when I need Kubernetes-specific control, Cloud Run functions for focused event handlers, and App Engine for 
applications that fit its runtime and deployment model.

| Feature                   | Cloud Run           | GKE                       | Cloud Run functions  | App Engine                               |
|---------------------------|---------------------|---------------------------|----------------------|------------------------------------------|
| Primary model             | Containers          | Kubernetes                | Functions            | Application platform                     |
| Infrastructure management | Minimal             | Low-high                  | Minimal              | Minimal                                  |
| Autoscaling               | Yes                 | Yes, configured           | Yes                  | Yes                                      |
| Scale to zero             | Yes, for services   | Depends on workload setup | Yes                  | Depends on environment and configuration |
| Best fit                  | APIs. microservices | Complex orchestration     | Event handlers       | Runtime-based web apps                   |

**A company has 15 Java Spring Boot microservices deployed on GKE. The services expose REST APIs, communicate through Pub/Sub, 
and do not require custom Kubernetes networking or operators. The team spends significant time maintaining Kubernetes infrastructure.
Would you recommend migrating these services to Cloud Run? What benefits, limitations, and migration risks would you consider?**

Yes, I would consider migrating these microservices from GKE to Cloud Run because they are containerized Java applications 
that expose REST APIs and communicate through Pub/Sub, without requiring Kubernetes-specific features.

The main benefit would be reduced operational overhead. Cloud Run provides fully managed infrastructure, automatic scaling,
and built-in support for containerized workloads. It can also scale to zero, potentially reducing costs during periods of low traffic.

However, I would first verify that the services are suitable for Cloud Run. I would examine whether they are stateless, 
how they handle background processing, their resource requirements, startup times, request timeouts, and database connection management.

I would also consider the differences in health checks, networking, and scaling behavior. For Java Spring Boot applications, 
cold starts and memory consumption could affect latency and cost.

I would recommend migrating one representative service first, running load tests, comparing operational costs, and validating 
Pub/Sub integration, observability, and failure recovery before migrating the remaining services.

***

**2. You are designing a Java Spring Boot REST API that receives unpredictable traffic, ranging from zero to several thousand requests per second.
Which Google Cloud compute service would you choose, and why?**

For a stateless Spring Boot REST API with highly unpredictable traffic, I would initially choose Google Cloud Run.

It is a fully managed container platform that automatically scaled based on incoming traffic and can scale down to zero
when the application is idle, helping reduce costs.

I would package the Spring Boot application as a container and configure CPU, memory, concurrency, and minimum and maximum
instances based on load testing.

One consideration is JVM startup time. If low latency is critical, I might configure minimum instances to reduce cold starts.

I would also ensure the application is stateless and verify that downstream services, especially the database and connection
pool, can handle increased load.

App Engine would also be a reasonable option, but for a new containerized API, I would generally prefer Cloud Run.

I would consider GKE if we need Kubernetes-specific capabilities, custom orchestration, or greater control over the
runtime environment.

**Comparing the compute services.**

| Service             | When I would choose it                                                                            | Key trade-off                                                       |
|---------------------|---------------------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| Cloud Run           | Stateless containerized APIs with variable traffic                                                | Cold starts, execution constraints, and less infrastructure control |
| App Engine Standard | Applications using supported runtimes and managed scaling                                         | More opinionated runtime environment                                |
| App Engine Flexible | Applications requiring a more customizable App Engine runtime                                     | At least one instance stays running                                 |
| GKE                 | Workloads needing Kubernetes orchestration, platform customization, or specific workloads control | More operational complexity                                         |
| Compute Engine      | Application requiring direct VM and OS control                                                    | You manage more infrastructure                                      |

**Memory phrase.**

> "Cloud Run first for stateless, bursty APIs. GKE when Kubernetes control is justified."

***

**3. How does concurrency work in Cloud Run, and how does it affect application performance, resource utilization, and scaling?**

Cloud Run concurrency defines how many HTTP requests a single container instance can process simultaneously.

It's different from autoscaling, which increase or decreases number of instances base on demand.

Higher concurrency can improve resource utilization and reduce the number of instances needed, particularly for I/O-bound 
applications. However, if too many requests compete for CPU, memory, threads, or database connections, latency can increase.

Lower concurrency can provide better request isolation, but it may cause Cloud Run to create more instances, increasing
costs and pressure on downstream services.

For a Spring Boot application, I would consider the request workload, JVM resources, thread pools, and HikariCP configuration.

I would tune concurrency through load testing, monitoring CPU utilization, p95 and p99 latency, instance counts, and
database connection usage.

The goal is to find a balance between throughput, latency, cost, and downstream capacity.

**Memory phrase.**

> "Concurrency = requests per instance. Scaling = number of instances."

> "Higher concurrency improves utilization until resource contention increases latency."

***

**4. What are the differences between Cloud Run autoscaling and GKE Horizontal Pod Autoscaling (HPA)? 
What are the advantages and limitations of each approach?**

Both Cloud Run autoscaling and GKE Horizontal Pod Autoscaling increase or decrease application capacity based on demand,
but they operate at different levels.

Cloud Run automatically scales container instances primarily base on incoming request concurrency and CPU utilization.
It supports scaling to zero and manages the underlying infrastructure, making it well suited for stateless APIs with
unpredictable traffic.

GKE HPA scales Kubernetes pod replicas based on configured metrics, such as CPU, memory, or custom metrics. It provides
greater control over scaling thresholds, policies, and stabilization behavior.

However, HPA only scales pods. The cluster must also have enough compute capacity, potentially requiring node autoscaling.

Cloud Run offers simplicity and lower operational overhead, while GKE HPA provides more flexibility for complex workloads.

For a typical stateless Spring Boot REST API, I would prefer Cloud Run. I would choose GKE when Kubernetes-specific capabilities 
or more advanced workload control justify the added complexity.

**Cloud Run vs. GKE HPA.**

| Feature                 | Cloud Run                                   | GKE HPA                                                  |
|-------------------------|---------------------------------------------|----------------------------------------------------------|
| Scaling unit            | Container instances                         | Kubernetes pod replicas                                  |
| Primary scaling signals | Request concurrency and CPU                 | CPU, memory, custom/external metrics                     |
| Scale to zero           | Yes                                         | Not with standard CPU/memory-based HPA                   |
| Scaling configuration   | Relatively simple                           | Highly configurable                                      |
| Node provisioning       | Managed by Google                           | Requires sufficient cluster capacity or node autoscaling |
| Scaling policies        | Mostly managed                              | Configurable policies and stabilization                  |
| Operational overhead    | Low                                         | Higher                                                   |
| Best fit                | Stateless APIs and request-driven workloads | Kubernetes workloads requiring fine-grained control      |

**Memory phrase.**

> "Cloud Run scales instances and manages compute. HPA scales pods; GKE must provide capacity."

***

**5. Your team needs to migrate a monolithic Java application to Google Cloud. What factors would you consider when deciding
between Cloud Run and GKE?**

When migrating a Java monolith to Google Cloud, I would first evaluate the application's runtime requirements rather than
choosing a platform based on its size or complexity.

I would check whether the application is stateless, how it handles sessions and background processing, its startup time,
memory consumption, and scaling requirements.

If it's primarily an HTTP-based application that can run in a container and does not require specialized infrastructure, 
I would initially consider Cloud Run because it provides managed scaling and reduces operational overhead.

I would consider GKE if the application requires Kubernetes-specific capabilities, more control over workload orchestration,
or has operational requirements that don't fit Cloud Run well.

I would also consider the company's existing infrastructure, team expertise, deployment standards, and total cost of ownership.

Finally, I would validate the chosen approach with a proof of concept before committing to the migration.

**Cloud Run vs. GKE for a Java monolith.**

| Factor                            | Cloud Run                                                  | GKE                                                         |
|-----------------------------------|------------------------------------------------------------|-------------------------------------------------------------|
| Stateless HTTP application        | Excellent fit                                              | Also supported                                              |
| Stateful application              | Requires externalizing persistent state                    | More workload options, but state still needs careful design |
| Traffic spikes                    | Managed autoscaling                                        | HPA plus sufficient cluster capacity                        |
| JVM cold starts                   | Can be an issue when scaling from zero                     | Pods can also have startup delays                           |
| Long-running background processes | Requires choosing an appropriate Cloud Run execution model | Greater flexibility for workers and scheduled processes     |
| Custom orchestration              | Limited                                                    | Strong Kubernetes capabilities                              |
| Deployment control                | Simpler, opinionated                                       | Highly configurable                                         |
| Operational overhead              | Lower                                                      | Higher, especially with GKE Standard                        |

**Memory phrase.**

> "Workload requirements -> State management -> Scaling -> Operational control -> Cost -> Proof of concept."

> "Choose Cloud Run by default when the workload fits; choose GKE when specific requirements justify Kubernetes."

***

## Section 2: Scalability, Reliability & Resilience.

**6. What is the difference between horizontal and vertical scaling? How are these scaling strategies implemented in Cloud Run and GKE?**

Horizontal scaling means increasing the number of application instances to distribute traffic, while vertical scaling
means increasing the CPU or memory available to each instance.

In Cloud Run, horizontal scaling is managed automatically based on traffic and CPU utilization. For vertical scaling, we
configure the CPU and memory allocated to each container instance.

In GKE, horizontal scaling is typically handled by the Horizontal Pod Autoscaler, which adjusts the number of pod replicas 
based on CPU, memory, or custom metrics.

Vertical scaling in GKE can be achieved by adjusting pod resource requests and limits, potentially using Vertical Pod Autoscaler.

GKE may also need node autoscaling to provide enough compute capacity for additional pods.

Horizontal scaling is generally preferable for stateless applications because it improves capacity and can support high availability.
Vertical scaling is useful when individual instances need more resources.

In practice, I would choose based on the application's bottlenecks and validate the configuration through load testing.

**Comparison by platform.**

| Scaling type       | Cloud Run                                              | GKE                                                                             |
|--------------------|--------------------------------------------------------|---------------------------------------------------------------------------------|
| Horizontal         | Automatically adjust the number of container instances | HPA adjusts pod replica counts                                                  |
| Vertical           | Configure CPU and memory per instance                  | Configure pod CPU/memory requests and limits; VPA can recommend or adjusts them |
| Underlying compute | Managed by Google                                      | GKE manages or provisions nodes depending on cluster mode                       |
| Scale to zero      | Supported                                              | Standard HPA generally doesn't scale to zero                                    |
| Main limitation    | Per-instance limits and platform constraints           | Pod scheduling, node capacity, and cluster configuration                        |

**Memory phrase.**

> "Horizontal = More instances. Vertical = Bigger instances."

> "Cloud Run: managed instances + configured resources. GKE: HPA + VPA + node autoscaling."

***

**7. A Spring Boot application deployed on Cloud Run occasionally experiences high latency during traffic spikes.
What could be causing this behavior, and how would you investigate and mitigate it?**

Traffic spikes can cause high latency in Cloud Run for several reasons.

First, Cloud Run may need to start additional container instances. For a Spring Boot application, JVM and application
initialization can introduce cold-start latence.

Second, high request concurrency may saturate CPU, application threads, or database connection pools.

Third, rapid horizontal scaling can overload downstream services such as PostgreSQL, because each new instance may create
its own connection pool.

I would investigate Cloud Run instance counts, startup latency, CPU and memory utilization, request concurrency, and p95
and p99 latency. I would also inspect database connection usage and query latency.

Depending on the findings, I might configure minimum instances, optimize application startup, tune concurrency and CPU allocation,
adjust database connection pooling, or limit maximum instances to protect downstream systems.

Finally, I would reproduce the issue through load testing and verify that the changes improve latency.

**Practical investigation: What would you check?**

| Symptoms                                | Possible cause                                           | Mitigation                                                           |
|-----------------------------------------|----------------------------------------------------------|----------------------------------------------------------------------|
| Latency spikes when new instances start | JVM/Spring Boot cold starts                              | Minimum instances, startup optimization                              |
| High CPU during traffic spikes          | CPU saturation                                           | Increase CPU, tune concurrency                                       |
| High latency but relatively low CPU     | Database waits, blocking I/O, connection pool exhaustion | Inspect traces, thread pools, HikariCP                               |
| PostgreSQL connection errors            | Too many Cloud Run instances opening connections         | Tune pool sizes, control instance count, consider connection pooling |
| Requests queue during spikes            | Insufficient available capacity                          | Review concurrency, scaling limits, startup time                     |
| Memory pressure or OOM                  | Too many concurrent requests or insufficient memory      | Increase memory, reduce concurrency, investigate allocations         |

**Memory phrase.**

> "Cold starts -> Concurrency -> CPU/Memory -> Database -> Scaling limits."

> "Measure the bottleneck before changing the scaling configuration."

***

**8. What is a cold start in a serverless environment? How can you reduce its impact on a Java application running on Cloud Run?**

A cold start occurs when a serverless platform needs to start a new application instance, introducing additional latency
before it can handle requests.

For Java applications on Cloud Run, this includes container startup, JVM initialization, class loading, and Spring Boot
application context initialization.

Cold starts are especially noticeable when a service scales from zero or rapidly adds instances during a traffic spike.

To reduce their impact, I would first measure application startup time and identify expensive initialization steps.

I would optimize Spring Boot startup, remove unnecessary dependencies, and consider lazy initialization where appropriate.

I would also evaluate Cloud Run's startup CPU boost and configure minimum instances if predictable low latency justifies the
additional cost.

Finally, I would distinguish cold-start latency from cache warm-up and database bottlenecks, using monitoring and load
testing to verify the improvements.

**How to reduce cold starts in Java on Cloud Run.**

| Technique                           | Benefit                               | Trade-off                                                   |
|-------------------------------------|---------------------------------------|-------------------------------------------------------------|
| Minimum instances                   | Keeps some instances warm             | Additional cost                                             |
| Startup CPU boost                   | More CPU available during startup     | Additional startup-related cost                             |
| Optimize Spring Boot initialization | Reduces startup work                  | May require code/configuration changes                      |
| Lazy initialization                 | Defers some startup work              | Can shift latency to first use                              |
| Reduce unnecessary dependencies     | Less initialization overhead          | Requires dependency review                                  |
| JVM/CDS optimization                | Can improve JVM startup               | Requires benchmarking and compatible configuration          |
| Native image with GraalVM           | Can significantly reduce startup time | More complex build process and compatibility considerations |

**Memory phrase.**

> "Cold start = Start container -> Initialize JVM -> Initialize Spring -> Serve requests."

> "Cold cache = Application is running, but data hasn't been cached yet."

***

**9. What is graceful shutdown, and why is it important for Java applications running in containers? How would you implement it in Spring Boot?**

Graceful shutdown is a mechanism that allows an application to finish in-flight requests and release resources before terminating.

It's particularly important for containerized Java applications because containers may be stopped during deployments, 
autoscaling, or infrastructure maintenance.

Typically, the application receives a SIGTERM signal, which initiates the shutdown process.

In Spring Boot, I would enable graceful shutdown using `server.shutdown=graceful` and configure the shutdown timeout.

This allows the embedded server to stop accepting new requests and wait for active requests to complete.

I would also ensure that background tasks, database connections, and message consumers are shut down appropriately.

Finally, I would configure the platform's termination grace period so the application has enough time to finish, while ensuring
shutdown doesn't hand indefinitely.

The goal is to minimize field requests, interrupted operations, and data inconsistency during deployments or scaling events.

**Important: Spring Boot vs. container platform.**

| Platform         | Graceful shutdown behavior                                                       |
|------------------|----------------------------------------------------------------------------------|
| Spring Boot      | Stops accepting new requests and allows in-flight requests to complete           |
| GKE / Kubernetes | Sends termination signals and uses terminationGracePeriodSeconds                 |
| Cloud Run        | Sends SIGTERM and provides a limited shutdown window before forceful termination |

**Memory phrase.**

> "SIGTERM -> Stop new work -> Finish in-flight requests -> Release resources -> Exit."

> "Graceful shutdown reduces disruption, but idempotency protects correctness." 

***

**10. How would you design a Java application to handle transient failures when communicating with external services?
Explain retries, exponential backoff, jitter, and circuit breakers.**

When communicating with external services, I would first classify failures as transient or permanent.

For transient failures, such as network timeouts, connection errors, HTTP 503, or rate limiting, I would use bounded
retries with exponential backoff and jitter.

Exponential backoff increases the delay between attempts, while jitter adds randomness to prevent multiple clients from
retrying simultaneously.

I would avoid retrying permanent errors, such as malformed requests, and ensure that retried operations are idempotent.

I would also use a circuit breaker to stop sending requests to a dependency that is consistently failing. After a cooldown
period, the circuit breaker allows limited test requests to check whether the service has recovered.

Finally, I would configure appropriate timeouts, monitor failure rates and retry attempts, and use fallbacks where appropriate.

The goal is to recover from temporary failures without creating additional load or cascading failures.

**Memory phrase.**

> "Timeout -> Classify -> Retry with backoff and jitter -> Circuit break -> Monitor."

> "Retries recover from transient failures. Circuit breakers prevent cascading failures. Idempotency protects correctness."

***

## Field 1.3 — Event-Driven Architecture.

**11. What are the main differences between synchronous and asynchronous communication in a microservices architecture?
When would you prefer one over the other?**

Synchronous communication means the caller waits for the downstream service to respond before continuing with dependent 
processing. REST or gRPC are common examples.

I would prefer synchronous communication when the result is immediately required, such as validating authentication or 
retrieving information needed to complete a request.

Asynchronous communication allows services to exchange messages or events without waiting for processing to finish. 
Technologies such as Kafka or Pub/Sub are commonly used.

I would prefer asynchronous communication for background processing, event distribution, or workflows that can tolerate
eventual consistency.

It can improve resilience by decoupling service availability and buffering work during traffic spikes.

However, asynchronous systems introduce additional complexity, including retries, duplicate messages, ordering, monitoring,
and eventual consistency.

In practice, I would often combine both approaches: synchronous communication for immediate user-facing operations and
asynchronous messaging for independent downstream processing.

**Synchronous vs. asynchronous communication.**

| Aspect                 | Synchronous                         | Asynchronous                                              |
|------------------------|-------------------------------------|-----------------------------------------------------------|
| Caller behavior        | Waits for a response                | Can continue without waiting for completion               |
| Common technologies    | REST, gRPC                          | Kafka, Pub/Sub, message queues                            |
| Temporal coupling      | Usually higher                      | Lower with durable messaging                              |
| Latency                | Includes downstream response time   | Caller can respond before background work finishes        |
| Failure handling       | Timeouts, retries, circuit breakers | Retries, dead-letter queues, idempotency                  |
| Data consistency       | Can provide an immediate result     | Often involves eventual consistency                       |
| Operational complexity | Simpler request flow                | More complex distributed workflows                        |
| Best suited for        | Immediate queries and validations   | Background work, event propagation, independent consumers |

**Memory phrase.**

> "Synchronous = Wait for the result. Asynchronous = Decouple the work."

> "Choose based on immediacy, coupling, reliability, and consistency requirements."

***

**12. A Java application publishes events to Pub/Sub, and multiple consumers process those events.
How would you ensure reliable message processing and avoid losing messages?**

To ensure reliable event processing with Google Cloud Pub/Sub, I would use durable subscriptions and acknowledge messages
only after successful processing.

If a consumer crashes or fails to acknowledge a message within its acknowledgment deadline, Pub/Sub can redeliver it.

Because Pub/Sub generally provides at-least-once delivery, I would design consumers to be idempotent, for example by recording
processed event IDs in PostgreSQL.

I would configure retry behavior and dead-letter topics to handle messages that repeatedly fail.

For multiple consumers, I would distinguish between shared subscriptions, where instances distribute the workload, and
separate subscriptions, where independent services each receive the event.

On the publishing side, I would handle transient publish failures and consider the Transactional Outbox pattern to ensure
events aren't lost after database transactions commit.

Finally, I would monitor unacknowledged messages, oldest message age, processing failures, and dead-letter modules.

**Kafka vs. Pub/Sub.**

| Kafka                 | Google Cloud Pub/Sub                                       |
|-----------------------|------------------------------------------------------------|
| Topic                 | Topic                                                      |
| Consumer group        | Shared subscription, conceptually similar                  |
| Partition offset      | Acknowledgment state managed by Pub/Sub                    |
| Offset commit         | Message acknowledgment                                     |
| Reconsume from offset | Seek/reply using supported snapshots or timeouts           |
| Message retention     | Configurable retention                                     |
| Consumer group lag    | Subscription backlog and oldest unacknowledged message age |

**Memory phrase.**

> "Publish durably -> Consume -> Process transactionally -> Acknowledge -> Retry failures -> Dead-letter poison messages."

> "Pub/Sub subscriptions distribute or fan out messages; acknowledgments track completion; idempotency handles duplicates."

***

**13. What is idempotency, and why is it particularly important in event-driven systems?
How would you implement an idempotent Pub/Sub consumer using Java and a database?**

Idempotency means that executing an operation multiple times has the same intended effect as executing it once.

It's particularly important in event-driven systems because message brokers such as Google Cloud Pub/Sub can redeliver
messages when acknowledgments fail or consumers crash.

To implement an idempotent consumer in Java, I would assign each business event a stable unique ID and maintain a table
of processed event IDs in PostgreSQL.

When receiving a message, I would start a database transaction and attempt to insert the event ID into that table, protected
by a unique constraint.

If the event was already processed, I would skip the business operation. Otherwise, I would update the business data in
the same transaction.

I would acknowledge the Pub/Sub message only after the transaction commits successfully.

This ensures that duplicate deliveries don't cause duplicate database updates, even if multiple consumer instances process
the same event concurrently.

**Memory phrase.**

> "Outbox protects publishing. Inbox protects consumption."

> "receive -> Insert event ID -> Update business state -> Commit -> ACK."

> "Use a unique constraint, not just a read-before-write check."

***

**14. How would you handle duplicate messages, out-of-order delivery, and poison messages in a Pub/Sub-based architecture?**

In a Pub/Sub-based architecture, I would handle duplicate messages, out-of-order delivery, and poison messages separately.

For duplicates, I would implement idempotent consumers using unique event IDs and a database table with a unique constraint.
I would record the event ID and update business state in the same transaction, then acknowledge the message after commit.

For out-of-order delivery, I would use Pub/Sub ordering keys when strict ordering is required for events belonging to the
same entity. I would also include version number or sequence numbers in events so consumers can detect stale updates.

For poison messages, I would configure retry policies and a dead-letter topic with a maximum delivery attempt threshold.
Messages that repeatedly fail would be moved to the dead-letter topic for investigation and possible replay.

Finally, I would monitor duplicate rates, processing failures, backlog age, and dead-letter volume to detect reliability 
problems early.

**Memory phrase.**

> "Duplicates -> Idempotency + unique constraint."

> "Out of order -> Ordering key + entity version."

> "Poison messages -> Bounded retries + dead-letter topic."

***

**15. When would you choose Pub/Sub instead of Cloud Tasks or Eventarc? Explain the architectural trade-offs.**

I would choose Google Cloud Pub/Sub when building an event-driven architecture where multiple independent services need 
to consume the same events.

Pub/Sub supports asynchronous communication, durable subscriptions, independent consumers, and high-throughput message
distribution.

I would choose Cloud Tasks when I need to execute a specific background operation, especially when I need controlled dispatch
rates, scheduling, retries, or protection of a downstream HTTP service.

Eventarc is useful when I need to route events from Google Cloud services, such as Cloud Storage or audit logs, to event handlers
without implementing custom ingestion.

The main trade-off is that Pub/Sub provides flexible messaging and fan-out, Cloud Tasks provides fine-grained task execution
control, and Eventarc simplifies event routing and integration.

I would choose based on whether I'm distributing business events, executing commands, or reacting to infrastructure events.

| Feature                        | Pub/Sub                       | Cloud Task                  | Eventarc                             |
|--------------------------------|-------------------------------|-----------------------------|--------------------------------------|
| Primary purpose                | Message distribution          | Task execution              | Event routing                        |
| Multiple independent consumers | Yes, via subscriptions        | Not the primary model       | Yes, via multiple triggers           |
| Delayed/scheduled execution    | Not its main purpose          | Yes                         | Not its main purpose                 |
| Dispatch rate limiting         | Limited consumer-side control | Strong queue-level controls | Not its primary purpose              |
| Retries                        | Yes                           | Yes                         | Depends on transport and destination |
| Typical use case               | OrderCreated event            | Generate invoice            | React to Cloud Storage upload        |

**Memory phrase.**

> "Pub/Sub = Distribute events."

> "Cloud Tasks = Execute commands."

> "Eventarc = Route events."

> "Fan-out -> Pub/Sub. Controlled work -> Cloud Tasks. Cloud events -> Eventarc."

***

## Field 1.4 — Application Design & Best Practices.

**16. What are the principles of a twelve-factor application? Which principles are particularly relevant when deploying
Spring Boot applications to Cloud Run?**

The twelve-factor methodology describes principles for building applications that are portable, scalable, and easy to
deploy in cloud environments.

Some of the most relevant principles for Spring Boot on Cloud Run are configuration, stateless process, disposability, and logs.

Configuration should be externalized through environment variables or a secret management service rather than embedded in
the application.

Processes should be stateless because Cloud Run can create or terminate instances dynamically. Sessions and persistent files
should be stored in external services such as Redis, Cloud Storage, or a database.

Disposability means the application should start quickly and shut down gracefully, which is particularly important for Java
applications because JVM and Spring Boot initialization can affect cold-start latency.

Logs should be written to standard output and standard error so Cloud Logging can collect them.

I would also follow the principle of separating build, release, and run stages and treating backing services as attached resources. 

**All twelve factors.**

| #  | Principle           | Meaning                                                    |
|----|---------------------|------------------------------------------------------------|
| 1  | Codebase            | One codebase tracked in version control, with many deploys |
| 2  | Dependencies        | Explicitly declare and isolate dependencies                |
| 3  | Config              | Store configuration in the environment                     |
| 4  | Backing services    | Treat databases, caches, and queues as attached resources  |
| 5  | Build, release, run | Strictly separate these stages                             |
| 6  | Processes           | Execute the application as stateless process               |
| 7  | Port binding        | Export services through a bound port                       |
| 8  | Concurrency         | Scale out using processes                                  |
| 9  | Disposability       | Fast startup and graceful shutdown                         |
| 10 | Dev/prod parity     | Keep development, staging, and production similar          |
| 11 | Logs                | Treat logs as event streams                                |
| 12 | Admin processes     | Run administrative tasks as one-off processes              |

**Memory phrase.**

> "Config outside, state outside, logs outside, start fast, shutdown gracefully."

> "3 - Config, 6 - Processes, 7 - Port binding, 9 - Disposability, 11 - Logs."

***

**17. What does it mean for an application to be stateless? Why is statelessness important for horizontal scaling,
and how would you handle user sessions and shared state?**

A stateless application doesn't depend on session or business state stored in a specific application instance between requests.

Each request provides enough context, such as an authentication token or session identifier, for any healthy instance to
process it using shared services when necessary.

Statelessness is important for horizontal scaling because requests can be distributed across multiple instances without
requiring sticky sessions.

It also improves resilience because if one instance fails, another can handle subsequent requests without losing essential
application state.

For user sessions, I would wither use stateless authentication, such as signed JWTs, or store server-side sessions in a
shared system like Redis.

Persistent business data would live in a database, while files would be stored in object storage.

For Spring Boot on Cloud Run, this approach allows instances to scale dynamically without depending on local memory or
disk for correctness.

**Memory phrase.**

> "Stateless = Any instance cn process the request."

> "Keep application instances disposable; keep essential state in shared, durable services."

***

**18. Your Cloud Run service communicates with a database and experiences connection exhaustion during traffic spikes.
What is causing this problem, and how would you solve it?**

Database connection exhaustion can occur when Cloud Run scales horizontally during traffic spikes.

Each Spring Boot instance typically maintains its own HikariCP connection pool. If the number of instances increases significantly,
the combined connection demand can exceed the database's maximum connection limit.

I would first investigate Cloud Run instance counts, active database connections, HikariCP pool metrics, connection acquisition
timeouts, and slow queries.

To mitigate the issue, I would calculate a connection budget and configure smaller connection pools per instance. I would
also set an appropriate Cloud Run maximum instance count to protect the database.

Depending on the database technology, I might introduce a connection pooler such as PgBouncer, optimize slow queries, or
reduce the time transactions hold connections.

Finally, I would load-test the application and monitor database utilization to verify that scaling remains stable.

The key principle is that application autoscaling must respect downstream database capacity.

**Memory phrase.**

> "Instances * Pool size <= Database connection budget."

> "Scale the application, but protect the database."

***

**19. How would you design a highly available Java application on Google Cloud that must continue operating during a regional outage?
What trade-offs would you consider?**

To design a highly available Spring Boot application that survives a regional outage, I would deploy the service across at
least two Google Cloud regions.

I would use a global external Application Load Balancer with regional backends and health checks to route traffic away 
from an unavailable region.

Since application availability also depends on data availability, I would design a cross-region database recovery strategy
using replication and failover.

I would first define the required recovery time objective, or RTO, and recovery point objective, or RPO, because these
determine whether active-passive or active-active architecture is appropriate.

For application layer, I would use stateless Cloud Run services, externalize sessions and files, and avoid region-specific
dependencies where possible.

I would also consider trade-offs involving cost, cross-region latency, data consistency, replication lag, and operational
complexity.

Finally, I would regularly test regional failover to verify that the system can actually meet its availability targets.

**Memory phrase.**

> "Multi-region compute + Multi-region data + Traffic failover + Tested recovery."

> "RTO determines how hast we recover. RPO determines how much data we can lose."

***

**20. You are designing an order-processing system using Spring Boot, Cloud Run, and Pub/Sub. How would you ensure scalability,
fault tolerance, and data consistency across multiple services?**

I would design the order-processing system using stateless Spring Boot services deployed on Cloud Run, with Pub/Sub for
asynchronous communication.

For scalability, Cloud Run would automatically scale services instances based on demand. I would configure concurrency
and maximum instances to avoid overwhelming downstream databases or external APIs.

For fault tolerance, I would use Pub/Sub's durable subscriptions, retries, and dead-letter topics. Consumers would
acknowledge messages only after successful processing and implement idempotency to handle duplicate deliveries.

For reliable event publication, I would use the Transactional Outbox pattern so that database changes and outgoing events
are recorded atomically.

For consistency across services, I would use a Saga pattern with local database transactions and compensating actions instead
of attempting one distributed transaction.

For example, if payment succeeds but inventory reservation fails, the workflow should initiate a refund or cancellation.

Finally, I would use distributed tracing, metrics, and alerts to monitor the entire order lifecycle.

| Decision                | Benefit                                     | Trade-off                                                   |
|-------------------------|---------------------------------------------|-------------------------------------------------------------|
| Cloud Run autoscaling   | Handles variable traffic                    | Clod starts and downstream capacity limits                  |
| Pub/Sub                 | Decouples services and buffers work         | Duplicate delivery and eventual consistency                 |
| Transactional Outbox    | Reliable event publication                  | Additional storage and publisher infrastructure             |
| Saga                    | Coordinates distributed business operations | Compensating and workflow complexity                        |
| Multi-region deployment | Improves regional resilience                | Higher cost and harder data failover                        |
| Strong consistency      | Simplifies some correctness guarantees      | May increase latency or reduce availability during failures |

**Memory phrase.**

> "Scalability -> Stateless Cloud Run + backpressure."

> "Fault tolerance -> Durable messaging + retries + idempotency."

> "Consistency -> Local transactions + Outbox + Saga."

> "Quorum protects replicated data consistency. Saga protects distributed business workflow consistency."

***

## Field 1.5 — Follow-up Challenges.

**21. A company has 15 Java Spring Boot microservices deployed on GKE. The services expose REST APIs, communicate through Pub/Sub,
and do not require custom Kubernetes networking or operators. The team spends significant time maintaining Kubernetes infrastructure.
Would you recommend migrating these services to Cloud Run? What benefits, limitations, and migration risks would you consider?**

Yes, I would seriously consider migrating these services to Cloud Run because the team spends significant time managing
Kubernetes and the applications don't appear to require Kubernetes-specific capabilities.

Cloud Run could reduce operational overhead by managing infrastructure, autoscaling, and deployments. It would also allow
services with variable traffic to scale down when idle.

However, I wouldn't migrate all 15 services immediately.

First, I would assess whether the services are stateless, how they consumer Pub/Sub messages, whether they require background
processing, and their CPU, memory, concurrency, and latency requirements.

I would also evaluate Spring Boot cold starts, database connection limits, security, networking, and the cost difference 
between Cloud Run and GKE.

Then I would select one low-risk service for a proof of concept, compare performance and operational costs, and validate
reliability under load.

If successful, I would migrate incrementally with monitoring and rollback plans.

My recommendation would be to move workloads that benefit from Cloud Run while retaining GKE for any services with genuine
Kubernetes-specific requirements.

**Cloud Run vs. GKE for this scenario.**

| Consideration                       | GKE                                                      | Cloud Run                                               |
|-------------------------------------|----------------------------------------------------------|---------------------------------------------------------|
| Infrastructure management           | More responsibility for cluster operations               | Much less infrastructure management                     |
| Autoscaling                         | HPA plus node capacity management, depending on GKE mode | Managed instance autoscaling                            |
| Scale to zero                       | Requires additional mechanisms for many workloads        | Supported for services                                  |
| Deployment                          | Kubernetes manifests, Helm, GitOps                       | Container deployment with revisions                     |
| Kubernetes customization            | Extensive                                                | Limited                                                 |
| Spring Boot REST APIs               | Good fit                                                 | Good fit                                                |
| Pub/Sub consumers                   | Flexible worker deployments                              | Good fit with appropriate push or pull execution model  |
| Always-running background processes | Natural fit                                              | Requires careful execution-model choice                 |
| Costs                               | Cluster and workload resources                           | Depends on requests, instance settings, and utilization |

**Memory phrase.**

> "No Kubernetes requirements -> Consider Cloud Run, but validate workload fit."

> "Assess -> Pilot -> Benchmark -> Migrate gradually -> Monitor -> Roll back if needed."

> "Choose the simplest platform that satisfies the workload's requirements - not the platform with most features."

***

**22. A Java microservice uses a continuously running Pub/Sub pull subscriber on GKE. Your team wants to migrate it to Cloud Run.
What architectural changes might be necessary?**

I would first check how the existing Pub/Sub pull subscribed runs on GKE.

If it continuously polls Pub/Sub in a background thread, moving it unchanged to a Cloud Run service with request-based CPU
allocation could cause processing to stop when there are no HTTP requests. The service could also scale to zero.

My preferred approach for a typical event-driven microservice would be to change the Pub/Sub subscription to push delivery
and expose an authenticated HTTP endpoint in Spring Boot.

This allows Cloud Run to scale on incoming messages without maintaining a continuously running pooling thread. 

I would ensure messages are processed idempotently, acknowledge them only after successful processing, and configure retries
and a dead-letter topic.

If the workload requires pull-specific flow control or continuous processing, I would evaluate Cloud Run worker pools or
instance-based billing with appropriate minimum instances.

Finally, I would load-test the new architecture and compare cost, latency, and reliability against the existing GKE deployments.

**Memory phrase.**

> "GKE worker = Always pooling. Cloud Run service = Request-driven."

> "Push for request-driven processing; continuous CPU or worker pools for long-running pull subscriptions."

***

**23. A Cloud Run service has a concurrency setting of 80 but experiences high response latency under load.
How would you determine whether concurrency is configured appropriately?**

I wouldn't assume the concurrency of 80 is correct without measuring the workload.

I would start by examining p95 and p99 response latency, request throughput, error rates, CPU and memory utilization,
Cloud Run instance counts, and database connection pool metrics.

Then I would run load tests reproduce realistic traffic patterns, including normal load and sudden spikes.

I would compare concurrency settings such as 10, 20, 40, and 80 while keeping the workload and other configuration consistent.

If high concurrency causes CPU saturation, thread contention, or database connection waits, reducing it may improve latency
by spreading requests across more instances.

However, lower concurrency can increase instance count, cost, cold starts, and total database connections.

I would choose the highest concurrency that consistently meets our latency and reliability objectives without overloading 
downstream dependencies.

| Metric                   | What it tells you                                  |
|--------------------------|----------------------------------------------------|
| p95/p99 latency          | Whether users experience slow responses under load |
| CPU utilization          | Whether too many requests compete for CPU          |
| Memory utilization / GC  | Whether JVM memory pressure affects response times |
| Instance count           | Whether Cloud Run scales as expected               |
| HikariCP pending threads | Whether requests wait for database connections     |
| Database query latency   | Whether the database is the actual bottleneck      |
| Error rate               | Whether timeouts or failures increase under load   |

**Memory phrase.**

> "Concurrency is not throughput. Measure latency, contention, scaling, and cost."

> "Higher concurrency -> Fewer instances, potentially more contention."

> "Lower concurrency -> More instances, potentially more downstream pressure."

***

**24. Your Spring Boot application takes 20 seconds to start. The business requires consistently low API latency.
How would you address this requirement without unnecessarily increasing infrastructure costs?**

First, I would profile the Spring Boot startup process to understand why it takes 20 seconds. I would investigate 
dependency initialization, database connectivity, component scanning, and any expensive startup operations.

I would optimize startup by removing unnecessary initialization, considering lazy beans where appropriate, and evaluating
JVM optimizations such as Class Data Sharing.

However, lazy initialization has a trade-off because it can shift startup latency to the first request.

On Cloud Run, I would evaluate startup CPU boost and configure a small number of minimum instances to reduce cold starts
during normal traffic.

I wouldn't immediately keep dozens of instances warm because that could be expensive. Instead, I would load-test normal
traffic and sudden spikes, then tune minimum instances, concurrency, and CPU allocation.

Finally, I would monitor p95 and p99 latency, startup time, cold-start frequency, and cost to find the best balance.

**Memory phrase.**

> "Optimize startup first, keep minimal warm capacity, then tune scaling." 

> "Lazy initialization reduces startup work, but may shift the delay to the first request."

***

**25. Your team proposes migrating every Kubernetes workload to Cloud Run to simplify operations. What types of workloads
would you challenge, and why?**

I would challenge the idea of migrating every Kubernetes workloads to Cloud Run because the right platform depends on the 
workload's requirement.

Cloud Run is a strong choice for stateless REST APIs and event-driven services, but I would examine stateful workloads,
continuously running background workers, and applications requiring Kubernetes-specific networking or scheduling.

For example, a service using persistent volumes, StatefulSets, or custom operators may be better suited to GKE.

I would also review workloads requiring specialized hardware, privileged containers, host-level access, or precise control
over pod placement.

For continuously running Pub/Sub consumers, I would evaluate Cloud Run worker pools or instance-based billing rather than
assuming a request-driven Cloud Run service is appropriate.

Finally, I would compare latency, scaling behavior, operational complexity, and cost. A high-utilization service running
continuously might be more economical on GKE.

I would recommend migration suitable workloads incrementally rather than enforcing a single compute platform for everything.

| Workload                         | Initial recommendation  | Reason                                            |
|----------------------------------|-------------------------|---------------------------------------------------|
| Stateless Spring Boot REST API   | Cloud Run               | Managed scaling and deployment                    |
| Pub/Sub push consumer            | Cloud Run               | Request-driven processing fits naturally          |
| Continuous Pub/Sub pull consumer | Evaluate both           | Worker pools or continuous CPU may fit            |
| Scheduled batch processing       | Cloud Run Jobs          | Designed for finite background tasks              |
| Kubernetes operator              | GKE                     | Requires Kubernetes API/control-plane integration |
| Custom networking daemon         | GKE                     | Requires low-level infrastructure access          |
| Always-busy Java service         | Benchmark both          | Cost depends on utilization and resources needs   |

**Memory phrase.**

> "State -> Execution model -> Infrastructure control -> Performance -> Cost."

> "Cloud Run by default for suitable managed workload; GKE when Kubernetes capabilities are justified."

***

# Week 2 — Security, IAM & Application Authentication

Focus: Identity and Access Management, service accounts, secrets, authentication, authorization, and secure application architecture.

## Field 2.1 — IAM & Service Accounts.

**26. What is the difference between authentication and authorization in Google Cloud?**

Authentication verifies the identity of a user or service, while authorization determines what actions that identity is
permitted to perform.

In Google Cloud, authentication can be performed using service accounts, OAuth 2.0 access tokens, or OIDC ID tokens, 
depending on the service and use case.

Authorization is typically enforced through IAM policies, roles, and permissions, following the principle of least privilege.

An authentication failure commonly results in HTTP 401 (Unauthorized) or the gRPC UNAUTHENTICATED status, whereas as
authorization results in HTTP 403 (Forbidden) or PERMISSION_DENIED.

For example, a Cloud Run application may successfully authenticate using its service account but still fail to publish 
a Pub/Sub message if the service account lacks the `pubsub.topics.publish` permission.

***

**27. Explain the principle of least privilege. How would you apply it to a Spring Boot application deployed on Cloud Run?**

The principle of least privilege means granting users and applications only the minimum permissions required to perform
their tasks. This reduces the attack surface and limits the blast radius if an identity is compromised.

Foa a Spring Boot application deployed on Cloud Run, I would create a dedicated service account and assign it to the Cloud
Run service.

I would grant only the necessary IAM roles, scoped to specific resources whenever possible. For example, it the application
needs to publish messages to a Pub/Sub topic, I would grant `roles/pubsub.publisher` on that topic rather than at the 
project level.

I would use Application Default Credentials (ADC) to authenticate to Google Cloud services without managing service account keys.

Additionally, I would use Spring Security to enforce application-level authorization, such as restricting access to specific
REST endpoints based on user roles.

This approach applies least privilege at both the cloud infrastructure and application levels.

| Feature     | Google Cloud IAM                               | Spring Security                                         |
|-------------|------------------------------------------------|---------------------------------------------------------|
| Controls    | Access to Google Cloud resources               | Access to application endpoints and business operations |
| Identity    | Service accounts, users, groups                | Application users and authenticated clients             |
| Permissions | `pubsub.topics.publish`, `storage.objects.get` | `ROLE_ADMIN`, `ROLE_USER`, application authorities      |
| Enforcement | Google Clous services                          | Spring Security filters and method security             |
| Example     | Can this service publish to Pub/Sub?           | Can this user cancel an order?                          |

***

**28. What is a Google Cloud service account, and how does it differ from a user account?**

A Google Cloud service account is a non-human identity used by applications, services, and automated workloads to authenticate
to Google Cloud APIs and resources.

Unlike a user account, which represents a human identity, a service account represents a workload such as a Cloud Run
service, a GKE application, or an automated CI/CD pipeline.

A service account is not a permission itself. Permissions are granted to it through IAM roles and policies.

For example, a Spring Boot application running on Cloud Run can use a dedicated service account with the 
`roles/pubsub.publisher` role to publish messages to a specific topic.

The application can authenticate using Application Default Credentials and short-lived credentials provided by the runtime,
without storing service account keys.

Using separate service accounts for different applications supports the principle of least privilege and reduces the blast 
radius if one workload is compromised.

| Feature        | Service Account                                  | User Account                          |
|----------------|--------------------------------------------------|---------------------------------------|
| Represents     | Application or workload                          | Human user                            |
| Typical usage  | Cloud Run, GKE, automation                       | Developer, administrator              |
| Identity       | `orders-service@project.iam.gserviceaccount.com` | `developer@example.com`               |
| Authentication | Workload credentials, short-lived tokens         | Interactive sign-in, user credentials |
| Authorization  | IAM roles and permissions                        | IAM roles and permissions             |
| Lifecycle      | Managed for a workload                           | Managed for a person                  |

***

**29. What are the differences between basic, predefined, and custom IAM roles? When would you create a custom role?**

Google Cloud IAM provides three categories of roles: basic, predefined, and custom.

Basic roles, such as Owner, Editor, and Viewer, provide broad permissions across Google Cloud services. They are generally
not recommended for application service accounts because they may violate the principle of least privilege.

Predefined roles are managed by Google and provide permissions for specific services or responsibilities. For example,
`roles/pubsub.publisher` allows publishing messages to Pub/Sub topics, while `roles/storage.objectViewer` provides read
access to Cloud Storage objects.

Custom roles are user-defined collections of supported IAM permissions. I would create a custom role when existing predefined 
roles grant more permissions than required and a more granular permission set is necessary.

Whenever possible, I would prefer predefined roles scoped to specific resources because they are easier to maintain and
follow Google's recommended access patterns. Custom roles should be used when there is a clear security or business
requirement for additional granularity.

| Role type  | Examples                                               | Characteristics                                                         |
|------------|--------------------------------------------------------|-------------------------------------------------------------------------|
| Basic      | `roles/owner`, `roles/editor`, `roles/viewer`          | Broad permissions across many Google Cloud services                     |
| Predefined | `roles/pubsub.publisher`, `roles/storage.objectViewer` | Google-managed roles designed for specific services or responsibilities |
| Custom     | `projects/my-project/roles/customPublisher`            | User-defined collections of supported IAM permissions                   |

***

**30. How does Application Default Credentials (ADC) work in a Java application running locally and on Cloud Run?**

Application Default Credentials (ADC) is a credential discovery mechanism used by Google Cloud client libraries to automatically
authenticate applications without explicitly configuring credentials in the application code.

ADC searches for credentials in a defined order: first the GOOGLE_APPLICATION_CREDENTIALS environment variable, then the
local ADC credentials file, and finally credentials provided by the Google Cloud metadata server.

During local development, developers typically run `gcloud auth application-default login` to generate local credentials associated
with their Google account. Alternatively, they can use service account impersonation.

On Cloud Run, ADC obtains short-lived credentials for the service account attached to the service through the metadata
server. This eliminates the need to store service account keys in the application.

The same Java application code can therefore run locally and in production without changing its authentication logic.
IAM roles assigned to the active identity determine which Google Cloud resources the application can access.

***

## Field 2.2 — Workload Identity & Service Authentication.

**31. How can a Spring Boot application deployed on Cloud Run authenticate to Pub/Sub without storing service account keys?**

A Spring Boot application running on Cloud Run can authenticate to Pub/Sub using Application Default Credentials (ADC) without
storing service account keys.

First, I would create a dedicated service account and attach it to the Cloud Run service. Then I would grant the service 
account the `roles/pubsub.publisher` IAM role on the specific topic it needs to access, following the principle of least
privilege.

When the application uses the Google Cloud Pub/Sub Java library, ADC automatically obtains short-lived access tokens for
the attached service account through the Cloud Run metadata server.

The client library uses these credentials to authenticate API requests, while Google Cloud IAM verifies whether the service
account has permission to publish messages.

This approach avoids long-lived service account keys and provides secure, automatically managed authentication.

***

**32. What is Workload Identity Federation, and what security problems does it solve?**

Workload Identity Federation is a Google Cloud authentication mechanism that allows external workloads to access Google
Cloud resources using identities from trusted external identity providers, without storing long-lived service account keys.

It supports scenarios such as GitHub Actions, AWS, Azure, and workloads running outside Google Cloud.

The external workload obtains credentials from its identity provider and exchanges them through Google Cloud Security Token
Service for short-lived federated credentials. The workload can then access supported Google Cloud resources directly or
impersonate a Google Cloud service account, depending on the configuration.

The main security benefit is eliminating long-lived service account keys, which reduces the risk of credentials leakage
and removed the need for manual key distribution and rotation.

Workload Identity Federation can aso be combined with IAM roles adn attribute-based conditions to restrict access to
specific workloads, repositories, or environments.

For example, GitHub Actions can use OIDC-based Workload Identity Federation to deploy a Sping Boot application to Cloud Run
without storing a Google Cloud service account JSON key in GitHub secrets.

| Security Problem                  | How WIF helps                                              |
|-----------------------------------|------------------------------------------------------------|
| Long-lived service account keys   | Uses short-lived credentials instead                       |
| Credential leakage                | Avoids disrupting private Google Cloud keys                |
| Manual key rotation               | Removes the need to rotate downloaded service account keys |
| Identity management across clouds | Reuses trusted external identities                         |
| Excessive access                  | Supports IAM conditions and restricted identity mappings   |

***

**33. How would you securely authenticate communication between two private Cloud Run services?**

To Securely authenticate communication between two private Cloud Run services, I would use Google Cloud IAM and OIDC ID tokens.

First, I would assign a dedicated service account to the calling Cloud Run service and grant it the `roles/run.invoker` 
role on the target service.

When making an HTTP request, the calling application obtains a Google-signed ID token through the Cloud Run metadata server,
setting the target service URL as the token audience.

The application sends the ID token in the `Authorization: Bearer` header over HTTPS. Cloud Run validates the token and checks
whether the calling service account has permission to invoke the target service.

This approach provides service-to-service authentication without storing service account keys or managing mTLS certificates
manually. I would also configure ingress restrictions where network-level access controls are required.

***

**34. What is the difference between IAM-based authentication and application-level authorization?**

IAM-based authentication and authorization control access to Google Cloud resources, while application-level authorization
controls what an authenticated caller can do inside an application.

For example, when one private Cloud Run service calls another, the caller sends a Google-signed OIDC ID token. Cloud Run
authenticates the caller and uses IAM to verify that its service account has the `roles/run.invoker` role on the target Service.

However, this permission only allows the caller to invoke the service. It does not determine which business operations the
caller is allowed to perform.

Application-level authorization is typically implemented using Spring Security, JWT scopes, roles, or business-specific
access-control rules. For example, a user might be allowed to view payments but not issue refunds.

Both layers are important: IAM protects access to cloud resources and service boundaries, while application-level authorization
enforces fine-grained business permissions.

***

**35. A Java application running on GKE needs to access BigQuery. How would you configure workload identity 
and permissions securely?**

I would use Workload Identity Federation for GKE to allow the Java application to authenticate to BigQuery without storing
service account keys.

First, I would enable Workload Identity Federation for GKE and create a dedicated Kubernetes ServiceAccount for the application.
I would configure the Pod to run using that ServiceAccount.

Next, I would grant the workload identity only the required BigQuery IAM permissions. For a read-only application that executes
queries, I would typically grant `roles/bigquery.dataViewer` on the specific dataset and `roles/bigquery.jobUser` on the
project where query jobs are created.

The Java application would use the BigQuery client library with Application Default Credentials. The GKE metadata server
would provide credentials for the workload identity automatically.

Where supported, I would use direct federated principal access. Alternatively, I could configure the Kubernetes ServiceAccount
to impersonate a dedicated Google Cloud IAM service account.

This approach avoids long-lived credentials, isolates permissions between workloads, and follows the principle of least privilege.

***

**36. What is Secret Manager, and why is it preferable to storing credentials in environment variables or source code?**

Google Cloud Secret Manager is a managed service for securely storing and accessing sensitive information such as database
passwords, API keys, and certificates.

It provides encryption, IAM-based access control, secret versioning, rotation support, and audit logging.

Secret Manager is preferable to storing credentials directly in source code or plaintext deployment configuration because
it reduces the risk of accidental exposure and allows access to be centrally controlled and audited.

For example, a Spring Boot application running on Cloud Run can use its dedicated service account and Application Default
Credentials to retrieve a secret. I would grant the `roles/secretmanager.secretAccessor` role only on the specific secret 
the application needs.

Secret Manager also supports integration with Cloud Run through environment variables or mounted files, so applications do
not need to embed sensitive values in container images or source code.

***

**37. How would you integrate Secret Manager with a Spring Boot application?**

I would integrate Google Secret Manager with Spring Boot using Spring Cloud GCP Secret Manager, the Secret Manager Java 
client library, or Cloud Run's native secret injection.

For a typical Spring Boot application, I would use the Spring Cloud GCP Secret Manager starter and reference secrets in
`application.yml` using the `sm://` syntax. This allows secrets to be injected into Spring components without hardcoding
sensitive values.

The application would authenticate using Application Default Credentials. On Cloud Run, ADC uses the service account attached
to the service, so no service account JSON keys are required.

I would grant the service account the `roles/secretmanager.secretAccessor` role on only the secrets it needs.

Alternatively, Cloud Run can expose Secret Manager secrets as environment variables or mounted files.

I would also consider secret rotation: secrets resolved at application startup may require a restart or refresh, while
programmatic retrieval can support dynamic updates.

| Approach                        | How it works                                              | Best suited for                                      |
|---------------------------------|-----------------------------------------------------------|------------------------------------------------------|
| Spring Cloud GCP Secret Manager | Integrates secrets into Spring's configuration system     | Database passwords, API keys, application properties |
| Secret Manager Java client      | Retrieves secrets programmatically using Google's API     | Dynamic retrieval, custom caching, rotation handling |
| Cloud Run secret injection      | Exposes secrets as environment variables or mounted files | Simple configuration with minimal Java changes       |

***

**38. What are the differences between accessing secrets through environment variables and mounted volumes in Cloud Run?**

In Cloud Run, Secret Manager secrets can be exposed either as environment variables or as mounted files.

When using environment variables, Cloud Run retrieves the secret when a container instance starts and makes its value
available through the process environment. This is convenient for Spring Boot configuration properties, but running instances
do not automatically receive updated secret values after rotation.

When using mounted volumes, Cloud Run exposes the secret as a file in the container filesystem. The application reads the file
when needed. If the volume references the `latest` secret version, subsequent file reads can retrieve an updated version
without restarting the instance.

Mounted volumes are particularly useful for certificates, private keys, and frequently rotated secrets, while environment
variables are simpler for relatively static configuration.

Both methods use Secret Manager and should be protected with least-privilege IAM permissions, typically 
`roles/secretmanager.secretAccessor` on the specific secret.

| Feature                       | Environment variable                             | Mounted Volume                                    |
|-------------------------------|--------------------------------------------------|---------------------------------------------------|
| Access method                 | `System.getenv()`                                | Read a file                                       |
| Secret retrieval              | At instance startup                              | When the file is read                             |
| Secret rotation               | Existing instances retain their value            | Can retrieve updated versions on subsequent reads |
| Recommended version reference | Pin a specific version                           | `latest` can support rotation                     |
| Startup behavior              | Instance startup fails if secret retrieval fails | Instance may start, but file reads can fail       |
| Typical use case              | API keys, static configuration                   | Certificates, files, rotating secrets             |

***

**39. How would you implement secret rotation without disrupting application availability?**

To rotate secrets without disturbing application availability, I would use a staged rotation strategy with overlapping credentials.

First, I would create a new credentials in the target system and store it as a new version in Google Cloud Secret Manager,
while keeping the old credentials valid.

Next, I would update the application to use the new secret. For a Spring Boot application on Cloud Run, I could either implement
dynamic secret refresh or deploy a new Cloud Run revision configured with the new secret version.

For a revision-based approach, I would verify the health of the new revision and gradually shifty traffic to it, monitoring
authentication failures, error rates, and latency.

Once all instances and dependent credentials are using the new credentials, I would revoke the old credentials.

The key is to avoid revoking the old credentials before the new one is fully deployed and validated. I would also ensure that
components, such as database connection pools or HTTP clients reload their credentials correctly.

***

**40. Your application accidentally logs an authentication token. What immediate actions would you take, and how would
you prevent similar incidents?**

If an application accidentally logs an authentication token, I would immediately treat the token as compromised and revoke
or invalidate it where possible. Depending on the credential type, I might also need to rotate the underlying credential or
temporarily restrict the affected identity's permissions.

Next, I would fix the logging implementation to prevent further exposure and restrict access to the affected logs.

I would perform a security audit to determine who had access to the logs, whether the token was accessed or used, and
which resources might have been compromised.

I would also remediate the affected logs and any exported copies while preserving necessary incident evidence.

To prevent similar incidents, I would avoid logging sensitive information such as authentication tokens, passwords, and
authorization headers. Where request logging is necessary, I would use allowlist-based logging and centralized 
sensitive-data masking.

Finally, I would implement automated security tests, least-privilege access to Cloud Logging, and monitoring for accidental
credential exposure.

***

| Question                      | Key takeaways                                                  |
|-------------------------------|----------------------------------------------------------------|
| Q36 - Secret Manager          | Centralized, encrypted, IAM-controlled secret storage          |
| Q37 - Spring Boot integration | Spring Cloud GCP, ADC, native secret injection                 |
| Q38 - Secret injection        | Environment variables ar startup; mounted volumes at read time |
| Q39 - Secret rotation         | Overlapping credentials, dynamic refresh, revision rollout     |
| Q40 - Credential leakage      | Revoke first, contain, investigate, remediate, prevent         |

***

## Field 2.4 — Networking & API Security.

**41. What is the difference between public and private Cloud Run services?**

A public Cloud Run service allows unauthenticated requests, whereas a private Cloud Run service requires callers to be
authenticates and authorized through IAM.

Cloud Run provides two independent security mechanisms: ingress configuration and IAM-based access control.

Ingress configuration determines where requests can originate from, while IAM determines which identities are authorized to
invoke the service.

A private Cloud Run service can still be reachable from the public internet if its ingress is configured to allow all traffic.
However, callers must provide a valid identity token and have the necessary invocation permissions, typically through the
`roles/run.invoker` role.

For internal microservices, I would combine restricted ingress with IAM-based authentication and dedicated service accounts
to achieve defense in depth.

***

**42. How would you restrict access to a Cloud Run API so that only authorized internal services can invoke it?**

I would restrict access to the Cloud Run API using a combination of internal ingress configuration and IAM-based authentication
and authorization.

First, I would configure the Cloud Run service with internal ingress and require authenticated invocation.

Next, I would assign dedicated service accounts to the calling service and grant the `roles/run.invoker` role only to
authorized service accounts at the target service level, following the principle of least privilege.

I would also configure appropriate VPC routing to ensure that requests from the calling services are recognized as internal traffic.

The calling services would obtain Google-signed OIDC ID tokens using their service account identities and include them in
the HTTP Authorization header.

This approach provides defense in depth by ensuring that only authenticated, authorized services originating from permitted
internal network sources can invoke the API.

| Requirement                      | Google Cloud mechanism  |
|----------------------------------|-------------------------|
| Restrict where request originate | Internal ingress        |
| Restrict who can invoke the API  | IAM                     |
| Identify the calling workload    | Service account         |
| Authorize invocation             | `roles/run.invoker`     |
| Authenticate HTTP requests       | OIDC ID token           |
| Enable internal communication    | Appropriate VPC routing |

***

**43. What is the difference between ingress controls, egress controls, and IAM authorization in Cloud Run?**

Ingress controls, egress controls, and IAM authorization address different aspects of Cloud Run security.

Ingress controls determine where incoming requests can originate from, such as the public internet, internal networks, 
or an external Application Load Balancer.

Egress controls determine how outbound traffic from a Cloud Run service is routed. Using Direct VPC egress or Serverless 
VPC Access, I can route traffic through a VPC and apply network security policies to restrict access to specific destinations.

IAM authorization determines which authenticated identities are permitted to perform specific operations. For example, the
`roles/run.invoker` role allows an authorized identity to invoke a Cloud Run service.

These mechanisms complement each other and can be combined to provide defense in depth.

| Mechanism         | Question it answers                         |
|-------------------|---------------------------------------------|
| Ingress           | Where can incoming requests originate from? |
| Egress            | How and where can outgoing traffic travel?  |
| Authentication    | Who is making the request?                  |
| IAM authorization | What is that identity allowed to do?        |

***

**44. How would you securely connect a Cloud Run application to a private database through a VPC?**

To securely connect a Cloud Run application to a private Cloud SQL database, I would configure the database with a private
IP address and establish VPC connectivity using Direct VPC egress or Serverless VPC Access.

I would assign a dedicated service account to the Cloud Run application and grant it the `roles/cloudsql.client` role
when using Cloud SQL Auth Proxy or the Cloud SQL Java Connector.

For a Spring Boot application, I would prefer the Cloud SQL Java Connector because it provides encrypted connections
and IAM-based connection authorization without requiring a separate proxy process.

For database authentication, I would use credentials stored in Secret Manager or automatic IAM database authentication.

Finally, I would apply appropriate network security controls, least-privilege database permissions, and connection pooling.

This approach allows the application to communicate securely with the database without exposing it to the public internet.

| Feature                                        | VPC egress        | Cloud SQL Auth Proxy  | Java Connector  |
|------------------------------------------------|-------------------|-----------------------|-----------------|
| Provides private network connectivity          | Yes               | No                    | No              |
| Provides encrypted database connections        | No, not by itself | Yes                   | Yes             |
| IAM-based connection authorization             | No                | Yes                   | Yes             |
| Requires a separate process                    | No                | Typically yes         | No              |
| Supports automatic IAM database authentication | No                | Yes                   | Yes             |

> "IAM permissions do not establish network connectivity, and network connectivity does not grant IAM permissions. 
Cloud SQL Auth Proxy is not a VPN or a VPC connector."

***

**45. What are the roles of HTTPS, OAuth 2.0, OpenID Connect, and JWT in securing cloud-native applications?**

HTTPS, OAuth 2.0, OpenID Connect, and JWT address different aspects of cloud-native application security.

HTTPS provides secure communication through TLS, ensuring confidentiality, integrity, and server authentication.

OAuth 2.0 is a delegated authorization framework that allows clients to access protected resources using access
tokens without sharing the resource owner's credentials.

OpenID Connect is an authentication layer built on top of OAuth 2.0. It allows applications to verify the identity of an
authenticated subject using ID tokens.

JWT is a standardized token format used to represent claims such as identity, issuer, audience, expiration, and optionally
authorization information. Applications must validate the token's signature and relevant claims before trusting it.

In Google Cloud, I would typically use OAuth 2.0 access tokens to access Google Cloud APIs and Google-signed OIDC ID tokens
for authenticated service-to-service communication with Cloud Run.

| Feature               | Access token                           | ID token                                     |
|-----------------------|----------------------------------------|----------------------------------------------|
| Primary purpose       | Access protected resources             | Assert an authenticated identity             |
| Standard              | OAuth 2.0                              | OpenID Connect                               |
| Format                | JWT or opaque                          | JWT                                          |
| Google Cloud use case | Pub/Sub, BigQuery, Secret Manager APIs | Authenticated Cloud Run invocation           |
| Authorization         | Scopes and resource-specific policies  | Target service separately checks permissions |

***

| Question                            | Key takeaway                                                                          |
|-------------------------------------|---------------------------------------------------------------------------------------|
| Q41 -Public vs private Cloud Run    | Network accessibility and authenticated invocation are independent                    |
| Q42 - Internal API security         | Internal ingress + IAM + ID token                                                     |
| Q43 - Network controls              | Ingress, egress and IAM serve different purposes                                      | 
| Q44 - Private database connectivity | VPC connectivity + secure connector + database authentication                         |
| Q45 - Security protocols            | HTTPS protects transport, OAuth authorizes, OIDC authenticates, JWT represents claims |

***

## Field 2.5 — Follow-up Challenges.

**46. A Cloud Run service has the Editor role at the project level but only needs to publish Pub/Sub messages.
How would you improve its security configuration?**

The Editor role is a basic IAM role that grants excessively broad permissions and violates the principle of least privilege
in this scenario.

I would create a dedicated service account for the Cloud Run service and grant it the predefined `roles/pubsub.publisher`
role at the specific Pub/Sub topic level rather than at the project level.

I would configure Cloud Run to use this service account and rely on Application Default Credentials for authentication,
without using service account JSON keys.

After validating that the application can successfully publish messages, I would remove the unnecessary Editor role, taking
into account any other workloads that might depend on the original service account.

This approach reduces the attack surface and limits the potential blast radius if the application is compromised.

| Role                      | Scope of permissions                    | Suitable?  |
|---------------------------|-----------------------------------------|------------|
| `roles/editor`            | Broad resource modification permissions | No         |
| `roles/pubsub.admin`      | Full Pub/Sub administration             | No         |
| `roles/pubsub.editor`     | Broad Pub/Sub modification permissions  | No         |
| `roles/pubsub.subscriber` | Consume messages                        | No         |
| `roles/pubsub.publisher`  | Publish messages                        | Yes        |

| Dimension      | Question                                            |
|----------------|-----------------------------------------------------|
| Role selection | What operations can the identity perform?           |
| Resource scope | On which resources can it perform those operations? |

***

**47. A developer downloads a service account JSON key to connect a local Java application to BigQuery.
What risks does this introduce, and what alternatives would you recommend?**

Downloading a service account JSON key introduces security risks because it contains a long-lived private key that could
be accidentally exposed through source code repositories, logs, backups, or insecure local storage.

If the key is compromised, an attacker could authenticate as the service account and access resources according to its 
IAM permissions.

For local Java development, I would recommend using Application Default Credentials with the developer's Google account 
or service account impersonation.

Service account impersonation allows the developer to obtain short-lived access tokens without downloading or managing
private keys.

I would also apply the principle of least privilege by granting only the necessary BigQuery permissions at the appropriate
resource scope.

For production workloads, I would use attached service accounts or Workload Identity Federation.

Although Secret Manager can securely store credentials, I would prefer eliminating service account keys entirely rather than
managing them in a secret store.

> "Whenever a certification question proposes storing, distributing, or rotating service account JSON keys, 
first consider whether you can eliminate those keys entirely."

***

**48. Two Cloud Run services communicate through HTTP. The receiving service must reject requests from unauthorized callers.
Design a secure service-to-service authentication mechanism.**

I would implement secure service-to-service authentication using dedicated service accounts, IAM authorization, Google-signed
OIDC ID tokens, and HTTPS.

First, I would configure the receiving Cloud Run service to require authenticated invocation.

Next, I would grant the calling service's dedicated service account the `roles/run.invoker` role on the receiving service,
following the principle of least privilege.

The calling application would obtain a short-lived OIDC ID token using its service account identity, with the receiving
service URL as the token audience.

It would include the token in the Authorization header of each HTTPS request.

Cloud Run would validate the token and verify the caller's IAM permissions before forwarding the request to the application.

This approach provides encrypted communication, keyless authentication, and identity-based authorization without requiring
custom mTLS infrastructure of long-lived service account keys.

> Service account -> Workload identity.

> OIDC ID token -> Authentication.

> `roles/run.invoker` -> Authorization.

> HTTPS -> Transport security.

> Internal ingress (optional) -> Network isolation. 

> Remember: for standard Cloud Run service-to-service authentication, think OIDC + IAM + HTTPS first, not mTLS.

***

**49. Your application must access a third-party API using a rotating API key. How would you manage the key throughout
development, testing, and production?**

I would use Google Cloud Secret Manager to securely store and manage third-party API keys.

I would maintain separate API keys and secrets for development, testing, and production to ensure environment isolation.

Each application would use a dedicated identity and receive the `roles/secretmanager.secretAccessor` role only on the 
required secret.

I would use Application Default Credentials to access Secret Manager without introducing additional service account keys.

To automate rotation, I would configure a Secret Manager rotation schedule and use Pub/Sub notifications to trigger a 
dedicated rotation service.

The rotation service would generate a new API key at the third-party provider, store it as a new secret version, and allow
the application to refresh its credentials.

After validating the new key and ensuring that all application instances have switched to it, I would revoke the previous key.

Finally, I would enable audit logging and monitor authentication failures to detect potential security or rotation issues.

| Responsibility               | Solution                                   |
|------------------------------|--------------------------------------------|
| Secure storage               | Secret Manager                             |
| Authentication               | ADC                                        |
| Access control               | IAM                                        |
| Environment isolation        | Separate secrets and identities            |
| Credential history           | Secret versioning                          |
| Rotation trigger             | Rotation schedule + Pub/Sub                |
| Actual credential rotation   | Custom rotation service                    |
| Availability during rotation | Credential overlap and application refresh |
| Security monitoring          | Audit logs                                 |

> Secret storage is only one part of secret management. A complete solution must address the entire credential lifecycle:
creation, storage, access, rotation, validation, revocation, and auditing.

***

**50. A Spring Boot application deployed on GKE receives 403 Permission Denied errors when accessing Cloud Storage.
Describe your troubleshooting process.**

A 403 Permission Denied error typically indicates an authorization failure or another security policy restrictions.

I would first inspect the application logs to identify the failed Cloud Storage operation, the required permission, and the
target resource.

Next, I would verify which Kubernetes ServiceAccount the application is using and ensure that Workload Identity Federation 
for GKE is configured correctly.

I would check whether the application uses Application Default Credentials and whether the actual workload identity has the
required IAM permissions on the target Cloud Storage bucket.

For example, if the application only needs to read objects, I would grant the `roles/storage.objectViewer` role at the bucket
level.

I would use Cloud Audit Logs and IAM Policy Troubleshooter to identify the actual caller and the reason for the authorization
failure.

If the IAM configuration is correct, I would investigate additional restrictions such as IAM deny policies and VPC Service 
Controls.

Finally, I would apply the minimum required configuration change and verify that the application can successfully access
Cloud Storage without introducing excessive permissions.

**Essential cheat sheet**

| Scenario                                     | Preferred solution                                              |
|----------------------------------------------|-----------------------------------------------------------------|
| Local Java authentication                    | ADC with user credentials                                       |
| Local testing as a service account           | Service account impersonation                                   |
| External CI/CD authentication                | Workload Identity Federation                                    |
| GKE accessing Google Cloud APIs              | Workload Identity Federation for GKE                            |
| Cloud Run accessing Google Cloud APIs        | Attached service account + ADC                                  |
| Cloud Run invoking another Cloud Run service | OIDC ID token + `roles/run.invoker`                             |
| Publishing Pub/Sub messages                  | `roles/pubsub.publisher`                                        |
| Reading Cloud Storage objects                | `roles/storage.objectViewer`                                    |
| Reading secrets                              | `roles/secretmanager.secretAccessor`                            |
| Private Cloud SQL connectivity               | Direct VPC egress + private IP + secure database authentication |
| Third-part API credentials                   | Secret Manager + automated rotation                             |
| Static outbound public IP                    | VPC egress + Cloud NAT                                          |
| IAM troubleshooting                          | Actual principal + permission + resource + audit logs           |

***






