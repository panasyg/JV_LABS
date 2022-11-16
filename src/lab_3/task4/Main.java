package task4;

import task4.multithreading.RunnableCalc;
import task4.multithreading.ThreadCalc;

public class Main {
    public static void main(String[] args) {
        ThreadCalc thread1 = new ThreadCalc(4,  100, "1 Thread");
        ThreadCalc thread2 = new ThreadCalc(7, 1100, "2 Thread");

        RunnableCalc runnable1 = new RunnableCalc(5, 200, "1 Runnable");
        RunnableCalc runnable2 = new RunnableCalc(2, 2000, "2 Runnable");
        Thread threadRunnable1 = new Thread(runnable1);
        Thread threadRunnable2 = new Thread(runnable2);


        thread1.start();
        thread2.start();
        threadRunnable1.start();
        threadRunnable2.start();

        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {

        }

        thread1.stopThread();
        thread2.stopThread();

        runnable1.stopThread();
        runnable2.stopThread();
    }
}
