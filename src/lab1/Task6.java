package lab1;

public class Task6 {

	public static void DisplayLogic(final User user) {
		System.out.println("hashCode of " + user + " = " + user.hashCode());
		final User copy = new User(user);
		System.out.println("equals of copy = " + user.equals(copy));
		final User notCopy = new User(new User("Ivan", "Panas", 18, "ivan@gmail.com"));
		System.out.println("equals of not copy = " + user.equals(notCopy));
	}

	public static void main(final String[] args) {
		final User user = new User("Ivan", "Panas", 18, "ivan@gmail.com");
		DisplayLogic(user);
	}
}
