package basic.map;

import java.util.HashMap;

/* getOrDefault
 * java 8에서 추가된 Collection API 함수.
 * 찾는 key가 존재한다면 key값 반환하고 없거나 null이면 default값을 반환
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
