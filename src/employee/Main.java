package employee;

import java.util.Scanner;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Employee employee = new Employee();

        System.out.println("Enter employee details:");

        while (true) {
            System.out.print("First Name: ");
            String firstName = input.nextLine();
            employee.setFirstName(firstName);
             break;
        }

        while (true) {
            System.out.print("Last Name: ");
            String lastName = input.nextLine();
            employee.setLastName(lastName);
            break;
        }

        while (true) {
            System.out.print("Age: ");
            if (input.hasNextInt()) {
                int age = input.nextInt();
                employee.setAge(age);
                if (employee.getAge() != 0) break;
            } else {
                System.out.println("Invalid input. Age must be a number.");
                input.next();
            }
        }

        while (true) {
            System.out.print("Email: ");
            String email = input.next();
            employee.setEmail(email);
            if (employee.getEmail() != null) break;

        }

        while (true) {
            Scanner num = new Scanner(System.in);
            System.out.print("Country Code (e.g., US, IN, UK): ");
            String countryCode = num.nextLine().toUpperCase();

            System.out.print("Phone Number: ");
            String phoneNumber = num.nextLine();

            employee.setNumber(countryCode, phoneNumber);
            if (employee.getNumber() != 0) break;
        }
        while (true) {
            System.out.println("enter the permanent address: ");
            employee.getAddress().setPermanentAddress(input.nextLine());
            break;
        }

        while (true) {
            System.out.print("enter the temporary address: ");
            employee.getAddress().setTemporaryAddress(input.nextLine());
            break;
        }



        while (true) {
            System.out.print("Department: ");
            String department = input.nextLine();
            employee.setDepartment(department);
            if (employee.getDepartment() != null) break;
        }

        while (true) {
            System.out.print("ID: ");
            if (input.hasNextInt()) {
                int id = input.nextInt();
                employee.setId(id);
                if (employee.getId() != 0) break;
            } else {
                System.out.println("Invalid input. ID must be numeric.");
                input.next();
            }
        }
        System.out.println("\nEmployee Details:");
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("Last Name: " + employee.getLastName());
        System.out.println("Age: " + employee.getAge());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Phone Number: " + employee.getNumber());
        System.out.println("permanent address: " + employee.getAddress().getPermanentAddress());
        System.out.println("temporary address: " + employee.getAddress().getTemporaryAddress());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("ID: " + employee.getId());
    }
}