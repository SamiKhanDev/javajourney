package designsystems.abstractfactory;
import designsystems.abstractfactory.bank.Bank;
import designsystems.abstractfactory.factories.AbstractFactory;
import designsystems.abstractfactory.factories.FactoryCreator;
import designsystems.abstractfactory.loan.Loan;

import java.util.Scanner;

class AbstractFactoryPatternExample {
      public static void main(String args[]) {
     Scanner br=new Scanner(System.in);
  
      System.out.print("Enter the name of Bank from where you want to take loan amount: ");  
      String bankName=br.nextLine();
  
System.out.print("Enter the type of loan e.g. home loan or business loan or education loan : ");
  
String loanName=br.nextLine();
AbstractFactory bankFactory = FactoryCreator.getFactory("Bank");
Bank b=bankFactory.getBank(bankName);
  
System.out.print("Enter the interest rate for "+b.getBankName()+ ": ");
  
double rate=Double.parseDouble(br.nextLine());
System.out.print("Enter the loan amount you want to take: ");
  
double loanAmount=Double.parseDouble(br.nextLine());
System.out.print("Enter the number of years to pay your entire loan amount: ");
int years=Integer.parseInt(br.nextLine());
  
System.out.println("you are taking the loan from "+ b.getBankName());
  
AbstractFactory loanFactory = FactoryCreator.getFactory("Loan");  
           Loan l=loanFactory.getLoan(loanName);
           l.getInterestRate(rate);  
           l.calculateLoanPayment(loanAmount,years);  
  }  
}