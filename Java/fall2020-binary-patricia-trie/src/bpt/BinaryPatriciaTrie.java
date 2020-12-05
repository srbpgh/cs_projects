package bpt;

import bpt.UnimplementedMethodException;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>{@code BinaryPatriciaTrie} is a Patricia Trie over the binary alphabet &#123;	 0, 1 &#125;. By restricting themselves
 * to this small but terrifically useful alphabet, Binary Patricia Tries combine all the positive
 * aspects of Patricia Tries while shedding the storage cost typically associated with tries that
 * deal with huge alphabets.</p>
 *
 * @author Shaun Birch!
 */
public class BinaryPatriciaTrie {

    /* We are giving you this class as an example of what your inner node might look like.
     * If you would prefer to use a size-2 array or hold other things in your nodes, please feel free
     * to do so. We can *guarantee* that a *correct* implementation exists with *exactly* this data
     * stored in the nodes.
     */
    private static class TrieNode {
        private TrieNode left, right;
        private String str;
        private boolean isKey;

        // Default constructor for your inner nodes.
        TrieNode() {
            this("", false);
        }

        // Non-default constructor.
        TrieNode(String str, boolean isKey) {
            left = right = null;
            this.str = str;
            this.isKey = isKey;
        }
    }

    private TrieNode root;
    private int count;

    /**
     * Simple constructor that will initialize the internals of {@code this}.
     */
    public BinaryPatriciaTrie() {
    	root = new TrieNode();
    	count = 0;
    }

    /**
     * Searches the trie for a given key.
     *
     * @param key The input {@link String} key.
     * @return {@code true} if and only if key is in the trie, {@code false} otherwise.
     */
    public boolean search(String key) {
    	if(key == null)
    		return false;
    	return searchHelper(key, root);
    }
    private boolean searchHelper(String key, TrieNode cur) {
    	if(key.equals(cur.str)) {
    		return cur.isKey;
    	}
    	if(key.substring(0, cur.str.length()).equals(cur.str) == false)
    		return false;
    	key = key.substring(cur.str.length(), key.length());
    	if(key.charAt(0) == '0') {
    		if(cur.left == null)
    			return false;
    		return searchHelper(key, cur.left);
    	}
    	if(cur.right == null)
    		return false;
    	return searchHelper(key, cur.right);
    }

    /**
     * Inserts key into the trie.
     *
     * @param key The input {@link String}  key.
     * @return {@code true} if and only if the key was not already in the trie, {@code false} otherwise.
     */
    public boolean insert(String key) {
    	if(key == null)
    		return false;
    	if(key == "") {
    		root.isKey = true;
    		count++;
    		return true;
    	}
    	if(key.charAt(0) == '0') {
    		if(root.left == null) {
    			TrieNode temp = new TrieNode(key, true);
    			root.left = temp;
    			count++;
    			return true;
    		}
    		return insertHelper(key, root.left);
    	}
    	if(root.right == null) {
			TrieNode temp = new TrieNode(key, true);
			root.right = temp;
			count++;
			return true;
		}
    	return insertHelper(key, root.right);
    }
    
    private boolean insertHelper(String key, TrieNode cur) {
    	if(key.equals(cur.str)) {
    		if(cur.isKey)
    			return false;
    		cur.isKey = true;
    		count++;
    		return true;
    	}
    	char keyChar = key.charAt(0);
    	char curChar = cur.str.charAt(0);
    	int lKey = key.length();
    	int lcur = cur.str.length();
    	int i = 0;
    	while(i < lKey - 1 && i < lcur - 1 && keyChar == curChar) {
    		i++;
    		keyChar = key.charAt(i);
    		curChar = cur.str.charAt(i);
    	}
    	if(keyChar != curChar) {
    		key = key.substring(i, key.length());
    		count++;
        	String prefix = cur.str.substring(0,i);;
    		TrieNode prevNode = new TrieNode(cur.str.substring(i, cur.str.length()), cur.isKey);
        	prevNode.left = cur.left;
        	prevNode.right = cur.right;
        	TrieNode newNode = new TrieNode(key, true);
        	//if we need to split the node
        	if(keyChar == '0') {
        		cur.left = newNode;
        		cur.right = prevNode;
        		cur.isKey = false;
        		cur.str = prefix;
        		return true;
        	}
        	cur.left = prevNode;
        	cur.right = newNode;
        	cur.isKey = false;
        	cur.str = prefix;
        	return true;
    	}
    	key = key.substring(i + 1,key.length());
    	if(i == lcur - 1) { 
	    	if(key.charAt(0) == '0') {
	    		if(cur.left == null) {
	    			TrieNode temp = new TrieNode(key, true);
	    			cur.left = temp;
	    			count++;
	    			return true;
	    		}
	    		return insertHelper(key, cur.left);
	    	} 
	    	if(cur.right == null) {
	    		TrieNode temp = new TrieNode(key, true);
	    		cur.right = temp;
	    		count++;
	    		return true;
	    	}
	    	return insertHelper(key, cur.right);
    	}

    	count++;
    	String prefix = cur.str.substring(0,i + 1);;
    	TrieNode newNode;

		newNode = new TrieNode(cur.str.substring(i + 1, cur.str.length()), cur.isKey);
		newNode.left = cur.left;
		newNode.right = cur.right;
		cur.str = prefix;
		cur.isKey = true;
		curChar = newNode.str.charAt(0);
		if(curChar == '0') {
			cur.left = newNode;
			cur.right = null;
		} else {
			cur.left = null;
			cur.right = newNode;
		}
		return true;
	}
    	
    	
    	


    /**
     * Deletes key from the trie.
     *
     * @param key The {@link String}  key to be deleted.
     * @return {@code true} if and only if key was contained by the trie before we attempted deletion, {@code false} otherwise.
     */
    public boolean delete(String key) {
    	if(key == null)
    		return false;
    	if(key.equals("")) {
    		if(root.isKey == true) {
    			root.isKey = false;
    			count--;
    			return true;
    		}
    		return false;
    	}
    	return deleteHelper(key, root);
    }
    
    private boolean deleteHelper(String key, TrieNode cur) {
    	char keyChar = key.charAt(0);
    	TrieNode checkedChild;
    	boolean left = false;
    	if(keyChar == '0') {
    		checkedChild = cur.left;
    		left = true;
    	} else {
    		checkedChild = cur.right;
    	}
		if(checkedChild == null) {
			return false;
		}
		char curChar = checkedChild.str.charAt(0);
		int i = 0;
		int lcur = checkedChild.str.length();
		int lkey = key.length();
		while(i < lcur - 1) {
			i++;
			if(i > lkey - 1)
				return false;
			curChar = checkedChild.str.charAt(i);
			keyChar = key.charAt(i);
			if(keyChar != curChar)
				return false;
		}
		key = key.substring(i + 1, key.length());
		if(key.equals("")) {
			if(checkedChild.isKey == false) {
				return false;
			}
			count--;
			if(checkedChild.left != null) {
				if(checkedChild.right != null) {
					checkedChild.isKey = false;
					return true;
				}
//				if(left) {
					checkedChild.left.str = cur.left.str.concat(checkedChild.left.str);
					if(left)
						cur.left = checkedChild.left;
					else
						cur.right = checkedChild.left;
//				}
//				else {
//					checkedChild.right.str = cur.right.str.concat(checkedChild.right.str);
//					cur.right = checkedChild.left;
//				}
				return true;
			}
			else if (checkedChild.right != null) {
				if(checkedChild.left != null) {
					checkedChild.isKey = false;
					return true;
				}
//				if(left) {
//					checkedChild.left.str = cur.left.str.concat(checkedChild.left.str);
//					cur.left = checkedChild.left;
//				}
//				else {
					checkedChild.right.str = cur.right.str.concat(checkedChild.right.str);
					if(left)
						cur.left = checkedChild.right;
					else
						cur.right = checkedChild.right;
//				}
				return true;
			}
			else {
				if(left) {
					cur.left = null;
				} else {
					cur.right = null;
				}
				if(cur.str.equals(""))
					return true;
				if(cur.isKey)
					return true;
				if(left) {
					if(cur.right != null) {
						cur.str = cur.str.concat(cur.right.str);
						cur.left = cur.right.left;
						cur.isKey = cur.right.isKey;
						cur.right = cur.right.right;
					}
				} else {
					if (cur.left != null) {
						cur.str = cur.str.concat(cur.left.str);
						cur.right = cur.left.right;
						cur.isKey = cur.left.isKey;
						cur.left = cur.left.left;
					}
				}
				return true;
				
			}
		}
		return deleteHelper(key, checkedChild);
		
		
    }

    /**
     * Queries the trie for emptiness.
     *
     * @return {@code true} if and only if {@link #getSize()} == 0, {@code false} otherwise.
     */
    public boolean isEmpty() {
    	if(getSize() == 0)
    		return true;
    	return false;
    }

    /**
     * Returns the number of keys in the tree.
     *
     * @return The number of keys in the tree.
     */
    public int getSize() {
    	return count;
    }

    /**
     * <p>Performs an <i>inorder (symmetric) traversal</i> of the Binary Patricia Trie. Remember from lecture that inorder
     * traversal in tries is NOT sorted traversal, unless all the stored keys have the same length. This
     * is of course not required by your implementation, so you should make sure that in your tests you
     * are not expecting this method to return keys in lexicographic order. We put this method in the
     * interface because it helps us test your submission thoroughly and it helps you debug your code! </p>
     *
     * <p>We <b>neither require nor test </b> whether the {@link Iterator} returned by this method is fail-safe or fail-fast.
     * This means that you  do <b>not</b> need to test for thrown {@link java.util.ConcurrentModificationException}s and we do
     * <b>not</b> test your code for the possible occurrence of concurrent modifications.</p>
     *
     * <p>We also assume that the {@link Iterator} is <em>immutable</em>, i,e we do <b>not</b> test for the behavior
     * of {@link Iterator#remove()}. You can handle it any way you want for your own application, yet <b>we</b> will
     * <b>not</b> test for it.</p>
     *
     * @return An {@link Iterator} over the {@link String} keys stored in the trie, exposing the elements in <i>symmetric
     * order</i>.
     */
    public Iterator<String> inorderTraversal() {

    	Iterator<String> iter = new Iterator<String>() {

    		ArrayList<String> strings = unrollTree(root, new ArrayList<String>(), "");
    		int i = 0;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				if(i < strings.size())
					return true;
				return false;
			}

			@Override
			public String next() {
				return strings.get(i++);
			}
    		
    	};
    	return iter;
    }

    private ArrayList<String> unrollTree(TrieNode cur, ArrayList<String> arr, String prefix) {
    	String newPrefix = prefix.concat(cur.str);
    	if(cur.left != null)
    		arr = unrollTree(cur.left, arr, newPrefix);
    	if(cur.isKey)
    		arr.add(newPrefix);
    	if(cur.right != null)
    		arr = unrollTree(cur.right, arr, newPrefix);
    	return arr;
    	
    }
    /**
     * Finds the longest {@link String} stored in the Binary Patricia Trie.
     * @return <p>The longest {@link String} stored in this. If the trie is empty, the empty string &quot;&quot; should be
     * returned. Careful: the empty string &quot;&quot;is <b>not</b> the same string as &quot; &quot;; the latter is a string
     * consisting of a single <b>space character</b>! It is also <b>not the same as the</b> null <b>reference</b>!</p>
     *
     * <p>Ties should be broken in terms of <b>value</b> of the bit string. For example, if our trie contained
     * only the binary strings 01 and 11, <b>11</b> would be the longest string. If our trie contained
     * only 001 and 010, <b>010</b> would be the longest string.</p>
     */
    public String getLongest() {
    	int longestLength = 0;
    	String longestString = "";
    	Iterator<String> iter = this.inorderTraversal();
    	while(iter.hasNext()) {
    		String cur = iter.next();
    		if(cur.length() > longestLength) {
    			longestString = cur;
    			longestLength = cur.length();
    		}
    		else if(cur.length() == longestLength && cur.compareTo(longestString) > 0) {
    			longestString = cur;
    			longestLength = cur.length();
    		}
    		
    	}
    	return longestString;    	
    }

    /**
     * Makes sure that your trie doesn't have splitter nodes with a single child. In a Patricia trie, those nodes should
     * be pruned.
     * @return {@code true} iff all nodes in the trie either denote stored strings or split into two subtrees, {@code false} otherwise.
     */
    public boolean isJunkFree(){
        return isEmpty() || (isJunkFree(root.left) && isJunkFree(root.right));
    }

    private boolean isJunkFree(TrieNode n){
        if(n == null){   // Null subtrees trivially junk-free
            return true;
        }
        if(!n.isKey){   // Non-key nodes need to be strict splitter nodes
            return ( (n.left != null) && (n.right != null) && isJunkFree(n.left) && isJunkFree(n.right) );
        } else {
            return ( isJunkFree(n.left) && isJunkFree(n.right) ); // But key-containing nodes need not.
        }
    }
}
