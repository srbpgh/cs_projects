package phonebook.hashes;

import phonebook.exceptions.UnimplementedMethodException;
import phonebook.utils.KVPair;
import phonebook.utils.PrimeGenerator;
import phonebook.utils.Probes;

/**
 * <p>{@link LinearProbingHashTable} is an Openly Addressed {@link HashTable} implemented with <b>Linear Probing</b> as its
 * collision resolution strategy: every key collision is resolved by moving one address over. It is
 * the most famous collision resolution strategy, praised for its simplicity, theoretical properties
 * and cache locality. It <b>does</b>, however, suffer from the &quot; clustering &quot; problem:
 * collision resolutions tend to cluster collision chains locally, making it hard for new keys to be
 * inserted without collisions. {@link QuadraticProbingHashTable} is a {@link HashTable} that
 * tries to avoid this problem, albeit sacrificing cache locality.</p>
 *
 * @author Shaun Birch
 *
 * @see HashTable
 * @see SeparateChainingHashTable
 * @see OrderedLinearProbingHashTable
 * @see QuadraticProbingHashTable
 * @see CollisionResolver
 */
public class LinearProbingHashTable extends OpenAddressingHashTable {

    /* ********************************************************************/
    /* ** INSERT ANY PRIVATE METHODS OR FIELDS YOU WANT TO USE HERE: ******/
    /* ********************************************************************/
	private int reinsertRestOfChain(int hashvalue) {
    	int probes = 1;
    	KVPair temp = table[hashvalue];
    	while(temp != null) {
    		table[hashvalue] = null;
    		hashvalue = (hashvalue + 1) % table.length;
    		probes++;
    		count--;
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
     *
     * @param soft A boolean indicator of whether we want to use soft deletion or not. {@code true} if and only if
     *             we want soft deletion, {@code false} otherwise.
     */
    public LinearProbingHashTable(boolean soft) {
    	softFlag = soft;
    	count = 0;
    	primeGenerator = new PrimeGenerator();
    	table = new KVPair[primeGenerator.getCurrPrime()];
    	numTombstones = 0;
    }

    /**
     * Inserts the pair &lt;key, value&gt; into this. The container should <b>not</b> allow for {@code null}
     * keys and values, and we <b>will</b> test if you are throwing a {@link IllegalArgumentException} from your code
     * if this method is given {@code null} arguments! It is important that we establish that no {@code null} entries
     * can exist in our database because the semantics of {@link #get(String)} and {@link #remove(String)} are that they
     * return {@code null} if, and only if, their key parameter is {@code null}. This method is expected to run in <em>amortized
     * constant time</em>.
     * <p>
     * Instances of {@link LinearProbingHashTable} will follow the writeup's guidelines about how to internally resize
     * the hash table when the capacity exceeds 50&#37;
     *
     * @param key   The record's key.
     * @param value The record's value.
     * @return The {@link phonebook.utils.Probes} with the value added and the number of probes it makes.
     * @throws IllegalArgumentException if either argument is {@code null}.
     */
    @Override
    public Probes put(String key, String value) {
    	if(key == null || value == null)
    		throw new IllegalArgumentException("key or valueu was null");
    	int probes = 0;
    	float numerator = count + numTombstones;
    	float decimal = numerator / ((float) table.length);
    	if(.5 < decimal)
    		probes = reinsertTable(true);
    	int hashvalue = hash(key);
    	probes++;
    	while(table[hashvalue] != null) {
    		hashvalue++;
    		probes++;
    		hashvalue = hashvalue % table.length;
    	}
    	count++;
    	table[hashvalue] = new KVPair(key, value);
    	Probes probe = new Probes(value, probes);
    	return probe;
    }

    @Override
    public Probes get(String key) {
    	if(key == null)
    		return new Probes(null, 0);
    	int hashvalue = hash(key);
    	int probes = 0;
    	KVPair temp = table[hashvalue];
    	while(temp != null && !temp.getKey().equals(key)) {
    		hashvalue++;
    		hashvalue = hashvalue % table.length;
    		temp = table[hashvalue];
    		probes++;
    	}
    	if(temp == null)
    		return new Probes(null, ++probes);
    	return new Probes(temp.getValue(), ++probes);
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
    	while(temp != null && !temp.getKey().equals(key)) {
    		hashvalue++;
    		hashvalue = hashvalue % table.length;
    		temp = table[hashvalue];
    		probes++;
    	}
    	if(temp == null) 
    		return new Probes(null, probes);
    	if(softFlag) {
    		Probes probe = new Probes(temp.getValue(), probes);
    		table[hashvalue] = TOMBSTONE;
    		numTombstones++;
    		count--;
    		return probe;
    	}
    	String value = temp.getValue();
    	table[hashvalue] = null;
    	count--;
    	probes += reinsertRestOfChain((hashvalue + 1) % table.length);

    	return new Probes(value, probes);
    	
    }
    
    

    @Override
    public boolean containsKey(String key) {
    	if(key == null)
    		return false;
    	int hashvalue = hash(key);
    	while(table[hashvalue] != null && !table[hashvalue].getKey().equals(key)) {
    		hashvalue = (hashvalue + 1) % table.length;
    	}
    	if(table[hashvalue] == null)
    		return false;
    	return true;
    }

    @Override
    public boolean containsValue(String value) {
    	if(value == null)
    		return false;
    	int length = table.length;
    	for(int i = 0; i < length;i++) {
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
