package abstractfactory.factories;

import abstractfactory.bank.Bank;
import abstractfactory.bank.HBL;
import abstractfactory.bank.Islamic;
import abstractfactory.bank.Meezan;
import abstractfactory.loan.Loan;

class BankFactory extends AbstractFactory{
   public Bank getBank(String bank){
      if(bank == null){  
         return null;  
      }  
      if(bank.equalsIgnoreCase("HBL")){
         return new HBL();
      } else if(bank.equalsIgnoreCase("Islamic")){
         return new Islamic();
      } else if(bank.equalsIgnoreCase("Meezan")){
         return new Meezan();
      }  
      return null;  
   }  
  public Loan getLoan(String loan) {
      return null;  
   }  
}