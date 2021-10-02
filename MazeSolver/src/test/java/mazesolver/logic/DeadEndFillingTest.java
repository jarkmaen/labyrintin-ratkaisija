package mazesolver.logic;

import mazesolver.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeadEndFillingTest {

    List<List<Integer>> verkko;
    Maze labyrintti;
    int n;

    @Before
    public void setup() {
        n = 50;
        labyrintti = new Maze(n);
        labyrintti.luoLabyrintti();
        verkko = labyrintti.haeVerkko();
    }

    @Test
    public void vainYksiPolkuJaaJaljelle() {
        DeadEndFilling deadEndFilling = new DeadEndFilling(labyrintti.haeVerkko(), n);
        deadEndFilling.ratkaise();
        int solmu = 0;
        int edellinenSolmu = -1;
        while (solmu != n * n - 1) {
            for (int i = 0; i < verkko.hae(solmu).koko(); i++) {
                int naapuri = verkko.hae(solmu).hae(i);
                if (!deadEndFilling.suljettu[naapuri] && edellinenSolmu != naapuri) {
                    edellinenSolmu = solmu;
                    solmu = naapuri;
                }
            }
        }
        assertTrue(solmu == n * n - 1);
    }
}
