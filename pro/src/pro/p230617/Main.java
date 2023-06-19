package pro.p230617;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// start, end ���� �ִܰŸ� ���
	// �ִܰŸ� �˰��� -> ���ͽ�Ʈ�� -> ��������Ʈ �׷��� + �켱���� ť ���
	// ��� ��� Ž�� (dfs)

	static class Node implements Comparable<Node> {
		int id;
		int cost;

		// ������ ���� ��ȣȭ ���(����ġ) ����
		public Node(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}

		// cost �߽����� �켱������ �������� ������ compareTo �޼��� �������̵�
		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.cost, other.cost);
		}
	}

	static ArrayList<Node>[] graph = new ArrayList[7];
	static int[] used = new int[7];

	public static void main(String[] args) throws Exception {
		/*
		 * pq�� �켱���� ť�� ������ ��������� �������� ���� �ּ� �Ÿ��� ����. �켱������ �Ÿ��� ª������ ����. check�� boolean
		 * �迭�� �ش� ������ �湮�ϴ��� üũ�Ѵ�. dist�� int �迭�� ������κ��� �ּ� �Ÿ��� �����Ѵ�.

		 */

		long startTime = System.currentTimeMillis();
		
		System.setIn(new FileInputStream("src/pro/p230617/sample_input2.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 7; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < 7; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[node1].add(new Node(node2, cost));
			graph[node2].add(new Node(node1, cost));
		}

		System.out.println("Node ���� �Է� �Ϸ�");

		int firstCase = Integer.MAX_VALUE;
		int secondCase = Integer.MAX_VALUE;
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			if (cost == findMinCost(start, end)) {
				System.out.println(start + "���� " + end + "���� �����ϴ� �ּ� ����� : " + cost);
			} else {
				System.out.println("����. ����� : " + cost);
			}
			
			if (i == 1 || i == 2) {
				firstCase = Math.min(firstCase, cost);
			} else {
				secondCase = Math.min(secondCase, cost);
			}
		}
		
		System.out.println("������ : " + (firstCase + secondCase));
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("�ҿ�ð� : " + (endTime - startTime) + "ms");
	}

	// start���� end���� �����ϴ� �ּ� ���
	private static int findMinCost(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();

		int[] cost = new int[7];
		Arrays.fill(cost, Integer.MAX_VALUE);

		pq.add(new Node(start, 0));
		cost[start] = 0;

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			for (Node next : graph[now.id]) {
				if (cost[next.id] <= cost[now.id] + next.cost)
					continue;
				
				cost[next.id] = cost[now.id] + next.cost;
				
				pq.add(new Node(next.id, cost[next.id]));
			}
		}

		return cost[end];
	}
}
