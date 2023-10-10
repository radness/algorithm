package mini_contast.PAST_02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 문명
class UserSolution {

	int N; // 좌표의 크기
	int[] dR = { 1, -1, 0, 0 };
	int[] dC = { 0, 0, 1, -1 };
	int[] parents;
	int[] IDs; // 특정 위치에 ID값이 얼마인지 저장
	Set<Integer>[] members; // 특정 위치에 맴버값이 얼마인지 저장
	Map<Integer, Integer> idxMap;

	void init(int N) {
		this.N = N; // 사이즈
		IDs = new int[N * N + 1];
		parents = new int[N * N + 1];
		members = new Set[N * N + 1];
		idxMap = new HashMap<>();
	}

	// 새로운 문명 생성
	int newCivilization(int r, int c, int mID) {
		// r, c를 기준으로 상하좌우 탐색
		int id = check(r, c);
		int idx = getIdx(r, c);
		if (id == 0) { // 인접한 부족이 없는 경우
			// r, c가 root 위치가 된다.
			IDs[idx] = mID;
			parents[idx] = idx;
			members[idx] = new HashSet<>();
			members[idx].add(idx);
			idxMap.put(mID, idx); // mID에 해당하는 지금 위치
			
			return mID;
		}
		// 기존애 있던 부족에 r, c가 추가되어야 한다.
		int rootIdx = idxMap.get(id); // 루트 위치 정보를 가져온다.
		parents[idx] = rootIdx;
		members[rootIdx].add(idx);
		
		return id;
	}

	private int check(int r, int c) {
		int id = 0;
		int count = 0; // 인접해 있는 개수
		// 몇개가 인접해있는지 저장
		Map<Integer, Integer> tmp = new HashMap<>();
		for (int i = 0; i < 4; i++) {
			// 체크할 좌표
			int nR = r + dR[i];
			int nC = c + dC[i];

			// 맵 안에 들어와 있는가?
			if (nR < 1 || nC < 1 || nR > N || nC > N)
				continue;
			// 새로운 좌표에 대한 idx값을 반환하여 사용
			int idx = getIdx(nR, nC);
			// 기존에 문명이 있는 경우 체크
			int rootIdx = find(idx);
			int tmpId = members[rootIdx] == null || members[rootIdx].contains(idx) ? IDs[rootIdx] : 0;
			if (tmpId != 0) {
				int tmpCount = tmp.getOrDefault(tmpId, 0) + 1;
				tmp.put(tmpId, tmpCount);
				if (tmpCount > count) {
					id = tmpId;
					count = tmpCount;
				} else if (tmpCount == count && tmpId < id) { // 개수가 같은데 id만 작은 경우
					id = tmpId;
				}
			}
		}
		return id;
	}

	// union-find
	private int find(int idx) {
		if (parents[idx] == idx)
			return idx;
		return parents[idx] = find(parents[idx]);
	}

	private int getIdx(int r, int c) {
		return (r - 1) * N + (c - 1);
	}

	// mID에 해당되는 부족을 삭제
	int removeCivilization(int mID) {
		if (idxMap.containsKey(mID)) { 
			// 부족이 존재한다면
			int idx = idxMap.get(mID);
			int ans = members[idx].size();
			IDs[idx] = 0; // 부족이 없어진 경우
			idxMap.remove(mID); // 삭제처리
			
			return ans; // 몇개의 땅이 있었는지 반환
		}
		
		// 존재히지 않는 부족
		return 0;
	}

	int getCivilization(int r, int c) {
		int idx = getIdx(r, c);
		int rootIdx = find(idx); // 최상위 위치 확인(지금 부족이 점유하고 있는 위치)
		
		// 내 위치정보가 포함되어 있다면
		if (members[rootIdx] != null && members[rootIdx].contains(idx)) {
			return IDs[rootIdx];
		}
		
		return 0;
	}

	// mID에 해당하는 부족이 가지고 있는 땅의 개수 반환
	int getCivilizationArea(int mID) {
		if (idxMap.containsKey(mID)) { // 요구한 조건에 부합하다면
			int rootIdx = idxMap.get(mID);
			// 점유하고 있는 땅의 갯수
			return members[rootIdx].size();
		}
		
		return 0;
	}

	// 두 부족을 병합
	int mergeCivilization(int mID1, int mID2) {
		// mID2 -> mID1으로 병합
		int idx1 = idxMap.get(mID1);
		int idx2 = idxMap.get(mID2);
		// 부족원의 수에 따라 분기 처리
		if (members[idx1].size() >= members[idx2].size()) {
			members[idx1].addAll(members[idx2]); // 1번에 추가
			parents[idx2] = idx1;
			idxMap.remove(mID2);
			
			return members[idx1].size(); // 통합된 원소의 수
		}
		
		// 1번의 맴버들을 2번으로 옮긴다.
		members[idx2].addAll(members[idx1]); // 원소 추가
		parents[idx1] = idx2; // 부무모 관계 업데이트 
		IDs[idx2] = mID1; // 위치 정보 갱신
		idxMap.put(mID1, idx2);
		idxMap.remove(mID2);
		
		return members[idx2].size(); // 합쳐진 부족원의 수
	}

}