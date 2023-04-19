package basic;

import java.util.ArrayList;
import java.util.Arrays;

// ����ġ �׷���
// -> ��� ��� Ž��
public class GraphExam4 {
	static ArrayList<Node>[] arr = new ArrayList[4];
	static int[] visited = new int[4];
	static int result = 0 ;
	
	public static void main(String[] args) throws Exception {
		run();
	}

	static void run() {
		arr[0] = new ArrayList<>(Arrays.asList(new Node(1, 3), new Node(2, 5), new Node(3, 8)));
		arr[1] = new ArrayList<>(Arrays.asList(new Node(2, 1), new Node(3, 8)));
		arr[2] = new ArrayList<>(Arrays.asList(new Node(0, 1), new Node(3, 2)));
		arr[3] = new ArrayList<>();

		visited[0] = 1;
		
		dfs(0, 0);	// 0�� ��忡�� �����ϰ�(ù��° ���ڰ�), 0����(�ι�° ���ڰ�)���� ����
		
		System.out.println(result + "����");
		
	}

	private static void dfs(int now, int sum) {
		if (now == 3) {
			result = Math.min(result, sum);	// �ּڰ� ����
			return;
		}
			
		for (int i = 0; i < arr[now].size(); i++) {
			Node next = arr[now].get(i);
			
			if (visited[next.n] == 1)
				continue;
			
			visited[next.n] = 1;
			dfs(next.n, sum + next.price);	// ���� �湮�ϴ� Ž�������� ���� sum + ����ġ ������ �̵�
			visited[next.n] = 0;
		}
	}
}

class Node {
	int n;
	int price;
	
	public Node(int n, int price) {
		this.n = n;
		this.price = price;
	}
}
