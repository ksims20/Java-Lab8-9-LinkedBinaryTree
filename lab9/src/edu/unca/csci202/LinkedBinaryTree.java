package edu.unca.csci202;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Binary Tree data structure
 *	@author Kamren Sims / Kevin Sanft
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T>, Iterable<T> {	
	// instance variables
	protected BinaryTreeNode<T> root;
	protected int modCount; //
	
	// Constructors
	public LinkedBinaryTree() {
		this.root = null;
	}
	public LinkedBinaryTree(T data) {
		this.root =  new BinaryTreeNode<T>(data);
	}
	public LinkedBinaryTree(T data, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right) {
		this.root = new BinaryTreeNode<T>(data);
		if (left!=null) {
			this.root.setLeft( left.getRootNode()  );
		}
		if (right!=null) {
			this.root.setRight( right.getRootNode() );
		}
	}
	
	
	/**
	 * To string method to Print the tree
	 */
	public String toString() {
		return print(root, 0);
	}
	
	/** 
	 * Print a subtree
	 */
	public String print(BinaryTreeNode<T> node, int level) {
		String ret = "";
		if(node != null) {
			for(int i=0;i<level;i++) {
				ret += "\t";
			}
			ret += node.getData();
			ret += "\n";
			// recurse left
			ret += print(node.getLeft(), level+1);
			// recurse right
			ret += print(node.getRight(), level+1);
		}
		return ret;
	}
	
	
	/** return the root node of this subtree
	 * 
	 * @return root node of sub tree
	 */
	public BinaryTreeNode<T> getRootNode(){
		return this.root;
	}
	
	
	/** return the root element of this subtree
	 * 
	 * @return root element of sub tree
	 */

	@Override
	public T getRootElement() {
		if(root == null) {
			return null;
		}
		return this.root.getData();
	}
	
	/** Checks if it's empty or not, returns true if it's empty
	 * 
	 * @return false if it's empty
	 */

	@Override
	public boolean isEmpty() {
		if(root == null) {
			return true;
		}
		return false;
	}
	/** Checks the size of the tree, and returns the value.
	 * 
	 * @return the number of elements in the tree
	 */

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}
		return root.numChildren() + 1;
	}

	
	/**
	 * Checks to see if the BinaryTree contains an element
	 * @param the specific element
	 * @returns that element
	 */
	
	@Override
	public boolean contains(T targetElement) {
		// solved in Lab8, override in LinkedBinarySearchTree
		return false;
	}

	/**
	 * Returns that reference used in the findNode method
	 * throws an exception if that element is not found
	 * @param T targetElement
	 */
	@Override
	public T find(T targetElement) {
		// solved in Lab8, override in LinkedBinarySearchTree
		return null;
	}

	
	/**
	 * CODE COPIED FROM LAB 8
	 * inOrder traversal using the recursive method below using Linkedlist
	 */
	@Override
	public Iterator<T> iteratorInOrder() {
		LinkedList<T> inorder = new LinkedList<T>();
		inOrder(root, inorder);
		return inorder.iterator();	
	}
	
	/**
	 * CODE COPIED FROM LAB 8
	 * Recursive inorder traversal
	 * @param BinaryTreeNode node
	 * @param LinkedList inorder
	 */
	
	protected void inOrder(BinaryTreeNode<T> node, LinkedList<T> inorder) {
		if (node != null) {
			inOrder(node.getLeft(), inorder);
			inorder.add(node.getData());
			inOrder(node.getRight(),inorder);
		}
	}
	
	// You do not need to implement these (implemented in Lab8)
	@Override
	public Iterator<T> iterator() {
		// implemented in Lab8
		return null;
	}
	@Override
	public Iterator<T> iteratorPreOrder() {
		// implemented in Lab8
		return null;
	}
	@Override
	public Iterator<T> iteratorPostOrder() {
		// implemented in Lab8
		return null;
	}
	@Override
	public Iterator<T> iteratorLevelOrder() {
		// implemented in Lab8
		return null;
	}
	
}
