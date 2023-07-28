package expedition.day10.PRO_03;

import java.util.ArrayList;

class Solution {

	static class Rect {
		int y; // 왼쪽 위 좌표
		int x;
		int size; // 한 변의 길이 -> 오른쪽위, 오른쪽 아래, 왼쪽 아래 좌표 다 알 수 있습니다.
		int num; // 이 사각형에 부여된 번호

		Rect(int y, int x, int size, int num) {
			this.y = y;
			this.x = x;
			this.size = size;
			this.num = num;
		}
	}

	private final static int LM = 20003;

	// 사각형의 소속 관리
	private static int[] PARENT = new int[LM];

	// 사각형들의 소속에 대해 몇개가 연결되어있는가? (춘추전국시대 - POP)
	// index : 사각형 번호 (대장/소속 번호)
	// value : 이 소속에 포함된 사각형의 수
	static int[] CNT;

	// 현황표 => 10x10으로 영역을 나눠서 사각형들을 저장
	static ArrayList<Rect> MAP[][];

	// 각 플레이어가 소유한 사각형의 개수
	// index : player 1, 2
	// value : 몇개의 사각형을 소유하고 있는가?
	static int[] playercnt;

	// Union_find에서 몇번 사각형과 몇번 사각형이 합쳐지는가? => 각 사각형에 부여할 번호
	// add를 할때마다 하나씩 늘려준다.
	static int rectnum;

	// 이 사각형의 소유주는 누구인가?
	// index : 사각형/소속 번호
	// value : 어떤 플레이어가 이 사각형을 소유하고 있는가 (1,2)
	static int[] ID;

	// 한 영역의 크기 (사각형이 가질 수 있는 최대 변의 크기)
	static int SIZE;

	static int[] ydir = { -1, 1, 0, 0, -1, -1, 1, 1, 0 };
	static int[] xdir = { 0, 0, -1, 1, -1, 1, -1, 1, 0 };

	public int Find(int x) {
		if (PARENT[x] == x)
			return x;
		return PARENT[x] = Find(PARENT[x]);
	}

	public void Union(int x, int y, int pid) {
		// y가 대장
		// x가 피합병자

		x = Find(x);
		y = Find(y);
		if (x == y)
			return;
		PARENT[x] = y; // y가 대장

		// ---추가 작업---
		// #1. 소유권 이전
		if (ID[y] != pid) {
			ID[y] = pid;
			// 여기에 (y, now)에 연결된 사각형의 개수 모두 pid번꺼가 된다.
			// 1 % 2 +1->2
			// 2 % 2 + 1->1
			playercnt[pid % 2 + 1] -= CNT[y]; // y에 소속된 사각형 개수를 옮겨주고
			playercnt[pid] += CNT[y];
		}

		// 소속의 사각형 개수 누적 ?
		CNT[y] += CNT[x];
		CNT[x] = 0;
	}

	public void init(int N, int M) {
		MAP = new ArrayList[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				MAP[i][j] = new ArrayList<>();

		// 사각형 번호 = 최대 2만개까지 만들어질 수 있다.
		playercnt = new int[3];
		CNT = new int[LM];
		ID = new int[LM];
		rectnum = 1;

		// PARENT 같은거 초기화
		for (int i = 0; i < LM; i++) {
			PARENT[i] = i;
			CNT[i] = 1; // 모든 사각형(소속)은 1개로 시작
		}

		SIZE = M;
	}

	public int Add(int row, int col, int size, int pid) {
		// (row, col)을 왼쪽 위 모서리로 갖는 size x size 크기의 정사각형 영역을 pid 플레이어가 선포합니다.
		// rectnum번 사각형
		// #1. pid번 플레이어는 1개의 사각형을 더 소유하게 됩니다.
		playercnt[pid]++;

		// #2. rectnum번 사각형은 pid플레이어의 소유가 됩니다.
		ID[rectnum] = pid;

		// #3. 이 사각형을 실제로 등록
		// 이 사각형이 어느 영역에 들어가는가?
		// 이 사각형이 포함될 영역 (y,x)
		int y = row / SIZE;
		int x = col / SIZE;

		// 병합 과정
		// 지금 영역으로부터, 인접한 9군데의 영역을 확인하고,
		// 이 영역에 포함되어있는 사각형들을 일일히 확인하면서
		// "겹친다면" => Union을 통해서
		// #1. 하나의 소속으로 만들어주고, #2. 개수 관리, #3. 소유권 이전
		for (int i = 0; i < 9; i++) {
			// 인접한 다음 영역 확인
			int ny = y + ydir[i];
			int nx = x + xdir[i];

			if (ny < 0 || nx < 0 || ny >= 10 || nx >= 10)
				continue;

			// 이 영역에 포함된 사각형들 확인
			ArrayList<Rect> temp = MAP[ny][nx];

			for (int j = 0; j < temp.size(); j++) {
				Rect now = temp.get(j);
				// 이 사각형과 겹친다면
				if (row >= now.y + now.size)
					continue;
				if (row + size <= now.y)
					continue;
				if (col >= now.x + now.size)
					continue;
				if (col + size <= now.x)
					continue;

				// 이 번호의 사각형이 now번 사각형과 합쳐진다.
				// 그리고 이 사각형들의 소유 및 개수는 다 pid번 플레이어것이 된다.
				Union(rectnum, now.num, pid);
			}
		}

		MAP[y][x].add(new Rect(row, col, size, rectnum));

		// 다음 add될 사각형은 = rectnum + 1;
		rectnum++;

		return playercnt[pid];
	}

	public int Get(int row, int col) {
		// (row, col)위치의 영역을 소유한 플레이어의 pid를 반환합니다.
		// 어떠한 플레이어도 소유하지 않았다면 0을 반환합니다.

		// 지금 주어진 (row,col) 면적 (한 점)이 포함될 가능성이 있는 사각형 아무거나 하나만 찾으면 됩니다.
		// ==> 이 점이 포함될수 있는 영역 = 주변 M 거리만큼의 영역에 포함된 사각형이 모두 후보가 될수 있다.
		// 주변 9개의 영역에 포함된 사각형들을 확인
		int y = row / SIZE;
		int x = col / SIZE;

		for (int i = 0; i < 9; i++) {
			int ny = y + ydir[i];
			int nx = x + xdir[i];
			if (ny < 0 || nx < 0 || ny >= 10 || nx >= 10)
				continue;
			// 이 영역에 포함된 사각형 확인
			ArrayList<Rect> temp = MAP[ny][nx];
			for (int j = 0; j < temp.size(); j++) {
				Rect now = temp.get(j);
				// 만약 now 사각형 안에 포함 된다면
				// 다른 사각형 볼 필요 없음

				// 점이 아닙니다.
				// (row, col) 위치에서 시작하는 사각형 "면적"
				if (row < now.y)
					continue;
				if (row >= now.y + now.size)
					continue;
				if (col < now.x)
					continue;
				if (col >= now.x + now.size)
					continue;

				return ID[Find(now.num)];
			}
		}
		return 0;
	}
}