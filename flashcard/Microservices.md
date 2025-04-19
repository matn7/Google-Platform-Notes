# Master Microservices with Spring Boot, Docker, Kubernetes.

## What are Microservices?

To understand microservices, lets imagine a bank called EazyBank. Typically, banks comprise various departments, including
Accounts, Cards, and Loans.

## The Monolith.

Back a decade, all the applications used to be deployed as a Single unit where all functionality deployed together inside a single
server. We call this architecture approach as Monolith.

### Pros.
- Simpler development and deployment for smaller teams and applications.
- Fewer cross-cutting concerns.
- Better performance due to no network latency.

### Cons.
- Difficult to adopt new technologies.
- Limited agility.
- Single code base and difficult to maintain.
- Not Fault tolerance.
- Tiny update and feature development always need a fill deployment.

We have various fforms of Monolithic with the names like Single-Process Monolith, Modular Monolith, Distributed Monolith.
In a monolithic approach, developers work with a single code base, which is then packaged as a unified unit, such as an
EAR/WAR file, and deployed onto a single web/application server. Additionally, the entire application is supported by a
single database.

## The SOA (Service Oriented Architecture).

SOA emerged as an approach to combat the challenges of large, monolithic applications. It is an architectural style that 
focuses on organizing software systems as a collection of loosely coupled, interoperable services. It provides a way to 
design and develop large-scale applications by decomposing them into smaller, modular services that can be independently
developed, deployed, and managed.

### Pros.
- Reusability of services.
- Better maintainability.
- Higher reliability.
- Parallel development.

### Cons.
- Complex management due to communication protocols (e.g. SOAP).
- High investment costs due top vendor in middleware.
- Extra overload.

I have observed numerous instances of SOA where teams aimed to create smaller services, yet they remained tightly coupled
to a shared database and required deploying everything as a cohesive unit. While they followed a service-oriented approach, 
it cannot be classified as microservices.

## The Great Microservices.

Microservices are independently releasable services that are modeled around a business domain. A service encapsulates 
functionality and makes it accessible to other services via networks - you construct a more complex system from these
building blocks. One microservice might represent Accounts, another Cards, and yet another Loans, but together they might
constitute an entire bank system.

### Pros.
- Easy to develop, test, and deploy.
- Increase agility.
- Ability to scale horizontally.
- Parallel development.
- Modeled Around a Business Domain.

### Cons.
- Complexity.
- Infrastructure overhead.
- Security concerns.

If there's one crucial takeaway from this course and the concept of microservices, it is to prioritize the independent
deploy-ability of your microservices. Develop the habit of deploying and releasing changes to a single microservice in
production without requiring the deployment of other components. By doing so, numerous benefits will naturally emerge. 

## Definition of microservice?

Microservices is an approach to developing a single application as a suite of small services, each running its own process
and communicating with lightweight mechanism, built around business capabilities amd independently deployable by fully
automated deployment machinery.

## How to build Microservices?

When considering a web application, the traditional approach involves packaging it as a WAR or EAR file. These archive 
formats are commonly used to bundle Java applications, which are then deployed to web servers like Tomcat or application
servers like WildFly. Do you thing the same approach works for building microservices? Of course not, because Organizations 
may need to build 100s of microservices. Building, packaging, and deploying all the microservices using traditional
methods can be an extremely challenging and practically impossible task. How to overcome this challenge? The cluse is
**Spring Boot**.

## Why Spring Boot for Microservices?

Why Spring Boot is the best framework to build microservice. Spring Boot is a framework that simplifies the development and
deployment of Java applications, including microservices. With Spring Boot, you can build self-container, executable JAR files 
instead of the traditional WAR or EAR files. These JAR files contain all the dependencies and configurations required to run
the microservice. This approach eliminates the need for external web servers or application servers.
- Provides a range of built-in features and integrations such as auto-configuration, dependency injection, and support for
various cloud platforms.
- Provides an embedded Tomcat, Jetty, or Undertow server, which can run the microservice directly without the need for a
separate server installation.
- Inbuilt support of production-ready features such as metrics, health checks, and externalized configuration.
- We can quickly bootstrap a microservice project and start coding with range of starter dependencies that provide
pre-configured settings for various components such as databases, queues etc.
- Well-suited for cloud-native development. It integrates smoothly with cloud platforms like Kubernetes, provides support
for containerization, and enables seamless deployment to popular cloud providers.

In the traditional approach, applications are typically packages as WARs and rely on the presence of a server in the
execution environment for running. However, in the micro services paradigm, applications are packages ad self-contained JARs,
also called fat-JARs or uber-JARs, since they contain the application itself, the dependencies, and the embedded server.

### Implementing REST Services.

REST (Representational state transfer) services are one of the most often encountered ways to implement communication 
between two web apps. REST offers access to functionality the server exposes through endpoints a client can call.
Below are the different use cases where REST services are being used most frequently these days.
- Mobile App - Communication using REST - Backend Server.
- Backend Server 2 - Communication using REST - Backend Server 2.
- Web App build using Angular, React JS etc. - Communication using REST - Backend Server.

Typically REST services are built to expose the business functionality and support CRUD operations on the storage system.
Attached are the standards that we need to follow while building REST services:
- Business logic supporting CRUD operations:
  - Create - HttpMethod.POST.
  - Read - HttpMethod.GET.
  - Update - HttpMethod.PUT/PATCH.
  - Delete - HttpMethod.DELETE.
- Proper input validation & Exception Handling:
  - Make sure all the REST services perform input validations, handle the runtime and business exceptions properly. In all
  kind of scenarios, REST services should send a meaningful response to the clients.
- Document REST Services:
  - With the help of standards like Open API Specification, Swagger, make sure to document your REST APIs. This helps
  your client, third party developers to understand your services clearly.

### DTO (Data Transfer Object) pattern.

The Data Transfer Object (DTO) pattern is a design pattern that allows you to transfer data between different parts of 
your application. DTOs are simple objects that contain only data, and they do not contain any business logic. This makes
them ideal for transferring data between different layers of your application, such as the presentation layer and the data 
access layer.
Here are some of benefits of using the DTO pattern:
- Reduces network traffic: DTOs can be used to batch up multiple pieces of data into a single object, which can reduce the
number of network requests that need to be made. This can improve performance and reduce the load on your servers.
- Encapsulates serialization: DTOs can be used to encapsulate the serialization logic for transferring data over the wire.
This makes it easier to change the serialization format in the future, without having to make changes to the rest of
your application.
- Decouples layers: DTOs can be used to decouple the presentation layer from the data access layer. This makes it easier
to change the presentation layer without having to change the data access layer.

### Different Annotations & Classes that supports building REST services.

- '@RestController' - can be used to put on top of a call. This will expose your methods as REST APIs. Developers can also 
use '@Controller' + '@ResponseBody' for same behavior.
- 'ResponseEntity<T>' - Allow developers to send response body, status, and headers on the HTTP response.
- 'RequestEntity<T>' - Allow developers to receive the request body, header in a HTTP request.
- '@ResponseBody' - can be used on top of a method to build a Rest API when we are using '@Controller' on top of a Java class.
- '@ControllerAdvice' - is used to mark the class as a REST controller advice. Along with '@ExceptionHandler', this can be
used to handle exceptions globally inside app. We have another annotation '@RestControllerAdvice' which is same as
'@ControllerAdvice' + '@ResponseBody'.
- '@RequestHeader' & '@RequestBody' - is used to receive the request body and header individually.

### Summary of the steps followed to build microservices.

1. Build empty Spring Boot applications: First we created empty Spring Boot applications with the required starter 
dependencies related to web, actuator, JPA, devtools, validations, H2 DB, Lombok, spring doc open API etc.
2. Build DB related logic, entities & DTOs: We created required DB tables schema, established connection details with H2 DB,
created JPA related entities, repositories. Post that using DTO pattern guidelines, we built DTO classes and mapper logic
inside all the microservices.
3. Build business logic: Inside all the microservices, we created REST APIs supporting CRUD operations with the help of
various annotations like '@PostMapping', '@GetMapping', '@PutMapping', '@DeleteMapping' etc.
4. Build Global Exception handling logic: We built global exception handling logic using annotations like '@ControllerAdvice' &
'@ExceptionHandler'. Also created custom business exceptions like **CustomerAlreadyExistsException**.
5. Perform data validations on the input: Perform validations on the input data using annotations present inside the
**jakarta.validation** package. These annotations are like '@NotEmpty', '@Size', '@Email', '@Pattern', '@Validated',
'@Valid' etc.
6. Perform auditing using Spring Data JPA: With the help of annotations like '@createdDate', '@CreatedBy', '@LastModifiedDate',
'@LastModifiedBy', '@EntityListeners' & '@EnableJpaAuditing', we implemented logic to populate audit columns in DB.
7. Documenting REST APIs: With the help of OpenAPI specifications, Swagger, Spring Docc library, we documented our REST APIs. 
In the same process, we used annotations like '@Schema', '@Tag', '@Operation', '@ApiResponse' etc.

### How to right size & identify service boundaries of microservices?

One of the most challenging aspects of building a successful microservices system is the identification of proper microservice
boundaries and defining the size of each microservice. Below are the most common approaches in the industry.
- Domain-Driven Sizing: Since many of our modifications or enhancements driven by the business needs, we cam size/define
boundaries of our microservices that are closely aligned with Domain-Driven design & Business capabilities. But this process
takes lot off time and need good domain knowledge.
- Event Storming Sizing: Conducting an interactive fun session among various stake holder to identify the list of important
events in the system like 'Completed Payment', 'Search for Product', etc. Based on the events we can identify 'Commande',
'Reactions' and can try to group them to a domain-driven services.

### Right Sizing & identifying service boundaries.

Now, let's take an example of a bank application the needs to be migrated or built base on microservice architecture and
attempt to determine the appropriate sizing for the services.
- Serving Account & Trading Account, Cards & Loans: Not correct sizing as we can see independent modules like cards & loans
clubbed together.
- Service Account, Trading Account, Cards, Loans: This might be the most reasonable correct sizing as we can see all
independent modules have separate service maintaining loosely coupled & highly cohesive.
- Saving Accounts, Trading Accounts, Debit Card, Credit Card, Home Loan, Vehicle Loan, Personal Loan: Not correct sizing
as we can see too many services under loans & cards.

## Monolith to Microservices.

Migration use case. Now let's take a scenario where an E-Commerce startup is following monolithic architecture and try to 
understand what's the challenges with it.
Problem that E-Commerce team is facing due to traditional monolithic design.

- Initial Days:
  - It is straightforward to build, test, deploy, troubleshoot and scale during the launch and when the team size is less.
- Later after few days the app/site is a super hit amd started evolving a lot. Now team has below problems:
  - The app has become so overwhelmingly complicated that no single person understands it.
  - You fear making changes - each change has unintended and costly side effects.
  - New features/fixes become tricky, time-consuming, and expensive to implement.
  - Each release as small as possible and requires a full deployment of the entire application.
  - One unstable component can crash the entire system.
  - New technologies and frameworks aren't an option.
  - It's difficult to maintain small isolated teams and implement agile delivery methodologies.

So the E-Commerce company decided and adopted the cloud-native design by leveraging Microservices architecture to make
their life easier and less risk with the continuous changes.

## Strangler Fig Pattern.

The Strangler Fig Pattern is a software migration pattern used to gradually replace or refactor a legacy system with a new
system, piece by piece, without disrupting the existing functionality. This pattern gets its name from the way a strangler
Fig plant grows around an existing tree, slowly replacing it until the original tree is no longer needed.

**When to Use the Strangler Fig Pattern**:
- When you need to modernize a large or complex legacy system.
- When you want to avoid the risk associated with a complete system rewrite or "big bang" migration.
- When the legacy system needs to remain operational during the transition to the new system.

The Strangler Fig Pattern facilitates the migration of the monolithic application to a modern microservices architecture
by leveraging a Domain-Driven Design (DDD) approach.
The legacy monolith is carefully analyzed, broken down into distinct domains, and services are gradually rewritten using
newer technologies. This incremental transformation ensures that each service is refactored independently, allowing for a
smooth transition from the monolith to a fully microservices-based architecture while maintaining functionality through 
the process.

Next, a Strangler Facade is introduced to manage traffic routing between the legacy monolithic application and the new
microservices. This routing can also be handled via an API Gateway, if preferred. During this phase, both the monolith 
and microservice coexists.
As the microservices are fully developed and stabilized, the corresponding functionality on the monolith is gradually
phased out or "strangled" and eventually removed. The process involves four key steps: identification, transformation,
co-existence, and elimination.

# Deployment, Portability & Scalability of Microservices.

**Deployment**: How do we deploy all the tiny 100s of microservices with less effort & cost?
**Portability**: How do we move our 100s microservices across environments with less effort, configuration & cost?
**Scalability**: How do we scale our applications based on the demand on the fly with minimum effort & cost?
To overcome the above challenges, we should **containerize** our microservices. Why? Containers offer a self-contained
and isolated environment for applications, including all necessary dependencies. By containerizing an application, it
becomes portable and can run seamlessly in any cloud environment. Containers enable unified management of applications 
regardless of the language or framework used.
**Docker** is an open source platform that "provides the ability to package and run an application in a loosely isolated
environment called a container".

## What are containers & how they are different from VMs?

Main difference between virtual machines and containers. Containers don't need the Guest OS nor the hypervisor to assign
resources; instead, they use the container engine.

## What are Containers & Docker?

**What is software containerization?**
Software containerization is an OS virtualization method that is used to deploy and run containers without using a virtual
machine (VM). Containers can run on physical hardware, in the cloud, VMs, and across multiple OSs.

**What is a container?**
A container is a loosely isolated environment that allows us to build and run software packages. These software packages 
include the code and all dependencies to run applications quickly and reliably on any computing environment. We call these
packages as container images.

**What is Docker?**
Docker is an open-source platform that enables developers to automate the deployment, scaling, and management of applications
using containerization. Containers are lightweight, isolated environments that encapsulate an application along with its
dependencies, libraries, and runtime components.

## What exactly happens in containerization?

Containers are based on the concept of operating system (OS) virtualization, where multiple containers can run on the same
physical or virtual machine, sharing the same OS kernel. This differs from traditional virtualization, where each virtual
machine (VM) runs a separate OS instance.
In containerization, Linux features such as namespaces and cgroups play a crucial role in providing isolation and 
resource management. Here's a brief explanation of these concepts.

**Namespaces**:
Linux namespaces allow for the creation of isolated environments within the operating system. Each container has its own
set of namespaces, including process, network, mount, IPC (Inter Process Communication), and user namespaces. These
namespaces ensure that processes within a container are only aware of and can interact with resources within their specific
namespace, providing a level of isolation.

**Control Groups**:
cgroups provide resource management and allocation capabilities for containers. They allow administrators to control and
limit the resources (such as CPU, memory, disk I/O, and network bandwidth) that containers can consume. By using cgroups, 
container runtimes can enforce resource restrictions and prevent one container from monopolizing system resources, ensuring
fair allocation among containers.

### How does docker works on Mac & Windows OS?

When you install Docker on a Linux OS, you receive the complete Docker Engine on your Linux host. However, if you opt for
Docker Desktop for Max or Windows, only the Docker client is installed on your macOS or Windows host. Behind the scenes,
a lightweight virtual machine is configured with Linux, and the Docker server component is installed within that virtual
machine.
As a user, you experience will be very similar to using Docker on a Linux machine, with minimal noticeable differences.
However, when you utilize the Docker CLI to execute commands, you are actually interacting with a Docker server running
on a separate machine, which in this case is the Linux-based virtual machine.

### Docker Architecture?

**Docker Client**:
Docker remote API, Docker CLI. We can issue commands to Docker Daemon using either Docker CLI or APIs.

**Docker Host/Server**:
Docker Daemon. Using Docker Daemon we can create & manages the docker images.

**Docker Registry**:
Docker Hub, Private Registry. The docker images can be maintained and pulled from the docker hub or private container 
registries.

Steps:
1. Instruction from Docker Client to Server to run a container.
2. Docker server finds the image in registry if not found locally.
3. Docker server pulls the image from registry into local.
4. Docker server creates a running container from the image.

### Generate Docker Images.

To generate docker images from our existing microservices, we will explore the below three different commonly used approaches. 

1. Dockerfile: We need to write a dockerfile with the list of instructions which can be passed to Docker server to generate
docker image based on given instructions.
2. Buildpacks: A project initiated by Heroku & Pivotal and now hosted by the CNCF. It simplifies containerization since with
it, we don't need to write a low-level dockerfile.
3. Google Jib: Jib is an open-source Java tool maintained by Google for building Docker images of Java applications. It
simplifies containerization since with it, we don't need to write a low-level dockerfile.

### Running a Spring Boot app as a container using Dockerfile.

**Steps to be followed**:
1. Run maven command, 'mvn clean install' from the location where pom.xml is present to generate a fat jar inside target folder.
2. Write instructions to Docker inside a file with the name **Dockerfile** to generate a Docker image.
3. Execute the docker command: 'docker build . -r panda/accounts:s4' from the location where Dockerfile is present. This
will generate the docker image based on the tag name provided.
4. Execute the docker command: 'docker run -p 8080:8080 panda/accounts:s4'. This will start the docker container based on
the docker image name and port mapping provided.

**Sample Dockerfile**

'''dockerfile
# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

# Information around who maintains the image
MAINTAINER pandatronik.com
 
# Add the application's jar to the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "/accounts-0.0.1-SNAPSHOT.jar"]
'''

### Port Mapping in Docker.

**What is port mapping or port forwarding or port publishing?**

By default, containers are connected to an isolated network within the Docker host. To access a container from your local
network, you need to configure port mapping explicitly. For instance, when running the accounts Service application, we
can provide the port mapping as an argument in the docker run command: '-p 8081:8080' (where the first value represents
the external port and second value represent the container port).

### Running a Spring Boot app as a container using Buildpacks.

**Steps to be followed**:
1. Add the configurations inside pom.xml. Make sure to pass the image name details.
2. Run the maven command: 'mvn spring-boot:build-image' from the location where pom.xml is present to generate the docker
image without the need of Dockerfile.
3. Execute the docker command 'docker run -p 8090:8090 panda/loans:s4'. This will start the docker container based on the 
docker image name and port mapping provided.

Cloud Native Buildpacks offer an alternative approach to Dockerfiles, prioritizing consistency, security, performance, and
governance. With Buildpacks, developers can automatically generate production-ready OCI images from their application source
code without the need to write a Dockerfile.

### Running a Spring Boot app as a container using Google Jib.

**Steps to be followed**:
1. Add the configurations inside the pom.xml. Make sure to pass the image name details.
2. Run the maven command 'mvn complie jib:dockerBuild' from location where pom.xml is present to generate the docker image
without the need of Dockerfile.
3. Execute the docker command 'docker run -p 9000:9000 panda/cards:s4'. This will start the docker container based on the
docker image name and port mapping provided.

Google Jib offer an alternative approach to Dockerfiles, prioritizing consistency, security, performance and governance.
With Jib, developers can automatically generate production-ready OCI images from their application source code without the
need to write a Dockerfile and even local Docker setup.

### Using Docker Compose to handle multiple containers.

**What is a Docker Compose?**

It is a tool provided by Docker that allows you to define and manage multi-container applications. It uses a YAML file to
describe the services, networks, and volumes required for your application. Using it, you can easily specify the configuration
and relationships between different containers, making it simpler to set up and manage complex application environments.

**Why can't we run multiple containers using CLI?**

When dealing with the execution of multiple containers, utilizing the Docker CLI can be error-prone. Writing commands
directly in a Terminal window can introduce errors, make the code difficult to comprehend, and pose challenges when
attempting to implement version control.

**Advantages of Docker Compose?**

By using a single command, you can create and start all the containers defined in your Docker Compose file. Docker Compose
handles the orchestration and networking aspects, ensuring that the containers can communicate with each other as specified
in the configuration. It also provides options for scaling services, controlling dependencies, and managing the application
lifecycle.

### Important Docker Commands.

1. 'docker images': To list all the docker images present in the Docker server.
2. 'docker image inspect [image-id]': To display detailed image information for a given image id.
3. 'docker image rm [image-id]': To remove one or more images for a given image ids.
4. 'docker build . -t [image-name]': To generate a docker image based on a Dockerfile.
5. 'docker run -p [hostport]:[containerport] [image_name]': To start a docker based on a given image.
6. 'docker ps': To show all running containers.
7. 'docker ps -a': To show all containers including running and stopped.
8. 'dokcer container start [container-id]': To start one or more stopped containers.
9. 'docker container pause [container-id]': To pause all processed within one or more container.
10. 'docker container unpause [container-id]': To resume/unpause all processed within one or more containers.
11. 'docker container stop [container-id]': To stop one or more running containers.
12. 'docker container kill [container-id]': To kill one or more running container instantly.
13. 'docker container restart [container-id]': To restart one or more containers.
14. 'docker container inspect [container-id]': To inspect all the details for a given container id.
15. 'docker container logs [container-id]': To fetch the logs of a given container id.
16. 'docker container logs -f [container-id]': To follow log output of a given container id.
17. 'docker rm [container-id]': To remove one or more containers based on container ids.
18. 'docker container prune': To remove all stopped containers.
19. 'docker image push [container_registry/username:tag]': To push an image from a container registry.
20. 'docker image pull [container_registry/username:tag]': To pull an image form a container registry.
21. 'docker image prune': To remove all unused images.
22. 'docker container stats': To show all containers statistics like CPU, memory, I/O usage.
23. 'docker system prune': Remove stopped containers, dangling images, and unused networks, volumes, and cache.
24. 'docker rmi [image-id]': To remove one or more images based on image ids.
25. 'docker login -u [username]': To login in to docker hub container registry.
26. 'docker logout': To login out from docker hub container registry.
27. 'docker history [image-name]': Displays the Intermediate layers and commands that were executed when building the image.
28. 'docker exec -it [container-id] sh': To open a shell inside a running container and execute commands.
29. 'docker compose up': To create and start containers based on given docker compose file.
30. 'docker compose down': To stop and remove containers from services defined in the Compose file.

# What are cloud native applications?

**The layman definition.**

Cloud-native applications are software applications designed specifically to leverage cloud computing principles and take
full advantage of cloud-native technologies and services. These applications are built and optimized to run in cloud environments,
utilizing the scalability, elasticity, and flexibility offered by the cloud.

**The Cloud Native Computing Foundation (CNCF) definition.**

Cloud native technologies empower organizations to build and run scalable applications in modern, dynamic environments 
such as public, private, and hybrid clouds. Containers, service meshes, microservices, immutable infrastructure, and
declarative APIs exemplify this approach.
These techniques enable loosely coupled systems that are resilient, manageable, and observable. Combined with robust 
automation, they allow engineers to make high-impact changes frequently and predictably with minimal toil.

## Important characteristics of cloud-native applications.

- **Microservices**: Often built using a microservice architecture, where the application is broken down into smaller,
loosely coupled services that can be developed, deployed, and scaled independently.
- **Containers**: Typically packaged and deployed using containers, such as Docker containers. Containers provide a 
lightweight and consistent environment for running applications, making them highly portable across different cloud
platforms and infrastructure.
- **Scalability & Elasticity**: Designed to scale horizontally, allowing them to handle increased loads by adding more 
instances of services. They can also automatically scale up or down based on demand, thanks to cloud-native orchestration
platform like Kubernetes.
- **DevOps Practices**: Embrace DevOps principles, promoting collaboration between development and operations teams. They
often incorporate continuous integration, continuous delivery, and automated deployment principles to streamline the software
development and deployment processes.
- **Resilience & Fault Tolerance**: Designed to be resilient in the face of failures, They utilize techniques such as
distributed architecture, load balancing, and automated failure recovery to ensure highly availability and fault tolerance.
- **Cloud-Native Solutions**: Take advantage of cloud-native services provided by the cloud platform, such as managed
databases, message queues, caching systems, and identity services. This allows developers to focus more on application
logic and less on managing infrastructure components.

## Difference between Cloud Native & Traditional apps.

**Cloud Native Applications.**

1. Predictable Behavior.
2. OS abstraction.
3. Right-sized capacity & Independent.
4. Continuous delivery.
5. Rapid recovery & Automated scalability.

**Traditional Enterprise Applications.**

1. Unpredictable behavior.
2. OS dependent.
3. Oversized capacity & Dependent.
4. Waterfall development.
5. Slow recovery.

## Development Principles of Cloud Native: 12 Factors & Beyond.

How to get succeeded in building Cloud Native Apps & what are the guiding principles that can be considered?
The engineering team at Heroku cloud platform introduced the 12 factor methodology, a set of development principles aimed 
at guiding the design and construction of cloud-native applications. These principles are the result of their expertise 
and provide valuable insight for building web applications with specific characteristics:

1. Cloud Platform Deployment: Applications designed to be seamlessly deployed on various cloud platforms.
2. Scalability as a Core Attribute: Architectures that inherently support scalability.
3. System Portability: Applications that can run across different systems and environments.
4. Enabling Continuous Deployment and Agility: Facilitating rapid and agile development cycles.

These principles were developed to assist developers in building effective cloud-native applications, emphasizing the key
factors that should be considered for optimal outcomes.
Subsequently, Kevin Hoffman expended upon the original factors and introduced additional ones in his book, "Beyond the
Twelve-Factor App". This revised approach, referred as the 15-Factor methodology, refreshing the content of the original
principles and incorporates three new factors.

### 15-Factor methodology.

1. One codebase, one application.
2. API first.
3. Dependency management.
4. Design, build, release, run.
5. Configuration, credentials & code.
6. Logs.
7. Disposability.
8. Backing services.
9. Environment parity.
10. Administrative process.
11. Port binding.
12. Stateless processes.
13. Concurrency.
14. Telemetry.
15. Authentication & authorization.

### Onc codebase, one application.

The 15-Factor methodology ensures a one-to-one correspondence between an application and its codebase, meaning each application
has a dedicated codebase. Shared code is managed separately as a library, allowing it to be utilized as a dependency or as
a standalone service, serving as a backing service for other applications. It is possible to track each codebase in its own 
repository, providing flexibility and organization.
In this methodology, a deployment refers to an operational instance of the application. Multiple deployments can exist across
different environments, all leveraging the same application artifact. It is unnecessary to rebuild the codebase for each
environment-specific deployment. Instead, any factors that vary between deployments, such as configuration settings, should
be maintained externally from the application codebase.

### API first.

In a cloud-native ecosystem, a typical setup consists of various services that interact through APIs. Adopting an API-first
approach during the design phase of a cloud-native application encourages a mindset aligned with distributed systems and
promotes the division of work among multiple teams. Designing the API as a priority allows other teams to build their
solutions based on that API when using the application as a backing service.
This upfront design of the API contract results in more reliable and testable integration with other systems as part of the
deployment pipeline. Moreover, internal modifications to the API implementation can be made without impacting other applications
or teams they rely on it.

### Dependency management.

It is crucial to explicitly declare all dependencies of an application in a manifest and ensure that they are accessible
to the dependency manager, which can download them from a central repository.
In the case of Java applications, we are fortunate to have robust tolls like Maven or Gradle that facilitates adherence to
this principle. The application should only have implicit dependencies on the language runtime and the dependency manager
tool, while all private dependencies must be resolved through the dependency manager itself. By following this approach,
we maintain a clear and controlled dependency management process for our application.

**Sample flow when use Maven as build tool.**

1. Maven reads the pom.xml file.
2. Check if the dependency jar/library is in local repository.
3. If the dependent jar/library is not in local repository, then it searches the maven central repository.
4. Download the Jar.
5. Put the downloaded Jar in the local repository.
6. Copy the jar files.

### Design, build, release, run.

Codebase progression from design to production deployment involves below stages.
Design stage: Determine technologies, dependencies, and tools for specific application features.
Build stage: Compile and package the codebase with dependencies, creating an immutable artifact (build). Unique identification
of the build artifact is essential.
Release stage: Combine the build with a specific deployment configuration. Each release is immutable and uniquely identifiable,
such as through semantic versioning, or timestamp. Central repository storage facilitates easy access, including rollbacks
if needed.
Run stage: Execute the application in the designated runtime environment using a specific release.
Following the 15-Factor methodology, these stages must maintain strict separation, and runtime code modifications are
prohibited to prevent mismatches with the build stage. Immutable build and release artifacts should bear unique identifiers,
ensuring reproducibility.

### Configuration, credentials & code.

According to the 15-Factor methodology, configuration encompasses all elements prone to change between deployments. It 
emphasizes the ability to modify application configuration independently, without code changes or the need to rebuild the
application.
Configuration may include resource handles for backing services (e.g., databases, messaging systems), credentials for
accessing third-party APIs, and features flags. It is essential to evaluate whether any confidential or environment-specific
information would be at risk if the codebase were exposed publicly. This assessment ensures proper externalization of
configuration.
To comply with this principle, configuration should not be embedded within the code or tracked in the same codebase, except for 
default configuration, which can be bundled with the application. Other configurations can still be managed using separate
files, but they should be stored in a distinct repository.
The methodology recommends utilizing environment variables to store configuration. This enables deploying the same application
in different environments while adapting its behavior based on the specific environment's configuration.

### Logs.

In a cloud-native application, log routing and storage are not the application's concern. Instead, applications should direct
their logs to the standard output, treating them as sequentially ordered events based on time. The responsibility of log
storage and rotation is now shifted to an external tool, known as a log aggregator. This tool retrieves, gathers, and provides
access to the logs for inspection purposes.

### Disposability.

In a traditional environment, ensuring the continuous operation of applications is a top priority, striving to prevent
any terminations. However, in a cloud environment, such meticulous attention is not necessary. Applications in the cloud
are considered ephemeral, meaning that if a failure occurs and the application becomes unresponsive, it can be terminated
and replaced within a new instance. Similarly, during high-load periods, additional instances of the application can be
spun up to handle the increased workload. This concept is referred to as application disposability, where applications can
be started ot stopped as needed.
To effectively manage application instances in this dynamic environment, it is crucial to design them for quick startup
when new instances are required and for graceful shutdown when they are no longer needed. A fast startup enables system
elasticity, ensuring robustness and resilience. Without fast startup capabilities, performance and availability issues may
aries.
A graceful shutdown involves application, upon receiving a termination signal, ceasing to accept new requests, completing any
ongoing ones, and the exiting. This process is straightforward for web processes. However, for worker processes or other
types, it involves returning any pending jobs to the work queue before exiting.
Docker containers along with an orchestrator like Kubernetes inherently satisfy this requirement.

### Backing services.

Backing services refer to external resources that an application relies on to provide its functionality. These resources 
can include databases, message brokers, caching systems, SMTP servers, FTP servers, or RESTful web services. By treating
these services as attached resources, you can modify or replace them without needing to make changes to application code.
Consider the usage of databases throughout the software development lifecycle. Typically, different databases are used in
different stages such as development, testing, and production. By treating the database as an attached resource, you can
easily switch to a different service depending on the environment. This attachment is achieved through resource binding,
which involves providing necessary information like a URL, username, and password for connecting to the database.
In example, we can see that a local DB can be swapped easily to a third part DB like AWS DB without any code changes.

### Environment Parity.

Environment parity aims to minimize differences between various environments & avoiding costly shortcuts. Here, the adoption
of containers can greatly contribute by promoting the same execution environment.
There are three gaps that this factor addresses:
Time gap: The time it takes for a code change to be deployed can be significant. The methodology encourages automation and
continuous deployment to reduce the time between code development and production deployment.
People gap: Developers create applications, while operators handle their deployment in production. To bridge ths gap, DevOps
culture promotes collaboration between developers and operators, fostering the "you build it, you run it" philosophy.
Tools gap: Handling of backing services differs across environments. For instance, developers might use the H2 database
locally but PostgreSQL in production. To achieve environment parity, it is recommended to use the same type and version
of backing services across all environment.

### Administrative processes.

Management task required to support applications, such as database migrations, barch jobs, or maintenance task, should
be treated as isolated processes. Similar to application processes, the code for these administrative tasks should be
version controlled, packaged alongside the application, and executed within the same environment.
It is advisable to consider administrative tasks as independent microservices that are executed once and then discarded,
or as functions configured within a stateless platform to respond to specific events. Alternatively, they can be integrated
directly into the application, activated by calling a designated endpoint.

### Port binding.

Cloud native applications, adhering to the 15-Factor methodology, should be self-contained and expose their services through
port binding. In production environment, routing services may be employed to translate requests from public endpoints to the 
internally port-bound services.
An application is considered self-contained when it doesn't rely on an external server within the execution environment.
For instance, a Java web application might typically run within a server container like Tomcat, Jetty, or Undertow. In
contrast, a cloud native application does not depend on the presence of a Tomcat server in the environment; it manages
the server as a dependency within itself. For example, Spring Boot enables the usage of embedded server, where the application
incorporates the server instead of relying on its availability in the execution environment. Consequently, each application
is mapped to its own server, diverging from the traditional approach of deploying multiple applications on a single server.
The services offered by the application are then exposed through port binding. For instance, a web application binds its
HTTP services to a specific port and can potentially serve as a backing service for another application. This is a common
practice within cloud native systems.

### Stateless processes.

Cloud native applications are often developed with high scalability in mind. One of the key principles to achieve scalability
is designing applications as stateless processes and adopting a share-nothing architecture. This means that no state should
be shared among different instances of the application. It is important to evaluate whether any data would be lost if an 
instance of the application is destroyed and recreated. If data loss would occur, then the application is not truly stateless.
However, it's important to note that some form of state management is necessary for applications to be functional. To address
this, we design applications to be stateless and delegate the handling and storage of state to specific stateful services,
such as data stores. In other words, a stateless application relies on a separate backing service to manage and store the
required state, while application itself remains stateless. This approach allows for better scalability and flexibility while
ensuring that necessary state is still maintained and accessible when needed.

### Concurrency.

Scalability is not solely archived by creating stateless applications. While stateless is important, scalability also requires
ability to serve a large number of users. This means the applications should support concurrent processing to handle multiple
users simultaneously.
According to the 15-Factor methodology, processes play a crucial role in application design. These processes should be
horizontally scalable, distributing the workload across multiple processes on different machines. This concurrency is only
feasible when applications are stateless. In Java Virtual Machine (JVM) applications, concurrency is typically managed through
the use of multiple threads, which are available from thread pools.
Processes can be categorized based on their respective types. For instance, there are web processes responsible for handling
HTTP requests, as well as worker processes that execute scheduled background jobs. By classifying processes and optimizing
their concurrency, applications can effectively scale and handle increased workloads.

### Telemetry.

Observability is a fundamental characteristics of cloud native applications. With the inherent complexity of managing a
distributed system in the cloud, it becomes essential to have access to accurate and comprehensive data from each component
of the system. This data enables remote monitoring of the system's behavior and facilitates effective management of its
intricacies. Telemetry data, such as logs, metrics, traces, health status, and events, plays a vital role in providing this
visibility.
In Kevin Hoffman's analogy, he emphasizes the significance of telemetry by comparing applications to separate probes. Just
like telemetry is crucial for monitoring and controlling space probes remotely, the same concept applies to applications. To
effectively monitor and control applications remotely, you need various types of telemetry data.
Consider the kind of telemetry that would be necessary to ensure remote monitoring and control of your applications. This
includes information such as detailed logs for troubleshooting, metrics to measure performance, traces to understand request
flows, health status to assess system well-being, and events to capture significant occurrences. By gathering and utilizing
these types of telemetry data you can gain valuable insights into your applications and make informed decisions to manage
them effectively from a remote location.

### Authentication and Authorization.

Security is crucial aspect of a software system, yet it often doesn't receive the necessary emphasis it deserves. To uphold a
zero-trust approach, it is essential to ensure that security of every interaction within the system, encompassing architectural
and infrastructural levels. While security involves more than just authentication and authorization, these aspects serve
as a solid starting point.
Authentication enables us to track and verify the identity of users accessing the application. By authenticating users, we
can then proceed to evaluate their permissions and determine if they have the necessary authorization to perform specific
actions. Implementing identity and access management standards can greatly enhance security. Notable examples include
'OAuth 2.1' and 'OpenID Connect'.

# Configuration Management in Microservices.

**Separation of configs/properties.**
How do we separate the configurations/properties form the microservices so that same Docker image can be deployed in multiple envs.

**Inject configs/properties.**
How do we inject configurations/properties that microservice need during start up of the service.

**Maintain configs/properties.**
How do we maintain configurations/properties in a centralized repository along with versioning of them.

There are multiple solutions available in Spring Boot ecosystem to handle this challenge. Below are the solutions.
Let's try to identify which one, suites for microservices:
1. Configuring Spring Boot with properties and profiles.
2. Applying external configuration with Spring Boot.
3. Implementing a configuration server with Spring Cloud Config Server.

### How configurations handled in traditional apps & microservices.

Traditional applications were typically bundled together with their source code and various configuration files that contained
environment-specific data. This meant that the configuration required rebuilding the entire application, or creating separate 
builds for each environment. As a result, there was no guarantee that the application would behave consistently across
different environments, leading to potential issues when moving from staging to production.
According to the 15-Factor methodology, configuration encompasses any element likely to change between deployments, such as
credentials, resource handles, and service URLs. Cloud native applications address this challenge by maintaining the immutability
of the application artifact across environments. Regardless of the deployment environment, the application build remains 
unchanged. In cloud native applications, each deployment involves combining the build with specific configuration data.
This allows the same build to be deployed to multiple environments while accommodating different configuration requirements.

### How Configurations work in Spring Boot?.

Spring Boot lets you externalize your configuration so that you can work with the same application code in different
environments. You can use variety of external configuration sources, include Java properties files, YAML files,
environment variables, and command-line arguments.

By default, Spring Boot look for the configurations or properties inside application.properties/yaml present in the 
classpath location. But we can have other property files as well and make Spring Boot ro read from them.
Spring Boot uses a very particular order that is designed to allow sensible overriding of values. Properties are considered
in the following order (with the values from lower items overriding earlier ones):
1. Properties present inside files like application.properties.
2. OS Environmental variables.
3. Java System properties (System.getProperty()).
4. JNDI attributes from java:comp/env.
5. ServletContext init parameters.
6. ServletConfig init parameters.
7. Command line arguments.

### How to read properties in Spring Boot apps.

In Spring Boot, there are multiple approaches to reading properties. Below are the most commonly used approaches.
1. Using '@Value' Annotation. You can use the '@Value' annotation to directly inject property values into your beans.
This approach is suitable for injecting individual properties into specific fields.
2. Using Environment. The Environment interface provides methods to access properties from the application's environment.
You can autowire the Environment bean and use its methods to retrieve property values. This approach is more flexible and
allows accessing properties programmatically.
3. Using '@ConfigurationProperties'. Recommended approach as it avoids hard coding the property keys. The '@ConfigurationProperties'
annotation enables building of entire groups of properties to a bean. You define a configuration class with annotated fields
matching the properties, and Spring Boot automatically maps the properties to the corresponding fields.

### Profiles.

Spring provides a great tool for grouping configuration properties into so-called profiles (dev, qa, prod) allowing us to
activate a bunch of configurations based on the active profile.
Profiles are perfect for setting up our application for different environments, but they're also being used in another use
cases like Bean creation based on a profile etc.
So basically a profile can influence the application properties loaded and beans which are loaded into the Spring context.
The default profile is always active. Spring Boot loads all properties in application.properties into the default profile.
We can create another profiles by creating property files like application_prod.properties or application_qa.profiles.
We can activate a specific profile using spring.profiles.active property like 'sping.profiles.active=prod'.
An important point to consider is that once an application is built and packaged, it should not be modified. If any 
configuration changes are required, such as updating credentials or database handles, the should be made externally. 

### How to externalize configurations using command-line arguments?

Spring Boot automatically converts command-line arguments into key/value pairs and adds them to the Environment object. In
a production application, this becomes the property source with the highest precedence. You can customize the application
configuration by specifying command-line arguments when running the JAR you built earlier.
The command-line argument follows the same naming convention as the corresponding Spring property, with the familiar -- prefix
for CLI arguments.

### How to externalize configurations using JVM system properties?

JVM system properties, similar to command-line arguments, can override Spring properties with a lower priority. This approach
allows for externalizing the configuration without the need to rebuild the JAR artifact. The JVM system property follows the
same naming convention as the corresponding Spring property, prefixed with -D for JVM arguments, In the application, the
message defined as a JVM system property will be utilized, taking precedence over property files.
In the scenario where both a JVM system property and a command-line argument are specified, the precedence rules dictate
that Spring will prioritize the value provided as a command-line argument. This means that the value specified through the
CLI will be utilized by the application, taking precedence over the JVM properties.

### How ro externalize configurations using environment variables?

Environment variables are widely used for externalized configuration as the offer portability across different operating
systems, as they are universally supported. Most programming languages, including Java, provide mechanism to access
environment variables, such as the System.getenv() method.
To map a Spring property key to an environment variable, you need to convert all letters to uppercase and replace any dots
or dashes with underscores. Spring Boot will handle this mapping correctly internally. For example, an environment variable
named BUILD_VERSION will be recognized as the property build.version. This feature is known as relaxed binding.

### Drawbacks of externalized configurations using Spring Boot alone.

1. CLI arguments, JVM properties, and environment variables are effective ways to externalize configuration and maintain
the immutability of the application build. However, using these approaches often involves executing separate commands and
manually setting up the application, which can introduce potential errors during deployment.
2. Given that configuration data evolves and requires changes, similar to application code, what strategy should be employed
to store, track revisions and audit the configuration used in release?
3. In scenarios where environment variables lack granular access control features, how can you effectively control access to
configuration data?
4. When the number of application instances grows, handling configuration in a distribute manner for each instance becomes
challenging. How can such challenges be overcome?
5. Considering that neither Spring Boot properties not environment variables support configuration encryption, how should
secrets be managed securely?
6. After modifying configuration data, how can you ensure that the application can read it at runtime without necessitating
a complete restart?

### Spring Cloud Config.

A centralized configuration server with Spring Cloud Config can overcome all the drawbacks that we discussed in the previous
slide. Sping Cloud Config provides server and client-side support for externalized configuration in a distributed system.
With the Config Server you have a central place to manage external properties for applications across all environments.
Centralized configuration resolves around two core elements:
A data store designed to handle configuration data, ensuring durability, version management, and potentially access control.
A server that oversees the configuration data within the data store, facilitating its management and distribution on 
multiple applications.
1. Microservices act as Config clients & load configurations during startup by connecting to Configuration service.
2. Spring Cloud Config Server load all the configurations by connecting to central repository.
3. Central repositories where properties get stored (database, github, file system/classpath).

### What is Spring Cloud? Using Spring Cloud for microservices development.

Spring Cloud provides frameworks for developers to quickly build some of the common patterns of Microservices.
1. Spring Cloud Config: Ensures that no matter how many microservice instances you bring up; they'll always have the same
configuration.
2. Service Registration & Discovery: New services will be registered & later consumers can invoke them through a logical
name rather than physical location.
3. Routing & Tracing: Make sure that all calls to your microservices go though a single "front door" before the targeted 
service is invoked & the same will be traced.
4. Load Balancing: Load balancing effectively distributes network traffic to multiple backend services or server pool.
5. Spring Cloud Security: Provides features related to token-based security in Spring Boot applications/microservices.
6. Distributed tracing & messaging: Incorporate components that helps in understanding the complex interactions between
services and asynchronous communication, allowing for scalable and resilient systems.

### Refresh configurations ar runtime using /refresh path.

What occurs when new updates are committed to the Git repository supporting the Config Service? In a typical Spring Boot 
application, modifying a property would require restart. However, Spring CCloud Config introduces the capability to dynamically
refresh the configuration in client applications during runtime. When a change is pushed to the configuration repository,
all integrated applications connected to the config server can be notified, prompting them to reload portions affected by
the configuration modification.
Let's see an approach for refreshing the configuration, which involves sending a specific POST request to a running instance
of the microservice. This request will initiate the reloading of the modified configuration data, enabling a hot reload
of the application. Below are the steps to follow:
1. Add actuator dependency in the Config Client services: Add Spring Boot Actuator dependency inside pom.xml of the individual
microservices like accounts, loans, cards to expose the '/refresh' endpoint.
2. Enable '/refresh' API: The Spring Boot Actuator library provides a configuration endpoint called "/actuator/refresh"
that can trigger a refresh event. By default, this endpoint is not exposed, so you need to explicitly enable it in the
application.yaml file.

### Refresh configurations at runtime using /refresh path.

1. Push new configuration data into Config repo (User).
2. Invokes '/actuator/refresh' using HTTP POST method (User).
3. Load latest configuration data related to accounts microservices (Accounts microservice).
4. Pull latest changes from remote GitHub config repo (Config Server).
5. Return latest config data related to accounts microservice (GitHub).
6. Return latest config data related to accounts microservice (Config Service).
7. Reload of the new configurations data inside the microservice without restart of the app (Accounts Microservice).

You invoked the refresh mechanism on Accounts Service, and it worked fine, since it was just one application with 1 instance. 
How about in production where there may be multiple services? If a production has many microservices, then team may prefer
to have an automated and efficient method for refreshing configuration instead of manually triggering each application 
instance.

### Refresh configurations at runtime using Spring Cloud Bus.

Spring Cloud Bus, facilitates seamless communication between all connected application instances by establishing a convenient
event broadcasting channel. It offers an implementation of AMQP brokers, such as RabbitMQ, and Kafka, enabling efficient
communication across the application ecosystem.
Below are the steps to follow:
1. Add actuator dependency in the Config Server & Client services: Add Spring Boot Actuator dependency inside pom.xml
of the individual microservices like accounts, loans and cards to expose the '/busrefresh' endpoint.
2. Enable '/busrefresh' API: The Sping Boot Actuator library provides a configuration endpoint called "/actuator/busrefresh"
that can trigger a refresh event. By default, this endpoint is not exposed, so you need to explicitly enable it in the
application.yaml file.
3. Add Spring Cloud Bus dependency in the Config Server & Client services: Add Spring Cloud Bus dependency
(spring-cloud-starter-bus-amqp) inside pom.xml of the individual microservices like accounts, loans, cards and Config server.
4. Set up a RabbitMQ: Using Docker, setup RabbitMQ service. If the service is not started with default values, then configure
the rabbitmq connection details in the application.yaml file of all the individual microservices and Config Server.

### Refresh configurations at runtime using Spring Cloud Bus.

1. Push new configuration data into Config repo (User).
2. Invoke '/actuator/busrefresh' on any of the microservice instance & config service will load the latest config data (GitHub).
3. Trigger a config change event & initiate refresh on all the subscribed nodes (Config Service - Message Broker).
4. Reload of the new configurations data from Config Server inside all the microservices without restart (microservices).

Though this approach reduce manual work to a great extent, but still there is a single manual step involved which is invoking
the '/actuator/busrefresh' on any of the microservice instance. Let's see how we can avoid and completely automate the process.

### Refresh configurations ar runtime using Spring Cloud Bus & Spring Cloud Config Monitor.

Spring Cloud Config offers the Monitor library, which enables the triggering of configuration change events in the Config
Service. By exposing the '/monitor' endpoint, it facilitates the propagation of these events to all listening applications
via the Bus. The Monitor library allows push notifications from popular code repository providers such as GitHub, GitLab, and
Bitbucket. You can configure webhooks in these services to automatically send a POST request to the Config Service after
each new push to the configuration repository. Below are steps to follow:
1. Add actuator dependency in the Config Server & Client services: Add Spring Boot Actuator dependency inside pom.xml of the
individual microservices like accounts, loans, cards, and Config Server to expose the '/busrefresh' endpoint.
2. Enable '/busrefresh' API: The Spring Boot Actuator library provides a configuration endpoint called "/actuator/busrefresh"
that can trigger a refresh event. By default, this endpoint is not exposed, so you need to explicitly enable it in
application.yaml file.
3. Add Spring Cloud Bus dependency in the Config Server & Client servicesL Add Spring Cloud Bus dependency
(spring-cloud-starter-bus-amqp) inside pom.xml of the individual microservices like accounts, loans, cards and Config server.
4. Add Spring Cloud Config monitor dependency in the Config Server: Add Spring Cloud Config monitor dependency 
(spring-cloud-config-monitor) inside pom.xml of Config server and this exposes '/monitor' endpoint.
5. Set up a RabbitMQ: Using Docker, setup RabbitMQ service. If the service is not started with default values, then configure
the rabbitmq connection details in the application.yaml file of all the individual microservices and Config Server.
6. Set up a WebHJook in GitHub: Set up a webhook to automatically send a POST request to Config Service '/monitor' path
after each new push to the config repo.

### Refresh configurations ar runtime using Spring Cloud Bus & Spring Cloud Config Monitor.

1. Push new configuration data into Config repo (User).
2. Webhook invokes '/monitor' on config server (GitHub).
3. Trigger a config change event & initiate refresh on all the subscribed nodes (Config Service - Message Broker).
4. Reload of the new configurations data from Config Server inside all the microservices without restart (Microservices).

In this solution, there is no manual step involved and everything is automated.

### Liveness and Readiness probes.

A liveness probe sends a signal that the container or application is either alive (passing) or dead (failing). If the 
container is alive, then no action is required because the current state is good. If the container is dead, then an attempt
should be made to heal the application by restarting it. In simple word, liveness answers a true-or-false question:
"Is the container alive?".

A readiness probe used to know whether the container or app being probed is ready to start receiving network traffic. 
If your container enters a state where it is still alive but cannot handle incoming network traffic (a common scenario
during startup), you want the readiness probe to fail. So that, traffic will not be sent to a container which isn't ready for it.
If someone prematurely send network traffic to the container, it could cause the load balancer (or router) to return a 502
error to the client and terminate the request. The client would get a "connection refused" error message.
In simple words, readiness answers a true-or-false question: "Is this container ready to receive network traffic?".

Inside Spring Boot apps, actuator gathers the "Liveness" and "Readiness" information from the ApplicationAvailability
interface and uses that information in dedicated health indicators: LivenessStateHealthIndicator and ReadinessStateHealthIndicator.
These indicators are shown on the global health endpoint ("/actuator/health"). They are also exposed as separate HTTP
Probes by using health groups: "/actuator/health/liveness" and "/actuator/health/readiness".

# Service Discovery & Registration in Microservices.

**How do services locate each other inside a network?**
Each instance of a microservice exposes a remote API with it's own host and port. How do other microservices & clients
know about these dynamic endpoint URLs to invoke them. So where is my service?

**How do new service instances enter into the network?**
If a microservice instance fails, new instances will be brought online to ensure constant availability. This means that
the IP address of the instances can be constantly changing. So how does these new instances can start serving to the clients?

**Load balance, info sharing between microservice instances.**
How do we make sure to properly load balance between the multiple microservice instances especially a microservice is
invoking another microservice? How do a specific service information shared across the network?

These challenges in microservices can be solved using below concepts or solution.
1. Service discovery.
2. Service registration.
3. Load balancing.

## How service communication happens in Traditional alls?

Inside web network, when a service/aoo want to communicate with another service/app, it must be given the necessary information
to locate it, such as an IP address or a DNS name. Let's examine the scenario of two services, Accounts and Loans. If there
was only a single instance of Loans microservice. Upstream Service (Accounts) internal communication between microservices
using hostname, DNS, ort IP address. No Service Discovery or Load Balancing involved. Load Microservice (Downstream Service).
Loans microservice will be a backing service with respect to Accounts microservice.

When there is only one instance of the Loans microservice running, managing the DNS name and its corresponding IP address
mapping is straightforward. However, in a cloud environment, it is common to deploy multiple instances of a service, with each
instance having its own unique IP address.

To address this challenge, one approach is to update DNS records with multiple IP addresses and rely on round-robin name
resolution. This method directs requests to one of the IP addresses assigned to the service replicas in a rotating manner.
However, this approach may not be suitable for microservices, as containers or services change frequently. This rapid change
makes it difficult to maintain accurate DNS records and ensure efficient communication between microservices.

Unlike physical machines or long-running virtual machines, cloud-based service instances have shorter lifespan. These 
instances are designed to be disposable and cen be terminated or replaced for various reasons, such as unresponsiveness.
Furthermore, auto-scaling capabilities can be enabled by automatically adjust the number of application instances based
on the workload.

### How Traditional LoadBalancers works?

Clients like other services uses generic DNS along with the service specific path to invoke a specific service.
DNS name for load balancers.
Traditional Service location resolution architecture using DNS & a load balancer.

### Limitations with Traditional LoadBalancers?

With traditional approach each instance of a service used to be deployed in one or more application servers. The number
of these application servers was often static and even in the case of restoration it would be restored to the same state
with the same IP and other configurations.

While this type of model works well with monolithic and SOA based applications with a relatively small number of servers
running on a group of static servers, it doesn't work well for cloud-based microservice applications for the following
reasons:
1. Limited horizontal scalability & license costs.
2. Single point of failure & Centralized choke-points.
3. Manual;y managed to update any IPs, configurations.
4. Complex in nature & not containers friendly.

The biggest challenge with traditional load balancers is that someone has to manually maintain the routing tables which
is an impossible task inside the microservices network. Because containers/services are ephemeral in nature.

### How to solve the problem for cloud native applications?

For cloud native applications, service discovery is the perfect solution. It involves tracking and storing information
about all running service instances in a service registry.
Whenever a new instance is created, it should be registered in the registry, and when it is terminated, it should be
appropriately removed automatically.
The registry acknowledges the multiple instances of the same application can be active simultaneously. When an application
needs to communicate with a backing service, it performs a lookup in the registry to determine the IP address to connect to.
If multiple instances are available, a load balancer strategy is employed to evenly distribute the workload among them.
Client-side service discovery and server-side service discovery are distinct approaches that address the service discovery
problem in different contexts.

In modern microservice architecture, knowing the right network location of an application is a much more complex problem
for the clients as service instances might have dynamically assigned IP addresses. More-over the number instances may vary
due to autoscaling and failures.
Microservices service discovery & registration is a way for applications and microservices to locate each other on a network.
This includes:
1. A central server (or servers) that maintain a global view of addresses.
2. Microservices/clients that connect to the central server to register their address when they start & ready.
3. Microservices/clients need to send their heartbeats at regular intervals to central server about their health.
4. Microservices/clients that connect to the central server to deregister their address when they are about to shut-down.

Service discovery & registrations deals with the problems about how microservices talk to each other, i.e, perform API calls.

### Client-side service discovery and load balancing.

In client side service discovery, applications are responsible for registering themselves with a service registry during 
startup and unregistering when shutting down. When an application needs to communicate with a backing service, it queries the
service registry for the associated IP address. If multiple instances of the service are available, the registry returns a 
list of IP addresses. The client application then selects one based on its own defined load-balancing strategy. 
Below workflow illustrates this process:
1. Loans service registers with Service registry during startup & send regular heart beats.
2. Accounts microservice to Service Registry. Dude, what are address details of loans service?
3. Service Registry to Accounts microservice. Buddy, here are the IP addresses of Loans instances.
4. Accounts microservice is going to invoke one of the instance of loans based on the load balancing strategy configured.

Client-side service discovery is an architectural pattern where client applications are responsible for locating and connecting
to services they depend on. In this approach, the client application communicates directly with a service registry to discover
available service instances, and obtain the necessary information to establish connection.
Here are the key aspects of client-side service discovery:
1. Service Registration: Client applications register themselves with the service registry upon startup. They provide essential
information about their location, such as IP address, port, and metadata, which helps identify and categorize the service.
2. Service Discovery: When a client application needs to communicate with a specific service, it queries the service registry
for available instances of that service. The registry responds with the necessary information, such as IP address and connection
details.
3. Load Balancing: Client-side service discovery often invokes load balancing to distribute the workloads across multiple 
service instances. The client application can implement load-balancing strategy to select a specific instance based on
factors like round-robin, weighted distribution, or latency.

The major advantage of client-side service discovery is load balancing can be implemented in various algorithms, such as
round-robin, weighted round-robin, the least connection, or even custom algorithms. A drawback is the client service discovery
assigns more responsibility to developers. Also, it results in one more service to deploy and maintain (the service registry).
Server-side discovery solutions (like Kubernetes) solve these issues.

Spring Cloud projects provides several alternatives for incorporating client-side service discovery in Spring Based microservice.






















