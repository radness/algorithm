package pro.past.no24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

// 6G 보급망
class UserSolution {

	// 건물에 대한 정보
	static HashMap<Integer, Integer> hmid;
	// 위치정보
	static TreeMap<Integer, Integer> tLocMap;

	public void init(int N, int[] mId, int[] mLocation) {
		// 초기화
		hmid = new HashMap<>();
		tLocMap = new TreeMap<>();

		for (int i = 0; i < N; i++) {
			hmid.put(mId[i], mLocation[i]);
			tLocMap.put(mLocation[i], mId[i]); // install, remove할 때 사용
		}
	}

	// 추가(mId에 대한 mLocation정보)
	public int add(int mId, int mLocation) {
		Integer preLocation = hmid.get(mId);
		if (preLocation != null) { // 이미 존재하는 id
			// 기존의 건물은 철거한다.
			tLocMap.remove(preLocation);
		}
		hmid.put(mId, mLocation);
		tLocMap.put(mLocation, mId);
		// 건물의 총 갯수 반환
		return hmid.size();
	}

	public int remove(int mStart, int mEnd) {
		// 위치정보를 기준으로 특정 구간을 잘라서 탐색(tLocMap은 TreeMap이므로 정렬되어 있다.)
		Set<Entry<Integer, Integer>> subSet = tLocMap.subMap(mStart, true, mEnd, true).entrySet();

		ArrayList<Integer> removeItem = new ArrayList<>();

		for (Entry<Integer, Integer> entry : subSet) {
			removeItem.add(entry.getKey());
			hmid.remove(entry.getValue());
		}
		for (Integer item : removeItem) {
			tLocMap.remove(item);
		}
		// 남은 건물의 개수 반환
		return hmid.size();
	}

	// 이분탐색
	public int install(int M) {
		int left = 0;
		int right = 1000000000;
		int ans = 0;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (possible(mid, M)) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans;
	}

	private boolean possible(int dist, int M) {
		int cnt = 0;
		int cur = -1000000000;

		for (int next : tLocMap.keySet()) {
			if (cur + dist <= next) {
				cnt++;

				if (cnt >= M)
					return true;

				cur = next;
			}
		}
		return false;
	}
}