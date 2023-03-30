package rowCutting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 도로의확장 {
	private static final long INF = 1_000_000_000_000_000_000l;
	private static int 도시수;
	static ArrayList<도로>[] 정방향list;
	static ArrayList<도로>[] 역방향list;

	public static void main(String[] args) throws Exception {
		Long start = System.currentTimeMillis();
		System.setIn(new FileInputStream(new File("도로의확장_input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			도시수 = Integer.parseInt(st.nextToken());
			int 도로수 = Integer.parseInt(st.nextToken());
			정방향list = new ArrayList[도시수 + 1];
			역방향list = new ArrayList[도시수 + 1];
			for (int i = 1; i <= 도시수; i++) {
				정방향list[i] = new ArrayList<도로>();
				역방향list[i] = new ArrayList<도로>();
			}
			for (int i = 1; i <= 도로수; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				long 시간 = Long.parseLong(st.nextToken());
				정방향list[from].add(new 도로(to, 시간));
				역방향list[to].add(new 도로(from, 시간));
			}
			long[][] 정dist = 다익스트라(1, 도시수);
			long[][] 역dist = 다익스트라(도시수, 1);
			long 기존최단시간 = 정dist[도시수][0] + 역dist[1][0];
			long 정확장최단시간 = 정dist[도시수][1] + 역dist[1][0];
			long 역확장최단시간 = 정dist[도시수][0] + 역dist[1][1];
			if (기존최단시간 <= 정확장최단시간 && 기존최단시간 <= 역확장최단시간) {
				System.out.println("#" + testcase + " -1");
			} else if (정확장최단시간 < 역확장최단시간) {
				System.out.println("#" + testcase + " " + 정확장최단시간 + " " + 추적(1, 도시수, 정dist));
			} else if (정확장최단시간 > 역확장최단시간) {
				System.out.println("#" + testcase + " " + 역확장최단시간 + " " + 추적(도시수, 1, 역dist));
			} else {
				System.out.println("#" + testcase + " " + 정확장최단시간 + " " + (추적(1, 도시수, 정dist) + 추적(도시수, 1, 역dist)));
			}
		}
		System.out.println("time(ms)=" + (System.currentTimeMillis() - start));
	}

	private static int 추적(int 시작도시, int 종료도시, long[][] dist) {
		LinkedList<Item> q = new LinkedList<Item>();
		boolean[][] isvisit = new boolean[도시수 + 1][2];
		q.add(new Item(종료도시, dist[종료도시][1], 1));
		HashSet<Long> set = new HashSet<Long>();
		while (!q.isEmpty()) {
			Item cur = q.poll();
			if (isvisit[cur.위치][cur.확장여부])
				continue;
			isvisit[cur.위치][cur.확장여부] = true;
			for (도로 pre : 역방향list[cur.위치]) {
				if (dist[pre.to][cur.확장여부] + pre.시간 == cur.시간) {
					q.add(new Item(pre.to, dist[pre.to][cur.확장여부], cur.확장여부));
				}
			}
			if (cur.확장여부 == 1) {
				for (도로 pre : 정방향list[cur.위치]) {
					if (dist[pre.to][0] + pre.시간 == cur.시간) {
						set.add(pre.to * 100_000l + cur.위치);
						q.add(new Item(pre.to, dist[pre.to][0], 0));
					}
				}
			}
		}
		return set.size();
	}

	private static long[][] 다익스트라(int 시작도시, int 종료도시) {
		PriorityQueue<Item> pq = new PriorityQueue<Item>(new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				return Long.compare(o1.시간, o2.시간);
			}
		});
		long[][] dist = new long[도시수 + 1][2];
		for (int i = 0; i <= 도시수; i++) {
			dist[i][0] = INF;
			dist[i][1] = INF;
		}
		dist[시작도시][0] = 0;
		pq.add(new Item(시작도시, 0, 0));
		while (!pq.isEmpty()) {
			Item cur = pq.poll();
			if (dist[cur.위치][cur.확장여부] < cur.시간)
				continue;
			for (도로 next : 정방향list[cur.위치]) {
				if (dist[next.to][0] > cur.시간 + next.시간) {
					if (cur.확장여부 == 0) {
						dist[next.to][0] = cur.시간 + next.시간;
						pq.add(new Item(next.to, dist[next.to][0], 0));
					} else if (cur.확장여부 == 1) {
						if (dist[next.to][1] > cur.시간 + next.시간) {
							dist[next.to][1] = cur.시간 + next.시간;
							pq.add(new Item(next.to, dist[next.to][1], 1));
						}
					}
				}
			}
			if (cur.확장여부 == 0) {
				for (도로 next : 역방향list[cur.위치]) {
					if (dist[next.to][0] > cur.시간 + next.시간) {
						if (dist[next.to][1] > cur.시간 + next.시간) {
							dist[next.to][1] = cur.시간 + next.시간;
							pq.add(new Item(next.to, dist[next.to][1], 1));
						}
					}
				}
			}
		}
		return dist;
	}

	static class 도로 {
		int to;
		long 시간;

		public 도로(int to, long 시간) {
			super();
			this.to = to;
			this.시간 = 시간;
		}
	}

	static class Item {
		int 위치;
		long 시간;
		int 확장여부;

		public Item(int 위치, long 시간, int 확장여부) {
			super();
			this.위치 = 위치;
			this.시간 = 시간;
			this.확장여부 = 확장여부;
		}
	}
}