package employee;

public final class IntegerTwo {
    final Integer num1;
    final Integer num2;


    public IntegerTwo(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }


}

 class IntegerUse{

     public static void main(String[] args) {

         IntegerTwo n = new IntegerTwo(12,12);
         System.out.println(n.num1+ n.num2);
    }

}
