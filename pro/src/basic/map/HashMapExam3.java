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
		
		sol.addPersion("raeni", 32);
		sol.addPersion("raeni", 34);
		sol.addPersion("raeni", 28);

		System.out.println("Max Age : " + sol.getMaxAge("raeni"));
		System.out.println("Min Age : " + sol.getMinAge("raeni"));
	}
	
	static class Solution {
		
		HashMap<String, Integer> StrToId = new HashMap<>();
		
		int id;

		PriorityQueue<Integer>[] datMaxPQ = new PriorityQueue[1000];
		PriorityQueue<Integer>[] datMinPQ = new PriorityQueue[1000];
		
		void addPersion(String name, int age) {
			
			if (!StrToId.containsKey(name)) {
				StrToId.put(name, ++id);
				
				datMaxPQ[id] = new PriorityQueue<>(Comparator.reverseOrder());
				datMinPQ[id] = new PriorityQueue<>();
			}
			
			int id = StrToId.get(name);
			
			datMaxPQ[id].add(age);
			datMinPQ[id].add(age);
		}
		
		int getMaxAge(String name) {
			int id = StrToId.getOrDefault(name, -1);
			if (id != -1)
				return datMaxPQ[id].poll();
			return 0;
		}
		
		int getMinAge(String name) {
			int id = StrToId.getOrDefault(name, -1);
			if (id != -1)
				return datMinPQ[id].poll();
			return 0;
		}
		
	}
}
