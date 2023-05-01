package basic;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/* Set
 * �ߺ��� ������ �� �ִ� Set �ڷᱸ��
 * 
 * HashSet �⺻ ����
 * ��°���� ����, ������ ���� ���� ��µ�(Unordered)
 * ->�ߺ��� �������� ��������, �������� ��Ģ�� ����. 
 * 
 * TreeSet
 * �����Ǵ� ������ �������ְ� �ʹٸ� HashSet ��� TreeSet�� ���.
 * -> �ߺ��� ���� ���� ������������ ����
 * 
 * �ӵ��� HashSet > TreeSet
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
