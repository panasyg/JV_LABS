package task4.multithreading;

public class RunnableCalc implements Runnable {
    private boolean shouldBeStopped = false;
    int iterations;
    int milliseconds;
    String threadName;

    Thread thread ;

    public RunnableCalc(int iterations, int milliseconds, String threadName) {
        this.iterations = iterations;
        this.milliseconds = milliseconds;
        this.threadName = threadName;
    }

    public RunnableCalc(String threadName) {
        this.threadName = threadName;
        this.milliseconds = 1000;
        this.iterations = 5;
    }

    public void stopThread() {
        this.shouldBeStopped = true;
    }

    @Override
    public void run() {
        System.out.printf("Thread [" + threadName + "]. started calculation \n");
        for (int i = 0; i < 10; i++) {
            if (!shouldBeStopped) {
                Calculations.calculate(i + 1, milliseconds, threadName);
            }
        }
        System.out.printf("Thread [" + threadName + "]. finished calculation \n");
    }
}
