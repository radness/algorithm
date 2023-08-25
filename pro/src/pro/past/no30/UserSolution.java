package pro.past.no30;

import java.util.ArrayList;

// 전자상가
class UserSolution {
	static int CHARGE; // 운송료
	static ArrayList<Item>[][] parts; // 부품목록[창고번호][부품종류]
	static final int INF = 100_000_000;

	static class Item {
		int price;
		int performance;

		public Item(int price, int performance) {
			this.price = price;
			this.performance = performance;
		}

//		@Override
//		public int compareTo(Item other) {
//			if (this.performance == other.performance)
//				// 2 가격이 낮은 순
//				return this.price - other.price;
//			// 1. 성능이 높은 순
//			return other.performance - this.performance;
//		}
	}

	void init(int mCharge) {
		CHARGE = mCharge; // 운송비
		parts = new ArrayList[2][3];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				parts[i][j] = new ArrayList<>();
			}
		}
		return;
	}

	// 부품 입고
	int stock(int mType, int mPrice, int mPerformance, int mPosition) {
		parts[mPosition][mType].add(new Item(mPrice, mPerformance));
		return parts[mPosition][mType].size();
	}

	// 예산 안에서 최대 성능을 내는 부품 조합
	Main.Result order(int mBudget) {
		Main.Result res = new Main.Result();
		int left = 0; // 성능
		int right = 1_000_000;

		while (left <= right) {
			int mid = (left + right) / 2;
			int ret = isPosible(mid, mBudget);
			if (ret != -1) { // 만족한 경우
				// 더 높은 성능을 찾는다.
				left = mid + 1;
				res.mPerformance = mid;
				res.mPrice = ret;
			} else { // 만족하지 못한 경우
				right = mid - 1;
			}
		}
		return res;
	}

	// 가능한가?
	private int isPosible(int mid, int mBudget) {
		// 최적 부품 가격 산정
		// 최적 부품 가격
		int[][] OptimalPartPrice = new int[2][3];

		for (int i = 0; i < 2; i++) { // 창고 번호
			for (int j = 0; j < 3; j++) { // 부품 번호
				OptimalPartPrice[i][j] = INF;
				for (Item item : parts[i][j]) {
					if (item.performance < mid)
						continue;
					if (OptimalPartPrice[i][j] > item.price)
						OptimalPartPrice[i][j] = item.price;
				}
			}
		}

		// 0번 창고
		int WH0Price = OptimalPartPrice[0][0] + OptimalPartPrice[0][1] + OptimalPartPrice[0][2];
		// 1번 창고
		int WH1Price = OptimalPartPrice[1][0] + OptimalPartPrice[1][1] + OptimalPartPrice[1][2];
		// 0 + 1번 창고 조합
		int WH2Price = Math.min(OptimalPartPrice[0][0], OptimalPartPrice[1][0])
				+ Math.min(OptimalPartPrice[0][1], OptimalPartPrice[1][1])
				+ Math.min(OptimalPartPrice[0][2], OptimalPartPrice[1][2])
				+ CHARGE;

		// 최종 가격
		int finalPrice = Math.min(Math.min(WH0Price, WH1Price), WH2Price);

		if (finalPrice <= mBudget)
			return finalPrice;

		return -1;
	}
}