package generics;

import java.util.ArrayList;

public class PracticeGenerics {
    public static<E> void eElements(E[] array){
        for( E elements:array){
            System.out.println(elements);
        }
    }
    public static void main(String[] args) {
        Integer[] arrays = {10,12,13,14};
        ArrayList<Integer> list  = new ArrayList<>();
        list.add(14);
        list.add(15);
        list.add(75);
        int n = list.get(0);
        System.out.println(n);
        GenericClass<Integer,String> intObj= new GenericClass<>(15,"sami");
        System.out.println(intObj.getData() + " " + intObj.getData2());
        System.out.println("print arrays");
        eElements(arrays);
    }
}
