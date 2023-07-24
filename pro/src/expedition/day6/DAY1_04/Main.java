import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 광물의 강도

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N; // 광물의 개수
	static int Q; // query의 개수 (연구원)
	static int[] arr;

	static int lower_bound(int target) {
		// mid = 얘가 target보다 크거나 같냐? (위치)
		int left = 0; // 배열의 첫 요소의 위치
		int right = N - 1; // 배열의 마지막 요소의 위치
		while (left <= right) {
			// mid = 이 위치의 값이 target이 처음 발생하는 위치다!
			int mid = (left + right) / 2;
			// test = 여기가 mid가 가리키는 값보다 크거나 같냐?
			if (arr[mid] >= target) {
				// 이거보다 앞에 target이 존재하나?
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	static int upper_bound(int target) {
		int left = 0; // 배열의 첫 요소의 위치
		int right = N - 1; // 배열의 마지막 요소의 위치
		while (left <= right) {
			int mid = (left + right) / 2;
			// test = 여기가 mid가 가리키는 값보다 크냐?
			if (arr[mid] > target) {
				// 얘가 target보다 큰 값중 가장 작은 값일까?
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		// 광물 입력
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		// binary search / parametric search =>
		// 구간을 찾는것 = sort
		Arrays.sort(arr);

		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			// S가 처음발생하는 위치
			int lb = lower_bound(S);
			// System.out.println(lb);

			// E보다 큰것이 처음 발생하는 위치
			int ub = upper_bound(E);
			// System.out.println(ub);

			// 중복된 개수만큼만 빼주면 된다.
			System.out.println(ub - lb);
		}
	}
}