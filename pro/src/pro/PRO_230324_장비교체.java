package pro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class PRO_230324_장비교체 {
	static int T;
	static int N;

	static long[] WORKTIMES;
	static long[][] MAX_TIMES;

	static List<Integer>[] LIST; // i번 작업의 선행 작업 목록

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			// 초기화
			WORKTIMES = new long[N + 1];
			MAX_TIMES = new long[N + 1][2]; // i번 작업까지 장비 교체 유무 2가지 경우
			LIST = new ArrayList[N + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i <= N; i++) {
				WORKTIMES[i] = Long.parseLong(st.nextToken());
				LIST[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 1; i < N; i++) {	// N-1개 동안 loop
				int parent = Integer.parseInt(st.nextToken());
				LIST[parent].add(i);
			}
			
			// 리프 -> 루트 순서로 탐색하기 위한 순서 저장
			// N을 루트로해서 BFS의 방문순서대로 stack에 저장 -> 나중에 방문된 정점부터 조회된다.
			Queue<Integer> queue = new LinkedList<>();
			Stack<Integer> stack = new Stack<>();
			
			queue.add(N);
			stack.add(N);
			
			while (!queue.isEmpty()) {
				for (int next : LIST[queue.poll()]) {
					queue.add(next);
					stack.add(next);
				}
			}
			
			
		}
	}
}
