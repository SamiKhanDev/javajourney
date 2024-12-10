package designsystems.abstractfactory.factorydesignsystem;

import java.util.Scanner;

public class Factory {
    public static void main(String[] args) {
        PlanFactory planFactory = new PlanFactory();
        Scanner input = new Scanner(System.in);
        System.out.println("enter the name of the plan");
        String in = input.nextLine();
        System.out.print("Enter the number of units for bill will be calculated: ");
        int units=Integer.parseInt(input.nextLine());
        Plan p = planFactory.getPlan(in);
        System.out.print("Bill amount for "+ in +" of  "+units+" units is: ");
        p.getRate();
        p.calculateBill(units);
    }
}
