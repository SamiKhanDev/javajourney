package employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean isValidPhoneNumber(String countryCode, String phoneNumber) {
        String regex = "";
        if (countryCode.equals("PK")) {
            regex = "^((\\+92)|(0092))-{0,1}\\d{3}-{0,1}\\d{7}$|^\\d{11}$|^\\d{4}-\\d{7}$\n";
        }

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean isValid = matcher.matches();
        System.out.println("Using regex: " + regex + " for phone number: " + phoneNumber);
        System.out.println("Regex match result: " + isValid);
        return isValid;
    }



}
