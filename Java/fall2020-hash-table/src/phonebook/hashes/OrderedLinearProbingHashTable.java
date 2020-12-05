package phonebook.hashes;

import phonebook.exceptions.UnimplementedMethodException;
import phonebook.utils.KVPair;
import phonebook.utils.PrimeGenerator;
import phonebook.utils.Probes;

/**
 * <p>{@link OrderedLinearProbingHashTable} is an Openly Addressed {@link HashTable} implemented with
 * <b>Ordered Linear Probing</b> as its collision resolution strategy: every key collision is resolved by moving
 * one address over, and the keys in the chain is in order. It suffer from the &quot; clustering &quot; problem:
 * collision resolutions tend to cluster collision chains locally, making it hard for new keys to be
 * inserted without collisions. {@link QuadraticProbingHashTable} is a {@link HashTable} that
 * tries to avoid this problem, albeit sacrificing cache locality.</p>
 *
 * @author YOUR NAME HERE!
 *
 * @see HashTable
 * @see SeparateChainingHashTable
 * @see LinearProbingHashTable
 * @see QuadraticProbingHashTable
 * @see CollisionResolver
 */
public class OrderedLinearProbingHashTable extends OpenAddressingHashTable {

    /* ********************************************************************/
    /* ** INSERT ANY PRIVATE METHODS OR FIELDS YOU WANT TO USE HERE: ******/
    /* ********************************************************************/
	private int reinsertRestOfChain(int hashvalue, String key) {
    	int probes = 1;
    	KVPair temp = table[hashvalue];
    	while(temp != null && temp.getKey().compareTo(key) > 0) {
    		table[hashvalue] = null;
    		hashvalue = (hashvalue + 1) % table.length;
    		probes++;
    		count--;
    		key = temp.getKey();
    		probes += put(temp.getKey(),temp.getValue()).getProbes();
    		temp = table[hashvalue];
    	}
    	return probes;
    	
    }
	
	
    /* ******************************************/
    /*  IMPLEMENT THE FOLLOWING PUBLIC METHODS: */
    /* **************************************** */

    /**
     * Constructor with soft deletion option. Initializes the internal storage with a size equal to the starting value of  {@link PrimeGenerator}.
     * @param soft A boolean indicator of whether we want to use soft deletion or not. {@code true} if and only if
     *               we want soft deletion, {@code false} otherwise.
     */
    public OrderedLinearProbingHashTable(boolean soft){
    	primeGenerator = new PrimeGenerator();
    	table = new KVPair[primeGenerator.getCurrPrime()];
    	softFlag = soft;
    	count = 0;
    	numTombstones = 0;
    	
    }



    /**
     * Inserts the pair &lt;key, value&gt; into this. The container should <b>not</b> allow for {@code null}
     * keys and values, and we <b>will</b> test if you are throwing a {@link IllegalArgumentException} from your code
     * if this method is given {@code null} arguments! It is important that we establish that no {@code null} entries
     * can exist in our database because the semantics of {@link #get(String)} and {@link #remove(String)} are that they
     * return {@code null} if, and only if, their key parameter is {@code null}. This method is expected to run in <em>amortized
     * constant time</em>.
     *
     * Different from {@link LinearProbingHashTable}, the keys in the chain are <b>in order</b>. As a result, we might increase
     * the cost of insertion and reduce the cost on search miss. One thing to notice is that, in soft deletion, we ignore
     * the tombstone during the reordering of the keys in the chain. We will have some example in the writeup.
     *
     * Instances of {@link OrderedLinearProbingHashTable} will follow the writeup's guidelines about how to internally resize
     * the hash table when the capacity exceeds 50&#37;
     * @param key The record's key.
     * @param value The record's value.
     * @throws IllegalArgumentException if either argument is {@code null}.
     * @return The {@link phonebook.utils.Probes} with the value added and the number of probes it makes.
     */
    @Override
    public Probes put(String key, String value) {
    	if(key == null || value == null)
    		throw new IllegalArgumentException("key or value was null");
    	int probes = 0;

    	if(.5 < ((float)count + (float)numTombstones)/(float)table.length)
    		probes = reinsertTable(true);
    	int hashvalue = hash(key);
    	probes++;
    	KVPair temp = table[hashvalue];
    	while(temp != null) {
    		if(temp.getKey().compareTo(key) > 0 ) {
    			KVPair newEntry = new KVPair(key,value);
    			key = temp.getKey();
    			value = temp.getValue();
    			table[hashvalue] = newEntry;
    		}
    		hashvalue = (1 + hashvalue) % table.length;
    		temp = table[hashvalue];
    		probes++;
    	}
    	count++;
    	table[hashvalue] = new KVPair(key, value);
    	return new Probes(value, probes);
    }

    @Override
    public Probes get(String key) {
    	if(key == null)
    		return new Probes(null, 0);
    	int hashvalue = hash(key);
    	int probes = 1;
    	KVPair temp = table[hashvalue];
    	while(temp != null && (temp.getKey().compareTo(key) < 0 || temp == TOMBSTONE)) {
    		hashvalue = (1 + hashvalue) % table.length;
    		temp = table[hashvalue];
    		probes++;
    	}
    	if(temp != null && temp.getKey().equals(key))
    		return new Probes(temp.getValue(), probes);
    	return new Probes(null, probes);
    }


    /**
     * <b>Return</b> the value associated with key in the {@link HashTable}, and <b>remove</b> the {@link phonebook.utils.KVPair} from the table.
     * If key does not exist in the database
     * or if key = {@code null}, this method returns {@code null}. This method is expected to run in <em>amortized constant time</em>.
     *
     * @param key The key to search for.
     * @return The {@link phonebook.utils.Probes} with associated value and the number of probe used. If the key is {@code null}, return value {@code null}
     * and 0 as number of probes; if the key doesn't exist in the database, return {@code null} and the number of probes used.
     */
    @Override
    public Probes remove(String key) {
    	if(key == null)
    		return new Probes(null, 0);
    	int hashvalue = hash(key);
    	int probes = 1;
    	KVPair temp = table[hashvalue];
    	while(temp != null && (temp.getKey().compareTo(key) < 0 || temp == TOMBSTONE)) {
    		hashvalue = (1 + hashvalue % table.length);
    		temp = table[hashvalue];
    		probes++;
    	}
    	if(temp != null && temp.getKey().equals(key)) {
    		count--;
			String value = temp.getValue();
    		if(softFlag) {
    			table[hashvalue] = TOMBSTONE;
    			numTombstones++;
    		} else {
        		table[hashvalue] = null;
        		probes += reinsertRestOfChain((hashvalue + 1) % table.length, value);
        			
    		}
    		return new Probes(value, probes);
    	}
    	return new Probes(null, probes);
    }

    @Override
    public boolean containsKey(String key) {
    	if(key == null)
    		return false;
    	int hashvalue = hash(key);
    	while(table[hashvalue] != null && table[hashvalue].getKey().compareTo(key) < 0) {
    		hashvalue = (1 + hashvalue % table.length);
    	}
    	if(table[hashvalue] != null && table[hashvalue].getKey().equals(key))
    		return true;
    	return false;
    }

    @Override
    public boolean containsValue(String value) {
    	if(value == null)
    		return false;
    	int length = table.length;
    	for(int i = 0; i < length; i ++) {
    		if(table[i] != null && table[i].getValue().equals(value))
    			return true;
    	}
    	return false;
    }

    @Override
    public int size() {
    	return count;
    }

    @Override
    public int capacity() {
    	return table.length;
    }

}
