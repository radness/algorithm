package basic.set;

import java.util.TreeSet;

public class TreeSetExam3 {
	public static void main(String[] args) {

		// �����Ǵ� ������ �������ְ� �ʹٸ�
		// HashSet��� TreeSet�� ����Ѵ�.
		TreeSet<Integer> tset = new TreeSet<>();

		tset.add(999);
		tset.add(999);
		tset.add(10);
		tset.add(15);
		tset.add(15);
		tset.add(999);

		// TreeSet�� �ߺ��� ���� ���� ������������ �����Ѵ�.
		System.out.println(tset);

		TreeSet<Node> tsetNode = new TreeSet<>();
		
		tsetNode.add(new Node(999));
		tsetNode.add(new Node(999));
		tsetNode.add(new Node(10));
		tsetNode.add(new Node(15));
		tsetNode.add(new Node(15));
		tsetNode.add(new Node(999));
		
		// PriorityQueueó�� ����� �� �ִ�.
		// -> �ӵ��� PQ�� ������.
		// PQ : �켱������ ���� ������, ������ �� �̾Ƴ� ��
		// TreeSet : �߰� �켱���� ����� �̾Ƴ� ��
		System.out.print("Comparable������ ���� TeeSet ���� ���� : ");
		
		while (!tsetNode.isEmpty()) {
			System.out.print(tsetNode.pollFirst().n + " ");
		}
	}

	// Node Ŭ������ Comparable�� �����Ͽ� ���ϴ´�� ������ ������ �� �ִ�.
	static class Node implements Comparable<Node> {
		int n;

		public Node(int n) {
			this.n = n;
		}

		// TreeSet �������� ����
		@Override
		public int compareTo(Node other) {
			return Integer.compare(other.n, this.n);
		}

	}
}
