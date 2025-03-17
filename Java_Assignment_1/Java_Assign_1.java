import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Java_Assign_1 {
    //----------------------------------Basic Java---------------------------------------------------------------------------------------------------------------
    //Que 1 Program to Calculate the Area of circle, rectangle and triangle
    // Area of Circle
    public static double Area_Circle(double r) {
        return 3.14 * r * r;
    }

    //Area of rectangle
    public static double Area_Rectangle(double l, double b) {
        return l * b;
    }

    //Area of Triangle
    // Method 1: Area using Base and Height
    public static double areaUsingBaseHeight(double base, double height) {
        return 0.5 * base * height;
    }

    // Method 2: Area using Heron's Formula
    public static double areaUsingHeron(double a, double b, double c) {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    // Method 3: Area using Two Sides and Included Angle
    public static double areaUsingTrigonometry(double a, double b, double angleInDegrees) {
        double angleInRadians = Math.toRadians(angleInDegrees);
        return 0.5 * a * b * Math.sin(angleInRadians);
    }

    //Que 2 Check if the number is even or odd
    public static boolean evenorodd(double num) {
        return num % 2 == 0;
    }

    //Que 3 Factorial of the Given number
    //recursive way
    public static double fact_recur(double num) {
        if (num == 1) return 1;
        return num * fact_recur(num - 1);
    }

    //Iterative way
    public static double fact_Iter(double num) {
        if (num == 1 || num == 0) return 1;
        double ans = 1;
        while (num != 1) {
            ans = ans * num;
            num = num - 1;
        }
        return ans;
    }

    //Que 4 Program to print the fibonacci series
    //recursive way
    public static void fibonacciRecursive(int num, int a, int b) {
        if (num < 0) return;
        System.out.print(a + " ");
        fibonacciRecursive(num - 1, b, a + b);
    }

    //Iterative way
    public static void fibonacciIterative(int n) {
        int a = 0, b = 1, c;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            c = a + b;
            a = b;
            b = c;
        }
        System.out.println();
    }

    //Que 5 Use the loops to print the triangle and square
    // Printing a right-angled triangle pattern
    public static void printTriangle(int rows) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    // Printing a square pattern
    public static void printSquare(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    //----------------------------------------------------------Data Types and Operators-----------------------------------------------------------------------
    // Data Types and Operators Que 1: Difference between primitive and reference types (COMMENT)
    /*
     * Primitive vs Reference Data Types:
     * 1. Storage: Primitives (int, double) hold values directly. References (String, arrays) hold memory addresses.
     * 2. Memory: Primitives in stack. References point to heap memory.
     * 3. Defaults: Primitives have defaults (e.g., int 0). References default to null.
     * 4. Example: int age = 30 (primitive). String name = "Alice" (reference).
     * 5. Operations: Primitives use operators (+, -). References use methods (e.g., .length()).
     *
     * Example of Primitive Data type is  int,float,long etc
     * Example of references data type is String, array etc
     */

    // Data Types Que 2: Demonstrate arithmetic, logical, and relational operators
    public static void demonstrateOperators() {
        int a = 15, b = 4;
        // Arithmetic
        System.out.println("15 + 4 = " + (a + b));
        System.out.println("15 % 4 = " + (a % b));

        // Relational
        System.out.println("15 > 4? " + (a > b));

        // Logical
        boolean x = true, y = false;
        System.out.println("x && y? " + (x && y));
    }

    // Data Types Que 3: Temperature converter
    public static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    //------------------------------------------------------Control flow statement -----------------------------------------------------------------------------
    // Control Flow Que 1: Check if a number is prime using if and else statement
    public static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
            else continue;
        }
        return true;
    }

    // Control Flow Que 2: Find largest of three numbers using the conditional statement
    // there are different type of conditional statement such as if-else, if-else-if, switch-case and ternary operator here i am using the ternary operators
    public static int largestOfThree(int a, int b, int c) {
        return (a >= b && a >= c) ? a : (b >= c) ? b : c;
    }

    // Control Flow Que 3: Multiplication table using for loop
    public static void printMultiplicationTable(int num) {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d x %d = %d\n", num, i, num * i);
        }
    }

    // Control Flow Que 4: Sum even numbers 1-10 using while loop
    public static int sumEven1To10() {
        int sum = 0, i = 1;
        while (i <= 10) {
            if (i % 2 == 0) sum += i;
            i++;
        }
        return sum;
    }

    //----------------------------------------------------------Arrays--------------------------------------------------------------------------------------
    // Arrays Que 1: Average of array elements
    public static double arrayAverage(int[] arr) {
        double sum = 0;
        for (int num : arr) sum += num;
        return sum / arr.length;
    }

    //Using Stream
    public static double arrayAverageUsingStream(int[] arr) {
        return Arrays.stream(arr).average().orElse(0);
    }

    // Arrays Que 2: Bubble sort
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Arrays Que 3: Linear search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    //-----------------------------------------------------------------String Manipulation-------------------------------------------------------------------
    // Que 1: Reverse a given string
    // Iterative way (more efficient for large strings)
    public static String reverseStringIterative(String str) {
        char[] chars = str.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }
    //Another Way of using the StringBuilder (More efficient)
    public static String reverseStringUsingStringBuilder(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Recursive way (demonstration, not recommended for very long strings)
    public static String reverseStringRecursive(String str) {
        if (str.isEmpty()) return str;
        return reverseStringRecursive(str.substring(1)) + str.charAt(0);
    }

    // Que 2: Count number of vowels in a string
    public static int countVowels(String str) {
        int count = 0;
        String lowerStr = str.toLowerCase();
        for (char c : lowerStr.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    // Que 3: Check if two strings are anagrams
    //Time Complexity of this code is o(nlogn) which can be optimize more to o(n) and constant space(0(256)~O(1))
    public static boolean areAnagrams(String str1, String str2) {
        // Early exit if lengths differ
        if (str1.length() != str2.length()) return false;

        // Convert to lowercase and sort characters
        char[] arr1 = str1.toLowerCase().toCharArray();
        char[] arr2 = str2.toLowerCase().toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }
    // O(n) Time Complexity Solution
    public static boolean areAnagrams2(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        int[] arr=new int[256];
        for (int i = 0; i < str1.length(); i++) {
            arr[str1.charAt(i)]++;
            arr[str2.charAt(i)]--;
        }
        for (int j : arr) {
            if (j != 0) return false;
        }
        return true;
    }

    //-----------------------------------------------------------------Advanced Topics---------------------------------------------------------------------------------
    //Question 1: Interface and Abstract Class
// **Definition:**
// Interfaces and abstract classes are used in Java for abstraction.
// - An **interface** defines a contract with abstract methods that implementing classes must follow.
// - An **abstract class** allows both abstract and concrete methods. It serves as a base class for derived classes.
// **Use Cases:**
// - Interfaces allow multiple inheritance.
// - Abstract classes provide partial implementation to subclasses.

    // **Example Implementation:**
// Interface
    interface Vehicle {
        void start();  // Abstract method (no implementation)
        void stop();
    }

    // Abstract Class
    abstract static class Animal {
        String name;

        // Constructor
        Animal(String name) {
            this.name = name;
        }

        // Abstract method (must be implemented by subclasses)
        abstract void makeSound();

        // Concrete method (common functionality)
        void eat() {
            System.out.println(name + " is eating.");
        }
    }

    // Subclass of Animal implementing the abstract method
    static class Dog extends Animal {
        Dog(String name) {
            super(name);
        }

        @Override
        void makeSound() {
            System.out.println(name + " barks.");
        }
    }

    // Class implementing the Vehicle interface
    static class Car implements Vehicle {
        @Override
        public void start() {
            System.out.println("Car is starting...");
        }

        @Override
        public void stop() {
            System.out.println("Car has stopped.");
        }
    }

//  Question 2: Exception Handling
// **Definition:**
// Exception handling ensures that a program does not crash due to unexpected runtime errors.
// - The try block contains code that might cause an error.
// - The catch block handles exceptions if they occur.
// - The finally block executes **regardless of whether an exception occurred or not**.

    // **Example Implementation:**
    class ExceptionHandlingExample {
        public static void divideNumbers(int a, int b) {
            try {
                int result = a / b; // Risky operation (division by zero)
                System.out.println("Result: " + result);
            } catch (ArithmeticException e) {
                // Catch and handle division by zero error
                System.out.println("Error: Division by zero is not allowed.");
            } finally {
                // This block will always execute
                System.out.println("Execution completed.");
            }
        }
    }

//  Question 3: File I/O Operations
// **Definition:**
// File handling in Java allows reading/writing data to files using classes like FileReader and BufferedReader.
// **Use Cases:**
// - Reading configuration files, logs, and external data sources.
// - Writing and storing output data to files.

    // **Example Implementation:**
    class FileOperations {
        public static void readFile(String fileName) {
            // Try-with-resources ensures automatic closure of the file
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("File Content: " + line);
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }

// Question 4: Multithreading
// **Definition:**
// Multithreading allows a program to execute multiple tasks simultaneously, improving performance.
// **Two Ways to Create a Thread:**
// 1**Extending the Thread class**
// 2️ **Implementing the Runnable interface**
// Runnable interface is preferred because it allows multiple inheritance (a class can implement multiple interfaces).

    // **Example 1: Using Thread Class**
    class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " - Count: " + i);
                try {
                    Thread.sleep(500); // Pause execution for 500ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // **Example 2: Using Runnable Interface**
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println(Thread.currentThread().getName() + " - Task Running");
                try {
                    Thread.sleep(500); // Pause execution for 500ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public void main(String[] args) {
        // Creating an object of Student
        Student student1 = new Student();
        student1.name = "Alice";
        student1.className = "10th Grade";
        student1.exams(student1.name, student1.className); // Calls the base class method

        // Creating an object of GraduateStudent
        GraduateStudent gradStudent = new GraduateStudent();
        gradStudent.name = "Bob";
        gradStudent.branch = "Computer Science";
        gradStudent.exams(gradStudent.name, gradStudent.branch); // Calls the overridden method

        // Using Encapsulation - Setting and getting marks
        gradStudent.setMarks(85);
        System.out.println("Bob's marks: " + gradStudent.getMarks());
        // Demonstrating Abstract Class & Interface
        Dog dog = new Dog("Buddy");
        dog.makeSound(); // Calls overridden method in Dog class
        dog.eat(); // Calls inherited method from Animal class

        Car car = new Car();
        car.start();
        car.stop();

        System.out.println("\n=== Question 2: Exception Handling Example ===");
        ExceptionHandlingExample.divideNumbers(10, 0); // Testing division by zero

        System.out.println("\n=== Question 3: File I/O Example ===");
        FileOperations.readFile("sample.txt"); // Ensure this file exists in your project

        System.out.println("\n=== Question 4: Multithreading Example ===");

        // Multithreading using Thread class
        MyThread thread1 = new MyThread();
        thread1.setName("Thread-1");
        thread1.start();

        // Multithreading using Runnable interface
        Thread thread2 = new Thread(new MyRunnable(), "Thread-2");
        thread2.start();

        System.out.println("\nMain thread execution completed.");
    }


}

//--------------------------------------------------------------------------------OOPs----------------------------------------------------------------------
//OOPs Que 1 create the class student and attribute like name,roll_number,marks
// Base class representing a Student
class Student {
    // Attributes of a student
    String name;
    int rollNumber;
    int marks;  // No need to redeclare marks in the child class
    String className;

    // Method to represent a student writing an exam
    public void exams(String name, String className) {
        System.out.println(name + " is writing the exam of " + className + " class.");
    }
}

// Child class GraduateStudent extending Student (Inheritance)
class GraduateStudent extends Student {
    String collegeName;
    String branch;

    // **Encapsulation**: Keeping marks private so it cannot be accessed directly
    private int marks;

    /**
     * **Polymorphism - Method Overriding**:
     * Here, we override the exams method from the Student class.
     * The parameters differ, so this is an example of **Runtime Polymorphism** (Method Overriding).
     */
    @Override
    public void exams(String name, String branch) {
        System.out.println(name + " is writing the exam of " + branch + " branch.");
    }

    /**
     * **Encapsulation**:
     * Since marks is private, we provide getter and setter methods
     * to control access to this variable.
     */
    public void setMarks(int marks) {
        if (marks >= 0 && marks <= 100) {  // Validating marks before setting
            this.marks = marks;
        } else {
            System.out.println("Invalid marks entered.");
        }
    }

    public int getMarks() {
        return marks;
    }
}