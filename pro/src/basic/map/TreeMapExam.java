package basic.map;

import java.util.Map;
import java.util.TreeMap;

/* HashMap : key ������ �߿����� ���� �� ���
 * TreeMap : key ������ �߿��� �� ���
 * 
 * Ordered�� TreeMap�� ����� ������ compare ������ �����ϴ�.
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
		
		// key ���� �����ϴ��� Ȯ��
		// containsKey�� ���������� .get�� ���� ������ ����.
		// get() / containsKey() ��� O(logN)
		System.out.println("AAA : " + tmap2.get("AAA")); // ������ null
		System.out.println("ABC : " + tmap2.containsKey("ABC") );	// ������ false
		
		// containsValue() : O(N)
		// TreeMap�� ��� ������ �˻��Ͽ� �ش� value�� �����ϴ��� Ȯ��
		System.out.println(tmap2.containsValue(100));
		
		// replace(key, value)
		// Ư�� ���� �����ϸ� �����Ѵ�.
		// ���� ������ ���� x
		// O(logN)
		tmap2.replace("AAA", 400);
		System.out.println("AAA ���� : " + tmap2.get("AAA"));

		// replace(key, oldValue, newValue)
		// key���� Ư�� ���̶�� �ٸ� ������ �����ϰڴ�.
		// replace ��� -> O(logN), �Ⱦ��� O(2logN)
		
		// remove()
		// �ð����⵵ : O(logN)
		tmap2.remove("BBB");
		System.out.println("BBB : " + tmap2.get("BBB"));
		
		// ���� �켱������ ���� �� return
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
		// ceiling : ũ�ų� ���� �͵� �߿�, ���� ���� ��(�ִ� �ѵ� ��)
		// higher : ū �͵� �߿�, ���� ���� ��(�ִ� �ѵ��� �Ѿ ��)
		Map.Entry<String, Integer> abc = tmap3.ceilingEntry("ABC");
		System.out.println("ABC�� ceiling key : " + abc.getKey() + " value : " + abc.getValue());
		
		Map.Entry<String, Integer> bbb = tmap3.higherEntry("BBB");
		System.out.println("BBB�� higher key : " + abc.getKey() + " value : " + abc.getValue());
		
		// floor / lower
		// floor : �۰ų� ���� �͵��߿� ���� ū ��(�ּ� �ѵ� ��)
		// lower : ���� �͵��߿� ���� ū ��(�ּ� �ѵ��� �Ѿ ��)
		Map.Entry<String, Integer> aaa = tmap3.floorEntry("AAA");
		System.out.println("AAA�� ceiling key : " + abc.getKey() + " value : " + abc.getValue());
		
		Map.Entry<String, Integer> ccc = tmap3.lowerEntry("CCC");
		System.out.println("CCC�� ceiling key : " + abc.getKey() + " value : " + abc.getValue());
		
		
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
