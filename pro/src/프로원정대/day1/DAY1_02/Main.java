package 프로원정대.day1.DAY1_02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

	static int[][] map;
	static TreeSet<Integer> tset;

	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());

		map = new int[height][width];

		for (int y = 0; y < height; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < width; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int bHeight = Integer.parseInt(st.nextToken());
		int bWidth = Integer.parseInt(st.nextToken());

		tset = new TreeSet<>();

		for (int y = 0; y < bHeight; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < bWidth; x++) {
				tset.add(Integer.parseInt(st.nextToken()));
			}
		}

		int totalSize = height * width;
		int blackList = 0;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (tset.contains(map[y][x])) {
					blackList++;
				}
			}
		}

		System.out.println(blackList);
		System.out.println(totalSize - blackList);

		long endTime = System.currentTimeMillis();
		
		System.out.println("걸린시간 : " + (endTime - startTime + "ms"));
	}
}