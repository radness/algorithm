package pro.p230512;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

class UserSolution230512 {
	class EMP {
		int start;
		int end;
		int gap;

		public EMP(int start, int end) {
			this.start = start;
			this.end = end;
			this.gap = end - start;
		}
	}

	HashMap<Integer, EMP> hm;

	void init() {
		hm = new HashMap<Integer, EMP>();
	}

	int add(int mId, int mStart, int mEnd) {
		hm.put(mId, new EMP(mStart, mEnd + 1));
		return hm.size();
	}

	int remove(int mId) {
		hm.remove(mId);
		return hm.size();
	}

	int announce(int mDuration, int M) {
		if (hm.size() < M)
			return -1;
		TreeSet<Integer> timeset = new TreeSet<Integer>();
		ArrayList<EMP> data = new ArrayList<EMP>();
		for (Entry<Integer, EMP> entry : hm.entrySet()) {
			EMP emp = entry.getValue();
			if (emp.gap >= mDuration) {
				timeset.add(emp.start);
				data.add(emp);
			}
		}
		if (data.size() < M)
			return -1;
		int mintime = 0;
		for (int curtime : timeset) {
			if (curtime < mintime)
				continue;
			int sum = 0; 
			int exsum = 0;
			int endtime = curtime + mDuration;
			for (EMP emp : data) {
				if (emp.start <= endtime && endtime <= emp.end) {
					exsum++;
					if (emp.start <= curtime && ++sum >= M)
						return curtime;
				}
			}
			if (exsum < M)
				mintime = endtime;
		}
		return -1;
	}
}
/*
input 정확하지 않음

1 100
11
1
2 1 0 4 1
2 2 3 13 2
2 3 4 10 3
2 4 11 23 4
2 5 8 18 5
4 2 2 3
4 3 3 8
3 3 4
4 3 3 11
4 3 3 11
*/