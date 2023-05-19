package tree;

/* Index Tree
 * 구간합, 구간 Min, Max, 포인트 업데이트
 * 
 * 구간합을 구하는 트리의 종료
 * 1. 세그먼트 트리(Top-Down)
 * 2. 인덱스 트리(Bottom-Up)
 * 3. 펜윅 트리(인덱스 트리 응용)
 * 
 * Leaf 노드에 입력받은 값을 넣은 후 
 * 부모 노드들에는 값들의 정보(보통 노드의 부분 합)을 지정해둠
 * 
 * 사용처
 * 1. 부분합을 계속해서 구해야 할 때
 * 2. 특정 인덱스의 변경
 * 
 * 시간복잡도 = O(MlogN) 
 * 
 * 
 * */

// Q1. N=6, 배열 9,2,4,6,8,5
// 1~4 구간의 최댓값을 구하여라
public class IndexTreeExam {
	static int[] nodes;	// 노드들의 값(부분함으 포함한다)
	static int[] nums; // 실제 값
	static int height;	// 높이
	static int leftCnt;	// Full Binary Tree의 리프노드 갯수
	
	public static void main(String[] args) throws Exception {
		
		
	}
}
