package mazesolver.util;

import mazesolver.logic.DeadEndFilling;
import mazesolver.logic.Maze;
import mazesolver.logic.WallFollower;

/**
 * Suorituskykytesti
 */
public class PerformanceTest {

    private Maze labyrintti1;
    private Maze labyrintti2;
    private Maze labyrintti3;
    private Maze labyrintti4;
    private Maze labyrintti5;
    private Maze labyrintti6;

    public PerformanceTest() {
        // Luodaan labyrintin valmiiksi ennen testien aloittamista
        
        labyrintti1 = new Maze(100); // 100x100
        labyrintti1.luoLabyrintti();

        labyrintti2 = new Maze(250); // 250x250
        labyrintti2.luoLabyrintti();

        labyrintti3 = new Maze(500); // 500x500
        labyrintti3.luoLabyrintti();

        labyrintti4 = new Maze(750); // 750x750
        labyrintti4.luoLabyrintti();

        labyrintti5 = new Maze(1000); // 1000x1000
        labyrintti5.luoLabyrintti();
        
        labyrintti6 = new Maze(2500); // 2500x2500
        labyrintti6.luoLabyrintti();
    }

    /*
    * Wall follower algoritmin tehokkuustesti
    */
    public String wallFollowerTehokkuustesti() {
        String tulos = "Wall follower:\n";
        long ajastin;

        ajastin = System.nanoTime();
        WallFollower wallFollower1 = new WallFollower(labyrintti1.haeVerkko(), 100);
        wallFollower1.ratkaise();
        tulos = tulos + "100x100: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        ajastin = System.nanoTime();
        WallFollower wallFollower2 = new WallFollower(labyrintti2.haeVerkko(), 250);
        wallFollower2.ratkaise();
        tulos = tulos + "250x250: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        ajastin = System.nanoTime();
        WallFollower wallFollower3 = new WallFollower(labyrintti3.haeVerkko(), 500);
        wallFollower3.ratkaise();
        tulos = tulos + "500x500: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        ajastin = System.nanoTime();
        WallFollower wallFollower4 = new WallFollower(labyrintti4.haeVerkko(), 750);
        wallFollower4.ratkaise();
        tulos = tulos + "750x750: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        ajastin = System.nanoTime();
        WallFollower wallFollower5 = new WallFollower(labyrintti5.haeVerkko(), 1000);
        wallFollower5.ratkaise();
        tulos = tulos + "1000x1000: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";
        
        ajastin = System.nanoTime();
        WallFollower wallFollower6 = new WallFollower(labyrintti6.haeVerkko(), 2500);
        wallFollower6.ratkaise();
        tulos = tulos + "2500x2500: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        return tulos;
    }

    /*
    * Dead-end filling algoritmin tehokkuustesti
    */
    public String deadEndFillingTehokkuustesti() {
        String tulos = "Dead-end filling:\n";
        long ajastin;

        ajastin = System.nanoTime();
        DeadEndFilling deadEndFilling1 = new DeadEndFilling(labyrintti1.haeVerkko(), 100);
        deadEndFilling1.ratkaise();
        tulos = tulos + "100x100: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        ajastin = System.nanoTime();
        DeadEndFilling deadEndFilling2 = new DeadEndFilling(labyrintti2.haeVerkko(), 250);
        deadEndFilling2.ratkaise();
        tulos = tulos + "250x250: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        ajastin = System.nanoTime();
        DeadEndFilling deadEndFilling3 = new DeadEndFilling(labyrintti3.haeVerkko(), 500);
        deadEndFilling3.ratkaise();
        tulos = tulos + "500x500: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        ajastin = System.nanoTime();
        DeadEndFilling deadEndFilling4 = new DeadEndFilling(labyrintti4.haeVerkko(), 750);
        deadEndFilling4.ratkaise();
        tulos = tulos + "750x750: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        ajastin = System.nanoTime();
        DeadEndFilling deadEndFilling5 = new DeadEndFilling(labyrintti5.haeVerkko(), 1000);
        deadEndFilling5.ratkaise();
        tulos = tulos + "1000x1000: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";
        
        ajastin = System.nanoTime();
        DeadEndFilling deadEndFilling6 = new DeadEndFilling(labyrintti6.haeVerkko(), 2500);
        deadEndFilling6.ratkaise();
        tulos = tulos + "2500x2500: " + ((System.nanoTime() - ajastin) / 1000000) + "ms\n";

        return tulos;
    }
}
