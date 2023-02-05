# Cloud Security & Networking

**Question 1: You want all your network traffic to route over the Google network and not traverse the public Internet. 
What level of network service should you choose?**

- Google-only
- Standard
- :white_check_mark: **Premium**
- Non-internet

```
* Premium is the network service level that routes all traffic over the Google network. 
* Standard tier may use the public Internet when routing traffic. 
* There are no service tiers called Google-only or non-Internet.
```

**Question 2: You have a website hosted on a Compute Engine VM. Users can access the website using the domain name you 
provided. You do some maintenance work on the VM and stop the server and restart it. Now users cannot access the website. 
No other changes have occurred on the subnet. What might be the cause of the problem?**

- :white_check_mark: **You used an ephemeral instead of a static IP address.**
- The restart caused a change in the DNS record.
- Your subnet has changed.
- You do not have enough addresses available on your subnet.

```
* Stopping and starting a VM will release ephemeral IP addresses. 
* Use a static IP address to have the same IP address across reboots. 
* Rebooting a VM does not change a DNS record. 
* If you had enough addresses to get an address when you first started the VM and you then released that IP address, 
there should be at least one IP address assuming no other devices are added to the subnet.
```

**Question 3: You are using Cloud Console to create a VPN. You want to configure the GCP end of the VPN. What section of 
the Create VPN form would you use?**

- Tunnels
- :white_check_mark: **Google Compute Engine VPN**
- Routing Options
- IKE Version

```
* The Google Compute Engine VPN is where you specify information about the Google Cloud end of the VPN connection, 
so it is correct. 
* You specify name, description, network, region, and IP address. 
* Other options are incoorect because tunnels are about the connections between the cloud and the remote network, 
routing options is about how to configure routers, IKE Version is about exchanging secret keys.
```

**Question 4: A health check is used to check what resources?**

- Load balancer
- Persistent disks
- :white_check_mark: **VMs**
- Storage buckets

```
* Health checks monitor the health of VMs used with load balancers.
* Near line storage is a type of Cloud Storage, storage devices or buckets are not health checked.
```

**Question 5: What record type is used to specify the IPv4 address of a domain?**

- NS
- AAAA
- :white_check_mark: **A**
- SOA

```
* The A record is used to map a domain name to an IPv4 address. 
* AAAA record is used for IPv6 addresses. 
* NS is a name server record. 
* SOA is a start of authority record.
```

**Question 6: When setting up a network in GCP, your network the resources in it are treated as what?**

- :white_check_mark: **Virtual private cloud**
- Cluster
- Subdomain
- Non of the above

```
* When you create a network, it is treated as a virtual private cloud. 
* Resources are added to the VPC and are not accessible outside the VPC unless you explicitly configure them to be. 
* A subdomain is related to web domains and not related to GPC network configuration. 
* Clusters, such as Kubernetes clusters, may be in your network, but are not a characteristic of the network.
```

**Question 7: A VM instance is trying to read from a Cloud Storage bucket. Reading the bucket is allowed by IAM roles 
granted to the service account of the VM. Reading buckets is denied by the scopes assigned to the VM. What will happen 
if the VM tries to read from the bucket?**

- The application performing the read will skip over the read operation.
- The read operation will succeed, but message will be logged to Stackdriver Logging.
- :white_check_mark: **The read will not execute because both scopes and IAM roles are applied to determine what operations can be performed.**
- The read will execute because the most permissive permission is allowed.

```
Both scopes and IAM roles assigned to service accounts must allow an operation for it to succeed. 
Note: Access controls do not affect the flow of control in applications unless explicitly coded for that. 
Also, the most permissive permission is not used.
```

**Question 8: The gcloud command to create a custom role is what?**

- :white_check_mark: **`gcloud iam roles create`**
- `gcloud project roles create`
- `gcloud project create roles`
- `gcloud iam create roles`

```
The correct answer is gcloud iam roles create. 
Note: references to project instead of iam is incorrect. 
Also the correct order is roles and create i.e. the verb should come last.
```

**Question 9: The CEO of your startup just read a news report about a company that was attacked by something called 
cache poisoning. The CEO wants to implement additional security measures to reduce the risk of DNS spoofing and cache 
poisoning. What would you recommend?**

- :white_check_mark: **Using DNSSEC**
- Deleting CNAME records
- Adding SOA records
- Adding CNAME records

```
* DNSSEC is a secure protocol designed to prevent spoofing and cache poisoning. 
* SOA and CNAME records contain data about the DNS record; they are not an additional security measure.
* Deleting a CNAME record does not improve security.
```

**Question 10: What command is used to create a network load balancer at the command line?**

- :white_check_mark: **`gcloud compute forwarding-rules create`**
- `gcloud compute create forwarding-rules`
- `gcloud network create forwarding-rules`
- `gcloud network forwarding-rules create`

```
* The correct answer, is gcloud compute forwarding-rules create. 
* Other options are incorrect, service should be compute, not network. 
* Also create comes after forwarding-rules.
```

**Question 11: You want to create a VPN using Cloud Console. What section of Cloud Console should you use?**

- Compute Engine
- App Engine
- IAM & Admin
- :white_check_mark: **Hybrid Connectivity**

```
The VPC create option is available in the Hybrid Connectivity section, so it is correct. 
Compute Engine, App Engine, and IAM & Admin do not have features related to VPNs.
```

**Question 12: What launch stages are available when creating custom roles?**

- :white_check_mark: **Alpha, beta, general availability, and disabled**
- Disabled only
- General availability only
- Alpha and beta only

```
The four launch stages available are alpha, beta, general availability, and disabled.
```

**Question 13: A developer intern is confused about what roles are used for. You describe IAM roles as a collection of what?**

- Audit logs
- Access control lists
- Identities
- :white_check_mark: **Permissions**

```
* Roles are used to group permissions that can then be assigned to identities, roles do not have identities, 
but identities can be granted roles. 
* Roles do not use access control lists. 
* Roles do not include audit logs. 
* Logs are collected and managed by Stackdriver Logging.
```

**Question 14: When you navigate to IAM & Admin in Cloud Console, what appears in the main body of the page?**

- Members only
- Roles only
- :white_check_mark: **Members and roles assigned**
- Roles and permissions assigned

```
Members and their roles are listed.
```

**Question 15: A team is setting up a web service for internal use. They want to use the same IP address for the 
foreseeable future. What type of IP address would you assign?**

- Ephemeral
- :white_check_mark: **Static**
- External
- Internal

```
* Static addresses are assigned until they are released. 
* Internal and External addresses determine whether traffic is routed into and out of the subnet. 
* External addresses can have traffic reach them from the Internet; internal addresses cannot. 
* Ephemeral addresses are released when a VM shuts down or is deleted.
```

**Question 16: What gcloud command would you use to assign a service account when creating a VM?**

- :white_check_mark: **`gcloud compute instance create [INSTANCE_NAME] --service-account [SERVICE_ACCOUNT_EMAIL]`**
- `gcloud compute instance create [INSTANCE_NAME] [SERVICE_ACCOUNT_EMAIL]`
- `gcloud compute create instances-service-account [INSTANCE_NAME] [SERVICE_ACCOUNT_EMAIL]`
- `gcloud compute instances define-service-account [INSTANCE_NAME] [SERVICE_ACCOUNT_EMAIL]*`

```
You can assign a service account when creating a VM using the create command. 
Note: there is no create-service-account or define-service-account or instances-service-account command.
```

**Question 17: Where do you specify the ports on a TCP Proxy load balancer that should have their traffic forwarded?**

- Backend
- Network Services section
- :white_check_mark: **Frontend**
- VPC

```
* You specify ports to forward when configuring the frontend. 
* The backend is where you configure how traffic is routed to VMs. 
* Network Services is a high-level area of the console. 
* VPCs are not where you specify load balancer configurations.
```

**Question 18: VPCs are ______________ resources.**

- Subnet
- :white_check_mark: **Global**
- Zonal
- Regional

```
Google operates a global network, and VPCs are resources that can span that global network.
```

**Question 19: You want to limit traffic to a set of instances. You decide to set a specific network tag on each instance. 
What part of a firewall rule can reference the network tag to determine the set of instances affected by the rule?**

- :white_check_mark: **Target**
- Direction
- Priority
- Action

```
* The target can be all instances in a network, instances with particular network tags, or instances using a specific 
service account. 
* Action is either allow or deny. 
* Priority determines which of all the matching rules is applied.
* Direction specifies whether the rule is applied to incoming or outgoing traffic.
```

**Question 20: You are deploying a new custom application and want to delegate some administration tasks to DevOps engineers. 
They do not need all the privileges of a full application administrator, but they do need a subset of those privileges. 
What kind of role should you use to grant those privileges?**

- predefined
- primitive
- :white_check_mark: **custom**
- advanced

```
* Primitive roles only include the Owner, Editor, and View permissions. 
* Predefined roles are designed for GCP products and services, like App Engine and BigQuery. 
* For a custom application, you can create sets of privileges that give the user with that role as much permission 
as needed but not more.
```

**Question 21: An application development team is deploying a set of specialized service endpoints and wants to limit 
traffic so that only traffic going to one of the endpoints is allowed through by firewall rules. 
The service endpoints will accept any UDP traffic and each endpoint will use a port in the range of 20000–30000. 
Which of the following commands would you use?**

- `gcloud compute firewall-rules create fwr1 --direction=ingress`
- `gcloud network firewall-rules create fwr1 --allow=udp:20000-30000 --direction=ingress`
- `gcloud compute firewall-rules create fwr1 --allow=udp`
- :white_check_mark: **`gcloud compute firewall-rules create fwr1 --allow=udp:20000-30000 --direction=ingress`**

```
* The rule in "gcloud compute firewall-rules create fwr1 --allow=udp:20000-30000 --direction=ingress" uses the 
correct gcloud command and specifies the allow and direction parameters. 
* Other options are incorrect because they references gcloud network instead of gcloud compute or because it does not 
specify the port range or because it does not specify the protocol or port range.
```

**Question 22: What role gives users full control over Compute Engine instances?**

- :white_check_mark: **Compute Admin role**
- Compute Security Admin
- Compute Manager role

```
* Compute Engine Admin Role is the role that gives users complete control over instances. 
* Compute Engine Security Admin gives users the privileges to create, modify, and delete SSL certificates 
and firewall rules. 
* Other options are ficticious role
```

**Question 23: What part of a firewall rule determines whether a rule applies to incoming or outgoing traffic?**

- Priority
- Target
- Action
- :white_check_mark: **Direction**

```
* Direction specifies whether the rule is applied to incoming or outgoing traffic, which makes it the right answer. 
* Action is either allow or deny. 
* Target specifies the set of instances that the rule applies to. 
* Priority determines which of all matching rules is applied.
```

**Question 24: Which regional load balancer allows for load balancing based on IP protocol, address, and port?**

- :white_check_mark: **Network TCP/UDP**
- SSL Proxy
- TCP Proxy
- HTTP(S)

```
* Network TCP/UDP enables balancing based on IP protocol, address, and port. 
* Other options are all global load balancers, not regional ones.
```

**Question 25: What are the options for setting scopes in a VM?**

- Allow Full Access or Set Access for Each API only.
- :white_check_mark: **Allow Default Access, Allow Full Access, and Set Access for Each API.**
- Allow Default Access and Set Access For Each API only.
- Allow Default Access and Allow Full Access only.

```
The options for setting scopes are: Allow Default Access, Allow Full Access, and Set Access For Each API.
```

**Question 26: What options are available for filtering log messages when viewing audit logs?**

- :white_check_mark: **Resource, type of log, log level, and period time only.**
- Resource and period time only.
- Period time and log level only.
- Type of log only.

```
Logs can be filtered by resource, type of logs, log level, and period of time only.
```

**Question 27: What two components need to be configured when creating a TCP Proxy load balancer?**

- Frontend and forwarding rule.
- Forwarding rule and backend only.
- Backend and forwarding rule only.
- :white_check_mark: **Frontend and backend.**

```
* TCP Proxy load balancers require you to configure both the frontend and backend. 
* Forwarding rules are the one component specified with network load balancing. 
* There is no component known as a traffic rule.
```

**Question 28: You are designing an application that uses a series of services to transform data from its original form 
into a format suitable for use in a data warehouse. Your transformation application will write to the message queue as 
it processes each input file. You don’t want to give users permission to write to the message queue. 
You could allow the application to write to the message queue by using which of the following?**

- Folder
- Billing account
- Messaging account
- :white_check_mark: **Service account**

```
* Service accounts are designed to give applications or VMs permission to perform tasks. 
* Billing accounts are for associating charges with a payment method. 
* Folders are part of resource hierarchies and have nothing to do with enabling an application to perform a task. 
* Messaging accounts are a fictitious option.
```

**Question 29: You are configuring a load balancer and want to implement private load balancing. Which option would you select?**

- :white_check_mark: **Only Between My VMs**
- Enable Private
- Disable Public
- Local Only

```
In the console there is an option to select between 'From Internet To My VMs' and 'Only Between My VMs'. 
This is the option to indicate private or public. Other options are all fictitious parameters.
```

**Question 30: A team with four members needs you to set up a project that needs only general permissions for all resources. 
You are granting each person a primitive role for different levels of access, depending on their responsibilities in the project.
Which of the following are not included as primitive roles in Google Cloud Platform?**

- :white_check_mark: **publisher**
- editor
- owner
- viewer

```
Publisher is not a primitive role. Owner, Editor, and Viewer are the three primitive privileges in GCP.
```

**Question 31: A DevOps engineer is confused about the purpose of scopes. Scopes are access controls that are applied 
to what kind of resources?**

- Subnets
- Persistent dicks
- :white_check_mark: **VM instances**
- Storage buckets

```
Scopes are permissions granted to VM instances. Scopes in combination with IAM roles assigned to service accounts 
assigned to the VM instance determine what operations the VM instance can perform. 
Note: scopes do not apply to storage resources or to subnets.
```

**Question 32: You want to have GCP manage cryptographic keys, so you’ve decided to use Cloud Key Management Services. 
Before you can start creating cryptographic keys, you must:**

- Create folders and set up billing.
- Give all grantable roles to create keys.
- :white_check_mark: **Enable Google Cloud Key Management Service (KMS) API and set up billing.**
- Enable Google Cloud KMS API and create folders.

```
Enabling the Google Cloud KMS API and setting up billing are steps common to using GCP services.
```

**Question 33: You want the router on a tunnel you are creating to learn routes from all GCP regions on the network. 
What feature of GCP routing would you enable?**

- :white_check_mark: **Global dynamic routing**
- VPC
- Regional routing
- Firewall rules

```
* Global dynamic routing is used to learn all routes on a network. 
* Regional routing would learn only routes in a region. 
* Other options are incorrect because they are not used to configure routing options.
```

**Question 34: The command to create a VPC from the command line is:**

- `gcloud networks vpc create`
- `gcloud compute create networks`
- :white_check_mark: **`gcloud compute networks create`**
- `gsutil networks vpc create`

```
* The correct answer is 'gcloud compute networks create'. 
* 'networks vpc' is not a correct part of the command. 
* 'gsutil' is the command used to work with Cloud Storage. 
* There is no such thing as network create.
```

**Question 35: Which load balancers provide global load balancing?**

- :white_check_mark: **HTTP(S), SSL Proxy, and TCP Proxy.**
- SSL Proxy and TCP Proxy only.
- Internal TCP/UDP, HTTP(S), SSL Proxy, and TCP Proxy.
- HTTP(S) only.

```
The three global load balancers are HTTP(S), SSL Proxy, and TCP Proxy. Internal TCP/UD is a regional load balancer.
```

**Question 36: An app for a finance company needs access to a database and a Cloud Storage bucket. 
There is no predefined role that grants all the needed permissions without granting some permissions that are not needed. 
You decide to create a custom role. When defining custom roles, you should follow which of the following principles?**

- Rotation of duties.
- Defense in depth.
- :white_check_mark: **Least privilege**
- Least principle.

```
* Users should have only the privileges that are needed to carry out their duties, 
this is the principle of least privilege. 
* Rotation of duties is another security principle related to having different people perform a task at 
a different times. 
* Defense in depth is the practice of using multiple security controls to protect the same asset. 
* Least Principle is not a real security principal. 
* This make "Least privilege" the correct answer
```

**Question 37: During an incident that has caused an application to fail, you suspect some resource may not have 
appropriate roles granted. The command to list roles granted to a resource is:**

- :white_check_mark: **`gcloud iam list-grantable-roles`**
- `gcloud resources grantable-roles`
- `gcloud list-grantable-roles`
- `gutil iam list-grantable-roles`

```
gcloud is the command-line tool and for working with IAM we need to add "iam", and list-grantable-roles 
is the correct command.
```

**Question 38: _____________________ routes traffic only over Google’s global network.**

- Advanced Tier
- Basic Tier
- Flexible Tier
- :white_check_mark: **Premium Tier**
- Standard Tier

```
* Premium Tier routes traffic only over Google’s global network. 
* When using the Standard Tier, your data is subject to the reliability of the public Internet. 
* Rest all not Valid Options for networking tier in GCP.
```

**Question 39: You are using gcloud to create a firewall rule. Which command would you use?**

- `gcloud network firewall-rules create`
- `gcloud network rules create`
- `gcloud compute rules create`
- :white_check_mark: **`gcloud compute firewall-rules create`**

```
* The product you are working with is compute and the resource you are creating is a firewall rule, so 
'gcloud compute firewall-rules create' is correct. 
* Other options references network instead of compute.
* One option references rules instead of firewall-rules, which makes it incorrect.
```

**Question 40: What command is used to create a DNS zone in the command line?**

- `gcloud beta managed-zones create`
- `gcloud beta dns create managed zones`
- `gsutil dns managed-zones create`
- :white_check_mark: **`gcloud beta dns managed-zones create`**

```
* The correct answer, is 'gcloud beta dns managed-zones create'. 
* gsutil command which is used to work with Cloud Storage. 
* Other options are incorrect, it is missing the term dns or the ordering of terms is incorrect.
```

**Question 41: You have been asked to create a network with 1,000 IP addresses. In the interest of minimizing unused 
IP addresses, which CIDR suffix would you use to create a network with at least 1,000 addresses but no more than necessary?**

- `/20`
- `/28`
- :white_check_mark: **`/22`**
- `/28`

```
32 - 22 = 10
2^10 = 1024
1024 - 2 = 1022 (- 2 is broadcast and ??? address)
The /22 suffix produces 1,022 usable IP addresses.
```

**Question 42: You have created a subnet called sn1 using 192.168.0.0. You want it to have 14 addresses. 
What prefix length would you use?**

- 32
- 20
- :white_check_mark: **28**
- 16

```
* The prefix length specifies the length in bits of the subnet mask. 
* The remaining bits of the IP address are used for device addresses. 
* Since there are 32 bits in an IP address, you subtract the length of the mask to get the number of bits used to 
represent the address. 
* 16 is equal to 24, so you need 4 bits to represent 14 addresses. 
* 32-4 is 28 is the correct answer.
```

**Question 43: You are meeting with an auditor to discuss security practices in the cloud. The auditor asks how you 
implement several best practices. You describe how IAM predefined roles help to implement which security best practice(s)?**

- Defense in depth
- Least privilege
- :white_check_mark: **Least privileges & Separation of duties**
- Separation of duties

```
* Predefined roles help implement both least privilege and separation of duties. 
* Predefined roles do not implement defense in depth by themselves but could be used with other security controls to 
implement defense in depth.
```

**Question 44: ___________ uses the public Internet network to transfer data between Google data centers**

- Premium Tier
- Basic Tier
- Advance Tier
- :white_check_mark: **Standard Tier**
- Flexible Tier

```
* When using the Standard Tier, your data is subject to the reliability of the public Internet. 
* Premium Tier routes traffic only over Google’s global network. 
* Rest all not Valid Options for networking tier in GCP.
```

**Question 45: You are tasked with mapping the authentication and authorization policies of your on-premises applications 
to GCP’s authentication and authorization mechanisms. The GCP documentation states that an identity must be authenticated 
in order to grant privileges to that identity. What does the term identity refer to?**

- Role
- Set of Privileges
- :white_check_mark: **User**
- VM ID

```
* Identities are abstractions of users. 
* They can also represent characteristics of processes that run on behalf of a human user or a VM in the GCP. 
* Identities are not related to VM IDs. 
* Roles are collections of privileges that can be granted to identities.
```

**Question 46: At what levels of the resource hierarchy can a shared VPC be created?**

- Folders and subnets
- Folders and resources
- Organizations and project
- :white_check_mark: **Organization and folders**

```
* Shared VPCs can be created at the organization or folder level of the resource hierarchy. 
* Shared VPCs are not created at the resource or project levels. 
* Shared VPCs are not applied at subnets, which are resources in the resource hierarchy.
```

**Question 47: You want to implement interproject communication between VPCs. Which feature of VPCs would you use to implement this?**

- VPN
- Interconnect
- :white_check_mark: **VPC peering**
- Interproject peering

```
* VPC is used for interproject communications. 
* There is no interproject peering. 
* VPN and Interconnect have to do with linking on-premise networks with networks in GCP.
```

**Question 48: Why are primitive roles classified in a category in addition to IAM?**

- They were created after IAM.
- :white_check_mark: **They were created before IAM.**
- They are not related to access control.
- They are part of IAM.

```
* Primitive roles were created before IAM and provided coarse-grained access controls. 
* IAM is the newer form of access control.
```

**Question 49: You have been asked to set up network security in a virtual private cloud. Your company wants to have 
multiple subnetworks and limit traffic between the subnetworks. Which network security control would you use to control 
the flow of traffic between subnets?**

- IP address table
- :white_check_mark: **Firewall**
- Router
- Identity access management

```
* Firewalls in Google Cloud Platform (GCP) are software-defined network controls that limit the flow of traffic 
into and out of a network or subnetwork. 
* Routers are used to move traffic to appropriate destinations on the network. 
* Identity access management is used for authenticating and authorizing users; it is not relevant to network controls 
between subnetworks. 
* IP address tables are not a security control.
```

**Question 50: Your company has just started using GCP, and executives want to have a dedicated connection from your data 
center to the GCP to allow for large data transfers. Which networking service would you recommend?**

- Google Cloud Carrier Interconnect Peering
- Google Cloud Interconnect Peering
- Google Cloud DNS
- :white_check_mark: **Google Cloud Interconnect - Dedicated**

```
Google Cloud Interconnect – Dedicated is the only option for a dedicated connection between a customer’s
data center and a Google data center.
```
