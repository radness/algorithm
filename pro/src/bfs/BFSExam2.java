package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSExam2 {
	public static void main(String[] args) throws Exception {
		/*
		 * BFS(�ʺ� �켱 Ž��)
		 * -> ť �ڷᱸ�� Queue ���� ���� ������ �� ������ ���� ���� ��� ������ �ȵȴ�.
		 * ������ ���� ó���� �����ߴ� �����͸� ���� �� �ִ�.
		 * ������ ���� ó���� �����ߴ� �����͸� ������ �� �ִ�.
		 */

		// Tree BFS Ž�� �ڵ�
		ArrayList<Integer>[] alist = new ArrayList[6];

		alist[0] = new ArrayList<>(Arrays.asList(1, 4));
		alist[1] = new ArrayList<>(Arrays.asList(2, 3));
		alist[2] = new ArrayList<>();
		alist[3] = new ArrayList<>();
		alist[4] = new ArrayList<>(Arrays.asList(5));
		alist[5] = new ArrayList<>();

		Queue<Integer> q = new LinkedList<>();

		q.add(0);

		while (!q.isEmpty()) {
			Integer now = q.poll();

			System.out.print(now + " "); // Ž�� ������� ��� ��ȣ ���

			for (Integer next : alist[now]) {
				q.add(next);
			}
		}
		
		// Graph BFS
		// �ѹ� ������ �ɾ��� ���� �ߺ� ������ �ɸ��� �ʵ��� used ó�����ش�.
		ArrayList<Integer>[] alist2 = new ArrayList[5];
		alist2[0] = new ArrayList<>(Arrays.asList(1, 2));
		alist2[1] = new ArrayList<>(Arrays.asList(2, 3));
		alist2[2] = new ArrayList<>(Arrays.asList(0, 3));
		alist2[3] = new ArrayList<>();
		
		Queue<Integer> q2 = new LinkedList<>();
		int[] used = new int[3];
		
		q2.add(0);
		used[0] = 1;
		
		while (!q2.isEmpty()) {
			Integer now = q2.poll();
			
			System.out.print(now + " ");
			
			for(Integer next : alist2[now]) {
				if (used[next] == 1)	// �湮�ߴٸ� �Ѿ��.
					continue;
				
				used[next] = 1;	// �湮 ó��
				q.add(next);	// ť�� �߰�
			}
		}
 	}
}
