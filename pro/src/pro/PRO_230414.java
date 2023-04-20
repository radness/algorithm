package pro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 토글
public class PRO_230414 {
	static int T;	// 테스트 케이스
	static int N; 	// 배열의 길이
	static int M;	// 명령어의 갯수
	static int[] arr;
	static ArrayList<Integer> dist;	// 명령어의 길이 별 최대 연속 0값
	static ArrayList<Integer> numList;
	static int ans; // 테스트 케이스별 최대 길이
	static int[] visited;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		dist = new ArrayList<>();
		
		for (int tc = 0; tc < T; tc++) {	// 테스트케이스만큼
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			arr = new int[N];
			visited = new int[N];
			
			numList = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				visited[i] = arr[i];
			}
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < M; i++) {
				numList.add(Integer.parseInt(st.nextToken()));
			}
			
			dist.add(maxLength(tc));
			
		}
		
		for (int i = 0; i < dist.size(); i++) {
			System.out.println("#" + i + " " + dist.get(i));
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("기준시간 : " + (endTime - startTime));
	}

	private static int maxLength(int tc) {
		int[] maxVals = new int[M];
		
		for (int i = 0; i < numList.size(); i++) {
			int changeIndex = numList.get(i) - 1;
			
			arr[changeIndex] = arr[changeIndex] - 1 == 0 ? 0 : 1;

			for (int j = 0; j < arr.length; j++) {
				visited[j] = arr[j];
			}
			
			cnt = 0;
			
			ans += findMaxLength(arr, maxVals[i]);
		}
		
		return ans;
	}

	// 테스트 케이스별 0을 변경할때마다 찾는 최대 길이
	private static int findMaxLength(int[] arr, int maxVal) {
		for (int i = 0; i < arr.length; i++) {

			if (visited[i] == 1) {
				maxVal = Math.max(cnt, maxVal);
				cnt = 0;
				continue;
			}
			
			visited[i] = 1;
			cnt++;
		}
		
		maxVal = Math.max(cnt, maxVal);
		
		return maxVal;
	}
}
/*
2
9 4
1 0 1 0 1 0 1 1 0
5 8 5 7
8 4
1 0 0 1 0 0 0 1
7 1 3 4

 */
