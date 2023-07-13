package expedition.day4.DAY4_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 배틀 로얄 게임
public class Main {

	static HashMap<String, Node> hmap;

	static class Node {
		String teamName;
		int score;
		int cnt;

		public Node(String teamName, int score, int cnt) {
			this.teamName = teamName;
			this.score = score;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 유저의 수

		hmap = new HashMap<>();
		StringTokenizer st;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String userId = st.nextToken().toString();
			int point = Integer.parseInt(st.nextToken());
			
			Node now = hmap.get(userId);
			
			if (now == null) {
				hmap.put(userId, new Node(userId, point, 1));
			} else {
				now.score += point;
				now.cnt += 1;
			}
			
		}
		
		st = new StringTokenizer(br.readLine());
		
		String cocoTeam = st.nextToken().toString();
		String friendTeam = st.nextToken().toString();
				
		// 코코가 속한 팀원 수
		Node coco = hmap.get(cocoTeam);
		System.out.println(coco.cnt + " " + coco.score);
		
		Node friend = hmap.get(friendTeam);
		System.out.println(friend.cnt + " " + friend.score);
		
		if (coco.score > friend.score) { 
			System.out.println(coco.teamName);
		} else {
			System.out.println(friend.teamName);
		}
		
	}
}
