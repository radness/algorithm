package expedition.day3.PRO_01;

import java.util.TreeMap;

// 선긋기
// 2022.03.11 (70 / 5 = 7.14%)
class UserSolution {

	// index : 지금 열(플레이어의 열 1 ~ 100)
	// key : 가로줄이 있는 행(오름차순으로 정렬된 형태의 행을 가지고 1. 올라가기 2. 내려가기) = y축
	// value : 이 행에서 가로줄을 타면 어느 열로 가는지에 대한 정보 = x축
	static TreeMap<Integer, Integer>[] tmap;

	public void init() {
		tmap = new TreeMap[101];
		
		for (int i = 0; i < 101; i++) {
			tmap[i] = new TreeMap<>();
		}
	}

	public void add(int x, int y) {
		// (x, y) <-> (x + 1, y)로 가는 가로줄을 추가하는 함수
		// x열에서는 y행에서 x + 1열로 갈 수 있는 가로줄이 생긴다.
		tmap[x].put(y, x + 1);
		// x + 1열에서도 y행에서 x열로 갈 수 있는 가로줄이 생긴다.
		tmap[x + 1].put(y, x);
	}

	public int getCrossCnt(int lineID) {
		// lineID = 사람의 ID = 어떤 열(x축)에서 시작하는가?
		int x = lineID; // lineID번 열에서 시작
		int y = 0; // 0번 행에서 시작(위에서부터 아래로)

		// 사다리 게임 시작
		int cnt = 0;

		while (true) {
			// 이 게임이 끝나는 조건
			// 내 열에서 아래에 있는 가로줄 중 가장 가까운 (위에있는) 가로줄을 탐색하면서 넘어간다.
			// -> 만약 더이상 아래에 가로줄을 찾을 수 없다면 -> 1자로 10억번까지 내려간다.
			// -> 가로줄이 더이상 발견되지 않을 때까지 반복
			
			// 내 열에서 행 기준으로 더 큰 값들중 가장 작은 것
			Integer nY = tmap[x].higherKey(y);

			// 가로줄이 없다면 break
			if (nY == null)
				break;

			// 가로줄이 존재한다면 여기에 존재하는 가로줄을 지나간다.
			cnt++;
			
			y = nY;	// 행 이동(y축)
			x = tmap[x].get(nY); // 지금 x열에서 y행에 있는 가로줄에 연결된 열로 옮겨간다.
		}

		return cnt;
	}

	public int getID(int x, int y) {
		// (x, y) 위치를 거쳐가는 ID는 누구인가?
		// (x, y)에서 출발해서 거꾸로 위로 올라간다.
		
		while (true) {
			// 지금 내 행을 기준으로 이 열에서 기준이되는 지점으로부터 위에 있는 행 중 가장 아래에 있는 행
			Integer nY = tmap[x].lowerKey(y);

			// 내 위에 더이상 없다 -> 그러면 이대로 올라간다. -> 지금 내가 있는 열 -> 정답
			if (nY == null)
				break;

			y = nY;
			x = tmap[x].get(nY);
		}
		
		// ID 리턴 = 열번호
		return x;
	}

	public void remove(int x, int y) {
		// (x, y) <=> (x + 1, y)로 가는 가로줄을 추가하는 함수
		// x열에서 y행에 존재하는 가로줄 삭제
		tmap[x].remove(y);
		// x+1열에서 y행에 존재하는 가로줄 삭제
		tmap[x + 1].remove(y);
	}
}