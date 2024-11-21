package employee;

import java.util.Scanner;

// Press ⇧ twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Employee employee = new Employee();

        System.out.println("Enter employee details:");

        employee.setFirstName(getValidInput(input,"First Name:","[a-zA-Z]+", "Only alphabets are allowed."));

        employee.setLastName(getValidInput(input,"Last Name: ","[a-zA-Z]+","Only alphabets are allowed."));

        employee.setAge(Integer.parseInt(getValidInput(input,"Age: ","\\d+","Age must be a number")));
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

        employee.setEmail(getValidInput(input,"Email: ",".+","email cannot be empty"));

//        while (true) {
//            System.out.print("Email: ");
//            String email = input.nextLine();
//            employee.setEmail(email);
//            if (employee.getEmail() != null) break;
//
//        }

        while (true) {
            System.out.print("Country Code (e.g., US, IN, UK): ");
            String countryCode = input.nextLine().toUpperCase();

            System.out.print("Phone Number: ");
            String phoneNumber = input.nextLine();

            employee.setNumber(countryCode, phoneNumber);
            if (employee.getNumber() != 0) break;
        }
        employee.getAddress().setPermanentAddress(getInput(input,"Permanent Address: "));
//        while (true) {
////            Scanner address= new Scanner(System.in);
//            System.out.println("enter the permanent address: ");
//            employee.getAddress().setPermanentAddress(input.nextLine());
//            break;
//        }

        employee.getAddress().setTemporaryAddress(getInput(input,"Temporary Address: "));


//        while (true) {
//            Scanner address2 = new Scanner(System.in);
//            System.out.print("enter the temporary address: ");
//            employee.getAddress().setTemporaryAddress(address2.nextLine());
//            break;
//        }

        employee.setDepartment(getInput(input,"Department: "));


//        while (true) {
//            System.out.print("Department: ");
//            String department = input.nextLine();
//            employee.setDepartment(department);
//            if (employee.getDepartment() != null) break;
//        }

        employee.setId(Integer.parseInt(getValidInput(input,"ID: ","\\d+","ID must be numeric")));

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
        System.out.println("Phone Number: " + employee.getNumber());
        System.out.println("permanent address: " + employee.getAddress().getPermanentAddress());
        System.out.println("temporary address: " + employee.getAddress().getTemporaryAddress());
        System.out.println("Department: " + employee.getDepartment());
        System.out.println("ID: " + employee.getId());
    }

    public static String getInput(Scanner input, String title) {
        System.out.print(title + "");
        return input.nextLine();

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