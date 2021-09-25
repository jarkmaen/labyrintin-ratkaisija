package mazesolver.logic;

import mazesolver.util.List;

/**
 * Wall Follower algoritmi
 */
public class WallFollower {

    private List<List<Integer>> verkko;
    private List<Integer> reitti;
    private int n;

    public WallFollower(List<List<Integer>> verkko, int n) {
        this.verkko = verkko;
        this.n = n;
    }

    public void ratkaise() {
        reitti = new List<>();
        int solmu = 0;
        int edellinenSolmu = 0;
        String suunta = "I";
        
        while (solmu != n * n - 1) {
            reitti.lisaa(solmu);
            int solmunVanhaSijainti = solmu;
            boolean b1 = false;
            boolean b2 = false;
            boolean b3 = false;
            
            if (suunta.equals("P")) {
                for (int i = 0; i < verkko.hae(solmu).koko(); i++) {
                    int naapuri = verkko.hae(solmu).hae(i);
                    if (naapuri == solmu - 1) b1 = true;
                    if (naapuri == solmu - n) b2 = true;
                    if (naapuri == solmu + 1) b3 = true;
                }
                if (b1) solmu = solmu - 1;
                else if (b2) solmu = solmu - n;
                else if (b3) solmu = solmu + 1;
                else solmu = edellinenSolmu;
            } else if (suunta.equals("I")) {
                for (int i = 0; i < verkko.hae(solmu).koko(); i++) {
                    int naapuri = verkko.hae(solmu).hae(i);
                    if (naapuri == solmu - n) b1 = true;
                    if (naapuri == solmu + 1) b2 = true;
                    if (naapuri == solmu + n) b3 = true;
                }
                if (b1) solmu = solmu - n;
                else if (b2) solmu = solmu + 1;
                else if (b3) solmu = solmu + n;
                else solmu = edellinenSolmu;
            } else if (suunta.equals("E")) {
                for (int i = 0; i < verkko.hae(solmu).koko(); i++) {
                    int naapuri = verkko.hae(solmu).hae(i);
                    if (naapuri == solmu + 1) b1 = true;
                    if (naapuri == solmu + n) b2 = true;
                    if (naapuri == solmu - 1) b3 = true;
                }
                if (b1) solmu = solmu + 1;
                else if (b2) solmu = solmu + n;
                else if (b3) solmu = solmu - 1;
                else solmu = edellinenSolmu;
            } else if (suunta.equals("L")) {
                for (int i = 0; i < verkko.hae(solmu).koko(); i++) {
                    int naapuri = verkko.hae(solmu).hae(i);
                    if (naapuri == solmu + n) b1 = true;
                    if (naapuri == solmu - 1) b2 = true;
                    if (naapuri == solmu - n) b3 = true;
                }
                if (b1) solmu = solmu + n;
                else if (b2) solmu = solmu - 1;
                else if (b3) solmu = solmu - n;
                else solmu = edellinenSolmu;
            }
            
            suunta = suunta(solmunVanhaSijainti, solmu);
            edellinenSolmu = solmunVanhaSijainti;
        }
        
        reitti.lisaa(solmu);
    }

    private String suunta(int nykyinenSolmu, int seuraavaSolmu) {
        String suunta = "";

        if (nykyinenSolmu > seuraavaSolmu) {
            if (nykyinenSolmu - seuraavaSolmu == 1) {
                suunta = "L";
            } else {
                suunta = "P";
            }
        } else if (seuraavaSolmu > nykyinenSolmu) {
            if (seuraavaSolmu - nykyinenSolmu == 1) {
                suunta = "I";
            } else {
                suunta = "E";
            }
        }

        return suunta;
    }
    
    public String tulostaReitti() {
        String liikkeet = "";
        for (int i = 0; i < reitti.koko(); i++) liikkeet = liikkeet + "" + reitti.hae(i) + " > ";
        return liikkeet;
    }
}
