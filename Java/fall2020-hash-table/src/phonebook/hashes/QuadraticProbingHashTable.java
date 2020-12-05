package phonebook.hashes;

import phonebook.exceptions.UnimplementedMethodException;
import phonebook.utils.KVPair;
import phonebook.utils.PrimeGenerator;
import phonebook.utils.Probes;

/**
 * <p>{@link QuadraticProbingHashTable} is an Openly Addressed {@link HashTable} which uses <b>Quadratic
 * Probing</b> as its collision resolution strategy. Quadratic Probing differs from <b>Linear</b> Probing
 * in that collisions are resolved by taking &quot; jumps &quot; on the hash table, the length of which
 * determined by an increasing polynomial factor. For example, during a key insertion which generates
 * several collisions, the first collision will be resolved by moving 1^2 + 1 = 2 positions over from
 * the originally hashed address (like Linear Probing), the second one will be resolved by moving
 * 2^2 + 2= 6 positions over from our hashed address, the third one by moving 3^2 + 3 = 12 positions over, etc.
 * </p>
 *
 * <p>By using this collision resolution technique, {@link QuadraticProbingHashTable} aims to get rid of the
 * &quot;key clustering &quot; problem that {@link LinearProbingHashTable} suffers from. Leaving more
 * space in between memory probes allows other keys to be inserted without many collisions. The tradeoff
 * is that, in doing so, {@link QuadraticProbingHashTable} sacrifices <em>cache locality</em>.</p>
 *
 * @author Shaun Birch
 *
 * @see HashTable
 * @see SeparateChainingHashTable
 * @see OrderedLinearProbingHashTable
 * @see LinearProbingHashTable
 * @see CollisionResolver
 */
public class QuadraticProbingHashTable extends OpenAddressingHashTable {

    /* ********************************************************************/
    /* ** INSERT ANY PRIVATE METHODS OR FIELDS YOU WANT TO USE HERE: ******/
    /* ********************************************************************/

	private int probeQuad(int hashvalue, int increment) {
		int value = (int) (hashvalue + (increment - 1) + (Math.pow(increment - 1, 2)));
		return value % table.length;
	}
	
    /* ******************************************/
    /*  IMPLEMENT THE FOLLOWING PUBLIC METHODS: */
    /* **************************************** */

    /**
     * Constructor with soft deletion option. Initializes the internal storage with a size equal to the starting value of  {@link PrimeGenerator}.
     * @param soft A boolean indicator of whether we want to use soft deletion or not. {@code true} if and only if
     *               we want soft deletion, {@code false} otherwise.
     */
    public QuadraticProbingHashTable(boolean soft) {
    	softFlag = soft;
    	primeGenerator = new PrimeGenerator();
    	table = new KVPair[primeGenerator.getCurrPrime()];
    	numTombstones = 0;
    	count = 0;
    }

    @Override
    public Probes put(String key, String value) {
    	if(key == null || value == null)
    		throw new IllegalArgumentException("key or value was null");
    	int probes = 0;
    	float numerator = count + numTombstones;
    	float decimal = numerator / ((float) table.length);
    	if(.5 < decimal)
    		probes = reinsertTable(true);
    	int hashvalue = hash(key);
    	int location = hashvalue;
    	int increment = 2;
    	while(table[location] != null) {
    		probes++;
    		location = probeQuad(hashvalue, increment);
    		increment++;
    	}
    	count++;
    	table[location] = new KVPair(key, value);
    	probes++;
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
    	int increment = 2;
    	int location = hashvalue;
    	probes++;
    	while(temp != null && !temp.getKey().equals(key)) {
    		location = probeQuad(hashvalue, increment);
    		increment++;
    		temp = table[location];
    		probes++;
    	}
    	if(temp == null)
    		return new Probes(null, probes);
    	return new Probes(temp.getValue(), probes);    
    }

    @Override
    public Probes remove(String key) {
    	if(key == null)
    		return new Probes(null, 0);
    	int hashvalue = hash(key);
    	int probes = 1;
    	KVPair temp = table[hashvalue];
    	int increment = 2;
    	int location = hashvalue;
    	while(temp != null && !temp.getKey().equals(key)) {
    		location = probeQuad(hashvalue, increment);
    		increment++;
    		temp = table[location];
    		probes++;
    	}
    	if(temp == null) 
    		return new Probes(null, probes);
    	if(softFlag) {
    		Probes probe = new Probes(temp.getValue(), probes);
    		table[location] = TOMBSTONE;
    		numTombstones++;
    		count--;
    		return probe; 		
    	}
    	String value = temp.getValue();
    	table[location] = null;
    	count--;
    	probes += reinsertTable(false);
    	return new Probes(value, probes);
    }


    @Override
    public boolean containsKey(String key) {
    	if(key == null)
    		return false;
    	int hashvalue = hash(key);
    	int increment = 2;
    	int location = hashvalue;
    	while(table[location] != null && !table[location].getKey().equals(key)) {
    		location = probeQuad(hashvalue, increment);
    		increment++;
    	}
    	if(table[location] == null)
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
    	return false;    }
    @Override
    public int size(){
    	return count;
    }

    @Override
    public int capacity() {
        return table.length;
    }

}