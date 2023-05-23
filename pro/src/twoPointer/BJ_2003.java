package twoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* Two Pointer
 * ����Ʈ�� ���������� �����ؾ� �� �ΰ��� ���� ��ġ�� ����ϸ鼭 ó���ϴ� �˰���
 * ���ĵǾ� �ִ� �� ����Ʈ�� �����˿� ����.
 * ��������(Merge Sort)�� conquer���� ����
 * 
 * ��ǥ���� : ���� 2003 (������ ��2)
 * https://www.acmicpc.net/problem/2003
 * �ð����⵵ : O(N)
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
		int cnt = 0;	// ����� ��
		
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
�Է�
N M
4 2
1 1 1 1

���
3
*/
