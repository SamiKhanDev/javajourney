package designsystems.abstractfactory.factories;

import designsystems.abstractfactory.bank.Bank;
import designsystems.abstractfactory.loan.*;

public class LoanFactory extends AbstractFactory {
           public Bank getBank(String bank){
                return null;  
          }  
        
     public Loan getLoan(String loan){
      if(loan == null){  
         return null;  
      }  
      if(loan.equalsIgnoreCase("Home")){  
         return new HomeLoan();
      } else if(loan.equalsIgnoreCase("Business")){  
         return new BuisnessLoan();
      } else if(loan.equalsIgnoreCase("Education")){  
         return new EducationLoan();
      }  
      return null;  
   }  
     
}  