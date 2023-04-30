package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class °Å½º¸§µ· {
	static int N;	// °Å½º¸§µ· 
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
