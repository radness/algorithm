package mini_contast.PAST_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

// 6G 보급망
class UserSolution {
	HashMap<Integer, Integer> hmid;
	TreeMap<Integer, Integer> locationMap;

	public void init(int N, int[] mId, int[] mLocation) {
		hmid = new HashMap<>();
		locationMap = new TreeMap<>();
		for (int i = 0; i < N; i++) {
			hmid.put(mId[i], mLocation[i]);
			locationMap.put(mLocation[i], mId[i]);
		}
	}

	public int add(int mId, int mLocation) {
		Integer preLocation = hmid.get(mId);
		if (preLocation != null) {
			locationMap.remove(preLocation);
		}
		hmid.put(mId, mLocation);
		locationMap.put(mLocation, mId);
		return hmid.size();
	}

	public int remove(int mStart, int mEnd) {
		Set<Entry<Integer, Integer>> subSet = locationMap.subMap(mStart, true, mEnd, true).entrySet();
		ArrayList<Integer> removeItem = new ArrayList<>();
		for (Entry<Integer, Integer> entry : subSet) {
			removeItem.add(entry.getKey());
			hmid.remove(entry.getValue());
		}
		for (Integer item : removeItem) {
			locationMap.remove(item);
		}
		return hmid.size();
	}

	public int install(int M) {
		int l = 0;
		int r = 1_000_000_000;
		int answer = 0;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (가능한가(mid, M)) {
				answer = mid;
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return r;
	}

	private boolean 가능한가(int dist, int M) {
		int cnt = 0;
		int cur = -1_000_000_000;
		for (int next : locationMap.keySet()) {
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