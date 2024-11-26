package employee;

import database.DatabaseConnector;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PhoneValidationHelper helper = new PhoneValidationHelper();
        DatabaseConnector db = new DatabaseConnector();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add New Employee");
            System.out.println("2. View Employee Table");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    addNewEmployee(input, helper, db);
                    break;

                case 2:
                    System.out.println("Employee Table:");
                    db.fetchEmployees();
                    break;

                case 3:
                    System.out.println("Enter the ID of the employee to update:");
                    int employeeIdToUpdate = Integer.parseInt(input.nextLine());

                    System.out.println("Which field would you like to update?");
                    System.out.println("Options: first_name, last_name, email, department, age, dob, permanent_address, temporary_address");
                    String fieldToUpdate = input.nextLine().toLowerCase();

                    System.out.println("Enter the new value for " + fieldToUpdate + ": ");
                    String newValue = input.nextLine();

                    Employee employeeToUpdate = new Employee();
                    employeeToUpdate.setId(employeeIdToUpdate);
                    db.updateEmployeeField(employeeToUpdate, fieldToUpdate, newValue);
                    break;

                case 4:
                    System.out.println("Enter the ID of the employee to delete:");
                    int employeeIdToDelete = Integer.parseInt(input.nextLine());
                    db.deleteEmployeeField(employeeIdToDelete);
                    break;

                case 5:
                    System.out.println("Exit");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void addNewEmployee(Scanner input, PhoneValidationHelper helper, DatabaseConnector db) {
        Employee employee = new Employee();

        System.out.println("\nEnter employee details:");
        employee.setId(Integer.parseInt(getValidInput(input, "ID: ", "\\d+", "ID must be numeric.")));
        employee.setFirstName(getValidInput(input, "First Name: ", "[a-zA-Z]+", "Only alphabets are allowed."));
        employee.setLastName(getValidInput(input, "Last Name: ", "[a-zA-Z]+", "Only alphabets are allowed."));
        employee.setAge(Integer.parseInt(getValidInput(input, "Age: ", "\\d+", "Age must be a number.")));
        employee.setDepartment(getInput(input, "Department: "));
        employee.getAddress().setPermanentAddress(getInput(input, "Permanent Address: "));
        employee.getAddress().setTemporaryAddress(getInput(input, "Temporary Address: "));

        while (true) {
            System.out.print("Email: ");
            String email = input.nextLine();
            employee.setEmail(email);
            if (employee.getEmail() != null) break;
        }

        while (true) {
            System.out.print("DOB (yyyy-MM-dd): ");
            String dob = input.nextLine();
            if (employee.setDob(dob)) {
                break;
            }
        }

        while (true) {
            System.out.print("Country Code (e.g., PK): ");
            String countryCode = input.nextLine().toUpperCase();

            if (!countryCode.equals("PK")) {
                System.out.println("Only PK country code is supported.");
                continue;
            }

            System.out.print("Phone Number: ");
            String phoneNumber = input.nextLine();

            if (helper.isValidPhoneNumber(countryCode, phoneNumber)) {
                employee.setNumber(countryCode, phoneNumber);
                break;
            } else {
                System.out.println("Invalid phone number format for " + countryCode + ". Please try again.");
            }
        }

        db.insertEmployee(employee);
        System.out.println("Employee added successfully!");
    }

    public static String getInput(Scanner input, String title) {
        System.out.print(title);
        return input.nextLine();
    }

    public static String getValidInput(Scanner input, String title, String regex, String errorMessage) {
        while (true) {
            System.out.print(title);
            String value = input.nextLine();
            if (value.matches(regex)) {
                return value;
            }
            System.out.println("Invalid input. " + errorMessage);
        }
    }
}
