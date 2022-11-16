package task4.multithreading;

public class Calculations {
    public static void calculate(int i, int milliseconds, String threadName){
        System.out.println("Thread [" + threadName + "]. iteration: " + i + ". ");
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e){
            System.out.println("Interrupted exception");
        }
    }
}
