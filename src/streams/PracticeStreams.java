package streams;

import java.util.Arrays;
import java.util.List;


public class PracticeStreams {
    public static void main(String[] args) {
        //here im using three streams in total
        List<Integer> nums = Arrays.asList(1,2,4,5,7,8,3);
        nums.stream().filter(n-> n%2==1)
                .sorted()
                .map(n->n*2)
                .forEach(System.out::println);

        List<Integer> nums2 = Arrays.asList(2,4,5,6,7,8,9,4);

        //here im using parallel stream it creates stream in multiple threads
        nums2.parallelStream().filter(n -> n%2!=1)
                .sorted()
                .map(n -> n/n)
                .forEach(System.out::println);



    }
}
