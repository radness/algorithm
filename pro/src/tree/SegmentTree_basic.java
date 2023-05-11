package tree;

/* ���׸�Ʈ Ʈ��
 * Ư�� ���� �� �����Ϳ� ���� ����(����)�� ������ ���� �� �ִ� Ʈ��.
 * ex) Ư�� ������ ��, �ּҰ�, �ִ밪, ��հ�
 * 
 * �ð����⵵
 * ������ ���� : O(logN)
 * ���� : O(logN)
 * ������ ������ ������ M�� ���� : O((logN + logN) * M) = O(MlogN)
 * 
 * ���� ��ǥ ���� : 2042�� - ���� �� ���ϱ�
 * 
 * ������ ���� : O(1)
 * ���� �� ���� : O(N)
 * M�� ������ ������ ������ ���� : O((1+N) * M) = O(N*M)
 * -> ���׸�Ʈ Ʈ���� ����Ѵٸ� => O(MlogN)
 * 
 * ���׸�Ʈ Ʈ�� ���� : ���� Ʈ��
 * 
 * 
 * */
public class SegmentTree_basic {
	public static void main(String[] args) {

	}

	static class SegmentTree {
		long[] tree; // �� ���Ұ� ��� Ʈ�� �迭
		int treeSize; // Ʈ���� ũ��

		public SegmentTree(int arrSize) {
			// Ʈ���� ���� ���ϱ�
			int height = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

			// ���̸� �̿��� �迭 ������ ���ϱ�
			this.treeSize = (int) Math.pow(2, height + 1);

			// �迭 ����
			tree = new long[treeSize];
		}

		// array : ���� �迭 node : ���� ��� start : ���� ���� �迭 ���� end : ���� ���� �迭 ��
		public long init(long[] array, int node, int start, int end) {
			if (start == end)
				return tree[node] = array[start];

			return tree[node] = init(array, node * 2, start, (start + end) / 2)
					+ init(array, node * +1, (start + end) / 2 + 1, end);
		}

		public void update(int node, int start, int end, int idx, long diff) {
			if (idx < start || end < idx)
				return;

			tree[node] += diff;

			if (start != end) {
				update(node * 2, start, (start + end) / 2, idx, diff);
				update(node * 2 + 1, (start + end) / 2, end, idx, diff);
			}
		}

		public long sum(int node, int start, int end, int left, int right) {
			if (left > end || right < start) {
				return 0;
			}

			if (left <= start && end <= right) {
				return tree[node];
			}

			return sum(node * 2, start, (start + end) / 2, left, right)
					+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		}
	}
}
