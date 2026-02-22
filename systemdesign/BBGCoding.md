# Coding Patterns.

## 01 Two Pointers.

**1. Two Pointers Pattern?**
* A technique using two variables to track positions in a data structure (like an array or linked list) to solve problems efficiently.

**2. Two Pointers: Core Idea?**
* Instead of nested loops `(O(n^2))`, use two pointers and predictable dynamics to achieve `O(n)` time complexity.

**3. Pointer (in the context of Two Pointers)?**
* A variable that holds an index or position within a data structure (array, linked list, string, etc.).

**4. Why use Two Pointers?**
* Reduces time complexity from `O(n^2)` (nested loops) to `O(n)` by leveraging predictable relationships in the data.

**5. Inward Traversal?**
* Pointers start at opposite ends of the data structure and move towards each other. 
* Good for comparing elements from both ends.

**6. Inward Traversal: When to use?**
* When you need to compare or combine elements from opposite ends of a sorted array or string 
(e.g., finding a pair that sums to a target).

**7. Unidirectional Traversal.**
* Both pointers start at the same end and move in the same direction. 
* One pointer might find info, the other tracks it.

**8. Unidirectional Traversal: When to use?**
* When you need to maintain a "window" or sub-region within the data structure while iterating (e.g., shifting elements).

**9. Staged Traversal.**
* One pointer searches for a specific condition, and then the second pointer is activated to find related information.

**10. Staged Traversal: When to use?**
* When you need to find an element that satisfies a condition and then find additional information related to that element.

**11. Predictable Dynamics.**
* A property of the data structure that allows you to make logical deductions as you move a pointer (e.g., sorted array, palindrome).

**12. Sorted Array: Predictable Dynamic?**
* YES. Moving a pointer to the right in an ascending sorted array guarantees you're moving to a value greater than or 
equal to the current one.

**13. Palindrome: Predictable Dynamic?**
* YES. Symmetrical structure allows you to compare characters from both ends moving inward.

**14. Two Pointers: When to consider?**
* Linear data structure (array, linked list, string). 
* Predictable dynamics (sorted data, palindromes). 
* Problem asks for a pair of values.

**15. When NOT to use Two Pointers?**
* When the data lacks predictable dynamics, or when you need to compare every element with every other element 
(nested loops might be necessary).

**16. Pair Sum in a Sorted Array.**
* Use inward traversal. 
* If the sum is too small, move the left pointer right. 
* If the sum is too big, move the right pointer left.

**17. Shifting Zeroes to the End.**
* Use unidirectional traversal. 
* One pointer iterates, the other tracks the position to place the next non-zero element.

**18. Two Pointers: Real-world example?**
* Garbage collection (memory compaction). 
* 'Scan' pointer finds live objects, 'free' pointer tracks the next available space.

**19. Two Pointers: Time Complexity.**
* Usually `O(n)`, where n is the size of the input data structure.

**20. Two Pointers: Space Complexity.**
* Usually `O(1)`, as it typically involves only a few extra variables (the pointers themselves).

**21. Invariant (in Two Pointers).**
* A condition that remains true throughout the execution of the two-pointer algorithm. 
* Maintaining the invariant is crucial for correctness.

**22. Invariant: Sorted Array Search.**
* In a sorted array search, the invariant might be that the target value, if present, must lie between the left and right pointers.

**23. Two Pointers: Handling Duplicates.**
* If duplicates exist in a sorted array, you might need extra logic to skip over them to avoid redundant comparisons or results.

**24. Two Pointers: Edge Cases.**
* Consider empty arrays, single-element arrays, and cases where the target value is not found.

**25. Two Pointers: Common Mistakes.**
* Watch out for off-by-one errors when initializing pointers or updating their positions (e.g., i < n vs. i <= n).

**26. Triplet Sum (Sorted Array).**
* Fix one number, then use two pointers (inward traversal) to find the other two numbers that sum to the target.

**27. Largest Container Problem.**
* Use inward traversal. 
* Move the pointer with the smaller height, as this is the limiting factor for the container's area.

**28. Is Palindrome Valid.**
* Use inward traversal, skipping non-alphanumeric characters.

**29. Fast and Slow Pointers.**
* A specialized two-pointer technique (covered in another chapter) where pointers move at different speeds. 
* Useful for detecting cycles in linked lists.

**30. Sliding Window.**
* Another pattern related to Two Pointers (covered in another chapter) that uses two pointers to define a "window" of elements.

***

## 02 Two Pointers.

**1. What is a Hash Map (or Hash Table/Dictionary)?**
* A data structure that stores data in key-value pairs, allowing for efficient retrieval of values based on their associated keys. 
* Keys are unique.

**2. What is the average time complexity for common Hash Map operations (Insert, Access, Delete)?**
* `O(1)` - Constant time. 
* This assumes a good hash function minimizes collisions.

**3. What is the worst-case time complexity for common Hash Map operations, and why does it occur?**
* `O(n)` - Linear time. 
* Occurs when there are many collisions due to a poor hash function, effectively turning the lookup into a linear search.

**4. List three key properties of Hash Maps.**
* Stores data as key-value pairs.
* Keys are unique (no duplicates).
* Unordered (no specific order of elements).

**5. What is a Hash Set?**
* A data structure that stores only keys (no values). 
* Used for checking the presence or absence of an item quickly.

**6. Give 2-3 common use cases for Hash Sets.**
* Storing unique elements.
* Checking for duplicates.
* Marking elements as "seen" or "visited".

**7. What keywords in a problem description might suggest using a Hash Map or Hash Set?**
* "Frequency", "Unique", "Map", "Dictionary", "Fast Lookup".

**8. What is the main difference between a Hash Map and a Hash Set?**
* A Hash Map stores key-value pairs, while a Hash Set stores only keys.

**9. What is a hash function's role in a Hash Map?**
* It takes a key as input and generates an index (a hash code) that determines where the key-value pair is stored in the 
underlying array.

**10. What are the characteristics of a "good" hash function?**
* Distributes keys evenly across the hash table.
* Minimizes collisions.
* Is efficient to compute.

**11. What is a collision in a Hash Map?**
* A collision occurs when two different keys produce the same hash code (index) in the hash table.

**12. Explain the collision resolution technique called "Chaining".**
* Each index in the hash table points to a linked list (or other data structure) of key-value pairs that have the same hash code.

**13. Explain the collision resolution technique called "Open Addressing".**
* When a collision occurs, the algorithm probes (searches) for an empty slot in the hash table to store the key-value pair. 
* Examples include linear probing, quadratic probing, and double hashing.

**14. What is the load factor of a Hash Map?**
* The ratio of the number of entries in the hash map to the size of the underlying array (capacity). 
* (load factor = number of entries / capacity).

**15. What is rehashing, and why is it necessary?**
* Rehashing is the process of increasing the capacity of the hash table and re-inserting all existing key-value pairs. 
* It's necessary when the load factor becomes too high, which degrades performance due to increased collisions.

**16. Describe how a Hash Map can be used for frequency counting.**
* The keys can represent the elements you want to count, and the values can store the frequency of each element.

**17. How can Hash Maps be used in caching?**
* Hash Maps provide fast lookups for cached data. 
* The key is the identifier for the data, and the value is the cached data itself.

**18. Are Hash Maps ordered or unordered? What does this mean for iteration?**
* Hash Maps are generally unordered. 
* The order in which you iterate through a Hash Map is not guaranteed to be the order in which elements were inserted.

**19. How does a Hash Set guarantee uniqueness?**
* It only allows one instance of each key to be stored. 
* If you try to insert a duplicate key, it will either be ignored or overwrite the existing key (depending on the implementation).

**20. What is "membership testing" in the context of a Hash Set, and why is it efficient?**
* Checking if a particular key is present in the Hash Set. 
* It's efficient because, on average, it takes `O(1)` time.

**21. How can a Hash Set be used to remove duplicates from a list?**
* Insert all elements from the list into the Hash Set. 
* Since the Hash Set only stores unique elements, any duplicates will be automatically removed.

**22. Describe a scenario where a Hash Set is useful for checking the existence of an element.**
* Imagine you need to determine if a specific user ID exists in a large database. 
* Storing the valid user IDs in a Hash Set allows for quick `O(1)` existence checks.

**23. When is a Hash Set NOT the appropriate data structure to use?**
* When you need to maintain the order of elements, or when you need to store associated values with each element 
* (in which case a Hash Map would be more suitable).

**24. What is the average time complexity for common Hash Set operations (Insert, Delete, Contains)?**
* `O(1)` - Constant time, on average.

**25. What is the worst-case time complexity for common Hash Set operations, and why?**
* `O(n)` - Linear time, due to hash collisions.

**26. How does a web browser use a Hash Map for caching?**
* The URL of a web resource (e.g., image, HTML file) can be the key, and the cached content can be the value.

**27. How are Hash Maps related to database indexing?**
* Some database systems use hash indexes to quickly locate specific rows in a table based on the value of a particular column.

**28. What are some common names for Hash Maps and Hash Sets in different programming languages?**
* Python: Dictionary (dict), Set (set).
* Java: HashMap, HashSet.
* C++: unordered_map, unordered_set.
* JavaScript: Object, Set.

**29. What is the space complexity of a Hash Map or Hash Set?**
* `O(n)`, where n is the number of key-value pairs (Hash Map) or keys (Hash Set) stored in the data structure.

**30. What are the key factors to consider when deciding whether to use a Hash Map, a Hash Set, or another data structure?**
* Do you need to store associated values with each key? (If yes, use a Hash Map)
* Do you need to maintain the order of elements? (If yes, consider a different data structure like a list or ordered dictionary)
* How important is fast lookup/insertion/deletion? (Hash Maps and Sets excel at these)
* What are the memory constraints?

**31. What does it mean for a key to be "hashable" in the context of a Hash Map or Hash Set?**
* A hashable object is one that has a `__hash__()` method (in Python) or equivalent in other languages, which returns an 
integer value that can be used as a hash code. 
* Hashable objects must also be immutable (their value cannot change after creation).

**32. Why must keys in a Hash Map or Hash Set be immutable?**
* If a key's value changes after it has been inserted into the Hash Map/Set, its hash code will also change. 
* This would make it impossible to find the key again, violating the fundamental principles of the data structure.

**33. How can you use custom objects (e.g., objects of a class you defined) as keys in a Hash Map or Hash Set?**
* You need to implement the `__hash__()` and `__eq__()` methods (or their equivalents in other languages) for your custom class. 
* `__hash__()` should return a hash code based on the object's attributes, and `__eq__()` should define how to compare 
two objects for equality. 
* Both methods must ensure that if two objects are equal, they have the same hash code.

**34. Are there any security considerations when using Hash Maps, particularly with regards to hash collisions?**
* Yes. If an attacker can intentionally cause a large number of collisions (e.g., by providing keys that all hash to the same value), 
they can degrade the performance of the Hash Map to `O(n)`, potentially leading to a denial-of-service attack. 
* This is known as a Hash Collision Denial of Service (HashDoS) attack.

**35. What are some strategies to mitigate HashDoS attacks?**
* Using a cryptographically secure hash function: These functions are designed to be more resistant to collision attacks.
* Randomizing the hash function: Using a different hash function for each instance of the Hash Map can make it more difficult 
for an attacker to predict collisions.
* Limiting the number of elements in the Hash Map: This can prevent the Hash Map from growing too large and consuming 
excessive resources.
* Using collision avoidance techniques: Techniques like Robin Hood hashing can help to distribute elements more evenly 
in the hash table, reducing the likelihood of collisions.

***

## 03 Two Pointers.

**1. Define a linked list.**
* A data structure of nodes where each node contains data (val) and a pointer (next) to the next node in the sequence.

**2. What are the components of a ListNode?**
* Data (val) to store information and a pointer (next) to the subsequent node.

**3. Describe a singly linked list.**
* Each node points only to the next node. 
* Traversal is unidirectional. 
* The last node's next pointer is null.

**4. What is the 'head' of a linked list?**
* The first node in the list. 
* It's the entry point for traversing the list.

**5. How do you access elements beyond the head in a singly linked list?**
* By following the next pointers from node to node, starting at the head.

**6. What is a key advantage of linked lists over arrays?**
* Dynamic sizing. 
* Linked lists can grow or shrink as needed.

**7. How do linked lists perform in terms of insertion and deletion compared to arrays?**
* Linked lists generally offer more efficient insertion and deletion because you only need to change pointers, 
not shift elements.

**8. What is a major disadvantage of linked lists compared to arrays?**
* Lack of random access. 
* You can't access an element directly by its index; you must traverse from the head.

**9. Describe a doubly linked list.**
* Each node has pointers to both the next node and the previous node (prev).

**10. What is a common feature of doubly linked list implementations?**
* Direct access to both the head and tail nodes.

**11. What is the primary advantage of a doubly linked list?**
* Bidirectional traversal. 
* You can move forward or backward through the list.

**12. How does node deletion in a doubly linked list compare to a singly linked list?**
* Generally easier because you have references to both the next and previous nodes, simplifying pointer updates.

**13. What skill is crucial for solving linked list problems?**
* Proficiency in pointer manipulation.

**14. What's a helpful technique for understanding pointer manipulation?**
* Visualize pointers as arrows and track how they change during operations.

**15. How are linked lists used in music player applications?**
* To implement playlists, especially doubly linked lists, for efficient song addition, removal, and reordering.

**16. Give examples of linked list problems that involve traversal.**
* Linked List Intersection, Palindrome Linked List.

**17. Give examples of linked list problems that involve restructuring.**
* Linked List Reversal, Remove the Kth Last Node From a Linked List, Flatten a Multi-Level Linked List.

**18. Give an example of a problem that commonly uses a doubly linked list.**
* LRU Cache.

**19. What steps are involved in inserting a new node into a singly linked list?**
* Create the new node.
* Update the next pointer of the previous node to point to the new node.
* Set the next pointer of the new node to point to the original next node.

**20. What steps are involved in deleting a node from a singly linked list?**
* Update the next pointer of the previous node to point to the node after the node being deleted.

**21. What steps are involved in inserting a new node into a doubly linked list?**
* Update the next pointer of the previous node to point to the new node.
* Update the prev pointer of the new node to point to the previous node.
* Update the next pointer of the new node to point to the original next node.
* Update the prev pointer of the original next node to point to the new node.

**22. What steps are involved in deleting a node from a doubly linked list?**
* Update the next pointer of the previous node to point to the node after the node being deleted.
* Update the prev pointer of the next node to point to the node before the node being deleted.

**23. What is the space complexity of a linked list?**
* `O(n)`, where n is the number of nodes in the list. 
* Each node stores data and a pointer.

**24. What is the time complexity to access the kth element in a singly linked list?**
* `O(k)` in the average case, `O(n)` in the worst case (if k is near the end of the list).

**25. What is the time complexity to access the kth element in a doubly linked list?**
* `O(k)` in the average case, but potentially `O(k/2)` if you start from the head or tail, whichever is closer.

**26. What is the time complexity of inserting or deleting a node at the head of a singly linked list?**
* `O(1)` - constant time.

**27. What is the time complexity of inserting or deleting a node in the middle of a singly linked list, given you already 
have a reference to the node before the insertion/deletion point?**
* `O(1)` - constant time. 
* However, finding that node before the insertion/deletion point is `O(n)` in the worst case.

**28. What is the time complexity of inserting or deleting a node at the head of a doubly linked list?**
* `O(1)` - constant time.

**29. What is the time complexity of inserting or deleting a node at the tail of a doubly linked list?**
* `O(1)` - constant time, assuming you have a pointer to the tail.

**30. What is the time complexity of inserting or deleting a node in the middle of a doubly linked list, given you already 
have a reference to the node to be deleted?**
* `O(1)` - constant time.

**31. What is a sentinel node (or dummy node) in a linked list?**
* A node added before the head or after the tail of a linked list. 
* It simplifies code by handling edge cases (empty list, insertion at the beginning) and avoids special case checks.

**32. What are some common errors to watch out for when working with linked lists?**
* Null pointer exceptions (accessing next of a null node).
* Memory leaks (not updating pointers correctly, leaving nodes unreferenced).
* Infinite loops (incorrectly updating pointers during traversal or restructuring).
* Off-by-one errors in traversal.

**33. What factors should you consider when deciding whether to use a singly or doubly linked list for a particular problem?**
* Memory Usage: Doubly linked lists use more memory due to the extra prev pointer.
* Traversal Direction: If you need to traverse in both directions, a doubly linked list is necessary.
* Insertion/Deletion Complexity: Doubly linked lists can simplify certain insertion/deletion operations, 
especially when you need to update pointers of adjacent nodes.
* Problem Constraints: The specific requirements of the problem (e.g., memory limits, performance requirements) 
will often dictate the best choice.

***

## 04 Fast And Slow Pointers.

**1. What is the core idea behind the Fast and Slow Pointers pattern?**
* Using two pointers that move at different speeds through a data structure (often a linked list) to achieve a goal that 
would be difficult or impossible with a single pointer or index.

**2. What are the typical speeds of the fast and slow pointers, and can they vary?**
* Typically, the slow pointer moves one step at a time, and the fast pointer moves two steps at a time. 
* However, the key is that the fast pointer moves more steps than the slow pointer. 
* The exact speeds can be adjusted based on the problem.

**3. Why are fast and slow pointers particularly useful with linked lists?**
* Linked lists lack direct index-based access. 
* Fast and slow pointers provide a way to analyze the structure and relationships between elements by observing their 
relative positions.

**4. How can fast and slow pointers be used to detect cycles in symlinks?**
* The slow pointer follows each symlink one step at a time, while the fast pointer moves two steps at a time. 
* If the fast pointer catches up to the slow pointer, it indicates a loop in the symlinks.

**5. What type of problem is "Linked List Loop" and how Fast and Slow Pointers pattern can be applied to it?**
* "Linked List Loop" is a cycle detection problem. 
* Fast and Slow Pointers can be used to determine if a loop exists in a linked list.

**6. What type of problem is "Happy Number" and how Fast and Slow Pointers pattern can be applied to it?**
* "Happy Number" is a sequence analysis problem. 
* Fast and Slow Pointers can be used to find if a number is a happy number, by detecting cycles in the sequence of numbers 
generated by the Happy Number algorithm.

**7. What type of problem is "Linked List Midpoint" and how Fast and Slow Pointers pattern can be applied to it?**
* "Linked List Midpoint" is a fractional point identification problem. 
* Fast and Slow Pointers can be used to find the middle node of a linked list.

**8. In a cycle detection problem, what is significant about the point where the fast and slow pointers meet?**
* The meeting point confirms the existence of a cycle. 
* The location of this meeting point can be used to find the entrance of the cycle (as you'll see in later problems).

**9. When should you consider using the Fast and Slow Pointers pattern?**
* Problems involving linked lists (especially when the length is unknown or finding the middle is important).
* Problems where you need to detect cycles or loops.
* Problems where you need to analyze a sequence of values based on some iterative process.

**10. Is the Fast and Slow Pointers pattern limited to linked lists?**
* No. While it's commonly used with linked lists, the underlying principle of using different speeds to analyze a sequence 
or data structure can be applied in other scenarios as well. 
* The "Happy Number" problem demonstrates this.

***

## 05 Sliding Windows.

**1. What is the Sliding Window technique and why is it useful?**
* A method to solve array/string problems by defining a "window" (subarray/substring) and moving it across the data. 
* It reduces time complexity from potentially `O(n^2)` to `O(n)` by avoiding nested loops.

**2. How does the sliding window technique relate to the two-pointer pattern?**
* The sliding window technique is a subset of the two-pointer pattern, using two pointers (left and right) to define the 
window's boundaries.

**3. What does it mean to "expand" the window?**
* To expand the window means to move the right pointer to include more elements in the current window.

**4. What does it mean to "shrink" the window?**
* To shrink the window means to move the left pointer to exclude elements from the current window, reducing its size.

**5. What does it mean to "slide" the window?**
* To slide the window means to move both the left and right pointers, maintaining the window's size (in a fixed window) 
or adjusting it (in a dynamic window). 
* Sliding is equivalent to expanding and shrinking at the same time.

**6. What is a Fixed Sliding Window?**
* A sliding window technique where the window size remains constant as it moves across the data structure.

**7. When is a Fixed Sliding Window most appropriate?**
* When the problem requires finding a subcomponent (subarray/substring) of a specific, predetermined length.

**8. Describe the basic structure of a fixed sliding window algorithm.**
* Initialize left = 0 and right = 0.
* Iterate while right < n.
* If right - left + 1 == fixed_window_size:
* Process the current window.
* left++ (slide the window).
* right++ (expand the window).

**9. What is a Dynamic Sliding Window?**
* A sliding window technique where the window size can increase or decrease as it moves across the data structure.

**10. When is a Dynamic Sliding Window most appropriate?**
* When the problem requires finding the longest or shortest subcomponent that satisfies a given condition.

**11. How does a dynamic sliding window typically expand and shrink when searching for the longest subcomponent?**
* Expand: If the current window satisfies the condition, expand it (move right) to potentially find a longer valid window.
* Shrink: If the current window violates the condition, shrink it (move left) until the condition is met again.

**12. Describe the basic structure of a dynamic sliding window algorithm.**
* Initialize left = 0 and right = 0.
* Iterate while right < n.
* while condition is violated:
* left++ (shrink the window).
* Process the current window.
* right++ (expand the window).

**13. What is the primary difference between Fixed and Dynamic Sliding Windows?**
* Fixed windows have a predetermined, constant size. 
* Dynamic windows adjust their size based on a condition.

**14. Give a real-world example of the sliding window technique.**
* Buffering in video streaming: A dynamic sliding window manages the amount of video data buffered, adjusting to network 
conditions for smooth playback.

**15. What is important to remember about updating the window contents?**
* The logic for updating the window (adding/removing elements, recalculating values) is highly problem-specific and 
depends on the condition being checked. 
* The templates focus on pointer movement, not the update logic itself.

**16. What type of sliding window is suitable for "Substring Anagrams" problem?**
* Fixed Sliding Window.

**17. What type of sliding window is suitable for "Longest Substring With Unique Characters" problem?**
* Dynamic Sliding Window.

**18. What type of sliding window is suitable for "Longest Uniform Substring After Replacements" problem?**
* Dynamic Sliding Window.

**19. What is the typical time complexity achieved by using the sliding window technique? Why?**
* Typically `O(n)`, where n is the size of the input data. 
* This is because each element is visited at most a constant number of times (usually once or twice by the left and right pointers).

**20. What are the space complexity considerations when using the sliding window technique?**
* The space complexity depends on whether you need to store additional information about the elements within the window. 
* In many cases, it can be `O(1)` (constant) if you only need a few variables. 
* However, if you need to store the window's contents in a hash map or other data structure, the space complexity could 
be `O(k)` in the case of fixed window or `O(n)` in the worst case of dynamic window, where k is the size of the window 
and n is the size of the input data.

**21. Are there situations where the sliding window technique is NOT the most efficient approach?**
* Yes. If the problem requires random access to elements far apart in the data structure, or if the problem inherently 
requires comparing all possible subcomponents regardless of their length or position, other techniques like divide-and-conquer 
or dynamic programming might be more suitable. Also, if the constraints of the problem do not require an optimized solution, a brute force approach might be easier to implement.

**22. What is a Monotonic Queue/Deque and how can it be used with Sliding Windows?**
* A Monotonic Queue (or Deque) maintains its elements in either increasing or decreasing order. 
* It can be used to optimize finding the minimum or maximum element within a sliding window in `O(1)` time, 
rather than `O(k)` (window size). 
* This is useful in problems where you need to repeatedly query the min/max of the current window.

**23. What is the most important part of implementing a dynamic sliding window?**
* Defining the "condition" that determines when to expand or shrink the window. 
* This condition is problem-specific and requires careful analysis of the problem statement.

**24. What kind of edge cases should you consider when implementing a sliding window?**
* Empty input array/string.
* Window size larger than the input size (for fixed windows).
* Condition never being met (leading to an infinite loop if not handled correctly).
* All elements satisfying the condition (potentially leading to the window expanding to the entire input).

***

## 06 Binary Search.

**1. Core Idea of Binary Search?**
* Binary search is like finding a word in a dictionary.
* It efficiently searches a sorted dataset.
* It repeatedly divides the search interval in half.

**2. Implementing Binary Search - The Tricky Part?**
* Correct implementation can be challenging.
* Details matter: initializing boundaries, loop conditions, and updating boundaries.

**3. Steps for Binary Search Implementation?**
* Define the search space.
* Narrow the search space within the loop.
* Choose an exit condition for the while loop.
* Return the correct value.

**4. Defining the Search Space?**
* The search space includes all possible values where the target might exist.
* In a sorted array, the initial search space is the entire array.

**5. Narrowing the Search Space - Left or Right?**
* Decide whether to move the left or right pointer inward.
* This reduces the search space until it contains one element or is empty.

**6. Using the Midpoint?**
* The mid variable points to the middle of the current search space.
* Key question: Is the target to the left or right of mid?

**7. Narrowing Right - Excluding the Midpoint?**
* left = mid + 1.
* Use when the midpoint value is definitely not the target.
* The midpoint is excluded from the next search space.

**8. Narrowing Right - Including the Midpoint?**
* left = mid.
* Use when the midpoint value could be the target.
* The midpoint is included in the next search space.

**9. Narrowing Left - Exclude/Include Logic?**
* The same exclude/include logic applies when narrowing the search space towards the left (right = mid - 1 vs right = mid).

**10. Calculating the Midpoint?**
* mid = (left + right) // 2 (Python).
* mid = left + (right - left) // 2 (safer in other languages to prevent overflow).

**11. Exit Condition - left < right?**
* The loop breaks when left and right meet.
* Both pointers converge to a single value.

**12. Exit Condition - left <= right?**
* The loop breaks when left surpasses right.

**13. Returning the Value?**
* After the loop, left and right point to the final value in the search space.
* Return this value (if a valid answer exists).

**14. Time Complexity?**
* Binary search has a time complexity of `O(log n)`.
* Each step halves the search space.

**15. Real-World Example?**
* Transaction search in financial systems.
* Efficiently finding records in sorted data.

**16. Initializing Left and Right Pointers?**
* left usually starts at the beginning of the sorted data (index 0).
* right usually starts at the end of the sorted data (index n-1, where n is the length).
* Correct initialization is crucial for the algorithm to work.

**17. The Importance of Sorted Data?**
* Binary search requires the data to be sorted.
* If the data is not sorted, the algorithm will not produce correct results.
* Sorting the data beforehand adds `O(n log n)` time complexity (if using an efficient sorting algorithm).

**18. Integer Overflow Considerations?**
* When calculating mid, (left + right) / 2 can lead to integer overflow if left + right is a very large number.
* Using left + (right - left) / 2 is a safer way to calculate the midpoint, especially in languages like C++ or Java.

**19. Binary Search Applications - Beyond Simple Searching?**
* Binary search isn't just for finding a specific value.
* It can be adapted to find the first/last occurrence of a value, insertion points, local maxima, and more.
* The core principle of halving the search space remains the same.

**20. Adapting Binary Search?**
* The basic binary search template can be modified to solve a variety of problems.
* Understanding the core logic allows you to adapt the algorithm to different scenarios.
* Careful consideration of the problem constraints is essential.

***

## 07 Binary Search.

**1. What is a Stack? Explain its core principle.**
* A stack is a linear data structure that follows the LIFO (Last-In-First-Out) principle. 
* Think of a stack of plates – the last plate you put on is the first one you take off.

**2. What are the fundamental operations associated with a stack?**
* Push: Adds an element to the top of the stack.
* Pop: Removes and returns the element at the top of the stack.
* Peek: Returns the top element without removing it.
* IsEmpty: Checks if the stack is empty.

**3. What is the time complexity of the core stack operations (push, pop, peek, isEmpty)?**
* All core stack operations (push, pop, peek, isEmpty) have a time complexity of `O(1)`.

**4. How can stacks be used to handle nested structures? Give an example.**
* Stacks are excellent for parsing or validating nested structures like parentheses in a string. 
* The LIFO principle allows you to match the innermost nested elements first. 
* Example: (())().

**5. How can stacks be used to reverse order of elements?**
* Because of LIFO nature, elements pushed onto a stack and then popped come out in reversed order.

**6. How can stacks be used as a substitute for recursion?**
* Recursive algorithms inherently use a call stack. 
* You can often implement recursive functions iteratively by using a stack data structure to manage the function calls.

**7. What is a monotonic stack? What are the two types of monotonic stacks?**
* A monotonic stack maintains its elements in a sorted order (either increasing or decreasing). 
* Before pushing a new element, elements that would break the order are popped. 
* There are two types: monotonic increasing stack and monotonic decreasing stack.

**8. Describe a real-world example of how stacks are used in computer science.**
* Function call management. 
* When a function is called, its state (parameters, local variables, return address) is pushed onto the call stack. 
* When the function completes, its state is popped, and the program returns to the calling function.

**9. How would you use a stack to determine if a string containing parentheses is valid?**
* Iterate through the string.
* If you encounter an opening parenthesis, push it onto the stack.
* If you encounter a closing parenthesis, check if the stack is empty or if the top element of the stack is the
corresponding opening parenthesis. 
* If not, the string is invalid. 
* Otherwise, pop the opening parenthesis from the stack.
* After iterating through the entire string, the stack should be empty for the string to be valid.

**10. How can you implement a Queue using two Stacks?**
* Use one stack for enqueue operations (push). 
* Use the second stack for dequeue operations (pop). 
* When the dequeue stack is empty, transfer all elements from the enqueue stack to the dequeue stack, reversing their order.

**11. How can a stack be used to evaluate arithmetic expressions (e.g., infix to postfix conversion, postfix evaluation)?**
* Stacks are crucial for evaluating expressions. 
* For postfix evaluation, push operands onto the stack. 
* When you encounter an operator, pop the necessary operands, perform the operation, and push the result back onto the stack. 
* For infix to postfix conversion (using the shunting yard algorithm), stacks help manage operator precedence.

**12. How can a deque (double-ended queue) be used to find the maximums of all sliding windows of size k in an array? 
How is this related to stacks?**
* A deque can efficiently maintain the indices of potential maximums within the sliding window. 
* The deque stores indices in decreasing order of their corresponding values. 
* As the window slides, elements that are out of the window range are removed from the front, and elements smaller than 
the current element are removed from the back before adding the current element's index. 
* While not a pure stack problem, the monotonic decreasing property of the deque is conceptually similar to a monotonic stack.

**13. Describe how to find the "Next Greater Element" to the right for each element in an array using a monotonic stack?**
* Use a monotonic decreasing stack. 
* Iterate through the array. 
* If the current element is greater than the element at the top of the stack, pop elements from the stack until the stack 
is empty or the top element is greater than or equal to the current element. 
* The current element is the "Next Greater Element" for all the popped elements. 
* Then, push the current element onto the stack.

**14. How can a stack be used to solve the "Repeated Removal of Adjacent Duplicates" problem in a string?**
* Iterate through the string. 
* If the current character is the same as the top of the stack, pop the stack (removing the duplicate). 
* Otherwise, push the current character onto the stack. 
* The remaining elements in the stack form the string with duplicates removed.

**15. What are the key differences between a Stack and a Queue?**
* Stack: LIFO (Last-In, First-Out). Elements are added and removed from the top.
* Queue: FIFO (First-In, First-Out). Elements are added to the rear and removed from the front.

**16. What is a stack overflow error, and when might it occur?**
* A stack overflow error occurs when the call stack exceeds its allocated memory. 
* This typically happens with excessively deep recursion, where the function calls keep piling up without returning, 
eventually exhausting the stack space.

***

## 08 Heaps.

**1. Define a heap data structure. What are its key properties? What are the two main types of heaps?**
* A heap is a tree-based data structure that satisfies the heap property: 
  * In a min-heap, the value of each node is less than or equal to the value of its children. 
  * In a max-heap, the value of each node is greater than or equal to the value of its children. 
* The two main types are min-heaps and max-heaps. 
* Heaps provide efficient access to the minimum or maximum element.

**2. Explain the difference between a min-heap and a max-heap. Give an example of when you might use each.**
* A min-heap prioritizes the smallest element, keeping it at the root. 
  * Use cases: finding the smallest element quickly, priority queues where lower values indicate higher priority. 
* A max-heap prioritizes the largest element at the root. 
  * Use cases: finding the largest element quickly, priority queues where higher values indicate higher priority.

**3. What are the common operations performed on a heap, and what is the time complexity of each?**
* Insert: `O(log n)`.
* Deletion (of root): `O(log n)`.
* Peek (find min/max): `O(1)`.
* Heapify (building a heap): `O(n)`.

**4. How is a heap typically implemented? What data structure is commonly used?**
* Heaps are commonly implemented using arrays. 
* The array represents a complete binary tree. 
* The children of node `i` are typically at indices `2i + 1` and `2i + 2`. 
* This array-based representation allows for efficient traversal and manipulation of the heap.

**5. What is a priority queue, and how does it relate to heaps?**
* A priority queue is an abstract data type that operates like a heap. 
* It allows elements to be inserted with a priority, and the element with the highest priority (min or max, depending on 
the implementation) is always the first to be removed. 
* Heaps are often used to implement priority queues efficiently. 
* The priority can be based on any criteria, not just numerical value.

**6. Describe a real-world example where heaps are used?**
* Task scheduling in operating systems. 
* The OS uses a priority queue (often implemented with a heap) to determine which process to run next. 
* Higher-priority processes are executed before lower-priority ones.

**7. Explain the "heapify" operation. What does it do, and why is its time complexity O(n)?**
* Heapify is the process of converting an unsorted array into a heap. 
* While a naive approach might suggest `O(n log n)`, a more efficient bottom-up approach achieves `O(n)` by processing 
nodes from the bottom of the tree upwards, ensuring that each subtree satisfies the heap property.

**8. In what scenarios would you prefer a heap over a sorted array for finding the minimum or maximum element repeatedly?**
* Heaps are preferable when you need to repeatedly find and remove the minimum/maximum element and insert new elements. 
* The `O(log n)` insertion and deletion in a heap are more efficient than maintaining a sorted array,
which would require `O(n)` for insertion or deletion in the worst case.

**9. How can a heap be used to sort a K-sorted array efficiently? What is the time complexity?**
* A K-sorted array is an array where each element is at most 'k' positions away from its sorted position. 
* A min-heap of size k+1 can be used. 
* Add the first k+1 elements to the heap. 
* Then, for each remaining element in the array, remove the smallest element from the heap and place it in the sorted array, 
and add the next element from the unsorted array into the heap. 
* Time complexity: `O(n log k)`.

**10. What is the space complexity of a heap?**
* The space complexity of a heap is `O(n)`, where n is the number of elements in the heap. 
* This is because, in the array-based implementation, you need to store all the elements of the heap in the array.

**11. Describe a scenario where using a heap would not be the most efficient approach.**
* If you need to find the minimum or maximum element only once, and you don't need to perform any insertions or deletions, 
simply iterating through the array to find the min/max would be more efficient (`O(n)`) than building a heap first 
(also `O(n)`, but with a higher constant factor). 
* Also, if you need a fully sorted array as the final result, other sorting algorithms like merge sort or quicksort might be more appropriate.

**12. Describe the process of "heapifying" a subtree within a larger heap. What steps are involved?**
* Heapifying a subtree involves ensuring that the subtree rooted at a particular node satisfies the heap property. 
* This typically involves comparing the node with its children and, if necessary, swapping the node with the smaller 
(min-heap) or larger (max-heap) child. 
* This process is then recursively applied to the affected subtree until the heap property is restored. 
* This is a key step in both insertion and deletion operations.

***

## 09 Intervals.

**1. What is an interval? Describe its key components. What are the different types of intervals?**
* An interval represents a continuous segment on a number line, defined by a start and end point. 
* Types: Closed (inclusive start & end), Open (exclusive start & end), Half-Open (inclusive start OR end). 
* Clarify the interval type in an interview!

**2. How do you determine if two intervals overlap? Why is managing overlapping intervals important in interval problems?**
* Two intervals overlap if they share at least one common value. 
* Managing overlaps is crucial because it directly impacts the problem's desired outcome (e.g., merging, counting, etc.).

**3. Why is sorting intervals a common first step? What is the typical sorting criteria? Are there situations where you 
might need a secondary sorting criteria?**
* Sorting allows processing intervals in chronological order. 
* Typically, sort by the start point. 
* If start points are equal, sort by the end point.

**4. Describe the "separating start and end points" technique. When is this approach useful?**
* Create two sorted arrays: one for start points, one for end points. 
* Useful for algorithms like the sweeping line algorithm where you need to track the number of active intervals at any given time.

**5. Give a real-world example of how intervals are used.**
* Scheduling systems (e.g., conference room booking). 
* Each booking is an interval. 
* Analyzing intervals helps prevent double bookings and optimize resource allocation.

**6. What are the two main categories of interval problems discussed in the chapter outline?**
* Managing Overlaps (merging, identifying overlaps). 
* Finding Values in Sorted Order (e.g., largest overlap).

**7. What edge cases should you consider when working with intervals? What about empty intervals (start == end)?**
* Consider:
    * Empty input list of intervals.
    * Overlapping intervals that are adjacent (e.g., `[1, 2]` and `[2, 3]`). Do they overlap in your problem's context?
    * Empty intervals (start == end). How should these be handled? The problem statement should define this.

**8. How does the choice of data structure (e.g., array of arrays, list of Interval objects) affect the implementation 
of interval algorithms?**
* The data structure dictates how you access and manipulate interval data. 
* Using an Interval class provides better encapsulation and readability, especially for complex logic. 
* Arrays of arrays are simpler for basic operations but can become less maintainable.

**9. What is the typical time complexity of algorithms involving sorting intervals? How does separating start and end 
points affect space complexity?**
* Sorting intervals usually takes `O(n log n)` time, where n is the number of intervals.
* Separating start and end points increases space complexity to `O(n)` because you're creating two new arrays.

**10. Describe different types of interval overlaps.**
* Complete Overlap: One interval is completely contained within another.
* Partial Overlap: Intervals share some common values but neither is fully contained within the other.
* No Overlap: Intervals are completely separate.
* Visualizing these scenarios helps in coding.

***

## 10 Prefix Sums.

**1. What is the core idea behind the Two Pointers pattern? When is it beneficial?**
* The Two Pointers pattern uses two variables (pointers) to traverse a data structure (usually a linear one like an array 
or linked list) in a coordinated way. 
* It's beneficial when you can leverage some inherent order or relationship between elements to reduce time complexity, 
often from `O(n^2)` to `O(n)`. 
* Think about comparisons and predictable dynamics.

**2. Describe the "Inward Traversal" strategy. What type of problems is it suited for?**
* Pointers start at opposite ends of the data structure and move towards each other. 
* Good for problems that involve finding pairs or relationships between elements at opposite ends, like checking if a 
string is a palindrome or finding a pair with a specific sum in a sorted array.

**3. Explain the "Unidirectional Traversal" strategy. What is a common use case?**
* Both pointers start at the same end and move in the same direction. 
* Often, one pointer is used to find information (e.g., the 'right' pointer), and the other is used to keep track of 
information or modify the data structure (e.g., the 'left' pointer). 
* Example: Moving all zeros to the end of an array.

**4. Describe the "Staged Traversal" strategy. How do the pointers interact?**
* One pointer (the 'first' pointer) moves until it finds an element that meets a certain condition. 
* Then, the second pointer is activated to find additional information related to the element found by the first pointer. 
* The pointers serve different purposes and don't necessarily move in lockstep.

**5. What are "predictable dynamics," and why are they important for Two Pointers? Give examples.**
* Predictable dynamics refer to inherent properties or order within the data structure that allow you to make logical 
deductions as you move the pointers. 
* Examples: a sorted array (knowing elements increase/decrease in a certain direction), a palindromic string (symmetry). 
* These dynamics allow you to avoid brute-force comparisons.

**6. What are some clues that a problem might be solvable with the Two Pointers pattern?**
* Look for:
    * Linear data structures (arrays, linked lists).
    * Sorted input.
    * Problems asking for pairs of values that satisfy a condition.
    * Problems where the relationship between elements changes predictably as you move through the data.

**7. What is the typical time complexity of a Two Pointers algorithm, and why?**
* Typically `O(n)`, because you're often replacing nested loops (`O(n^2)`) with a single pass through the data using two pointers.

**8. Describe the real-world example of Garbage Collection Algorithms, and how it relates to the Two Pointer technique.**
* In memory compaction, a 'scan' pointer identifies live objects, while a 'free' pointer tracks the next available space. 
* The 'scan' pointer skips dead objects and shifts live objects to the 'free' pointer's position, compacting memory.

**9. What is the typical space complexity of a Two Pointers algorithm? Are there situations where it might be higher?**
* Two Pointers algorithms are often designed to be in-place, meaning they modify the input directly and use minimal extra space.
* Therefore, the space complexity is often `O(1)` (constant). 
* However, if you need to create auxiliary data structures (e.g., a hash map to store seen values in a variation of the problem), 
the space complexity could increase to `O(n)` in the worst case.

**10. What are some common mistakes to watch out for when implementing Two Pointers?**
* Off-by-one errors: Carefully consider the loop conditions (e.g., left < right vs. left <= right) to avoid skipping 
elements or going out of bounds.
* Incorrect pointer movement: Ensure you're moving the pointers in the correct direction based on the problem's logic 
and the predictable dynamics of the data.
* Not handling edge cases: Consider empty arrays, single-element arrays, or other special input conditions.
* Forgetting to update both pointers: In some scenarios, you need to update both pointers in each iteration of the loop.

**11. Can the Two Pointers pattern be applied to linked lists? If so, how might it differ from its application in arrays?**
* Yes, Two Pointers can be used with linked lists. 
* However, you can't directly access elements by index like you can with arrays. 
* Instead, you move the pointers by following next pointers. 
* Common use cases include finding the middle of a linked list, detecting cycles, or reversing a linked list (with some modifications).

**12. How does the Two Pointers pattern relate to the Sliding Window pattern? What are the key differences?**
* The Sliding Window pattern can be considered a specialized form of the Two Pointers pattern. 
* In Sliding Window, you have two pointers defining a "window" of elements, and you move this window across the data structure, 
typically maintaining some property within the window. 
* The Two Pointers pattern is more general and doesn't necessarily involve maintaining a contiguous window.


**13. Describe the approach to finding a pair with a specific sum in a sorted array using inward traversal.**
* Use two pointers, one at the beginning (left) and one at the end (right). 
* If `nums[left] + nums[right]` is less than the target, increment left. 
* If it's greater than the target, decrement right. 
* If it equals the target, you've found the pair.

**14. How can you extend the Pair Sum approach to find triplets that sum to a target?**
* Iterate through the array. 
* For each element `nums[i]`, use the Pair Sum approach on the remaining subarray (`nums[i+1]` to the end) to find two 
numbers that sum to `target - nums[i]`. 
* Be mindful of duplicates!

**15. In the "Largest Container" problem (area between lines), how do you decide which pointer to move?**
* Move the pointer associated with the shorter line. 
* The area is limited by the shorter line, so increasing the shorter line has the potential to increase the area. 
* Moving the taller line will always decrease the area.

**16. How do you check if a string is a palindrome using inward traversal, potentially ignoring non-alphanumeric characters?**
* Use two pointers, one at the beginning and one at the end. 
* Move inward, skipping non-alphanumeric characters. 
* Compare the characters at each pointer (case-insensitively). If they don't match, it's not a palindrome.

**17. Describe how to shift all zeros to the end of an array while maintaining the relative order of the non-zero elements 
using unidirectional traversal.**
* Use two pointers: left (points to the position where the next non-zero element should be placed) and right 
(iterates through the array). 
* If `nums[right]` is non-zero, swap `nums[left]` and `nums[right]`, and increment left.

**18. Outline the steps to find the next lexicographical permutation of a sequence using staged traversal.**
* Find the pivot: Scan from right to left to find the first element `nums[i]` that is smaller than `nums[i+1]`.
* Find the swap element: Scan from right to left to find the smallest element `nums[j]` that is greater than `nums[i]`.
* Swap: Swap `nums[i]` and `nums[j]`.
* Reverse: Reverse the subarray from i+1 to the end.

***

## 11 Trees.

Card 1: Title: Tree Fundamentals

Front: Define a tree. What are its key components (nodes, edges, root, leaf, parent, child)? What's a binary tree?
Back: A tree is a hierarchical data structure. Nodes store data and have connections (edges) to other nodes. Key components:
Root: Topmost node, no parent.
Leaf: Node with no children.
Parent/Child: Defines the hierarchical relationship.
Binary Tree: Each node has at most two children (left and right).
Card 2: Title: Tree Terminology

Front: Define subtree, path, depth, and height in the context of trees.
Back:
Subtree: A tree formed by a node and all its descendants.
Path: A sequence of connected nodes.
Depth: Number of edges from the root to a node.
Height: Length of the longest path from the root to a leaf. (Important: In this chapter, a tree with one node has a height of 1).
Card 3: Title: DFS - Depth-First Search

Front: Explain DFS. What are the three main types of DFS traversals? Give code snippet for each one.
Back: DFS explores a tree branch as far as possible before backtracking. Types:
Preorder: Process node, then left, then right.
def preorder(node):
if not node: return
process(node)
preorder(node.left)
preorder(node.right)
Inorder: Left, then process node, then right.
def inorder(node):
if not node: return
inorder(node.left)
process(node)
inorder(node.right)
Postorder: Left, then right, then process node.
def postorder(node):
if not node: return
postorder(node.left)
postorder(node.right)
process(node)
Card 4: Title: DFS Use Cases

Front: When would you choose Preorder, Inorder, or Postorder traversal?
Back:
Preorder: Good for duplicating a tree or getting prefix notation of an expression tree. Process root before children.
Inorder: Useful for Binary Search Trees (BSTs) to get elements in sorted order. Process nodes from left to right.
Postorder: Good for deleting a tree (delete children before parent) or getting postfix notation of an expression tree. Process subtrees before their root.
Card 5: Title: BFS - Breadth-First Search

Front: Explain BFS. How is it typically implemented? Give code snippet.
Back: BFS explores a tree level by level. It uses a queue.
from collections import deque
def bfs(root):
if not root: return
queue = deque([root])
while queue:
node = queue.popleft()
process(node)
if node.left: queue.append(node.left)
if node.right: queue.append(node.right)
Card 6: Title: BFS Use Cases

Front: When is BFS preferred over DFS?
Back: BFS is preferred when you need to find the shortest path between nodes or when you need to process the tree level by level. Level-order traversal is a variant of BFS.
Card 7: Title: Complexity Analysis - DFS

Front: What are the time and space complexities of DFS? Explain.
Back:
Time: O(n), where n is the number of nodes (visits each node once).
Space: O(h), where h is the height of the tree (due to the call stack). Worst case: O(n) for a skewed tree. Best (balanced) case: O(log n).
Card 8: Title: Complexity Analysis - BFS

Front: What are the time and space complexities of BFS? Explain.
Back:
Time: O(n), where n is the number of nodes (visits each node once).
Space: O(n), in the worst case, when the queue contains all nodes at the widest level (approximately n/2 nodes in a complete binary tree).
Card 9: Title: Real-World Tree Applications

Front: Describe a real-world example of tree data structures.
Back: File systems are a classic example. The root directory is the root of the tree, and directories/files are nodes. Other examples include: organizational charts, DOM (Document Object Model) in web pages, and decision trees in machine learning.
Card 10: Title: Choosing DFS vs. BFS

Front: Summarize the key differences between DFS and BFS, and how to choose between them.
Back:
DFS: Explores deeply, uses recursion (stack). Good for existence checks, path finding when path length isn't a primary concern. Less memory if tree is balanced.
BFS: Explores level by level, uses a queue. Good for shortest path problems, level-order traversal. More memory.
Choice: Shortest path => BFS. Space is a concern & tree is deep => DFS. Need to process in a specific order (pre/in/post) => DFS.


Card 11: Title: Binary Search Trees (BSTs)

Front: What is a Binary Search Tree (BST)? What are its key properties? Why are they useful?
Back:
A BST is a binary tree with the following properties:
The value of each node is greater than or equal to all values in its left subtree.
The value of each node is less than or equal to all values in its right subtree.
BSTs allow for efficient searching, insertion, and deletion of nodes (O(log n) on average for balanced trees).
Inorder traversal of a BST yields the nodes in sorted order.
Card 12: Title: Tree Balancing

Front: What does it mean for a tree to be balanced? Why is balancing important? Give examples of balanced vs. unbalanced trees.
Back:
A balanced tree is one where the heights of the subtrees of any node differ by at most a certain constant (often 1).
Balancing is important to ensure that operations like search, insert, and delete remain efficient (O(log n)). Unbalanced trees can degenerate into linked lists, leading to O(n) performance.
Examples:
Balanced: AVL trees, Red-Black trees are self-balancing. A complete binary tree is also balanced.
Unbalanced: A skewed tree (all nodes on one side) is unbalanced.















