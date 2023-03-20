package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// ���� ����Ʈ ����
public class BFS_Test {
	static int N;
	static int M;
	static int V;

	static List<Integer>[] adList;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // ������ ����
		M = Integer.parseInt(st.nextToken()); // ������ ����
		V = Integer.parseInt(st.nextToken()); // Ž���� ������ ������ ��ȣ

		adList = new LinkedList[N + 1];
		visit = new boolean[N + 1]; // �湮 �迭

		for (int i = 0; i <= N; i++) {
			adList[i] = new LinkedList<>();
		}

		// ����
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			adList[v1].add(v2);
			adList[v2].add(v1);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(adList[i]); // �湮 ������ ���ؼ� �������� ����
		}

		System.out.println("bfs ��������Ʈ");

		bfs(V, adList, visit);
	}

	private static void bfs(int v, List<Integer>[] adList, boolean[] visit) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		visit[v] = true;	// �湮 üũ
		
		q.add(v);	// ť�� �Է�
		
		while (!q.isEmpty()) {
			v = q.poll();	// ������
			System.out.println("V : " + v);
			
			Iterator<Integer> iter = adList[v].listIterator();
			
			while (iter.hasNext()) {
				int item = iter.next();
				if (!visit[item]) {
					visit[item] = true;
					q.add(item);
				}
			}
		}
	
	}
}
