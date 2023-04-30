package basic.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/* ArrarDeque
 * Deque("��")�� Double Ended Queue(�����ť)�� ���Ӹ�
 * Dequeue���� ���� "ArrayDeque" �� ����Ѵ�.
 * 
 * ArrayDeque ��� �뵵
 * Stack, Queue ��� ���
 * 
 *  Deque�� ������ �������ؼ� ���� �����带 ������ �� ���
 *  ArrayDeque�� Deque�� ���� ���� �� ������.
*/
public class ArrayDequeExam {
	public static void main(String[] args) throws Exception {
		Deque<Integer> stack = new ArrayDeque<>();

		stack.push(1);
		stack.push(3);
		stack.push(7);

		System.out.println("������ �� : " + stack.peek());

		while (!stack.isEmpty()) {
			// Stack collection�� ������ ����.
			// �߰� : push() - �� ���ʿ� �߰���
			// �б� : peek()
			// �б�&���� : pop()
			int now = stack.pop();
			System.out.print(now + " ");
		}

		System.out.println();
		
		// ������ �ִ� element �߰�
		// add, push�� ���� ���� -> O(1)
		// addLast()
		// addFirst()
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.addLast(1);
		deque.addLast(2);
		deque.addLast(3);
		deque.addFirst(9);
		deque.addFirst(8);
		deque.addFirst(7);
		
		System.out.println(deque);	// [7, 8, 9, 1, 2, 3]
		
		// poll()�� ���� ���� -> O(1)
		// pollLast()
		// pollFirst()
		deque.pollLast();	// �ڿ� �ִ� ��
		deque.pollFirst();	// �տ� �ִ� ��
		
		System.out.println(deque);	// [8, 9, 1, 2]
		
		// peek() �� ���� ���� -> O(1)
		// peekFirst()
		// peekLast()
		System.out.println("ó�� �� : " + deque.peekFirst());
		System.out.println("������ �� : " + deque.peekLast());
	
		// Stack�� Queue�� ����ϴ� ��� -> Deque
		// �߰��� ����� element�� �а������ ArrayList
		
		
	}
}

