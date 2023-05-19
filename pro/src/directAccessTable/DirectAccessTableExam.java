package directAccessTable;

// Direct Access Table
// ���ڿ� �߿� ���� ���� ������ �� ���ϱ�
public class DirectAccessTableExam {
	public static void main(String[] args) {

		String str = "BBZZASEQDSFOWENKLSAASUDSASSSXXASXXXSVBBB";

		int[] dat = new int[91];

		// dat �迭 �����
		for (int i = 0; i < str.length(); i++) {
			int num = str.charAt(i);
			dat[num] += 1;
		}

		// max index ã��
		int max = 0;
		char maxCh = ' ';

		for (int i = 'A'; i <= 'Z'; i++) {
			if (dat[i] > max) {
				max = dat[i];
				maxCh = (char) i;
			}
		}

		System.out.println("���ڿ� �� ���� ���� ������ �� " + maxCh);
	}
}
