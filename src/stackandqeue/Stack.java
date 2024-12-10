package stackandqeue;

import java.util.ArrayList;

public class Stack <T>{
    ArrayList<T> elements = new ArrayList<>();
    public void push( T value){
        elements.add(value);

    }
    public T pop(){
        if(elements.isEmpty()){
          throw new RuntimeException();
        }else
          return elements.remove(elements.size()-1);
    }

    public boolean isEmpty (){
        return elements.isEmpty();
    }
    public  T peek(){
        if (isEmpty()){
            throw new RuntimeException();
        }else
        return elements.remove(elements.size()-1);

    }
    public int size(){
        return elements.size();
    }

}

