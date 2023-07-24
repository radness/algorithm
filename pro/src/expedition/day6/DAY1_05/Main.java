package expedition.day6.DAY1_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 츄러스

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N; // 츄러스의 개수
	static int K; // 잘라야 하는 츄러스의 개수
	static int[] churros;
	static int MAXlen = Integer.MIN_VALUE;

	static boolean test(int mid) {
		// mid길이로 모든 churros를 잘랐을때 K개 이상이 만들어지는가?
		// 다 잘라봐야합니다. (O(N))
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int now = churros[i];
			// 이 now번 츄려스를 mid 길이로 잘랐을떄, 몇개가 완성될까?
			cnt += now / mid;
			if (cnt >= K)
				return true;
		}
		// 다 짤라봤는데, K개 안되네...?
//		if(cnt >= K)
//			return true; 
		return false;
	}

	// 최종시간복잡도 : O(NlogN)
	static int psearch() {
		// parametric search = O(logN)
		// mid = 이 길이의 츄려스가 K개를 만들수 있는 최대 길이이다!
		int left = 1; // 자를 수 있는 츄러스의 최소 길이
		int right = MAXlen; // 자를 수 있는 츄러스의 최대 길이
		while (left <= right) {
			int mid = (left + right) / 2; // 이게 정답이다!
			// test = O(N)
			if (test(mid)) {
				// test = 이 mid 길이로 잘랐을때, K개 이상의 츄러스를 만들 수 있는가? (일정한 길이)
				// 통과했다면 -> 이거보다 더 길게 잘라도 가능할까?
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		churros = new int[N];
		// 츄러스들의 입력
		for (int i = 0; i < N; i++) {
			int length = Integer.parseInt(br.readLine());
			churros[i] = length;
			// 가장 긴 길이를 기록
			if (length > MAXlen)
				MAXlen = length;
		}
		System.out.println(psearch());
	}
}