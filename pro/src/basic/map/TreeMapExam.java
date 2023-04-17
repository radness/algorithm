package basic.map;

import java.util.Map;
import java.util.TreeMap;

/* HashMap : key 순서가 중요하지 않을 때 사용
 * TreeMap : key 순서가 중요할 때 사용
 * 
 * Ordered인 TreeMap은 사용자 저으이 compare 구현이 가능하다.
 * */
public class TreeMapExam {
	public static void main(String[] args) throws Exception {
		TreeMap<Node, Integer> tmap = new TreeMap<>();
		
		tmap.put(new Node("ABC"), 100);
		tmap.put(new Node("DEF"), 200);
		tmap.put(new Node("ASD"), 300);
		
		for (Map.Entry<Node, Integer> entry : tmap.entrySet()) {
			System.out.println(entry.getKey().name + " " + entry.getValue());
		}
		
		TreeMap<String, Integer> tmap2 = new TreeMap<>();
		tmap2.put("AAA", 100);
		tmap2.put("BBB", 200);
		tmap2.put("CCC", 300);
		
		// key 값이 존재하는지 확인
		// containsKey는 내부적으로 .get과 동작 원리가 같다.
		// get() / containsKey() 모두 O(logN)
		System.out.println("AAA : " + tmap2.get("AAA")); // 없으면 null
		System.out.println("ABC : " + tmap2.containsKey("ABC") );	// 없으면 false
		
		// containsValue() : O(N)
		// TreeMap의 모든 내용을 검색하여 해당 value가 존재하는지 확인
		System.out.println(tmap2.containsValue(100));
		
		// replace(key, value)
		// 특정 값이 존재하면 변경한다.
		// 값이 없으면 동작 x
		// O(logN)
		tmap2.replace("AAA", 400);
		System.out.println("AAA 변경 : " + tmap2.get("AAA"));

		// replace(key, oldValue, newValue)
		// key값이 특정 값이라면 다른 값으로 변경하겠다.
		// replace 사용 -> O(logN), 안쓰면 O(2logN)
		
		// remove()
		// 시간복잡도 : O(logN)
		tmap2.remove("BBB");
		System.out.println("BBB : " + tmap2.get("BBB"));
		
		// 가장 우선순위가 높은 값 return
		// firstEntry() : O(logN)
		// lastEntry() : O(logN)
		TreeMap<String, Integer> tmap3 = new TreeMap<>();
		tmap3.put("ABC", 1);
		tmap3.put("AAA", 2);
		tmap3.put("BBB", 3);
		tmap3.put("CCC", 4);

		System.out.println(tmap3);
		
		System.out.println("first key: " + tmap3.firstKey());
		System.out.println("last key: " + tmap3.lastKey());
		
		// ceiling / higher
		// ceiling : 크거나 같은 것들 중에, 가장 작은 값(최대 한도 값)
		// higher : 큰 것들 중에, 가장 작은 값(최대 한도를 넘어선 값)
		Map.Entry<String, Integer> abc = tmap3.ceilingEntry("ABC");
		System.out.println("ABC의 ceiling key : " + abc.getKey() + " value : " + abc.getValue());
		
		Map.Entry<String, Integer> bbb = tmap3.higherEntry("BBB");
		System.out.println("BBB의 higher key : " + abc.getKey() + " value : " + abc.getValue());
		
		// floor / lower
		// floor : 작거나 같은 것들중에 가장 큰 값(최소 한도 값)
		// lower : 작은 것들중에 가장 큰 값(최소 한도를 넘어선 값)
		Map.Entry<String, Integer> aaa = tmap3.floorEntry("AAA");
		System.out.println("AAA의 ceiling key : " + abc.getKey() + " value : " + abc.getValue());
		
		Map.Entry<String, Integer> ccc = tmap3.lowerEntry("CCC");
		System.out.println("CCC의 ceiling key : " + abc.getKey() + " value : " + abc.getValue());
		
		
	}
	
	static class Node implements Comparable<Node>{
		String name;
		
		public Node(String key) {
			this.name = key;
		}

		@Override
		public int compareTo(Node o) {
			return o.name.compareTo(this.name);
		}
		
	}
}
