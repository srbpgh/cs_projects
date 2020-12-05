package avlg;

import avlg.exceptions.UnimplementedMethodException;
import avlg.exceptions.EmptyTreeException;
import avlg.exceptions.InvalidBalanceException;

/** <p>{@link AVLGTree}  is a class representing an <a href="https://en.wikipedia.org/wiki/AVL_tree">AVL Tree</a> with
 * a relaxed balance condition. Its constructor receives a strictly  positive parameter which controls the <b>maximum</b>
 * imbalance allowed on any subtree of the tree which it creates. So, for example:</p>
 *  <ul>
 *      <li>An AVL-1 tree is a classic AVL tree, which only allows for perfectly balanced binary
 *      subtrees (imbalance of 0 everywhere), or subtrees with a maximum imbalance of 1 (somewhere). </li>
 *      <li>An AVL-2 tree relaxes the criteria of AVL-1 trees, by also allowing for subtrees
 *      that have an imbalance of 2.</li>
 *      <li>AVL-3 trees allow an imbalance of 3.</li>
 *      <li>...</li>
 *  </ul>
 *
 *  <p>The idea behind AVL-G trees is that rotations cost time, so maybe we would be willing to
 *  accept bad search performance now and then if it would mean less rotations. On the other hand, increasing
 *  the balance parameter also means that we will be making <b>insertions</b> faster.</p>
 *
 * @author Shaun Birch
 *
 * @see EmptyTreeException
 * @see InvalidBalanceException
 * @see StudentTests
 */
public class AVLGTree<T extends Comparable<T>> {

    /* ********************************************************* *
     * Write any private data elements or private methods here...*
     * ********************************************************* */
	int g;
	Node root;
    
	
	private class Node {
		Node l;
		Node r;
		T value;
	}
	
	private int getHeightAtNode(Node cur) {
		if(cur == null)
			return -1;
		return Math.max(getHeightAtNode(cur.l), getHeightAtNode(cur.r)) + 1;
	}
    
	private int getCountHelper(Node cur) {
    	if (cur == null)
    		return 0;
    	return getCountHelper(cur.l) + getCountHelper(cur.r) + 1;
    }
	private int getBalance(Node cur) {
		return getHeightAtNode(cur.l) - getHeightAtNode(cur.r);
	}
	private void balance(Node cur) {
		int b = getBalance(cur);
		if (b > g || b < -g) {
			if( b > g) {
				if (getBalance(cur.l) < 0) {
					//left right rotation 
					leftRotation(cur.l);
					rightRotation(cur);
				} else {
					rightRotation(cur);
					//right rotation
				}
			}
			else {
				if (getBalance(cur.r) > 0) {
					rightRotation(cur.r);
					leftRotation(cur);
					//right left rotation i think
				} else {
					leftRotation(cur);
					//left rotation these could be wrong
				}
			}
		}
	}
	private void rightRotation(Node cur) {
		Node temp = new Node();
		temp.r = cur.r;
		temp.l = cur.l;
		temp.value = cur.value;
		cur.value = cur.l.value;
		cur.l = cur.l.l;
		cur.r = temp;
		temp.l = temp.l.r;
	}
	
	private void leftRotation(Node cur) {
		Node temp = new Node();
		temp.r = cur.r;
		temp.l = cur.l;
		temp.value = cur.value;
		cur.value = cur.r.value;
		cur.r = cur.r.r;
		cur.l = temp;
		temp.r = temp.r.l;
		
	}
    private boolean avlgbTester(Node cur) {
    	boolean correct = true;
    	if (getBalance(cur) > g)
    		return false;
    	if(cur.l != null) {
    		correct = avlgbTester(cur.l);
    		if (!correct)
    			return false;
    	}
    	if(cur.r != null)
    		correct = avlgbTester(cur.r);
    	return correct;    	
    }
    
    private void printTreeHelper(Node cur) {
    	if (cur != null) {
    		System.out.println(cur.value.toString() + " ");
    		printTreeHelper(cur.l);
    		printTreeHelper(cur.r);
    	}
    }
    
    private boolean bstHelper(Node cur) {
    	boolean isCorrect = true;
    	if(cur.l != null) {
    		if (cur.value.compareTo(cur.l.value) < 0)
    			isCorrect = false;
    	}
    	if (cur.r != null) {
    		if (cur.value.compareTo(cur.r.value) > 0)
    			isCorrect = false;
    	}
    	return isCorrect;
    }
    
    private Node searchHelper(T key, Node cur) {
    	int compareValue = cur.value.compareTo(key);
    	if( compareValue == 0)
    		return cur;
    	if(compareValue > 0) {
    		if(cur.l == null)
    			return null;
    		return searchHelper(key, cur.l);
    	}
    	if(cur.r == null)
    		return null;
    	return searchHelper(key, cur.r);
    }
    private void insertHelper(T key, Node cur) {
    	if (key.compareTo(cur.value) < 0) {
    		if (cur.l != null) {
    			insertHelper(key, cur.l);
    			balance(cur);
    		}
    		else {
    			Node temp = new Node();
    			temp.value = key;
    			temp.l = null;
    			temp.r = null;
    			cur.l = temp;
    		}
    	} else {
    		if (cur.r != null) {
    			insertHelper(key, cur.r);
    			balance(cur);
    		} else {
    			Node temp = new Node();
    			temp.value = key;
    			temp.l = null;
    			temp.r = null;
    			cur.r = temp;
    		}
    	}
    }
    private T deleteHelper(T key, Node cur) {
    	int compareValue = cur.value.compareTo(key);
    	if(compareValue == 0) {
    		if (cur.r != null) {
    			
    			T inOrderValue = getLeftmost(cur.r); //gets inOrderSuccessor
        		deleteHelper(inOrderValue, cur);
        		cur.value = inOrderValue;
    		} else if(cur.l != null) {
    			cur.value = cur.l.value;
    			cur.r = cur.l.r;
    			cur.l = cur.l.l;
    		} else {
    			cur.value = null;
    		}
        	balance(cur);
        	return key;
    	}
    	else if (compareValue > 0) {
    		T result = null;
    		if(cur.l == null)
    			return null;
    		result = deleteHelper(key, cur.l);
    		if(cur.l.value == null)
    			cur.l = null;
    		balance(cur);
    		return result;
    	} else {
    		T result = null;
    		if (cur.r == null)
    			return null;
    		result = deleteHelper(key, cur.r);
    		if(cur.r.value == null)
    			cur.r = null;
    		balance(cur);
    		return result;
    	}
    }
    private T getLeftmost(Node cur) {
    	if (cur.l != null) {
    		return getLeftmost(cur.l);
    	}
    	return cur.value;
    }
	/* ******************************************************** *
     * ************************ PUBLIC METHODS **************** *
     * ******************************************************** */

    /**
     * The class constructor provides the tree with the maximum imbalance allowed.
     * @param maxImbalance The maximum imbalance allowed by the AVL-G Tree.
     * @throws InvalidBalanceException if maxImbalance is a value smaller than 1.
     */
    public AVLGTree(int maxImbalance) throws InvalidBalanceException {
    	if(maxImbalance < 1)
    		throw new InvalidBalanceException("invalid max imbalance");
    	g = maxImbalance;
    	root = new Node();
    	root.l = null;
    	root.r = null;
    	root.value = null;
    }

    /**
     * Insert key in the tree. You will <b>not</b> be tested on
     * duplicates! This means that in a deletion test, any key that has been
     * inserted and subsequently deleted should <b>not</b> be found in the tree!
     * s
     * @param key The key to insert in the tree.
     */
    public void insert(T key) {
    	if (root.value != null) {
    		insertHelper(key, root);
    	} else {
    		root.value = key;
    	}
    }


    /**
     * Delete the key from the data structure and return it to the caller.
     * @param key The key to delete from the structure.
     * @return The key that was removed, or {@code null} if the key was not found.
     * @throws EmptyTreeException if the tree is empty.
     */
    public T delete(T key) throws EmptyTreeException {
    	if(isEmpty())
    		throw new EmptyTreeException("the tree is empty");
    	return deleteHelper(key, root);
    }
    

    /**
     * <p>Search for key in the tree. Return a reference to it if it's in there,
     * or {@code null} otherwise.</p>
     * @param key The key to search for.
     * @return key if key is in the tree, or {@code null} otherwise.
     * @throws EmptyTreeException if the tree is empty.
     */
    public T search(T key) throws EmptyTreeException {
    	if(isEmpty())
    		throw new EmptyTreeException("the tree is empty");
    	Node n = searchHelper(key, root);
    	if (n == null)
    		return null;
    	return n.value;
    }

    /**
     * Retrieves the maximum imbalance parameter.
     * @return The maximum imbalance parameter provided as a constructor parameter.
     */
    public int getMaxImbalance(){
    	return g;
    }


    /**
     * <p>Return the height of the tree. The height of the tree is defined as the length of the
     * longest path between the root and the leaf level. By definition of path length, a
     * stub tree has a height of 0, and we define an empty tree to have a height of -1.</p>
     * @return The height of the tree. If the tree is empty, returns -1.
     */
    public int getHeight() {
    	if(isEmpty())
    		return -1;
    	return getHeightAtNode(root);
    }

    /**
     * Query the tree for emptiness. A tree is empty iff it has zero keys stored.
     * @return {@code true} if the tree is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
    	if (root.value == null)
    		return true;
    	return false;
    }

    /**
     * Return the key at the tree's root node.
     * @return The key at the tree's root node.
     * @throws  EmptyTreeException if the tree is empty.
     */
    public T getRoot() throws EmptyTreeException{
    	if (root.value == null)
    		throw new EmptyTreeException("the tree is empty");
    	return root.value;
    }


    /**
     * <p>Establishes whether the AVL-G tree <em>globally</em> satisfies the BST condition. This method is
     * <b>terrifically useful for testing!</b></p>
     * @return {@code true} if the tree satisfies the Binary Search Tree property,
     * {@code false} otherwise.
     */
    public boolean isBST() {
    	return bstHelper(root);
    }



    /**
     * <p>Establishes whether the AVL-G tree <em>globally</em> satisfies the AVL-G condition. This method is
     * <b>terrifically useful for testing!</b></p>
     * @return {@code true} if the tree satisfies the balance requirements of an AVLG tree, {@code false}
     * otherwise.
     */
    public boolean isAVLGBalanced() {
    	return avlgbTester(root);
    }


    /**
     * <p>Empties the AVL-G Tree of all its elements. After a call to this method, the
     * tree should have <b>0</b> elements.</p>
     */
    public void clear(){
    	root = new Node();
    	root.value = null;
    	root.l = null;
    	root.r = null;
    	
    }


    /**
     * <p>Return the number of elements in the tree.</p>
     * @return  The number of elements in the tree.
     */
    public int getCount(){
    	if (isEmpty())
    		return 0;
    	return getCountHelper(root);
    	
    }
    
    public void printTree() {
    	if(!isEmpty()) {
    		System.out.print("tree pre-order is ");
    		printTreeHelper(root);
    	}
    	else {
    		System.out.println("the tree is empty");
    	}
    }
    

    public void printNode(T key) {
    	Node n = searchHelper(key, root);
    	if (n == null) {
    		System.out.println("node doesn't exist");
    	} else {
	    	System.out.print("Node is " + n.value.toString());
	    	if(n.l != null) {
	    		System.out.print(" left child is " + n.l.value.toString());
	    	} else {
	    		System.out.print(" left child is null");
	    	}
	    	if(n.r != null) {
	    		System.out.println(" right child is " + n.r.value.toString());
	    	} else {
	    		System.out.println(" right child is null");
	    	}
    	}
    }
}
