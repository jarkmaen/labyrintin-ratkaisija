package mazesolver.util;

/**
 * Näennäissatunnaislukugeneraattori
 * https://en.wikipedia.org/wiki/Linear_congruential_generator
 */
public class RandomNumberGenerator {

    private final long modulus = 281474976710656L;
    private final long multiplier = 25214903917L;
    private final long increment = 11;
    private final long seed = System.currentTimeMillis();
    
    private List<Long> numerot;

    public RandomNumberGenerator() {
        this.numerot = new List<>();
        numerot.lisaa(seed);
    }
    
    /**
     * Generoi satunnaisen luvun 0...yläraja-1 väliltä
     * @param ylaraja - Satunnaisenluvun yläraja
     */
    public int generoi(int ylaraja) {
        long luku = (multiplier * numerot.hae(numerot.koko() - 1) + increment) % modulus;
        numerot.lisaa(luku);
        long satunnaisluku = luku % ylaraja;
        return (int) (satunnaisluku < 0 ? -satunnaisluku : satunnaisluku);
    }
}
