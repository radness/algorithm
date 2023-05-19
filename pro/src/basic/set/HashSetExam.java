package basic.set;

import java.util.HashSet;

/* HashSet
 * ������� �Էµ��� �ʰ�, �����ϰ� �������� �ʴ´�.
 * null�� �����
 * �ߺ��� ������� �ʴ´�.
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
		System.out.println("1 ����");
		System.out.println(hset);
		
		System.out.println("1�� �����ϴ°�? " + hset.contains(1));
		
	}
}
