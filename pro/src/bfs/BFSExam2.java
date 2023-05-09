package bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BFSExam2 {
	public static void main(String[] args) throws Exception {
		/* BFS(�ʺ� �켱 Ž��) -> ť �ڷᱸ��
		 * Queue
		 * ���� ���� ������ �� ������ ���� ���� ��� ������ �ȵȴ�.
		 * ������ ���� ó���� �����ߴ� �����͸� ���� �� �ִ�.
		 * ������ ���� ó���� �����ߴ� �����͸� ������ �� �ִ�.
		 * 
		 * */
		Queue<Integer> q = new LinkedList<>();
		
		q.add(1);
		q.add(3);
		q.add(5);
		
		System.out.println(q.peek());// �б�
		
		System.out.println(q.poll());// �б� + ����
		
		while (!q.isEmpty()) {
			System.out.println(q.poll());
		}
	}
}
