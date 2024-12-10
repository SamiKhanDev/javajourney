package stackandqeue;

public class QueueMain {
    public static void main(String[] args) {
        Qeue<Integer> add = new Qeue<>();
        add.enqueue(10);
        add.enqueue(20);
        add.enqueue(30);
        add.enqueue(40);
        add.enqueue(50);
        System.out.println("remove element" + add.dequeue());
        System.out.println("top element" + add.peek());
        System.out.println("size of linklist" + add.size());
    }
}
