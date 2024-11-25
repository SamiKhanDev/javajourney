package employee;

import database.DatabaseConnector;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {
        Employee employee = new Employee();
        Scanner input = new Scanner(System.in);
        PhoneValidationHelper helper = new PhoneValidationHelper();
        DatabaseConnector db = new DatabaseConnector();
        System.out.println("Enter employee details:");
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

        employee.setId(Integer.parseInt(getValidInput(input, "ID: ", "\\d+", "ID must be numeric.")));
        employee.setFirstName(getValidInput(input, "First Name: ", "[a-zA-Z]+", "Only alphabets are allowed."));
        employee.setLastName(getValidInput(input, "Last Name: ", "[a-zA-Z]+", "Only alphabets are allowed."));
        employee.setAge(Integer.parseInt(getValidInput(input, "Age: ", "\\d+", "Age must be a number.")));
        employee.setDepartment(getInput(input,"Department: "));
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


//        while (true) {
//            System.out.print("Age: ");
//            if (input.hasNextInt()) {
//                int age = input.nextInt();
//                input.nextLine();
//                employee.setAge(age);
//                if (employee.getAge() != 0) break;
//            } else {
//                System.out.println("Invalid input. Age must be a number.");
//                input.nextLine();
//            }
//        }

//        employee.setEmail(getValidInput(input,"Email: ",".+","email cannot be empty"));

//        while (true) {
////            Scanner address= new Scanner(System.in);
//            System.out.println("enter the permanent address: ");
//            employee.getAddress().setPermanentAddress(input.nextLine());
//            break;
//        }

//        while (true) {
//            Scanner address2 = new Scanner(System.in);
//            System.out.print("enter the temporary address: ");
//            employee.getAddress().setTemporaryAddress(address2.nextLine());
//            break;
//        }

//        while (true) {
//            System.out.print("Department: ");
//            String department = input.nextLine();
//            employee.setDepartment(department);
//            if (employee.getDepartment() != null) break;
//        }

//        while (true) {
//            System.out.print("ID: ");
//            if (input.hasNextInt()) {
//                int id = input.nextInt();
//                input.nextLine();
//                employee.setId(id);
//                if (employee.getId() != 0) break;
//            } else {
//                System.out.println("Invalid input. ID must be numeric.");
//                input.next();
//            }
//        }
        System.out.println("\nEmployee Details:");
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("Last Name: " + employee.getLastName());
        System.out.println("Age: " + employee.getAge());
        System.out.println("Email: " + employee.getEmail());
//        System.out.println("Phone Number: " + employee.getNumber());
        System.out.println("permanent address: " + employee.getAddress().getPermanentAddress());
        System.out.println("temporary address: " + employee.getAddress().getTemporaryAddress());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("ID: " + employee.getId());

        db.insertEmployee(employee);
        System.out.println("Employee inserted successfully. Would you like to update a field? (yes/no)");
        String updateChoice = input.nextLine();
        if (updateChoice.equalsIgnoreCase("yes")) {
            System.out.println("Enter the ID of the employee to update:");
            int employeeIdToUpdate = Integer.parseInt(input.nextLine());

            System.out.println("Which field would you like to update?");
            System.out.println("Options: first_name, last_name, email, department, age, dob, permanent_address, temporary_address");
            String fieldToUpdate = input.nextLine().toLowerCase();

            System.out.println("Enter the new value for " + fieldToUpdate + ": ");
            String newValue = input.nextLine();

            employee.setId(employeeIdToUpdate);

            db.updateEmployeeField(employee, fieldToUpdate, newValue);
        }

        System.out.println("\nFetching employee from the database");
        db.fetchEmployees();
    }



    public static String getInput(Scanner input, String title) {
        while (true) {
            System.out.print(title + "");
            return input.nextLine();
        }
    }

    public static String getValidInput(Scanner input, String title, String regex, String errorMessage) {
        while (true) {
            System.out.print(title + "");
            String value = input.nextLine();
            if (value.matches(regex)) {
                return value;
            }
            System.out.println("invalid input " + errorMessage);


        }

    }
}