package designsystems.abstractfactory.bank;

public class Islamic implements Bank{
    private final String bankName;

    public Islamic() {
        bankName = "Islamic";
    }

    @Override
    public String getBankName() {
        return bankName;
    }
}
