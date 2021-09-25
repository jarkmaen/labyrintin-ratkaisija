package mazesolver.util;

/**
 * Pinorakenne
 * @param <E> E - Tyyppiparametri
 */
public class Stack<E> {

    private Node ylin;
    private int koko;

    public Stack() {
        this.ylin = null;
        this.koko = 0;
    }

    public void lisays(E e) {
        Node n = new Node(e);
        n.seuraava = ylin;
        ylin = n;
        koko++;
    }

    public void poisto() {
        if (onkoTyhja()) {
            return;
        }
        ylin = ylin.seuraava;
        koko--;
    }

    public E kurkistus() {
        if (onkoTyhja()) {
            return null;
        }
        return (E) ylin.e;
    }

    public boolean onkoTyhja() {
        return koko == 0;
    }

    private class Node<E> {

        private E e;
        private Node seuraava;

        public Node(E e) {
            this.e = e;
            this.seuraava = null;
        }
    }
}
