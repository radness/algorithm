package basic.list;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {
	private static int N;
	private static List[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		list = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

//		Arrays.fill(list, Integer.MAX_VALUE);

		for (int i = 0; i < N; i++) {
			System.out.println(list[i]);
		}
	}
}
