package basic.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

// ID값 부여하기
// ID값을 부여한다. 이때만 Hash를 사용한다.
// 이후 부터는 ID값을 사용한 DAT(배열)을 사용한다.
public class HashMapExam3 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		
		
	}
	
	static class Solution {
		HashMap<String, Integer> StrToId = new HashMap<>();
		int id;
		
		PriorityQueue<Integer>[] dataMaxPQ = new PriorityQueue[1000];
		PriorityQueue<Integer>[] dataMinPQ = new PriorityQueue[1000];
		
		void addPersion(String name, int age) {
			if (!StrToId.containsKey(name)) {
				StrToId.put(name, ++id);	// 1번부터 값 부여
				// 1번부터 id에 생성 후 추가
				dataMaxPQ[id] = new PriorityQueue<>(Comparator.reverseOrder());
				dataMinPQ[id] = new PriorityQueue<>();
			}
			
			id = StrToId.get(name);
			
			dataMaxPQ[id].add(age);
			dataMaxPQ[id].add(age);
		}
		
		int getMaxAge(String name) {
			// 같지만 속도가 getOrDefault가 더 빠르다.
			// getOrDefault : 있으면 값을 리턴하고 없으면 -1을 default로 리턴한다.
//			if (StrToId.containsKey(name)) {	// 두번 읽는다.
//				id = StrToId.get(name);
//			}
			// getOrDefault : 
			int id = StrToId.getOrDefault(name, -1);
			if (id != -1)
				return dataMaxPQ[id].poll();
			
			return 0;
		}
		
		int getMinAge(String name) {
			int id = StrToId.getOrDefault(name, -1);
			if (id != -1)
				return dataMaxPQ[id].poll();
			
			return 0;
		}
	}
}
