package expedition.day2.DAY2_03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inStr = br.readLine().toString();

		ArrayList<Character> list = new ArrayList<>();
		int cursor = 0;

		for (int i = 0; i < inStr.length(); i++) {
			char now = inStr.charAt(i);

			if (now == '<') {
				cursor--;

				if (cursor < 0)
					cursor = 0;

			} else if (now == '>') {
				cursor++;

				if (cursor > list.size())
					cursor = list.size();

			} else if (now == 'd') {
				list.remove(cursor);
			} else if (now == 'b') {
				cursor--;

				if (cursor >= 0)
					list.remove(cursor);
				else
					cursor = 0;

			} else if (now == 'e') {
				cursor = list.size();
			} else if (now == 'h') {
				cursor = 0;
			} else {

				// 삽입
				list.add(cursor, now);
				// 커서 이동
				cursor++;
			}

		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}

		System.out.println(sb);

	}
}
