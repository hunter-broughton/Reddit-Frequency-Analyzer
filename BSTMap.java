/**
 * Binary Search Tree implementation of the MapSet interface.
 * 
 * This class implements a binary search tree data structure for storing
 * key-value pairs. The tree maintains sorted order based on the keys,
 * allowing for efficient search, insertion, and deletion operations.
 * 
 * Time Complexity:
 * - Average case: O(log n) for search, insert, delete
 * - Worst case: O(n) for unbalanced trees
 * 
 * @author Hunter Broughton
 * @course CS231A  
 * @date April 1, 2023
 * 
 * Compilation: javac BSTMap.java
 */


//import required libraries
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Binary Search Tree implementation of the MapSet interface.
 * Stores key-value pairs in a sorted tree structure for efficient operations.
 * 
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class BSTMap<K, V> implements MapSet<K, V> {

    /**
     * Simple test method to verify basic functionality.
     * For comprehensive testing, see BSTMapTest.java
     */
    public static void main(String[] args) {
        BSTMap<String, Integer> myTree = new BSTMap<>();

        // Testing put and toString()
        myTree.put("10", 10);
        myTree.put("30", 30);
        myTree.put("20", 20);
        myTree.put("40", 40);

        System.out.println("Initial tree:");
        System.out.println(myTree.toString());

        myTree.remove("10");

        System.out.println("\nAfter removing '10':");
        System.out.println(myTree.toString());
    }

    /**
     * Internal Node class for the binary search tree.
     * Each node contains a key-value pair and references to left and right children.
     */
    private static class Node<K, V> extends KeyValuePair<K, V> {
        Node<K, V> left, right;

        /**
         * Creates a new node with the specified key and value.
         * 
         * @param key the key for this node
         * @param value the value for this node
         */
        public Node(K key, V value) {
            super(key, value);
            left = null;
            right = null;
        }
    }

    // Instance fields for BST Map
    private Node<K, V> root;        // Root node of the tree
    private int size;               // Number of key-value pairs in the tree
    private Comparator<K> comparator; // Comparator for key comparison

    /**
     * Constructs a new BSTMap with the specified comparator.
     * 
     * @param comparator the comparator to use for key comparison.
     *                  If null, keys must implement Comparable
     */
    public BSTMap(Comparator<K> comparator) {
        size = 0;
        root = null;
        if (comparator != null) {
            this.comparator = comparator;
        } else {
            // Default comparator for Comparable keys
            this.comparator = new Comparator<K>() {
                @Override
                public int compare(K o1, K o2) {
                    return ((Comparable<K>) o1).compareTo(o2);
                }
            };
        }
    }

    /**
     * Constructs a new BSTMap with the default comparator.
     * Keys must implement Comparable interface.
     */
    public BSTMap() {
        this(null);
    }



    /**
     * Associates the specified value with the specified key in this map.
     * If the key already exists, the old value is replaced.
     * 
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with key, or null if no mapping existed
     */
    @Override
    public V put(K key, V value) {
        if (size == 0) {
            root = new Node<>(key, value);
            size++;
            return null;
        } else {
            return put(key, value, root);
        }
    }

    /**
     * Recursive helper method for put operation.
     * Finds the correct position for the new key-value pair in the tree.
     * 
     * @param key the key to insert
     * @param value the value to insert
     * @param curNode the current node being examined
     * @return the previous value if key existed, null otherwise
     */
    private V put(K key, V value, Node<K, V> curNode) {
        if (comparator.compare(key, curNode.getKey()) < 0) {
            // Key is smaller, go to left subtree
            if (curNode.left == null) {
                curNode.left = new Node<>(key, value);
                size++;
                return null;
            } else {
                return put(key, value, curNode.left);
            }
        } else if (comparator.compare(key, curNode.getKey()) > 0) {
            // Key is larger, go to right subtree
            if (curNode.right == null) {
                curNode.right = new Node<>(key, value);
                size++;
                return null;
            } else {
                return put(key, value, curNode.right);
            }
        } else {
            // Key already exists, update value
            V oldVal = curNode.getValue();
            curNode.setValue(value);
            return oldVal;
        }
    }


    /**
     * Returns true if this map contains a mapping for the specified key.
     * 
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(K key) {
        V value = get(key);
        return value != null;
    }

    /**
     * Returns the value to which the specified key is mapped.
     * 
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if no mapping exists
     */
    @Override
    public V get(K key) {
        return get(key, root);
    }

    /**
     * Recursive helper method for get operation.
     * Searches the tree for the specified key.
     * 
     * @param key the key to search for
     * @param cur the current node being examined
     * @return the value associated with the key, or null if not found
     */
    private V get(K key, Node<K, V> cur) {
        if (cur == null) {
            return null;
        }
        
        int compareResult = comparator.compare(key, cur.getKey());
        if (compareResult < 0) {
            return get(key, cur.left);
        } else if (compareResult > 0) {
            return get(key, cur.right);
        } else {
            return cur.getValue();
        }
    }

    /*
     * removes a key value pair from the BST and returns the value of the pairing that is removed
     * returns null if the key value pairs is not found within the tree
     */
    public V remove(K key) {
        Node<K, V> toRemoveParent = null;
        Node<K, V> toRemove = root;

        while(toRemove != null){
            if(comparator.compare(key, toRemove.getKey()) < 0){
                toRemoveParent = toRemove;
                toRemove = toRemove.left;
            } else if(comparator.compare(key, toRemove.getKey()) > 0){
                toRemoveParent = toRemove;
                toRemove = toRemove.right;
            }else{
                break;
            }
        }

        if(toRemove == null){
            return null;
        }

        V value = toRemove.getValue();

        handleReplacement(toRemove, toRemoveParent);

        size--;

        return value;

    }


    /*
     * method that handles the replacement of nodes within the binary search tree
     * for when you remove from the binary search tree
     */
    public void handleReplacement(Node<K, V> toDelete, Node<K, V> toDeleteParent){
        Node<K, V> replacement;
        if(toDelete.left == null){
            replacement = toDelete.right;
        }else if(toDelete.right == null){
            replacement = toDelete.left;
        }else{
            Node<K, V> parent = toDelete;
            replacement = toDelete.right;
            while(replacement.left!= null){
                parent = replacement;
                replacement = replacement.left;
            }

            if(parent != toDelete){
                parent.left = replacement.right;
            }
            else{
                parent.right = replacement.right;
            }

            replacement.left = toDelete.left;
            replacement.right = toDelete.right;
        }

        if(toDeleteParent == null){
            root = replacement;
        }else if (toDeleteParent.left == toDelete){
            toDeleteParent.left = replacement;
        }else{
            toDeleteParent.right = replacement;
        }

    }

    
    /*
     * returns an arraylist of the keys in the binary search tree
     */
    public ArrayList<K> keySet() {
        ArrayList keys = new ArrayList();
        keySet(root, keys);
        return keys;
    }


    //recursive method that gathers the keys in the binary search tree
    public void keySet(Node<K, V> cur, ArrayList<K> output){
        if(cur == null){
            return;
        }

        keySet(cur.left, output);
        output.add(cur.getKey());
        keySet(cur.right, output);
    }

    //returns an arraylist of the values in the binary search tree
    public ArrayList<V> values() {
        ArrayList vals = new ArrayList();
        values(root, vals);
        return vals;
    }

    //recursive method that gathers the values in the binary search tree
    public void values(Node<K, V> cur, ArrayList<V> output){
        if(cur == null){
            return;
        }

        values(cur.left, output);
        output.add(cur.getValue());
        values(cur.right, output);
    }

    
    //returns an arraylist of the keyvalue pairs in the binary search tree
    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet() {
        ArrayList pairs = new ArrayList();
        entrySet(root, pairs);
        return pairs;
    }

    //recursive method that gathers the key value pairs in the binary search tree
    public void entrySet(Node<K, V> cur, ArrayList<MapSet.KeyValuePair<K, V>> output){
        if(cur == null){
            return;
        }

        entrySet(cur.left, output);
        K key = cur.getKey();
        V val = cur.getValue();
        output.add(new KeyValuePair<>(key, val));
        entrySet(cur.right, output);

    }

    //returns the size of the binary search tree
    public int size() {
        return size(root);
    }

    //recursive method that calculates the size of the tree
    public int size(Node<K, V> cur){
        if(cur == null){
            return 0;
        }
        int sizeLeft = size(cur.left);
        int sizeRight = size(cur.right);
        return 1 + sizeLeft + sizeRight;
    }

    //clears the entire tree
    public void clear() {
        this.size = 0;
        root = null;
    }

    
    //returns the maxdepth of the BST
    public int maxDepth() {
        return maxDepth(root);
    }

    //recursive method that calculates the maxdepth of the bst
    public int maxDepth(Node<K, V> cur){
        if (cur == null){
            return 0;
        }
        int depthLeft = maxDepth(cur.left);
        int depthRight = maxDepth(cur.right);
        return 1 + Math.max(depthLeft, depthRight);
    }

    //returns a string representation of the BST
    public String toString(){
        if(size == 0){
            return "Empty Tree";
        }
        else{
            return toString(root,0,"root");
        }
    }

    //builds the stringrepresentation to be returned if the BST contains nodes
    private String toString(Node<K, V> curNode, int depth, String direction) {
        if (curNode == null) {
            return "";
        }
    
        String myself = curNode.toString();
    
        String left = toString(curNode.left, depth + 1, "left");
        String right = toString(curNode.right, depth + 1, "right");
    
        String result = "";
        if (!right.isEmpty()) {
            result += right + '\n';
        }
        result += "  ".repeat(depth) + direction + ": " + myself;
        if (!left.isEmpty()) {
            result += '\n' + left;
        }
        return result;
    }
}
    
    

