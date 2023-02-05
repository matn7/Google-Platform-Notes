# Security & Compliance

**Question 1: Your company is launching an IoT service and will receive large volumes of streaming data. You have to store 
this data in Bigtable. You want to explore the Bigtable environment from the command line. What command would you run to 
ensure you have command-line tools installed?**

- `apt-get install bigtable-tools`
- `gcloud components install bigtable-tools`
- `apt-get install cbt`
- :white_check_mark: **`gcloud components install cbt`**

```
* The correct command is 'gcloud components install cbt` to install the Bigtable.
* 'apt-get' is used to install packages on some Linux systems but is not specific to GCP. 
* There is no such command as bigtable-tools.
```

**Question 2: You are the lead developer on a medical application that uses patients’ smartphones to capture biometric data. 
The app is required to collect data and store it on the smartphone when data cannot be reliably transmitted to the 
backend application. You want to minimize the amount of development you have to do to keep data synchronized between 
smartphones and backend data stores. Which data store option should you recommend?**

- Cloud Datastore
- Cloud Spanner
- Cloud SQL
- :white_check_mark: **Cloud Firestore**

```
* Cloud Firestore is a mobile database service that can synchronize data between mobile devices 
and centralized storage. 
* Spanner is a global relational database for large-scale applications that require transaction support in highly 
scaled databases.
* Datastore and Cloud SQL could be used but would require more custom development to synchronize data between 
mobile devices and the centralized data store.
```

**Question 3: Once a GCP Cloud dataproc cluster is created , it can be:**

- :white_check_mark: **Scaled up**
- :white_check_mark: **Only workers nodes can change**
- Both workers and master nodes can change
- :white_check_mark: **Scaled down**

```
* Once a cluster is created, it can be scaled up or down. 
* Only the number of worker nodes can change—master nodes are fixed.
```

**Question 4: A team of mobile developers is developing a new application. It will require synchronizing data between 
mobile devices and a backend database. Which database service would you recommend?**

- BigQuery
- Spanner
- :white_check_mark: **Firestore**

```
* Firestore is a document database that has mobile supporting features, like data synchronization, so it is 
the right answer. 
* BigQuery is for analytics, not mobile or transactional applications. 
* Spanner is a global relational database but does not have mobile-specific features. 
* Bigtable could be used with mobile devices, but it does not have mobile-specific features like synchronization.
```

**Question 5: A team of data scientists have asked for your help setting up an Apache Spark cluster. 
You suggest they use a managed GCP service instead of managing a cluster themselves on Compute Engine. 
The service they would use is:**

- BigQuery
- Cloud Hadoop
- Cloud Dataflow
- :white_check_mark: **Cloud Dataproc**

```
* Cloud Dataproc is the managed Spark service. 
* Cloud Dataflow is for stream and batch processing of data.
* BigQuery is for analytics.
* Cloud Hadoop is not a GCP service.
```

**Question 6: You are working with a startup developing analytics software for IoT data. You have to be able to ingest 
large volumes of data consistently and store it for several months. The startup has several applications that will need 
to query this data. Volumes are expected to grow to petabyte volumes. Which database should you use?**

- :white_check_mark: **Bigtable**
- Bigquery
- Datastore
- Cloud Spanner

```
* Bigtable is a wide-column database that can ingest large volumes of data consistently. 
* It also supports low-millisecond latency, making it a good choice for supporting querying. 
* Cloud Spanner is a global relational database that is not suitable for high-speed ingestion of large volumes of data. 
* Datastore is an object data model and not a good fit for IoT or other time series data. 
* BigQuery is an analytics database and not designed for ingestion of large volumes of data in short periods of time.
```

**Question 7: You need to store data for X and therefore you are using a cache for Y. How will the cache affect data retrieval?**

- Caches can get out of sync with the system of truth.
- A cache will continue to store data even if power is lost, improving availability.
- :white_check_mark: **Using a cache will reduce latency, since retrieving from a cache is faster than retrieving from SSDs or HDDs.**
- A cache improves the execution of client-side JavaScript.

```
* Caches use memory, and that makes them the fastest storage type for reading data. 
* Caches are data stores on the backend of distributed systems, not the clients. 
* A cache would have no effect on client-side JavaScript execution. 
* Caches do not store data in a cache if power is lost; the data would have to be reloaded. 
* Caches can get out of sync with the system of truth because the system of truth could be updated, but the cache may 
not be updated. 
* Caches have faster read times than SSDs and HDDs.
```

**Question 8: A software developer on your team is asking for your help improving the query performance of a database 
application. The developer is using a Cloud SQL MySQL, Second Generation instance. Which options would you recommend?**

- Datastore and SSD persistent disks.
- Datastore and HDD persistent disks.
- :white_check_mark: **Memorystore and SSD persistent disks**
- Memorystore and HDD persistent disks.

```
* Memorystore is a managed Redis cache. The cache can be used to store the results of queries. 
* Follow-on queries that reference the data stored in the cache can read it from the cache, which is much faster than 
reading from persistent disks. 
* SSDs have significantly lower latency than hard disk drives and should be used for performance-sensitive 
applications like databases. 
* Datastore is a managed NoSQL database.
```

**Question 9: As required by your company’s policy, you need to back up your Datastore database at least once per day. 
An auditor is questioning whether or not Datastore export is sufficient. You explain that the Datastore export command 
produces what outputs?**

- A metadata file, an entity file, and a folder with the data.
- A metadata file.
- :white_check_mark: **A metadata file and a folder with the data.**
- A single entity file.

```
The export process creates a metadata file with information about the data exported and a folder that has 
the data itself.
```

**Question 10: You have just created a Cloud Spanner instance. You have been tasked with creating a way to store data 
about a product catalog. What is the next step after creating a Cloud Spanner instance that you would perform to enable you to load data?**

- Create tables to hold the data.
- Use the Cloud Spanner console to import data into tables created with the instance.
- :white_check_mark: **Create a database within the instance.**
- Run `gcloud spanner update-security-patches`

```
The next step is to create a database within the instance. Once a database is created, tables can be created, and data 
can be loaded into tables. Cloud Spanner is a managed database, so you do not need to apply security patches. 
You can’t create tables without first having created a database
```

**Question 11: A software developer asks for your help exporting data from a Cloud SQL database. 
The developer tells you which database to export and which bucket to store the export file in, but hasn’t mentioned which 
file format should be used for the export file. What are the options for the export file format?**

- JSON and SQL
- CSV and JSON
- :white_check_mark: **CSV and SQL**
- CSV and XML

```
When exporting a database from Cloud SQL, the export file format options are CSV and SQL, JSON & XML are not an option.
```

**Question 12: Cloud Filestore is based on what file system technology?**

- EXT4
- XFS
- :white_check_mark: **Network File System (NFS)**
- ReiserFS

```
Cloud Filestore is based on Network Filesystem (NSF), which is a distributed file management system. 
The other options are file systems supported by Linux but are not the foundation of Cloud Filestore.
```

**Question 13: Which of the following file formats is not an option for an export file when exporting from BigQuery?**

- CSV
- :white_check_mark: **XML**
- JSON
- Avro

```
XML is not an option in BigQuery’s export process. All other options are available.
```

**Question 14: A new government regulation requires all pharmaceutical companies to keep their financial data for 5 years. 
What would you recommend as the best way to comply with the regulation requirement?**

- Use Coldline Storage
- Define a lifecycle policy
- :white_check_mark: **Define a data retention policy**
- Use Nearline Storage

```
You would be tempted to answer Coldline Storage - but the correct answer is "Define a data retention policy.".
Note there is no mention about the access to this data i.e. will it be access frequenctly or infrequenctly? 
By defining a retention policy - we ensure that the data is not deleted, as required by the regulation.
```

**Question 15: You need to copy files from your local device to a bucket in Cloud Storage. What command would you use? 
Assume you have Cloud SDK installed on your local computer.**

- `gcloud storage objects copy`
- :white_check_mark: **`gsutil cp`**
- `gsutil copy`
- `gcloud cp`

```
gsutil is the command to copy files to Cloud Storage. The verb is cp, not copy.
```

**Question 16: What features can you configure when running a Second Generation MySQL database in Cloud SQL?**

- :white_check_mark: **Machine type**
- :white_check_mark: **Failover replicas**
- :white_check_mark: **Maintenance window**

```
With a second-generation instance, you can configure the MySQL version, connectivity, machine type, automatic backups, 
failover replicas, database flags, maintenance windows, and labels, hence all if the above is correct.
```

**Question 17: Your manager asks you to set up a bare-bones Pub/Sub system as a sandbox for new developers to learn about 
messaging systems. What are the two resources within Pub/Sub you will need to create?**

- Topics and databases
- Topics and tables
- Tables and subscriptions
- :white_check_mark: **Topics and subscriptions**

```
* Pub/Sub works with topics, which receive and hold messages, and subscriptions, which make messages available 
to consuming applications. 
* Tables are data structures in relational databases, not message queues. 
* Databases exist in instances of database management systems, not messaging systems. 
* Tables are not a resource in messaging systems.
```

**Question 18: You want to estimate the cost of running a BigQuery query. What two services within 
Google Cloud Platform will you need to use?**

- Billing and Pricing Calculator
- BigQuery and Billing
- :white_check_mark: **BigQuery and Pricing Calculator**
- Billing and Pricing Calculator

```
* BigQuery provides an estimate of the amount of data scanned, and the Pricing Calculator gives a cost estimate 
for scanning that volume of data. 
* The Billing service tracks charges incurred. It is not used to estimate future or potential charges.
```

**Question 19: The correct command to create a Pub/Sub topic is which of the following?**

- :white_check_mark: **`gcloud pubsub topics create`**
- `cbt pubsub topics create`
- `gcloud pubsub create topics`
- `bq pubsub create topics`

```
gcloud followed by the service, in this case pubsub, followed by the resource, in this case topics,
and finally the verb, in this case create.
```

**Question 20: You have received a large data set from an Internet of Things (IoT) system. You want to use BigQuery to 
analyze the data. What command-line command would you use to make data available for analysis in BigQuery?**

- `gcloud BigQuery load --autodetect --source_format=[FORMAT] [DATASET].[TABLE][PATH_TO_SOURCE]`
- `bq import load --autodetect --source_format=[FORMAT] [DATASET].[TABLE][PATH_TO_SOURCE]`
- :white_check_mark: **`bq load --autodetect --source_format=[FORMAT] [DATASET].[TABLE][PATH_TO_SOURCE]`**
- `gcloud BigQuery load --autodetect --source_format=[FORMAT][DATASET].[TABLE][PATH_TO_SOURCE]`

```
The autodetect and source_format parameters and path to source are correctly specified in all options. 
To work with Bigquery we use bq and "load" is the right option and not import.
```

**Question 21: What is the query language used by Datastore?**

- :white_check_mark: **GQL**
- MDX
- SQL
- DataFrames

```
* Datastore uses a SQL-like query language called GQL. 
* SQL is not used. 
* MDX is a query language for online analytic processing (OLAP) systems. 
* DataFrames is a data structure used in Spark.
```

**Question 22: When you enter a query into the BigQuery query form, BigQuery analyzes the query and displays an estimate 
of what metric?**

- Cost of the query.
- :white_check_mark: **Amount of data scanned.**
- Number of bytes passed between servers in the BigQuery cluster.
- Time required to enter the query.

```
BigQuery displays an estimate of the amount of data scanned. 
This is important because BigQuery charges for data scanned in queries.
```

**Question 23: You have created a Cloud Spanner instance and database. According to Google best practices, 
how often should you update VM packages using apt-get?**

- Every 7 days.
- :white_check_mark: **Never, Cloud Spanner is a managed service.**
- Every 24 hours.
- Every 30 days.

```
There is no need to apply patches to the underlying compute resources when using Cloud Spanner, because Google manages 
resources used by Cloud Spanner. Updating packages is a good practice when using VMs, for example, with Compute Engine, 
but it is not necessary with a managed service.
```

**Question 24: All block storage systems use what block size?**

- 8 KB
- 16 KB
- 4 KB
- :white_check_mark: **Block size can vary.**

```
Block sizes in a block storage system can vary; therefore, option D is the correct answer.
Block size is established when a file system is created. 4KB block sizes are commonly used in Linux.
```

**Question 25: What is one of the direct advantages of using a message queue in distributed systems?**

- It increases security.
- It supports more programming languages.
- It stores messages until they are ready by default.
- :white_check_mark: **It decouples services, so if one lags, it does not cause other services to lag.**

```
Using a message queue between services decouples the services, so if one lags it does not cause other services to lag. 
Note, adding a message queue does not directly mitigate any security risks that might exist in the distributed system, 
such as overly permissive permissions.
```

**Question 26: What kind of data model is used by Datastore?**

- Relational
- :white_check_mark: **Document**
- Graph
- Wide-column

```
Datastore is a document database. 
Cloud SQL and Spanner are relational databases. 
Bigtable is a wide-column database.
```

**Question 27: Which of the following commands will create a subscription on the topic ace-exam-topic1?**

- `gcloud pubsub create --topic=ace-exam-topic1 ace-exam-sub1`
- :white_check_mark: **`gcloud pubsub subscriptions create --topic=ace-exam-topic1 ace-exam-sub1`**
- `gsutil pubsub subscriptions create --topic=ace-exam-topic1 ace-exam-sub1`
- `gcloud pubsub subscriptions create --topic=ace-exam-topic1`

```
gcloud pubsub subscriptions create followed by the topic and the name of the subscription.
```

**Question 28: Before you can start storing data in BigQuery, what must you create?**

- A persistent disk
- An entity
- :white_check_mark: **A data set**
- A bucket

```
To use BigQuery to store data, you must have a data set to store it, which makes it the right answer. 
Buckets are used by Cloud Storage, not BigQuery. You do not manage persistent disks when using BigQuery. 
An entity is a data structure in Datastore, not BigQuery.
```

**Question 29: Your department has been asked to analyze large batches of data every night. The jobs will run for about 
three to four hours. You want to shut down resources as soon as the analysis is done, so you decide to write a script to 
create a Dataproc cluster every night at midnight. What command would you use to create a cluster called 
spark-nightly-analysis in the us-west2-a zone?**

- `bq dataproc clusters create spark-nightly-analysis --zone us-west2-a`
- `gcloud dataproc clusters spark-nightly-analysis --zone us-west2-a`
- :white_check_mark: **`gcloud dataproc clusters create spark-nightly-analysis --zone us-west2-a`
**

```
The correct command is gcloud dataproc clusters create followed by the name of the cluster and the a --zone parameter. 
bq is the command-line tool for BigQuery, not Dataproc. Other combination of gcloud command missing a verb or command
```

**Question 30: Which of the following file formats is not supported when importing data into BigQuery?**

- CSV
- Avro
- :white_check_mark: **YAML**
- Parquet

```
YAML is not a file storage format; it used for specifying configuration data.
```
