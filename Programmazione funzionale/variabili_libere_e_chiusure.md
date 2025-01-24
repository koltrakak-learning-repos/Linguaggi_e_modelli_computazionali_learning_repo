## Variabili libere e chiusure
Una variabile libera è una variabile usata dentro ad una funzione in cui **non è definita localmente**.
- necessità di **criteri per dare significato alle variabili libere**
    - CHIUSURA LESSICALE vs CHIUSURA DINAMICA (vedi dopo)

Nei linguaggi tradizionali, le uniche variabili libere sono, praticamente, solamente quelle appartenenti all'ambiente globale.

Se il linguaggio supporta funzioni come FCE, la presenza di variabili libere determina la nascita della nozione di **chiusura**.

```
una chiusura è un "oggetto funzione" (overro una funzione FCE) ottenuto "chiudendo" rispetto a un certo contesto più esterno (contenente la definizione della variabile libera) una definizione di funzione che aveva variabili libere.
```

L'idea è che le variabili libere, in quanto non definite localmente, vanno ricercate negli ambienti più esterni rispetto a quello della definizione della funzione. Una volta trovato un ambiente contenente la definizione della/delle variabili libere, la definizione della funzione di partenza può venire completata (**chiusa**) con un riferimento all'ambiente esterno trovato.
- il prodotto finale dell’atto di chiudere le variabili libere di una funzione rispetto a un certo contesto d’uso è una chiusura
- **NB**: una chiusura è innanzitutto un normale oggetto funzione!

**es**:
function ff(f,x) {
    return function(r){ return **f**(**x**)+r; }
}

Invocando *ff(f, x)* si ottiene come risultato un oggetto-funzione (chiusura) che incorpora al proprio interno i riferimenti alle due variabili *f* e *x* che **non sono definite al suo interno**.


### Tempo di vita delle variabili di chiusura
La presenza di chiusure ha delle **conseguenze sul modello computazionale** del linguaggio che le adotta!

In particolare:
```
il tempo di vita delle variabili locali di una funzione, non coincide più necessariamente con quello della funzione che le contiene
```

In altre parole, le variabili locali di una funzione (che fanno parte di una chiusura) **non si possono più allocare "semplicemente" sullo stack**. In quanto **quest'ultimo viene deallocato al termine della funzione**.

Se così fosse, **si otterrebbere delle gran NPE**!
- una variabile locale di una funzione "esterna" può essere indispensabile al funzionamento della funzione più interna (se variabile locale e funzione interna fanno parte della stessa chiusura)
- il tempo di vita delle variabili locali che fanno parte di una chiusura è pari a quello della chiusura (la chiusura continua ad avere un riferimento), anche se la funzione che le definisce termina prima
- perciò **tali variabili sono allocate sull'heap** e non sullo stack! La chiusura poi, mantiene un semplice riferimento a queste variabili

**NB**: complicazione per l'interprete/compilatore che deve identificare le chiusure e capire cosa allocare sullo stack e cosa sull'heap 

**NB 2**: si sarebbe potuto anche effettuare una semplice **copia delle variabili da chiudere** mantenendo quest'ultime sullo stack, ma a quel punto eventuali **modifiche da parte di altre chiusure sulla stessa variabilie non sarebbero più visibili** (stessa logica del passaggio per valore/riferimento) rendendo la chiusura uno strumento meno potente (vedi dopo es. canale di comunicazione privato).
- esempio funzione che mi genera una funzione che mi stampa quante volte la funzione di secondo livello è stata invocata
    - chiusura con copia non funziona
    - chiusura con riferimento funziona 




### Chiusura lessicale e chiusura dinamica
Occorre stabilire un criterio sul __come e dove cercare una definizione per le variabili libere da chiudere__.

 In presenza di funzioni innestate, si apre una questione:
- da un lato, **il testo del programma** contiene fisicamente una catena di ambienti di definizione innestati (**catena lessicale**)
    - la variabile libera viene **chiusa a "compile-time"**, staticamente, appena viene valutata la funzione che la contiene
    - considero gli ambienti del codice:
        - nell'esempio, *globalEnv(dove x = 20) <- provaEnv*
- dall'altro, **l'attivazione delle funzioni** crea a **run-time** una catena di ambienti attivi (**catena dinamica**) che riflette **l'ordine delle chiamate**
    - la variabile libera viene **chiusa a run-time**, dinamicamente, appena viene invocata la funzione che la contiene per la prima volta
    - considero gli ambienti definiti dall'ordine delle chiamate:
        - nell'esempio, *testEnv(dove x=-1) <- provaEnv*

Le due catene sono in generale diverse, quindi bisogna scegliere quale seguire.

**es**:
var x = 20;

// qua ho definito la chiusura, con catena lessicale
// la variabile libera viene chiusa staticamente qua 
function provaEnv(z) {
    return z + x;   
}

function testEnv() {
    var x = -1;
    // qua utilizzo la chiusura per la prima volta
    // con catena dinamica la variabile libera viene chiusa solo in questo momento
    return provaEnv(18);
} 

Se si utilizza catena lessicale provaEnv(18) = 38; altrimenti, provaEnv(18) = 17

**Caso lessicale**:
- **più predicibile**
    - alla definizione della chiusura so chiaramente qual'è il valore della variabile puntata
    - NON devo ricostruire la sequenza delle chiamate
- compatibile con la costruzione di librerie
    - **non importa chi mi chiama**, le variabili libere hanno il valore che definisco io
- per lo stesso motivo facilità anche di testing

**Caso dinamico**: 
- più difficile da debuggare
    - per dare valore alle variabili libere **devo considerare la sequenza delle chiamate**
    - il risultato della chiamata a funzione dipende da chi mi chiama
- incompatibile con la costruzione delle librerie
    - **le variabili libere dipendono da chi mi chiama**
- impredicibilità anche nel testing

Poiché la comprensibilità è cruciale, **praticamente tutti i linguaggi di programmazione adottano il criterio di chiusura lessicale**.



## A che cosa servono le chiusure?
Permettono di modellare cose carine in linguaggi che non hanno il concetto di oggetto o qualificatori private ecc...
- stato privato
- oppuredue funzioni che codividono una variabile di una funzione esterne ottengono un canale di comunicazione
    - una sorta di sistema ad oggetti
- modo per costruirsi nuovi costrutti che non esistono nel linguaggio (pensa tipo all'esempio della funzione loop())

...

idea di cedere il controllo alle strutture dati e non di lasciarle passive: lista.iterate( () => comportamento )
ci si concentra su cosa si vuole e non sul controllo totale  -> di nuovo l'idea di non avere l'ossessione del controllo