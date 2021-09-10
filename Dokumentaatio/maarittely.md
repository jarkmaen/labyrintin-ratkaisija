# Määrittely (TKT) (Suomi)
Sovelluksen tarkoituksena on ratkaista itse generoituja labyrinttejä käyttäen wall follower algoritmia ja dead-end filling algoritmia. Labyrintin generoimiseen käytetään yksinkertaista dfs-algoritmia. Ratkaisujen esittäminen näytetään graafisen käyttöliittymän avulla. Projektin ohjelmointikieli on Java.

## Tietorakenteet ja algoritmit
Sovellus generoi labyrintin rekursiivisesti dfs algoritmilla ja käyttää wall follower ja dead-end filling algoritmejä sen ratkaisimiseen. Molemmat algoritmit sopivat hyvin projektiin koska jokaisessa generoidussa labyrintissä on vain yksi oikea ratkaisu.

Tarvittavat tietorakenteet ovat pino ja lista.

## Aika- ja tilavaativuudet
Jokaisen algoritmin aikavaatimus on O(n) missä n on labyrintin leveys x korkeus.

Wall follower algoritmin tilavaativuus on O(1) sillä sen tarvitsee vain tietää mitä on sen ympärillä. Dead-end algoritmin tilavaativuus on O(n) koska sen täytyy muistaa jokaisen umpikujan sijainnin.

## Lähteet
- https://en.wikipedia.org/wiki/Maze_generation_algorithm
- https://en.wikipedia.org/wiki/Maze-solving_algorithm