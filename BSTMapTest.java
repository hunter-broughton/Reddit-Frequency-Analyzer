/**
 * Unit tests for BSTMap implementation.
 * 
 * Tests all major functionality of the Binary Search Tree implementation
 * including insertion, deletion, searching, and utility methods.
 * 
 * @author Hunter Broughton
 * @course CS231A
 * @date April 9, 2023
 * 
 * Usage: 
 *   javac BSTMapTest.java
 *   java -ea BSTMapTest
 */

// Import required libraries
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Comprehensive test suite for BSTMap implementation.
 */
public class BSTMapTest {

    /**
     * Main method to run all test cases.
     * Uses Java assertions to verify correctness.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Running BSTMap tests...");
        testPut();
        testContainsKey();
        testGet();
        testRemove();
        testKeySet();
        testValues();
        testSize();
        testClear();
        testMaxDepth();
        System.out.println("All BSTMap tests passed!");
    }
    
    
    
    /**
     * Tests the put() method functionality.
     * Verifies insertion of new keys and updating of existing keys.
     */
    private static void testPut() {
        BSTMap<String, Integer> bst = new BSTMap<>();
        assert bst.put("apple", 1) == null : "testPut 1 failed";
        assert bst.put("banana", 2) == null : "testPut 2 failed";
        assert bst.put("orange", 3) == null : "testPut 3 failed";
        assert bst.put("apple", 4) == 1 : "testPut 4 failed"; // Should return old value
    }

    /**
     * Tests the containsKey() method functionality.
     * Verifies presence and absence of keys.
     */
    private static void testContainsKey() {
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("apple", 1);
        bst.put("banana", 2);
        bst.put("orange", 3);
        assert bst.containsKey("apple") : "testContainsKey 1 failed";
        assert bst.containsKey("banana") : "testContainsKey 2 failed";
        assert bst.containsKey("orange") : "testContainsKey 3 failed";
        assert !bst.containsKey("pear") : "testContainsKey 4 failed";
    }

    /**
     * Tests the get() method functionality.
     * Verifies retrieval of values for existing and non-existing keys.
     */
    private static void testGet() {
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("apple", 1);
        bst.put("banana", 2);
        bst.put("orange", 3);
        assert bst.get("apple") == 1 : "testGet 1 failed";
        assert bst.get("banana") == 2 : "testGet 2 failed";
        assert bst.get("orange") == 3 : "testGet 3 failed";
        assert bst.get("pear") == null : "testGet 4 failed";
    }

    /**
     * Tests the remove() method functionality.
     * Verifies removal of existing keys and handling of non-existing keys.
     */
    private static void testRemove() {
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("apple", 1);
        bst.put("banana", 2);
        bst.put("orange", 3);
        assert bst.remove("banana") == 2 : "testRemove 1 failed";
        assert !bst.containsKey("banana") : "testRemove 2 failed";
        assert bst.remove("pear") == null : "testRemove 3 failed";
    }
    

    /*
     * tests the keySet() method from BSTMap.java
     */
    private static void testKeySet() {
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("apple", 1);
        bst.put("banana", 2);
        bst.put("orange", 3);
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("apple", "banana", "orange"));
        assert bst.keySet().equals(expected) : "testKeySet failed";
    }
    

    /*
     * tests the values() method from BSTMap.java
     */
    private static void testValues() {
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("apple", 1);
        bst.put("banana", 2);
        bst.put("orange", 3);
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3));
        assert bst.values().equals(expected) : "testValues failed";
    }
    

    /*
     * tests the size() method from BSTMap.java
     */
    private static void testSize() {
        BSTMap<String, Integer> bst = new BSTMap<>();
        assert bst.size() == 0 : "testSize 1 failed";
        bst.put("apple", 1);
        assert bst.size() == 1 : "testSize 2 failed";
        bst.put("banana", 2);
        assert bst.size() == 2 : "testSize 3 failed";
        bst.remove("apple");
        assert bst.size() == 1 : "testSize 4 failed";
    }
    

    /*
     * tests the clear() method from BSTMap.java
     */
    private static void testClear() {
        BSTMap<String, Integer> bst = new BSTMap<>();
        bst.put("apple", 1);
        bst.put("banana", 2);
        bst.put("orange", 3);
        assert bst.size() == 3 : "testClear 1 failed";
        bst.clear();
        assert bst.size() == 0 : "testClear 2 failed";
        assert bst.entrySet().isEmpty() : "testClear 3 failed";
    }
    

    /*
     * tests the maxDepth() method from BSTMap.java
     */
    private static void testMaxDepth() {
        BSTMap<String, Integer> bst = new BSTMap<>();
        assert bst.maxDepth() == 0 : "testMaxDepth 1 failed";
        bst.put("apple", 1);
        assert bst.maxDepth() == 1 : "testMaxDepth 2 failed";
        bst.put("banana", 2);
        assert bst.maxDepth() == 2 : "testMaxDepth 3 failed";
        bst.put("orange", 3);
        assert bst.maxDepth() == 3 : "testMaxDepth 4 failed";
    }
    
}

