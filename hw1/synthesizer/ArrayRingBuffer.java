package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    public void enqueue(T x) {
        if (fillCount == capacity) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        this.fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T res = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity;
        fillCount--;
        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }

        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator<T> implements Iterator<T> {
        private int i = first;
        private int iterstep = 0;

        @Override
        public boolean hasNext() {
            return iterstep < fillCount;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }

            T res = (T) rb[i];
            i = (i + 1) % capacity;
            iterstep++;
            return res;

        }
    }

}
