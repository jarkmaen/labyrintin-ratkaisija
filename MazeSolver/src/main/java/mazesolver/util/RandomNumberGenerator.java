package mazesolver.util;

/**
 * Efficient Linear Congruential Generator (LCG) implementation
 * LCG formula: X(n+1) = (a * X(n) + c) mod m
 */
public class RandomNumberGenerator {

    private static final long INCREMENT = 11;
    private static final long MODULUS = 281474976710656L;
    private static final long MULTIPLIER = 25214903917L;

    private long seed;

    public RandomNumberGenerator() {
        seed = System.currentTimeMillis() & (MODULUS - 1);
    }

    /**
     * Generates the next pseudorandom number in the range [0, max-1]
     * 
     * @param max the upper bound (exclusive)
     */
    public int generate(int max) {
        long nextX = (MULTIPLIER * seed + INCREMENT) % MODULUS;
        long scaled = nextX % max;
        seed = nextX;

        if (scaled < 0) {
            scaled += max;
        }

        return (int) scaled;
    }
}
