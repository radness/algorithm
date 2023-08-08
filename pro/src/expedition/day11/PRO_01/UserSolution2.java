package expedition.day11.PRO_01;

import java.util.ArrayDeque;
import java.util.Iterator;

// 블록 제거 게임
class UserSolution2 {

	static class Block {
		int start;
		int len;
		int time;
		int end;
		int[] removed = new int[colcnt]; // 지금 블록의 어떤 열들이 삭제가 되었는가?

		Block(int start, int len, int time) {
			this.start = start;
			this.len = len;
			this.time = time;
			this.end = this.start + this.len;
		}
	}

	static ArrayDeque<Block> q;
	static int colcnt; // 열의 개수
	static int rowcnt; // 행의 개수
	static int totalBlocks;

	void init(int R, int C) {
		q = new ArrayDeque<>();
		rowcnt = R;
		colcnt = C;
		totalBlocks = 0;
	}

	int dropBlocks(int mTimestamp, int mCol, int mLen) {
		drop(mTimestamp);
		// 시간이 지나면서 내려왔다!
		// 지금 추가되는 블록만큼만 반영
		q.add(new Block(mCol, mLen, mTimestamp));
		totalBlocks += mLen;
		return totalBlocks;
	}

	int removeBlocks(int mTimestamp) {
		drop(mTimestamp);

		// 이번 호출에서 삭제된 열들을 관리할 DAT
		int[] removed = new int[colcnt];

		// 아래에서부터 지울수 있는 블록들을 지워줘야합니다.
		// 현존하는 모든 블록들을 한번씩 접근 (아래에서부터)
		Iterator it = q.iterator();
		while (it.hasNext()) {
			Block now = (Block) it.next();
			// 이 블록이 현재 차지하고 있는 칸들을 확인
			// for(int i = now.start; i < now.start + now.len; i++)
			for (int i = now.start; i < now.end; i++) {
				// #1. 아래에서 이번 removeBLocks 호출때 이미 삭제된 열 = 다시 삭제하지 않습니다.
				if (removed[i] == 1)
					continue;

				// #2. 이 블록의 이 부분이 이미 삭제된 애라면 => 다시 삭제하지 않습니다.
				if (now.removed[i] == 1)
					continue;

				// 이 호출에서 이 열이 삭제된다!
				removed[i] = 1;

				// 이 부분을 삭제
				now.removed[i] = 1;
				now.len--;
				totalBlocks--;
			}
		}
		return totalBlocks;
	}

	static void drop(int time) {
		// 이 시간대에 아래 부분을 넘어가서 사라지는 블록들을 삭제
		while (!q.isEmpty()) {
			// 맨 아래에 있는 블록을 봐야 합니다.
			Block now = q.peekFirst();
			// 얘가 rowcnt 만큼 움직여서 우리의 게임판을 나갔는가? -> remove
			// 그게 아니라면, 블록 삭제하는 것을 중단하면 됩니다.
			if (time - now.time < rowcnt)
				// 아직 생존하고 있는 블록
				break;
			// 이 블록이 삭제되면서, 이 블록의 개수만큼 총 블록의 수에서 빠집니다.
			totalBlocks -= q.removeFirst().len;
		}
	}
}