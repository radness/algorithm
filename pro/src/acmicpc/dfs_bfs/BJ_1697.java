package acmicpc.dfs_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 숨바꼭질
public class BJ_1697 {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		Queue<Subin> queue = new LinkedList<>();
		queue.add(new Subin(N, 0));
		
		int[] minCounts = new int[100_001];	// [i]번 위치로 오기 위한 최소 이동횟수
		Arrays.fill(minCounts, Integer.MAX_VALUE);
		
		while (!queue.isEmpty()) {
			Subin now = queue.poll();                    
			
			// 목적지에 도착한 경우
			if (now.pos == K) {
				System.out.println(now.count);
				break;
			}
			
			// 3가지의 독립된 탐색
			// now.pos + 1
			if (now.pos + 1 <= 100_000 && minCounts[now.pos + 1] > now.count + 1) {
				minCounts[now.pos + 1] = now.count + 1;
				queue.add(new Subin(now.pos + 1, now.count + 1));
			}
			// now.pos - 1
			if (now.pos - 1 >= 0 && minCounts[now.pos - 1] > now.count + 1) {
				minCounts[now.pos - 1] = now.count + 1;
				queue.add(new Subin(now.pos - 1, now.count + 1));
			}
			// now.pos * 2
			if (now.pos * 2 <= 100_000 && minCounts[now.pos * 2] > now.count + 1) {
				minCounts[now.pos * 2] = now.count + 1;
				queue.add(new Subin(now.pos * 2, now.count + 1));
			}
		}
		
	}

	static class Subin {
		int pos;
		int count;

		public Subin(int pos, int count) {
			super();
			this.pos = pos;
			this.count = count;
		}
	}
}

/*
5 17

4
*/