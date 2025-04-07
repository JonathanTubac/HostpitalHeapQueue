/*Programmed by Jonathan Tubac 24484 */
package uvg.edu;

import java.util.Vector;

/**
 * vHeap is a generic implementation of a min-heap data structure.
 * It uses a {@link Vector} to store elements and maintains the heap property
 * where the smallest element is always at the root.
 * 
 * @param <E> The type of elements stored in the heap. Must implement {@link Comparable}.
 */
public class vHeap<E extends Comparable<E>> {
    private Vector<E> data;

    /**
     * Constructs an empty vHeap.
     */
    public vHeap() {
        data = new Vector<>();
    }

    /**
     * Adds an element to the heap and restores the heap property by percolating up.
     * 
     * @param item The element to add to the heap.
     */
    public void add(E item) {
        data.add(item);
        int current = data.size() - 1;

        // Percolate up
        while (current > 0) {
            int parent = (current - 1) / 2;
            if (data.get(current).compareTo(data.get(parent)) < 0) {
                // Swap current with parent
                E temp = data.get(current);
                data.set(current, data.get(parent));
                data.set(parent, temp);
                current = parent;
            } else {
                break;
            }
        }
    }

    /**
     * Removes and returns the smallest element (root) from the heap.
     * Restores the heap property by percolating down.
     * 
     * @return The smallest element in the heap.
     * @throws IllegalStateException If the heap is empty.
     */
    public E remove() {
        if (data.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        // Get the root element (highest priority)
        E root = data.get(0);

        // Replace root with the last element
        E last = data.remove(data.size() - 1);
        if (!data.isEmpty()) {
            data.set(0, last);

            // Percolate down
            int current = 0;
            while (true) {
                int leftChild = 2 * current + 1;
                int rightChild = 2 * current + 2;
                int smallest = current;

                if (leftChild < data.size() && data.get(leftChild).compareTo(data.get(smallest)) < 0) {
                    smallest = leftChild;
                }
                if (rightChild < data.size() && data.get(rightChild).compareTo(data.get(smallest)) < 0) {
                    smallest = rightChild;
                }

                if (smallest != current) {
                    // Swap current with smallest child
                    E temp = data.get(current);
                    data.set(current, data.get(smallest));
                    data.set(smallest, temp);
                    current = smallest;
                } else {
                    break;
                }
            }
        }

        return root;
    }

    /**
     * Checks if the heap is empty.
     * 
     * @return {@code true} if the heap is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }
}