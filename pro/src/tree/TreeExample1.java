package tree;

public class TreeExample1 {
	private Node newNode;

	public static void main(String[] args) throws Exception {
		TreeClass tc = new TreeClass();
		Node n50 = tc.makeNode(50, null, null);
		Node n60 = tc.makeNode(60, null, null);
		Node n20 = tc.makeNode(20, n50, n60);
		Node n30 = tc.makeNode(30, null, null);
		Node n10 = tc.makeNode(10, n20, n30);
		
		/*
         *      10
         *    /     \
         *   20      30
         *  /  \
         * 50   60
         */
	}
}

class TreeClass {
	private Node newNode;

	public Node makeNode(int data, Node leftNode, Node rightNode) {
		newNode = new Node();
		newNode.setData(data);
		newNode.setLeftNode(leftNode);
		newNode.setRightNode(rightNode);
		return newNode;
	}
}

class Node {
	private int data; // 노드의 값
	private Node leftNode; // 왼쪽 자식노드의 값
	private Node rightNode; // 오른쪽 자식노드의 값

	public Node() {

	}

	public Node(int data, Node leftNode, Node rightNode) {
		this.data = data;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
}
