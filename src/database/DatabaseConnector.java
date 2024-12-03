package database;

import employee.Address;
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

    public void insertEmployee(Employee employee, Address address, int departmentId) {
        String employeeSql = """
        INSERT INTO employee (id, first_name, last_name, email, department, age, dob, phone_number)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE
        first_name = VALUES(first_name),
        last_name = VALUES(last_name),
        email = VALUES(email),
        department = VALUES(department),
        age = VALUES(age),
        dob = VALUES(dob),
        phone_number = VALUES(phone_number)
    """;

        String addressSql = """
        INSERT INTO address (employee_id, permanent_address, temporary_address)
        VALUES (?, ?, ?)
        ON DUPLICATE KEY UPDATE
        permanent_address = VALUES(permanent_address),
        temporary_address = VALUES(temporary_address)
    """;

        String linkSql = """
        INSERT INTO employee_address_department (employee_id, address_id, department_id)
        VALUES(?,?,?)
    """;

        try (Connection connection = getConnection();
             PreparedStatement employeeStmt = connection.prepareStatement(employeeSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement addressStmt = connection.prepareStatement(addressSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement linkStmt = connection.prepareStatement(linkSql)) {

            // Insert employee data
            employeeStmt.setInt(1, employee.getId());
            employeeStmt.setString(2, employee.getFirstName());
            employeeStmt.setString(3, employee.getLastName());
            employeeStmt.setString(4, employee.getEmail());
            employeeStmt.setString(5, employee.getDepartment());
            employeeStmt.setInt(6, employee.getAge());
            employeeStmt.setString(7, employee.getDob());
            employeeStmt.setLong(8, employee.getNumber());
            employeeStmt.executeUpdate();

            int employee_id = employee.getId();
            if (employee_id == 0) {
                try (ResultSet generatedKeys = employeeStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        employee_id = generatedKeys.getInt(1);
                    }
                }
            }

            // Insert address data
            addressStmt.setInt(1, employee.getId());
            addressStmt.setString(2, address.getPermanentAddress());
            addressStmt.setString(3, address.getTemporaryAddress());
            addressStmt.executeUpdate();

            int address_id = 0;
            try (ResultSet generatedKeys = addressStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    address_id = generatedKeys.getInt(1);
                }
            }

            if (address_id > 0) {
                linkStmt.setInt(1, employee_id);
                linkStmt.setInt(2, address_id);
                linkStmt.setInt(3, departmentId);
                linkStmt.executeUpdate();
            }

            System.out.println("Employee and address data inserted/updated successfully!");

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
        String sql = """
       SELECT e.id, e.first_name, e.last_name, e.email, e.age, e.dob, e.phone_number,
              a.permanent_address, a.temporary_address,\s
              GROUP_CONCAT(d.name) AS departments
       FROM employee e
       LEFT JOIN address a ON e.id = a.employee_id
       LEFT JOIN employee_address_department ead ON e.id = ead.employee_id
       LEFT JOIN department d ON ead.department_id = d.id
       GROUP BY e.id, e.first_name, e.last_name, e.email, e.age, e.dob, e.phone_number,\s
                a.permanent_address, a.temporary_address
       
    """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("First Name: " + resultSet.getString("first_name"));
                System.out.println("Last Name: " + resultSet.getString("last_name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Phone: " + resultSet.getString("phone_number"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("DOB: " + resultSet.getString("dob"));
                System.out.println("Permanent Address: " + resultSet.getString("permanent_address"));
                System.out.println("Temporary Address: " + resultSet.getString("temporary_address"));
                System.out.println("Departments: " + resultSet.getString("departments")); // Display associated departments
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
