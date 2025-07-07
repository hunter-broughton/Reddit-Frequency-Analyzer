/**
 * Unit tests for AVL Tree implementation.
 * 
 * Tests the self-balancing functionality of the AVL Tree implementation,
 * specifically focusing on rotation operations and balance maintenance.
 * 
 * @author Hunter Broughton
 * @course CS231A
 * @date April 15, 2023
 * 
 * Usage:
 *   javac AVLTreeTest.java
 *   java -ea AVLTreeTest
 */

/**
 * Test suite for AVL Tree self-balancing functionality.
 */
public class AVLTreeTest {
    
    /**
     * Main method to run all test cases.
     * Uses Java assertions to verify correctness.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Running AVL Tree tests...");
        testPutAndRebalance();
        testRemoveAndRebalance();
        System.out.println("All AVL Tree tests passed!");
    }

    /**
     * Tests insertion operations and automatic rebalancing.
     * Verifies that the tree maintains balanced height after insertions.
     */
    public static void testPutAndRebalance() {
        AVLTree<String, Integer> tree = new AVLTree<>();

        // Insert values that would create imbalance in regular BST
        tree.put("20", 20);
        tree.put("15", 15);
        tree.put("25", 25);
        tree.put("10", 10);
        tree.put("30", 30);
        tree.put("5", 5);
        tree.put("7", 7);

        int rootHeight = tree.maxDepth();
        assert rootHeight == 4 : "Error: Expected height 4, found " + rootHeight;

        // Insert additional value that would cause imbalance
        tree.put("6", 6);

        // Verify tree remains balanced
        rootHeight = tree.maxDepth();
        assert rootHeight == 4 : "Error: Expected height 4 after rebalancing, found " + rootHeight;
    }

    /**
     * Tests deletion operations and automatic rebalancing.
     * Verifies that the tree maintains balanced height after deletions.
     */
    public static void testRemoveAndRebalance() {
        AVLTree<String, Integer> tree = new AVLTree<>();

        // Set up initial balanced tree
        tree.put("20", 20);
        tree.put("15", 15);
        tree.put("25", 25);
        tree.put("10", 10);
        tree.put("30", 30);
        tree.put("5", 5);
        tree.put("7", 7);
        tree.put("6", 6);

        // Remove node and verify balance is maintained
        tree.remove("30");
        int rootHeight = tree.maxDepth();
        assert rootHeight == 4 : "Error: Expected height 4 after removal, found " + rootHeight;

        // Remove another node and verify balance
        tree.remove("15");        
        rootHeight = tree.maxDepth();
        assert rootHeight == 4 : "Error: Expected height 4 after second removal, found " + rootHeight;
    }
}