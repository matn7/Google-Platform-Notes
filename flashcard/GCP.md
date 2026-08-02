# Google Cloud Platform

## NEW SECTION - Mine Flashcards

### 1) Service Level Indicator (SLI)?

**Answer:** Quantitative measure of a service aspect. Categories: availability, latency, throughput, durability,
correctness (error rate). Usually aggregated over a time window (e.g. 1 minute).

### 2) Easiest authentication/authorization for apps on App Engine, Cloud Run and GKE?

**Answer:** Identity-Aware Proxy (IAP).

### 3) GKE microservices need common Cloud Logging and Cloud Monitoring permissions?

**Answer:** Node Pool Service Account.

### 4) Recommended Google Cloud CI/CD service?

**Answer:** Cloud Build.

### 5) Designing APIs with gRPC?

**Answer:**

- Based on Remote Procedure Call (RPC).
- Example: `createTodo(username, description) -> todoId`.
- Addressable entities are procedures.
- **Advantages:**
    - Hides HTTP details (uses HTTP/2).
    - Generates client stubs and server skeletons.
    - Binary Protocol Buffers provide high performance.
    - Efficient connection management with HTTP/2.
- **Disadvantages:**
    - REST APIs are easier to consume.
    - Requires gRPC libraries/frameworks.

### 6) In-memory databases / caches?

**Answer:** Cloud Memorystore. Recommended for microsecond-latency workloads.

### 7) NoSQL databases?

**Answer:**

- Cloud Firestore (Datastore)
- Cloud Bigtable

Use for schema-less applications.

**Firestore**

- Serverless transactional document database.
- Mobile/web applications.
- Small–medium databases (up to a few TB).

**Bigtable**

- Large databases (10 TB–PB).
- Streaming (IoT), operational and analytical workloads.
- Not serverless.

### 8) Relational OLAP database?

**Answer:** BigQuery. Columnar storage with predefined schema for analytics and data warehousing.

### 9) Relational OLTP databases?

**Answer:**

- Cloud SQL
- Cloud Spanner

Transactional workloads requiring predefined schema and row storage.

**Cloud SQL**

- MySQL
- PostgreSQL
- SQL Server

**Cloud Spanner**

- Global scale
- Horizontal scaling
- 99.999% availability

### 10) Cloud SQL Auth Proxy?

**Answer:**
Simplifies secure client connections to Cloud SQL.

- TLS 1.2 encryption (128-bit AES)
- No certificate management
- Supports private/public IPs
- Can connect from local environments
- Requires proxy on client machine
- Automatically supported by App Engine Standard and Cloud Run.

### 11) Cloud SQL High Availability?

**Answer:**

- Configure HA within one region.
- Primary + Secondary instance.
- Synchronous replication.
- Automatic failover after zonal failure.
- Failback is **not** automatic.
- HA instance **cannot** be used as a Read Replica.

### 12) Cloud Storage: Object must not be modified for 2 years?

**Answer:** Configure and lock a Data Retention Policy.

### 13) Cloud Storage: Customer manages encryption keys?

**Answer:** Customer-managed encryption keys (Cloud KMS).

### 14) Cloud Storage lifecycle optimization?

**Answer:**

- Initial storage: Standard
- After 30 days → Archive
- Delete after 4 years

### 15) Consuming Google Cloud APIs?

**Answer:**
Authentication order:

1. `GOOGLE_APPLICATION_CREDENTIALS`
2. Attached Service Account
3. Otherwise → error

Recommended: Attach Service Accounts to Compute Engine, GKE, App Engine, Cloud Run and Cloud Functions.

### 16) Cloud Spanner transaction mode avoiding table locks?

**Answer:** Partitioned DML.

### 17) Connect to Cloud SQL from serverless using Private IP?

**Answer:** Serverless VPC Access.

### 18) Connect VM inside VPC to Cloud SQL using Private IP?

**Answer:** Private Service Connection.

### 19) Connect to Cloud SQL from GCE/GKE using Public IP?

**Answer:** Cloud SQL Auth Proxy.

### 20) Recommended database for modern mobile/web apps?

**Answer:** Firestore (Native mode).

### 21) Cloud Dataflow?

**Answer:** Export Bigtable data to Cloud Storage (Avro or Parquet).

### 22) Anthos Service Mesh use cases?

**Answer:**

- Canary deployments
- mTLS between pods
- SLO monitoring

### 23) Overall security view of a project?

**Answer:** Security Command Center.

### 24) Detect runtime container threats?

**Answer:** Container Threat Detection.

### 25) Three pillars of observability?

**Answer:**

- Logs
- Metrics
- Traces

### 26) Recommended streaming pipeline?

**Answer:** Pub/Sub → Dataflow → BigQuery.

### 27) Schedule asynchronous tasks?

**Answer:** Cloud Tasks.

### 28) Real-time queries over massive streaming time-series data?

**Answer:** Pub/Sub → Dataflow → Bigtable.

### 29) Dashboards for BigQuery?

**Answer:** Data Studio.

### 30) Clean imported data?

**Answer:** Cloud Dataprep.

### 31) Docker image best practices?

**Answer:**

- Prefer lightweight images (Alpine)
- Copy only required files
- Place rarely-changing layers first for cache reuse

### 32) Dockerfile instructions?

**Answer:**

- FROM → base image
- WORKDIR → working directory
- RUN → execute commands
- EXPOSE → container port
- COPY → copy files
- CMD → default command

### 33) Recommended for advanced cross-cloud deployments?

**Answer:** Spinnaker.

### 34) Deployment strategy for feature testing?

**Answer:** A/B Testing.

### 35) Zero-downtime deployment without extra infrastructure?

**Answer:** Canary Deployment.

### 36) GKE releases: Cloud Build vs Spinnaker?

**Answer:**

- Cloud Build → build images + simple deployments
- Spinnaker → advanced automated multi-cloud deployments

### 37) GKE Canary deployment?

**Answer:**
Deploy new version and split traffic using Istio / Anthos Service Mesh or Spinnaker.

### 38) GKE Blue-Green deployment?

**Answer:**
Deploy new version and switch traffic using Ingress/Service or Spinnaker.

### 39) GKE Rolling Update / Recreate?

**Answer:** Configure Deployment strategy.

### 40) App Engine releases?

**Answer:**

- Deploy: `gcloud app deploy`
- Deploy without traffic: `--no-promote`
- Switch traffic: `gcloud app services set-traffic`
- Gradual migration: `--migrate`
- A/B testing: split traffic between versions

### 41) MIG Blue-Green deployment?

**Answer:** Create a new Managed Instance Group and update Load Balancer backends.

### 42) Deploy only trusted container images?

**Answer:** Binary Authorization.

### 43) Scan container images?

**Answer:** Container Scanning API.

### 44) Detect crypto mining on VMs?

**Answer:** Anomaly Detection.

### 45) Protect applications against DDoS and common attacks?

**Answer:** Cloud Armor.

### 46) Scan App Engine apps for XSS and common vulnerabilities?

**Answer:** Web Security Scanner.

### 47) Find storage buckets containing sensitive data?

**Answer:** Cloud DLP (Data Loss Prevention).

### 48) Find performance bottlenecks?

**Answer:** Cloud Profiler.

### 49) View logs for a specific request?

**Answer:** Cloud Logging.

### 50) Cloud Firestore best practices?

**Answer:**

- Flexible document database.
- Good for user profiles and similar data.
- Store object references (e.g. Cloud Storage image URLs).
- Design keys/indexes carefully.
- Avoid monotonically increasing keys.
- Place DB close to users and compute.
- Prefer batch operations.
- Soft limits:
    - 1 write/sec/document
    - 1M concurrent connections/database
- Gradually increase traffic (500/50/5 rule):
    - Start ≤500 ops/sec
    - Increase by 50% every 5 minutes

### 51) Debug production by adding log points or snapshots?

**Answer:** Cloud Debugger.

### 52) Trace a request across multiple microservices?

**Answer:** Cloud Trace.

### 53) Identify prominent exceptions for a microservice?

**Answer:** Error Reporting.

### 54) Dashboard showing occurrences of a substring in logs?

**Answer:** Create a log-based counter metric.

### 55) Alert when an application becomes unavailable?

**Answer:** Configure a Cloud Monitoring Uptime Check.

### 56) Cloud SQL best practices?

**Answer:**

- Prefer Cloud SQL Auth Proxy.
- Use connection pooling and exponential backoff.
- Keep transactions short.
- Prefer Private IP.
- Understand scaling:
    - HA = availability (regional primary + standby).
    - Read Replicas = offload reads only.
    - Read Replicas do **not** improve availability.
- Prefer multiple small instances over one very large instance.

### 57) Connecting to Cloud SQL?

**Answer:**
Prefer **Private IP**.

Serverless (App Engine, Cloud Functions, Cloud Run):

- Private IP → Serverless VPC Access.
- Public IP → Cloud SQL Auth Proxy.

GCE / GKE:

- Public or Private IP → Cloud SQL Auth Proxy.

Without Auth Proxy, use self-managed SSL/TLS certificates.

### 58) IAM best practices?

**Answer:**

- Principle of Least Privilege.
- Separation of Duties.
- Monitor continuously.
- Prefer Groups over individual users.

### 59) Allow VMs to access Google APIs via Private IP?

**Answer:** Enable Private Google Access.

### 60) Cloud VPN?

**Answer:**
Connect on-premises to GCP over IPsec.

Traffic:

- Public Internet
- Encrypted (IKE)

Types:

- HA VPN → Dynamic routing (BGP)
- Classic VPN → Static or Dynamic routing (BGP)

### 61) Direct Peering?

**Answer:**
Direct connection from on-premises to Google network.

- Not a GCP service.
- Bypasses public Internet.
- Generally prefer Cloud Interconnect or Cloud VPN.

### 62) Cloud Interconnect?

**Answer:**
Dedicated private connection between on-premises and VPC.

Benefits:

- High bandwidth
- High availability
- Private network

Types:

- Dedicated Interconnect (10/100 Gbps)
- Partner Interconnect (50 Mbps–10 Gbps)

Recommended for high-bandwidth workloads.

### 63) Connect VPCs across different organizations?

**Answer:** VPC Peering.

### 64) Kubernetes-native CI/CD pipeline?

**Answer:** Tekton Pipelines.

### 65) Share files between Cloud Build steps?

**Answer:** `/workspace`.

### 66) SRE best practices?

**Answer:**

- Handle excess load (load shedding, QoS reduction).
- Prevent cascading failures.
- Perform:
    - Penetration testing
    - Load testing
    - Resilience testing
    - Chaos testing
    - Network failure testing
    - DIRT (Disaster Recovery Testing)

Resilience = acceptable behavior despite failures.

### 67) Error Budget?

**Answer:**
`100% - SLO`

Measures remaining reliability budget and balances reliability vs development speed.

### 68) Service Level Agreement (SLA)?

**Answer:**
SLO + contractual consequences.

Internal SLOs should be stricter than external SLAs.

### 69) GKE microservices requiring individual permissions?

**Answer:** Workload Identity.

### 70) Kubernetes security best practices?

**Answer:**

- Enable Node Auto Upgrade.
- Use Shielded GKE Nodes.
- Enable Workload Identity.
- Use Namespaces + RBAC.
- Restrict Pod traffic with Network Policies.
- Store secrets in Kubernetes Secrets or Secret Manager.
- HashiCorp Vault for multi-cloud secrets.

### 71) Important predefined GKE IAM roles?

**Answer:**

- Kubernetes Engine Admin
- Kubernetes Engine Cluster Admin
- Kubernetes Engine Cluster Viewer
- Kubernetes Engine Developer
- Kubernetes Engine Viewer

### 72) Kubernetes RBAC?

**Answer:**
Fine-grained authorization at cluster and namespace level.

Controls access to resources such as:

- Deployments
- Pods
- Services
- Secrets
- ConfigMaps

### 73) Authorization for Kubernetes workloads?

**Answer:**
Options:

- Node Service Account
- Kubernetes Secrets
- Workload Identity (**recommended**)

Benefits:

- Least privilege
- No secret management

### 74) Assign a Service Account to a Pod?

**Answer:**

- Kubernetes Secrets
- Workload Identity (**recommended**)

### 75) Default Service Account on GKE nodes?

**Answer:** Compute Engine Default Service Account.

### 76) Is a Service Account an identity or resource?

**Answer:** Both.

- Identity → receives IAM roles.
- Resource → others can receive roles on it.

### 77) Service-to-service authentication?

**Answer:** OpenID Connect (OIDC) ID Tokens.

### 78) IAM concepts?

**Answer:**

- Member → Who?
- Role → Permissions.
- Policy → Assigns Roles to Members with optional Conditions.

Remember:

- Permissions belong to Roles.
- Members receive permissions through Roles.
- Members may have multiple Roles.

### 79) IAM identities?

**Answer:**

- Google User
- External User
- Group
- GCP Application
- On-prem Application
- Unauthenticated User

### 80) Set active project?

**Answer:**

```bash
gcloud config set project PROJECT_NAME
```

### 81) Cloud Storage lifecycle transitions?

**Answer:**
Allowed:

- Standard / Regional / Multi-Regional
  → Nearline
  → Coldline
  → Archive

Nearline → Coldline → Archive

### 82) Storage class common features?

**Answer:**

- High durability
- Low latency
- Unlimited autoscaling
- No minimum object size
- Same API across classes

Archive has no committed SLA.

### 83) Cloud KMS key options?

**Answer:**

- Google-managed
- Customer-managed (KMS)
- Customer-supplied

### 84) Multi-cloud Kubernetes platform?

**Answer:** Anthos.

### 85) Cloud Functions cold start?

**Answer:**
New instance initialization increases latency.

Mitigation:
Configure minimum instances (higher cost).

### 86) Cloud Functions event sources?

**Answer:**

- Cloud Storage
- Pub/Sub
- HTTP
- Firebase
- Firestore
- Cloud Logging

### 87) Kubernetes service-to-service communication?

**Answer:** Kubernetes Service Discovery.

### 88) Divide one Kubernetes cluster between teams?

**Answer:** Separate Namespaces.

### 89) Recommended external access for Kubernetes services?

**Answer:** Ingress.

### 90) Default Kubernetes deployment strategy?

**Answer:** RollingUpdate.

### 91) GKE CPU reservation?

**Answer:**
Reserved for control plane:

- Core 1 → 6%
- Core 2 → 1%
- Core 3–4 → 0.5%
- Remaining → 0.25%

### 92) PodUnschedulable?

**Answer:**
Pod cannot be scheduled.

Check cluster resources.

### 93) CrashLoopBackOff?

**Answer:**
Container repeatedly crashes.

Useful commands:

```bash
kubectl logs POD_NAME
kubectl exec -it POD_NAME -- ls
```

### 94) Troubleshoot Kubernetes Pods?

**Answer:**

```bash
kubectl describe pod POD_NAME
```

Shows current state and recent events.

### 95) ImagePullBackOff / ErrImagePull?

**Answer:**
Check:

- Image name
- Image tag
- Node Service Account permissions to Container Registry

### 96) Global, Regional and Zonal resources?

**Answer:**

**Global**

- Images
- Snapshots
- Instance Templates

**Regional**

- Regional MIG
- Regional Persistent Disk

**Zonal**

- VM Instances
- Zonal MIG
- Persistent Disk

A disk can attach only to VMs in the same zone.

### 97) Highest IOPS with disposable data?

**Answer:** Local SSD.

### 98) High-performance shared filesystem?

**Answer:** Filestore.

### 99) Backup VM configuration and attached disks?

**Answer:** Machine Image.

### 100) Simplify launching hardened VMs?

**Answer:** Custom Image.

### 101) Persistent network block storage in GCP?

**Answer:** Persistent Disk (PD).

### 102) What contains everything needed to recreate a VM?

**Answer:** Machine Image.

Includes:

- VM configuration
- Metadata
- Boot disk
- Attached Persistent Disks

### 103) Cloud Storage?

**Answer:**
Fully managed object storage.

Features:

- Serverless, autoscaling, virtually unlimited capacity
- Key-value object storage (whole-object updates only)
- Object-level access control
- REST API, `gsutil`, client libraries
- Stores any file type

Typical use cases:

- Media & archives
- Application packages
- Logs
- Backups
- Migration staging

### 104) Cloud Storage Objects & Buckets?

**Answer:**

**Buckets**

- Globally unique
- 3–63 chars
- Lowercase letters, numbers, `-`, `_`, `.`
- Cannot start with `goog` or contain "google"
- Belong to one project
- Unlimited objects

**Objects**

- Unique key within bucket
- Max size: 5 TB

### 105) IAM Roles?

**Answer:**

**Basic Roles**

- Owner
- Editor
- Viewer

Legacy roles—avoid in production.

**Predefined Roles**
Google-managed fine-grained roles.
Examples:

- Storage Admin
- Storage Object Admin
- Storage Object Viewer
- Storage Object Creator

**Custom Roles**
User-defined when predefined roles aren't sufficient.

### 106) Cloud IAM?

**Answer:**

Provides:

- Authentication (Who?)
- Authorization (What?)

Supports:

- Users
- Groups
- Service Accounts
- External identities

Allows permissions based on:

- Resource
- Action
- IP
- Time

### 107) Typical cloud identity management?

**Answer:**

Resources require identities.

IAM determines:

- Who can access
- Which resources
- Which actions are allowed

### 108) Cloud Storage Encryption?

**Answer:**

Server-side (recommended):

- Google-managed keys
- Customer-managed keys (Cloud KMS)

Optional:

- Client-side encryption before upload

### 109) Object Versioning?

**Answer:**

Prevents accidental deletion.

Features:

- Enabled per bucket
- Live + non-current versions
- Versions identified by generation number
- Delete old versions to reduce costs

### 110) Cloud Filestore?

**Answer:**

Managed shared NFS storage.

Features:

- NFS v3
- HDD & SSD
- Up to 320 TB
- Up to 16 GB/s throughput
- Up to 480K IOPS

Use cases:

- Shared files
- Media workflows
- Client management

### 111) Automatically delete scheduled snapshots?

**Answer:**
Configure retention in Snapshot Scheduling.

### 112) Improve Persistent Disk performance?

**Answer:**

- Increase PD size
- Add more PDs
- Increase VM vCPUs

### 113) Improve Persistent Disk durability?

**Answer:**
Use Regional Persistent Disks (replicated across two zones).

### 114) Hourly Persistent Disk backups?

**Answer:**
Configure scheduled snapshots.

### 115) Cloud Storage access control?

**Answer:**

**Uniform (recommended)**

- IAM only
- Bucket-level permissions

**Fine-grained**

- IAM + ACLs
- Object-level permissions

Use:

- Uniform → same access for all objects
- Fine-grained → object-specific permissions

### 116) ACL (Access Control Lists)?

**Answer:**

ACLs provide object-level permissions.

Difference:

IAM

- Applies to entire bucket

ACL

- Individual objects

Access is granted if permitted by either IAM or ACL.

### 117) VM in Project-A accessing bucket in Project-B?

**Answer:**
Grant Project-A Service Account **Storage Object Viewer** on the bucket in Project-B.

### 118) Is a Service Account an identity or resource?

**Answer:**
Both.

- Identity → receives roles.
- Resource → others can receive roles on it.

### 119) VM application publishing to Pub/Sub?

**Answer:**
Assign a Service Account with required Pub/Sub permissions.

### 120) VM application accessing Cloud Storage?

**Answer:**
Assign a Service Account with required Storage permissions.

### 121) Service Account from on-prem to Google APIs (short-lived)?

**Answer:**

Recommended credential types:

- OAuth2 Access Token
- OpenID Connect ID Token
- JWT

Benefits:

- Short-lived credentials
- No long-lived keys
- Lower security risk

### 122) Service Account from on-prem (long-lived)?

**Answer:**

Steps:

1. Create Service Account.
2. Create user-managed key.
3. Download key securely.
4. Set:
   `GOOGLE_APPLICATION_CREDENTIALS`
5. Use Google Client Libraries (ADC).

### 123) VM → Cloud Storage using Service Account?

**Answer:**

Steps:

1. Create Service Account.
2. Grant required roles.
3. Attach Service Account to VM.

Benefits:

- Google-managed keys
- Automatic rotation
- No credential files

Do not delete Service Accounts used by running VMs.

### 124) Service Accounts?

**Answer:**

Recommended for applications accessing GCP.

Properties:

- Email identity
- No password
- RSA key pair
- Cannot log in interactively

Types:

**Default**

- Auto-created
- Usually Editor role
- Not recommended

**User-managed**

- Recommended
- Fine-grained permissions

**Google-managed**

- Used internally by GCP

### 125) Reporting applications overload database?

**Answer:**

Options:

- Vertical scaling
- Database cluster
- Read Replicas (**recommended** for read-only workloads)

### 126) Read Replica?

**Answer:**

Purpose:

- Offload read traffic
- Reporting & analytics

Benefits:

- Reduce primary load
- Multiple regions supported
- Snapshots from replicas
- Some databases allow promotion to primary

### 127) Relational OLTP summary?

**Answer:**

Cloud SQL

- MySQL
- PostgreSQL
- SQL Server

Cloud Spanner

- Global scale
- Horizontal scaling
- 99.999% availability

### 128) Consistency?

**Answer:**

**Strong consistency**

- Synchronous replication
- Higher latency

**Eventual consistency**

- Asynchronous replication
- Better scalability
- Temporary stale reads

Example:
Social media posts.

**Read-after-write**

- Inserts immediately visible
- Updates may be eventually consistent

### 129) IAM key concepts?

**Answer:**

Member → Who?

Role → Permissions.

Policy → Assigns Roles to Members with optional Conditions.

Remember:

- Permissions belong to Roles.
- Members receive permissions through Roles.
- Multiple Roles per Member.

### 130) IAM Policy?

**Answer:**

Policy consists of Bindings.

Binding:

- Role
- Members

Member prefixes include:

- user
- serviceAccount
- group
- domain

### 131) VM recovery after crash?

**Answer:**

Snapshots every 48 hours.

Recovery time: 45 minutes.

- **RTO:** 45 minutes
- **RPO:** 48 hours

### 132) RTO/RPO: minimal downtime & minimal data loss?

**Answer:**
Hot Standby.

- Automatic synchronization
- Automatic failover

### 133) Low data loss, moderate downtime?

**Answer:**
Warm Standby.

- Automatic synchronization
- Minimal infrastructure
- Scale after failure

### 134) Critical data, downtime acceptable?

**Answer:**
Use:

- Regular snapshots
- Transaction logs

Restore from both.

### 135) Data loss acceptable?

**Answer:**
Fail over to a completely new server.

### 136) Database for massive IoT streams?

**Answer:** Cloud Bigtable.

### 137) Database for huge time-series workloads?

**Answer:** Cloud Bigtable.

### 138) Analytics over petabytes?

**Answer:** BigQuery.

### 139) Database cache?

**Answer:** Cloud Memorystore.

### 140) Global transactional database?

**Answer:** Cloud Spanner.

### 141) Local transactional database?

**Answer:** Cloud SQL.

### 142) Startup with evolving schema?

**Answer:** Firestore / Datastore.

### 143) Small NoSQL database (~10 GB)?

**Answer:** Cloud Datastore.

### 144) In-memory cache?

**Answer:** Cloud Memorystore.

Microsecond latency.

### 145) NoSQL databases?

**Answer:**

**Firestore**

- Serverless
- Transactional document DB
- Mobile/web
- Few TB

**Bigtable**

- 10 TB–PB
- Streaming
- Analytics
- Operational workloads
- Not serverless

### 146) Relational OLAP summary?

**Answer:**
BigQuery.

- Columnar storage
- Predefined schema
- Analytics & data warehousing

### 147) Cloud Bigtable?

**Answer:**

Wide-column NoSQL database.

Features:

- PB scale
- Millions of TPS
- Low latency
- Single-row transactions
- Horizontal scaling
- HBase compatible
- Not serverless

Tools:

- `cbt`
- Dataflow export/import
- Java utilities

### 148) Wide-column database?

**Answer:**

Bigtable structure:

- Row key
- Column families
- Column qualifiers

Benefits:

- Petabyte scale
- Millisecond latency
- Millions TPS

Use cases:

- IoT
- Time series
- Financial data
- Real-time analytics

Export using Dataflow.

### 149) Memorystore?

**Answer:**

Managed Redis/Memcached.

Features:

- HA
- Replication
- Failover
- Monitoring

Use Memcached:

- Cache
- Sessions

Use Redis:

- Persistence
- Gaming
- Streams
- Leaderboards

### 150) Firewall ingress & egress?

**Answer:**

**Ingress**

- Incoming traffic
- Target: All VMs / Tags / Service Accounts
- Source: CIDR / Tags / Service Accounts

**Egress**

- Outgoing traffic
- Destination: CIDR

Rule options:

- Priority
- Allow / Deny
- Protocol
- Enable / Disable

### 151) Shared VPC?

**Answer:**

Allows multiple projects in the same organization to share one VPC.

Structure:

- **Host Project** → owns the shared VPC.
- **Service Projects** → use the shared VPC.

Benefits:

- Centralized network management.
- Separation of responsibilities:
    - Network admins manage Host Project.
    - Application teams manage Service Projects.

### 152) VPC Peering?

**Answer:**

Connects VPCs:

- Same project
- Different projects
- Different organizations

Features:

- Internal IP communication only
- Private Google network
- No Internet exposure
- No data transfer charges between peered VPCs

Remember:

- IAM/network administration is **not** shared.

### 153) Cloud VPN?

**Answer:**

Secure IPsec VPN between on-premises and GCP.

Traffic:

- Uses public Internet
- Encrypted with IKE

Types:

**HA VPN**

- 99.99% SLA
- Two external IPs
- BGP only

**Classic VPN**

- 99.9% SLA
- One external IP
- Static or dynamic routing

### 154) Cloud Interconnect?

**Answer:**

Private high-speed connection between on-premises and VPC.

Benefits:

- High bandwidth
- High availability
- Lower egress costs
- Private access to supported Google APIs

Types:

- Dedicated (10/100 Gbps)
- Partner (50 Mbps–10 Gbps)

Use for high-bandwidth workloads.
Otherwise prefer Cloud VPN.

### 155) Direct Peering?

**Answer:**

Direct connection to Google's network.

- Not a managed GCP service.
- Prefer Cloud Interconnect or Cloud VPN.

### 156) Datastore vs Firestore?

**Answer:**

**Datastore**

- Serverless NoSQL document DB
- Few TB
- Transactions
- Indexes
- GQL
- No joins or aggregates
- Export via gcloud

**Firestore**

- Successor to Datastore
- Strong consistency
- Mobile/Web SDKs
- Offline sync
- Native & Datastore modes

### 157) Google Cloud VPC?

**Answer:**

Private isolated network in GCP.

Features:

- Global resource
- Contains regional subnets
- Secure communication between cloud resources
- Isolated from other VPCs

Best practice:
Deploy compute, storage and databases inside a VPC.

### 158) Why VPC Subnets?

**Answer:**

Separate:

- Public resources
- Private resources

Benefits:

- Better security
- Regional distribution
- High availability

### 159) VPC Subnets?

**Answer:**

Public subnet:

- Internet accessible

Private subnet:

- No Internet access
- Reachable from public subnet

Each subnet belongs to one region.

### 160) Creating VPCs?

**Answer:**

Every project has a default VPC.

Modes:

**Auto**

- Subnets created automatically.

**Custom (recommended)**

- Full subnet/IP control.

Subnet options:

- Private Google Access
- Flow Logs

### 161) CIDR blocks?

**Answer:**

Defines an IP range.

Example:
`69.208.0.0/28`

- First 28 bits fixed
- Remaining 4 bits vary
- 16 addresses

### 162) Addresses in `69.208.0.0/26`?

**Answer:**

64 addresses.

Range:
`69.208.0.0 – 69.208.0.63`

### 163) Addresses in `69.208.0.0/30`?

**Answer:**

4 addresses.

Range:
`69.208.0.0 – 69.208.0.3`

### 164) Difference between `0.0.0.0/0` and `0.0.0.0/32`?

**Answer:**

`0.0.0.0/0`
→ All IPv4 addresses.

`0.0.0.0/32`
→ Single IP address.

### 165) Firewall Rules?

**Answer:**

Stateful firewall.

Defaults:

- Allow all egress
- Deny all ingress

Priority:

- 0 = highest
- 65535 = lowest

Default VPC also includes:

- allow-internal
- allow-ssh
- allow-rdp
- allow-icmp

### 166) System Event Logs?

**Answer:**

Automatically enabled.

Examples:

- Host maintenance
- VM preemption
- Automatic restart

### 167) Admin Activity Logs?

**Answer:**

Automatically enabled.

Logs configuration changes.

Examples:

- VM creation
- IAM changes
- Bucket modifications

### 168) Cloud Logging export use cases?

**Answer:**

**Troubleshooting**

- VM → Cloud Logging

**Analytics**

- Cloud Logging → BigQuery

**Long-term retention**

- Cloud Logging → Cloud Storage

Uses Log Router sinks.

### 169) Cloud Logging export?

**Answer:**

Export destinations:

- Cloud Storage
- BigQuery
- Pub/Sub

Configured using Log Router sinks.

Supports include/exclude filters.

### 170) Cloud Logging buckets?

**Answer:**

**_Required**

- Admin Activity
- System Events
- Access Transparency
- 400-day retention
- Free
- Immutable

**_Default**

- Other logs
- 30-day retention
- Configurable (1–3650 days)
- Can disable ingestion

### 171) Audit & Security Logs?

**Answer:**

Cloud Audit Logs:

- Admin Activity
- Data Access
- System Events
- Policy Denied

Access Transparency:

- Google personnel access
- Enterprise support only

### 172) Why Google Cloud VPC?

**Answer:**

Provides a secure private network similar to an on-premises data center.

Benefits:

- Resource isolation
- Secure communication
- Database protection
- Internet isolation

### 173) Pub/Sub message flow?

**Answer:**

Publisher
→ Topic
→ Subscription(s)
→ Subscriber

Delivery:

- Push
- Pull

Messages remain until acknowledged.

### 174) Cloud Monitoring?

**Answer:**

Monitors infrastructure using metrics.

Provides:

- Dashboards
- Alerts
- Workspaces

VM Monitoring Agent collects:

- CPU
- Disk
- Network
- Process metrics

### 175) Data Access Logs?

**Answer:**

Disabled by default.

Logs read operations.

Examples:

- List VMs
- Read Cloud Storage objects

### 176) Policy Denied Logs?

**Answer:**

Enabled by default.

Logs denied access caused by IAM or security policy.

### 177) Cloud Logging?

**Answer:**

Fully managed logging platform.

Features:

- Store
- Search
- Analyze
- Alert

Components:

- Logs Explorer
- Dashboards
- Log-based Metrics
- Log Router

### 178) Cloud Logging collection?

**Answer:**

Automatically collected from managed services.

VMs:

- Install Logging Agent (fluentd)

On-prem:

- BindPlane
- Cloud Logging API

### 179) Pub/Sub Topics & Subscriptions?

**Answer:**

Flow:

1. Create Topic.
2. Create Subscription(s).
3. Subscribers connect.

Multiple subscribers:

- Same subscription → messages shared.
- Different subscriptions → each receives every message.

### 180) Cloud Pub/Sub?

**Answer:**

Fully managed messaging service.

Features:

- Reliable
- Scalable
- Auto-scaling
- Pay-per-use

Supports:

- Push
- Pull

Typical use:
Streaming pipelines.

### 181) Pub/Sub architecture?

**Answer:**

Publisher:

- Sends HTTP requests to Pub/Sub.

Subscriber:

- Pulls messages
  or
- Receives Push callbacks.

Supports:

- One→Many
- Many→One
- Many→Many

### 182) Relational DB import/export?

**Answer:**

**Cloud SQL**

- SQL/CSV
- Console
- gcloud
- REST
- Serverless export

**Cloud Spanner**

- Cloud Storage
- Dataflow

**BigQuery**

- Cloud Storage
- Datastore
- BigQuery Transfer Service
- Dataflow

Formats:
CSV, JSON, Avro, Parquet, ORC.

### 183) NoSQL import/export?

**Answer:**

**Firestore / Datastore**

- Cloud Storage
- Console
- gcloud
- REST

**Bigtable**

- Dataflow
- Avro
- Parquet
- SequenceFiles

Ensure Service Accounts have Storage permissions.

### 184) Database services to remember?

**Answer:**

Needs infrastructure:

- Cloud SQL
- Bigtable

Serverless:

- BigQuery
- Datastore
- Firestore

Quick summary:

- Cloud SQL → OLTP
- Cloud Spanner → Global OLTP
- BigQuery → Data Warehouse
- Firestore → Small NoSQL
- Bigtable → Large IoT / Analytics

### 185) Synchronous communication problem?

**Answer:**

If a dependent service fails:

- Caller blocks
- Availability decreases
- High load propagates failures

### 186) Why asynchronous communication?

**Answer:**

Publisher → Topic → Consumer

Benefits:

- Decoupling
- High availability
- Independent scaling
- Durable messaging

### 187) In-memory databases?

**Answer:**

Use Cloud Memorystore.

Advantages:

- Microsecond latency
- Redis
- Memcached

Use cases:

- Cache
- Sessions
- Leaderboards
- Geospatial apps

### 188) Cloud SQL?

**Answer:**

Managed relational database.

Supports:

- MySQL
- PostgreSQL
- SQL Server

Features:

- Regional
- 99.95% SLA
- SSD/HDD
- Up to 416 GB RAM
- Up to 30 TB storage

Use Cloud Spanner for:

- Global scale
- Multi-TB databases
- 99.999% availability

### 189) Cloud SQL features?

**Answer:**

- Automatic encryption
- Updates
- HA & failover
- Read Replicas
- PITR
- Automatic storage growth
- Automated/on-demand backups
- Database Migration Service
- SQL/CSV export

### 190) Cloud SQL High Availability?

**Answer:**

Primary + Secondary in one region.

- Synchronous replication
- Automatic failover
- No automatic failback
- Cannot be Read Replica

### 191) Cloud Spanner?

**Answer:**

Managed global relational database.

Features:

- 99.999% SLA
- Strong consistency
- Automatic sharding
- Horizontal read/write scaling
- Regional & Multi-Regional

More expensive than Cloud SQL.

### 192) BigQuery?

**Answer:**

Serverless analytical data warehouse.

Features:

- SQL
- Petabyte scale
- Columnar storage
- Streaming support
- Multiple import/export formats
- External tables
- Automatic table expiration

### 193) BigQuery querying?

**Answer:**

Access via:

- Console
- `bq`
- REST API

Best practice:
Estimate query cost using dry-run before execution.

### 194) Firestore vs Bigtable?

**Answer:**

**Firestore**

- Serverless
- ACID
- Mobile/Web
- Few TB

**Bigtable**

- Wide-column
- Not serverless
- > 10 TB
- Analytics & IoT
- Single-row transactions

### 195) NoSQL databases?

**Answer:**

Characteristics:

- Flexible schema
- Horizontal scaling
- High throughput

Trade-off:
Less SQL functionality for higher scalability.

GCP:

- Firestore
- Bigtable

### 196) OLAP vs OLTP?

**Answer:**

**OLTP**

- Row storage
- Small transactions

**OLAP**

- Column storage
- Analytics
- Compression
- Distributed execution

### 197) OLAP?

**Answer:**

Analytical workloads.

Examples:

- BI
- Reporting
- Warehousing

Recommended service:
**BigQuery**

### 198) OLTP?

**Answer:**

Transactional workloads.

Examples:

- Banking
- ERP
- CRM
- E-commerce

Recommended:

- Cloud SQL
- Cloud Spanner

### 199) Relational databases?

**Answer:**

Characteristics:

- Fixed schema
- Relationships
- Strong transactions

Used for:

- OLTP
- OLAP

### 200) Cloud Logging sink destinations?

**Answer:**

- Cloud Storage
- BigQuery
- Pub/Sub

### 201) Which log buckets receive logs from the default log sinks?

**Answer:**

- `_Default`
- `_Required`

### 202) Types of Cloud Audit Logs?

**Answer:**

- Admin Activity
- Data Access
- Policy Denied

### 203) View logs for a specific request?

**Answer:** Cloud Logging.

### 204) Identify prominent exceptions for a microservice?

**Answer:** Error Reporting.

### 205) Trace a request across multiple microservices?

**Answer:** Cloud Trace.

### 206) Audit every operation on Cloud Storage objects?

**Answer:** Enable **Data Access Audit Logs** for the bucket.

### 207) Cloud Storage static website?

**Answer:**

1. Create bucket matching the DNS name.
2. Verify domain ownership.
3. Upload website files.
4. Configure index/error pages.
5. Grant **Storage Object Viewer** to `allUsers`.
6. Enable public access.

### 208) Database primer?

**Answer:**
Key concepts when selecting a database:

- Availability
- Durability
- Consistency
- Transactions
- RTO
- RPO

### 209) Database snapshots?

**Answer:**
Snapshots improve recovery but introduce challenges:

- Database unavailable if data center fails.
- Data loss possible between snapshots.
- Snapshot creation impacts performance.

### 210) Cloud Storage Signed URL?

**Answer:**

Provides temporary access to objects without requiring a Google account.

Steps:

1. Create Service Account/User key.
2. Generate Signed URL.

Example:

```bash
gsutil signurl -d 10m KEY.json gs://BUCKET/OBJECT
```

### 211) Transaction logs?

**Answer:**

Adds transaction log replication.

Benefits:

- Recover data since last snapshot.

Remaining issue:

- Database unavailable after data-center failure.

### 212) Standby database?

**Answer:**

Replication to standby solves:

- High availability
- Minimal data loss
- Faster backups (taken from standby)

Primary workload remains unaffected.

### 213) Availability vs Durability?

**Answer:**

**Availability**

- Can data be accessed now?
- Measured as uptime (e.g. 99.99%).

**Durability**

- Will data still exist years later?
- Example: 11 nines (99.999999999%).

### 214) 11 nines durability?

**Answer:**

Approximately one lost file after storing one million files for ten million years.

High durability minimizes permanent data loss.

### 215) Increasing database availability & durability?

**Answer:**

Increase availability:

- Multiple standbys
- Multi-zone
- Multi-region

Increase durability:

- Replicas
- Snapshots
- Transaction logs

### 216) RTO & RPO?

**Answer:**

**RPO**

- Maximum acceptable data loss.

**RTO**

- Maximum acceptable downtime.

Lower RTO/RPO generally increases cost.

### 217) Database categories?

**Answer:**

Types:

- Relational
- Document
- Key-Value
- Graph
- In-Memory

Selection factors:

- Fixed vs schema-less
- Transaction requirements
- Latency
- TPS
- Data volume

### 218) Cloud Trace?

**Answer:**

Distributed tracing service.

Measures:

- Request latency
- Average response time
- Performance trends

Supports:

- GCE
- GKE
- App Engine
- Cloud Trace API

Client libraries:
Go, Java, C#, Node.js, PHP, Python, Ruby.

### 219) Error Reporting?

**Answer:**

Real-time exception monitoring.

Features:

- Error aggregation
- Stack traces
- Centralized dashboard
- Top/recent errors

Errors can come from:

- Cloud Logging
- Error Reporting API

Supports major programming languages.

### 220) Cloud Profiler?

**Answer:**

Production profiler.

Collects:

- CPU usage
- Memory usage

Components:

- Profiling Agent
- Profiler UI

Helps identify performance bottlenecks.

### 221) Service Level Objective (SLO)?

**Answer:**

SLI + target.

Examples:

- 99.99% availability
- 11 nines durability
- 99th percentile response time < 1 second

### 222) IAM role for Identity-Aware Proxy?

**Answer:** IAP-Secured Web App User.

### 223) Restrict GKE teams to their namespaces?

**Answer:** Kubernetes RBAC.

### 224) Role with full GKE access?

**Answer:**

`roles/container.admin`

(Kubernetes Engine Admin)

### 225) RBAC configured but cluster access denied?

**Answer:**

Verify IAM includes:

`container.clusters.get`

### 226) OAuth?

**Answer:**

Authorization framework.

Allows third-party applications to access resources **without sharing user credentials**.

Example:
Photo editor accessing Google Drive.

### 227) OpenID Connect (OIDC)?

**Answer:**

Adds authentication to OAuth2.

Typical use:
"Sign in with Google."

### 228) IAM vs ACL?

**Answer:**

IAM

- Bucket-wide permissions.

ACL

- Object-level permissions.

### 229) Encryption keys remain on-premises?

**Answer:** Client-side encryption.

### 230) Kubernetes Stateful deployments?

**Answer:**

Use **StatefulSet**.

Provides:

- Stable identities
- Stable hostnames
- Persistent storage

Suitable for:

- Kafka
- Redis
- ZooKeeper

### 231) Run one Pod on every node?

**Answer:** DaemonSet.

Typical uses:

- Logging
- Monitoring

### 232) Autoscale Pods and Nodes?

**Answer:**

Pods:

```bash
kubectl autoscale deployment
```

Cluster:

```bash
gcloud container clusters update --enable-autoscaling
```

### 233) Kubernetes service communication?

**Answer:**

Use Service Discovery.

FQDN:

```
SERVICE.NAMESPACE.svc.cluster.local
```

### 234) Kubernetes Services vs Ingress?

**Answer:**

**Service**

- Stable endpoint
- Internal load balancing

**Ingress**

- External HTTP/HTTPS routing
- Routes traffic to Services

### 235) App Engine scaling without max instances?

**Answer:** Manual Scaling.

### 236) App Engine reminders?

**Answer:**

- Regional service.
- Region cannot be changed.
- Good for simple microservices.

Standard:

- Scale to zero.
- Lower cost.

Flexible:

- Container-based.
- At least one instance always running.

Cost optimization:

- Prefer dynamic instances.
- Keep resident instances only if needed.

### 237) Kubernetes deployment workflow?

**Answer:**

1. Create cluster.
2. Connect (`get-credentials`).
3. Deploy application.
4. Expose Service.
5. Scale Pods.
6. Scale Nodes.
7. Configure HPA.
8. Enable Cluster Autoscaler.
9. Create ConfigMaps.
10. Create Secrets.
11. Add GPU Node Pools if required.
12. Delete deployments/services when finished.
13. Delete cluster.

Common commands:

- `kubectl create deployment`
- `kubectl expose`
- `kubectl scale`
- `kubectl autoscale`
- `gcloud container clusters update --enable-autoscaling`

### 238) GKE Cluster?

**Answer:**

Components:

**Control Plane**

- API Server
- Scheduler
- Controller Manager
- etcd

**Worker Nodes**

- Pods
- kubelet

### 239) GKE Cluster types?

**Answer:**

- Zonal
- Multi-zonal
- Regional
- Private
- Alpha

### 240) Kubernetes Pods?

**Answer:**

Smallest deployable unit.

A Pod:

- Contains one or more containers.
- Has one IP.
- Shares storage, network and volumes.

States:

- Pending
- Running
- Succeeded
- Failed
- Unknown

### 241) Deployment vs ReplicaSet?

**Answer:**

Deployment:

- Higher-level controller.
- Manages ReplicaSets.
- Supports rolling updates.

ReplicaSet:

- Maintains desired number of Pods.

Use **Deployment** in almost all cases.

### 242) Kubernetes Services?

**Answer:**

Provides stable network endpoint for Pods.

Types:

- ClusterIP
- NodePort
- LoadBalancer
- ExternalName

### 243) ClusterIP?

**Answer:**

Default Service type.

Internal communication only.

### 244) NodePort?

**Answer:**

Exposes Service on every node using a static port.

Accessible from outside the cluster.

### 245) LoadBalancer?

**Answer:**

Creates an external cloud Load Balancer.

Recommended for public services.

### 246) ExternalName?

**Answer:**

Maps a Kubernetes Service to an external DNS name.

### 247) ConfigMap?

**Answer:**

Stores non-sensitive configuration separately from containers.

### 248) Secret?

**Answer:**

Stores sensitive configuration such as:

- Passwords
- API keys
- Certificates

### 249) Horizontal Pod Autoscaler (HPA)?

**Answer:**

Automatically scales Pods based on metrics (typically CPU).

### 250) Cluster Autoscaler?

**Answer:**

Automatically adds or removes GKE nodes based on Pod scheduling demand.

### 251) Google Kubernetes Engine (GKE)?

**Answer:**

Managed Kubernetes service.

Features:

- Auto-repair
- Auto-upgrade
- Pod Autoscaling
- Cluster Autoscaling
- Built-in Cloud Logging & Cloud Monitoring
- Container-Optimized OS
- Persistent Disk & Local SSD support

### 252) GKE workload management commands?

**Answer:**

View resources:

```bash
kubectl get pods
kubectl get services
kubectl get replicasets
```

Deployment:

```bash
kubectl apply -f deployment.yaml
kubectl create deployment
```

Expose Service:

```bash
kubectl expose deployment hello-world --type=LoadBalancer --port=8080
```

Scale:

```bash
kubectl scale deployment hello-world --replicas=5
```

Autoscale:

```bash
kubectl autoscale deployment --min --max --cpu-percent
```

Update:

```bash
kubectl apply -f deployment.yaml
```

Rollback:

```bash
kubectl rollout undo deployment hello-world --to-revision=1
```

Delete:

```bash
kubectl delete deployment hello-world
```

### 253) Kubernetes terminology?

**Answer:**

**Infrastructure**

- Cluster
- Master Node(s)
- Worker Node(s)
- Node Pool (nodes with identical configuration)

**Workloads**

- Pod → smallest deployable unit
- Deployment → manages Pods
- Service → exposes Deployments

### 254) GKE cluster management commands?

**Answer:**

Create:

```bash
gcloud container clusters create
```

Resize:

```bash
gcloud container clusters resize
```

Autoscale:

```bash
gcloud container clusters update
```

Delete:

```bash
gcloud container clusters delete
```

Node Pool:

```bash
gcloud container node-pools create
```

Container Images:

```bash
gcloud container images list
```

### 255) Pod remains in **Waiting**?

**Answer:**

Most likely image pull failure.

### 256) Pod remains in **Pending**?

**Answer:**

Most likely insufficient cluster resources (Pod cannot be scheduled).

### 257) Internal communication only between Kubernetes microservices?

**Answer:**

Create a **ClusterIP** Service.

### 258) Delete an Instance Template?

**Answer:**

```bash
gcloud compute instance-templates delete
```

### 259) Kubernetes?

**Answer:**

Popular open-source container orchestration platform.

Provides:

- Cluster management
- Multiple VM types
- Auto Scaling
- Service Discovery
- Load Balancing
- Self-healing
- Zero-downtime deployments

### 260) Connect to a Kubernetes cluster?

**Answer:**

```bash
gcloud container clusters get-credentials
```

### 261) Run one Pod per node (logging/monitoring)?

**Answer:**

DaemonSet.

### 262) Worker-node component communicating with Control Plane?

**Answer:**

kubelet.

### 263) Worker-node component managing container networking?

**Answer:**

kube-proxy.

### 264) Distributed key-value store used by Kubernetes?

**Answer:**

etcd.

### 265) Kubernetes scheduler?

**Answer:**

Assigns Pods to suitable Worker Nodes.

### 266) Kubernetes Controller Manager?

**Answer:**

Maintains desired cluster state (Deployments, ReplicaSets, Nodes, etc.).

### 267) Kubernetes API Server?

**Answer:**

Main entry point for all Kubernetes API requests and cluster communication.

### 268) Container runtime?

**Answer:**

Runs containers on Worker Nodes (e.g. containerd).

### 269) Node Pool?

**Answer:**

Group of Worker Nodes sharing the same machine type and configuration.

Use multiple Node Pools for different workloads.

### 270) Rolling Update?

**Answer:**

Gradually replaces old Pods with new ones while keeping the application available.

### 271) Recreate deployment?

**Answer:**

Stops all old Pods before creating new Pods.

Results in application downtime.

### 272) Blue-Green deployment?

**Answer:**

Deploy new version separately and switch traffic after validation.

### 273) Canary deployment?

**Answer:**

Gradually route a percentage of traffic to the new version before full rollout.

### 274) Kubernetes Secret?

**Answer:**

Stores sensitive information such as:

- Passwords
- Tokens
- Certificates

### 275) ConfigMap?

**Answer:**

Stores non-sensitive application configuration separately from the container image.

### 276) Liveness Probe?

**Answer:**

Determines whether a container should be restarted.

### 277) Readiness Probe?

**Answer:**

Determines whether a Pod is ready to receive traffic.

### 278) Startup Probe?

**Answer:**

Allows slow-starting applications extra startup time before liveness checks begin.

### 279) Horizontal Pod Autoscaler (HPA)?

**Answer:**

Automatically scales Pods based on CPU or custom metrics.

### 280) Cluster Autoscaler?

**Answer:**

Automatically adds/removes Worker Nodes when Pods cannot be scheduled or nodes become underutilized.

### 281) StatefulSet?

**Answer:**

For stateful applications requiring:

- Stable network identity
- Persistent storage
- Ordered deployment

Examples:

- Kafka
- ZooKeeper
- Redis

### 282) Deployment?

**Answer:**

Declarative controller managing ReplicaSets and Pods with rolling updates and rollback support.

### 283) ReplicaSet?

**Answer:**

Ensures the desired number of identical Pods is always running.

### 284) Service?

**Answer:**

Provides a stable network endpoint and load balances traffic to Pods.

### 285) Ingress?

**Answer:**

Manages external HTTP/HTTPS access and routes traffic to Kubernetes Services.

### 286) Namespace?

**Answer:**

Logical partition inside a cluster for isolation, RBAC and resource organization.

### 287) Persistent Volume (PV)?

**Answer:**

Cluster storage resource independent of Pod lifecycle.

### 288) Persistent Volume Claim (PVC)?

**Answer:**

Request for Persistent Volume storage by a Pod.

### 289) Local SSD?

**Answer:**

Very high-performance local storage.

Suitable for temporary or disposable data only.

### 290) Persistent Disk?

**Answer:**

Durable block storage surviving VM restarts and replacement.

### 291) Filestore?

**Answer:**

Managed NFS file storage for shared access by multiple VMs.

### 292) Block Storage?

**Answer:**

Disk attached to a VM.

Characteristics:

- Usually attached to one VM.
- Multiple disks can attach to one VM.
- Read-only sharing supported in some cases.

Examples:

- Direct Attached Storage (DAS)
- Storage Area Network (SAN)

### 293) Block Storage vs File Storage?

**Answer:**

**Block Storage**

- Virtual disks
- VM storage

**File Storage**

- Shared file systems
- Multiple users/VMs

### 294) Scalability?

**Answer:**

Ability to increase or decrease capacity by adding or removing resources.

### 295) Availability?

**Answer:**

Ability to access a resource when needed.

Usually measured as uptime percentage.

### 296) Bootstrapping?

**Answer:**

Automatically install OS patches or software when a VM starts.

### 297) Reliability?

**Answer:**

Probability that a system meets its SLO over time.

Often measured using MTBF (Mean Time Between Failures).

### 298) Maintainability?

**Answer:**

Probability of successfully repairing a system within a given time.

### 299) Configurable parameters when adding a disk?

**Answer:**

- Disk type
- Encryption key management
- Source image

### 300) Create VM with a Service Account?

**Answer:**

```bash
gcloud compute instances create INSTANCE_NAME \
  --service-account=SERVICE_ACCOUNT_EMAIL
```

### 301) Lowest level of the GCP resource hierarchy?

**Answer:** Project.

### 302) Cloud Dataproc?

**Answer:** Managed Hadoop and Spark service.

### 303) Which GCP object is both a resource and an identity?

**Answer:** Service Account.

### 304) Kubernetes features?

**Answer:**

- Load balancing across cluster nodes
- Automatic node scaling
- Automatic cluster upgrades

### 305) How many Organizations can exist in one resource hierarchy?

**Answer:**
One.

A hierarchy has:

- 1 Organization
- Multiple Folders
- Multiple Projects

### 306) Database for IoT data?

**Answer:** Cloud Bigtable.

Optimized for billions of rows.

### 307) Data warehouse?

**Answer:** BigQuery.

Managed analytical warehouse supporting tens of PBs.

### 308) Stream processing / ETL for IoT?

**Answer:** Cloud Dataflow.

Supports both streaming and batch processing.

### 309) What is a Pod?

**Answer:**

Smallest deployable unit in Kubernetes.

Represents one running application instance.

### 310) Stackdriver (Cloud Operations) on Kubernetes?

**Answer:**

- Monitoring
- Logging
- Notifications

### 311) Create Bigtable table `iot-ingest-data`?

**Answer:**

```bash
cbt createtable iot-ingest-data
```

### 312) Where is the desired number of Pods defined?

**Answer:** ReplicaSet.

### 313) GCP organizational components?

**Answer:**

- Organization
- Folder
- Project

### 314) Storage for database files?

**Answer:** Block Storage.

### 315) Create Network Load Balancer?

**Answer:**

```bash
gcloud compute forwarding-rules create
```

### 316) Create a VPC?

**Answer:**

```bash
gcloud compute networks create
```

### 317) CPU reserved by Kubernetes?

**Answer:**

Approximately **0.25%–6%** of CPU cores.

### 318) Kubernetes cluster consists of?

**Answer:**

- Control Plane (Master)
- Worker Nodes

### 319) Where can Shared VPC be created?

**Answer:**

- Organization
- Folder

### 320) Firestore?

**Answer:**

Document database supporting:

- Mobile synchronization
- Offline support
- Real-time updates

### 321) Firewall rule field controlling inbound/outbound traffic?

**Answer:** Direction.

### 322) Administer Compute Engine resources?

**Answer:**

```bash
gcloud compute instances
```

### 323) `iam.serviceAccountUser`?

**Answer:**

Granted at project level, it allows a user to use/manage Service Accounts in that project.

### 324) Valid Storage Class transitions?

**Answer:**

Allowed:

- Regional → Nearline
- Multi-Regional → Coldline
- Nearline → Coldline

Not allowed:

- Regional ↔ Multi-Regional

### 325) Allow UDP ports 20000–30000?

**Answer:**

```bash
gcloud compute firewall-rules create fwr1 \
--allow=udp:20000-30000 \
--direction=INGRESS
```

### 326) Create Cloud SQL backup?

**Answer:**

```bash
gcloud sql backup create
```

### 327) Restore Cloud SQL backup?

**Answer:**

```bash
gcloud sql backups restore
```

### 328) Export Cloud SQL database?

**Answer:**

```bash
gcloud sql export sql
```

### 329) Import SQL into Cloud SQL?

**Answer:**

```bash
gcloud sql import sql
```

### 330) List Compute Engine instances?

**Answer:**

```bash
gcloud compute instances list
```

### 331) Stop a VM?

**Answer:**

```bash
gcloud compute instances stop
```

### 332) Start a VM?

**Answer:**

```bash
gcloud compute instances start
```

### 333) Delete a VM?

**Answer:**

```bash
gcloud compute instances delete
```

### 334) Create a Persistent Disk?

**Answer:**

```bash
gcloud compute disks create
```

### 335) Create a snapshot?

**Answer:**

```bash
gcloud compute disks snapshot
```

### 336) Create an image?

**Answer:**

```bash
gcloud compute images create
```

### 337) Managed Instance Group (MIG)?

**Answer:**

Provides:

- Automatic healing
- Autoscaling
- Rolling updates
- Load balancing integration

### 338) Unmanaged Instance Group?

**Answer:**

Collection of manually managed VMs.

No autoscaling or autohealing.

### 339) Instance Template?

**Answer:**

Defines VM configuration used by Managed Instance Groups.

### 340) Health Check?

**Answer:**

Determines VM health.

Used by:

- Load Balancers
- Managed Instance Groups

### 341) Autoscaling signals?

**Answer:**

- CPU utilization
- Load Balancer utilization
- Cloud Monitoring metrics

### 342) Autohealing?

**Answer:**

Automatically recreates unhealthy VM instances using health checks.

### 343) Load Balancer purpose?

**Answer:**

Distributes traffic across multiple backend instances.

### 344) External Load Balancer?

**Answer:**

Accepts traffic from the Internet.

### 345) Internal Load Balancer?

**Answer:**

Accepts traffic only from inside the VPC.

### 346) Cloud Run?

**Answer:**

Serverless platform for running containers.

Features:

- Autoscaling
- Scale to zero
- Pay per request

### 347) App Engine vs Cloud Run?

**Answer:**

**App Engine**

- PaaS
- Source-code deployment

**Cloud Run**

- Container-based
- Any language/framework

### 348) Cloud Functions concepts?

**Answer:**

**Event**

- e.g. Cloud Storage upload

**Trigger**

- Invokes a Function

Supported triggers:

- Cloud Storage
- Pub/Sub
- HTTP
- Firebase
- Firestore
- Cloud Logging

### 349) Cloud Functions?

**Answer:**

Serverless event-driven compute.

Features:

- No server management
- Automatic scaling
- Pay per use
- Languages: Java, Python, Node.js, etc.
- Max execution: **60 min**
- 1st Gen & 2nd Gen

### 350) Required role to view BigQuery data?

**Answer:** BigQuery Data Viewer.

### 351) Which role manages Service Accounts?

**Answer:** Service Account Admin.

### 352) Which role manages Kubernetes API objects?

**Answer:** Kubernetes Engine Developer.

### 353) Which role uploads objects to Cloud Storage?

**Answer:** Storage Object Creator.

### 354) Give a development team access to shared resources?

**Answer:**

- Create a Google Group.
- Add developers to the group.
- Assign the required predefined IAM roles to the group.

### 355) Temporary access to one Cloud Storage object?

**Answer:** Create a Signed URL.

### 356) Permanent access to an entire Cloud Storage bucket?

**Answer:** Use IAM.

### 357) Permanent access to only selected objects in a bucket?

**Answer:** Use ACLs (Access Control Lists).

### 358) SSH into Linux Compute Engine VMs?

**Answer:**
Options:

- Console → **SSH**
- `gcloud compute ssh`
- Custom SSH keys

### 359) Linux VM SSH authentication?

**Answer:**

Compute Engine uses **key-based SSH**.

Options:

**Metadata-managed**

- Manually manage SSH keys.

**OS Login (recommended)**

- Centralized SSH management.
- Google identity mapped to Linux user.
- Enable metadata:
  `enable-oslogin=true`
- Supports importing Linux users from AD/LDAP.
- Required roles:
    - `roles/compute.osLogin`
    - `roles/compute.osAdminLogin`

Windows uses password authentication.

### 360) Other important IAM roles?

**Answer:**

- Security Admin
- Security Reviewer
- Organization Role Admin
- Organization Role Viewer
- Role Admin
- Role Viewer
- Browser (`roles/browser`)

### 361) Logging & Service Account IAM roles?

**Answer:**

**Logging**

- Logs Viewer
- Private Logs Viewer
- Logging Admin

**Service Accounts**

- Service Account Admin
- Service Account User
- Service Account Token Creator
- Service Account Key Admin

### 362) Organization, Billing & Project roles?

**Answer:**

**Organization Administrator**

- Manage resource hierarchy
- IAM policies
- Users & roles

**Billing Account Creator**

- Create billing accounts

**Billing Account Administrator**

- Manage billing accounts
- Billing exports
- Link/unlink projects
- Cannot create billing accounts

**Billing Account User**

- Associate projects with billing accounts
- Often paired with **Project Creator**

**Billing Account Viewer**

- Read-only billing access

### 363) Resource Hierarchy & IAM Policy?

**Answer:**

IAM Policies can be applied at any hierarchy level.

Inheritance:

- Resources inherit policies from all parents.
- Effective policy = resource policy + inherited policies.
- Inheritance is transitive.

Remember:

- Higher-level permissions cannot be restricted at lower levels.

### 364) Organization Policy Service?

**Answer:**

Enforces organization-wide constraints.

Examples:

- Disable Service Account creation.
- Restrict allowed regions.

Required role:

- Organization Policy Administrator.

Difference:

- **IAM** → Who can do what?
- **Organization Policy** → What is allowed?

### 365) GCE VM (Project A) accessing Cloud Storage (Project B)?

**Answer:**

Grant the VM's Service Account (Project A) the required IAM role on the bucket in Project B.

### 366) External auditor needs read-only access?

**Answer:**

Grant:

- `roles/viewer`

(Simple solution; predefined roles are generally preferred over basic roles.)

### 367) Cloud Functions - Scaling & Concurrency?

**Answer:**

Scaling:

- Automatic instance creation.
- Cold starts possible.
- Configure minimum instances to reduce cold starts (higher cost).

Concurrency:

- Up to **1000** concurrent requests per instance.
- Function code must be thread-safe.

### 368) Cloud Functions (2nd Gen)?

**Answer:**

Improvements:

- HTTP timeout: **60 min**
- Up to **16 GB RAM**
- Up to **4 vCPUs**
- Up to **1000** concurrent requests
- Multiple revisions
- Traffic splitting
- 90+ Eventarc event sources

### 369) One-time access for a team member?

**Answer:**

Grant the required IAM role directly to the user's Google Workspace account.

For ongoing access, use a Group.

### 370) Production access for Operations team?

**Answer:**

Create a Google Group and assign IAM roles to the Group.

### 371) IAM Members / Identities?

**Answer:**

Types:

- Google Account
- Service Account
- Google Group
- Google Workspace Domain
- Cloud Identity Domain

Purpose:

- Users
- Applications
- Groups
- Enterprise identity management

### 372) Corporate Directory Federation?

**Answer:**

Federate Cloud Identity / Google Workspace with an external IdP.

Examples:

- Active Directory
- Azure AD

Benefits:

- Single Sign-On (SSO)
- SAML authentication

Examples:

- GCDS
- ADFS

### 373) User Identity Management?

**Answer:**

Small environments:

- Gmail account (Super Admin)

Enterprise:

- Google Workspace
  or
- Federated external Identity Provider

### 374) Billing Budgets, Alerts & Exports?

**Answer:**

Use Cloud Billing to:

- Create Budgets
- Configure Alerts
- Export billing data

Export targets:

- BigQuery
- Cloud Storage

### 375) Billing Accounts?

**Answer:**

Required for creating billable resources.

Properties:

- One billing account → multiple projects.
- Organization → multiple billing accounts.

Types:

- Self-Service
- Invoiced

### 376) Resource Hierarchy Best Practices?

**Answer:**

- Separate projects by environment.
- Separate folders by department.
- Shared folder for shared resources.
- One project per application per environment.

### 377) GCP Resource Hierarchy?

**Answer:**

Hierarchy:

- Organization
- Folder
- Project
- Resources

Resources always belong to a Project.

### 378) Create VM with 4 CPUs?

**Answer:**

```bash
gcloud compute instances create web-server-1 \
--machine-type=n1-standard-4
```

### 379) Data Analytics?

**Answer:**

Services supporting:

- ETL
- Batch processing
- Streaming processing

### 380) Instance Group autoscaling metrics?

**Answer:**

Can scale using:

- CPU utilization
- Network latency
- Load Balancer capacity

### 381) Primitive IAM Roles?

**Answer:**

- Owner
- Editor
- Viewer

### 382) List grantable IAM roles?

**Answer:**

```bash
gcloud iam list-grantable-roles
```

### 383) BigQuery?

**Answer:**

Fully managed, serverless data warehouse.

Optimized for:

- Petabyte-scale analytics
- SQL queries

### 384) Cloud Storage Triggers?

**Answer:**

- `google.storage.object.finalize`
- `google.storage.object.archive`
- `google.storage.object.metadataUpdate`

### 385) Alerting Policy includes?

**Answer:**

- Conditions
- Documentation
- Time-to-live (TTL)

### 386) Override inherited IAM policy?

**Answer:**

Define a policy at:

- Folder
  or
- Project level

### 387) Cloud Bigtable?

**Answer:**

Wide-column NoSQL database.

Best for:

- Semi-structured data
- Row-key access
- Datasets >1 TB

### 388) Dataproc?

**Answer:**

Managed Hadoop/Spark service.

Supports:

- Apache Spark
- Machine Learning libraries

### 389) Docker image registry?

**Answer:** Container Registry.

### 390) Cloud Firestore?

**Answer:**

Mobile-focused NoSQL database.

Features:

- Real-time synchronization
- Centralized storage

### 391) OpenCensus?

**Answer:**

Library for creating custom metrics for Cloud Operations.

### 392) Export Datastore?

**Answer:**

```bash
gcloud datastore export \
--namespace=NAMESPACE \
gs://BUCKET-NAME
```

### 393) Database for IoT sensor data?

**Answer:** Cloud Bigtable.

### 394) Cloud Filestore file system?

**Answer:** NFS (Network File System).

### 395) Bigtable clusters in multiple regions?

**Answer:**

Benefits:

- Higher availability
- Survives regional failures

Trade-off:

- Higher replication latency

### 396) In-memory database/cache?

**Answer:** Cloud Memorystore.

### 397) Petabyte-scale SQL data warehouse?

**Answer:** BigQuery.

### 398) App Engine request routing?

**Answer:**

Options:

- URL routing
- `dispatch.yaml`
- Cloud Load Balancing

Deploy:

```bash
gcloud app deploy dispatch.yaml
```

### 399) Deploy App Engine without downtime?

**Answer:**

Steps:

1. Deploy new version (`--no-promote`).
2. Test.
3. Gradually migrate traffic (`--migrate`).

### 400) Split traffic between App Engine versions?

**Answer:**

Methods:

- Cookie
- IP
- Random

Command:

```bash
gcloud app services set-traffic --split-by=<cookie|ip|random>


### 401) Kubernetes features reducing DevOps effort?
**Answer:**
- Load balancing across cluster VMs
- Automatic node scaling
- Automatic cluster upgrades



### 402) GKE autoscaling: CPU utilization calculation?
**Answer:**

Calculated across the **entire Deployment**.

Scaling adds replicas, so CPU utilization is evaluated at the Deployment level.



### 403) Instance Group autoscaling metrics?
**Answer:**
- CPU utilization
- Network latency
- Load Balancer capacity
- Cloud Monitoring metrics
- Queue-based workload



### 404) Kubernetes API?
**Answer:**

All cluster interactions go through the **Control Plane (Master)** via the Kubernetes API.



### 405) Docker advantages?
**Answer:**
- Lightweight containers
- Process isolation
- Cloud-neutral



### 406) App Engine?
**Answer:**

Fully managed PaaS for deploying applications.

Supports:
- Go
- Java
- Node.js
- PHP
- Python
- Ruby

Features:
- Automatic scaling
- Load balancing
- Platform updates
- Health monitoring
- Versioning
- Traffic splitting

Integrates with GCP services (e.g. Cloud SQL).

Pay only for provisioned resources.



### 407) Compute Engine vs App Engine?
**Answer:**

**Compute Engine**
- IaaS
- Maximum flexibility
- Customer manages OS, software, networking and security

**App Engine**
- PaaS
- Serverless
- Less operational overhead
- Lower flexibility



### 408) App Engine Standard Environment?
**Answer:**

Applications run in language-specific sandboxes.

**V1**
- Java
- Python
- PHP
- Go

Restrictions:
- Python/PHP → limited networking & extensions
- Java/Go → no restrictions

**V2**
- Java
- Python
- PHP
- Node.js
- Ruby
- Go

Features:
- Full network access
- No extension restrictions



### 409) App Engine Flexible Environment?
**Answer:**

Applications run in Docker containers on Compute Engine VMs.

Features:
- Any runtime
- Background processes
- Local disk access



### 410) Cloud Launcher solution categories?
**Answer:**
- Data sets
- Operating systems
- Developer tools



### 411) ReplicaSet?
**Answer:**

Maintains the desired number of Pods.

Deployment manages ReplicaSets and provides rolling updates.



### 412) GCP service for building Docker images?
**Answer:** Cloud Build.



### 413) WordPress deployment via Cloud Launcher: configurable resources?
**Answer:**
- Machine type
- Disk type
- Disk size
- Zone
- HTTP/HTTPS firewall rules



### 414) App Engine scaling modes?
**Answer:**

**Automatic**
- Scales based on load.
- Recommended for continuously running applications.

**Basic**
- Creates instances only when needed.
- Suitable for ad-hoc workloads.

**Manual**
- Fixed number of instances.
- Scaled manually.



### 415) App Engine hierarchy?
**Answer:**

Application
→ Services (Modules)
→ Versions
→ Instances

Notes:
- One Application per Project.
- Multiple Services.
- Multiple Versions.
- Supports rollback and traffic splitting.



### 416) CPU >70% for 3 minutes is an example of?
**Answer:** Cloud Monitoring Alerting Policy.



### 417) Upgrading from `n1-standard-1` to `n1-standard-4`?
**Answer:** Vertical Scaling.



### 418) Run a container on Kubernetes?
**Answer:**

```bash
kubectl run
```

### 419) Cloud Functions best use case?

**Answer:**

Event-driven workloads.

Examples:

- Cloud Storage upload
- Pub/Sub message

### 420) IAM role required to create snapshots?

**Answer:** Compute Storage Admin.

### 421) Show Deployment Manager deployment details?

**Answer:**

```bash
gcloud deployment-manager deployments describe
```

### 422) Database services requiring no VM configuration?

**Answer:**

- BigQuery
- Datastore
- Firebase

(Serverless services.)

### 423) Protect VMs against rootkits?

**Answer:** Shielded VM.

Provides:

- Secure Boot
- Integrity Monitoring

### 424) Cloud Trace?

**Answer:**

Measures execution time and latency across distributed application calls.

### 425) Why doesn't Kubernetes deploy containers directly?

**Answer:**

Pods are the smallest deployable unit.

A Pod usually contains one, but may contain multiple containers.

### 426) Create Pub/Sub subscription?

**Answer:**

```bash
gcloud pubsub subscriptions create \
--topic=ace-exam-topic1 \
ace-exam-sub1
```

### 427) Cloud Storage class changes?

**Answer:**

Allowed:

- Regional → Nearline
- Regional → Coldline
- Multi-Regional → Nearline
- Multi-Regional → Coldline
- Nearline → Coldline

Not supported:

- Coldline → another class

### 428) First step before storing data in BigQuery?

**Answer:** Create a Dataset.

### 429) Reliability metric for SaaS negotiation?

**Answer:** Mean Time Between Failures (MTBF).

### 430) Install Bigtable CLI?

**Answer:**

```bash
gcloud components install cbt
```

### 431) Cloud SQL export formats?

**Answer:**

- SQL
- CSV

### 432) Database for 50 TB of semi-structured key-based data?

**Answer:** Cloud Bigtable.

### 433) Cloud Spanner export: which service incurs processing cost?

**Answer:** Dataflow.

### 434) Advantages of message queues?

**Answer:**

Decouple services.

If one service slows down, others continue independently.

### 435) Nightly 3.5-hour Spark job?

**Answer:**

Create a temporary Dataproc cluster.

Example:

```bash
gcloud dataproc clusters create \
spark-nightly-analysis \
--zone=us-west2-a
```

### 436) Purpose of Instance Groups in Kubernetes?

**Answer:**

Manage Worker VMs as a single unit.

### 437) Compute Engine billing?

**Answer:**

- Per-second billing
- Cost depends on CPU & memory
- Custom machine types supported
- Preemptible VMs up to 80% cheaper
- Sustained-use discounts available

### 438) Scale Deployment to 10 replicas?

**Answer:**

```bash
kubectl scale deployment ch07-app-deploy --replicas=10
```

### 439) Stackdriver Monitoring metrics for which service?

**Answer:** BigQuery.

Examples:

- Query count
- Execution time
- Bytes scanned
- Table count

### 440) App Engine summary?

**Answer:**

PaaS for deploying applications without managing servers or clusters.

### 441) Autoscaler waiting period before collecting metrics?

**Answer:** Cool-down period.

### 442) Move Cloud Storage objects to Coldline?

**Answer:**

```bash
gsutil rewrite -s COLDLINE gs://PATH-TO-OBJECT
```

### 443) Cache effect?

**Answer:**

Reduces latency because cache access is faster than SSD/HDD.

### 444) Estimate BigQuery query cost?

**Answer:**

- BigQuery (data scanned estimate)
- Pricing Calculator (cost estimate)

### 445) Managed petabyte-scale replacement for MySQL warehouse?

**Answer:** BigQuery.

### 446) Export Cloud SQL (MySQL)?

**Answer:**

```bash
gcloud sql export sql \
gcp-exam-mysql1 \
gs://gcp-exam-bucket1/gcp-exam-mysql-export.sql \
--database=mysql
```

### 447) Datastore data model?

**Answer:** Document.

### 448) Create Pub/Sub topic?

**Answer:**

```bash
gcloud pubsub topics create
```

### 449) Delete Compute Engine instance?

**Answer:**

```bash
gcloud compute instance delete ch06-instance-3
```

### 450) Instance Group with different VM configurations?

**Answer:** Unmanaged Instance Group.

Managed Instance Groups require identical VMs created from the same Instance Template.

### 451) Kubernetes Services?

**Answer:**

Provide a stable endpoint for accessing Pods.

Benefits:

- Pods are ephemeral.
- Clients connect to Services.
- Services discover and route traffic to Pods.

### 452) Minimum information to create a VM?

**Answer:**

- Name
- Machine type
- Region
- Zone

### 453) On-demand resource provisioning?

**Answer:**

Provision resources only when needed and release them when no longer required.

### 454) Cloud advantages?

**Answer:**

- Convert capital expense to variable expense.
- Benefit from economies of scale.
- Avoid capacity planning.

### 455) High availability for global applications?

**Answer:**

Deploy workloads across:

- Multiple Regions
- Multiple Zones

### 456) Compute Engine (GCE)?

**Answer:**

Managed VM service.

Features:

- Create/manage VM lifecycle
- Load balancing
- Autoscaling
- Attach block & network storage
- Configure networking

### 457) Compute Engine Machine Families?

**Answer:**

**General Purpose**

- E2, N2, N2D, N1
- Web apps
- App servers
- Small/medium databases
- Development

**Memory Optimized**

- M1, M2
- In-memory databases
- In-memory analytics

**Compute Optimized**

- C2
- CPU-intensive workloads
- Gaming

### 458) Images?

**Answer:**

Define the OS and installed software.

Types:

- Public Images (Google, OSS, third-party)
- Custom Images (user-created)

Benefits:

- Faster provisioning
- Hardened OS
- Reusable across projects

### 459) Synchronize two Cloud Storage buckets?

**Answer:**

```bash
gsutil rsync
```

### 460) Estimate BigQuery query size?

**Answer:**

Use a dry run:

```bash
bq --location=LOCATION query \
--use_legacy_sql=false \
--dry_run SQL_QUERY
```

### 451) IoT company streams large volumes of data into GCP. Which service filters, transforms and analyzes data before storing it in Datastore?

**Answer:** Cloud Dataflow.

Supports stream and batch ETL processing.

### 452) Does Cloud Storage provide 11 nines availability?

**Answer:** No.

- **11 nines = durability**
- Availability = ability to access data when needed

### 453) Database for IoT analytics with petabyte-scale growth?

**Answer:** Cloud Bigtable.

- Wide-column NoSQL
- Low-millisecond latency
- Handles massive ingest

### 454) What estimate does BigQuery display before running a query?

**Answer:** Amount of data scanned.

### 455) `e2-standard-2` meaning?

**Answer:**

- **e2** → machine family
- **standard** → workload type
- **2** → vCPUs

### 456) Internal vs External IP?

**Answer:**

**External IP**

- Public
- Internet reachable

**Internal IP**

- Private VPC address
- Every VM has at least one
- Same private IPs may exist in different networks

Notes:

- External IP is released when a VM stops (unless static).

### 457) Static IP Address?

**Answer:**

Benefits:

- Permanent public IP
- Can move between VMs in the same project
- Required by many Load Balancers

Notes:

- Survives VM stop/start
- Charged even when unused

### 458) Reduce VM launch time?

**Answer:** Use a **Custom Image**.

Advantages:

- Pre-installed OS patches/software
- Faster boot
- Can be hardened
- Share across projects
- Prefer over Startup Scripts

Sources:

- VM
- Persistent Disk
- Snapshot
- Image
- Cloud Storage file

### 459) Instance Templates?

**Answer:**

Reusable VM definitions.

Include:

- Machine type
- Image
- Labels
- Startup scripts
- Metadata

Used by:

- VM creation
- Managed Instance Groups

Notes:

- Immutable
- Copy to modify
- Can reference Image Families

### 460) Create VM with HTTP server?

**Answer:**

Options:

- Startup Script
- Instance Template
- Custom Image

Bootstrapping installs software during VM startup.

### 461) Best machine family for CPU-intensive workloads?

**Answer:** Compute Optimized.

### 462) Avoid specifying VM configuration repeatedly?

**Answer:** Instance Template.

### 463) Preferred way to reduce VM launch time?

**Answer:** Custom Hardened Image.

### 464) Sustained Use Discounts?

**Answer:**

Automatic discounts for long-running VMs.

Features:

- Increase with usage
- Applies to GCE & GKE VMs

Not supported:

- E2
- A2
- App Engine Flexible
- Dataflow VMs

### 465) Committed Use Discounts?

**Answer:**

For predictable workloads.

- 1- or 3-year commitment
- Up to 70% discount

### 466) Preemptible VM?

**Answer:**

Low-cost VM (up to 80% cheaper).

Characteristics:

- Can terminate anytime (within 24h)
- No SLA
- No automatic restart
- Cannot convert to regular VM

Best for:

- Fault-tolerant
- Batch jobs
- Non-urgent workloads

### 467) Spot VM?

**Answer:**

Successor to Preemptible VM.

Differences:

- No 24-hour limit
- May terminate anytime (30s notice)

Features:

- 60–91% discount
- No Free Tier credits
- Availability not guaranteed

### 468) Compute Engine billing?

**Answer:**

- Per-second billing (1-minute minimum)
- Stopped VMs incur **storage** costs only

Cost optimization:

- Budget alerts
- Billing exports
- Right machine type
- Discounts

### 469) Live Migration & Availability Policy?

**Answer:**

**Live Migration**

- Moves running VM during maintenance
- Same zone
- No VM property changes
- Supports Local SSD
- Not supported for GPUs or Preemptible VMs

Availability Policy:

- **Migrate** (default)
- **Terminate**
- **Automatic restart**

### 470) Custom Machine Types?

**Answer:**

Customize:

- vCPUs
- Memory
- GPUs

Supported families:

- E2
- N1
- N2

Supports most operating systems.

Billing:

- Per vCPU
- Per GB RAM

### 471) GPU?

**Answer:**

For:

- AI/ML
- Scientific computing
- Graphics

Recommendations:

- GPU-enabled images
- Automatic restart enabled

Restrictions:

- Not all machine types
- Host maintenance = Terminate

### 472) Compute Engine reminders?

**Answer:**

- VM belongs to a Project
- VM is Zonal
- Images & Instance Templates are Global
- Machine type changes require stopped VM
- Default monitoring includes:
    - CPU
    - Network
    - Disk

Memory metrics require Cloud Monitoring Agent.

### 473) Compute Engine best practices?

**Answer:**

Choose:

- Region
- Zone
- Machine type

Recommendations:

- Multi-zone deployment
- GPUs for compute-heavy workloads
- Committed discounts for steady workloads
- Spot/Preemptible for batch jobs
- Labels

### 474) Prerequisites for creating a VM?

**Answer:**

- Project
- Billing Account
- Compute Engine API enabled

### 475) Dedicated hardware for compliance/licensing?

**Answer:** Sole-tenant Nodes.

### 476) Manage OS patching, inventory and configuration on thousands of VMs?

**Answer:** VM Manager.

### 477) Install software on a VM?

**Answer:** SSH into the VM.

### 478) Keep a VM private?

**Answer:** Do **not** assign an External IP.

### 479) Allow HTTP access to a VM?

**Answer:** Configure Firewall Rules.

### 480) Machine Types?

**Answer:**

Combination of:

- CPU
- Memory
- Storage
- Networking

Choose predefined or create a **Custom Machine Type**.

### 481) Remember:

**Answer:**

- Static IP → permanent VM IP
- Instance Template → reusable VM configuration
- Spot/Preemptible VM → low-cost fault-tolerant workloads

### 482) Metrics available without Cloud Monitoring Agent?

**Answer:** CPU Utilization.

### 483) Export Datastore (default namespace)?

**Answer:**

```bash
gcloud datastore export \
--namespace="default" \
gs://ace-exam-bucket1
```

### 484) `gcloud`?

**Answer:**

Main CLI for Google Cloud.

Examples:

- Compute Engine
- Databases
- IAM
- MIGs

Related CLIs:

- `gsutil`
- `bq`
- `cbt`
- `kubectl`

### 485) Initialize gcloud?

**Answer:**

```bash
gcloud init
```

View config:

```bash
gcloud config list
```

### 486) Set active configuration?

**Answer:**

Examples:

```bash
gcloud config set project
gcloud config set compute/region
gcloud config set compute/zone
```

Unset:

```bash
gcloud config unset
```

### 487) Multiple gcloud configurations?

**Answer:**

```bash
gcloud config configurations create
gcloud config configurations activate
gcloud config configurations list
gcloud config configurations delete
```

### 488) gcloud command structure?

**Answer:**

```
gcloud GROUP SUBGROUP ACTION
```

Examples:

```bash
gcloud compute instances list
gcloud compute instances create
gcloud compute instances describe
gcloud compute instances delete
```

### 489) Create Compute Engine VM?

**Answer:**

```bash
gcloud compute instances create NAME
```

### 490) Highest priority when determining a zone?

**Answer:**

The `--zone` flag specified in the command.

### 491) Static IP Address, Instance Template, Preemptible VM?

**Answer:**

**Static IP**

- Permanent VM IP
- Survives VM restart
- Can move between VMs

**Instance Template**

- Reusable VM configuration
- Simplifies VM creation

**Preemptible VM**

- Up to 80% cheaper
- For fault-tolerant, non-critical workloads

### 492) Compute Engine metrics available without Cloud Monitoring Agent?

**Answer:** CPU Utilization.

### 493) Export Datastore (default namespace)?

**Answer:**

```bash
gcloud datastore export \
--namespace="default" \
gs://ace-exam-bucket1
```

### 494) `gcloud`?

**Answer:**

Primary Google Cloud CLI.

Manages:

- Compute Engine
- Managed Instance Groups
- Databases
- IAM
- Most GCP resources

Related CLIs:

- `gsutil`
- `bq`
- `cbt`
- `kubectl`

### 495) Common `gcloud` commands?

**Answer:**

Initialize:

```bash
gcloud init
```

View configuration:

```bash
gcloud config list
```

### 496) `gcloud config set`?

**Answer:**

Examples:

```bash
gcloud config set project PROJECT
gcloud config set compute/region REGION
gcloud config set compute/zone ZONE
gcloud config set core/verbosity LEVEL
```

Syntax:

```bash
gcloud config set SECTION/PROPERTY VALUE
```

Remove setting:

```bash
gcloud config unset
```

### 497) Multiple `gcloud` configurations?

**Answer:**

Create:

```bash
gcloud config configurations create NAME
```

Activate:

```bash
gcloud config configurations activate NAME
```

Other commands:

```bash
gcloud config configurations list
gcloud config configurations delete NAME
```

### 498) `gcloud` command structure?

**Answer:**

```
gcloud GROUP SUBGROUP ACTION
```

Examples:

```bash
gcloud compute instances list
gcloud compute instances create
gcloud compute instances describe
gcloud compute instances delete
```

### 499) Create Compute Engine VM?

**Answer:**

```bash
gcloud compute instances create NAME
```

### 500) Zone priority for `gcloud` commands?

**Answer:**

Highest priority:

```text
--zone
```

specified directly in the command.

### 501) Instance Groups?

**Answer:**

Manage multiple VMs as one unit.

Types:

**Managed**

- Identical VMs
- Instance Template
- Autoscaling
- Autohealing
- Rolling updates

**Unmanaged**

- Different VM configurations
- Manual management
- No autoscaling

Can be:

- Zonal
- Regional (recommended for HA)

### 502) Managed Instance Groups (MIG)?

**Answer:**

Features:

- Maintain desired instance count
- Self-healing (Health Checks)
- Autoscaling
- Load Balancing
- Regional deployment
- Rolling updates
- Canary deployments

### 503) Cloud Dataproc manages which big-data platforms?

**Answer:**

- Apache Spark
- Hadoop

### 504) Relational database supporting transactions and SQL?

**Answer:**

- Cloud SQL
- Cloud Spanner

### 505) Job History?

**Answer:**

Shows:

- Active jobs
- Completed jobs
- Failed jobs

### 506) Create Bigtable table `iot-ingest-data`?

**Answer:**

```bash
cbt createtable iot-ingest-data
```

### 507) Next step after creating a Cloud Spanner instance?

**Answer:** Create a database.

### 508) Dataproc export?

**Answer:** Cluster configuration.

### 509) Move Cloud Storage objects to Nearline after 90 days?

**Answer:**

Create an **Object Lifecycle Management** policy:

- Condition:
    - Age = 90 days
- Action:
    - `SetStorageClass = Nearline`

### 510) Create Cloud Storage bucket?

**Answer:**

```bash
gsutil mb
```

### 511) Creating a Managed Instance Group?

**Answer:**

Requires:

- Instance Template

Optional configuration:

- Autoscaling
    - Min instances
    - Max instances
    - CPU / Load Balancer / Monitoring metrics
- Cool-down period
- Scale-in controls
- Autohealing
    - Health Check
    - Initial delay

### 512) Scale In vs Scale Out?

**Answer:**

**Scale In**

- Reduce VM count

**Scale Out**

- Increase VM count

### 513) Updating a Managed Instance Group?

**Answer:**

Supports:

**Rolling Update**

- Gradual deployment
- Optional canary template

Update modes:

- Proactive
- Opportunistic

Configuration:

- Max Surge
- Max Unavailable

Also supports:

- Rolling Restart
- Rolling Replace

### 514) Prevent frequent scaling in a MIG?

**Answer:** Configure a **Cool-down period** (initial delay).

### 515) Automatically replace unhealthy MIG instances?

**Answer:** Configure **Health Checks** (self-healing).

### 516) Keep MIG available during maintenance?

**Answer:**

Instance Template:

- Automatic Restart = Enabled
- On-host maintenance = Migrate

Supports:

- Live Migration
- Automatic restart

### 517) Preserve VM state in a MIG?

**Answer:** Stateful Managed Instance Group.

Preserves:

- Instance name
- Persistent Disks
- Metadata

Best for:

- Databases
- Stateful applications

### 518) Different VM configurations in one Instance Group?

**Answer:** Unmanaged Instance Group.

### 519) Survive zonal failures with a MIG?

**Answer:** Regional (multi-zone) Managed Instance Group.

### 520) Common MIG commands?

**Answer:**

Create:

```bash
gcloud compute instance-groups managed create
```

Autoscaling:

```bash
gcloud compute instance-groups managed set-autoscaling
```

Update:

```bash
gcloud compute instance-groups managed update
```

### 521) Update Instance Template in a MIG?

**Answer:**

```bash
gcloud compute instance-groups managed set-instance-template
```

Roll out using:

- update-instances
- recreate-instances
- rolling-action start-update

### 522) Zero-capacity reduction during rollout?

**Answer:**

```text
--max-unavailable=0
```

### 523) Prevent excessive autoscaling?

**Answer:**

```text
--cool-down-period
```

### 524) Cloud Load Balancing?

**Answer:**

Distributes traffic across one or more regions.

Features:

- Managed
- Health Checks
- Autoscaling
- Global Anycast IP
- High Availability
- Resilience

### 525) Cloud Load Balancing terminology?

**Answer:**

**Backend**

- Receives traffic
- Typically Instance Groups

**Frontend**

- IP
- Port
- Protocol
- SSL certificate (HTTPS)

**Host & Path Rules**

- Route requests by:
    - Host
    - URL path
    - HTTP headers

### 526) SSL/TLS termination (offloading)?

**Answer:**

Client → Load Balancer:

- HTTPS (recommended)

Load Balancer → Backend:

- HTTP (acceptable on Google's private network)
- HTTPS preferred

Benefit:

- SSL/TLS processing handled by the Load Balancer.

### 527) Cloud Load Balancer types?

**Answer:**

**External HTTP(S)**

- Global
- Proxy
- HTTP/HTTPS

**Internal HTTP(S)**

- Regional
- Proxy
- HTTP/HTTPS

**SSL Proxy**

- Global
- TCP + SSL offload

**TCP Proxy**

- Global
- TCP

**External TCP/UDP**

- Regional
- Pass-through

**Internal TCP/UDP**

- Regional
- Pass-through

### 528) Send traffic only to healthy instances?

**Answer:** Configure **Health Checks**.

### 529) High availability for VM applications?

**Answer:**

- Deploy multiple Managed Instance Groups
- Across multiple regions
- Front them with a Load Balancer

### 530) Route one Load Balancer to multiple microservices?

**Answer:**

Create:

- Separate MIGs
- Separate Backends

Route using:

- Host rules
- Path rules
- HTTP headers

Can also route to Cloud Storage backends.

### 531) Global HTTPS load balancing?

**Answer:** External HTTPS Load Balancer.

### 532) SSL termination for global non-HTTPS traffic?

**Answer:** SSL Proxy Load Balancer.

### 533) Why Microservices?

**Answer:**

Benefits:

- Small, focused services
- Independent deployments
- Polyglot development (Go, Java, Python, JavaScript)

Challenge:

- Deployment complexity

Solution:

- Containers

### 534) Docker containers?

**Answer:**

Docker Image includes:

- Application
- Runtime
- Dependencies

Runs consistently:

- Local
- On-prem
- Cloud

Benefits:

- Lightweight
- Isolated
- Cloud-neutral

### 535) Container Orchestration?

**Answer:**

Provides:

- Autoscaling
- Service Discovery
- Load Balancing
- Self-healing
- Zero-downtime deployments

### 536) Serverless?

**Answer:**

Focus on code, not infrastructure.

Benefits:

- No server management
- Automatic scaling
- High availability
- Pay per use/request

### 537) Serverless characteristics?

**Answer:**

Features:

- No infrastructure management
- Automatic scaling
- High availability

Billing:

- Pay per invocation
- Scale to zero (supported services)

Examples:

- App Engine
- Cloud Functions
- AWS Lambda
- Azure Functions
- AWS Fargate

### 538) Instance Groups?

**Answer:**

Groups of VMs managed together.

Benefits:

- Scaling
- Load Balancing
- Higher availability

### 539) Prerequisite for an Instance Group?

**Answer:** Instance Template.

### 540) Two App Engine applications in one project?

**Answer:** Not possible.

One App Engine application per Project.

Supports:

- Multiple Services
- Multiple Versions

### 541) Move App Engine to another region?

**Answer:** Not supported.

Create:

- New Project
- New App Engine application
- Desired region

### 542) App Engine Canary Deployment?

**Answer:**

Deploy without traffic:

```bash
gcloud app deploy --no-promote
```

Split traffic:

```bash
gcloud app services set-traffic \
s1 --split v1=0.9,v2=0.1
```

### 543) Gradually migrate App Engine traffic?

**Answer:**

Use:

```text
--migrate
```

### 544) Archive Storage?

**Answer:**

- Minimum storage: **365 days**
- Availability:
    - 99.95% (Multi/Dual Region)
    - 99.9% (Region)
- Best for data accessed **less than once/year**

### 545) Coldline Storage?

**Answer:**

- Minimum storage: **90 days**
- Availability:
    - 99.95% (Multi/Dual Region)
    - 99.9% (Region)
- Best for quarterly access

### 546) Nearline Storage?

**Answer:**

- Minimum storage: **30 days**
- Availability:
    - 99.95% (Multi/Dual Region)
    - 99.9% (Region)
- Best for monthly access

### 547) Standard Storage?

**Answer:**

- No minimum storage duration
- 99.99% availability
- Best for frequently accessed data

### 548) Why Storage Classes?

**Answer:**

Optimize storage cost based on access frequency.

Typical data:

- Media
- Logs
- Backups
- Archives

Designed for:

- 11 nines durability

### 549) Common features across Storage Classes?

**Answer:**

- 11 nines durability
- Low latency
- Unlimited autoscaling
- No minimum object size
- Same APIs

Committed SLA:

- Standard
- Nearline
- Coldline

No committed SLA:

- Archive

### 550) Cloud IAM example?

**Answer:**

Scenario:
Grant a colleague access to manage one Storage bucket.

Concepts:

- **Member** → colleague
- **Resource** → bucket
- **Role** → Storage Object Admin
- **Policy** → binds Member to Role

### 551) Change Storage Class of an existing bucket?

**Answer:**

1. Change bucket's default Storage Class.
2. Update existing objects to the new class.

### 552) Automatically move objects between Storage Classes?

**Answer:** Object Lifecycle Management.

### 553) Logs accessed once per quarter?

**Answer:** Coldline Storage.

### 554) Long-term archive with almost no access?

**Answer:** Archive Storage.

### 555) Speed up very large Cloud Storage uploads?

**Answer:** Parallel Composite Uploads.

Splits the file into chunks and uploads them in parallel.

### 556) Cloud Storage upload/download methods?

**Answer:**

**Uploads**

- Simple
- Multipart
- Resumable *(recommended)*
- Streaming
- Parallel Composite

**Downloads**

- Simple
- Streaming
- Sliced

### 557) Object Lifecycle Management?

**Answer:**

Automatically changes Storage Class or deletes objects.

Conditions:

- Age
- CreatedBefore
- IsLive
- MatchesStorageClass
- NumberOfNewVersions
- Others

Actions:

- `SetStorageClass`
- `Delete`

Supported transitions:

- Standard / Multi-Regional / Regional → Nearline / Coldline / Archive
- Nearline → Coldline / Archive
- Coldline → Archive