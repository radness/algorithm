package pro.past.no38;

// 물품보관
// 세그먼트 트리
class UserSolution {
	int[] HEIGHT; // i번 위치의 높이
	int[] TREE; // 해당 구간 중 물품의 높이가 가장 높은곳의 위치값 저장
	int LEAF;

	public void init(int N) {
		LEAF = 1;

		while (LEAF < N) {
			LEAF <<= 1;
		}

		HEIGHT = new int[LEAF + 1];
		TREE = new int[LEAF * 2];

		for (int i = 0; i < LEAF; i++) {
			TREE[LEAF + i] = i + 1;
		}
		for (int i = LEAF - 1; i > 0; i--) {
			TREE[i] = TREE[i * 2];
		}

		return;
	}

	public int stock(int mLoc, int mBox) {
		HEIGHT[mLoc] += mBox;
		rebuild(mLoc);
		return getSize();
	}

	public int ship(int mLoc, int mBox) {
		HEIGHT[mLoc] -= mBox;
		rebuild(mLoc);
		return getSize();
	}

	private void rebuild(int mLoc) {
		int position = (LEAF + mLoc - 1) / 2;

		while (position > 0) {
			TREE[position] = HEIGHT[TREE[position * 2]] >= HEIGHT[TREE[position * 2 + 1]] ? TREE[position]
					: TREE[position * 2 + 1];
			position >>= 1;
		}

	}

	// 천막의 크기를 구한다.
	private int getSize() {
		// 전체 구간에서 가장 높은곳의 위치 확인 = top
		int size = HEIGHT[TREE[1]];
		// 1 ~ (top - 1) 의 넓이
		int end = TREE[1] - 1;
		// 왼쪽 천막의 넓이
		while (end > 0) {
			// 탐색할 구간 중 가장 높이가 높은 곳의 위치를 확인
			int topIdx = getMax(1, 1, LEAF, 1, end);

			// 물품이 없으면 탐색 x
			if (HEIGHT[topIdx] == 0) {
				break;
			}
			// 가로길이 * 높이
			size += (end - topIdx + 1) * HEIGHT[topIdx];
			end = topIdx - 1; // 마지막 위치의 앞칸
		}
		// (top + 1) ~ LEAF(N) 의 넓이 (오른쪽 천막의 넓이)
		int start = TREE[1] + 1;
		while (start <= LEAF) {
			int topIdx = getMax(1, 1, LEAF, start, LEAF); // 가장 높은 곳

			if (HEIGHT[topIdx] == 0) {
				break;
			}
			// 크기는 = 가로 * 높이
			size += (topIdx - start + 1) * HEIGHT[topIdx];
			start = topIdx + 1;
		}

		return size;
	}

	// 세그먼트 트리
	// start ~ end 구간 중 물품이 가장 높에 쌓여있는 곳의 위치
	private int getMax(int pos, int rangeStart, int rangeEnd, int start, int end) {
		// 찾고싶은 구간을 현재 위치에서 확인할 수 있는 경우
		if (rangeStart == start && rangeEnd == end) {
			return TREE[pos];
		}

		// 내가 찾고자 하는 영역이
		int mid = (rangeStart + rangeEnd) / 2;

		// 1. start ~ end 가 모두 왼쪽
		if (end <= mid) {
			return getMax(pos * 2, rangeStart, mid, start, end);
		}
		// 2. start ~ end 가 모두 오른쪽
		if (start > mid) {
			return getMax(pos * 2 + 1, mid + 1, rangeEnd, start, end);
		}

		// 3. 양쪽에 걸쳐있는 경우
		int leftIdx = getMax(pos * 2, rangeStart, mid, start, end);
		int rightIdx = getMax(pos * 2 + 1, mid + 1, rangeEnd, mid + 1, end);

		// 구간의 높이 값으로 비교
		return HEIGHT[leftIdx] >= HEIGHT[rightIdx] ? leftIdx : rightIdx;
	}

	// mLoc 위치의 높이를 구한다.
	public int getHeight(int mLoc) {
		return HEIGHT[mLoc];
	}

}