package expedition.day11.PRO_01;

import java.util.ArrayDeque;
import java.util.Iterator;

// 블록 제거 게임
public class UserSolution {
	static ArrayDeque<Block> q;
	static int ROW_CNT; // 행의 갯수
	static int COL_CNT; // 열의 갯수
	static int TOTAL_BLOCK_CNT; // 맵에 남아있는 전체 블록의 갯수
	static int[] removed;
	
	static class Block {
		int start; // 시작점
		int length; // 길이
		int time; // 입력 시간
		int end; // 끝점
		int[] removed = new int[COL_CNT]; // col마다 삭제 처리 되었는지

		public Block(int start, int length, int time) {
			this.start = start;
			this.length = length;
			this.time = time;
			this.end = this.start + this.length;
		}
	}

	void init(int R, int C) {
		q = new ArrayDeque<>();
		ROW_CNT = R;
		COL_CNT = C;
		TOTAL_BLOCK_CNT = 0;
	}

	int dropBlocks(int mTimestamp, int mCol, int mLen) {
		// 시각만큼 떨어뜨린다
		drop(mTimestamp);

		// 추가된 블록의 갯수 찾기
		q.add(new Block(mCol, mLen, mTimestamp));
		TOTAL_BLOCK_CNT += mLen;

		return TOTAL_BLOCK_CNT;
	}

	private void drop(int time) {
		// 시간대에 아래 부분을 넘어가는 사라지는 블록들 삭제
		while (!q.isEmpty()) {
			// 맨 아래 있는 블록
			Block now = q.peekFirst();

			// 시간(ROW_CNT) 만큼 움직여서 주어진 게임판을 벚어나면 => remove
			// 벚어나지 않으면 중단 => break
			if (time - now.time < ROW_CNT)
				// 아직 생존하고 있는 불록
				break;

			// 이 블록이 삭제 되면서 블록의 개수만큼 총 블록의 수 에서 빼기
			TOTAL_BLOCK_CNT -= q.removeFirst().length;
		}
	}

	int removeBlocks(int mTimestamp) {
		drop(mTimestamp);

		// 삭제된 열들을 관리할 DAT
		removed = new int[COL_CNT];

		// 아래부터 지울 수 있는 블록들을 지운다.
		// "아래" 부터 현재 맵에 존재하는 모든 블록들을 한번씩 접근
		Iterator iter = q.iterator();

		while (iter.hasNext()) {
			Block now = (Block) iter.next();

			// 이 블록기 현재 맵에서 차지하고 있는 영역(칸)들을 확인
			for (int i = now.start; i < now.end; i++) {
				// 1. 아래에서 이번 removeBlocks 호출할 때 이미 삭제된 열이 있다면 skip
				if (removed[i] == 1)
					continue;

				// 2. 이 블록이 이미 삭제 처리되었으면 skip
				if (now.removed[i] == 1)
					continue;

				// 해당 열 삭제
				removed[i] = 1;

				// 삭제 처리
				now.removed[i] = 1;
				now.length--;
				TOTAL_BLOCK_CNT--;
			}
		}

		return TOTAL_BLOCK_CNT;
	}
}
