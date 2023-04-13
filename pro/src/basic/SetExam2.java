package basic;

import java.util.Set;
import java.util.TreeSet;

// Comparable 사용하기
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

		// PQ -> 우선순위가 가장 높은것을 뽑아낼 때
		// TreeSet -> 중간 우선순위 등수를 뽑아낼 때
		
	}

	static class Node implements Comparable<Node> {
		int n;

		public Node(int n) {
			this.n = n;
		}

		// 내림차순 정렬
		@Override
		public int compareTo(Node other) {
			return Integer.compare(other.n, this.n);
		}

	}
}
