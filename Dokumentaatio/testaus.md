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

| Labyrintin koko  | Wall follower | Dead-end filling |
| ------------- | :-----------: | :-----------: |
| 100x100  | 7ms  | 4ms |
| 250x250  | 9ms  | 9ms |
| 500x500  | 121ms  | 17ms |
| 750x750  | 25ms  | 46ms |
| 1000x1000  | 53ms  | 67ms |
| 2500x2500  | 176ms  | 560ms |

## Testikattavuus

<img src="https://raw.githubusercontent.com/jarkmaen/labyrintin-ratkaisija/master/Dokumentaatio/kuvat/testikattavuus.PNG">
