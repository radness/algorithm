package pro.past.no19;

import java.util.ArrayList;

// 짝 맞추기 게임
class UserSolution {
	public void playGame(int N) {
		// 차이에 대한 인덱스 정보
		ArrayList<Integer>[] list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		list[0].add(0);
		for (int idx = 1; idx < N * 2; idx++) {
			int left = 0;
			int right = N - 1;
			int diff = 0;

			// 이분 탐색
			while (left <= right) {
				int mid = (left + right) / 2;
				if (Main.checkCards(0, idx, mid)) {
					diff = mid;
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			
			list[diff].add(idx);
		}
		// 결과처리
		for (int gap = 0; gap < N; gap++) {
			if (list[gap].size() == 0)
				continue;
			
			if (list[gap].size() == 2)
				Main.checkCards(list[gap].get(0), list[gap].get(1), 0);
			else if (list[gap].size() == 4) {
				Main.checkCards(list[gap].get(0), list[gap].get(1), 0);
				Main.checkCards(list[gap].get(0), list[gap].get(2), 0);
				Main.checkCards(list[gap].get(0), list[gap].get(3), 0);
				Main.checkCards(list[gap].get(1), list[gap].get(2), 0);
				Main.checkCards(list[gap].get(1), list[gap].get(3), 0);
				Main.checkCards(list[gap].get(2), list[gap].get(3), 0);
			}
		}
	}
}