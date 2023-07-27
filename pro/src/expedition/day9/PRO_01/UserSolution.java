package expedition.day9.PRO_01;

import java.util.ArrayList;

class UserSolution {
	
	public void playGame(int N) {

		// index : base 0번을 기준으로 다른 카드와 나는 차이(절대값)
		// values : 이만큼 차이가 나는 카드의 위치(최대 4장)

		// 1 ~ N까지 / 가장 크게 나는 차이는 N-1
		ArrayList<Integer>[] list = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		// 100억건 -> 안됨.
//		for (int i = 0; i < N * 2; i++) { // 10만
//			for (int j = i + 1; j < N * 2; j++) {	// 10만
//				main.checkCards(i, j, 0);
//			}
//		}

		// base = 0
		for (int i = 0; i < N * 2; i++) { // 10만
			// base와 i번 카드의 차이 값이 얼마인가?
//			for (int j = 0; j < N - 1; j++) {	// 5만 -> 시간 초과
//				if (main.checkCards(i, j, 0);

			// Parametric Search
			// mid = 0번과 i번 카드의 차이는 mid가 정답.
			int left = 0; // 두 카드의 최소 차이
			int right = N - 1; // 두 카드의 최대 차이

			// 찾으려는 값 : base와 i번 카드의 정확한 차이
			// -> test(checkCards) 를 통해서 알 수 있는 것
			// diff가 실제 차이보다 작거나 같냐? 를 알 수 있다 (true)
			// 계속해서 작은 차이를 찾아가다보면 정확한 차이를 알 수 있다.(마지막 요소)
			while (left <= right) {
				// 이 만큼이 base와 i번 카드의 차이
				int mid = (left + right) / 2;

				// base와 i번 카드의 차이가 mid 이하인가?
//				if (test(mid)) {
				if (Main.checkCards(0, i, mid)) {
					// 통과 -> 더 작은 차이가 존재하는지 찾아볼 것
					right = mid - 1;

				} else {
					// 실패 -> base와 i번 카드의 차이가 기준값보다 크다
					// 더 큰차이 찾아보기
					left = mid + 1;
				}
			}
			
			// Parametric Search가 끝나면 0번과 i번 카드의 차이를 알 수 있다.
			list[left].add(i); // i번 카드는 base를 기준으로 차이만큼 차이난다.
		}

		// list에 가은 차이를 가진 값들이 들어가 있다.
		// 매칭 시켜주기

		for (int i = 1; i < N; i++) {
			// 0번은 안해도 된다. -> 이미 위에서 fountCnt++

			// 2개의 카드가 존재할 때
			if (list[i].size() == 2) {
				Main.checkCards(list[i].get(0), list[i].get(1), 0);
			} else if (list[i].size() == 4) { // 4개의 카드가 존재할 때
				if (Main.checkCards(list[i].get(0), list[i].get(1), 0)) {
					Main.checkCards(list[i].get(2), list[i].get(3), 0);
				} else if (Main.checkCards(list[i].get(0), list[i].get(2), 0)) {
					Main.checkCards(list[i].get(1), list[i].get(3), 0);
				} else if (Main.checkCards(list[i].get(0), list[i].get(3), 0)) {
					Main.checkCards(list[i].get(1), list[i].get(2), 0);
				}
			}
		}
	}
}