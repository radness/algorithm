package pro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class PRO_230324_장비교체_샘플 {
	static long[] WORKTIMES; // i 번 작업의 소요시간
	static long[][] MAX_TIMES; // [i][0] = i 번 작업까지 장비 교체 없이 진행하는 시간
								// [i][1] = i 번 작업까지 장비 교체를 해서 진행하는 최소 시간
	static List<Integer>[] LIST; // i 번 작업의 선행작업 목록

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("java\\src\\miracom\\pro2023\\input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			WORKTIMES = new long[N + 1];
			MAX_TIMES = new long[N + 1][2];
			LIST = new ArrayList[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				WORKTIMES[i] = Long.parseLong(st.nextToken());
				LIST[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N; i++) {
				int parent = Integer.parseInt(st.nextToken());
				LIST[parent].add(i);
			}

			// LEAF -> ROOT 순서로 탐색하기 위한 순서 저장
			// N 을 루트로 해서 BFS의 방문순서대로 stack 에 저장함 -> 나중에 방문된 정점부터 조회 됨
			Queue<Integer> que = new LinkedList<>();
			Stack<Integer> stack = new Stack<>();
			que.add(N);
			stack.add(N);
			while (!que.isEmpty()) {
				for (int next : LIST[que.poll()]) {
					que.add(next);
					stack.add(next);
				}
			}

			while (!stack.isEmpty()) {
				int now = stack.pop();
				if (LIST[now].size() == 0) {
					// SUB NODE가 없는 경우 = LEAF
					MAX_TIMES[now][0] = WORKTIMES[now];
					MAX_TIMES[now][1] = 1;
					continue;
				}
				// LEAF 가 아닌 경우 = 1개 이상의 SUB NODE를 가지고 있는 경우
				long max = 0; // 선행 작업들 중 장비교체 없이 가장 오래걸리는 시간
				long max_sub = 0; // 장비교체 없이 선행 작업이 가장 오래 걸리는 작업을 장비교체를 해서 단축시킨 시간
				Queue<Long> mque = new PriorityQueue<>();
				for (int sub : LIST[now]) {
					if (MAX_TIMES[sub][0] > max) {
						max = MAX_TIMES[sub][0];
						max_sub = MAX_TIMES[sub][1];
					}
					mque.add(MAX_TIMES[sub][0]);
					if (mque.size() > 2) {
						mque.poll();
					}
				}

				MAX_TIMES[now][0] = max + WORKTIMES[now]; // 이번 작업 까지 장비교체를 안한 경우 소요시간
				MAX_TIMES[now][1] = max + 1; // 이번 장비를 교체한 경우의 소요시간
				if (mque.size() == 1) {
					// 직전 선행 작업이 1개인 경우
					// [선행작업까지 장비교체를 해서 단축시킨 시간 + 이번 장비의 소요시간] 과 기존 값 비교
					MAX_TIMES[now][1] = Math.min(MAX_TIMES[now][1], max_sub + WORKTIMES[now]);
				} else {
					// 직전 선행 작업이 2개 이상인 경우
					// 장비교체를 사용하여 선행작업을 완료하는 시간
					// = 기존 가장 오래걸린 선행작업을 장비교체하여 단축시킨 시간 vs 장비교체 없이 두 번째로 오래걸린 선행작업의 완료시간 중 큰 값
					MAX_TIMES[now][1] = Math.min(MAX_TIMES[now][1], Math.max(max_sub, mque.peek()) + WORKTIMES[now]);
				}
			}
			System.out.println("#" + test_case + " " + MAX_TIMES[N][1]);
		}
	}
}

/*
 * 3 8 3 2 3 2 5 3 4 1 2 3 8 2 3 7 8 10 8 9 7 2 6 6 6 2 6 6 10 10 2 10 9 2 10 9
 * 3 3 5 2 4 3 1
 * 
 * [결과] #1 8 #2 26 #3 7
 * 
 */
