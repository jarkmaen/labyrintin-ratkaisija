package mazesolver.logic;

import mazesolver.util.List;
import mazesolver.util.Pair;
import mazesolver.util.RandomNumberGenerator;
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
    }

    /**
     * Generoi labyrintin käyttäen satunnaistettua syvyyshaku algoritmia
     */
    public void luoLabyrintti() {
        RandomNumberGenerator rng = new RandomNumberGenerator();
        Stack<Pair<Integer, Integer>> pino = new Stack<>();
        boolean[][] visited = new boolean[n][n];
        polku = new List<>();

        verkko = new List<>();
        for (int i = 0; i < n * n; i++) verkko.lisaa(new List<>());
        int edellinenSolmu;
        int nykyinenSolmu = 0;

        // Valitaan satunnaisesti aloitus kohta
        int r1 = rng.generoi(n);
        int r2 = rng.generoi(n);
        pino.lisays(new Pair<>(r1, r2));
        visited[r1][r2] = true;

        while (!pino.onkoTyhja()) {
            List<String> suunnat = new List<>();
            int x = pino.kurkistus().haeAvain();
            int y = pino.kurkistus().haeArvo();
            pino.poisto();

            // P = Pohjoinen, I = Itä, E = Etelä, L = Länsi
            if (y != 0 && !visited[x][y - 1]) suunnat.lisaa("P");
            if (x + 1 != n && !visited[x + 1][y]) suunnat.lisaa("I");
            if (y + 1 != n && !visited[x][y + 1]) suunnat.lisaa("E");
            if (x != 0 && !visited[x - 1][y]) suunnat.lisaa("L");

            if (suunnat.koko() != 0) {
                pino.lisays(new Pair<>(x, y));
                int r = rng.generoi(suunnat.koko());
                String suunta = suunnat.hae(r);

                int sX = 0;
                int sY = 0;
                
                if (suunta.equals("P")) sY = -1;
                else if (suunta.equals("I")) sX = 1;
                else if (suunta.equals("E")) sY = 1;
                else if (suunta.equals("L")) sX = -1;
                
                visited[x + sX][y + sY] = true;
                pino.lisays(new Pair<>(x + sX, y + sY));
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
