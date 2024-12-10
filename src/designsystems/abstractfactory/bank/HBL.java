package designsystems.abstractfactory.bank;

public class HBL implements Bank{
    private final String bankName;



    public HBL() {
        bankName = "HBL";
    }

    @Override
    public String getBankName() {
        return bankName;
    }
}
