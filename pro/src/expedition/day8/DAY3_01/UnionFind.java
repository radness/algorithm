package expedition.day8.DAY3_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Union Find
public class UnionFind {

	static int[] PARENT;

	static int Find(int node) {
		// 1. 빠져나오는 종료 조건
		if (PARENT[node] == node) {
//			System.out.println("대표");
			return node;
		}

		return PARENT[node] = Find(PARENT[node]); 
	}

	static void Union(int A, int B) {
		
		int pa = Find(A);
		int pb = Find(B);
		
		if (pa == pb) {
			return;
		}
		
		// B가 A 아래로 들어간다 -> B의 소속은 A
		PARENT[pb] = pa;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		PARENT = new int[101];

		for (int i = 0; i < 101; i++)
			PARENT[i] = i - 1;
		PARENT[1] = 1;

		int target = 100;

		Union(1, 2);
		
		System.out.println(Find(target));

	}
}
