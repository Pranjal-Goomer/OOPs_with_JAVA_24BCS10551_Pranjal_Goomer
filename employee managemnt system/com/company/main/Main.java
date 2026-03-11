package com.company.main;

import com.company.employee.Employee;
import com.company.exception.EmployeeNotFoundException;
import com.company.exception.InvalidSalaryException;
import com.company.service.EmployeeService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Employee ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Department: ");
                        String department = scanner.nextLine();

                        System.out.print("Enter Salary: ");
                        double salary = scanner.nextDouble();
                        scanner.nextLine();

                        Employee employee = new Employee(id, name, department, salary);
                        service.addEmployee(employee);
                        System.out.println("Employee added successfully.");
                    } catch (InvalidSalaryException | IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        service.displayEmployees();
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter Employee ID to search: ");
                        int searchId = scanner.nextInt();
                        scanner.nextLine();

                        Employee found = service.searchEmployee(searchId);
                        System.out.println("Employee found:");
                        found.displayEmployee();
                    } catch (EmployeeNotFoundException | IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
