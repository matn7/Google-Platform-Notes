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























