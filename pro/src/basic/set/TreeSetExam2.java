package basic.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/* TreeSet
 * JDK 1.2부터 제공
 * Set 인터페이스를 구현한 클래스
 * 객체를 중복해서 저장할 수 없고 저장 순서가 유지되지 않는다.(Set 성질과 동일)
 * HashSet과 다르게 이진탐색트리(Binary Search Tree)구조로 이루어져 있다.
 * 이진탐색트리는 추가와 삭제에는 시간이 조금 더 걸리지만 정렬, 검색에 높은 성능을 보이는 자료구조.
 * -> 검색과 정렬
 * nature ordering을 지원
 * 생성자의 매개변수로 Comparator 객체를 입력하여 정렬 방법을 임의로 정할 수 있다.
 * 
 * */
public class TreeSetExam2 {
	public static void main(String[] args) {

		TreeSet<Integer> tset1 = new TreeSet<>();

		TreeSet<Integer> tset2 = new TreeSet<>(Arrays.asList(1, 2, 3)); // 초기값 지정

		// 추가
		tset1.add(7);
		tset1.add(10);
		tset1.add(2);
		tset1.add(333);
		tset1.add(2);
		tset1.add(2);

		// 오름차순 정렬된 데이터로 출력
		System.out.println(tset1);

		// 삭제
		tset1.remove(7);	// 값 삭제
		System.out.println(tset1);

		int size = tset1.size();
		System.out.println("TreeSet의 사이즈 : " + size);
		
		tset1.clear();	// 모든 값 삭제
		System.out.println(tset1);
		
		tset1.add(7);
		tset1.add(10);
		tset1.add(2);
		tset1.add(333);
		tset1.add(120);
		tset1.add(2);
		tset1.add(2);
		
		// 값 가져오기
		System.out.println(tset1.first());	// 최소값 출력
		System.out.println(tset2.last());	// 최대값 출력
		
		System.out.println(tset1.higher(30));	// 입력 값보다 큰 데이터중 최소값 출력, 없으면 null
		System.out.println(tset1.lower(30));	// 입력 값보다 작은 데이터중 최댓값 출력, 없으면 null
		
		System.out.println();
		
		Iterator<Integer> iter = tset1.iterator();
		
		while (iter.hasNext()) {	// 값이 존재하면 true 없으면 false
			System.out.println(iter.next());	// 출력
		}

		TreeSet<Person> person = new TreeSet<>();
		
		person.add(new Person("김XX", 30));
		person.add(new Person("이XX", 20));
		person.add(new Person("박XX", 43));
		
		Iterator<Person> iter2 = person.iterator();
		
		// 나이 순으로 정렬된 person 을 출력
		while (iter2.hasNext()) {
			Person p = iter2.next();
			System.out.println("이름 : " + p.name + " 나이 : " + p.age);
		}
	}
	
	// Person 객체 생성 후 나이순으로 정렬(오름차순)
	static class Person implements Comparable<Person> {
		private String name; 
		private int age;
		
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		@Override
		public int compareTo(Person other) {
			if (this.age < other.age)
				return -1;
			else if (this.age > other.age)
				return 1;

			return 0;
		}
		
		public String getName() {
			return name;
		}
		
		public int getAge() {
			return age;
		}
		
	}
}
