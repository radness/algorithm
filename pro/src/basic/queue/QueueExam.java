package basic.queue;

import java.util.LinkedList;
import java.util.Queue;

/* Queue : ���� ���� ������� ó���ϴ� �ڷᱸ��
 * FIFO : First In First Out
 * ���� ���� �����Ͱ� ���� ���� ������.
 * BFSŽ��, ���� ó���� �� ���
 * */
public class QueueExam {
	public static void main(String[] args) {
		// �ڹٿ��� ť�� LinkedList�� ����ؼ� ����
		Queue<Integer> queue = new LinkedList<>();
		Queue<String> queue2 = new LinkedList<>();
		
		// ť�� �� �߰�
		queue.add(1);
		queue.add(2);
		queue.add(3);
		
		while (!queue.isEmpty()) {
			int val = queue.poll();
			System.out.println(val);
		}
		
		queue.poll();	// ť�� ������� �� ���� ��ȯ�ϰ� ����ִٸ� null ��ȯ
		queue.remove();	// ť�� ù��° ��(���� ���� �� ��) ����
		queue.clear();	// ť �ʱ�ȭ
		
		queue.peek();	// ť�� ù��° �� ����
		
		
	}
}
