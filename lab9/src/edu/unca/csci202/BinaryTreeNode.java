package edu.unca.csci202;

/**
 * Class to represent a node in a binary tree.
 * 
 * @author Kevin Sanft
 *
 * @param <T>
 */
public class BinaryTreeNode<T> {
	// instance variables
	protected T data;
	protected BinaryTreeNode<T> left;
	protected BinaryTreeNode<T> right;

	// Constructors
	public BinaryTreeNode(T data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}

	public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	// Getters
	public T getData() {
		return this.data;
	}
	public BinaryTreeNode<T> getLeft(){
		return this.left;
	}
	public BinaryTreeNode<T> getRight(){
		return this.right;
	}
	// Setters
	public void setData(T data) {
		this.data = data;
	}
	public void setLeft(BinaryTreeNode<T> left) {
		this.left = left;
	}
	public void setRight(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	// "count" method
	public int numChildren() {
		int total = 0;
		// recurse left
		if(this.left != null) {
			total = 1 + left.numChildren();
		}
		// recurse right
		if(this.right != null) {
			total = total + 1 + right.numChildren();
		}	
		return total;
	}
}
