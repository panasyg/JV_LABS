package task4.multithreading;

public class ThreadCalc extends Thread {
    private boolean shouldBeStopped = false;
    int iterations = 10;
    int milliseconds = 1000;
    String threadName;

    public ThreadCalc(int iterations, int milliseconds, String threadName) {
        this.iterations = iterations;
        this.milliseconds = milliseconds;
        this.threadName = threadName;
    }

    public ThreadCalc(String threadName) {
        this.threadName = threadName;
    }

    public void stopThread() {
        this.shouldBeStopped = true;
    }

    @Override
    public void run() {
        System.out.printf("Thread [" + threadName + "]. started calculation \n");
        for (int i = 0; i < 10; i++) {
            if (!isInterrupted() && !shouldBeStopped) {
                Calculations.calculate(i + 1, milliseconds, threadName);
            }
        }
        System.out.printf("Thread [" + threadName + "]. finished calculation \n");
    }
}
