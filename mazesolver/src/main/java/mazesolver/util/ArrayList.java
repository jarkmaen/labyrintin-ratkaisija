package mazesolver.util;

/**
 * Expandable array data structure
 * 
 * @param <E> element type
 */
public class ArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void extend() {
        Object[] copy = new Object[size * 2];

        for (int i = 0; i < size; i++) {
            copy[i] = array[i];
        }

        array = copy;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is invalid.");
        }

        return (E) array[index];
    }

    public int size() {
        return size;
    }

    public void add(E element) {
        if (array.length == size) {
            extend();
        }

        array[size] = element;
        size++;
    }
}
