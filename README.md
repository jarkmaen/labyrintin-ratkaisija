# Labyrintin ratkaisija

## Dokumentaatio

[Määrittely](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/maarittely.md)

## Raportit

[Viikko 1](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/viikkoraportti1.md)

[Viikko 2](https://github.com/jarkmaen/maze-solver/blob/master/Dokumentaatio/viikkoraportti2.md)

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

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Jacoco testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Raportin tulos löytyy _target/site/jacoco/_ -hakemistosta nimellä _index.html_

### JavaDoc

JavaDoc luodaan komennolla

```
mvn javadoc:javadoc
```

Raportin tulos löytyy _target/site/apidocs/_ -hakemistosta nimellä _index.html_
