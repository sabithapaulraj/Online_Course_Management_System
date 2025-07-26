# Online_Course_Management_System
Low Level Design Implementation of Online Course Management System in Java

## Date: 26-07-2025  

## Problem Statement

Design an **Online Course Management System** that supports:

- User Roles: `Student`, `Instructor`
- Course Creation
- Enrollment
- Assignment Upload & Grading
- Role-Based Access Control


## UML Class Diagram

### Here's the UML Class Diagram of the system:

<img width="392" height="1022" alt="image" src="https://github.com/user-attachments/assets/11e3011e-5251-477b-b525-f4e2ac97e21c" />


# Java Implementation (Low Level Design of Online Course Management System):
```
abstract class User {
    protected int id;
    protected String name;
    protected String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public abstract void login();

    public void viewCourses(java.util.List<Course> courses) {
        System.out.println(name + "'s Courses:");
        for (Course course : courses) {
            System.out.println("- " + course.title);
        }
    }
}

class Student extends User {
    public java.util.List<Course> enrolledCourses = new java.util.ArrayList<>();

    public Student(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public void login() {
        System.out.println("Student " + name + " logged in.");
    }

    public void uploadAssignment(Assignment assignment, String content) {
        assignment.submission = content;
        System.out.println(name + " uploaded assignment: " + assignment.title);
    }

    public void viewGrades() {
        for (Course course : enrolledCourses) {
            for (Assignment a : course.assignments) {
                String gradeStr = (a.grade != null) ? String.valueOf(a.grade.score) : "Not graded";
                System.out.println("Course: " + course.title + " - Assignment: " + a.title + " - Grade: " + gradeStr);
            }
        }
    }
}

class Instructor extends User {
    public java.util.List<Course> createdCourses = new java.util.ArrayList<>();

    public Instructor(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public void login() {
        System.out.println("Instructor " + name + " logged in.");
    }

    public Course createCourse(int id, String title, String description) {
        Course course = new Course(id, title, description, this);
        createdCourses.add(course);
        System.out.println("Course created: " + title);
        return course;
    }

    public void gradeAssignment(Assignment assignment, int score, String feedback) {
        assignment.grade = new Grade(score, feedback);
        System.out.println("Assignment '" + assignment.title + "' graded with score: " + score);
    }
}

class Course {
    public int id;
    public String title;
    public String description;
    public Instructor instructor;
    public java.util.List<Student> students = new java.util.ArrayList<>();
    public java.util.List<Assignment> assignments = new java.util.ArrayList<>();

    public Course(int id, String title, String description, Instructor instructor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.enrolledCourses.add(this);
        System.out.println(student.name + " enrolled in course: " + title);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
        System.out.println("Assignment '" + assignment.title + "' added to course: " + title);
    }
}

class Assignment {
    public String title;
    public String description;
    public String submission;
    public Grade grade;

    public Assignment(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void submit(String content) {
        this.submission = content;
    }

    public Grade getGrade() {
        return grade;
    }
}

class Grade {
    public int score;
    public String feedback;

    public Grade(int score, String feedback) {
        this.score = score;
        this.feedback = feedback;
    }

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }
}

```

## OOP Principles Explained

### Abstraction

We use abstraction by defining a general `User` class to represent common user behavior and properties like `id`, `name`, `email`, and shared methods like `login()` and `viewCourses()`.

Instead of putting all logic in one place, we let `Student` and `Instructor` inherit from `User` and define their own specific actions like `uploadAssignment()` or `createCourse()`.

---

### Encapsulation

All sensitive/internal data like `score`, `feedback`, and `submission` are accessed or modified only through specific methods like `getScore()` or `submit()`.

This protects the internal state of each object and ensures it's only updated in controlled ways.

Rather than allowing direct access to grades or submissions, all updates go through well-defined methods.

---

### Inheritance

Both `Student` and `Instructor` classes inherit from the base `User` class, which means they automatically get shared functionality like `login()` and `viewCourses()`.

They then extend this behavior by adding their own role-specific methods.

This promotes code reuse and helps maintain a clean, scalable structure.

---

### Polymorphism

Polymorphism is demonstrated via method overriding — the `login()` method behaves differently depending on whether it’s called by a `Student` or an `Instructor`.

This lets us use the same method name but implement custom logic for each role.

It allows flexible role handling while maintaining a common interface.

---

## SOLID Design Principles

### S — Single Responsibility Principle

Every class in the system is responsible for a single piece of functionality:

- `User`: basic user properties and actions
- `Student`: viewing grades, submitting assignments
- `Instructor`: creating courses, grading
- `Course`: handling enrollment and assignment logic
- `Grade`: managing scores and feedback

This separation makes each class easier to understand and maintain.

---

### O — Open/Closed Principle

The system is open to extension but closed to modification.

You can add new features (e.g., an `Admin` or `TeachingAssistant` role) by extending existing classes — no need to touch the core logic.

For example, adding support for TAs means just creating a new class.

---

### L — Liskov Substitution Principle

Since `Student` and `Instructor` inherit from `User`, you can use either wherever a `User` is expected.

Substituting subclasses won’t break the system because they conform to the same structure and interface.

---

### I — Interface Segregation Principle

Even without formal interfaces (like in TypeScript or C#), we give each class only the methods it actually needs.

- `Student` doesn’t handle grading.
- `Instructor` doesn’t handle assignment submissions.

This avoids forcing roles to implement irrelevant methods.

---

### D — Dependency Inversion Principle

High-level modules like `Instructor` don’t rely on the low-level implementation details of `Assignment` or `Grade`.

Instead, they interact with them through abstracted methods like `gradeAssignment()`.

This makes the codebase more flexible, testable, and maintainable.
