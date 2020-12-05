package bpt;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * A jUnit test suite for {@link BinaryPatriciaTrie}.
 *
 * @author --- Shaun Birch ----.
 */
public class StudentTests {


    @Test public void testEmptyTrie() {
        BinaryPatriciaTrie trie = new BinaryPatriciaTrie();

        assertTrue("Trie should be empty",trie.isEmpty());
        assertEquals("Trie size should be 0", 0, trie.getSize());

        assertFalse("No string inserted so search should fail", trie.search("0101"));

    }

    @Test public void testFewInsertionsWithSearch() {
        BinaryPatriciaTrie trie = new BinaryPatriciaTrie();

        assertTrue("String should be inserted successfully",trie.insert("00000"));
        assertTrue("String should be inserted successfully",trie.insert("00011"));
        assertFalse("Search should fail as string does not exist",trie.search("000"));

    }
    @Test public void testLongest() {
    	BinaryPatriciaTrie trie = new BinaryPatriciaTrie();
    	assertTrue(trie.insert("01"));
    	assertTrue(trie.insert("11"));
    	assertTrue(trie.getLongest().equals("11"));
    }
    
    @Test public void testDeleteAdvanced() {

    	BinaryPatriciaTrie trie = new BinaryPatriciaTrie();
    	assertTrue(trie.insert("00111011"));
    	assertTrue(trie.insert("010010"));
    	assertTrue(trie.insert("01010111"));
    	assertTrue(trie.insert("100"));
    	assertTrue(trie.insert("1001010"));
    	assertTrue(trie.insert("1011"));
    	assertTrue(trie.insert("111100011"));
    	assertTrue(trie.insert("111111000"));
    	assertTrue(trie.insert("1101010"));

    	assertTrue(trie.getSize() == 9);
    	assertTrue(trie.delete("1011"));

    	assertTrue(trie.getSize() == 8);
    	assertTrue(trie.delete("1101010"));

    	assertTrue(trie.getSize() == 7);
    	assertTrue(trie.delete("010010"));

    	assertTrue(trie.getSize() == 6);
    	
    	

    }
    
    @Test public void testIterator() {
    	BinaryPatriciaTrie trie = new BinaryPatriciaTrie();
    	assertTrue(trie.insert("11"));
    	assertTrue(trie.insert("00100"));
    	assertTrue(trie.insert("001"));
    	assertTrue(trie.insert("1101"));
    	assertTrue(trie.insert("111100110001"));
    	assertTrue(trie.insert("1111000"));
    	
    	Iterator<String> iter = trie.inorderTraversal();
    	assertTrue(iter.hasNext());
    	assertTrue(iter.next().equals("00100"));
    	assertTrue(iter.hasNext());
    	assertTrue(iter.next().equals("001"));
    	assertTrue(iter.hasNext());
    	assertTrue(iter.next().equals("1101"));
    	assertTrue(iter.hasNext());
    	assertTrue(iter.next().equals("11"));
    	assertTrue(iter.hasNext());
    	assertTrue(iter.next().equals("1111000"));
    	assertTrue(iter.hasNext());
    	assertTrue(iter.next().equals("111100110001"));
    	assertFalse(iter.hasNext());
    }
    
    @Test public void testDelete() {

    	BinaryPatriciaTrie trie = new BinaryPatriciaTrie();
    	assertTrue(trie.insert("11"));
    	assertTrue(trie.insert("00100"));
    	assertTrue(trie.insert("001"));
    	assertTrue(trie.insert("1101"));
    	assertTrue(trie.insert("111100110001"));
    	assertTrue(trie.insert("1111000"));
    	

    	assertTrue(trie.getSize() == 6);
    	assertTrue(trie.delete("11"));
    	assertTrue(trie.getSize() == 5);
    	assertTrue(trie.delete("001"));

    	assertTrue(trie.getSize() == 4);
    	assertTrue(trie.delete("111100110001"));

    	assertTrue(trie.getSize() == 3);
    	assertTrue(trie.delete("00100"));
    	assertTrue(trie.getSize() == 2);
    	assertTrue(trie.delete("1111000"));
    	assertTrue(trie.getSize() == 1);
    	assertTrue(trie.delete("1101"));
    	assertTrue(trie.getSize() == 0);
    	assertTrue(trie.isEmpty());



    }
    
    @Test public void testBadDeletes() {
    	BinaryPatriciaTrie trie = new BinaryPatriciaTrie();
    	assertTrue(trie.insert("11"));
    	assertTrue(trie.insert("00100"));
    	assertTrue(trie.insert("001"));
    	assertTrue(trie.insert("1101"));
    	assertTrue(trie.insert("111100110001"));
    	assertTrue(trie.insert("1111000"));
    	
    	assertFalse(trie.delete("1"));
    	assertFalse(trie.delete("10101010110"));
    	assertFalse(trie.delete("10000"));
    	assertFalse(trie.delete("0"));
    	assertFalse(trie.delete("001000"));
    	assertFalse(trie.delete("000"));
    	assertFalse(trie.delete("001011010"));
    	assertFalse(trie.delete("1100"));
    	assertFalse(trie.delete("11010"));
    	assertFalse(trie.delete("11011"));
    	assertFalse(trie.delete("11110"));
    	assertFalse(trie.delete("1111000000000"));
    	
    	
    }
    
    @Test public void testInsertion() {
    	BinaryPatriciaTrie trie = new BinaryPatriciaTrie();
    	assertTrue(trie.insert("11"));
    	assertTrue(trie.insert("00100"));
    	assertTrue(trie.insert("001"));
    	assertTrue(trie.insert("1101"));
    	assertTrue(trie.insert("111100110001"));
    	assertTrue(trie.insert("1111000"));
    	

    	
    	assertTrue(trie.insert("111100"));
    	assertTrue(trie.insert("11011"));
    	assertTrue(trie.insert("0010"));
    	assertTrue(trie.insert("111100110010"));

    	
    	assertTrue(trie.search("11"));
    	assertTrue(trie.search("00100"));
    	assertTrue(trie.search("001"));
    	assertTrue(trie.search("1101"));
    	assertTrue(trie.search("111100110001"));
    	assertTrue(trie.search("1111000"));
    	
    	assertTrue(trie.search("111100"));
    	assertTrue(trie.search("111100"));
    	assertTrue(trie.search("11011"));
    	assertTrue(trie.search("0010"));
    	assertTrue(trie.search("111100110010"));

    	
    }


    //testing isEmpty function
    @Test public void testFewInsertionsWithDeletion() {
        BinaryPatriciaTrie trie = new BinaryPatriciaTrie();

        trie.insert("000");
        trie.insert("001");
        trie.insert("011");
        trie.insert("1001");
        trie.insert("1");

        assertFalse("After inserting five strings, the trie should not be considered empty!", trie.isEmpty());
        assertEquals("After inserting five strings, the trie should report five strings stored.", 5, trie.getSize());

        trie.delete("0"); // Failed deletion; should affect exactly nothing.
        assertEquals("After inserting five strings and requesting the deletion of one not in the trie, the trie " +
                "should report five strings stored.", 5, trie.getSize());
        assertTrue("After inserting five strings and requesting the deletion of one not in the trie, the trie had some junk in it!",
                trie.isJunkFree());

        trie.delete("011"); // Successful deletion
        assertEquals("After inserting five strings and deleting one of them, the trie should report 4 strings.", 4, trie.getSize());
        assertTrue("After inserting five strings and deleting one of them, the trie had some junk in it!",
                trie.isJunkFree());
    }
}