package designsystems.abstractfactory.factorydesignsystem;

public class PlanFactory {
    public Plan getPlan(String type){
        if (type==null){
            return null;
        }
        if (type.equalsIgnoreCase("DomesticPlan")){
            return new DomesticPlan();
        }
        if (type.equalsIgnoreCase("CommercialPlan")){
            return new ComercialPlan();
        }
        if (type.equalsIgnoreCase("InstitutionPLan")){
            return new InstitutationPlan();
        }
        return null;
    }

}
