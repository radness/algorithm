package pro.p230512;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class P230512 {
	public static void main(String[] args) {

		/*
		 * TreeSet은 HashSet과 마찬가지로 Set 인터페이스를 구현한 클래스 객체를 중복해서 저장할 수 없고 저장 순서가 유지되지
		 * 않는다(Set의 성질) TreeSet은 HashSet과 달리 이진탐색트리(Binary Search Tree) 구조로 이루어져 있다. 이진
		 * 탐색 트리는 추가와 삭제에는 시간이 조금 더 걸리지만 정렬, 검색에 높은 성능을 보이는 자료구조. HashSet보다 데이터의 추가와 삭제는
		 * 시간이 더 걸리지만 검색과 정렬에는 유리하다. TreeSet은 데이터를 저장할 시 이진탐색트리의 형태로 데이터를 저장하기 떄문에 기본적인
		 * nature ordering을 지원하며 생성자의 매개변수로 Comparator 객체를 입력하여 정렬 방법을 사용자 정의대로 지정할 수
		 * 있다.
		 */
		TreeSet<Integer> tset = new TreeSet<>();

		// 시간복잡도 : O(1)
		tset.add(1);
		tset.add(5);
		tset.add(2000);
		tset.add(3);

		System.out.println(tset);

		// 시간복잡도 : O(1)
		tset.remove(1); // 값 1 제거

		System.out.println("값 1 제거 후 전체 출력 : " + tset);

		System.out.println("tset의 크기는 : " + tset.size());

		System.out.println("최소값 출력 : " + tset.first());

		System.out.println("최대값 출력 : " + tset.last());

		// Iterator 사용
		Iterator iter = tset.iterator();
		while (iter.hasNext()) { // 값이 있으면 true, 없으면 false
			System.out.print(iter.next() + " ");
		}

		/*
		 * HashMap
		 */
		HashMap<Integer, String> hmap = new HashMap<>();

		hmap.put(1, "20");

		System.out.println();
		System.out.println("---------------------------");

		/*
		 * ArrayList
		 */

		/* PriorityQueue */
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node node1, Node node2) {
				return Integer.compare(node1.start, node2.start);
			}
		});

		pq.add(new Node(0, 4));
		pq.add(new Node(3, 10));
		pq.add(new Node(6, 7));
		pq.add(new Node(2, 30));

		System.out.println("start 순서대로 정렬");
		while (!pq.isEmpty()) {
			Node node = pq.poll();

			System.out.print(node.start + " ");
		}
	}

	static class Node {
		int start;
		int end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}
