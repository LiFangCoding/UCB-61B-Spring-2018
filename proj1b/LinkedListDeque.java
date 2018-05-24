public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        public T val;
        public Node next;
        public Node prev;

        public Node() {

        }

        public Node(T t) {
            val = t;
        }
    }

    private Node sen;
    private int size;

    @Override
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

    @Override
    public void addLast(T item) {
        Node oldLast = sen.prev;
        Node newLast = new Node(item);
        oldLast.next = newLast;
        newLast.prev = oldLast;
        newLast.next = sen;
        sen.prev = newLast;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = sen.next;
        while (size != 0) {
            System.out.println(p.val.toString());
            p = p.next;
            size--;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = sen.next;
        sen.next = first.next;
        sen.next.prev = sen;
        size--;
        return first.val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        Node last = sen.prev;
        last.prev.next = sen;
        sen.prev = last.prev;
        size--;
        return last.val;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        Node p = sen;
        while (index >= 0) {
            p = p.next;
            index--;
        }
        return p.val;
    }

    public LinkedListDeque() {
        sen = new Node();
        sen.prev = sen;
        sen.next = sen;
        size = 0;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        Node n = sen.next;
        return getRecursive(index, n);
    }

    private T getRecursive(int index, Node n) {
        if (index == 0) {
            return n.val;
        }

        return getRecursive(index - 1, n.next);
    }
}
