package pro.past.no5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 보험사 직원
class Solution2 {
	static class Case implements Comparable<Case> {
		int timeStamp; // 발생시각
		int caseID; // 사건 번호
		int cityID; // 사건 발생 도시 번호
		int priority; // 우선순위
		boolean isCancel;

		public Case(int timeStamp, int caseID, int cityID, int priority) {
			this.timeStamp = timeStamp;
			this.caseID = caseID;
			this.cityID = cityID;
			this.priority = priority;
		}

		@Override
		public int compareTo(Case other) {
			if (this.priority == other.priority)
				return this.timeStamp - other.timeStamp;
			return other.priority - this.priority;
		}
	}

	static PriorityQueue<Case> pq;

	static int[][] dist;
	static int curTime; // 현재시간
	static int curPosition; // 현재 위치
	static ArrayList<Integer>[] loads; // 도로
	static Case[] caseList; // 사건 목록

	public void init(int N, int[] parent) {
		final int INF = 1_000_000_000;
		pq = new PriorityQueue<>();
		dist = new int[N][N];
		curTime = 0;
		curPosition = 0;
		loads = new ArrayList[N];
		caseList = new Case[100000];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0; // 자기자신
			loads[i] = new ArrayList<>();
		}

		// 첫번째 제외
		for (int from = 1; from < N; from++) {
			int to = parent[from];
			dist[from][to] = 1; // 1시간이 걸린다.
			dist[to][from] = 1;
			loads[from].add(to);
			loads[to].add(from);
		}

		// 플로이드 와샬
		for (int mid = 0; mid < N; mid++) {
			for (int from = 0; from < N; from++) {
				for (int to = 0; to < N; to++) {
					if (dist[from][to] > dist[from][mid] + dist[mid][to]) {
						dist[from][to] = dist[from][mid] + dist[mid][to];
					}
				}
			}
		}
	}

	// 발생한 사건 접수 [2]
	public void occur(int timeStamp, int caseID, int cityID, int priority) {
		position(timeStamp);
		caseList[caseID] = new Case(timeStamp, caseID, cityID, priority);
		pq.add(caseList[caseID]);
	}

	// 사건 접수 취소 [3]
	public void cancel(int timeStamp, int caseID) {
		position(timeStamp);
		caseList[caseID].isCancel = true;
	}

	// timeStamp시각에 위치한 민철의 위치 반환 [4]
	public int position(int timeStamp) {
		while (!pq.isEmpty()) {
			if (curTime == timeStamp)
				break;
			
			Case cur = pq.peek(); // 현재 사건
			
			if (cur.isCancel)
				pq.poll();
			else if (spentOneHour(cur.cityID)) // 시간 보내기
				pq.poll();
		}
		curTime = timeStamp;
		return curPosition;
	}

	private boolean spentOneHour(int target) {
		curTime++;
		// 보험 처리
		if (curPosition == target)
			return true;

		for (int next : loads[curPosition]) {
			if (dist[curPosition][target] > dist[next][target]) {
				curPosition = next;
				break;
			}
		}
		return false;
	}
}
