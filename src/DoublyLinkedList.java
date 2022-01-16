public class DoublyLinkedList<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    public static class Node<T>{
        private T data;
        private Node<T> prev, next;
        public Node(T data, Node<T> prev, Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    // Empty the linked list
    public void clear(){
        Node<T> trav = head;
        while(trav != null){
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        head = tail = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(T element){
        addLast(element);
    }

    // add element at the end of the linked list
    public void addLast(T element){
        if(isEmpty()){
            head = tail = new Node<T>(element, null, null);
        }else{
            tail.next = new Node<T>(element, tail, null);;
            tail = tail.next;
        }
        size++;
    }

    // add element at the beginning of the linked list
    public void addFirst(T element){
        if(isEmpty()){
            head = tail = new Node<T>(element, null, null);
        }else{
            head.prev = new Node<T>(element, null, head);
            head = head.prev;
        }
        size++;
    }

    // add element at a specified index
    public void addAt(T element, int index) throws Exception{
        if(index < 0 || index > size) throw new Exception("Illegal Index");

        if(index == 0){
            addFirst(element);
        }else if(index == size){
            addLast(element);
        }

        Node<T> trav = head;
        for(int i = 0; i < index - 1; i++){
            trav = trav.next;
        }
        Node<T> newNode = new Node<T>(element, trav, trav.next);
        trav.next.prev = newNode;
        trav.next = newNode;
        size++;
    }

    public T peekFirst() {
        if(isEmpty()) throw new RuntimeException("List is empty");
        return head.data;
    }

    public T peekLast() {
        if(isEmpty()) throw new RuntimeException("List is empty");
        return tail.data;
    }

    public T removeFirst() {
        if(isEmpty()) throw new RuntimeException("List is empty");

        T data = head.data;
        head = head.next;

        if(isEmpty()) tail = null;
        else head.prev = null;

        size--;

        return data;
    }

    public T removeLast(){
        if(isEmpty()) throw new RuntimeException("List is empty");

        T data = tail.data;
        tail = tail.prev;

        if(isEmpty()) head = null;
        else tail.next = null;

        size--;
        return data;
    }

    private T remove(Node<T> node){

        if(node.prev == null) removeFirst();
        if(node.next == null) removeLast();

        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;

        node.data = null;
        node = node.prev = node.next = null;
        size--;

        return data;
    }

    public T removeAt(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException();

        if(index == 0) return removeFirst();

        if(index == size - 1) return removeLast();

        int i;
        Node<T> trav;
        if(index < size / 2){
            // search from the front of the list
            for(i = 0, trav = head; i < index; i++){
                trav = trav.next;
            }
        }else{
            // search from the back of the list
            for(i = size - 1, trav = tail; i >= 0; i--){
                trav = trav.prev;
            }
        }

        return remove(trav);
    }

    public boolean remove(Object obj){
        Node<T> trav;

        // Support searching for null
        if(obj == null){
            for(trav = head; trav != null; trav = trav.next){
                if(trav == null){
                    remove(trav);
                    return true;
                }
            }
        }else{
            for(trav = head; trav != null; trav = trav.next){
                if(obj.equals(trav.data)){
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    // Find index of a particular value in the linked list
    public int indexOf(Object obj){

        Node<T> trav;
        int i;

        if(obj == null){
            // Support searching for null
            for(trav = head, i = 0; trav != null; trav = trav.next, i++){
                if(trav.data == null) return i;
            }
        }else{
            // Search for non null
            for(trav = head, i = 0; trav != null; trav = trav.next, i++){
                if(obj.equals(trav.data)) return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);

        System.out.println(linkedList.indexOf(4));

        linkedList.remove(4);
        System.out.println(linkedList.indexOf(4));

    }
}
