package expedition.day8.DAY3_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전기차 충전
public class Main {

	static int T; // 충전기의 개수 (1~T번 버젼까지 있다)
	static int N; // 대기하고 있는 차량의 수

	static int[] PARENT;

	static int Find(int node) {
		if (node == PARENT[node])
			return node;
		return PARENT[node] = Find(PARENT[node]);
	}

	static void Union(int A, int B) {
		int pa = Find(A);
		int pb = Find(B);
		if (pa == pb)
			return;
		PARENT[pb] = pa;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());

		PARENT = new int[T + 1]; // 1~T번까지
		for (int i = 0; i <= T; i++)
			PARENT[i] = i;

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			// 현재 차 버젼
			int carver = Integer.parseInt(br.readLine());
			// 이 차가 어디에 충전할 수 있는가?를 찾아갑니다.
			int chargernum = Find(carver);
			// 이제 충전할 충전기가 없다면 => 종료
			// 0번으로 유도하고 있다면 더 이상 충전할 곳이 없다.
			if (chargernum == 0)
				break;
			// 일단은 이 carver 버젼의 차가 충전할 곳은 있다!
			// 충전을 시킨다.
			cnt++;
			// ** 차선책 -> chargernum -> 사용됩니다.
			// 얘에서 바로 하나 위 (버젼은 아래) 충전기로 연결
			// Union(A,B) = A가 대표가 된다 했으니
			Union(chargernum - 1, chargernum);
		}
		System.out.println(cnt);
	}

}
