package expedition.day1.DAY1_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 강사님 코드
public class Main2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st; 

	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		// 아파트의 정보를 입력받습니다.
		// 저희 입력을 받으면서 DAT를 구성 =>
		// index : 주민 번호
		// value : 이 아파트에 이 주민번호를 가진 사람이 몇명이 사는가? 
		int[] apt = new int[100000+1];
		
		for(int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < width; j++) {
				int num = Integer.parseInt(st.nextToken());
				// 아파트에는 이 주민번호의 사람이 한명 더 있다!
				apt[num]++; 
			}
		}
		
		// 블랙리스트를 입력
		st = new StringTokenizer(br.readLine());
		int bheight = Integer.parseInt(st.nextToken());
		int bwidth = Integer.parseInt(st.nextToken());
		
		int cnt = 0; 
		for(int i = 0; i < bheight; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < bwidth; j++) {
				int num = Integer.parseInt(st.nextToken());
				// 이 num명 만큼 아파트에 존재하는 사람들 = 모두 블랙리스트이다!
				// 계속 15가 들어오면 계속 더해지고 있습니다.
				cnt += apt[num]; 
				// 이미 한번 체크된 블랙리스트라면 -> 더이상 더해졌을때 영향을 안주도록
				// num번은 이미 블랙리스트 체크되었으니까
				// 한번 더 들어오면 -> "영향을 안주도록" -> 0으로 바꿔주면 됩니다.
				apt[num] = 0; 
			}
		}
		
		System.out.println(cnt);
		// 일반 시민 = 아파트의 총 인원수 - 블랙리스트의 수
		System.out.println(height * width - cnt);
	}
}
