package basic.set;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/* TreeSet
 * JDK 1.2���� ����
 * Set �������̽��� ������ Ŭ����
 * ��ü�� �ߺ��ؼ� ������ �� ���� ���� ������ �������� �ʴ´�.(Set ������ ����)
 * HashSet�� �ٸ��� ����Ž��Ʈ��(Binary Search Tree)������ �̷���� �ִ�.
 * ����Ž��Ʈ���� �߰��� �������� �ð��� ���� �� �ɸ����� ����, �˻��� ���� ������ ���̴� �ڷᱸ��.
 * -> �˻��� ����
 * nature ordering�� ����
 * �������� �Ű������� Comparator ��ü�� �Է��Ͽ� ���� ����� ���Ƿ� ���� �� �ִ�.
 * 
 * */
public class TreeSetExam2 {
	public static void main(String[] args) {

		TreeSet<Integer> tset1 = new TreeSet<>();

		TreeSet<Integer> tset2 = new TreeSet<>(Arrays.asList(1, 2, 3)); // �ʱⰪ ����

		// �߰�
		tset1.add(7);
		tset1.add(10);
		tset1.add(2);
		tset1.add(333);
		tset1.add(2);
		tset1.add(2);

		// �������� ���ĵ� �����ͷ� ���
		System.out.println(tset1);

		// ����
		tset1.remove(7);	// �� ����
		System.out.println(tset1);

		int size = tset1.size();
		System.out.println("TreeSet�� ������ : " + size);
		
		tset1.clear();	// ��� �� ����
		System.out.println(tset1);
		
		tset1.add(7);
		tset1.add(10);
		tset1.add(2);
		tset1.add(333);
		tset1.add(120);
		tset1.add(2);
		tset1.add(2);
		
		// �� ��������
		System.out.println(tset1.first());	// �ּҰ� ���
		System.out.println(tset2.last());	// �ִ밪 ���
		
		System.out.println(tset1.higher(30));	// �Է� ������ ū �������� �ּҰ� ���, ������ null
		System.out.println(tset1.lower(30));	// �Է� ������ ���� �������� �ִ� ���, ������ null
		
		System.out.println();
		
		Iterator<Integer> iter = tset1.iterator();
		
		while (iter.hasNext()) {	// ���� �����ϸ� true ������ false
			System.out.println(iter.next());	// ���
		}

	}
}
