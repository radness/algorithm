package basic.map;

import java.util.ArrayList;
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
		HashMap<Integer, Node> hmapNode = new HashMap<>();
		hmapNode.put(1, new Node(10, 20));
		hmapNode.put(2, new Node(30, 40));
		hmapNode.put(3, new Node(50, 50));

		System.out.println("Y : " + hmapNode.get(2).y);
		System.out.println("X : " + hmapNode.get(2).x);

		// containsKey : O(1)
		// containsValue : O(N) -> 전체 검색
		HashMap<Integer, String> hmapStr = new HashMap<>();
		hmapStr.put(1, "AAA");
		hmapStr.put(2, "BBB");
		hmapStr.put(3, "CCC");

		System.out.println(hmapStr.containsKey(2));
		System.out.println(hmapStr.containsValue("AAA"));

		// Remove - O(1)
		// remove(key) : 노드 삭제 - O(1)
		// remove(key, value) : (key, value) 인 항목을 삭제 - O(1)
		hmapStr.remove(3);
		System.out.println(hmapStr.get(3));
		
		// Replace - O(1)
		// replace(key, value) : key 값이 존재할 때, value로 변경
		// replace(key, oldValue, newValue) : key 값이 old일 때 new로 변경
		hmapStr.replace(2, "BBB", "ASD");
		System.out.println(hmapStr);
		System.out.println(hmapStr.get(2));
		
		// HashMpa + ArrayList
		// hmap2에는 alist의 참조값을 저장한다.
		// alist에 값을 추가하면 hmap2에서도 값을 읽을 수 있다.
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
