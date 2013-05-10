package com.kprav.bst;

import java.util.LinkedList;
import java.util.Queue;

public class BST {

	private Node root;

	// Tree Node class
	public class Node {
		int data;
		int size;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.size = 1;
		}
	}

	/**
	 * Constructor
	 * - Creates a new BST with root set to null
	 */
	public BST() {
		root = null;
	}

	/**
	 * Build a sample BST of the following format:
	 * 				4
	 * 		2				5
	 * 	1		3
	 */
	public void buildSampleBST() {
		root = null;
		add(4);
		add(5);
		add(2);
		add(1);
		add(3);
	}

	/**
	 * Add a new node to the tree
	 * @param data
	 */
	public void add(int data) {
		root = insertNode(root, data);
	}

	/**
	 * Return the size of the tree i.e. no. of nodes
	 * @return size
	 */
	public int size() {
		return size(root);
	}

	/**
	 * Return the maximum depth of the tree
	 * @return maxDepth
	 */
	public int maxDepth() {
		return maxDepth(root);
	}

	/**
	 * Return the minimum value node in the tree
	 */
	public int minValue() {
		return minValue(root);
		// return minValueRecurse(root);
	}

	/**
	 * Return the maximum value node in the tree 
	 */
	public int maxValue() {
		return maxValue(root);
		// return maxValueRecurse(root);
	}

	/**
	 * Print the tree contents in pre-order
	 */
	public void printPreOrder() {
		printPreOrder(root);
	}

	/**
	 * Print the tree contents in in-order
	 */
	public void printInOrder() {
		printInOrder(root);
	}

	/**
	 * Print the tree contents in post-order
	 */
	public void printPostOrder() {
		printPostOrder(root);
	}

	/**
	 * Print the tree contents in level-order
	 */
	public void printLevelOrder() {
		printLevelOrder(root);
	}

	/**
	 * Check if the tree has a path from root
	 * to leaf that has the sum passed by client.
	 * @param sum
	 * @return true if sum is found
	 */
	public boolean hasPathSum(int sum) {
		return hasPathSum(root, sum);
	}

	/**
	 * Print all paths from root to leaf nodes
	 */
	public void printPaths() {
		Queue<Node> path = new LinkedList<Node>();
		printPaths(root, path);
	}

	/**
	 * Make a mirror image of the tree
	 * NOTE: This will render the tree non-BST
	 */
	public void mirrorTree() {
		mirror(root);
	}

	/**
	 * Duplicate each node in tree.
	 * Insert duplicate as left child of original.
	 * Make the original left child - the left child of duplicate.
	 */
	public void doubleTree() {
		doubleTree(root);
	}

	/**
	 * Check if two trees are the same - in structure and values.
	 * @param A BST to compare
	 * @return true if the two trees are same
	 */
	public boolean sameTree(BST bst) {
		return sameTree(this.root, bst.root);
	}

	/**
	 * Check if tree is a BST
	 * @return true if the tree is a BST
	 */
	public boolean isBST() {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Check if tree from an arbitrary node is a BST
	 * @param node
	 * @return true if the tree is a BST
	 */
	public boolean isBst(Node node) {
		return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private Node newNode(int data) {
		Node node = new Node(data);
		return node;
	}

	private Node insertNode(Node node, int data) {
		if (node == null)
			return newNode(data);
		else {
			if (data <= node.data)
				node.left = insertNode(node.left, data);
			else
				node.right = insertNode(node.right, data);
			node.size = size(node.left) + 1 + size(node.right);
			return node;
		}
	}

	private int size(Node node) {
		if (node == null)
			return 0;
		else
			return node.size;
	}

	private int maxDepth(Node node) {
		if (node == null)
			return 0;
		else {
			return (Math.max(maxDepth(node.left) + 1, maxDepth(node.right) + 1));
		}
	}

	private int minValue(Node node) {
		while (node.left != null)
			node = node.left;
		return node.data;
	}

	private int maxValue(Node node) {
		while (node.right != null)
			node = node.right;
		return node.data;
	}

	private int minValueRecurse(Node node) {
		if (node.left == null)
			return node.data;
		return minValueRecurse(node.left);
	}

	private int maxValueRecurse(Node node) {
		if (node.right == null)
			return node.data;
		return maxValueRecurse(node.right);
	}

	private void printInOrder(Node node) {
		if (node == null)
			return;
		printInOrder(node.left);
		System.out.print(" " + node.data);
		printInOrder(node.right);
	}

	private void printPreOrder(Node node) {
		if (node == null)
			return;
		System.out.print(" " + node.data);
		printPreOrder(node.left);
		printPreOrder(node.right);
	}

	private void printPostOrder(Node node) {
		if (node == null)
			return;
		printPostOrder(node.left);
		printPostOrder(node.right);
		System.out.print(" " + node.data);
	}

	private void printLevelOrder(Node node) {
		if (node == null)
			return;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Node treeNode = queue.remove();
			System.out.print(" " + treeNode.data);
			if (treeNode.left != null)
				queue.add(treeNode.left);
			if (treeNode.right != null)
				queue.add(treeNode.right);
		}
	}

	private boolean hasPathSum(Node node, int sum) {
		if (node == null)
			return sum == 0;
		else {
			int subSum = sum - node.data;
			return (hasPathSum(node.left, subSum) || hasPathSum(node.right,
					subSum));
		}
	}

	private void printPaths(Node node, Queue<Node> path) {
		if (node == null)
			return;
		path.add(node);
		if (node.left == null && node.right == null) {
			for (Node treeNode : path) {
				System.out.print(" " + treeNode.data);
			}
			System.out.println("");
		}
		printPaths(node.left, path);
		printPaths(node.right, path);
		path.remove(node);
	}

	private void mirror(Node node) {
		if (node == null)
			return;
		Node temp = node.left;
		node.left = node.right;
		node.right = temp;
		mirror(node.left);
		mirror(node.right);
	}

	private void doubleTree(Node node) {
		if (node == null)
			return;
		Node temp = node.left;
		Node newNode = newNode(node.data);
		node.left = newNode;
		newNode.left = temp;
		doubleTree(node.left.left); // or doubleTree(newNode.left)
		doubleTree(node.right);
	}

	public boolean sameTree(Node a, Node b) {
		if (a == null && b == null)
			return true;
		else if (a != null && b != null) {
			return (a.data == b.data && sameTree(a.left, b.left) && sameTree(
					a.right, b.right));
		} else
			return false;
	}

	private boolean isBST(Node node, int min, int max) {
		if (node == null)
			return true;
		else if (!(node.data > min && node.data <= max))
			return false;
		else
			return isBST(node.left, min, node.data)
					&& isBST(node.right, node.data, max);
	}

}
