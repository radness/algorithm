package expedition.day3.DAY3_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 레벨 제한 아이템 배급
// O(NlogM)
public class Main {

	// key : level, val : X
	static TreeMap<Integer, Integer> tmap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 길드원 수
		int M = Integer.parseInt(st.nextToken()); // 아이템 수

		tmap = new TreeMap<>();
		int ans = 0;

		// M개 아이템
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			tmap.put(Integer.parseInt(st.nextToken()), 1);
		}

		// N명의 길드원
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int person = Integer.parseInt(st.nextToken());
			
			
			// person이 최대한 자기 레벨에 맞는 아이템 찾기
			// 최대한 자기 레벨에 맞다  = 레벨보다 같거나 작은 아이템중 가장 큰거
			Integer item = tmap.floorKey(person);

			if (item == null) {
				break;
			}
			
			ans++;
			tmap.remove(item);
			
		}
		System.out.println(ans);
	}
}
