package mazesolver.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ListTest {

    @Test
    public void listaHakeeOikeanAlkion() {
        List<Integer> lista = new List<>();
        for (int i = 0; i < 10; i++) {
            lista.lisaa(i);
        }
        assertTrue(lista.hae(9) == 9);
    }
    
    @Test
    public void listaEiHyvaksyLiianSuurtaIndeksia() {
        List<Integer> lista = new List<>();
        lista.lisaa(1);
        assertTrue(lista.hae(1) == null);
    }
    
    @Test
    public void listaEiHyvaksyNegatiivistaIndeksia() {
        List<Integer> lista = new List<>();
        lista.lisaa(1);
        assertTrue(lista.hae(-1) == null);
    }
    
    @Test
    public void listaanVoiLisataAlkioita() {
        List<Integer> lista = new List<>();
        lista.lisaa(1);
        assertTrue(lista.koko() == 1);
    }

    @Test
    public void listaKasvattaaKapasiteettia() {
        List<Integer> lista = new List<>();
        for (int i = 0; i < 100; i++) {
            lista.lisaa(i);
        }
        assertTrue(lista.hae(99) == 99);
    }
}
