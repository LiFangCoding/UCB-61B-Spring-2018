public class LinkedListDeque<T> {
    private class Node {
        public T val;
        public Node next;
        public Node prev;

        public Node(T t){
            val = t;
        }
    }

    private Node sen;
    private int size;

    public LinkedListDeque(T t){
        sen = new Node(t);
        sen.prev = sen;
        sen.next = sen;
        size = 0;
    }
    //这个用一个双向sentinel指针的方法, 还是非常叼的.
    // invariant: sen的next是first, prev是last.
    public void addFirst(T item) {
        Node newFirst = new Node(item);
        Node oldFirst = sen.next;
        oldFirst.prev = newFirst;
        sen.next = newFirst;
        newFirst.prev = sen;
        newFirst.next = oldFirst;
        size++;
    }

    public void addLast(T item) {
        Node oldLast = sen.prev;
        Node newLast = new Node(item);
        oldLast.next = newLast;
        newLast.prev = oldLast;
        newLast.next = sen;
        sen.prev = newLast;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size() {
        return size;
    }
    public void printDeque() {

    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = sen.next;
        sen.next = first.next;
        first.next.prev = sen;
        return first.val;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node last = sen.prev;
        last.prev.next = sen;
        sen.prev = last.prev;
        return last.val;
    }

    public T get(int index) {

    }

    public LinkedListDeque() {

    }

    public T getRecursive(int index){

    }
}
