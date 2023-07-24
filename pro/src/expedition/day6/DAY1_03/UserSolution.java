package expedition.day6.DAY1_03;

class UserSolution {

	static int left;
	static int right;
	static int cnt;
	static int limitCnt;
	
	public void init(int start, int end, int limit) {
		left = start;
		right = end;
		limitCnt = limit;
		cnt = 0;
	}

	public int[] guess(int num) {
		// Binary Search
		cnt = 0;
		int upCnt = 0;
		int downCnt = 0;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			
			cnt++;
			
			// 경우의 수
			if (mid == num) {	// 찾은 경우
				break;
			} else if (mid < num) {
				left = mid + 1;
				
				upCnt++;
			} else if (mid > num) {
				right = mid - 1;
				
				downCnt++;
			}
		}
		
		return new int[] { upCnt, downCnt };
	}

	public int sendResult() {
		if (cnt > limitCnt)
			return -1;
		return cnt;
	}
}