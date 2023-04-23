package basic;

import java.util.ArrayList;
import java.util.Collections;

/* �ּ� ��� ���� Ʈ���� ã�� �˰���
 * ���� ���� ������� ��� ����带 �����ϱ� ���� ����ϴ� �˰���
 * �ּ� ���д� Ʈ��(MST, Minimum Spanning Tree)�� ã�´�.
 * ������ ����ġ�� ���� �ּҰ��� �ǵ��� �ϴ� �˰���
 * ���д� Ʈ�� : �׷������� �Ϻ� ������ �����ؼ� ���� Ʈ��
 * �ּ� ���д� Ʈ�� : ���д� Ʈ�� �߿� ������ ������ ����ġ�� ���� �ּ��� Ʈ��
 * 
 * ���� ���� : E(����), �������� ���� : V(���)
 * -> �ð����⵵ : O(ElogV)
 * -> Union-Find ���信 ���� ������ ����Ǿ�� �Ѵ�.
*/
public class KruskalAlgorithmExam {
	static int[] parent;
	static ArrayList<Node> edgeList;
	static int ans = 0;

	public static void main(String[] args) {
		edgeList = new ArrayList<>();
		edgeList.add(new Node(1, 2, 5));
		edgeList.add(new Node(1, 3, 4));
		edgeList.add(new Node(2, 3, 2));
		edgeList.add(new Node(2, 4, 7));
		edgeList.add(new Node(3, 4, 6));
		edgeList.add(new Node(3, 5, 11));
		edgeList.add(new Node(4, 5, 8));
		edgeList.add(new Node(4, 6, 8));
		edgeList.add(new Node(5, 6, 8));

		parent = new int[6 + 1];

		for (int i = 1; i <= 6; i++) {
			parent[i] = i;
		}

		Collections.sort(edgeList);

		for (int i = 0; i < edgeList.size(); i++) {
			Node node = edgeList.get(i);

			if (!isSameParent(node.from, node.to)) {
				ans += node.cost;
				union(node.from, node.to);
			}
		}

		System.out.println(ans);
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y)
			parent[y] = x;

	}

	private static int find(int val) {
		if (parent[val] == val)
			return val;

		return parent[val] = find(parent[val]);
	}

	private static boolean isSameParent(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y)
			return true;

		return false;
	}

	static class Node implements Comparable<Node> {
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			if (this.cost < other.cost)
				return -1;

			if (this.cost > other.cost)
				return 1;

			return 0;
		}
	}
}
