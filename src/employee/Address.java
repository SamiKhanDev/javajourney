package employee;

public class Address {
    private String permanentAddress;
    private String temporaryAddress;

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        if (permanentAddress != null && !permanentAddress.trim().isEmpty()) {
            this.permanentAddress = permanentAddress;
        } else {
            System.out.println("Invalid permanent address.");
        }
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        if (temporaryAddress != null && !temporaryAddress.trim().isEmpty()) {
            this.temporaryAddress = temporaryAddress;
        } else {
            System.out.println("Invalid temporary address.");
        }
    }
}
