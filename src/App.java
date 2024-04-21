import java.util.*;
import java.lang.System;
public class App {
    // Course database
    static class Course {
        String code;
        String title;
        String description;
        int capacity;
        int registeredStudents;
        String schedule;

        Course(String code, String title, String description, int capacity, String schedule) {
            this.code = code;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.registeredStudents = 0;
            this.schedule = schedule;
        }
    }

    // Student database
    static class Student {
        String id;
        String name;
        List<Course> registeredCourses;

        Student(String id, String name) {
            this.id = id;
            this.name = name;
            this.registeredCourses = new ArrayList<>();
        }
        void dropCourse(Course course) {
            if (registeredCourses.contains(course)) {
                registeredCourses.remove(course);
                course.registeredStudents--;
                System.out.println("Successfully dropped the course: " + course.code);
            } else {
                System.out.println("Sorry, you are not registered for this course.");
            }
        }
    }
    // Course Listing
    static void displayCourseListing(List<Course> courses) {
        System.out.printf("\n%-10s %-30s %-30s %-10s %-10s%n", "Code", "Title", "Description", "Capacity", "Schedule");
        System.out.println("-------------------------------------------------------------------------------------");
        for (Course course : courses) {
            System.out.printf("%-10s %-30s %-30s %-10d %-10s%n", course.code, course.title, course.description, course.capacity - course.registeredStudents, course.schedule);
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println();
    }

    // Student Registration
    static void registerStudent(Student student, List<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        Course course = null;
        for (Course c : courses) {
        if (c.code.equals(courseCode)) {
        course = c;
        break;
        }
        }
        if (course != null) {
            if (course.registeredStudents < course.capacity) {
                student.registeredCourses.add(course);
                course.registeredStudents++;
                System.out.println("Successfully registered for the course: " + course.code);
            } else {
                System.out.println("Sorry, the course is full. Registration failed.");
            }                         
        } else {
            System.out.println("Sorry, the course does not exist.");
        }    
    }

    static void dropStudent(Student student, List<Course> courses) {
        System.out.print("Enter course code: ");
        Scanner scanner = new Scanner(System.in);
        String courseCode = scanner.nextLine();
        Course course = null;
        for (Course c : courses) {
            if (c.code.equals(courseCode)) {
                course = c;
                break;
            }
        }
        if (course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Sorry, the course does not exist.");
        }
    }


    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("****  STUDENT COURSE REGISTRATION SYSTEM  ****");
        System.out.println("Below you can find the list of available courses.");
        System.out.println("Please enter your valid student id and select the course you want to register into.");
        System.out.println("If the course capacity is already full you will be notified that the registration has failed.");
        System.out.println("And you can continue registering into other course.");
        System.out.println("-------------------------------------------------------------------------------------");

        // Initialize course database
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("MATH101", "Calculus I", "Learn differential calculus", 40, "TR-SA 11:00-12:00"));
        courses.add(new Course("PH102", "Physics", "Learn the basics of physics", 30, "TU-F 1:00-2:00"));
        courses.add(new Course("CH103", "Chemistry", "Learn the basics of chemistry", 30, "M-W-F 3:00-40:00"));
        courses.add(new Course("LE104", "English", "Learn the avanced english", 30, "M-SA 8:00-9:00"));
        courses.add(new Course("CS105", "Computer Science", "Learn the basics of computer", 30, "M-W-F 9:00-10:00"));

        // Initialize student database
        List<Student> students = new ArrayList<>();
        students.add(new Student("20NE01", "Ariel"));
        students.add(new Student("20NE02", "Becca"));     
        students.add(new Student("20NE03", "Charlie"));    
        students.add(new Student("20NE04", "Daphne")); 
        students.add(new Student("20NE05", "Estelle"));     

        // Course Listing
        displayCourseListing(courses);

        // Student Registration
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        Student student = null;
        for (Student s : students) {
            if (s.id.equals(studentId)) {
                student = s;
                break;
            }
        }
        
        if (student != null) {
            while(true){
                System.out.println("\nOPTIONS: \n1.Register for a Course \n2.Drop a Course \n3.Exit");
                System.out.print("Enter your choice code: ");
                int ch = scanner.nextInt();
                switch(ch){
                    case 1:registerStudent(student,courses);
                            break;
                    case 2:dropStudent(student, courses);
                            break;
                    case 3:System.exit(0);
                    default:System.out.println("Invalid choice! Please enter choice between 1 to 3.");
                } 
        }
    }
        else {
            System.out.println("Sorry, the mentioned student id is not registered !!");
        }
        scanner.close();
    }
   
}
