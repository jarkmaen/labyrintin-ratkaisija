package mazesolver.util;

public class Pair<K, V> {

    private K avain;
    private V arvo;

    public Pair(K avain, V arvo) {
        this.avain = avain;
        this.arvo = arvo;
    }
    
    public K haeAvain() {
        return avain;
    }
    
    public V haeArvo() {
        return arvo;
    }
}
