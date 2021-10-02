package mazesolver.logic;

import mazesolver.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WallFollowerTest {

    Maze labyrintti;
    int n;

    @Before
    public void setup() {
        n = 50;
        labyrintti = new Maze(n);
        labyrintti.luoLabyrintti();
    }

    @Test
    public void algoritmiRatkaiseeLabyrintin() {
        WallFollower wallFollower = new WallFollower(labyrintti.haeVerkko(), n);
        wallFollower.ratkaise();
        List<Integer> polku = wallFollower.haePolku();
        assertTrue(polku.hae(polku.koko() - 1) == n * n - 1);
    }
}
