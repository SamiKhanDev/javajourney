package database;

import employee.Employee;
import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/Employee_details";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static DatabaseConnector instance;

    public DatabaseConnector(){}

    public static synchronized DatabaseConnector getInstance(){
        if(instance==null){
            instance = new DatabaseConnector();
        }
        return instance;
    }

//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to the database");
        }
    }

    public void insertEmployee(Employee employee) {
        String sql = """
        INSERT INTO employee (id, first_name, last_name, email, department, age, dob, permanent_address, temporary_address,phone_number)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE
        first_name = VALUES(first_name),
        last_name = VALUES(last_name),
        email = VALUES(email),
        department = VALUES(department),
        age = VALUES(age),
        dob = VALUES(dob),
        permanent_address = VALUES(permanent_address),
        temporary_address = VALUES(temporary_address),
        phone_number = VALUES(phone_number)
        """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getEmail());
            statement.setString(5, employee.getDepartment());
            statement.setInt(6, employee.getAge());
            statement.setString(7, employee.getDob());
            statement.setString(8, employee.getAddress().getPermanentAddress());
            statement.setString(9, employee.getAddress().getTemporaryAddress());
            statement.setLong(10, employee.getNumber());


            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("New employee data inserted successfully!");
            } else if (rowsAffected > 1) {
                System.out.println("Existing employee data updated successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeField(Employee employee, String fieldName, String newValue) {
        String sql = "";
        switch (fieldName.toLowerCase()) {
            case "first_name":
                sql = "UPDATE employee SET first_name = ? WHERE id = ?";
                break;
            case "last_name":
                sql = "UPDATE employee SET last_name = ? WHERE id = ?";
                break;
            case "email":
                sql = "UPDATE employee SET email = ? WHERE id = ?";
                break;
            case "department":
                sql = "UPDATE employee SET department = ? WHERE id = ?";
                break;
            case "age":
                sql = "UPDATE employee SET age = ? WHERE id = ?";
                break;
            case "dob":
                sql = "UPDATE employee SET dob = ? WHERE id = ?";
                break;
            case "permanent_address":
                sql = "UPDATE employee SET permanent_address = ? WHERE id = ?";
                break;
            case "temporary_address":
                sql = "UPDATE employee SET temporary_address = ? WHERE id = ?";
                break;
            case "phone_number":
                sql = "UPDATE employee SET phone_number = ? WHERE id = ?";
                break;
            default:
                System.out.println("Invalid field name");
                return;
        }


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newValue);
            statement.setInt(2, employee.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println(fieldName + " updated successfully!");
            } else {
                System.out.println("Employee ID not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployeeField(int employeeId){
        String sql = "delete from employee where id=?";


        try (Connection connection=getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,employeeId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected==1){
                System.out.println("Employee with ID " + employeeId + " deleted Successfully" );
            }else{
                System.out.println(employeeId + " was not found");
            }




        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    public void fetchEmployees() {
        String sql = "SELECT * FROM employee";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("First Name: " + resultSet.getString("first_name"));
                System.out.println("Last Name: " + resultSet.getString("last_name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Phone: " + resultSet.getString("phone_number"));
                System.out.println("Department: " + resultSet.getString("department"));
                System.out.println("age: " + resultSet.getInt("age"));
                System.out.println("DOB : " + resultSet.getString("DOB"));


                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
