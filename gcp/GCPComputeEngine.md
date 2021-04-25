# Migrate to Google Cloud Platform

## Introduction to VM Migration

### What is cloud computing?

- On-demand self-service.
    - No human intervention needed to get resources.
- Broad network access:
    - Access from anywhere.
- Resource pooling:
    - Provider shares resources to customers.
- Rapid elasticity:
    - Get more resources quickly as needed.
- Measured service:
    - Pay only for waht you consume.    
    
**On-premises total cost of ownership**

- Capital Expense.
- Operational Expense.
- Total Cost of Ownership. 

**Cloud total cost of ownership**

- Operational Expense.
- Total Cost of Ownership.

**Common reasons for Moving to the cloud**

- Refresh Cycle
- Flexibility
- Elasticity
- Security

### Migration strategies

- Lift and Shift.
- Rebuild
- Remain On-premises
- Improve and Move

**Lift and Shift overview**

- Moving applications as they exist to the cloud.
- When to use it:
    - Applications that can run unmodified in the cloud.
    - Speed is necessary.
    - Little appetite or need for change.
    - Minimal risk. 

### The migration process

**The VM migration process**

- Assess:
    - Evaluate Your Environment.
- Prepare:
    - Understand the platform.
- Migrate:
    - Move virtual machines.        
- Optimize:
    - Tune your operations and save on costs.
    
#### Quiz

**1. Select a common incentive to move to the cloud.**    

- The cloud allows individual customers to enjoy economies of scale.

**2. Select the main stages of the migration process in the correct order.**

- Assess, Prepare, Migrate, Optimize.

**3. What’s the purpose of the Optimize stage of the migration process?**
    
- Tune your operations and save on costs.

## Assessing the source environment 
    
### Assess Phase

**Assess overview**

- The Assess phase helps uncover the existing workloads that need to be migrated and the information necessary to 
determine **migration type**, **level of effort**, and **application groups**.
- Input:
    - Business objectives
    - Key stakeholders
    - Technical assumptions
    - Security approval for tooling
- Activities:
    - Automated discovery.
- Output:
    - Workload grouping
    - First mover workloads identified
    - Refined TCO/ROI Analysis
    - High-level effort estimations            

**Automated discovery options**

- Discover tour application landscape.
- Estimate cost.
- Assess your workload portfolio.

### CloudPhysics

- On-Premises TCO Model: Understand the infrastructure costs.

**Decide if GCP is right for you**

- Compare side-by-side cost and compatibility between GCP and on-premises.

**Automatic GCP instance match**

- Estimate costs of GCP for any instance in any location, with no commitment to migrate using actual utilization data.

**AWS and vSphare support**

**Requirements**

- Appliance Requirements:
    - 8-GB vRAM
    - 4 vCPU
    - 1 NIC
    - 12 GB Disk
    - Internet access
    - Access to VMware vCenter
- AWS Requirements:
    - AWS IAM Policy
    - AWS IAM Role
    - AWS S3 Bucket (Billing)
    - AWS Cost and Usage Report or Detailed Billing Report (DBR)    

**CloudPhysics Observer**

- Observer collects performance and configurations for the following VMware resources:
    - 8-GB vRA
    - vCenter
    - Data centers
    - Clusters
    - Resource pools
    - Hosts
    - VMs
    - Network
    - Storage
    - Events
  
### Choosing VMs to Migrate

**Choosing VMs to migrate**

- Easy to migrate.
- Harder to migrate.
- Won't be migrated.    

**CloudPhysics assessment**

- Report:
    - vCenter Summary
    - Host Analysis
    - Guest OS

**Host Analysis Card**

- Hardware inventory migration indications:
    - ESX End of Support.
    - VMWare Hardware Compatibility List (HCL).
    - Hypervisor Version Compatibility.

**Guest OS Analysis Card**

- Guest OS migration indications:
    - Indicates End of Support OSs.
    - Helps tag/untag VMs based on their OS versions.
    - Power On/Off/Suspend.
    
**On-Prem IT Cloud Simulator Card**

**GCP Cloud Simulator Card**

- Users can choose or adjust:
    - GCP Data Center Locations.
    - Storage Type and Performance.
    - Rightsizing.
    - Additional Capacity options.
    - Term Lengths.
    - Instance Classes.
    
**Rightsizing**

- The longer the Observer collects data, the more accurate the resizing can be.

#### Quiz

**1. Choosing a machine rightsize recommendation based on the 99th percentile means.**

- The destination machine will have a CPU capacity equivalent to the peak CPU consumption across 99% of its sample time.

**2. Which of the following tools can help list your VM inventory, predict total cost of ownership post-migration, 
and spot migration challenges and obstacles?**
        
- Cloudamize
- CloudPhysics
- StratoZone

***

## Google Cloud Platform Fundamentals

### Google Cloud Platform terminology

**On-premises vs. Google Cloud: Compute**

- Data center:
    - Physical or virtualized hardware
    - VMWare ESXi
    - Hyper-V
    - KVM
    - XEN
- AWS:
    - Amazon Elastic Compute Cloud.
- GCP:
    - Compute Engine    

**On-premises vs. Google Cloud: Storage**

- Data center
    - SAN
    - NAS
    - DAS
- AWS:
    - Amazon Elastic Block Store
- GCP:
    - Persistent Disk        

**On-premises vs. Google Cloud: Identity**

- Data center:
    - Active Directory
    - LDAP
- AWS:
    - Amazon IAM
- Google Cloud:
    - Cloud Identity
    - IAM        

### Google Cloud Platform resource hierarchy

**Resource hierarchy levels define trust boundaries**

- Group your resources according to your organization structure.
- Levels of the hierarchy provide trust boundaries and resource isolation.

**All GCP services you use are associated with a Project**

- Track resource and quota usage.
- Enable billing.
- Manage permissions and credentials.
- Enable services and APIs.
- Separate administrative control plane.

**Projects have three identifying attributes**

| | | | |
|---|---|---|---|
| Project ID | Globally Unique | Chosen by you | Immutable |
| Project name | Need not be unique | Chosen by you | Mutable |
| Project number | Globally unique | Assigned by GCP | Immutable |

**Folders offer flexible management**

- Folders group projects under an organization.
- Folders can contain projects, other folders, or both.
- Use folders to assign policies.

**The organization node organizes projects**

- The organization node is the root node for Google Cloud resources.
- Notable organization roles:
    - **Organization Policy Administrator:** Broad control over all cloud resources.
    - **Project Creator:** Fine-grained control of project creation.

**An example IAM resource hierarchy**

- A policy is set on a resource:
    - Each policy contains a set of roles and role members.
- Resources inherit policies from parent:
    - Resource policies are a union of parent and resource.    
- A less restrictive parent policy overrides a more restrictive resource policy.

#### Quiz

**1. True or False: All Google Cloud resources are associated with a project.**

- True. All Google Cloud resources are associated with a project. Each project is associated with a billing account, 
which functions as the payment method for these resources.

### Identity and Access Management (IAM)

**Shared Responsibilities Model**

- Google is responsible for managing its infrastructure security.
- You are responsible for securing your data.
- Google helps you with best practices, templates, products, and solutions.

**Identity and Access Management**

- Who
- can do what
- on which resource

**Who: IAM policies can apply to any of four types of principals**

- Google account: test@gmail.com
- Service account: test@project_id.iam.gserviceaccount.com
- Google group: test@googlegroups.com
- Cloud Identity: example.com

**Can do what: IAM roles are collections of related permissions**

- Basic
- Predefined
- Custom

**IAM basic roles apply across all Google Cloud services in a project**

- can do what on all resources.

**IAM basic roles offer fixed, coarse-grained levels of access**

- Owner:
    - Invite members
    - Remove members
    - Delete projects
- Editor:
    - Deploy applications
    - Modify code
    - Configure services
- Viewer:
    - Read-only access
- Billing Administrator:
    - Manage billing
    - Add and remove administrators
- Project can have multiple owners, editors, viewers, and billing administrators.                

**IAM predefined roles apply to a particular Google Cloud service in a project**

- can do what on Compute Engine resources in this project, or folder, or org.

**IAM predefined roles offer more fine-grained permissions on particular services**

- Google Group:
    - InstanceAdmin Role:
        - compute.instances.delete
        - compute.instances.get
        - compute.instances.list
        - compute.instances.setMachineType
        - compute.instances.start
        - compute.instances.stop
        
**Compute Engine IAM roles**

| Role Title | Description |
|---|---|
| Compute Admin | Full control of all Compute Engine resources (compute.*) |
| Network Admin | Permissions to create, modify, and delete networking resources, except for firewall rules and SSL certs. |
| Storage Admin | Permissions to create, modify, and delete disks, images, and snapshots. |

**IAM custom roles let you define a precise set of permissions**

- Google Group:
    - InstanceOperator Role:
        - compute.instances.get
        - compute.instances.list
        - compute.instances.start
        - compute.instances.stop
        - ...

**On which resource: Users get roles on specific items in the hierarchy**

- on which resource

**Service accounts provide an identity for carrying out server-to-server interactions**

- Programs running within Compute Engine instances can automatically acquire access tokens with credentials.
- Tokens are used to access any service API in your project and any other services that granted access to that service
account.
- Service accounts are convenient when you're not accessing used data.

#### Quiz

**1. True or False: If a Google Cloud IAM policy gives you Owner permissions at the project level, 
your access to a resource in the project may be restricted by a more restrictive policy on that resource.**

- False. Policies are a union of the parent and the resource. If a parent policy is less restrictive, 
it overrides a more restrictive resource policy.

**2. Service accounts are used to provide which of the following?**

- Authentication between Google Cloud services.
- Key generation and rotation when used with App Engine and Compute Engine.
- A way to restrict the actions that a resource (such as a VM) can perform.
- A way to allow users to act with service account permissions.

### Identity

- Gmail account, Cloud Identity/Google Workspace, Service Account: 
    - Can be part of a group.
        - User@gmail.com, Project A, Owner
        - User@yourcompany.com, Project B, Instance Admin
- Note: You cannot use Cloud IAM to create or manage your users or groups.

**What if you already have a different corporate directory?**

```
Users and groups in your        Google Cloud Directory Sync
existing directory service      ----- Scheduled sync ----->     Users and groups in your Cloud Identity domain.
```

### Interacting with Google Cloud Platform

**There are four ways to interact with GCP**

| Cloud Platform Console | Cloud SDK and Cloud Shell | Cloud Console Mobile App | REST-based API |
|---|---|---|---|
| Web user interface | Command-line interface | For IOS and Android | For custom applications |

**Google Cloud Console**

- Centralized GUI-based console for all project data.
- Developer tools:
    - Cloud Source Respositories.
    - Cloud Shell.
    - Test Lab (mobile app testing).
- Access to product APIs.
- Manage and create projects.    

**The Cloud SDK and Cloud Shell**

- The Cloud SDK includes CLI tools for Google Cloud products and services:
    - gcloud, gsutil (Cloud Storage), bg (Big Query).
- Available as Docker image.
- Available via Cloud Shell:
    - Centralized version of the Cloud SDK on Compute Engine instance.    

**RESTful APIs**

- Programmatic access to products and services:
    - Typically use JSON as an interchange format.
    - Use OAuth 2.0 for authentication and authorization.
- Enabled through the Google Cloud Platform Console.
- To help you control spend, most include daily quotas and rates (limits):
    - Quotas and rates can be raised by request.    

**Cloud Console Mobile App**

- Manage virtual machines and database instances.
- Manage apps in Google App Engine.
- Manage your billing.
- Virtualize your projects with a customizable dashboard.

### Billing, labels, and quotas

**Resource Manager lets you hierarchically manage resources**

- **Billing and Resource Monitoring**:
    - Organization contains all billing accounts.
    - Project is associated with one billing account.
    - A resource belongs to one and only one project.
- **Identity and Access Management**:
    - Child policies cannot restrict access granted at the parent level.

**Resource quotas**

- Quotas **provide protection** against:
    - Cost overruns.
        - Can be indicators of bad code.
    - Other poorly behaved GCP customers.
- **Default quotas** may **increase as your use** of Google Cloud Platform expands over time.
- Most quotas are applied **per project**, based on **resource type and allocation**.             

**Why use Project quotas?**

- Prevent runaway consumption in case of an error or malicious attack.
- Prevent billing spikes or surprises.
- Forces sizing consideration and periodic review.

**Estimate costs with the Google Cloud Pricing Calculator**

**Budgets and email alerts**

**Labels are a utility for organizing GCP resources**

- Attached to resources: VM, disk, snapshot, image:
    - GCP Console, gcloud, or API.
- Example uses of labels:
    - Inventory.
    - Filter resources.
    - In scripts:
        - Help analyze costs.
        - Run bulk operations.    

**Use labels for ...**

- Team or Cost Center.
    - `team:marketing`
    - `team:research`
- Components:
    - `component:redis`
    - `component:frontend`
- Environment or stage:
    - `environment:prod`
    - `environment:test`
- Owner or contact:
    - `owner:gaurav`
    - `contact:opm`
- State:
    - `state:inuse`
    - `state:readyfordelete`                

**Visualize Google Cloud spend with Data Studio**

***

## Virtual Machines and Networks in the Cloud

### Regions and Zones

**GCP is organized into regions and zones**

- Region: europe-west2
- Zone: europe-west2-a, europe-west2-b, europe-west2-c 

**Spreading resources across zones in a region**

### Virtual Private Cloud (VPC) Network

**Projects and Virtual Private Cloud (VPC) network**

**VPC regional subnets**

**3 VPC Network Types**

- Default:
    - Every project
    - One subnet per region
    - Default firewall rules
- Auto Mode:
    - Default network
    - One subnet per region
    - Regional IP allocation
    - Fixed /20 subnetwork per region
    - Expandable up to /16    
- Custom Mode:
    - No default subnets created
    - Full control of IP ranges
    - Regional IP allocation
    - Expendable to any RFC 1918 size

**Firewall rules**

- VPC network functions as distributed firewall.
- Can use tags to globally apply rules.
- Firewall rules are stateful.
- Implied deny all ingress and allow all egress.

**A firewall rule is composed of...**

- `direction`:
    - Inbound connections are matched against `ingress` rules only.
    - Outbound connections are matched against `egress` rules only.
- `source or destination`:
    - For the `ingress` direction, `sources` can be specified as part of the rule with IP addresses, source tags, or a
    source service account.
    - For the `egress` direction, `destinations` can be specified as part of the rule with one or more ranges of 
    IP addresses.
- `protocol` and `port`:
    - Any rule can be restricted to apply to specific protocols only or specific combinations of protocols and ports only.
- `action`:
    - To allow or deny packets that match the direction, protocol, port, and source or destination of the rule.
- `priority`:
    - Governs the order in which rules are evaluated; the first matching rule is applied.

### Compute Engine Virtual Machines

**Compute Engine Features**

- Infrastructure as a Service (IaaS).
- No upfornt cost.
- Customizable and flexible.
- Fast startup time.

**VMs can have internal and external IP addresses**

**Internal IP**

- Allocated from subnet range to VMs by DHCP.
- DHCP lease is renewed every 24 hours.
- VM name + IP is registered with network-scoped DNS.

**External IP**

- Assigned from pool (ephemeral).
- Reserved (static).
- Billed when not attached to a running VM.
- VM doesn't know external IP; it is mapped to the internal IP.

**Standard machine types**

```
3.75 GB of memory
-----------------
    1 vCPU
```

**High-memory machine types**

```
6.5 GB of memory
----------------
    1 vCPU
```

**Memory-optimized machine types**

```
> 14 GB of memory
-----------------
    1 vCPU
```

**High-CPU machine types**

```
0.9 GB of memory
----------------
    1 vCPU
```

**Compute-optimized machine types**

```
Highest performance per vCPU 
(up to 3.8 Ghz sustained all-core turbo
```

**Shared-core machine types**

| | vCPUs | Memory (GB) |
|---|---|---|
| f1-micro | 0.2 | 0.60 |
| g1-small | 0.5 | 1.70 |

**Creating custom machine types**

- When to select custom:
    - Requirements fit between the predefined types.
    - Need more memory or more CPU.
- Customize the amount of memory and vCPU for your machine:
    - Either 1 vCPU or even number of vCPU.
    - 0.9 GB per vCPU, up to 6.5 GB per vCPU (default).
    - Total memory must be multiple of 256 MB.    

**Compute Engine Pricing Features**

- Billing in second increments:
    - For compute, data processing and other services.
- Discounts for sustained use:
    - Automatically applied to virtual machine use over 25% of a month.
- Discounts for committed use:
    - Pay less for long-term workloads.
- Preemptible machines:
    - Pay less for interruptible workloads.
    - Up to 30s shutdown notice.
    - 24h Max.
    - Up to 80% discount.

**Sole-tenant nodes physically isolate workloads**

- You can bring your own license!

### Persistent Disks and NICs

**Persistent disks**

- Network storage appearing as a block device:
    - Zonal or regional resource.
    - Durable storage: can survive VM terminate.
    - Bootable: you can attach to a VM and boot from it.
    - Performance: Scales with size.
- Features:
    - HDD (magnetic) or SSD (faster, solid-state) options.
    - Disk resizing: even running and attached!
    - Can be attached in read-only mode to multiple VMs.
    - Encryption keys:
        - Google-managed
        - Customer-managed
        - Customer-supplied

**Local SSD disks are physically attached to a VM**

- More IOPS, lower latency, higher throughput than persistent disk.
- 375-GB disk up to eight, total of 3 TB.
- Data survives a reset, but not a VM stop or terminate.
- VM-specific: cannot be reattached to a different VM.

**Summary of disk options**

| | Persistent disk HDD | Persistent disk SSD | Local SSD disk |
|---|---|---|---|
| Data redundancy | Yes | Yes | No |
| Encryption at rest | Yes | Yes | Yes |
| Bootable | Yes | Yes | No |
| Use case | General, bulk file storage | Random IOPS | High IOPS and low latency |

**Persistent disk maagement differences**

- Cloud Persistent Disk:
    - Single file system is best
    - Resize (grow) disks
    - Resize file system
    - Built-in snapshot service
    - Automatic encryption
- Computer Hardware Disk:
    - Partitioning
    - Reparation disk
    - Reformat
    - Redundant disk arrays
    - Subvolume management and snapshots
    - Encrypt files before write to disk    

**Networking**

- Network throughput scales at 2 Gbps per vCPU:
    - Max throughput of 32 Gbps with 16 vCPU.
    - Including persistent disk IOPS throughput.
- Up to 8 NIC, connected to different networks.
- Can not modify after creation.

### Interconnecting networks

**Google Cloud offers many interconnect options**    

- **VPN**: Secure multi-Gbps connection over VPN tunnels.
- **Dedicated Interconnect**: Connect up to 8 x 10 Gbps or 2 x 100 Gbps transport circuits for private cloud traffic to
Google Cloud at Google POPs.
- **Partner Interconnect**: Connectivity between your on-premises network and your VPC network through a supported
service provider.

**Cloud VPN securely connects your on-premises network to your Google Cloud VPC network**

- Useful for low-volume data connections.
- 99.9% SLA
- Supports:
    - Site-to-site VPN
    - Static routes
    - Dynamic routes (Cloud Router)
    - IKEv1 and IKEv2 ciphers

**Dynamic routing with Cloud Router**

**Dedicated Interconnect provides direct physical connections**

**Create a Dedicated Interconnect Connection**

- Order your Dedicated Interconnect.
- Send LOA-CFAs to your vendor.
- Create VLAN attachments and establish BGP sessions.
- Test Interconnect.

**Partner Interconnect provides connectivity through a supported service provider**

**Comparision of Interconnect options**

| Connection | Provides | Capacity | Requirements | Access Type |
|---|---|---|---|---|
| IPsec VPN tunnel | Encrypted tunnel to VPC net through the public internet | 1.5-3 Gbps per tunnel | On-premises VPN gateway | |
| Dedicated Interconnect | Dedicate, direct connection to VPC net | 10 Gbps or 100 Gbps per link | Connection in collocation facility | Internal IP addresses |
| Partner Interconnect | Dedicated bandwidth, connection to VPC network through a service provider |  50 Mbps - 10 Gbps per connection | Service provider | |

***

## Migrate

### Migrate for Compute Engine

- **Minimal Downtime**
- **Reduce Risk**
- **Rightsize Recommendations**

### Migration Technical Architecture

**Migrate for Compute Engine Manager**

**Migrate for Compute Engine Backend**

**AWS Importer**

**Cloud Extension**

### Installation Process

- Setup VPN + Network tags.
- Create GCP roles and service accounts.
- Deploy Migrate for Compute Engine Manager.
- Configure source environment.
- Deploy Cloud Extension.

***

## Governance

### User Identity Management

**Controlling access**

- Authentication: 
    - Cloud Identity:
        - Users
        - Groups
        - Org Units
- Authorization: 
    - Cloud IAM:
        - Access to Google Cloud resources.

**The Organization node**

**User provisioning options**

**Google Cloud Directory Sync (GCDS)**

- One-way synchronization.
- Only synchronizes deltas for faster possible provisioning.
- Configure which users accounts to sync.

**User authentication options**

- Google authentication: No SSO.
- Single sign-on (SSO): Google auth plus Cloud Identity as Identity Provider.
- Single sign-on (SSO): External Identity Provider.

**G Suite Password Sync (GSPS)**

- Synchronizes user password from Active Directory to Cloud Identity as they are changed (in real time).
- GSPS intercepts the raw password and applies a salted SHA512 hash.
- Encrypted via TLS, the salted hash is sent to Cloud Identity using the Directory API.














