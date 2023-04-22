package acmicpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/* ���ͽ�Ʈ��
 * �ϳ��� �������� �ٸ� ��� ���������� �ּ� �Ÿ�
 * 
 * �÷��̵�ͼ�
 * ��� �������� �ٸ� ��� ���������� �ּ� �Ÿ�
 * 
 * �ּҺ�� ���ϱ�
 * -> �ϳ��� ���ÿ��� �ּҺ���� ���ϴ� ������ ���ͽ�Ʈ�� ���
 * */
public class BJ_1916 {
	static int N, M;
	static ArrayList<Node>[] graph;// ��������Ʈ �׷���
	static int[] dist; // �ִܰŸ� ���� �迭
	static boolean[] visited; // �湮 ���� �迭
	static int INF = Integer.MAX_VALUE;
	static int ans = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // ������ ����
		M = Integer.parseInt(br.readLine()); // ������ ����
		
		graph = new ArrayList[N + 1];	// ���� ���� ����
		dist = new int[N + 1];
		visited = new boolean[N + 1];

		// �׷��� �ʱ�ȭ
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = INF;
		}

		// �׷��� �Է� �ޱ�
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int cost = Integer.parseInt(input[2]);
			
			graph[from].add(new Node(to, cost));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		findMin(start);
		
		System.out.println(dist[end]);
		
	}

	private static void findMin(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));	// ��������, ���

		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			visited[now.index] = true;
			
			for (Node next : graph[now.index]) {
				if (visited[next.index])
					continue;
				
				if (dist[next.index] > dist[now.index] + next.cost) {
					dist[next.index] = dist[now.index] + next.cost;
					pq.add(new Node(next.index, dist[next.index]));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int index;
		int cost;

		Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
//			return Integer.compare(cost, other.cost);
			return this.cost - other.cost;
		}
	}
}
