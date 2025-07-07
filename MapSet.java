/**
 * Interface for a data structure that maps a key to a value, like a
 * Python dictionary would. This is a subset of the Java Map interface.
 * 
 * Javadocs taken directly from their corresponding methods in the Java Map interface.
 *
 * @author Hunter Broughton
 * @course CS231A
 * @date April 2023
 * 
 * Original interface design by srtaylor, bmaxwell, mbender
 */
import java.util.ArrayList;

/**
 * Map interface for key-value pair data structures.
 * Supports basic map operations including put, get, remove, and iteration.
 * 
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface MapSet<K, V> {

    /**
     * Inner class representing a key-value pair.
     * Used to return entries from the map data structure.
     * 
     * @param <K> the type of the key
     * @param <V> the type of the value
     */
    public class KeyValuePair<K, V> {

        private K key;
        private V value;

        /**
         * Creates a new key-value pair.
         * 
         * @param k the key
         * @param v the value
         */
        public KeyValuePair(K k, V v) {
            key = k;
            value = v;
        }

        /**
         * Returns the key of this key-value pair.
         * 
         * @return the key
         */
        public K getKey() {
            return key;
        }

        /**
         * Sets the value of this key-value pair.
         * 
         * @param v the new value
         */
        public void setValue(V v) {
            value = v;
        }

        /**
         * Returns the value of this key-value pair.
         * 
         * @return the value
         */
        public V getValue() {
            return value;
        }

        /**
         * Returns a string representation of this key-value pair.
         * 
         * @return string in format "<key -> value>"
         */
        public String toString() {
            return "<" + key + " -> " + value + ">";
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced. Does nothing if {@code value} is {@code null}.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    public V put(K key, V value);

    /**
     * Returns {@code true} if this map contains a mapping for the
     * specified key to a value.
     *
     * @param key The key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified
     *         key to a value.
     */
    public boolean containsKey(K key);

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     * 
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
    public V get(K key);

    /**
     * Removes the mapping for a key from this map if it is present. More formally,
     * if this map contains a mapping from key {@code k} to value {@code v} such
     * that {@code key.equals(k)}, that mapping is removed. (The map can contain at
     * most one such mapping.)
     * 
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     */
    public V remove(K key);

    /**
     * Returns an ArrayList of all the keys in the map.
     * 
     * @return an ArrayList of all the keys in the map.
     */
    public ArrayList<K> keySet();

    /**
     * Returns an ArrayList of all the values in the map in the same order as the
     * keys as returned by keySet().
     * 
     * @return an ArrayList of all the values in the map in the same order as the
     *         keys as returned by keySet().
     */
    public ArrayList<V> values();

    /**
     * Returns an ArrayList of each {@code KeyValuePair} in the map in the same
     * order as the keys as returned by keySet().
     * 
     * @return an ArrayList of each {@code KeyValuePair} in the map in the same
     *         order as the keys as returned by keySet().
     */
    public ArrayList<KeyValuePair<K, V>> entrySet();

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    public int size();

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear();

    /**
     * Returns the maximal number of iterations to find any particular element of
     * the Map. For tree structures, this represents the height. For hash tables,
     * this represents the maximum bucket size.
     * 
     * @return the maximum depth/height of the data structure
     */
    public int maxDepth();
}