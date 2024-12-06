package therads;

public class Threads {
    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        Thread thread2 = new Thread(new MyRunnable());
        thread2.start();
        t1.start();
        try {
            t1.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t2.start();

    }
}
