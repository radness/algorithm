package basic.queue;

import java.util.PriorityQueue;

public class PriorityQueueExam4 {
	public static void main(String[] args) {

		// ���� ũ�� 1 ������ -1 ������ 0
		int ret = Integer.compare(2, 3);
		
		System.out.println(ret);
		
		Integer num = 100;
		int ret2 = num.compareTo(200);
		
		System.out.println(ret2);
		
		// String�� ����
		String str1 = "DBS";
		String str2 = "ADE";
		
		// ����� ���������� �տ� ����. ������ ���������� �ڿ� �մ�
		System.out.println(str1.compareTo(str2));
		
		// ������� ���ϱ�
		PriorityQueue<Person> pq = new PriorityQueue<>();

		Person p1 = new Person(32, "jey");
		Person p2 = new Person(34, "rob");

		int ret3 = p1.compareTo(p2);
		System.out.println(ret3);
		
		
	}
	
	static class Person implements Comparable<Person> {
		int age;
		String name;
		
		public Person(int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Person other) {
			return Integer.compare(this.age, other.age);
		}
	}
}
