package abstractfactory.loan;

import abstractfactory.loan.Loan;

public class HomeLoan extends Loan {
    @Override
    public void getInterestRate(double r) {
        rate= r;
    }
}
