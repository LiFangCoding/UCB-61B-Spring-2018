public class ArrayDeque<T> implements Deque<T> {
    T[] items;
    // the items in the array
    int size;
    // record the length or capacity of circular array
    int len;
    int nextFirst;
    int nextLast;

    @Override
    public void addFirst(T item) {
        if (size == len) {
            resize(len * 2);
        }

            items[nextFirst] = item;
            nextFirst = (nextFirst - 1 + len) % len;
            size++;

    }

    @Override
    public void addLast(T item) {
        if (size == len) {
            resize(len * 2);
        }

            items[nextLast] = item;
            nextLast = (nextLast + 1) % len;
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
        int iter = nextFirst;
        int n = size;
        while (n > 0) {
            iter = (iter + 1) % len;
            System.out.print(items[iter].toString());
            n--;
        }
    }

    @Override
    public T removeFirst() {
        if (len >= 16 && ((double) size / len <= 0.25)) {
            resize(len / 2);
        }
        if (size == 0) {
            return null;
        }
        int first = (nextFirst + 1) % len;
        T res = items[first];
        items[first] = null;
        nextFirst = first;
        size--;
        return res;
    }

    @Override
    public T removeLast() {
        if (len >= 16 && ((double) size / len <= 0.25)) {
            resize(len / 2);
        }
        if (size == 0) {
            return null;
        }

        int last = (nextLast - 1 + len) % len;
        T res = items[last];
        items[last] = null;
        nextLast = last;
        size--;
        return res;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int iter = nextFirst;
        for (int i = 0; i <= index; i++) {
            iter = (iter + 1) % len;
        }
        return items[iter];
    }

    public ArrayDeque() {
        len = 8;
        items = (T[]) new Object[len];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    // risize到一定的capacity或者说len.
    // capacity is the cap of newitems
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int iter = nextFirst;
        int n = size;
        int i = 0;

        while (n > 0) {
            iter = (iter + 1) % len;
            newItems[i++] = items[iter];
            n--;
        }

        len = capacity;
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }


}
