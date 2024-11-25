package employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//public class PhoneValidationHelper {
////    private Properties properties = new Properties();
////
////    public PhoneValidationHelper() {
////        try (InputStream input = getClass().getClassLoader().getResourceAsStream("employee/phone_validation.properties")) {
////            if (input == null) {
////                throw new IOException("Properties file not found!");
////            }
////            properties.load(input);
////        } catch (IOException e) {
////            System.err.println("Error loading properties file: " + e.getMessage());
////        }
////    }
////
////    public String getPhonePrefix(String countryCode) {
////        String value = properties.getProperty(countryCode);
////        return value != null ? value.split(":")[0] : null;
////    }
////
////    public int getPhoneNumberLength(String countryCode) {
////        String value = properties.getProperty(countryCode);
////        return value != null ? Integer.parseInt(value.split(":")[1]) : -1;
////    }
////
////    public boolean isValidPhoneNumber(String countryCode, String phoneNumber) {
////        String value = properties.getProperty(countryCode);
////        if (value == null) return false;
////
////        String[] parts = value.split(":");
////        int requiredLength = Integer.parseInt(parts[1]);
////
////        if (phoneNumber.length() != requiredLength) return false;
////
////        for (int i = 2; i < parts.length; i++) {
////            String[] network = parts[i].split("=");
////            String[] validPrefixes = network[1].split("\\|");
////            for (String prefix : validPrefixes) {
////                if (phoneNumber.startsWith(prefix)) {
////                    return true;
////                }
////            }
////        }
////        return false;
////    }
//}


import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
        String regex = properties.getProperty(countryCode);
        if (regex == null) {
            System.err.println("No pattern found for the given country code: " + countryCode);
            return false;
        }

        try {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(phoneNumber).matches();
        } catch (PatternSyntaxException e) {
            System.err.println("Invalid regex pattern: " + regex);
            return false;
        }
    }

}
