package edu.unca.csci202;

import java.util.Iterator;

/**
 * class to demo used to print out the LinkedBinaryTree
 * 
 * @author Kamren Sims / Kevin Sanft 
 *
 */

public class Main {

	public static void main(String[] args) {
		LinkedBinarySearchTree<String> bst = new LinkedBinarySearchTree<String>();
		// insert in any order
		bst.addElement("A");
		bst.addElement("F");
		bst.addElement("E");
		bst.addElement("B");
		bst.addElement("G");
		String letterD = new String("D");
		bst.addElement(letterD);
		bst.addElement("C");

		bst.removeMax();//should remove "G"
		bst.removeMin();//should remove "A"
		
		// with above code as is,
		// inorder traversal should print in alphabetical order (B C D E F)
		try {
			System.out.print("InOrder: ");
			Iterator<String> itr = bst.iteratorInOrder();
			while(itr.hasNext()) {
				System.out.print(itr.next() + " ");
			}
			System.out.println();
		}catch(Exception e) {
			System.out.println("Need to implement LinkedBinaryTree.inorder iterator.");
		}

		
		if( ! bst.contains( new String("C") ) ) {
			System.out.println("Need to implement LinkedBinarySearchTree.contains.");
		} else {
			System.out.println("contains() seems to work correctly!");
		}
		
		if( bst.find( bst.getRootElement() ) != bst.getRootElement() ) {
			System.out.println("Need to implement LinkedBinarySearchTree.find.");
		} else {
			if (bst.find(new String("D")) != letterD) {
				System.out.println("find() did not return a reference to the object.");
			} else {
				System.out.println("find() seems to work correctly!");
			}
		}
	}

}
