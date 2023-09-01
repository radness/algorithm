package pro.past.no20;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

// 프린터 공장
class UserSolution {

	static class Printer {
		int id;
		int printTime;

		public Printer(int id, int printTime) {
			this.id = id;
			this.printTime = printTime;
		}
	}

	static HashMap<Integer, Printer> hmap;
	static TreeSet<Printer> tset;

	public void init(int N, int mId[], int pTime[]) {
		hmap = new HashMap<Integer, Printer>();
		tset = new TreeSet<>(new Comparator<Printer>() {

			@Override
			public int compare(Printer o1, Printer o2) {
				// 1. 오래 걸리는 순
				if (o1.printTime > o2.printTime)
					return -1;
				if (o1.printTime < o2.printTime)
					return 1;
				// 2. id가 큰 프린터 순
				if (o1.id > o2.id)
					return -1;
				if (o1.id < o2.id)
					return 1;
				return 0;
			}
		});

		for (int i = 0; i < N; i++) {
			hmap.put(mId[i], new Printer(mId[i], pTime[i]));
			tset.add(new Printer(mId[i], pTime[i]));
		}
	}

	public int add(int mId, int pTime) {
		if (hmap.get(mId) != null) {
			Printer p = hmap.get(mId);
			tset.remove(p);
			p.printTime = pTime;
			hmap.put(mId, p);
			tset.add(p);
		} else {
			hmap.put(mId, new Printer(mId, pTime));
			tset.add(new Printer(mId, pTime));
		}

//		System.out.println("add : " + hmap.size());
		return hmap.size();
	}

	public int remove(int k) {
		while (k > 0) {
			Printer p = tset.pollFirst();
			hmap.remove(p.id);
			k--;
		}

		int ans = tset.first().id;
//		System.out.println("remove : " + ans);
		return ans;
	}

	public int produce(int M) {
		// 범위에서 찾기(가장빠른 시간 ~ 가장느린 시간)
		// parametric search
		// int start = 이론적으로 가능한 최소값;
		// int end = 이론적으로 가능한 최대값;
		int start = tset.last().printTime;
		int end = start * M + 1;

		int tmp;
		
		while (start < end) {
			tmp = 0;
			int mid = (start + end) / 2;
			// mid값이 정답이 될 수 있는지 체크

			for (Printer p : tset) {
				tmp += mid / p.printTime;
			}
			
			if (tmp >= M) {
				System.out.println("produce : " + tmp);
				return tmp;
			}
			
			start = mid + 1;
			end = mid;
		}

		return 0;
	}
}