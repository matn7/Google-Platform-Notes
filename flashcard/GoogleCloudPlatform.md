Google Cloud Platform

NEW SECTION - Mine Flashcard

1) Service Level Indicator (SLI)?
Answer: Quantitive measure of an aspect of a service. Categories: availability, latencies, throughput, durability,
correctness (error rate). Typically aggregated - "Over 1 minute".

2) What is the easiest way to implement authentication and authorization for your apps deployed in App Engine,
Cloud Run and GKE?
Answer: Identity-Aware Proxy (IAP).

3) Your microservices are going to be deployed to GKE. You want to give common permissions to Cloud Logging
and Cloud Monitoring?
Answer: Node Pool Service Account.

4) Which Google Cloud Service is recommended for implementing CI-CD pipeline?
Answer: Cloud Build.

5) Designing API gRPC?
Answer: gRPC: Based on Remote Procedure Call (RPC) model.
a) 'createTodo(username, description)' - todo_id. Addressable entities - procedures.
b) Advantages: Hides HTTP details from developers (uses HTTP/2 in background). Generate gRPC stubs and skeletons. Clients
use stubs to make gRPC calls. Service providers use skeletons to implement services. Provides very good performance:
use binary payload - Protobuff. Efficient management of connections (because of HTTP2).
c) Disadvantages: REST API are easier to consume, does not require any special framework/libraries.

6) In memory databases / caches?
Answer: Cloud Memorystore. Applications needing microsecond responses.

7) NoSQL Databases?
Answer: Cloud Firestore (Datastore), Cloud BigTable. Apps needing quickly evolving structure (schema-less).
Cloud Firestore: Serverless transactional document Database supporting mobile and web apps. Small to medium database
(0 to few Tera Bytes). Cloud BigTable: Large databases (10 TB - PBs). Streaming (IOT), analytical and operational workloads.
NOT serverless.

8) Relational OLAP databases?
Answer: BigQuery. Columnar storage with predefined schema. Data warehousing and BigData workloads.

9) Relational OLTP databases?
Answer: CloudSQL, Cloud Spanner. Transactional use cases needing predefined schema and very strong transactional
capabilities (row storage). CloudSQL: MySQL, PostgreSQL, SQL server DBs. Cloud Spanner: Unlimited scale and 99.999%
availability for global apps with horizontal scaling.

10) Cloud SQL Auth Proxy?
Answer: How to simplify connection management and ensure secure (HTTPS) between clients and SQL server? Cloud SQL Auth Proxy.
Communication encrypted with TLS1.2 with a 128 - bit AES cipher. Do not worry about certificates. Easier connection manager.
Needs isolation of Proxy Client on the client machine. Can be used to connect from local env. Works with both private and public
IP addresses. Cloud SQL Auth proxy is automatically supported on some GCP services. App Engine Standard, Cloud Run.

11) Cloud SQL - High Availability?
Answer: Create a High Availability (HA) configuration. Choose Primary and Secondary zones with a region. You will have two
instances: Primary and Secondary instances. Changes from Primary are replicated synchronously to secondary. In case of
zonal failure, automatic failover to secondary instance: If primary zone becomes available, failover to secondary
instance. If primary zone becomes available, failover does not revert automatically. Remember, High Availability setup
cannot be used as a Read Replica.

12) Cloud Storage Scenarios. Regulatory compliance: Object should not be modified for 2 years?
Answer: Configure and lock data retention policy.

13) Cloud Storage Scenarios. Customer wants to manage their keys?
Answer: Customer managed keys, managed by customer in Cloud KMS.

14) Cloud Storage Scenarios. I will frequently access objects in a bucket for 30 days. After that I don't expect to access
objects at all. We have compliance requirements to store objects for 4 years. How can I minimize my costs?
Answer: Initial Storage Class: Standard. Lifecycle policy: Move to Archive class after 30 days. Delete after 4 years.

15) Consuming Google Cloud APIs?
Answer: How does authentication for client libraries work?
a) Client libraries look for env variable GOOGLE_APPLICATION_CREDENTIALS - if present, the service account tagged is used.
b) Use Service account attached to the resource where your code is running. Else throw an error.
Recommended if you are using Compute Engine, Google Kubernetes Engine, App Engine, Cloud Run or Cloud Functions to run
your app: assign service account to the resources.

16) Which Cloud Spanner Transaction modes is recommended for ensuring that a table does NOT get locked during bulk update?
Answer: Partitioned DML.

17) Connect to Cloud SQL from serverless environments using Private IP address?
Answer: Use serverless VPC Access.

18) How to connect from a VM instance inside a VPC to a Cloud SQL instance using private address?
Answer: Create a Private Service Connection.

19) Connect to Cloud SQL from GCP VM or GKE using public IP address?
Answer: Use Cloud SQL Auth Proxy.

20) Recommended for new mobile and web apps that need real-time updates and offline features?
Answer: Firestore in Native mode.

21) Cloud Dataflow?
Answer: Used to export data from BigTable to CloudStorage. For example: Cloud BigTable to Cloud Storage Avro,
Cloud BigTable to Cloud Storage Parquet.

22) Use case for Anthos Service Mesh?
Answer: Canary deployments (Distribute traffic to two versions of the same microservice). mTLS: two way TLS between pods.
SLO monitoring.

23) Get an overall security picture of your project?
Answer: Security Command Center.

24) Detect container threats at runtime?
Answer: Container Threat Detection.

25) Three Pillars of Observability?
Answer: Logs, metrics, traces.

26) Recommended flow for handling streaming data in Google CLoud?
Answer: Pub/Sub, Dataflow, BigQuery.

27) Services to schedule asynchronous tasks (from your microservices) with an explicit invocation in Google Cloud?
Answer: Cloud Task.

28) Simple architecture to execute real-time simple pre-defined queries on a single table containing huge volume of time
series data that is continuously streamed in?
Answer: Pub/Sub, Dataflow, BigTable.

29) Which services can be used to create dashboards and visualization around data stored in BigQuery?
Answer: Cloud Data Studio.

30) Which service can be used to clean data on-boarded from external sources?
Answer: Cloud Dataprep.

31) Docker Images, Best Practices?
Answer: Use light-weight images (prefer Alpine linux to ubuntu). Do not copy anything that's unnecessary (node-modules
for example). Move things that change less often to top to enable layer reuse.

32) Creating Docker Images - Dockerfile?
Answer: Instruction to create Docker image:
FROM: sets a base image. WORKDIR: sets working directory. RUN: execute a command. EXPOSE: Informs docker about the part
that the container listens on at runtime. COPY: copies new files or directories into image. CMD: default command for an
executing container.

33) Which is recommended for advanced cross-cloud deployments?
Answer: Spinnaker.

34) Which deployment approaches gives the ability to test if your users like a feature in production?
Answer: A/B testing.

35) Which deployment approaches allows you to perform zero-downtime deployment without using any additional infrastructure?
Answer: Canary Deployment.

36) GKE - Releasing New Versions. Spinnaker versus Cloud Build?
Answer: Cloud Build or Spinnaker can be used for building Docker Image (CI). Cloud Build can perform simple deployments.
For advanced automated cross cloud deployments, Spinnaker is recommended.

37) GKE - Releasing New Version. Canary Deployment?
Answer: Create new deployment. Use Service Mesh like Istio. Anthos service mesh is GCP service for Istio, or you can use
Spinnaker.

38) GKE - Releasing New Versions. Blue Green Deployment?
Answer: Create new deployment. Control traffic using Ingress (or service) or you can use Spinnaker.

39) GKE - Releasing New Versions. Rolling Update and Recreate?
Answer: Set strategy type on Deployment.

40) App Engine - Releasing new versions?
Answer:
a) Deploy and Shift all traffic at once: 'gcloud app deploy'.
b) Deploy v2 without shifting traffic: '--no-promote'.
c) Shift traffic to v2 all at once: 'gcloud app service set-traffic s1 --splits v1=1'.
d) Gradual migration: Add --migrate option to previous command.
e) A/B testing: 'gcloud app series set-traffic s1 --splits=v2=.5,v1=.5'.

41) Deployment Approaches MIG - Blue Green Deployment?
Answer: Create a new MIG and make manual adjustments to load balancer backends as needed.

42) Deploy only trusted container images to Google Cloud?
Answer: Binary Authorization.

43) Scan your container images for vulnerabilities?
Answer: Container Scanning API.

44) Detect if your VM is being used for Bitcoin mining?
Answer: Anomaly Detection.

45) Protect your apps from DDoS and common vulnerabilities?
Answer: Cloud Armour.

46) Scan and find common vulnerabilities like cross-site-scripting (XSS) in your App Engine apps?
Answer: Web security scanner.

47) Find out which storage bucket contains sensitive data?
Answer: Cloud DLP (Data Loss Prevention).

48) You want to solve performance bottlenecks by identifying parts of the app consuming the most resources?
Answer: Cloud Profiler.

49) You want to look at the logs for a specific request?
Answer: Cloud Logging.

50) Cloud Firestore Best Practices?
Answer: Cloud Firestore is a document store with flexible schema: Recommended for storing things like user profiles.
Index for object stored in Cloud Storage (like profile image). Design keys and indexes carefully: distribute every keys,
avoid monotonically increasing key values. Create DB nearest to users and compute resources. Prefer batch operations.
Firestore soft limits: 1 write per sec per doc, 1 million concurrent connection per database. Gradually ramp up traffic
500/50/5 rule. Start max 500 operations, increase by 50% every 5 minute.

51) You want to debug a problem in production by conditionally adding in a log-point or capturing a snapshot?
Answer: Cloud Debugger.

52) You want to Trace a request across multiple microservices?
Answer: Cloud Trace.

53) You want to identify prominent exceptions (or errors) for a specific microservice?
Answer: Error Reporting.

54) You want to create a dashboard with how many times a substring occurs in a log?
Answer: Create a log-based counter metrics.

55) How can you trigger an alert when your app is unavailable?
Answer: Configure a Cloud Monitoring uptime check.

56) Cloud SQL best practices?
Answer:
a) Use cloud SQL auth proxy when possible.
b) Use connection pooling & exponential backoff.
c) Keep transactions short and small.
d) Use internal IP as much as possible.
e) Understand scalability: Enable HA configuration for HA: primary instance and standby instance created in the same region
(remember - regional). Read replicas help offload read workloads (reporting, analytics etc.). Remember Read Replicas do not
increase availability. Prefer a number of small cloud sql instances to having one large instance.

57) How to connect to Cloud SQL instance?
Answer: Prefer private IP address to public IP addresses. Use serverless VPC access for private IP connections for
serverless environment. App Engine, Cloud Functions, Cloud Run. Use Cloud SQL, Auth proxy for these scenarios: Connecting
with Public IP from Serverless Environments (App Engine, Cloud Functions, Cloud Run). Connecting with Public/Private IPs
from other environments GCE VMs, GKE etc. If you are NOT using Cloud SQL Auth Proxy, it is recommended to use self-managed
SSL/TLS certificates.

58) IAM Best Practices?
Answer: Principle of Least Privilege: Give least possible privilege needed for a role. Separation of Duties - Involve
at least 2 people in sensitive tasks. Example have separate deployer and traffic migrator roles. Constant Monitoring.
Use Groups when possible.

59) Which options would you enable on a subnet to allows VM's in a subnet to connect to Google APIs using private IPs?
Answer: Enable Private Google Access.

60) Cloud VPN?
Answer: Cloud VPN - Connect on-premise network to the GCP network. Implemented using IPsec VPN Tunnel. Traffic through
internet (public). Traffic encrypted using Internet Key. Exchange protocol. Two types of Cloud VPN: HA VPN - only
dynamic routing BGP supported. Classic VPN - supports static routing policy based, route-based and dynamic routing BGP.

61) Direct Peering?
Answer: Hybrid Cloud. Connect customer network to google network using network peering. Direct path from on-premises
network to Google services. Not a GCP service: lower level network connection outside of GCP. Not recommended: use cloud
interconnect and Cloud VPN.

62) Cloud Interconnect?
Answer: Hybrid Cloud. High Speed physical connection between on-premise and VPC network. High available and high
throughput. Two types: Dedicated interconnect - 10 Giga Bytes per seconds or 100 Giga Bytes per second. Partner
Interconnect - 50 Mega bytes per second to 10 Giga bytes per seconds. Data exchange happens through a private network.
Use only for high bandwidth needs: for low bandwidth, cloud VPN is recommended.

63) How to connect VPC networks across different organizations?
Answer: VPC Peering. Networks in same project, different projects and across projects in different organizations can
be peered.

64) What can be used to create Kubernetes - style CI/CD pipeline that run as an extension on a Kubernetes cluster?
Answer: Tekton Pipelines,

65) Options to share files between Cloud Build steps?
Answer: Use '/workspace' folder.

66) SRE - Best Practices?
Answer: Handling Excess load: load shedding, reduced Quality of Service. Avoid cascading failures: Plan to avoid
thrashing. Penetration Testing (Ethical Hacking). Load Testing. Resilience Testing - "How does an app behaves under
stress?". Resilience - "Ability of system to provide acceptable behavior even when one or more parts of the system fails".
Approaches: Chaos Testing, Add huge stress on one of the layer, Include network in your testing, DIRT (Disaster Recovery
testing in Google).

67) Error budgets - SRE Metrics?
Answer: 100% - SLO. How well is a team meeting their reliability objectives? Used to manage development velocity.

68) Service Level Agreement (SLA)?
Answer: SLO plus consequences (contract). What is the consequence of NOT meeting an SLO? (Defined in contract). Have
stricter internal SLOs than external SLAs.

69) Your microservices are going to be deployed to GKE. You want to give each microservice specific access to
Google Cloud resources?
Answer: Workload Identity.

70) Kubernetes Security Best Practices?
Answer: Enable Node Auto Upgrade for GKE nodes. Use Shielded GKE nodes with secure boot. Enable Workload Identity. Use
namespace and RBAC to restrict user access to cluster resources. Restrict traffic among Pods with a network policy.
Use Kubernetes secrets or Secret Manager for Secret management: Consider HashiCorp Vault if you need multi-cloud
secrets management.

71) Important Predefined GKE Roles?
Answer: Kubernetes Engine Admin: Complete access to cluster and kubernetes API objects. Kubernetes Engine Cluster Admin -
Provides access to management of clusters. Kubernetes Engine Cluster Viewer - Read only access to cluster. Kubernetes
Engine Developer - Manage Kubernetes API Objects (and read cluster info). Kubernetes Engine Viewer - get/list cluster
and Kubernetes api objects.

72) Kubernetes RBAC?
Answer: Fine grained access controls at a individual cluster and namespace level. Control access at Kubernetes
resource levels (deployment, service, pod, secrets, configMap).

73) Authorization for Kubernetes Workload?
Answer: Option 1: Assign permissions at Node (Compute Engine VM) level. Option 2: Assign permissions at Pod (individual
microservice) level, Using Kubernetes Secrets or Using Workload Identity. Recommend option is to use Workload Identity:
Follows principle of least privilege. Do not need any secret management.

74) How can you assign a Service Account to a microservice (or a pod)?
Answer: Option 1: Using Kubernetes Secrets (or secrets management). Option 2: Using Workload Identity.

75) Kubernetes Node Level permission. Default service account assigned to a node?
Answer: Compute Engine Default service account.

76) Is Service Account an identity or a resource?
Answer: It is both. You can attach roles with Service Account (identity). You can let other member access a Service
Account by granting them a role on the Service Account (resource).

77) Service to Service auth?
Answer: OpenID Connect ID tokens.

78) IAM Concepts?
Answer: Member: Who? Roles: Permissions (What actions? What resources?). Policy: Assign permissions to members. Map Roles
(What?), Members (Who?) and Conditions (which Resources?, When?, From Where?). Remember: Permissions are NOT directly
assigned to member. Permissions are represented by a Role. Member gets permissions through Role! A role can have multiple
permissions. You can assign multiple roles to a Member.

79) IAM identities?
Answer: A GCP User (Google Account or Externally Authenticated User). A Group of GCP Users. An Application running in GCP.
An Application running in your data center. Unauthenticated user.

80) Cloud console set project?
Answer: 'gcloud config set project PRJ_NAME'.

81) Object Lifecycle Management Allowed Transitions?
Answer: Standard or Multi-Regional or Regional to Nearline or Coldline or Archive. Nearline to Coldline or Archive.
Coldline to Archive.

82) Features across storage classes?
Answer: High durability. Low latency. Unlimited storage: Autoscaling, no min object size. Same API across storage classes.
Committed SLA - No committed SLA for Archive Storage.

83) Google KMS options?
Answer: Google-managed key: No configuration required. Customer-managed key: Use key from KMS. Customer-supplied key:
Provide your own key.

84) Which platform enables you to run your Kubernetes Cluster across multiple platforms (multi-cloud and on-premises)?
Answer: Anthos.

85) Cloud Function, cold Start?
Answer: New function instance initialization from scratch can take time. Solution configure min number of instances
(increases cost).

86) Cloud Functions, events are triggered from?
Answer: Cloud Storage. Cloud Pub/Sub. HTTP POST/GET/DELETE/PUT/OPTIONS. Firebase. Cloud Firestore. Stack driver logging.

87) How can a service in Kubernetes cluster talk to another service in the same Kubernetes cluster without using its URL?
Answer: Kubernetes Service Discovery.

88) How can you divide one physical Kubernetes cluster into multiple virtual clusters with customized access to
different teams?
Answer: Create Separate Namespace.

89) Recommended approach for providing external access to services in a cluster (One Load Balancer serves multiple
microservices)?
Answer: Ingress.

90) What is the default deployment strategy in Kubernetes?
Answer: RollingUpdates.

91) GKE - Remember CPU?
Answer: Some CPU on the nodes is reserved by Control Plane. First core - 6%, second core - 1%, third and fourth - 0.5%,
rest - 0.25%.

92) Kubernetes Status - PodUnschedulable?
Answer: Pod cannot be scheduled. Are there sufficient resources on the cluster?

93) Kubernetes Status - CrashLoopBackOff?
Answer: Container is repeatedly crashing. Look at the logs to find out why container is crashing.
'kubectl logs POD_NAME'. You can also try to connect to the pod and execute commands. 'kubectl exec -it POD_NAME -- ls'.

94) Troubleshooting Kubernetes?
Answer: 'kubectl describe pods POD_NAME'. Gives you current state of the POD and recent events.

95) Kubernetes status - ImagePullBackOff, ErrImagePull?
Answer: Is image name correct? Is image tag correct? Does the node pool service account have access to the container
registry image?

96) Global, Regional and Zonal Resources?
Answer:
a) Global: Image, Snapshots, Instance Templates (unless you use zonal resources in your template).
b) Regional: Regional managed instance group, Regional persistent disks.
c) Zonal: Zonal managed instance groups, Instances, Persistent disks: you can attach a disk only to instances in the same
zone as the disk.

97) You want very high IOPs but your data can be lost without a problem?
Answer: Local SSDs.

98) You want to create a high performance file sharing system in GCP which can be attached with multiple VMs?
Answer: Filestore.

99) You want to backup your VM configuration along with all its attached Persistent Disks?
Answer: Create a Machine Image.

100) You want to make it easy to launch VMs with hardened OS and customized software?
Answer: Create a Custom Image.

101) The Persistent network block, storage service in GCP?
Answer: Persistent Disk.

102) Which contains everything you need to create a VM instance - VM configuration, Metadata, Boot disk and data from
one or more persistent disk?
Answer: Machine Image.

103) Cloud Storage?
Answer: Must popular, very flexible and inexpensive storage service. Serverless: Autoscaling and infinite scale.
Store large objects using a key-value approach: Treats entire object as a unit (Partial Updates not allowed). Recommended
when you operate on entire object most of the time. Access control at object level. Also called Object storage.
Provides REST API to access and modify objects. Also provides CLI (gsutil) and Client Libraries (C++, C#, Java, Node.js,
PHP, Python and Ruby). Store all file types - text, binary, backup and archives: Media files and archives. App packages
and logs. Backup of your databases or storage devices. Staging data during on-premise to cloud database migration.

104) Cloud Storage - Objects and Buckets?
Answer: Objects are stored in buckets. Bucket names are globally unique. Bucket names are used as part of object URLs,
can contain only lowercase letters, numbers, hyphens, underscores and periods. 3 to 63 characters max. Can't start with
goog prefix or should not contain google (even misspelled). Unlimited objects in a bucket. Each bucket is associated
with a project. Each object is identified by a unique key - key is unique in a bucket. Max object size is 5 Tera Bytes.
But you can store unlimited number of such objects.

105) IAM - Roles?
Answer: Roles are Permissions: perform some set of actions on some set of resources. Three Types:
a) Basic Roles (or Primitive roles): Owner, Editor, Viewer. Viewer (roles.viewer) - Read-only actions. Editor
(roles.editor) - Viewer plus Edit actions. Owner (roles.owner) - Editor plus Manage Roles and Permissions plus Billing.
Earliest version: created before IAM. Not recommended: don't use in production.
b) Predefined Roles - Fine grained roles predefined and managed by Google. Different roles for different purposes.
Examples: Storage Admin, Storage Object Admin, Storage Object Viewer, Storage Object Creator.
c) Custom Roles: When predefined roles are not sufficient, you can create your own custom roles.

106) Cloud Identity and Access Management (IAM)?
Answer: Authentication (is it the right user?) and Authorization (do they have the right access?).
Identities can be: A GCP User (Google Account or Externally Authenticated User). A Group of GCP Users. An Application
running in GCP. An Application running in your data center. Unauthenticated user. Provides very granular control.
Limit a single user: to perform single action, on a specific resource, from a specific IP address, during a specific
time window.

107) Typical identity management in the cloud?
Answer: You have resources in the cloud (examples - a virtual server, a database etc.). You have identities (human, and
non-human) that need to access those resources and perform actions. For example: Launch (start, stop or terminate) a
virtual server. How do you identify users in the cloud? How do you configure resources they can access?
How can you configure what actions to allow? In GCP: Identity and Access management (Cloud IAM) provides this service.

108) Cloud Storage - Encryption?
Answer: Cloud storage allows encryption of data on the server side. Configure server side encryption: Encryption performed
by Cloud Storage. Google managed encryption key: Default (no configuration required). Customer managed encryption key:
Created using Cloud Key Management Service (KMS). Managed by customer in KMS. Cloud Storage Service Account should have
access to keys in KMS for encrypting and decrypting using customer-managed encryption keys. (Optional) Client-Side
encryption: Encryption performed by customer before upload. GCP does not know about the keys used.

109) Object Versioning?
Answer: Prevents accidental deletion and provides history. Enabled at bucket level. Can be turned on/off at any time.
Live version is the latest version. If delete live object, it becomes non-current object version. If delete non-current
object version, it is deleted. Older versions are uniquely identified by / object key plus a generation number. Reduce
cost by deleting older (non-current) versions!

110) Cloud Filestore?
Answer: Shared Cloud File Storage: Supports NFS v3 protocol. Provisioned Capacity. Suitable for high performance
workloads: Up to 320 Tera Bytes with throughput of 16 Giga Bytes per seconds and 480 thousands IOPS. Supports HDD
(general purpose) and SSD (performance-critical workloads). Use cases: File share, media workflows and client
management.

111) You want to delete old snapshots created by scheduled snapshots?
Answer: Configure it as part of your snapshot scheduling!

112) You want to improve performance of persistent disk (PD)?
Answer: Increase size of PD and Add more PDs. Increase vCPUs in your VM.

113) You want to increase durability of Persistent Disk (PD)?
Answer: Go for regional PDs (2 times cost but replicated in 2 zones).

114) You want to take hourly backup of Persistent Disks (PD) for disaster recovery?
Answer: Schedule hourly snapshots!

115) Access Control - Overview?
Answer: How do you control access to objects in a Cloud Storage bucket? Two types of access control: Uniform (Recommended)
Uniform bucket level access using IAM. Fine-grained - Use IAM and ACLs to control access: Both bucket level and
individual object level permissions. Use uniform access when all users have same level of access across all objects in
bucket. Fine grained access with ACLs can be used when you need to customize the access at an object level. Give a user
specific access to edit specific objects in a bucket.

116) ACL - Access Control Lists?
Answer: ACL: Define Who has access to your buckets and objects, as well as what level of access they have. How is this
different from IAM? IAM permissions apply to all objects within a bucket. ACLs can be used to customize specific accesses
to different objects. Users gets access if he is allowed by either IAM of ACL! Remember - Use IAM for common permissions
to all objects in a bucket. Remember - Use ACLs if you need to customize access to individual objects.

117) VM instance with default service account in Project-A needs to access CLoud Storage bucket in Project-B?
Answer: In Project-B, add the service account from Project-A, and assign Storage Object Viewer Permission on the bucket.

118) Is Service Account an Identity or a resource?
Answer: It is both. You can attach roles with Service Account (identity). You can let other members to access a Service 
Account by granting them a role on the Service Account (resource).

119) Service Account - Application on a VM wants to put a message on a Pub/Sub Topic?
Answer: Configure the VM to use a Service Account with right permissions.

120) Service Account - Application on a VM wants to talk to a Cloud Storage bucket?
Answer: Configure the VM to use a Service Account with right permissions.

121) Service Account - Use Case On Perm to Google Cloud APIs (short-lived)?
Answer: Make calls from outside GCP to Google Cloud APIs with short-lived permissions. Few hours or shorter. Less risk
compared to sharing service account keys! Credential Types: OAuth 2.0 access token. OpenID Connect ID tokens. Self-signed
JSON Web Tokens (JWTs). Examples: When a member needs elevated permissions, he can assume the service account role (create
OAuth2.0 access token for service account). OpenID connect ID tokens is recommended for service to service authentications:
A service in GCP needs to authenticate itself to a service in other cloud.

122) Service Account - Use Case on Prem to Google Storage (long-lived)?
Answer: You cannot assign Service Account directly to an on PremApp. One: Create a Service Account with right permissions.
Two: Create a Service Account User Managed key: 'gcloud iam service-accounts keys create'. Download the service account
key file, keep it secure (it can be used to impersonate service account)! Three: Make the service account key file
accessible to your app: Set environment variable GOOGLE_APPLICATION_CREDENTIALS. Four: Use Google Client Libraries -
Google Cloud Client Libraries - App Default Credentials/ADC.

123) Service Account - Use Case VM to Cloud Storage?
Answer: One: Create a Service Account Role with the right permissions. Two: Assign Service Account role to VM instance.
Use Google Cloud-Managed Keys: Key generation and use are automatically handled by IAM when we assign a service account
to the instance. Automatically rotated. No need to store credentials in config files. Do not delete service accounts used
by running instances. App running on those instances will lose access!

124) Service Accounts?
Answer: Scenario: An application on a VM needs access to cloud storage. You don't want to use personal credentials to
allow access. Recommended - Use Service Accounts: Identified by an email address
(example: id-compute@developer.gserviceaccount.com). Does not have password: Has a private/public RSA key-pair. Can't
log in via browsers or cookies. Service account types: Default service account - Automatically created when some services
are used. Not-Recommended - has Editor role by default. User Managed - User created: Recommended Provides fine-grained
access control. Google-Managed service accounts - Created and managed by Google. Used by GCP to perform operations on
user's behalf. In general, we do not need to worry about them.

125) New-Scenario: Reporting and Analytics Applications?
Answer: New reporting and analytics apps are being launched using the same database. These apps will only read data.
Within a few days you see that the db performance is impacted. How can we fix the problem? Vertically scale the database -
increase CPU and memory. Create a database cluster (distribute the database) - Typically database clusters are expensive
to setup. Create read replicas - Run read only apps against read replicas.

126) Database - Read Replica?
Answer: Add read replica. Connect reporting and analytics apps to read replica. Reduces load on the master database.
Upgrade read replica to master database (supported by some databases). Create read replicas in multiple regions. Take
snapshots from read replicas.

127) Relational OLTP databases summary?
Answer: Cloud SQL, Cloud Spanner. Transactional use cases needing predefined schema and very strong transactional
capabilities (Row storage). Cloud SQL: MySQL, PostgreSQL, SQL server DBs. Cloud Spanner: Unlimited scale and 99.999%
availability for global apps with horizontal scaling.

128) Consistency?
Answer: How do you ensure that data in multiple databases instances (stand by and replicas) is updated simultaneously?
Storing Consistency - Synchronous replication to all replicas. Will be slow if you have multiple replicas or standbys.
Eventual Consistency - Asynchronous replication. A little lag - few seconds - before the change is available in all
replicas. In the intermediate period, different replicas might return different values. Used when scalability is more
important than data integrity. Examples: Social Media Posts, Facebook status messages, Twitter tweets, LinkedIn posts etc.
Read-after-write consistency: Inserts are immediately available. However, updates would have eventual consistency.

129) IAM most important concepts?
Answer: Member: Who? Roles: Permissions (What Actions? What resources?). Policy: Assign Permissions to Members:
Map Roles (What?), Members (Who?) and Conditions (Which Resources?, When?, From Where?). Remember: Permissions are NOT
directly assigned to Member: Permissions are represented by a Role. Member gets permissions through Role! A Role can
have multiple permissions. You can assign multiple roles and a Member.

130) IAM Policy?
Answer: Roles are assigned to users through IAM Policy documents. Represented by a policy object. Policy object has list
of bindings. A binding, binds a role to list of members. Member type is identified by prefix: Example: user,
service account, group or domain.

131) You are running an app in VM instance storing its data on a persistent data storage. You are taking snapshots every
48 hours. If the VM instance crashes, you can manually bring it back up inj 45 minutes from snapshot. What is your RTO
and RPO?
Answer: RTO (Recovery Time Objective): 45 minutes. RPO (Recovery Point Objective): 48 hours.

132) RTO and RPO. Very small data loss (RPO - 1 minute), Very small downtime (RTO - 5 minutes)?
Answer: Hot standby - Automatically synchronize data. Have standby ready to pick up load. Use automatic failover from
master to standby.

133) RTO and RPO. Very small data loss (RPO - 1 minute). But I can tolerate some downtime (RTO - 15 minutes)?
Answer: Warm standby - Automatically synchronize data. Have a standby with minimum infrastructure. Scale it up when a
failure happens.

134) RTO and RPO. Data is critical (RPO - 1 minute) but can tolerate downtime of a few hours (RTO - few hours)?
Answer: Create regular data snapshots and transaction logs. Create database from snapshots and transaction logs when a
failure happens.

135) RTO and RPO. Data can be lost without a problem (for example: cached data)?
Answer: Failover to a completely new server.

136) Database for storing huge volumes stream data from IOT devices?
Answer: BigTable.

137) Database for storing huge streams of time series data?
Answer: BigTable.

138) Database for analytics processing of petabytes of data?
Answer: BigQuery.

139) Cache data (from database) for a web application?
Answer: Memorystore.

140) Transactional global database with predefined schema needing to process a million of transactions per second?
Answer: Cloud Spanner.

141) Transactional local database processing thousands of transactions per second?
Answer: Cloud SQL.

142) A startup with quickly evolving schema (table-structure)?
Answer: Cloud Datastore / Firestore.

143) Non-relational database with less storage (10 Giga Bytes)?
Answer: Cloud Datastore.

144) In memory database caches?
Answer: Cloud Memorystore. Applications needing microseconds responses.

145) NoSQL Databases?
Answer: Cloud Firestore (Datastore), Cloud BigTable. Apps needing quickly evolving structure (schema-less).
Cloud Firestore:Serverless transactional document database supporting mobile and web apps. Small to medium databases
(0 to few Tera Bytes). Cloud BigTable: Large databases (10 Tera Bytes to Petabytes). Streaming IoT, analytical and
operational workloads. Not Serverless.

146) Relational OLAP database summary?
Answer: BigQuery. Columnar storage with predefined schema. Data warehousing and BigData workloads.

147) Cloud BigTable?
Answer: Petabytes scale, wide column NoSQL Database (HBase API compatible). Designed for huge volumes of analytical and
operational data. IOT streams, Analytics, Time Series Data etc. Handle millions of read / write TPS at very low latency.
Single row transactions (multi row transactions NOT supported). Not Serverless: You need to create a server instance (Use
SSD or HDD). Scale horizontally with multiple nodes (no downtime for cluster resizing). Cannot export data using cloud
console or gcloud: Either use a Java app (java-jar JAR export/import). Use HBase commands. Use cbt command line tool to
work with BigTable (Not gcloud). Example: 'cbt createtable my-table'.

148) Cloud BigTable - Wide Column Database?
Answer: At the most basic level, each table is a sorted key/value map. Each value in a row is indexed using a key-row key.
Related columns are grouped into columns families. Each column is identified by using column-family:column-qualifier
(or name). This structure supports high read and write throughput at low latency. Advantages: Scalable to petabytes of
data with milliseconds responses up to millions of TPS. Use cases: IOT streams, graph data and real time analytics
(time-series data, financial data, transaction histories, stock prices etc.) Cloud Dataflow: Used to export data from
BigTable to CloudStorage.

149) Memorystore?
Answer: In memory data store: Reduce access time. Fully managed (Provisioning, Replication, Failover, Patching).
Highly available with 99.9% availability SLA. Monitoring can be easily setup using Cloud Monitoring. Supports for Redis
and Memcached. Use Memcached for Caching: Reference data, database query, caching, session store etc. Use Redis for low
latency access with persistence and high availability. Gaming Leader Board, Player Profiles, In memory stream processing.

150) Firewall Rules - Ingress and Egress Rules?
Answer: Ingress Rules: Incoming traffic from outside to GCP targets. Target (defines the destination): All instances or
instances with TAG / SA. Source (defines where the traffic is coming from): CIDR or All instances / instances with
TAG / SA (service account). Egress Rules: Outgoing traffic to destination from GCP targets. Target (defines the source):
All instances or instances with TAG / SA. Destination CIDR block. Along with each rule, you can also define:
a) Priority: Lower the number, higher the priority.
b) Action on match: Allow or Deny traffic.
c) Protocol: example TCP or UDP or ICMP.
d) Enforcement Status: Enable or Disable the rules.

151) Shared VPC?
Answer: Scenario: Your organization has multiple projects. You want resources in different projects to talk to each other?
How to allow resources in different projects to talk with internal IPs securely and efficiently?
Enter Shared VPCs: Created at organization or shared folder level (Access Needed: Shared VPC Admin). Allows VPC Network to
be shared between projects in same organization. Shared VPC contains one host project and multiple service projects.
Host Project: Contains shared VPC Network. Service Projects: Attached to host projects. Helps you achieve separation of
concerns: Network admins responsible for Host projects and Resource users use service project.

152) VPC Peering?
Answer: Scenario: How to connect VPC network across different organizations? Enter VPC Peering: Networks in same project,
different projects and across projects in different organizations can be peered. All communication happens using internal
IP address. Highly efficient because all communication happens inside Google Network. Highly Secure because not accessible
from internet. No data transfer charges for data transfer between services. Remember: network administration is Not
changes. Admin of one VPC do not get the role automatically in a peered network.

153) Cloud VPN?
Answer: Cloud VPN - Connect on-premise network to the GCP network. Implemented using IP-Sec VPN Tunnel. Traffic through
internet (public). Traffic encrypted using Internet Key Exchange protocol. Two types of Cloud VPN solutions:
a) HA VPN (SLA of 99.99% service availability with two external IP addresses). Only dynamic routing (BGP) supported.
b) Classic VPN (SLA of 99.9% service availability, a single external IP address). Supports Static Routing (policy-based,
route-based) and dynamic routing using BGP.

154) Cloud Interconnect?
Answer: High Speed physical connection between on premise and VPC networks. Highly available and high throughput.
Two types of connection possible. Dedicated interconnect: 10 Giga Bits per second or 100 Giga Bits per second
configuration. Partner Interconnect: 10 Mega Bits per Second to 10 Giga Bits per Seconds configurations.
Data exchange happens through a private network: Communicate using VPC Networks internal IP address from on-premise
network. Reduces egress cost. As public internet is not used. Supported Google API's and services can be privately
addressed from on-premise. Used only for high bandwidth needs: For low bandwidth, Cloud VPN is recommended.

155) Direct Peering?
Answer: Connect customer network to Google network using network peering. Direct path from on-premises network to Google
Services. Not a GCP Service: Lower level network connection outside GCP. Not Recommended: Use Cloud Interconnect and
Cloud VPN.

156) Cloud Datastore and Firestore?
Answer: Datastore: Highly scalable NoSQL Document Database. Automatically scales and partitions data as it grows.
Recommended for up to a few Tera Bytes of data. For bigger volume, BigTable is recommended. Supports Transactions,
Indexes and SQL like queries (GQL). Does not support Joins or Aggregate (sum or count) operations. For use cases needing
flexible schema with transactions. Examples: User Profile and Product Catalogs. Structure: Kind > Entity (use namespaces
to group entities). You can only export data from gcloud (not from console). Export contains a metadata file and a folder
with the data.
Firestore: Datastore++: Optimized for multi device access. Offline mode and data synchronization across multiple devices
mobile, IoT etc. Provides client side libraries: Web, iOS, Android and more. Offers Datastore and Native modes.

157) Google Cloud VPC (Virtual Private Cloud)?
Answer: Your own isolated network in GCP Cloud. Network traffic within a VPC is isolated (not visible) from all other
Google Cloud VPCs. You control all the traffic coming in and going outside a VPC. Best practice create all your GCP
resources (Compute, Storage, databases etc.) within a VPC. Secure resources from unauthorized access. Enable secure
communication between your cloud resources. VPC is a global resource and contains subnets in one or more region.
Not tied to a region or a zone. VPC resources can be in any region or zone.

158) Need for VPC Subnets?
Answer: Different types of resources are created on cloud, databases, compute. Each type of resource has its own needs.
Load Balancers are accessible from internet (public resources). Databases or VM instances should not be accessible from
internet. Only apps within your network (VPC) should be able to access them (private resources). How do you separate
public resources from private resources inside a VPC? Create Separate Subnets! Additional reason, you want to distribute
resources across multiple regions for high availability.

159) VPC Subnets?
Answer: Solution, create different subnets for public and private resources. Resources in a public subnet can be accessed
from internet. Resources in a private subnets cannot be accessed from internet. But resources in public subnet can tak to
resources in private subnet. Each subnet is created in a region. Example: VPC-demo-vpc, subnets region us-central1,
europe-west1 or us-west1.

160) Creating VPCs and Subnets?
Answer: By default, every project has a different VPC. You can create your own VPCs
Option1: Auto mode VPC network. Subnets are automatically created in each region. Default VPC created automatically in
the project uses auto mode!
Option2: Custom mode VPC network. No subnets are automatically created. You have complete control over subnets and their
IP ranges. Recommended for Production.
Options when you create a subnet. Enable Private Google Access - Allows VM's to connect to Google API's using private IPs.
Enable Flowlogs to troubleshoot any VPC related network issues.

161) CIDR (Classless Inter-Domain Routing) Blocks?
Answer: Resources in a network us continuous IP addresses to make routing easy. Example: resource inside a specific
network can use IP address form '69.208.0.0' to '69.208.0.15'. How do you express a range of addresses that resources in
a network can have? A CIDR block consists of starting IP address (69.208.0.0) and a range (/28). Example: CIDR block
'69.208.0.0/28' represents addresses from '69.208.0.0' to '69.208.0.15' a total of 16 addresses. Quick tip:
'69.208.0.0/28' indicates that the first 28 bits (out of 32) are fixed. Last 4 bits can change, 2 to the power of 4 is
equal to 16 addresses.

162) How many addresses does '69.208.0.0/26' represents?
Answer: 2 to the power 6 is 64 addresses. From '69.208.0.0' to '69.208.0.63'.

163) How many addresses does '69.208.0.0/30' represents?
Answer: 2 to the power 2 is 4 addresses. From '69.208.0.0' to '69.208.0.3'.

164) What is the difference between '0.0.0.0/0' and '0.0.0.0/32'?
Answer: '0.0.0.0/0' represents all IP addresses. '0.0.0.0/32' represent just one IP address '0.0.0.0'.

165) Firewall Rules?
Answer: Configure Firewall Rules to control traffic going in or out of the network. Stateful. Each firewall rule has
priority (0 to 65535) assigned to it. 0 has highest priority. 65535 has the lowest priority. Default implied rule with
the lowest priority (65535). Allow all egress. Deny all ingress. Default rules can't be deleted. You can override
default rules by defining new rules with priority from 0 to 65534. Default VPC has 4 additional rules with priority 65534.
default-allow-internal, default-allow-rdp, default-allow-ssh, default-allow-icmp.

166) System Event Logs?
Answer: Google administrative actions. Enabled by default. Examples: On host maintenance, instance preemption, automatic
restart. Access Needed: Logging, Logs Viewer or Project Viewer.

167) Admin Activity Logs?
Answer: API calls or other actions that modify the config of resources. Enabled by default. Example: VM creation,
patching resources, change in IAM permissions. Cloud Storage: Modify bucket or object. Access Needed: Logging, Logs
Viewer or Project Viewer.

168) Cloud Logging - Export Use Cases?
Answer: Use Case1: Troubleshoot using VM Logs. Install Cloud Logging agent in all VMs and send logs to Cloud Logging.
Search for logs in Cloud Logging.
Use Case2: Export VM logs to BigQuery for querying using SQL like queries. Install
Cloud Logging agent in all VM;s and send logs to Cloud Logging. Create a BigQuery datasets for storing the logs.
Create an export sink in Cloud Logging with BigQuery dataset as sink destination.
Use Case3: You want to retain audit logs for external auditors at min cost. Create an export sink in Cloud Logging with
Cloud Storage buckets as sink destination. Provide auditors with Storage Object Viewer role on the bucket.

169) Cloud Logging - Export?
Answer: Logs are ideally stored in Cloud Logging for limited periods: For long term retention (compliance, audit) logs
can be exported to: Cloud storage bucket (example 'bucket/syslog/20250505'). Big Query dataset (example tables
syslog-20250505 > columns, timestamp, log). Cloud Pub/Sub topic (base 64encoding log entries).
How do you export logs? Create sinks to these destinations using Log Router: You can create include or exclude filters to
limit the logs.

170) Cloud Logging - Controlling & Routing?
Answer: How do you manage your logs? Logs from various sources reaches Log Router. Log Router checks against configured
rules: What to ingest? What to discard? Where to route?
Two types of logs buckets:
a) '_Required': Holds Admins activity, System Events and Access Transparency Logs (retained for 400 days). Zero charge,
you cannot delete the bucket. You cannot change retention period.
b) '_Default': All other logs (retained for 30 days). You are billed based on Cloud Logging pricing. You cannot delete
the bucket: But you can disable the '_Default' log sink route to disable ingestion. You can edit retention settings
(1 to 3650 days).

171) Cloud Logging - Audit and Security Logs?
Answer: Access Transparency Log: Captures Actions performed by GCP team on your content (not supported by all services).
Only for organizations with Global support level and above. Cloud Audit Logs: Answers who did what, when and where:
Admin activity logs. Data Access Logs. System Event Audit Logs. Policy Denied Audit Logs.

172) Need for Google Cloud VPC?
Answer: In a corporate network or on-premises data center: Can anyone on the internet see the data exchange between apps
and the database? No. Can anyone from internet directly connect to your database? Typically, Not. Corporate network
provides a secure internal network protecting your resources, data and communication for external users. How do you
create your private network in the cloud? Enter Virtual Private Cloud (VPC).

173) Pub/Sub - Sending and Receiving a Message?
Answer: Publisher Sends a message to Topic. Message individually delivered to each and every subscription. Subscribers
can receive a message either by. Push: Pub/Sub sends the message to subscriber. Pull: Subscribers poll for messages.
Subscribers send acknowledgement(s). Message(s) are removed form subscriptions message queue: Pub/Sub ensures for
messages is retained per subscription until it is acknowledged.

174) Cloud Monitoring?
Answer: Tools to monitor your infrastructure. Measures key aspects of services (Metrics). Create visualization (Graphs
and Dashboards). Configure Alerts (when metrics are Not healthy). Define Alerting Policies: Condition, Notifications -
Multiple Channels, Documentation. Workspaces are needed to organize monitoring information. Step1: Create workspace in
a specific project (Host Project). Step2: Add other GCP projects (or AWS accounts) to the workspace. Default metrics
monitored (VMs): CPU utilization, Some disk traffic metrics, Network traffic, Uptime info. Install Cloud Monitoring
Agent on the VM to get more disk, CPU, network, and process metrics: collect-d based daemon. Gathers metrics from VM
and sends them to Cloud Monitoring.

175) Data Access Logs?
Answer: Reading config of resources. Disabled by default. Examples: Listing resources (VMs, images etc.). Cloud Storage:
Modify/Read bucket or object. Access Needed: Logging/Private Logs Viewer or Project/Viewer.

176) Policy Denied Logs?
Answer: When user or service account in derived access. Enabled by default. Examples: Security policy violation logs.
Access Needed: Logging/Logs Viewer or Project/Viewer.

177) Cloud Logging?
Answer: Real time log management and analytics tool. Allows to store, search, analyze and alert on massive volume of data.
Exabyte Scale, fully managed service - No server provisioning, patching. Ingest log data from any source. Key features:
Logs Explorer: Search, sort and analyze using flexible queries. Logs Dashboard: Rich visualization. Logs Metrics: Capture
metrics from logs (using queries/matching strings). Logs Router: Route different log entries to different destinations.

178) Cloud Logging - Collection?
Answer: Most GCP Managed Services automatically send logs to Cloud Logging: GKE, App Engine, Cloud Run. Ingest logs from
GCE VMs: Install Logging Agent (based on fluentd). Run logging Agent on all VM instances. Ingest logs from on-premises:
Use the BindPlane tool from Blue Medora. Use Cloud Logging API.

179) Pub/Sub - Getting Ready with Topic and Subscriptions?
Answer: Step1: Topic is created. Step2: Subscription(s) are created. Subscribers register to the topic. Each Subscription
represents discrete pull of messages from a topic. Multiple clients pull same subscription, message split between
clients. Multiple clients create a subscription each client will get every message.

180) Cloud Pub/Sub?
Answer: Reliable, scalable, fully-managed synchronous messaging service. Backbone for Highly Available and Highly
scalable solutions. Auto-scale to process billions of messages per day. Low cost (pay for use). Use cases: Event
registration and delivery for streaming analytics pipeline. Supports push and pull message deliveries.

181) Pub/Sub - How does it work?
Answer: Publisher sender of message. Publishers send messages by making HTTP's request to 'pubsub.googleapis.com'.
Subscriber: Receiver of messages. Pull: Subscriber pulls messages when ready. Subscriber makes HTTP's request to
'pubsub.googleapis.com'. Push - Messages are sent to subscribers. Subscribers provide a web hook endpoint at the time of
registration. When a message is received on the topic. A HTTP's POST request is sent to the web hook endpoints.
Very Flexible Publisher(s) and Subscriber(s) Relationship: One to Many, Many to One, Many to Many.

182) Relational Database - Import and Export?
Answer: Cloud SQL: to/from Cloud Storage (gcloud sql/csv export/import). From console/gcloud/REST API. SQL and CSV format.
For large database, use serverless made. Reduces performance impact off export on the live database.
Cloud Spanner: to/from Cloud Storage. From Console (uses Cloud Data Flow).
BigQuery: to/from Cloud Storage and Others (bq extract/load). From Console/bq. Formats: CSV/JSON (with gzip compression),
Avro (with deflate or snoppy compression). Variety of options to import data. Load data from Cloud Storage Datastore,
Cloud Storage, BigQuery. Batch loading with BigQuery Data Transfer Service. Dataflow to setup streaming pipeline.

183) NoSQL Databases - Import and Export?
Answer: Cloud Datastore/Firestore: 'to/from' Cloud Storage. From 'console/gcloud/REST' API.
'gcloud datastore/firestore export/import --kinds --namespaces'. Cloud BigTable: 'to/from' Cloud Storage. Create Dataflow
jobs. Formats: 'Avro/Parquet/SequenceFiles'. Remember ensure that service accounts have access to Cloud Storage Buckets.
ACL (gcloud acl ch -U SERVICE_ACCOUNT:W BUCKET). Roles Storage Admin or Storage Object Admin or Storage Object Creator.

184) Databases - important things to remember?
Answer: BigQuery, Datastore, Firebase does NOT need VM configuration. Whereas Cloud SQL and BigTable need VM configuration.
Relational Databases: small local databases: Cloud SQL. 
Highly scalable global databases: Cloud Spanner. 
Data warehouse:BigQuery.
NoSQL Databases: Transactional databases for a few terabytes of data - Cloud Datastore. 
Huge volume of IoT or streaming analytics data - CLoud BigTable.

185) Synchronous Communication?
Answer: Applications on your web server makes synchronous calls to the logging service. What if your logging service goes
down? Will your apps go down too? What if all sudden, there is high load and there are a lot of logs coming in? Log
Service is not able to handle the load and goes down very often.

186) Need for Asynchronous Communication?
Answer: Create a topic and have your apps put log messages on the topic. Logging service picks them up for processing
when ready. Advantages: Decoupling: Publisher (Apps) don't care about who is listening. Availability: Publisher (App) up
even if a subscriber (logging service) is down. Scalability: Scale consumer instances (logging service) under high load.
Durability: Message is not lost even if subscriber (logging service) is down.

187) In-memory Databases?
Answer: Retrieving data from memory is much faster than retrieving data from disk. In-memory database like Redis deliver
microsecond latency by storing persistent data in memory. Recommended GCP Managed Service: Memory Store. Use cases:
Caching, session management, gaming leader boards, geospatial apps.

188) Cloud SQL?
Answer: Fully Managed Relational DB Service: Configure your needs and do NOT worry about managing the database. Supports
MySQL, PostgreSQL, and SQL Server. Regional Service providing High Availability (99.95%). Use SSDs or HDDs for best
performance use SSDs. Up to 416 Giga Bytes RAM and 30 Tera Bytes of data storage. Use CloudSQL for simple relational use
cases: To migrate local MySQL, PostgreSQL and SQL Server database. To reduce your maintenance cost for a simple relational
database. Remember use CloudSpanner instead of CloudSQL if: Huge volumes of relational data (Tera Bytes). You need infinite
scaling. Higher Availability (99.999%). You need global DB.

189) Cloud SQL Features?
Answer: Automatic encryption (tables/backups), maintenance and updates. High availability and failover. Create a standby
with automatic failover. Pre-requisites: Automated backups and Binary Logging. Read replicas for read workloads: Options:
Cross-zone, cross-region, and External (non cloud SQL DB). Automatic storage increase without downtime (for newer versions).
Point-in-time recovery: Enable binary logging. Backups (Automated and on-demand backups). Supports migration for other
sources: Use database migration service (DMD). You can export data from UI (console) or gcloud formats: SQL or CSV.

190) Cloud SQL - High Availability?
Answer: Create a High Availability (HA) Configuration. Choose Primary and Secondary zones within a region. You will have
two instances: Primary and Secondary. Changes from primary are replicated synchronously to secondary. In case of zonal
failure, automatic failover to secondary instance: If primary zone becomes available, failover does not revert automatically.
Remember High Availability setup cannot be used as Read Replica.

191) Cloud Spanner?
Answer: Fully managed, mission-critical, relational SQL, globally distributed with very high availability (99.999%).
Strong transactional consistency at global scale. Scaled to PBs of data with automatic sharding. Cloud Spanner
scales horizontally for reads and writes. Configure no of nodes. Remember in comparison, Cloud SQL provides read replicas:
But you cannot horizontally scale write operations with Cloud SQL! Regional and Multi-Regional configurations. Expensive
(compared to Cloud SQL): Pay for nodes and storage. Data Export: Use Cloud Console to export data: Other option is to
use Data Flow to automate export.

192) BigQuery Data warehouse?
Answer: Exabytes scale modern Data warehousing solution for GCP. Relational database (SQL, schema, consistency). Use sql
like commands to query massive datasets. Traditional (storage + compute), Modern (Realtime + Serverless).
When we are talking about a Data warehouse, importing and exporting data (and formats) becomes very important. Load data
from a variety of services, including streaming data. Variety of import formats - CSV, JSON, Avro, Parquet, ORC,
Datastore backups. Export to Cloud Storage (long term storage) and Data Studio (visualization). Formats - CSV/JSON (with
Gzip compression), Avro (with deflate or snoppy compression). Automatically expire data (configurable table expiration).
Query external data sources without storing data in BigQuery. Cloud Storage, Cloud SQL, BigTable, Google Drive. Use
Permanent or Temporary external tables.

193) BigQuery - Accessing and Querying Data?
Answer: Access database using: Cloud console. 'bq' command-line tool (not 'gcloud'). BigQuery Rest API. HBase API based
libraries (Java, .NET and Python). Remember BigQuery queries can be expensive as you are running them on large data sets!
Best Practice Estimate BigQuery queries before running: Use UI (console) / 'bq' (--dry-run) - get scanned data volume
(estimate). Use Pricing Calculator: Find price for scanning 1 Mega Bytes data. Calculate cost.

194) Cloud Firestore (Datastore) vs Cloud BigTable?
Answer: Cloud Datastore: Managed serverless NoSQL document database. Provides ACID transactions, SQL-like queries indexes.
Design for transactional mobile and web applications. Firestore (next version of Datastore) adds: Strong Consistency.
Mobile and Web Client libraries. Recommended for small to medium databases (0 to a few Terabytes).
Cloud BigTable: Managed, Scalable NoSQL wide column database. Not serverless (you need to create instances). Recommended
for data size > 10 Terabytes to several Petabytes. Recommended for large analytical and operational workloads. Not
recommended for transactional workloads (does not support multi row transactions - supports only single-row transactions).

195) NoSQL Databases?
Answer: NoSQL = not only SQL. Flexible schema. Structure data the way your app needs it. Let the schema evolve with time.
Horizontally scale to petabytes of data with millions of TPS. Not a 100% accurate generalization but a great starting
point. Typical NoSQL databases trade-off "Strong Consistency and SQL features" to achieve "Scalability and high-performance".
Google Managed Services: Cloud Firestore (Datastore), Cloud BigTable.

196) Relational Databases - OLAP vs OLTP?
Answer: OLAP and OLTP use similar data structures. But very different approach in how data is stored. OLTP databases use
row storage. Each table row is stored together. Efficient for processing small transactions. OLAP databases use columnar
storage. Each table column is stored together. High compression - store petabytes of data efficiently. Distribute data -
one table in multiple cluster nodes. Execute single query across multiple nodes - complex queries can be executed
efficiently.

197) Relational Database - OLAP (Online Analytics Processing)?
Answer: Apps allowing users to analyze petabytes of data. Examples: Reporting apps, data warehouses, business
intelligence apps, analytics systems. Sample application: Decide insurance premiums analyzing data from last hundred
years. Data is consolidated from multiple transactional databases. Recommended GCP Managed Service - BigQuery, Petabyte
scale distributed data warehouse.

198) Relational Database - OLTP (Online Transaction Processing)?
Answer: Applications where large number of users make large number of small transactions. Small data reads, updates and
deletes. Use cases: Most traditional apps, ERP, CRM, e-commerce, banking. Popular databases: MySQL, Oracle, SQL Server etc.
Recommended Google Managed Services: CloudSQL supports PostgreSQL, MySQL and SQL server for regional databases (up to
a few Terabytes). CloudSpanner unlimited scale (multiple Petabytes) and 99.999% availability for global apps with
horizontal scaling.

199) Relational Databases?
Answer: It was only options decades back! Most popular type of databases. Predefined schemas with tables and relationships.
Very strong transactional capabilities. Used for: OLTP (Online Transaction Processing) use cases. OLAP (Online Analytics
Processing) use cases.

200) Which destinations can be used as sinks for reporting log data from Cloud Logging?
Answer: Cloud Storage, Cloud BigQuery, Cloud Pub/Sub.

201) Which log buckets receive logs from the (two) Default log sinks?
Answer: '_Default', '_Required'.

202) Types of Cloud Audit Logs?
Answer: Admin Activity Logs. Data Access Audit Logs. Policy Denied Audit Logs.

203) You want to look at the logs for a specific request?
Answer: Cloud Logging.

204) You want to identify prominent exceptions (or errors) for a specific microservice?
Answer: Error Reporting.

205) You want to trace a request across multiple microservices?
Answer: Cloud Trace.

206) You would like to record all operations/requests on all objects in a bucket (for auditing)?
Answer: Turn on data across audit logging for the bucket.

207) Cloud Storage - Static Website?
Answer:
a) Create a bucket with the same name as website name (Name of bucket should match DNS name off the website). Verify that
the domain is owned by you.
b) Copy the files to the bucket. Add index and error html files for better user experience.
c) Add member 'allUsers' and grant storage Object Viewer option. Select Allow Public Access.

208) Database Primer?
Answer: Databases provide organized and persistent storage for your data. To choose between different databases types, we
would need to understand: Availability, Durability, RTO, RPO, Consistency, Transactions.

209) Database - snapshots?
Answer: Let's automate taking copy of the database (take a snapshots) every hour to another data center in London. Lets
consider some challenges.
Challenge 1: Your database will go down if the data center crashes.
Challenge 2 (Partially Solved): You will lose data if the database crashes. You can setup database from latest snapshot.
But depending on when failure occurs you can lose up to an hour of data.
Challenge 3: Database will be slow when you take snapshots.

210) Cloud Storage Signed Url?
Answer: You would want to allow a user limited time access to your objects. Users do not need Google accounts.
Use Signed Url functionality. A URL that gives permissions for limited time duration to perform specific actions.
To create a signed url. One create a key (YOUR_KEY) for the service Account / User with the desired permissions.
Two create Signed URL with the key: 'gsutil signurl -d 10m YOUR_KEY gs://BUCKET_NAME/OBJECT_PATH'.

211) Database - Transaction Log?
Answer: Let's add transaction logs to database and create a process to copy it over to the second data center. Let's
consider challenges:
Challenge 1: Your database will go down if the data center crashes.
Challenge 2 (Solved): You will lose data, if the database crashes. You can setup database from latest snapshot and apply
transaction logs.
Challenge 3: Database will be slow when you take snapshots.

212) Database - Add a standby?
Answer: Let's add a standby database in the second data center with replication. Some challenges:
Challenge 1 (SOLVED): Your database will go down if the data center crashes. You can switch to the standby database.
Challenge 2 (SOLVED): You will lose data if the database crashes.
Challenge 3 (SOLVED): Database will be slow when you take snapshots. Take snapshots from standby. Apps connecting to
master will get good performance always.

213) Availability and Durability?
Answer: Availability: Will I be able to access my data now and when I need it? Percentages of time an application provides
the operations expected of it.
Durability: Will my data be available after 10 or 100 or 1000 years? Examples of measuring availability and durability:
4, 9's - "99.99", 11, 9's - "99.999999999". Typically, an availability of four nines is considered very good. Typically,
a durability of eleven nines is considered very good.

214) Durability?
Answer: What does a durability of 11 9's mean? If you store one million files for ten million years, you would expect to
lose one file. Why should, durability be high? Because we hate losing data. Once we lose data, it is gone.

215) Increasing Availability and Durability of Databases?
Answer: 
Increase Availability: Have multiple standbys available or distribute the database: in multiple regions, in multiple zones. 
Increase Durability: Multiple copies of data (standbys, snapshots, transaction logs and replicas). 
In multiple zones, in multiple regions. Replicating data comes with its own challenges.

216) RTO and RPO?
Answer: Imagine a financial transaction being lost. Imagine a trade being lost. Imagine a stock exchange going down for
an hour. Typically business are fine with some downtime but they hate losing data. Availability and Durability are
technical measures. How do we measure how quickly we can recover from failure? RPO (Recovery Point Objective): Max acceptable
period of data loss. RTO (Recovery Time Objective): Max acceptable downtime. Achieving minimum RTO and RPO is expensive.
Trade off based on the criticality of the data.

217) Database Categories?
Answer: Relational (OLTP and OLAP), Document, Key Value, Graph, In Memory.
Choosing Database factors: Do you want a fixed schema? Do you want flexibility in defining and changing your schema?
(Schema-less). What level of transaction properties do you need? (atomicity and consistency). What kind of latency do you
want? (second, millisecond or microseconds). How many transactions do you expect? (hundreds, thousands, millions transactions
per second). How much data will be stored? (Megabytes, Gigabytes, Terabytes, Petabytes).

218) Cloud Trace?
Answer: Distributed Tracing System for GCP: Collect latency data from: Supported Google Cloud services. Instrumented
applications (using tracing libraries) using Cloud Traces API. Find out: How long does a service take to handle requests?
What is the average latency of requests? How are we doing over time? (increasing / decreasing trend). Supported for:
Compute Engine, GKE, App Engine (Flexible/Standard) etc. Trace Client Libraries available for: "C#", Go, Java, Node.js,
PHP, Python, Ruby.

219) Error Reporting?
Answer: How do you identify prod problems in real time? Real-time exception monitoring. Aggregates and displays errors
reported from cloud services (using stack-traces). Centralized Error Management console: Identify & manage top errors
or recent errors. Use Firebase Crash Reporting for errors from Android & iOS client apps. Supported for Go, Java,
.NET, Node.js, PHP, Python and Ruby. Errors can be reported by: Sending them to Cloud Logging. By calling Error Reporting
API. Error Reporting can be accessed from desktop.

220) Cloud Profiler?
Answer: How do you identify performance bottlenecks in production? Cloud Profiler - Statistical, low-overhead profiler.
Continuously gathers CPU and Memory Usage from prod systems. Connecting profiling data with app source code: Easily
identify performance bottlenecks. Two major components: Profiling agent (collects profiling info). Profiler interface
(visualization).

221) Service Level Objective (SLO) - SLI plus target?
Answer: 99.99% availability, 11 9's durability. Response time: 99th Percentile - 1 second. Choosing an appropriate SLO
is complex.

222) Which of these roles would you map to your users to allow them access to apps protected by Identity Aware Proxy (IAP)?
Answer: IAP - Secured Web App User.

223) You are sharing GKE cluster between different teams. You want to ensure that each team has access to deploy only
to their specific namespaces?
Answer: Kubernetes RBAC.

224) Which role gives you complete access to GKE clusters and the Kubernetes API objects?
Answer: Kubernetes Engine Admin (roles/container.admin).

225) You are assigned permissions using RBAC. But you are unable to access the cluster?
Answer: Check if IAM member has the permission - 'container.cluster.get'.

226) OAuth?
Answer: Allows users to grant access to server resources to another entity without sharing credentials. Example: Give
access to your Google Drive to Your Photo Editing Application.

227) OpenID Connect (OIDC)?
Answer: OAuth is only about authorization. Does not care about how authentication is implemented. What if you want to
allow users to log in with Google Id into your app? Use OpenID Connect (OIDC). OpenID Connect extends OAuth2.

228) How ACL (Access Control List) is different from IAM?
Answer: IAM permissions apply to all objects within a bucket. ACLs can be used to customize specific accesses to
different objects. IAM - common permissions. ACL - Customize access to individual objects.

229) You want to store your encryption keys on-premises, encrypt data on-premises and send encrypted data to Cloud
Storage. Which of these approaches would you recommend?
Answer: Client-Side Encryption.

230) Kubernetes Stateful Deployments?
Answer: Kubernetes supports stateful deployments like Kafka, Redis, Zookeeper. StatefulSet - Set of Pods with unique,
persistent identities and stable hostnames.

231) GKE - How do we run Services on nodes for log collection or monitoring?
Answer: DaemonSet - One pod on every node! For background services.

232) Kubernetes Cluster - Enable auto-scaling for the microservice. You also want to increase the number of nodes in the
cluster if existing nodes are not sufficient?
Answer: Use 'kubectl autoscale deployment' command to scale pods and has 'gcloud container cluster update' command with
'--enable-autoscaling' to auto-scale cluster.

233) How can a service in a Kubernetes cluster talk to another service in the same Kubernetes cluster without hardening
the URL?
Answer: Service Discovery. Fully qualified domain name (FQDN) - 'SERVICE-NAME.NAMESPACE-NAME.svc.cluster.local'.

234) Kubernetes Services and Ingress?
Answer: Services are sets of Pods with a network endpoint that can be used for discovery and load balancing. Ingress are
collections of rules for routing external HTTPS traffic to Services.

235) App Engine, which types of scaling does NOT allow specifying a max number of instances?
Answer: Manual-scaling.

236) App Engine - Remember?
Answer: App Engine is Regional (services deployed across zones). You cannot change an App's region. Good option for
simple microservices (multiple services). Use standard v2 when you are using supported languages. Use Flexible if you
are building containerized apps. Be aware-at least one container is always running when using flexible. Go for standard
if you want to be able to scale down the number of instances to zero when there is no load. Use a combination of resident
and dynamic instances. Resident Instances: Run continuously. Dynamic Instances: Added based on load. Use all dynamic
instances if you are cost sensitive. If you are not very cost sensitive, keep a set of resident instances running always.

237) Kubernetes Deployment Microservice steps?
Answer:
a) Create a Kubernetes cluster with the default node pool: 'gcloud container cluster create' or use cloud console.
b) Login to Cloud Shell.
c) Connect to Kubernetes Cluster: 
"gcloud container cluster get-credentials my-cluster --zone us-central1-a --project my-project-123".
d) Deploy Microservice to Kubernetes: Create deployment and Service Using kubectl commands:
"kubectl create deployment hello-world --image=img:REL". 
"kubectl expose deployment hello-world --type=LoadBalancer --port=8080".
e) Increase number of instances of your microservice: 
"kubectl scale deployment hello-world --replicas=2".
f) Increase number of nodes in your Kubernetes cluster:
"gcloud container cluster resize my-cluster --node-pool my-node-pool --replicas=2". 
Not happy about manually increasing number of instances and nodes!
g) Setup auto-scaling for your microservice: 
"kubectl autoscale deployment hello-world --max=10 --cpu-percent=70".
Also called horizontal pod autoscaling - HPA - "kubectl get hpa".
h) Setup auto-scaling for your Kubernetes Cluster:
"gcloud container cluster update cluster-name --enable-autoscaling --min-nodes=1 --max-nodes=10".
i) Add some app config for your microservice. Config Map:
"kubectl create configmap todo-web-application-config --from-literal=RDS_DB_NAME=todos".
j) Add password configuration for your microservice: kubernetes secrets.
"kubectl create secret generic todo-web-app-secrets-1 --from-literal=RDS_PWD=dummypwd".
k) Deploy a new microservice which needs nodes with a GPU attached. Attach a new node pool with GPU instances to your
cluster.
"gcloud container node-pools create POOL_NAME --cluster CLUSTER_NAME".
"gcloud container node-pools list --cluster CLUSTER_NAME".
Deploy the new microservice to the new pool by setting up nodeSelector in the "deployment.yaml". nodeSelector:
"cloud.google.com/gke-nodepool:POOL_NAME".
l) Deploy the Microservice. 
Delete service: "kubectl delete service". 
Delete deployment: "kubectl delete deployment".
m) Delete the Cluster: "gcloud container cluster delete".

238) Google Kubernetes Engine (GKE) Cluster?
Answer: Cluster: Group of Compute Engine Instances: Master Node(s): Manages the Cluster. Worker Node(s): Run your workload
(pods). Master Node (Control Plane) components. API Server: Handles all communication for a k8s cluster (from nodes and
outside). Scheduler: Decides placement of pods. Control Manager: Manages deployments and replica sets.
Etcd: Distributed database storing the cluster state. Worker Node components: Runs your pods. Kubelet - Manages
communication with master node(s).

239) GKE Cluster Types?
Answer: Zonal Cluster: Single zone - Single Control Plane. Nodes running in the same zone. Multi-zonal - Single Control
Plane but nodes running in multiple zone. Regional Cluster: Replicas of the control runs in multiple zones of a given
region. Nodes also run in same zone where control plane runs. Private Cluster: VPC - native cluster. Nodes only have
internal IP addresses. Alpha Cluster: Clusters with alpha APIs - early feature API's. Used to test new K8s features.

240) Kubernetes - Pods?
Answer: Smallest deployable unit in Kubernetes. A Pod contains one or more containers. Each Pod is assigned an ephemeral
IP address. All containers in Pod share: Network, Storage, IP Address, Ports and Volumes (Shared persistent disks).
Pod statuses: Running, Pending, Succeeded, Failed, Unknown.

241) Kubernetes - Deployment vs ReplicaSet?
Answer: A deployment is created for each microservice: 'kubectl create deployment m1 --image=img:v1'. Deployment represents
a microservice (with all its releases). Deployment manages new releases ensuring zero downtime. ReplicaSet ensure that a
specific number of pods are running for a specific microservice version: 'kubectl scale deployment m2 --replicas-2'.
Even if one of the Pod is killed, replica set will launch a new one. Deploy V2 microservice - creates new replica set.

242) Kubernetes - Service?
Answer: Each Pod has its own IP address. How do you ensure that external users are not impacted when: A Pod fails and is
replicated by replica set. A new release happens and all existing Pods of old release are replaced one by one of new release.
Create Service: 'kubectl expose deployment name --type=LoadBalancer --port=80'. Expose Pods to outside world using a
stable IP Address. Ensures that the external world does not get impacted as Pods go down and come up.
Three Types:
a) ClusterIP: Exposes Service on a cluster internal IP. Use cases: You want your microservice only be available inside
the cluster (Intra Cluster communication).
b) LoadBalancer: Exposes Service externally using a cloud provider's load balancer. Use case: You want to create individual
Load Balancers for each microservice.
c) NodePort: Exposes Service on each Node's IP at a static port (the NodePort). Use case: You do not want to create an
external Load Balancer for each microservice (You can create one Ingress component to load balance multiple microservices).

243) Container Registry - Image Repository?
Answer: You've created docker images for your microservices: Where do you store them? Container Registry - fully-managed
container registry provided by GCP. (Alternative) Docker Hub. Can be integrated to CI/CD tools to publish images to
repository. You can secure your container images. Analyze for vulnerabilities and enforce deployment policies. Naming:
'HostName / ProjectID / ImageTag - gcr.io'.

244) GKE - Things to Remember?
Answer: Replicate master nodes across multiple zones for high availability. 
Some CPU on the nodes is reserved by Control Plane. 
Creating Docker Image for your microservice (Dockerfile). Build Image: "docker build -y some/name : V1 .".
Test it locally: "docker run -d -p 8080:8080 some/name : V1".
Push it to Container Repository: "docker push some/name : V1".
Kubernetes supports stateful deployments like Kafka, Redis, ZooKeeper. 
StatefulSet: Set of Pods with unique, persistent identifiers and stable hostname. 
How do we run services on nodes for log collection or monitoring? 
DaemonSet: One Pod on every node! (For background services). 
Enabled by default, Integrates with Cloud Monitoring and Cloud Logging. 
Cloud Logging System and Application Logs can be exported to BigQuery or Pub/Sub.

245) You want to keep your costs low and optimize your GKE implementation?
Answer: Consider Preemptible VMs, Appropriate region, committed use discounts. E2 machine types are cheaper then N1.
Choose right environment to fit your workload type (use multiple node pools if needed).

246) You want an efficient, completely auto-scaling GKE solution?
Answer: Configure Horizontal Pod Autoscaler for deployments and Cluster Autoscaler for node pools.

247) You want to execute untrusted third-party code in Kubernetes?
Answer: Create a new node pool with GKE sandbox. Deploy untrusted code to sandbox node pool.

248) What purpose do instance groups play in a Kubernetes Cluster?
Answer: They create sets of VMs that can be managed as a unit.

249) IAM - Predefined Roles - Example Permissions?
Answer: Important Cloud Storage Roles: Storage Admin (roles/storage.admin) - 'storage.buckets', 'storage.objects'.
Storage Object Admin (roles/storage.objectAdmin) - 'storage.objects'. Storage Object Creator (roles/storage.objectCreator) -
'storage.objects.create'. Storage Object Viewer (roles/storage.objectViewer) - 'storage.object.get', 'storage.objects.list'.
All four roles have these permissions: 'resourcemanager.projects.get', 'resourcemanager.projects.list'.

250) Google Kubernetes Engine - Autopilot Mode?
Answer: New mode for GKE. Reduce your operational costs in running Kubernetes cluster. Provides and hands-off experience.
Do not worry about managing the cluster infrastructure (nodes, node pools). GKE will manage the cluster for you!

251) Google Kubernetes Engine (GKE)?
Answer: Managed Kubernetes Service. Minimize operations with auto-repair and auto-upgrade. Provides Pod and Cluster
Autoscaling. Enable Cloud Logging and Cloud Monitoring with simple configuration. Uses container optimized OS,
a hardware OS build by Google. Provides support for Persistent disks and Local SSD.

252) GKE - Workload Management Command Line?
Answer: List Pods, Services, ReplicaSets. 'kubectl get pods / services / replicasets'.
Create Deployment: 'kubectl apply -f deployment.yaml' or 'kubectl create deployment'.
Create Service: 'kubectl expose deployment hello-world --type=LoadBalancer --port=8080'.
Scale Deployment: 'kubectl scale deployment hello-world --replicas=5'.
Autoscale Deployment: 'kubectl autoscale deployment --min --max --cup-percent'.
Delete Deployment: 'kubectl delete deployment hello-world'.
Update Deployment: 'kubectl apply -f deployment.yaml'.
Rollback Deployment: 'kubectl rollout undo deployment hello-world --to-revision=1'.

253) Kubernetes Terminology?
Answer: Hardware (Cluster). Master Node(s) - Manages the cluster. Worker Node(s) - Run your workload as pods. Node Pool -
Group of nodes in a cluster with the same configuration. Software. Pods - smallest deployable unit in Kubernetes.
Deployed to Worker Node(s). Deployment - Manage Pods. Service - Expose Deployments.

254) GKE - Cluster Management - Command Line?
Answer: Create Cluster: 'gcloud container cluster create'.
Resize Cluster: 'gcloud container cluster resize'.
Autoscale Cluster: 'gcloud container cluster update'.
Delete Cluster: 'gcloud container cluster delete'.
Adding Node Pool: 'gcloud container node-pools create'.
List Images: 'gcloud container images list'.

255) My pod stays waiting?
Answer: Most probably failure to pull the image.

256) My pod stays pending?
Answer: Most probably Pod cannot be scheduled onto a node (insufficient resources).

257) You want to enable only internal communication between your microservice deployments in a Kubernetes Cluster?
Answer: Create Service of type ClusterIP.

258) Command to delete an instance group template?
Answer: 'gcloud compute instance-template delete'.

259) Kubernetes?
Answer: Most popular open source container orchestration solution. Provides Cluster Management (including upgrades).
Each cluster can have different types of virtual machines. Provides all important container orchestration features.
Auto Scaling, Service Discovery, Load Balancer, Self Healing, Zero Downtime Deployment.

260) Commands to connect to a Kubernetes Cluster?
Answer: 'gcloud container cluster get-credentials'.

261) You want to run one pod on each node for log collection or monitoring. Which of Kubernetes components would you use?
Answer: DaemonSet.

262) Which worker node component is responsible for communication with the master node?
Answer: kubelet.

263) Cloud KMS?
Answer: Create and manage cryptographic keys (symmetric and asymmetric). Control their use in your applications and GCP
services. Provides an API to encrypt, decrypt or sign data. Use existing cryptographic keys created on premises. Integrates
with almost all GCP that need data encryption. Google-managed keys: No configuration required. Customer-manager key: Use
key from KMS. Customer-supplied key: Provide your own key.

264) Asymmetric Key Encryption?
Answer: Two keys: Public key and Private key. Also called Public Key Cryptography. Encrypt data with Public key and
decrypt with Private Key. Share Public Key with everybody and keep the private key with you. No crazy questions: Will
somebody not figure out private key using the public key?

265) Persistent Disks - Snapshots - Recommendations?
Answer: Avoid taking snapshots more often than once an hour. Disk volumes is available but snapshots reduce performance
(create during off-peak). Creating from disk is faster than from images. But creating disks from image is faster than
creating from snapshots. If you are repeatedly creating disks from snapshots. Create an image from snapshot and use the
image to create disks. Snapshots are incremental: But you don't lose data by deleting older snapshots. Deleting a snapshot
only deletes data which is NOT needed by other snapshots. Do not hesitate to delete unnecessary snapshots.

266) Symmetric Key Encryption?
Answer: Symmetric encryption algorithms use the same key from encryption and decryption. 
Key Factor 1: Choose the right encryption algorithm. 
Key Factor 2: How do we secure the encryption key? 
Key Factor 3: How do we share the encryption key.

267) Encryption?
Answer: If you store data as is, what would happen if an unauthorized entity gets access to it? Imagine Losing an
unencrypted hard disk. First law of security: Defense in Depth. Typically, enterprises encrypt all data. Data on your
hard disk. Data in your database. Data on your file servers. Is it sufficient if you encrypt data at rest? No. Encrypt
data in transit - between app to database as well.

268) Data states?
Answer: Data at rest: Stored on a device or a backup. Examples: data on hard disk, in a database, backups and archives.
Data in motion: Being transferred across a network. Also called Data in transit. Examples: Data copied from on-premise
to cloud storage, an app talking to database. Two types: In and out of cloud (from internet). Within cloud. Data in use:
Active data processed in a non-persistent state. Example data in RAM.

269) Which Kubernetes component is responsible for replacing an unhealthy pod?
Answer: ReplicaSet.

270) Dedicated connection from your data center to GCP. What networking service would you recommend?
Answer: Google Cloud Interconnect - Dedicated.

271) Playing with Disks - Command Line?
Answer: 'gcloud compute disk create'. What should be the size and type? What should be on the disk? How
should data on disk be encrypted?
'gcloud compute disks resize'. Only increasing disk size is supported.
'gcloud compute disks snapshot test'.

272) Snapshots vs Images vs Machine Images?
Answer: Machine Image: Single disk backup. Multiple disk backup. Differential backup. Instance cloning and replication.
VM instance config.
PD snapshots: Single disk backup. Differential backup.
Custom Image: Single disk backup. Instance cloning and replication.
Instance Template: Instance cloning and replication. VM instance config.

273) Playing with Machine Images?
Answer: Machine images is different from image. Multiple disks can be attached with VM. One Boot Disk (Your OS runs from
Boot Disk). Multiple Data Disk. An image is created from the boot Persistent Disk. However, a Machine Image is created
from a VM instance. Machine Image contains everything you need to create a VM instance. Configuration, Metadata,
Permissions, Data from one or more disks. Recommended for disk backups, instance cloning and replication.

274) Can a MIG contain VMs created with different machine types?
Answer: No.

275) Same GCP services have specific CLI tools?
Answer: 
Cloud Storage: 'gsutil'. 
Cloud BigQuery: 'bq'. 
Cloud BigTable: 'cbt'. 
Kubernetes: 'kubectl' (in addition to gcloud which is used to manage clusters).

276) 'gcloud config list'?
Answer: Lists all properties of the active configuration.

277) IAAS (Infrastructure as a Service)?
Answer: Use only infrastructure from cloud provider. Example: Using VM to deploy your apps or databases. You are
responsible for. Application code and Runtime. Configuring Load Balancing. Auto Scaling. OS upgrades and patches.
Availability.

278) PAAS (Platform AS A Service)?
Answer: Use a platform provided by cloud. Cloud Provider is responsible for: OS (including upgrades and patches).
App Runtime. Auto Scaling, Availability and Load Balancing. You are responsible for: Configuration (of Application
Service). Application code (if needed). Varieties: CAAS (Container AS A Service): Containers instead of Apps.
FAAS (Function as a Service): Functions instead of Apps. Databases-Relational and NoSQL (RDS, Cloud SQL), Queues, AI, ML,
Operations.

279) Compute Engine?
Answer: High-performance and general purpose VMs that scale globally.

280) Google Kubernetes Engine?
Answer: Orchestrate containerized microservices on Kubernetes. Needs advanced cluster configuration and monitoring. CaaS.

281) App Engine?
Answer: Build highly scalable apps on a fully managed platform using open and familiar languages and tools. PaaS (CaaS,
Serverless).

282) Cloud Functions?
Answer: Build event driven apps using, single-purpose functions. FAAS, Serverless.

283) Cloud Run?
Answer: Develop and deploy highly scalable containerized apps. Does not need a cluster! CaaS (Serverless).

284) Persistent Disks vs Local SSDs?
Answer: Persistent Disk:
a) Attached to VM instance: As a network drive.
b) Lifecycle: Separate from VM instance.
c) I/O Speed: Lower (network latency).
d) Snapshots: Supported.
e) Use case: Permanent Storage.
Local SSDs:
a) Attached to VM instance: Physically attached.
b) Lifecycle: Tied with VM instance.
c) I/O Speed: 10-100 times of PDs.
d) Snapshots: Not Supported.
e) Use case: Ephemeral Storage.

285) Persistent Disks - Snapshots?
Answer: Take point-in-time snapshots of your Persistent Disks. You can also schedule snapshots configure a schedule:
You can also auto-delete snapshots after X days. Snapshots can be Multi-regional and Regional. You can share snapshots
across projects. You can create new disks and instances from snapshots. Snapshots are incremental: Deleting a snapshot
only deletes data which is NOT needed by other snapshots. Keep similar data together on a Persistent Disk: Separate
your operating system, volatile data and permanent data. Attach multiple disks if needed. This helps to better organize
your snapshots and images.

286) Persistent Disk (PD)?
Answer: Network block storage attached to your VM instance. Provisioned capacity. Very flexible: Increase size when you
need it - when attached to VM instance. Performance scales with size. For higher performance, resize or add more PDs.
Independent lifecycle from VM instance - Attach/Detach from one VM instance to another. Options: Regional and Zonal.
Zonal replicated in single zone. Regional in 2 zones in same regions. Use case: Run your custom database.

287) Local SSDs - Advantage and Disadvantage?
Answer: Advantages: Very fast I/O (10 to 100 times compared to PDs). Higher throughput and lower latency. Ideal for use
cases needing high IOPs while storing temporary information. Examples: Caches, temporary data, scratch files, etc.
Disadvantages: Ephemeral storage. Lower durability, lower availability, lower flexibility compared to PDs. You cannot
detach and attach it to another VM instance.

288) Local SSDs?
Answer: Physically attached to the host of VM instance. Provide very high (IOPs) and very low latency. Ephemeral Storage -
Temporary data (Data persists only until instance is running). Enable live migration for data to survive maintenance events.
Data automatically encrypted. However, you cannot configure encryption keys. Lifecycle tied to VM instance. Only same
machine types support Local SSDs. Supports SCSI and NVMe interfaces. Remember: Choose NVMe enabled and multi-queue
SCSI images for best performance. Larger Local SSDs (more storage), more vCPUs (attached to VM). Even Better Performance.

289) GCP - Block Storage?
Answer: Two popular types of block storage can be attached to VM instances. Local SSDs, Persistent Disks. Local SSDs are
physically attached to the host of the VM instances. Temporary data, Lifecycle tied to VM instance. Persistent Disks are
network storage. More durable. Lifecycle Not ties to VM instance.

290) GCP - Block Storage and File Storage?
Answer: Block Storage: Persistent Disks: Network Block Storage. Zonal: Data replicated in one zone. Regional: Data
replicated in multi zone. Local SSDs: Local Block Storage. File Storage: Filestore: High performance file storage.

291) File Storage?
Answer: Media workflow need huge shared storage for supporting process like video editing. Enterprise users need a quick
way to share files in a secure and organized way. These file shares are shared by several virtual servers.

292) Block Storage?
Answer: Hard disk attached to your computers. Typically, one bock storage device can be connected to one virtual server.
Exceptions, you can attach read only block devices with multiple virtual servers and certain cloud providers are
exploring multi-writers disks as well! However, you can connect multiple different block storage devices to one virtual
server. Used as: Direct-attached storage (DAS): Similar to a hard disk. Storage Area Network (SAN) - High Speed network
connecting a pool of storage devices: used by Databases - Oracle and Microsoft SQL Server.

293) Storage Types - Block Storage and FIle Storage?
Answer: What is the type of storage of your hard disk? Block Storage. 
You've created a file share to share a set of files with your colleagues in an enterprise. 
What type of storage are you using? File Storage.

294) Scalability?
Answer: Scalability is the ability of a system to handle increases or decrease in workload by adding or deleting the
resources to the systems as needed.

295) Availability?
Answer: Availability is defined as the ability of user to access a resource at specific time. Availability is usually
measured as the percentage of time that system is operational.

296) Bootstrapping?
Answer: Install OS patches or software when an VM instance is launched.

297) Reliability?
Answer: Reliability is defined as the probability that a system will meet service-level objectives for some duration of
time. Reliability is often measured as mean time between failures.

298) Maintainability?
Answer: Maintainability is defined as the probability of performing a successful repair action within a given time.

299) Adding Additional disk through Google Cloud Console, which params can be set?
Answer: Disk Type. Encryption Key Management. Source image for the disk.

300) gcloud command to assign a service account when creating a VM?
Answer: 'gcloud compute instance create INSTANCE_NAME --service-account SERVICE-ACCOUNT-EMAIL'.

301) What is the lowest level of resource hierarchy?
Answer: Project.

302) Cloud Dataproc?
Answer: Cloud Dataproc is a managed service for Hadoop and Spark.

303) What Objects in GCP are sometimes treated as resources and sometimes as identities?
Answer: Service account.

304) Kubernetes Features?
Answer: Load Balancing across Compute Engine VMs that are deployed in a Kubernetes cluster.
Automatic scaling of nodes in the cluster. Automatic upgrading of cluster software as needed.

305) How many organizations can you create in a resource hierarchy?
Answer: 1. A resource hierarchy has only one organization. You can, however, create multiple folders and projects within
a resource hierarchy.

306) Database for IoT collected data?
Answer: Cloud Bigtable. Bigtable is designed to accept billions of rows of data.

307) Data warehouse?
Answer: BigQuery. BigQuery is a managed service for data warehouses and analytics support tens of petabytes of data.

308) IoT, stream processing component (filtered, transformed and analyzed)?
Answer: Cloud Dataflow. Cloud Dataflow allows for stream and batch processing of data and is well suited for this kind
of ETL.

309) What is a Pod in Kubernetes?
Answer: A single instance of a running process in a cluster.

310) Stackdriver is used for what operations on Kubernetes Cluster?
Answer: Notifications, monitoring and logging.

311) You need to create a table called 'iot-ingest-data' in BigTable. What command would you use?
Answer: 'cbt createtable iot-ingest-data'.

312) Kubernetes, where would you look for details on the number of Pods that should be running?
Answer: ReplicaSets.

313) What organizing components are available in GCP?
Answer: Organization, folder, projects.

314) Which type of storage system would you use to store data fies of the database?
Answer: Block storage.

315) Command to create a network Load Balancer at the command line?
Answer: 'gcloud compute forwarding-rules create'.

316) The command to create a VPC from the command line?
Answer: 'gcloud compute networks create'.

317) Kubernetes reserves CPU resources in percentage of cores available. The percentage is what range?
Answer: 0.25 percent to 6 percent.

318) What kind of instances are required to have a Kubernetes cluster?
Answer: A cluster master and nodes to execute workloads.

319) At what levels of the resource hierarchy can a shared VPC be created?
Answer: Organizations and folders.

320) Firestore?
Answer: Firestore is a document database that has mobile supporting features, like data synchronization.

321) What part of a firewall rule determines whether a rule is applies to incoming or outgoing traffic?
Answer: Direction.

322) Command for administering resources in Compute Engine?
Answer: 'gcloud compute instances'.

323) 'iam.serviceAccountUser'?
Answer: What a user is granted 'iam.serviceAccountUser' at the project level, that user can manage all service
accounts in the project.

324) All possible options for transitioning from one storage class to another?
Answer: Nearline to Coldline. Regional to nearline. Multiregional to coldline. Regional to Multiregional or Multiregional
to Regional are not possible.

325) Firewall rules for service endpoint that accepts any UDP traffic and each endpoint will use a port in the range of
20000 - 30000?
Answer: 'gcloud compute firewall-rules create fwr1 --allow=udp:20000-30000 --direction=ingress'.

326) Command to create a backup of a Cloud SQL? 
Answer: 'gcloud sql backup create'.

327) When you create a cloud router, what kind of unique identifier do you need to assign for the BGP protocol?
Answer: ASN (Autonomous, System number) is a number used to identify a cloud router on a network.

328) Command to create a buckets in Cloud Storage?
Answer: 'gsutil mb'.

329) What does term identity refers to?
Answer: User.

330) What role gives users full control over Compute Engine Instance?
Answer: Compute Admin Role.

331) Compute Security Admin role?
Answer: Gives users the privileges to create, modify and delete SSL certificates and firewall rules.

332) Which regional Load Balancer allows for LB based on IP protocol, address and port?
Answer: Network TCP/UDP.

333) Security measures to reduce the risk of DNS spoofing and cache poisoning?
Answer: Using DNSSEC.

334) A scope is identified using what kind of identifier?
Answer: A URL beginning with https://www.googleapis.com/auth.

335) Cloud Query Roles?
Answer: BigQuery Admin. BigQuery Data Owner. BigQuery Data Editor. BigQuery Data Viewer. BigQuery Job User.
BigQuery User. To see data, you need either BigQuery User or BigQuery Data Viewer roles: You cannot see data with
BigQuery Job User roles. BigQuery Data Owner or Data Viewer roles do NOT have access to jobs.

336) Cloud Storage - Roles?
Answer: Storage Admin. Storage Object Admin. Storage Object Creator. Storage Object Viewer. Container Registry container
images in Cloud Storage buckets: Control access to images in Container Registry using Cloud Storage permissions! Storage
Admin vs Storage Object Admin. Storage Admin can create buckets and play with objects. Storage Object Admin cannot create
buckets but can play with objects in a bucket!

337) Google Kubernetes Engine (GKE) IAM Roles?
Answer: Kubernetes Engine Admin - Complete Access to Cluster and Kubernetes API objects. 
Kubernetes Engine Cluster Admin - Provides access to management of Clusters (cannot access Kubernetes API objects - 
Deployments, Pods etc.).
Kubernetes Engine Developer - Manage Kubernetes API objects (and read cluster info). 
Kubernetes Engine Viewer - get / list cluster and Kubernetes api objects.

338) What is a secure way of setting up application deployment? Roles?
Answer: Application Deployer: Roles: App Engine Deployer plus Service Account User. Limited to deploying new versions and
deleting old versions that are not serving traffic. Will NOT be able to configure traffic. Operations - Role: App Engine
Service Admin. Cannot deploy a new version of an app. Change traffic between versions.

339) What is the difference between Compute Engine Admin vs Compute Instance Admin?
Answer: Compute Instance Admin can do everything with instances and disks only. Compute Engine Admin is admin for
everything in compute-instances, disks, images, network, firewalls etc.

340) App Engine Roles?
Answer: App Engine Creator. App Engine Admin. App Engine Viewer. App Engine Code Viewer. App Engine Deployer.
App Engine Service Admin. App Engine Roles do not allow you to: View and download application logs. View and monitor
charts in the Cloud Console. Enable and Disable billing. Access to configuration or data stored in other services.

341) Compute Engine Roles?
Answer: Compute Engine IAM Roles:
a) Compute Engine Admin: Complete control of compute-instances, images, load balancers, network, firewalls etc.
b) Compute Instance Admin: Create, modify and delete virtual machine instances and disks.
c) Compute Engine Network Admin: Complete access to networking resources (routers, networks, health checks, VPN, Gateways, etc.)
and READ ONLY access to (firewall rules and SSL certificates).
d) Compute Engine Security Admin: Complete access to firewall rules and SSL certificates.
e) Compute Storage Admin: Complete access to disks, images, snapshots.
f) Compute Engine Viewer: Read only access to everything in compute.
g) Compute OS Admin Login: Login to a Compute Engine instance as an administrator user.
h) Compute OS Login: Log in to a Compute Engine instances as a standard user.

342) I'm a billing auditor?
Answer: Roles needed: Billing Account Viewer role.

343) I'm creating a project and I want to associate an existing billing account with the project?
Answer: Roles needed: Project Creator and Billing Account User (link project to billing account).

344) Billing Roles - Quick Review?
Answer:
a) Billing Account Creator: Permissions to create a new billing account. Finance Team.
b) Billing Account Administrator: Manages billing account but can't create them. Finance Team.
c) Billing Account User: Assigns projects to billing accounts. Project Owner.
d) Billing Account Viewer: View only access to billing account. Auditor.

345) Cloud Run - From the command line?
Answer: Deploy a new Container: 'gcloud run deploy SERVICE-NAME --image=IMAGE-URL --version --suffix v1'.
First deployment creates a service account first revision. Next for the same service create new revisions.
List available revisions: 'gcloud run revisions list'.
Adjust traffic assignments: 'gcloud run services update --traffic myservice --to-revisions=v2=1-,v2=90'.

346) Cloud Run & Cloud Run For Anthos?
Answer: Cloud Run - "container to production in seconds". Build on top of an open standard - knative. Fully managed
serverless platform for containerized apps: Zero infrastructure management. Pay-per-use (For used CPU, Memory, Request and
Network). Fully integrated end-to-end developer experience: No limitations in languages, binaries and deployments.
Easily portable because of containers. Cloud Code, Cloud Build, Cloud Monitoring & Cloud Logging Integrations.
Anthos: Run Kubernetes Clusters anywhere. Cloud, Multi Cloud and On-Premises. Cloud Run for Anthos: Deploy your workload
to Anthos clusters running on-premises or on Google Cloud.

347) Cloud Function - Remember?
Answer: No Server Management: You don't need to worry about scaling or availability of your Function. Cloud Functions
automatically spin up and back down in response to events. They scale horizontally! Cloud Functions are recommended
for responding to events: Cloud Functions are NOT ideal for long running processes.

348) Cloud Functions - Concepts?
Answer: Event: Upload object to cloud storage. Trigger: Respond to event with a Function call. Trigger: Which function
to trigger when an event happen? Functions: Take event data and perform actions? Events are triggered from:
Cloud Storage, Cloud Pub/Sub, HTTP POST/GET/DELETE/PUT/OPTIONS, Firebase, Cloud Firestore, Stack driver Logging.

349) Cloud Functions?
Answer: Imagine you want to execute some code when an event happen? A message arrives to Pub/Sub etc.
Enter Cloud Functions: Run code in response to events. Write in Python, Java, Node.js etc.
Don't worry about servers or scaling or availability. Pay only for what you use. Number of invocations. Memory and CPU
provisioned. Time Bound - Default 1 min and MAX 60 minutes (3600 seconds). 2 product version: 1st Gen, 2nd Gen.

350) Which Role is required? View data in BigQuery?
Answer: BigQuery Data Viewer.

351) Which Role? Manage service accounts?
Answer: Service Account Admin.

352) Which Role? Manage Kubernetes API objects?
Answer: Kubernetes Engine Developer.

353) Which Role? Upload objects to Cloud Storage?
Answer: Storage Object Creator.

354) You want to give access to a set-off resources to your development team?
Answer: Create a Group with your development team as member. Bind the right Predefined Roles to your Group.

355) IAM Scenario. You want to provide time limited access to a specific object in a Cloud Storage Bucket?
Answer: Create a signed url.

356) IAM Scenario. You want to give permanent access to the entire bucket in a Cloud Storage Bucket?
Answer: Use IAM.

357) IAM Scenario. You want to give permanent access to a subset of objects in a Cloud Storage Bucket?
Answer: Use Access Control List (ACLs).

358) SSHing into Linux VMs - Details?
Answer:
a) Console - SSH Button.
b) Gcloud - 'gcloud compute ssh'.
c) Use customize SSH keys.

359) SSHing into Linux VMs - Options?
Answer: Compute Engine Linux VMs uses key-based SSH authentication. Two options:
a) Metadata managed: manually create and configure individual SSH keys.
b) OS Login: Manage SSH access without managing individual SSH keys! Recommended for managing multiple users across
instances or project. Your Linux user account is linked to your Google Identity. To enable: Set enabled - os login to
true in metadata. Ability to import existing linux accounts from on premises AD and LDAP. Users need to have roles:
'roles/compute.oslogin' or 'roles/compute.osAdminLogin'. Windows password authentication.

360) Other Important IAM Roles?
Answer: securityAdmin, securityReviewer, organizationRoleAdmin, organizationRoleViewer, roleAdmin, roleViewer, roles/browser.

361) Logging IAM Roles and Service Account Roles?
Answer: Logging and Audit Logging: Logs Viewer, Private Log Viewer, Logging Admin.
Service Accounts: serviceAccountAdmin, serviceAccountUser, serviceAccountTokenCreator, serviceAccountKeyAdmin.

362) Organization, Billing and Project Roles?
Answer:
a) Organization Administrator: Define Resource Hierarchy. Define Access Management Policies. Manage other users and roles.
b) BillingAccountCreator: Create Billing Accounts.
c) Billing Account, Administrator: Manage Billing Accounts (payment instruments, billing exports, link and unlink projects,
manage roles on billing account). Cannot create a Billing Account.
d) Billing Account User: Associate Projects with Billing Accounts: Typically used in combination with Project Creator.
These two roles allow user to create new project and link it with billing account.
e) Billing Account Viewer: See all Billing Account details.

363) Resource Hierarchy & IAM Policy?
Answer: IAM Policy can be set at any level of the hierarchy. Resources inherit the policies of All parents. The effective
policy for a resource is the union of the policy on that resource and its parents. Policy inheritance is transitive:
For example: Organization policies are applied at resource level. You can't restrict at lower level if permission is
given at a higher level.

364) Organization Policy Service?
Answer: How to enable centralized constraints on all resources created in an organization? Configure Organization Policy.
Example: Disable creation of Service Account. Example: Allow/Deny creation of resources in specific region.
Needs a Role - Organization Policy Administrator. IAM focus on Who. Who can take specific actions on resources?
Organization Policy focuses on What. What can be done on specific resources?

365) Your app deployed on a GCE VM (Project A) needs to access cloud storage bucket from a different project (Project B).
Answer: In Project B, assign the right role to GCE VM service from Project A.

366) You want to provide an external auditor access to views all resources in your project BUT he should NOT be able to
make any changes?
Answer: Give them roles/viewer role (Generally basic roles are not recommended but, it is the simplest way to provide
view only access to all resources)!

367) Cloud Functions - Scaling and Concurrency?
Answer: Autoscaling - As new invocation come in, new function instances are created. One function instances handles only
one request at time. Typical Problem, Cold Start. New function instance init from scratch can take time. Configure min
number of instances (increase cost). One function instance can handle multiple requests at the same time. Concurrency:
How many concurrent invocations can one function instance handle? Max 1000. Your function code should be safe to execute
concurrently.

368) Cloud Functions - Second Generation?
Answer: Longer Request timeout: Up to 60 minutes for HTTP-triggered functions.
Larger instances sizes: Up to 16 Giga Byes RAM with 4 vCPU.
Concurrency: Up to 1000 concurrent requests per function instance.
Multiple Function Revisions and Traffic splitting supported.
Support for 90 plus event-types enabled by Eventerc.

369) All members of your team have G-Suite accounts. You are setting up a new project. You want to provide a one time
quick access to a team member?
Answer: Assign the necessary role directly to G Suite email address of your team member. If it is not a one time quick
access, the recommended approach would be to create a Group.

370) All members in your team have G Suite accounts. You are creating a new production project and would want to provide
access to your operations team?
Answer: Create a Group with all your operations team. Provide access to production project to the Group.

371) IAM Members / Identities?
Answer: Google Account - Represents a person (an email address).
Service Account: Represents an application account (not person).
Google group: Collection Google & Service Accounts: Has an unique email address. Helps to apply access policy to a group.
Google Workspace Domain: Google Workspace (formerly G Suite) provides collaboration services for enterprises: Tools like
Gmail, Calendar, Meet, Chat, Drive, Docs atc. are included. If your enterprise is using Google Workspace, you can manage
permissions using your Google Workspace Domain.
Cloud Identity Domain - Cloud Identity is an Identity as a Service (IDaaS) solution that centrally manages users and
groups. You can use IAM to manage access to resources for each Cloud Identity account.

372) Corporate Directory Federation?
Answer: Federate Cloud Identity or Google Workspace with your external identity provider (IdP) such as Active Directory
or Azure Active Directory.
Enable Single Sign On: Users are redirected to an external IdP to authenticate. When users are authenticated, SAML
assertion is sent to Google Sign-In.
Examples: Federate Active Directory with Cloud Identity by using Google Cloud Directory Sync (GCDS) and Active Directory
Federation Services (ADFS). Federating Azure AD with Cloud Identity.

373) User Identity Management in Google Cloud?
Answer: Email used to create free trial account, "Super Admin". Access to everything in your GCP organization. Manage
access to other users using their Gmail accounts.
However, this is not recommended for enterprise.
Option1: Your Enterprise is using Google Workspace.
Option2: Your Enterprise uses an identity provider of its own. Federate Google Cloud with your Identity Provider.

374) Managing Billing - Budget, Alerts and Exports?
Answer: Set up a Cloud Billing Budget to avoid surprises: Configure Alerts. Billing data can be exported (on a schedule)
to BigQuery, Cloud Storage.

375) Billing Accounts?
Answer: Billing Account is mandatory for creating resources in a project. Billing Account can be associated with one or
more projects. You can have multiple billing accounts in an Organization. Create Billing Accounts representing your
organization structure. Two Types: Self Service: Billed directly to credit card or Bank Account. Invoiced: Generate
invoices (used by large enterprises).

376) Resource Hierarchy - Recommendations for Enterprises?
Answer: Create separate projects for different environments. Complete isolation between test and prod environments.
Create separate folders for each department: Isolate prod apps of one department from another. We can create a shared
folder for shared resources. One project per app per environment.

377) Resource Hierarchy in GCP?
Answer: Well defined hierarchy: Organization, Folder, Project, Resources.
Resources are created in projects. A Folder can contain multiple projects. Organizations can contain multiple Folders.

378) Command to create a VM with four CPUs that is names web-server-1?
Answer: 'gcloud compute instances create --machine-type=n1-standard-4 web-server-1'.

379) Data analytics?
Answer: The data analytics set of specialized services includes products that help with extraction, transformation, and
loading (ETL) and work with both batch and streaming data.

380) What can be the basis for scaling up an instance group?
Answer: Network latency, Load Balancing capacity and CPU utilization.

381) GCP Primitive Roles?
Answer: Owner, Editor, Viewer.

382) The command to list roles generated to a resource?
Answer: 'gcloud iam list-grantable-roles'.

383) BigQuery?
Answer: BigQuery is a fully managed service, designed for petabyte-scale analytics and provides a SQL interface.

384) Cloud Storage Triggers?
Answer: "google.storage.object.finalize": Occurs after a file is uploaded.
"google.storage.object.archive": Triggers when a file is archived.
"google.storage.object.metadataUpdate": Is triggered when some metadata attribute changes.

385) When creating an alerting policy, what can be specified?
Answer: Conditions, documentation, and time to live.

386) How can you override a policy inherited from another entity in the resource hierarchy?
Answer: Inherited policies can be overridden by defining a policy at folder or project level.

387) Cloud BigTable?
Answer: BigTable supports semi-structured data which is accessed using row key and work well at dataset which are larger
than 1 Tera Bytes.

388) Dataproc?
Answer: Dataproc supports Apache Spark, which has libraries for machine learning.

389) The GCP service for storing and managing Docker containers is?
Answer: Container Registry.

390) Cloud Firestore?
Answer: Cloud Firestore is a mobile database service that can synchronize data between mobile devices and centralized
storage.

391) OpenCensus?
Answer: OpenCensus is a library for developing custom metrics that can be used with Stackdriver logging.

392) Command Line Structure export data from Datastore?
Answer: 'gcloud datastore export --namespace=NAMESPACE gs://BUCKET-NAME'.

393) IoT data from sensors, data from several factories. Each sensor collects and sends data every 5 second. Daily reports.
No need to support transactions. Which data product would you recommend?
Answer: Cloud BigTable.

394) Cloud Filestore is based on what file system technology?
Answer: Network File System (NFS).

395) Running BigTable clusters in different regions?
Answer: You will ensure higher availability as you can handle region level failure. But it will also increase
replication latency as data needs to be replicated across cluster in different region.

396) Data available in memory to reduce response time, and to reduce the load on the database server. Which GCP service
would you use to keep data in memory?
Answer: Cloud Memorystore.

397) Data warehouse for company. Must support tens of petabytes of data. SQL for query language. Which service would you
choose?
Answer: BigQuery.

398) App Engine - Request Routing? // HERE
Answer: You can use a combination of three approaches:
a) Routing with URLs.
b) Routing with a dispatch file: Configure 'dispatch.yaml' with routes 'gcloud app deploy dispatch.yaml'.
c) Routing with Cloud Load Balancing.

399) App Engine - Deploying new versions without downtime?
Answer:
a) I'm very confident - Deploy & shift all traffic at once: 'gcloud app deploy'.
b) Manage migration from v1 to v2. Deploy v2 without traffic (--no-promote). Shift traffic to v2. Gradually migrate
(--migrate). Splitting.

400) How to split traffic between multiple versions?
Answer: IP Splitting - Based on request IP. Cookie Splitting - Based on a cookie (GOOG APP UID). Random.
Inclusive '--split-by' in 'gcloud app services set-traffic'. Options: cookie, ip, random.

401) Features of Kubernetes provides to reduce the workload on DevOps engineers.
Answer:
a) Load Balancing across Compute Engine VMs that are deployed in Kubernetes cluster.
b) Automatic scaling of nodes in the cluster.
c) Automatic upgrading of cluster software as needed.

402) App runs in Kubernetes cluster. You have configured autoscaling when the CPU utilization exceeds 75 percent. 
How is the CPU utilization calculated?
Answer: Based on all CPUs used by the deployment. Replicas are added to the deployment and hence CPU Utilization should
be checked at deployment level.

403) What can be the basis for scaling up an instance group?
Answer: Network latency, load balancing capacity and CPU utilization, monitoring metrics, or queue-based workload.

404) The Kubernetes API?
Answer: All interactions with the cluster are done through the master using the Kubernetes API.

405) Advantages for Docker?
Answer: Docker containers are lightweight. Docker provides isolation for containers. Docker is cloud-neutral.

406) App Engine?
Answer: Simplest way to deploy and scale your applications in GCP. Provides end-to-end application management.
Supports: Go, Java, Node.js, PHP, Python, Ruby using pre-configured runtimes. Use run-time and write code in any
language. Connect to variety of Google Cloud Storage products (Cloud SQL etc.)). No usage charges - Pay for resources
provisioned. Features: Automatic load balancing & Auto Scaling. Managed platform updates & App health monitoring.
App versioning. Traffic splitting.

407) Compute Engine vs App Engine?
Answer:
a) Compute Engine: IaaS. More flexibility. More Responsibility: choosing image, installing software, choosing hardware,
fine-grained access, permissions (certificates/firewalls).
b) App Engine: PaaS. Serverless. Lesser Responsibility. Lower Flexibility.

408) App Engine environments Standard?
Answer: Apps run in language specific sandboxes. Complete isolation from OS, Disk, other Apps (V1/V2).
V1: Java, Python, PHP, Go. Only for Python and PHP runtimes: restricted network access only white-listed extensions and
libraries. No restrictions for Java and Go.
V2: Java, Python, PHP, Node.js, Ruby, Go. Full Network Access and No restrictions on language extensions.

409) App Engine Flexible environment?
Answer: App instances run within Docker containers. Make use of Compute Engine virtual machines. Support any runtime.
Provides access to background process and local disks.

410) What are categories off Cloud Launcher solutions?
Answer: Data sets, operating systems, and developer tools.

411) Kubernetes Replicaset?
Answer: A Replicaset is a controller that manages the number of pods running for a deployment. A deployment is a
higher-level concept that manages Replicaset and provides declarative updates.

412) The GCP service for creating Docker image is?
Answer: Cloud Build.

413) Which resources can you configure when launching a WordPress site using Cloud Launcher?
Answer: Disk Type. Disk Size. Machine Type. Firewall rules to allow HTTP and HTTPS. Change Zone.

414) App Engine - Scaling Instances?
Answer:
a) Automatic: Automatically scale instances based on load. Recommended for continuously running workloads.
b) Basic: Instances are created as and where requests are required. Recommended for Adhoc Workloads.
c) Manual: Configure specific number of instances to run. Adjust number of instances manually over time.

415) App Engine Application Component Hierarchy?
Answer:
a) Application: One App per Project.
b) Services: Multiple Microservices or App components: You can have multiple services in a single app. Each Service can
have different settings. Earlier called modules.
c) Version(s): Each version associated with code and configuration: Each version can run in one or more instances.
Multiple versions can co-exist. Options to rollback and split traffic.

416) CPU utilization exceeding 70 percent for three minutes is example of?
Answer: Alerting Policy. In Stackdriver Monitoring, alerts are created using an alerting policy. CPU Utilization
is one such example.

417) When we are moving services from "n1-standard-1" to "n1-standard-4" it is called as Vertical Scaling?
Answer: True. Vertical scaling, requires moving services from one VM to another VM with more or fewer resources.

418) What command is used to run a Docker image on a cluster?
Answer: 'kubectl run'. The kubectl command is used to control workloads on Kubernetes cluster once it is created.

419) Cloud Functions?
Answer: Cloud Functions is best suited for event-driven processing such as file being uploaded to Cloud Storage or an
event being writing to a Pub/Sub queue.

420) What role would you need to grant, to create snapshots?
Answer: Compute Storage Admin.

421) Command to show the details of deployment?
Answer: 'gcloud deployment-manager deployments describe'.

422) Which database services do not require you to specify configuration information for VMs?
Answer: BigQuery, Datastore and Firebase.

423) Server with a high level of security. Prepare for inject a rootkit attack (a kind of malware that can alter the OS).
Which option should you select when creating a VM?
Answer: Shield VM. Shield VM is an advanced set of security controls that includes Integrity Monitoring, a check to ensure
boot images have not been tampered with.

424) Stackdriver Trace?
Answer: Stackdriver Trace is used to collect information about the time required to execute functions in a system call
stack.

425) Why there is no deploy container command in Kubernetes?
Answer: Kubernetes uses Pods as the smallest deployable unit, and Pod have usually one but possibly multiple containers
that are deployed as unit.

426) Command to create a subscription on the topic ace-exam-topic1?
Answer: 'gcloud pubsub subscriptions create --topic=ace-exam-topic1 ace-exam-sub1'.

427) Bucket storage class?
Answer: Once a bucket is set to coldline, it cannot be changed to another storage class. Regional and multiregional
can change to nearline and coldline. Nearline bucket can change to coldline.

428) Before you can start storing data in BigQuery, what must you create?
Answer: A data set.

429) What metric would you use when negotiating with the SaaS provider to ensure that your client's reliability are met?
Answer: Mean time between failure.

430) Store large volumes of data in BigTable. You want to explore the Bigtable env from command line. Command to ensure
you have command-line tool installed?
Answer: 'gcloud components install cbt'.

431) Exporting from Cloud SQL file format options?
Answer: CSV and SQL.

432) If you want to store 50 Terabytes of semi-structured data which will be accessed using single key, you would choose?
Answer: Cloud BigTable.

433) Cloud Spanner process to export data to Cloud Storage. What GCP Service might be incurring charges during the Cloud
Spanner export?
Answer: Dataflow. Dataflow is a pipeline service for processing streaming and batch data that implements workflows used
by Cloud Spanner.

434) Direct advantages of using a message queue in distributed systems?
Answer: It decouples services, so if one lags, it does not cause other services to lag.

435) Analyze large batch of data every night. Job will run 3.5 hour. You decide to write a script to create a Dataproc
cluster every night at midnight. Command example to create a cluster called spark-nightly-analysis in the
'us-west2-a-zone'?
Answer: 'gcloud dataproc cluster create spark-nightly-analysis --zone us-west2-a'.

436) What purpose do instance group play in Kubernetes cluster?
Answer: They create sets of VMs that can be managed as unit.

437) Billing and costs?
Answer: VMs are billed in 1 second increments. Cost varies with number of CPUs and amount of memory in a machine type.
You can create custom machine types. Preemptible VMs cost up to 80 percent less than standard VMs. Google offers
discounts for sustained usage.

438) What command would you use to have 10 replicas of a deployment named 'ch07-app-deploy'?
Answer: 'kubectl scale deployment ch07-app-deploy --replicas=10'.

439) Stackdriver Monitoring collects metrics such as query counts, execution times, scanned bytes,
and table count are captured for which GCP managed services ?
Answer: Cloud BigQuery. Stackdriver Monitoring collects metrics on the performance of infrastructure resources and applications.
The mentioned metrics are captured for BigQuery.

440) App Engine?
Answer: App Engine is a PaaS that allows developers to deploy full application without having to manage servers or clusters.

441) The amount of time GCP will wait before collecting performance statistics from the instance for Autoscaling is called as?
Answer: Cool down period.

442) Bucket containing old data. You don't want to delete it, but you want to minimize the cost of storing it. Command
to change storage class to coldline?
Answer: 'gsutil rewrite -s STORAGE-CLASS gs://PATH-TO-OBJECT'.

443) How will the cache affect data retrieval?
Answer: Using a cache will reduce latency, since retrieving from a cache is faster than retrieving from SSDs or HDDs.

444) You want to estimate the cost of running a BigQuery query. What two services within Google Cloud Platform will you
need to use?
Answer: BigQuery and Pricing Calculator. BigQuery provides an estimate of the amount of data scanned, and the pricing
calculator gives a cost estimate for scanning that volume of data.

445) On premise data warehouse running on MySQL is being migrated to GCP. The team wants to use a managed service which
can scale to Petabytes. Which service choose?
Answer: Cloud BigQuery.

446) Command to export a MySQL database called 'gcp-exam-mysql1' to a file called 'gcp-exam-mysql-export.sql', in a bucket
named 'gcp-exam-bucket1'?
Answer: 'gcloud sql export sql gcp-exam-mysql1 gs://gcp-exam-bucket1/gcp-exam-mysql-export.sql --database=mysql'.

447) What kind of data model is used by Datastore?
Answer: Document.

448) Correct command to create a Pub/Sub topic?
Answer: 'gcloud pubsub topics create'.

449) Command to delete an instance named 'ch06-instance-3'?
Answer: 'gcloud compute instance delete ch06-instance-3'.

450) You need to create multiple VMs which needs heterogeneous configurations as part of instance group. How can we
achieve that?
Answer: Create an unmanaged instance group. Managed instance group (MIGs) consists of identically configured VMs,
unmanaged instance groups allows for heterogeneous VMs.

451) Kubernetes Services?
Answer: Services provide a level of indirection to accessing Pods. Pods are ephemeral. Clients connect to Services, which
can discover Pods.

452) Starting a single VM. What is the minimal set of info you will need?
Answer: A name for the VM, a machine-type, a region, and a zone.

453) What is on-demand resource provisioning?
Answer: Provisioning (renting) resources when you want them and releasing them back to the cloud when you do not need them.

454) Advantages of the Cloud?
Answer: Trade capital expense for variable expense. Benefit from massive economics of scale. Stop guessing capacity.

455) How can you get high availability for global applications?
Answer: Distribute virtual servers across multiple regions and multiple zones.

456) Google Compute Engine (GCE) - Features?
Answer: Google Compute Engine (GCE) - Provision & Manage Virtual Machines. Create and manage lifecycle of Virtual
Machine (VM) instances. Load balancing and auto-scaling for multiple VM instances. Attach storage (& network storage) to
your VM instances. Manage network connectivity and configuration for your VM instances.

457) Compute Engine Machine Family?
Answer: What type of hardware do you want to run your workloads on? Different Machine Families for Different workloads:
a) General Purpose (E2, N2, N2D, N1): Best price-performance ratio. Web and application server small-medium databases,
dev environment.
b) Memory Optimized (M2, M1): Ultra high memory workload. Large in-memory databases and in-memory analytics.
c) Compute Optimized (C2): Compute intensive workloads. Gaming Applications.

458) Image?
Answer: What OS and software do you want on the instance? Reduce boot time and improve security by creating custom
hardened Images. You can share an Image with other projects. Types of images:
a) Public Images: Provided & maintained by Google or Open Source communities or third party vendors.
b) Custom Images: Created by you for your projects.

459) Site where users can upload videos, and need to assure redundancy for the files, therefore two buckets for Cloud
Storage were created. Command to synchronize the contents of the two buckets?
Answer: 'gsutil rsync'.

460) An Estimate of the volume of data scanned by BigQuery from the command line. Command example?
Answer: 'bq --location=LOCATION query --use_legacy_sql=false --dry_run SQL-QUERY'.

461) IoT space company. Company stream large volumes of data into GCP. Data needs to be filtered, transformed, and
analyzed before being stored in GCP Datastore. A good option for the stream processing component?
Answer: Cloud Dataflow. Allows for stream and batch processing of data and is well suited for this kind of ETL work.

462) Cloud Storage provides eleven 9's annual availability?
Answer: False. Cloud Storage provides (eleven 9's) annual durability. Availability is the ability to access an object
when you want it.

463) Developing analytics software for IoT. Ingest large volumes of data consistently and store it for several months.
Volumes are expected to grow to petabytes volumes. Which Database use?
Answer: BigTable. BigTable is a wide-column database that can ingest large volumes of data consistently. It also supports
low-millisecond latency.

464) When you enter a query into the BigQuery query form, BigQuery analyzes the query and displays estimate of what metric?
Answer: Amount of data scanned.

465) e2-standard-2 machine type?
Answer: e2: Machine Type Family. standard: Type of workload. 2: Number of CPUs.

466) Internal and External IP Address?
Answer: External (Public) IP addresses are Internet addressable.
Internal (Private) IP address are internal to a corporate network. You cannot have two resources with same public (external)
IP address, However, two different corporate networks can have resources with same Internal (private) IP address. All VM
are assigned at least one Internal IP address. Creation of External IP address can be enabled for VM instances: Remember
when you stop a VM instance, External IP address is lost.

467) Static IP Address?
Answer: Assign a Static IP Address to the VM! Load Balancers. Static IP can be switched to another VM instance in same project.
Static IP remains attached even if you stop the instance. You have to manually detach it. You are billed for an static IP
when you are not using it as well!

468) Reducing Launch Time with Custom Image?
Answer: Installing OS patches and software at launch of VM instances increases boot up time. How about creating a custom
image with OS patches and software pre-installed? Can be created from an instance, a persistent disk, a snapshot, another
image, or a file in Cloud Storage. Can be shared across projects. Deprecate old images (& specify replacement image).
Hardening an Image - Customize images to your corporate security standards. Prefer using Custom Image to Startup Script.

469) Instance Templates?
Answer: Why do you need to specify all the VM instance details (Image, instance type) every time you launch an instance?
How about creating an Instance Template? Define machine type, image, labels, startup scripts and other properties. Used to
create VM instances and managed instance groups. Provides a convenient way to create similar instances. Cannot be updated.
To make a change, copy an existing template and modify it. Optional Image family can be specified (example-debian-9).

470) Creating VM instance with HTTP server, ways?
Answer: Startup script, Instance Template, Custom Image. Bootstrapping: Install OS patches or software when an VM instance
is launched. In VM, you can configure startup script to bootstrap.

471) Which machine type family is best suited to run CPU intensive workload?
Answer: Compute Optimized.

472) How can you avoid specifying all the VM instances details (Image, Machine type, Firewall settings etc.) every time
you create a VM?
Answer: Create an Instance Template.

473) Which is a preferred option to reduce the launch time of an VM instance?
Answer: Use a Custom Hardened Image.

474) Sustained use discounts?
Answer: Automatic discounts for running VM instances for significant portion of the billing month. Discount increases with
usage. Applicable for instances created by Google Kubernetes Engine and Compute Engine. Restriction: Does not apply on
certain machine types (example: E2 and A2). Does not apply to VMs created by App Engine flexible and Dataflow.

475) Committed use discounts?
Answer: For workloads with predictable resources needs. Commit for 1 year or 3 years. Up to 70% discount based on machine
type and CPUs.

476) Preemptible VM?
Answer: Short-lived cheaper (up to 80%) compute instances. Can be stopped by GCP any time (preempted) within 24 hours.
Use Preempt VM if: Your applications are fault-tolerant. You are very cost sensitive. Your workload is not immediate.
Example: No immediate batch processing jobs. Restrictions: Not always available. No SLA and cannot be migrated to regular
VMs. No automatic restarts.

477) Spot VMs?
Answer: Spot VMs: latest version of preemptible VMs. Key Differences: does not have a maximum runtime. Compared to traditional
preemptible VMs which have a maximum runtime of 24 hours. Other features similar to traditional preemptible VMs. May be
reclaimed at any time with 30 seconds notice. Not always available. Dynamic Pricing: 60-91 % discount compared to
on-demand VMs. Free Tier credits not applicable.

478) Google Compute Engine - Billing?
Answer: You are billed by second (after a minimum of 1 minute). You are not billed for compute when a compute instance
is stopped. However, you will be billed for any storage attached with it! Always create Budget alerts and make use of
Budget exports to stay on top of billing! What are the ways you can save money? Choose the right machine type and image
for your workload. Be aware of the discount available.

479) Live Migration & Availability Policy?
Answer: How do you keep your VM instances running when a host system needs to be updated (a software or a hardware update
needs to be performed)? Live Migration: Your running instance is migrated to another host in the same zone. Does not
change any attributes or properties of the VM. Supported for instances with local SSDs. Not supported for GPUs and
preemptible instances. Important configuration - Availability Policy: On host maintenance, what should happen during
periodic infrastructure maintenance? Migrate (default): Migrate VM instances to other hardware. Terminate: Stop the VM
instance. Automatic restart: Restart VM instances if they are terminated due to non-user-initiated reasons (maintenance
event, hardware failure, etc.).

480) Custom Machine Types?
Answer: What do you do when predefined VM options are NOT appropriate for your workload? Create a machine type customized
to your needs (a Custom Machine Type). Custom Machine Type: Adjust vCPUs, memory and GPUs. Choose between E2, N2, or N1
machine types. Support a wide variety of OS: CentOS, CoreOS, Debian, Red Hat, Ubuntu, Windows etc. Billed per vCPU, memory
provisioned to each instance.

481) GPU?
Answer: How do you accelerate math intensive and graphics intensive workloads for AI/ML etc.?
Add a GPU to your virtual machine: High performance for math intensive and graphics intensive workloads. Higher Cost.
Remember, use images with GPU libraries (deep learning) installed. CPU restrictions: Not supported on all machine types.
On host maintenance can only have the value "Terminate VM instance". Recommended Availability Policy for GPU:
Automatic restart-on.

482) Virtual Machine Remember?
Answer: Associated with a project. Machine type availability can vary from region to regions. You can only change the
machine type (adjust the number of vCPU and memory) of a stopped instance. VM's can be filtered by various properties.
Name, Zone, Machine Type, Internal / External IP, Network, labels etc. Instances are Zonal (Run in a specific zone (in a
specific region)). Images are global (you can provide access to other projects - if needed). Instance templates are global
(unless you use zonal resources in your templates). Automatic Basic monitoring is enabled. Default Metrics: CPU utilization,
Network Bytes(in/out), Disk Throughput / IOPs. For Memory Utilization & Disk Space Utilization - Cloud Monitoring agents
is needed.

483) Virtual Machines - Best Practices?
Answer: Choose Zone and Region based on: Cost, Regulations, Availability Needs, Latency and Specific Hardware needs.
Distribute instances in multiple zones and regions for high availability. Choose right machine type for your
needs. Play with them to find out the right machine type. Use GPUs for Math and Graphic intensive apps. Reserve for
"committed use discounts" for  constant workloads. Use preemptible instances for fault-tolerance, non time critical
workloads. Use labels.

484) What are the prerequisites to be able to create a VM instance?
Answer: Project. Billing Account. Compute Engine APIs should be enabled.

485) You want discounted hardware for your compliance, licensing, and management needs?
Answer: Sole-tenant nodes.

486) I have 1000s of VM and I want to automate OS patch management, OS inventory management and OS configuration
management (manage software installed).
Answer: Use "VM Manager".

487) You want to log in to your VM instance to install software?
Answer: You can SSH into it.

488) You do not want to expose a VM to internet?
Answer: Do not assign an external IP Address.

489) You want to allow HTTP traffic to your VM?
Answer: Configure Firewall Rules.

490) Machine Types?
Answer: Optimized combination of compute (CPU, GPU), memory, disk (storage) and networking for specific workload.
You can create your own Custom Machine Types when existing ones don't fit your needs.

491) Static IP Address. Instance Template. Preemptible VM?
Answer: Static IP Address: Get a constant IP address for VM instances. Instance Template: Pre configured templates
simplifying the creation of VM instances. Preemptible VM: Short lived cheaper (up to 80%) compute instances for
non-time-critical fault-tolerant workloads.

492) Which metrics are available by default (without installing Cloud Monitoring agent) for a Compute Engine Virtual Machine?
Answer: CPU Utilization.

493) Back up data from Datastore database to an object storage system. Your data is stored in default namespace. Command
to export from Datastore to bucket ace-exam-bucket1?
Answer: 'gcloud datastore export --namespace="default" gs://ace-exam-bucket1'.

494) Gcloud?
Answer: Command line interface to interact with Google Cloud Resources. Most GCP Services can be managed from CLI using.
"gcloud": Compute Engine Virtual Machines. Managed Instance Groups. Databases. You can create, delete, update, read
existing resources and platform actions like deployments as well! Some GCP services have specific CLI tools:
Cloud Storage: "gsutil". Cloud BigQuery: "bq". Cloud BigTable: "cbt". Kubernetes: "kubectl".

495) Gcloud commands?
Answer: 'gcloud init': Initialize of reinitialize gcloud. Authorize gcloud to use your user account credentials. Setup
configuration: Include current project, default zone. 'gcloud config list': Lists all properties of the active configuration.

496) Gcloud config set?
Answer: Sets the specified property in your active configuration:
a) 'gcloud config set core/project VALUE'.
b) 'gcloud config set compute/region VALUE'.
c) 'gcloud config set compute/zone VALUE'.
d) 'gcloud config set core/verbosity VALUE'.
Syntax: 'gcloud config set SECTION/PROPERTY VALUE'. Core, compute: SECTIONS. Project, region, zone: PROPERTIES.
Specifying core is optional as it is the default SECTION. Opposite: 'gcloud config unset'.

497) Gcloud - Monitoring Multiple Configurations?
Answer: 'gcloud config configurations create/list/delete'.
Create new configuration: 'gcloud config configurations create NAME/dev'. Activate specific configuration:
'gcloud config configurations activate NAME/dev'.

498) Gcloud command structure - Playing with Services?
Answer: 'gcloud GROUP SUBGROUP ACTION...': GROUP: config, compute, container, dataflow, functions, iam.
SUBGROUP: instances or images or instance-template or machine-types or regions or zones.
ACTION: create or list of stop or describe.
Examples:
a) 'gcloud compute instances list'.
b) 'gcloud compute instances create my-first-instance'.
c) 'gcloud compute instances describe'.
d) 'gcloud compute instances delete my-first-instance'.

499) Gcloud compute instances create?
Answer: Creating Compute Instances: 'gcloud compute instances create NAME'.

500) Which of these has the highest priority (to decide the zone) when you execute a gcloud command?
Answer: '--zone' option specified along with the command.

501) Instance Groups?
Answer: How do you create a group of VM instances? Instance Group: Group of VM instances managed as a single entity.
Manage group of similar VMs having similar lifecycle as one unit. Two types of Instance Groups. Managed: Identical VMs
creating using a template. Features: Auto scaling, auto healing and managed releases. Unmanaged: Different configuration
of VMs in same group. Does not offer auto-scaling, auto-healing & other services. Not recommended unless you need different
VMs. Location can be Zonal or Regional. Regional gives you higher availability (Recommended).

502) Managed Instance Groups (MIG)?
Answer: Managed Instance Group: Identical VMs created using an instance template. Important Features:
a) Maintain certain number of instances: If an instance crashes, MIG launches another instance.
b) Detect application failures: Using health checks (self-healing).
c) Increase and decrease instances based on load (Auto Scaling).
d) Add Load Balancer to distribute load.
e) Create instances in multiple zones (regional MIGs). Regional MIGs provide higher availability compared to zonal MIGs.
f) Release new application Versions without downtime.
g) Rolling updates: Release new version step by step (gradually). Update a percentage of instances to the new version at
a time.
h) Canary deployment: Test new version with a group of instances before releasing it across all instances.

503) Cloud Dataproc is managed service for which two big data platform?
Answer: Spark and Hadoop.

504) What database service use? Transactions and support for tabular data. Database would support common query tools?
Answer: Cloud SQL and Spanner.

505) Job History menu item?
Answer: Job History shows active jobs, completed jobs, and jobs that generated errors.

506) Command to create a table called 'iot-ingest-data' in BigTable?
Answer: 'cbt createtable iot-ingest-data'.

507) What is the next step after creating a Cloud Spanner Instance that you would perform to enable you to load data?
Answer: Create a database within the instance.

508) You have just exported from Dataproc cluster. What have you exported?
Answer: Configuration data about the cluster.

509) Object Management policy, requires that objects stored in Cloud Storage be migrated from regional storage to nearline
storage 90 days after object is created. Way to do this?
Answer: Create a lifecycle management configuration policy specifying an age of 90 days and setStorageClass as nearline.

510) The command-line command to create a Cloud Storage bucket?
Answer: 'gsutil mb'.

511) Creating Managed Instance Group (MIG)?
Answer: Instance Template is mandatory. Configure auto-scaling to automatically adjust number of instances based on load:
Minimum number of instances. Maximum number of instances. Auto-scaling metrics: CPU Utilization target or Load Balancer
Utilization target or Any other metric from Stack Driver. Cool-down period: How long to wait before looking at auto-scaling
metrics again? Scale In Controls: Prevent a sudden drop in number of VMs. Auto-healing: Configure a Health Check with
initial delay (how long should you wait for your app to initialize before running a health check)?

512) Scale In, Scale Out?
Answer: Scale In: Reduce number of VM instances when load decreases. Scale Out: Increase number of VM instances when
load increases.

513) Updating a Managed Instance Group (MIG)?
Answer: Rolling Update: Gradual update of instances in an instance group to the new instance template. Specify new
template: Optional specify template for canary testing. Specify how you want the update to be done. When should the
update happen? Start update immediately proactive or when instance group is resized later Opportunistic.
How should the update happen? Maximum surge: How many instances are added at any point in time? Maximum unavailable: How
many instances can be offline during the update? Rolling Restart/Replace: Gradual restart of replace of all instances in
the group.

514) Instance Group. Avoid frequent scale up & downs.
Answer: Cool-down period / initial delay.

515) Instance Group. You want unhealthy instances to be automatically replaces?
Answer: Configure health check on the MIG (self-healing).

516) Instance Group. You want high availability in an MIG even when there are hardware/software updates?
Answer: Use an instance template with availability policy automatic restart: enabled & on-host maintenance. Migrate
Ensures live migration and automatic restarts.

517) Instance Group. You want to preserve VM state in an MIG?
Answer: Stateful MIG: Preserve VM state (Instance name, attached Persistent disk and Metadata). Recommended for stateful
workloads (database, data processing apps).

518) Instance Group. You want to create VMs of different configurations in the same group.
Answer: Create Un-managed Instance Group.

519) Instance Group. You want MIG managed application to survive Zonal Failures?
Answer: Create multiple zone MIG (or regional MIG).

520) Managed instance Groups Commands?
Answer: Gcloud compute instances-group managed:
a) Create instance group: 'gcloud compute instance-group managed create my-mig --zone us-central1-a --template
my-instance-template --size=1'.
b) Setup Autoscaling: Set-autoscaling/stop autoscaling. 'gcloud compute instance-groups managed set-autoscaling my-mig
--max-num-replicas=10'.
c) Update existing MIG policies (example auth-healing): 'gcloud compute instance-groups managed update my-mig'.

521) Update instance template?
Answer: 'gcloud compute instance-groups managed set-instance-template my-mig --template=v2-template'. After updating
instance template you can trigger rollout of the new template using update-instances, recreate-instances or
rolling-action start-update commands.

522) I want to make a new release without a reduction in capacity. Which of these options should I configure with a
value of 0?
Answer: --max-unavailable.

523) Which configuration options can prevent frequent scaling operations in MIGs?
Answer: --cool-down-period.

524) Cloud Load Balancing?
Answer: Distributes user traffic across instances of an application in single region or multiple regions.
Fully distributed, software defined managed service. Important Features: Health check, Auto Scaling, Global Load
Balancing with single anycastIP. Enables: High Availability, Auto scaling, Resilience.

525) Cloud Load Balancing - Terminology?
Answer: Backend: Group of endpoints that receive traffic from a Google Cloud load balancer (example: instance groups).
Frontend: Specify an IP address, port and protocol. This IP address is the frontend IP for your clients requests. For SSL
a certificate must also be assigned. Host and path rules (for HTTPS load balancing): Define rules redirecting the traffic
to different backends: Based on path. Based on host. Based on HTTP headers.

526) Load Balancing - SSL/TLS termination / offloading?
Answer: Client to Load Balancer: Over internet HTTPS recommended. Load Balancer to VM instance: Through Google internal
network. HTTP is OK. HTTPS is preferred. SSL/TLS Termination / Offloading: Client to Load Balancer: HTTPS/TLS.
Load Balancer to VM instance: HTTP/TCP.

527) Cloud Load Balancing - Features?
Answer: External HTTP(s): Global, External, HTTP or HTTPs. Proxy. HTTP on 80 or 8080, HTTPS on 443.
Internal HTTPS: Regional, Internal, HTTP or HTTPs. Proxy. HTTP on 80 or 8080, HTTPS on 443.
SSL Proxy: Global, External, TCP with SSL offload. Proxy. A big list of ports.
TCP Proxy: Global, External, TCP without SSL offload. Proxy. A big list of ports.
External Network TCP/UDP: Regional, External, TCP or UDP. Pass-Through. Any ports.
Internal TCP/UDP: Regional, Internal, TCP or UDP. Pass-Through. Any ports.

528) Load Balancer. You want only healthy instances to receive traffic?
Answer: Configure health-check.

529) Load Balancer. You want high availability for your VM instances?
Answer: Create Multiple MIGs for your VM instances in multiple regions. Load balance using a Load Balancer.

530) Load Balancer. You want to route requests to multiple microservices using the same load balancer?
Answer: Create individual MIGs and backends for each microservice. Create Host and Path rules to redirect to specific
microservice backend based on the path (/microservice-a, /microservice-b, etc.). You can route to a backend cloud storage
buckets as well.

531) Load Balancer. You want to load balance Global external HTTPS traffic across backend instances, across multiple
regions?
Answer: Choose External HTTPS Load Balancer.

532) You want SSL termination for Global non-HTTPS traffic with load balancing?
Answer: Choose SSL Proxy Load Balancer.

533) Microservices?
Answer: Enterprises are heading towards microservices architecture. Build small focused microservices. Flexibility to
innovate and build apps in different languages (Go, Java, Python, JavaScript). But development become complex.
How can we have one way of deploying, Go, Java, Python or JavaScript microservices? Enter containers.

534) Containers - Docker?
Answer: Create Docker images for each microservice. Docker image has all needs of a microservice. App Runtime,
App Code and Dependencies. Runs the same way on any infrastructure. Your local machine. Corporate data center. Cloud.
Advantages: Docker containers are light-weight (no guest OS). Docker provides isolation for containers. Docker is
cloud neutral.

535) Container Orchestration?
Answer: Typical Features: Auto Scaling: Scale containers based on demand. Service Discovery: Help microservices find one
another. Load Balancer: Distribute Load among multiple instances of a microservice. Self Healing: Do health checks and
replace failing instances. Zero Downtime Deployments: Release new versions without downtime.

536) Serverless?
Answer: What if you don't need to worry about servers and focus on your code? Enter Serverless. You don't worry about
infrastructure (zero visibility into infrastructure). Flexible scaling and automated high availability. Pay for use.
You focus on code and the cloud managed service takes care of all that is needed to scale your code to serve millions
of requests! Pay for requests.

537) Serverless - Important Features?
Answer:
Zero worry about infrastructure, scaling and availability. Google App Engine, AWS Fargate, Google Functions, AWS Lambda,
Azure Functions.
Zero invocation - Zero Cost. Google App Engine, AWS Fargate, Google Functions, AWS Lambda, Azure Functions.
Pay for invocations and NOT for instances (or nodes or servers). Google Functions, AWS Lambda, Azure Functions.

538) Instance groups?
Answer: Instance groups are sets of VMs that can be configured to scale and are used load balancers, which contribute to
improving availability.

539) Before creating an instance group, you need to create what?
Answer: Instance group template.

540) App Engine. I want to create two Google App Engine Apps in the same project?
Answer: Not possible. You can only have one App Engine App per project. However, you can have multiple services and
multiple version of each service.

541) App Engine. I want to move my Google App Engine to a different region?
Answer: App Engine is region specific. You cannot move it to different region. Create a new project and create new app
engine app in the new region.

542) App Engine Canary Deployment?
Answer: Deploy V2 without shifting traffic: 'gcloud app deploy --no-promote'. Shift some traffic to V2:
'gcloud app services set-traffic s1 --split v1=0.9,v2=0.1'.

543) What flag is used to migrate traffic gradually to the new version in App Engine? 'gcloud app services set-traffic'.
Answer: '--migrate'.

544) Cloud Storage Archive Storage?
Answer: Name: Archive. Min storage duration: 365 days. Typical Monthly availability: 99.95% in multi region and dual
region, 99.9% in regions. Use case: Less than once a year.

545) Storage Classes Coldline Storage?
Answer: Name: Coldline. Min storage duration: 90 days. Typical Monthly availability: 99.95% in multi region and dual
region, 99.9% in regions. Use case: Read or modify at most once a quarter.

546) Storage Class Nearline Storage?
Answer: Name: Nearline. Min storage duration: 30 days. Typical Monthly availability: 99.95% in multi region and dual
region, 99.9% in regions. Use case: Read or modify once a month on average.

547) Storage Class Standard?
Answer: Name: Standard. Min Storage Duration: None. Typical Monthly Availability: 99.99% in multi region, region and
dual region, 99.99% in regions. Use Case: Frequently used data / short period of time.

548) Cloud Storage - Storage Classes?
Answer: Different kind of data can be stored in Cloud Storage. Media Files and archives. App packages and logs.
Backups of your databases or storage devices. Long term archives. Huge variations in access patterns. Can I pay a cheaper
price for objects I access less frequently? Storage classes help to optimize your costs based on your access needs.
Designed for durability (eleven, 9's).

549) Features across Storage Classes?
Answer: High durability (11 9's annual durability). Low latency (first byte typically in tens of milliseconds).
Unlimited storage: Autoscaling (no config needed). No minimum object size. Same APIs across storage classes.
Committed SLA is 99.95% for multi region and 99.9% for single region for Standard, Nearline and Coldline Storage.
No committed SLA for Archive Storage.

550) Cloud IAM Example?
Answer: I want to provide access to manage a specific cloud storage bucket to a colleague of mine: Important Generic
Concepts. Member: My Colleague. Resources: Specific cloud storage bucket. Action: Upload/Delete objects.
In Google Cloud IAM. Roles: A set of permissions (to perform specific actions on specific resources). How do you assign
permissions to a member? Policy: You assign (or bind) a role to a member. Choose a Role with right permissions (Example
Storage Object Admin). Create Policy binding member (your friend) with role (permissions). IAM in AWS is very different
from GCP.

551) How do you change storage class of an existing bucket in Cloud Storage?
Answer: Step1: Change Default Storage Class of the bucket. Step 2: Update the storage class of the objects in the bucket.

552) Which features of Cloud Storage allows you to optimize costs by automatically moving files between different storage
classes?
Answer: Object Lifecycle Management.

553) Log files stored in Cloud Storage. You expect to access them once in quarter?
Answer: Coldline.

554) You want permanently store application logs for regulatory reasons. You don't expect to access them at all?
Answer: Cloud Storage - Archive.

555) How do you speed up large uploads (example: 100 Gigabytes) to Cloud Storage?
Answer: Use Parallel Composite Uploads (File is broken in small chunks and uploaded).

556) Cloud Storage - Uploading and Downloading Objects?
Answer: Simple Upload: Small files (that can be re-uploaded in case of failures) plus no object metadata.
Multipart Upload: Small files (that can be re-uploaded in case of failures) plus object metadata.
Resumable upload: Larger files. Recommended for most use-cases (even for small files - costs one additional HTTP request).
Streaming transfer: Upload an object of unknown size.
Parallel composite uploads: File divided up to 32 chunks and uploaded in parallel. Significantly faster if network and
disk speed are not limiting factors.
Simple download: Downloading objects to a destination.
Streaming download: Downloading data to a process.
Sliced object download: Slice and download large objects.

557) Object Lifecycle management?
Answer: Files are frequently accessed when they are created. Generally usage reduces with time. How do you save costs by
moving files automatically between storage classes? Solution: Object Lifecycle management.
Identify objects using conditions based on: Age, Created Before, IsLive, MatchesStorageClass, NumberOfNewVersions etc.
Set multiple conditions: all conditions must be satisfied for action to happen. Two kinds of actions:
SetStorageClass actions (change from one storage class to another). Deletion actions (delete objects).
Allowed Transactions: Standard or Multi-Region or Regional to Nearline or Coldline or Archive.
Nearline to Coldline or Archive. Coldline to Archive.


NEW SECTION - GCP Core Services.

1) What is Google Cloud Platform (GCP)?
Answer: Google Cloud Platform (GCP) is a suite of cloud computing services that runs on the same infrastructure that
Google uses internally for its end-user products, such as Google Search, Gmail, file storage, and YouTube.
GCP offers a range of services including computing, data storage, data analytics, and machine learning.

2) Can you explain the difference between IaaS, PaaS, and SaaS? Provide examples from GCP.
Answer: IaaS (Infrastructure as a Service): Provides virtualized computing resources over the internet. 
In GCP, Google Compute Engine (G C E) is an example of IaaS, offering virtual machines that users can customize and control.
PaaS (Platform as a Service): Offers hardware and software tools over the internet, typically for application development. 
Google App Engine (G A E) is a PaaS offering, providing a platform for developers to build, deploy, and scale applications.
SaaS (Software as a Service): Delivers software applications over the internet, on a subscription basis. 
Google Workspace (formerly G Suite) is an example of SaaS, offering productivity tools for businesses.

3) What are Google Compute Engine and Google App Engine? How do they differ?
Answer: Google Compute Engine (G C E) is an IaaS that provides virtual machines (VMs) in the cloud, giving users complete 
control over the OS, network settings, and the entire software stack. On the other hand, Google App Engine (G A E)
is a PaaS that abstracts much of the underlying infrastructure, allowing developers to focus on writing code without 
worrying about the environment it runs in. (G A E) automatically scales your application in response to the traffic it receives.

4) Explain the concept of load balancing in GCP.
Answer: Load balancing is a method used to distribute network or application traffic across multiple servers in a server
farm or server pool. In GCP, load balancing allows users to distribute incoming traffic across multiple instances 
of their applications, improving performance and availability. GCP offers various types of load balancers, 
including HTTP(S) Load Balancing, TCP/SSL Proxy Load Balancing, and Network Load Balancing, each designed for specific scenarios.

5) What is Google Cloud Storage? What are the different types of storage classes it offers?
Answer: Google Cloud Storage is a unified object storage solution that allows users to store and access data from anywhere. 
It offers different storage classes for various use cases: Standard (for frequently accessed data), 
Nearline (for data accessed less than once a month), Coldline (for data accessed less than once a quarter), 
and Archive (for long-term storage, accessed less than once a year), each varying in price and accessibility.

6) Describe the difference between BigQuery and Cloud Bigtable?
Answer: BigQuery is a fully managed, serverless, and highly scalable data warehouse designed for business agility. 
It's ideal for running big data analytics. Cloud Bigtable, on the other hand, is a NoSQL database service suited for 
real-time analytics and operational workloads with large amounts of data. While BigQuery is optimized for analytical queries, 
Cloud Bigtable is optimized for low latency data access.

7) What are Virtual Private Cloud (VPC) and its benefits in GCP?
Answer: A Virtual Private Cloud (VPC) in GCP is a managed networking functionality for your Cloud resources. 
It provides a private network with IP allocation, routing, and network firewall policies to secure and manage your cloud resources. 
Benefits include isolation of cloud resources, scalable and flexible networking options, and integration with GCP services 
for a seamless cloud environment.

8) How does GCP manage identity and access?
Answer: GCP manages identity and access through Cloud Identity and Access Management (IAM). IAM allows administrators 
to authorize who can take action on specific resources, giving them control over which users and services can access 
different resources. IAM policies define permissions and can be set at various levels in the GCP resource hierarchy.

9) What is the role of Kubernetes Engine in GCP?
Answer: Google Kubernetes Engine (GKE) is a managed environment in GCP for deploying, managing, and scaling containerized 
applications using Google infrastructure. GKE automates many aspects of setting up and maintaining Kubernetes clusters, 
making it easier to deploy applications, scale them as needed, and manage their lifecycle without deep Kubernetes expertise.

10) Explain the concept of serverless computing in GCP. Mention some serverless products offered by GCP.
Answer: Serverless computing allows you to build and run applications and services without managing infrastructure. 
Your application still runs on servers, but all the server management is done by GCP. This model enables developers 
to focus on their code while GCP handles scaling, provisioning, and managing servers. Examples of serverless products 
in GCP include Google Cloud Functions (event-driven functions), Google App Engine (application platform), 
and Google Cloud Run (containerized applications).


NEW SECTION - GCP Compute Services.-

1) What are the main compute services offered by GCP?
Answer: GCP offers several compute services, including:
a) Google Compute Engine (GCE): A flexible and scalable IaaS that provides virtual machines (VMs) in the cloud.
b) Google Kubernetes Engine (GKE): A managed service that allows users to run and manage Docker containers 
and container-based applications on a cluster of VMs.
c) Google App Engine (GAE): A PaaS for building scalable web applications and mobile backends.
d) Google Cloud Functions (GCF): A serverless execution environment for building and connecting cloud services with 
single-purpose functions that respond to cloud events.

2) Describe Google Compute Engine (GCE) and its key features.
Answer: Google Compute Engine provides scalable and flexible virtual machine instances for running your workloads 
on Google's infrastructure. Key features include:
a) Custom and predefined machine types to match your workload needs.
b) Persistent disks for high-performance block storage.
c) Global load balancing for distributing user traffic across instances.
d) Live migration of VMs to keep them running during maintenance.

3) How does Google Kubernetes Engine (GKE) simplify container management?
Answer: GKE simplifies container management by providing a managed environment that automates many aspects 
of configuring and operating Kubernetes clusters, including:
a) Automated provisioning and scaling of VMs for containerized applications.
b) Integrated logging and monitoring for insight into application performance.
c) Built-in network policies for securing applications.
d) Seamless integration with GCP services for enhanced functionality.

4) What is the difference between Google App Engine Standard and Flexible environments?
Answer:
App Engine Standard Environment: Provides a fully managed platform that automatically scales your app up and down while 
balancing the load. It supports specific runtime environments and limits access to the underlying OS and filesystem.
App Engine Flexible Environment: Offers more flexibility and allows you to customize the runtime environment, 
access the underlying OS, and use any programming language or third-party software. 
It's suitable for applications that require access to custom or native code.

5) Explain how Google Cloud Functions work and their use cases.
Answer: Google Cloud Functions is a serverless execution environment that runs your code in response to events. 
It automatically scales the execution of your code by running instances in parallel as needed. Use cases include:
a) Processing data stored in Google Cloud Storage.
b) Responding to changes in Firebase Realtime Database.
c) Implementing webhook backends for GitHub or Slack.
d) Building lightweight APIs and microservices.

6) What are preemptible VMs in GCE, and when would you use them?
Answer: Preemptible VMs are a cost-effective option for running workloads on Compute Engine that can be interrupted. 
These VMs are offered at a lower price than standard instances but can be terminated by GCP if it requires access to 
the resources for other tasks. Use them for batch jobs, fault-tolerant workloads, or any task that can be easily 
restarted and doesn't require persistent compute resources.

7) How do you ensure high availability and fault tolerance for GCP compute resources?
Answer: To ensure high availability and fault tolerance, you can:
a) Deploy applications across multiple zones or regions to protect against zone or region failures.
b) Use managed services like GKE, which automatically handles the replacement of failed instances.
c) Implement health checks and auto-restarting for VMs.
d) Utilize global HTTP(S) load balancing to distribute traffic across instances in different regions.

8) Can you explain the concept of machine types in GCE?
Answer: Machine types in GCE define the CPU, memory, and other resources available to a virtual machine. 
GCE offers various machine types:
a) Predefined machine types: Optimized for specific workloads, such as general-purpose, memory-optimized, 
and compute-optimized.
b) Custom machine types: Allow you to specify the exact amount of CPU and memory you need, providing flexibility and cost 
savings for workloads that don't fit predefined types.

9) What role does GCP's Cloud Load Balancing play in managing compute resources?
Answer: Cloud Load Balancing distributes incoming network traffic across multiple compute instances to ensure that no 
single instance bears too much load. This improves the availability and fault tolerance of your application by:
a) Providing global distribution of traffic across multiple regions.
b) Offering automatic scaling and failover.
c) Supporting HTTP(S), TCP/SSL, and UDP traffic.
d) Integrating with GCP’s auto-scaling to dynamically adjust resources based on demand.

10) Describe how to manage stateful applications in Kubernetes Engine?
Answer: Managing stateful applications in GKE involves:
a) Using StatefulSets, which provide unique, persistent identities and stable, persistent storage for each pod.
b) Implementing persistent volumes that are independent of the lifecycle of a pod to store data.
c) Configuring services to provide stable networking for communicating with the pods.
d) Employing readiness and liveness probes to ensure that pods are only considered ready when they're capable of serving 
requests and are restarted if they fail.


NEW SECTION - GCP Storage & Databases.

1) What are the primary storage options available in GCP?
Answer: GCP offers a variety of storage options to cater to different needs:
a) Google Cloud Storage: An object storage service for storing and accessing any amount of data.
b) Persistent Disk: Block storage for Google Compute Engine VMs.
c) Filestore: A managed file storage service for applications that require a file system interface and a shared file system.
d) Local SSD: High-performance, transient, local block storage designed to provide high throughput and low latency.

2) Can you explain the difference between Cloud SQL and Cloud Spanner?
Answer: Cloud SQL is a fully managed relational database service that supports MySQL, PostgreSQL, and SQL Server. 
It's ideal for traditional database workloads that require a relational database and is designed for compatibility with 
existing applications.
Cloud Spanner is a fully managed, scalable, relational database service for regional and global application data. 
It offers horizontal scaling without compromising on strong consistency and relational semantics, making it suitable 
for larger, distributed applications that require high availability and global distribution.

3) How does Google Cloud Storage ensure data durability and availability?
Answer: Google Cloud Storage provides high data durability and availability through:
a) Data replication: Storing multiple copies of data across different physical locations to protect against equipment 
failures and disasters.
b) Automatic checksums: Verifying data integrity both at rest and in transit to detect and correct any corruption.
c) Storage classes: Offering various storage classes (Standard, Nearline, Coldline, and Archive) that balance access 
frequency with cost, allowing users to choose the appropriate level of availability and price for their data.

4) What is BigQuery, and how is it used?
Answer: BigQuery is a fully managed, serverless, and highly scalable data warehouse on GCP designed for big data analytics. 
It enables users to analyze large amounts of data in real-time using SQL-like queries. BigQuery is used for running fast, 
SQL-like queries against multi-terabyte datasets, making it ideal for data analytics applications, business intelligence, 
and reporting.

5) Describe the use cases for Firestore and Realtime Database in Firebase.
Answer: Firestore is a flexible, scalable database for mobile, web, and server development from Firebase and GCP. 
It supports rich querying, real-time updates, and multi-region data replication, making it suitable for applications 
that require complex, hierarchical data structures and real-time synchronization.
Realtime Database is a cloud-hosted NoSQL database that lets you store and sync data between your users in real-time. 
It's a good fit for applications that require high performance, real-time updates to very large sets of data, 
such as collaborative editing apps, real-time analytics, and multiplayer games.

6) What are the advantages of using Cloud Bigtable?
Answer: Cloud Bigtable is a fully managed, scalable NoSQL database service for large analytical and operational workloads. 
Advantages include:
a) High performance: Optimized for both throughput and low latency for read and write operations.
b) Scalability: Automatically scales up and down according to workload, capable of handling petabytes of data across thousands of VMs.
c) Integration: Seamlessly integrates with BigQuery, Dataflow, and Hadoop, making it suitable for machine learning, 
data analytics, and real-time applications.

7) How do you choose between different GCP storage options for your application?
Answer: Choosing the right GCP storage option depends on several factors:
a) Data model: Whether your data is structured or unstructured.
b) Access patterns: Frequency of access and whether your workload is read-heavy, write-heavy, or requires real-time access.
c) Scalability: The volume of data and growth over time.
d) Geographical requirements: Whether your data needs to be available globally or in specific regions.
e) Cost considerations: Budget constraints and the need to balance cost with performance and access frequency.

8) Explain the concept of data consistency in Cloud Firestore?
Answer: Cloud Firestore ensures strong consistency for all reads and writes, meaning that every client sees the same 
view of the data at any given time. This is achieved through a distributed system design that synchronizes data across 
multiple locations, ensuring that any write operation is immediately visible to all subsequent read operations, 
regardless of the client's location.

9) What is the role of Google Cloud Memorystore?
Answer: Google Cloud Memorystore provides fully managed in-memory data store services, supporting Redis and Memcached. 
It's designed to be used as a cache or session store, offering sub-millisecond data access. 
This makes it ideal for applications that require fast access to data, such as caching frequently accessed information 
to improve application response times.

10) How can you secure data stored in GCP?
Answer: Securing data in GCP can be achieved through several practices:
a) Encryption: GCP automatically encrypts data at rest and in transit. Additionally, customers can manage their 
encryption keys using Cloud Key Management Service.
b) Access control: Using Identity and Access Management (IAM) to define who has access to resources and what actions 
they can perform.
c) Data residency: Choosing storage locations that comply with regulatory requirements.
d) Monitoring and logging: Using Cloud Audit Logs and Security Command Center to monitor access and detect security 
threats to your data.


NEW SECTION - GCP Networking.

1) What is a VPC in GCP and why is it important?
Answer: A Virtual Private Cloud (VPC) in GCP is a managed networking layer for your GCP resources. It provides a private, 
isolated section of the cloud where you can launch resources in a virtual network that you've defined. VPCs are crucial 
because they provide scalable and flexible networking for your cloud-based resources, allowing you to control 
IP address ranges, subnets, network gateways, and firewall rules.

2) Explain the difference between Global and Regional VPCs in GCP.
Answer: In GCP, a Global VPC (Virtual Private Cloud) spans across all regions, allowing resources in different regions 
to communicate with each other using internal IP addresses without the need for external IP addresses or VPNs. 
Conversely, a Regional VPC is limited to a single region. While Global VPCs offer seamless connectivity and simplified 
network architecture for multi-regional deployments, Regional VPCs can be beneficial for localized deployments with 
specific compliance or latency requirements.

3) What is Cloud Load Balancing in GCP, and what are its types?
Answer: Cloud Load Balancing is a fully distributed, software-defined managed service for balancing the load of your traffic. 
In GCP, it comes in various types to suit different needs: HTTP(S) Load Balancing for HTTP and HTTPS traffic, 
TCP/SSL Proxy for TCP and SSL traffic, Internal Load Balancing for distributing traffic within GCP, 
and Network Load Balancing for balancing TCP/UDP traffic on a global scale. 
Each type is designed to optimize the performance and availability of your applications.

4) How does Cloud CDN (Content Delivery Network) work with GCP?
Answer: Cloud CDN uses Google's globally distributed edge points of presence to cache HTTPS load balanced content 
close to your users. In GCP, Cloud CDN is integrated with HTTPS Load Balancing, allowing it to serve content directly 
from cache, reducing latency and improving page load times for your applications. It works by caching content at the 
edge locations; when a user requests content, Cloud CDN serves it from the nearest edge location, if available, 
to minimize distance and latency.

5) Describe the function of Cloud Interconnect and when you would use it.
Answer: Cloud Interconnect provides a high-speed, dedicated, and secure connection between your on-premises network 
and your Google Cloud VPC. It's ideal for scenarios requiring high throughput, low latency, or when transferring large 
amounts of data between the cloud and on-premises infrastructure. There are two types: Dedicated Interconnect for 
private connectivity through a dedicated physical link, and Partner Interconnect for connections through a supported 
service provider.

6) What are GCP Firewall Rules and how do they work?
Answer: GCP Firewall Rules control the traffic to and from instances within a VPC. They allow you to define what inbound 
and outbound traffic is allowed or denied based on IP addresses, protocols, and ports. Firewall rules are stateful, 
meaning the response traffic to allowed inbound traffic is automatically allowed, regardless of outbound rules. 
They are crucial for ensuring the security and proper functioning of your cloud resources.

7) Explain the concept of Subnetworks in GCP and their significance.
Answer: Subnetworks (or subnets) in GCP are subdivisions of a VPC network, allowing you to segment your network more 
efficiently. Each subnet is associated with a specific region and defines a range of IP addresses. They enable you to 
organize resources by department, application, or whatever makes sense for your operation, enhancing network management, 
security, and performance by controlling the flow of traffic based on policies.

8) How do you ensure high availability and disaster recovery for GCP networks?
Answer: Ensuring high availability and disaster recovery in GCP networks involves designing with redundancy, 
choosing multi-regional services when possible, using managed services that provide automatic failover (such as Cloud SQL), 
and implementing robust monitoring and alerting. Additionally, using Cloud Load Balancing for distributing traffic across 
instances in multiple regions and designing applications to be stateless where possible can enhance resilience.

9) Describe IP ranges in VPCs and how they relate to subnets?
Answer: IP ranges in VPCs are defined when you create a VPC or subnet, specifying the IP address range available for 
use by resources within that network. Each subnet within a VPC must have a unique IP range that does not overlap with 
other subnets within the same VPC. This structure allows for efficient IP address management, preventing conflicts and 
ensuring that network resources can communicate effectively.

10) What is Network Peering in GCP, and how does it differ from VPN?
Answer: Network Peering in GCP allows you to connect two VPCs so that they can share resources without using the public 
internet, providing a secure and direct network path. It differs from VPN (Virtual Private Network) in that VPN creates 
a secure, encrypted connection over the internet to connect your networks, while peering provides a direct connection 
without encryption. Peering is typically used for high-speed, low-latency connections between VPCs within the same 
organization or across different organizations, while VPNs are used for connecting remote users or different networks 
over the internet securely.


NEW SECTION - GCP Security & Identity.

1) What is Identity and Access Management (IAM) in GCP?
Answer: IAM in GCP manages access control by defining who (identity) has what access (roles) to resources within GCP. 
It allows administrators to grant granular permissions to users, groups, and service accounts.

2) Explain the role of Service Accounts in GCP?
Answer: Service Accounts are special accounts used by applications or virtual machines (VMs) to interact with other 
Google Cloud services. These accounts can be assigned IAM roles and used to authenticate applications for accessing GCP 
resources programmatically.

3) What is Cloud Identity in GCP and how does it integrate with IAM?
Answer: Cloud Identity is a unified identity, access, app, and device management solution built for the cloud. 
It integrates with IAM to provide centralized administration of users and groups, allowing for the management of their 
access to GCP services and resources.

4) How does GCP handle data encryption at rest and in transit?
a) At rest: GCP automatically encrypts data before it is written to disk, using encryption keys managed by Google. 
Customers can also manage their own encryption keys using Cloud Key Management Service (KMS).
b) In transit: GCP encrypts data as it moves between its data centers and to/from clients over the internet using 
protocols such as TLS.

5) What is the purpose of Cloud Security Command Center (CSCC) in GCP?
Answer: CSCC is a comprehensive security management and data risk platform for GCP that helps you prevent, detect, 
and respond to threats. It provides visibility into and control over cloud resources, allowing you to scan for 
sensitive data, detect common web vulnerabilities, and review access rights.

6) Explain the concept of VPC Service Controls in GCP?
Answer: VPC Service Controls enhance the security perimeter around API-based services in GCP, helping to mitigate data 
exfiltration risks. It allows administrators to define perimeters around resources, control data movement across 
the perimeter, and provide access to services from managed environments.

7) How does GCP implement network segmentation for security?
Answer: GCP uses VPCs for network segmentation, allowing administrators to segment their cloud network into subnets. 
Firewalls and VPC Service Controls can then be used to enforce policies and isolate workloads, minimizing the risk of 
lateral movement in case of a breach.

8) What are Google Cloud Audit Logs and their significance?
Answer: Google Cloud Audit Logs provide a record of activities and access within your GCP environment. 
They are crucial for security and compliance, offering visibility into who did what, where, and when. 
This helps in monitoring and analyzing security events and in forensic investigations.

9) How do GCP Security Health Analytics work?
Answer: Security Health Analytics is a feature within the Cloud Security Command Center that automatically scans your GCP 
resources to detect vulnerabilities and deviations from best practices, such as open storage buckets, public IPs on VMs, 
or misconfigured network firewalls.

10) What is Google’s BeyondCorp model, and how is it applied in GCP?
Answer: BeyondCorp is Google’s implementation of a zero trust security model that shifts access controls from the network 
perimeter to individual users and devices, enabling secure access to applications and resources based on the user’s 
identity and context of their request, without a traditional VPN. In GCP, this model is realized through technologies 
like Identity-Aware Proxy (IAP) which allows secure access to applications based on identity and context.


NEW SECTION - GCP Management Tools.

1) What is Google Cloud Console and what are its key features?
Answer: Google Cloud Console is a web-based, graphical user interface that allows users to manage their Google Cloud 
resources. Key features include the ability to deploy and manage applications, monitor services, manage billing, 
set up IAM roles and permissions, access Cloud Shell, and utilize APIs. It provides an intuitive way to navigate 
and control every aspect of Google Cloud.

2) Explain the role of Google Cloud SDK in GCP management?
Answer: The Google Cloud SDK is a set of tools that can be used to manage resources and applications hosted on Google Cloud. 
These tools include the gcloud command-line tool, gsutil, and bq, which respectively allow for managing Google Cloud 
resources, interacting with Google Cloud Storage, and querying BigQuery datasets. The SDK enables scripting and automation 
of cloud management tasks directly from the command line or through scripts.

3) What is Cloud Monitoring and how does it integrate with GCP services?
Answer: Cloud Monitoring is a service within GCP that provides visibility into the performance, uptime, and overall 
health of cloud-powered applications. It collects metrics, events, and metadata from GCP services, virtual machines, 
and application instrumentation. Using Cloud Monitoring, developers and operators can gain insights into their applications' 
performance in real-time, set up alerts, and create custom dashboards for visualizing metric data. It integrates seamlessly 
with most GCP services, offering out-of-the-box monitoring capabilities.

4) Describe Cloud Logging and its importance in GCP?
Answer: Cloud Logging is a fully managed service in GCP that allows you to store, search, analyze, monitor, and alert 
on logging data and events from Google Cloud and Amazon Web Services. It plays a crucial role in debugging and 
troubleshooting application issues, security monitoring, and audit trail maintenance. Cloud Logging provides a 
centralized platform for log management, supporting real-time log management and analysis, making it easier for 
developers and sysadmins to understand their cloud environments.

5) How does Google Cloud's operations suite enhance application and infrastructure monitoring?
Answer: Google Cloud's operations suite, formerly known as Stackdriver, enhances application and infrastructure 
monitoring by offering integrated and intelligent monitoring, logging, and diagnostics. It provides powerful monitoring 
capabilities that include performance metrics, dashboards, uptime checks, and alerting systems. The suite also offers 
extensive logging capabilities, error reporting, and trace analysis for understanding application performance and health 
in depth, facilitating proactive management and optimization of cloud resources.

6) What is Cloud Deployment Manager and what problem does it solve?
Answer: Cloud Deployment Manager is an infrastructure management service that automates the deployment and management 
of Google Cloud resources. It allows users to define all the resources needed for their application in a declarative 
format using yaml or Python. The primary problem it solves is the complexity of managing multiple resources manually. 
By automating the creation and management of resources, it ensures consistency, reduces the potential for human error, 
and supports efficient deployment processes.

7) Discuss the significance of Identity and Access Management (IAM) in managing GCP environments?
Answer: Identity and Access Management (IAM) in GCP is crucial for securing and managing access to GCP resources. 
It allows administrators to define who (identity) has access to which resources and what actions they can perform 
(roles and permissions). This granularity helps in implementing the principle of least privilege, reducing the risk of 
unauthorized access or actions. IAM supports managing access for users, service accounts, and group memberships, 
facilitating secure and efficient operations within GCP environments.

8) What are the benefits of using Cloud Billing reports and cost management tools in GCP?
Answer: Cloud Billing reports and cost management tools in GCP provide detailed insights into cloud spending, helping 
organizations to understand where and how their cloud resources are being used and at what cost. These tools offer 
features like budgeting, alerts, and detailed reports that break down costs by project, service, and resource, enabling 
cost optimization strategies such as identifying underutilized resources, forecasting future spending, and making 
informed decisions on resource allocation. They are essential for managing and optimizing cloud expenses effectively.

9) Explain the functionality of Cloud Build and its use cases?
Answer: Cloud Build is a service that executes your builds on Google Cloud Platform. It allows you to build, test, and 
deploy applications in a fast, scalable, and secure manner. Use cases include continuous integration and delivery (CI/CD) 
pipelines, automating the build-test-deploy cycle for applications, container image creation, and deploying applications 
to Google Kubernetes Engine (GKE) or other GCP services. Cloud Build supports a variety of programming languages and 
integrates with other GCP services for a seamless development experience.

10) How does Cloud Armor protect GCP-hosted applications?
Answer: Cloud Armor is a security service that provides Distributed Denial of Service (DDoS) protection and a 
Web Application Firewall (WAF) to protect applications running on GCP against a variety of threats, including DDoS attacks, 
SQL injection, and cross-site scripting (XSS). It works by applying security rules to incoming traffic, blocking or 
filtering out malicious requests before they can reach the application. Cloud Armor supports custom rules and integrates 
with global HTTP(S) load balancing for application-centric security policies.


NEW SECTION - GCP Developer Tools.

1) What is Cloud SDK and how is it used in GCP development?
Answer: The Google Cloud SDK is a set of tools that are used to manage resources and applications hosted on the 
Google Cloud Platform. These tools include the gcloud command-line tool for creating and managing GCP resources, 
gsutil for interacting with Google Cloud Storage, and bq for managing BigQuery data. It's essential for automating 
deployment and management tasks, and for scripting interactions with GCP services. 

2) Explain the purpose and benefits of using Cloud Source Repositories?
Answer: Cloud Source Repositories is a fully managed version control service that hosts private Git repositories on 
Google Cloud. It provides a secure, scalable platform for collaborative development. Benefits include easy integration 
with other GCP services, such as Cloud Build for CI/CD pipelines, and IAM for access control. It also supports mirroring 
of GitHub and Bitbucket repositories for unified code search and analysis within GCP.

3) How does Cloud Build facilitate CI/CD in GCP?
Answer: Cloud Build is Google Cloud's continuous integration and continuous deployment (CI/CD) platform that allows you 
to build, test, and deploy software across all languages. It automates the build, test, and deploy process, enabling 
developers to quickly deliver high-quality code. Cloud Build integrates with Cloud Source Repositories, GitHub, and 
Bitbucket, allowing for seamless integration into the development workflow. It supports custom workflows, 
containerization, and serverless deployments, making it a versatile tool for modern application delivery.

4) Describe the functionalities provided by Cloud Functions?
Answer: Cloud Functions is a serverless execution environment for building and connecting cloud services. 
With Cloud Functions, you can write simple, single-purpose functions that are attached to events emitted from your 
cloud infrastructure and services. It automatically scales based on the load, supports Node.js, Python, Go, 
and Java runtime environments, and integrates with GCP services for complex applications. Use cases include processing data, 
integrating third-party services, and implementing APIs and microservices architectures.

5) What is App Engine and how does it differ from Kubernetes Engine?
Answer: App Engine is a fully managed, serverless platform for developing and hosting web applications at scale. 
It abstracts away infrastructure management, allowing developers to focus on code. App Engine automatically scales 
applications up and down while balancing the load. Kubernetes Engine (GKE), on the other hand, is a managed environment 
for running containerized applications using Google Cloud's infrastructure. GKE offers more control over the environment 
and is ideal for microservices architectures. The key difference lies in the level of abstraction and control; 
App Engine provides a higher-level, more abstracted environment compared to GKE.

6) How does Cloud Endpoints enhance API management and development?
Answer: Cloud Endpoints is a distributed API management system that provides tools for creating, deploying, and managing 
APIs. It enhances API management by offering features like authentication, monitoring, logging, and API keys for access 
control. It supports OpenAPI and gRPC protocols for easy API deployment and integrates with App Engine, Compute Engine, 
and GKE. Cloud Endpoints helps developers secure, monitor, and scale APIs, providing a seamless way to manage API ecosystems.

7) Discuss the advantages of using Firebase in mobile and web app development?
Answer: Firebase is a comprehensive app development platform that provides a wide range of tools and services to help 
developers build, improve, and grow their mobile and web applications. Advantages include real-time databases, user 
authentication, analytics, crash reporting, and cloud messaging. Firebase simplifies backend development, provides 
scalable infrastructure, and integrates seamlessly with GCP services. It accelerates the development process, enabling 
rapid iteration and deployment of applications.

8) What role does Cloud Pub/Sub play in event-driven architectures?
Answer: Cloud Pub/Sub is a messaging service that enables asynchronous communication between components in a distributed 
system. It supports event-driven architectures by providing a highly scalable and reliable messaging infrastructure for 
publishing and subscribing to events. This facilitates loose coupling between components, allowing them to communicate 
efficiently without direct connections. Cloud Pub/Sub is essential for building scalable, resilient applications and is 
used for event ingestion, data integration, and real-time analytics.

9) How can developers leverage Cloud Firestore for application development?
Answer: Cloud Firestore is a fully managed, NoSQL document database for building web, mobile, and server applications 
at scale. Developers can leverage Cloud Firestore for real-time data synchronization, offline support, and ACID 
transactions across distributed data. It offers seamless integration with Firebase for developing feature-rich applications, 
provides robust scalability, and ensures global data distribution. Firestore simplifies data modeling and querying, 
enabling developers to focus on creating engaging user experiences.

10) Explain the significance of Container Registry in GCP?
Answer: Container Registry is a single place for your team to manage Docker images, perform vulnerability analysis, 
and decide who can access what with fine-grained access control. It's integrated with Google Cloud's security and 
privacy features, providing a secure and private repository for Docker images. Container Registry supports fast, 
scalable retrieval and deployment of containers to GCP services like GKE, Compute Engine, and App Engine. It's crucial 
for organizations adopting containerized deployment practices, enabling efficient container management and deployment workflows.


NEW SECTION - GCP Data Analytics.

1) What is BigQuery, and how does it support data analytics on GCP?
Answer: BigQuery is a fully managed, serverless, and highly scalable data warehouse on Google Cloud Platform designed for 
business agility. It enables super-fast SQL queries against petabytes of data using the processing power of Google's 
infrastructure. It supports real-time analytics with the ability to stream data into BigQuery at millions of rows per second. 
BigQuery's serverless model abstracts infrastructure management, allowing analysts and data scientists to focus on analyzing 
data to find meaningful insights without worrying about the underlying hardware or database management.

2) How do you handle streaming data in GCP for analytics purposes?
Answer: For handling streaming data in GCP, you can use Cloud Pub/Sub for ingesting event streams, which can then be 
processed in real-time using Cloud Dataflow or directly streamed into BigQuery for analysis. Cloud Pub/Sub serves as a 
highly scalable and reliable messaging service, while Cloud Dataflow provides a fully managed stream and batch data 
processing service. This combination allows for real-time analytics and processing of streaming data, enabling immediate 
insights and actions based on the incoming data streams.

3) Explain the role of Cloud Dataflow in GCP’s data analytics pipeline?
Answer: Cloud Dataflow is a fully managed service for stream and batch data processing. It's designed to process large 
amounts of data in real-time or in batches, providing a flexible and scalable way to transform, aggregate, and analyze data. 
Cloud Dataflow is built on Apache Beam, allowing developers to use a single programming model for both batch and stream 
processing. It plays a crucial role in GCP’s data analytics pipeline by enabling complex data transformations and analyses, 
data integration, and ETL (Extract, Transform, Load) processes, facilitating insights from both real-time and historical data.

4) Discuss how Google Cloud Dataprep supports data analysts?
Answer: Google Cloud Dataprep by Trifacta is an intelligent cloud service for visually exploring, cleaning, and preparing 
data for analysis and machine learning. It is designed to be user-friendly for data analysts without deep technical skills. 
Dataprep automatically detects data schemas, data types, and anomalies such as missing values or duplicates. It provides 
suggestions for data transformations and allows analysts to apply these transformations through a simple, interactive interface. 
This accelerates the data preparation process, making it easier for analysts to prepare data for analysis or machine 
learning models without needing to write code.

5) What are the main features of Cloud Dataproc, and when would you use it?
Answer: Cloud Dataproc is a managed Spark and Hadoop service that simplifies running big data processing frameworks on GCP. 
The main features include fast and easy provisioning of Spark and Hadoop clusters, scalable and flexible pricing options, 
and integration with other GCP services like BigQuery and Cloud Storage. You would use Cloud Dataproc when you need to 
process large datasets using Spark or Hadoop-based applications but want to avoid the complexity and operational overhead 
of managing those clusters yourself. It's ideal for ETL tasks, batch processing, and running big data analytics applications.

6) How does GCP integrate machine learning into its data analytics services?
Answer: GCP integrates machine learning into its data analytics services in several ways. BigQuery ML enables users to 
create and execute machine learning models directly within BigQuery using SQL queries, making machine learning accessible 
to data analysts. AutoML Tables and AutoML Vision provide an easy way to build and deploy machine learning models without 
deep machine learning expertise. Additionally, AI Platform offers a suite of tools and services for building, training, 
and deploying machine learning models at scale. These integrations allow users to seamlessly incorporate machine learning 
into their data analytics workflows, enhancing insights and predictive capabilities.

7) Describe the purpose and use cases of Google Data Studio.
Answer: Google Data Studio is a free, cloud-based data visualization tool that allows users to create interactive dashboards 
and reports from a variety of data sources. Its purpose is to make data accessible and understandable to all stakeholders 
through visual representation. Use cases include creating reports that aggregate data from multiple sources 
(such as BigQuery, Sheets, and Analytics), sharing insights with team members or clients, and monitoring business metrics 
in real-time. Data Studio’s collaborative features and easy-to-use interface make it ideal for businesses looking to 
democratize data analysis and decision-making.

8) What is the significance of Cloud Datalab for data scientists working on GCP?
Answer: Cloud Datalab is an interactive tool built on Jupyter (formerly IPython) that facilitates exploration, analysis, 
visualization, and machine learning on GCP. It is significant for data scientists as it provides a powerful and flexible 
environment to explore and analyze data, build machine learning models, and test and prototype applications. 
Cloud Datalab seamlessly integrates with GCP services like BigQuery, Cloud Storage, and Cloud Machine Learning Engine, 
enabling data scientists to leverage the full power of GCP for their data science projects. It supports Python and its 
data science libraries, making it a versatile tool for data analysis and machine learning tasks.

9) Explain how Looker fits into the GCP data analytics ecosystem.
Answer: Looker is a business intelligence (BI) and analytics platform that integrates with the Google Cloud data 
analytics ecosystem to provide data exploration, visualization, and reporting capabilities. It fits into the GCP 
ecosystem by offering a powerful analytics layer on top of BigQuery and other GCP data services, enabling users to build 
data models, create insightful visualizations, and share reports across organizations. Looker's integration with GCP 
enhances data-driven decision-making by providing real-time access to data, interactive dashboards, and a flexible modeling 
language called LookML for defining data relationships and transformations.

10)  How do you secure data analytics environments on GCP?
Answer: Securing data analytics environments on GCP involves multiple layers of security measures. These include:
a) Identity and Access Management (IAM): Control who has access to your GCP resources and what actions they can perform.
b) Data Encryption: Use GCP’s built-in data encryption at rest and in transit to protect your data.
c) Private Connectivity: Utilize private networks and Cloud VPN or Cloud Interconnect to securely connect to your GCP resources.
d) Audit Logging: Enable audit logs to track who did what and when, providing visibility into access and usage patterns.
e) Compliance and Best Practices: Follow GCP’s security best practices and comply with regulatory requirements relevant 
to your data and industry.


NEW SECTION - GCP Machine Learning & AI.

1) What is AI Platform on GCP, and how is it used for machine learning projects?
Answer: AI Platform is a comprehensive suite of services and tools on GCP designed to help data scientists and machine 
learning engineers build, deploy, and manage ML models at scale. It supports various ML frameworks, including TensorFlow, 
scikit-learn, and XGBoost, and offers both managed services for training models and making predictions as well as deep 
learning VMs for more customized model development. AI Platform facilitates the entire ML workflow from data preprocessing, 
model training and evaluation, to deployment and prediction, making it easier for teams to adopt machine learning in their projects.

2) How does AutoML on GCP democratize machine learning?
Answer: AutoML on GCP democratizes machine learning by providing a suite of products that enable users with limited machine 
learning expertise to train high-quality models specific to their business needs. AutoML covers various domains such as 
vision, language, and structured data, allowing users to create custom models through an easy-to-use graphical interface. 
It automates the model selection, training, and tuning process, making state-of-the-art ML accessible to non-experts and 
significantly reducing the time and resources required to deploy ML solutions.

3) Explain the role of BigQuery ML for data scientists and analysts.
Answer: BigQuery ML enables data scientists and analysts to build and deploy machine learning models directly within 
Google BigQuery using SQL commands. This integration allows users to create models on large datasets without moving 
data out of the database or mastering complex machine learning frameworks. It supports various ML models, including 
linear regression, logistic regression, and k-means clustering, and is particularly useful for making predictions 
directly from structured data stored in BigQuery. BigQuery ML lowers the barrier to entry for ML by leveraging SQL skills, 
which are widely known among data professionals.

4) What is TensorFlow, and why is it significant in GCP's AI and machine learning offerings?
Answer: TensorFlow is an open-source machine learning framework developed by Google and used extensively in GCP's AI and 
machine learning offerings. It's designed for complex computations and provides a flexible platform for conducting research 
and development as well as deploying production-grade machine learning models. TensorFlow supports deep learning and other 
machine learning algorithms with its extensive library and tools for tasks such as neural networks. Its significance in 
GCP's offerings lies in its integration with AI Platform, making it easier to train, deploy, and manage TensorFlow models 
at scale within GCP.

5) Describe how GCP's Vision AI services can be used in applications.
Answer: GCP's Vision AI services, including the Vision API and AutoML Vision, enable applications to understand images 
and videos more deeply. The Vision API provides pre-trained models that can detect objects, faces, text, and more within 
images, enabling features like content moderation, emotion detection, and optical character recognition. AutoML Vision 
allows creating custom models tailored to specific needs without deep machine learning expertise. These services can be 
used in various applications, from retail and social media to security and healthcare, for tasks like product identification, 
image-based search, and automated content tagging.

6) How does GCP support natural language processing (NLP) applications?
Answer: GCP supports NLP applications through its Cloud Natural Language API and AutoML Natural Language. The Cloud Natural 
Language API provides pre-trained models for analyzing text, including sentiment analysis, entity recognition, and syntax 
analysis. It enables applications to understand the structure and meaning of text in multiple languages. AutoML Natural 
Language allows users to train custom models for classification, entity extraction, and sentiment analysis tailored to 
their specific datasets. These tools support a wide range of NLP applications, from content classification and customer 
sentiment analysis to conversational AI and content recommendation systems.

7) What are the benefits of using Kubeflow on GCP for machine learning projects?
Answer: Kubeflow is an open-source project that facilitates the deployment of machine learning workflows on Kubernetes, 
and its integration with GCP offers several benefits. It enables ML engineers to build, train, and deploy machine 
learning models in a scalable and portable environment. Kubeflow on GCP simplifies the orchestration of machine learning 
pipelines, supports multiple ML frameworks, and provides a consistent environment for development and production. 
It leverages GCP's managed services like AI Platform for training and prediction, making it easier to scale ML models 
and manage resources efficiently. Kubeflow's compatibility with GCP ensures seamless integration with GCP services for 
data storage, processing, and analytics.

8) How do you ensure model fairness and avoid bias in machine learning models on GCP?
Answer: Ensuring model fairness and avoiding bias in machine learning models on GCP involves several strategies. 
GCP provides tools and documentation for best practices in ML fairness, such as:
a) Utilizing the What-If Tool to visualize and probe model predictions for fairness analysis.
b) Implementing fairness indicators to evaluate model performance across different groups.
c) Applying techniques for data preprocessing and augmentation to mitigate bias in the training data.
d) Regularly reviewing and updating models with diverse datasets to reflect a broad range of perspectives.
e) Following principles of responsible AI, including transparency, accountability, and ethical considerations in model 
development and deployment.

9) Explain the concept of federated learning and its potential applications in GCP.
Answer: Federated learning is a machine learning approach that trains algorithms across multiple decentralized devices 
or servers holding local data samples, without exchanging them. This technique improves privacy and reduces the need for 
data centralization. While GCP does not directly offer a federated learning service, it supports the development and 
deployment of federated learning models through TensorFlow and TFF (TensorFlow Federated). Potential applications include 
enhancing privacy in personalized recommendations, improving models based on user interactions without data leaving 
their devices, and collaborative learning across organizations without sharing sensitive data.

10) What are the best practices for deploying machine learning models at scale on GCP?
Answer: Deploying machine learning models at scale on GCP involves several best practices:
a) Utilize AI Platform Predictions for scalable, managed model deployment with support for versioning and A/B testing.
b) Implement continuous integration and delivery (CI/CD) pipelines for ML models using Cloud Build and AI Platform 
Pipelines to automate training, evaluation, and deployment processes.
c) Monitor model performance in production using AI Platform's monitoring tools and incorporate feedback loops for 
continuous improvement.
d) Optimize resource allocation and manage costs by selecting the appropriate machine types and using preemption for 
cost-effective training.
e) Ensure model security and compliance by leveraging GCP's security features, including IAM roles, VPC Service Controls, 
and encryption at rest and in transit.


NEW SECTION - GCP Containers & Kubernetes.

1) What is Google Kubernetes Engine (GKE), and how does it simplify Kubernetes operations?
Answer: Google Kubernetes Engine (GKE) is a managed, production-ready environment for deploying containerized applications 
using Kubernetes on Google Cloud. It simplifies Kubernetes operations by automating tasks such as provisioning, scaling, 
and updating the Kubernetes cluster. GKE offers integrated logging and monitoring, seamless integration with 
Google Cloud services, and advanced network and security features, making it easier to deploy and manage applications at scale.

2) How does GCP support containerized applications outside of Kubernetes?
Answer: Apart from Kubernetes, GCP supports containerized applications through services like Cloud Run and App Engine 
flexible environment. Cloud Run is a managed platform that automatically scales stateless containers in response to 
requests or events, focusing on ease of use and performance. App Engine flexible environment allows for the deployment 
of containerized web applications and APIs, offering automatic scaling, versioning, and security features. These services 
provide developers with choices for running containers, depending on their needs for flexibility, scalability, 
and manageability.

3) Explain the concept of a Pod in Kubernetes.
Answer: A Pod in Kubernetes is the smallest, most basic deployable object. It represents a single instance of a running 
process in your cluster. Pods contain one or more containers that share storage, network, and a specification on how to 
run the containers. Containers in the same Pod are always scheduled together on the same host machine and share the same 
IP address and port space, allowing them to communicate more efficiently. Pods are considered ephemeral and disposable 
entities that can be created, destroyed, and replaced dynamically.

4) How do you manage stateful applications in Kubernetes?
Answer: Managing stateful applications in Kubernetes involves using StatefulSets, Persistent Volumes (PVs), 
and Persistent Volume Claims (PVCs). StatefulSets provide a mechanism for deploying and scaling sets of Pods while 
maintaining a sticky identity for each Pod. They manage the deployment and scaling of a set of Pods and provide guarantees 
about the ordering and uniqueness of these Pods. PVs and PVCs offer a way to store data persistently, independent of the 
Pod lifecycle, ensuring that data is preserved across Pod restarts and rescheduling.

5) What role do Services play in Kubernetes?
Answer: Services in Kubernetes provide a stable, abstract way to expose a set of Pods as a network service. 
With the rapid creation and destruction of Pods, a Service ensures that network traffic can be directed to the current 
Pods for an application using a consistent IP address or DNS name. Services select Pods based on labels and selectors, 
and manage load balancing, offering a single access point for accessing Pods, which can be crucial for scaling and 
ensuring the high availability of applications.

6) Describe how GKE integrates with other GCP services for a complete cloud solution.
Answer: GKE integrates seamlessly with a range of GCP services to provide a comprehensive cloud solution. This includes 
Cloud Storage for persistent storage, Cloud SQL for managed database services, Cloud Pub/Sub for event-driven messaging,
and Cloud Functions for serverless computing. Integration with Identity and Access Management (IAM) ensures secure access 
control, while Stackdriver provides logging and monitoring. This ecosystem allows for the development of highly scalable, 
secure, and resilient applications that leverage the strengths of various GCP services.

7) How do you ensure high availability and disaster recovery for Kubernetes clusters in GKE?
Answer: Ensuring high availability and disaster recovery in GKE involves deploying clusters across multiple zones or 
regions (multi-zonal or regional clusters), using automated replicas and backups, and implementing proactive monitoring 
and alerting. Regional clusters provide redundancy and failover by running nodes in multiple zones within a region. 
Persistent data should be backed up using Cloud Storage, and application state can be replicated across regions. 
Stackdriver can monitor cluster health and trigger alerts or automated recovery processes in case of failure.

8) What are Kubernetes Ingress and Network Policies, and how are they used?
Answer: Kubernetes Ingress is an API object that manages external access to the services in a cluster, typically HTTP. 
Ingress can provide load balancing, SSL termination, and name-based virtual hosting. Network Policies are a Kubernetes 
resource that controls the flow of traffic between Pods and/or network endpoints. They specify how groups of Pods can 
communicate with each other and other network endpoints, allowing for the implementation of secure network policies and 
segmentation within a cluster.

9) Explain the importance of container security in GCP and how it's achieved.
Answer: Container security is crucial in GCP to protect applications from threats and vulnerabilities. It's achieved 
through various means, including ensuring secure container images by scanning for vulnerabilities with Container Analysis 
and Artifact Registry, implementing least privilege access controls using IAM, isolating workloads using GKE namespaces 
and network policies, and encrypting data at rest and in transit. GCP also offers tools like Security Command Center 
and Cloud Armor to monitor and protect against threats.

10) How do you automate CI/CD pipelines for Kubernetes applications on GCP?
Answer: Automating CI/CD pipelines for Kubernetes applications on GCP can be achieved using Cloud Build, a service that 
executes your builds on Google Cloud's infrastructure. Cloud Build can be integrated with source repositories 
(like Cloud Source Repositories, GitHub, or Bitbucket) to trigger builds automatically upon code commits. It can build 
container images, run tests, and deploy applications to GKE or other services automatically. Integration with Spinnaker 
or Tekton can further enhance automation for complex deployment strategies, ensuring seamless, automated workflows from 
code commit to deployment.


NEW SECTION - GCP API Management.

1) What is Apigee and why is it important for API management in GCP?
Answer: Apigee is a platform offered by Google Cloud for managing, developing, and securing APIs. It allows organizations 
to expose their backend services as APIs in a secure and scalable manner. Apigee is important for API management because 
it provides features like API analytics, rate limiting, OAuth and key management, and transformation capabilities, enabling 
businesses to securely share data and services across myriad applications and devices.

2) How does Apigee Edge differ from Apigee X?
Answer: Apigee Edge is the legacy API management platform that provides tools for API security, governance, analytics, 
and operations. It can be deployed on-premises or as a managed service. Apigee X, on the other hand, is the latest 
version of Google Cloud's API management platform, offering enhanced features like AI-powered security, global networking, 
and integrated Google Cloud services. Apigee X is designed to be cloud-native, offering a more scalable and secure 
architecture that leverages Google Cloud's infrastructure.

3) Can you explain the role of API proxies in Apigee and how they work?
Answer: API proxies in Apigee act as intermediaries between the client applications and backend services. They provide 
a layer of abstraction that allows developers to modify the API behavior without changing the backend service code. 
API proxies can handle tasks such as authentication, rate limiting, request and response transformation, and API version 
management. They work by intercepting API requests, applying configured policies, and then routing the requests to the 
appropriate backend service.

4) What is OAuth2.0 support in Apigee, and why is it important?
Answer: OAuth2.0 support in Apigee enables the platform to secure APIs using the OAuth 2.0 protocol, a standard for 
authorization. Apigee allows the configuration of OAuth2.0 as an authorization server to manage access tokens, client 
credentials, and authorization codes. This is important for securing APIs as it ensures that only authorized clients 
can access sensitive data and services, providing a robust mechanism for managing and validating access rights.

5) How do you handle API versioning in Apigee?
Answer: API versioning in Apigee can be handled through the creation of new API proxies for each version, allowing 
multiple versions of an API to coexist. Developers can route requests to the appropriate version based on the request path, 
query parameters, or headers. Apigee also allows the implementation of custom policies to manage the transition between 
different API versions, ensuring a smooth evolution of APIs without breaking existing client applications.

6) Describe how Apigee allows monitoring and analytics of API usage.
Answer: Apigee provides comprehensive monitoring and analytics features that allow organizations to track API performance, 
usage patterns, and error rates in real-time. It collects data on API calls, response times, client locations, and types 
of devices accessing the API. This information can be used to generate detailed reports and dashboards, offering insights 
into how APIs are being used and how they can be optimized for better performance and user experience.

7) What is API rate limiting in Apigee, and how can it be configured?
Answer: API rate limiting in Apigee is a feature that allows developers to control the number of requests a client can 
make to an API within a specific time period. It's used to protect backend services from overload and ensure fair usage 
among consumers. Rate limiting can be configured using policies in Apigee, where developers can specify the rate limit 
criteria, such as the number of allowed requests per minute, and apply these to individual APIs or API products.

8) How does Apigee facilitate API security?
Answer: Apigee facilitates API security through a variety of mechanisms, including OAuth2.0 and API key validation for 
authentication, SSL/TLS for data encryption in transit, and JSON and XML threat protection to guard against injection attacks. 
It also supports policies for access control, content-based security, and anomaly detection. These features help ensure 
that only authorized users can access APIs and that data is protected both in transit and at rest. 

9) Explain the significance of API products in Apigee.
Answer: API products in Apigee are a way to bundle one or more APIs together and define access limits and quota policies. 
They enable API providers to package APIs based on consumer needs, business models, or usage patterns. API products 
facilitate the monetization of APIs, streamline developer access through simplified key management, and allow for 
fine-grained control over who can access specific APIs and how often.

10) How can developers manage API lifecycle stages in Apigee?
Answer: Developers can manage API lifecycle stages in Apigee by utilizing the platform's capabilities to create, test, 
deploy, version, and retire APIs. Apigee provides environments for development, testing, and production, allowing for 
continuous integration and delivery workflows. Version management capabilities enable smooth transitions between different 
API versions, and the platform's analytics features help in making informed decisions about when to deprecate or retire 
API versions based on usage patterns and performance metrics.


NEW SECTION - GCP DevOps & SRE Practices.

1) What is the role of Google Cloud Build in GCP DevOps practices?
Answer: Google Cloud Build is a fully managed continuous integration and delivery (CI/CD) platform that automates the 
build, test, and deployment of software. In GCP DevOps practices, it plays a pivotal role by enabling developers to 
create automated workflows that compile source code, run tests, and deploy applications to Google Cloud or any other 
environment. It supports a wide range of programming languages and integrates with Google Cloud services and GitHub, 
making it a versatile tool for DevOps pipelines.

2) How does GCP implement Infrastructure as Code (IaC), and what tools does it offer for this?
Answer: GCP implements Infrastructure as Code (IaC) through Google Cloud Deployment Manager and Terraform. These tools 
allow developers and operations teams to automate the provisioning of Google Cloud resources using configuration files. 
Deployment Manager is GCP's native IaC service, enabling the management of cloud resources with templates and configuration 
files. Terraform, an open-source tool, offers a declarative configuration language to model and manage GCP and other 
cloud services' infrastructure. Both tools enable versioning, repeatability, and codification of infrastructure, 
key principles in DevOps.

3) Explain the importance of Google Kubernetes Engine (GKE) in DevOps practices.
Answer: Google Kubernetes Engine (GKE) is crucial for DevOps practices as it provides a managed environment for deploying, 
managing, and scaling containerized applications using Kubernetes. GKE enhances DevOps by automating many operational 
tasks such as deployment rollouts, scaling, and self-healing of applications. It integrates with Google Cloud CI/CD tools 
for seamless application development and deployment workflows, supporting microservices architectures, and promoting DevOps 
principles like automation, continuous delivery, and scalability.

4) What are SRE practices, and how do they integrate with DevOps in GCP?
Answer: Site Reliability Engineering (SRE) practices are a set of principles and practices that combine software 
engineering and systems engineering to build and run large-scale, highly reliable, and efficient software systems. 
In GCP, SRE practices integrate with DevOps by sharing common goals of automation, reliability, and rapid iteration. 
SRE focuses on creating scalable and reliable software systems, which complements DevOps' emphasis on rapid development 
and deployment. SRE introduces concepts like error budgets and service level objectives (SLOs) to balance the need for 
reliability with the pace of innovation.

5) How does Google Cloud Operations (formerly Stackdriver) support DevOps and SRE practices?
Answer: Google Cloud Operations suite offers monitoring, logging, and diagnostics capabilities that are essential for 
both DevOps and SRE practices. It provides real-time insights into the health, performance, and availability of 
applications and infrastructure, supporting proactive incident management and operational efficiency. For DevOps, it 
enables continuous monitoring throughout the software development lifecycle, facilitating rapid feedback and improvements. 
For SRE, it supports the measurement and management of service level indicators (SLIs) and objectives (SLOs), crucial 
for maintaining service reliability.

6) Describe the concept of error budgets in SRE. How are they used in GCP?
Answer: Error budgets define the acceptable threshold for system unreliability, balancing the need for stability with 
the pace of innovation. In SRE practices within GCP, error budgets are used to quantify the acceptable level of risk in 
terms of downtime or errors, allowing teams to make informed decisions about deploying new features versus focusing on 
reliability improvements. If the error budget is not exhausted, teams can push new releases. If it's depleted, efforts 
shift towards improving stability. GCP tools, like Cloud Operations, help in tracking and managing error budgets.

7) What role does automation play in GCP's DevOps and SRE practices?
Answer: Automation is a cornerstone of both DevOps and SRE practices in GCP, aiming to reduce manual toil, improve 
efficiency, and enhance reliability. It encompasses various aspects, including infrastructure provisioning, application 
deployment, testing, scaling, and incident response. Automation enables consistent and error-free operations, rapid scaling, 
and the ability to quickly recover from failures, thereby supporting continuous integration and delivery (CI/CD) and 
ensuring high availability and reliability of services.

8) How do Service Level Objectives (SLOs) guide DevOps and SRE practices in GCP?
Answer: Service Level Objectives (SLOs) are specific, measurable goals related to the reliability and performance of 
services. In GCP's DevOps and SRE practices, SLOs guide decision-making and prioritization by defining the desired level 
of service reliability. They help in aligning development and operational efforts with business objectives, focusing on 
customer satisfaction. SLOs also determine how resources are allocated, whether it's towards new features, maintenance, 
or reliability improvements, based on the current performance against these objectives.

9) How does GCP facilitate continuous improvement in DevOps and SRE practices?
Answer: GCP facilitates continuous improvement in DevOps and SRE practices through integrated monitoring, logging, and 
feedback tools, such as Cloud Operations suite, and the practice of blameless postmortems. These tools and practices help 
teams to learn from failures and operational incidents without assigning blame, encouraging a culture of transparency and 
continuous learning. GCP's CI/CD tools support ongoing development, testing, and deployment, allowing teams to iteratively 
improve applications and infrastructure based on real-world usage and performance data.

10) What is the significance of microservices architecture in GCP's DevOps practices?
Answer: Microservices architecture plays a significant role in GCP's DevOps practices by enabling organizations to develop, 
deploy, and scale their applications as a collection of loosely coupled services. This approach enhances agility, allowing 
teams to deploy updates to individual services independently, which is a core principle of DevOps. GCP supports microservices 
architecture through services like Google Kubernetes Engine (GKE), Cloud Run, and App Engine, providing scalable and reliable 
infrastructure for deploying and managing microservices, and facilitating DevOps practices such as continuous integration 
and delivery.


NEW SECTION - Migration to GCP.

1) What are the key considerations before starting a migration to GCP?
Answer: Before starting a migration to GCP, consider:
a) Assessment of Current Environment: Understand the current infrastructure, applications, and data to determine 
migration feasibility and strategy.
b) Cost Analysis: Estimate the costs involved in migration and running your infrastructure in GCP to ensure budget alignment.
c) Security and Compliance: Evaluate GCP's security features and compliance certifications to ensure they meet your 
organization's requirements.
d) Dependency Mapping: Identify dependencies between applications and services to plan the migration order and strategy.
e) Skillset and Training: Assess the team's familiarity with GCP and identify any training needs to ensure a smooth transition.

2) What tools does GCP offer for migrating VMs, and how do they work?
Answer: For migrating VMs, GCP offers tools like Migrate for Compute Engine (formerly Velostrata), which allows for 
streaming of data from the source environment to GCP, enabling quick migrations with minimal downtime. It supports 
lift-and-shift migrations, allowing VMs to be moved to GCP without modification.

3) Can you explain the process of data migration to GCP and the tools involved?
Answer: Data migration to GCP can involve different tools depending on the type and size of the data:
a) Cloud Storage Transfer Service: For online transfer of data from other cloud storage services or HTTP/HTTPS locations 
to Google Cloud Storage.
b) Transfer Appliance: A hardware appliance for offline data transfer when moving large volumes of data.
c) BigQuery Data Transfer Service: For migrating data into BigQuery from SaaS applications or other Google services. 
The process typically involves selecting the appropriate tool, configuring the source and destination, and executing the 
transfer while ensuring data integrity and security.

4) What are some best practices for ensuring a smooth migration to GCP?
Answer: Best practices include:
a) Start with a Pilot: Begin with migrating a non-critical system to test the process.
b) Use the Strangler Pattern: Gradually replace parts of your application with new functionalities in GCP.
c) Implement DevOps Practices: Use CI/CD pipelines for automation and efficiency.
d) Optimize as You Go: Take the opportunity to optimize applications for cloud-native features.
e) Focus on Security: Implement GCP’s security best practices from the start.
f) Train Your Team: Ensure your team is trained and familiar with GCP services and best practices.

5) What is the role of Google's Professional Services Organization (PSO) and partner ecosystem in migrations?
Answer: Google's PSO and its partner ecosystem play a crucial role in migrations by providing expertise, best practices, 
and support to ensure successful migration. They can offer specialized services for assessment, planning, executing, 
and optimizing migrations to GCP, leveraging their deep knowledge of Google Cloud technologies and experience in complex 
migration scenarios. 

6) How do you manage application dependencies during migration?
Answer: Manage application dependencies by:
a) Mapping Dependencies: Thoroughly map out all application dependencies before migration.
b) Migrating Dependent Services First: Ensure that services which others depend on are migrated first or are accessible 
during the migration.
c) Using Interconnect and Peering: Utilize GCP’s Interconnect and Peering services to maintain connectivity with 
on-premises environments as needed during the migration.

7) What strategies can be used for migrating databases to GCP?
Answer: Strategies include:
a) Lift-and-Shift: For moving existing databases to GCP without modification, using services like Cloud SQL.
b) Database Dump and Restore: Exporting data from the existing database and importing it into GCP.
c) Managed Database Migration Services: Using Cloud Database Migration Service for a more automated and managed 
migration process.

8) How do you ensure business continuity during the migration process?
Answer: Ensure business continuity by:
a) Thorough Planning: Have a detailed migration plan that includes rollback procedures.
b) Testing: Conduct extensive testing at every stage of the migration.
c) Minimizing Downtime: Use migration tools that support minimal downtime strategies, such as incremental migration.
d) Monitoring: Continuously monitor the migration process and the performance of applications during and after the migration.

9) How can companies optimize their cloud costs after migrating to GCP?
Answer: Companies can optimize cloud costs by:
a) Right-Sizing Resources: Adjusting the size of VM instances and databases based on actual usage.
b) Using Committed Use Discounts: Committing to certain resource usage in exchange for discounts.
c) Monitoring and Managing Resources: Using tools like Cloud Billing Reports and Cost Management tools to monitor and 
optimize resource usage.
d) Automating Cost Controls: Implementing policies to automatically shut down or scale down resources during low usage 
periods.


NEW SECTION - GCP Serverless Solutions.

1) What is serverless computing, and how does GCP support it?
Answer: Serverless computing is a cloud computing execution model where the cloud provider manages the allocation and 
provisioning of servers. GCP supports serverless computing through products like Cloud Functions, App Engine, Cloud Run, 
and Firestore, allowing developers to build and deploy applications that scale automatically without managing underlying 
infrastructure.

2) Can you explain what Google Cloud Functions is and give a use case?
Answer: Google Cloud Functions is a scalable, event-driven service that allows developers to run backend code in response 
to events triggered by GCP services or HTTP requests. A use case includes processing data stored in Cloud Storage, 
such as image resizing or metadata extraction, without provisioning or managing servers.

3) How does Google Cloud Run differ from Google Cloud Functions?
Answer: Google Cloud Run is a managed platform that enables you to run stateless containers that are invocable via HTTP 
requests. Unlike Cloud Functions, which is designed for executing small, single-purpose functions, Cloud Run is suitable 
for running more complex applications or microservices that require custom runtimes or dependencies not supported by 
Cloud Functions.

4) Describe the key features and benefits of Google App Engine.
Answer: Google App Engine is a fully managed, serverless platform for building and hosting web applications at scale. 
Key features include automatic scaling, versioning, and traffic splitting, support for multiple development languages, 
and integration with GCP services. Benefits include reduced operational overhead, quick deployments, and a focus on 
writing code rather than managing infrastructure.

5) How do you manage authentication and authorization in serverless applications on GCP?
Answer: Authentication and authorization in serverless applications on GCP can be managed using Google Cloud Identity 
and Access Management (IAM) for controlling access to resources and Google Firebase Authentication for managing user 
identities. IAM allows you to define who (which users or service accounts) has access to which resources, while Firebase 
Authentication provides a full identity solution, supporting email/password, phone, and social media authentication methods.

6) What are the considerations for choosing between Cloud Functions, Cloud Run, and App Engine?
Answer: Considerations include:
a) Runtime Support: Cloud Run and App Engine support custom runtimes, while Cloud Functions is limited to supported languages.
b) Statelessness: Cloud Run is ideal for stateless containers, whereas App Engine can support applications that maintain 
state across requests.
c) Event-driven vs. HTTP-driven: Cloud Functions excels at event-driven scenarios, whereas Cloud Run and App Engine are 
better suited for HTTP/web workloads.
d) Scalability and Performance Needs: App Engine and Cloud Run can handle more complex scaling scenarios and provide 
more configuration options compared to Cloud Functions.

7) How does GCP ensure the scalability of serverless applications?
Answer: GCP ensures scalability by automatically managing the infrastructure resources based on the incoming request 
volume. For Cloud Functions, Cloud Run, and App Engine, GCP dynamically allocates more resources as demand increases and 
scales down when demand decreases, ensuring applications remain highly available without manual intervention.

8) What is Firestore, and how does it support serverless architectures?
Answer: Firestore is a fully managed, NoSQL document database built for automatic scaling, high performance, and ease of 
application development. It supports serverless architectures by providing a real-time database with seamless integration 
to serverless services like Cloud Functions, allowing developers to build dynamic applications without managing database 
servers.

9) Discuss the pricing model for GCP's serverless products.
Answer: GCP's serverless products typically follow a pay-as-you-go pricing model, where you are charged based on the 
resources consumed by your application, such as compute time, memory usage, and the number of requests. This model 
allows for cost-effective scaling, as you only pay for what you use, with no upfront costs or fees for idle resources.

10) How do you monitor and debug serverless applications on GCP?
Answer: GCP provides tools like Cloud Monitoring, Cloud Logging, and Error Reporting to monitor and debug serverless 
applications. Cloud Monitoring allows you to track application performance metrics, Cloud Logging collects and analyzes 
logs from your application, and Error Reporting identifies and reports errors. Additionally, Cloud Trace and Cloud Profiler 
can be used for performance analysis and identifying bottlenecks in application code.


NEW SECTION - GCP Hybrid and Multi-Cloud.

1) What is Google Cloud's Anthos, and how does it support hybrid and multi-cloud environments?
Answer: Anthos is Google Cloud's open platform that enables you to build and manage modern applications across environments, 
including GCP, other cloud providers, and on-premises infrastructure. It supports hybrid and multi-cloud environments by 
offering a consistent development and operations experience, leveraging Kubernetes for container orchestration. 
Anthos enables policies, security, and services to be applied uniformly across different environments, facilitating easier 
workload mobility and management. 

2) How does Anthos facilitate application modernization in hybrid environments?
Answer: Anthos facilitates application modernization by allowing organizations to use containerized applications in a 
consistent manner across various environments. It leverages Kubernetes and other cloud-native technologies to modernize 
existing applications and build new ones, supporting a move to a microservices architecture. Anthos’ managed services 
simplify operations, such as service mesh for microservices communication and monitoring, and config management for 
policy enforcement, helping businesses modernize applications without being locked into a single cloud provider.

3) Can you explain the role of Google Kubernetes Engine (GKE) in hybrid and multi-cloud strategies?
Answer: In hybrid and multi-cloud strategies, Google Kubernetes Engine (GKE) plays a pivotal role by providing a managed 
environment for deploying, managing, and scaling containerized applications using Google's infrastructure. GKE extends 
to GKE On-Prem as part of the Anthos platform, allowing organizations to run Kubernetes in their own data centers and 
seamlessly connect to Google Cloud services. This integration enables a consistent and unified management experience 
across cloud and on-premises environments, facilitating workload portability and management across a hybrid and 
multi-cloud landscape.

4) What is Cloud Interconnect, and how does it support hybrid cloud configurations?
Answer: Cloud Interconnect is a Google Cloud service that provides high-speed, secure, and reliable connections between 
your on-premises network and Google Cloud, or between Google Cloud and other cloud providers. It supports hybrid cloud 
configurations by offering different options like Dedicated Interconnect for private connectivity and Partner Interconnect 
for connecting through a service provider. This facilitates a seamless, enterprise-grade connection to Google Cloud, 
enabling low-latency access to cloud services and resources, which is crucial for hybrid cloud operations.

5) How does Google Cloud's Traffic Director support multi-cloud deployments?
Answer: Google Cloud's Traffic Director is a fully managed service mesh control plane that provides global load balancing 
for microservices across GKE and VM-based services on Google Cloud and on-premises environments. In multi-cloud deployments, 
Traffic Director enables consistent traffic management, security policies, and telemetry across environments, regardless 
of where the services are deployed. It helps ensure high availability and optimal performance of applications by dynamically 
routing traffic to the best endpoint based on health, geography, and other parameters.

6) Describe the role of Cloud VPN in connecting on-premises networks to GCP.
Answer: Cloud VPN securely connects your on-premises network to your Google Cloud VPC (Virtual Private Cloud) network 
through an IPsec VPN connection. It plays a crucial role in hybrid cloud setups by enabling secure communication between 
on-premises servers and cloud services, ensuring data in transit is encrypted and protected. Cloud VPN supports both 
static and dynamic routing, making it suitable for various networking scenarios and providing a cost-effective solution 
for integrating on-premises resources with GCP.

7) How can organizations manage APIs in hybrid and multi-cloud environments using Google Cloud?
Answer: Organizations can manage APIs in hybrid and multi-cloud environments using Apigee, Google Cloud’s API management 
platform. Apigee enables enterprises to design, secure, analyze, and scale APIs, regardless of where the APIs and the 
consuming applications are hosted. It supports multi-cloud and hybrid deployments by offering features like API gateways 
deployed on-premises or in any cloud, centralized management of APIs across environments, and analytics for API usage 
and performance. This facilitates a consistent API strategy across the organization's entire digital ecosystem.

8) What security features does Google Cloud offer for hybrid and multi-cloud setups?
Answer: Google Cloud provides several security features for hybrid and multi-cloud setups, including:
a) Context-Aware Access: Controls access to applications and VMs based on identity and context.
b) Cloud Armor: Protects applications from DDoS attacks.
c) Binary Authorization: Ensures only trusted container images are deployed on GKE.
d) Cloud Identity and Access Management (IAM): Manages access control and permissions.
e) Data Loss Prevention (DLP): Helps discover and protect sensitive data across hybrid and multi-cloud environments. 
These features ensure that security is consistent and centrally managed, regardless of where the resources or users 
are located.

9) Explain how Google Cloud’s operations suite supports monitoring and logging in hybrid and multi-cloud environments.
Answer: Google Cloud’s operations suite (formerly Stackdriver) offers integrated monitoring, logging, and diagnostics 
tools that work across Google Cloud, on-premises environments, and other cloud providers. It supports hybrid and 
multi-cloud environments by providing a single pane of glass for visibility into the performance, availability, 
and health of applications and infrastructure. This includes real-time monitoring, log management, application tracing, 
and error reporting, enabling organizations to detect and resolve issues quickly across their entire cloud landscape.

10) How do organizations benefit from deploying applications in multi-cloud environments using Google Cloud?
Answer: Deploying applications in multi-cloud environments using Google Cloud offers organizations several benefits, including:
a) Flexibility and Freedom: Avoid vendor lock-in and choose the best cloud provider based on specific application needs 
or geographic requirements.
b) Improved Reliability: Enhance application availability and disaster recovery by leveraging multiple cloud providers.
c) Optimized Costs: Take advantage of different pricing models and services from multiple providers to optimize cloud spending.
d) Innovation and Agility: Access unique services and capabilities from different cloud providers to drive innovation. 
Google Cloud facilitates these benefits through its comprehensive suite of services and tools designed for interoperability 
and ease of management across hybrid and multi-cloud environments.


NEW SECTION - GCP Internet of Things (IoT).

1) What is Google Cloud IoT Core and what are its primary features?
Answer: Google Cloud IoT Core is a fully managed service that allows users to easily and securely connect, manage, and 
ingest data from millions of globally dispersed devices. Its primary features include secure device connection and 
management at scale, a device manager to manage device identities and metadata, and seamless integration with the 
Google Cloud data analytics platform for real-time insights.

2) How does GCP ensure secure device connection and data transmission in IoT projects?
Answer: GCP ensures secure device connection and data transmission in IoT projects through multiple mechanisms. 
It uses industry-standard protocols like MQTT and HTTP for secure communication, supports end-to-end encryption, 
and allows for automatic device provisioning with secure connection credentials. Google Cloud IoT Core also integrates 
with Cloud IAM for fine-grained access control and uses Cloud Audit Logs for monitoring and logging of device activity.

3) Can you explain how Cloud IoT Core integrates with other GCP services for IoT data analysis?
Answer: Cloud IoT Core integrates seamlessly with other GCP services to enable comprehensive IoT data analysis. 
Data ingested through IoT Core can be directly streamed to BigQuery for warehousing, Cloud Pub/Sub for messaging and 
event-driven processing, Cloud Dataflow for stream and batch data processing, and Cloud Machine Learning Engine for 
advanced analytics and AI applications. This integration facilitates a powerful end-to-end solution for collecting, 
processing, analyzing, and visualizing IoT data in real-time.

4) What are the benefits of using Cloud Pub/Sub in an IoT solution on GCP?
Answer: Cloud Pub/Sub benefits an IoT solution on GCP by providing a scalable and flexible messaging and event ingestion 
service. It allows for real-time messaging between devices and the cloud, enabling decoupled services that can scale 
independently. Cloud Pub/Sub supports high-volume event streams, such as those generated by IoT devices, and integrates 
with other GCP services for downstream processing and analysis, enhancing the overall efficiency and responsiveness of 
IoT solutions.

5) Describe how Google Cloud Functions can be used in IoT applications.
Answer: Google Cloud Functions can be used in IoT applications to build lightweight, event-driven backends that respond 
to events from IoT devices without the need for managing servers. For example, Cloud Functions can process data as it 
arrives from IoT devices, perform real-time analytics, update databases, send alerts, or invoke other cloud services. 
This serverless approach allows developers to focus on code and business logic, while GCP handles the scaling and 
infrastructure management.

6) How does GCP support real-time data processing for IoT applications?
Answer: GCP supports real-time data processing for IoT applications through services like Cloud Dataflow and Cloud Pub/Sub. 
Cloud Dataflow allows users to develop and execute a wide range of data processing patterns, including ETL, batch computations, 
and continuous computation on streaming data. When combined with Cloud Pub/Sub for messaging and event ingestion, these 
services provide a powerful infrastructure for analyzing and processing IoT data streams in real-time, enabling immediate 
insights and actions.

7) What role does AI and machine learning play in GCP IoT solutions?
Answer: AI and machine learning play a crucial role in GCP IoT solutions by providing advanced analytics capabilities to 
extract insights from vast amounts of IoT data. With services like Cloud Machine Learning Engine and TensorFlow, users 
can develop and deploy custom machine learning models that can predict trends, optimize operations, and detect anomalies. 
This integration enables smarter IoT solutions that can learn from data, improve over time, and make autonomous decisions.

8) How can BigQuery be utilized for IoT data analytics?
Answer: BigQuery can be utilized for IoT data analytics by serving as a highly scalable and serverless data warehouse to 
store and analyze large volumes of IoT data. It enables fast SQL queries across petabytes of data, making it suitable 
for analyzing historical data from IoT devices to identify patterns, trends, and insights. BigQuery's integration with 
other GCP services allows for easy ingestion, analysis, and visualization of IoT data, supporting data-driven decision-making.

9) What is the significance of Edge TPU in GCP's IoT solutions?
Answer: The Edge TPU is Google’s purpose-built ASIC (Application-Specific Integrated Circuit) designed to run AI at the edge. 
It significantly accelerates machine learning inference on edge devices, enabling IoT solutions to process data locally 
with low latency and reduced cloud dependency. This is particularly important for applications requiring immediate 
decision-making, privacy-sensitive computations, or operating in environments with limited connectivity. 
Integrating Edge TPU with GCP's IoT solutions allows for efficient deployment of AI-driven insights directly on IoT devices.

10) How does GCP facilitate the management of large-scale IoT deployments?
Answer: GCP facilitates the management of large-scale IoT deployments through Cloud IoT Core's device management capabilities, 
which enable users to securely register, organize, monitor, and remotely manage IoT devices at scale. Features such as 
device configurations, state reporting, and automatic device provisioning help streamline the deployment and maintenance 
of IoT devices. Additionally, GCP's global infrastructure ensures the scalability and reliability required to manage vast 
numbers of devices distributed around the world.


NEW SECTION - GCP Cost Management.

1) What is Google Cloud's approach to cost management and optimization?
Answer: Google Cloud's approach to cost management and optimization involves providing visibility into resource usage 
and costs, offering pricing models that align with user needs (such as sustained use discounts, committed use discounts, 
and preemptible VMs), and tools like the Google Cloud Pricing Calculator, the Billing Report, and the Cost Management 
tools in the Google Cloud Console. Google Cloud encourages practices like rightsizing resources, selecting appropriate 
pricing models, and monitoring and managing resources efficiently to optimize costs.

2) How can you monitor your GCP costs?
Answer: You can monitor GCP costs using the Google Cloud Console's "Billing" section, where you can view detailed reports 
and analysis of your costs and usage. The Billing Reports feature allows you to visualize your spend over time, break 
down costs by project, service, and location, and identify trends. Google Cloud Billing also integrates with BigQuery, 
enabling advanced data analysis and custom reporting.

3) What are Committed Use Discounts in GCP and how do they work?
Answer: Committed Use Discounts (CUDs) in GCP are a pricing model that provides significant discounts in exchange for 
committing to a consistent usage level (in terms of CPU, memory, and other resources) for a one or three-year term. 
These discounts apply automatically to compute resource usage that matches the commitments, helping customers save money 
compared to on-demand pricing for sustained, predictable workloads.

4) Can you explain the role of labels in cost management on GCP?
Answer: Labels in GCP are key-value pairs that you can attach to resources to organize and track cost data based on your 
business structure, such as by department, project, or environment. By applying labels, you can filter and break down 
your billing data in the Google Cloud Console or BigQuery, making it easier to allocate costs, enforce budgets, and analyze 
spending patterns across different segments of your organization.

5) What is the Google Cloud Pricing Calculator and how is it used?
Answer: The Google Cloud Pricing Calculator is an online tool that allows users to estimate the cost of running their 
cloud workloads on Google Cloud. Users can select various services, configure their resources (like compute instances, 
storage, and networking options), and see an estimate of the costs based on current pricing. It's useful for planning 
and budgeting purposes, as well as comparing costs for different configurations or services. 

6) How can the GCP's Budgets and Alerts help with cost management?
Answer: GCP's Budgets and Alerts feature allows users to set budgets for their projects or billing accounts and receive 
alerts when their costs exceed predefined thresholds. This helps in proactively managing cloud spend by notifying 
stakeholders of potential overruns, enabling them to take corrective actions, such as scaling down resources, 
optimizing usage, or investigating unexpected charges.

7) What are preemptible VMs and how can they reduce costs?
Answer: Preemptible VMs are short-lived compute instances offered by Google Cloud at a significantly lower price than 
standard instances. They can be terminated by GCP at any time if the resources are needed elsewhere. Preemptible VMs 
are ideal for workloads that can tolerate interruptions, such as batch processing, data analysis, and background tasks, 
allowing users to significantly reduce their compute costs.

8) Describe how Rightsizing Recommendations can help in cost optimization on GCP.
Answer: Rightsizing Recommendations in GCP analyze your virtual machine instances' usage patterns and suggest adjustments 
to their configurations (such as changing the machine type or size) to optimize their efficiency and cost. By applying 
these recommendations, users can ensure they are not overprovisioning resources and paying for more than they need, 
leading to direct cost savings.

9) How does the sustained use discount work in GCP?
Answer: Sustained Use Discounts in GCP automatically apply to compute engine resources, offering a discount for instances 
that are used for a significant portion of the billing month. These discounts are tiered, so the more you use, the greater 
the discount, encouraging continuous usage of resources. This discount model helps reduce costs for workloads with 
consistent compute needs without requiring any upfront commitment.

10) What is the importance of Cost Allocation Reports in GCP?
Answer: Cost Allocation Reports in GCP provide detailed insights into how resources are being used and how costs are 
distributed across different projects, services, and resources within your Google Cloud account. By analyzing these reports, 
organizations can identify high-cost drivers, uncover inefficiencies, and better allocate cloud spending according 
to business needs or budgetary constraints. This is crucial for maintaining financial governance and optimizing cloud 
spend across the organization.


NEW SECTION - GCP Compliance & Governance.

1) What is the role of Google Cloud's Identity and Access Management (IAM) in governance?
Answer: Google Cloud's Identity and Access Management (IAM) plays a crucial role in governance by providing fine-grained 
access control over resources in Google Cloud. It allows administrators to define who (identity) has what access (roles) 
to which resources, ensuring that only authorized users can perform specific actions. This helps in enforcing the principle 
of least privilege, reducing the risk of unauthorized access and data breaches.

2) How does GCP support compliance with regulations like GDPR and HIPAA?
Answer: GCP supports compliance with regulations like GDPR and HIPAA by offering a secure and compliant infrastructure, 
including data processing agreements, BAA (Business Associate Agreements) for HIPAA-covered entities, and built-in 
security features to protect sensitive data. Google Cloud also provides documentation and resources to help customers 
understand their responsibilities and implement the necessary controls to achieve and maintain compliance.

3) Can you explain the purpose of Resource Hierarchy in GCP governance?
Answer: The purpose of the Resource Hierarchy in GCP governance is to organize and manage resources in a structured way. 
It consists of Organizations, Folders, Projects, and Resources, allowing for hierarchical management of access control 
and policies. This structure enables governance at scale by applying policies at different levels of the hierarchy, 
facilitating effective management and control over resources and access across the entire organization.

4) What are GCP's Cloud Audit Logs and their significance in compliance and governance?
Answer: GCP's Cloud Audit Logs provide a record of activities that have affected your GCP resources. They are crucial 
for compliance and governance as they offer visibility into who did what, where, and when within your Google Cloud 
environment. These logs are essential for security and compliance auditing, helping organizations track access and 
changes to their resources and identify any potential security issues or compliance violations.

5) How does GCP ensure data sovereignty and compliance with regional regulations?
Answer: GCP ensures data sovereignty and compliance with regional regulations by offering a global network of data centers, 
allowing customers to choose where their data is stored and processed. GCP complies with regional regulations and standards 
by implementing strict data protection and privacy measures, providing the necessary controls for data residency, 
and undergoing regular audits to ensure compliance with various global and regional standards.

6) Describe how Security Command Center contributes to GCP governance.
Answer: Security Command Center contributes to GCP governance by providing a comprehensive view of the security and risk 
posture of GCP assets. It helps identify vulnerabilities and threats, reviews security settings, and provides actionable 
recommendations to improve security. By centralizing visibility and control over cloud resources, Security Command Center 
enables organizations to enforce governance policies and ensure compliance with internal and external regulations.

7) What are GCP's Data Loss Prevention (DLP) API and its role in governance?
Answer: GCP's Data Loss Prevention (DLP) API helps organizations discover, classify, and protect sensitive information 
across GCP services. Its role in governance is to prevent accidental or unauthorized exposure of sensitive data, 
such as personally identifiable information (PII), by providing tools to redact, mask, or encrypt data in accordance 
with compliance requirements and governance policies. This helps maintain data privacy and regulatory compliance.

8) How do Custom Roles in IAM aid in fine-tuning access control for governance?
Answer: Custom Roles in IAM aid in fine-tuning access control for governance by allowing administrators to create specific 
sets of permissions that match the unique needs of their organization. Unlike predefined roles, custom roles can be 
tailored to provide the precise level of access needed for a user or service account, enforcing the principle of least 
privilege and reducing the risk of excessive permissions that could lead to security vulnerabilities.

9) Explain the significance of VPC Service Controls in enhancing GCP governance.
Answer: VPC Service Controls enhance GCP governance by creating a security perimeter around data stored in GCP services, 
preventing data exfiltration and mitigating data breaches. It allows organizations to define fine-grained access control 
policies for services and their data, ensuring that sensitive information is accessed only by authorized services and users, 
even within the same organization. This strengthens governance by providing an additional layer of protection for critical data.

10) What is the role of GCP's Compliance Reports Manager?
Answer: GCP's Compliance Reports Manager is a tool that helps organizations access and manage compliance reports, 
such as ISO, SOC, and PCI DSS, directly within Google Cloud. The role of Compliance Reports Manager in governance is to 
streamline the process of demonstrating compliance with various standards and regulations by providing easy access to 
relevant audit reports and certificates. This assists organizations in understanding GCP's compliance posture and 
simplifies the audit process for their own compliance requirements.


NEW SECTION - GCP for Enterprise.

1) How does GCP ensure data security and privacy for enterprise customers?
Answer: GCP ensures data security and privacy for enterprise customers through a multi-layered security approach that 
includes physical security, data encryption at rest and in transit, identity and access management, network security, 
and regular security audits. GCP complies with global data protection regulations and offers tools and features like 
VPC Service Controls and Data Loss Prevention (DLP) API to help enterprises manage and protect their data.

2) What are the key benefits of using GCP for enterprise-grade applications?
Answer: Key benefits of using GCP for enterprise-grade applications include scalability, reliability, and global network 
infrastructure. GCP provides highly scalable computing resources, managed services for application development, 
and big data analytics. Its global fiber network ensures low-latency access worldwide. GCP's commitment to innovation, 
continuous integration, and deployment tools also support agile development and operational efficiency.

3) Can you describe how enterprises can leverage GCP for digital transformation?
Answer: Enterprises can leverage GCP for digital transformation by utilizing its extensive range of services, including 
cloud computing, machine learning, and data analytics, to innovate and improve business processes. GCP enables enterprises 
to build and deploy scalable and secure applications, gain insights from data using big data solutions, and improve 
customer experiences through AI-driven technologies. Additionally, GCP's collaboration and productivity tools 
(Google Workspace) support seamless communication and collaboration among teams.

4) Can you describe how enterprises can leverage GCP for digital transformation?
Answer: Enterprises can leverage GCP for digital transformation by utilizing its extensive range of services, including 
cloud computing, machine learning, and data analytics, to innovate and improve business processes. GCP enables enterprises 
to build and deploy scalable and secure applications, gain insights from data using big data solutions, and improve customer 
experiences through AI-driven technologies. Additionally, GCP's collaboration and productivity tools (Google Workspace) 
support seamless communication and collaboration among teams.

5) What role does artificial intelligence and machine learning play in GCP for enterprises?
Answer: Artificial intelligence (AI) and machine learning (ML) play a pivotal role in GCP for enterprises by offering 
advanced tools and services to drive innovation, automate processes, and derive insights from data. GCP's AI Platform, 
AutoML, and pre-trained AI services enable enterprises to develop custom ML models and integrate AI into applications 
without deep expertise in data science. This can enhance customer experiences, optimize operations, and create new 
business opportunities.

6) How can enterprises manage costs effectively in GCP?
Answer: Enterprises can manage costs effectively in GCP by utilizing cost management tools and features such as the 
Google Cloud Pricing Calculator, Budgets and Alerts, and Cost Management reports. Implementing best practices like 
rightsizing resources, using committed use discounts for sustained workloads, and monitoring and optimizing resource 
usage with recommendations from the Cloud Operations suite can further help control costs. GCP also provides detailed 
documentation and best practices to guide cost optimization.

7) Describe the migration services available in GCP for enterprise workloads.
Answer: GCP offers various migration services for enterprise workloads, including the Migrate for Compute Engine, 
Migrate for Anthos, Database Migration Service, and Transfer Appliance. These services facilitate the migration of 
virtual machines, applications, databases, and large datasets to GCP, minimizing downtime and ensuring data integrity. 
GCP also provides resources and professional services to assist enterprises in planning and executing migration projects.

8) How does GCP facilitate compliance and governance for enterprises?
Answer: GCP facilitates compliance and governance for enterprises by offering a comprehensive suite of tools and features 
to manage access, monitor resources, and ensure data protection. Features like Cloud Identity & Access Management, 
Cloud Audit Logs, and Security Command Center help enterprises enforce policies, audit operations, and detect vulnerabilities. 
GCP undergoes regular third-party audits and certifications to ensure compliance with global and industry-specific regulations.

9) What disaster recovery solutions does GCP offer to enterprises?
Answer: GCP offers robust disaster recovery solutions to enterprises, including multi-regional storage options, snapshot 
and backup services, and the ability to replicate workloads across different regions. GCP's global infrastructure ensures 
that enterprises can quickly recover from disasters by distributing applications and data across multiple locations, 
minimizing downtime and data loss. Google Cloud's operations suite also provides monitoring and logging services to 
detect and respond to incidents promptly.

10) How does GCP support enterprise collaboration and productivity?
Answer: GCP supports enterprise collaboration and productivity through Google Workspace, which includes applications 
like Gmail, Docs, Drive, Calendar, Meet, and more. Google Workspace enables teams to collaborate in real-time, share 
documents securely, and communicate effectively, regardless of location. Additionally, GCP's infrastructure and services 
support the development and deployment of custom collaboration tools and integrations, further enhancing productivity 
and teamwork within enterprises.


NEW SECTION - GCP Emerging Technologies and Innovation.

1) How is GCP contributing to the advancement of quantum computing?
Answer: GCP is contributing to the advancement of quantum computing through its Quantum AI team, which focuses on 
developing quantum algorithms, software, and hardware to solve complex computational problems. GCP offers cloud access 
to quantum processors and simulators via its Quantum Computing Service, allowing researchers and developers to experiment 
with quantum algorithms and applications, thereby accelerating the field's growth and practical applications.

2) What role does GCP play in the development and deployment of AI and machine learning models?
Answer: GCP plays a significant role in AI and ML by providing a comprehensive suite of tools and services like TensorFlow, 
AI Platform, and AutoML. These services enable developers to easily build, train, and deploy machine learning models at scale. 
GCP also offers pre-trained AI models and APIs for vision, natural language, and other tasks, allowing businesses to 
integrate advanced AI capabilities into their applications without requiring deep expertise in machine learning.

3) How does GCP support IoT applications and devices?
Answer: GCP supports IoT applications and devices through its Cloud IoT Core service, which allows secure connection, 
management, and ingestion of data from millions of globally dispersed devices. Integrated with GCP's data analytics and 
machine learning tools, Cloud IoT Core enables real-time processing and analysis of IoT data for insights and decision-making, 
facilitating efficient operations, predictive maintenance, and personalized customer experiences.

4) Can you describe how GCP is fostering innovations in data analytics?
Answer: GCP fosters innovations in data analytics by offering powerful tools like BigQuery, Dataflow, and Looker. 
BigQuery, a fully managed, serverless data warehouse, allows for fast SQL queries and analysis of big data. Dataflow 
provides a unified stream and batch data processing, and Looker offers business intelligence capabilities. Together, 
these tools enable enterprises to harness the power of their data, uncover insights, and make data-driven decisions.

5) What initiatives has GCP taken towards sustainability and green computing?
Answer: GCP is committed to sustainability and green computing, aiming to operate on carbon-free energy 24/7 across all 
its data centers by 2030. Google has been carbon neutral since 2007 and matches 100% of its electricity consumption with 
renewable energy. GCP also offers tools for customers to monitor and reduce their carbon footprint, emphasizing efficient 
use of resources, renewable energy, and environmental responsibility in computing.

6) How does GCP integrate blockchain technologies into its services?
Answer: While GCP itself does not offer native blockchain services, it supports blockchain technologies by providing a 
secure and scalable infrastructure for deploying and running blockchain networks and applications. Through its marketplace, 
GCP offers solutions from third-party providers that enable enterprises to launch and manage blockchain nodes, develop 
smart contracts, and build decentralized applications, leveraging GCP's global infrastructure and security.

7) What advancements has GCP made in edge computing?
Answer: GCP has made significant advancements in edge computing through its Anthos platform and Edge TPU. Anthos allows 
businesses to run applications both in the cloud and at the edge, providing a consistent management layer across environments. 
The Edge TPU, a purpose-built ASIC designed to run machine learning models at the edge, enables high-speed, low-latency 
AI processing, supporting use cases such as IoT, smart cities, and autonomous vehicles.

8) How is GCP driving innovation in serverless computing?
Answer: GCP is driving innovation in serverless computing with products like Cloud Functions, Cloud Run, and App Engine, 
which allow developers to build and deploy applications without managing the underlying infrastructure. These services 
automatically scale resources to match demand, optimize costs, and enable faster development cycles. GCP's serverless 
offerings support a wide range of programming languages and integrate seamlessly with other GCP services, fostering a 
highly productive development environment. 

9) Discuss GCP's role in enhancing cybersecurity through emerging technologies.
Answer: GCP enhances cybersecurity through emerging technologies by incorporating advanced security tools and features 
like Security Command Center, Cloud Armor, and Confidential Computing. These tools leverage AI and machine learning to 
detect and respond to threats in real-time, offer protection against web and DDoS attacks, and enable the processing of 
sensitive data in a secure, encrypted environment. GCP's commitment to security innovation helps protect customer data 
and applications against evolving cyber threats.

10) How does GCP facilitate experimentation with new technologies for developers and enterprises?
Answer: GCP facilitates experimentation with new technologies through its comprehensive suite of cloud services, extensive 
documentation, and developer tools. The Google Cloud Free Tier, credits for startups, and various support resources lower 
the barrier to entry for experimenting with cloud technologies. GCP's regular updates and introduction of cutting-edge 
services provide developers and enterprises with the tools they need to innovate, experiment, and deploy modern applications 
leveraging the latest in cloud technology.


NEW SECTION - INTERVIEW 1

1) What is Google Cloud Platform (GCP)?
Answer: Google Cloud Platform (GCP) is a suite of cloud computing services that runs on the same infrastructure that Google 
uses internally for its end-user products, such as Google Search, Gmail, file storage, and YouTube. GCP offers a range of 
services including computing, data storage, data analytics, and machine learning.

2) Can you explain the difference between IaaS, PaaS, and SaaS? Provide examples from GCP.
Answer:
a) IaaS (Infrastructure as a Service): Provides virtualized computing resources over the internet. In GCP, 
Google Compute Engine (GCE) is an example of IaaS, offering virtual machines that users can customize and control.
b) PaaS (Platform as a Service): Offers hardware and software tools over the internet, typically for application development. 
Google App Engine (GAE) is a PaaS offering, providing a platform for developers to build, deploy, and scale applications.
c) SaaS (Software as a Service): Delivers software applications over the internet, on a subscription basis. 
Google Workspace (formerly G Suite) is an example of SaaS, offering productivity tools for businesses.

3) What are Google Compute Engine and Google App Engine? How do they differ?
Answer: Google Compute Engine (GCE) is an IaaS that provides virtual machines (VMs) in the cloud, giving users complete 
control over the OS, network settings, and the entire software stack. On the other hand, Google App Engine (GAE) is a PaaS 
that abstracts much of the underlying infrastructure, allowing developers to focus on writing code without worrying about 
the environment it runs in. GAE automatically scales your application in response to the traffic it receives.

4) Explain the concept of load balancing in GCP.
Answer: Load balancing is a method used to distribute network or application traffic across multiple servers in a server 
farm or server pool. In GCP, load balancing allows users to distribute incoming traffic across multiple instances of their 
applications, improving performance and availability. GCP offers various types of load balancers, including HTTP(S) 
Load Balancing, TCP/SSL Proxy Load Balancing, and Network Load Balancing, each designed for specific scenarios.

5) What is Google Cloud Storage? What are the different types of storage classes it offers?
Answer: Google Cloud Storage is a unified object storage solution that allows users to store and access data from anywhere. 
It offers different storage classes for various use cases: Standard (for frequently accessed data), Nearline 
(for data accessed less than once a month), Coldline (for data accessed less than once a quarter), and Archive 
(for long-term storage, accessed less than once a year), each varying in price and accessibility.


NEW SECTION - INTERVIEW 2

1) Describe the difference between BigQuery and Cloud Bigtable.
Answer: BigQuery is a fully managed, serverless, and highly scalable data warehouse designed for business agility. 
It's ideal for running big data analytics. Cloud Bigtable, on the other hand, is a NoSQL database service suited for 
real-time analytics and operational workloads with large amounts of data. While BigQuery is optimized for analytical 
queries, Cloud Bigtable is optimized for low latency data access.

2) What are Virtual Private Cloud (VPC) and its benefits in GCP?
Answer: A Virtual Private Cloud (VPC) in GCP is a managed networking functionality for your Cloud resources. It provides 
a private network with IP allocation, routing, and network firewall policies to secure and manage your cloud resources. 
Benefits include isolation of cloud resources, scalable and flexible networking options, and integration with GCP services 
for a seamless cloud environment.

3) How does GCP manage identity and access?
Answer: GCP manages identity and access through Cloud Identity and Access Management (IAM). IAM allows administrators to 
authorize who can take action on specific resources, giving them control over which users and services can access different 
resources. IAM policies define permissions and can be set at various levels in the GCP resource hierarchy.

4) What is the role of Kubernetes Engine in GCP?
Answer: Google Kubernetes Engine (GKE) is a managed environment in GCP for deploying, managing, and scaling containerized 
applications using Google infrastructure. GKE automates many aspects of setting up and maintaining Kubernetes clusters, 
making it easier to deploy applications, scale them as needed, and manage their lifecycle without deep Kubernetes expertise.

5) Explain the concept of serverless computing in GCP. Mention some serverless products offered by GCP.
Answer: Serverless computing allows you to build and run applications and services without managing infrastructure. 
Your application still runs on servers, but all the server management is done by GCP. This model enables developers to 
focus on their code while GCP handles scaling, provisioning, and managing servers. Examples of serverless products in 
GCP include Google Cloud Functions (event-driven functions), Google App Engine (application platform), 
and Google Cloud Run (containerized applications).


NEW SECTION - INTERVIEW 3

1) What are the primary storage options available in GCP?
Answer: GCP offers a variety of storage options to cater to different needs:
a) Google Cloud Storage: An object storage service for storing and accessing any amount of data.
b) Persistent Disk: Block storage for Google Compute Engine VMs.
c) Filestore: A managed file storage service for applications that require a file system interface and a shared file system.
d) Local SSD: High-performance, transient, local block storage designed to provide high throughput and low latency.

2) Can you explain the difference between Cloud SQL and Cloud Spanner?
Answer:
a) Cloud SQL is a fully managed relational database service that supports MySQL, PostgreSQL, and SQL Server. It's ideal 
for traditional database workloads that require a relational database and is designed for compatibility with existing applications.
b) Cloud Spanner is a fully managed, scalable, relational database service for regional and global application data. 
It offers horizontal scaling without compromising on strong consistency and relational semantics, making it suitable for larger, 
distributed applications that require high availability and global distribution.

3) What is the role of Google Cloud Build in GCP DevOps practices?
Answer: Google Cloud Build is a fully managed continuous integration and delivery (CI/CD) platform that automates the build, 
test, and deployment of software. In GCP DevOps practices, it plays a pivotal role by enabling developers to create 
automated workflows that compile source code, run tests, and deploy applications to Google Cloud or any other environment. 
It supports a wide range of programming languages and integrates with Google Cloud services and GitHub, making it a 
versatile tool for DevOps pipelines.

4) How does GCP implement Infrastructure as Code (IaC), and what tools does it offer for this?
Answer: GCP implements Infrastructure as Code (IaC) through Google Cloud Deployment Manager and Terraform. These tools 
allow developers and operations teams to automate the provisioning of Google Cloud resources using configuration files. 
Deployment Manager is GCP's native IaC service, enabling the management of cloud resources with templates and configuration 
files. Terraform, an open-source tool, offers a declarative configuration language to model and manage GCP and other 
cloud services' infrastructure. Both tools enable versioning, repeatability, and codification of infrastructure, 
key principles in DevOps.

5) What is Google Kubernetes Engine (GKE), and how does it simplify Kubernetes operations?
Answer: Google Kubernetes Engine (GKE) is a managed, production-ready environment for deploying containerized applications 
using Kubernetes on Google Cloud. It simplifies Kubernetes operations by automating tasks such as provisioning, scaling, 
and updating the Kubernetes cluster. GKE offers integrated logging and monitoring, seamless integration with Google Cloud 
services, and advanced network and security features, making it easier to deploy and manage applications at scale.


NEW SECTION - INTERVIEW 4

1) What is Identity and Access Management (IAM) in GCP?
Answer: IAM in GCP manages access control by defining who (identity) has what access (roles) to resources within GCP.
It allows administrators to grant granular permissions to users, groups, and service accounts.

2) Explain the role of Service Accounts in GCP.
Answer: Service Accounts are special accounts used by applications or virtual machines (VMs) to interact with other 
Google Cloud services. These accounts can be assigned IAM roles and used to authenticate applications for accessing GCP 
resources programmatically.

3) What is Cloud Identity in GCP and how does it integrate with IAM?
Answer: Cloud Identity is a unified identity, access, app, and device management solution built for the cloud. It 
integrates with IAM to provide centralized administration of users and groups, allowing for the management of their 
access to GCP services and resources.

4) How does GCP handle data encryption at rest and in transit?
Answer:
a) At rest: GCP automatically encrypts data before it is written to disk, using encryption keys managed by Google.
Customers can also manage their own encryption keys using Cloud Key Management Service (KMS).
b) In transit: GCP encrypts data as it moves between its data centers and to/from clients over the internet using 
protocols such as TLS.

5) What is the purpose of Cloud Security Command Center (CSCC) in GCP?
CSCC is a comprehensive security management and data risk platform for GCP that helps you prevent, detect, and respond
to threats. It provides visibility into and control over cloud resources, allowing you to scan for sensitive data,
detect common web vulnerabilities, and review access rights.


NEW SECTION - INTERVIEW 5

1) What is Google Cloud Console and what are its key features?
Answer: Google Cloud Console is a web-based, graphical user interface that allows users to manage their Google Cloud resources.
Key features include the ability to deploy and manage applications, monitor services, manage billing, set up IAM roles and
permissions, access Cloud Shell, and utilize APIs. It provides an intuitive way to navigate and control every aspect of 
Google Cloud.

2) Explain the role of Google Cloud SDK in GCP management.
Answer: The Google Cloud SDK is a set of tools that can be used to manage resources and applications hosted on Google Cloud.
These tools include the gcloud command-line tool, gsutil, and bq, which respectively allow for managing Google Cloud resources,
interacting with Google Cloud Storage, and querying BigQuery datasets. The SDK enables scripting and automation of cloud
management tasks directly from the command line or through scripts.

3) What is Cloud Monitoring and how does it integrate with GCP services?
Answer: Cloud Monitoring is a service within GCP that provides visibility into the performance, uptime, and overall health of
cloud-powered applications. It collects metrics, events, and metadata from GCP services, virtual machines, and application
instrumentation. Using Cloud Monitoring, developers and operators can gain insights into their applications' performance
in real-time, set up alerts, and create custom dashboards for visualizing metric data. It integrates seamlessly with most
GCP services, offering out-of-the-box monitoring capabilities.

4) Describe Cloud Logging and its importance in GCP.
Answer: Cloud Logging is a fully managed service in GCP that allows you to store, search, analyze, monitor, and alert on
logging data and events from Google Cloud and Amazon Web Services. It plays a crucial role in debugging and troubleshooting
application issues, security monitoring, and audit trail maintenance. Cloud Logging provides a centralized platform for
log management, supporting real-time log management and analysis, making it easier for developers and sysadmins to
understand their cloud environments.

5) How does Google Cloud's operations suite enhance application and infrastructure monitoring?
Answer: Google Cloud's operations suite, formerly known as Stackdriver, enhances application and infrastructure monitoring
by offering integrated and intelligent monitoring, logging, and diagnostics. It provides powerful monitoring capabilities
that include performance metrics, dashboards, uptime checks, and alerting systems. The suite also offers extensive logging
capabilities, error reporting, and trace analysis for understanding application performance and health in depth,
facilitating proactive management and optimization of cloud resources.


NEW SECTION - Compute Engine

1) When using Create Deployment from Cloud Console, which of the following cannot be specified for a deployment?
Answer: Time to live (TTL). Time to Live is not an attribute of deployments. Application name, container image, and initial
command can all be specified.

2) Stackdriver Monitoring collects metrics such as query counts, execution times, scanned bytes,
and table count are captured for which GCP managed services ?
Answer: Cloud BigQuery. Stackdriver Monitoring collects metrics on the performance of infrastructure resources and applications.
The mentioned metrics are captured for BigQuery.

3) You are deploying an application that will need to scale and be highly available.
Which of these Compute Engine components will help achieve scalability and high availability?
Answer: Instance groups. Instance groups are sets of VMs that can be configured to scale and are used with load balancers,
which contribute to improving availability. Preemptible instances are not highly available because they can be shut down
at any time by GCP. Cloud Storage is not a Compute Engine component. GPUs can help improve throughput for math-intensive
operations but do not contribute to high availability.

4) Customers are complaining of long waits while booking airline tickets. There are many microservices in your
ticket processing system. You would like to view information about the time each microservice takes to run.
What Stackdriver service would you use?
Answer: Stackdriver Trace.
a) Stackdriver Trace is used to collect information about the time required to execute functions in a call stack.
b) Stackdriver Logging is used to collect semi-structured data about events.
c) Stackdriver Monitoring collects performance metrics.
d) Stackdriver Debugger is used to inspect the state of running code.

5) An architect has suggested a particular machine type for your workload. You are in the console creating
a VM and you don’t see the machine type in the list of available machine types. What could be the reason for this?
Answer: That machine type is not available in the zone you specified.
Different zones may have different machine types available, so you will need to specify a region first and then a zone
to determine the set of machine types available. If the machine type does not appear in the list, it is not available
in that zone. Subnets and IP addresses are not related to the machine types available. Unless you are specifying a custom
machine type, you do not specify the amount of memory; that is defined by the machine type.

6) What are the categories of Cloud Launcher solutions?
Answer: Data sets, operating systems, and developer tools.
Categories of solutions include all of the categories mentioned, so "Data sets, operating systems,
and developer tools". Others include Kubernetes Apps, API & Services, and Databases.

7) You notice that 5 VMs are processing the data at 11:00 AM GMT as compared to 7 VMs at 10:30 AM GMT.
What feature of managed instance groups is at work here?
Answer: Autoscaling.
Autoscaling adds or removes instances based on instance metrics and workloads.

8) You are deploying a Python web application to GCP. The application uses only custom code and basic
Python libraries. You expect to have sporadic use of the application for the foreseeable future and want to minimize
both the cost of running the application and the DevOps overhead of managing the application.
Which computing service is the best option for running the application?
Answer: App Engine standard environment.
The App Engine standard environment can run Python applications, which can autoscale down to no instances when there
is no load and thereby minimize costs.
Compute Engine and the App Engine flexible environment both require more configuration management than
the App Engine standard environment.
Kubernetes Engine is used when a cluster of servers is needed to support large or multiple applications using the
same computing resources.

9) The amount of time GCP will wait before collecting performance statistics from the instance for
Autoscaling is called as ?
Answer: Cool down period.

10) Your management team is considering three different cloud providers. You have been asked to summarize
billing and cost information to help the management team compare cost structures between clouds. Which of the following
would you mention about the cost of VMs in GCP?
Answer: VMs are billed in 1-second increments, cost varies with the number of CPUs and amount of memory
in a machine type, you can create custom machine types, preemptible VMs cost up to 80 percent less than standard VMs,
and Google offers discounts for sustained usage.

11) You are attempting to execute commands to initiate a deployment on a Kubernetes cluster. The commands are
not having any effect. You suspect that a Kubernetes component is not functioning correctly. What component could be the problem?
Answer: The Kubernetes API.
All interactions with the cluster are done through the master using the Kubernetes API.
If an action is to be taken on a node, the command is issued by the cluster master.

12) You need to create multiple VMs which needs heterogeneous configurations as part of instance group.
How can we achieve that?
Answer: Create an unmanaged instance group.
Managed instance groups (MIGs) consist of identically configured VMs.
Unmanaged instance groups allow for heterogeneous VMs.

13) CPU utilization exceeding 70 percent for three minutes is an example of ?
Answer: Alerting Policy.
In Stackdriver Monitoring, alerts are created using an alerting policy. CPU Utilization is one such example.

14) Running BigTable clusters in different regions will _____ replication latency and ________ availability**
Answer: increase, increase.
When running the BigTable cluster across multiple region - you will ensure higher availability as you can handle region
level failure. But it will also increase replication latency as data needs to be replicated across cluster in different region.

15) Your application is running on Kubernetes cluster on GCP. You have configured autoscaling when the CPU
utilizations exceeds 75 percent. How is the CPU Utilizations calculated?
Answer: Based on all CPUs used by the deployment.
Replicas are added to the deployment and hence CPU Utilizations should be checked at deployment level.
It would be incorrect to check the CPU at Cluster level as it would have one or more deployment running on it.

16) An architect is moving a legacy application to Google Cloud and wants to minimize the changes to the
existing architecture while administering the cluster as a single entity. The legacy application runs on a load-balanced
cluster that runs nodes with two different configurations. The two configurations are required because of design decisions
made several years ago. The load on the application is fairly consistent, so there is rarely a need to scale up or down.
What GCP Compute Engine resource would you recommended using?
Answer: Unmanaged instance groups.
Unmanaged instance groups are available for limited use cases such as this. Unmanaged instance groups are not recommended
in general. Managed instance groups are the recommended way to use instance groups, but the two different configurations
prevents their use. Preemptible instances and GPUs are not relevant to this scenario.

17) Your company is based in X and will be running a virtual server for Y. What factor determines the unit per minute cost.
Answer: The characteristics of the server.
The characteristics of the server, such as the number of virtual servers, the amount of memory, and the region where you
run the VM, influence the cost. Time of day is not a factor, nor is the type of application you run on the VM.

18) What can be the basis for scaling up an instance group?
Answer: Network latency, load balancing capacity, and CPU utilization.
You can configure an autoscaling policy to trigger adding or removing instances based on CPU utilization,
monitoring metric, load balancing capacity, or queue-based workloads.
Disk, network latency, and memory can trigger scaling if monitoring metrics on those resources are configured.

19) If a pod is terminated for being unhealthy—then the <WHAT> will add pods until the desired state is reached.
Answer: Kubernetes ReplicaSets
A ReplicaSet is a controller that manages the number of pods running for a deployment.
A deployment is a higher-level concept that manages ReplicaSets and provides declarative updates.

20) Cloud Dataflow does require you to specify machine types when configuring a pipeline?
Answer: FALSE.
Cloud Dataflow does not require you to specify machine types, but you can specify machine type and worker disk type if
you want that level of control.

21) Your manager is making a presentation to executives in your company advocating that you start using
Kubernetes Engine. You suggest that the manager highlight all the features Kubernetes provides to reduce the workload
on DevOps engineers. You describe several features, including all of the following except which one?
Automatic upgrading of cluster software as needed.
Answer: Security scanning for vulnerabilities.
Load balancing across Compute Engine VMs that are deployed in a Kubernetes cluster.
Automatic scaling of nodes in the cluster.
Kubernetes provides load balancing, scaling, and automatic upgrading of software.
It does not provide vulnerability scanning.
GCP does have a Cloud Security Scanner product, but that is designed to work with App Engine to identify common
application vulnerabilities.

22) A DevOps engineer is noticing a spike in CPU utilization on your servers. You explain you have just launched
a deployment. You’d like to show the DevOps engineer the details of a deployment you just launched. What command would you use?
Answer: 'gcloud deployment-manager deployments describe'
The correct answer is gcloud deployment-manager deployments describe, cloud-launcher is not the name of the service.
Note: list displays a brief summary of each deployment and describe displays a detailed description.

23) You are using snapshots to save copies of a 100GB disk. You make a snapshot and then add 10GB of data.
You create a second snapshot. How much storage is used in total for the two snapshots (assume no compression)?
Answer: 110 GB, with 100 GB for the first and 10 GB for the second.
When you first create a snapshot, GCP will make a full copy of the data on the persistent disk.
The next time you create a snapshot from that disk, GCP will only copy the data that has changed since
the last snapshot.
Note : GCP does not store a full copy of the second snapshot and the first snapshot is not deleted automatically.
Also, subsequent snapshots do not incur 10 percent overhead.

24) Before creating an instance group, you need to create what?
Answer: Instance group template.
An instance group template is used to specify how the instance group should be created.
Note: instances are created automatically when an instance group is created.
Boot disk images and snapshots do not have to be created before creating an instance group.

25) You need to set up a server with a high level of security. You want to be prepared in case of attacks
on your server by someone trying to inject a rootkit (a kind of malware that can alter the operating system).
Which option should you select when creating a VM?
Answer: Shield VM.
Shield VM is an advanced set of security controls that includes Integrity Monitoring, a check to ensure
boot images have not been tampered with, which makes it the right answer.
Firewalls are used to control ingress and egress of network traffic to a server or subnet.
Project-wide SSH keys are used for authenticating users across servers within a project.
Boot disk integrity check is a fictional feature.

26) You have decided to delegate the task of making backup snapshots to a member of your team.
What role would you need to grant to your team member to create snapshots?
Answer: Compute Storage Admin.
To work with snapshots, a user must be assigned the Compute Storage Admin role.

27) You will be building a number of machine learning models on an instance and attaching GPU to the instance.
When you run your machine learning models they take an unusually long time to run. It appears that GPU is not being used.
What could be the cause of this?
Answer: GPU libraries are not installed.
To function properly, the operating system must have GPU libraries installed. The operating system does not have to be
Ubuntu based, and there is no need to have at least eight CPUs in an instance before you can attach and use a GPU.
Available disk space does not determine if a GPU is used or not.

28) You lead a team of cloud engineers who maintain cloud resources for several departments in your company.
You’ve noticed a problem with configuration drift. Some machine configurations are no longer in the same state as they
were when created. You can’t find notes or documentation on how the changes were made or why.
What practice would you implement to solve this problem?
Answer: Write scripts using gcloud commands to change configuration and store those scripts in a version control system.
Using version-controlled scripts is the best approach of the four options. Scripts can be documented with reasons for
the changes and they can be run repeatedly on different machines to implement the same change.
This reduces the chance of error when manually entering a command.

29) You have been asked by your manager to deploy a WordPress site. You expect heavy traffic, and your manager
wants to make sure the VM hosting the WordPress site has enough resources. Which resources can you configure when launching
a WordPress site using Cloud Launcher?
Answer: Disk type, Disk size, Machine type.
You can also specify firewall rules to allow both HTTP and HTTPS traffic or change the zone in which the VM runs.

30) You have acquired a 10 GB data set from a third-party research firm. A group of data scientists would
like to access this data from their statistics programs written in R. R works well with Linux and Windows file systems,
and the data scientists are familiar with file operations in R. The data scientists would each like to have their own
dedicated VM with the data available in the VM’s file system. What is a way to make this data readily available on a VM
and minimize the steps the data scientists will have to take?
Answer: Create VMs using a source image created from a disk with the data on it.
10 GB of data is small enough to store on a single disk. By creating an image of a disk with the data stored on it,
you can specify that source image when creating a VM. Other options would require the data scientist to copy the data
from Cloud Storage to a disk on the VM. Google Drive would similarly require copying the data. loading into BigQuery
would load data into a database, not a file system as specified in the requirements.

31) A client has brought you in to help reduce their DevOps overhead. Engineers are spending too much time
patching servers and optimizing server utilization. They want to move to serverless platforms as much as possible.
Your client has heard of Cloud Functions and wants to use them as much as possible. You recommend all of the following
types of applications except which one?
Answer: Long-running data warehouse data load procedures.
Cloud Functions is best suited for event-driven processing, such as a file being uploaded to Cloud Storage or an event
being writing to a Pub/Sub queue. Long-running jobs, such as loading data into a data warehouse, are better suited to
Compute Engine or App Engine.

32) The marketing department in your company wants to deploy a web application but does not want to have to
manage servers or clusters. A good option for them is?
Answer: App Engine.
App Engine is a PaaS that allows developers to deploy full applications without having to manage servers or clusters.
Compute Engine and Kubernetes Engine require management of servers.
Cloud Functions is suitable for short-running Node.js or Python functions but not full applications.

33) The GCP service for storing and managing Docker containers is?
Answer: Container Registry.
The GCP service for storing and managing Docker containers is Container Registry.
Cloud Build is for creating images.

34) When we are moving services from "n1-standard-1" to "n1-standard-4" it is called as Vertical Scaling?
Answer: TRUE.
Vertical scaling, requires moving services from one VM to another VM with more or fewer resources

35) Your team is developing a new pipeline to analyze a stream of data from sensors on manufacturing devices.
The old pipeline occasionally corrupted data because parallel threads overwrote data written by other threads.
You decide to use Cloud Functions as part of the pipeline. As a developer of a Cloud Function, what do you have to do to
prevent multiple invocations of the function from interfering with each other?
Answer: Nothing. GCP ensures that function invocations do not interfere with each other.
Each invocation of a cloud function runs in a secure, isolated runtime environment.
There is no need to check whether other invocations are running.
With the Cloud Functions service, there is no way for a developer to control code execution at the process
or thread level.

36) A new engineer is asking for clarification about when it is best to use Kubernetes and when to use
instance groups. You point out that Kubernetes uses instance groups. What purpose do instance groups play in a Kubernetes cluster?
Answer: They create sets of VMs that can be managed as a unit.
Kubernetes creates instance groups as part of the process of creating a cluster, which makes it the correct answer.
Stackdriver, not instance groups, is used to monitor the health of nodes and to create alerts and notifications.
Kubernetes creates pods and deployments; they are not provided by instance groups.

37) Your company is about to release a new online service that builds on a new user interface experience
driven by a set of services that will run on your servers. There is a separate set of services that manage authentication
and authorization. A data store set of services keeps track of account information. All three sets of services must be
highly reliable and scale to meet demand. Which of the GCP services is the best option for deploying this?
Answer: Kubernetes Engine.
The scenario described is a good fit for Kubernetes.
Each of the groups of services can be structured in pods and deployed using Kubernetes deployment.
Kubernetes Engine manages node health, load balancing, and scaling.

38) Adding virtual machines to an instance group can be triggered in an autoscaling policy by which one?
Answer:
a) CPU utilization.
b) Stackdriver metrics.
c) Load balancing serving capacity.
IAM policy violations do not trigger changes in the size of clusters. All other options can be used to trigger a change
in cluster size.

39) GCP compute Engine VMs can be created using?
Answer:
a) Cloud Console.
b) REST API Request.
c) SOAP Request.
d) Command line SDK.
VMs are provisioned using the cloud console, the command-line SDK, or the REST API. SOAP request is not correct choice.

40) What command is used to run a Docker image on a cluster?
Answer: 'kubectl run'.
The kubectl command is used to control workloads on a Kubernetes cluster once it is created. gcloud is not used to
manipulate Kubernetes processes. Note: beta is not required in kubectl commands.

41) The Network tab of the create VM form is where you would perform which of the following operations?
Answer: Add a network interface to the VM.
In the Network tab of the VM form, you can add another network interface. GCP sets the IP address. There is no option
to specify a router or change firewall rules on the Network tab.

42) What command would you use to have 10 replicas of a deployment named ch07-app-deploy?
Answer: 'kubectl scale deployment ch07-app-deploy --replicas=10'
Use of gcloud and upgrade is incorrect. "scale" is the right command along with replicas.

43) You have deployed an application to a Kubernetes cluster. You have noticed that several pods are starved
for resources for a period of time and the pods are shut down. When resources are available, new instantiations of those
pods are created. Clients are still able to connect to pods even though the new pods have different IP addresses from the
pods that were terminated. What Kubernetes component makes this possible?
Answer: Service.
Services provide a level of indirection to accessing pods.
Pods are ephemeral.
Clients connect to services, which can discover pods.
ReplicaSets and StatefulSets provide managed pods.
Alerts are for reporting on the state of resources.

44) You have just opened the GCP console at console.google.com. You have authenticated with the user you want
to use. What is one of the first things you should do before performing tasks on VMs?
Answer: Verify that the selected projects is the one you want to work with.
You should verify the project selected because all operations you perform will apply to resources
in the selected project, making it the correct answer.
You do not need to open Cloud Shell unless you want to work with the command line, and if you did, you should
verify that the project is correctly selected first.
Logging into a VM using SSH is one of the tasks that requires you to be working with the correct project,
so logging in via SSH should not happen before verifying the project.
The list of VMs in the VM Instance window is a list of VMs in the current project.
You should verify which project you are using to ensure you are viewing the set of VMs you think you are using.

45) A software engineer comes to you for a recommendation. She has implemented a machine learning algorithm
to identify cancerous cells in medical images. The algorithm is computationally intensive, makes many mathematical
calculations, requires immediate access to large amounts of data, and cannot be easily distributed over multiple servers.
What kind of Compute Engine configuration would you recommend?
Answer: High memory, high CPU, GPU.
A computationally intensive application obviously requires high CPUs, but the fact that there are many mathematical
calculations indicates that a GPU should be used.
You might consider running this in a cluster, but the work is not easily distributed over multiple servers,
so you will need to have a single server capable of handling the load.
Immediate access to large amounts of data indicates that a high-memory machine should be recommended.

46) Which command will delete an instance named ch06-instance-3?
Answer: 'gcloud compute instances delete ch06-instance-3'.
The command to delete an instance is gcloud compute instances delete followed by the name of the instance.
Note : stop command stops but does not delete the instance.

47) You have an application that uses a Pub/Sub message queue to maintain a list of tasks that are to be
processed by another application. The application that consumes messages from the Pub/Sub queue removes the message only
after completing the task. It takes approximately 10 seconds to complete a task. It is not a problem if two or more VMs
perform the same task. What is a cost-effective configuration for processing this workload?
Answer: Use preemptible VMs.
This is a good use case for preemptible VMs because they could reduce the cost of running
the second application without the risk of losing work.
Since tasks are deleted from the queue only after they are completed if a preemptible VM is shut down before
completing the task, another VM can perform the task.
Also, there is no harm in running a task more than once, so if two VMs do the same task, it will not adversely
affect the output of the application.
DataProc and Spanner are not appropriate products for this task.

48) Your team is very new to Kubernetes and are migrating to GCP. Developers of the team are unfamiliar with
Kubernetes and does not understand why they cannot find a command to deploy a container. How would you describe the reason
why there is no deploy container command?
Answer: Kubernetes uses pods as the smallest deployable unit.
Kubernetes uses pods as the smallest deployable unit, and pods have usually one but possibly multiple containers that
are deployed as a unit.

49) Stackdriver Monitoring can collect metrics on Amazon Web Services ?
Answer: TRUE.
Stackdriver Monitoring collects metrics on the performance of infrastructure resources and applications.
Resources can be in GCP as well as the Amazon Web Services cloud.

50) A colleague has asked for your assistance setting up a test environment in Google Cloud.
They have never worked in GCP. You suggest starting with a single VM. Which of the following is the minimal set of
information you will need?
Answer: A name for the VM, a machine type, a region, and a zone.
The name of the VM, the region and zone, and the machine type can all be specified in the console along
with other parameters.


NEW SECTION - Security & Compliance

1) Your company is launching an IoT service and will receive large volumes of streaming data. You have to store
this data in Bigtable. You want to explore the Bigtable environment from the command line. What command would you run to
ensure you have command-line tools installed?
Answer: 'gcloud components install cbt'.
The correct command is 'gcloud components install cbt` to install the Bigtable.

2) You are the lead developer on a medical application that uses patients’ smartphones to capture biometric data.
The app is required to collect data and store it on the smartphone when data cannot be reliably transmitted to the
backend application. You want to minimize the amount of development you have to do to keep data synchronized between
smartphones and backend data stores. Which data store option should you recommend?
Answer: Cloud Firestore.
Cloud Firestore is a mobile database service that can synchronize data between mobile devices
and centralized storage.
Spanner is a global relational database for large-scale applications that require transaction support in highly
scaled databases.
Datastore and Cloud SQL could be used but would require more custom development to synchronize data between
mobile devices and the centralized data store.

3) Once a GCP Cloud dataproc cluster is created, it can be?
Answer: Scaled up, Only workers nodes can change, Scaled down.
Once a cluster is created, it can be scaled up or down.
Only the number of worker nodes can change—master nodes are fixed.

4) A team of mobile developers is developing a new application. It will require synchronizing data between
mobile devices and a backend database. Which database service would you recommend?
Answer: Firestore.
Firestore is a document database that has mobile supporting features, like data synchronization, so it is
the right answer.
BigQuery is for analytics, not mobile or transactional applications.
Spanner is a global relational database but does not have mobile-specific features.
Bigtable could be used with mobile devices, but it does not have mobile-specific features like synchronization.

5) A team of data scientists have asked for your help setting up an Apache Spark cluster.
You suggest they use a managed GCP service instead of managing a cluster themselves on Compute Engine.
The service they would use is?
Answer: Cloud Dataproc.
Cloud Dataproc is the managed Spark service.
Cloud Dataflow is for stream and batch processing of data.
BigQuery is for analytics.
Cloud Hadoop is not a GCP service.

6) You are working with a startup developing analytics software for IoT data. You have to be able to ingest
large volumes of data consistently and store it for several months. The startup has several applications that will need
to query this data. Volumes are expected to grow to petabyte volumes. Which database should you use?
Answer: Bigtable.
Bigtable is a wide-column database that can ingest large volumes of data consistently.
It also supports low-millisecond latency, making it a good choice for supporting querying.
Cloud Spanner is a global relational database that is not suitable for high-speed ingestion of large volumes of data.
Datastore is an object data model and not a good fit for IoT or other time series data.
BigQuery is an analytics database and not designed for ingestion of large volumes of data in short periods of time.

7) You need to store data for X and therefore you are using a cache for Y. How will the cache affect data retrieval?
Answer: Using a cache will reduce latency, since retrieving from a cache is faster than retrieving from SSDs or HDDs.
Caches use memory, and that makes them the fastest storage type for reading data.
Caches are data stores on the backend of distributed systems, not the clients.
A cache would have no effect on client-side JavaScript execution.
Caches do not store data in a cache if power is lost; the data would have to be reloaded.
Caches can get out of sync with the system of truth because the system of truth could be updated, but the cache may
not be updated.
Caches have faster read times than SSDs and HDDs.

8) A software developer on your team is asking for your help improving the query performance of a database
application. The developer is using a Cloud SQL MySQL, Second Generation instance. Which options would you recommend?
Answer: Memorystore and SSD persistent disks.
Memorystore is a managed Redis cache. The cache can be used to store the results of queries.
Follow-on queries that reference the data stored in the cache can read it from the cache, which is much faster than
reading from persistent disks.
SSDs have significantly lower latency than hard disk drives and should be used for performance-sensitive
applications like databases.
Datastore is a managed NoSQL database.

9) As required by your company’s policy, you need to back up your Datastore database at least once per day.
An auditor is questioning whether or not Datastore export is sufficient. You explain that the Datastore export command
produces what outputs?
Answer: A metadata file and a folder with the data.
The export process creates a metadata file with information about the data exported and a folder that has
the data itself.

10) You have just created a Cloud Spanner instance. You have been tasked with creating a way to store data
about a product catalog. What is the next step after creating a Cloud Spanner instance that you would perform to enable
you to load data?
Answer: Create a database within the instance.
The next step is to create a database within the instance. Once a database is created, tables can be created, and data
can be loaded into tables. Cloud Spanner is a managed database, so you do not need to apply security patches.
You can’t create tables without first having created a database.

11) A software developer asks for your help exporting data from a Cloud SQL database.
The developer tells you which database to export and which bucket to store the export file in, but hasn’t mentioned which
file format should be used for the export file. What are the options for the export file format?
Answer: CSV and SQL.
When exporting a database from Cloud SQL, the export file format options are CSV and SQL, JSON & XML are not an option.

12) Cloud Filestore is based on what file system technology?
Answer: Network File System (NFS).
Cloud Filestore is based on Network Filesystem (NSF), which is a distributed file management system.
The other options are file systems supported by Linux but are not the foundation of Cloud Filestore.

13) Which of the following file formats is and which is not an option for an export file when exporting from BigQuery?
Answer: CSV, JSON. Avro.
XML is not an option in BigQuery’s export process.

14) A new government regulation requires all pharmaceutical companies to keep their financial data for 5 years.
What would you recommend as the best way to comply with the regulation requirement?
Answer: Define a data retention policy.
You would be tempted to answer Coldline Storage - but the correct answer is "Define a data retention policy.".
Note there is no mention about the access to this data i.e. will it be access frequenctly or infrequenctly?
By defining a retention policy - we ensure that the data is not deleted, as required by the regulation.

15) You need to copy files from your local device to a bucket in Cloud Storage. What command would you use?
Assume you have Cloud SDK installed on your local computer. 
Answer: 'gsutil cp'.
gsutil is the command to copy files to Cloud Storage. The verb is cp, not copy.

16) What features can you configure when running a Second Generation MySQL database in Cloud SQL?
Answer: Machine type, Failover replicas, Maintenance window.
With a second-generation instance, you can configure the MySQL version, connectivity, machine type, automatic backups,
failover replicas, database flags, maintenance windows, and labels, hence all if the above is correct.

17) Your manager asks you to set up a bare-bones Pub/Sub system as a sandbox for new developers to learn about
messaging systems. What are the two resources within Pub/Sub you will need to create?
Answer: Topics and subscriptions.
Pub/Sub works with topics, which receive and hold messages, and subscriptions, which make messages available to 
consuming applications.
Tables are data structures in relational databases, not message queues.
Databases exist in instances of database management systems, not messaging systems.
Tables are not a resource in messaging systems.

18) You want to estimate the cost of running a BigQuery query. What two services within
Google Cloud Platform will you need to use?
Answer: BigQuery and Pricing Calculator.
BigQuery provides an estimate of the amount of data scanned, and the Pricing Calculator gives a cost estimate
for scanning that volume of data.
The Billing service tracks charges incurred. It is not used to estimate future or potential charges.

19) The correct command to create a Pub/Sub topic is which of the following?
Answer: 'gcloud pubsub topics create'.
gcloud followed by the service, in this case pubsub, followed by the resource, in this case topics,
and finally the verb, in this case create.

20) You have received a large data set from an Internet of Things (IoT) system. You want to use BigQuery to
analyze the data. What command-line command would you use to make data available for analysis in BigQuery?
Answer: 'bq load --autodetect --source_format=[FORMAT] [DATASET].[TABLE][PATH_TO_SOURCE]'
The autodetect and source_format parameters and path to source are correctly specified in all options.
To work with Bigquery we use bq and "load" is the right option and not import.

21) What is the query language used by Datastore?
Answer: GQL.
Datastore uses a SQL-like query language called GQL.
SQL is not used.
MDX is a query language for online analytic processing (OLAP) systems.
DataFrames is a data structure used in Spark.

22) When you enter a query into the BigQuery query form, BigQuery analyzes the query and displays an estimate
of what metric?
Answer: Amount of data scanned.
BigQuery displays an estimate of the amount of data scanned.
This is important because BigQuery charges for data scanned in queries.

23) You have created a Cloud Spanner instance and database. According to Google best practices,
how often should you update VM packages using apt-get?
Answer: Never, Cloud Spanner is a managed service.
There is no need to apply patches to the underlying compute resources when using Cloud Spanner, because Google manages
resources used by Cloud Spanner. Updating packages is a good practice when using VMs, for example, with Compute Engine,
but it is not necessary with a managed service.

24) All block storage systems use what block size?
Answer: Block size can vary.
Block size is established when a file system is created. 4KB block sizes are commonly used in Linux.

25) What is one of the direct advantages of using a message queue in distributed systems?
Answer: It decouples services, so if one lags, it does not cause other services to lag.
Using a message queue between services decouples the services, so if one lags it does not cause other services to lag.
Note, adding a message queue does not directly mitigate any security risks that might exist in the distributed system,
such as overly permissive permissions.

26) What kind of data model is used by Datastore?
Answer: Document.
Datastore is a document database.
Cloud SQL and Spanner are relational databases.
Bigtable is a wide-column database.

27) Which of the following commands will create a subscription on the topic ace-exam-topic1?
Answer: 'gcloud pubsub subscriptions create --topic=ace-exam-topic1 ace-exam-sub1'.
gcloud pubsub subscriptions create followed by the topic and the name of the subscription.

28) Before you can start storing data in BigQuery, what must you create?
Answer: A data set.
To use BigQuery to store data, you must have a data set to store it, which makes it the right answer.
Buckets are used by Cloud Storage, not BigQuery. You do not manage persistent disks when using BigQuery.
An entity is a data structure in Datastore, not BigQuery.

29) Your department has been asked to analyze large batches of data every night. The jobs will run for about
three to four hours. You want to shut down resources as soon as the analysis is done, so you decide to write a script to
create a Dataproc cluster every night at midnight. What command would you use to create a cluster called
spark-nightly-analysis in the us-west2-a zone?
Answer: 'gcloud dataproc clusters create spark-nightly-analysis --zone us-west2-a'.
The correct command is gcloud dataproc clusters create followed by the name of the cluster and the a --zone parameter.
bq is the command-line tool for BigQuery, not Dataproc. Other combination of gcloud command missing a verb or command

30) Which of the following file formats is not supported when importing data into BigQuery?
Answer: YAML.
YAML is not a file storage format; it used for specifying configuration data.


NEW SECTION - Cloud Security & Networking

1) You want all your network traffic to route over the Google network and not traverse the public Internet.
What level of network service should you choose?
Answer: Premium.
Premium is the network service level that routes all traffic over the Google network.
Standard tier may use the public Internet when routing traffic.
There are no service tiers called Google-only or non-Internet.

2) You have a website hosted on a Compute Engine VM. Users can access the website using the domain name you
provided. You do some maintenance work on the VM and stop the server and restart it. Now users cannot access the website.
No other changes have occurred on the subnet. What might be the cause of the problem?
Answer: You used an ephemeral instead of a static IP address. Stopping and starting a VM will release ephemeral IP addresses.
Use a static IP address to have the same IP address across reboots. Rebooting a VM does not change a DNS record.
If you had enough addresses to get an address when you first started the VM and you then released that IP address,
there should be at least one IP address assuming no other devices are added to the subnet.

3) You are using Cloud Console to create a VPN. You want to configure the GCP end of the VPN. What section of
the Create VPN form would you use?
Answer: Google Compute Engine VPN.
The Google Compute Engine VPN is where you specify information about the Google Cloud end of the VPN connection,
so it is correct.
You specify name, description, network, region, and IP address.

4) A health check is used to check what resources?
Answer: VMs
Health checks monitor the health of VMs used with load balancers.
Near line storage is a type of Cloud Storage, storage devices or buckets are not health checked.

5) What record type is used to specify the IPv4 address of a domain?
Answer: A.
a) The A record is used to map a domain name to an IPv4 address.
b) AAAA record is used for IPv6 addresses.
c) NS is a name server record.
d) SOA is a start of authority record.

6) When setting up a network in GCP, your network the resources in it are treated as what?
Answer: Virtual private cloud.
When you create a network, it is treated as a virtual private cloud.
Resources are added to the VPC and are not accessible outside the VPC unless you explicitly configure them to be.
A subdomain is related to web domains and not related to GPC network configuration.
Clusters, such as Kubernetes clusters, may be in your network, but are not a characteristic of the network.

7) A VM instance is trying to read from a Cloud Storage bucket. Reading the bucket is allowed by IAM roles
granted to the service account of the VM. Reading buckets is denied by the scopes assigned to the VM. What will happen
if the VM tries to read from the bucket?
Answer: The read will not execute because both scopes and IAM roles are applied to determine what operations can be performed.
Both scopes and IAM roles assigned to service accounts must allow an operation for it to succeed.
Note: Access controls do not affect the flow of control in applications unless explicitly coded for that.
Also, the most permissive permission is not used.

8) The gcloud command to create a custom role is what?
Answer: 'gcloud iam roles create'
The correct answer is 'gcloud iam roles create'.

9) The CEO of your startup just read a news report about a company that was attacked by something called
cache poisoning. The CEO wants to implement additional security measures to reduce the risk of DNS spoofing and cache
poisoning. What would you recommend?
Answer: Using DNSSEC.
DNSSEC is a secure protocol designed to prevent spoofing and cache poisoning.
SOA and CNAME records contain data about the DNS record; they are not an additional security measure.
Deleting a CNAME record does not improve security.

10) What command is used to create a network load balancer at the command line?
Answer: 'gcloud compute forwarding-rules create'
The correct answer, is 'gcloud compute forwarding-rules create'.

11) You want to create a VPN using Cloud Console. What section of Cloud Console should you use?
Answer: Hybrid Connectivity.
The VPC create option is available in the Hybrid Connectivity section, so it is correct.
Compute Engine, App Engine, and IAM & Admin do not have features related to VPNs.

12) What launch stages are available when creating custom roles?
Answer: Alpha, beta, general availability, and disabled.
The four launch stages available are alpha, beta, general availability, and disabled.

13) A developer intern is confused about what roles are used for. You describe IAM roles as a collection of what?
Answer: Permissions.
Roles are used to group permissions that can then be assigned to identities, roles do not have identities,
but identities can be granted roles. Roles do not use access control lists. Roles do not include audit logs.
Logs are collected and managed by Stackdriver Logging.

14) When you navigate to IAM & Admin in Cloud Console, what appears in the main body of the page?
Answer: Members and roles assigned.
Members and their roles are listed.

15) A team is setting up a web service for internal use. They want to use the same IP address for the
foreseeable future. What type of IP address would you assign?
Answer: Static.
Static addresses are assigned until they are released.
Internal and External addresses determine whether traffic is routed into and out of the subnet.
External addresses can have traffic reach them from the Internet; internal addresses cannot.
Ephemeral addresses are released when a VM shuts down or is deleted.

16) What gcloud command would you use to assign a service account when creating a VM?
Answer: 'gcloud compute instance create [INSTANCE_NAME] --service-account [SERVICE_ACCOUNT_EMAIL]'
You can assign a service account when creating a VM using the create command.
Note: there is no create-service-account or define-service-account or instances-service-account command.

17) Where do you specify the ports on a TCP Proxy load balancer that should have their traffic forwarded?
Answer: Frontend.
You specify ports to forward when configuring the frontend. The backend is where you configure how traffic is routed to VMs.
Network Services is a high-level area of the console. VPCs are not where you specify load balancer configurations.

18) VPCs are [WHAT] resources.
Answer: Global.
Google operates a global network, and VPCs are resources that can span that global network.

19) You want to limit traffic to a set of instances. You decide to set a specific network tag on each instance.
What part of a firewall rule can reference the network tag to determine the set of instances affected by the rule?
Answer: Target.
The target can be all instances in a network, instances with particular network tags, or instances using a specific
service account. Action is either allow or deny. Priority determines which of all the matching rules is applied.
Direction specifies whether the rule is applied to incoming or outgoing traffic.

20) You are deploying a new custom application and want to delegate some administration tasks to DevOps engineers.
They do not need all the privileges of a full application administrator, but they do need a subset of those privileges.
What kind of role should you use to grant those privileges?
Answer: custom.
Primitive roles only include the Owner, Editor, and View permissions. Predefined roles are designed for GCP products
and services, like App Engine and BigQuery. For a custom application, you can create sets of privileges that give the
user with that role as much permission as needed but not more.

21) An application development team is deploying a set of specialized service endpoints and wants to limit
traffic so that only traffic going to one of the endpoints is allowed through by firewall rules.
The service endpoints will accept any UDP traffic and each endpoint will use a port in the range of 20000–30000.
Which of the following commands would you use?
Answer: 'gcloud compute firewall-rules create fwr1 --allow=udp:20000-30000 --direction=ingress'.
The rule in "gcloud compute firewall-rules create fwr1 --allow=udp:20000-30000 --direction=ingress" uses the
correct gcloud command and specifies the allow and direction parameters.

22) What role gives users full control over Compute Engine instances?
Answer: Compute Admin role.
Compute Engine Admin Role is the role that gives users complete control over instances.
Compute Engine Security Admin gives users the privileges to create, modify, and delete SSL certificates
and firewall rules.

23) What part of a firewall rule determines whether a rule applies to incoming or outgoing traffic?
Answer: Direction.
Direction specifies whether the rule is applied to incoming or outgoing traffic, which makes it the right answer.
Action is either allow or deny. Target specifies the set of instances that the rule applies to.
Priority determines which of all matching rules is applied.

24) Which regional load balancer allows for load balancing based on IP protocol, address, and port?
Answer: Network TCP/UDP.
Network TCP/UDP enables balancing based on IP protocol, address, and port.

25) What are the options for setting scopes in a VM?
Answer: Allow Default Access, Allow Full Access, and Set Access for Each API.
The options for setting scopes are: Allow Default Access, Allow Full Access, and Set Access For Each API.

26) What options are available for filtering log messages when viewing audit logs?
Answer: Resource, type of log, log level, and period time only.
Logs can be filtered by resource, type of logs, log level, and period of time only.

27) What two components need to be configured when creating a TCP Proxy load balancer?
Answer: Frontend and backend.
TCP Proxy load balancers require you to configure both the frontend and backend.
Forwarding rules are the one component specified with network load balancing.

28) You are designing an application that uses a series of services to transform data from its original form
into a format suitable for use in a data warehouse. Your transformation application will write to the message queue as
it processes each input file. You don’t want to give users permission to write to the message queue.
You could allow the application to write to the message queue by using which of the following?
Answer: Service account.
Service accounts are designed to give applications or VMs permission to perform tasks.
Billing accounts are for associating charges with a payment method.
Folders are part of resource hierarchies and have nothing to do with enabling an application to perform a task.

29) You are configuring a load balancer and want to implement private load balancing. Which option would you select?
Answer: Only Between My VMs.
In the console there is an option to select between 'From Internet To My VMs' and 'Only Between My VMs'.
This is the option to indicate private or public.

30) A team with four members needs you to set up a project that needs only general permissions for all resources.
You are granting each person a primitive role for different levels of access, depending on their responsibilities in the project.
Which of the following are not included as primitive roles in Google Cloud Platform?
Answer: publisher.
Publisher is not a primitive role. Owner, Editor, and Viewer are the three primitive privileges in GCP.

31) A DevOps engineer is confused about the purpose of scopes. Scopes are access controls that are applied
to what kind of resources?
Answer: VM instances.
Scopes are permissions granted to VM instances. Scopes in combination with IAM roles assigned to service accounts
assigned to the VM instance determine what operations the VM instance can perform.
Note: scopes do not apply to storage resources or to subnets.

32) You want to have GCP manage cryptographic keys, so you’ve decided to use Cloud Key Management Services.
Before you can start creating cryptographic keys, you must?
Answer: Enable Google Cloud Key Management Service (KMS) API and set up billing.
Enabling the Google Cloud KMS API and setting up billing are steps common to using GCP services.

33) You want the router on a tunnel you are creating to learn routes from all GCP regions on the network.
What feature of GCP routing would you enable?
Answer: Global dynamic routing.
Global dynamic routing is used to learn all routes on a network. Regional routing would learn only routes in a region.

34) The command to create a VPC from the command line is?
Answer: 'gcloud compute networks create'.
The correct answer is 'gcloud compute networks create'. 'networks vpc' is not a correct part of the command.
'gsutil' is the command used to work with Cloud Storage. There is no such thing as network create.

35) Which load balancers provide global load balancing?
Answer: HTTP(S), SSL Proxy, and TCP Proxy.
The three global load balancers are HTTP(S), SSL Proxy, and TCP Proxy. Internal TCP/UD is a regional load balancer.

36) An app for a finance company needs access to a database and a Cloud Storage bucket.
There is no predefined role that grants all the needed permissions without granting some permissions that are not needed.
You decide to create a custom role. When defining custom roles, you should follow which of the following principles?
Answer: Least privilege.
Users should have only the privileges that are needed to carry out their duties, this is the principle of least privilege.
Rotation of duties is another security principle related to having different people perform a task at
a different times. 
Defense in depth is the practice of using multiple security controls to protect the same asset.
Least Principle is not a real security principal. This make "Least privilege" the correct answer

37) During an incident that has caused an application to fail, you suspect some resource may not have
appropriate roles granted. The command to list roles granted to a resource is?
Answer: 'gcloud iam list-grantable-roles'.
gcloud is the command-line tool and for working with IAM we need to add "iam", and list-grantable-roles
is the correct command.

38) _____________________ routes traffic only over Google’s global network.
Answer: Premium Tier.
Premium Tier routes traffic only over Google’s global network.
When using the Standard Tier, your data is subject to the reliability of the public Internet.

39) You are using gcloud to create a firewall rule. Which command would you use?
Answer: 'gcloud compute firewall-rules create'.
The product you are working with is compute and the resource you are creating is a firewall rule, so
'gcloud compute firewall-rules create' is correct.

40) What command is used to create a DNS zone in the command line?
Answer: 'gcloud beta dns managed-zones create'.
The correct answer, is 'gcloud beta dns managed-zones create'. 
gsutil command which is used to work with Cloud Storage.

41) You have been asked to create a network with 1,000 IP addresses. In the interest of minimizing unused
IP addresses, which CIDR suffix would you use to create a network with at least 1,000 addresses but no more than necessary?
Answer: '/22'.
The '/22' suffix produces 1,022 usable IP addresses.

42) You have created a subnet called sn1 using 192.168.0.0. You want it to have 14 addresses.
What prefix length would you use?
Answer: '28'.
The prefix length specifies the length in bits of the subnet mask. The remaining bits of the IP address are used for
device addresses. Since there are 32 bits in an IP address, you subtract the length of the mask to get the number of bits
used to represent the address.

43) You are meeting with an auditor to discuss security practices in the cloud. The auditor asks how you
implement several best practices. You describe how IAM predefined roles help to implement which security best practice(s)?
Answer: Least privileges & Separation of duties.
Predefined roles help implement both least privilege and separation of duties.
Predefined roles do not implement defense in depth by themselves but could be used with other security controls to
implement defense in depth.

44) ___________ uses the public Internet network to transfer data between Google data centers?
Answer: Standard Tier.
When using the Standard Tier, your data is subject to the reliability of the public Internet.
Premium Tier routes traffic only over Google’s global network.
Rest all not Valid Options for networking tier in GCP.

45) You are tasked with mapping the authentication and authorization policies of your on-premises applications
to GCP’s authentication and authorization mechanisms. The GCP documentation states that an identity must be authenticated
in order to grant privileges to that identity. What does the term identity refer to?
Answer: User.
Identities are abstractions of users. They can also represent characteristics of processes that run on behalf of a human
user or a VM in the GCP. Identities are not related to VM IDs. Roles are collections of privileges that can be granted to
identities.

46) At what levels of the resource hierarchy can a shared VPC be created?
Answer: Organization and folders.
Shared VPCs can be created at the organization or folder level of the resource hierarchy. Shared VPCs are not created at
the resource or project levels. Shared VPCs are not applied at subnets, which are resources in the resource hierarchy.

47) You want to implement interproject communication between VPCs. Which feature of VPCs would you use to implement this?
Answer: VPC peering.
VPC is used for interproject communications.

48) Why are primitive roles classified in a category in addition to IAM?
Answer: They were created before IAM.
Primitive roles were created before IAM and provided coarse-grained access controls. IAM is the newer form of access control.

49) You have been asked to set up network security in a virtual private cloud. Your company wants to have
multiple subnetworks and limit traffic between the subnetworks. Which network security control would you use to control
the flow of traffic between subnets?
Answer: Firewall.
Firewalls in Google Cloud Platform (GCP) are software-defined network controls that limit the flow of traffic
into and out of a network or subnetwork.
Routers are used to move traffic to appropriate destinations on the network.
Identity access management is used for authenticating and authorizing users; it is not relevant to network controls
between subnetworks.
IP address tables are not a security control.

50) Your company has just started using GCP, and executives want to have a dedicated connection from your data
center to the GCP to allow for large data transfers. Which networking service would you recommend?
Answer: Google Cloud Interconnect - Dedicated.
Google Cloud Interconnect – Dedicated is the only option for a dedicated connection between a customer’s
data center and a Google data center.

