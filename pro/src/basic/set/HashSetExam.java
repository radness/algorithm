package basic.set;

import java.util.HashSet;

/* HashSet
 * 순서대로 입력되지 않고, 일정하게 유지되지 않는다.
 * null도 허용함
 * 중복을 허용하지 않는다.
 * 
 * 
 * */
public class HashSetExam {
	public static void main(String[] args) {
		HashSet<Integer> hset = new HashSet<>();
		
		hset.add(30);
		hset.add(2);
		hset.add(1);
		hset.add(5);
		hset.add(1);
		hset.add(1);
		hset.add(1);
		hset.add(-10);
		hset.add(40);
		hset.add(null);
		
		System.out.println(hset);
		
		hset.remove(1);
		System.out.println("1 삭제");
		System.out.println(hset);
		
		System.out.println("1이 존재하는가? " + hset.contains(1));
		
	}
}
