package phonebook.hashes;

import phonebook.exceptions.UnimplementedMethodException;
import phonebook.utils.KVPairList;
import phonebook.utils.KVPair;
import phonebook.utils.PrimeGenerator;
import phonebook.utils.Probes;
import java.util.Iterator;

/**<p>{@link SeparateChainingHashTable} is a {@link HashTable} that implements <b>Separate Chaining</b>
 * as its collision resolution strategy, i.e the collision chains are implemented as actual
 * Linked Lists. These Linked Lists are <b>not assumed ordered</b>. It is the easiest and most &quot; natural &quot; way to
 * implement a hash table and is useful for estimating hash function quality. In practice, it would
 * <b>not</b> be the best way to implement a hash table, because of the wasted space for the heads of the lists.
 * Open Addressing methods, like those implemented in {@link LinearProbingHashTable} and {@link QuadraticProbingHashTable}
 * are more desirable in practice, since they use the original space of the table for the collision chains themselves.</p>
 *
 * @author Shaun Birch
 * @see HashTable
 * @see SeparateChainingHashTable
 * @see LinearProbingHashTable
 * @see OrderedLinearProbingHashTable
 * @see CollisionResolver
 */
public class SeparateChainingHashTable implements HashTable{

    /* ****************************************************************** */
    /* ***** PRIVATE FIELDS / METHODS PROVIDED TO YOU: DO NOT EDIT! ***** */
    /* ****************************************************************** */

    private KVPairList[] table;
    private int count;
    private PrimeGenerator primeGenerator;

    // We mask the top bit of the default hashCode() to filter away negative values.
    // Have to copy over the implementation from OpenAddressingHashTable; no biggie.
    private int hash(String key){
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    /* **************************************** */
    /*  IMPLEMENT THE FOLLOWING PUBLIC METHODS:  */
    /* **************************************** */
    /**
     *  Default constructor. Initializes the internal storage with a size equal to the default of {@link PrimeGenerator}.
     */
    public SeparateChainingHashTable(){
    	 count = 0;
    	 primeGenerator = new PrimeGenerator();
    	 table = new KVPairList[primeGenerator.getCurrPrime()];
    	 for(int i = 0; i < table.length; i++){
    		 table[i] = new KVPairList();
    	 }
    }

    @Override
    public Probes put(String key, String value) {
    	if(key == null || value == null)
    		return new Probes(null, 0);
    	count++;
    	int hash = hash(key);
    	if(table[hash] == null)
    		table[hash] = new KVPairList();
    	
    	table[hash].addBack(key, value);
    	Probes probe;
    	probe = new Probes(value, 1);
    	return probe;

    }

    @Override
    public Probes get(String key) {
    	if(key == null)
    		return new Probes(null, 0);
    	int hash = hash(key);
    	Probes probe;
    		probe = table[hash].getValue(key);
    	return probe;
    }

    @Override
    public Probes remove(String key) {
    	if(key == null)
    		return new Probes(null, 0);
    	int hash = hash(key);
    	Probes probe;
    	probe = table[hash].removeByKey(key);
    	count--;
    	return probe;
    }

    @Override
    public boolean containsKey(String key) {
    	if(key == null)
    		return false;
    	int hash = hash(key);
    	if(table[hash] != null)
    		return table[hash].containsKey(key);
    	return false;
    	
    }

    @Override
    public boolean containsValue(String value) {
    	if(value == null)
    		return false;
    	for(int i = 0; i < table.length; i ++) {
    		if(table[i] != null) {
	    		if(table[i].containsValue(value))
	    			return true;
	    	}
	    }
    	return false;
    	    	    	
    }

    @Override
    public int size() {
    	return count;
    }

    @Override
    public int capacity() {
        return table.length; // Or the value of the current prime.
    }

    /**
     * Enlarges this hash table. At the very minimum, this method should increase the <b>capacity</b> of the hash table and ensure
     * that the new size is prime. The class {@link PrimeGenerator} implements the enlargement heuristic that
     * we have talked about in class and can be used as a black box if you wish.
     * @see PrimeGenerator#getNextPrime()
     */
    public void enlarge() {
    	KVPairList[] temp = table.clone();
    	table = new KVPairList[primeGenerator.getNextPrime()];
    	for(int i = 0; i < temp.length; i++) {
    		if(temp[i] != null) {
	    		Iterator<KVPair> iter = temp[i].iterator();
	    		while(iter.hasNext()) {
	    			KVPair cur = iter.next();
	    			int hashvalue = hash(cur.getKey());
	    			if(table[hashvalue] == null)
	    				table[hashvalue] = new KVPairList();
	    			table[hashvalue].addBack(cur.getKey(), cur.getValue());
	    		}
	    	}
    	}
    	
    }

    /**
     * Shrinks this hash table. At the very minimum, this method should decrease the size of the hash table and ensure
     * that the new size is prime. The class {@link PrimeGenerator} implements the shrinking heuristic that
     * we have talked about in class and can be used as a black box if you wish.
     *
     * @see PrimeGenerator#getPreviousPrime()
     */
    public void shrink(){
    	KVPairList[] temp = table.clone();
    	table = new KVPairList[primeGenerator.getPreviousPrime()];
    	for(int i = 0; i < temp.length; i++) {
    		if(temp[i] != null) {
	    		Iterator<KVPair> iter = temp[i].iterator();
	    		while(iter.hasNext()) {
	    			KVPair cur = iter.next();
	    			int hashvalue = hash(cur.getKey());
	    			if(table[hashvalue] == null)
	    				table[hashvalue] = new KVPairList();
	    			table[hashvalue].addBack(cur.getKey(), cur.getValue());
	    		}
	    	}
    	}
    }	
}
