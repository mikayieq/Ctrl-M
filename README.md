
Ready.Set.Recover: An Interactive Emergency Preparedness System for Climate Awareness and Disaster Response

Description
As the world faces increasing threats from climate change and natural disasters, accessible tools for awareness and preparedness have never been more important. This project presents a Java console-based program called Ready.Set.Recover, designed to raise awareness, provide emergency guidance, and promote disaster preparedness. The program offers interactive features and reliable information to help users act effectively before, during, and after crises. It empowers individuals and communities to build resilience and respond proactively to environmental threats.

OOP Concepts Applied in Ready Set Recover

1. Encapsulation 
Definition: Encapsulation is the bundling of data (attributes) and methods that operate on that data within a single unit (class), while restricting direct access to some components.
Implementation in Our Project:

A. Hospital Class (DuringDisaster.java)
Private fields: name, city, and extra are private, preventing direct external access
Public getters: Controlled access to data through getter methods
Immutability: Final fields ensure data cannot be modified after creation

B. Report Class (DuringDisaster.java)
Data hiding: All fields are private
Controlled modification: Only status can be changed through setter
Data integrity: Constructor ensures all required fields are initialized

C. Survivor Class (AfterDisaster.java)
Private fields: All survivor data is encapsulated
Inner class: Survivor is an inner class, further encapsulating it within AfterDisaster
Controlled display: Data can only be displayed through the display() method

2. Inheritance
Definition: Inheritance allows a class to inherit properties and methods from another class, promoting code reuse and establishing an "is-a" relationship.
Implementation in Our Project:

A. DisasterManager Hierarchy (DuringDisaster.java)
Benefits:
All disaster managers share common CRUD operations (Insert, Search, Display, Delete)
Each disaster type has unique safety suggestions
Code reusability: Common functionality defined once in parent class
Easy to add new disaster types by extending DisasterManager

B. DisasterGuide Hierarchy (AfterDisaster.java)
Benefits:
Each guide type inherits from DisasterGuide
Forces all guides to implement displayGuide() method
Consistent interface for all disaster recovery guides

4.  Polymorphism
Definition: Polymorphism allows objects of different classes to be treated as objects of a common parent class, enabling one interface to be used for different data types.
Implementation in Our Project:

A. Method Overriding (Runtime Polymorphism)
In DuringDisaster.java:
<img width="410" height="603" alt="Screenshot 2025-11-28 155129" src="https://github.com/user-attachments/assets/94a67b5c-db36-4618-b39e-111632c7fb2e" />

Usage in disasterMenu() method:

<img width="578" height="328" alt="Screenshot 2025-11-28 135719" src="https://github.com/user-attachments/assets/cd22cbed-49ef-4c98-ab78-db783bd9c404" />

In AfterDisaster.java:

<img width="534" height="384" alt="Screenshot 2025-11-28 135903" src="https://github.com/user-attachments/assets/814b60ce-7613-462f-9305-1cb5c27fd77c" />

B. Constructor Overloading (Compile-time Polymorphism)
In Hospital class:

<img width="616" height="337" alt="Screenshot 2025-11-28 140049" src="https://github.com/user-attachments/assets/90bd4de0-1a13-4374-a757-d9d6d0e0ed08" />

Benefits of Polymorphism:
Flexibility: One method name, multiple behaviors
Maintainability: Easy to add new disaster types without changing existing code
Code reusability: Common interface for different implementations

4. Abstraction
Definition: Abstraction is the process of hiding complex implementation details and showing only essential features of an object.
Implementation in Our Project:

A. Abstract DisasterManager Class (DuringDisaster.java)
Why this is abstraction:
Cannot create an instance of DisasterManager directly
Provides a blueprint for all disaster-specific managers
Hides the common implementation while exposing the interface
Forces child classes to implement disaster-specific behavior

B. Abstract DisasterGuide Class (AfterDisaster.java)
Benefits:
Defines a contract that all guides must follow
Ensures consistency across different disaster recovery guides
Hides implementation details from the user

C. Interface-like Abstraction (BeforeDisaster.java)

<img width="604" height="264" alt="Screenshot 2025-11-28 140607" src="https://github.com/user-attachments/assets/95ef9bc5-2e69-4359-a25b-beed0664139d" />

5. Exception Handling
Definition: Exception handling is a mechanism to handle runtime errors, maintaining normal application flow even when errors occur.
Implementation in Our Project:

A. Custom Exceptions (DuringDisaster.java)
1. ReportNotFoundException:
   
<img width="633" height="652" alt="Screenshot 2025-11-28 140730" src="https://github.com/user-attachments/assets/11afce02-f985-43da-9a6a-e698317acbea" />

2. InvalidChoiceException:

<img width="613" height="714" alt="Screenshot 2025-11-28 141017" src="https://github.com/user-attachments/assets/f9384ad9-d897-469b-8bdc-99d4b9e22e74" />

B. Custom Exception (AfterDisaster.java)
SurvivorNotFoundException:

<img width="541" height="473" alt="Screenshot 2025-11-28 141140" src="https://github.com/user-attachments/assets/f8003b14-b7eb-4e53-a839-16dd1d8c8462" />

C. Built-in Exception Handling
InputMismatchException (AfterDisaster.java):

<img width="518" height="199" alt="Screenshot 2025-11-28 141342" src="https://github.com/user-attachments/assets/13b9a7cc-54f9-4a8a-a8a0-6985ba7bd0d6" />

Multiple Exception Handling (DuringDisaster.java):

Benefits of Exception Handling:
Robustness: Application doesn't crash on errors
User-friendly: Clear error messages for users
Debugging: Easier to identify and fix issues
Flow control: Separates error-handling code from normal code

6. Collections in Java
Definition: Collections are frameworks that provide architecture to store and manipulate groups of objects.
Implementation in Our Project:

A. Map Collection (DuringDisaster.java)
1. LinkedHashMap for Reports:
Why LinkedHashMap?
Maintains insertion order (important for display)
Fast lookup by Report ID (O(1) complexity)
Easy to iterate through reports

2. TreeMap for Hospital Directory:
Why TreeMap?
Automatically sorts cities alphabetically
Case-insensitive comparison
Efficient searching and retrieval

B. List Collection
1. ArrayList for Hospital Lists:

<img width="606" height="455" alt="Screenshot 2025-11-28 141957" src="https://github.com/user-attachments/assets/b2dba305-452e-4455-9f53-dd17ca1b46fe" />


2. ArrayList for Reports:

<img width="508" height="410" alt="Screenshot 2025-11-28 142050" src="https://github.com/user-attachments/assets/e53a10eb-360d-4d67-b9bd-bcd0884906fd" />

3. Arrays.asList() for Safety Suggestions:

<img width="568" height="512" alt="Screenshot 2025-11-28 142138" src="https://github.com/user-attachments/assets/8a8524dd-222f-4082-8213-e404fe7ead92" />

C. Array Collection (DisasterResponseSystem.java)
Fixed-size Array for Survivors:

<img width="491" height="694" alt="Screenshot 2025-11-28 142341" src="https://github.com/user-attachments/assets/1ce93c96-767d-4707-b849-617aa81198e8" />

D. Advanced Collection Usage
1. AtomicInteger for Thread-safe Counter:

<img width="465" height="140" alt="Screenshot 2025-11-28 161043" src="https://github.com/user-attachments/assets/0a685aa4-1879-4297-8a3c-8e4894aa86aa" />

2. computeIfAbsent for Lazy Initialization:
Benefits of Collections:
Dynamic sizing: Lists grow as needed
Type safety: Generic types prevent runtime errors
Built-in methods: Sort, search, filter operations
Performance: Optimized for different use cases
Flexibility: Easy to switch between collection types

Program Structure Applied in Ready Set Recover
ReadySetRecover (Main Entry Point)
File: ReadySetRecover.java
Role: Central controller and application entry point that coordinates the entire disaster management system.
Responsibilities: Display welcome screen and system information
Manage main menu navigation between disaster phases
Route users to appropriate modules (Before/During/After)
Handle application lifecycle (start/exit)
Coordinate between different disaster management modules

How to Run the Ready Set Recover
Main Menu:
Press 1 for BEFORE (Preparation)
Press 2 for DURING (Emergency Response)
Press 3 for AFTER (Recovery)
Press 0 to Exit
Using BeforeDisaster
Select disaster type (Earthquake, Typhoon, Flood, and Fire)
View preparation guide
Press Enter to continue
Press to return to main menu
Using DuringDisaster
Select disaster type (earthquake, typhoon, flood, or fire).
Choose operation:
1 - Insert new report
2 - Search report
3 - Display all reports
4 - Delete report
5 - Get safety suggestions
0 - Back to main menu
Follow prompts to enter information
Type Y to continue or N to go back
Using AfterDisaster
Choose option:
1 - View recovery guides
2 - Add survivor
3 - Delete survivor
4 - Search survivor
5 - Sort survivors
6 - Display all survivors
0 - Return to main menu
Follow prompts to enter information
Exit the Program
Press 0 from the main menu


Sample output of the Ready Set Recover

<img width="569" height="836" alt="Screenshot 2025-11-28 152401" src="https://github.com/user-attachments/assets/5ea90b5e-7ca2-40c7-800a-b0e8527fe7a5" />
<img width="567" height="791" alt="Screenshot 2025-11-28 152351" src="https://github.com/user-attachments/assets/ba2625c5-f741-471e-8595-a2b2160ceced" />
<img width="1053" height="858" alt="Screenshot 2025-11-28 152341" src="https://github.com/user-attachments/assets/9a8839f1-ed34-4715-be9e-2b96c2ad492b" />
<img width="570" height="798" alt="Screenshot 2025-11-28 152329" src="https://github.com/user-attachments/assets/f28a5e34-88fa-404b-a57d-647ecc374306" />
<img width="746" height="681" alt="Screenshot 2025-11-28 152318" src="https://github.com/user-attachments/assets/01fd07a3-99ee-4e8f-94b1-c86898698571" />
<img width="753" height="802" alt="Screenshot 2025-11-28 152306" src="https://github.com/user-attachments/assets/2e7ee390-cb85-4610-a665-6e2d4f04716b" />
<img width="780" height="690" alt="Screenshot 2025-11-28 152248" src="https://github.com/user-attachments/assets/da1d2ec0-385a-4dd2-b667-06ef9f70378c" />
<img width="521" height="587" alt="Screenshot 2025-11-28 152213" src="https://github.com/user-attachments/assets/f92b3b57-0aeb-4236-aefc-282c750616f9" />
<img width="513" height="614" alt="Screenshot 2025-11-28 152203" src="https://github.com/user-attachments/assets/def70cb5-df7b-4633-b249-2f02b4447d1d" />
<img width="549" height="681" alt="Screenshot 2025-11-28 152426" src="https://github.com/user-attachments/assets/15079e1d-26cc-4af2-ad65-db13bac0d035" />
<img width="575" height="884" alt="Screenshot 2025-11-28 152419" src="https://github.com/user-attachments/assets/e6a93468-273d-4d10-ac3d-06016390cd6c" />
<img width="506" height="802" alt="Screenshot 2025-11-28 152410" src="https://github.com/user-attachments/assets/013c5a75-f574-49de-a3b6-5f24e9cddf20" />

Author and Acknowledgement

Author
Banaag, Mikayla D.
BS Information Technology
Batangas State University- Alangilan Campus
Academic Year: 2025-2026
24-03255@g.batstate-u.edu.ph

Parungao, Martin Ryan D.
BS Information Technology
Batangas State University- Alangilan Campus
Academic Year: 2025-2026
24-08517@g.batstate-u.edu.ph

Torino, Kristal Marie T.
BS Information Technology
Batangas State University- Alangilan Campus
Academic Year: 2025-2026
24-07953@g.batstate-u.edu.ph

Acknowledgements
This project, Ready Set Recover: A Comprehensive Disaster Preparedness and Response Management System, would not have been possible without the support and contributions of the following individuals and organizations:

Academic Guidance
Mr. Juriel Comia- For guidance in Object-Oriented Programming and project development
Java Programming Course - For teaching fundamental and advanced Java concepts including:
Encapsulation and data hiding principles
Inheritance and class hierarchies
Polymorphism and method overriding
Abstraction through abstract classes
Exception handling and custom exceptions
Java Collections Framework (Map, List, ArrayList, LinkedHashMap, TreeMap)

