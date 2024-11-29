package employee;

import database.DatabaseConnector;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PhoneValidationHelper helper = new PhoneValidationHelper();
        DatabaseConnector db = DatabaseConnector.getInstance();

        while (true) {
            log.info("\nChoose an option:\n1. Add New Employee\n2. View Employee Table\n3. Update Employee\n4. Delete Employee\n5. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    addNewEmployee(input, helper, db);
                    break;

                case 2:
                   log.info("Employee Table:");
                    db.fetchEmployees();
                    break;

                case 3:
                    log.info("Enter the ID of the employee to update:");
                    int employeeIdToUpdate = Integer.parseInt(input.nextLine());

                   log.info("Which field would you like to update?");
                    log.info("Options: first_name, last_name, email, department, age, dob, permanent_address, temporary_address");
                    String fieldToUpdate = input.nextLine().toLowerCase();

                    log.info("Enter the new value for " + fieldToUpdate + ": ");
                    String newValue = input.nextLine();

                    Employee employeeToUpdate = new Employee();
                    employeeToUpdate.setId(employeeIdToUpdate);
                    db.updateEmployeeField(employeeToUpdate, fieldToUpdate, newValue);
                    break;

                case 4:
                   log.info("Enter the ID of the employee to delete:");
                    int employeeIdToDelete = Integer.parseInt(input.nextLine());
                    db.deleteEmployeeField(employeeIdToDelete);
                    break;

                case 5:
                   log.info("Exit");
                    return;

                default:
                   log.log(Level.WARNING,"Invalid choice");
            }
        }
    }

    public static void addNewEmployee(Scanner input, PhoneValidationHelper helper, DatabaseConnector db) {
        Employee employee = new Employee();

        log.info("\nEnter employee details:");
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
            log.info("DOB (yyyy-MM-dd): ");
            String dob = input.nextLine();
            if (employee.setDob(dob)) {
                break;
            }
        }

        while (true) {
           log.info("Country Code (e.g., PK): ");
            String countryCode = input.nextLine().toUpperCase();

            if (!countryCode.equals("PK")) {
                log.log(Level.WARNING,"Only PK country code is supported.");
                continue;
            }

           log.info("Phone Number: ");
            String phoneNumber = input.nextLine();

            if (helper.isValidPhoneNumber(countryCode, phoneNumber)) {
                employee.setNumber(countryCode, phoneNumber);
                break;
            } else {
                log.log(Level.WARNING,"Invalid phone number format for " + countryCode + ". Please try again.");
            }
        }

        db.insertEmployee(employee);
       log.info("Employee added successfully!");
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
            log.log(Level.WARNING,"Invalid input. " + errorMessage);
        }
    }
}
