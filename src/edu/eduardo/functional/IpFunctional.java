package edu.eduardo.functional;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IpFunctional {

	public static void main(String[] args) {
//		printMultiples(3,10);
//		System.out.println(asterisks(5));
//		linesAsterisks(5);
//		System.out.println(sumExceeds(14));
//		System.out.println(doubleFactorial(7));
//		System.out.println(countDivisors(36));
//		System.out.println(isPerfect(28));
//		System.out.println(isPerfect(496));
//		System.out.println(countPerfets(500));
//		System.out.println(isPrime(7));
		System.out.println(countPrimes(30));

	}

	static void printMultiples(int k, int n) {
		IntStream.rangeClosed(1, n).filter(i -> i % k == 0).forEach(System.out::println);
	}

	static String asterisks(int n) {
		return IntStream.iterate(1, i -> 1).limit(n).mapToObj(i -> "*").collect(Collectors.joining());
	}

	static void linesAsterisks(int n) {
		IntStream.rangeClosed(1, n).mapToObj(i -> asterisks(i)).forEach(System.out::println);
	}

	static int sum(int n) {
		return IntStream.rangeClosed(0, n).sum();
	}

	static int sumExceeds(int k) {
		return IntStream.iterate(1, i -> sum(i) < k, i -> i + 1).max().getAsInt() + 1;
	}

	static int sumBetween(int m, int n) {
		return IntStream.rangeClosed(m, n).sum();
	}

	static int sumEvens(int n) {
		return IntStream.rangeClosed(1, n).filter(i -> i % 2 == 0).sum();
	}

	static int factorial(int n) {
		return IntStream.rangeClosed(1, n).reduce(1, (x, y) -> x * y);
	}

	static int doubleFactorial(int n) {
		return IntStream.iterate(isEven(n) ? 2 : 1, i -> i + 2).limit(isEven(n) ? n / 2 : n / 2 + 1).reduce(1,
				(x, y) -> x * y);
	}

	private static boolean isEven(int n) {
		return n % 2 == 0;
	}

	static int logarithm(int n) {
		return -1; // TODO
	}

	static int countDivisors(int n) {
		return (int) IntStream.iterate(1, i -> i + 1).limit(n / 2).filter(i -> n % i == 0).count();
	}

	static boolean isPerfect(int n) {
		return IntStream.iterate(1, i -> i + 1).limit(n / 2).filter(i -> n % i == 0).sum() == n;
	}

	static int countPerfets(int n) {
		return (int) IntStream.rangeClosed(1, n).filter(i -> isPerfect(i)).count();
	}

	/*
	 * By modern definition, a number is prime if it's divided by exactly two
	 * numbers, so 1 is out...
	 */
	static boolean isPrime(int n) {
		return IntStream.rangeClosed(1, n).filter(i -> n % i == 0).count() == 2;
	}

	static int countPrimes(int n) {
		return (int) IntStream.rangeClosed(1, n).filter(i -> isPrime(i)).count();
	}

}
