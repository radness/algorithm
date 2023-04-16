package acmicpc.dfs_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// ���ٲ���
public class BJ_1697 {
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		Queue<Subin> queue = new LinkedList<>();
		queue.add(new Subin(N, 0));
		
		int[] minCounts = new int[100_001];	// [i]�� ��ġ�� ���� ���� �ּ� �̵�Ƚ��
		Arrays.fill(minCounts, Integer.MAX_VALUE);
		
		while (!queue.isEmpty()) {
			Subin now = queue.poll();                    
			
			// �������� ������ ���
			if (now.pos == K) {
				System.out.println(now.count);
				break;
			}
			
			// 3������ ������ Ž��
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