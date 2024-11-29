package employee;

import database.DatabaseConnector;

import java.util.Scanner;
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
            int choice;
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                log.warning("Invalid choice. Please enter a numeric value.");
                continue;
            }

            switch (choice) {
                case 1:
                    addNewEmployee(input, helper, db);
                    break;
                case 2:
                    log.info("Employee Table:");
                    db.fetchEmployees();
                    break;
                case 3:
                    updateEmployee(input, db);
                    break;
                case 4:
                    deleteEmployee(input, db);
                    break;
                case 5:
                    log.info("Exiting the application.");
                    return;
                default:
                    log.warning("Invalid choice. Please select a valid option.");
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
            log.info("Enter Email: ");
            String email = input.nextLine();
            employee.setEmail(email);
            if (employee.getEmail() != null) break;
        }

        while (true) {
            log.info("Enter DOB (yyyy-MM-dd): ");
            String dob = input.nextLine();
            if (employee.setDob(dob)) {
                break;
            }
        }

        while (true) {
            log.info("Enter Country Code (e.g., PK): ");
            String countryCode = input.nextLine().toUpperCase();

            if (!countryCode.equals("PK")) {
                log.warning("Only PK country code is supported.");
                continue;
            }

            log.info("Enter Phone Number: ");
            String phoneNumber = input.nextLine();

            if (helper.isValidPhoneNumber(countryCode, phoneNumber)) {
                employee.setNumber(countryCode, phoneNumber);
                break;
            } else {
                log.warning("Invalid phone number format for " + countryCode + ". Please try again.");
            }
        }

        db.insertEmployee(employee);
        log.info("Employee added successfully!");
    }

    public static void updateEmployee(Scanner input, DatabaseConnector db) {
        log.info("Enter the ID of the employee to update:");
        int employeeIdToUpdate;
        try {
            employeeIdToUpdate = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            log.warning("Invalid ID. Please enter a numeric value.");
            return;
        }

        log.info("Which field would you like to update?\nOptions: first_name, last_name, email, department, age, dob, permanent_address, temporary_address");
        String fieldToUpdate = input.nextLine().toLowerCase();

        log.info("Enter the new value for " + fieldToUpdate + ": ");
        String newValue = input.nextLine();

        Employee employeeToUpdate = new Employee();
        employeeToUpdate.setId(employeeIdToUpdate);

        db.updateEmployeeField(employeeToUpdate, fieldToUpdate, newValue);
        log.info("Employee updated successfully!");
    }

    public static void deleteEmployee(Scanner input, DatabaseConnector db) {
        log.info("Enter the ID of the employee to delete:");
        int employeeIdToDelete;
        try {
            employeeIdToDelete = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            log.warning("Invalid ID. Please enter a numeric value.");
            return;
        }

        db.deleteEmployeeField(employeeIdToDelete);
        log.info("Employee deleted successfully!");
    }

    public static String getInput(Scanner input, String title) {
        log.info(title);
        return input.nextLine();
    }

    public static String getValidInput(Scanner input, String title, String regex, String errorMessage) {
        while (true) {
            log.info(title);
            String value = input.nextLine();
            if (value.matches(regex)) {
                return value;
            }
            log.warning("Invalid input. " + errorMessage);
        }
    }
}
