package twoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* Two Pointer
 * 리스트에 순차적으로 접근해야 할 두개의 점의 위치를 기록하면서 처리하는 알고리즘
 * 정렬되어 있는 두 리스트의 합집팝에 사용됨.
 * 병합정렬(Merge Sort)의 conquer영역 기초
 * 
 * 대표예제 : 백준 2003 (수들의 합2)
 * https://www.acmicpc.net/problem/2003
 * 시간복잡도 : O(N)
 * 
 * */
public class BJ_2003 {
	static int[] arr;
	static int N;
	static int M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		
		ans = getCnt();
		
		System.out.println(ans);
		
	}

	private static int getCnt() {
		int startPoint = 0;
		int endPoint = 0;
		int sum = 0;
		int cnt = 0;	// 경우의 수
		
		for (int i = startPoint; i <= arr.length; i++) {
			while (sum < M && endPoint < N) {
				sum += arr[endPoint];
				endPoint++;
			}
			
			if (sum == M)
				cnt++;
			
			sum -= arr[startPoint];
		}
		
		return cnt >= 0 ? cnt : -1;
	}
}
/*
입력
N M
4 2
1 1 1 1

출력
3
*/
