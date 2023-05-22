package pro.p230512;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class P230512 {
	public static void main(String[] args) {

		/*
		 * TreeSet�� HashSet�� ���������� Set �������̽��� ������ Ŭ���� ��ü�� �ߺ��ؼ� ������ �� ���� ���� ������ ��������
		 * �ʴ´�(Set�� ����) TreeSet�� HashSet�� �޸� ����Ž��Ʈ��(Binary Search Tree) ������ �̷���� �ִ�. ����
		 * Ž�� Ʈ���� �߰��� �������� �ð��� ���� �� �ɸ����� ����, �˻��� ���� ������ ���̴� �ڷᱸ��. HashSet���� �������� �߰��� ������
		 * �ð��� �� �ɸ����� �˻��� ���Ŀ��� �����ϴ�. TreeSet�� �����͸� ������ �� ����Ž��Ʈ���� ���·� �����͸� �����ϱ� ������ �⺻����
		 * nature ordering�� �����ϸ� �������� �Ű������� Comparator ��ü�� �Է��Ͽ� ���� ����� ����� ���Ǵ�� ������ ��
		 * �ִ�.
		 */
		TreeSet<Integer> tset = new TreeSet<>();

		// �ð����⵵ : O(1)
		tset.add(1);
		tset.add(5);
		tset.add(2000);
		tset.add(3);

		System.out.println(tset);

		// �ð����⵵ : O(1)
		tset.remove(1); // �� 1 ����

		System.out.println("�� 1 ���� �� ��ü ��� : " + tset);

		System.out.println("tset�� ũ��� : " + tset.size());

		System.out.println("�ּҰ� ��� : " + tset.first());

		System.out.println("�ִ밪 ��� : " + tset.last());

		// Iterator ���
		Iterator iter = tset.iterator();
		while (iter.hasNext()) { // ���� ������ true, ������ false
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

		System.out.println("start ������� ����");
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
