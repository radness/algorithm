package pro.past.no20;

import java.util.HashMap;
import java.util.TreeSet;

class UserSolution2 {

	static class Printer {
		int id;
		int time;

		public Printer(int id, int time) {
			this.id = id;
			this.time = time;
		}
	}

	static TreeSet<Printer> tset;
	static HashMap<Integer, Printer> hmap;

	public void init(int N, int mId[], int[] pTime) {
		hmap = new HashMap<>();
		tset = new TreeSet<>((a, b) -> {
			if (a.time > b.time)
				return a.id - b.id;

			return a.time - b.time;
		});

		for (int i = 0; i < N; i++) {
			add(mId[i], pTime[i]);
		}
	}

	public int add(int mId, int pTime) {
		// 중복여부 확인
		// 기입력된 프린터라면 -> 이전정보 수정 or 삭제 후 재입력
		if (hmap.containsKey(mId)) {
			Printer p = hmap.get(mId);
			tset.remove(p);
			hmap.remove(mId);
		}
		Printer p = new Printer(mId, pTime);
		tset.add(p);
		hmap.put(mId, p);

		return hmap.size();
	}

	public int remove(int k) {
		// 우선순위 순서로 k개 삭제 -> 2가지 자료구조 모두(정합성)
		return 0;
	}

	public int produce(int M) {
		// M개
		// 범위에서 찾기(가장빠른 시간 ~ 가장느린 시간)
		// parametric search
		/*
		 * int start = 최소값; int end = 최대값 + 1; while (start < end) { int mid = (start +
		 * end) / 2; // mid값이 정답이 될 수 있는지 체크
		 * 
		 * start = mid + 1; end = mid; }
		 */

		return 0;
	}
}
