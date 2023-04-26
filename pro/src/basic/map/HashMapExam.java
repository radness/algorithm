package basic.map;

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
		HashMap<Integer,Node> hmapNode = new HashMap<>();
		hmapNode.put(1,  new Node(10, 20));
		hmapNode.put(2,  new Node(30, 40));
		hmapNode.put(3,  new Node(50, 50));
		
		System.out.println("Y : " + hmapNode.get(2).y);
		System.out.println("X : " + hmapNode.get(2).x);
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

