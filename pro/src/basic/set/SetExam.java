package basic;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/* Set
 * 중복을 제거할 수 있는 Set 자료구조
 * 
 * HashSet 기본 사용법
 * 출력결과를 보면, 정해진 순서 없이 출력됨(Unordered)
 * ->중복을 없애지만 오름차순, 내림차순 규칙이 없다. 
 * 
 * TreeSet
 * 나열되는 순서를 지정해주고 싶다면 HashSet 대신 TreeSet을 사용.
 * -> 중복을 없앤 값을 오름차순으로 관리
 * 
 * 속도는 HashSet > TreeSet
 * 
 */

public class SetExam {
	public static void main(String[] args) {
		Set<Integer> hset = new HashSet<>();
		hset.add(999);
		hset.add(999);
		hset.add(3);
		hset.add(10);
		hset.add(2);
		System.out.println("HashSet : " + hset);

		Set<Integer> tset = new TreeSet<>();
		tset.add(999);
		tset.add(999);
		tset.add(3);
		tset.add(10);
		tset.add(2);
		System.out.println("TreeSet : " + tset);
	}
}
