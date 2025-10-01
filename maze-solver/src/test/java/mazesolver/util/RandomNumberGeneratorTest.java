package mazesolver.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class RandomNumberGeneratorTest {

    @Test
    public void generateEventuallyProducesAllValuesInRange() {
        RandomNumberGenerator rng = new RandomNumberGenerator();

        boolean[] seen = new boolean[10];

        for (int i = 0; i < 10000; i++) {
            int value = rng.generate(10);
            seen[value] = true;
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(seen[i]);
        }
    }

    @Test
    public void generateReturnsValueWithinRange() {
        RandomNumberGenerator rng = new RandomNumberGenerator();

        for (int i = 0; i < 1000; i++) {
            int value = rng.generate(10);
            assertTrue(value >= 0);
            assertTrue(value < 10);
        }
    }

    @Test
    public void generateWithMaxOneAlwaysReturnsZero() {
        RandomNumberGenerator rng = new RandomNumberGenerator();

        for (int i = 0; i < 100; i++) {
            assertEquals(0, rng.generate(1));
        }
    }
}
