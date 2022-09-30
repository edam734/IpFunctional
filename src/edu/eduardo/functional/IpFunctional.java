package edu.eduardo.functional;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
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
//		System.out.println(countPrimes(30));
//		System.out.println(biggestDiff(50));
//		System.out.println(firstDigit(6532));
//		System.out.println(isCapicua(3241423));
		System.out.println(isCapicuaFunctional(332414233));
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

	static int biggestDiff(int n) {
		List<Integer> primes = IntStream.rangeClosed(1, n).filter(i -> isPrime(i)).boxed().collect(Collectors.toList());
		return IntStream.range(0, primes.size() - 1).map(index -> primes.get(index + 1) - primes.get(index)).max()
				.getAsInt();
	}

	static int mmc(int m, int n) {
		if (m == n) {
			return m;
		}
		if (m < n) {
			return mmc(m, n - m);
		} else {
			return mmc(m - n, m);
		}
	}

	static int mdc(int m, int n) {
		while (n != 0) {
			int r = m % n;
			m = n;
			n = r;
		}
		return m;
	}

	static int firstDigit(int n) {
		String first = String.valueOf(n).charAt(0) + "";
		return Integer.valueOf(first);
	}

	static boolean isCapicua(int n) {
		int m = n;
		int i = 0;
		while (n != 0) {
			n = n / 10;
			i++;
		}
		int[] a = new int[i];
		int r = -1;
		i = 0;
		while (m != 0) {
			r = m % 10;
			m = m / 10;
			a[i] = r;
			i++;
		}
		int l = a.length - 1;
		i = 0;
		boolean eCapicua = true;
		while (i < a.length / 2) {
			if (a[i] != a[l]) {
				eCapicua = false;
				break;
			}
			i++;
			l--;
		}
		return eCapicua;
	}

	static boolean isCapicuaFunctional(int n) {
		List<Integer> digits = String.valueOf(n).chars().boxed().collect(Collectors.toList());
		
		// split a list of integers in half
		Collection<List<Integer>> values = IntStream.range(0, digits.size()).boxed()
				.collect(Collectors.groupingBy(index -> index < digits.size() / 2, Collectors.toList())).values();
		Iterator<List<Integer>> iterator = values.iterator();
		
		List<Integer> first = iterator.next(); // first half
		List<Integer> second = iterator.next(); // second half
		
		// if n is odd, both lists are not balanced, we need to remove the first element
		// of the first half
		if (digits.size() % 2 != 0) {
			first = first.stream().skip(1).collect(Collectors.toList());
		}
		
		// reverse order for both lists became equal
		first = first.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		
		// replace natural numbers for elements of the list digits
		first = replaceAll(first, digits);
		second = replaceAll(second, digits);

		return first.equals(second);
	}
	
	static List<Integer> replaceAll(List<Integer> list, List<Integer> list2) {
		return list.stream().mapToInt(i -> list2.get(i)).boxed().collect(Collectors.toList());
	}
}
