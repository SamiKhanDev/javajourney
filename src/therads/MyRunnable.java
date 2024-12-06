package therads;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("thread is running" + Thread.currentThread().getName());
    }
}
