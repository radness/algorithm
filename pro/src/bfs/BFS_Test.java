package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접 리스트 구현
public class BFS_Test {
	static int N;
	static int M;
	static int V;

	static List<Integer>[] adList;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점의 갯수
		M = Integer.parseInt(st.nextToken()); // 간선의 갯수
		V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호

		adList = new LinkedList[N + 1];
		visit = new boolean[N + 1]; // 방문 배열

		for (int i = 0; i <= N; i++) {
			adList[i] = new LinkedList<>();
		}

		// 간선
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());

			adList[v1].add(v2);
			adList[v2].add(v1);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(adList[i]); // 방문 순서를 위해서 오름차순 정렬
		}

		System.out.println("bfs 인접리스트");

		bfs(V, adList, visit);
	}

	private static void bfs(int v, List<Integer>[] adList, boolean[] visit) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		visit[v] = true;	// 방문 체크
		
		q.add(v);	// 큐에 입력
		
		while (!q.isEmpty()) {
			v = q.poll();	// 꺼내서
			System.out.println("V : " + v);
			
			Iterator<Integer> iter = adList[v].listIterator();
			
			while (iter.hasNext()) {
				int item = iter.next();
				if (!visit[item]) {
					visit[item] = true;
					q.add(item);
				}
			}
		}
	
	}
}
