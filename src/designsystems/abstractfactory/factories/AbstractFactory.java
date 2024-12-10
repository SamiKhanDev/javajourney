package designsystems.abstractfactory.factories;

import designsystems.abstractfactory.bank.Bank;
import designsystems.abstractfactory.loan.Loan;

public abstract class AbstractFactory {
    public abstract Bank getBank(String bank);
    public abstract Loan getLoan(String Loan);
}
