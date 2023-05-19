package basic.queue;

import java.util.PriorityQueue;

public class PriorityQueueExam4 {
	public static void main(String[] args) {

		// 앞이 크면 1 작으면 -1 같으면 0
		int ret = Integer.compare(2, 3);
		
		System.out.println(ret);
		
		Integer num = 100;
		int ret2 = num.compareTo(200);
		
		System.out.println(ret2);
		
		// String은 간격
		String str1 = "DBS";
		String str2 = "ADE";
		
		// 양수면 사전적으로 앞에 있음. 음수면 사적적으로 뒤에 잇다
		System.out.println(str1.compareTo(str2));
		
		// 덩어리끼리 비교하기
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
