package pro.pre_test.p230623;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

// mins 패키지관리시스템
public class Main {

	static ArrayList<String> arr;
	static int Q;
	static HashMap<String, ArrayList<String>> hmap;
	static TreeSet<String> ans;
	static PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	});

	public static void main(String[] args) throws Exception {
//		long startTime = System.currentTimeMillis();

		System.setIn(new FileInputStream("src/pro/pre_test/p230623/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Q = Integer.parseInt(br.readLine()); // 쿼리 수

		hmap = new HashMap<>();

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			String command = st.nextToken();

			if ("depend".equals(command)) {
				String start = st.nextToken();
				String end = st.nextToken();

				if (hmap.get(start) != null && !hmap.get(start).isEmpty()) {
					ArrayList<String> arr = hmap.get(start);
					arr.add(end);
					hmap.put(start, arr);
				} else {
					ArrayList<String> arr = new ArrayList<>();
					arr.add(end);
					hmap.put(start, arr);
				}
			} else if ("setup".equals(command)) {
				String pack = st.nextToken();

				ans = new TreeSet<>();

				find(pack);

				if (!ans.isEmpty()) {
					Iterator<String> iter = ans.iterator();
					while (iter.hasNext()) {
						System.out.print(iter.next() + " ");
					}
				}
				System.out.println();

			}
		}

//		long endTime = System.currentTimeMillis();
//		System.out.println("걸린 시간 : " + (endTime - startTime) + "ms");

	}

	private static void find(String pack) {
		ArrayList<String> arr = hmap.get(pack);

		if (arr != null) {
			for (int i = 0; i < arr.size(); i++) {
				String prePack = arr.get(i);
				ans.add(prePack);
				pq.add(prePack);
			}

			while (!pq.isEmpty()) {
				String prePack = pq.poll();
				
				if (hmap.get(prePack) != null && !hmap.get(prePack).isEmpty()) {
					find(prePack);
				}
			}
		}
	}
}