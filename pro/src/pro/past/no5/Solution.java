package pro.past.no5;

import java.util.ArrayList;
import java.util.TreeMap;

// 보험사 직원
class Solution {

	static int SIZE;
	// 발생한 시간 순서
	// key : 도시 번호
	// value : 부모 도시 번호
	static TreeMap<Integer, Case> tmap;
	
	static int[] TIME; // 시간별 민철의 위치

	static ArrayList<Integer>[] graph;
	static int[] PARENTS;

	static class Case implements Comparable<Case> {
		int time; // 발생시각
		int caseNo; // 사건 번호
		int cityNo; // 사건 발생 도시 번호
		int priority; // 우선순위

		public Case(int time, int caseNo, int cityNo, int priority) {
			this.time = time;
			this.caseNo = caseNo;
			this.cityNo = cityNo;
			this.priority = priority;
		}

		@Override
		public int compareTo(Case other) {
			// 1. 우선순위 큰 경우
			if (this.priority < other.priority)
				return -1;
			if (this.priority > other.priority)
				return 1;
			// 2. 빨리 발생한 사건
			if (this.time > other.time)
				return -1;
			if (this.time < other.time)
				return 1;
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public void init(int N, int[] parent) {
		SIZE = N;
		TIME = new int[5000001];
		PARENTS = new int[N];

		graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		tmap = new TreeMap<>();
		for (int i = 0; i < N; i++) {
			PARENTS[i] = parent[i];

			// 그래프 연결
			graph[i].add(parent[i]);
			if (parent[i] == -1)
				continue;
			graph[parent[i]].add(i);
		}
	}

	// 발생한 사건 접수 [2]
	public void occur(int timeStamp, int caseID, int cityID, int priority) {
		tmap.put(timeStamp, new Case(timeStamp, caseID, cityID, priority));
	}
	
	// 사건 접수 취소 [3]
	public void cancel(int timeStamp, int caseID) {

	}

	// timeStamp시각에 위치한 민철의 위치 반환 [4] 
	public int position(int timeStamp) {
		if (tmap.isEmpty())
			return 0;
		
		// 목표 사건
		Case now = tmap.floorEntry(timeStamp).getValue();
	
		// 1. 목표 사건이 없는 경우
		
		// 2. 목표 사건의 위치에 도착한 경우
		
		// 3. 목표 사건의 위치에 도착하지 못한 경우
		
		
		return now.cityNo;
	}
}