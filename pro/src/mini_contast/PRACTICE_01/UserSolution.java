package mini_contast.PRACTICE_01;

import java.util.ArrayList;
import java.util.Collections;

// 정중앙 대학교
class UserSolution {

	static ArrayList<Integer> alist;
	static int cnt;

	public void init() {
		alist = new ArrayList<>();
		alist.add(500);

		cnt = 0;
	}

	public void enroll(int A, int B) {
		alist.add(A);
		alist.add(B);

		cnt += 2;

		Collections.sort(alist);
	}

	public int getMidValue() {
		int ans = 500;

		int idx = Math.round(cnt / 2);
		ans = alist.get(idx);

//		System.out.println(ans);

		return ans;
	}
}