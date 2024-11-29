package employee;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Employee {
    private static final Logger log = Logger.getLogger(Employee.class.getName());
    public String getDob() {
        return dob;
    }



    public boolean setDob(String dob) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfBirth = LocalDate.parse(dob, formatter);

            if (dateOfBirth.isAfter(LocalDate.now())) {
                log.log(Level.WARNING,"Invalid date of birth. Date cannot be in the future.");
                return false;
            } else {
                this.dob = dob;
           log.log(Level.INFO,"Date of birth set successfully.");
                return true;
            }
        } catch (DateTimeParseException e) {
            log.log(Level.SEVERE,"Invalid date format. Please use yyyy-MM-dd.");
            return false;
        }
    }



    private String dob;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private long number;
    private String department;
    private int id;
    private  Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee(){
        this.address= new Address();
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        if(salary>=25000) {
            this.Salary = salary;
        }
        else {
            System.out.println("Invalid amount");
        }
    }


    private int Salary;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && !firstName.trim().isEmpty()&& firstName.matches("[a-zA-Z]+")) {
            this.firstName = firstName;
        }
      else {
           log.log(Level.WARNING,"Invalid first name");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.trim().isEmpty()&& lastName.matches("[a-zA-Z]+")) {
            this.lastName = lastName;
        } else {
            log.log(Level.WARNING,"Invalid last name");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 18 && age < 65) {
            this.age = age;
        } else {
            log.log(Level.WARNING,"Invalid age. Age should be between 18 and 65.");
        }
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        String regex = getRegexFromProperties("email.regex");
        if (regex != null && email != null && email.matches(regex)) {
            this.email = email;
        } else {
            log.log(Level.WARNING,"Invalid email format.");
        }
    }

    private String getRegexFromProperties(String key) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("employee/phone_validation.properties")) {
            if (input == null) {
               log.log(Level.WARNING,"Unable to find config.properties");
                return null;
            }
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
           log.log(Level.SEVERE,"Error loading properties file.");
            return null;
        }
    }



    public long getNumber() {
        return number;
    }
    public void setNumber(String countryCode, String number) {
        PhoneValidationHelper helper = new PhoneValidationHelper();

        if (!helper.isValidPhoneNumber(countryCode, number)) {
            log.log(Level.WARNING,"Invalid phone number. It must match the country-specific format.");
            this.number = 0;
            return;
        }

        // Strip country code for storage
        if (number.startsWith("+92")) {
            number = number.substring(3);
        } else if (number.startsWith("92")) {
            number = number.substring(2);
        } else if (number.startsWith("0")) {
            number = number.substring(1);
        }

        this.number = Long.parseLong(number.replaceAll("\\D", ""));
    }

//    public void setNumber(String countryCode, String number) {
//        PhoneValidationHelper helper = new PhoneValidationHelper();
//
//        String prefix = helper.getPhonePrefix(countryCode);
//        int requiredLength = helper.getPhoneNumberLength(countryCode);
//
//        if (prefix == null || requiredLength == -1) {
//            System.out.println("Invalid country code.");
//            this.number = 0;
//            return;
//        }
//        String normalizeNumber= number.replaceAll("\\s+","");
//        if(!normalizeNumber.startsWith(prefix)){
//            System.out.println("Invalid phone number. It must match the country-specific format.");
//            this.number = 0;
//        }
//        String numberWithoutPrefix = normalizeNumber.substring(prefix.length());
//        if(numberWithoutPrefix.length()==requiredLength&& numberWithoutPrefix.matches("\\d+")){
//            this.number =Long.parseLong(numberWithoutPrefix);
//        }else {
//            System.out.println("Invalid phone number. It must match the country-specific format.");
//            this.number = 0;
//        }
//
////        String pattern = "^" + prefix + "\\d{" + requiredLength + "}$";
////        if (number.matches(pattern)) {
////            this.number = Long.parseLong(number.replaceAll("\\D", ""));
////        } else {
////            System.out.println("Invalid phone number. It must match the country-specific format.");
////            this.number = 0;
////        }
//    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
            this.department = department;
        } else {
            log.log(Level.WARNING,"Invalid department");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
         log.log(Level.WARNING,"Invalid ID");
        }
    }

}
