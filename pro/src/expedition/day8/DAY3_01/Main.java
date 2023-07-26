package expedition.day8.DAY3_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] PARENT;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		PARENT = new int[N+1];
		
		for (int i = 0; i < N+1; i++) {
			PARENT[i] = i;
		}

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			int K = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			if (K == 0) {
				// 노드 A, B 그룹 찾기
				if (Find(A) == Find(B)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			} else if (K == 1) {
				// 노드 A, B를 연결
				Union(A, B);
			}
		}
	}

	private static void Union(int A, int B) {
		int pa = Find(A);
		int pb = Find(B);

		if (pa == pb) {
			return;
		}

		// B가 A 아래로 들어간다 -> B의 소속은 A
		PARENT[pb] = pa;
	}

	// 같은 그룹이면 YES, 다른 그룹이면 NO
	private static int Find(int node) {
		if (PARENT[node] == node) {
			return node;
		}

		return PARENT[node] = Find(PARENT[node]);
	}
}
