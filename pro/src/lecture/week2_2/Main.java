package lecture.week2_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 그래프
public class Main {

	static int[] used;
	static ArrayList<Integer>[] arr;
	static int result = 0;
	
	static ArrayList<Integer>[] arr2;
	static int[] used2;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/lecture/week2_2/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		used = new int[N];

		arr = new ArrayList[N];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = new ArrayList<>();
		}

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int num = Integer.parseInt(st.nextToken());

			while (st.hasMoreTokens()) {
				int val = Integer.parseInt(st.nextToken());
				arr[num].add(val);
			}
		}

		used[0] = 1;
		dfs(0); // 경로의 수

		System.out.println();
		System.out.println("0번에서 3번 노드까지 갈 수 있는 경로 수 : " + result);

		// 인접리스트 초기화 후 연결 노드 출력
		arr2 = new ArrayList[5];
		int[] value = { 5, 12, 4, 7, 1 };
		
		arr2[0] = new ArrayList<>(Arrays.asList(3, 4));
		arr2[1] = new ArrayList<>(Arrays.asList(0, 2));
		arr2[2] = new ArrayList<>(Arrays.asList(0, 4));
		arr2[3] = new ArrayList<>(Arrays.asList(4));
		arr2[4] = new ArrayList<>();
		
		int tar = 2;
		
		System.out.println("tar(2번) 노드가 가리키고 있는 노드의 값 출력");
		
		for (int i = 0; i < arr2[tar].size(); i++) {
			int next = arr2[tar].get(i);
			System.out.println(next + "번 : " + value[next]);
		}

		// 그래프 탐색하기 DFS(인접리스트 초기화)
		// Cycle 방지 코드 추가 되어야 함.
		// Used 코드 추가(한번 방문한 노드는 다시 방문하지 않는다.)
		used2 = new int[5];
		used2[0] = 1;
		dfs2(0);
	}

	private static void dfs2(int now) {
		System.out.print(now + " ");
		
		for (int i = 0; i < arr2[now].size(); i++) {
			int next = arr2[now].get(i);
			
			if (used2[next] == 1)
				continue;
			
			used2[next] = 1;
			
			dfs2(next);
		}
	}

	// 경로의 수
	private static void dfs(int now) {
		if (now == 3) {
			result++;
			return;
		}

		// 0번에서 3번 노드까지 갈 수 있는 경로 출력
		for (int i = 0; i < arr[now].size(); i++) {
			int next = arr[now].get(i);
			System.out.print(next + " ");

			if (used[next] == 1)
				continue;

			used[next] = 1;

			dfs(next);

//			used[next] = 0;
		}
	}
}
