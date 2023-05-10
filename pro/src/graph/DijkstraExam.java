package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
�Է�
5
9
1 2 10
1 3 5
2 3 2
3 1 1
3 2 13
4 1 8
4 5 3
5 4 9
5 2 31
4

���
0 8 18 13 0 3
*/
public class ���ͽ�Ʈ��1 {
	static List<Node>[] graph;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("src/�׷���/���ͽ�Ʈ��1_�Է�"));
		// �Է�
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// ������ ����, ������ ����
		int n = Integer.parseInt(bf.readLine());
		int m = Integer.parseInt(bf.readLine());

		graph = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++)
			graph[i] = new ArrayList<>();

		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[v].add(new Node(w, cost));
		}

		int start = Integer.parseInt(bf.readLine());

		// ���ͽ�Ʈ�� �˰��� ����
		Dijkstra(n, start);
	}

	// n: ����� ũ��, start: �����
	// ����� ũ��, �����
	public static void Dijkstra(int n, int start) {
		boolean[] check = new boolean[n + 1];
		int[] dist = new int[n + 1];
		int INF = Integer.MAX_VALUE;

		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			int nowVertex = pq.poll().index;

			if (check[nowVertex])
				continue;
			check[nowVertex] = true;

			// index�� ����� ���� ��
			for (Node next : graph[nowVertex]) {
				if (dist[next.index] > dist[nowVertex] + next.cost) {
					dist[next.index] = dist[nowVertex] + next.cost;

					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}

		// �ּҰŸ� ���
		for (int i : dist) {
			if (i == INF)
				System.out.print(0 + " ");
			else
				System.out.print(i + " ");
		}
	}

	// �켱���� ť�� ������ȣ + ����ġ ���忡 �ʿ�
	static class Node implements Comparable<Node> {
		int index; // ���� ��ȣ
		int cost; // ����ġ(=���)

		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		/* cost �߽����� �켱������ �������� ������ compareTo �������̵� */
		@Override
		public int compareTo(Node o) {
			return Integer.compare(cost, o.cost);
		}
	}
}
