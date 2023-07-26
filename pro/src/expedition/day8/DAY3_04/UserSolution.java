package expedition.day8.DAY3_04;

class UserSolution {

	// Union-Find 소속 관리
	static int[] PARENT;

	// 국가별 인구 수
	// index : 국가 번호
	// value : 국가별 사람의 수
	// 만약 0명이 존재한다면 -> 망한 국가가 된다.
	static int[] POP;

	// 국가의 수
	static int countryCnt;

	static int Find(int node) {
		if (PARENT[node] == node)
			return node;

		return PARENT[node] = Find(PARENT[node]);
	}
	
	public void init(int N, int[] countries) {
		PARENT = new int[N];
		POP = new int[N];

		// 초기화
		for (int i = 0; i < N; i++) {
			PARENT[i] = i; // 모든 국가는 자기 자신이 대장
			POP[i] = countries[i]; // 모든 국가의 초기 인구 설정
		}

		countryCnt = N;
	}

	public int alliance(char A, char B) {
		// A의 소속(A = 0, B = 1, ...)
		int pa = Find(A - 'A');
		int pb = Find(B - 'A');

		// 동맹이 정상적으로 이루어지지 않은 경우
		// POP[pa] == 0 -> 멸망한 경우
		if (pa == pb || POP[pa] == 0 || POP[pb] == 0) {
			return -1;
		}

		// 동맹으로 만든다.
		// 1. 소속 관리
		PARENT[pb] = pa;

		// 2. 인구 수 병합
		POP[pa] += POP[pb];
		POP[pb] = 0; // pb나라는 존재하지 않게 됨.

		// 두개의 국가가 한개가 됨
		countryCnt--;

		// 정상 동맹
		return 1;
	}

	public int war(char A, char B) {
		// A의 동맹국과 B의 동맹국이 전쟁
		int pa = Find(A - 'A');
		int pb = Find(B - 'A');

		if (pa == pb || POP[pa] == 0 || POP[pb] == 0) {
			return -1; // 정상적으로 전쟁을 할 수 없는 경우
		}

		// 1. 이기는 상황
		if (POP[pa] > POP[pb]) {
			// pa가 승자
			// 패배한 나라의 인구수만큼 뺸다.
			POP[pa] -= POP[pb];
			POP[pb] = 0; // 멸망

			// 땅 자체는 승자에게 넘어간다.
			PARENT[pb] = pa;

			countryCnt--; // 국가 수 감소
		} else if (POP[pa] < POP[pb]) {
			// pb가 승자
			// 패배한 나라의 인구수만큼 뺸다.
			POP[pb] -= POP[pa];
			POP[pa] = 0; // 멸망

			// 땅 자체는 승자에게 넘어간다.
			PARENT[pa] = pb;

			countryCnt--; // 국가 수 감소
		}

		// 2. 둘다 망하는 상황
		if (POP[pa] == POP[pb]) {
			POP[pa] = 0;
			POP[pb] = 0;

			countryCnt -= 2;
		}

		return 1;
	}

	public int getCnt() {
		return countryCnt;
	}

	public int getPopCnt(char A) {
		return POP[Find(A - 'A')];
	}

}