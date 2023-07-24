package expedition.day6.DAY1_02;

import java.io.*;
import java.util.*;

// 회의실 배정

public class DAY1_02 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st; 
	
	static class Meeting implements Comparable <Meeting>{
		int start;
		int end;
		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Meeting o) {
			// 빨리 끝나는 순 (종료시간이 빠른순)
			if(this.end < o.end) return -1;
			if(this.end > o.end) return 1;  
			
			// 시작이 빠른 순
			if(this.start < o.start) return -1;
			if(this.start > o.start) return 1;
			
			return 0; 
		}
	}

	public static void main(String[] args) throws  IOException {
		int N = Integer.parseInt(br.readLine());
		
		Meeting[] meetings = new Meeting[N]; 
		// N개의 회의의 #1. 시작, #2. 종료 시간에 대한 정보가 주어집니다.
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			meetings[i] = new Meeting(start, end); 
		}
		
		// 끝나는거 빠른순으로 정렬
		Arrays.sort(meetings);
		
		// 하나씩 보면서 선택(O(N))
		// -> 겹치지 않는 것들을 순차적으로 진행하면 된다
		//    --> 지금 회의의 종료 시간이랑 다음 회의의 시작 시간이 겹치면 안됩니다. 
		// 그리고, 무조건 가장 먼저 끝나는 회의는 진행을 하게 될겁니다. 
		// 첫 회의는 무조건 진행
		// 지금 시간 = 첫번째 회의가 끝나는 시간 
		int curtime = meetings[0].end;
		int cnt = 1; // 한개의 회의를 진행했다! 
		
		// 얘를 기준으로 순차적으로 빨리 끝나는 회의순으로 선택
		for(int i = 1; i < N; i++) {
			Meeting next = meetings[i]; 
			// 겹치는지 확인
			// 다음 회의의 시작 시간이 지금 진행하는 회의의 종료 시간보다 작으면 = 겹친다. 
			if(next.start < curtime)
				continue; // 선택 못함 
			// 이제 이 회의를 진행한다!
			curtime = next.end; // 이 다음회의가 끝나는 시간
			cnt++; // 한개의 회의를 더 진행했다!
		}
		System.out.println(cnt);
	}
}