import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* DP
 * ��ȭ�� : D[i][j] = D[i-1][j-1] + D[i-1][j]
 * */
public class BJ_16395_�Ľ�Į�ﰢ�� {
	static int N;
	static int K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		long[][] D = new long[N + 1][N + 1];

		for (int �� = 1; �� <= N; ��++) {
			for (int �� = 1; �� <= ��; ��++) {
				// �ʱ�ȭ
				if (�� == 1 || �� == ��) {
					D[��][��] = 1;
					continue;
				}
				// ��ȭ��
				D[��][��] = D[�� - 1][�� - 1] + D[�� - 1][��];
			}
		}

		// ���
		System.out.println(D[N][K]);
	}
}
