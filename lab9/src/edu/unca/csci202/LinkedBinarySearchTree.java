package edu.unca.csci202;

/**
 * Binary Search Tree data structure
 *	@author Kamren Sims / Kevin Sanft
 * @param <T>
 */
public class LinkedBinarySearchTree<T extends Comparable<T>> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {
	/**
	 * Creates an empty binary search tree.
	 */
	public LinkedBinarySearchTree() 
	{
		super();
	}

	/**
	 * Creates a binary search with the specified element as its root.
	 *
	 * @param element the element that will be the root of the new binary
	 *        search tree
	 */
	public LinkedBinarySearchTree(T element) 
	{
		super(element);

		if (!(element instanceof Comparable))
			throw new NonComparableElementException("LinkedBinarySearchTree");
	}

	/**
	 * Adds the specified object to the binary search tree in the
	 * appropriate position according to its natural order.  Note that
	 * equal elements are added to the right.
	 *
	 * @param element the element to be added to the binary search tree
	 */
	public void addElement(T element) 
	{
		if (!(element instanceof Comparable))
			throw new NonComparableElementException("LinkedBinarySearchTree");

		Comparable<T> comparableElement = (Comparable<T>)element;

		if (isEmpty())
			root = new BinaryTreeNode<T>(element);
		else 
		{
			if (comparableElement.compareTo(root.getData()) < 0)
			{
				if (root.getLeft() == null) 
					this.getRootNode().setLeft(new BinaryTreeNode<T>(element));
				else
					addElement(element, root.getLeft());
			}
			else
			{
				if (root.getRight() == null) 
					this.getRootNode().setRight(new BinaryTreeNode<T>(element));
				else
					addElement(element, root.getRight());
			}
		}
		modCount++;
	}

	/**
	 * Adds the specified object to the binary search tree in the
	 * appropriate position according to its natural order.  Note that
	 * equal elements are added to the right.
	 *
	 * @param element the element to be added to the binary search tree
	 */
	private void addElement(T element, BinaryTreeNode<T> node) 
	{
		Comparable<T> comparableElement = (Comparable<T>)element;

		if (comparableElement.compareTo(node.getData()) < 0)
		{
			if (node.getLeft() == null) 
				node.setLeft(new BinaryTreeNode<T>(element));
			else
				addElement(element, node.getLeft());
		}
		else
		{
			if (node.getRight() == null) 
				node.setRight(new BinaryTreeNode<T>(element));
			else
				addElement(element, node.getRight());
		}
	}

	/**
	 * Removes the first element that matches the specified target
	 * element from the binary search tree and returns a reference to
	 * it.  Throws a ElementNotFoundException if the specified target
	 * element is not found in the binary search tree.
	 *
	 * @param targetElement the element being sought in the binary search tree
	 * @throws ElementNotFoundException if the target element is not found
	 */
	public T removeElement(T targetElement)
			throws ElementNotFoundException 
	{
		T result = null;

		if (isEmpty())
			throw new ElementNotFoundException("LinkedBinarySearchTree");
		else
		{
			BinaryTreeNode<T> parent = null;
			if (((Comparable<T>)targetElement).equals(root.getData())) 
			{
				result =  root.getData();
				BinaryTreeNode<T> temp = replacement(root);
				if (temp == null)
					root = null;
				else 
				{
					root.data = temp.data;
					root.setRight(temp.right);
					root.setLeft(temp.left);
				}

				modCount++;
			}
			else 
			{                
				parent = root;
				if (((Comparable<T>)targetElement).compareTo(root.getData()) < 0)
					result = removeElement(targetElement, root.getLeft(), parent);
				else
					result = removeElement(targetElement, root.getRight(), parent);
			}
		}

		return result;
	}

	/**
	 * Removes the first element that matches the specified target
	 * element from the binary search tree and returns a reference to
	 * it.  Throws a ElementNotFoundException if the specified target
	 * element is not found in the binary search tree.
	 *
	 * @param targetElement the element being sought in the binary search tree
	 * @param node the node from which to search
	 * @param parent the parent of the node from which to search
	 * @throws ElementNotFoundException if the target element is not found
	 */
	private T removeElement(T targetElement, BinaryTreeNode<T> node, BinaryTreeNode<T> parent)
			throws ElementNotFoundException 
	{
		T result = null;

		if (node == null)
			throw new ElementNotFoundException("LinkedBinarySearchTree");
		else
		{
			if (((Comparable<T>)targetElement).equals(node.getData())) 
			{
				result =  node.getData();
				BinaryTreeNode<T> temp = replacement(node);
				if (parent.right == node)
					parent.right = temp;
				else 
					parent.left = temp;

				modCount++;
			}
			else 
			{                
				parent = node;
				if (((Comparable<T>)targetElement).compareTo(node.getData()) < 0)
					result = removeElement(targetElement, node.getLeft(), parent);
				else
					result = removeElement(targetElement, node.getRight(), parent);
			}
		}

		return result;
	}

	/**
	 * Returns a reference to a node that will replace the one
	 * specified for removal. In the case where the removed node has 
	 * two children, the inorder successor is used as its replacement.
	 *
	 * @param node the node to be removed
	 * @return a reference to the replacing node
	 */
	private BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) 
	{
		BinaryTreeNode<T> result = null;

		if ((node.left == null) && (node.right == null))
			result = null;

		else if ((node.left != null) && (node.right == null))
			result = node.left;

		else if ((node.left == null) && (node.right != null))
			result = node.right;

		else
		{
			BinaryTreeNode<T> current = node.right;
			BinaryTreeNode<T> parent = node;

			while (current.left != null)
			{
				parent = current;
				current = current.left;
			}

			current.left = node.left;
			if (node.right != current)
			{
				parent.left = current.right;
				current.right = node.right;
			}

			result = current;
		}

		return result;
	}

	/**
	 * Removes elements that match the specified target element from 
	 * the binary search tree. Throws a ElementNotFoundException if 
	 * the specified target element is not found in this tree.
	 *
	 * @param targetElement the element being sought in the binary search tree
	 * @throws ElementNotFoundException if the target element is not found
	 */
	public void removeAllOccurrences(T targetElement)
			throws ElementNotFoundException 
	{
		removeElement(targetElement);

		try
		{
			while (contains((T)targetElement))
				removeElement(targetElement);
		}

		catch (Exception ElementNotFoundException)
		{
		}
	}

	/**
	 * Removes the node with the least value from the binary search
	 * tree and returns a reference to its element. Throws an
	 * EmptyCollectionException if this tree is empty. 
	 *
	 * @return a reference to the node with the least value
	 * @throws EmptyCollectionException if the tree is empty
	 */
	public T removeMin() throws EmptyCollectionException 
	{
		T result = null;

		if (isEmpty())
			throw new EmptyCollectionException("LinkedBinarySearchTree");
		else 
		{
			if (root.left == null) 
			{
				result = root.getData();
				root = root.right;
			}
			else 
			{
				BinaryTreeNode<T> parent = root;
				BinaryTreeNode<T> current = root.left;
				while (current.left != null) 
				{
					parent = current;
					current = current.left;
				}
				result =  current.getData();
				parent.left = current.right;
			}

			modCount++;
		}

		return result;
	}

	/**
	 * Removes the node with the highest value from the binary
	 * search tree and returns a reference to its element. Throws an
	 * EmptyCollectionException if this tree is empty. 
	 *
	 * @return a reference to the node with the highest value
	 * @throws EmptyCollectionException if the tree is empty
	 */
	public T removeMax() throws EmptyCollectionException 
	{
		T result = null;

		if (isEmpty())
			throw new EmptyCollectionException("LinkedBinarySearchTree");
		else 
		{
			if (root.right == null) 
			{
				result = root.getData();
				root = root.left;
			}
			else 
			{
				BinaryTreeNode<T> parent = root;
				BinaryTreeNode<T> current = root.right;
				while (current.right != null) 
				{
					parent = current;
					current = current.right;
				}
				result =  current.getData();
				parent.right = current.left;
			}

			modCount++;
		}

		return result;
	}

	/**
	 * Returns the element with the least value in the binary search
	 * tree. It does not remove the node from the binary search tree. 
	 * Throws an EmptyCollectionException if this tree is empty.
	 *
	 * @return the element with the least value
	 * @throws EmptyCollectionException if the tree is empty
	 */
	public T findMin() throws EmptyCollectionException 
	{
		T result = null;

		if (isEmpty())
			throw new EmptyCollectionException("LinkedBinarySearchTree");
		else 
		{
			if (root.left == null) 
			{
				result = root.getData();
			}
			else 
			{
				BinaryTreeNode<T> current = root.left;
				while (current.left != null) 
				{
					current = current.left;
				}
				result =  current.getData();
			}
		}

		return result;		
	}

	/**
	 * Returns the element with the highest value in the binary
	 * search tree. It does not remove the node from the binary
	 * search tree. Throws an EmptyCollectionException if this 
	 * tree is empty.
	 *
	 * @return the element with the highest value
	 * @throws EmptyCollectionException if the tree is empty
	 */
	public T findMax() throws EmptyCollectionException 
	{
		T result = null;
		
		if (isEmpty())
			throw new EmptyCollectionException("LinkedBinarySearchTree");
		else 
		{
			if (root.right == null) 
			{
				result = root.getData();
			}
			else 
			{
				BinaryTreeNode<T> current = root.right;
				while (current.right != null) 
				{
					current = current.right;
				}
				result =  current.getData();
			}
		}

		return result;		

	}
	
	
	/**
	 * Checks to see if the BinaryTree contains an element
	 * @param the specific element
	 * @returns that element
	 */
	@Override
	public boolean contains(T targetElement) {
		System.out.println("calling LinkedBinarySearchTree.contains");
		
		T temp;
		boolean contains = false;
		try {
			temp = find(targetElement);
			contains = true;
		} catch (Exception NotFoundException) {
			contains = false;
		}
		
		return contains;
	}

	/**
	 * Returns that reference used in the findNode method
	 * throws an exception if that element is not found
	 * @param T targetElement
	 */
	
	@Override
	public T find(T targetElement) throws ElementNotFoundException {
		System.out.println("calling LinkedBinarySearchTree.find");
		BinaryTreeNode<T> find = findNode(targetElement, root);
		if (find == null)
			throw new ElementNotFoundException(null);

		return (find.getData());	
	}
	
	/**
	 * Reference to the targetElement if that targetElement is in the tree
	 * @param T targetElement
	 * @param BinaryTreeNode<T> next
	 * @return temp
	 */
	
	private BinaryTreeNode<T> findNode(T targetElement, BinaryTreeNode<T> next){
		{
		if (next==null)
			return null;
		}
		if(next.getData().equals(targetElement))
			return next;
		BinaryTreeNode<T> temp = findNode(targetElement, next.getLeft());
		if (temp == null)
			temp = findNode(targetElement, next.getRight());
		return temp;
	
}
}
