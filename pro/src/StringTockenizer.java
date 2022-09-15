import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StringTockenizer {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int sum = 0;
		
		while (st.hasMoreTokens()) {
			sum += Integer.valueOf(st.nextToken());
		}
		
		System.out.println(sum);
	}
}
