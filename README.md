# Student Course Management System

A small console-based Java application for managing students, courses, and enrollments.

Checklist
- Project purpose and entry point
- Entity hierarchy and relationships
- Service layer and in-memory storage
- Console UI flow
- ID generation and error handling

Five key points

1) Project purpose & entry point
- This is a simple student–course management console application (Maven project). The runtime entry point is `src/main/java/org/example/consoleUI/Main.java`, which provides an interactive menu to perform student, course, and enrollment operations.

2) Entity hierarchy and relationships
- Core domain objects are under `src/main/java/org/example/entites`: `Person` is the base class; `Student` extends `Person`. `Course` represents course details and `Enrollment` links a student to a course. Enum values for enrollment state are in `src/main/java/org/example/entites/Enums/Status.java`.

3) Service layer and in-memory data model
- Services live in `src/main/java/org/example/services`: `StudentService` and `CourseService` manage in-memory `List` storage for students and courses; `EnrollmentService` stores enrollments in a `Map<Integer, List<Enrollment>>` keyed by `studentId`. Services implement business rules (add, find, list, modify status).

4) Console UI flow and interaction pattern
- `Main` creates service instances and runs a looped interactive menu. Typical flow: user selects a menu → UI gathers inputs → UI calls a service method (e.g., add student, enroll) → service returns an object or throws an exception → UI prints the result or error.

5) ID handling, validation, and error flow
- IDs are assigned by `src/main/java/org/example/utility/IdGenerator.java` (simple incremental counters). Services validate operations and throw domain exceptions from `src/main/java/org/example/exceptions` (e.g., `EmptyContentException`, `EntityNotFoundException`, `UnActiveEntityException`). All data is in-memory for the app lifecycle (no persistence), so state resets each run.

Next steps
- Run the app with `mvn -q exec:java -Dexec.mainClass="org.example.consoleUI.Main"` or build the jar with `mvn package` and run the produced jar. Adjust commands to your environment as needed.

---


Q: Why did you use `ArrayList` instead of an array?
- `ArrayList` provides dynamic resizing, convenient APIs (add, remove, contains, iteration), and works well with Java Collections utilities; it avoids manual resizing and index-management that arrays require. In this project we need flexible in-memory collections for students and courses, so `ArrayList` keeps the service implementations simple and readable.

Q: Where did you use `static` members and why?
- `IdGenerator` uses `static` counters and static methods (`getStudentId`, `getCourseId`, `getEnrollmentId`) so IDs are shared across the application without creating an instance. This provides a simple global, process-wide ID source for in-memory objects during the app run.

Q: Where did you use inheritance and what did you gain from it?
- `Student` extends `Person` (inheritance is in `src/main/java/org/example/entites/Student.java` and `Person.java`). This lets `Student` reuse common fields and methods (id, first/last name, email) and keeps shared behavior centralized in `Person`, reducing duplication and improving maintainability.
