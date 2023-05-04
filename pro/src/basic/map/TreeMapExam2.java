package basic.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/* TreeMap
 * 이진트리를 기반으로 하는 Map Collection
 * TreeSet은 값만 저장한다면 TreeMap은 key와 value가 저장되는 Map, Entry를 저장하는 것
 * 자동으로 정렬됨.
 * key는 저장과 동시에 오름차순으로 정렬되고 숫자 타입인 경우 값으로
 * 문자 타입인 경우 유니코드로 정렬된다.
 * key값이 낮으면 왼쪽 높으면 오른쪽
 * HashMap보다 성능이 떨어진다.
 * 즉시 정렬하기 때문에 add나 delete가 HashMap보다 오래 걸린다.
 * 정렬된 데이터를 조회해야 하는 범위 검색인 경우 TreeMap이 효율적이다.
 * 
 * */
public class TreeMapExam2 {
	public static void main(String[] args) throws Exception {

		// 트리맵 선언
		TreeMap<Integer, String> tmap1 = new TreeMap<>();

		tmap1.put(1, "사과");
		tmap1.put(2, "배");
		tmap1.put(3, "포도");

		System.out.println(tmap1);

		/*
		 * TreeMap과 HassMap은 기본적으로 Map 인터페이스를 동일하게 상속 받으므로 기본적인 메소드의 사용법 자체는 동일하다.
		 * 
		 * 값 추가 : put(key, value) 입력되는 key 값이 TreeMap 내부에 존재한다면 기존의 값은 새로 입력되는 값으로 변경된다.
		 */

		// 값 삭제
		tmap1.remove(1); // key값 1 제거

		System.out.println(tmap1);

		tmap1.clear(); // 모든 값 제거

		System.out.println(tmap1);

		System.out.println();

		tmap1.put(1, "사과");
		tmap1.put(2, "배");
		tmap1.put(5, "딸기");
		tmap1.put(3, "포도");

		System.out.println(tmap1); // 전체 출력

		// key 1의 value 가져오기
		System.out.println(tmap1.get(1));
		// 최소 Entry 출력
		System.out.println(tmap1.firstEntry());
		// 최소 key 출력
		System.out.println(tmap1.firstKey());
		// 최대 Entry 출력
		System.out.println(tmap1.lastEntry());
		// 최대 key 출력
		System.out.println(tmap1.lastKey());

		// 전체 값 출력
		// entrySet() 활용
		System.out.println("전체 값 출력");
		for (Entry<Integer, String> entry : tmap1.entrySet()) {
			System.out.println("[Key]: " + entry.getKey() + " [Value] : " + entry.getValue());
		}

		// KeySet() 활용
		System.out.println("KeySet() 활용");
		for (Integer i : tmap1.keySet()) { // 저장된 key값 확인
			System.out.println("[Key]: " + i + " [Value]: " + tmap1.get(i));
		}

		// Iterator 사용
		TreeMap<Integer, String> tmap3 = new TreeMap<Integer, String>() {
			private static final long serialVersionUID = 1L;

			{// 초기값 설정
				put(1, "사과");// 값 추가
				put(2, "복숭아");
				put(3, "수박");
			}
		};

		System.out.println();

		// entrySet().iterator()
		Iterator<Entry<Integer, String>> entries = tmap3.entrySet().iterator();

		while (entries.hasNext()) {
			Map.Entry<Integer, String> entry = entries.next();
			System.out.println("[Key]:" + entry.getKey() + " [Value]:" + entry.getValue());
		}
		
		System.out.println();
		
		Iterator<Integer> keys = tmap3.keySet().iterator();
		
		while (keys.hasNext()) {
			int key = keys.next();
			System.out.println("[Key] : " + key);
		}
		
	}
}
