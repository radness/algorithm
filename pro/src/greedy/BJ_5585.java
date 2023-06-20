package 그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 거스름돈 {
	static int N;	// 거스름돈 
	static int ans;
	static int[] coinType;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		ans = 0;
	
		coinType = new int[] {500, 100, 50, 10};
		
		for (int i = 0; i < coinType.length; i++) {
			ans += (N / coinType[i]);
			N %= coinType[i];
		}
		
		System.out.println("result : " + ans);
		
	}
}
