package mazesolver.util;

/**
 * Listarakenne
 * @param <E> E - Tyyppiparametri
 */
public class List<E> {
    
    private Object[] alkiot;
    private int koko;
    
    public List() {
        this.alkiot = new Object[10];
        this.koko = 0;
    }
    
    public E hae(int i) {
        if (i >= koko || i < 0) {
            return null;
        }
        return (E) alkiot[i];
    }
    
    public void lisaa(E e) {
        if (alkiot.length == koko) {
            kasvata();
        }
        alkiot[koko++] = e;
    }
    
    public int koko() {
        return koko;
    }
    
    private void kasvata() {
        Object[] kopio = new Object[koko * 2];
        for (int i = 0; i < alkiot.length; i++) {
            kopio[i] = alkiot[i];
        }
        alkiot = kopio;
    }
}
