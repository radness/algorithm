package basic.priority_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CompareExam {
	public static void main(String[] args) throws Exception {

		// Compare �޼���
		// ù��° ���� ������ -1, ������ 0, ũ�� 1
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

		Arrays.sort(arr, Comparator.reverseOrder()); // ���� ����

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
		// ���� ���� ���ʿ� ��ġ�ǰ� �ϱ� ���� 
		// Compare(���ʰ�, �����ʰ�) ����� -1�̶�� �׳� ������
		// ����� 1�̶�� �� ���� swap�Ѵ�.
		
		// ���� ���� ���ش�� Sort �ϴ� ���
		// Compare �Լ��� �������Ѵ�.
		// this�� other�� ���ؼ� ������ -1 ũ�� 1 ������ 0
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