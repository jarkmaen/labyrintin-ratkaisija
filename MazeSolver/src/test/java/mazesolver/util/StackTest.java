package mazesolver.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void pinoonVoiLisataAlkioita() {
        Stack<Integer> pino = new Stack<>();
        pino.lisays(1);
        assertFalse(pino.onkoTyhja());
    }

    @Test
    public void pinoPoistaaYlimmanAlkion() {
        Stack<Integer> pino = new Stack<>();
        pino.lisays(1);
        pino.lisays(2);
        assertTrue(pino.kurkistus() == 2);
        pino.poisto();
        assertTrue(pino.kurkistus() == 1);
    }
    
    @Test
    public void tyhjastaPinostaEiVoiPoistaa() {
        Stack<Integer> pino = new Stack<>();
        assertTrue(pino.onkoTyhja());
        pino.poisto();
        assertTrue(pino.onkoTyhja());
        pino.lisays(1);
        assertFalse(pino.onkoTyhja());
    }
    
    @Test
    public void pinonKurkistusPalauttaaOikeanAlkion() {
        Stack<Integer> pino = new Stack<>();
        for (int i = 0; i < 100; i++) {
            pino.lisays(i);
        }
        assertTrue(pino.kurkistus() == 99);
    }
    
    @Test
    public void tyhjastaPinostaEiVoiKurkistaa() {
        Stack<Integer> pino = new Stack<>();
        assertTrue(pino.kurkistus() == null);
    }
}
