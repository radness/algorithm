import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class StreamExam {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

		Iterator<Integer> iter = list.iterator();

		System.out.println("Iterator");
		while (iter.hasNext()) {
			int num = iter.next();

			System.out.println("값 : " + num);
		}
		
		System.out.println("Stream");
		
		// Stream 객체 생성
		Stream<Integer> stream = list.stream();
		stream.forEach(num -> System.out.println("값 : " + num));
		
		ArrayList<String> sList = new ArrayList<>(Arrays.asList("a", "b", "c"));
		
		sList.stream()
			.filter("b"::equals)
			.forEach(System.out::println);
		
	}
}
