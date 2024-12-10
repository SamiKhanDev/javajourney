package therads;

public class LambdaThread {
    public static void main(String[] args) {
        Thread thread = new Thread(()-> System.out.println("thread chal rha hai "+ Thread.currentThread().getName()));
        thread.start();

    }
}
