# Event Driven Microservices with CQRS, Saga, Event Sourcing.

**Agenda**
1. Database-per-service pattern.
2. API composition pattern.
3. Command Query Responsibility Segregation (CQRS).
4. Event Sourcing Pattern.
5. Materialized View Pattern.
6. Transactional Outbox Pattern.
7. Saga Pattern.

## Database-per-service pattern.

The Database-per-service pattern is a microservice architecture approach where each microservice is assigned its own
dedicated database. This pattern enhances separation of concerns, ensuring data isolation, loose coupling, decentralized
data management, technology diversity and improves scalability etc.
In a microservice architecture, services are intended to be small, specialized, and independent, each handling a specific
function. To uphold this separation, it's crucial that each microservice independently manages its data. 
The Database-per-service pattern supports this by assigning a distinct database to each microservice.
Implementing the Database-per-service pattern allows you to select the most suitable data stores (such as relational or
non-relational databases) based on your application and business needs.
This approach ensures that microservices do not share a data layer, meaning changes to a microservice's database don't
affect others. Each microservice's data store is inaccessible to other services directly, and persistent data is accessed
solely through API.
By decoupling data stores, the overall resiliency of your application is enhanced, preventing any single database from
becoming a single point of failure.

**Benefits**
* Loose Coupling.
* Independent Scaling.
* Faster development.
* Resilience, Fault tolerance.
* Freedom to choose different databases.
* Prevents unauthorized access to another service's data.

**Challenges.**
* Cross-Service Queries: Joining data across services is not straightforward. This often requires additional efforts like
data replication or using an API to aggregate data.
* Data Consistency: Ensuring data consistency across services can be challenging, especially when a business process spans
multiple services.
* Complex Transactions: Distributed transactions across multiple services and their databases are difficult to manage.
Techniques like Sage patterns or eventual consistency are often used.
* Data Duplication: Sometimes, the same data needs to be stored in multiple services, leading to data duplication and
challenges in keeping this data synchronized.

### Cross-Service Queries.

In a monolithic architecture, complex queries that join data from multiple tables are straightforward. However, in
microservices with the Database-per-service pattern, performing queries that span multiple services becomes complex 
because the data is distributed across different databases. For example, inside bank microservices, the consumer might
want to see a summary of his data including the personal information, Accounts, Loans and Cards data. Joining data across
these services to provide a combined search result is challenging.

![DB per microservice - cross-service queries](flashcard-img/database-per-service-cross-queries.png "DB per microservice - cross-service queries")

The Cross-Service Queries challenge can be addressed by using the following approaches.
* API composition pattern: This is the simplest approach and should be used whenever possible. It works by making the 
clients responsible for invoking the services that own the data and then combining the results.
* CQRS pattern: Command Query Responsibility Segregation (CQRS) pattern is more powerful but also more complex. It involves
maintaining one or more view databases whose sole purpose is to support queries, making it easier to retrieve and combin
data from multiple services.

#### API composition pattern.

The API composition pattern handles query operations by calling the services that own the required data and then combining
their responses. There are two key participants in this pattern:
* API Composer: This component executes the query by interacting with the provider services.
* Provider Service: This is the service responsible for managing part of the data returned by the query.

While implementing this pattern, one has to decide who plays the role of API Composer. In general, one end up choosing
any of the below 3 options:
1. Client like frontend web application itself acts as an API composer.
2. The API gateway can act as an API composer.
3. A standalone service can act as an API composer.

It is a best practice for the API composer component to leverage the Reactive programming model to make the dependent 
service invocations parallely with minimum number of threads, memory etc.

While the API composition pattern offers a straightforward and intuitive approach to implementing query operations in a
microservice architecture, it codes come with several drawbacks:
1. Increased Latency.
2. The logic of composing the results can become complex.
3. Error Handling.
4. Data Consistency.
5. Limited Scalability.
6. Dependency on Service Availability.
7. Testing Challenges.

#### Gateway Aggregator / Composition pattern.

In microservices architecture, a Gateway Aggregator or Gateway Composition pattern is used when a request from a client
needs to retrieve or process data from multiple backend microservices. Instead of having the client make multiple calls
to various microservices, the API Gateway consolidates the requests into a single response.

![Gateway Aggregator/Composition pattern](flashcard-img/gateway-aggregator.png "Gateway Aggregator/Composition pattern")

#### Data Consistency & Complex Transactions.

Consider an e-commerce platform with separate microservices for orders, payments, and inventory. When a customer places 
an order, the system needs to:
* Create an order in the Order Service.
* Deduct the payment in the Payment Service.
* Update the inventory in the Inventory Service.

If any these steps fails (e.g. payment fails after the order is created), the system must ensure that the data remains 
consistent across all services. Without a sharded database, this consistency is difficult to achieve. 

![Data Consistency](flashcard-img/data-consistency-and-complex-transactions.png "Data Consistency")

In a banking application, consider a credit card payment from a saving account. Ensuring that both the debit and credit 
operations are executed as a single atomic transaction is challenging because they involve different services and databases.

![Data Consistency](flashcard-img/data-consistency-and-complex-transactions-bank.png "Data Consistency")

The Data consistency and complex transactions challenges can be addressed by using the following pattern.
**Saga Pattern**
* The Saga pattern can be used to manage distributed transactions. Each microservice executes a local transaction and 
publishes an event to trigger the next step. If a step fails, the previous steps are compensated.
* For example, if payment fails after an order is created the Saga pattern can automatically trigger a cancellation of the
order.

### Data duplication.

In microservices, some data might need to be available to multiple services, leading to data duplication. For example, in
a bank system, the accounts, loans and cards microservices need customer data. If three services maintain their own copies
of customer data, ensuring the updates to customer information (e.g. a change in mobile number) are reflected in all 
services becomes complex.

![Data Duplication](flashcard-img/data-duplication.png "Data duplication")












