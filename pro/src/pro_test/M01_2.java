package pro_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 아파트 주민들
public class M01_2 {
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[5][4];
		
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 4; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		int tmp = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				tmp += map[i][j];
			}
			
			sb.append(tmp + " ");
			tmp = 0;
		}
		
		System.out.println(sb);
	}
}
