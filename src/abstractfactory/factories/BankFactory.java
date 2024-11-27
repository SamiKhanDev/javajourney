package abstractfactory.factories;

import abstractfactory.bank.*;
import abstractfactory.loan.BuisnessLoan;

class BankFactory extends AbstractFactory {
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
  public BuisnessLoan getLoan(String loan) {
      return null;  
   }  
}