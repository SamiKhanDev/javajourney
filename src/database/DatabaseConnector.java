package database;

import employee.Address;
import employee.Employee;
import java.sql.*;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/Employee_details";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static DatabaseConnector instance;

    public DatabaseConnector() {
    }

    public static synchronized DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

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
            INSERT INTO address (employee_id, street_number, street_name, city, state, postal_code, country)
            VALUES (?, ?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
            street_number = VALUES(street_number),
            street_name = VALUES(street_name),
            city = VALUES(city),
            state = VALUES(state),
            postal_code = VALUES(postal_code),
            country = VALUES(country)
        """;


        String linkSql = """
            INSERT INTO employee_address_department (employee_id, address_id, department_id)
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE
            department_id = VALUES(department_id)
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

            int employeeId = employee.getId();
            if (employeeId == 0) {
                try (ResultSet generatedKeys = employeeStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        employeeId = generatedKeys.getInt(1);
                    }
                }
            }

            // Insert address data
            addressStmt.setInt(1, employeeId);
            addressStmt.setString(2, address.getStreetNumber());
            addressStmt.setString(3, address.getStreetName());
            addressStmt.setString(4, address.getCity());
            addressStmt.setString(5, address.getState());
            addressStmt.setString(6, address.getPostalCode());
            addressStmt.setString(7, address.getCountry());
            addressStmt.executeUpdate();


            int addressId = 0;
            try (ResultSet generatedKeys = addressStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    addressId = generatedKeys.getInt(1);
                }
            }

            // Link employee, address, and department
            if (addressId > 0) {
                linkStmt.setInt(1, employeeId);
                linkStmt.setInt(2, addressId);
                linkStmt.setInt(3, departmentId);
                linkStmt.executeUpdate();
            }

            System.out.println("Employee and related data inserted/updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployeeField(Employee employee, String fieldName, String newValue) {
        String sql = switch (fieldName.toLowerCase()) {
            case "first_name" -> "UPDATE employee SET first_name = ? WHERE id = ?";
            case "last_name" -> "UPDATE employee SET last_name = ? WHERE id = ?";
            case "email" -> "UPDATE employee SET email = ? WHERE id = ?";
            case "department" -> "UPDATE employee SET department = ? WHERE id = ?";
            case "age" -> "UPDATE employee SET age = ? WHERE id = ?";
            case "dob" -> "UPDATE employee SET dob = ? WHERE id = ?";
            case "phone_number" -> "UPDATE employee SET phone_number = ? WHERE id = ?";
            default -> {
                System.out.println("Invalid field name");
                yield null;
            }
        };

        if (sql == null) return;

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

    public void deleteEmployeeField(int employeeId) {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, employeeId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Employee with ID " + employeeId + " deleted successfully");
            } else {
                System.out.println("Employee ID " + employeeId + " was not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchEmployees() {
        String sql = """
            SELECT e.id, e.first_name, e.last_name, e.email, e.age, e.dob, e.phone_number,
                   a.street_name, a.city, a.state, a.postal_code, a.country,
                   GROUP_CONCAT(d.name) AS departments
            FROM employee e
            LEFT JOIN address a ON e.id = a.employee_id
            LEFT JOIN employee_address_department ead ON e.id = ead.employee_id
            LEFT JOIN department d ON ead.department_id = d.id
            GROUP BY e.id, e.first_name, e.last_name, e.email, e.age, e.dob, e.phone_number,
                     a.street_number, a.street_name, a.city, a.state, a.postal_code, a.country
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
//                System.out.println("Street Number: " + resultSet.getString("street_number"));
                System.out.println("Street Name: " + resultSet.getString("street_name"));
                System.out.println("City: " + resultSet.getString("city"));
                System.out.println("State: " + resultSet.getString("state"));
                System.out.println("Postal Code: " + resultSet.getString("postal_code"));
                System.out.println("Country: " + resultSet.getString("country"));
                System.out.println("Departments: " + resultSet.getString("departments"));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        }

