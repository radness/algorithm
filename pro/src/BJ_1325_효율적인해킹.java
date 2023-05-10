import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1325_효율적인해킹 {
	private static int N; // 노드
	private static int M; // 리프
	private static int[] visited; // 방문 배열
	private static int[] ans; // 컴퓨터 대수
	private static List<Integer>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String input = br.readLine();
		
		StringTokenizer st = new StringTokenizer(input);

		N = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		visited = new int[N + 1];
		ans = new int[N + 1];

		// 인접리스트 초기화
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		} 

		String[] inputs = new String[2];
		for (int i = 1; i <= M; i++) {
			input = br.readLine();
			inputs = input.split(" ");
			list[Integer.parseInt(inputs[0])].add(Integer.parseInt(inputs[1]));
		}

		// DFS
		for (int i = 1; i <= N; i++) {
			visited = new int[N+1];
			visited[i] = 1;
			dfs(i);
		}
		
		// BFS
		for (int i = 1; i <= N; i++) {
			// 모든 컴퓨터에 대해서 loop문을 돌아야하기 때문에 방문배열을 초기화
			visited = new int[N + 1];
			bfs(i);
		}
		
		// answer
		int max = 0;
		for (int i = 0; i <= N; i++) {
			max = Math.max(max,  ans[i]);
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <= N; i++) {
			
		}
		
		bw.close();
	}

	private static void bfs(int i) {
		// TODO Auto-generated method stub
		
	}

	private static void dfs(int i) {
		// TODO Auto-generated method stub
		
	}
}
