package tree;

/* 세그먼트 트리
 * 특정 구간 내 데이터에 대한 연산(쿼리)를 빠르게 구할 수 있는 트리.
 * ex) 특정 구간의 합, 최소값, 최대값, 평균값
 * 
 * 시간복잡도
 * 데이터 변경 : O(logN)
 * 연산 : O(logN)
 * 데이터 변경할 때마다 M번 연산 : O((logN + logN) * M) = O(MlogN)
 * 
 * 백준 대표 예제 : 2042번 - 구간 합 구하기
 * 
 * 데이터 변경 : O(1)
 * 구간 합 연산 : O(N)
 * M번 데이터 변경할 때마다 연산 : O((1+N) * M) = O(N*M)
 * -> 세그먼트 트리를 사용한다면 => O(MlogN)
 * 
 * 세그먼트 트리 구조 : 이진 트리
 * 
 * 
 * */
public class SegmentTree_basic {
	public static void main(String[] args) {

	}

	static class SegmentTree {
		long[] tree; // 각 원소가 담길 트리 배열
		int treeSize; // 트리의 크기

		public SegmentTree(int arrSize) {
			// 트리의 높이 구하기
			int height = (int) Math.ceil(Math.log(arrSize) / Math.log(2));

			// 높이를 이용한 배열 사이즈 구하기
			this.treeSize = (int) Math.pow(2, height + 1);

			// 배열 생성
			tree = new long[treeSize];
		}

		// array : 원소 배열 node : 현재 노드 start : 현재 구간 배열 시작 end : 현재 구간 배열 끝
		public long init(long[] array, int node, int start, int end) {
			if (start == end)
				return tree[node] = array[start];

			return tree[node] = init(array, node * 2, start, (start + end) / 2)
					+ init(array, node * +1, (start + end) / 2 + 1, end);
		}
	}
}
