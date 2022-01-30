import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class Entry<K, V>{
    int hash;
    K key;
    V value;

    public Entry(K key, V value){
        this.key = key;
        this.value = value;
        this.hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> otherEntry){
        if(otherEntry.hash != hash) return false;
        return key.equals(otherEntry.key);
    }

    @Override
    public String toString(){
        return key + " => " + value;
    }
}

public class HashTableSeperateChaining <K, V> implements Iterable<K>{

    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEAFULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K, V>>[] table;

    public HashTableSeperateChaining() {
        this(DEFAULT_CAPACITY, DEAFULT_LOAD_FACTOR);
    }

    public HashTableSeperateChaining(int capacity) {
        this(capacity, DEAFULT_LOAD_FACTOR);
    }

    public HashTableSeperateChaining(int capacity, double maxLoadFactor) {
        if(capacity < 0) throw new IllegalArgumentException("Illegal capacity");
        if(maxLoadFactor < 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
            throw new IllegalArgumentException("Illegal max load factor");

        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        this.threshold = (int) (this.capacity * this.maxLoadFactor);
        this.table = new LinkedList[capacity];
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int normalizeIndex(int keyHash){
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    public void clear(){
        Arrays.fill(table, null);
        this.size = 0;
    }

    public boolean containsKey(K key){
        return hasKey(key);
    }

    // Returns true/false depending on whether a key is in the hash table
    public boolean hasKey(K key){
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    // Insert, put and add all place a value in the hash-table
    public V put(K key, V value){
        return insert(key, value);
    }

    public V insert(K key, V value){
        if(key == null) throw new IllegalArgumentException("Null key");

        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = new Entry<>(key, value);
        return bucketInsertEntity(bucketIndex, entry);
    }

    public V remove(K key){
        if(key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    // Gets a key's values from the map and returns the value.
    // NOTE: returns null if the value is null AND also returns
    // null if the key does not exist
    public V get(K key){
        if(key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if(entry == null) return null;
        return entry.value;
    }

    // Finds and returns a particular entry in a given bucket if it exists, returns null otherwise
    public Entry<K, V> bucketSeekEntry(int bucketIndex, K key){
        if(key == null) return null;
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];

        if(bucket == null) return null;
        for(Entry<K, V> entry : bucket){
            if(key.equals(entry)) return entry;
        }
        return null;
    }

    // Insert an entry in a given bucket only if the entry does not already
    // exist in the given bucket. But if it does then update the entry value
    private V bucketInsertEntity(int bucketIndex, Entry<K, V> entry){
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if(bucket == null) table[bucketIndex] = bucket = new LinkedList<>();

        Entry<K, V> existingEntry = bucketSeekEntry(bucketIndex, entry.key);
        if(existingEntry == null){
            bucket.add(entry);
            if(++size > threshold) resizeTable();
            return null; // null indicates that there was no previous entry
        }else{
            V oldValue = existingEntry.value;
            existingEntry.value = entry.value;
            return oldValue;
        }
    }

    public V bucketRemoveEntry(int bucketIndex, K key){
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if(entry == null) return null;

        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        bucket.remove(entry);
        size--;
        return entry.value;
    }

    public void resizeTable(){
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];

        for(int i = 0; i < table.length; i++){
            if(table[i] != null){
                for(Entry<K, V> entry : table[i]){
                    int bucketIndex = normalizeIndex(entry.hash);
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    if(bucket == null) bucket = newTable[bucketIndex] = new LinkedList<>();
                    bucket.add(entry);
                }
                table[i].clear();
                table[i] = null;
            }
        }
        table = newTable;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}


























