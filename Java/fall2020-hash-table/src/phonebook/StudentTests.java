package phonebook;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import phonebook.hashes.*;
import phonebook.utils.NoMorePrimesException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;
import static phonebook.hashes.CollisionResolver.*;

//import sun.plugin.perf.PluginRollup;

/**
 * <p> {@link StudentTests} is a place for you to write your tests for {@link Phonebook} and all the various
 * {@link HashTable} instances.</p>
 *
 * @author Shaun Birch
 * @see Phonebook
 * @see HashTable
 * @see SeparateChainingHashTable
 * @see LinearProbingHashTable
 * @see QuadraticProbingHashTable
 */
public class StudentTests {

    private Phonebook pb;
    private CollisionResolver[] resolvers = {SEPARATE_CHAINING, LINEAR_PROBING, ORDERED_LINEAR_PROBING, QUADRATIC_PROBING};
    private HashMap<String, String> testingPhoneBook;
    private static final long SEED = 47;
    private static final Random RNG = new Random(SEED);
    private static final int NUMS = 1000;
    private static final int UPPER_BOUND = 100;

    private String format(String error, CollisionResolver namesToPhones, CollisionResolver phonesToNames) {
        return error + "Collision resolvers:" + namesToPhones + ", " + phonesToNames + ".";
    }


    private String errorData(Throwable t) {
        return "Received a " + t.getClass().getSimpleName() + " with message: " + t.getMessage() + ".";
    }

    @Before
    public void setUp() {
        testingPhoneBook = new HashMap<>();
        testingPhoneBook.put("Arnold", "894-59-0011");
        testingPhoneBook.put("Tiffany", "894-59-0011");
        testingPhoneBook.put("Jessie", "705-12-7500");
        testingPhoneBook.put("Mary", "888-1212-3340");
    }

    @After
    public void tearDown() {
        testingPhoneBook.clear();
    }


    @Test
    public void testHashing() {
    	int hashvalue = 1;
    	int increment = 1;
		int value = (int) (hashvalue + (increment - 1) + (Math.pow(increment - 1, 2)));
		//System.out.println(value);

		assertTrue(value == 1);

		increment++;
		value = (int) (hashvalue + (increment - 1) + (Math.pow(increment - 1, 2)));
	//	System.out.println(value);
		assertTrue(value == 3);
		increment++;
		value = (int) (hashvalue + (increment - 1) + (Math.pow(increment - 1, 2)));
//		System.out.println(value);
		assertTrue(value == 7);
		
    }
    @Test
    public void testEmpty() {
        SeparateChainingHashTable sc = new SeparateChainingHashTable();

        OrderedLinearProbingHashTable op = new OrderedLinearProbingHashTable(false);
        LinearProbingHashTable lp = new LinearProbingHashTable(false);
        QuadraticProbingHashTable qp = new QuadraticProbingHashTable(false);
        sc.get("aaaa");
        sc.containsValue("aaaa");
        sc.containsKey("aaa");
        sc.remove("aaa");
        sc.get(null);
        sc.containsValue(null);
        sc.containsValue(null);
        op.get("aaaaa");
        op.containsKey("aaaaaa");
        op.containsValue("aaaaaa");
        op.remove("aaaa");
        lp.get("aaaaa");
        lp.containsKey("aaaaaa");
        lp.containsValue("aaaaaa");
        lp.remove("aaaa");
        qp.get("aaaaa");
        qp.containsKey("aaaaaa");
        qp.containsValue("aaaaaa");
        qp.remove("aaaa");
    }
    
    @Test
    public void testSizes() {

        OrderedLinearProbingHashTable op = new OrderedLinearProbingHashTable(false);
        LinearProbingHashTable lp = new LinearProbingHashTable(false);
        QuadraticProbingHashTable qp = new QuadraticProbingHashTable(false);
        assertTrue(op.capacity() == 7);
        assertTrue(lp.capacity() == 7);
        assertTrue(qp.capacity() == 7);
    }
    @Test
    public void testGettingKeys() {
        OrderedLinearProbingHashTable op = new OrderedLinearProbingHashTable(false);
        LinearProbingHashTable lp = new LinearProbingHashTable(false);
        QuadraticProbingHashTable qp = new QuadraticProbingHashTable(false);
        op.put("g", "g");
        op.put("h", "h");
        op.put("j", "j");
        op.put("a", "a");
        op.put("i", "i");
        op.put("b", "b");
        op.put("c", "c");
        op.put("d", "d");
        op.put("e", "e");
        op.put("f", "f");
        
        
        lp.put("h", "h");
        lp.put("i", "i");
        lp.put("j", "j");
        lp.put("a", "a");
        lp.put("b", "b");
        lp.put("c", "c");
        lp.put("d", "d");
        lp.put("e", "e");
        lp.put("f", "f");
        lp.put("g", "g");
       
        
        qp.put("g", "g");
        qp.put("h", "h");
        qp.put("i", "i");
        qp.put("j", "j");
        qp.put("a", "a");
        qp.put("b", "b");
        qp.put("c", "c");
        qp.put("d", "d");
        qp.put("e", "e");
        qp.put("f", "f");
        
        assertTrue(op.containsKey("a"));
        assertTrue(op.containsKey("b"));
        assertTrue(op.containsKey("c"));
        assertTrue(op.containsKey("d"));
        assertTrue(op.containsKey("e"));
        assertTrue(op.containsKey("f"));
        assertTrue(op.containsKey("g"));
        assertTrue(op.containsKey("h"));
        assertTrue(op.containsKey("i"));
        assertTrue(op.containsKey("j"));
        
        assertTrue(lp.containsKey("a"));
        assertTrue(lp.containsKey("b"));
        assertTrue(lp.containsKey("c"));
        assertTrue(lp.containsKey("d"));
        assertTrue(lp.containsKey("e"));
        assertTrue(lp.containsKey("f"));
        assertTrue(lp.containsKey("g"));
        assertTrue(lp.containsKey("h"));
        assertTrue(lp.containsKey("i"));
        assertTrue(lp.containsKey("j"));
        
        assertTrue(qp.containsKey("a"));
        assertTrue(qp.containsKey("b"));
        assertTrue(qp.containsKey("c"));
        assertTrue(qp.containsKey("d"));
        assertTrue(qp.containsKey("e"));
        assertTrue(qp.containsKey("f"));
        assertTrue(qp.containsKey("g"));
        assertTrue(qp.containsKey("h"));
        assertTrue(qp.containsKey("i"));
        assertTrue(qp.containsKey("j"));
        lp.put("bob", "");
        op.put("bob", "");
        qp.put("bob", "");
        assertTrue(lp.containsValue(""));
        assertTrue(qp.containsValue(""));
        assertTrue(op.containsValue(""));
        lp.put("", "hya");
        op.put("", "hya");
        qp.put("", "hya");
        assertTrue(lp.containsKey(""));
        assertTrue(op.containsKey(""));
        assertTrue(qp.containsKey(""));


    }
    
    @Test
    public void testReversability() {
    	 OrderedLinearProbingHashTable op = new OrderedLinearProbingHashTable(false);
         LinearProbingHashTable lp = new LinearProbingHashTable(false);
         QuadraticProbingHashTable qp = new QuadraticProbingHashTable(false);

         SeparateChainingHashTable sp = new SeparateChainingHashTable();
        
    	for(int i = 0; i < 60; i++) {
    		qp.put(i + "", i + "");
    	}
    	for(int i = 60; i >=0; i--) {
    		qp.remove(i + "");
    	}
    	assertTrue(qp.size() == 0);
    	for(int i = 0; i < 60; i++) {
    		qp.put(i + "", i + "");
    	}
    	for(int i = 0; i < 60; i++) {
    		qp.remove(i + "");
    	}

    	assertTrue(qp.size() == 0);
    	for(int i = 0; i < 60; i++) {
    		qp.put(i + "", i + "");
    	}
    	for(int i = 59; i >=0; i--) {
    		qp.remove(i + "");
    	}

    	assertTrue(qp.size() == 0);
    	for(int i = 0; i < 60; i++) {
    		qp.put(i + "", i + "");
    	}
    	for(int i = 0; i < 60; i++) {
    		qp.remove(i + "");
    	}
    	assertTrue(qp.size() == 0);

    	for(int i = 0; i < 60; i++) {
    		lp.put(i + "", i + "");
    	}
    	for(int i = 59; i >=0; i--) {
    		lp.remove(i + "");
    	}

    	assertTrue(lp.size() == 0);
    	for(int i = 0; i < 60; i++) {
    		lp.put(i + "", i + "");
    	}
    	for(int i = 0; i < 60; i++) {
    		lp.remove(i + "");
    	}

    	assertTrue(lp.size() == 0);

    	assertTrue(op.size() == 0);
    	for(int i = 0; i < 60; i++) {
    		op.put(i + "", i + "");
    	}
    	for(int i = 59; i >=0; i--) {
    		op.remove(i + "");
    	}

    	assertTrue(op.size() == 0);
    	for(int i = 0; i < 60; i++) {
    		op.put(i + "", i + "");
    	}
    	for(int i = 0; i < 60; i++) {
    		op.remove(i + "");
    	}

    	assertTrue(op.size() == 0);

    	assertTrue(sp.size() == 0);
    	for(int i = 0; i < 60; i++) {
    		sp.put(i + "", i + "");
    	}
    	for(int i = 0; i < 60; i++) {
    		sp.remove(i + "");
    	}
    	assertTrue(sp.size() == 0);
    	
    	
    }
    
    @Test
    public void testQuadratic() {

        QuadraticProbingHashTable qp = new QuadraticProbingHashTable(false);
        System.out.println(qp.put("a","a").getProbes());
        System.out.println(qp.put("zxc","zxc").getProbes());
        System.out.println(qp.put("bdf","bdf").getProbes());
        System.out.println(qp.put("bgh","bgh").getProbes());
        assertTrue(qp.containsKey("bdf"));
        assertTrue(qp.containsValue("a"));
        assertTrue(qp.get("a").getProbes() == 1);
        assertTrue(qp.get("zxc").getProbes() == 1);
        assertTrue(qp.get("bdf").getProbes() == 2);
        assertTrue(qp.containsKey("zxc"));
        System.out.println(qp.put("htf","htf").getProbes());
        System.out.println(qp.put("f","f").getProbes());
        System.out.println(qp.put("gh","gh").getProbes());
        System.out.println(qp.remove("htf").getProbes());
        System.out.println(qp.put("ij","ij").getProbes());
        System.out.println(qp.put("kl","kl").getProbes());
        
        assertTrue(qp.containsKey("gh"));
        assertTrue(qp.containsKey("ij"));
        assertTrue(qp.containsKey("bdf"));
        
        qp.remove("a");
        qp.remove("zxc");
        qp.remove("bdf");
        qp.remove("bgh");
        qp.remove("f");
        qp.remove("gh");
        qp.remove("ij");
        qp.remove("kl");
        System.out.println(qp.size());
        assertTrue(qp.size() == 0);


    }
    
    @Test
    public void testSeperate() {
        SeparateChainingHashTable sp = new SeparateChainingHashTable();
        System.out.println(sp.put("a","a").getProbes());
        System.out.println(sp.put("zxc","zxc").getProbes());
        System.out.println(sp.put("bdf","bdf").getProbes());
        
        System.out.println(sp.put("bgh","bgh").getProbes());
        System.out.println(sp.put("htf","htf").getProbes());
        System.out.println(sp.put("f","f").getProbes());
        System.out.println(sp.put("gh","gh").getProbes());
        System.out.println(sp.remove("htf").getProbes());
        System.out.println(sp.put("ij","ij").getProbes());
        System.out.println(sp.put("kl","kl").getProbes());
        
        assertTrue(sp.containsKey("gh"));
        assertTrue(sp.containsKey("ij"));
        assertTrue(sp.containsKey("bdf"));
        
        sp.remove("a");
        sp.remove("zxc");
        sp.remove("bdf");
        sp.remove("bgh");
        sp.remove("f");
        sp.remove("gh");
        sp.remove("ij");
        sp.remove("kl");
        System.out.println(sp.size());
        assertTrue(sp.size() == 0);
    }
    
    @Test
    public void testLinear() {

        LinearProbingHashTable lp = new LinearProbingHashTable(false);
    	System.out.println(lp.put("a","a").getProbes());
        System.out.println(lp.put("zxc","zxc").getProbes());
        System.out.println(lp.put("bdf","bdf").getProbes());
        System.out.println(lp.put("bgh","bgh").getProbes());
        assertTrue(lp.containsKey("bdf"));
        assertTrue(lp.containsValue("a"));
        assertTrue(lp.get("a").getProbes() == 1);
        assertTrue(lp.get("bdf").getProbes() == 2);
        System.out.println(lp.put("htf","htf").getProbes());
        System.out.println(lp.put("f","f").getProbes());
        System.out.println(lp.put("gh","gh").getProbes());
        System.out.println(lp.remove("zxc").getProbes());
        System.out.println(lp.put("ij","ij").getProbes());
        System.out.println(lp.put("kl","kl").getProbes());
        
        assertTrue(lp.containsKey("gh"));
        assertTrue(lp.containsKey("ij"));
        assertTrue(lp.containsKey("bdf"));
        
        lp.remove("a");
        lp.remove("htf");
        lp.remove("bdf");
        lp.remove("bgh");
        lp.remove("f");
        lp.remove("gh");
        lp.remove("ij");
        lp.remove("kl");
        System.out.println(lp.size());
        assertTrue(lp.size() == 0);
    }
    
    @Test
    public void testOrdered() {

        OrderedLinearProbingHashTable op = new OrderedLinearProbingHashTable(false);
    	System.out.println(op.put("a","a").getProbes());
        System.out.println(op.put("zxc","zxc").getProbes());
        System.out.println(op.put("bdf","bdf").getProbes());
        System.out.println(op.put("bgh","bgh").getProbes());
        assertTrue(op.containsKey("bdf"));
        assertTrue(op.containsValue("a"));
        assertTrue(op.get("a").getProbes() == 1);
        assertTrue(op.get("zxc").getProbes() == 2);
        assertTrue(op.get("bdf").getProbes() == 1);
        assertTrue(op.containsKey("zxc"));
        System.out.println(op.put("htf","htf").getProbes());
        System.out.println(op.put("f","f").getProbes());
        System.out.println(op.put("gh","gh").getProbes());
        System.out.println(op.remove("zxc").getProbes());
        System.out.println(op.put("ij","ij").getProbes());
//        System.out.println(op.put("kl","kl").getProbes());
        
        assertTrue(op.containsKey("gh"));
        assertTrue(op.containsKey("ij"));
        assertTrue(op.containsKey("bdf"));
        
        op.remove("a");
        op.remove("htf");
        op.remove("bdf");
        op.remove("bgh");
        op.remove("f");
        op.remove("gh");
        op.remove("ij");
//        op.remove("kl");
        System.out.println(op.size());
        assertTrue(op.size() == 0);
    }
    @Test
    public void testOrderedSoft() {

        OrderedLinearProbingHashTable op = new OrderedLinearProbingHashTable(true);
    	System.out.println(op.put("a","a").getProbes());
        System.out.println(op.put("zxc","zxc").getProbes());
        System.out.println(op.put("bdf","bdf").getProbes());
        System.out.println(op.remove("bdf").getProbes());
        System.out.println(op.remove("a").getProbes());
        System.out.println(op.put("bgh","bgh").getProbes());
        System.out.println(op.put("cdc","cdc").getProbes());
        System.out.println(op.put("efg","efg").getProbes());
        assertTrue(op.containsKey("zxc"));
        assertFalse(op.containsKey("a"));
        System.out.println(op.put("htf","htf").getProbes());
        System.out.println(op.put("f","f").getProbes());
        System.out.println(op.put("gh","gh").getProbes());
        System.out.println(op.remove("zxc").getProbes());
        System.out.println(op.put("ij","ij").getProbes());
//        System.out.println(op.put("kl","kl").getProbes());
        
        assertTrue(op.containsKey("gh"));
        assertTrue(op.containsKey("ij"));
        assertTrue(op.containsKey("efg"));
        
        op.remove("cdc");
        op.remove("htf");
        op.remove("efg");
        op.remove("bgh");
        op.remove("f");
        op.remove("gh");
        op.remove("ij");
//        op.remove("kl");
        System.out.println(op.size());
        assertTrue(op.size() == 0);
    }
    
    
    @Test
    public void testCollisions() {

        OrderedLinearProbingHashTable op = new OrderedLinearProbingHashTable(false);
        LinearProbingHashTable lp = new LinearProbingHashTable(false);
        QuadraticProbingHashTable qp = new QuadraticProbingHashTable(false);
        op.put("1", "1");
      //  System.out.println(op.put("7", "7").getProbes());
//        for(int i = 0; i < 5; i ++) {
//        	System.out.println(lp.put("" + i, "" + i).getProbes());
//        }
        lp.put("1","1" );
        lp.put("2", "2");
        lp.put("3", "3");
        lp.put("4", "4");
        lp.put("5", "5");
        //System.out.println(lp.containsKey("3"));
//        System.out.println(lp.remove("3").getProbes());

        OrderedLinearProbingHashTable ap = new OrderedLinearProbingHashTable(true);
        ap.put("1","1" );
        ap.put("2", "2");
        ap.put("3", "3");
        ap.put("4", "4");
        ap.put("5", "5");
  //      System.out.println(ap.containsKey("3"));
    //    System.out.println(ap.remove("3").getProbes());
        
    }
    @Test
    public void testGettingValues() {

        OrderedLinearProbingHashTable op = new OrderedLinearProbingHashTable(false);
        LinearProbingHashTable lp = new LinearProbingHashTable(false);
        QuadraticProbingHashTable qp = new QuadraticProbingHashTable(false);
        op.put("a", "a");
        op.put("t", "b");
        op.put("b", "c");
        op.put("y", "d");
        op.put("sdf", "e");
        op.put("e", "f");
        op.put("u", "g");
        op.put("z", "h");
        op.put("y", "i");
        op.put("p", "j");

        lp.put("a", "a");
        lp.put("t", "b");
        lp.put("b", "c");
        lp.put("y", "d");
        lp.put("sdf", "e");
        lp.put("e", "f");
        lp.put("u", "g");
        lp.put("z", "h");
        lp.put("y", "i");
        lp.put("p", "j");
        
        qp.put("a", "a");
        qp.put("t", "b");
        qp.put("b", "c");
        qp.put("y", "d");
        qp.put("sdf", "e");
        qp.put("e", "f");
        qp.put("u", "g");
        qp.put("z", "h");
        qp.put("y", "i");
        qp.put("p", "j");
        
        assertTrue(op.containsValue("a"));
        assertTrue(op.containsValue("b"));
        assertTrue(op.containsValue("c"));
        assertTrue(op.containsValue("d"));
        assertTrue(op.containsValue("e"));
        assertTrue(op.containsValue("f"));
        assertTrue(op.containsValue("g"));
        assertTrue(op.containsValue("h"));
        assertTrue(op.containsValue("i"));
        assertTrue(op.containsValue("j"));
        
        assertTrue(lp.containsValue("a"));
        assertTrue(lp.containsValue("b"));
        assertTrue(lp.containsValue("c"));
        assertTrue(lp.containsValue("d"));
        assertTrue(lp.containsValue("e"));
        assertTrue(lp.containsValue("f"));
        assertTrue(lp.containsValue("g"));
        assertTrue(lp.containsValue("h"));
        assertTrue(lp.containsValue("i"));
        assertTrue(lp.containsValue("j"));
        
        assertTrue(qp.containsValue("a"));
        assertTrue(qp.containsValue("b"));
        assertTrue(qp.containsValue("c"));
        assertTrue(qp.containsValue("d"));
        assertTrue(qp.containsValue("e"));
        assertTrue(qp.containsValue("f"));
        assertTrue(qp.containsValue("g"));
        assertTrue(qp.containsValue("h"));
        assertTrue(qp.containsValue("i"));
        assertTrue(qp.containsValue("j"));
        
        assertFalse(qp.containsValue("k"));
        assertFalse(qp.containsValue("l"));
        assertFalse(qp.containsValue("m"));
        assertFalse(qp.containsValue("n"));
        assertFalse(qp.containsValue("o"));
        assertFalse(qp.containsValue("p"));

        assertFalse(op.containsValue("k"));
        assertFalse(op.containsValue("l"));
        assertFalse(op.containsValue("m"));
        assertFalse(op.containsValue("n"));
        assertFalse(op.containsValue("o"));
        assertFalse(op.containsValue("p"));
        
        assertFalse(lp.containsValue("k"));
        assertFalse(lp.containsValue("l"));
        assertFalse(lp.containsValue("m"));
        assertFalse(lp.containsValue("n"));
        assertFalse(lp.containsValue("o"));
        assertFalse(lp.containsValue("p"));
    }
    
    @Test
    public void testInsertions() {
    	
    }
    @Test
    public void testProbes() {

        LinearProbingHashTable lp = new LinearProbingHashTable(false);
        QuadraticProbingHashTable qp = new QuadraticProbingHashTable(false);
    	 String[] add1 = new String[]{"Tiffany", "Helen", "Alexander", "Paulette", "Jason", "Money", "Nakeesha", "Ray", "Jing", "Amg"};
         String[] remove1 = new String[]{"Helen", "Alexander", "Paulette", "Jason", "Money", "Nakeesha", "Ray", "Jing", "Amg"};
         String[] add2 = new String[]{"Christine", "Carl"};
//System.out.println("start    ");
         for(String s: add1) {
        	 lp.put(s, s);
        	 qp.put(s, s);
         }
  //       System.out.println("end");

    }
    // Make sure that all possible phonebooks we can create will report empty when beginning.
    @Test
    public void testBehaviorWhenEmpty() {
        for (CollisionResolver namesToPhones : resolvers) {
            for (CollisionResolver phonesToNames : resolvers) {
                pb = new Phonebook(namesToPhones, phonesToNames);
                assertTrue(format("Phonebook should be empty", namesToPhones, phonesToNames), pb.isEmpty());
            }
        }
    }

    // See if all of our hash tables cover the simple example from the writeup.
    @Test
    public void testOpenAddressingResizeWhenInsert() {
        SeparateChainingHashTable sc = new SeparateChainingHashTable();
        LinearProbingHashTable lp = new LinearProbingHashTable(false);
        QuadraticProbingHashTable qp = new QuadraticProbingHashTable(false);
        assertEquals("Separate Chaining hash should have a capacity of 7 at startup.", 7, sc.capacity());
        assertEquals("Linear Probing hash should have a capacity of 7 at startup.", 7, lp.capacity());
        assertEquals("Quadratic Probing hash should have a capacity of 7 at startup.", 7, qp.capacity());
        for (Map.Entry<String, String> entry : testingPhoneBook.entrySet()) { // https://docs.oracle.com/javase/10/docs/api/java/util/Map.Entry.html
            sc.put(entry.getKey(), entry.getValue());
            lp.put(entry.getKey(), entry.getValue());
            qp.put(entry.getKey(), entry.getValue());
        }
        assertEquals("Separate Chaining hash should have a capacity of 7 after inserting 4 elements.", 7, sc.capacity());
        assertEquals("Linear Probing hash should have a capacity of 7 after inserting 4 elements.", 7, lp.capacity());
        assertEquals("Quadratic Probing hash should have a capacity of 7 after inserting 4 elements.", 7, qp.capacity());

        sc.put("DeAndre", "888-1212-3340");
        assertEquals("Separate Chaining hash should still have a capacity of 7 after inserting 5 elements.", 7, sc.capacity());
        sc.enlarge();
        assertEquals("Separate Chaining hash should have a capacity of 13 after first call to enlarge().", 13, sc.capacity());
        sc.enlarge();
        assertEquals("Separate Chaining hash should have a capacity of 23 after second call to enlarge().", 23, sc.capacity());
        sc.shrink();
        assertEquals("Separate Chaining hash should have a capacity of 13 after two calls to enlarge() and one to shrink().",
                13, sc.capacity());
        sc.shrink();
        assertEquals("Separate Chaining hash should have a capacity of 7 after two calls to enlarge() and two to shrink().",
                7, sc.capacity());
        lp.put("DeAndre","888-1212-3340" );
        assertEquals("Linear Probing hash should have a capacity of 13 after inserting 5 elements.",
                13, lp.capacity());
        qp.put("DeAndre","888-1212-3340" );
        assertEquals("Quadratic Probing hash should have a capacity of 13 after inserting 5 elements.",
                13, qp.capacity());

        // The following two deletions should both fail and thus not affect capacity.

        lp.remove("Thomas");
        assertEquals("Linear Probing hash with starting capacity of 7 should have a capacity of 13 after " +
                "five insertions and a failed deletion.", 13, lp.capacity());
        qp.remove("Thomas" );
        assertEquals("Quadratic Probing hash with starting capacity of 7 should have a capacity of 13 after " +
                "five insertions and a failed deletion.", 13, qp.capacity());
    }

    // An example of a stress test to catch any insertion errors that you might get.
    @Test
    public void insertionStressTest() {
        HashTable sc = new SeparateChainingHashTable();
        HashTable lp = new LinearProbingHashTable(false);
        HashTable qp = new QuadraticProbingHashTable(false);
        for (int i = 0; i < NUMS; i++) {
            String randomNumber = Integer.toString(RNG.nextInt(UPPER_BOUND));
            String randomNumber2 = Integer.toString(RNG.nextInt(UPPER_BOUND));
            try {
                sc.put(randomNumber, randomNumber2);
            } catch (NoMorePrimesException ignored) {
                // To have this exception thrown is not a problem; we have a finite #primes to generate resizings for.
            } catch (Throwable t) {
                fail("Separate Chaining hash failed insertion #" + i + ". Error message: " + errorData(t));
            }

            try {
                lp.put(randomNumber, randomNumber2);
            } catch (NoMorePrimesException ignored) {
                // To have this exception thrown is not a problem; we have a finite #primes to generate resizings for.
            } catch (Throwable t) {
                fail("Linear Probing hash failed insertion #" + i + ". Error message: " + errorData(t));
            }


            try {
                qp.put(randomNumber, randomNumber2);
            } catch (NoMorePrimesException ignored) {
                // To have this exception thrown is not a problem; we have a finite #primes to generate resizings for.
            } catch (Throwable t) {
                fail("Quadratic Probing hash failed insertion #" + i + ". Error message: " + errorData(t));
            }
        }

    }

    @Test
    public void testSCProbes() {
        SeparateChainingHashTable sc = new SeparateChainingHashTable();

        assertEquals(1, sc.put("Arnold", "894-59-0011").getProbes());
        assertEquals(1, sc.put("Tiffany", "894-59-0011").getProbes());
        assertEquals(1, sc.put("Jessie", "705-12-7500").getProbes());
        assertEquals(1, sc.put("Mary", "888-1212-3340").getProbes());

        assertEquals(1, sc.get("Arnold").getProbes());
        assertEquals("894-59-0011", sc.get("Arnold").getValue());
        assertEquals(1, sc.get("Tiffany").getProbes());
        assertEquals(2, sc.get("Jessie").getProbes());
        assertEquals(1, sc.get("Mary").getProbes());

        // Search fail
        assertEquals(2, sc.get("Jerry").getProbes());
        assertEquals(2, sc.remove("Jerry").getProbes());
        assertNull(sc.remove("Jerry").getValue());

        assertEquals(1, sc.remove("Arnold").getProbes());
        assertEquals(1, sc.remove("Tiffany").getProbes());
        assertEquals(1, sc.remove("Jessie").getProbes());
        assertEquals(1, sc.remove("Mary").getProbes());

    }


    @Test
    public void testLProbes() {

        LinearProbingHashTable lp = new LinearProbingHashTable(false);

        assertEquals(1, lp.put("Arnold", "894-59-0011").getProbes());
        assertEquals(1, lp.put("Tiffany", "894-59-0011").getProbes());
        assertEquals(2, lp.put("Jessie", "705-12-7500").getProbes());
        assertEquals(1, lp.put("Mary", "888-1212-3340").getProbes());


        assertEquals(1, lp.get("Arnold").getProbes());
        assertEquals("894-59-0011", lp.get("Arnold").getValue());
        assertEquals(1, lp.get("Tiffany").getProbes());
        assertEquals(2, lp.get("Jessie").getProbes());
        assertEquals(1, lp.get("Mary").getProbes());

        // Search fail
        assertEquals(2, lp.get("Jerry").getProbes());
        assertEquals(2, lp.remove("Jerry").getProbes());
        assertEquals(null, lp.remove("Jerry").getValue());

        assertEquals(3, lp.remove("Jessie").getProbes());
        assertEquals(2, lp.remove("Arnold").getProbes());
        assertEquals(2, lp.remove("Tiffany").getProbes());
        assertEquals(2, lp.remove("Mary").getProbes());



    }

    @Test
    public void testResizeSoftLProbes() {

        LinearProbingHashTable lp = new LinearProbingHashTable(true);
        String[] add1 = new String[]{"Tiffany", "Helen", "Alexander", "Paulette", "Jason", "Money", "Nakeesha", "Ray", "Jing", "Amg"};
        String[] remove1 = new String[]{"Helen", "Alexander", "Paulette", "Jason", "Money", "Nakeesha", "Ray", "Jing", "Amg"};
        String[] add2 = new String[]{"Christine", "Carl"};

        for(String s: add1) {
            lp.put(s, s);
        }

        for (String s: remove1) {
            lp.remove(s);
        }

        for(String s: add2) {
            lp.put(s, s);
        }

        assertEquals("After additions and deletions, and additions again, the capacity should be 23, but get " + lp.capacity() + ".", 23, lp.capacity());

        lp.put("Terry", "new");
        assertEquals("After additions and deletions, and additions again, resize should be triggered and the capacity should be 43, but get " + lp.capacity() + ".", 43, lp.capacity());

    }


}
