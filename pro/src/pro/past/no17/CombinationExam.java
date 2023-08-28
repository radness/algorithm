package pro.past.no17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CombinationExam {
	static List<Integer> arr;
	static boolean[] visited = new boolean[5];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		arr = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			arr.add(i);
		}

		combination(arr, visited, 0, R);

	}

	private static void combination(List<Integer> arr, boolean[] visited, int index, int r) {
		if (r == 0) {
			for (int i = 0; i < arr.size(); i++) {
				if (visited[i])
					System.out.print(arr.get(i) + " ");
			}
			System.out.println();
			return;
		}

		if (index == arr.size()) {
			return;
		}

		visited[index] = true;	// �����ϴ� ���
		combination(arr, visited, index + 1, r - 1);

		visited[index] = false;	// �������� �ʴ� ���
		combination(arr, visited, index + 1, r);

	}
}
