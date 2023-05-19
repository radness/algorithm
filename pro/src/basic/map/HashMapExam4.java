package basic.map;

import java.util.HashMap;

/* getOrDefault
 * java 8���� �߰��� Collection API �Լ�.
 * ã�� key�� �����Ѵٸ� key�� ��ȯ�ϰ� ���ų� null�̸� default���� ��ȯ
 */
public class HashMapExam4 {
	public static void main(String[] args) {
		String[] str = { "A", "B", "C", "D" };

		HashMap<String, Integer> hmap = new HashMap<>();

		for (String key : str) {
			hmap.put(key, hmap.getOrDefault(key, 0));
		}
		
		System.out.println(hmap);
	}
}
