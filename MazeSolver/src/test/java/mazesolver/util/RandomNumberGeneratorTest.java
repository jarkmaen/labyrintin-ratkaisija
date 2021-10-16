package mazesolver.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class RandomNumberGeneratorTest {

    @Test
    public void palauttaaLukujaNollanJaYlarajanValilta() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        boolean virhe = false;
        for (int i = 0; i < 100; i++) {
            int satunnaisluku = rng.generoi(3);
            if (satunnaisluku > 2 || satunnaisluku < 0) {
                virhe = true;
            }
        }
        assertFalse(virhe);
    }
}
