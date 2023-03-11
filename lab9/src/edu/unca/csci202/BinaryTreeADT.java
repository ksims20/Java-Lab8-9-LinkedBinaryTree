package edu.unca.csci202;

import java.util.Iterator;

/**
 * Interface to a binary tree data structure
 *
 * @param <T>
 */
public interface BinaryTreeADT<T> {

	public T getRootElement();
	
	public boolean isEmpty();
	
	public int size();
	
	public boolean contains(T targetElement);
	
	public T find(T targetElement);
	
	public String toString();
	
	public Iterator<T> iterator();
	
	public Iterator<T> iteratorInOrder();
	
	public Iterator<T> iteratorPreOrder();
	
	public Iterator<T> iteratorPostOrder();
	
	public Iterator<T> iteratorLevelOrder();
	
}
