## Event Driven Microservices.

**1. Each microservice owns its own dedicated database.**
* Other services cannot access it directly; data is exposed only via APIs, ensuring loose coupling and autonomy.

**2. Key benefits of Database-per-Service?** 
* Loose coupling, independent scaling, faster development, fault tolerance, technology freedom, and improved security.

**3. Main challenges of Database-per-Service?** 
* Cross-service queries, data consistency, complex distributed transactions, and data duplication.

**4. Why are cross-service queries hard in microservices?** 
* Data is distributed across multiple databases, making joins across services impossible without aggregation strategies.

**5. What is the API Composition pattern?** 
* A pattern where an API Composer calls multiple services that own the data and combines their responses into one result.

**6. Who can act as an API Composer?** 
* (1) Client application, (2) API Gateway, or (3) Dedicated composition service.

**7. Limitations of API Composition?** 
* Increased latency, complex aggregation logic, error handling issues, data consistency challenges, limited scalability, 
service dependency, and testing complexity.

**8. What is the Gateway Aggregator pattern?**
* An API Gateway aggregates responses from multiple backend services and returns a single consolidated response to the client.

**9. Why is data consistency difficult in microservices?**
* Business transactions often span multiple services and databases, making atomic commits impossible without coordination.

**10. What is the Saga Pattern?**
* A pattern for managing distributed transactions by breaking them into local transactions coordinated through events 
and compensating actions.

**11. What happens when a Saga step fails?** 
* Previously completed steps are rolled back using compensating transactions executed in reverse order.

**12. Two ways to implement Saga?** 
* Choreography (event-driven, decentralized) and Orchestration (central saga manager).

**13. Why does data duplication occur in microservices?** 
* Multiple services need the same reference data (e.g., customer info), leading each service to store its own copy.

**14. How does event-driven architecture help with data duplication?**
* Services publish events on data changes; other services subscribe and update their local copies asynchronously.

**15. What is CQRS?**
* Command Query Responsibility Segregation separates write operations (commands) from read operations (queries).

**16. Advantages of CQRS?**
* Independent scaling, optimized read/write models, flexibility in projections, and compatibility with event sourcing.

**17. Disadvantages of CQRS?**
* Increased complexity, eventual consistency, and higher development overhead.

**18. What is Event Sourcing?**
* A pattern where state changes are stored as immutable events instead of saving only the latest state.

**19. Difference between a Command and an Event?**
* A Command represents intent to change state; an Event represents something that already happened.

**20. What is an Event Store?**
* A database that stores events in append-only order and allows replaying events to rebuild state.

**21. What is an Aggregate?**
* A domain object that encapsulates state and behavior, applies events, and enforces business rules.

**22. What are Projections?**
* Read-only models built by processing event streams, optimized for querying and reporting.

**23. Key benefits of Event Sourcing?**
* Full audit trail, replayability, scalability with CQRS, and flexible read models.

**24. When to use CQRS with single read/write model?**
* Small to medium systems needing simplicity, strong consistency, and low complexity.

**25. Benefit of different read/write models with one DB?**
* Optimized queries and writes with strong consistency and moderate complexity.

**26. Trade-offs of separate read/write databases?**
* High scalability and performance, but high complexity and eventual consistency.

**27. Why combine CQRS with Event Sourcing?**
* Event streams ensure ordering and consistency while enabling scalable, decoupled read models.

**28. What is CDC?**
* A technique that captures database changes (insert, update, delete) from transaction logs and publishes them as events.

**29. Benefits of CDC in microservices?**
* Loose coupling, asynchronous communication, event-driven workflows, and improved scalability.

**30. Example of a CDC tool?**
* Debezium, commonly used with Apache Kafka.

**31. What is the Materialized View pattern?**
* A pattern that stores a precomputed, denormalized view of data optimized for fast read queries.

**32. How are materialized views kept up-to-date?**
* By subscribing to domain events and updating the view asynchronously.

**33. When should you use a materialized view?**
* Read-heavy systems needing aggregated data from multiple microservices.

**34. What problem does the Transactional Outbox solve?**
* Ensures reliable event publishing by writing business data and events in the same database transaction.

**35. How does the Transactional Outbox work?**
* Data is written to business tables and an outbox table atomically; a separate process publishes events from the outbox.

**36. Why combine Outbox with CDC?**
* CDC reads database logs instead of polling, improving efficiency and scalability.

**37. Key difference between Materialized View and Outbox?**
* Materialized View optimizes reads; Outbox ensures reliable event publishing.

**38. What is a snapshot?**
* A saved state of an aggregate at a point in time to avoid replaying all historical events.

**39. When should snapshots be used?**
* High event volumes, performance concerns, and faster recovery needs.

**40. Trade-offs of snapshots?**
* Extra storage, choosing snapshot frequency, and ensuring snapshot integrity.