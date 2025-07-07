/**
 * Hash Table implementation of the MapSet interface.
 * 
 * This class implements a hash table using separate chaining for collision resolution.
 * Features dynamic resizing to maintain optimal load factor and performance.
 * 
 * Time Complexity:
 * - Average case: O(1) for search, insert, delete
 * - Worst case: O(n) when all keys hash to same bucket
 * 
 * @author Hunter Broughton
 * @course CS231A
 * @date April 9, 2023
 * 
 * Compilation: javac HashMap.java
 */



// Import required library
import java.util.ArrayList;

/**
 * Hash Table implementation using separate chaining for collision resolution.
 * Provides constant-time average performance for basic operations.
 * 
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class HashMap<K, V> implements MapSet<K, V> {

    /**
     * Simple test method to verify basic functionality.
     * For comprehensive testing, see HashMapTest.java
     */
    public static void main(String[] args) {
        HashMap<String, Integer> myHasher = new HashMap<>();
        myHasher.put("apple", 10);
        myHasher.put("banana", 9);
        myHasher.remove("apple");
        System.out.println("Entry set: " + myHasher.entrySet());
        System.out.println("HashMap structure: " + myHasher);   
    }
    


    /**
     * Internal Node class for the hash table chains.
     * Each node contains a key-value pair and a reference to the next node in the chain.
     */
    private static class Node<K, V> extends KeyValuePair<K, V> {
        Node<K, V> next;

        /**
         * Creates a new node with the specified key, value, and next reference.
         * 
         * @param key the key for this node
         * @param value the value for this node  
         * @param next reference to the next node in the chain
         */
        public Node(K key, V value, Node<K, V> next) {
            super(key, value);
            this.next = next;
        }
    }

    
    // Instance fields
    private Node<K, V>[] buckets;    // Array of hash table buckets
    private int size;                // Number of key-value pairs
    private double maxLoadFactor;    // Maximum load factor before resizing

    /**
     * Constructs a new HashMap with default initial capacity of 16.
     */
    public HashMap() {
        this(16);
    }

    /**
     * Constructs a new HashMap with specified initial capacity and default load factor.
     * 
     * @param initialCapacity the initial capacity of the hash table
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, 0.75);
    }

    /**
     * Constructs a new HashMap with specified initial capacity and load factor.
     * 
     * @param initialCapacity the initial capacity of the hash table
     * @param maxLoadFactor the maximum load factor before resizing occurs
     */
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity, double maxLoadFactor) {
        this.buckets = (Node<K, V>[]) new Node[initialCapacity];
        this.size = 0;
        this.maxLoadFactor = maxLoadFactor;
    }

    //returns the capacity of the hashmap
    private int capacity(){
        return buckets.length;
    }

    //returns the hash value for a given key
    private int hash(K key){
        return Math.abs(key.hashCode() % capacity());
    }

   
    //puts a key value pair into the hash map and returns the value 
    //if the key pair is already in the hashmap, it will return the old value but replace it with the new value 
    public V put(K key, V value) {
        int index = hash(key);

        if(buckets[index] == null){
            buckets[index] = new Node<K, V>(key, value, null);
        }else{
            for(Node<K, V> curNode = buckets[index]; curNode!= null; curNode = curNode.next){
                if(curNode.getKey().equals(key)){
                    V oldVal = curNode.getValue();
                    curNode.setValue(value);
                    return oldVal;
                }
            }
            buckets[index] = new Node <K, V> (key, value, buckets[index]);
        }

        size++;
        if(size > capacity() * maxLoadFactor){
            resize(capacity() * 2);
        }
        return null;
    }


    /*
     * resizes the HashMap to a newCapacity 
     */
    private void resize(int newCapacity){
        Node<K, V>[] myBuckets = buckets;
        buckets = (Node<K, V>[]) new Node[newCapacity];
        size = 0;
        for(Node<K, V> curNode : myBuckets){

            while(curNode != null){
                put(curNode.getKey(), curNode.getValue());
                curNode = curNode.next;
            }
        }
    }

    /*
     * checks to see if the hashmap contains a given key
     */
    @Override
    public boolean containsKey(K key) {
        int index = hash(key);

        if(buckets[index] == null){
            return false;
        }else{
            for(Node<K, V> curNode = buckets[index]; curNode!= null; curNode = curNode.next){
                if(curNode.getKey().equals(key)){
                    return true;
                }
        }
        return false;
    }
    }

    /*
     * gets the value of a given key in the hashmap, returns null if the hashmap doesnt contain the key
     */
    @Override
    public V get(K key) {
        int index = hash(key);

        if(buckets[index] == null){
            return null;
        }else{
            for(Node<K, V> curNode = buckets[index]; curNode!= null; curNode = curNode.next){
                if(curNode.getKey().equals(key)){
                    return curNode.getValue();
                }
        }
        return null;
    }
    }

    /*
     * removes a key value pair from the hashmap and returns the value 
     * 
     * if the key is not found, null is returned
     */
    @Override
    public V remove(K key) {
        int index = hash(key);

        if(buckets[index] == null){
            return null;
        }else{
            Node <K, V> previousNode = null;
            Node<K, V> currNode = buckets[index];
            
            while(currNode != null){
                if(currNode.getKey().equals(key)){
                    if(previousNode == null){
                        buckets[index] = currNode.next;
                    }else{
                        previousNode.next = currNode.next;
                    }
                    size--;
                    if(size < .25 * maxLoadFactor * capacity()){
                        resize(capacity() / 2);
                    }
                    return currNode.getValue();
                }
                previousNode = currNode;
                currNode = currNode.next;
            }
        
        return null;
        }
    }

    /*
     * returns an arraylist of the keys in the hashMap
     */
    @Override
    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList();
        for(Node<K, V> curNode : buckets){

            while(curNode != null){
                keys.add(curNode.getKey());
                curNode = curNode.next;
            }
        }
        return keys;
    }

    /*
     * returns an arrayList of the values in the hashMap
     */
    @Override
    public ArrayList<V> values() {
        ArrayList<V> vals = new ArrayList();
        for(Node<K, V> curNode : buckets){

            while(curNode != null){
                vals.add(curNode.getValue());
                curNode = curNode.next;
            }
        }
        return vals;
    }

    /*
     * returns an arrayList of the key value pairs in the HashMap
     */
    @Override
    public ArrayList<MapSet.KeyValuePair<K, V>> entrySet() {
        ArrayList<KeyValuePair<K, V>> myList = new ArrayList();
        if(buckets == null){
            return myList;
        }

        for(Node<K, V> curNode : buckets){
            
            while(curNode!= null){
                myList.add(new KeyValuePair<>(curNode.getKey(), curNode.getValue()));
                curNode = curNode.next;
            }
        }
        return myList;
    }

    //returns the size of the hashmap
    @Override
    public int size() {
        return size;
    }

    /*
     * clears the contents of the hashmap
     */
    @Override
    public void clear() {
        size = 0;
        buckets = null;
    }

    /*
     * returns the maxDepth of the hashMap - aka, the length of the longest bucket
     */
    @Override
    public int maxDepth() {
        int maxDepth = 0;
        for(Node<K, V> curNode : buckets){
            int bucketDepth = 0;
            while(curNode!= null){
                bucketDepth++;
                curNode = curNode.next;
            }
            if(bucketDepth > maxDepth){
                maxDepth = bucketDepth;
            }
        }
        return maxDepth;
    }

    /*
     * returns a string representation of the Hashmap
     */
    public String toString(){
        String result = "{\n";
        boolean isFirst = true;
        int bucketCounter = 1;
        for(Node<K, V> curNode : buckets){
            result+= "Bucket " + bucketCounter + ":  ";
            while (curNode != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    result += ", ";
                }
                result += curNode.getKey() + "=" + curNode.getValue();
                curNode = curNode.next;
        }
        result += "\n";
        isFirst = true;
        bucketCounter++;
    }

    result += "}";
    return result;

    }

}
