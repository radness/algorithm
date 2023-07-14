package expedition.day5.DAY5_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 액체괴물 놀이
public class Main {
	
	static PriorityQueue<Integer> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());	// 액체괴물 수
		pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		
		int time = 0;
		
		while (!pq.isEmpty()) {
			if (pq.size() < 2)
				break;
			
			int A = pq.poll();
			int B = pq.poll();
			
			int tmpSum = A + B;
			time += tmpSum;
			pq.add(tmpSum);
			
		}
		
		System.out.println(time);
	}
}
