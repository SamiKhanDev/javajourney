package stackandqeue;

public class StackMain {
    public static void main(String[] args) {
        Stack<Integer> myStack = new Stack<>();
        myStack.push(10);
        myStack.push(1);
        myStack.push(11);
        myStack.push(12);
        myStack.push(13);
        myStack.push(14);
        System.out.println("top element"+ myStack.peek());
        System.out.println("pop element"+ myStack.pop());
        System.out.println("size of array"+ myStack.size());
    }
}
