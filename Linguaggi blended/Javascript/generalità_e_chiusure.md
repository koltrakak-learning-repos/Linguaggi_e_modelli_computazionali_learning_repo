blah blah...

### Variabili e Scope
La dichiarazione di una variabile può essere:
- implicita (la si usa e basta)           ->  x = 18
- esplicita con la parola chiave *var*    ->  var x = 19
- esplicita con la parola chiave *let*    ->  let x = 19

La differenza sta nello **scope**:
- una variabile dichiarata implicitamente, senza keyword, si intende definita a livello **globale**
- una variabile dichiarata tramite *var* esiste in tutta la **funzione** in cui è definita (per lo scope più esterno, equivale al livello globale)
    - A differenza di Java, un blocco non delimita uno scope per le variabili globali o definite con *var*
- una variabile dichiarata tramite *let* esiste solo nel blocco in cui è definita
    - un blocco delimita uno scope per le variabili definite con *let*


blah blah...


### Chiusure in javascript

1. Rappresentare uno stato privato e nascosto
    - Utile per realizzare factory di oggetti
    - Utile per realizzare un canale di comunicazione mediante oggetti privati della chiusura

2. Realizzare nuove strutture di controllo
    - la funzione esterna esprime il controllo, mentre quella ricevuta come argomento esprime il "corpo da eseguire"

3. Riprogettare/semplificare API
    - attraverso metodi parametrici che «ricevono comportamento» come argomento, anziché metodi specializzati

...

Attenzione però ai riferimenti (chiusure dentro ad un for) ...
