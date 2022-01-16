import java.util.EmptyStackException;
import java.util.LinkedList;

public class Queue<T> {
    LinkedList<T> list;

    public Queue(){
        list = new LinkedList<>();
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void enqueue(T element){
        list.addLast(element);
    }

    public T dequeue(){
        if(isEmpty()) throw new EmptyStackException();
        return list.removeFirst();
    }

    public T peekFirst(){
        if(isEmpty()) throw new EmptyStackException();
        return list.peekFirst();
    }

    public void getAllElement(){
        if(isEmpty()) throw new EmptyStackException();

        for(T i : list){
            System.out.println(i);
        }
    }

    public static void main(String[] args){
        Queue<Integer> queue = new Queue<>();

        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(9);

        queue.dequeue();
        queue.getAllElement();
    }
}
