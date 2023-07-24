package expedition.day6.DAY1_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class DAY1_01 {

	static ArrayList<Node> list;
	
	static class Node implements Comparable<Node> {
		int num;
		char ch;

		public Node(int num, char ch) {
			this.num = num;
			this.ch = ch;
		}

		@Override
		public int compareTo(Node other) {
			// 1. 짝수 우선
			if (this.num % 2 == 0 && other.num % 2 == 1) return -1;
			if (this.num % 2 == 1 && other.num % 2 == 0) return 1;
			// 2. 작은 수 우선
			if (this.num < other.num) return -1;
			if (this.num > other.num) return 1;
			// 3. 문자 사전 순
			if (this.ch < other.ch) return -1;
			if (this.ch > other.ch) return 1;
			
			return 0;
		}
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st;

		list = new ArrayList<>();

		Integer[] arrA = new Integer[N];
		Node[] arrB = new Node[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arrB[i] = new Node(arrA[i], st.nextToken().charAt(0));
		}
		
		// 1.
		Arrays.sort(arrA);
		for (int i = 0; i < N; i++) {
			System.out.print(arrA[i] + " ");
		}
		System.out.println();
		
		// 2.
		Arrays.sort(arrA, Comparator.reverseOrder());
		for (int i = 0; i < N; i++) {
			System.out.print(arrA[i] + " ");
		}
		System.out.println();
		
		// 3.
		Arrays.sort(arrB);
		
		for(int i = 0; i < N; i++)
			System.out.print(arrB[i].num + " ");
		System.out.println();
		for(int i = 0; i < N; i++)
			System.out.print(arrB[i].ch + " ");
		System.out.println();


	}
}
