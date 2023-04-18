package basic.priority_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CompareExam {
	public static void main(String[] args) throws Exception {

		// Compare 메서드
		// 첫번째 값이 작으면 -1, 같으면 0, 크면 1
		int ret = Integer.compare(10, 20);

		System.out.println(ret);

		Integer num = 100;

		int ret2 = num.compareTo(500);

		System.out.println(ret2);

		Person p1 = new Person(170, 30);
		Person p2 = new Person(175, 30);

		int ret3 = p1.compareTo(p2);
		System.out.println(ret3);

		Integer[] arr = { 5, 3, 2, 7, 100 };

		Arrays.sort(arr, Comparator.reverseOrder()); // 역순 정렬

		for (int num2 : arr) {
			System.out.print(num2 + " ");
		}

		System.out.println();
		
		ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(1, 2, 53, 2, 30, 3));

//		Collections.sort(arr2);
		Collections.sort(arr2, Comparator.reverseOrder());
		
		for (int num2 : arr2) {
			System.out.print(num2 + " ");
		}
		
		// Sort Libraary
		// 작은 값을 왼쪽에 배치되게 하기 위해 
		// Compare(왼쪽값, 오른쪽값) 결과가 -1이라면 그냥 두지만
		// 결과가 1이라면 두 값을 swap한다.
		
		// 내가 정한 기준대로 Sort 하는 방법
		// Compare 함수를 재정의한다.
		// this와 other를 비교해서 적으면 -1 크면 1 같으면 0
	}
}

class Person implements Comparable<Person> {
	int height;
	int age;

	public Person(int height, int age) {
		super();
		this.height = height;
		this.age = age;
	}

	@Override
	public int compareTo(Person other) {
		if (this.age < other.age)
			return -1;
		if (this.age > other.age)
			return 1;

//		if (this.height < other.height) return -1;
//		if (this.height > other.height) return 1;
//		return 0;

		return Integer.compare(this.height, other.height);
	}
}