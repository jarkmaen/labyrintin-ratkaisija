package mazesolver.util;

/**
 * Last-In First-Out (LIFO) stack data structure implemented using a linked list
 * 
 * @param <E> element type
 */
public class Stack<E> {

    private Node<E> top;

    public E peek() {
        if (top == null) {
            throw new java.util.EmptyStackException();
        }

        return top.getData();
    }

    public E pop() {
        if (top == null) {
            throw new java.util.EmptyStackException();
        }

        E data = top.getData();
        top = top.getNext();

        return data;
    }

    public boolean empty() {
        return top == null;
    }

    public void push(E element) {
        Node<E> node = new Node<>(element);
        node.setNext(top);
        top = node;
    }
}
