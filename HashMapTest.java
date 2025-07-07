/**
 * Unit tests for HashMap implementation.
 * 
 * Tests all major functionality of the Hash Table implementation
 * including insertion, deletion, searching, and utility methods.
 * 
 * @author Hunter Broughton
 * @course CS231A
 * @date April 9, 2023
 * 
 * Usage:
 *   javac HashMapTest.java
 *   java -ea HashMapTest
 */

import java.util.ArrayList;

/**
 * Comprehensive test suite for HashMap implementation.
 */
public class HashMapTest {

    /**
     * Main method to run all test cases.
     * Uses Java assertions to verify correctness.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Running HashMap tests...");
        testSize();
        testClear();
        testMaxDepth();
        testToString();
        System.out.println("All HashMap tests passed!");
    }


    /**
     * Tests the size() method functionality.
     * Verifies size tracking during insertions and deletions.
     */
    private static void testSize() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        assert hashMap.size() == 0 : "testSize 1 failed";
        hashMap.put("apple", 1);
        assert hashMap.size() == 1 : "testSize 2 failed";
        hashMap.put("banana", 2);
        assert hashMap.size() == 2 : "testSize 3 failed";
        hashMap.remove("apple");
        assert hashMap.size() == 1 : "testSize 4 failed";
    }

    /**
     * Tests the clear() method functionality.
     * Verifies that all entries are removed and size is reset.
     */
    private static void testClear() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("apple", 1);
        hashMap.put("banana", 2);
        hashMap.put("orange", 3);
        assert hashMap.size() == 3 : "testClear 1 failed";
        hashMap.clear();
        assert hashMap.size() == 0 : "testClear 2 failed";
        assert hashMap.entrySet().isEmpty() : "testClear 3 failed";
    }

    /**
     * Tests the maxDepth() method functionality.
     * Verifies depth calculation for hash table buckets.
     */
    private static void testMaxDepth() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        assert hashMap.maxDepth() == 0 : "testMaxDepth 1 failed";
        hashMap.put("apple", 1);
        hashMap.put("banana", 2);
        hashMap.put("orange", 3);
        assert hashMap.maxDepth() >= 1 : "testMaxDepth 2 failed";
    }

    /**
     * Tests the toString() method functionality.
     * Verifies string representation contains expected entries.
     */
    private static void testToString() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("apple", 1);
        hashMap.put("banana", 2);
        hashMap.put("orange", 3);
        String result = hashMap.toString();
        assert result.contains("apple=1") : "testToString 1 failed";
        assert result.contains("banana=2") : "testToString 2 failed";
        assert result.contains("orange=3") : "testToString 3 failed";
    }
}
