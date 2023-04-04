package basic;

import java.util.Stack;

/* Stack
 * �����͸� �״� �ڷᱸ��
 * LIFO : Last In First Out
 * ���� ���߿� �� ���� ���� ó������ ������ �ڷᱸ��
 * DFSŽ��, ��� �Լ��� ȣ���� �� ���
 * */
public class StackExam {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();	// int�� ���� ����
		Stack<String> stack2 = new Stack<>();	// String�� ����
		
		// Stack �� �߰�
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		while (!stack.isEmpty()) {	// �ݴ��� ������ ���
			int val = stack.pop();
			System.out.println(val);
		}
		
		stack.push(4);
		stack.push(5);
		stack.push(6);
		
		stack.pop();	// Stack �� ����
		
		int lastVal = stack.peek();	// Stack�� ���� �ֱ��� �Է¹��� �� ���(���� ����� ��)
		System.out.println("������ �Է� �� : " + lastVal);
		
		stack.clear();	// Stack �� �ʱ�ȭ
		
	}
}
