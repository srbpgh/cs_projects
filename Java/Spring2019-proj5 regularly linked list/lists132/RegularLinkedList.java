package lists132;

//Shaun Birch 115938328 srbpgh
import java.lang.IndexOutOfBoundsException;

import org.w3c.dom.Node;
import java.io.*; 
import java.util.*; 

/**
 * makes a linked list that can be added to consiting of objects of type T the
 * list can be added and subtracted to as well as searched in various ways
 * 
 * @author Shaun Birch srbpgh 115938328
 *
 * @param <T> the type of object that the list will use
 */
public class RegularLinkedList<T extends Comparable<T>> 
implements Comparable<RegularLinkedList<T>>, Iterable<T>{

	protected class Node {
		protected T data;
		protected Node next;
	}

	protected Node head = null;

	public RegularLinkedList() {
		Node a = new Node();
		head = a;
	}
	public Iterator<T> iterator(){
		return new myIterator();
	}
	
	 class myIterator implements Iterator<T> {
		private Node cur;
		private Node last;
		
		
		public boolean hasNext() {
			if(cur == null) {
				if(head == null) {
					return false;
				}
				if(head.data != null) {
					return true;
				}
				return false;
			}
			if(cur.next == null) {
				return false;
			}
			return true;
		}
		
		@Override
		public T next()  throws NoSuchElementException{
			if(cur == null) {
				cur = head;
				if(cur.data == null) {
					throw new NoSuchElementException();
				}
				else {
					return cur.data;
				}
			}
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				last = cur;
				cur = cur.next;
				return cur.data;
			}
		}
		
		@Override
		public void remove() throws IllegalStateException{
			if(cur == head && last != cur) {
				head = cur.next;
			}
			else {
					
				if(cur == null || last == cur) {
					throw new IllegalStateException();
				}
				
				last.next = cur.next;
				cur = last;
			}
		}
	}

	/**
	 * Appends a value to the end of the list
	 * 
	 * @param newValue the value to be appended to the end of the list
	 * @throws IllegalArgumentException if the value being added is null
	 */
	public void add(T newValue) throws IllegalArgumentException {
		if (newValue == null) {
			throw new IllegalArgumentException();
		}
		Node cur = head;
		if (head.data == null) {
			head.data = newValue;
		} else {
			while (cur.next != null) {
				cur = cur.next;
			}
			Node n = new Node();
			n.data = newValue;
			cur.next = n;
		}

	}

	/**
	 * 
	 * @return returns the number of elements in the list
	 */
	public int length() {
		if(head == null) {
			return 0;
		}
		if (head.data == null && head.next == null) {
			return 0;
		}
		Node cur = head;
		int counter = 0;
		while (cur != null) {
			counter++;
			cur = cur.next;
		}
		return counter;
	}

	/**
	 * returns the values of the list as a string
	 */
	public String toString() {
		Node cur = head;
		String s = "";
		if (this.length() == 0) {
			return "";
		}
		while (cur != null) {
			if (cur.data != null) {
				if (cur.next == null) {
					s += cur.data.toString() + "";
				} else {
					s += cur.data.toString() + " ";
				}
			}
			cur = cur.next;
		}
		return s;
	}

	/**
	 * removes all the elements from the list
	 */
	public void clear() {
		head.data = null;
		head.next = null;
	}

	/**
	 * counts the number of times a value is present in the list
	 * 
	 * @param value the value being searched for
	 * @return the number of times the value appears in the list
	 * @throws IllegalArgumentException if the value that is being 
	 * searched for is null
	 */
	public int numTimesValueIsPresent(T value) throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		Node cur = head;
		int count = 0;
		while (cur != null) {
			if (cur.data.compareTo(value) == 0) {
				count++;
			}
			cur = cur.next;
		}
		return count;
	}

	/**
	 * returns the index position of the value in this list
	 * 
	 * @param value the value being searched for
	 * @return returns the index position of the value that is searched for in
	 *  the list if it exists in the list
	 * @throws IllegalArgumentException is thrown if the value being 
	 * searched for is null
	 */
	public int indexOfValue(T value) throws IllegalArgumentException {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		Node cur = head;
		int index = 0;
		while (cur != null) {
			if (cur.data.compareTo(value) == 0) {
				return index;
			}
			cur = cur.next;
			index++;
		}
		return -1;
	}

	/**
	 * gets the value at the index specified by int position in the list
	 * 
	 * @param position the index value of the element being searched for
	 * @return the data in the element specified by position
	 * @throws IndexOutOfBoundsException if the position is outside of 
	 * the length of the list
	 */
	public T valueAtIndex(int position) throws IndexOutOfBoundsException {
		if (position < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node cur = head;
		int count = 0;
		while (cur != null) {
			if (count == position) {
				return cur.data;
			}
			cur = cur.next;
			count++;
		}
		throw new IndexOutOfBoundsException();
	}

	/**
	 * removes the values int the list between index position fromPos and toPos
	 * 
	 * @param fromPos the starting index
	 * @param toPos   the ending index
	 * @throws IndexOutOfBoundsException if fromPos or twoPos is not in the list
	 */
	public void removeValuesInRange(int fromPos, int toPos)
			throws IndexOutOfBoundsException {
		if (fromPos < 0 || toPos < 0 || valueAtIndex(fromPos) == null
				|| valueAtIndex(toPos) == null) {
			throw new IndexOutOfBoundsException();
		}
		Node cur = head;
		int index = 0;
		if (fromPos == 0) {
			if (fromPos != toPos) {
				for (int i = 0; i < toPos; i++) {
					cur.next = cur.next.next;
					if (cur.next != null) {
						cur.data = cur.next.data;
					}
				}
				if (cur.next != null) {
					cur.next = cur.next.next;
				} else {
					cur.data = null;
				}
			} else {
				cur.data = cur.next.data;
				cur.next = cur.next.next;
			}
		} else {
			while (cur.next != null) {
				if (index + 1 >= fromPos && index + 1 <= toPos) {
					cur.next = cur.next.next;
				} else {
					cur = cur.next;
				}
				index++;
			}
		}
	}

	/**
	 * compares the current list to otherList
	 */
	public int compareTo(RegularLinkedList<T> otherList) {
		Node cur1 = head;
		Node cur2 = otherList.head;
		if (this == otherList) {
			return 0;
		}
		if (cur1.data == null) {
			if (cur2.data != null) {
				return -1;
			}
		}
		if (cur2.data == null) {
			if (cur1.data != null) {
				return 1;
			}
		}

		if (head.data != null && otherList.head.data != null) {
			while (cur1 != null) {
				if (cur2 == null) {
					return 1;
				}
				if (cur1.data.compareTo(cur2.data) > 0) {
					return 1;
				} else if (cur1.data.compareTo(cur2.data) < 0) {
					return -1;
				}
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			if (cur2 != null) {
				return -1;
			}
		}

		return 0;
	}

}
