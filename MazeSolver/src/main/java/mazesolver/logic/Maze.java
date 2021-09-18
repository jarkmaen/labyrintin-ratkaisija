package mazesolver.logic;

import java.util.Random;
import java.util.Stack;
import mazesolver.util.List;
import mazesolver.util.Pair;

public class Maze {

    private List<Pair<Integer, Integer>> polku;
    private int n;

    public Maze(int n) {
        this.n = n;
        luoLabyrintti();
    }

    private void luoLabyrintti() {
        Stack<Pair<Integer, Integer>> pino = new Stack<>();
        boolean[][] visited = new boolean[n][n];
        Random random = new Random();
        polku = new List<>();
        
        int r1 = random.nextInt(n);
        int r2 = random.nextInt(n);
        pino.push(new Pair<>(r1, r2));
        visited[r1][r2] = true;

        while (!pino.isEmpty()) {
            List<String> suunnat = new List<>();
            int x = pino.peek().haeAvain();
            int y = pino.peek().haeArvo();
            pino.pop();

            if (y != 0 && !visited[x][y - 1]) suunnat.lisaa("P");
            if (x + 1 != n && !visited[x + 1][y]) suunnat.lisaa("I");
            if (y + 1 != n && !visited[x][y + 1]) suunnat.lisaa("E");
            if (x != 0 && !visited[x - 1][y]) suunnat.lisaa("L");
            
            if (suunnat.koko() != 0) {
                pino.push(new Pair<>(x, y));
                int r = random.nextInt(suunnat.koko());
                String suunta = suunnat.hae(r);
                
                if (suunta.equals("P")) {
                    visited[x][y - 1] = true;
                    pino.push(new Pair<>(x, y - 1));
                } else if (suunta.equals("I")) {
                    visited[x + 1][y] = true;
                    pino.push(new Pair<>(x + 1, y));
                } else if (suunta.equals("E")) {
                    visited[x][y + 1] = true;
                    pino.push(new Pair<>(x, y + 1));
                } else if (suunta.equals("L")) {
                    visited[x - 1][y] = true;
                    pino.push(new Pair<>(x - 1, y));
                }
            }
            
            polku.lisaa(new Pair<>(x, y));
        }
    }
    
    public List<Pair<Integer, Integer>> haePolku() {
        return this.polku;
    }
}
