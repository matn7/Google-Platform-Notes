# Processes vs Threads

## What is a Process?

A **process** is an independent program in execution.

Each process has its own:

- Virtual address space
- Heap
- Global variables
- Open file descriptors
- Security context
- Process ID (PID)

Processes are isolated from one another. One process cannot directly access another process's memory.

### Examples.

- Google Chrome.
- Spotify.
- Visual Studio Code.

***

## What is a Thread?

A **thread** is the smallest unit of execution inside a process.

Multiple threads can exist within the same process.

Each thread has its own:

- Stack.
- Program Counter (PC).
- CPU Registers.
- Thread ID (TID).

Threads **share** the following resources with other threads in the same process:

- Code segment.
- Heap.
- Global variables.
- Open files.
- Address space.

***

## Process vs Thread

| Feature             | Process             | Thread                          |
|---------------------|---------------------|---------------------------------|
| Definition          | Independent program | Execution unit within a process |
| Address Space       | Separate            | Shared                          |
| Heap                | Separate            | Shared                          |
| Global Variables    | Separate            | Shared                          |
| Stack               | Own                 | Own                             |
| Registers           | Own                 | Own                             |
| Program Counter     | Own                 | Own                             |
| File Descriptors    | Own                 | Shared                          |
| Communication       | IPC                 | Shared Memory                   |
| Creation Cost       | High                | Low                             |
| Context Switch Cost | High                | Low                             |
| Isolation           | High                | Low                             |

***

## Memory Layout.

```text
Process

+--------------------------------------+
| Code                                 |
| Global Variables                     |
| Heap                                 |
|                                      |
| Thread 1 -> Stack                    |
| Thread 2 -> Stack                    |
| Thread 3 -> Stack                    |
+--------------------------------------+
```

Another process has an entirely different memory layout.

***

## Why Use Threads?

Imagine a web browser with only one thread.

```text
Load HTML
      ↓
Render Page
      ↓
Execute JavaScript
      ↓
Download Images
      ↓
Update UI
```

If JavaScript blocks for 5 seconds, the entire application freezes.

Modern applications use multiple threads, for example:

- UI Thread.
- Rendering Thread.
- Network Thread.
- JavaScript Thread.
- Background Worker.
- GPU Thread.

This allows different tasks to execute concurrently, keeping the application responsive.

***

## Why Use Processes?

Processes provide:

- Memory isolation.
- Security boundaries.
- Crash isolation.

For example:

If Spotify crashes, Chrome continues running because they are different processes.

Modern browsers also isolate tabs into separate processes so that one crashing tab does not terminate the entire browser.

***

## Process Creation.

### Linux.

```c
fork();
exec();
```

### Windows.

```cpp
CreateProcess(...)
```

Creating a process requires:

- New virtual address space.
- Page tables.
- Kernel structures.
- Resource allocation.

Therefore, process creation is relatively expensive.

***

## Thread Creation.

### Linux.

```cpp
pthread_create(...)
```

### Windows.

```cpp
CreateThread(...)
```

Threads reuse the existing process resources, making them significantly cheaper to create.

***

## Context Switching.

A CPU core executes only one thread at a time.

When switching execution:

```text
Thread A
↓
Save Registers
↓
Load Registers
↓
Thread B
```

This operation is called a **context switch**.

***

## Process Context Switch.

Requires switching:

- Registers.
- Stack.
- Address space.
- Page tables.
- Memory mappings.
- TLB (often flushed or updated).

Because the memory context changes, process context switches are relatively expensive.

***

## Thread Context Switch.

Requires switching only:

- Registers.
- Stack.
- Program Counter.

Since the address space remains the same, thread context switches are much faster.

***

## Communication.

### Between Processes.

Processes cannot directly access each other's memory.

Communication requires **Inter-Process Communication (IPC)**.

Common IPC mechanisms:

- Pipes.
- Sockets.
- Shared Memory.
- Message Queues.
- Signals.

### Between Threads

Threads communicate through shared memory.

Example:

```cpp
counter++;
```

This is efficient but introduces synchronization challenges.

***

## Race Condition.

Suppose two threads execute:

```cpp
counter++;
```

at the same time.

Expected result:

```text
7
```

Possible result:

```text
6
```

Both threads read the same value before either writes the updated value.

This is called a **race condition**.

***

## Synchronization.

To prevent race conditions, use synchronization primitives such as:

- Mutex.
- Semaphore.
- Read-Write Lock.
- Atomic Operations.
- Condition Variable.

These ensure that shared resources are accessed safely.

***

## Advantages.

### Processes.

✅ Strong isolation.
✅ Better security.
✅ Independent failure handling.

Suitable for:

- Browsers.
- Databases.
- Operating Systems.
- Independent services.

---

### Threads.

✅ Low creation overhead.
✅ Fast communication.
✅ Better CPU utilization.

Suitable for:

- Web servers.
- Game engines.
- Rendering pipelines.
- Parallel computations.

***

## Disadvantages

### Processes.

- Higher memory consumption.
- Slower communication.
- Higher creation overhead.

### Threads.

- Race conditions.
- Deadlocks.
- More difficult debugging.
- Shared-memory bugs.

***

## Common Interview Questions.

### Why are threads cheaper than processes?

* Threads share the process's address space, heap, code, and open resources. 
* The operating system only creates a new execution context (stack, registers, thread metadata), making thread creation 
and switching much cheaper than processes.

### Can one process have multiple threads?

* Yes.
* Example:

```text
Chrome Process

├── UI Thread
├── Rendering Thread
├── Network Thread
├── Audio Thread
└── GPU Thread
```

### Can threads from different processes share memory?

* Not directly.
* They require an IPC mechanism such as:
  - Shared Memory.
  - Pipes.
  - Sockets.
  - Message Queues.


### Which is more expensive?

* **Process creation** and **process context switching** are generally more expensive than thread creation and thread 
context switching because they involve separate virtual memory management.

### When should you use processes instead of threads?

* Use **processes** when you need:
  - Fault isolation.
  - Security boundaries.
  - Independent applications.
* Examples:
  - Browser tabs.
  - Database servers.
  - Microservices.


* Use **threads** when you need:
  - Shared memory.
  - Fast communication.
  - Parallel execution within a single application.
* Examples:
  - Web servers.
  - Background workers.
  - Rendering pipelines.

***

## 30-Second Interview Answer.

> A **process** is an independent program with its own virtual address space, heap, and operating system resources. 
> A **thread** is a lightweight execution unit within a process. 
> Threads share the process's memory and resources but maintain their own stack, registers, and program counter. 
> Threads are cheaper to create and switch between because they share the same address space, but they require synchronization to avoid race conditions. 
> Processes provide stronger isolation and fault tolerance, while threads provide better performance for concurrent execution.

***

# Mutexes

## What is a Mutex?

* A **Mutex** (short for **Mutual Exclusion**) is a synchronization primitive that ensures **only one thread at a time** 
can access a shared resource (critical section).
* Think of it as a lock on a room:
  - A thread **locks** the mutex before entering.
  - While the mutex is locked, all other threads must wait.
  - The thread **unlocks** the mutex when it finishes.
* This prevents **race conditions**.

## Why Do We Need a Mutex?

* Consider two threads updating the same variable.

```cpp
int counter = 0;

Thread A:
counter++;

Thread B:
counter++;
```

* The operation `counter++` is **not atomic**.
* Internally it consists of three steps:

```text
1. Read counter
2. Increment
3. Write counter
```

* Possible execution:

```text
Counter = 0

Thread A reads 0
Thread B reads 0

Thread A writes 1
Thread B writes 1

Final result = 1 ❌
Expected = 2
```

* This is called a **race condition**.

## Using a Mutex

```cpp
std::mutex m;

void increment() {
    m.lock();
    counter++;
    m.unlock();
}
```

* Execution:

```text
Thread A
Lock Mutex
Increment Counter
Unlock Mutex

↓

Thread B
Lock Mutex
Increment Counter
Unlock Mutex
```

* Now the final value is always correct.

## Critical Section.

* A **critical section** is a piece of code that accesses shared data.
* Example:

```cpp
balance += 100;
```

* Since multiple threads could modify `balance`, this code must be protected by a mutex.

```cpp
std::mutex m;

m.lock();

balance += 100;

m.unlock();
```

## How a Mutex Works.

* Suppose the mutex is initially unlocked.

```text
Mutex = Unlocked
```

### Thread A.

```text
Lock()
↓
Mutex becomes Locked
↓
Critical Section
↓
Unlock()
↓
Mutex becomes Unlocked
```

### Thread B.

* If Thread B calls:

```cpp
lock();
```

* while Thread A owns the mutex, it will:

```text
Wait
↓
Blocked
↓
Scheduler puts thread to sleep
↓
Wake up after unlock
```

* Only one thread owns the mutex at any time.

## Lock Ownership.

* A mutex has an **owner**.
* Only the thread that successfully locks the mutex may unlock it.

```text
Thread A
lock()
↓
Owner = Thread A
↓
unlock()
↓
Owner = None
```

* Attempting to unlock a mutex owned by another thread results in **undefined behavior** (or a runtime error, depending on the implementation).

## RAII (Recommended in C++).

* Instead of manually calling `lock()` and `unlock()`, use `std::lock_guard`.

```cpp
std::mutex m;

void increment() {
    std::lock_guard<std::mutex> guard(m);

    counter++;
}
```

* Advantages:
  - Automatically unlocks the mutex.
  - Safe if an exception is thrown.
  - Prevents forgetting to unlock.

## Recursive Mutex

* Normally:

```cpp
lock();
lock();
```

* The same thread blocks itself.
* This creates a deadlock.
* A **recursive mutex** allows the same thread to acquire the mutex multiple times.
* Example:

```cpp
std::recursive_mutex m;
```

* Use only when necessary, as it can hide design issues.

## Try Lock.

* Instead of blocking indefinitely:

```cpp
m.try_lock();
```

* Possible outcomes:

```text
Mutex Available
↓
Lock Acquired

OR

Mutex Busy
↓
Return false
```

* Useful when waiting is undesirable.

## Timed Mutex.

* Acquire the mutex only for a limited time.

```cpp
try_lock_for(100ms)
```

* If unavailable after the timeout:

```text
Return false
```

* Useful in real-time or responsive systems.

### Advantages.

- Prevents race conditions.
- Ensures data consistency.
- Simple synchronization primitive.
- Widely supported across programming languages.

### Disadvantages

- Threads may block and wait.
- Reduces parallelism.
- Incorrect use can lead to deadlocks.
- Excessive locking reduces performance.

## Common Mistakes.

### Forgetting to Unlock.

```cpp
m.lock();

return;
```

* The mutex remains locked forever.
* Solution:
* Use RAII (`std::lock_guard` or `std::unique_lock`).

### Locking Too Much.

* Bad:

```cpp
lock();

performSlowNetworkRequest();

unlock();
```

* The mutex is held while waiting for I/O, unnecessarily blocking other threads.
* Better:

```cpp
performSlowNetworkRequest();

lock();

updateSharedData();

unlock();
```

* Keep the critical section as short as possible.

### Double Lock.

```cpp
lock();

lock();
```

* With a regular mutex, the thread waits on itself, causing a deadlock.

## Mutex vs Spinlock.

| Mutex                 | Spinlock                    |
|-----------------------|-----------------------------|
| Sleeping wait         | Busy waiting                |
| OS scheduler involved | CPU continuously checks     |
| Better for long waits | Better for very short waits |
| Lower CPU usage       | Higher CPU usage            |

* Use a **mutex** for most application code.

***

## Mutex vs Semaphore.

| Mutex                    | Semaphore             |
|--------------------------|-----------------------|
| One owner                | No owner              |
| Binary (locked/unlocked) | Counter               |
| Only owner unlocks       | Any thread can signal |
| Mutual exclusion         | Resource management   |

* A mutex protects **shared data**.
* A semaphore controls access to a limited number of resources.

***

## Mutex vs Atomic.

```cpp
std::atomic<int> counter;
```

* Atomic operations:
  - Faster.
  - No locking.
  - Limited to simple operations.
* Mutexes:
  - Can protect multiple variables.
  - Can protect complex operations.
  - More flexible.

***

## Time Complexity.

| Operation  | Complexity       |
|------------|------------------|
| lock()     | O(1) (typically) |
| unlock()   | O(1)             |
| try_lock() | O(1)             |

* Actual waiting time depends on contention.

***

## Common Interview Questions.

### What problem does a mutex solve?.

* A mutex prevents **race conditions** by ensuring only one thread at a time executes a critical section.

### Why is `counter++` not thread-safe?

* Because it consists of multiple machine-level operations (read, modify, write). 
* Two threads can interleave these operations and overwrite each other's updates.

### What happens if you forget to unlock a mutex?

* Other threads waiting on the mutex may block indefinitely, potentially causing a deadlock or application hang.

### Can another thread unlock my mutex?

* No.
* Only the thread that owns the mutex should unlock it.

### Can a mutex protect multiple variables?

* Yes.
* One mutex can protect an entire critical section involving several related variables.
* Example:

```cpp
lock();

balance += amount;
transactions.push_back(amount);
lastUpdated = now();

unlock();
```

### When should you use an atomic instead of a mutex?

* Use an **atomic** for simple operations on a single variable (e.g., incrementing a counter).
* Use a **mutex** when protecting multiple variables or a sequence of operations that must execute atomically.

***

## 30-Second Interview Answer.

> A **mutex** (mutual exclusion lock) is a synchronization primitive that ensures only one thread at a time can execute a critical section. 
> A thread acquires the mutex before accessing shared data and releases it afterward, preventing race conditions. 
> Mutexes are ideal for protecting shared resources but can reduce concurrency and may lead to deadlocks if locks are acquired incorrectly. 
> In modern C++, `std::lock_guard` or `std::unique_lock` is preferred over manual `lock()`/`unlock()` because they automatically release the mutex, even when exceptions occur.

***

# Semaphores.

> **Note:** The Google Interview Prep Guide only states that candidates should understand **semaphores** as part of operating systems and concurrency. It does **not** explain how semaphores work. The explanations below are additional interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is a Semaphore?

A **semaphore** is a synchronization primitive used to **control access to a limited number of shared resources**.

Unlike a mutex, a semaphore allows **multiple threads** to access the resource simultaneously, up to a predefined limit.

Think of it as a **parking lot**:

- The parking lot has **N parking spaces**.
- A car (thread) may enter only if a space is available.
- When a car leaves, another waiting car may enter.

---

# Why Do We Need a Semaphore?

Imagine a connection pool with only **3 database connections**.

```text
Database Connection Pool

Available Connections = 3
```

If 10 threads want to query the database:

- Only 3 threads should execute immediately.
- The remaining 7 threads should wait until a connection becomes available.

A semaphore solves this problem.

---

# Semaphore Operations

Semaphores have two fundamental operations.

## Wait (Acquire)

Also called:

- `wait()`
- `P()`
- `down()`
- `acquire()`

Operation:

```text
If counter > 0

↓

counter--

↓

Continue

Otherwise

↓

Block
```

---

## Signal (Release)

Also called:

- `signal()`
- `V()`
- `up()`
- `release()`

Operation:

```text
counter++

↓

Wake one waiting thread (if any)
```

---

# Example

Initial semaphore value:

```text
Semaphore = 3
```

Execution:

```text
Thread A acquire()

Counter = 2

Thread B acquire()

Counter = 1

Thread C acquire()

Counter = 0

Thread D acquire()

↓

Blocked
```

When Thread A releases:

```text
Counter = 1

↓

Thread D wakes up

↓

Counter = 0
```

---

# Binary Semaphore

A **binary semaphore** has only two possible values:

```text
0

or

1
```

It behaves similarly to a mutex but is **not identical**.

Example:

```text
Semaphore = 1

Thread A acquire()

↓

Semaphore = 0

Thread B acquire()

↓

Wait
```

---

# Counting Semaphore

A **counting semaphore** allows more than one thread to access a resource.

Example:

```text
Semaphore = 5
```

Five threads may enter simultaneously.

The sixth thread waits.

Common use cases:

- Database connection pools
- Thread pools
- Printer queues
- API rate limiting

---

# Semaphore Example (C++20)

```cpp
#include <semaphore>

std::counting_semaphore<3> dbConnections(3);

void worker() {
    dbConnections.acquire();

    // Use database connection

    dbConnections.release();
}
```

At most **3 threads** execute the protected section concurrently.

---

# Semaphore vs Mutex

| Mutex | Semaphore |
|--------|-----------|
| Protects shared data | Controls access to resources |
| Only one thread enters | Multiple threads may enter |
| Has an owner | No owner |
| Locked / Unlocked | Integer counter |
| Owner must unlock | Any thread may release |

---

# Ownership

A mutex has an owner.

```text
Thread A locks mutex

↓

Only Thread A may unlock
```

A semaphore has **no owner**.

Example:

```text
Thread A acquire()

↓

Thread B release()
```

This is valid.

---

# Resource Pool Example

Suppose:

```text
10 Workers

↓

3 Database Connections
```

Semaphore:

```text
Semaphore = 3
```

Execution:

```text
Worker 1

Acquire

↓

Connection 1

Worker 2

Acquire

↓

Connection 2

Worker 3

Acquire

↓

Connection 3

Worker 4

↓

Blocked
```

When one worker finishes:

```text
Release()

↓

Worker 4 wakes up
```

---

# Producer-Consumer Example

Semaphores are commonly used in the Producer-Consumer problem.

Example:

```text
Buffer Size = 5
```

Use two semaphores:

```text
Empty = 5

Full = 0
```

Producer:

```text
Wait(Empty)

↓

Add Item

↓

Signal(Full)
```

Consumer:

```text
Wait(Full)

↓

Remove Item

↓

Signal(Empty)
```

This prevents:

- Buffer overflow
- Buffer underflow

---

# Advantages

- Controls concurrent access.
- Efficient for managing limited resources.
- Useful for producer-consumer problems.
- Prevents resource exhaustion.

---

# Disadvantages

- Incorrect use may lead to deadlocks.
- Easy to forget to release.
- Harder to debug than mutexes.
- Does not protect against all race conditions.

---

# Common Mistakes

## Forgetting to Release

```cpp
sem.acquire();

// Work

return;
```

The semaphore count never increases.

Waiting threads remain blocked.

---

## Releasing Too Many Times

```cpp
sem.release();
sem.release();
```

The semaphore count becomes incorrect.

More threads than intended may enter the critical section.

---

## Using a Semaphore Instead of a Mutex

Suppose:

```cpp
balance += 100;
```

This should use a **mutex**, not a counting semaphore.

Semaphores are for **resource management**, not protecting individual variables.

---

# Common Use Cases

- Database connection pools
- Thread pools
- Worker queues
- Limiting concurrent downloads
- API request throttling
- Printer management
- Producer-consumer queues

---

# Binary Semaphore vs Mutex

| Binary Semaphore | Mutex |
|------------------|-------|
| Value is 0 or 1 | Locked or unlocked |
| No owner | Has owner |
| Any thread may release | Only owner releases |
| Resource synchronization | Mutual exclusion |

Although a binary semaphore can mimic a mutex, a mutex is generally preferred for protecting shared data because ownership rules help prevent programming errors.

---

# Semaphore vs Atomic

| Semaphore | Atomic |
|-----------|--------|
| Manages multiple threads | Single atomic variable |
| May block threads | Never blocks |
| Uses scheduler | CPU instruction |
| Resource management | Lock-free operations |

---

# Time Complexity

| Operation | Complexity |
|-----------|------------|
| acquire() | O(1) (typically) |
| release() | O(1) |
| Waiting | Depends on scheduler |

---

# Common Interview Questions

## What problem does a semaphore solve?

A semaphore limits how many threads can access a shared resource simultaneously.

Example:

- Only 10 worker threads may access a database at once.

---

## What is the difference between a binary semaphore and a counting semaphore?

- **Binary semaphore:** Counter is either 0 or 1.
- **Counting semaphore:** Counter may be any non-negative integer.

---

## Can a semaphore replace a mutex?

A binary semaphore can provide mutual exclusion, but a **mutex is the preferred choice** for protecting shared data because it enforces ownership.

---

## Why does a semaphore not have an owner?

A semaphore represents the **availability of resources**, not ownership of a critical section. Any thread can signal that a resource has become available.

---

## Give a real-world example.

A database has **20 connections**.

Initialize:

```text
Semaphore = 20
```

Each request:

1. Acquire a connection.
2. Execute the query.
3. Release the connection.

No more than 20 requests use the database simultaneously.

---

# When to Use What?

| Scenario | Best Choice |
|----------|-------------|
| Protect shared variable | Mutex |
| Database connection pool | Semaphore |
| Thread-safe counter | Atomic |
| Allow N concurrent workers | Counting Semaphore |
| One thread at a time | Mutex |

---

# 30-Second Interview Answer

> A **semaphore** is a synchronization primitive that controls access to a limited number of shared resources using an internal counter. A thread performs **acquire (wait)** to decrement the counter before using a resource and **release (signal)** to increment it when finished. If the counter is zero, additional threads block until a resource becomes available. Unlike a mutex, a semaphore has **no ownership**, and multiple threads may access the protected resource concurrently up to the semaphore's limit. Semaphores are commonly used for connection pools, producer-consumer queues, and limiting concurrent access to shared resources.

***

# Locks

> **Note:** The Google Interview Prep Guide states that candidates should understand **locks, mutexes, semaphores, monitors, and concurrency issues**, but it does **not** define or explain them. The material below is additional interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is a Lock?

A **lock** is a synchronization mechanism that ensures shared resources are accessed safely by multiple threads.

Its primary goal is to prevent:

- Race conditions
- Data corruption
- Inconsistent program state

A lock ensures that only the allowed thread(s) can execute a **critical section** at a given time.

Think of a lock as the **general concept** of controlling access to shared resources.

A **mutex**, **spinlock**, and **read-write lock** are all specific types of locks.

---

# Why Do We Need Locks?

Suppose multiple threads update a shared bank account.

```cpp
balance += 100;
```

Without synchronization:

```text
Thread A reads balance = 1000

↓

Thread B reads balance = 1000

↓

Thread A writes 1100

↓

Thread B writes 1100

Final balance = 1100 ❌

Expected = 1200
```

This is a **race condition**.

A lock guarantees that only one thread updates the balance at a time.

---

# Critical Section

A **critical section** is any block of code that accesses shared mutable data.

Example:

```cpp
balance += amount;
transactions.push_back(amount);
```

Because multiple threads could modify these objects simultaneously, this section should be protected by a lock.

---

# Basic Lock Workflow

```text
Acquire Lock

↓

Execute Critical Section

↓

Release Lock
```

Only one thread can execute the critical section while the lock is held.

---

# Example (C++)

```cpp
std::mutex lock;

void deposit(int amount)
{
    lock.lock();

    balance += amount;

    lock.unlock();
}
```

Modern C++ prefers RAII:

```cpp
std::mutex lock;

void deposit(int amount)
{
    std::lock_guard<std::mutex> guard(lock);

    balance += amount;
}
```

The lock is automatically released when the scope ends.

---

# Types of Locks

## 1. Mutex (Exclusive Lock)

Only one thread can own the lock.

```text
Thread A

↓

Lock

↓

Critical Section

↓

Unlock

↓

Thread B
```

Use for protecting shared variables and data structures.

---

## 2. Spinlock

Instead of sleeping, a waiting thread repeatedly checks whether the lock becomes available.

```text
Lock Busy

↓

Check

↓

Check

↓

Check

↓

Acquire
```

Advantages:

- Very fast for extremely short critical sections.

Disadvantages:

- Wastes CPU while waiting.

Typical use:

- Operating system kernels
- Low-level synchronization

---

## 3. Read-Write Lock (Shared Mutex)

Allows:

- Multiple readers simultaneously
- Only one writer

```text
Reader A ✔

Reader B ✔

Reader C ✔

Writer ✘ (waits)
```

When writing:

```text
Writer ✔

Readers wait
```

Useful when reads greatly outnumber writes.

C++:

```cpp
std::shared_mutex
```

---

## 4. Recursive Lock

Allows the same thread to acquire the same lock multiple times.

```cpp
lock();

lock();

unlock();

unlock();
```

Useful in some recursive algorithms, but generally avoided because it can hide design problems.

---

# Lock Granularity

## Coarse-Grained Locking

One lock protects a large section of code.

```text
Entire Database

↓

One Lock
```

Advantages:

- Simple
- Easier to reason about

Disadvantages:

- Lower concurrency

---

## Fine-Grained Locking

Each resource has its own lock.

```text
Account A -> Lock A

Account B -> Lock B

Account C -> Lock C
```

Advantages:

- Better parallelism

Disadvantages:

- More complex
- Higher risk of deadlocks

---

# Lock Contention

Contention occurs when many threads compete for the same lock.

Example:

```text
10 Threads

↓

One Lock

↓

Only one executes

↓

Nine wait
```

High contention reduces application performance.

---

# Deadlock

Deadlock occurs when threads wait forever for each other's locks.

Example:

```text
Thread A

Lock X

↓

Waiting for Lock Y

--------------------

Thread B

Lock Y

↓

Waiting for Lock X
```

Neither thread can continue.

---

# Avoiding Deadlocks

Common techniques:

- Always acquire locks in the same order.
- Keep critical sections short.
- Avoid nested locking when possible.
- Use `try_lock()` with timeout.
- Prefer higher-level synchronization primitives when appropriate.

---

# Lock Ordering Example

Bad:

```text
Thread A

Lock A

↓

Lock B

----------------

Thread B

Lock B

↓

Lock A
```

Good:

```text
All threads acquire:

Lock A

↓

Lock B
```

A consistent lock order prevents circular waits.

---

# Lock Scope

Keep locks only as long as necessary.

Bad:

```cpp
lock();

downloadFile();

updateDatabase();

unlock();
```

The file download unnecessarily blocks other threads.

Better:

```cpp
downloadFile();

lock();

updateDatabase();

unlock();
```

Only protect the shared data.

---

# Lock vs Mutex

These terms are often used interchangeably, but they are not exactly the same.

| Lock | Mutex |
|------|-------|
| General synchronization concept | Specific type of lock |
| May allow multiple readers | Exclusive ownership |
| Includes many implementations | One implementation |
| Generic term | Mutual exclusion lock |

A **mutex is a type of lock**, but not every lock is a mutex.

---

# Lock vs Spinlock

| Mutex | Spinlock |
|--------|----------|
| Waiting thread sleeps | Waiting thread spins |
| Lower CPU usage | Higher CPU usage |
| Better for long waits | Better for very short waits |

---

# Lock vs Semaphore

| Lock | Semaphore |
|------|-----------|
| Protects critical sections | Controls resource availability |
| Usually one owner | No ownership |
| Typically exclusive | May allow multiple threads |

---

# Lock vs Atomic

| Lock | Atomic |
|------|--------|
| Protects complex operations | Protects single atomic operations |
| May block | Never blocks |
| More flexible | Faster for simple operations |

---

# Advantages

- Prevents race conditions.
- Protects shared state.
- Easy to understand.
- Essential for thread-safe programming.

---

# Disadvantages

- Can reduce parallelism.
- Lock contention impacts performance.
- Incorrect use can cause deadlocks.
- Increases implementation complexity.

---

# Common Interview Questions

## What problem do locks solve?

Locks prevent multiple threads from simultaneously modifying shared data, avoiding race conditions and inconsistent state.

---

## What is a critical section?

A critical section is code that accesses shared mutable resources and must not execute concurrently with conflicting operations.

---

## Why should locks be held for as little time as possible?

Long critical sections increase contention, reduce concurrency, and decrease overall application performance.

---

## What is lock contention?

Lock contention occurs when multiple threads compete for the same lock, causing waiting and reduced throughput.

---

## What is lock granularity?

Lock granularity refers to how much data or code a single lock protects.

- **Coarse-grained locking:** fewer locks, simpler design, lower concurrency.
- **Fine-grained locking:** more locks, higher concurrency, greater complexity.

---

## Can locks cause deadlocks?

Yes.

Deadlocks occur when threads acquire locks in inconsistent orders and wait indefinitely for each other.

---

## When would you use a read-write lock?

When the workload consists of many concurrent reads but relatively few writes, allowing readers to proceed in parallel while writers obtain exclusive access.

---

# 30-Second Interview Answer

> A **lock** is a synchronization mechanism used to protect shared resources from concurrent access. A thread acquires the lock before entering a critical section and releases it afterward, ensuring the shared data remains consistent. There are different kinds of locks, such as **mutexes**, **spinlocks**, and **read-write locks**, each optimized for different scenarios. While locks prevent race conditions, they can also introduce contention and deadlocks if used incorrectly. Good practice is to keep critical sections short, acquire locks in a consistent order, and use higher-level abstractions like `std::lock_guard` or `std::unique_lock` in modern C++.

***

# Deadlock

> **Note:** The Google Interview Prep Guide states that candidates should understand **deadlocks, livelocks, locks, mutexes, semaphores, and concurrency**, but it does **not** explain these concepts. The material below is additional interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is a Deadlock?

A **deadlock** is a situation where **two or more threads (or processes) are permanently blocked because each is waiting for a resource held by another**.

As a result:

- No thread can continue.
- No thread releases its resource.
- The application becomes stuck indefinitely.

---

# Simple Example

Suppose we have two mutexes:

```text
Mutex A
Mutex B
```

Two threads execute the following:

### Thread 1

```text
Lock A

↓

Lock B
```

### Thread 2

```text
Lock B

↓

Lock A
```

Execution:

```text
Thread 1 locks A

↓

Thread 2 locks B

↓

Thread 1 waits for B

↓

Thread 2 waits for A

↓

Nobody can continue
```

This is a **deadlock**.

---

# Visualization

```text
Thread 1
──────────────

Owns Lock A

↓

Waiting for Lock B

────────────────────────

Thread 2

Owns Lock B

↓

Waiting for Lock A
```

Both threads wait forever.

---

# Real-World Analogy

Imagine two cars on a one-lane bridge.

```text
Car A

↓

Bridge

↓

Car B
```

Neither car can move forward.

Neither car backs up.

Traffic stops forever.

That is a deadlock.

---

# The Four Necessary Conditions (Coffman Conditions)

A deadlock can occur only if **all four** of these conditions hold simultaneously.

---

## 1. Mutual Exclusion

A resource can be used by only one thread at a time.

Example:

```text
Printer

↓

Only one thread may use it
```

---

## 2. Hold and Wait

A thread holds one resource while waiting for another.

Example:

```text
Thread A

Holding Lock A

↓

Waiting for Lock B
```

---

## 3. No Preemption

Resources cannot be forcibly taken away.

Only the owning thread may release the resource.

Example:

```text
Thread A owns Mutex

↓

OS cannot steal it
```

---

## 4. Circular Wait

A cycle exists where each thread waits for a resource held by another.

Example:

```text
Thread A

↓

Waiting for Thread B

↓

Waiting for Thread C

↓

Waiting for Thread A
```

This circular dependency creates the deadlock.

---

# Deadlock Example (C++)

```cpp
std::mutex m1;
std::mutex m2;

void thread1() {
    std::lock_guard<std::mutex> lock1(m1);

    std::this_thread::sleep_for(std::chrono::milliseconds(100));

    std::lock_guard<std::mutex> lock2(m2);
}

void thread2() {
    std::lock_guard<std::mutex> lock2(m2);

    std::this_thread::sleep_for(std::chrono::milliseconds(100));

    std::lock_guard<std::mutex> lock1(m1);
}
```

Possible execution:

```text
Thread 1

Locks m1

↓

Waits for m2

-------------------

Thread 2

Locks m2

↓

Waits for m1
```

Both threads block forever.

---

# Resource Allocation Graph

Deadlocks are often represented using a graph.

```text
Thread A

↓

Lock B

↑

↓

Thread B

↓

Lock A

↑
```

The cycle indicates a potential deadlock.

---

# How to Prevent Deadlocks

## 1. Lock Ordering (Most Common Solution)

Always acquire locks in the same order.

Bad:

```text
Thread A

Lock A

↓

Lock B

----------------

Thread B

Lock B

↓

Lock A
```

Good:

```text
Every thread

Lock A

↓

Lock B
```

No circular wait can occur.

---

## 2. Hold Only One Lock

Avoid holding multiple locks simultaneously whenever possible.

Instead of:

```text
Lock A

↓

Lock B

↓

Critical Section
```

Use:

```text
Lock A

↓

Unlock A

↓

Lock B
```

This reduces the risk of circular waits.

---

## 3. Use `std::lock()`

C++ provides a deadlock-safe way to acquire multiple mutexes.

```cpp
std::lock(m1, m2);

std::lock_guard<std::mutex> lock1(m1, std::adopt_lock);
std::lock_guard<std::mutex> lock2(m2, std::adopt_lock);
```

`std::lock()` guarantees that the mutexes are acquired without deadlocking.

---

## 4. Use `try_lock()`

Instead of waiting forever:

```cpp
if (m.try_lock()) {
    // Critical section

    m.unlock();
}
```

If the lock is unavailable:

- Retry later.
- Back off.
- Perform other work.

---

## 5. Timeout

Many synchronization primitives support timeouts.

```cpp
mutex.try_lock_for(100ms);
```

If the timeout expires:

- Abort.
- Retry.
- Report an error.

---

# Deadlock Detection

Operating systems or applications may periodically inspect waiting threads.

Example:

```text
Thread A

↓

Waiting for B

↓

Waiting for C

↓

Waiting for A
```

A cycle indicates a deadlock.

Possible recovery:

- Kill one thread.
- Abort one transaction.
- Restart the operation.

Databases commonly use deadlock detection.

---

# Deadlock vs Starvation

| Deadlock | Starvation |
|-----------|------------|
| Threads wait forever | One thread waits indefinitely |
| No thread makes progress | Other threads continue |
| Circular dependency | Resource scheduling problem |

---

# Deadlock vs Livelock

| Deadlock | Livelock |
|-----------|-----------|
| Threads are blocked | Threads continue running |
| No work is done | Still no useful work is completed |
| Waiting forever | Constantly retrying |

Example of livelock:

```text
Person A

"I'll let you go."

↓

Person B

"No, you first."

↓

Both step aside

↓

Repeat forever
```

Both are active but make no progress.

---

# Deadlock vs Race Condition

| Deadlock | Race Condition |
|-----------|----------------|
| Threads stop | Threads continue |
| Program freezes | Program behaves unpredictably |
| Caused by lock dependencies | Caused by unsynchronized access |

---

# Common Causes

- Inconsistent lock ordering.
- Nested locks.
- Multiple shared resources.
- Long critical sections.
- Complex synchronization logic.

---

# Best Practices

- Always acquire locks in a consistent order.
- Keep critical sections short.
- Prefer RAII (`std::lock_guard`, `std::unique_lock`).
- Use `std::lock()` when acquiring multiple mutexes.
- Avoid unnecessary nested locking.
- Consider higher-level concurrency primitives when appropriate.

---

# Common Interview Questions

## What is a deadlock?

A deadlock occurs when two or more threads wait indefinitely for resources held by one another, so none can continue.

---

## What are the four Coffman conditions?

1. Mutual Exclusion
2. Hold and Wait
3. No Preemption
4. Circular Wait

All four must exist for a deadlock to occur.

---

## How can you prevent deadlocks?

- Acquire locks in a consistent order.
- Keep critical sections short.
- Use `std::lock()` for multiple mutexes.
- Use `try_lock()` or timeouts.
- Avoid nested locking when possible.

---

## What is the easiest way to avoid deadlocks?

The simplest and most common technique is **consistent lock ordering**.

Every thread acquires locks in exactly the same order.

---

## Can deadlocks occur with one mutex?

Generally, **no**.

Deadlocks usually require multiple resources or recursive locking of a non-recursive mutex.

---

## Can operating systems detect deadlocks?

Some systems can.

Examples include:

- Database management systems
- Certain operating systems
- Distributed systems

Others rely on prevention instead of detection.

---

# Interview Example

Suppose two bank accounts need to be updated.

Bad implementation:

```text
Transfer A → B

Lock Account A

↓

Lock Account B

----------------------

Transfer B → A

Lock Account B

↓

Lock Account A
```

This may deadlock.

Correct implementation:

```text
Always lock the account with the smaller ID first.

↓

Then lock the second account.
```

This guarantees a consistent lock order and prevents deadlocks.

---

# 30-Second Interview Answer

> A **deadlock** occurs when two or more threads are permanently blocked because each is waiting for a resource held by another. Deadlocks require four conditions: **mutual exclusion**, **hold and wait**, **no preemption**, and **circular wait**. The most common cause is inconsistent lock ordering. The best prevention technique is to acquire locks in a consistent order, keep critical sections short, and use utilities like `std::lock()` when locking multiple mutexes. Deadlocks differ from race conditions because threads stop making progress entirely, whereas race conditions produce incorrect or unpredictable results while execution continues.

***


# Livelock

> **Note:** The Google Interview Prep Guide states that candidates should understand **livelocks** as part of operating systems and concurrency, but it does **not** explain the concept. The explanations below are additional interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is a Livelock?

A **livelock** is a situation where **two or more threads continuously respond to each other and change their state, but no thread makes useful progress**.

Unlike a deadlock:

- Threads are **not blocked**.
- Threads continue **running**.
- CPU is actively used.
- The desired work is **never completed**.

---

# Deadlock vs Livelock

## Deadlock

```text
Thread A

Waiting...

Thread B

Waiting...
```

Result:

- Nobody moves.
- CPU usage is low.
- Threads are blocked forever.

---

## Livelock

```text
Thread A

Retry...

Retry...

Retry...

Thread B

Retry...

Retry...

Retry...
```

Result:

- Everybody keeps running.
- CPU usage is high.
- No useful work is completed.

---

# Real-World Analogy

Imagine two people walking toward each other in a hallway.

```text
Person A

↓

Person B
```

Both try to be polite.

```text
Person A steps left.

↓

Person B also steps left.

↓

Person A steps right.

↓

Person B also steps right.

↓

Repeat forever.
```

Neither person is blocked.

Neither person passes.

This is a **livelock**.

---

# Example

Suppose two threads need two locks.

Instead of waiting, they immediately release their lock if they cannot obtain the second one.

Thread A:

```text
Lock A

↓

Try Lock B

↓

Fail

↓

Unlock A

↓

Retry
```

Thread B:

```text
Lock B

↓

Try Lock A

↓

Fail

↓

Unlock B

↓

Retry
```

Execution:

```text
Thread A locks A

↓

Thread B locks B

↓

Both fail

↓

Both unlock

↓

Both retry

↓

Repeat forever
```

No deadlock occurs.

However, neither thread completes.

---

# Visualization

```text
Thread A

Acquire Lock A

↓

Cannot acquire Lock B

↓

Release Lock A

↓

Retry

↓

Repeat
```

```text
Thread B

Acquire Lock B

↓

Cannot acquire Lock A

↓

Release Lock B

↓

Retry

↓

Repeat
```

Both remain active forever.

---

# Why Does Livelock Happen?

Livelock usually occurs because threads are **too polite**.

Instead of waiting:

```text
"I'll let you go."

↓

"No, I'll let you go."

↓

"I'll let you go."

↓

"No, I'll let you go."
```

Everyone cooperates.

Nobody finishes.

---

# Example Using `try_lock()`

```cpp
while (true) {
    if (mutex1.try_lock()) {

        if (mutex2.try_lock()) {
            // Critical section

            mutex2.unlock();
            mutex1.unlock();
            break;
        }

        mutex1.unlock();
    }
}
```

If two threads execute this simultaneously, they may repeatedly:

- acquire one mutex,
- fail to acquire the second,
- release,
- retry,

creating a livelock.

---

# Why Isn't This a Deadlock?

Because:

- Threads are **not waiting**.
- Threads continue executing.
- Locks are continuously acquired and released.
- CPU remains busy.

---

# Common Causes

- Aggressive retry loops.
- Poor backoff algorithms.
- Symmetric retry behavior.
- Overly polite conflict resolution.
- Excessive use of `try_lock()` without randomness.

---

# How to Prevent Livelock

## 1. Random Backoff (Most Common)

Instead of retrying immediately:

```text
Retry

↓

Sleep Random Time

↓

Retry Again
```

Different retry times reduce repeated collisions.

---

## 2. Exponential Backoff

Each retry waits longer.

Example:

```text
Attempt 1

10 ms

↓

Attempt 2

20 ms

↓

Attempt 3

40 ms

↓

Attempt 4

80 ms
```

Common in:

- Ethernet (CSMA/CD)
- Wi-Fi
- Distributed systems

---

## 3. Fixed Lock Ordering

Acquire resources in the same order.

```text
Always

Lock A

↓

Lock B
```

This also helps prevent deadlocks.

---

## 4. Use Blocking Locks

Instead of:

```cpp
try_lock()
```

Use:

```cpp
lock()
```

The thread blocks instead of repeatedly retrying.

---

## 5. Limit Retries

Instead of:

```text
Retry Forever
```

Use:

```text
Retry 5 Times

↓

Report Failure
```

---

# Livelock vs Deadlock

| Deadlock | Livelock |
|-----------|-----------|
| Threads blocked | Threads active |
| CPU mostly idle | CPU busy |
| Waiting forever | Retrying forever |
| No state changes | Constant state changes |
| No progress | No progress |

---

# Livelock vs Starvation

| Livelock | Starvation |
|-----------|------------|
| Multiple threads affected | Usually one thread affected |
| Everyone keeps retrying | One thread never gets scheduled or resources |
| Continuous state changes | Other threads continue normally |

---

# Livelock vs Race Condition

| Livelock | Race Condition |
|-----------|----------------|
| Threads make no progress | Threads make progress incorrectly |
| CPU busy | CPU busy or idle |
| Caused by retry behavior | Caused by unsynchronized shared access |

---

# Advantages

Livelock is **not desirable**.

However, algorithms that avoid deadlocks by releasing locks immediately may intentionally risk livelock because it can often be resolved with randomized backoff or improved scheduling.

---

# Disadvantages

- Wastes CPU.
- No useful work is completed.
- Difficult to detect.
- Can significantly reduce throughput.

---

# Common Interview Questions

## What is a livelock?

A livelock occurs when threads continuously react to one another and keep changing state, but none makes useful progress.

---

## How is a livelock different from a deadlock?

In a deadlock:

- Threads are blocked.

In a livelock:

- Threads continue executing but never complete their work.

---

## Why does `try_lock()` sometimes cause livelock?

If multiple threads repeatedly acquire one lock, fail to acquire another, release, and immediately retry in the same pattern, they may continually interfere with each other.

---

## How do you prevent livelock?

Common techniques include:

- Random backoff.
- Exponential backoff.
- Fixed lock ordering.
- Blocking locks.
- Limiting retry attempts.

---

## Which consumes more CPU: deadlock or livelock?

A **livelock** generally consumes much more CPU because threads continue executing and retrying.

A **deadlock** typically consumes little CPU because threads are blocked.

---

## Give a real-world example.

Two network nodes detect a collision.

Both immediately retry transmission.

Another collision occurs.

Both retry again.

If both always retry simultaneously, neither succeeds.

Protocols such as Ethernet solve this by using **random exponential backoff**.

---

# Interview Summary

| Problem | State | CPU Usage | Progress |
|----------|-------|-----------|----------|
| Race Condition | Running | Varies | Incorrect |
| Deadlock | Blocked | Low | None |
| Livelock | Running | High | None |
| Starvation | Running | Varies | Some threads only |

---

# 30-Second Interview Answer

> A **livelock** occurs when two or more threads continuously react to each other's actions, repeatedly changing state without making useful progress. Unlike a deadlock, the threads are **not blocked**—they remain active and consume CPU time, but the intended work never completes. Livelocks often occur when threads repeatedly acquire and release locks using `try_lock()` in the same pattern. Common prevention techniques include **random or exponential backoff**, consistent lock ordering, using blocking locks where appropriate, and limiting the number of retries.


***



# Context Switching

> **Note:** The Google Interview Prep Guide states that candidates should understand **how context switching works** as part of operating systems and concurrency, but it does **not** explain the concept in detail. The material below is additional interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is Context Switching?

A **context switch** is the process by which the **operating system saves the state (context) of one running process or thread and restores the state of another**, allowing the CPU to switch execution between them.

Since a CPU core can execute **only one thread at a time** (ignoring simultaneous multithreading like Intel Hyper-Threading), context switching enables **multitasking**.

---

# Why is Context Switching Needed?

Suppose a computer runs:

- Chrome
- Spotify
- Visual Studio Code

But the CPU has only **one core**.

The operating system rapidly switches between these programs:

```text
Chrome

↓

VS Code

↓

Spotify

↓

Chrome

↓

VS Code
```

The switching happens so quickly (often in milliseconds) that users perceive all applications as running simultaneously.

---

# What is a Context?

A **context** is the complete execution state of a thread or process.

It includes:

- Program Counter (PC)
- CPU Registers
- Stack Pointer (SP)
- CPU Flags
- Scheduling Information

For a **process**, it also includes:

- Virtual Address Space
- Page Tables
- Memory Mappings

---

# Context Switching Workflow

```text
Running Thread A

↓

Save Context

↓

Scheduler Chooses Thread B

↓

Load Context

↓

Resume Thread B
```

The switch is transparent to the application.

---

# Step-by-Step Example

Suppose Thread A is executing:

```cpp
int x = 5;
x++;
```

Before it finishes:

```text
Timer Interrupt

↓

Operating System

↓

Save Registers

↓

Save Program Counter

↓

Save Stack Pointer
```

Later:

```text
Restore Registers

↓

Restore Program Counter

↓

Resume Execution
```

The thread continues exactly where it left off.

---

# What Gets Saved?

Typical CPU state:

- General-purpose registers
- Program Counter (Instruction Pointer)
- Stack Pointer
- CPU Flags
- Floating-point registers (if used)

Operating system state:

- Thread state
- Scheduling information
- CPU affinity
- Priority

---

# Process Context Switch

Switching between **processes** requires:

- Saving CPU registers
- Switching virtual address spaces
- Updating page tables
- Changing memory mappings
- Flushing or updating the Translation Lookaside Buffer (TLB)

```text
Process A

↓

Save CPU State

↓

Switch Address Space

↓

Load Process B
```

This is relatively expensive.

---

# Thread Context Switch

Switching between **threads of the same process** requires:

- Saving registers
- Saving stack pointer
- Saving program counter

Since the address space is shared, **no virtual memory switch is required**.

```text
Thread A

↓

Save Registers

↓

Load Thread B

↓

Continue
```

This is significantly cheaper than a process context switch.

---

# Process vs Thread Context Switch

| Process Switch | Thread Switch |
|---------------|---------------|
| Changes address space | Same address space |
| Switches page tables | No page table switch |
| Higher overhead | Lower overhead |
| More expensive | Faster |

---

# When Does Context Switching Occur?

## 1. Time Slice Expiration

Most operating systems use **preemptive multitasking**.

Example:

```text
Thread A

Runs for 5 ms

↓

Timer Interrupt

↓

Switch to Thread B
```

---

## 2. Blocking on I/O

A thread performs:

```cpp
read(file);
```

Instead of wasting CPU time:

```text
Thread A waits

↓

CPU switches

↓

Thread B runs
```

---

## 3. Waiting for a Lock

```cpp
mutex.lock();
```

If the mutex is unavailable:

```text
Thread blocks

↓

Scheduler selects another thread
```

---

## 4. Higher Priority Thread Arrives

Suppose:

```text
Low Priority Thread

Running
```

Then:

```text
High Priority Thread

Becomes Ready
```

The scheduler may immediately preempt the low-priority thread.

---

## 5. Explicit Yield

A thread voluntarily gives up the CPU.

Example:

```cpp
std::this_thread::yield();
```

---

# Hardware vs Software Trigger

### Hardware

- Timer interrupt
- External interrupt
- Device interrupt

### Software

- System call
- Blocking I/O
- Yield
- Sleep

---

# Scheduler

The **scheduler** decides which thread or process should run next.

Common scheduling goals:

- Fairness
- Responsiveness
- Throughput
- CPU utilization

The scheduler typically maintains queues such as:

```text
Ready Queue

↓

Running

↓

Blocked

↓

Ready
```

---

# Why Context Switching is Expensive

Although useful, context switching performs **no application work**.

Costs include:

- Saving registers
- Loading registers
- Kernel mode transitions
- Cache invalidation
- TLB flushes (process switches)
- Pipeline disruption

Frequent context switching can significantly reduce performance.

---

# Context Switching Overhead

Example:

```text
Useful Work

5 ms

↓

Context Switch

0.1 ms

↓

Useful Work

5 ms

↓

Context Switch

0.1 ms
```

Too many switches reduce effective CPU time available for useful work.

---

# Cache Effects

Modern CPUs rely heavily on caches.

When switching threads:

```text
Thread A

Uses Cache

↓

Switch

↓

Thread B

Loads Different Data

↓

Cache Misses
```

This can significantly impact performance.

---

# User Mode vs Kernel Mode

A context switch typically involves entering the **kernel**, where the scheduler runs.

```text
User Mode

↓

Kernel Mode

↓

Scheduler

↓

Kernel Mode

↓

User Mode
```

Not every transition to kernel mode results in a context switch, but every scheduler-driven context switch requires kernel involvement.

---

# Advantages

- Enables multitasking.
- Improves CPU utilization.
- Keeps systems responsive.
- Allows efficient scheduling of multiple applications.

---

# Disadvantages

- CPU overhead.
- Cache invalidation.
- Pipeline stalls.
- TLB flushes (process switches).
- Reduced throughput if switches are too frequent.

---

# Common Interview Questions

## What is a context switch?

A context switch is the operating system saving the execution state of one thread or process and restoring another so the CPU can execute it.

---

## Why are thread context switches cheaper than process context switches?

Threads within the same process share the same virtual address space, so there is no need to switch page tables or memory mappings.

---

## What triggers a context switch?

Common triggers include:

- Timer interrupt
- Blocking I/O
- Waiting for a lock
- Higher-priority thread becoming ready
- Voluntary yield

---

## What information is saved during a context switch?

Typically:

- CPU registers
- Program Counter
- Stack Pointer
- CPU flags
- Scheduling state

Process switches additionally require switching virtual memory state.

---

## Why is context switching expensive?

Because it performs no useful application work and may involve:

- Saving/restoring CPU state
- Entering kernel mode
- Cache misses
- TLB updates or flushes
- CPU pipeline disruption

---

## Can context switching happen without multiple CPU cores?

Yes.

Even on a single-core CPU, the operating system rapidly switches between threads or processes, creating the illusion of parallel execution.

---

# Real-World Example

Suppose your computer is running:

- Google Chrome
- Slack
- Spotify
- Visual Studio Code

The operating system repeatedly performs:

```text
Chrome

↓

VS Code

↓

Slack

↓

Spotify

↓

Chrome
```

Each switch saves the current application's execution state and restores the next one, allowing all applications to appear responsive.

---

# Interview Summary

| Topic | Process Context Switch | Thread Context Switch |
|--------|------------------------|-----------------------|
| Address Space Switch | Yes | No |
| Page Table Switch | Yes | No |
| Register Save/Restore | Yes | Yes |
| Stack Switch | Yes | Yes |
| TLB Update/Flush | Usually | No |
| Relative Cost | High | Low |

---

# 30-Second Interview Answer

> A **context switch** is the operating system's process of saving the execution state of one thread or process and restoring another so the CPU can switch execution between them. The saved context includes items such as the **program counter, CPU registers, and stack pointer**. Process context switches are more expensive because they also require switching the virtual address space and page tables, while thread context switches within the same process reuse the existing address space. Context switches are triggered by events such as timer interrupts, blocking I/O, waiting for locks, higher-priority threads becoming ready, or voluntary yielding. Although essential for multitasking, excessive context switching reduces performance because it incurs scheduling overhead and disrupts CPU caches.


***



# Scheduling (CPU Scheduling)

> **Note:** The Google Interview Prep Guide states that candidates should understand **scheduling** as part of operating systems and concurrency, but it does **not** explain scheduling algorithms or implementation details. The material below is additional interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is CPU Scheduling?

**CPU Scheduling** is the process by which the **operating system decides which process or thread should run next on the CPU**.

Since there are usually more runnable tasks than available CPU cores, the scheduler determines:

- Which task runs next.
- How long it runs.
- When it should be interrupted.

The scheduler aims to maximize CPU utilization while keeping the system responsive and fair.

---

# Why is Scheduling Needed?

Suppose your computer is running:

- Chrome
- Visual Studio Code
- Spotify
- Slack

But your CPU has only **2 cores**.

The operating system cannot execute all programs simultaneously.

Instead:

```text
CPU

↓

Chrome

↓

VS Code

↓

Spotify

↓

Slack

↓

Chrome
```

The scheduler rapidly switches between tasks.

---

# Scheduler Goals

A good scheduler tries to achieve:

- High CPU utilization
- High throughput
- Low response time
- Low waiting time
- Low turnaround time
- Fairness among processes
- Avoid starvation

---

# Process States

A process (or thread) moves between different states.

```text
New

↓

Ready

↓

Running

↓

Waiting (Blocked)

↓

Ready

↓

Running

↓

Terminated
```

---

# Ready Queue

The scheduler chooses tasks from the **Ready Queue**.

```text
Ready Queue

Process A

Process B

Process C

↓

Scheduler

↓

CPU
```

Only one process (per CPU core) can be in the **Running** state.

---

# Types of Schedulers

## Long-Term Scheduler

Determines which jobs enter the system.

Responsible for:

- Controlling system load.
- Managing the number of active processes.

Modern desktop operating systems rarely expose this separately.

---

## Short-Term Scheduler (CPU Scheduler)

Runs very frequently.

Responsibilities:

- Chooses the next process or thread.
- Performs context switching.
- Allocates CPU time.

This is the scheduler typically discussed in interviews.

---

## Medium-Term Scheduler

Temporarily suspends (swaps out) processes to reduce memory pressure.

Example:

```text
Running

↓

Swapped Out

↓

Later Restored
```

---

# Preemptive vs Non-Preemptive Scheduling

## Preemptive Scheduling

The operating system may interrupt a running process.

```text
Thread A

Running

↓

Timer Interrupt

↓

Thread B Runs
```

Examples:

- Linux
- Windows
- macOS

Advantages:

- Better responsiveness.
- Fair CPU sharing.

---

## Non-Preemptive Scheduling

A process keeps the CPU until it:

- Finishes.
- Blocks.
- Voluntarily yields.

```text
Thread A

Runs

Runs

Runs

Finishes

↓

Thread B
```

Advantages:

- Simpler implementation.
- Lower context-switch overhead.

Disadvantages:

- Poor responsiveness.

---

# Common Scheduling Algorithms

---

## 1. First-Come, First-Served (FCFS)

Processes execute in arrival order.

```text
Arrival

P1

P2

P3

↓

Execution

P1

↓

P2

↓

P3
```

Advantages:

- Very simple.
- No starvation.

Disadvantages:

- Poor average waiting time.
- Suffers from the **Convoy Effect** (a long-running process delays shorter ones).

---

## 2. Shortest Job First (SJF)

The process with the shortest execution time runs first.

Example:

```text
P1 = 10 ms

P2 = 2 ms

P3 = 5 ms

↓

P2

↓

P3

↓

P1
```

Advantages:

- Minimizes average waiting time.

Disadvantages:

- Requires knowing execution time in advance.
- Long jobs may starve.

---

## 3. Shortest Remaining Time First (SRTF)

Preemptive version of SJF.

If a shorter job arrives:

```text
Running Job

↓

New Shorter Job Arrives

↓

Immediate Switch
```

Advantages:

- Excellent average response time.

Disadvantages:

- More context switching.
- Possible starvation.

---

## 4. Round Robin (RR)

Each process receives a fixed **time quantum**.

Example:

```text
Quantum = 5 ms

P1

↓

P2

↓

P3

↓

P1

↓

P2
```

Advantages:

- Fair.
- Excellent for interactive systems.

Disadvantages:

- Too small a quantum increases context switching.
- Too large a quantum behaves like FCFS.

---

## 5. Priority Scheduling

Each process has a priority.

Highest priority executes first.

```text
Priority

P2 = High

P1 = Medium

P3 = Low

↓

Execution

P2

↓

P1

↓

P3
```

Advantages:

- Important tasks execute sooner.

Disadvantages:

- Low-priority processes may starve.

Solution:

**Priority Aging** gradually increases the priority of waiting processes.

---

## 6. Multilevel Queue Scheduling

Separate queues for different classes of tasks.

Example:

```text
System Processes

↓

Interactive Processes

↓

Background Processes
```

Each queue may use a different scheduling algorithm.

---

## 7. Multilevel Feedback Queue (MLFQ)

Processes move between queues based on behavior.

Example:

```text
High Priority Queue

↓

Medium Queue

↓

Low Queue
```

Interactive tasks remain near the top.

CPU-intensive tasks gradually move lower.

Modern operating systems use algorithms inspired by MLFQ.

---

# Scheduling Metrics

## CPU Utilization

Percentage of time the CPU performs useful work.

Higher is better.

---

## Throughput

Number of completed processes per unit of time.

Higher is better.

---

## Turnaround Time

Total time from process arrival until completion.

```text
Turnaround Time

=

Completion Time

-

Arrival Time
```

---

## Waiting Time

Time spent waiting in the ready queue.

```text
Waiting Time

=

Turnaround Time

-

CPU Execution Time
```

---

## Response Time

Time until the process first receives CPU time.

```text
Arrival

↓

First Execution
```

Important for interactive applications.

---

# Starvation

A process waits indefinitely because higher-priority tasks continuously run.

Example:

```text
Low Priority Task

↓

High Priority Tasks Keep Arriving

↓

Low Priority Never Executes
```

Solution:

Use **priority aging**.

---

# Scheduling and Context Switching

Scheduling determines **what** runs next.

Context switching performs the actual transition.

```text
Scheduler

↓

Choose Next Thread

↓

Context Switch

↓

CPU Executes
```

---

# Real-World Example

Suppose:

```text
CPU

↓

Chrome

↓

Spotify

↓

VS Code

↓

Slack
```

The scheduler rapidly allocates CPU time to each application, ensuring all remain responsive.

---

# Advantages of Good Scheduling

- Better CPU utilization.
- Faster response time.
- Higher throughput.
- Improved user experience.
- Fair distribution of CPU time.

---

# Disadvantages

- Frequent context switching introduces overhead.
- Complex algorithms are harder to implement.
- Poor scheduling can lead to starvation or unfairness.

---

# Common Interview Questions

## What is CPU scheduling?

CPU scheduling is the operating system's mechanism for deciding which runnable process or thread should execute next.

---

## What is the difference between preemptive and non-preemptive scheduling?

**Preemptive scheduling** allows the operating system to interrupt a running process.

**Non-preemptive scheduling** lets a process run until it blocks or finishes.

---

## Which scheduling algorithm is used by modern operating systems?

Modern operating systems generally use sophisticated, priority-based schedulers inspired by **Multilevel Feedback Queues (MLFQ)**, with many additional optimizations.

---

## What is starvation?

Starvation occurs when a process waits indefinitely because other processes continuously receive the CPU.

---

## How can starvation be prevented?

A common solution is **priority aging**, which gradually increases the priority of waiting processes.

---

## What is the difference between waiting time and response time?

- **Waiting Time:** Total time spent waiting in the ready queue.
- **Response Time:** Time from arrival until the process first begins executing.

---

## Which algorithm is best for interactive systems?

**Round Robin** is well-suited for interactive systems because it gives every process regular CPU time, providing good responsiveness.

---

# Comparison of Scheduling Algorithms

| Algorithm | Preemptive | Advantages | Disadvantages |
|------------|------------|------------|---------------|
| FCFS | No | Simple, no starvation | Convoy effect |
| SJF | No | Lowest average waiting time | Requires execution time estimate |
| SRTF | Yes | Excellent response time | More context switches, starvation |
| Round Robin | Yes | Fair, responsive | Quantum selection is critical |
| Priority | Both | Supports important tasks | Starvation |
| MLFQ | Yes | Adaptive, efficient | Complex implementation |

---

# 30-Second Interview Answer

> **CPU scheduling** is the operating system's process of deciding which runnable process or thread should execute next on the CPU. The scheduler aims to maximize CPU utilization, improve responsiveness, minimize waiting and turnaround time, and ensure fairness. Common scheduling algorithms include **First-Come, First-Served (FCFS), Shortest Job First (SJF), Round Robin, Priority Scheduling, and Multilevel Feedback Queue (MLFQ)**. Modern operating systems typically use preemptive, priority-based schedulers inspired by MLFQ. After the scheduler selects the next task, the operating system performs a **context switch** to transfer CPU execution to it.


***


# Virtual Memory

> **Note:** The Google Interview Prep Guide states that candidates should understand **virtual memory** as part of operating systems, but it does **not** explain how it works. The material below is additional interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is Virtual Memory?

**Virtual Memory** is a memory management technique that gives **each process the illusion of having its own large, contiguous block of memory**, regardless of the amount of physical RAM installed.

The operating system and the **Memory Management Unit (MMU)** work together to translate **virtual addresses** into **physical addresses**.

This provides:

- Process isolation
- Memory protection
- Efficient RAM usage
- Ability to run programs larger than physical memory

---

# Why Do We Need Virtual Memory?

Suppose your computer has:

```text
RAM = 8 GB
```

But you open:

- Chrome (3 GB)
- VS Code (2 GB)
- Docker (6 GB)
- Spotify (500 MB)

Total memory requested:

```text
11.5 GB
```

Without virtual memory:

```text
Out of Memory
```

With virtual memory:

- Frequently used pages remain in RAM.
- Less frequently used pages are moved to disk (swap/page file).

Applications continue running, although performance may decrease.

---

# Virtual Address vs Physical Address

Every process uses **virtual addresses**.

Example:

```text
Virtual Address

0x7FFE12345678
```

The CPU cannot directly access RAM using this address.

Instead:

```text
Virtual Address

↓

MMU

↓

Physical Address

↓

RAM
```

---

# Address Translation

The CPU generates:

```text
Virtual Address
```

The MMU performs:

```text
Virtual Address

↓

Page Table

↓

Physical Address
```

Only after translation can memory be accessed.

---

# Memory Layout

Each process sees its own virtual address space.

```text
+-------------------------+
| Code Segment            |
+-------------------------+
| Global Variables        |
+-------------------------+
| Heap                    |
|                         |
|                         |
+-------------------------+
|                         |
|                         |
| Stack                   |
+-------------------------+
```

Even if two processes both use address:

```text
0x1000
```

they map to **different physical memory locations**.

---

# Process Isolation

Suppose:

```text
Process A

Address

0x1000
```

and

```text
Process B

Address

0x1000
```

These are different virtual addresses belonging to different address spaces.

```text
Process A

0x1000

↓

RAM Address 5000

--------------------

Process B

0x1000

↓

RAM Address 9000
```

Neither process can directly access the other's memory.

---

# Paging

Virtual memory is divided into fixed-size blocks called **pages**.

Physical memory is divided into **frames** of the same size.

Example:

```text
Virtual Memory

Page 0

Page 1

Page 2

Page 3
```

```text
RAM

Frame 10

Frame 5

Frame 17

Frame 2
```

Pages may be stored in **any** physical frame.

---

# Typical Page Size

Common page sizes:

- 4 KB (most common)
- 2 MB (Huge Pages)
- 1 GB (Huge Pages)

The operating system chooses the page size.

---

# Page Table

Each process has a **page table**.

Example:

| Virtual Page | Physical Frame |
|--------------|----------------|
| 0 | 5 |
| 1 | 12 |
| 2 | 8 |
| 3 | Not Present |

When accessing:

```text
Virtual Page 2
```

The MMU finds:

```text
Frame 8
```

---

# Memory Management Unit (MMU)

The **MMU** is hardware inside the CPU.

Responsibilities:

- Address translation.
- Memory protection.
- Permission checking.
- Detecting invalid accesses.

Every memory access goes through the MMU.

---

# Translation Lookaside Buffer (TLB)

Looking up the page table for every memory access would be slow.

The CPU therefore keeps a cache called the **Translation Lookaside Buffer (TLB)**.

```text
Virtual Address

↓

TLB

↓

Found?

↓

Yes → Physical Address

↓

No

↓

Page Table Lookup

↓

Update TLB
```

The TLB significantly speeds up address translation.

---

# Page Fault

A **page fault** occurs when a requested page is **not currently in physical memory**.

Example:

```text
Virtual Page

↓

Not in RAM

↓

Page Fault

↓

Operating System Loads Page

↓

Continue Execution
```

---

# Types of Page Faults

## Minor Page Fault

The page already exists in memory but is not mapped into the current process.

Fast.

---

## Major Page Fault

The page must be loaded from disk.

```text
Disk

↓

RAM

↓

Continue
```

Much slower.

---

# Swapping

If RAM becomes full:

```text
RAM

↓

Least Recently Used Page

↓

Swap File (Disk)
```

Later:

```text
Swap File

↓

RAM
```

This allows more memory than physically available.

---

# Thrashing

If too many page faults occur:

```text
RAM Full

↓

Constant Swapping

↓

CPU Waits for Disk

↓

Poor Performance
```

This is called **thrashing**.

Symptoms:

- High disk activity.
- Very slow system.
- Low CPU utilization.

---

# Advantages of Virtual Memory

- Process isolation.
- Memory protection.
- Efficient RAM usage.
- Programs can use more memory than available RAM.
- Simplifies memory allocation.
- Supports shared libraries.

---

# Disadvantages

- Address translation overhead.
- Page table memory overhead.
- Page faults are expensive.
- Thrashing severely impacts performance.

---

# Virtual Memory vs Physical Memory

| Virtual Memory | Physical Memory |
|----------------|-----------------|
| Logical addresses | Actual RAM |
| Per-process address space | Shared hardware resource |
| Managed by OS + MMU | Installed memory chips |
| May exceed RAM size | Limited by hardware |

---

# Virtual Memory vs Cache

| Virtual Memory | CPU Cache |
|----------------|-----------|
| Managed by OS + MMU | Managed by CPU hardware |
| Uses disk if needed | Uses SRAM |
| Page size (KB/MB) | Cache lines (typically 64 bytes) |
| Much slower | Extremely fast |

---

# Virtual Memory vs Swap

| Virtual Memory | Swap |
|----------------|------|
| Entire memory abstraction | Disk space used by virtual memory |
| Includes RAM and disk | Disk only |
| Always exists | Used when RAM is insufficient |

---

# Common Interview Questions

## What is virtual memory?

Virtual memory is a memory management technique that provides each process with its own virtual address space while allowing the operating system to map virtual addresses to physical RAM.

---

## Why do we need virtual memory?

It provides:

- Process isolation.
- Memory protection.
- Efficient RAM utilization.
- Ability to run applications larger than physical memory.

---

## What is the difference between virtual and physical memory?

Virtual memory consists of logical addresses visible to a process.

Physical memory is the actual RAM installed in the computer.

---

## What is a page?

A page is a fixed-size block of virtual memory.

It maps to a physical frame in RAM.

---

## What is a page fault?

A page fault occurs when a process accesses a page that is not currently available in RAM.

The operating system loads the page and resumes execution.

---

## What is the TLB?

The **Translation Lookaside Buffer (TLB)** is a small hardware cache that stores recent virtual-to-physical address translations, avoiding repeated page table lookups.

---

## Why is virtual memory slower than RAM?

Accessing a page already in RAM is fast.

However, if a page fault requires reading from disk, access becomes thousands to millions of times slower than a normal memory access.

---

## What is thrashing?

Thrashing occurs when the operating system spends most of its time swapping pages between RAM and disk instead of executing application code.

---

# Interview Summary

| Component | Purpose |
|-----------|---------|
| Virtual Address | Address generated by the CPU |
| Physical Address | Actual RAM location |
| MMU | Translates virtual → physical addresses |
| Page Table | Stores page mappings |
| TLB | Caches recent translations |
| Page | Fixed-size virtual memory block |
| Frame | Fixed-size physical memory block |
| Page Fault | Requested page not currently in RAM |
| Swap | Disk space used when RAM is full |
| Thrashing | Excessive paging causing severe performance degradation |

---

# 30-Second Interview Answer

> **Virtual memory** is a memory management technique that gives each process its own isolated virtual address space, independent of the underlying physical RAM. The CPU generates virtual addresses, and the **Memory Management Unit (MMU)** translates them into physical addresses using **page tables**, with recent translations cached in the **Translation Lookaside Buffer (TLB)** for performance. Virtual memory provides process isolation, memory protection, and allows programs to use more memory than is physically installed by paging data between RAM and disk. However, excessive paging can lead to **thrashing**, where the system spends more time moving pages than executing useful work.


***


# Paging

> **Note:** The Google Interview Prep Guide states that candidates should understand **paging** as part of operating systems and memory management, but it does **not** explain the concept. The material below is additional interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is Paging?

**Paging** is a memory management technique that divides:

- **Virtual memory** into fixed-size blocks called **pages**.
- **Physical memory (RAM)** into fixed-size blocks called **frames**.

The operating system maps **virtual pages** to **physical frames**, allowing processes to use memory without requiring contiguous physical RAM.

---

# Why Do We Need Paging?

Without paging, programs require one large contiguous block of physical memory.

Example:

```text
RAM

+----------------------------+
| Free | Used | Free | Used |
+----------------------------+
```

Suppose a process needs:

```text
8 KB
```

Even if there is **8 KB total free memory**, it may be split into multiple smaller blocks.

Without paging:

```text
Allocation fails.
```

With paging:

```text
Page 1 → Frame 3

Page 2 → Frame 7
```

The pages can reside anywhere in RAM.

---

# Pages and Frames

A **page** is a fixed-size block of **virtual memory**.

A **frame** is a fixed-size block of **physical memory (RAM)**.

They always have the same size.

Example:

```text
Page Size = 4 KB

Frame Size = 4 KB
```

---

# Example

Suppose a process requires:

```text
16 KB
```

Using a page size of:

```text
4 KB
```

The process is divided into:

```text
Page 0

Page 1

Page 2

Page 3
```

Physical RAM might contain:

```text
Frame 10

Frame 3

Frame 18

Frame 7
```

Mapping:

```text
Page 0 → Frame 10

Page 1 → Frame 3

Page 2 → Frame 18

Page 3 → Frame 7
```

The pages do **not** need to be stored next to each other.

---

# Visualization

## Virtual Memory

```text
Process

+---------+
| Page 0  |
+---------+
| Page 1  |
+---------+
| Page 2  |
+---------+
| Page 3  |
+---------+
```

## Physical Memory (RAM)

```text
RAM

Frame 0

Frame 1

Frame 2

Frame 3

Frame 4

Frame 5

Frame 6

Frame 7

...
```

The operating system places each page into any available frame.

---

# Page Table

Every process has a **page table**.

The page table stores the mapping between virtual pages and physical frames.

Example:

| Virtual Page | Physical Frame |
|--------------|----------------|
| 0 | 10 |
| 1 | 3 |
| 2 | 18 |
| 3 | 7 |

---

# Address Translation

Suppose the CPU accesses:

```text
Virtual Address

0x12345
```

The translation process:

```text
Virtual Address

↓

Extract Page Number

↓

Page Table

↓

Physical Frame

↓

Combine With Offset

↓

Physical Address

↓

RAM
```

The CPU never directly accesses physical addresses.

---

# Page Offset

A virtual address consists of:

```text
+----------------+----------------+
| Page Number    | Offset         |
+----------------+----------------+
```

The **page number** identifies which page to use.

The **offset** identifies the exact byte inside that page.

Example:

```text
Page Size = 4096 bytes

Address =

Page 5

Offset 123
```

After translation:

```text
Frame 18

Offset 123
```

The offset remains unchanged.

---

# Translation Lookaside Buffer (TLB)

Searching the page table for every memory access would be slow.

The CPU maintains a **Translation Lookaside Buffer (TLB)**.

```text
Virtual Address

↓

TLB

↓

Hit?

↓

Yes

↓

Physical Address

↓

RAM
```

If not found:

```text
TLB Miss

↓

Page Table Lookup

↓

Update TLB

↓

Continue
```

The TLB greatly improves memory access performance.

---

# Page Fault

A **page fault** occurs when a required page is **not currently loaded into RAM**.

Example:

```text
CPU Accesses Page 5

↓

Page Not Present

↓

Page Fault

↓

Operating System Loads Page

↓

Resume Execution
```

---

# Demand Paging

With **demand paging**, pages are loaded **only when they are actually needed**.

Example:

A program contains:

```text
100 Pages
```

Initially, only:

```text
Pages 0–5
```

are loaded.

The remaining pages are loaded only if accessed.

Advantages:

- Lower memory usage.
- Faster program startup.

---

# Page Replacement

If RAM is full:

```text
Need New Page

↓

Choose Existing Page

↓

Remove It

↓

Load New Page
```

This is called **page replacement**.

---

# Common Page Replacement Algorithms

## FIFO (First In, First Out)

Remove the oldest page.

```text
Oldest Page

↓

Removed
```

Simple but not always efficient.

---

## LRU (Least Recently Used)

Remove the page that has not been used for the longest time.

```text
Recently Used ✔

Old Page

↓

Remove
```

Widely used because it approximates typical program behavior.

---

## Optimal (OPT)

Remove the page that will not be used for the longest time in the future.

Produces the fewest page faults but requires future knowledge, making it impractical for real systems.

Used mainly for theoretical comparisons.

---

# Internal Fragmentation

Paging may waste space within the last page.

Example:

```text
Page Size = 4 KB

Program Needs = 10 KB
```

Allocated:

```text
3 Pages

=

12 KB
```

Unused:

```text
2 KB
```

This unused space inside allocated pages is called **internal fragmentation**.

---

# External Fragmentation

Paging largely eliminates **external fragmentation**.

Example:

```text
RAM

Frame 2

Frame 7

Frame 12
```

Pages can be placed into any free frame, so contiguous physical memory is unnecessary.

---

# Advantages of Paging

- Eliminates external fragmentation.
- Simplifies memory allocation.
- Enables virtual memory.
- Provides process isolation.
- Supports demand paging.
- Allows efficient sharing of libraries.

---

# Disadvantages

- Internal fragmentation.
- Page table memory overhead.
- Address translation overhead.
- Page faults are expensive.
- Large page tables consume memory.

---

# Paging vs Segmentation

| Paging | Segmentation |
|---------|--------------|
| Fixed-size blocks | Variable-size blocks |
| Pages | Segments |
| Eliminates external fragmentation | May suffer external fragmentation |
| Invisible to programmer | Often visible to programmer |
| Simpler memory allocation | Matches logical program structure |

Modern operating systems primarily use **paging**, though some architectures combine paging and segmentation.

---

# Paging vs Swapping

| Paging | Swapping |
|---------|-----------|
| Moves individual pages | Moves entire processes (or large memory regions, depending on the OS) |
| Fine-grained | Coarser-grained |
| More efficient | More expensive |

Modern operating systems primarily page individual memory pages rather than swapping entire processes.

---

# Common Interview Questions

## What is paging?

Paging is a memory management technique that divides virtual memory into fixed-size pages and physical memory into equally sized frames, allowing pages to be mapped to any available frame.

---

## Why is paging used?

Paging:

- Eliminates external fragmentation.
- Simplifies memory management.
- Enables virtual memory.
- Provides process isolation.

---

## What is the difference between a page and a frame?

- **Page:** Fixed-size block of virtual memory.
- **Frame:** Fixed-size block of physical RAM.

---

## What is a page table?

A page table stores the mapping from virtual pages to physical frames for a process.

---

## What is a page fault?

A page fault occurs when the requested page is not present in RAM. The operating system loads the page from secondary storage (if necessary), updates the page table, and resumes execution.

---

## What is demand paging?

Demand paging loads pages into memory only when they are first accessed, reducing memory usage and startup time.

---

## Why are pages and frames the same size?

Using the same size allows any virtual page to fit directly into any physical frame without additional translation or splitting.

---

## What type of fragmentation does paging eliminate?

Paging eliminates **external fragmentation** but may introduce **internal fragmentation** within the final page of an allocation.

---

# Interview Summary

| Component | Purpose |
|-----------|---------|
| Page | Fixed-size block of virtual memory |
| Frame | Fixed-size block of physical memory |
| Page Table | Maps pages to frames |
| MMU | Translates virtual addresses to physical addresses |
| TLB | Caches recent page translations |
| Page Fault | Requested page not currently in RAM |
| Demand Paging | Load pages only when needed |
| Page Replacement | Select page to remove when RAM is full |
| Internal Fragmentation | Unused space within allocated pages |
| External Fragmentation | Eliminated by paging |

---

# 30-Second Interview Answer

> **Paging** is a memory management technique that divides **virtual memory into fixed-size pages** and **physical memory into equally sized frames**. Each process has a **page table** that maps its virtual pages to physical frames, and the **Memory Management Unit (MMU)** performs address translation, often accelerated by the **Translation Lookaside Buffer (TLB)**. Paging eliminates **external fragmentation**, enables **virtual memory**, and allows processes to use non-contiguous physical memory. When a requested page is not in RAM, a **page fault** occurs, and the operating system loads the page into memory, potentially replacing another page if RAM is full.


***



# Memory Allocation

> **Note:** The Google Interview Prep Guide does **not explicitly mention memory allocation**. It only states that candidates should understand **memory cache, operating systems, processes, threads, and modern concurrency**. The material below is additional operating systems and C/C++ interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is Memory Allocation?

**Memory allocation** is the process of reserving memory for a program's variables, objects, and data structures.

Memory can be allocated:

- Automatically by the compiler/runtime.
- Dynamically by the programmer.
- By the operating system.

---

# Why Do We Need Memory Allocation?

Consider:

```cpp
int x = 10;
```

Memory is allocated automatically.

But suppose we need an array whose size is only known at runtime:

```cpp
int n;

std::cin >> n;
```

Now we cannot write:

```cpp
int arr[n];   // Not valid in standard C++
```

Instead:

```cpp
int* arr = new int[n];
```

Memory must be allocated dynamically.

---

# Types of Memory Allocation

There are two primary types.

## 1. Static Memory Allocation

Memory size is known at compile time.

Example:

```cpp
int x;
double y;
char buffer[256];
```

Characteristics:

- Allocated before program execution.
- Fast.
- Fixed size.
- Automatically released.

---

## 2. Dynamic Memory Allocation

Memory is allocated while the program is running.

Example:

```cpp
int* arr = new int[n];
```

Characteristics:

- Size determined at runtime.
- More flexible.
- Requires explicit deallocation (or automatic garbage collection in managed languages).

---

# Memory Layout of a Process

A typical process memory layout:

```text
+----------------------+
| Code (Text Segment)  |
+----------------------+
| Global Variables     |
| Static Variables     |
+----------------------+
| Heap                 |
|        ↑             |
|        |             |
|        |             |
+----------------------+
|        |             |
|        ↓             |
| Stack                |
+----------------------+
```

The **heap grows upward**.

The **stack grows downward** (on most architectures).

---

# Stack Allocation

Variables declared inside functions are usually stored on the **stack**.

Example:

```cpp
void foo()
{
    int x = 5;
}
```

When the function returns:

```text
Stack Frame

↓

Destroyed Automatically
```

Advantages:

- Extremely fast.
- Automatic cleanup.
- No memory leaks.

Disadvantages:

- Limited size.
- Lifetime tied to function scope.

---

# Heap Allocation

Objects created with `new` (or `malloc`) are allocated on the **heap**.

Example:

```cpp
int* ptr = new int(42);
```

The memory remains allocated until:

```cpp
delete ptr;
```

Advantages:

- Flexible size.
- Long lifetime.
- Can outlive function calls.

Disadvantages:

- Slower allocation.
- Possible memory leaks.
- Fragmentation.

---

# Stack vs Heap

| Stack | Heap |
|--------|------|
| Automatic allocation | Manual or runtime allocation |
| Very fast | Slower |
| Small size | Much larger |
| Automatic cleanup | Requires deallocation |
| LIFO | No ordering requirement |

---

# C++ Memory Allocation

## Stack

```cpp
int x = 5;
```

---

## Heap

```cpp
int* p = new int(5);

delete p;
```

---

## Arrays

```cpp
int* arr = new int[100];

delete[] arr;
```

Always use:

```cpp
delete[]
```

for arrays.

---

# C Memory Allocation

C uses:

```c
malloc()
calloc()
realloc()
free()
```

Example:

```c
int* arr = malloc(100 * sizeof(int));

free(arr);
```

---

# Modern C++

Avoid raw pointers when possible.

Prefer:

```cpp
auto ptr = std::make_unique<int>(5);
```

or

```cpp
auto ptr = std::make_shared<MyClass>();
```

Advantages:

- Automatic cleanup.
- Exception safety.
- No manual `delete`.

---

# Memory Fragmentation

Over time:

```text
Used

Free

Used

Free

Used

Free
```

Memory becomes fragmented.

---

## Internal Fragmentation

Allocated memory is larger than required.

Example:

```text
Need

30 Bytes

↓

Allocator Gives

32 Bytes
```

Unused memory:

```text
2 Bytes
```

---

## External Fragmentation

Free memory exists but is split into many small blocks.

Example:

```text
Free

2 KB

Used

Free

2 KB

Used

Free

2 KB
```

Need:

```text
6 KB
```

Allocation fails even though:

```text
2 + 2 + 2 = 6 KB
```

---

# Memory Leak

A **memory leak** occurs when allocated memory is never released.

Example:

```cpp
int* p = new int(5);

// Forgot delete
```

After many allocations:

```text
Memory Usage

↑

↑

↑

Eventually

Out of Memory
```

---

# Dangling Pointer

A pointer referencing freed memory.

Example:

```cpp
int* p = new int(5);

delete p;

*p = 10;
```

Undefined behavior.

---

# Double Free

Deleting the same memory twice.

```cpp
delete p;

delete p;
```

Undefined behavior.

---

# Smart Pointers (Modern C++)

## unique_ptr

Single owner.

```cpp
auto p = std::make_unique<MyClass>();
```

Automatically destroyed.

---

## shared_ptr

Multiple owners.

```cpp
auto p = std::make_shared<MyClass>();
```

Uses **reference counting**.

---

## weak_ptr

Does not own the object.

Used to break circular references.

---

# Memory Pool

Instead of repeatedly allocating memory:

```text
new

delete

new

delete
```

Create one large block.

```text
Pool

↓

Allocate Objects

↓

Reuse Memory
```

Benefits:

- Faster.
- Less fragmentation.
- Common in game engines and databases.

---

# Allocation Strategies

Common strategies:

- First Fit
- Best Fit
- Worst Fit
- Buddy Allocator
- Slab Allocator

Linux commonly uses **Buddy** and **Slab** allocators in the kernel.

---

# RAII (Resource Acquisition Is Initialization)

Modern C++ uses RAII.

Instead of:

```cpp
new

delete
```

Use objects whose destructors automatically release resources.

Example:

```cpp
std::vector<int> v(100);
```

No manual cleanup required.

---

# Common Interview Questions

## What is memory allocation?

Memory allocation is the process of reserving memory for variables and objects during program execution.

---

## What is the difference between stack and heap allocation?

Stack allocation is automatic, fast, and tied to function scope.

Heap allocation is dynamic, flexible, and must be explicitly managed (or automatically managed using smart pointers or garbage collection).

---

## Why is heap allocation slower?

Because the allocator must:

- Find a free memory block.
- Manage fragmentation.
- Maintain allocator metadata.
- Synchronize in multithreaded environments.

---

## What is a memory leak?

A memory leak occurs when allocated memory is no longer reachable but has not been released.

---

## What is a dangling pointer?

A pointer that still references memory after it has been freed.

---

## What is fragmentation?

Fragmentation occurs when memory becomes inefficiently organized.

- **Internal:** Unused space inside allocated blocks.
- **External:** Free memory exists but is split into small pieces.

---

## Why should we prefer smart pointers in C++?

Smart pointers automatically manage object lifetimes, reducing memory leaks and making code exception-safe.

---

## Why is stack allocation faster than heap allocation?

Stack allocation simply moves the stack pointer, while heap allocation requires searching for suitable free blocks, maintaining allocator metadata, and handling fragmentation.

---

# Interview Summary

| Concept | Description |
|----------|-------------|
| Stack Allocation | Automatic, fast, scope-based |
| Heap Allocation | Dynamic, flexible, manual or managed |
| Static Allocation | Compile-time allocation |
| Dynamic Allocation | Runtime allocation |
| Memory Leak | Allocated memory never released |
| Dangling Pointer | Pointer to freed memory |
| Double Free | Releasing the same memory twice |
| Internal Fragmentation | Wasted space inside allocated blocks |
| External Fragmentation | Free memory split into many small blocks |
| RAII | Automatic resource management |
| Smart Pointer | Automatic memory management |

---

# 30-Second Interview Answer

> **Memory allocation** is the process of reserving memory for variables and objects. Memory can be allocated **statically** at compile time or **dynamically** at runtime. In C++, local variables are typically allocated on the **stack**, which is fast and automatically managed, while dynamically created objects are allocated on the **heap**, which offers greater flexibility but requires proper lifetime management. Common memory management issues include **memory leaks**, **dangling pointers**, **double frees**, and **fragmentation**. Modern C++ encourages the use of **RAII** and **smart pointers** (`std::unique_ptr`, `std::shared_ptr`) to manage memory safely and automatically.


***


# Concurrency

> **Note:** The Google Interview Prep Guide states that candidates should understand **concurrency issues** as part of operating systems and multi-core programming, but it does **not** explain concurrency in detail. The material below is additional interview preparation beyond the guide. :contentReference[oaicite:0]{index=0}

---

# What is Concurrency?

**Concurrency** is the ability of a system to **manage multiple tasks that make progress during overlapping periods of time**.

The tasks **do not necessarily execute simultaneously**.

Instead, the operating system rapidly switches between them (or schedules them across multiple CPU cores).

---

# Why Do We Need Concurrency?

Suppose a web server receives:

```text
1000 HTTP Requests
```

Without concurrency:

```text
Request 1

↓

Request 2

↓

Request 3

↓

...
```

Every request waits for the previous one.

With concurrency:

```text
Request 1

↓

Request 2

↓

Request 3

↓

Request 4

↓

...
```

Multiple requests make progress simultaneously, improving responsiveness and throughput.

---

# Concurrency vs Parallelism

These terms are often confused.

## Concurrency

Multiple tasks make progress during overlapping time periods.

Example:

```text
CPU

Task A

↓

Task B

↓

Task A

↓

Task B
```

One CPU core is sufficient.

---

## Parallelism

Multiple tasks execute **at the exact same time**.

Example:

```text
Core 1

Task A

---------------

Core 2

Task B
```

Requires multiple CPU cores (or hardware threads).

---

# Visualization

## Concurrency

```text
CPU

Task A

↓

Task B

↓

Task A

↓

Task C

↓

Task B
```

---

## Parallelism

```text
Core 1

Task A

---------------

Core 2

Task B

---------------

Core 3

Task C
```

---

# Real-World Example

Imagine a chef.

## Sequential

```text
Cook Pasta

↓

Prepare Sauce

↓

Cut Vegetables
```

---

## Concurrent

```text
Boil Water

↓

While Water Boils

↓

Cut Vegetables

↓

Prepare Sauce
```

One chef manages multiple activities.

---

## Parallel

```text
Chef 1

Cook Pasta

---------------

Chef 2

Prepare Sauce

---------------

Chef 3

Cut Vegetables
```

Multiple chefs work simultaneously.

---

# How Concurrency Works

The operating system scheduler repeatedly performs:

```text
Run Thread A

↓

Context Switch

↓

Run Thread B

↓

Context Switch

↓

Run Thread C
```

Each thread gets CPU time.

---

# Why Concurrency is Important

Concurrency improves:

- Responsiveness
- Throughput
- CPU utilization
- Scalability
- User experience

Examples:

- Web servers
- Browsers
- Databases
- Game engines
- Operating systems

---

# Concurrency Models

## Multi-Threading

Multiple threads execute within the same process.

```text
Process

├── Thread A

├── Thread B

└── Thread C
```

Advantages:

- Shared memory
- Low communication cost

Disadvantages:

- Synchronization required

---

## Multi-Processing

Multiple independent processes.

```text
Process A

Process B

Process C
```

Advantages:

- Better isolation
- Crash protection

Disadvantages:

- More expensive communication (IPC)

---

## Asynchronous Programming

Tasks voluntarily yield while waiting.

Example:

```text
Send Network Request

↓

Do Other Work

↓

Receive Response
```

Common in:

- Node.js
- Python asyncio
- JavaScript

---

## Event-Driven Programming

Execution is triggered by events.

Example:

```text
Mouse Click

↓

Callback

↓

Handle Event
```

Used in:

- GUI applications
- Browsers
- Web servers

---

# Common Concurrency Problems

## Race Condition

Two threads modify shared data simultaneously.

Example:

```cpp
counter++;
```

Both threads execute:

```text
Read

↓

Increment

↓

Write
```

Incorrect results may occur.

---

## Deadlock

Threads wait forever.

```text
Thread A

Waiting for B

↓

Thread B

Waiting for A
```

No progress.

---

## Livelock

Threads remain active but continuously interfere with each other.

```text
Retry

↓

Retry

↓

Retry
```

No useful work.

---

## Starvation

A thread never gets CPU time or resources.

Example:

```text
High Priority Tasks

↓

Always Execute

↓

Low Priority Thread Never Runs
```

---

# Synchronization

Concurrency requires synchronization to protect shared resources.

Common synchronization primitives:

- Mutex
- Semaphore
- Read-Write Lock
- Atomic Operations
- Condition Variable
- Monitor

---

# Critical Section

A **critical section** is code that accesses shared mutable data.

Example:

```cpp
balance += amount;
```

Protect it using:

```cpp
std::mutex m;

std::lock_guard<std::mutex> lock(m);

balance += amount;
```

---

# Atomic Operations

Simple operations can avoid locks.

Example:

```cpp
std::atomic<int> counter;

counter++;
```

Advantages:

- No mutex
- Faster
- Lock-free

Limitations:

- Suitable only for simple operations

---

# Thread Safety

A function is **thread-safe** if multiple threads can execute it concurrently without causing incorrect behavior.

Example:

```cpp
std::atomic<int> counter;

counter++;
```

is thread-safe.

---

# Immutable Objects

Immutable objects never change after creation.

Example:

```cpp
const std::string name = "Google";
```

Immutable data is naturally thread-safe because there are no modifications.

---

# Producer-Consumer Problem

A classic concurrency problem.

Producer:

```text
Create Item

↓

Queue
```

Consumer:

```text
Queue

↓

Process Item
```

Synchronization is required to:

- Prevent overflow.
- Prevent underflow.

Typically solved using:

- Semaphores
- Mutexes
- Condition Variables

---

# Readers-Writers Problem

Multiple readers may access data simultaneously.

Only one writer may modify it.

Solution:

```text
Read-Write Lock
```

---

# Dining Philosophers Problem

Classic synchronization problem.

```text
Fork

↓

Philosopher

↓

Fork
```

Improper locking can cause:

- Deadlock
- Starvation

---

# Thread Pool

Instead of creating a thread per task:

```text
Task

↓

Thread Pool

↓

Worker Thread
```

Benefits:

- Reduced thread creation overhead.
- Better scalability.
- Improved resource management.

---

# Advantages of Concurrency

- Better CPU utilization.
- Improved responsiveness.
- Increased throughput.
- Better scalability.
- Efficient handling of I/O-bound workloads.

---

# Disadvantages

- Race conditions.
- Deadlocks.
- Livelocks.
- Starvation.
- Complex debugging.
- Synchronization overhead.

---

# Common Interview Questions

## What is concurrency?

Concurrency is the ability to manage multiple tasks that make progress during overlapping periods of time.

---

## What is the difference between concurrency and parallelism?

Concurrency means tasks overlap in execution.

Parallelism means tasks execute simultaneously on different CPU cores.

---

## Why is concurrency difficult?

Because multiple threads may access shared resources simultaneously, leading to race conditions, deadlocks, and other synchronization issues.

---

## What is thread safety?

Thread safety means code behaves correctly even when executed concurrently by multiple threads.

---

## What is a critical section?

A critical section is code that accesses shared mutable state and therefore requires synchronization.

---

## How do you make code thread-safe?

Common techniques:

- Mutexes
- Semaphores
- Read-write locks
- Atomics
- Immutable data
- Thread-local storage
- Message passing

---

## What is thread-local storage?

Thread-local storage (TLS) provides each thread with its own independent instance of a variable.

Example (C++):

```cpp
thread_local int counter = 0;
```

No synchronization is required because each thread has its own copy.

---

## When should you use atomics instead of mutexes?

Use **atomics** for simple operations on individual variables.

Use **mutexes** when protecting multiple variables or complex critical sections.

---

# Interview Summary

| Concept | Description |
|----------|-------------|
| Concurrency | Multiple tasks make progress during overlapping time periods |
| Parallelism | Multiple tasks execute simultaneously |
| Thread Safety | Safe concurrent execution |
| Critical Section | Code accessing shared mutable data |
| Race Condition | Unsynchronized concurrent access |
| Deadlock | Threads wait forever |
| Livelock | Threads remain active but make no progress |
| Starvation | Thread never gets resources |
| Synchronization | Coordinating concurrent access |
| Atomic Operation | Lock-free operation on a single variable |
| Thread Pool | Reusable worker threads for executing tasks |

---

# 30-Second Interview Answer

> **Concurrency** is the ability of a system to manage multiple tasks that make progress during overlapping periods of time. Unlike **parallelism**, concurrency does not require multiple CPU cores—it can be achieved through scheduling and context switching on a single core. Concurrency improves responsiveness and throughput but introduces challenges such as **race conditions**, **deadlocks**, **livelocks**, and **starvation** when threads access shared resources. These issues are addressed using synchronization primitives like **mutexes**, **semaphores**, **read-write locks**, **condition variables**, and **atomic operations**. Writing correct concurrent code requires minimizing shared mutable state, protecting critical sections, and choosing the appropriate synchronization mechanism.
















 

 
 
