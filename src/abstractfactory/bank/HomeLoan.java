package abstractfactory.bank;

import abstractfactory.Loan;

public class HomeLoan extends Loan {
    @Override
    void getInterestRate(double r) {
        rate= r;
    }
}
