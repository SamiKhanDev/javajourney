package employee;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class PhoneValidationHelper {
//    private Properties properties = new Properties();
//
//    public PhoneValidationHelper() {
//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("phone_validation.properties")) {
//            if (input == null) {
//                throw new IOException("Properties file not found!");
//            }
//            properties.load(input);
//        } catch (IOException e) {
//            System.err.println("Error loading properties file: " + e.getMessage());
//        }
//    }
//
//    public int getPhoneNumberLength(String countryCode) {
//        String length = properties.getProperty(countryCode);
//        return length != null ? Integer.parseInt(length) : -1;
//    }
//}


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PhoneValidationHelper {
    Properties properties = new Properties();
    public PhoneValidationHelper(){
        try (InputStream input = getClass().getResourceAsStream("phone_validation.properties")){
            if(input==null){
                System.out.println("invalid property");
            }
            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int getPhoneNumberLength(String countryCode){
        String length = properties.getProperty(countryCode);
        return length != null ? Integer.parseInt(length): -1;
    }
}