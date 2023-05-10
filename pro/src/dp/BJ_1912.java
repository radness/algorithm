package dynamicProgramming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1912 {

	static int[] d;
	static int [] data;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		d = new int[N];
		data = new int[N];
		
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}

		d[0] = data[0];
		
		for (int i = 1; i < N; i++) {
			d[i] = Math.max(d[i-1] + data[i], data[i]);
		}

		result = d[0]; 

		for (int i = 0; i < N; i++) {
			result = Math.max(result, d[i]);
		}
		
		System.out.println(result);
	}
}
