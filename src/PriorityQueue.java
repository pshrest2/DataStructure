import java.util.*;

/*
    min priority queue implementation using binary heap.
 */

public class PriorityQueue<T extends Comparable<T>> {
    private List<T> heap = null;
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    public int size(){
        return heap.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void clear(){
        heap.clear();
        map.clear();
    }

    public T peek(){
        if(isEmpty()) return null;
        return heap.get(0);
    }

    public T poll(){
        return removeAt(0);
    }

    public T removeAt(int index){
        if(isEmpty()) return null;

        int indexOfLastElement = size() - 1;
        T removedData = heap.get(indexOfLastElement);

        // swap the index with the last element of heap
        swap(index, indexOfLastElement);

        // remove the last element of heap
        heap.remove(indexOfLastElement);
        mapRemove(removedData, indexOfLastElement);

        T element = heap.get(index);

        // try swim
        sink(index);

        // if does not work, sink
        if(heap.get(index) == element) swim(index);

        return removedData;
    }

    public void sink(int i){
        // TODO: implement bottom down
    }

    public void swim(int i){
        // TODO: implement bottom up
    }

    public void swap(int i, int j){
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);

        mapSwap(elem_i, elem_j, i, j);
    }

    public void mapRemove(T value, int index){
        Set<Integer> set = map.get(value);
        set.remove(index);
        if(set.size() == 0) map.remove(value);
    }

    public void mapSwap(T val1, T val2, int val1Index, int val2Index){
        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        // swap val1Index of set1 with val2Index of set2
        set1.remove(val1Index);
        set2.remove(val2Index);

        set1.add(val2Index);
        set2.add(val1Index);
    }
}


























