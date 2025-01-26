blah blah...

### Variabili e Scope
Le variabili in Javascript sono **loosely typed**
- caratteristica chiave per gestire facilmente funzioni come dati, in particolare funzioni di ordine superiore

La **dichiarazione** di una variabile può essere:
- implicita (la si usa e basta)           ->  x = 18
- esplicita con la parola chiave *var*    ->  var x = 19
- esplicita con la parola chiave *let*    ->  let x = 19
La differenza sta nello **scope**:
- una variabile dichiarata **implicitamente**, senza keyword, si intende definita a livello **globale**
- una variabile dichiarata **tramite var** esiste in tutta la **funzione** in cui è definita (per lo scope più esterno, equivale al livello globale)
    - **NB**: A differenza di Java, **un blocco non delimita uno scope per le variabili globali o definite con *var***
- una variabile dichiarata tramite *let* esiste solo nel blocco in cui è definita
    - **NB**: un blocco delimita uno scope per le variabili definite con *let*


blah blah...


