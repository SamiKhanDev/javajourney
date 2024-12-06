package frameworks;

import java.util.*;

public class Frameworks {
    public static void main(String[] args) {
        //use of arraylist
        ArrayList<String> cars = new ArrayList<>(5);

        cars.add("suzuki");
        cars.add("mehran");
        cars.add("civic");
        cars.add("bmw");
        cars.add("honda");
        cars.add("mercer");
        System.out.println(cars);
        Collections.sort(cars);

        //use of linklist
        LinkedList<Integer> list = new LinkedList<>();

        list.add(127);
        list.add(124);
        list.add(125);
        list.add(126);
        //collections
        Collections.sort(list);

        System.out.println(list);

        HashMap<String,String> map = new HashMap<>();
        map.put("pakistan","Islamabad");
        map.put("india","Delhi");
        map.put("england","london");
        System.out.println(map.get("pakistan"));
        System.out.println(map.remove("india"));
        System.out.println(map.size());
        System.out.println(map.remove("india"));
        for(String i: map.keySet()){
            System.out.println(i);
        }
        for(String j : map.values()){
            System.out.println(j);
        }
        HashMap<String,Integer> user = new HashMap<>();

        user.put("sami",23);
        user.put("ubaid",24);
        user.put("ahmad",23);
        System.out.println(user);

        for(String k: user.keySet()){
            System.out.println("key: " + k + "value: " + user.get(k));

        }

        //Hashset Implementation
        HashSet<String> names = new HashSet<>();
        names.add("sami");
        names.add("ahmad");
        names.add("ubaid");
        names.add("furqan");
        names.add("sami");
        names.add("ahmad");
        System.out.println(names);

        HashSet<Integer> numbers = new HashSet<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(11);
        numbers.add(12);
        numbers.add(13);

        for (int i = 1; i<=10; i++){
            if(numbers.contains(i)){
                System.out.println("number is between 10");
            }else
                System.out.println("number was not found");
        }
        //implementation of Iterator
        ArrayList<String> bikes = new ArrayList<>();

        bikes.add("suzuki");
        bikes.add("mehran");
        bikes.add("civic");
        bikes.add("bmw");
        bikes.add("honda");
        bikes.add("mercer");
        Iterator<String> it = bikes.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }


    }
}
