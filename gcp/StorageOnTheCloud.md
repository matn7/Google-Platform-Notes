# Storage on the Cloud

**Question 1: You need to create a table called iot-ingest-data in Bigtable. What command would you use?**

- `gcloud bigtable create yables iot-ingest-date`
- :white_check_mark: **cbt createtable iot-ingest-data**
- `gcloud create iot-ingest-data`
- `gcloud bigtable tables create iot-ingest-data`

```
You will need to use a cbt command, which is the command-line tool for working with BigTable.
All other options reference gcloud and are therefore incorrect.
```

**Question 2: Your company has an object management policy that requires that objects stored in Cloud Storage be migrated 
from regional storage to nearline storage 90 days after the object is created. The most efficient way to do this is to:**

- :white_check_mark: **Create a lifecycle management configuration policy specifying an age of 90 days and SetStorageClass as nearline.**
- Set the MigrateObjectAfter property on the stored object to 90 days.
- Copy the object to persistent storage attached to a VM and then copy the object to a bucket created on nearline storage.
- Create a cloud function to copy objects from regional storage to nearline storage.

```
The lifecycle configuration policy allows administrators to specify criteria for migrating data to other storage 
systems without having to concern themselves with running jobs to actually execute the necessary steps. 
The other options are inefficient or do not exist.
```

**Question 3: A new government regulation requires all pharmaceutical companies to keep their financial data for 5 years. 
What would you recommend as the best way to comply with the regulation requirement?**

- :white_check_mark: **Define a data retention policy.**
- Use Coldline Storage.
- Define a lifecycle policy.
- Use Nearline Storage.

```
You would be tempted to answer Coldline Storage - but the correct answer is "Define a data retention policy.". 
Note there is no mention about the access to this data i.e. will it be access frequenctly or infrequenctly ?. 
By defining a retention policy - we ensure that the data is not deleted, as required by the regulation
```

**Question 4: If you want to store 50 TB of Semi-Structure data which will be accessed using single key, what would you
choose?**

- Cloud BigQuery
- Cloud Spanner
- Cloud SQL
- :white_check_mark: **Cloud BigTable**

```
Big table supports semi-structured data which is accessed using row key and works well at dataset which are larger 
than 1 TB. Cloud SQL cannot scale upto 50 TB and works well on structured data. Cloud Spanner too works on 
structured data.
```

**Question 5: As a developer on a project using Bigtable for an IoT application, you will need to export data from Bigtable 
to make some data available for analysis with another tool. What would you use to export the data, assuming you want to 
minimize the amount of effort required on your part?**

- An import tool provided by the analysis tool.
- `gcloud bigtable table export` 
- A Java program designed for importing and exporting data from BigTable.
- `bg bigtable table export`

```
Bigtable data is exported using a compiled Java program.
Note, there is no gcloud Bigtable command. Also, bq is not used with Bigtable.
```

**Question 6: Once a bucket has its storage class set to coldline, what are other storage classes it can transition to?**

- Multi-regional
- Nearline
- Regional
- :white_check_mark: **None of above**

```
Once a bucket is set to coldline, it cannot be changed to another storage class. 
Regional and multiregional can change to nearline and coldline. Nearline buckets can change to coldline.
```

**Question 7: What is the correct command-line structure to export data from Datastore?**

- `gcloud datastore dump --namespaces='[NAMESPACE]' gs://[BUCKET_NAME]`
- :white_check_mark: **gcloud datastore export --namespaces='[NAMESPACE]' gs://[BUCKET_NAME]**
- `gcloud datastore export gs://[BUCKET_NAME]`
- `gcloud datastore export '[NAMESPACE]' gs://[BUCKET_NAME]`

```
It has the correct base command, gcloud datastore export, followed by the --namespaces parameter and the name of a 
Cloud Storage bucket to hold the export file.
```

**Question 8: The correct command to create a Pub/Sub topic is which of the following?**

- `gcloud pubsub create topic`
- `bq pubsub create topics`
- `cbt pubsub topics create`
- :white_check_mark: **gcloud pubsub topics create**

```
gcloud followed by the service, in this case pubsub, followed by the resource, in this case topics; and finally the verb,
in this case create
```

**Question 9: Which of the following commands will create a subscription on the topic ace-exam-topic1?**

- `gsutil pubsub subscriptions create --topic=ace-exam-topic1 ace-exam-sub1`
- :white_check_mark: **gcloud pubsub subscriptions create --topic=ace-exam-topic1 ace-exam-sub1**
- `gcloud pubsub create --topic=ace-exam-topic1 ace-exam-sub1`
- `gcloud pubsub subscriptions create --topic=ace-exam-topic1`

```
gcloud pubsub subscriptions create followed by the topic and the name of the subscription
```

**Question 10: _____________ is defined as the ability of a user to access a resource at a specific time**

- Scalability
- Reliability
- Maintainability
- :white_check_mark: **Availability**

```
Availability is defined as the ability of a user to access a resource at a specific time. 
Availability is usually measured as the percentage of time that a system is operational. 
Reliability which is defined as the probability that a system will meet service-level objectives for some duration of time. 
Reliability is often measured as the mean time between failures. Scalability is the ability of a system to handle increases 
or decrease in workload by adding or deleting the resources to the system as needed. 
Maintainability is defined as the probability of performing a successful repair action within a given time.
```

**Question 11: GCP bucket name can be renamed?**

- :white_check_mark: **FALSE**
- TRUE

```
A bucket is named when it is created and cannot be renamed. To simulate renaming a bucket, you will need to copy 
the contents of the bucket to a new bucket with the desired name and then delete the original bucket.
```

**Question 12: Which of the following commands is used to create a backup of a Cloud SQL database?**

- `gcloud sql create backups`
- :white_check_mark: **gcloud sql backups create**
- `gcloud sql backups export`
- `gsutil sql backups create`

```
Cloud SQL is controlled using the gcloud command; the sequence of terms in gcloud commands is gcloud followed by the service, 
in this case SQL; followed by a resource, in this case backups, and a command or verb, in this case create
```

**Question 13: The command-line command to create a Cloud Storage bucket is:**

- :white_check_mark: **gsutil mb**
- `gsutil mbbucket`
- `gcloud mkbucket`
- `gcloud mb`

```
gsutil is the command line for accessing and manipulating Cloud Storage from the command line.
mb is the specific command for creating, or making, a bucket.
```

**Question 14: What is one of the direct advantages of using a message queue in distributed systems?**

- It stores messages until they are read by default.
- It increases security.
- :white_check_mark: **It decouples services, so if one lags, it does not cause other services to lag.**
- It supports more programming languages.

```
Using a message queue between services decouples the services, so if one lags it does not cause other services to lag. 
Note , adding a message queue does not directly mitigate any security risks that might exist in the distributed system, 
such as overly permissive permissions.
```

**Question 15: Your company is launching an IoT service and will receive large volumes of streaming data. 
You have to store this data in Bigtable. You want to explore the Bigtable environment from the command line. 
What command would you run to ensure you have command-line tools installed?**

- `apt-get install cbt`
- `apt-get install bigtable-tools`
- :white_check_mark: **gcloud components install cbt**
- `gcloud components install bigtable-tools`

```
The correct command is gcloud components install cbt to install the Bigtable apt-get is used to install packages on some 
Linux systems but is not specific to GCP. There is no such command as bigtable-tools.
```

**Question 16: Before you can start storing data in BigQuery, what must you create?**

- :white_check_mark: **A data set**
- A persistent disk
- A bucket
- An entity

```
To use BigQuery to store data, you must have a data set to store it, which makes it the right answer. 
Buckets are used by Cloud Storage, not BigQuery. You do not manage persistent disks when using BigQuery. 
An entity is a data structure in Datastore, not BigQuery.
```

**Question 17: You have just created a Cloud Spanner instance. You have been tasked with creating a way to store data 
about a product catalog. What is the next step after creating a Cloud Spanner instance that you would perform to enable 
you to load data?**

- Use the Cloud Spanner console to import data into tables created with the instance.
- Run `gcloud spanner update-security-patches`
- :white_check_mark: **Create a database within the instance**
- Create tables to hold the data.

```
The next step is to create a database within the instance. Once a database is created, tables can be created, 
and data can be loaded into tables. Cloud Spanner is a managed database, so you do not need to apply security patches. 
You can’t create tables without first having created a database
```

**Question 18: An education client maintains a site where users can upload videos, and your client needs to assure 
redundancy for the files; therefore, you have created two buckets for Cloud Storage. Which command do you use to 
synchronize the contents of the two buckets?**

- `gcloud cp sync`
- :white_check_mark: **gsutil rsync**
- `gcloud rsync`
- `gsutil cp sync`

```
gsutil is the command-line tool for working with Cloud Storage. rsync is the specific command in gsutil for synchronizing buckets.
``` 

**Question 19: Which of the following statements about Cloud Storage is not true?**

- :white_check_mark: **Lifecycle configurations can be used to change storage class from regional to multiregional.**
- Cloud Storage buckets can have retention periods.
- Cloud Storage does not provide block-level access to data within files stored in buckets.
- Cloud Storage is designed for high durability.

```
Lifecycle configurations can change storage class from regional to nearline or coldline. 
Once a bucket is created as regional or multiregional, it cannot be changed to the other
```

**Question 20: A remote component in your network has failed, which results in a transient network error. 
When you submit a gsutil command, it fails because of a transient error. By default, the command will:**

- Terminate and log a message to Cloud Shell.
- Terminate and log a message to Stackdriver.
- :white_check_mark: **Retry using a truncated binary exponential back-off strategy**
- Prompt the used to decide to retry or quit.

```
gcloud by default will retry a failed network operation and will wait a long time before each retry. 
The time to wait is calculated using a truncated binary exponential back-off strategy.
```

**Question 21: A product manager has asked for your advice on which database services might be options for a new application. 
Transactions and support for tabular data are important. Ideally, the database would support common query tools. 
What databases would you recommend the product manager consider?**

- Bigtable and Spanner
- :white_check_mark: **Cloud SQL and Spanner**
- BigQuery and Spanner
- Cloud SQL and Bigtable

```
Both Cloud SQL and Spanner are relational databases and are well suited for transaction-processing applications. 
BigQuery is relational, but it is designed for data warehousing and analytics, not transaction processing. 
Bigtable a wide-column NoSQL database, not a relational database.
```

**Question 22: You have just exported from a Dataproc cluster. What have you exported?**

- All tables in the Hadoop database.
- All tables in the Spark database.
- :white_check_mark: **Configuration data about cluster.**
- Data in Spark DataFrames.

```
Exporting from Dataproc exports data about the cluster configuration. Data in DataFrames is not exported. 
Spark does not have tables for persistently storing data like relational databases. No data from Hadoop is exported.
```

**Question 23: You want to estimate the cost of running a BigQuery query. What two services within Google Cloud 
Platform will you need to use?**

- Billing and Pricing Calculator.
- :white_check_mark: **BigQuery and Pricing Calculator.**
- BigQuery and Billing.

```
BigQuery provides an estimate of the amount of data scanned, and the Pricing Calculator gives a cost estimate for 
scanning that volume of data. The Billing service tracks charges incurred. It is not used to estimate future or 
potential charges.
```

**Question 24: You have been tasked with creating a data warehouse for your company. It must support tens of petabytes 
of data and use SQL for a query language. Which managed database service would you choose?**

- Cloud SQL
- SQL Server
- Bigtable
- :white_check_mark: **BigQuery**

```
BigQuery is a managed service designed for data warehouses and analytics. It uses standard SQL for querying, 
which makes it the right answer. Bigtable can support the volume of data described, but it does not use SQL as a 
query language. Cloud SQL is not the best option to scale to tens of petabytes.
```

**Question 25: Cloud Filestore is based on what file system technology?**

- XFS
- ReiserFS
- :white_check_mark: **Network File System (NFS)**
- EXT4

```
Cloud Filestore is based on Network Filesystem (NSF), which is a distributed file management system.
The other options are file systems supported by Linux but are not the foundation of Cloud Filestore.
```

**Question 26: On premise data warehouse running on MySQL is being migrated to GCP. The team wants to use a managed 
service which can scale to Petabytes. Which of the below GCP service would you choose ?**

- Cloud Storage
- :white_check_mark: **Cloud BigQuery**
- Cloud Filestore
- Cloud BigTable

```
Cloud BigQuery is an ideal choice for dataware housing as it is managed GCP service and scale upto Peta bytes. 
BigQuery can run SQL as well. Cloud BigTable & Cloud Firestore are NoSQL database. Cloud Storage is an object storage.
```

**Question 27: A software developer asks for your help exporting data from a Cloud SQL database. The developer tells 
you which database to export and which bucket to store the export file in, but hasn’t mentioned which file format 
should be used for the export file. What are the options for the export file format?**

- JSON and SQL
- CSV and JSON
- CSV and XML
- :white_check_mark: **CSV and SQL**

```
When exporting a database from Cloud SQL, the export file format options are CSV and SQL, JSON & XML are not an option.
```

**Question 28: Your company has deployed 100,000 Internet of Things (IoT) sensors to collect data on the state of 
equipment in several factories. Each sensor will collect and send data to a data store every 5 seconds. Sensors will 
run continuously. Daily reports will produce data on the maximum, minimum, and average value for each metric collected 
on each sensor. There is no need to support transactions in this application. Which database product would you recommend?**

- Cloud SQL MySQL
- :white_check_mark: **Cloud Bigtable**
- Cloud SQL PostrgeSQL
- Cloud Spanner

```
Bigtable is designed to accept billions of rows of data. Collecting data from 100,000 sensors every 5 seconds will 
generate 6,000,000 data points every minute, or 8,640,000,000 data points per day. Spanner is a relational database 
and supports transactions, but they are not needed. Cloud SQL MySQL and Cloud SQL PostgreSQL would be difficult to
scale to this level of read and write performance.
```

**Question 29: You are migrating a large number of files from a local storage system to Cloud Storage. 
You want to use the Cloud Console instead of writing a script. Which of the following Cloud Storage operations can 
you perform in the console?**

- Upload files only.
- Compare local files with files in the bucket using the diff command.
- :white_check_mark: **Upload files and folders.**
- Upload folders only.

```
From the console, you can upload both files and folders. Note : there is no diff operation in Cloud Console.
```

**Question 30: When you enter a query into the BigQuery query form, BigQuery analyzes the query and displays an 
estimate of what metric?**

- Time required to enter the query.
- Number of bytes passed between servers in the BigQuery cluster.
- :white_check_mark: **Amount of data scanned.**
- Cost of the query.

```
BigQuery displays an estimate of the amount of data scanned. 
This is important because BigQuery charges for data scanned in queries
```

**Question 31: Cloud Dataproc is a managed service for which two big data platforms?**

- Hadoop and Cassandra
- Spark and Cassandra
- Spark and TensorFlow
- :white_check_mark: **Spark and Hadoop**

```
Cloud Dataproc is a managed service for Spark and Hadoop. Cassandra is a big data distributed database but is not 
offered as a managed service by Google. TensorFlow is a deep learning platform not included in Dataproc.
```

**Question 32: As part of a compliance regimen, your team is required to back up data from your Datastore database to 
an object storage system. Your data is stored in the default namespace. What command would you use to export the default 
namespace from Datastore to a bucket called ace-exam-bucket1?** 

- `gcloud datastore dump --namespace="(default)" gs://ace-exam-bucket1`
- `gcloud datastore export --namespace="(default)" ace-exam-bucket1`
- `gcloud datastore dump --namespace="(default)" ace-exam-bucket1`
- :white_check_mark: **gcloud datastore export --namespace="(default)" gs://ace-exam-bucket1**

```
gcloud datastore export followed by a namespace and a bucket name. "gs://" is required and "dump" is not a option.
```

**Question 33: Your team wants to ensure that the SaaS service will have only minimal downtime. What metric would you 
use when negotiating with the SaaS provider to ensure that your client’s reliability requirements are met?**

- Average CPU utilization
- Mean time to recovery
- Maximum CPU utilization
- :white_check_mark: **Mean time between failure**

```
Reliability has nothing to do with CPU Utilizations , Reliability is measured as the mean time between failures.
```

**Question 34: Your department has been asked to analyze large batches of data every night. The jobs will run for about 
three to four hours. You want to shut down resources as soon as the analysis is done, so you decide to write a script to 
create a Dataproc cluster every night at midnight. What command would you use to create a cluster called 
spark-nightly-analysis in the us-west2-a zone?**

- `bq dataproc clusters create spark-nightly-analysis --zone us-west2-a`
- `gcloud dataproc clusters spark-nightly-analysis --zone us-west2-a`
- :white_check_mark: **gcloud dataproc clusters create spark-nightly-analysis --zone us-west2-a**

```
The correct command is gcloud dataproc clusters create followed by the name of the cluster and the a --zone parameter. 
bq is the command-line tool for BigQuery, not Dataproc. Other combination of gcloud command missing a verb or command
```

**Question 35: Which of the following Cloud Spanner configurations would have the highest hourly cost?**

- Located in us-central1
- :white_check_mark: **Located in nam-eur-asia1**
- Located in name3
- Located in us-west1-a

```
The multiregional and multi-super-regional location of nam-eur-aisa1 is the most expensive
```

**Question 36: You have a number of buckets containing old data that is hardly ever used. You don’t want to delete it, 
but you want to minimize the cost of storing it. You decide to change the storage class to coldline for each of 
those buckets. What is the command structure that you would use?**

- :white_check_mark:  **gsutil rewrite -s [STORAGE_CLASS] gs://[PATH_TO_OBJECT]**
- `bq rewrite -s [STORAGE_CLASS] gs://[PATH_TO_OBJECT]`
- `cbt rewrite -s [STORAGE_CLASS] gs://[PATH_TO_OBJECT]`
- `gcloud rewrite -s [STORAGE_CLASS] gs://[PATH_TO_OBJECT]`

```
gsutil is the correct command, gcloud commands are not used to manage Cloud Storage. "cbt and bq" commands are for 
Bigtable and BigQuery, respectively.
```

**Question 37: Running BigTable clusters in different regions will ________ replication latency and _________ availability**

- decrease, decrease
- :white_check_mark: **increase, increase**
- increase, decrease
- decrease, increase

```
When running the BigTable cluster across multiple region - you will ensure higher availability as you can handle region 
level failure. But it will also increase replication latency as data needs to be replicated across cluster in different 
region.
```

**Question 38: You are working with a startup developing analytics software for IoT data. You have to be able to ingest 
large volumes of data consistently and store it for several months. The startup has several applications that will need
to query this data. Volumes are expected to grow to petabyte volumes. Which database should you use?**

- BigQuery
- Cloud Spanner
- Datastore
- :white_check_mark: **Bigtable**

```
* Bigtable is a wide-column database that can ingest large volumes of data consistently. It also supports 
low-millisecond latency, making it a good choice for supporting querying. 
* Cloud Spanner is a global relational database that is not suitable for high-speed ingestion of large volumes of data. 
* Datastore is an object data model and not a good fit for IoT or other time series data. 
* BigQuery is an analytics database and not designed for ingestion of large volumes of data in short periods of time.
```

**Question 39: You want to get an estimate of the volume of data scanned by BigQuery from the command line. 
Which option shows the command structure you should use?**

- `gsutil --location=[LOCATION] query --use_legacy_sql=false --dry_run [SQL_QUERY]`
- `cbt BigQuery query estimate [SQL_QUERY]`
- :white_check_mark: **`bq --location=[LOCATION] query --use_legacy_sql=false --dry_run [SQL_QUERY]`**
- `gcloud BigQuery query estimate [SQL_QUERY]`

```
It shows the correct bq command structure, which includes location and the ––dry_run option. This option calculates an 
estimate without actually running the query. gcloud and gsutil are not used with BigQuery. cbt is a tool for working 
with Bigtable, not BigQuery. Be careful not to confuse the two because their names are similar.
```

**Question 40: You are using Cloud Console and want to check on some jobs running in BigQuery. You navigate to the 
BigQuery part of the console. Which menu item would you click to view jobs?**

- Active Jobs.
- You can't view job status in the console; you have to use `bq` on the command line.
- :white_check_mark: **Job History**
- My Jobs.

```
The correct menu option is Job History. There is no Active Jobs or My Jobs option. Job History shows active jobs, 
completed jobs, and jobs that generated errors. Note : you can get job status in the console.
```

**Question 41: Which of the following are database services that do not require you to specify configuration information for VMs?**

- :white_check_mark: **BigQuery, Datastore and Firebase**
- Datastore only
- BigQuery only
- Firebase and Datastore

```
BigQuery, Datastore, and Firebase are all fully managed services that do not require you to specify configuration 
information for VMs
```

**Question 42: You have been hired as a consultant to a startup in the Internet of Things (IoT) space. 
The startup will stream large volumes of data into GCP. The data needs to be filtered, transformed, 
and analyzed before being stored in GCP Datastore. A good option for the stream processing component is:**

- :white_check_mark: **Cloud Dataflow**
- Dataproc
- Cloud Interconnect
- Cloud Endpoints

```
* Cloud Dataflow allows for stream and batch processing of data and is well suited for this kind of ETL work. 
* Dataproc is a managed Hadoop and Spark service that is used for big data analytics. 
* Cloud Endpoints is an API service.
* Cloud Interconnect is a network service.
```

**Question 43: You have set up a Cloud Spanner process to export data to Cloud Storage. You notice that each time the 
process runs you incur charges for another GCP service, which you think is related to the export process. 
What other GCP service might be incurring charges during the Cloud Spanner export?**

- Dataproc
- Datastore
- `bq`
- :white_check_mark: **Dataflow**

```
Dataflow is a pipeline service for processing streaming and batch data that implements workflows used by Cloud Spanner.
```

**Question 44: Which command will export a MySQL database called gcp-exam-mysql1 to a file called 
gcp-exam-mysql-export.sql in a bucket named gcp-exam-bucket1?**

- `gcloud sql export sql gcp-exam-mysql1 gs://gcp-exam-mysql-export.sql/gcp-exam-bucket1/ \ --database=mysql`
- `gcloud storage export sql gcp-exam-mysql1 gs://gcp-exam-mysql-export.sql/gcp-exam-bucket1/ \ --database=mysql`
- `gcloud sql export gcp-exam-mysql1 gs://gcp-exam-mysql-export.sql/gcp-exam-bucket1/ \ --database=mysql`
- :white_check_mark: **`gcloud sql export sql gcp-exam-mysql1 gs://gcp-exam-bucket1/gcp-mysql-export.sql \ --database=mysql`**

```
gcloud sql export sql, indicating that the service is Cloud SQL, the operation is export, and the export file format 
is SQL. The filename and target bucket are correctly formed.
```

**Question 45: A software developer on your team is asking for your help improving the query performance of a database 
application. The developer is using a Cloud SQL MySQL, Second Generation instance. Which options would you recommend?**

- Memorystore and HDD persistent disk.
- Datastore and HDD persistent disks.
- Datastore and SSD persistent disks.
- :white_check_mark: **Memorystore and SSD persistent disks.**

```
Memorystore is a managed Redis cache. The cache can be used to store the results of queries. 
Follow-on queries that reference the data stored in the cache can read it from the cache, which is much faster than 
reading from persistent disks. SSDs have significantly lower latency than hard disk drives and should be used for 
performance-sensitive applications like databases. Datastore is a managed NoSQL database
```

**Question 46: What kind of data model is used by Datastore?**

- Relational
- Graph
- :white_check_mark: **Document**
- Wide-column

```
* Datastore is a document database. 
* Cloud SQL and Spanner are relational databases. 
* Bigtable is a wide-column database.
```

**Question 47: Cloud Storage provides 99.999999999 percent (eleven 9s) annual availability ?**

- :white_check_mark: **FALSE**
- TRUE

```
* Cloud Storage provides 99.999999999 percent (eleven 9s) annual durability and not availability. 
* Availability is the ability to access an object when you want it. 
* An object can be durably stored but unavailable. 
For example, a network outage in a region would prevent you from accessing an object stored in that region, although 
it would continue to be stored in multiple zones.
```

**Question 48: A database administrator has asked for an export of a MySQL database in Cloud SQL. 
The database administrator will load the data into another relational database and would to do it with the least amount of work. 
Specifically, the loading method should not require the database administrator to define a schema. 
What file format would you recommend for this task?**

- JSON
- CSV
- XML
- :white_check_mark: **SQL**

```
SQL format, exports a database as a series of SQL data definition commands. These commands can be executed in another 
relational database without having to first create a schema. 
CSV would require additoinal work and JSON & XML are not an option.
```

**Question 49: You need to store data for X and therefore you are using a cache for Y. How will the cache affect data retrieval?**

- A cache will continue to store data even if power is lost, imporving availability.
- Caches can get out of sync with the system of truth.
- A cache improves the execution of client-side JavaScript.
- :white_check_mark: **Using a cache will reduce latency, since retrieving from a cache is faster than retrieving from SSDs or HDDs.**

```
* Caches use memory, and that makes them the fastest storage type for reading data. 
* Caches are data stores on the backend of distributed systems, not the clients. 
* A cache would have no effect on client-side JavaScript execution. 
* Caches do not store data in a cache if power is lost; the data would have to be reloaded. 
* Caches can get out of sync with the system of truth because the system of truth could be updated, but the cache may 
not be updated. 
* Caches have faster read times than SSDs and HDDs.
```

**Question 50: Your department is deploying an application that has a database backend. You are concerned about the read 
load on the database server and want to have data available in memory to reduce the time to respond to queries and to 
reduce the load on the database server. Which GCP service would you use to keep data in memory?**

- Cloud SQL
- :white_check_mark: **Cloud Memorystore**
- Cloud Datastore
- Cloud Spanner

```
* Cloud Memorystore is the only GCP designed to cache data in memory. 
* Cloud SQL is a relational database service and might be a good option for the backend database. 
* Cloud Spanner is a global relational database and is a good option when you need a globally consistent database. 
* Cloud Datastore is a document database suitable for product catalogs, user profiles, and other semistructured data.
```
