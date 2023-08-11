package pro.past.no12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 버스 노선 관리
class UserSolution {
	// 버스 정류장
	static int busStop;
	
	int[][] minCosts; //i번 라인을 타고 j번 정류장까지 오는 최소비용
	List<Line>[] LINES; // 라인
	
	// 라인
	static class Line {
		int num; // 정류장 번호
		int to;
		boolean isForward;
		int cost;
		int transCnt; // 환승 횟수
		
		public Line(int num, int to, boolean isForward, int cost, int transCnt) {
			super();
			this.num = num;
			this.to = to;
			this.isForward = isForward;
			this.cost = cost;
			this.transCnt = transCnt;
		}
	}

	public void init(int N, int mLastStation1[], int mLastStation2[]) {
		busStop = N;

		LINES = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			LINES[i] = new ArrayList<>();
		}

		minCosts = new int[5][N + 1];

		for (int i = 0; i < 5; i++) {
			LINES[mLastStation1[i]].add(new Line(i, mLastStation2[i], true, 1, 0));
			LINES[mLastStation2[i]].add(new Line(i, mLastStation1[i], false, 1, 0));
		}
	}

	public void add(int mLine, int mPrevStation, int mStation) {
		// 간선 정보
		// 라인에서 pre -> next -> pre -> station -> next
		// 반대로 next -> pre -> next -> station -> pre
		for (Line forward : LINES[mPrevStation]) {
			if (forward.isForward && forward.num == mLine) {
				int nextStation = forward.to;

				// 역방향 간선 찾기
				for (Line backWard : LINES[nextStation]) {
					if (!backWard.isForward && backWard.num == mLine) {
						// 원래 이동하고 있었던
						// next -> pre ==> next -> mStation 으로 변경
						backWard.to = mStation;
						break;
					}
				}

				forward.to = mStation;

				// 새로운 정류장에서 원래 도착지
				LINES[mStation].add(new Line(mLine, nextStation, true, 1, 0));
				// 새로운 정류장에서 원래 출발지(역방향 간선)
				LINES[mStation].add(new Line(mLine, mPrevStation, false, 1, 0));

				break;
			}
		}
	}

	// mStartStation에서 mEndStation으로 이동한다.
	// return : 이동한 시간을 분단위로 반환
	// ** 최단 경로 구하기 **
	public int minTravelTime(int mStartStation, int mEndStation) {
		// 시작지점 == 종료지점
		if (mStartStation == mEndStation)
			return 0;

		for (int i = 0; i < 5; i++) {
			Arrays.fill(minCosts[i], Integer.MAX_VALUE);
		}

		// 우선순위 큐 사용
		// 큐<노선정보>
//		Queue<Line> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
		
		Queue<Line> q = new PriorityQueue<>(new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return o1.cost - o2.cost;
			}
		});

		for (Line line : LINES[mStartStation]) {
			q.add(line);
			minCosts[line.num][line.to] = 1; // 초기 비용
		}

		// 반복문
		while (!q.isEmpty()) {
			Line now = q.poll();

			if (now.to == mEndStation) {
				return now.cost; // 최소 비용
			}

			if (now.cost > minCosts[now.num][now.to])
				continue;

			// 새로운 간선들에 대한 정보 확인[현재 간선]
			for (Line next : LINES[now.to]) {
//				int nextCost = now.cost + 1;
				int nextCost = now.cost + next.cost;

				// 환승 체크
				if (now.num != next.num) {
					// 환승 비용 발생
					nextCost++;
				}

				if (nextCost >= minCosts[next.num][next.to])
					continue;

				minCosts[next.num][next.to] = nextCost;

				q.add(new Line(next.num, next.to, next.isForward, nextCost, 0));
			}
		}

		return -1;
	}

	// mStartStation : 출발 정류장
	// mEdnStation : 도착 정류장
	// mStartStation 정류장에서 mEndStation 정류장으로 최소 환승 횟수 리턴
	public int minTransfer(int mStartStation, int mEndStation) {
		// 시작지점 == 종료지점
		if (mStartStation == mEndStation)
			return 0;

		for (int i = 0; i < 5; i++) {
			Arrays.fill(minCosts[i], Integer.MAX_VALUE);
		}

		// 우선순위 큐 사용
		// 환승횟수
//		Queue<Line> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.transCnt));
		
		Queue<Line> q = new PriorityQueue<>(new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return o1.transCnt - o2.transCnt;
			}
		});
		
		for (Line line : LINES[mStartStation]) {
			q.add(line);
			minCosts[line.num][line.to] = 0; // 초기 비용
		}

		// 반복문
		while (!q.isEmpty()) {
			Line now = q.poll();

			if (now.to == mEndStation) {
				return now.transCnt; // 환승 횟수
			}

			if (now.transCnt > minCosts[now.num][now.to])
				continue;

			// 새로운 간선들에 대한 정보 확인[현재 간선]
			for (Line next : LINES[now.to]) {
				int nextCost = now.transCnt;

				// 환승 체크
				if (now.num != next.num) {
					// 환승 비용 발생
					nextCost++;
				}

				if (nextCost >= minCosts[next.num][next.to])
					continue;

				minCosts[next.num][next.to] = nextCost;

				q.add(new Line(next.num, next.to, next.isForward, 1, nextCost));
			}
		}

		return -1;
	}
}