package uvg.edu;

import java.util.Vector;

public class vHeap<E extends Comparable<E>> {
    private Vector<E> data;

    public vHeap() {
        data = new Vector<>();
    }

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

    public boolean isEmpty() {
        return data.isEmpty();
    }
}