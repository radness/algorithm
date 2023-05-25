package basic.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

// ID�� �ο��ϱ�
// ID���� �ο��Ѵ�. �̶��� Hash�� ����Ѵ�.
// ���� ���ʹ� ID���� ����� DAT(�迭)�� ����Ѵ�.
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
