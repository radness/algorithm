package basic;

import java.util.ArrayList;
import java.util.Arrays;

/* 인접리스트 초기화 후 연결 노드 출력
*/
public class GraphExam2 {
	static ArrayList<Integer>[] arr = new ArrayList[5];
	static int[] value = { 4, 1, 5, 3, 7 };

	public static void main(String[] args) throws Exception {
		run();
	}

	static void run() {
		arr[0] = new ArrayList<>(Arrays.asList(1, 2));
		arr[1] = new ArrayList<>(Arrays.asList(0, 2));
		arr[2] = new ArrayList<>(Arrays.asList(2, 3));
		arr[3] = new ArrayList<>(Arrays.asList(4));
		arr[4] = new ArrayList<>();
		
		int tar = 2;

		for (int i = 0; i < arr[tar].size(); i++) {
			int next = arr[tar].get(i);
			System.out.println(next + "번, " + value[next]);
		}
	}
}
