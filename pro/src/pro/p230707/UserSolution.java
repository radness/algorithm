package pro.p230707;

import java.util.HashMap;

// 조직개편
public class UserSolution {
	
	int[] p;
	int[] left;
	int[] right;
	int[] cnt;
	int[] minus;
	
	int seq;
	int groupCnt;

	HashMap<Integer, Integer> idMap;
	
	// num : 인원수
	void postOrder(int id, int num) {
		if (id == 0)
			return;
		
		if (cnt[id] >= num) {
			if (cnt[left[id]] > cnt[right[id]]) {
				postOrder(left[id], num);
				postOrder(right[id], num);
			} else {
				postOrder(right[id], num);
				postOrder(left[id], num);
			}
		}
		
		if ((cnt[id] - minus[id]) <= num) {
			if (p[id] != 0 && cnt[p[id]] - minus[p[id]] <= num)
				return;
			
			groupCnt++;
			minus[id] = cnt[id];
		}
		
		minus[p[id]] += minus[id];
			
	}
	
	public void init (int mID, int mNum) {
		idMap = new HashMap<>();
		p = new int[8002];
		left = new int[8002];
		right = new int[8002];
		cnt = new int[8002];
		seq = 1;
		idMap.put(0, 0);
		add(mID, mNum, 0);
	}
	
	public int add(int mID, int mNum, int mParent) {
		Integer pID = idMap.get(mParent);
		if (pID == null)
			return -1;
		
		if (left[pID] != 0 && right[pID] != 0)
			return -1;
		
		int id = seq++;
		idMap.put(mID, id);
		p[id] = pID;
		
		if (left[pID] == 0)
			left[pID] = id;
		else right[pID] = id;
		
		update(id, mNum);
		
		// return : 상위 부서로부터 아래 모든 부서 인원의 합
		return cnt[pID];
	}
	
	public void update (int id, int num) {
		if (id == 0)
			return;
		
		cnt[id] += num;
		
		update(p[id], num);
	}
	
	public int remove(int mID) {
		Integer id = idMap.get(mID);
		
		if (id == null)
			return -1;
		
		if (left[p[id]] == id)
			left[p[id]] = 0;
		else right[p[id]] = 0;
		
		idMap.remove(mID);
		
		int ret = cnt[id];
		
		update(id, -ret);
		
		p[id] = 0;
		
		return ret;
	}
	
	public int reorganize(int M, int K) {
		minus = new int[8002];
		groupCnt = 0;
		postOrder(1, K);
		
		if (cnt[1] == minus[1] && groupCnt <= M)
			return 1;
		
		return 0;
	}
}