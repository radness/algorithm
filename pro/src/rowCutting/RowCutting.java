package rowCutting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/* 막대기 자르기
 *  
 * */
public class RowCutting {
	private static int N = 0; // 막대의 길이
	private static int cost[] = null; // 길이별 가치
	private static int maxByLength[] = null; // 길이에 따른 최대값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N + 1];
		maxByLength = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1 ; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		int output = dp(N);
		
		bw.write(String.valueOf(output));
		bw.flush();
		bw.close();
	}
	
	public static int dp(int n) {
		if (n == 1) {
			return maxByLength[1] = cost[1];
		} else if (maxByLength[n] != 0) {
			return maxByLength[n];
		}
		
		int tmp = 0;
		int output = 0;
		
		for (int i = 1; i < n; i++) {
			tmp = Math.max(cost[n], dp(i) + dp(n - i));
			output = Math.max(output, tmp);
		}
		
		return maxByLength[n] = output;
	}
}
