package pro.past.no3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class UserSolution {
	static PriorityQueue<Restaurant> pq; // 지역별 레스토랑
	static ArrayList<PriorityQueue<Restaurant>>[] area; // 지역
	static ArrayList<Integer>[] graph; // 지역별 연결정보
	static int[] used; // 방문 목록
	static Queue<Integer> queue;

	static class Restaurant implements Comparable<Restaurant> {
		String name;
		int score;

		public Restaurant(String name, int score) {
			this.name = name;
			this.score = score;
		}

		// 지역별 레스토랑 순위대로 정렬(큰수부터)
		@Override
		public int compareTo(Restaurant other) {
			return Integer.compare(this.score, other.score);
		}

	}

	// 커맨드 1
	public void init(int N, int M, int mRoads[][]) {
		System.out.println();

		area = new ArrayList[N + 1];
		graph = new ArrayList[N + 1];
		pq = new PriorityQueue<>();

		for (int i = 0; i <= N; i++) {
			area[i] = new ArrayList<>();
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int vill1 = mRoads[i][0];
			int vill2 = mRoads[i][1];

			graph[vill1].add(vill2);
			graph[vill2].add(vill1);
		}

//		System.out.println(graph);
	}

	// 커맨드 2
	public void addRestaurant(int mCityID, char mName[]) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mName.length; i++) {
			sb.append(mName[i]);
		}

		String resName = sb.toString().trim();

		PriorityQueue<Restaurant> newPQ = new PriorityQueue<>();
		newPQ.add(new Restaurant(resName, 0));

		area[mCityID].add(newPQ);

//		System.out.println("식당 추가,  mCityID : " + mCityID + " 레스토랑 이름 : " + resName);
	}

	/*
	 * mCityID : 카페가 위치한마을의 번호 (1 ≤ townID ≤ N) mName: 카페의 이름 (3 ≤ 이름의 길이 ≤ 5) 커맨드 3
	 */
	public void addValue(char mName[], int mScore) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mName.length; i++) {
			sb.append(mName[i]);
		}

		String resName = sb.toString().trim();

		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[i].size(); j++) {
				Restaurant restaurant = area[i].get(j).peek();

				if (restaurant.name.equals(resName)) {
					restaurant.score += mScore;

//					System.out.println("레스토랑 이름: " + resName + " 점수: " + restaurant.score);
				}
			}
		}
	}

	// 커맨드 4
	public int bestValue(char mStr[]) {
		int bestScore = 0;

		PriorityQueue<Integer> pqBest = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mStr.length; i++) {
			if (mStr[i] == 0) {
				break;
			}
			sb.append(mStr[i]);
		}

		String restaurantName = sb.toString().trim();

		for (int i = 1; i < area.length; i++) {
			for (int j = 0; j < area[i].size(); j++) {
				Restaurant restaurant = area[i].get(j).peek();

				if (restaurant.name.contains(restaurantName)) {
					pqBest.add(restaurant.score);
				}
			}
		}

//		System.out.println("bestVal : " + pqBest.peek());

		return pqBest.peek();
	}

	// mCityID : 출발 위치, mDist : 최대 거리
	// 커맨드 5
	public int regionalValue(int mCityID, int mDist) {
		int ans = 0; // 정답

		PriorityQueue<Restaurant> resPQ = new PriorityQueue<>(new Comparator<Restaurant>() {
			@Override
			public int compare(Restaurant o1, Restaurant o2) {
				return Integer.compare(o2.score, o1.score);
			}
		}); // 식당 목록

		used = new int[51];
		queue = new LinkedList<>();

		// mDist가 0인경우 현재 위치의 점수의 합을 return
		if (mDist == 0) {
			for (int i = 0; i < area[mCityID].size(); i++) {
				ans += area[mCityID].get(i).peek().score;
			}
//			System.out.println("mDist가 0인 경우 : " + ans);
			return ans;
		}

		// 갈수 있는 마을 전체에서 가장 가치가 높은 식당 3개의 총 합
		// 1. 갈 수 있는 마을 전체 식당 목록 구하기

		queue.add(mCityID);
		used[mCityID] = 1;

		for (int i = 0; i < area[mCityID].size(); i++) {
			Restaurant restaurant = area[mCityID].get(i).peek();
			resPQ.add(restaurant);
		}

		dfs(mCityID, mDist, resPQ);

		// 2. 식당 목록에서 가장 가치 높은 3개의 식당 점수 총합 구하기
		if (resPQ.size() < 3) {
			// 식당이 3개 이하라면 해당하는 모든 식당의 가치의 총 합.
			while (!resPQ.isEmpty()) {
				ans += resPQ.poll().score;
			}
		} else {
			// 거리내에 식당들 중 가장 가치가 높은 3개의 식당 총합
			for (int i = 0; i < 3; i++) {
				Restaurant r = resPQ.poll();
				ans += r.score;
			}
		}

//		System.out.println("regionalValue: " + ans);

		return ans;
	}

	private void dfs(int mCityID, int mDist, PriorityQueue<Restaurant> resPQ) {
		if (mDist == 0)
			return;

		int now = queue.poll();

		for (int i = mDist; i > 0; i--) {
			for (int next : graph[now]) {
				if (used[next] == 1) {
					continue;
				}
				used[next] = 1;
				queue.add(next);
			}

			while (!queue.isEmpty()) {
				now = queue.poll();

				for (int j = 0; j < area[now].size(); j++) {
					Restaurant restaurant = area[now].get(j).peek();
					resPQ.add(restaurant);
				}
			}
		}
	}
}