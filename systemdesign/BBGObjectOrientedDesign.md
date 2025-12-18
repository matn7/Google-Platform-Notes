## 3 - OOP Fundamentals.

**1 OOP Principles.**
* Encapsulation: Bundle data/methods, hide internal state.
* Abstraction: Simplify complex systems, hide details.
* Inheritance: Reuse code, create "is-a" relationships.
* Polymorphism: Objects take multiple forms.

**2. Encapsulation.**
* Protect data integrity with private attributes.
* Use getters/setters for controlled access.
* Avoid over/under-encapsulation.

**3. Abstraction.**
* Use abstract classes/interfaces.
* Hide implementation details.
* Focus on "what" not "how".

**4. Inheritance.**
* Create class hierarchies ("is-a").
* Promotes code reuse.
* Can lead to tight coupling.

**5. Composition.**
* "Has-a" relationship.
* More flexible than inheritance.
* Preferred for swappable behaviors.

**6. Polymorphism.**
* Objects behave differently based on context.
* Compile-time (overloading) & runtime (overriding).
* Enables extensibility.

**7. SOLID - SRP.**
* Single Responsibility Principle.
* One class, one reason to change.
* Improves maintainability.

**8. SOLID - OCP.**
* Open/Closed Principle.
* Open for extension, closed for modification.
* Use abstract classes/interfaces.

**9. SOLID - LSP.**
* Liskov Substitution Principle.
* Subtypes must be substitutable for base types.
* Maintain expected behavior.

**10. SOLID - ISP & DIP.**
* ISP: Interface Segregation Principle (no forced dependencies).
* DIP: Dependency Inversion Principle (depend on abstractions).
* Promote loose coupling.

## 04 - Design a Parking Lot.

**1. Parking Lot Requirements.**
* Vehicle Types: Motorcycle, Car, Truck.
* Spot Types: Compact, Regular, Oversized.
* Tickets: Issued on entry, fees calculated on exit (duration, size, time).
* Scalability & Reliability.

**2. Key Objects.**
* Vehicle: Represents a vehicle (license, size).
* ParkingSpot: Individual parking space (availability, size).
* Ticket: Tracks parking session (vehicle, spot, entry/exit times).
* ParkingManager: Allocates/deallocates spots.
* ParkingLot: Facade, manages entry/exit.

**3. Vehicle Design.**
* Vehicle: Interface (getLicensePlate, getSize).
* VehicleSize: Enum (SMALL, MEDIUM, LARGE).
* Motorcycle, Car, Truck: Implement Vehicle.
* getSize() preferred over getType() for flexibility.

**4. Parking Spot Design.**
* ParkingSpot: Interface (isAvailable, occupy, vacate, getSpotNumber, getSize).
* CompactSpot, RegularSpot, OversizedSpot: Implement ParkingSpot.
* ParkingSpot focuses on state; ParkingManager handles logic.

**5. Parking Manager.**
* Manages spot allocation/deallocation
* findSpotForVehicle(Vehicle): Finds suitable spot.
* parkVehicle(Vehicle): Parks vehicle in spot.
* unparkVehicle(Vehicle): Removes vehicle from spot.
* Uses availableSpots and vehicleToSpotMap.

**6. Ticket Design.**
* ticketId, vehicle, parkingSpot, entryTime, exitTime.
* calculateParkingDuration().
* Immutable record of parking event.
* Fee calculation delegated to FareCalculator.

**7. Fare Calculation.**
* FareStrategy: Interface (calculateFare).
* BaseFareStrategy: Base rate based on vehicle size & duration.
* PeakHoursFareStrategy: Adjusts for peak hours.
* FareCalculator: Applies strategies in sequence.

**8. Parking Lot as Facade.**
* Simplifies interactions with the system.
* enterVehicle(Vehicle): Handles vehicle entry.
* leaveVehicle(Ticket): Handles vehicle exit.
* Delegates to ParkingManager & FareCalculator.

**9. Design Patterns Used.**
* Strategy: FareStrategy (flexible pricing rules).
* Facade: ParkingLot (simplifies system interaction).

**10. Potential Interview Questions.**
* Adding new ParkingSpot types (HandicappedSpot).
* Faster Parking Spot Management (bidirectional maps - spotToVehicleMap).
* Trade-offs of different design choices.

## 05 - Design a Movie Ticket Booking System.

**1. What is the primary goal of a Movie Ticket Booking System?**
* To allow users to seamlessly browse movies, select showtimes, book seats, and receive digital tickets, while efficiently
managing seat availability and tracking screenings.

**2. Name the core objects in a Movie Ticket Booking System.**
* Movie, Cinema, Room, Layout, Seat, Screening, Ticket, Order, ScreeningManager, MovieBookingSystem

**3. Why separate the Movie and Screening classes?**
* Movie stores static film details (title, genre, duration). 
* Screening links a Movie to a specific Room and time, allowing for multiple showtimes. 
* This improves reusability and clarity.

**4. How is seat pricing handled, and why?**
* Using the Strategy pattern (PricingStrategy interface with NormalRate, PremiumRate, VIPRate). 
* This allows flexible pricing models without modifying the Seat class.

**5. What data structure is used in the Layout class, and why?**
* A nested map (`Map<Integer, Map<Integer, Seat>>`) for seat arrangement and a map (`Map<String, Seat>`) for seat number lookup. 
* This allows dynamic row creation and supports variable row sizes with efficient access.

**6. What is the relationship between the Cinema and Room classes?**
* Composition. A Cinema has-a list of Rooms. This models a real-world cinema with multiple screening rooms. 

**7. What is the role of the ScreeningManager class?**
* It manages key operations like searching for screenings, identifying available seats, and storing purchased tickets. 
* It centralizes screening and ticket-related operations.

**8. What design pattern does the MovieBookingSystem class implement, and why?**
* Facade. It provides a simplified interface for client code to interact with the system, hiding the complexity of the 
underlying classes.

**9. What are two strategies for handling concurrent bookings?**
* Pessimistic Locking (locks seats at the start of booking) and Optimistic Locking (verifies seat availability at the end).

**10. What are the trade-offs between pessimistic and optimistic locking?**
* Pessimistic locking guarantees exclusive access but increases latency. 
* Optimistic locking is lightweight but may require retries in high-contention scenarios.

## 06 - Design a Unix File Search System.

**1. What is the primary objective of designing a Unix File Search system?**
* To create a modular, maintainable, and scalable system for locating files based on specified criteria within a directory structure.

**2. List the main functional requirements of the file search system.**
* Search by attributes (size, type, filename, owner).
* Support comparison types (equals, regex, >, =, <).
* Combine criteria with logical operators (AND, OR, NOT).
* Recursive directory searches.
* Apply criteria to both files and directories.

**3. What are the key non-functional requirements?**
* Scalability: Handle large directory trees efficiently.
* Extensibility: Add new attributes/operators easily.
* Separation of Concerns: Keep traversal and filtering separate.

**4. Name the core objects in the design and their roles.**
* File: Represents a file or directory with attributes.
* FileSearch: Manages the search process, traverses the file system.
* FileSearchCriteria: Determines if a file matches the search condition.
* Predicate: Interface for defining match conditions.
* SimplePredicate: Implements simple attribute comparisons.
* CompositePredicate: Combines predicates (AND, OR, NOT).
* ComparisonOperator: Interface for comparing attribute values.

**5. What is the purpose of the File class and the FileAttribute enum?**
* File models files/directories with attributes (size, owner, etc.) and a hierarchical structure. 
* FileAttribute is an enum that provides a type-safe set of attributes for search conditions.

**6. What is the main responsibility of the FileSearch class?**
* To traverse the file system, apply the FileSearchCriteria, and return a list of matching files. 
* It uses a stack-based approach for efficient traversal.

**7. What role does FileSearchCriteria play in the system?**
* It acts as a wrapper around a Predicate and determines whether a given File matches the search criteria by delegating 
to the Predicate.

**8. Explain the purpose of the Predicate interface and its key implementations.**
* Predicate defines the isMatch method for evaluating files against a condition. 
* SimplePredicate handles single attribute comparisons. 
* CompositePredicate (AndPredicate, OrPredicate, NotPredicate) combines multiple predicates.

**9. What is the purpose of the ComparisonOperator interface and give examples of its implementations?**
* Defines how attribute values are compared (e.g., equals, greater than, regex match). 
* Implementations include EqualsOperator, GreaterThanOperator, RegexMatchOperator, etc.

**10. How is the Composite pattern used in this design?**
* It's used to create complex search conditions by combining simpler predicates (SimplePredicates) using logical 
operators (AND, OR, NOT) via the CompositePredicate interface and its implementations. 
* This allows treating individual conditions and complex combinations uniformly.

**11. Describe some alternative design choices and the trade-offs involved.**
* Merging FileSearchCriteria and Predicate: Simplifies the design but reduces modularity and makes it harder to change 
filtering logic independently.
* Representing Operations with Strings/Enums: Strings simplify initial implementation but shift validation to runtime. 
Enums are safer but require modification to add new operations.
* Recursive vs. Stack-Based Traversal: Recursive is simpler for small directories but can cause stack overflow in deep 
filesystems. Stack-based is more robust.

**12. What are the main advantages of the chosen design for the file search system?**
* Modularity: Clear separation of concerns between file representation, traversal, and filtering.
* Extensibility: Easy to add new attributes, comparison operators, and logical combinations without modifying core logic.
* Scalability: Stack-based traversal handles large directory trees efficiently.
* Type Safety: Generics in ComparisonOperator ensure type-safe comparisons.

***

## 07 - Design a Vending Machine.

**1. Core Requirements.**
* Functional: Product selection, inventory management, payment processing (cash, change).
* Non-Functional: Intuitive UI, secure access (admin vs. user), inventory tracking.

**2. Actors & Use Cases.**
* User: Insert Money, Select Product, Receive Product, Receive Change.
* Admin: Add Product, Remove Product, Update Inventory.
* System: Process Payment, Dispense Product, Check Inventory, Display Message.

**3. Core Objects.**
* VendingMachine: Orchestrates operations (the Facade).
* Product: Item details (code, description, price).
* Rack: Holds product units, manages dispenser.
* InventoryManager: Tracks product levels in racks.
* PaymentProcessor: Handles payments, balance, change.

**4. Product Class.**
* Attributes: productCode (String), description (String), unitPrice (BigDecimal).
* BigDecimal is preferred for monetary values.

**5. Rack Class.**
* Attributes: rackCode (String), product (Product), count (int).
* Focus: Managing inventory count and product information.

**6. InventoryManager Class.**
* Key Attribute: racks (`Map<String, Rack>`).
* Key Methods: `getProductInRack(String rackCode)`, `dispenseProductFromRack(Rack rack)`, `updateRack(Map<String, Rack> racks)`.

**7. PaymentProcessor Class.**
* Key Attribute: currentBalance (BigDecimal).
* Key Methods: `addBalance(BigDecimal amount)`, `charge(BigDecimal amount)`, `returnChange()`.

**8. VendingMachine Class.**
* Key Attributes: inventoryManager, paymentProcessor, currentTransaction, transactionHistory.
* Uses the Facade pattern.

**9. State Pattern.**
* Enforces action sequences (insert money -> select product).
* Key States: NoMoneyInsertedState, MoneyInsertedState, DispenseState.
* Each state handles specific actions and provides feedback.

**10. Key Design Principles.**
* Single Responsibility Principle: Each class has a focused purpose.
* Facade Pattern: Simplifies the interface to a complex system.
* State Pattern: Manages state-dependent behavior.
* Composition: Rack uses composition to represent the inventory spaces within the vending machine.

**11. Transaction Class.**
* Attributes: rack (Rack), product (Product), totalAmount (BigDecimal).
* Purpose: Data structure to track the state of a purchase in progress.
* Benefits: Encapsulates purchase details, improves coordination between components.

**12. Error Handling & Validation.**
* Importance: Crucial for preventing damage or financial loss.
* Example: `validateTransaction()` method in VendingMachine (checks product availability, sufficient funds).
* Clear and concise error messages are essential for user guidance.

**13. Design Choices - Racks vs Products.**
* Products are linked to racks because racks represent physical storage locations.
* Racks don't manage product details to adhere to the single responsibility principle.

**14. Design Choices - Collections Management.**
* When managing collections like racks in InventoryManager, decide whether to expose the collection directly, a copy, or specific methods.
* Balance flexibility and control.
* Consider immutable collections or defensive copying to prevent unintended modifications and ensure thread safety 
in multi-threaded environments.

***

## 08 - Design an Elevator System.

**1. What is the central class in the elevator system design, and what are its primary responsibilities?**
* ElevatorSystem. Manages ElevatorCar instances, tracks their status, and delegates dispatching requests. 
* APIs: `getAllElevatorStatuses()`, `requestElevator()`, `selectFloor()`.

**2. What does the ElevatorCar class represent, and what data does it hold?**
* Represents a single elevator. 
* Holds ElevatorStatus (current floor, direction) and a `Queue<Integer>` of targetFloors. 
* Methods: `getStatus()`, `addFloorRequest()`, `isIdle()`, `updateDirection()`.

**3. What is the purpose of the ElevatorStatus class?**
* Encapsulates the current state of an elevator: currentFloor and currentDirection. 
* Provides a snapshot of the elevator's condition.

**4. What are the possible values of the Direction enum, and why is an enum used?**
* `UP`, `DOWN`, `IDLE`. 
* Using an enum provides type safety and avoids ambiguity when representing elevator movement.
 
**5. What is the role of the ElevatorDispatch class?**
* Assigns elevators to handle hallway requests. 
* Uses a DispatchingStrategy to select the most appropriate elevator. 
* Method: `dispatchElevatorCar()`.

**6. Why is the Strategy Pattern used for elevator dispatch?**
* Allows the system to dynamically switch between different algorithms for optimizing elevator allocation (e.g., FCFS, SSTF). 
* Promotes flexibility and testability.

**7. Describe the First Come, First Serve (FCFS) dispatching strategy.**
* Assigns requests to the next available elevator, regardless of direction or proximity. 
* Simple but potentially inefficient.

**8. Describe the Shortest Seek Time First (SSTF) dispatching strategy.**
* Assigns requests to the elevator that can reach the requested floor the fastest, considering direction and proximity. 
* Aims to minimize wait time.

**9. How can the Observer Pattern improve hallway request handling?**
* Decouples hallway buttons from the dispatch controller. 
* Buttons act as subjects, notifying the controller (observer) when pressed. 
* Improves responsiveness and maintainability.

**10. How can the design be modified to handle elevators that serve different sets of floors?**
* Add an accessibleFloors attribute to ElevatorCar. 
* Validate hallway requests to ensure the assigned elevator can stop at the requested floor. 
* Update dispatching strategies to consider accessible floors.

**11. What are the main actors and use cases in the elevator system?**
* Actors: Passenger, System. 
* Use Cases (Passenger): Request Elevator, Select Floor. 
* Use Cases (System): Assign Elevator, Move Elevator, Report Elevator Status.

**12. What are the key object-oriented design principles demonstrated in this system?**
* Modularity (separation of concerns into classes like ElevatorSystem, ElevatorCar, ElevatorDispatch), 
* Encapsulation (ElevatorStatus hides state details). 
* Strategy Pattern (for dispatching). 
* Observer Pattern (for event-driven requests). 
* Managing complexity through delegation and composition.

## 09 - Design a Grocery Store System.

**1. Grocery Store System.**
* Design a system for grocery store workers to manage item catalogs, inventory, pricing, discounts, 
and checkout processes efficiently.

**2. Functional Requirements.**
* The system must support catalog management, inventory tracking, checkout processing, and discount campaign management.

**3. System Qualities.**
* The system should provide clear error messages and be modular, allowing for independent updates to components.

**4. Core Objects.**
* Item, Catalog, Inventory, Order, DiscountCampaign. These are the fundamental building blocks of the system.

**5. Item.**
* Represents a product with attributes like name, barcode, category, and price. It's a simple data container.

**6. Catalog.**
* Manages the list of available items, providing methods to add, update, remove, and retrieve items by barcode. 
* Uses a Map for efficient lookups.

**7. Inventory.**
* Tracks the stock levels for each item, using a Map to associate barcodes with quantities. 
* Supports adding, reducing, and getting stock.

**8. Separation of Concerns.**
* The Catalog manages static item details (name, price), while Inventory manages dynamic stock levels. 
* This improves modularity.

**9. Discount Criteria.**
* Defines the interface for determining if a discount applies to an item (e.g., `isApplicable()`). 
* Allows for different criteria types (category-based, item-based). 

**10. Discount Strategy.**
* Defines the interface for calculating the discounted price (e.g., `calculateDiscountedPrice()`). 
* Supports different calculation strategies (amount-based, percentage-based).

**11. Discount Campaign.**
* Combines a DiscountCriteria and a DiscountCalculationStrategy to define a specific discount promotion.

**12. Order Item.**
* Represents a specific item in an order, along with its quantity. 
* Calculates the total price for the item, with or without discounts.

**13. Order.**
* Manages the list of items in an order, applies discounts, and calculates the subtotal and total amounts.

**14. Checkout.**
* Orchestrates the checkout process, adding items to the order, applying discounts, processing payments, 
and generating a receipt.

**15. System Facade.**
* Provides a unified interface to interact with the system's components (Catalog, Inventory, Checkout). 
* Simplifies client interactions.

**16. Composite Criteria.**
* Used to combine multiple DiscountCriteria objects using logical operators (AND, OR). 
* Allows for complex discount eligibility rules (e.g., "Item in category X AND price > $Y").

**17. Decorator Pattern.**
* Used to layer multiple discount calculations sequentially (e.g., fixed amount discount followed by a percentage discount). 
* Wraps DiscountCalculationStrategy objects.

**18. Discount Decorators.**
* FixedDiscountDecorator and PercentageDiscountDecorator are examples of decorators that wrap a base 
DiscountCalculationStrategy to add additional discount logic.

**19.Design for Extensibility.**
* The use of interfaces (DiscountCriteria, DiscountCalculationStrategy) and design patterns (Composite, Decorator) makes 
the system highly extensible. 
* New discount types and criteria can be added without modifying existing code (Open/Closed Principle).

**20. Key Principles.**
* Separation of Concerns, Modularity, Extensibility, and the application of design patterns are crucial for creating 
a well-designed and maintainable grocery store system.

## 10 - Design a Tic Tac Toe Game.

**1. Functional requirements for Tic-Tac-Toe.**
* 3x3 board, win (align 3 symbols), draw (full board), in-progress status, score tracking, invalid move handling with feedback.

**2. Key non-functional requirements for the Tic-Tac-Toe game.**
* Intuitive user interface, clear feedback for invalid moves and game outcomes, easily accessible gameplay instructions, 
support for future enhancements without major architectural changes.

**3. Identify the core objects (classes) required for the game and their primary responsibilities.**
* Board (manages the game grid), Player (represents a player), Game (coordinates gameplay), ScoreTracker (tracks player ratings).

**4. Detailed responsibilities of the Game class.**
* Initializes Board and ScoreTracker, manages turn-taking between players, validates moves (ensuring cell isn't occupied), 
tracks the game's status (GameCondition enum: IN_PROGRESS, ENDED), updates ScoreTracker at game end.

**5. Detailed responsibilities of the Board class.**
* Represents the 3x3 grid using a 2D array (Player[][] grid), provides methods to `updateBoard()` with player moves, 
`getWinner()` by checking rows, columns, and diagonals, `isFull()` to determine a draw, `reset()` the board for a new game, 
and `getPlayerAt()` a specific position.

**6. Detailed responsibilities of the ScoreTracker class.**
* Manages player ratings across multiple games using a `HashMap<Player, Integer>`, provides methods to `reportGameResult()` 
to update scores based on win/loss/draw, `getTopPlayers()` to retrieve a sorted map of top-ranked players, and `getRank()`
to determine a player's rank.

**7. Purpose and structure of the Move class.**
* A simple data structure to encapsulate a player's move, containing rowIndex, colIndex, and a reference to the Player 
who made the move. 
* Improves code readability and maintainability by bundling move-related data.

**8. Attributes and purpose of the Player class.**
* Stores basic player information: name (String) and symbol (char). 
* Adheres to the Single Responsibility Principle (SRP) by focusing solely on player identity and avoiding game logic.

**9. Explanation and importance of the Single Responsibility Principle (SRP) in the Tic-Tac-Toe design.**
* Each class should have only one reason to change. 
* This promotes modularity, maintainability, and testability. 
* The Game class delegates grid management to Board and rating updates to ScoreTracker to adhere to SRP.

**10. Purpose and values of the GameCondition enum.**
* Represents the current state of the game: IN_PROGRESS or ENDED. 
* Used by the Game class to determine the game's status and control the flow of gameplay.

**11. How to implement undo functionality in the Tic-Tac-Toe game.**
* Utilize a stack (`ArrayDeque<Move>`) within a MoveHistory class to store moves in chronological order. 
* The `undoMove()` method pops the most recent move from the stack and reverts the board state accordingly.

**12. Explanation of the Memento Pattern and its application in implementing undo functionality.**
* The Memento Pattern allows an object to save and restore its previous state without exposing its internal structure. 
* Key components: Move (Memento), MoveHistory (Caretaker), Game (Originator).

**13.Potential challenges of using the Memento Pattern, specifically regarding memory overhead.**
* Storing every move in the history can consume significant memory, especially in more complex games with larger states. 
* Consider alternative approaches (e.g., storing only the changes made) to mitigate memory usage.

**14. Rationale behind the choice of specific data structures in the Tic-Tac-Toe design.**
* 2D array (Player[][] grid) in Board for direct mapping to the game grid and O(1) access time. 
* `HashMap<Player, Integer>` in ScoreTracker for efficient score lookups and updates. 
* `ArrayDeque<Move>` in MoveHistory for LIFO move storage and retrieval.

## 11 - Design a Blackjack Game.

**1. Blackjack game flow: dealing, player turns (hit/stand), dealer turn (hit until 17), determining the winner, 
and settling bets.**
* The activity diagram is a great way to visualize this flow.

**2. What are the core classes needed for the Blackjack game?**
* BlackJackGame, Player (interface), RealPlayer, DealerPlayer, Hand, Deck, Card, Rank (enum), Suit (enum).

**3. Purpose of the Card class?**
* Represents a single playing card with Rank and Suit. It's immutable.

**4. Why use enums for Rank and Suit?**
* Type-safe, readable, and maintainable. Enums ensure valid card values and suits, avoiding errors.

**5. Responsibilities of the Deck class?**
* Manages the collection of cards, shuffles, deals cards, and tracks remaining cards.

**6. Why use a Player interface? What are the benefits?**
* Abstraction for different player types (RealPlayer, DealerPlayer). Promotes extensibility and polymorphism.

**7. What does the Hand class do?**
* Manages a player's cards, calculates possible hand values (especially with Aces), and determines if the hand is bust.

**8. How does the code handle Aces (values of 1 or 11)?**
* The Hand class pre-calculates all possible hand values, considering Aces as both 1 and 11.

**9. Role of the BlackJackGame class?**
* Orchestrates the entire game: manages players, dealer, deck, turn order, and determines the winner.

**10. What are the different phases of the game?**
* STARTED, BET_PLACED, INITIAL_CARD_DRAWN, and END.

**11. How to decouple player and dealer decision-making?**
* Use the Strategy Pattern with a PlayerDecisionLogic interface and concrete implementations 
(RealPlayerDecisionLogic, DealerDecisionLogic).

**12. What is the Strategy Pattern and how is it used in this design?**
* Defines a family of algorithms, encapsulates each one, and makes them interchangeable. 
* Used to allow different players (human or dealer) to have different decision-making strategies.

***

## 12 - Design a Shipping Locker System.

















