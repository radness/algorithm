package basic;

import java.util.Set;
import java.util.TreeSet;

// Comparable ����ϱ�
public class SetExam2 {
	public static void main(String[] args) throws Exception {

		Set<Node> tset = new TreeSet<>();

		tset.add(new Node(999));
		tset.add(new Node(999));
		tset.add(new Node(999));
		tset.add(new Node(10));
		tset.add(new Node(115));

		for (Node node : tset) {
			System.out.print(node.n + " ");
		}

		// PQ -> �켱������ ���� �������� �̾Ƴ� ��
		// TreeSet -> �߰� �켱���� ����� �̾Ƴ� ��
		
	}

	static class Node implements Comparable<Node> {
		int n;

		public Node(int n) {
			this.n = n;
		}

		// �������� ����
		@Override
		public int compareTo(Node other) {
			return Integer.compare(other.n, this.n);
		}

	}
}
