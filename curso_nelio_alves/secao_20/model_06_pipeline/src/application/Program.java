package application;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(3, 4, 5, 10, 7);
		
		Stream<Integer> streamNumbers = numbers.stream().map(x -> x * 10);
		
		System.out.println("New List: " + Arrays.toString(streamNumbers.toArray()));
		
		int sumNumbers = numbers.stream().reduce(0, (x, y) -> x + y);
		
		System.out.println("The sum is " + sumNumbers);
		
		List<Integer> newNumbers = numbers.stream()
				.filter(number -> number % 2 == 0)
				.map(number -> number * 10)
				.collect(Collectors.toList());
		
		System.out.println("Another new List: " + Arrays.toString(newNumbers.toArray()));
	}

}
