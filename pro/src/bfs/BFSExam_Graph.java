package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/* Graph BFS
 * �ѹ� ������ �ɾ��� ���� �ߺ� ������ �ɸ��� �ʵ���
 * used ó�����ش�.
 * */
public class BFSExam_Graph {
	public static void main(String[] args) {
		// ��������Ʈ �ʱ�ȭ
		ArrayList<Integer>[] alist = new ArrayList[4];
		alist[0] = new ArrayList<>(Arrays.asList(1, 2));
		alist[1] = new ArrayList<>(Arrays.asList(2, 3));
		alist[2] = new ArrayList<>(Arrays.asList(0, 3));
		alist[3] = new ArrayList<>();

		Queue<Integer> q = new LinkedList<>();
		q.add(0);

		int[] used = { 1, 0, 0, 0 };
		
		while (!q.isEmpty()) {
			Integer now = q.poll();	// �̾ƿͼ�
			System.out.println("���� �� : " + now);
			
			for (Integer next : alist[now]) {
				if (used[next] == 1)
					continue;
				
				used[next] = 1;	// �湮 ó��
				q.add(next);	// ť�� �߰�
				
			}
		}
	}
}
