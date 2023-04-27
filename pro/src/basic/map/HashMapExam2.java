package basic.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HashMapExam2 {
	// ID�� �ο� �� DAT ����ϱ�
	
	// Hash ���ټӵ� VS DAT ���� �ӵ� ��
	// Hash < DAT
	// DAT(�迭)�� Hash���� 1.5�� ������.
			
	// �ٷ��� �̸��� ���̰� �־�����.
	// Ư�� �̸��� �־����� �������� �� ���� ���� ���̿� ���� ���� ���� ã��
	public static void main(String[] args) {
		Solution sol = new Solution();
	
		sol.addPerson("BOB", 32);
		sol.addPerson("JON", 34);
		sol.addPerson("BOB", 28);
		sol.addPerson("KIM", 28);
		
		System.out.println("BOB�� �ִ� ���� : " + sol.getMaxAge("BOB"));
		System.out.println("BOB�� �ּ� ���� : " + sol.getMaxAge("BOB"));
	}

	static class Solution {
		HashMap<String, PriorityQueue<Integer>> maxHmap = new HashMap<>();
		HashMap<String, PriorityQueue<Integer>> minHmap = new HashMap<>();
		
		void addPerson(String name, int age) {
			if (!maxHmap.containsKey(name)) {
				maxHmap.put(name, new PriorityQueue<>(Comparator.reverseOrder()));
				minHmap.put(name, new PriorityQueue<>());
			}
			maxHmap.get(name).add(age);
			minHmap.get(name).add(age);
		}
		
		int getMaxAge(String name) {
			if (maxHmap.containsKey(name))
				return maxHmap.get(name).poll();
			return 0;
		}
		
		int getMinAge(String name) {
			if (minHmap.containsKey(name))
				return minHmap.get(name).poll();
			return 0;
		}
	
	}
}
