package expedition.day13.PRO_06;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

// 식당의 가치
class UserSolution {

	// index : 도시 번호(1~N)
	// value : 이 도시에 존재하는 식당 이름 목록
	static ArrayList<String>[] restaurants;

	// key : 레스토랑 이름(유니크)
	// value : 레스토랑의 점수
	static HashMap<String, Integer> scores;

	// key : 모든 식당 이름의 모든 이어진 substring
	// value : substring을 가진 식당 중 최대 점수
	static HashMap<String, Integer> maxScores;

	static ArrayList<Integer>[] aList; // 그래프 구성

	static int cityCnt; // 도시 개수

	// N : 마을의 갯수
	// M : 도로의 갯수(간선의 갯수)
	// mRoads[i][0] = from
	// mRoads[i][1] = to
	public void init(int N, int M, int mRoads[][]) {
		restaurants = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			restaurants[i] = new ArrayList<>();
		}

		scores = new HashMap<>();
		maxScores = new HashMap<>();

		// 그래프 구성
		aList = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			aList[i] = new ArrayList<>(); // 초기화
		}

		// 간선 정보
		for (int i = 0; i < M; i++) {
			int from = mRoads[i][0];
			int to = mRoads[i][1];

			// 그래프에 간선 추가(양방향)
			aList[from].add(to);
			aList[to].add(from);
		}

		cityCnt = N;
	}

	public void addRestaurant(int mCityID, char mName[]) {
		// mCity번 도시에 mName 식당이 추가된다.
		String name = getName(mName);
		restaurants[mCityID].add(name);

		// 처음 추가되는 레스토랑(고유한 이름)
		// 처음 점수가 매겨지지 않은 상태의 레스토랑 = 0점
		scores.put(name, 0);
	}

	private String getName(char[] mName) {
		// 식당 이름은 알파벳으로 주어지기 때문에
		// 알파벳이 끊어질 때까지만 문자열을 결합
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mName.length; i++) {
			char now = mName[i];
			// 알파벳이 소문자가 아니라면 문자열이 완성
			if (now < 'a' || now > 'z')
				break;
			sb.append(now);
		}
		return sb.toString();
	}

	public void addValue(char mName[], int mScore) {
		// mName 식당이 평점 mScore점수를 받는다(누적된다)
		String name = getName(mName);

		// 점수 누적
		scores.put(name, scores.get(name) + mScore);

		int score = scores.get(name);

		// 현재 레스토랑에 대한 모든 순차적인 substring을 만들어서 -> maxScore 관리
		for (int i = 0; i < name.length(); i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = i; j < name.length(); j++) {
				sb.append(name.charAt(j));
				String subStr = sb.toString();

				// subStr에 대한 최대 점수 관리
				if (maxScores.get(subStr) == null)
					maxScores.put(subStr, score);
				else
					maxScores.put(subStr, Math.max(score, maxScores.get(subStr)));
			}
		}
	}

	public int bestValue(char mStr[]) {
		String name = getName(mStr);

		// 0점인 경우
		if (maxScores.get(name) == null)
			return 0;

		return maxScores.get(name);
	}

	public int regionalValue(int mCityID, int mDist) {
		// mCityID번 마을에서 mDist 이하 도로를 거쳐 도달할 수 있는 식당들 중
		// 가장 가치가 높은 식당 3개의 가치의 총 합을 반환
		// 3개 미만일 경우는 다 합쳐서 리턴

		// 1. mCityID부터 살펴본다(BFS)
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(mCityID);

		int[] visited = new int[cityCnt + 1];
		visited[mCityID] = 1;

		// MIN Heap(작은거 부터)
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		while (!q.isEmpty()) {
			int now = q.poll();

			// mDist 이상을 가면 더 가볼 필요가 없다.
			if (visited[now] - 1 > mDist)
				break;

			// 이 도시에 존재하는 식당을 검색
			for (int i = 0; i < restaurants[now].size(); i++) {
				String name = restaurants[now].get(i);

				// 이름은 상관없고, 가장 높은 점수의 레스토랑 3개 뽑기
				pq.add(scores.get(name));
			}

//			for(int next = 1; next <= cityCnt; next++) {
//				if(mat[now][next] == 0)
//					continue;
//				if(visited[next] != 0)
//					continue;
//				visited[next] = visited[now] + 1;
//				q.add(next); 
//			}

			for (int i = 0; i < aList[now].size(); i++) {
				int next = aList[now].get(i);

				if (visited[next] != 0)
					continue;

				visited[next] = visited[now] + 1;

				q.add(next);
			}
		}

		// pq에는 내가 지금 방문했던 모든 도시에 존재하는
		// 레스토랑의 점수들이 기록되어 있다.
		int sum = 0;

		// 3개 미만
		if (pq.size() < 3) {
			while (!pq.isEmpty()) {
				sum += pq.remove();
			}
		} else {
			for (int i = 0; i < 3; i++) {
				sum += pq.remove();
			}
		}

		return sum;
	}
}