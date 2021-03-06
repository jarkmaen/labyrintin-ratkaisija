# Ohjelman toteutus

## Yleisrakenne

### Pakkaukset

- `mazesolver.logic` - sisältää sovelluslogiikan ja algoritmit
- `mazesolver.ui` - sisältää käyttöliittymään liittyvät luokat
- `mazesolver.util` - sisältää tarvittavat tietorakenteet ja muut apuvälineet

### Logic

- Maze: Generoi ja hallitsee labyrinttiä
- WallFollower: Ratkaisee labyrintin käyttäen wall follower-algoritmia
- DeadEndFilling: Ratkaisee labyrintin käyttäen dead-end filling-algoritmia

### UI

- MazeSolverUI: Luo, piirtää ja hallitsee käyttöliittymää

### Util

- List: Yksinkertainen lista tietorakenne
- Pair: Yksinkertainen pari tietorakenne
- PerformanceTest: Suorittaa suorituskykytestit
- RandomNumberGenerator: Yksinkertainen satunnaislukugeneraattori
- Stack: Dynaaminen pino tietorakenne

### Muut

- Main: Ohjelman pääluokka mikä käynnistää käyttöliittymän
- Test: Sisältää sovelluksen testaamiseen käytettävät luokat

## Saavutetut aika- ja tilavaativuudet

- Wall follower
  - Aikavaativuus: O(n)
  - Tilavaativuus: O(1)
- Dead-end filling
  - Aikavaativuus: O(n)
  - Tilavaativuus: O(n)

## Lähteet

- https://en.wikipedia.org/wiki/Maze-solving_algorithm
