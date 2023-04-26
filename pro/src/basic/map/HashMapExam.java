package basic.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* HashMap VS TreeMap
 * HashMap : key ������ �߿����� ���� �� ���
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

		for (Map.Entry<String, Boolean> str : hmap.entrySet()) {

		}

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

		System.out.println(hmapStr.containsKey(2));
		System.out.println(hmapStr.containsValue("AAA"));

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
		
		// HashMpa + ArrayList
		// hmap2���� alist�� �������� �����Ѵ�.
		// alist�� ���� �߰��ϸ� hmap2������ ���� ���� �� �ִ�.
		HashMap<Integer, ArrayList<String>> hmap2 = new HashMap<>();
		ArrayList<String> alist = new ArrayList<>();
		hmap2.put(1, alist);
		
		alist.add("AAA");
		alist.add("BBB");
		alist.add("CCC");
		
		for (String str : hmap2.get(1)) {
			System.out.print(str + " ");
		}
		
		
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
