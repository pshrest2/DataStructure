import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
    min priority queue implementation using binary heap.
 */

public class PriorityQueue<T extends Comparable<T>> {
    private List<T> heap = null;
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    public PriorityQueue(T[] elements) {
        int heapSize = elements.length;
        heap = new ArrayList<T>(heapSize);

        // Place all elements in the heap
        for (int i = 0; i < heapSize; i++) {
            mapAdd(elements[i], i);
            heap.add(elements[i]);
        }

        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--)
            sink(i);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        heap.clear();
        map.clear();
    }

    public T peek() {
        if (isEmpty())
            return null;
        return heap.get(0);
    }

    public T poll() {
        return removeAt(0);
    }

    public T removeAt(int index) {
        if (isEmpty())
            return null;

        int indexOfLastElement = size() - 1;
        T removedData = heap.get(index);

        // swap the index with the last element of heap
        swap(index, indexOfLastElement);

        // remove the last element of heap
        heap.remove(indexOfLastElement);
        mapRemove(removedData, indexOfLastElement);

        T element = heap.get(index);

        // try swim
        sink(index);

        // if does not work, sink
        if (heap.get(index) == element)
            swim(index);

        return removedData;
    }

    public boolean remove(T element) {
        Integer index = mapGet(element);
        if (index != null)
            removeAt(index);
        return index != null;
    }

    // index k is the root of the heap
    public boolean isMinHeap(int k) {
        int heapSize = heap.size();
        if (k >= heapSize)
            return true;

        int leftIndex = 2 * k + 1;
        int rightIndex = 2 * k + 2;

        if (leftIndex < heapSize && !less(k, leftIndex))
            return false;
        if (rightIndex < heapSize && !less(k, rightIndex))
            return false;

        return isMinHeap(leftIndex) && isMinHeap(rightIndex);
    }

    public Integer mapGet(T key) {
        TreeSet<Integer> indexes = map.get(key);
        if (indexes != null)
            return indexes.last();
        return null;
    }

    public void mapAdd(T key, int value) {
        if (map.containsKey(key)) {
            TreeSet<Integer> set = map.get(key);
            set.add(value);
            map.put(key, set);
        } else {
            TreeSet<Integer> set = new TreeSet<>();
            set.add(value);
            map.put(key, set);
        }
    }

    public void sink(int i) {
        int heapSize = heap.size();
        while (true) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;

            int smallest = leftIndex;

            if (rightIndex < heapSize && less(rightIndex, leftIndex))
                smallest = rightIndex;

            if (smallest >= heapSize || less(i, smallest))
                break;
            swap(i, smallest);
            i = smallest;
        }
    }

    private boolean less(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        if (elem_i.compareTo(elem_j) <= 0)
            return true;
        else
            return false;
    }

    public void swim(int i) {
        int parent = (i - 1) / 2;

        while (i > 0 && less(parent, i)) {
            swap(parent, i);
            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);

        mapSwap(elem_i, elem_j, i, j);
    }

    public void mapRemove(T value, int index) {
        Set<Integer> set = map.get(value);
        set.remove(index);
        if (set.size() == 0)
            map.remove(value);
    }

    public void mapSwap(T val1, T val2, int val1Index, int val2Index) {
        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        // swap val1Index of set1 with val2Index of set2
        set1.remove(val1Index);
        set2.remove(val2Index);

        set1.add(val2Index);
        set2.add(val1Index);
    }

    public static void main(String[] args) {
        Integer[] elements = new Integer[] { 5, 7, 8, 2, 9, 5, 3, 1 };
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(elements);

        System.out.println(minHeap.poll());
        System.out.println(minHeap.peek());
    }
}
