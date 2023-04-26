package basic.map;

import java.util.HashMap;
import java.util.Map;

/* HashMap VS TreeMap
 * HashMap : key 순서가 중요하지 않을 때 사용
 * TreeMap : key 순서가 중요할 때 사용
 * 
 * HashSet은 HashMap으로 구현되어 있기 때문에 HashMap을 사용한다.
 * 
 * HashMap
 * put() : 삽입 - O(1)
 * get() : 읽기(없으면 null 리턴) - O(1)
 * */
public class HashMapExam {
	public static void main(String[] args) {

		HashMap<String, Boolean> hmap = new HashMap<>();

		hmap.put("AAA", true);
		hmap.put("BBB", true);
		hmap.put("CCC", false);

		for (Map.Entry<String, Boolean> str : hmap.entrySet()) {
			
		}
		
		// Class를 Value로 사용하기
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

