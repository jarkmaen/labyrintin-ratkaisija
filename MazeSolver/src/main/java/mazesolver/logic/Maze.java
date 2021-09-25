package mazesolver.logic;

import java.util.Random;
import mazesolver.util.List;
import mazesolver.util.Pair;
import mazesolver.util.Stack;

/**
 * Labyrintin logiikasta vastaava luokka
 */
public class Maze {

    private List<Pair<Integer, Integer>> polku;
    private List<List<Integer>> verkko;
    private int n;

    public Maze(int n) {
        this.n = n;
        luoLabyrintti();
    }

    /**
     * Generoi labyrintin käyttäen satunnaistettua syvyyshaku algoritmia
     */
    private void luoLabyrintti() {
        Stack<Pair<Integer, Integer>> pino = new Stack<>();
        boolean[][] visited = new boolean[n][n];
        Random random = new Random();
        polku = new List<>();

        verkko = new List<>();
        for (int i = 0; i < n * n; i++) verkko.lisaa(new List<>());
        int edellinenSolmu;
        int nykyinenSolmu = 0;

        int r1 = random.nextInt(n);
        int r2 = random.nextInt(n);
        pino.lisays(new Pair<>(r1, r2));
        visited[r1][r2] = true;

        while (!pino.onkoTyhja()) {
            List<String> suunnat = new List<>();
            int x = pino.kurkistus().haeAvain();
            int y = pino.kurkistus().haeArvo();
            pino.poisto();

            if (y != 0 && !visited[x][y - 1]) suunnat.lisaa("P");
            if (x + 1 != n && !visited[x + 1][y]) suunnat.lisaa("I");
            if (y + 1 != n && !visited[x][y + 1]) suunnat.lisaa("E");
            if (x != 0 && !visited[x - 1][y]) suunnat.lisaa("L");

            if (suunnat.koko() != 0) {
                pino.lisays(new Pair<>(x, y));
                int r = random.nextInt(suunnat.koko());
                String suunta = suunnat.hae(r);

                if (suunta.equals("P")) {
                    visited[x][y - 1] = true;
                    pino.lisays(new Pair<>(x, y - 1));
                } else if (suunta.equals("I")) {
                    visited[x + 1][y] = true;
                    pino.lisays(new Pair<>(x + 1, y));
                } else if (suunta.equals("E")) {
                    visited[x][y + 1] = true;
                    pino.lisays(new Pair<>(x, y + 1));
                } else if (suunta.equals("L")) {
                    visited[x - 1][y] = true;
                    pino.lisays(new Pair<>(x - 1, y));
                }
            }

            edellinenSolmu = nykyinenSolmu;
            nykyinenSolmu = x + n * y;
            if (polku.koko() != 0) verkko.hae(edellinenSolmu).lisaa(nykyinenSolmu);

            polku.lisaa(new Pair<>(x, y));
        }
    }

    public List<Pair<Integer, Integer>> haePolku() {
        return this.polku;
    }

    public List<List<Integer>> haeVerkko() {
        return this.verkko;
    }
}
