package basic;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* Map
 * 
 * HashMap
 * �迭���� ������ ��ġ�� ��Ÿ���� index���� �׻� 0���� ���ų� ū �����̾�� �Ѵ�.
 * ������ ��ġ ���� ����, ���ڿ� ���� ����ϰ� ���� �� HashMap�� ����Ѵ�.
 * 
 * TreeMap
 * Ordered -> ������ ������ �� �ִ�.
 * key���� ���������� ��µȴ�.
 * 
 * */
public class MapExam {
	public static void main(String[] args) throws Exception {
		// HashMap
		Map<String, Integer> hmap = new HashMap<>();

		hmap.put("A", 100);
		hmap.put("C", 300);
		hmap.put("B", 600);

		// key���� �ش��ϴ� value�� ���
		System.out.println(hmap.get("A"));

		// key ���� ���� Ȯ��
		// containsKey() �޼��� : boolean�� ����
		if (hmap.containsKey("A")) {
			System.out.println("A �� �� : " + hmap.get("A"));
		} else {
			System.out.println("�ش� key�� �������� �ʽ��ϴ�.");
		}

		// map�� �ӵ� -> O(1) (�����̰� ���� �ִ�.)

		// HashMap ��ü ����ϱ�
		// key, value ��� ����ϱ�
		// ������ ������� �ʴ´�.(Unordered)
		// Entry collection ���
		System.out.println("HashMap");
		
		for (Map.Entry<String, Integer> entry : hmap.entrySet()) {
			System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
		}
		
		// TreeMap
		Map<String, Integer> tmap = new TreeMap<>();
		tmap.put("ABC", 100);
		tmap.put("QWE", 1202);
		tmap.put("ZXC", 4309);
		
		System.out.println("TashMap");
		
		for (Map.Entry<String, Integer> entry : tmap.entrySet()) {
			System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
		}
		
	}
}

