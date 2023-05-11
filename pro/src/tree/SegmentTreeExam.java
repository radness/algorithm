package tree;

public class SegmentTreeExam {
	static int[] tree;
	static int[] a;
	static int[] D;
	
	public static void main(String[] args) {
		
		
	}
	
	void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = a[start];
		} else {
			init(node * 2, start, (start + end) / 2);
			init(node * 2 + 1, (start + end) / 2 + 1, end);
			tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
		}
	}
	
	int query(int node, int start, int end, int i , int j) {
		if (i > end || j < start)
			return -1;
		
		if (i <= start && end <= j)
			return D[node];
		
		int m1 = query(2 * node, start, (start + end) / 2, i , j);
		int m2 = query(2 * node + 1, (start+end) / 2 + 1, end, i, j);
		
		if (m1 == -1) {
			return m2;
		} else if (m2 == -1) {
			return m1;
		} else {
			return Math.min(m1,  m2);
		}
	}
}
