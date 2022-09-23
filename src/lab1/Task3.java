package lab1;

public class Task3 {
	
	private static long loop(final int x) {
		long num = 1;
		for(long i = 1; i <= x; i++) {
			num *= i;
		}
		return num;
	}
	
	private static long Recursion(final int x) {
		if (x < 1) {
			return 1;
		}
		return x * Recursion(x-1);
	}
	
	public static void main(final String[] args) {
		for (int x = 0; x <= 10; x++) {
			System.out.println(x + "! = " + loop(x) + " == " + Recursion(x));
		}
	}
}
