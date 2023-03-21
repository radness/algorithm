package pro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class PRO_230310_기차역2 {
	static int T; // 테스트 케이스
	static int N; // 도시의 수
	static int Q; // 질문의 수

	static List<Train>[] LIST; // i번 도시에서(각각의 도시에서) 출발하는 기차 목록
	static long[] MIN_COST; // 수도에서 i번 도시까지 이동하는 최소 비용
	static int[] PRE_STATIONS; // 수도에서 i번 도시가지 최소비용으로 이동할 때 직전 역 번호
	static boolean[] IS_CLOSED; // i번 역의 폐쇄 여부

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/pro/PRO_230310_기차역"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력

		for (int tc = 1; tc <= T; tc++) { // 테스트 케이스만큼 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 도시의 수
			Q = Integer.parseInt(st.nextToken()); // 질문의 수

			LIST = new ArrayList[N + 1];
			MIN_COST = new long[N + 1];
			PRE_STATIONS = new int[N + 1];
			IS_CLOSED = new boolean[N + 1];

			for (int i = 1; i <= N; i++) {
				LIST[i] = new ArrayList<>();
				MIN_COST[i] = Long.MAX_VALUE;
			}

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()); // 시작
				int end = Integer.parseInt(st.nextToken()); // 도착
				int cost = Integer.parseInt(st.nextToken()); // 비용

				LIST[start].add(new Train(end, cost));
				LIST[end].add(new Train(start, cost));
			}

			// 수도에서 각 도시까지의 최단거리 탐색, 수도에서 이동 시 순환하는 부분이 없기 때문에
			// 우선순위 큐를 사용할 필요가 없다.
			MIN_COST[1] = 0; // 초기화
			Queue<Train> queue = new LinkedList<>();
			queue.add(new Train(1, 0)); // 도착지점 : 1, 비용 : 0

			while (!queue.isEmpty()) {
				Train current = queue.poll(); // 값을 꺼내서

				for (Train next : LIST[current.end]) {
					if (MIN_COST[next.end] <= current.cost + next.cost)
						continue;

					MIN_COST[next.end] = current.cost + next.cost; // 최소값은 현재 비용 + 다음 방문 비용
					PRE_STATIONS[next.end] = current.end; // 최단 경로로 이동할 경우 이전 역 정보 저장
					queue.add(new Train(next.end, current.cost + next.cost));
				}
			}

			long ans = 0; // 정답

			for (int i = 0; i < Q; i++) { // 질문 수만큼 loop
				st = new StringTokenizer(br.readLine());

				int target = Integer.parseInt(st.nextToken()); // 도착지 정보
				int station = Integer.parseInt(st.nextToken()); // 출발 역 정보

				if (target == 0) { // 폐쇄된 역이라면
					IS_CLOSED[station] = true; // 폐쇄 처리
					continue;
				}

				// 입력받은 위치에서 폐쇄되지 않은 가장 가까운 역 확인
				int nearStation = findNearestStation(station);

				ans += MIN_COST[nearStation];
			}

			System.out.println("#" + tc + " " + ans);
		}
	}

	// 폐쇄역일 때 가장 가까운 역 찾기
	private static int findNearestStation(int station) {
		if (!IS_CLOSED[station])
			return station;

		if (station == 1)
			return station;
		
		// 폐쇄된 역이라면 최단 경로로 이동할 경우의 직전 역에서
		// 가장 가까운 역을 확인해서 값을 저장 후 리턴
		return PRE_STATIONS[station] = findNearestStation(PRE_STATIONS[station]);
	}

	static class Train {
		int end; // 도착지점
		long cost; // 비용

		public Train(int end, long cost) {
			this.end = end;
			this.cost = cost;
		}
	}
}
