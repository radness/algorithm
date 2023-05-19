package directAccessTable;

// Direct Access Table
// 문자열 중에 가장 많이 나오는 값 구하기
public class DirectAccessTableExam {
	public static void main(String[] args) {

		String str = "BBZZASEQDSFOWENKLSAASUDSASSSXXASXXXSVBBB";

		int[] dat = new int[91];

		// dat 배열 만들기
		for (int i = 0; i < str.length(); i++) {
			int num = str.charAt(i);
			dat[num] += 1;
		}

		// max index 찾기
		int max = 0;
		char maxCh = ' ';

		for (int i = 'A'; i <= 'Z'; i++) {
			if (dat[i] > max) {
				max = dat[i];
				maxCh = (char) i;
			}
		}

		System.out.println("문자열 중 가장 많이 나오는 값 " + maxCh);
	}
}
