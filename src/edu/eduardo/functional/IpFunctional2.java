package edu.eduardo.functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IpFunctional2 {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays
				.asList(new Integer[] { 1, 0, 3, 34, 7, 4, 0, 12, 8, 95, 0, 3, 12, 95, 7, 34, 0, 0, 21, 5, 95 });
		List<Double> doubles = Arrays.asList(5.2, 4.2, 1.0, 8.7, 3.5);

		// 1
		System.out.println(soma(doubles.stream()));
		// 2
		System.out.println(zeros(numbers));
		// 3
		System.out.println(conta(numbers, 7));
		// 4
		System.out.println(menores(numbers, 20));
		// 5
		System.out.println(pertence(numbers, 33));
		// 6
		System.out.println(doisZeros(numbers));
		// 7
		quadrados(25).forEach(System.out::println);
		// 8
		quadradosDecrescente(25).forEach(System.out::println);
		// 9
		divisores(30).forEach(System.out::println);
		// 10
		System.out.println(maximo(doubles.stream()));
		// 11
		System.out.println(primeiraPosicaoMaximo(numbers));
		// 12
		System.out.println(ultimaPosicaoMaximo(numbers));
		// 13
		System.out.println(somaPosicoesMaximo(numbers));
		// 14
		posicoesMaximo(numbers).forEach(System.out::println);
		// 15
		aoQuadrado(numbers).forEach(System.out::println);
		// 16
		inverte(numbers).forEach(System.out::println);
	}

	static double soma(Stream<Double> v) {
		return v.collect(Collectors.summingDouble(Double::doubleValue));
	}

	static int zeros(List<Integer> v) {
		return (int) v.stream().filter(e -> e == 0).count();
	}

	static int conta(List<Integer> v, int n) {
		return (int) v.stream().filter(e -> e == n).count();
	}

	static int menores(List<Integer> v, int n) {
		return (int) v.stream().filter(e -> e < n).count();
	}

	static boolean pertence(List<Integer> v, int n) {
		return v.stream().anyMatch(e -> e == n);
	}

	static boolean doisZeros(List<Integer> v) {
		return IntStream.range(0, v.size() - 1).map(index -> v.get(index) + v.get(index + 1)).anyMatch(e -> e == 0);
	}

	static List<Integer> quadrados(int n) {
		int m = (int) Math.sqrt(n);
		return IntStream.rangeClosed(1, m).map(e -> e * e).boxed().collect(Collectors.toList());
	}

	static List<Integer> quadradosDecrescente(int n) {
		return quadrados(n).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}

	static List<Integer> divisores(int n) {
		return IntStream.rangeClosed(1, n / 2).filter(e -> n % e == 0).boxed().collect(Collectors.toList());
	}

	static double maximo(Stream<Double> v) {
		return v.max(Comparator.naturalOrder()).get();
	}

	static int primeiraPosicaoMaximo(List<Integer> v) {
		int max = getMax(v);
		return IntStream.rangeClosed(0, v.size() - 1).filter(index -> v.get(index) == max).findFirst().getAsInt();
	}

	static int ultimaPosicaoMaximo(List<Integer> v) {
		int max = getMax(v);
		return IntStream.rangeClosed(0, v.size() - 1).filter(index -> v.get(index) == max).max().getAsInt();
	}

	static int somaPosicoesMaximo(List<Integer> v) {
		int max = getMax(v);
		return IntStream.rangeClosed(0, v.size() - 1).filter(index -> v.get(index) == max).sum();
	}

	static List<Integer> posicoesMaximo(List<Integer> v) {
		int max = getMax(v);
		return IntStream.rangeClosed(0, v.size() - 1).filter(index -> v.get(index) == max).boxed()
				.collect(Collectors.toList());
	}

	private static int getMax(List<Integer> v) {
		return v.stream().max(Comparator.naturalOrder()).get();
	}

	static List<Integer> aoQuadrado(List<Integer> v) {
		return v.stream().map(e -> e * e).collect(Collectors.toList());
	}

	static List<Integer> inverte(List<Integer> v) {
		List<Integer> indexes = IntStream.rangeClosed(0, v.size() - 1).boxed().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
		return indexes.stream().map(index -> v.get(index)).collect(Collectors.toList());
	}

}
