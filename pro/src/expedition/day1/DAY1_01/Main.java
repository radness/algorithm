package expedition.day1.DAY1_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 입력과 출력
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int type = Integer.parseInt(br.readLine());

		switch (type) {
		case 1:
			int num = Integer.parseInt(br.readLine());

			int sum = 0;
			long multi = 1;
			ArrayList<Integer> iArr = new ArrayList<>();

			st = new StringTokenizer(br.readLine());

			for (int i = 0; i < num; i++) {
				iArr.add(Integer.parseInt(st.nextToken()));
			}

			for (int i = 0; i < iArr.size(); i++) {
				sum += iArr.get(i);
				multi *= iArr.get(i);
			}

			System.out.println(sum + " " + multi);

			break;
		case 2:
			int n = Integer.parseInt(br.readLine());

			ArrayList<String> sArr = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				sArr.add(br.readLine());
			}

			int minVal = Integer.MIN_VALUE;
			int maxVal = Integer.MAX_VALUE;

			String minStrVal = "";
			String maxStrVal = "";

			for (int i = 0; i < sArr.size(); i++) {
				if (minVal <= sArr.get(i).length()) {
					minVal = sArr.get(i).length();
					maxStrVal = sArr.get(i);
				}
				if (maxVal >= sArr.get(i).length()) {
					maxVal = sArr.get(i).length();
					minStrVal = sArr.get(i);
				}
			}

			System.out.println(maxStrVal);
			System.out.println(minStrVal);

			break;
		case 3:
			st = new StringTokenizer(br.readLine());

			int Y = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());

			int[][] map = new int[Y][X];

			for (int y = 0; y < Y; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < X; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			int min = Integer.MAX_VALUE;

			for (int y = 0; y < Y; y++) {
				for (int x = 0; x < X; x++) {
					if (min > map[y][x]) {
						min = map[y][x];
					}
				}
			}

			int cnt = 0;

			for (int y = 0; y < Y; y++) {
				for (int x = 0; x < X; x++) {
					if (min == map[y][x]) {
						cnt++;
					}
				}
			}

			System.out.println(min);
			System.out.println(cnt + "개");

			break;

		default:
			break;
		}
	}
}