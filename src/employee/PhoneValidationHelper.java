package employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PhoneValidationHelper {
    private Properties properties = new Properties();

    public PhoneValidationHelper() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("employee/phone_validation.properties")) {
            if (input == null) {
                throw new IOException("Properties file not found!");
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
    }

    public String getPhonePrefix(String countryCode) {
        String value = properties.getProperty(countryCode);
        return value != null ? value.split(":")[0] : null;
    }

    public int getPhoneNumberLength(String countryCode) {
        String value = properties.getProperty(countryCode);
        return value != null ? Integer.parseInt(value.split(":")[1]) : -1;
    }
}
