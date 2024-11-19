### Variabile libera
Una variabile usata dentro ad una funzione che però non è dichiarata al suo interno.

Rientrano in questa categoria variabili appartenenti ad ambienti più esterni rispetto a quello della funzione in considerazione.

Nei linguaggi tradizionali, praticamente solamente l'ambiente globale.

### Chiusure
Per dare significato ad una variabile libera, bisogna trovare l'ambiente esterno che contiene la sua definizione. L'atto di trovare l'ambiente con la definizione e "racchiuderci" dentro la variabile libera prende il nome di __chiusura__.

...

__OSS IMPORTANTE__: i linguaggi che fanno lo "sforzo" di avere le funzioni come FCE lo fanno per avere concetti come le chiusure e di poter combinare agevolmente funzioni tra loro. Senza questi concetti non ci sarebbe tutto questo guadagno con le FCE functions.

#### Tempo di vita delle variabili
In presenza di variabili libere e del concetto di chiusura. la maniera classica di invocare funzioni tramite creazione e __distruzione__ del record di attivazione sullo stack non basta più (magari utilizziamo l'heap).

...

__NB__: si sarebbe potuto anche effettuare un passaggio per copia ma a quel punto eventuali modifiche deglia argomenti da altre funzioni che hanno un riferimento non vengono viste.

