package abstractfactory.factories;

import abstractfactory.bank.Bank;
import abstractfactory.loan.BuisnessLoan;
import abstractfactory.loan.Loan;

public abstract class AbstractFactory {
    public abstract Bank getBank(String bank);
    public abstract Loan getLoan(String Loan);
}
