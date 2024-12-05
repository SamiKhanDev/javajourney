package employee;

public class Address {
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        if (streetNumber != null && !streetNumber.trim().isEmpty()) {
            this.streetNumber = streetNumber;
        } else {
            System.out.println("Invalid street number.");
        }
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        if (streetName != null && !streetName.trim().isEmpty()) {
            this.streetName = streetName;
        } else {
            System.out.println("Invalid street name.");
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city != null && !city.trim().isEmpty()) {
            this.city = city;
        } else {
            System.out.println("Invalid city.");
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state != null && !state.trim().isEmpty()) {
            this.state = state;
        } else {
            System.out.println("Invalid state.");
        }
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        if (postalCode != null && !postalCode.trim().isEmpty()) {
            this.postalCode = postalCode;
        } else {
            System.out.println("Invalid postal code.");
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country != null && !country.trim().isEmpty()) {
            this.country = country;
        } else {
            System.out.println("Invalid country.");
        }
    }

    // Override toString for displaying address details
    @Override
    public String toString() {
        return "Address{" +
                "streetNumber='" + streetNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
