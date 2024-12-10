package designsystems.abstractfactory.factorydesignsystem;

public abstract class Plan {
    abstract void getRate();
    protected double rate;
    public void calculateBill(int units){
        System.out.println(rate*units);
    }

}
