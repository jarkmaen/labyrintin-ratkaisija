package mazesolver.logic;

import mazesolver.util.List;

/**
 * Dead-end filling algoritmi
 */
public class DeadEndFilling {

    private List<List<Integer>> verkko;
    private List<Integer> polku;
    boolean suljettu[];
    private int n;

    public DeadEndFilling(List<List<Integer>> verkko, int n) {
        this.verkko = verkko;
        this.n = n;
    }

    public void ratkaise() {
        List<Integer> umpikujat = new List<>();
        suljettu = new boolean[n * n];
        polku = new List<>();
        int maali = n * n - 1;

        // Etsitään umpikujat
        for (int i = 0; i < verkko.koko(); i++) {
            // Jos solmulla on vain yksi naapuri se on umpikuja
            if (verkko.hae(i).koko() == 1 && i != 0 && i != maali) {
                umpikujat.lisaa(i);
                suljettu[i] = true;
                polku.lisaa(i);
            }
        }

        // Käydään umpikujat läpi
        for (int i = 0; i < umpikujat.koko(); i++) {
            int solmu = verkko.hae(umpikujat.hae(i)).hae(0);

            // Liikutaan umpikujasta eteenpäin kunnes tulee risteys vastaan tai
            // seuraava vapaa solmu on aloitus/maali solmu
            while (true) {
                if (solmu == 0 || solmu == maali || suljettu[solmu]) break;

                int suljettujaNaapureita = laskeSuljetutNaapurit(solmu);
                int koko = verkko.hae(solmu).koko();
                
                if (koko - suljettujaNaapureita == 0) {
                    suljettu[solmu] = true;
                    polku.lisaa(solmu);
                    break;
                }
                
                if (koko - suljettujaNaapureita == 1) {
                    int seuraavaSolmu = etsiSeuraavaSolmu(solmu);
                    solmu = seuraavaSolmu;
                }
                
                if (koko - suljettujaNaapureita > 1) {
                    break;
                }
            }
        }
    }

    /**
     * Lasketaan solmun suljettujen naapurien määrä
     */
    private int laskeSuljetutNaapurit(int solmu) {
        int suljettujaNaapureita = 0;

        for (int i = 0; i < verkko.hae(solmu).koko(); i++) {
            if (suljettu[verkko.hae(solmu).hae(i)]) suljettujaNaapureita++;
        }

        return suljettujaNaapureita;
    }

    /**
     * Käydään solmun naapurit läpi ja palautetaan seuraava vapaa solmu
     */
    private int etsiSeuraavaSolmu(int solmu) {
        int seuraavaSolmu = 0;

        for (int i = 0; i < verkko.hae(solmu).koko(); i++) {
            if (!suljettu[verkko.hae(solmu).hae(i)]) {
                seuraavaSolmu = verkko.hae(solmu).hae(i);
                suljettu[solmu] = true;
                polku.lisaa(solmu);
                break;
            }
        }

        return seuraavaSolmu;
    }

    public List<Integer> haePolku() {
        return this.polku;
    }
}
