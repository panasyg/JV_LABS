package lab1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task7 {

	public static void DisplayListProduct(final ListProduct listProduct) {
		System.out.println(listProduct);
		System.out.println("Total price = " + listProduct.calcPrice() + "$");
	}
	
	public static void main(final String[] args) {

		final ListProduct listProduct = new ListProduct(Stream.of(
			new PairProduct(new Product("gpu", 2.4), 3),
			new PairProduct(new Product("ram", 0.5), 14),
			new PairProduct(new Product("cpu", 1.2), 52)
		).collect(Collectors.toList()));
		DisplayListProduct(listProduct);
	}
}
