package pro.recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P15713 {
	static class Edge {
		long to;
		long cost;

		public Edge(long to, long cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	static List<Edge>[] LIST; // [i] : id 가 i인 높이와 연결된 간선
	static Map<Long, Integer> HEIGHT_TO_ID; // <key, value> : 높이 key 의 id
	static List<Long> HEIGHT_LIST; // 트램폴린이 있는 높이 목록
	static long[] MIN_COST;
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		long S = Long.parseLong(st.nextToken());

		LIST = new ArrayList[N * 2];
		MIN_COST = new long[N * 2];
		for (int i = 0; i < N * 2; i++) {
			LIST[i] = new ArrayList<>();
			MIN_COST[i] = Long.MAX_VALUE / 2;
		}
		HEIGHT_TO_ID = new HashMap<>();
		HEIGHT_LIST = new ArrayList<>();
		idx = 0;

		long[][] INPUTS = new long[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			INPUTS[i][0] = Long.parseLong(st.nextToken());
			INPUTS[i][1] = Long.parseLong(st.nextToken());
			if (!HEIGHT_TO_ID.containsKey(INPUTS[i][0])) {
				HEIGHT_TO_ID.put(INPUTS[i][0], idx++);
			}
			int id = HEIGHT_TO_ID.get(INPUTS[i][0]);
			LIST[id].add(new Edge(INPUTS[i][0] + INPUTS[i][1], INPUTS[i][1]));
			HEIGHT_LIST.add(INPUTS[i][0]);
		}
		Collections.sort(HEIGHT_LIST);
		for (int i = HEIGHT_LIST.size() - 1; i > 0; i--) {
			if (HEIGHT_LIST.get(i) == HEIGHT_LIST.get(i - 1)) {
				continue;
			}
			int id = HEIGHT_TO_ID.get(HEIGHT_LIST.get(i));
			LIST[id].add(new Edge(HEIGHT_LIST.get(i - 1), HEIGHT_LIST.get(i) - HEIGHT_LIST.get(i - 1)));
		}

		for (long[] input : INPUTS) {
			long height = input[0] + input[1];
			int i = Collections.binarySearch(HEIGHT_LIST, height);
			if (i >= 0) {
				continue;
			}
			i += 2;
			i *= -1;
			if (i < 0) {
				continue;
			}
			if (!HEIGHT_TO_ID.containsKey(height)) {
				HEIGHT_TO_ID.put(height, idx++);
			}
			int id = HEIGHT_TO_ID.get(height);
			LIST[id].add(new Edge(HEIGHT_LIST.get(i), height - HEIGHT_LIST.get(i)));
		}

		if (!HEIGHT_TO_ID.containsKey(0L)) {
			System.out.println("Ducks can't fly");
			return;
		}

		Queue<Edge> que = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));
		que.add(new Edge(0L, 0L));
		MIN_COST[HEIGHT_TO_ID.get(0L)] = 0L;
		while (!que.isEmpty()) {
			Edge now = que.poll();
			if (now.to == S) {
				System.out.println(now.cost);
				return;
			}
			int fromId = HEIGHT_TO_ID.get(now.to);
			if (MIN_COST[fromId] < now.cost) {
				continue;
			}

			for (Edge next : LIST[fromId]) {
				if (next.to >= S) {
					que.add(new Edge(S, now.cost + S - now.to));
					continue;
				}
				int toId = HEIGHT_TO_ID.get(next.to);
				if (MIN_COST[toId] <= now.cost + next.cost) {
					continue;
				}
				MIN_COST[toId] = now.cost + next.cost;
				que.add(new Edge(next.to, now.cost + next.cost));
			}
		}
		System.out.println("Ducks can't fly");
	}
}
/*
 * 4 10 0 7 0 5 6 3 9 11
 * 
 */