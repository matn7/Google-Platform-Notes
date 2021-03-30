# System Design

## Design Fundamentals

## Client - Server Model

![Client - Server](img/system-client-server-model.png "Client - Server")

- `dig`: DNS queries.

```console
$> dig pandtronik.com

$> nc -l 8081
$> nc 127.0.0.1 8081
```

**Client**

- A machine or process that requests data or service from a server.
- Note that a single machine or piece of software can be both a client and s server at the same time. For instance, a single
machine could act as a server for end user and as s client for a database.

**Server**

- A machine or process that provides data or service for a client, usually by listening for incomming network calls.
- Note that a single machine or piece of software can be both a client and s server at the same time. For instance, a single
machine could act as a server for end user and as s client for a database.

**Client-Server Model**

- The paradigm by which modern systems are designed, which consists of clients requesting data or service from servers
and servers providing data or service to clients.

**IP Address**

- An address given to each machine connected to public internet. IPv4 addresses consist of four numbers separated by dots:
**a.b.c.d** where all four numbers are between 0 and 255. Special values include:
    - **127.0.0.1**: Your own local machine. Also referred to as **localhost**.
    - **192.168.x.x**: Your private network. For instance, your machine and all machines on your private wifi network will 
    usually have the **192.168** prefix.

**Port**

- In order for multiple programs to listen for new network connections on the same machine without colliding, they pick a
**port** to listen on. A port is an integer between 0 and 65,535 (2^16 ports total).
- Typically, ports 0-1023 are reserved for system ports (also called well-known ports) and shouldn't be used by 
user-level processes. Certain ports have pre-defined uses, and although you usually won't be required to have them
memorized, they can sometimes come in handy. Below are some examples:
    - 22: Secure Shell
    - 53: DNS lookup
    - 80: HTTP
    - 443: HTTPS
    
**DNS**

- Short for Domain Name System, it describes the entities and protocol involved in translation from domain names to
IP Addresses. Typically, machines make a DNS query to a well known entity which is responsible for returning the
IP address (or multiple ones) of the requested domain name in the response.    

### Network Protocols

- Protocol: An agreed upon set of rules for an interconnection between two parties.

**IP**

![IP segment](img/system-ip.png "IP segment")

- Stands for **Internet Protocol**. This network protocol outlines how almost all machine-to-machine communications should
happen in the world. Other protocols like **TCP**, **UDP** and **HTTP** are built on top of IP.

**TCP**

![TCP/IP segment](img/system-tcp.png "TCP/IP segment")

- Network protocol built on top of the Internet Protocol (IP). Allows for ordered, reliable data delivery between 
machines over the public internet by creating a **connection**.
- TCP is usually implemented in the kernel, which exposes **sockets** to applications that they can use to stream
data through an open connection.

**HTTP**

- The HyperText Transfer Protocol is a very common network protocol implemented on top of TCP. Clients make HTTP requests,
and servers respond with a response.
- Requests typically have the following schema:

```
host: string (example: pandatronik.com)
port: integer (example: 80 or 443)
method: string (example: GET, PUT, POST, DELETE, OPTIONS or PATCH)
headers: pair list (example: "Content-Type" => "application/json")
body: opaque sequence of bytes
```

- Responses typically have the following schema:

```
status code: integer (example: 200, 401)
headers: pair list (example: :Content-Length" => 1238)
body: opaque sequence of bytes
```

**IP Packet**

- Sometimes more broadly referred to as just a (network) **packet**, and IP packet is effectively the smallest unit
used to describe data being sent over **IP**, aside from bytes. An IP packet consists of:
    - An **IP header**, which contains the source and destination **IP addresses** as well as other information
    related to the network.
    - A **payload**, which is just the data being sent over the network.

### Storage

**Databases**

- Databases are programs that either use disk or memory to do 2 core things: **record** data and **query** data.
In general, they are themselves servers that are long lived and interact with the rest of your app through network
calls, with protocols on top of TCP or even HTTP.
- Some databases only keep records in memory, and the users of such databases are aware of the fact that those records
may be lost forever if the machine or process dies.
- For the most part though, databases need persistence of those records, and thus cannot use memory. This means that
you have to write your data to disk. Anything written to disk will remain through power loss or network partitions, so 
that's what is used to keep permanent records.
- Since machines die often in a large scale system, special disk partitions or volumes are used by the database processes,
and those volumes can get recovered even if the machine were to go down permanently.

**Disk**

- Usually refers to either **HDD (hard-disk drive)** or **SSD (solid-state drive)**. Data written to disk will persist
through power failures and general machine crashes. Disk is also referred to as **non-volatile storage**.
- SSD is far faster than HDD but also far more expensive from financial point of view. Because of that, HDD will typically 
be used for data that's rarely accessed or updated, but that's stored for long time, and SSD will be used for data
that's frequently accessed and updated.

**Memory**

- Short of **Random Access Memory (RAM)**. Data stored in memory will be lost when the process that has written that data
dies.

**Persistent Storage**

- Usually refers to disk, but in general it is any form of storage that persists if the process in charge of 
managing it dies. 

### Latency And Throughput

- Measure of performance of system.

**Latency**

- The time it takes for a certain operation to complete in a system. Most often this measure is a time duration, like 
milliseconds or seconds. You should know these orders of:
    - Reading 1MB from RAM: 250 us (0.25 ms)
    - Reading 1MB from SSD: 1,000 us (1 ms)
    - Transfer 1MB over Network: 10,000 us (10 ms)
    - Reading 1MB from HDD: 20,00 us (20 ms)
    - Inter-Continental Round Trip: 150,000 us (150 ms)
    
**Throughput**

- The number of operations that a system can handle properly per time unit. For instance the throughput of a server can 
often be measured in request per second (RPS or QPS).     

### Availability

**Availability**

- The odds of a particular server or service being up and running at any point in time, usually measured in percentages.
A server that has 99% availability will be operational 99% of the time (this would be described as having two nines of
availability).

**High Availability**

- Used to describe systems that have particular high levels of availability, typically 5 nines or more; sometimes
abbreviated "HA".

**Nines**

- Typically, refers to percentages of uptime. For example, 5 nines of availability means an uptime of 99.999%.
Below are the downtimes expected per year depending on those 9s:

```
- 99% (two 9s): 87.7 hours
- 99.9% (three 9s): 8.8 hours
- 99.99%: 52.6 minutes
- 99.999%: 5.3 minutes
```

**Redundancy**

- The process of replicating parts of a system in an effort to make it more reliable.

**SLA**

- Short for "service-level-agreement", an SLA is a collection of guarantees given to a customer by a service provider.
SLAs typically make guarantees on a system's availability, amongst other things. SLAs are made up of one or multiple
SLOs.

**SLO**

- Short for "service-level objective", an SLO is a guarantee given to a customer by a service provider. SLOs typically
make guarantees on a system's availability, amongst other things. SLOs constitute an SLA.

### Caching

**Cache**

- A piece of hardware of software that stores data, typically meant to retrieve that data faster than otherwise.
- Caches are often used to store responses to network requests as well as result of computationally-long
operations.
- Note that data in a cache can become stale if the main source of truth for that data (i.e. the main database
behind the cache) gets updated and the cache doesn't.

**Cache Hit**

- When requested data is found in a cache.

**Cache Miss**

- When requested data could have been found in a cache but isn't. This is typically used to refer to a negative
consequence of a system failure or of a poor design choice. For example:
    - If a server goes down, our load balancer will have to forward requests to a new server, which will result in
    cache misses.

**Cache Eviction Policy**

- The policy by which values get evicted or removed from a cache. Popular cache eviction policies include
**LRU** (least-recently used), **FIFO** (first in first out), and **LFU** (least-frequently used).

**Content Delivery Network**

- CDN is a third-party service that acts like a cache for your servers. Sometimes, web apps can be slow for users
in a particular region if your servers are located only in another region. A CDN has servers all around the world,
meaning that the latency to a CDN's servers will almost always be far better than the latency of your servers. A CDN's
servers are often referred to as PoPs (Points of Presence). Two of the most popular CDNs are Cloudflare and Google 
Cloud Platform.     



















