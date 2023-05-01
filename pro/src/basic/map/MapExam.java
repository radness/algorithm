package basic;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* Map
 * 
 * HashMap
 * 배열에서 저장할 위치를 나타내는 index값은 항상 0보다 같거나 큰 정수이어야 한다.
 * 저장할 위치 값을 음수, 문자열 등을 사용하고 싶을 때 HashMap을 사용한다.
 * 
 * TreeMap
 * Ordered -> 순서를 지정할 수 있다.
 * key값이 사전순으로 출력된다.
 * 
 * */
public class MapExam {
	public static void main(String[] args) throws Exception {
		// HashMap
		Map<String, Integer> hmap = new HashMap<>();

		hmap.put("A", 100);
		hmap.put("C", 300);
		hmap.put("B", 600);

		// key값에 해당하는 value값 출력
		System.out.println(hmap.get("A"));

		// key 존재 여부 확인
		// containsKey() 메서드 : boolean값 리턴
		if (hmap.containsKey("A")) {
			System.out.println("A 의 값 : " + hmap.get("A"));
		} else {
			System.out.println("해당 key는 존재하지 않습니다.");
		}

		// map의 속도 -> O(1) (딜레이가 조금 있다.)

		// HashMap 전체 출력하기
		// key, value 모두 출력하기
		// 순서가 보장되지 않는다.(Unordered)
		// Entry collection 사용
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

