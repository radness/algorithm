package basic.set;

import java.util.TreeSet;

public class TreeSetExam3 {
	public static void main(String[] args) {

		// 나열되는 순서를 지정해주고 싶다면
		// HashSet대신 TreeSet을 사용한다.
		TreeSet<Integer> tset = new TreeSet<>();

		tset.add(999);
		tset.add(999);
		tset.add(10);
		tset.add(15);
		tset.add(15);
		tset.add(999);

		// TreeSet은 중복을 없앤 값을 오름차순으로 관리한다.
		System.out.println(tset);

		TreeSet<Node> tsetNode = new TreeSet<>();
		
		tsetNode.add(new Node(999));
		tsetNode.add(new Node(999));
		tsetNode.add(new Node(10));
		tsetNode.add(new Node(15));
		tsetNode.add(new Node(15));
		tsetNode.add(new Node(999));
		
		System.out.print("Comparable구현을 통한 TeeSet 역순 정렬 : ");
		
		while (!tsetNode.isEmpty()) {
			System.out.print(tsetNode.pollFirst().n + " ");
		}
	}

	// Node 클래스에 Comparable을 구현하여 원하는대로 순서를 관리할 수 있다.
	static class Node implements Comparable<Node> {
		int n;

		public Node(int n) {
			this.n = n;
		}

		// TreeSet 내림차순 정렬
		@Override
		public int compareTo(Node other) {
			return Integer.compare(other.n, this.n);
		}

	}
}
