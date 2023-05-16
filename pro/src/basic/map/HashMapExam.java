package basic.map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/* HashMap VS TreeMap
 * HashMap : key ������ �߿����� ���� �� ���(TreeMap ���� ���� �ӵ�)
 * TreeMap : key ������ �߿��� �� ���
 * 
 * HashSet�� HashMap���� �����Ǿ� �ֱ� ������ HashMap�� ����Ѵ�.
 * 
 * HashMap
 * put() : ���� - O(1)
 * get() : �б�(������ null ����) - O(1)
 * */
public class HashMapExam {
	public static void main(String[] args) {

		HashMap<String, Boolean> hmap = new HashMap<>();

		hmap.put("AAA", true);
		hmap.put("BBB", true);
		hmap.put("CCC", false);
		hmap.put("DDD", true);
		
		System.out.println("hmap ������ : " + hmap.size());
		
		for (Map.Entry<String, Boolean> str : hmap.entrySet()) {
			
		}
		
		// ���  : Set��� Map�� ���.
		
		// Class�� Value�� ����ϱ�
		// put, get - O(1)
		HashMap<Integer, Node> hmapNode = new HashMap<>();
		hmapNode.put(1, new Node(10, 20));
		hmapNode.put(2, new Node(30, 40));
		hmapNode.put(3, new Node(50, 50));

		System.out.println("Y : " + hmapNode.get(2).y);
		System.out.println("X : " + hmapNode.get(2).x);

		// containsKey : O(1)
		// containsValue : O(N) -> ��ü �˻�
		HashMap<Integer, String> hmapStr = new HashMap<>();
		hmapStr.put(1, "AAA");
		hmapStr.put(2, "BBB");
		hmapStr.put(3, "CCC");

		System.out.println("containsKey : " + hmapStr.containsKey(2));
		System.out.println("containsValue : " + hmapStr.containsValue("AAA"));

		// Remove - O(1)
		// remove(key) : ��� ���� - O(1)
		// remove(key, value) : (key, value) �� �׸��� ���� - O(1)
		hmapStr.remove(3);
		System.out.println(hmapStr.get(3));
		
		// Replace - O(1)
		// replace(key, value) : key ���� ������ ��, value�� ����
		// replace(key, oldValue, newValue) : key ���� old�� �� new�� ����
		hmapStr.replace(2, "BBB", "ASD");
		System.out.println(hmapStr);
		System.out.println(hmapStr.get(2));

		// Collection�� ������ HashMap
		// HashMap + ArrayList
		// hmap2���� alist�� �������� �����Ѵ�.
		// alist�� ���� �߰��ϸ� hmap2������ ���� ���� �� �ִ�.
		HashMap<Integer, ArrayList<String>> hmap2 = new HashMap<>();
		ArrayList<String> alist = new ArrayList<>();
		hmap2.put(1, alist);
		hmap2.put(2, new ArrayList<>());
		
		alist.add("AAA");
		alist.add("BBB");
		alist.add("CCC");
		
		for (String str : hmap2.get(1)) {
			System.out.print(str + " ");
		}
		
		System.out.println();
		
		// HashMap + PriorityQueue
		// hmap���� pqc�� �������� �����Ѵ�.
		// pq�� ���� �߰��ϸ� hmap������ ���� ���� �� �ִ�.
		HashMap<Integer, PriorityQueue<Integer>> hmap3 = new HashMap<>();
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		hmap3.put(1, pq);
		
		pq.add(1);
		pq.add(3);
		pq.add(5);
		pq.add(7);
			
		System.out.print("hmap3�� 1��° �迭�� �� : ");
		while (hmap3.get(1).isEmpty() == false) {
			System.out.print(hmap3.get(1).poll() + " ");
		}

		// ū �� ��� �����ؼ� �ֱ� : Comparator.reverseOrder()
		HashMap<Integer, PriorityQueue<Integer>> hmap4 = new HashMap<>();
		PriorityQueue<Integer> pq4 = new PriorityQueue<>();
		hmap4.put(1, new PriorityQueue<>(Comparator.reverseOrder())); // ū������ ���(��������)
		
		hmap4.get(1).add(1);
		hmap4.get(1).add(3);
		hmap4.get(1).add(5);
		hmap4.get(1).add(7);
		
		
		// hmap3�� ������ item�� ����
		System.out.println();
		System.out.print("hmap4�� 1��° �迭�� �� : ");
		while (hmap4.get(1).isEmpty() == false) {
			System.out.print(hmap4.get(1).poll() + " ");
		}
		
		/* PQ, HashMap, TreeMap */
		
		/*** ID�� �ο� �� DAT ����ϱ� ***/
		
		
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
