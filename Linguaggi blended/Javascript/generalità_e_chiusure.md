blah blah...

### Variabili e Scope
La dichiarazione di una variabile può essere:
- implicita (la si usa e basta)         ->  x = 18
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
    - Utile per realizzare un canale di comunicazione passando degli oggetti

2. Realizzare nuove strutture di controllo

...

Attenzione però ai riferimenti...

## Modello a prototipi

### Definizione di oggetti

...

con Funzione costruttrice e operatore new (di fatto si chiama la funzione costruttrice con argomento nascosto uguale alla memoria necessaria)

**ATTENZIONE a THIS: Negli esempi è fondamentale**

**Il qualificatore private non è necessario**; basta usare delle chiusure

...

Le classi fissano la struttura


## Prototipi di oggetti
ereditarietà tra oggetti e non tra classi. Qua è tutto più fluido, non ho la (conveniente) rigidità delle classi

I costruttori predispongono un campo \_\_proto\_\_ che popolono con i prototipi degli oggetti che creano

**perchè ricorsione**?
anche nel prototipo dei literal

![alt text](image.png)