package expedition.day8.DAY3_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 인디언 합창단
public class Main {

	static int[] INDIANS;
	// index: 연주자 / 소속
	// values : // 소속에 포함된 인원 수
	static int[] GROUPS;
	
	static int grpCnt;
	static int singleCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		grpCnt = 0;
		singleCnt = 0;
		INDIANS = new int[26];
		GROUPS = new int[26];

		for (int i = 0; i < 26; i++) {
			INDIANS[i] = i;
			GROUPS[i] = 1;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int iName1 = st.nextToken().charAt(0) - 'A';
			int iName2 = st.nextToken().charAt(0) - 'A';
			Union(iName1, iName2);
		}

		// GROUP을 보면서
		// 2명 이상이면 하나의 팀
		// -1은 카운팅 X
		for (int i = 0; i < 26; i++) {
			if (GROUPS[i] == 1)
				singleCnt++;

			else if (GROUPS[i] > 1)
				grpCnt++;

		}

		System.out.println(grpCnt);
		System.out.println(singleCnt);

	}

	private static void Union(int A, int B) {
		int pa = Find(A);
		int pb = Find(B);

		if (pa == pb) {
			return;
		}

		// B가 A 아래로 들어간다 -> B의 소속은 A
		INDIANS[pb] = pa;

		// A쪽으로 B의 소속 인원들이 옮겨간다.
		GROUPS[pa] += GROUPS[pb];

		// B 소속은 더이상 존재하지 않는다.
		GROUPS[pb] = -1;
	}

	private static int Find(int node) {
		if (node == INDIANS[node])
			return node;
		
		return INDIANS[node] = Find(INDIANS[node]);
	}
}
