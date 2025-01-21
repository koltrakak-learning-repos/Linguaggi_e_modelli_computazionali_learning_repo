## Variabili e assegnamenti
Tutti i linguaggi di programmazione introducono a un qualche livello le nozioni di variabile e assegnamento. Tuttavia, l'operatore utilizzato: *=* non rappresenta una uguaglianza in senso matematico.

    x = x + 1 è un obrobrio. 

il carattere *=* non vuol dire uguale, e il simbolo *x* significa due cose diverse a sinistra o a destra dell'uguale.

- La peculiarità dell'assegnamento è che il simbolo di __variabile ha significato diverso a destra__ e a sinistra dell’operatore =
- Il simbolo di variabile è **overloaded**
    - il suo significato dipende dalla posizione rispetto all’operatore
    - a sinistra, indica il contenitore a destra, il contenuto

La semantica informale può quindi essere così riassunta:
1. Prendere **il valore** della variabile **a destra** dell’operatore =
2. Usarlo per valutare il valore dell’espressione a destra dell’ =
3. Porre il risultato nel **contenitore** rappresentato dalla variabile specificata **a sinistra** dell’ =

Il simbolo di variabile significa quindi:
- la variabile in quanto tale, quando compare a sinistra (L-value)
    - contenitore
- il contenuto di quella variabile, quando compare a destra (R-value)
    - contenuto

**NB**: **Questo tipo di sintassi non è LL(1)!**
- richiede minimo LL(2) in quanto dopo ogni variabile bisogno anche controllare se c'è anche un uguale dopo
    - se trovo *ID* e basta non riesco a capire se devo parsarlo come l-value (contenitore) oppure come r-value (contenuto)
    - con LL(2) se leggo *ID* e *=* so che mi sto riferendo all'l-value, metre se leggo qualcosa come *ID* e *+* so che sono a destra di un uguale e quindi mi sto riferendo al r-value. 
- Per questo motivo è utile **distinguere sintatticamente L-value da R-value**.
    - Ad esempi, nei linguaggi di shell: *x = $x+1*

### Assegnamenti distruttivi
Altro grande tema è capire se *x=x+1* è una cosa accettabile o meno. È lecito cambiare il valore associato in precedenza a un simbolo di variabile? Questo è cio che prende il nome di __assegnamento distruttivo__. 

Se questo fosse il caso, l'unico modo possibile per capire che valore è associato ad una variabile in un dato punto del programma è simulare il l'evoluzione del programma fino a quel punto (debug).
- essere umano si abbassa al livello della macchina :(

```
L'idea del riscrivere una variabile dopo che le hai gia dato un valore è tipica del modello imperativo (ispirato dalla macchina di turing).
```

Una alternativa è quella dei linguaggi logichi. **Le variabili possono subire un solo assegnamento e in seguito non possono venire più modificate**.

```
In questo modello a singolo assegnamento si computa sintetizzando nuovi valori e non modificando variabili.
```

Con questo approccio si ottiene __trasparenza referenziale__:
- ogni simbolo mantiene il suo valore in qualsiasi punto del programma (come in una dimostrazione matematica).

__NB__: il fatto che assegnamenti distruttivi non siano permessi, non significa che le variabili diventano costanti! Con una costante si conosce il valore desiderato a priori. Variabili a singolo assegnamento invece, sono "variabili" nel senso che __a runtime possono assumere valori diversi__, l'unica cosa che cambia è che non si contemplano assegnamenti multipli. 

### Environment ed effetti collaterali
Per esprimere la semantica dell’assegnamento occorre introdurre il concetto di **environment** inteso come mappa che mantiene la associazioni simbolo-valore.

**Gli assegnamenti modificano l'environment**, causando un **effetto collaterale**
- L'effetto principale di un assegnamento è quello di restituire il risultato dell'assegnamento.
    - x = 13; denota il valore 13
- Il side effect è la modifica dell'environment. Il mondo prima dell'assegnamento è quello dopo sono diversi!
    - Per capire come mai si chiama collaterale pensa ad x++. Questa espressione inizialemente ritorna x ma se chiamata una seconda volta ritorna x+1.
    - Ho chiamato la stessa espressione due volte ed ho ottenuto due valori diversi? Si, perchè l'assegnamento ha modificato l'environment (stato)

**NB**: Se il linguaggio è a singolo assegnamento, una riga dell'environment gia settata non può più venire modificata (restituzione di un errore), si possono solo aggiungere entry nella mappa. Nei linguaggi ad assegnamento distruttivo, invece, questo è possibile.

**curiosità**: il concetto di effetto collaterale è anche presente nel contesto delle funzioni, in cui si distinguono
- funzioni "pure" (pure functions), funzioni che, dato lo stesso input, restituiscono sempre lo stesso risultato.
- funzioni con side-effect, funzioni che modificano un qualche stato condiviso, e che quindi, se invocate più volte, possono restituire risultati diversi (anche in maniera difficile da debuggare se non ci si accorge del side-effect).

### Environment multipli
L’environment è spesso suddiviso in sotto-ambienti, di norma collegati al tempo di vita delle strutture run-time
- **environmente globale**: contiene le coppie il cui tempo di vita è associato con l’intero programma (variabili globali)
- **environmente locali**: contengono coppie il cui tempo di vita non coincide con l’intero programma; tipicamente legati all’attivazione di funzioni o altre strutture run-time (blocchi).

__NB__: Ogni modello computazionale deve specificare il campo di visibilità dei suoi simboli (scope), ossia quali environment sono visibili in quali punti della struttura fisica del programma.


### Assegnamenti multipli
Distinguiamo **espressioni**:
- frammenti di codice che **ritornano un valore** quando vengono valutati
- **non modificano l'ambiente**

da **istruzioni**:
- possono ritornare qualcosa ma **il loro obiettivo principale è produrre un effetto collaterale**
- **modificano l'ambiente**

L'assegnamento potrebbe sembrare di rientrare nella categoria delle istruzioni, in realtà, è comodo che quest'ultimi ritornino anche il valore assegnato. Pensa ad assegnamenti dentro ad istruzioni di controllo, oppure ad assegnamenti multipli.

```
per permettere l'assegnamento multiplo l'assegnamento va definito come una espressione al posto di una istruzione.
```

**OSS**: è una mossa intelligente rendere l'assegnamento un'espressione? Sicuramente rende il codice più compatto, però, può causare anche degli errori difficilmente debugabbili come: *if (x = 0)*  .

L'operatore di **assegnamento è necessariamente associativo a destra**, perché il valore da assegnare è quello  dell'ultimo elemento. (se si provasse associatività a sinistra si otterrebbe: x = y = 7 -> $y = 7; valore assegnato ad un valore che non ha senso)

## Estensione dell'interprete
Per introdurre l’assegnamento in un linguaggio occorre stabilire:
- la sintassi dei nomi delle variabili
- se l’assegnamento sia distruttivo o meno
- se la scrittura di assegnamento x=valore sia un’__istruzione__ o una __espressione__
    - Quest’ultima scelta dipende dal fatto che si voglia o meno supportare l’assegnamento multiplo.

La grammatica si arricchisce con le seguenti produzioni:


Nuovo concetto L-Ident → usato in AssignExp
Nuovo tipo di fattore → nuova produzione per FACTOR

EXP     ::= TERM
EXP     ::= EXP + TERM
EXP     ::= EXP – TERM
**EXP     ::= ASSIGN**          // Nuovo espressione di assegnamento (multipla) → nuova produzione per EXP
**ASSIGN  ::= IDENT = EXP**     // Qua IDENT rappresente l-value

TERM    ::= FACTOR
TERM    ::= TERM * FACTOR
TERM    ::= TERM / FACTOR

FACTOR  ::= num
FACTOR  ::= ( EXP )
**FACTOR  ::= $IDENT**          // Nuovo tipo di fattore (r-value di un IDENT) → nuova produzione per FACTOR 




### Espressioni sequenza
COSA SONO: sequenze di espressioni separate da virgola (,)
- IPOTESI 1: la prima espressione è sempre un assegnamento
- IPOTESI 2: il valore complessivo è quello dell'exp più a destra

Sintassi astratta:

    EXP ::= ASSIGN , EXP // SeqExp

__NB__: con questa estensione ci si avvicina sempre più alla sintassi e ai costrutti di un "vero" linguaggio di programmazione, in cui di solito:
- prima si inizializzano le variabili
- poi si computa su di esse