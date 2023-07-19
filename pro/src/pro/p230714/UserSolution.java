package pro.p230714;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import pro.p230714.Solution.Result;

// 전자상가
class UserSolution {

	class Item implements Comparable<Item> {
		int price; // 가격
		int perform; // 성능

		public Item(int cost, int perform) {
			this.price = cost;
			this.perform = perform;
		}

		@Override
		public int compareTo(Item other) {
			if (this.price == other.price)
				return other.perform - this.perform;

			return this.price - other.price;
		}
	}

	int shipCost; // 운송료
	TreeMap<Integer, Integer>[] warehouse; // 창고
	ArrayList<Item>[][] parts; // 부품

	void init(int mCharge) {
		shipCost = mCharge;

		warehouse = new TreeMap[3]; // 창고1, 창고2, 창고1+2

		for (int i = 0; i < 3; i++) {
			warehouse[i] = new TreeMap<>(Collections.reverseOrder());
		}

		parts = new ArrayList[2][3];

		for (int i = 0; i < 2; i++) { // 창고 번호
			for (int j = 0; j < 3; j++) { // 부품 번호
				parts[i][j] = new ArrayList<>();
			}
		}
	}

	int stock(int mType, int mPrice, int mPerformance, int mPosition) {
		parts[mPosition][mType].add(new Item(mPrice, mPerformance));

		if (mType < 1)
			// 조합 생성
			createCombination(mType, mPrice, mPerformance, mPosition);

		return parts[mPosition][mType].size();
	}

	// 조합 생성
	// mType : 부품 번호
	// mPrice : 가격
	// mPerformance : 성능
	// mPosition : 창고번호
	private void createCombination(int mType, int mPrice, int mPerformance, int mPosition) {
		// 다른 부품 번호
		int otherPartsNum = Math.abs(mType - 1);

		for (int i = 0; i < 2; i++) {
			for (Item item : parts[i][otherPartsNum]) { // 다른 부품
				// 조합가격 = 가격 + 다른부품.가격
				int combiPrice = mPrice + item.price;
				// 조합성능 = 성능과 다른부품.성능 중 작은값
				int combiPerform = Math.min(mPerformance, item.perform);

				if (i == mPosition) { // 창고번호와 같으면
					// 기존 조합 성능
					int ExCombiPerform = warehouse[mPosition].getOrDefault(combiPrice, 0);

					/*
					 * getOrDefault(Object key, V DefaultValue) key : 값을 가져와야 하는 요소의 키 defaultValue
					 * : 지정된 키로 매핑된 값이 없는 경우 반환되어야 하는 기본값. 
					 * return값 -> key가 있으면 key에 존재하는 값을 반환하고 없으면 0반환.
					 */
					if (ExCombiPerform < combiPerform)
						warehouse[mPosition].put(combiPrice, combiPerform);
				} else {
					int ExCombiPerform = warehouse[2].getOrDefault(combiPrice, 0);

					if (ExCombiPerform < combiPerform)
						warehouse[2].put(combiPrice, combiPerform);
				}
			}
		}
	}

	// (0번과 1번의 조합) + (2번 부품) 최적값 계산
	Solution.Result order(int mBudget) {
		Solution.Result res = new Solution.Result();

		for (int i = 0; i < 2; i++) {
			Collections.sort(parts[i][2]);
			CalCombi(mBudget, res, i, i);
			CalCombi(mBudget, res, i, Math.abs(i - 1));
			CalCombi(mBudget, res, i, 2);
		}

		return res;
	}

	// 조합 계산
	// WPart2 : 부품2창고
	// WComb : 창고조합
	private void CalCombi(int mBudget, Result res, int WPart2, int WCombi) {
		int idx = 0;
		int size = parts[WPart2][2].size();
		int addPrice = 0;

		if (WPart2 != WCombi)
			addPrice = shipCost; // 운송료

		Item parts2 = new Item(0, 0); // 최적부품2

		for (Map.Entry<Integer, Integer> entry : warehouse[WCombi].entrySet()) {
			int combiPrice = entry.getKey(); // 조합 가격

			if (combiPrice > mBudget)
				continue;

			int combiPerfrom = entry.getValue(); // 조합 성능

			while (idx < size) {
				Item cur = parts[WPart2][2].get(idx);

				if (mBudget > combiPrice + cur.price + addPrice) {
					if (parts2.perform < cur.perform) {
						parts2.perform = cur.perform;
						parts2.price = cur.price;
					} else if (parts2.perform == cur.perform) {
						parts2.price = Math.min(parts2.price, cur.price);
					}
				} else
					break;

				idx++;
			}

			int CalPerfrom = Math.min(combiPerfrom, parts2.perform);
			int CalPrice = combiPrice + parts2.price + addPrice;

			if (CalPrice > mBudget)
				continue;

			if (res.mPerformance < CalPerfrom) {
				res.mPerformance = CalPerfrom;
				res.mPrice = CalPrice;
			} else if (res.mPerformance == CalPerfrom) {
				res.mPrice = Math.min(res.mPrice, CalPrice);
			}
		}
	}
}
