package edu.eduardo.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IpFunctional2 {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays
				.asList(new Integer[] { 1, 45, 3, 34, 7, 4, 0, 12, 8, 95, 0, 3, 12, 95, 7, 34, 0, 0, 21, 5, 95 });
		List<Integer> numbers2 = Arrays.asList(new Integer[] { 104, 56, 23, 90 });
		List<Integer> scores = Arrays
				.asList(new Integer[] { 12, 15, 10, 10, 14, 16, 20, 15, 11, 15, 12, 16, 9, 13, 8, 14, 12, 12, 13 });
		List<Double> doubles = Arrays.asList(5.2, 4.2, 1.0, 8.7, 3.5);

		System.out.println(sum(doubles.stream()));
		System.out.println(zeros(numbers));
		System.out.println(count(numbers, 7));
		System.out.println(lessers(numbers, 20));
		System.out.println(belong(numbers, 33));
		System.out.println(twoZeros(numbers));
		squares(25).forEach(System.out::println);
		squaresDesc(25).forEach(System.out::println);
		dividers(30).forEach(System.out::println);
		System.out.println(maximum(doubles.stream()));
		System.out.println(firstPositionMax(numbers));
		System.out.println(lastPositionMax(numbers));
		System.out.println(positionsMaximumSum(numbers));
		positionsMaximum(numbers).forEach(System.out::println);
		square(numbers).forEach(System.out::println);
		invert(numbers).forEach(System.out::println);
		System.out.println(splitByCategoryCount(numbers, 34).toString());
		System.out.println(splitByCategoryLists(numbers, 34).toString());
		System.out.println(evensAfterNum(numbers, 7));
		System.out.println(evensAfterLastNum(numbers, 7));
		System.out.println(join(numbers, numbers2).toString());
		System.out.println(histogram(scores).toString());
	}

	static double sum(Stream<Double> v) {
		return v.collect(Collectors.summingDouble(Double::doubleValue));
	}

	static int zeros(List<Integer> v) {
		return (int) v.stream().filter(e -> e == 0).count();
	}

	static int count(List<Integer> v, int n) {
		return (int) v.stream().filter(e -> e == n).count();
	}

	static int lessers(List<Integer> v, int n) {
		return (int) v.stream().filter(e -> e < n).count();
	}

	static boolean belong(List<Integer> v, int n) {
		return v.stream().anyMatch(e -> e == n);
	}

	static boolean twoZeros(List<Integer> v) {
		return IntStream.range(0, v.size() - 1).map(index -> v.get(index) + v.get(index + 1)).anyMatch(e -> e == 0);
	}

	static List<Integer> squares(int n) {
		int m = (int) Math.sqrt(n);
		return IntStream.rangeClosed(1, m).map(e -> e * e).boxed().collect(Collectors.toList());
	}

	static List<Integer> squaresDesc(int n) {
		return squares(n).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}

	static List<Integer> dividers(int n) {
		return IntStream.rangeClosed(1, n / 2).filter(e -> n % e == 0).boxed().collect(Collectors.toList());
	}

	static double maximum(Stream<Double> v) {
		return v.max(Comparator.naturalOrder()).get();
	}

	static int firstPositionMax(List<Integer> v) {
		int max = getMax(v);
		return IntStream.rangeClosed(0, v.size() - 1).filter(index -> v.get(index) == max).findFirst().getAsInt();
	}

	static int lastPositionMax(List<Integer> v) {
		int max = getMax(v);
		return IntStream.rangeClosed(0, v.size() - 1).filter(index -> v.get(index) == max).max().getAsInt();
	}

	static int positionsMaximumSum(List<Integer> v) {
		int max = getMax(v);
		return IntStream.rangeClosed(0, v.size() - 1).filter(index -> v.get(index) == max).sum();
	}

	static List<Integer> positionsMaximum(List<Integer> v) {
		int max = getMax(v);
		return IntStream.rangeClosed(0, v.size() - 1).filter(index -> v.get(index) == max).boxed()
				.collect(Collectors.toList());
	}

	private static int getMax(List<Integer> v) {
		return v.stream().max(Comparator.naturalOrder()).get();
	}

	static List<Integer> square(List<Integer> v) {
		return v.stream().map(e -> e * e).collect(Collectors.toList());
	}

	static List<Integer> invert(List<Integer> v) {
		List<Integer> indexes = IntStream.rangeClosed(0, v.size() - 1).boxed().sorted(Collections.reverseOrder())
				.collect(Collectors.toList());
		return indexes.stream().map(index -> v.get(index)).collect(Collectors.toList());
	}

	static Map<String, Long> splitByCategoryCount(List<Integer> v, int n) {
		return IntStream.range(0, v.size()).mapToObj(index -> new SpecialInt(v.get(index), n))
				.collect(Collectors.groupingBy(SpecialInt::getCategory, Collectors.counting()));
	}

	static Map<String, List<Integer>> splitByCategoryLists(List<Integer> v, int n) {
		return IntStream.range(0, v.size()).mapToObj(index -> new SpecialInt(v.get(index), n))
				.collect(Collectors.groupingBy(SpecialInt::getCategory, SpectialIntCollector.toList()));
	}

	static class SpecialInt {

		int i;
		int n;

		public SpecialInt(int i, int n) {
			this.i = i;
			this.n = n;
		}

		public String getCategory() {
			if (i > n) {
				return "maior";
			} else if (i == n) {
				return "igual";
			} else {
				return "menor";
			}
		}

		public Integer getInteger() {
			return i;
		}

	}

	static class SpectialIntCollector implements Collector<SpecialInt, List<Integer>, List<Integer>> {

		public static SpectialIntCollector toList() {
			return new SpectialIntCollector();
		}

		@Override
		public Supplier<List<Integer>> supplier() {
			return ArrayList::new;
		}

		@Override
		public BiConsumer<List<Integer>, SpecialInt> accumulator() {
			return (list, specInt) -> list.add(specInt.getInteger());
		}

		@Override
		public BinaryOperator<List<Integer>> combiner() {
			return (list1, list2) -> {
				list1.addAll(list2);
				return list1;
			};
		}

		@Override
		public Function<List<Integer>, List<Integer>> finisher() {
			return Collections::unmodifiableList;
		}

		@Override
		public Set<Characteristics> characteristics() {
			return Set.of(Characteristics.UNORDERED);
		}

	}

	static List<Integer> getIndexesOfNum(List<Integer> v, int n) {
		return IntStream.rangeClosed(0, v.size() - 1).filter(index -> v.get(index) == n).boxed()
				.collect(Collectors.toList());
	}

	static int indexFirstOccurrence(List<Integer> v, int n) {
		return getIndexesOfNum(v, n).get(0);
	}

	static int inexLastOccurrence(List<Integer> v, int n) {
		return v.lastIndexOf(n);
	}

	static int evensAfterNum(List<Integer> v, int n) {
		int startIndex = indexFirstOccurrence(v, n);
		return (int) IntStream.rangeClosed(startIndex, v.size() - 1).mapToObj(index -> v.get(index))
				.filter(e -> e % 2 == 0).count();
	}

	static int evensAfterLastNum(List<Integer> v, int n) {
		int lastIndex = inexLastOccurrence(v, n);
		return (int) IntStream.rangeClosed(lastIndex, v.size() - 1).mapToObj(index -> v.get(index))
				.filter(e -> e % 2 == 0).count();
	}

	static List<Integer> join(List<Integer> v, List<Integer> w) {
		List<Integer> all = new ArrayList<Integer>(v);
		all.addAll(w);
		return all;
	}

	static int countOccurrencesOfN(List<Integer> v, int n) {
		return getIndexesOfNum(v, n).size();
	}

	static List<Integer> histogram(List<Integer> v) {
		return IntStream.rangeClosed(0, v.size() - 1).mapToObj(index -> countOccurrencesOfN(v, v.get(index)))
				.collect(Collectors.toList());
	}

}