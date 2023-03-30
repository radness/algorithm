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

public class ������Ȯ�� {
	private static final long INF = 1_000_000_000_000_000_000l;
	private static int ���ü�;
	static ArrayList<����>[] ������list;
	static ArrayList<����>[] ������list;

	public static void main(String[] args) throws Exception {
		Long start = System.currentTimeMillis();
		System.setIn(new FileInputStream(new File("������Ȯ��_input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			���ü� = Integer.parseInt(st.nextToken());
			int ���μ� = Integer.parseInt(st.nextToken());
			������list = new ArrayList[���ü� + 1];
			������list = new ArrayList[���ü� + 1];
			for (int i = 1; i <= ���ü�; i++) {
				������list[i] = new ArrayList<����>();
				������list[i] = new ArrayList<����>();
			}
			for (int i = 1; i <= ���μ�; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				long �ð� = Long.parseLong(st.nextToken());
				������list[from].add(new ����(to, �ð�));
				������list[to].add(new ����(from, �ð�));
			}
			long[][] ��dist = ���ͽ�Ʈ��(1, ���ü�);
			long[][] ��dist = ���ͽ�Ʈ��(���ü�, 1);
			long �����ִܽð� = ��dist[���ü�][0] + ��dist[1][0];
			long ��Ȯ���ִܽð� = ��dist[���ü�][1] + ��dist[1][0];
			long ��Ȯ���ִܽð� = ��dist[���ü�][0] + ��dist[1][1];
			if (�����ִܽð� <= ��Ȯ���ִܽð� && �����ִܽð� <= ��Ȯ���ִܽð�) {
				System.out.println("#" + testcase + " -1");
			} else if (��Ȯ���ִܽð� < ��Ȯ���ִܽð�) {
				System.out.println("#" + testcase + " " + ��Ȯ���ִܽð� + " " + ����(1, ���ü�, ��dist));
			} else if (��Ȯ���ִܽð� > ��Ȯ���ִܽð�) {
				System.out.println("#" + testcase + " " + ��Ȯ���ִܽð� + " " + ����(���ü�, 1, ��dist));
			} else {
				System.out.println("#" + testcase + " " + ��Ȯ���ִܽð� + " " + (����(1, ���ü�, ��dist) + ����(���ü�, 1, ��dist)));
			}
		}
		System.out.println("time(ms)=" + (System.currentTimeMillis() - start));
	}

	private static int ����(int ���۵���, int ���ᵵ��, long[][] dist) {
		LinkedList<Item> q = new LinkedList<Item>();
		boolean[][] isvisit = new boolean[���ü� + 1][2];
		q.add(new Item(���ᵵ��, dist[���ᵵ��][1], 1));
		HashSet<Long> set = new HashSet<Long>();
		while (!q.isEmpty()) {
			Item cur = q.poll();
			if (isvisit[cur.��ġ][cur.Ȯ�忩��])
				continue;
			isvisit[cur.��ġ][cur.Ȯ�忩��] = true;
			for (���� pre : ������list[cur.��ġ]) {
				if (dist[pre.to][cur.Ȯ�忩��] + pre.�ð� == cur.�ð�) {
					q.add(new Item(pre.to, dist[pre.to][cur.Ȯ�忩��], cur.Ȯ�忩��));
				}
			}
			if (cur.Ȯ�忩�� == 1) {
				for (���� pre : ������list[cur.��ġ]) {
					if (dist[pre.to][0] + pre.�ð� == cur.�ð�) {
						set.add(pre.to * 100_000l + cur.��ġ);
						q.add(new Item(pre.to, dist[pre.to][0], 0));
					}
				}
			}
		}
		return set.size();
	}

	private static long[][] ���ͽ�Ʈ��(int ���۵���, int ���ᵵ��) {
		PriorityQueue<Item> pq = new PriorityQueue<Item>(new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				return Long.compare(o1.�ð�, o2.�ð�);
			}
		});
		long[][] dist = new long[���ü� + 1][2];
		for (int i = 0; i <= ���ü�; i++) {
			dist[i][0] = INF;
			dist[i][1] = INF;
		}
		dist[���۵���][0] = 0;
		pq.add(new Item(���۵���, 0, 0));
		while (!pq.isEmpty()) {
			Item cur = pq.poll();
			if (dist[cur.��ġ][cur.Ȯ�忩��] < cur.�ð�)
				continue;
			for (���� next : ������list[cur.��ġ]) {
				if (dist[next.to][0] > cur.�ð� + next.�ð�) {
					if (cur.Ȯ�忩�� == 0) {
						dist[next.to][0] = cur.�ð� + next.�ð�;
						pq.add(new Item(next.to, dist[next.to][0], 0));
					} else if (cur.Ȯ�忩�� == 1) {
						if (dist[next.to][1] > cur.�ð� + next.�ð�) {
							dist[next.to][1] = cur.�ð� + next.�ð�;
							pq.add(new Item(next.to, dist[next.to][1], 1));
						}
					}
				}
			}
			if (cur.Ȯ�忩�� == 0) {
				for (���� next : ������list[cur.��ġ]) {
					if (dist[next.to][0] > cur.�ð� + next.�ð�) {
						if (dist[next.to][1] > cur.�ð� + next.�ð�) {
							dist[next.to][1] = cur.�ð� + next.�ð�;
							pq.add(new Item(next.to, dist[next.to][1], 1));
						}
					}
				}
			}
		}
		return dist;
	}

	static class ���� {
		int to;
		long �ð�;

		public ����(int to, long �ð�) {
			super();
			this.to = to;
			this.�ð� = �ð�;
		}
	}

	static class Item {
		int ��ġ;
		long �ð�;
		int Ȯ�忩��;

		public Item(int ��ġ, long �ð�, int Ȯ�忩��) {
			super();
			this.��ġ = ��ġ;
			this.�ð� = �ð�;
			this.Ȯ�忩�� = Ȯ�忩��;
		}
	}
}