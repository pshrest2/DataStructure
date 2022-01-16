import java.util.EmptyStackException;
import java.util.LinkedList;

public class Stack<T> {

    LinkedList<T> list = new LinkedList<>();

    public Stack(){ }

    public Stack(T firstElement){
        push(firstElement);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void push(T element){
        list.addLast(element);
    }

    public T pop(){
        if(isEmpty()) throw new EmptyStackException();
        return list.removeLast();
    }

    public T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return list.peekLast();
    }

    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        System.out.println(stack.pop());
    }
}
