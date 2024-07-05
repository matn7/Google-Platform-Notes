NEW SECTION - Compute Engine

Question 1) When using Create Deployment from Cloud Console, which of the following cannot be specified for a deployment?
Answer: Time to live (TTL). Time to Live is not an attribute of deployments. Application name, container image, and initial 
command can all be specified.

Question 2) Stackdriver Monitoring collects metrics such as query counts, execution times, scanned bytes,
and table count are captured for which GCP managed services ?
Answer: Cloud BigQuery. Stackdriver Monitoring collects metrics on the performance of infrastructure resources and applications. 
The mentioned metrics are captured for BigQuery.

Question 3) You are deploying an application that will need to scale and be highly available.
Which of these Compute Engine components will help achieve scalability and high availability?
Answer: Instance groups. Instance groups are sets of VMs that can be configured to scale and are used with load balancers, 
which contribute to improving availability. Preemptible instances are not highly available because they can be shut down 
at any time by GCP. Cloud Storage is not a Compute Engine component. GPUs can help improve throughput for math-intensive 
operations but do not contribute to high availability.

Question 4) Customers are complaining of long waits while booking airline tickets. There are many microservices in your
ticket processing system. You would like to view information about the time each microservice takes to run.
What Stackdriver service would you use?
Answer: Stackdriver Trace.
a) Stackdriver Trace is used to collect information about the time required to execute functions in a call stack. 
b) Stackdriver Logging is used to collect semi-structured data about events. 
c) Stackdriver Monitoring collects performance metrics. 
d) Stackdriver Debugger is used to inspect the state of running code.

Question 5) An architect has suggested a particular machine type for your workload. You are in the console creating
a VM and you don’t see the machine type in the list of available machine types. What could be the reason for this?
Answer: That machine type is not available in the zone you specified.
Different zones may have different machine types available, so you will need to specify a region first and then a zone 
to determine the set of machine types available. If the machine type does not appear in the list, it is not available 
in that zone. Subnets and IP addresses are not related to the machine types available. Unless you are specifying a custom 
machine type, you do not specify the amount of memory; that is defined by the machine type.

Question 6) What are the categories of Cloud Launcher solutions?
Answer: Data sets, operating systems, and developer tools.
Categories of solutions include all of the categories mentioned, so "Data sets, operating systems, 
and developer tools". Others include Kubernetes Apps, API & Services, and Databases.

Question 7) You notice that 5 VMs are processing the data at 11:00 AM GMT as compared to 7 VMs at 10:30 AM GMT.
What feature of managed instance groups is at work here?
Answer: Autoscaling.
Autoscaling adds or removes instances based on instance metrics and workloads.

Question 8) You are deploying a Python web application to GCP. The application uses only custom code and basic
Python libraries. You expect to have sporadic use of the application for the foreseeable future and want to minimize
both the cost of running the application and the DevOps overhead of managing the application.
Which computing service is the best option for running the application?**
Answer: App Engine standard environment.
The App Engine standard environment can run Python applications, which can autoscale down to no instances when there 
is no load and thereby minimize costs. 
Compute Engine and the App Engine flexible environment both require more configuration management than 
the App Engine standard environment. 
Kubernetes Engine is used when a cluster of servers is needed to support large or multiple applications using the 
same computing resources.

Question 9) The amount of time GCP will wait before collecting performance statistics from the instance for
Autoscaling is called as ?
Answer: Cool down period.

Question 10) Your management team is considering three different cloud providers. You have been asked to summarize
billing and cost information to help the management team compare cost structures between clouds. Which of the following
would you mention about the cost of VMs in GCP?**
Answer: VMs are billed in 1-second increments, cost varies with the number of CPUs and amount of memory
in a machine type, you can create custom machine types, preemptible VMs cost up to 80 percent less than standard VMs,
and Google offers discounts for sustained usage.

Question 11) You are attempting to execute commands to initiate a deployment on a Kubernetes cluster. The commands are
not having any effect. You suspect that a Kubernetes component is not functioning correctly. What component could be the problem?
Answer: The Kubernetes API.
All interactions with the cluster are done through the master using the Kubernetes API. 
If an action is to be taken on a node, the command is issued by the cluster master.

Question 12) You need to create multiple VMs which needs heterogeneous configurations as part of instance group.
How can we achieve that?
Answer: Create an unmanaged instance group.
Managed instance groups (MIGs) consist of identically configured VMs. 
Unmanaged instance groups allow for heterogeneous VMs.

Question 13) CPU utilization exceeding 70 percent for three minutes is an example of ?**
Answer: Alerting Policy.
In Stackdriver Monitoring, alerts are created using an alerting policy. CPU Utilization is one such example.

Question 14) Running BigTable clusters in different regions will _____ replication latency and ________ availability**
Answer: increase, increase.
When running the BigTable cluster across multiple region - you will ensure higher availability as you can handle region 
level failure. But it will also increase replication latency as data needs to be replicated across cluster in different region.

Question 15) Your application is running on Kubernetes cluster on GCP. You have configured autoscaling when the CPU
utilizations exceeds 75 percent. How is the CPU Utilizations calculated?
Answer: Based on all CPUs used by the deployment.
Replicas are added to the deployment and hence CPU Utilizations should be checked at deployment level. 
It would be incorrect to check the CPU at Cluster level as it would have one or more deployment running on it.

Question 16) An architect is moving a legacy application to Google Cloud and wants to minimize the changes to the
existing architecture while administering the cluster as a single entity. The legacy application runs on a load-balanced
cluster that runs nodes with two different configurations. The two configurations are required because of design decisions
made several years ago. The load on the application is fairly consistent, so there is rarely a need to scale up or down.
What GCP Compute Engine resource would you recommended using?
Answer: Unmanaged instance groups. 
Unmanaged instance groups are available for limited use cases such as this. Unmanaged instance groups are not recommended 
in general. Managed instance groups are the recommended way to use instance groups, but the two different configurations 
prevents their use. Preemptible instances and GPUs are not relevant to this scenario.

Question 17) Your company is based in X and will be running a virtual server for Y. What factor determines the unit per minute cost.
Answer: The characteristics of the server.
The characteristics of the server, such as the number of virtual servers, the amount of memory, and the region where you 
run the VM, influence the cost. Time of day is not a factor, nor is the type of application you run on the VM.

Question 18) What can be the basis for scaling up an instance group?
Answer: Network latency, load balancing capacity, and CPU utilization.
You can configure an autoscaling policy to trigger adding or removing instances based on CPU utilization, 
monitoring metric, load balancing capacity, or queue-based workloads. 
Disk, network latency, and memory can trigger scaling if monitoring metrics on those resources are configured.

Question 19) If a pod is terminated for being unhealthy—then the <WHAT> >will add pods until the desired state is reached.
Answer: Kubernetes ReplicaSets
A ReplicaSet is a controller that manages the number of pods running for a deployment. 
A deployment is a higher-level concept that manages ReplicaSets and provides declarative updates.

Question 20) Cloud Dataflow does require you to specify machine types when configuring a pipeline?
Answer: FALSE.
Cloud Dataflow does not require you to specify machine types, but you can specify machine type and worker disk type if 
you want that level of control.

Question 21) Your manager is making a presentation to executives in your company advocating that you start using
Kubernetes Engine. You suggest that the manager highlight all the features Kubernetes provides to reduce the workload
on DevOps engineers. You describe several features, including all of the following except which one?
- Automatic upgrading of cluster software as needed.
- Answer: Security scanning for vulnerabilities.
- Load balancing across Compute Engine VMs that are deployed in a Kubernetes cluster.
- Automatic scaling of nodes in the cluster. 
Kubernetes provides load balancing, scaling, and automatic upgrading of software. 
It does not provide vulnerability scanning. 
GCP does have a Cloud Security Scanner product, but that is designed to work with App Engine to identify common 
application vulnerabilities.

Question 22) A DevOps engineer is noticing a spike in CPU utilization on your servers. You explain you have just launched
a deployment. You’d like to show the DevOps engineer the details of a deployment you just launched. What command would you use?
Answer: 'gcloud deployment-manager deployments describe'
The correct answer is gcloud deployment-manager deployments describe, cloud-launcher is not the name of the service. 
Note: list displays a brief summary of each deployment and describe displays a detailed description.

Question 23) You are using snapshots to save copies of a 100GB disk. You make a snapshot and then add 10GB of data.
You create a second snapshot. How much storage is used in total for the two snapshots (assume no compression)?**
Answer: 110 GB, with 100 GB for the first and 10 GB for the second.
When you first create a snapshot, GCP will make a full copy of the data on the persistent disk. 
The next time you create a snapshot from that disk, GCP will only copy the data that has changed since 
the last snapshot. 
Note : GCP does not store a full copy of the second snapshot and the first snapshot is not deleted automatically. 
Also, subsequent snapshots do not incur 10 percent overhead.

Question 24) Before creating an instance group, you need to create what? 
Answer: Instance group template.
An instance group template is used to specify how the instance group should be created. 
Note: instances are created automatically when an instance group is created. 
Boot disk images and snapshots do not have to be created before creating an instance group.

Question 25) You need to set up a server with a high level of security. You want to be prepared in case of attacks
on your server by someone trying to inject a rootkit (a kind of malware that can alter the operating system).
Which option should you select when creating a VM?
Answer: Shield VM.
Shield VM is an advanced set of security controls that includes Integrity Monitoring, a check to ensure 
boot images have not been tampered with, which makes it the right answer. 
Firewalls are used to control ingress and egress of network traffic to a server or subnet. 
Project-wide SSH keys are used for authenticating users across servers within a project. 
Boot disk integrity check is a fictional feature.

Question 26) You have decided to delegate the task of making backup snapshots to a member of your team.
What role would you need to grant to your team member to create snapshots?
Answer: Compute Storage Admin.
To work with snapshots, a user must be assigned the Compute Storage Admin role. The other options are fictitious roles.

Question 27) You will be building a number of machine learning models on an instance and attaching GPU to the instance.
When you run your machine learning models they take an unusually long time to run. It appears that GPU is not being used.
What could be the cause of this?
Answer: GPU libraries are not installed.
To function properly, the operating system must have GPU libraries installed. The operating system does not have to be 
Ubuntu based, and there is no need to have at least eight CPUs in an instance before you can attach and use a GPU. 
Available disk space does not determine if a GPU is used or not.

Question 28) You lead a team of cloud engineers who maintain cloud resources for several departments in your company.
You’ve noticed a problem with configuration drift. Some machine configurations are no longer in the same state as they
were when created. You can’t find notes or documentation on how the changes were made or why.
What practice would you implement to solve this problem?
Answer: Write scripts using gcloud commands to change configuration and store those scripts in a version control system.
Using version-controlled scripts is the best approach of the four options. Scripts can be documented with reasons for 
the changes and they can be run repeatedly on different machines to implement the same change. 
This reduces the chance of error when manually entering a command.

Question 29) You have been asked by your manager to deploy a WordPress site. You expect heavy traffic, and your manager
wants to make sure the VM hosting the WordPress site has enough resources. Which resources can you configure when launching
a WordPress site using Cloud Launcher?
Answer: Disk type, Disk size, Machine type.
You can change the configuration of any of the items listed, so option D is correct. 
You can also specify firewall rules to allow both HTTP and HTTPS traffic or change the zone in which the VM runs.

Question 30) You have acquired a 10 GB data set from a third-party research firm. A group of data scientists would
like to access this data from their statistics programs written in R. R works well with Linux and Windows file systems,
and the data scientists are familiar with file operations in R. The data scientists would each like to have their own
dedicated VM with the data available in the VM’s file system. What is a way to make this data readily available on a VM
and minimize the steps the data scientists will have to take?
Answer: Create VMs using a source image created from a disk with the data on it.
10 GB of data is small enough to store on a single disk. By creating an image of a disk with the data stored on it, 
you can specify that source image when creating a VM. Other options would require the data scientist to copy the data 
from Cloud Storage to a disk on the VM. Google Drive would similarly require copying the data. loading into BigQuery 
would load data into a database, not a file system as specified in the requirements.

Question 31) A client has brought you in to help reduce their DevOps overhead. Engineers are spending too much time
patching servers and optimizing server utilization. They want to move to serverless platforms as much as possible.
Your client has heard of Cloud Functions and wants to use them as much as possible. You recommend all of the following
types of applications except which one?
Answer: Long-running data warehouse data load procedures.
Cloud Functions is best suited for event-driven processing, such as a file being uploaded to Cloud Storage or an event 
being writing to a Pub/Sub queue. Long-running jobs, such as loading data into a data warehouse, are better suited to 
Compute Engine or App Engine.

Question 32) The marketing department in your company wants to deploy a web application but does not want to have to
manage servers or clusters. A good option for them is?
Answer: App Engine.
App Engine is a PaaS that allows developers to deploy full applications without having to manage servers or clusters. 
Compute Engine and Kubernetes Engine require management of servers. 
Cloud Functions is suitable for short-running Node.js or Python functions but not full applications.

Question 33) The GCP service for storing and managing Docker containers is?
Answer: Container Registry.
The GCP service for storing and managing Docker containers is Container Registry. 
Cloud Build is for creating images.

Question 34) When we are moving services from "n1-standard-1" to "n1-standard-4" it is called as Vertical Scaling?
Answer: TRUE.
Vertical scaling, requires moving services from one VM to another VM with more or fewer resources

Question 35) Your team is developing a new pipeline to analyze a stream of data from sensors on manufacturing devices.
The old pipeline occasionally corrupted data because parallel threads overwrote data written by other threads.
You decide to use Cloud Functions as part of the pipeline. As a developer of a Cloud Function, what do you have to do to
prevent multiple invocations of the function from interfering with each other?
Answer: Nothing. GCP ensures that function invocations do not interfere with each other.
Each invocation of a cloud function runs in a secure, isolated runtime environment. 
There is no need to check whether other invocations are running. 
With the Cloud Functions service, there is no way for a developer to control code execution at the process 
or thread level.

Question 36) A new engineer is asking for clarification about when it is best to use Kubernetes and when to use
instance groups. You point out that Kubernetes uses instance groups. What purpose do instance groups play in a Kubernetes cluster?
Answer: They create sets of VMs that can be managed as a unit.
Kubernetes creates instance groups as part of the process of creating a cluster, which makes it the correct answer. 
Stackdriver, not instance groups, is used to monitor the health of nodes and to create alerts and notifications. 
Kubernetes creates pods and deployments; they are not provided by instance groups.

Question 37) Your company is about to release a new online service that builds on a new user interface experience
driven by a set of services that will run on your servers. There is a separate set of services that manage authentication
and authorization. A data store set of services keeps track of account information. All three sets of services must be
highly reliable and scale to meet demand. Which of the GCP services is the best option for deploying this?
Answer: Kubernetes Engine.
The scenario described is a good fit for Kubernetes. 
Each of the groups of services can be structured in pods and deployed using Kubernetes deployment. 
Kubernetes Engine manages node health, load balancing, and scaling. 

Question 38) Adding virtual machines to an instance group can be triggered in an autoscaling policy by which one?**
Answer:
a) CPU utilization.
b) Stackdriver metrics.
c) Load balancing serving capacity.
IAM policy violations do not trigger changes in the size of clusters. All other options can be used to trigger a change 
in cluster size.

Question 39) GCP compute Engine VMs can be created using?
Answer: 
a) Cloud Console.
b) REST API Request.
c) SOAP Request. 
d) Command line SDK.
VMs are provisioned using the cloud console, the command-line SDK, or the REST API. SOAP request is not correct choice.

Question 40) What command is used to run a Docker image on a cluster?
Answer: 'kubectl run'.
The kubectl command is used to control workloads on a Kubernetes cluster once it is created. gcloud is not used to 
manipulate Kubernetes processes. Note: beta is not required in kubectl commands.

Question 41) The Network tab of the create VM form is where you would perform which of the following operations?
Answer: Add a network interface to the VM.
In the Network tab of the VM form, you can add another network interface. GCP sets the IP address. There is no option 
to specify a router or change firewall rules on the Network tab.

Question 42) What command would you use to have 10 replicas of a deployment named ch07-app-deploy?
Answer: 'kubectl scale deployment ch07-app-deploy --replicas=10'
Use of gcloud and upgrade is incorrect. "scale" is the right command along with replicas.

Question 43) You have deployed an application to a Kubernetes cluster. You have noticed that several pods are starved
for resources for a period of time and the pods are shut down. When resources are available, new instantiations of those
pods are created. Clients are still able to connect to pods even though the new pods have different IP addresses from the
pods that were terminated. What Kubernetes component makes this possible?
Answer: Service.
Services provide a level of indirection to accessing pods. 
Pods are ephemeral. 
Clients connect to services, which can discover pods. 
ReplicaSets and StatefulSets provide managed pods. 
Alerts are for reporting on the state of resources.

Question 44) You have just opened the GCP console at console.google.com. You have authenticated with the user you want
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

Question 45) A software engineer comes to you for a recommendation. She has implemented a machine learning algorithm
to identify cancerous cells in medical images. The algorithm is computationally intensive, makes many mathematical
calculations, requires immediate access to large amounts of data, and cannot be easily distributed over multiple servers.
What kind of Compute Engine configuration would you recommend?
Answer: High memory, high CPU, GPU.
A computationally intensive application obviously requires high CPUs, but the fact that there are many mathematical 
calculations indicates that a GPU should be used. 
You might consider running this in a cluster, but the work is not easily distributed over multiple servers, 
so you will need to have a single server capable of handling the load. 
Immediate access to large amounts of data indicates that a high-memory machine should be recommended.

Question 46) Which command will delete an instance named ch06-instance-3?
Answer: 'gcloud compute instances delete ch06-instance-3'.
The command to delete an instance is gcloud compute instances delete followed by the name of the instance. 
Note : stop command stops but does not delete the instance.

Question 47) You have an application that uses a Pub/Sub message queue to maintain a list of tasks that are to be
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

Question 48) Your team is very new to Kubernetes and are migrating to GCP. Developers of the team are unfamiliar with
Kubernetes and does not understand why they cannot find a command to deploy a container. How would you describe the reason
why there is no deploy container command?
Answer: Kubernetes uses pods as the smallest deployable unit.
Kubernetes uses pods as the smallest deployable unit, and pods have usually one but possibly multiple containers that 
are deployed as a unit.

Question 49) Stackdriver Monitoring can collect metrics on Amazon Web Services ?
Answer: TRUE.
Stackdriver Monitoring collects metrics on the performance of infrastructure resources and applications. 
Resources can be in GCP as well as the Amazon Web Services cloud.

Question 50) A colleague has asked for your assistance setting up a test environment in Google Cloud.
They have never worked in GCP. You suggest starting with a single VM. Which of the following is the minimal set of
information you will need?
Answer: A name for the VM, a machine type, a region, and a zone.
The name of the VM, the region and zone, and the machine type can all be specified in the console along 
with other parameters. 
