package pro.past.no11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 문명
class UserSolution {
	// r,c : 1 ~ 1000 -> idx : 0 ~ 100,000 -> (r - 1) * N + c - 1
	static int N; // 크기

	int[] dy = { 1, -1, 0, 0 };
	int[] dx = { 0, 0, 1, -1 };

	int[] parents; // 부모 관계
	int[] ID;
	Set<Integer>[] members;
	Map<Integer, Integer> idxMap;

	void init(int N) {
		this.N = N;
		ID = new int[N * N + 1];
		parents = new int[N * N + 1];
		members = new Set[N * N + 1];
		idxMap = new HashMap();
	}

	// 문명 생성
	int newCivilization(int r, int c, int mID) {
		// 상하좌우 탐색하면서 합병될 문명이 있는지 체크
		int id = check(r, c);

		int idx = getIdx(r, c);

		if (id == 0) {
			// 인접한 부족이 없는 경우
			ID[idx] = mID;
			parents[idx] = idx;
			members[idx] = new HashSet<>();
			members[idx].add(idx);
			idxMap.put(mID, idx);

			return mID;
		}
		// 기존에 있던 부족에 추가가 되는 경우
		// 루트 정보
		int rootIdx = idxMap.get(id);
		parents[idx] = rootIdx;
		members[rootIdx].add(idx);

		return id;
	}

	private int check(int r, int c) {
		int id = 0;
		int cnt = 0;
		Map<Integer, Integer> tmpMap = new HashMap<>();
		// 상하좌우
		for (int i = 0; i < 4; i++) {
			int ny = r + dy[i];
			int nx = c + dx[i];
			// 경계를 벗어난 값
			if (ny < 1 || nx < 1 || ny > N || nx > N)
				continue;

			// 새로운 좌표에 대한 idx
			int idx = getIdx(ny, nx);
			// 대표가 되는 위치정보값
			int rootIdx = find(idx);

			int tmpId = members[rootIdx] == null || members[rootIdx].contains(idx) ? ID[rootIdx] : 0;

			// 인접한 부족이 만들어졌다.
			if (tmpId != 0) {
				int tmpCnt = tmpMap.getOrDefault(tmpId, 0) + 1;
				tmpMap.put(tmpId, tmpCnt);
				if (tmpCnt > cnt) {
					id = tmpId;
					cnt = tmpCnt;
				} else if (tmpCnt == cnt && tmpId < id) {
					id = tmpId;
				}
			}
		}
		return id;
	}

	private int find(int idx) {
		if (parents[idx] == idx)
			return idx;
		return parents[idx] = find(parents[idx]);
	}

	private int getIdx(int r, int c) {
		return (r - 1) * N + (c - 1);
	}

	// mID 부족 삭제 후 점유하고 있는 땅의 개수 반환.
	int removeCivilization(int mID) {
		if (idxMap.containsKey(mID)) {
			// 부족이 존재
			int idx = idxMap.get(mID);
			int ans = members[idx].size();
			ID[idx] = 0;
			idxMap.remove(mID);

			return ans;
		}
		return 0;
	}

	int getCivilization(int r, int c) {
		int idx = getIdx(r, c);
		// 지금 부족이 점유하고 있는 위치
		int rootIdx = find(idx);

		// 부족이 있으면
		if (members[rootIdx] != null && members[rootIdx].contains(idx)) {
			return ID[rootIdx];
		}
		// 부족정보가 없거나, 새롭게 만들어진 부족이면
		return 0;
	}

	int getCivilizationArea(int mID) {
		// mID가 유효한 부족인가?
		if (idxMap.containsKey(mID)) {
			int rootIdx = idxMap.get(mID);
			return members[rootIdx].size();
		}
		return 0;
	}

	// 두 부족을 병합 후 부족의 개수 반환
	int mergeCivilization(int mID1, int mID2) {
		// mID2 -> mID1
		int idx1 = idxMap.get(mID1);
		int idx2 = idxMap.get(mID2);

		// 부족원에 수에 따라(1번의 맴버가 더 많으면)
		if (members[idx1].size() >= members[idx2].size()) {
			members[idx1].addAll(members[idx2]);
			parents[idx2] = idx1;

			// 2번 정보 삭제
			idxMap.remove(mID2);

			return members[idx1].size();
		}

		// 2번이 더 많은 경우
		members[idx2].addAll(members[idx1]);
		parents[idx1] = idx2;
		ID[idx2] = mID1;
		idxMap.put(mID1, idx2);
		idxMap.remove(mID2);

		return members[idx2].size();
	}
}