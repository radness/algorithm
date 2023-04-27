package basic.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HashMapExam2 {
	// ID값 부여 후 DAT 사용하기
	
	// Hash 접근속도 VS DAT 접근 속도 비교
	// Hash < DAT
	// DAT(배열)이 Hash보다 1.5배 빠르다.
			
	// 다량의 이름과 나이가 주어진다.
	// 특정 이름이 주어지면 동명이인 중 가장 적은 나이와 가장 많은 나이 찾기
	public static void main(String[] args) {
		Solution sol = new Solution();
	
		sol.addPerson("BOB", 32);
		sol.addPerson("JON", 34);
		sol.addPerson("BOB", 28);
		sol.addPerson("KIM", 28);
		
		System.out.println("BOB의 최대 나이 : " + sol.getMaxAge("BOB"));
		System.out.println("BOB의 최소 나이 : " + sol.getMaxAge("BOB"));
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
