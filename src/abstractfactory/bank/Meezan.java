package abstractfactory.bank;

public class Meezan implements Bank{
    private final String bankName;

    public Meezan() {
        bankName = "Meezan";
    }

    @Override
    public String getBankName() {
        return bankName;
    }
}
