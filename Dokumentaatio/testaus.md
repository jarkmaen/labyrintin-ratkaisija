# Ohjelman testaus

## Yksikkötestit

Yksikkötestit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Raportin tulos löytyy _target/site/jacoco/_ -hakemistosta nimellä _index.html_

## Suorituskykytestit

Labyrintit generoidaan valmiiksi ennen algoritmien suorittamista

| Labyrintin koko  | Wall follower | Dead-end filling |
| ------------- | :-----------: | :-----------: |
| 100x100  | 7ms  | 3ms |
| 250x250  | 7ms  | 9ms |
| 500x500  | 9ms  | 19ms |
| 750x750  | 121ms  | 49ms |
| 1000x1000  | 76ms  | 72ms |
| 2500x2500  | 403ms  | 494ms |

## Testikattavuus

Käyttöliittymä ja tehokkuustesti luokat ovat jätetty pois testeistä

<img src="https://raw.githubusercontent.com/jarkmaen/labyrintin-ratkaisija/master/Dokumentaatio/kuvat/testikattavuus.PNG">
