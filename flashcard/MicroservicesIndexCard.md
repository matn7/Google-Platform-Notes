## Microservices.

**1. What is a monolithic architecture?**
* A single deployable unit containing all functionality, often packaged as one EAR/WAR and supported by one database.

**2. One major drawback of monolithic systems?**
* Even tiny changes require a full application redeployment.

**3. What is SOA?**
* Service-Oriented Architecture—loosely coupled, modular services interacting via protocols like SOAP.

**4. Key drawback of SOA?**
* High complexity and often dependency on heavyweight middleware.

**5. What are microservices?**
* Independently deployable services modeled around business domains and communicating via lightweight protocols.

**6. Most important characteristic of microservices?**
* Independent deployability.

**7. Advantage of microservices over monoliths?**
* Horizontal scalability and agility.

**8. Main disadvantage of microservices?**
* Increased complexity and infrastructure overhead.

**9. What packaging format does Spring Boot enable?**
* Fat/uber JARs with embedded servers.

**10. Why is Spring Boot ideal for microservices?**
* Auto-configuration, embedded servers, starter dependencies, health checks, easy containerization.

## REST & DTO.

**11. What HTTP method is used for CREATE?**
* POST.

**12. Why use DTO pattern?**
* Reduces network calls, decouples layers, encapsulates serialization.

**13. What does @RestController do?**
* Exposes all methods as REST API endpoints.

**14. What is ResponseEntity used for?**
* Returning body, headers, and HTTP status.

**15. What is @ControllerAdvice used for?**
* Global exception handling.

## Microservice Implementation Steps.

**16. What is the first step in building microservices?**
* Create empty Spring Boot apps with required starter dependencies.

**17. What annotations support auditing in JPA?**
* `@CreatedDate`, `@CreatedBy`, `@LastModifiedDate`, `@LastModifiedBy`, `@EnableJpaAuditing`.

**18. Why document REST APIs?**
* Helps clients understand endpoints using OpenAPI/Swagger.

## Service Boundaries.

**19. What is domain-driven sizing?**
* Defining microservice boundaries based on business capabilities.

**20. What is event storming?**
* Identifying system events → commands → reactions → service boundaries.

**21. What is bad microservice sizing?**
* Either too large (multiple domains) or too many tiny services.

## Modernization.

**22. What is Strangler Fig Pattern?**
* Gradually replace monolith pieces with microservices without downtime.

**23. Why is monolith hard to scale?**
* One unstable component can crash the entire system.

## Containers & Docker.

**24. What is containerization?**
* Packaging code + dependencies into portable isolated environments.

**25. Key difference between VMs and containers?**
* Containers share host OS kernel; VMs have full OS per instance.

**26. What is Docker?**
* Platform for building, running, and managing containers.

**27. What do Linux namespaces provide?**
* Isolation of processes, network, users, mount points.

**28. What do cgroups provide?**
* Resource limits for CPU, memory, I/O, etc.

**29. Where does Docker actually run on Mac/Windows?**
* Inside a lightweight Linux VM.

## Docker Image Creation.

**30. Three ways to build Docker images?**
* Dockerfile, Buildpacks, Google Jib.

**31. What command creates an image via Dockerfile?**
* `docker build -t <name> .`.

**32. What command runs a container with port mapping?**
* `docker run -p host:container <image>`.

**33. What is port mapping?**
* Exposing container ports to external systems.

## Docker Compose.

**34. What is Docker Compose?**
* Tool for running multi-container apps using a YAML file.

**35. Why use Compose over CLI?**
* Easier configuration, version control, and handles inter-container networking.

## Cloud Native.

**36. Definition of cloud-native?**
* Apps designed for scalable, resilient cloud environments using containers, microservices, automation.

**37. Key traits of cloud-native apps?**
* Scalability, resilience, DevOps, containers, microservices.

**38. What are the 15 factors?**
* Extended 12-factor methodology for cloud-native app design.

**39. What is disposability?**
* Apps start fast and shut down gracefully, allowing replacement anytime.

**40. What is the principle of statelessness?**
* Instances store no session/state; external backing services store state.

## Configuration Management.
**41. What is externalized configuration?**
* Injecting configuration at runtime via env vars, args, config servers.

**42. What is Spring Cloud Config?**
* Centralized config server pulling from Git and serving microservices.

**43. What does /actuator/refresh do?**
* Hot reloads configuration without restarting the app.

**44. What does Spring Cloud Bus do?**
* Broadcasts config refresh events across all services using RabbitMQ/Kafka.

**45. What does the Config Monitor enable?**
* Auto-refresh triggered via Git webhook -> `/monitor` endpoint.

## Health Probes.

**46. What is a liveness probe?**
* Checks if app is alive; restart if failing.

**47.  What is a readiness probe?**
* Checks if app is ready to receive traffic.

## Service Discovery.

**48. What problem does service discovery solve?**
* Dynamic locating of microservices with changing IPs.

**49. What is client-side discovery?**
* Client queries registry and performs its own load balancing.

**50. What is Eureka?**
* Spring Cloud Netflix service registry for client-side discovery.

## Docker & Containerization.

**51. What is the Docker Daemon responsible for?**
* Managing images, containers, networks, volumes, and responding to Docker API requests.

**52. What is contained in a Docker image layer?**
* Each layer represents a filesystem delta; layers are immutable and cached for reuse.

**53. What is a container volume?**
* A mechanism to persist data outside the container lifecycle.

**54. What is COPY vs ADD in Dockerfile?**
* COPY copies local files; ADD can also download remote URLs and extract archives.

**55.  What is a multistage Docker build?**
* A technique to produce smaller images by separating build and runtime stages.

**56. What is the ENTRYPOINT used for?**
* Defines the main command executed when the container starts.

**57. What is the CMD instruction used for?**
* Provides default arguments to the entrypoint.

**58. What is the docker inspect command for?**
* Viewing low-level container or image details such as env vars, mounts, and network info.

**59. What does `docker logs -f` do?**
* Streams real-time logs from the container.

**60. Purpose of `docker exec -it <container> sh`?**
* Opens a shell inside a running container.

**61. What does docker prune clean?**
* Removes unused containers, networks, images, and build cache.

**62. What does Docker Hub provide?**
* A public registry hosting images for download.

**63. What is container resource limiting?**
* Restricting CPU/memory usage with flags like --memory and --cpus.

## Spring Boot Configuration.

**64. What is Spring Boot’s property precedence rule?**
* Command-line > env vars > system props > application.properties > default configs.

**65. What is relaxed binding?**
* Spring Boot automatically maps `ENV_VAR`, `env.var`, `env-var` -> `property.name`.

**66. What does `@ConfigurationProperties` enable?**
* Mapping groups of related properties into strongly typed POJOs.

**67. How are profiles activated?**
* Via `spring.profiles.active`, CLI flags, or environment variables.

**68. What is the bootstrap phase in Spring Cloud?**
* An early initialization stage where config server properties are loaded.

**69. What is a composite config repository?**
* A config server setup using multiple property sources (Git, filesystem, Vault).

**70. What is FAIL-FAST mode?**
* Causes app startup to fail immediately if config server is unreachable.

## Spring Cloud Config & Bus.

**71. What is encryption at rest in Config Server?**
* Ability to encrypt sensitive values in config using `/encrypt` and `/decrypt`.

**72. How does a config client fetch properties?**
* On startup, via REST call: `/application/profile`.

**73. What problem does Spring Cloud Bus solve?**
* Broadcasting refresh events across all microservice instances.

**74. Why is RabbitMQ used with Cloud Bus?**
* To act as a message broker for event propagation.

**75. What does the /monitor endpoint do?**
* Receives Git webhook events and triggers config refresh.

**76. What is the benefit of Git-based config?**
* Versioning, auditability, rollback, collaborative editing.

**77. What is hot reloading of config?**
* Updating app configuration without restarting the microservice.

**78. How does the Bus avoid manual refresh?**
* Auto-publishes refresh events to all subscribed services.

## Service Discovery.

**79. What is a service registry?**
* A centralized store containing addresses of all running service instances.

**80. What is Eureka lease renewal?**
* Heartbeat mechanism where clients renew lease every X seconds.

**81. What is Eureka’s self-preservation mode?**
* Protects the registry from mass eviction during network issues.

**82. What is the eviction policy in Eureka?**
* Removes unhealthy instances that miss heartbeats.

**83. What is Eureka Client?**
* A microservice that registers itself and fetches other service details.

**84. Why does Eureka replicate data across servers?**
* For high availability and failover in clusters.

**85. What is metadata in Eureka registration?**
* Custom key-value data describing service instance details.

**86. What is logical service naming?**
* Clients refer to service names, not IP addresses.

**87. Why is Eureka client-side discovery?**
* The client itself fetches service list and chooses an instance.

## Load Balancing & Feign.

**88. What is Spring Cloud Load Balancer?**
* A client-side load balancer replacing Netflix Ribbon.

**89. Common load-balancing algorithms?**
* Round-robin, random, weighted, retry-based, availability-aware.

**90. What is Feign Client?**
* A declarative REST client that maps interfaces to HTTP calls.

**91. What are Feign fallbacks?**
* Backup behavior when the remote service fails.

**92. Why integrate Feign with Load Balancer?**
* To automatically resolve and balance calls across service instances.

## Resilience Patterns.

**93. What is a circuit breaker?**
* A pattern preventing repeated calls to failing services to avoid cascading failures.

**94. What is rate limiting?**
* Restricting the number of requests per time window to protect services.

**95. What is a retry pattern?**
* Automatically reattempting failed requests with delays/backoff.

**96. What is the bulkhead pattern?**
* Isolating resources so failures in one component don't affect others.

## API Gateway.

**97. What is an API Gateway?**
* A single entry point that routes, filters, authenticates, and manages requests to microservices.

**98. Why use an API gateway?**
* Security, centralized routing, rate limiting, monitoring, and protocol translation.

**99. What is request filtering in gateways?**
* Applying cross-cutting rules like logging, auth, or header modification.

**100. What is the difference between gateway and load balancer?**
* Gateway handles routing, security, and filters; load balancer only distributes traffic.

## Kubernetes Core Concepts.

**101. What is Kubernetes?**
* An orchestration platform for deploying, scaling, and managing containerized applications.

**102. What is a Kubernetes cluster?**
* A collection of nodes consisting of control plane components and worker nodes.

**103. What is etcd?**
* A distributed key-value store holding all Kubernetes cluster state.

**104. What does kube-apiserver do?**
* Acts as the central control-plane API; accepts and validates all cluster requests.

**105. What is kube-scheduler?**
* Assigns pods to nodes based on resources and scheduling rules.

**106. What is kube-controller-manager?**
* Runs controllers that ensure cluster state matches the desired state.

**107. What does kubelet do?**
* An agent on worker nodes that ensures containers are running in pods.

**108. What does kube-proxy do?**
* Handles network routing and load balancing across pods on each node.

**109. What is a Pod?**
* The smallest deployable Kubernetes unit containing one or more containers.

**110. What is a ReplicaSet?**
* Ensures a specified number of pod replicas are running at all times.

**111. What is a Deployment?**
* A higher-level controller managing ReplicaSets and rolling updates.

**112. What is a DaemonSet?**
* Ensures one pod runs on every (or selected) node (e.g., for logging or monitoring agents).

## Kubernetes Networking.

**113. What is Kubernetes networking's core rule?**
* Every pod can communicate with every other pod without NAT.

**114. What is a Kubernetes Service?**
* A stable virtual endpoint that exposes a group of pods.

**115. What is ClusterIP service?**
* Default service type that exposes the service inside the cluster.

**116. What is NodePort service?**
* Exposes a pod externally on a static port across all nodes.

**117. What is LoadBalancer service?**
* Provisions a cloud load balancer to expose the service externally.

**118. What is an Ingress?**
* A reverse proxy layer enabling HTTP(S) routing to services.

**119. What is an Ingress controller?**
* The implementation (e.g., Nginx, Istio, Traefik) that enforces Ingress rules.

**120. What are Network Policies?**
* Rules that control traffic allowed between pods and namespaces.

## Kubernetes Storage.

**121. What is a Kubernetes Volume?**
* A directory accessible to containers in a pod, lifecycle tied to the pod.

**122. What is a Persistent Volume (PV)?**
* A cluster-managed storage resource provisioned by an admin or storage class.

**123. What is a Persistent Volume Claim (PVC)?**
* A request for storage by a pod.

**124. What is a StorageClass?**
* Defines dynamic provisioning rules for persistent storage.

**125. What is a StatefulSet?**
* Ensures stable network IDs and storage for stateful workloads like databases.

## Kubernetes Config & Policies.

**126. What is a ConfigMap?**
* A resource for storing non-secret configuration data.

**127. What is a Secret?**
* A base64-encoded storage of sensitive data like credentials or tokens.

**128. What is Downward API?**
* Allows pods to access runtime metadata like labels or resource limits.

**129. What are resource requests?**
* Minimum amount of CPU/memory required by a container.

**130. What are resource limits?**
* Maximum CPU/memory a container can consume.

**131. What are taints and tolerations?**
* Mechanisms to repel or allow pods to schedule on specific nodes.

**132. What is node affinity?**
* Rules to place pods on specific nodes based on node labels.

## Kubernetes Scaling & Health.

**133. What is a liveness probe?**
* Checks if a container should be restarted.

**134. What is a readiness probe?**
* Checks if a pod is ready to serve traffic.

**135. What is a startup probe?**
* Ensures containers that take time to initialize aren’t killed early.

**136. What is a Horizontal Pod Autoscaler (HPA)?**
* Automatically adjusts the number of pods based on CPU/memory metrics.

**137. What is a Vertical Pod Autoscaler (VPA)?**
* Adjusts container resource requests/limits automatically.

## Kubernetes Deployment Strategies.

**138. What is a rolling update?**
* Gradual replacement of old pods with new pods without downtime.

**139. What is a recreate strategy?**
* Deletes all old pods first, then creates new ones (causes downtime).

**140. What is Blue/Green deployment?**
* Two environments run side-by-side; traffic switches after validation.

**141. What is a canary deployment?**
* Releases new versions to a small subset of users first.

**142. What is progressive delivery?**
* Automated, controlled release strategy with metrics-based promotion.

## Kubernetes Security.

**143. What is RBAC in Kubernetes?**
* Role-Based Access Control for API operations authorization.

**144. What is a ServiceAccount?**
* An identity for processes running inside a pod.

**145. Why encrypt secrets at rest?**
* Prevents exposure if etcd is compromised.

**146. What are Pod Security Standards?**
* Baseline rules for workload security (privileges, capabilities, isolation).

**147. What is an admission controller?**
* Intercepts requests to the API server to enforce policies.

**148. What is image scanning?**
* Checking container images for vulnerabilities and malware.

**149.  What are network policies used for?**
* Limiting traffic between pods/namespaces to enforce zero-trust networking.

**150. What is Kubernetes API authentication?**
* Methods like certificates, tokens, OIDC to verify caller identity.

## Kubernetes Observability.

**151. How does Kubernetes handle logs?**
* Logs are captured from containers and aggregated by logging agents.

**152. What is Prometheus used for?**
* Collecting and querying metrics.

**153. What is Grafana used for?**
* Visualizing metrics with dashboards.

**154. What are Kubernetes events?**
* System messages indicating state changes or issues (e.g., scheduling failures).

**155. What is distributed tracing?**
* Tracking requests across microservices using tools like Jaeger/Zipkin.

## Helm Basics.

**156. What is Helm?**
* A package manager for Kubernetes enabling installation of repeatable deployments.

**157. What is a Helm chart?**
* A structured package containing templates + values + metadata.

**158. What is values.yaml?**
* The file where user-defined configuration overrides are stored.

**159. What is helm install used for?**
* Deploying a chart into the cluster as a release.

**160. What is a Helm release?**
* A running instance of a chart with assigned values.

**161. What does helm repo add do?**
* Adds a chart repository to your Helm client.

**162. What does helm upgrade do?**
* Updates an existing release with new chart or values.

## Helm Advanced.

**163. What is Chart.yaml?**
* File containing chart metadata like name, version, and dependencies.

**164. What is the templates/ directory in Helm?**
* Contains Kubernetes YAML templates processed by Helm.

**165. What are Helm template functions?**
* Go template functions that allow logic (e.g., default, upper, repeat) within templates.

**166. What are conditional templates?**
* Using if statements to include/exclude resources based on values.

**167. What are pipeline templates in Helm?**
* Template pipelines that chain functions using |.

**168. What are Helm chart dependencies?**
* Other charts that your chart relies on, declared in Chart.yaml.

**169. What are global values in Helm?**
* Special values shared across all subcharts.

**170. What are Helm lifecycle hooks?**
* Tasks that run at specific points (pre-install, post-upgrade, pre-delete).

## Service Mesh Fundamentals.

**171. What is a service mesh?**
* A dedicated infrastructure layer that handles service-to-service communication, reliability, security, and observability.

**172. What is the sidecar pattern in service mesh?**
* A proxy injected next to each service instance to handle networking concerns like routing, mTLS, and telemetry.

**173. What problem does a service mesh solve?**
* Provides consistent traffic management, security, and observability across microservices without changing application code.

**174. What are the two main components of a service mesh?**
* Data plane (sidecar proxies) and control plane (manages configuration and policies).

## Istio Overview.

**175. What is Istio?**
* A popular service mesh providing traffic control, security, and observability for microservices.

**176. What does Istio’s data plane consist of?**
* Envoy sidecar proxies injected into each pod.

**177. What does Istio’s control plane do?**
* Configures and manages the behavior of the Envoy proxies (via Istiod).

**178. What is automatic sidecar injection in Istio?**
* Kubernetes mutating webhooks add Envoy containers to pods automatically.

## Traffic Management.

**179. What is a VirtualService in Istio?**
* A resource defining routing rules for traffic to a service.

**180. What is a DestinationRule in Istio?**
* Defines subsets (versions) and policies like load balancing and connection settings.

**181. How does Istio support canary deployments?**
* By routing percentages of traffic to different service versions using VirtualService rules.

**182. What is traffic shifting in Istio?**
* Gradually moving traffic between versions for safe releases.

## Security & mTLS.

**183. What is mTLS in Istio?**
* Mutual TLS ensuring encrypted communication and service identity verification.

**184. What component provides certificates in Istio?**
* Istiod issues and rotates certificates automatically via its CA.

**185. What is zero-trust networking?**
* A security model that requires every service-to-service request to be authenticated and authorized.

## Observability.

**186. What observability features does Istio provide?**
* Metrics, logs, and traces collected automatically from Envoy sidecars.

**187. What is distributed tracing in Istio?**
* Capturing and correlating requests across microservices using tools like Jaeger or Zipkin.

**188. How does Istio integrate with Prometheus?**
* Envoy proxies expose metrics scraped by Prometheus for dashboards and alerts.

## Linkerd Overview.

**189. What is Linkerd?**
* A lightweight, security-focused service mesh designed for simplicity and low latency.

**190. Difference between Linkerd and Istio?**
* Linkerd is simpler and lighter with Rust-based proxies; Istio is more feature-rich but heavier.

## General CI/CD Concepts.

**191. What is CI?**
* Continuous Integration — merging code frequently with automated builds and tests.

**192. What is CD?**
* Continuous Delivery/Deployment — automatically preparing or deploying software to production.

**193. What is a CI pipeline?**
* A sequence of automated steps to build, test, and validate code changes.

**194. What is a CD pipeline?**
* A pipeline that deploys artifacts to staging/production environments.

**195. What is infrastructure as code in CI/CD?**
* Defining infrastructure in version-controlled files (e.g., YAML for pipelines).

### Jenkins.

**196. What is Jenkins?**
* A self-hosted automation server used to run CI/CD pipelines.

**197. What is a Jenkins Pipeline?**
* A scripted or declarative definition of CI/CD steps written in Jenkinsfile.

**198. What are Jenkins agents?**
* Worker nodes that execute pipeline steps distributed across machines or containers.

**199. What are Jenkins plugins?**
* Extensions adding build tools, integrations, UI improvements, and cloud support.

**200. What does Jenkins Blue Ocean provide?**
* A modern UI for visualizing and managing pipelines.

### GitHub Actions.
**201. What is GitHub Actions?**
* A CI/CD platform integrated into GitHub using YAML-defined workflows.

**202. What is a workflow in GitHub Actions?**
* A pipeline triggered by events like pushes, pull requests, or schedules.

**203. What are GitHub Action runners?**
* Machines (hosted or self-hosted) that execute workflow jobs.

**204. What is a GitHub Actions job?**
* A set of steps executed in a runner environment.

**205. What is a GitHub Actions step?**
* An individual task in a job, like checking out code or running tests.

### ArgoCD (GitOps).

**206. What is ArgoCD?**
* A GitOps-based continuous delivery tool for Kubernetes.

**207. What is GitOps?**
* Managing infrastructure and deployments through Git as the single source of truth.

**208. How does ArgoCD deploy applications?**
* By syncing Kubernetes manifests from Git repositories to clusters.

**209. What is ArgoCD auto-sync?**
* Automatically updates cluster state when Git changes.

### Tekton (Kubernetes-Native CI/CD).

**210. What is Tekton?**
* A Kubernetes-native CI/CD framework using CRDs for pipelines.

**211. What is a Tekton Task?**
* A reusable CI step defined as a custom Kubernetes resource.

**212. What is a Tekton Pipeline?**
* A set of ordered Tasks describing CI/CD flow.

**213. How does Tekton run tasks?**
* Each task runs inside its own container within Kubernetes.

**214. Why is Tekton cloud-native?**
* Pipelines run entirely inside the cluster using Kubernetes primitives.


## Event-Driven Architecture Basics.

**211. What is Event-Driven Architecture (EDA)?**
* A system design where services communicate by producing and consuming events asynchronously.

**212. What is an event?**
* A record that something happened (state change) in a system.

**213. What are the three core components of EDA?**
* Event producers, event brokers, and event consumers.

**214. What is an event broker?**
* A middleware system (Kafka/RabbitMQ) that stores, routes, and delivers messages between services.

**215. Difference between commands and events?**
* Commands request an action; events represent facts about something that already happened.

### Messaging Models.

**216. What is point-to-point messaging?**
* A message goes to exactly one consumer via a queue.

**217. What is publish/subscribe messaging?**
* A message is broadcast to all subscribers of a topic.

**218. What is message durability?**
* The guarantee that messages are stored reliably until processed.

**219. What is idempotency in message processing?**
* The ability for a consumer to handle duplicate messages safely.

**220. What is at-least-once delivery?**
* Messages may be delivered more than once but never lost.

### Apache Kafka.

**221. What is Apache Kafka?**
* A distributed streaming platform optimized for high-throughput event processing.

**222. What is a Kafka topic?**
* A log-structured sequence of events grouped by category.

**223. What is a Kafka partition?**
* A shard of a topic enabling parallelism and scalability.

**224. What is a Kafka consumer group?**
* A group of consumers that share the work of reading partitions.

**225. What are Kafka offsets?**
* Numeric pointers representing a consumer's position in a topic partition.

**226. What is log retention in Kafka?**
* How long Kafka stores event data before deleting or compacting it.

### RabbitMQ.

**227. What is RabbitMQ?**
* A message broker using the AMQP protocol, optimized for routing and reliable messaging.

**228. What is an exchange in RabbitMQ?**
* A router that decides how messages are distributed to queues.

**229. What are the main RabbitMQ exchange types?**
* Direct, fanout, topic, and headers exchanges.

**230. What is a dead-letter queue (DLQ)?**
* A queue where rejected, expired, or failed messages are routed for later inspection.

## Foundations of Observability.

**231. What are the three pillars of observability?**
* Logs, metrics, and traces.

**232. What is observability?**
* The ability to understand a system’s internal state using its external outputs.

**233. Difference between monitoring and observability?**
* Monitoring tells you when something is wrong; observability tells you why.

### Metrics.

**234. What are metrics in observability?**
* Numeric time-series data used to measure system performance (e.g., CPU, latency).

**235. What is Prometheus?**
* A metrics collection and alerting system based on a pull model.

**236. What is Grafana used for?**
* Visualizing metrics and building dashboards.

### Logs.

**237. What are logs?**
* Timestamped structured or unstructured records of events in a system.

**238. What is centralized logging?**
* Collecting logs from all services in one location for search and analysis.

**239. What tools are common for central logging?**
* ELK stack (Elasticsearch, Logstash, Kibana) and Loki.

### Distributed Tracing.

**240. What is distributed tracing?**
* Tracking a request across multiple microservices to analyze latency and failures.

**241. What is a trace?**
* A collection of spans following a request through a system.

**242. What is a span?**
* A timed operation representing a single unit of work within a trace.

**243. What is context propagation?**
* Passing trace IDs and metadata through service calls for trace continuity.

### OpenTelemetry & Tracing Tools.

**244. What is OpenTelemetry?**
* A unified framework for generating, collecting, and exporting logs, metrics, and traces.

**245. What are common tracing backends?**
* Jaeger, Zipkin, and Tempo.

## Core API Security Concepts.

**246. What is API security?**
* Protecting APIs from unauthorized access using authentication, authorization, encryption, and threat detection.

**247. What is authentication?**
* Verifying the identity of a user or service.

**248. What is authorization?**
* Determining what resources an authenticated user is allowed to access.

**249. What is token-based authentication?**
* Users authenticate once and receive a token used for subsequent requests.

### API Keys.

**250. What is an API key?**
* A static token used to identify and authenticate a client calling an API.

**251. Why are API keys considered weaker security?**
* They are static, easily leaked, and lack user context or strong access controls.

### JWT (JSON Web Tokens).

**252. What is a JWT?**
* A compact, signed token used for stateless authentication.

**253. What are the three parts of a JWT?**
* Header, payload (claims), and signature.

**254. What are claims in a JWT?**
* Information inside the token, such as user ID, roles, expiration.

**255. Why are JWTs stateless?**
* The server verifies tokens using signatures without storing session state.

### OAuth 2.0.

**256. What is OAuth 2.0?**
* A framework for delegated authorization allowing apps to access resources without sharing passwords.

**257. What is an access token in OAuth2?**
* A token granting temporary access to protected resources.

**258. What is a refresh token?**
* A long-lived token used to obtain new access tokens without re-authentication.

**259. What is the Authorization Code Grant?**
* A secure OAuth flow where a client exchanges a code (from a login page) for tokens.

### OpenID Connect (OIDC).

**260. What is OpenID Connect?**
* An identity layer on top of OAuth2 that provides authentication and user profile information via ID tokens.

## Cloud Fundamentals.

**261. What is cloud elasticity?**
* The ability to automatically scale resources up or down based on demand.

**262. What is cloud availability?**
* The ability of cloud services to remain operational despite failures.

**263. What is a region in cloud platforms?**
* A geographic area containing multiple isolated data centers.

**264. What is an availability zone (AZ)?**
* A physically separate data center within a region providing fault isolation.

### Cloud Networking.

**265. What is a VPC (Virtual Private Cloud)?**
* A logically isolated network in the cloud where you deploy resources.

**266. What is a subnet?**
* A partition of a VPC used to isolate workloads (public or private).

**267. What is a security group?**
* A virtual firewall controlling inbound and outbound traffic for cloud resources.

**268. What is a load balancer in the cloud?**
* A managed service distributing traffic across instances (ELB, ALB, NLB).

**269. What is a NAT gateway?**
* A service allowing private subnets to access the internet without exposing them.

### Cloud Storage.

**270. What is object storage?**
* A scalable, distributed storage system for files (e.g., AWS S3, GCP Cloud Storage).

**271. What is block storage?**
* Storage attached to VMs for low-latency workloads (EBS, Persistent Disk).

**272. What is a cloud storage bucket?**
* A container for storing objects in services like S3 or GCS.

**273. What is lifecycle management in object storage?**
* Rules that automatically transition or delete objects based on age or access pattern.

### Serverless & Managed Compute.

**274. What is serverless computing?**
* A model where the cloud provider handles scaling and infrastructure (e.g., AWS Lambda).

**275. What is a function-as-a-service (FaaS)?**
* Event-driven compute units that run only when invoked.

**276. What is a container orchestrator in the cloud?**
* Managed Kubernetes services (EKS, GKE, AKS).

**277. What is autoscaling in the cloud?**
* Automatically adjusting compute resources based on load.

### Cloud IAM & Security.

**278. What is Identity and Access Management (IAM)?**
* A service to manage permissions and access to cloud resources.

**279. What is the principle of least privilege?**
* Giving users or services only the permissions absolutely necessary.

**280. What are managed policies in IAM?**
* Predefined permission sets maintained by cloud providers.

