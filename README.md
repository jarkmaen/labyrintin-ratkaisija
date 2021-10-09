# Labyrintin ratkaisija

## Dokumentaatio

[Määrittely](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/maarittely.md)

[Testaus](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/testaus.md)

[Toteutus](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/toteutus.md)

## Raportit

[Viikko 1](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/viikkoraportti1.md)

[Viikko 2](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/viikkoraportti2.md)

[Viikko 3](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/viikkoraportti3.md)

[Viikko 4](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/viikkoraportti4.md)

[Viikko 5](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/viikkoraportti5.md)

## Komentorivitoiminnot

### Ohjelman suorittaminen

Ohjelma ajetaan komennolla

```
mvn compile exec:java -Dexec.mainClass=mazesolver.Main
```

Suoritettavan jarin generointi

```
mvn package
```

Luotu jar-tiedosto löytyy hakemistosta _target_ nimellä _MazeSolver-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc luodaan komennolla

```
mvn javadoc:javadoc
```

Raportin tulos löytyy _target/site/apidocs/_ -hakemistosta nimellä _index.html_

### Checkstyle

Checkstyle tarkistus suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Raportin tulos löytyy _target/site/_ -hakemistosta nimellä _checkstyle.html_
