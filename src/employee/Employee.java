package employee;

public class Employee {
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
            System.out.println("Invalid first name");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && !lastName.trim().isEmpty()&& lastName.matches("[a-zA-Z]+")) {
            this.lastName = lastName;
        } else {
            System.out.println("Invalid last name");
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 18 && age < 65) {
            this.age = age;
        } else {
            System.out.println("Invalid age. Age should be between 18 and 65.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            System.out.println("Invalid email");
        }
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(String countryCode, String number) {
        PhoneValidationHelper helper = new PhoneValidationHelper();

        String prefix = helper.getPhonePrefix(countryCode);
        int requiredLength = helper.getPhoneNumberLength(countryCode);

        if (prefix == null || requiredLength == -1) {
            System.out.println("Invalid country code.");
            this.number = 0;
            return;
        }
        String normalizeNumber= number.replaceAll("\\s+","");
        if(!normalizeNumber.startsWith(prefix)){
            System.out.println("Invalid phone number. It must match the country-specific format.");
            this.number = 0;
        }
        String numberWithoutPrefix = normalizeNumber.substring(prefix.length());
        if(numberWithoutPrefix.length()==requiredLength&& numberWithoutPrefix.matches("\\d+")){
            this.number =Long.parseLong(numberWithoutPrefix);
        }else {
            System.out.println("Invalid phone number. It must match the country-specific format.");
            this.number = 0;
        }

//        String pattern = "^" + prefix + "\\d{" + requiredLength + "}$";
//        if (number.matches(pattern)) {
//            this.number = Long.parseLong(number.replaceAll("\\D", ""));
//        } else {
//            System.out.println("Invalid phone number. It must match the country-specific format.");
//            this.number = 0;
//        }
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department != null && !department.trim().isEmpty()) {
            this.department = department;
        } else {
            System.out.println("Invalid department");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("Invalid ID");
        }
    }

}
