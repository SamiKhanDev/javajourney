package stackandqeue;

import java.util.LinkedList;

public class Qeue <V> {
    LinkedList<V> queue = new LinkedList<>();

    public void enqueue( V value){
        queue.addLast(value);
    }

    public V dequeue (){
        if(queue.isEmpty()){
            throw new RuntimeException();

        }else
            return queue.removeFirst();
    }

    public V peek(){
        if (isEmpty()){
            throw new RuntimeException();
        }else
            return queue.getFirst();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
    public int size (){
        return queue.size();
    }

}
