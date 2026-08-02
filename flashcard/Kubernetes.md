# NEW SECTION – CONTAINERS

### 1. What is a Container? How is it different from a Virtual Machine?

* A container is a lightweight package containing an application and its dependencies.
* Unlike a VM, it shares the host OS kernel, making it faster and more resource-efficient.

### 2. What is Docker?

* Docker is a platform for building, running, and managing containers.
* It simplifies container creation using **Dockerfiles** and is widely used for application deployment.

### 3. What is Container Orchestration?

* Container orchestration automates deploying, scaling, and managing containers.
* Common tools include **Kubernetes**, **Docker Swarm**, and **Apache Mesos**.

### 4. What is a Container Registry?

* A container registry stores and distributes container images.
* Developers push and pull versioned images.
* Examples: **Docker Hub** and **Google Container Registry**.

### 5. What are Namespaces?

* Linux namespaces isolate container resources (processes, network, files, users), ensuring containers run
  independently.

### 6. How do Containers Communicate?

* Containers communicate through networking (bridge, overlay, or host networks).
* They can also expose ports for external access.

### 7. What is the Difference Between a Container Image and a Container?

* A **container image** is a static template containing the application and dependencies.
* A **container** is the running instance of that image.

### 8. What is a Dockerfile?

* A Dockerfile is a script that defines how to build a Docker image, including software installation,
  environment variables, and startup commands.

### 9. What are Microservices? Why are Containers Useful?

* Microservices split an application into independent services.
* Containers provide isolation, portability, and easy scaling for each service.

### 10. How are Containers Monitored?

* Containers are monitored using metrics like CPU, memory, disk, and network usage.
* Popular tools include **Prometheus**, **Grafana**, and **cAdvisor**.

# NEW SECTION – KUBERNETES COMPONENTS

### 1. What are the Main Components of a Kubernetes Cluster?

* A Kubernetes cluster consists of the **Control Plane** (manages the cluster) and **Worker Nodes** (run applications).

### 2. What is `kube-apiserver`?

* The API server is the entry point to Kubernetes.
* It handles API requests and manages cluster resources.

### 3. What is `etcd`?

* `etcd` is a distributed key-value store that keeps all Kubernetes cluster data and configuration.

### 4. What is `kube-scheduler`?

* The scheduler assigns Pods to the most suitable worker nodes based on available resources and constraints.

### 5. What is `kube-controller-manager`?

* It runs controllers that continuously compare the current cluster state with the desired state and make corrections.

### 6. What is `kubelet`?

* `kubelet` is the agent on each worker node that ensures Pods and containers are running as specified.

### 7. What is `kube-proxy`?

* `kube-proxy` manages network rules, enabling communication between Pods and Services.

### 8. What is a Kubernetes Pod?

* A Pod is the smallest deployable unit in Kubernetes, containing one or more containers that share networking and
  storage.

### 9. What is a Container Runtime?

* The container runtime (e.g., **containerd**, **CRI-O**, Docker) pulls images and runs containers.

### 10. What is a Kubernetes Service?

* A Service provides a stable IP and DNS name for a group of Pods, enabling communication and load balancing.

# NEW SECTION – KUBERNETES INSTALLATION

### 1. What are the Prerequisites for Installing Kubernetes?

* Linux machines (physical/VM), a container runtime (e.g., Docker/containerd), network connectivity, and **kubectl**.

### 2. What is `kubeadm`?

* `kubeadm` is a tool that automates Kubernetes cluster setup, including the control plane and node configuration.

### 3. How do you Install Kubernetes with `kubeadm`?

1. Install **kubeadm**, **kubelet**, and **kubectl**.
2. Run `kubeadm init`.
3. Configure `kubectl` (`kubeconfig`).
4. Install a CNI network plugin.
5. Join worker nodes using the generated join command.

### 4. What is Minikube?

* Minikube runs a single-node Kubernetes cluster locally for learning and testing.

### 5. How are Network Plugins Configured?

* Install a CNI plugin (e.g., **Calico**, **Flannel**, **Weave**) using `kubectl apply`.

### 6. How is High Availability (HA) Achieved?

* Use multiple control-plane nodes, a load balancer, and a replicated **etcd** cluster.

### 7. Can Kubernetes Run on Any Cloud Platform?

* Yes. Use managed services (**EKS**, **AKS**, **GKE**) or install manually with **kubeadm**.

### 8. What is Helm?

* Helm is Kubernetes' package manager that installs and manages applications using **Helm Charts**.

### 9. How do you Upgrade a Kubernetes Cluster?

* Upgrade the control plane first, then worker nodes, typically using **kubeadm**.

### 10. What are Common Kubernetes Installation Challenges?

* Network configuration, compatibility issues, HA setup, security, and limited system resources.

# NEW SECTION – DEPLOYMENTS

### 1. What is a Kubernetes Deployment?

* A Deployment manages Pods declaratively, ensuring the desired number of replicas and handling updates and rollbacks.

### 2. How do you Update a Deployment?

* Modify the Deployment (e.g., change the container image).
* Kubernetes performs a **rolling update** automatically.

### 3. What are Rolling Updates and Rollbacks?

* **Rolling updates** replace Pods gradually with minimal downtime.
* **Rollbacks** restore the previous stable version if needed.

### 4. How are Deployments Scaled?

* Scale manually with `kubectl scale` or automatically using the **Horizontal Pod Autoscaler (HPA)**.

### 5. What Happens if a Node Fails?

* Kubernetes recreates lost Pods on healthy nodes to maintain the desired replica count.

### 6. How do you Monitor a Deployment?

- `kubectl get deployments`.
- `kubectl describe deployment`.
- `kubectl rollout status`.

### 7. What is the Desired State?

* The desired state defines how the application should run (replicas, image, resources).
* Kubernetes continuously matches the actual state to it.

### 8. What is a ReplicaSet?

* A ReplicaSet ensures the required number of Pod replicas are running.
* Deployments manage ReplicaSets.

### 9. How are Environment-Specific Configurations Managed?

* Use **ConfigMaps** for configuration and **Secrets** for sensitive data.

### 10. What are Probes?

- **Liveness Probe:** Restarts unhealthy containers.
- **Readiness Probe:** Determines when a container is ready to receive traffic.

# NEW SECTION – STATEFULSET

### 1. What is a StatefulSet? How is it Different from a Deployment?

* A StatefulSet manages **stateful applications** requiring stable identities and persistent storage.
* Unlike Deployments, Pods have fixed names and storage.

### 2. How is Pod Identity Managed?

* Each Pod gets a unique ordinal name (e.g., `app-0`, `app-1`) and a stable DNS name that persists after restarts.

### 3. How do StatefulSets Handle Scaling and Updates?

* Pods are created, updated, and deleted **one at a time** in order.
* Scaling down occurs in reverse order.

### 4. What is the Role of Persistent Volumes?

* Each Pod gets its own **Persistent Volume Claim (PVC)**, ensuring data persists even if the Pod is recreated.

### 5. What is a Headless Service?

* A headless Service (`clusterIP: None`) provides stable DNS names for each StatefulSet Pod.

### 6. How is Pod Ordering Maintained?

* StatefulSets create and terminate Pods in a predictable order using ordinal indexes and a headless Service.

### 7. What Happens if a Node Fails?

* Pods keep their identity and storage.
* Kubernetes restores them while preserving their associated data.

### 8. How are StatefulSets Updated?

* They use **rolling updates**, updating Pods sequentially to maintain application consistency.

### 9. What is `podManagementPolicy`?

- **OrderedReady (default):** Pods start one by one.
- **Parallel:** Pods start or stop simultaneously.

### 10. What are StatefulSet Backup Best Practices?

* Use **Persistent Volume snapshots**, backup tools (e.g., **Velero**), replication, and external backup storage.

# NEW SECTION – REPLICASET.

### 1. What is a ReplicaSet?

* A ReplicaSet ensures a specified number of identical Pod replicas are always running.

### 2. How is a ReplicaSet Different from a ReplicationController?

* ReplicaSets support **set-based label selectors**, making them more flexible than ReplicationControllers.

### 3. How do you Create a ReplicaSet?

* Define it in a YAML file and create it with:

```bash
kubectl apply -f replicaset.yaml
```

### 4. What Happens if a Pod Fails?

* The ReplicaSet automatically creates a replacement Pod to maintain the desired replica count.

### 5. How do you Scale a ReplicaSet?

* Update the `replicas` field or run:

```bash
kubectl scale replicaset <name> --replicas=<number>
```

### 6. How does a ReplicaSet Work with a Deployment?

* Deployments manage ReplicaSets, providing rolling updates, rollbacks, and scaling.

### 7. What are ReplicaSet Use Cases?

* Maintaining Pod availability, scaling stateless applications, and providing fault tolerance.

### 8. How do you Update Pods in a ReplicaSet?

* Modify the Pod template, but **Deployments** are recommended for safe rolling updates.

### 9. What is the Role of Label Selectors?

* Label selectors identify which Pods are managed by the ReplicaSet.

### 10. How do ReplicaSets Improve High Availability?

* Run multiple replicas across different nodes to ensure applications remain available during failures.

# NEW SECTION – PODS

### 1. What is a Pod?

* A Pod is the smallest deployable unit in Kubernetes.
* It contains one or more containers sharing network and storage.

### 2. How do Pods Communicate?

* Pods communicate using their unique IP addresses.
* Containers within the same Pod also share networking and volumes.

### 3. What is the Pod Lifecycle?

* Pod phases are:
    - **Pending**.
    - **Running**.
    - **Succeeded**.
    - **Failed**.
    - **Unknown**.

### 4. What is the Difference Between a Pod and a Container?

* A **container** runs an application, while a **Pod** is a Kubernetes object that hosts one or more containers.

### 5. How are Resources Allocated?

* Pods use **CPU** and **memory requests/limits** defined in the Pod specification.

### 6. What are Multi-Container Pods?

* Pods with multiple tightly coupled containers that share networking and storage (e.g., app + sidecar).

### 7. How are Pods Scaled?

* Pods are scaled using **Deployments**, **ReplicaSets**, or **StatefulSets**.

### 8. What are Init Containers?

* Init containers run **before** application containers to perform setup tasks, then exit.

### 9. How Does Kubernetes Handle Pod Failures?

* Controllers automatically recreate failed Pods to maintain the desired state.

### 10. How do you Expose a Pod Externally?

* Use a **Service** (e.g., **NodePort** or **LoadBalancer**) to provide external access to the Pod.

# NEW SECTION – JOBS.

### 1. What is a Kubernetes Job?

* A Job runs a task to **completion** (e.g., batch processing, backups, data migration).

### 2. How is a Job Different from a Pod?

* A Pod runs a container, while a Job manages Pods until the task completes successfully.

### 3. What is the Lifecycle of a Job?

* A Job creates Pods, monitors them, retries failed Pods (if configured), and completes after the required successful
  executions.

### 4. What is a CronJob?

* A CronJob schedules Jobs to run automatically at specified times, similar to Linux `cron`.

### 5. How do you Monitor a Job?

- `kubectl describe job <job-name>`
- `kubectl get pods --selector=job-name=<job-name>`

### 6. What Happens After a Job Completes?

* Completed Pods remain by default for log inspection but can be automatically cleaned up using **TTL**.

### 7. How do you Control Parallel Execution?

- **completions** – total successful Pods required.
- **parallelism** – maximum Pods running simultaneously.

### 8. How are Jobs Used for Work Queues?

* Multiple Pods process queue items in parallel until all tasks are completed.

### 9. How are Job Failures Handled?

* Failed Pods are retried based on **restartPolicy** and **backoffLimit**.

### 10. Can a Job's Pod Template be Updated?

* **No.**
* The Pod template is immutable.
* Create a new Job to apply changes.

# NEW SECTION – NAMESPACES.

### 1. What are Kubernetes Namespaces?

* Namespaces logically divide a Kubernetes cluster to organize and isolate resources for different teams, projects, or
  environments.

### 2. How do Namespaces Affect Resource Allocation?

* They support **resource quotas** and isolation, preventing one namespace from consuming excessive cluster resources.

### 3. Can Different Namespaces Communicate?

* Yes.
* Services can communicate using their **FQDN**.
* **Network Policies** can restrict cross-namespace traffic.

### 4. What is the Default Namespace?

* `default` is the namespace used when no namespace is specified.

### 5. How do you Create and Manage Namespaces?

```bash
kubectl create namespace <name>
kubectl delete namespace <name>
```

* Use **RBAC** and **Resource Quotas** to manage access and resources.

### 6. What are Resource Quotas?

* Resource Quotas limit CPU, memory, storage, and object counts (Pods, Services, PVCs) per namespace.

### 7. What are Namespace Best Practices?

* Separate **dev**, **staging**, and **production**; apply **RBAC**, **Resource Quotas**, and **Network Policies**.

### 8. How do Labels and Annotations Work?

* **Labels** organize/select namespaces, while **annotations** store additional metadata.

### 9. Can Resources be Moved Between Namespaces?

* Not directly.
* Resources must usually be recreated in the target namespace.

### 10. How is Security Managed in Namespaces?

* Use **RBAC**, **Network Policies**, and **Security Contexts** to control access and isolate workloads.

# NEW SECTION – SERVICES.

### 1. What is a Kubernetes Service?

* A Service provides a stable IP, DNS name, and load balancing for a group of Pods.

### 2. What are the Types of Kubernetes Services?

- **ClusterIP:** Internal access only (default).
- **NodePort:** Exposes the Service on each node.
- **LoadBalancer:** External access via a cloud load balancer.
- **ExternalName:** Maps to an external DNS name.

### 3. How do Services Discover Pods?

* Services use **label selectors** to find matching Pods and automatically route traffic to them.

### 4. What are Endpoints?

* Endpoints store the IP addresses and ports of Pods behind a Service and update automatically.

### 5. What is a NodePort Service?

* NodePort exposes a Service on a fixed port on every node, allowing external access.

### 6. What is a LoadBalancer Service?

* LoadBalancer provides a cloud-managed external IP, unlike **ClusterIP** (internal) and **NodePort** (node-based).

### 7. How do you Secure a Service?

* Use **Network Policies**, **TLS/SSL**, and **authentication/authorization**.

### 8. Can a Service be Updated Without Downtime?

* Yes.
* Most changes (e.g., labels) are seamless, but changing ports or type may require creating a new Service.

### 9. What is a Headless Service?

* A headless Service (`clusterIP: None`) provides direct DNS access to individual Pods.

### 10. How do Services Work with StatefulSets?

* Headless Services provide stable DNS names and network identities for StatefulSet Pods.

# NEW SECTION – PROBES.

### 1. What are Probes in Kubernetes?

* Probes are health checks used to determine a container's status and whether it should receive traffic or be restarted.

### 2. What is the Difference Between Liveness and Readiness Probes?

- **Liveness Probe:** Restarts unhealthy containers.
- **Readiness Probe:** Determines if a Pod is ready to receive traffic.

### 3. What is a Startup Probe?

* A Startup Probe checks whether a slow-starting application has finished starting.
* Liveness and Readiness probes wait until it succeeds.

### 4. What Types of Probes are Available?

- **HTTP GET**.
- **TCP Socket**.
- **Exec Command**.

### 5. How are Probes Configured?

* Configure probes in the Pod spec using parameters like `initialDelaySeconds`, `periodSeconds`, `timeoutSeconds`,
  `successThreshold`, and `failureThreshold`.

### 6. Can Probes Affect Performance?

* Yes.
* Very frequent probes increase CPU, memory, and network usage.

### 7. What is `initialDelaySeconds`?

* It specifies how long Kubernetes waits before performing the first probe, preventing unnecessary failures during
  startup.

### 8. What Happens if a Liveness Probe Fails?

* Kubernetes restarts the container after the configured failure threshold is reached.

### 9. When Should You Avoid Liveness Probes?

* Avoid them for applications with temporary failures or slow startup, where unnecessary restarts may occur.

### 10. How do Readiness Probes Affect Load Balancing?

* Only Pods with successful readiness probes receive traffic from Services and Ingress.

# NEW SECTION – HELM.

### 1. What is Helm?

* Helm is the **package manager for Kubernetes**.
* It uses **Charts** to simplify application deployment and management. (Helm 3 is **Tiller-less**.)

### 2. How is Helm 3 Different from Helm 2?

* Helm 3 removes **Tiller**, improving security, simplifies dependency management, and enhances release handling.

### 3. What are Helm Charts?

* Helm Charts are reusable packages containing Kubernetes manifests and configuration for deploying applications.

### 4. What are Chart Hooks?

* Hooks run tasks during chart lifecycle events (install, upgrade, delete), such as database migrations or cleanup.

### 5. How are Dependencies Managed?

* Dependencies are defined in `Chart.yaml` under the `dependencies` section.

### 6. What is a Helm Repository?

* A Helm Repository stores and distributes Helm Charts for installation and sharing.

### 7. How do you Update a Helm Release?

```bash
helm upgrade <release> <chart>
```

### 8. What is Helm Rollback?

* Helm Rollback restores a previous release version:

```bash
helm rollback <release> <revision>
```

### 9. How do you Create a Custom Helm Chart?

1. `helm create <chart-name>`.
2. Edit `Chart.yaml` and `values.yaml`.
3. Modify templates.
4. Test with `helm install --dry-run --debug`.

### 10. How does Helm Manage Releases?

* Helm tracks application releases, revisions, upgrades, and rollbacks, making deployments easier to manage.

# NEW SECTION – DEPLOYMENT PATTERNS.

### 1. What is a Kubernetes Deployment?

* A Deployment manages Pods and ReplicaSets, supporting declarative updates, scaling, rollouts, and rollbacks.

### 2. What is Blue/Green Deployment?

* Two identical environments (**Blue** and **Green**) run simultaneously.
* Traffic switches to the new version after validation, enabling quick rollback.

### 3. What is Canary Deployment?

* A new version is released to a small percentage of users first.
* Traffic gradually increases if the deployment is successful.

### 4. What is a Rolling Update?

* The default update strategy that gradually replaces old Pods with new ones while maintaining application availability.

### 5. How is A/B Testing Implemented?

* Different user groups are routed to different application versions using **Ingress** or a **Service Mesh** (e.g.,
  Istio).

### 6. How is a StatefulSet Different from a Deployment?

* StatefulSets manage **stateful** applications with stable identities and persistent storage, while Deployments
  manage **stateless** workloads.

### 7. What is Shadow Deployment?

* Production traffic is duplicated to a new version for testing without affecting user responses.

### 8. What are Jobs and CronJobs?

- **Job:** Runs a task to completion once.
- **CronJob:** Runs Jobs on a scheduled basis.

### 9. How do you Roll Back a Deployment?

```bash
kubectl rollout undo deployment <deployment-name>
```

### 10. What is the Declarative Approach?

* Define the desired application state in YAML/JSON, and Kubernetes automatically maintains that state.

# NEW SECTION – AUTOSCALING.

### 1. What is Autoscaling in Kubernetes?

* Autoscaling automatically adjusts the number of **Pods** or **nodes** based on workload demand to improve performance
  and reduce costs.

### 2. What is Horizontal Pod Autoscaler (HPA)?

* HPA automatically scales the number of Pods based on **CPU**, **memory**, or **custom/external metrics**.

### 3. What is Vertical Pod Autoscaler (VPA)?

* VPA automatically adjusts a Pod's **CPU** and **memory requests/limits** based on usage.

### 4. What is Cluster Autoscaler?

* Cluster Autoscaler adds or removes **worker nodes** based on pending Pods and node utilization.

### 5. What is the Role of Metrics Server?

* Metrics Server collects **CPU** and **memory** usage data used by HPA and VPA.

### 6. What are Custom and External Metrics?

- **Custom Metrics:** Application metrics (e.g., requests/sec).
- **External Metrics:** Metrics from outside the cluster (e.g., cloud queues).

### 7. How do you Configure Autoscaling?

* Create **HPA** or **VPA** resources, define target workloads, metrics, and minimum/maximum limits.

### 8. What is the Difference Between Horizontal and Vertical Scaling?

- **Horizontal Scaling:** Add/remove Pods.
- **Vertical Scaling:** Increase/decrease CPU and memory per Pod.

### 9. Can Autoscaling Work with Stateful Applications?

* Yes, but it requires careful handling of persistent storage and application state.

### 10. What Should You Consider When Configuring Autoscaling?

* Workload type, scaling metrics, min/max limits, application behavior, performance, and cost.

# NEW SECTION – SECURITY.

### 1. How do Namespaces Improve Security?

* Namespaces isolate resources and enable separate **RBAC**, quotas, and policies for different teams or environments.

### 2. What is RBAC?

* **Role-Based Access Control (RBAC)** grants permissions to users, groups, or service accounts based on assigned roles.

### 3. What is Pod Security Policy (PSP)?

* PSP controlled Pod security settings (privileges, host access, etc.).
* **Note:** PSP is **deprecated** and replaced by **Pod Security Admission (PSA)**.

### 4. What are Network Policies?

* Network Policies control which Pods can communicate with each other and external networks.

### 5. What are Kubernetes Secrets?

* Secrets securely store sensitive data such as passwords, API keys, certificates, and tokens.

### 6. How is Certificate Management Handled?

* Kubernetes uses **TLS certificates** (issued by a CA) to secure communication between cluster components.

### 7. What are Kubernetes Security Best Practices?

* Use **RBAC**, **Namespaces**, **Network Policies**, **Pod Security Admission (PSA)**, image scanning, regular updates,
  and secret management.

### 8. Why is Container Image Security Important?

* Scan images for vulnerabilities, use trusted registries, and sign images to ensure integrity.

### 9. How are Security Logging and Monitoring Implemented?

* Collect logs and metrics using tools like **ELK Stack**, **Prometheus**, and **Grafana** to detect security issues.

### 10. Why are Compliance and Security Audits Important?

* Regular audits ensure compliance with security standards, identify vulnerabilities, and improve the cluster's security
  posture.

# NEW SECTION – MONITORING AND LOGGING.

### 1. Why are Monitoring and Logging Important?

* Monitoring tracks cluster health and performance, while logging helps with troubleshooting, debugging, and auditing.

### 2. What Key Metrics Should be Monitored?

* Monitor **CPU**, **memory**, **disk**, **network**, Pod status, node health, deployment status, and API server
  metrics.

### 3. What Monitoring Tools are Commonly Used?

* **Prometheus**, **Grafana**, **Metrics Server**, and **cAdvisor**.

### 4. How does Prometheus Work?

* Prometheus scrapes metrics from Kubernetes components, stores them as time-series data, and queries them using *
  *PromQL**.

### 5. What are DaemonSets Used for in Monitoring?

* DaemonSets run monitoring or logging agents on every node (e.g., Fluentd, node exporters).

### 6. What is Grafana?

* Grafana visualizes metrics through dashboards and is commonly paired with **Prometheus**.

### 7. How is Logging Implemented?

* Logging uses agents (e.g., **Fluentd**, **Logstash**) to collect logs and send them to centralized storage.

### 8. What is the EFK Stack?

* **Elasticsearch + Fluentd + Kibana** for centralized log collection, storage, and visualization.

### 9. Why are Kubernetes Events Useful?

* Events record cluster activities (e.g., scheduling, failures, evictions) and help troubleshoot issues.

### 10. What are Monitoring and Logging Best Practices?

* Use centralized logging, meaningful alerts, dashboards, structured logs, and monitor both cluster and application
  metrics.

# NEW SECTION – RESOURCE MANAGEMENT.

### 1. What is Resource Management in Kubernetes?

* Resource management allocates **CPU** and **memory** to Pods, ensuring efficient resource usage and application
  stability.

### 2. What are Resource Requests and Limits?

- **Request:** Minimum guaranteed CPU/memory.
- **Limit:** Maximum CPU/memory a container can use.

### 3. What is Quality of Service (QoS)?

* Kubernetes assigns Pods to QoS classes:
    - **Guaranteed**.
    - **Burstable**.
    - **BestEffort**.

### 4. What is the Role of the Scheduler?

* The scheduler places Pods on nodes based on available resources, requests, affinity, taints, and other constraints.

### 5. What is a LimitRange?

* A **LimitRange** sets default and maximum CPU/memory values for Pods and containers within a namespace.

### 6. What is Node Affinity?

* Node Affinity schedules Pods onto nodes with specific labels or characteristics.

### 7. What is a ResourceQuota?

* A **ResourceQuota** limits total resource usage (CPU, memory, Pods, PVCs, etc.) within a namespace.

### 8. What is Resource Overcommitment?

* Kubernetes allows allocating more resources than physically available, relying on **QoS** and limits to manage
  contention.

### 9. What are Taints and Tolerations?

* **Taints** prevent Pods from being scheduled on certain nodes unless the Pods have matching **Tolerations**.

### 10. How does HPA Help with Resource Management?

* **Horizontal Pod Autoscaler (HPA)** automatically scales the number of Pods based on CPU, memory, or custom metrics.

# NEW SECTION – RBAC

### 1. What is RBAC in Kubernetes?

* **Role-Based Access Control (RBAC)** controls access to Kubernetes resources based on assigned roles.

### 2. What are Roles and RoleBindings?

- **Role:** Defines permissions within a **namespace**.
- **RoleBinding:** Assigns a Role to a user, group, or service account.

### 3. What are ClusterRoles and ClusterRoleBindings?

- **ClusterRole:** Defines cluster-wide permissions.
- **ClusterRoleBinding:** Assigns a ClusterRole across the entire cluster.

### 4. How are RBAC Policies Managed?

* Create **Roles**, **RoleBindings**, **ClusterRoles**, and **ClusterRoleBindings** in YAML and apply them with
  `kubectl apply`.

### 5. What is the Principle of Least Privilege?

* Grant users and applications only the minimum permissions required to perform their tasks.

### 6. What is the Default RBAC Policy?

* RBAC is usually enabled by default with system roles, but custom user permissions must be configured.

### 7. How do Namespaces Affect RBAC?

* **Roles** and **RoleBindings** are namespace-scoped, while **ClusterRoles** and **ClusterRoleBindings** are
  cluster-wide.

### 8. How are Service Accounts Used with RBAC?

* Service accounts are assigned Roles or ClusterRoles to grant Pods the required permissions.

### 9. Can RBAC be Customized?

* Yes.
* Administrators can create custom Roles and Bindings with specific permissions.

### 10. What are Common RBAC Challenges?

* Managing permissions at scale, enforcing least privilege, keeping policies updated, and auditing access.

# NEW SECTION – STORAGE AND VOLUMES.

### 1. What are Volumes in Kubernetes?

* Volumes provide storage for Pods, allowing data to persist across container restarts and be shared between containers.

### 2. What are Common Volume Types?

- **emptyDir** – Temporary storage.
- **hostPath** – Mounts host filesystem.
- **NFS** – Network file system.
- **PersistentVolumeClaim (PVC)** – Persistent storage.
- **ConfigMap / Secret / DownwardAPI** – Configuration and metadata.

### 3. What are PV and PVC?

- **PersistentVolume (PV):** Cluster storage resource.
- **PersistentVolumeClaim (PVC):** Request for storage that binds to a PV.

### 4. What is Dynamic Provisioning?

* Kubernetes automatically creates storage volumes using a **StorageClass** when a PVC is requested.

### 5. What is a StorageClass?

* A StorageClass defines how storage is provisioned (e.g., SSD, HDD, cloud storage).

### 6. How do Containers Share Data in a Pod?

* Containers share data by mounting the same volume (commonly **emptyDir**).

### 7. How do you Use a PVC in a Pod?

* Reference the PVC under `volumes` and mount it using `volumeMounts`.

### 8. What is a Reclaim Policy?

- **Retain** – Keep the volume and data.
- **Delete** – Remove the volume and storage.
- **Recycle** – Deprecated.

### 9. What are Kubernetes Access Modes?

**Answer:**

- **ReadWriteOnce (RWO)**
- **ReadOnlyMany (ROX)**
- **ReadWriteMany (RWX)**

### 10. How is Data Preserved During Pod Rescheduling?

**Answer:** Pods reconnect to the same **Persistent Volume (PV)** through their **Persistent Volume Claim (PVC)**.

# NEW SECTION – SCHEDULING.

### 1. What is the Role of the Kubernetes Scheduler?

* The scheduler assigns Pods to the most suitable nodes based on resources, constraints, and policies.

### 2. How does the Scheduler Meet Resource Requirements?

* It schedules Pods only on nodes with sufficient **CPU**, **memory**, and other required resources.

### 3. What are Taints and Tolerations?

- **Taints:** Prevent Pods from running on specific nodes.
- **Tolerations:** Allow Pods to run on tainted nodes.

### 4. What are Node Affinity and Anti-Affinity?

- **Affinity:** Prefers or requires specific nodes.
- **Anti-Affinity:** Prevents scheduling on certain nodes.

### 5. How Else Can Scheduling be Controlled?

* Using **Node Selectors**, **Resource Requests/Limits**, **Pod Priority & Preemption**, or a **Custom Scheduler**.

### 6. What Happens if a Pod Cannot be Scheduled?

* The Pod remains in the **Pending** state until a suitable node becomes available.

### 7. How do you Troubleshoot Scheduling Failures?

* Check scheduling events with:

```bash
kubectl describe pod <pod-name>
```

### 8. What is a DaemonSet?

* A DaemonSet ensures one Pod runs on every (or selected) node, commonly for logging and monitoring.

### 9. Can Pods be Scheduled to Specific Hardware?

* Yes.
* Use **Node Labels**, **Node Selectors**, or **Node Affinity** to target nodes (e.g., GPU nodes).

### 10. What is a Pod Disruption Budget (PDB)?

* A **PDB** limits how many Pods can be unavailable during voluntary disruptions, helping maintain high availability.

# NEW SECTION – `kubectl`.

### 1. What is `kubectl`?

* `kubectl` is the Kubernetes command-line tool used to interact with the API server and manage cluster resources.

### 2. How do you Create and Manage Resources?

* Use commands such as:

```bash
kubectl apply -f file.yaml
kubectl create
kubectl delete
kubectl edit
```

### 3. How do you Debug Pods and Services?

- `kubectl logs` – View container logs.
- `kubectl describe` – View detailed resource information.
- `kubectl exec` – Run commands inside a container.
- `kubectl get` – List resources.

### 4. How do you Scale Applications?

```bash
kubectl scale deployment <name> --replicas=<number>
```

### 5. How do you Update Applications?

```bash
kubectl set image deployment/<name> <container>=<image:tag>
```

* This triggers a rolling update.

### 6. How do you Monitor Cluster Health?

- `kubectl get`
- `kubectl describe`
- `kubectl top`

### 7. What are Contexts in `kubectl`?

* Contexts allow switching between different clusters and namespaces using `kubectl config`.

### 8. How does `kubectl` Communicate with Kubernetes?

* It sends authenticated HTTP API requests to the Kubernetes **API Server**.

### 9. What is the Role of `kubectl` in CI/CD?

* It automates deployments, updates, scaling, and resource management in CI/CD pipelines.

### 10. What are Some Advanced `kubectl` Commands?

- `kubectl port-forward`
- `kubectl proxy`
- `kubectl cp`
- `kubectl patch`

# NEW SECTION – KUSTOMIZE.

### 1. What is Kustomize?

* Kustomize is a Kubernetes configuration management tool that customizes YAML files **without templates**.
* It is built into `kubectl`.

### 2. What are Overlays?

* Overlays apply environment-specific changes (e.g., **dev**, **staging**, **prod**) to shared base manifests.

### 3. How is Kustomize Different from Helm?

- **Kustomize:** Template-free, uses overlays and patches.
- **Helm:** Uses templates and packages applications as Charts.

### 4. What is a `kustomization.yaml` File?

* It defines resources, patches, labels, name changes, generators, and other customizations.

### 5. How do you Manage Multiple Environments?

* Create a common **base** and separate **overlay** directories for each environment.

### 6. Can Kustomize Generate Resources?

* Yes.
* It can generate **ConfigMaps**, **Secrets**, labels, and other resource configurations.

### 7. What are Patches?

* Patches modify specific fields in existing Kubernetes resources without changing the original manifests.

### 8. How are ConfigMaps and Secrets Managed?

* Kustomize generates them from files or literals and updates their hashes when content changes.

### 9. How does Kustomize Improve Reusability?

* It separates **base configurations** from **environment-specific overlays**, reducing duplication.

### 10. What are Kustomize Best Practices?

* Keep bases and overlays in version control, generate manifests during CI/CD, and validate before deployment.

# NEW SECTION – GITOPS.

### 1. What is GitOps?

* GitOps uses **Git as the single source of truth** for infrastructure and application deployments through declarative
  configuration.

### 2. What is the Role of Git in GitOps?

* Git stores the desired system state.
* Changes are made through commits, triggering automated deployments.

### 3. How does GitOps Improve Deployments?

* It automates deployments, increases consistency, enables frequent releases, and reduces human error.

### 4. What are the Benefits of GitOps?

* Automated deployments, easy rollbacks, version control, audit trails, consistency, and simplified Kubernetes
  management.

### 5. How do you Implement GitOps?

- Store configs in Git.
- Use IaC tools (e.g., Terraform).
- Deploy with **Argo CD** or **Flux**.
- Enforce Git-based workflows.

### 6. What is the Role of CI/CD in GitOps?

* **CI** tests and validates changes, while **CD** automatically synchronizes Kubernetes with the desired Git state.

### 7. What are GitOps Security Best Practices?

* Secure Git access, protect secrets, use RBAC, code reviews, image scanning, and signed commits.

### 8. How does GitOps Support Rollbacks?

* Revert to a previous Git commit, and GitOps tools automatically restore the previous cluster state.

### 9. What Tools are Commonly Used in GitOps?

* **GitHub/GitLab**, **Argo CD**, **Flux**, **Terraform**, **Kubernetes**, **Prometheus**, and **Grafana**.

### 10. How is Configuration Management Handled?

* All configuration is stored in Git and applied automatically, ensuring the cluster always matches the desired state.

# NEW SECTION – KUBERNETES MASTERCLASS

### 1. What is a Kubernetes Cluster?

* A cluster consists of a **Control Plane (Master)** and one or more **Worker Nodes**.
* Production clusters typically use multiple control-plane nodes for high availability.

### 2. What are the Main Kubernetes Components?

- **Control Plane:** API Server, etcd, Scheduler, Controller Manager.
- **Worker Node:** Kubelet, Container Runtime, Kube-proxy.

### 3. What are Common Kubernetes Tools?

- **kind / Minikube:** Local Kubernetes clusters.
- **kubectl:** CLI for managing Kubernetes.

### 4. What is Kubeconfig?

* Configuration file (`~/.kube/config`) storing cluster, user, and context information.

### 5. What is a Pod?

* The smallest deployable unit in Kubernetes.
* A Pod runs one or more containers that share networking and storage.

### 6. What is Docker?

* Docker packages applications into **images**, which run as **containers**.
* Containers communicate via IP or Services.

### 7. What are Common Pod States?

* **Pending**.
* **ContainerCreating**.
* **Running**.
* **Completed**.
* **Error**.
* **CrashLoopBackOff**.
* **ImagePullBackOff**.
* **Terminating**.

### 8. What are ReplicaSets and Deployments?

- **ReplicaSet:** Maintains the desired number of Pods.
- **Deployment:** Manages ReplicaSets, rolling updates, and rollbacks.

### 9. What are Kubernetes Services?

- **ClusterIP:** Internal access.
- **NodePort:** External access via node port.
- **LoadBalancer:** External access via cloud load balancer.

### 10. What are Probes?

- **Startup Probe:** Checks application startup.
- **Liveness Probe:** Restarts unhealthy containers.
- **Readiness Probe:** Determines when a Pod can receive traffic.

### 11. What are ConfigMaps and Secrets?

- **ConfigMap:** Stores non-sensitive configuration.
- **Secret:** Stores sensitive data (e.g., passwords, keys).

### 12. What are PV, PVC, and StorageClass?

- **StorageClass:** Defines storage type.
- **PVC:** Requests storage.
- **PV:** Actual persistent storage resource.

### 13. What is a StatefulSet?

* Manages stateful applications with stable identities and persistent storage.

### 14. What is an Ingress?

* An HTTP/HTTPS router that exposes Services externally using routing rules managed by an **Ingress Controller**.

### 15. Essential `kubectl` Commands

```bash
kubectl get nodes
kubectl get pods
kubectl describe pod <pod>
kubectl logs <pod>
kubectl exec -it <pod> -- bash
kubectl apply -f file.yaml
kubectl delete -f file.yaml
kubectl port-forward <pod> 8080:80
```

