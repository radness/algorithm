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
		
		
	}
	
	static class Solution {
		HashMap<String, Integer> StrToId = new HashMap<>();
		int id;
		
		PriorityQueue<Integer>[] dataMaxPQ = new PriorityQueue[1000];
		PriorityQueue<Integer>[] dataMinPQ = new PriorityQueue[1000];
		
		void addPersion(String name, int age) {
			if (!StrToId.containsKey(name)) {
				StrToId.put(name, ++id);	// 1������ �� �ο�
				// 1������ id�� ���� �� �߰�
				dataMaxPQ[id] = new PriorityQueue<>(Comparator.reverseOrder());
				dataMinPQ[id] = new PriorityQueue<>();
			}
			
			id = StrToId.get(name);
			
			dataMaxPQ[id].add(age);
			dataMaxPQ[id].add(age);
		}
		
		int getMaxAge(String name) {
			// ������ �ӵ��� getOrDefault�� �� ������.
			// getOrDefault : ������ ���� �����ϰ� ������ -1�� default�� �����Ѵ�.
//			if (StrToId.containsKey(name)) {	// �ι� �д´�.
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
