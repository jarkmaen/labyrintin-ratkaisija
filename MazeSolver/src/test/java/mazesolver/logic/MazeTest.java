package mazesolver.logic;

import mazesolver.util.List;
import mazesolver.util.Stack;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MazeTest {

    List<List<Integer>> verkko;
    Maze labyrintti;
    int n;

    @Before
    public void setup() {
        Random random = new Random();
        n = random.nextInt(98) + 2;
        labyrintti = new Maze(n);
        labyrintti.luoLabyrintti();
        verkko = labyrintti.haeVerkko();
    }

    @Test
    public void labyrintinPolkuOnOikeanKokoinen() {
        assertTrue(labyrintti.haePolku().koko() == 2 * n * n - 1);
    }

    @Test
    public void generoituLabyrinttiVoidaanRatkaista() {
        boolean[] vierailtu = new boolean[n * n];
        Stack<Integer> pino = new Stack<>();
        pino.lisays(0);
        vierailtu[0] = true;
        while (!pino.onkoTyhja()) {
            int solmu = pino.kurkistus();
            pino.poisto();
            for (int i = 0; i < verkko.hae(solmu).koko(); i++) {
                int seuraavaSolmu = verkko.hae(solmu).hae(i);
                if (!vierailtu[seuraavaSolmu]) {
                    pino.lisays(seuraavaSolmu);
                    vierailtu[seuraavaSolmu] = true;
                }
            }
        }
        assertTrue(vierailtu[n * n - 1]);
    }
}
