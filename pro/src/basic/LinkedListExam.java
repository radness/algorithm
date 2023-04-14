package basic;

import java.util.LinkedList;

// LinkedList
// ������ + ���� ��� ���� ����
public class LinkedListExam {
	public static void main(String[] args) throws Exception {
		
		LinkedList<Integer> list = new LinkedList<>();
		
		// �� �߰�
		list.add(1);
		list.add(2);
		list.add(3);
		
		System.out.println(list);
		
		// �ε��� 1���� ������ 10 �߰�
		list.add(1, 10);
		
		// �� ����
		list.remove(2);
		System.out.println(list);
		
		// �� �տ� �� �߰�
		list.addFirst(100);
		System.out.println(list);
		
		// �� �ڿ� �� �߰�
		list.addLast(300);
		System.out.println(list);
		
		// �� Ȯ��
		// ���� �����ϸ� true, ������ false
		System.out.println(list.contains(1));
		
	}
}
