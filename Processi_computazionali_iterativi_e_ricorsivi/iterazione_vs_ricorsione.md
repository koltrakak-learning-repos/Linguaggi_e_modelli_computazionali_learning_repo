**Domanda iniziale**:
per affermare se si sta adottando un modello computazionale iterativo o ricorsivo, basta guardare solo se c'è un ciclo oppure se una funzione chiama se stessa? **NO**, studiamo più attentamente che cosa caratterizza questi de modelli computazionali

    cosa caratterizza un processo computazionale iterativo rispetto a uno ricorsivo?

## Macchine iterative
Il tipico costrutto linguistico per l'iterazione è il ciclo.

Ma al di là della specifica sintassi, in questo modello computazionale:
- c’è sempre un qualcosa (variabile, schermo, file, ...)  che funge da **accumulatore**
    - inizializzata prima e **modificata durante il ciclo**
- che, **al termine del ciclo, contiene il risultato**
- al passo k, **l’accumulatore contiene il risultato parziale** k-esimo
- Il processo computazionale **computa IN AVANTI**

**NB**: Senza l'accumulatore il ciclo non serve a niente.

I cicli sintentizzano mano a mano un risultato ad ogni iterazione. Se invece di fermarsi alla iterazione 6 ci si ferma alla iterazione 3 **si ha comunque in mano un risultato parziale**.

**NB**: Ad ogni iterazione si ha un'assegnamento distruttivo.



## Macchine ricorsive
Al contrario, un processo computazionale ricorsivo è tipicamente espresso da una **funzione ricorsiva**

Al di là della specifica sintassi, in tale schema:
- **non c’è alcun accumulatore**
- la chiamata ricorsiva permette di ottenere il risultato (n-1)esimo
- il corpo della funzione opera sul risultato (n-1)-esimo per sintetizzare il risultato (n)-esimo desiderato da restituire eventualmente ad un livello superiore
- **Il risultato è sintetizzato mentre le chiamate si chiudono** -> **il processo computa ALL’INDIETRO**
    - computare all'indietro significa arrivare al caso limite ed ottenere il risultando a furia di *return*  
    - È un processo computazionale basato sulla **sintesi di nuovi valori che non sovrascrivono i precedenti** (no assegnamenti distruttivi sulla variabile contatore)
    - Qua non si accumula, si hanno dei **singoli assegnamenti** su delle variabili distinte **in posti diversi (frame) dello stack**
        - Pensa alla matematica in cui si hanno vari *t_i* con *i* appartenente [1,n]
    - Nei vari record di attivazione, si ha **la storia di come si è arrivato al risultato finale**.
- **Durante le chiamate ricorsive non c’è alcun risultato parziale**
    - si sta solamente "srotolando" il problema
    - Se si interrompe l’iterazione al passo k, durante questa **fase di srotolamento**, non si ha in mano niente; **NON c'è un risultato parziale**
    - solamente **durante la chiusura delle chiamate**, quando viene via via sintetizzato il risultato finale all’indietro, **si ottiene un risultato parziale**

**NB**: 
- la macchina iterativa occupa sempre e solo la **memoria necessaria all'accumulatore**
- la macchina ricorsiva occupa tutta la **memoria necessaria ai vari stack frame**. Gli stack frame sono tutti necessari e da mantenere in memoria fino a che non si incomincia ritornare dal caso base (il risultato n-esimo si ottiene solo dopo aver recuperato il [n-1]-esimo)

**Punto chiave**: l'uso di ricorsione non porta necessariamente ad un processo computazionale ricorsivo e viceversa. Si può usare l'uno per ottenere l'altro:
- faccia ricorsiva, processo iterativo -> TAIL RECURSION
- faccia iterativa, processo ricorsivo -> due cicli (uno che simula le invocazioni e l'altro che simula i return) con stack



## TAIL RECURSION
- Non è detto che una sintassi ricorsiva dia luogo a un processo computazionale che opera all'indietro!
- Un costrutto sintatticamente e formalmente ricorsivo può dar luogo a un processo computazionale iterativo, che, cioè, **computa in avanti**
- Ciò accade se la **chiamata ricorsiva è l’ultima istruzione della funzione** e **il risultato parziale k-esimo viene "passato avanti" come argomento (accumulatore)**
- Si parla in tal caso di **ricorsione in coda (tail recursion)**

**NB**: continuano a non esserci assegnamenti distruttivi in quanto i risultati parziali vengono salvati nello stack frame di ogni chiamata, è peroò comunque considerabile come processo iterativo.

**es**:

// versione regular
int fact(int n) {
    return n==0 ? 1 : fact(n-1) * n;
}

// versione tail-ricorsiva
int factIt(**int acc**, int n) {
    return n==0 ? **acc** : factIt(**n*acc**, n-1);
}
print(factIt(1,n))

**NB**:
- L'accumulatore porta avanti il risultato parziale
- Il valore finale dell’accumulatore è il risultato, esso viene sintetizzato in avanti e le chiusure delle varie chiamate non effettuano altre computazioni, lo restituiscono indietro e basta
- Come nel caso del ciclo, se si interrompe l’iterazione al passo k, l’accumulatore contiene il risultato k-esimo
- È un processo computazionale basato sulla sintesi di nuovi valori che però **possono sovrascrivere i precedenti** perché, computando in avanti, questi ultimi **non sono più strettametne necessari quando si fa la nuova chiamata**.
    - come nel caso iterativo, **basta un solo record di attivazione**!

### Tail Recursion Optimization (TRO)
La tail recursion può essere ottimizzata stabilendo sovrascrivere il vecchio record di attivazione con il nuovo. In tal modo, l’occupazione di memoria è identica al caso ciclico. 

**Se il linguaggio non supporta la TRO?**
- o si trasforma l’algoritmo in una iterazione (ciclo)
- oppure, se l’algoritmo non si presta / è espresso meglio in modo ricorsivo, **occorre ricostruirsi «a mano» un supporto per la TRO**

### TRO con TRAMPOLINO
IDEA CHIAVE:
per non riempire lo stack, prima di effettuare la chiamata ricorsiva **occorre terminare la chiamata precedente**
- PER FARLO, occorre un qualche «ausilio esterno» che «scateni la prossima chiamata», ma non prima che la precedente sia terminata
    - il «trampolino»
- Internamente, il trampolino effettuerà un’iterazione; ma esternamente, **consentirà di mantenere il «look» ricorsivo**

### Perchè c'è bisogno della Tail recursion?
La tail recursion può essere usata in **alternativa ai cicli per esprimere l’iterazione** ove quest'ultimi non sono disponibili.

- i **linguaggi funzionali e logici** tendono a seguire questo approccio, **ottimizzandone opportunamente il runtime (TRO)**
- i **linguaggi imperativi**, invece, avendo i cicli, di norma non ottimizzano la tail-recursion
- i **linguaggi blended**, in quanto anche logici/funzionali, tendono sempre di più a supportare la TRO (seppur **limitatamente alla ricorsione diretta**) 

Inoltre, **alcuni problemi si riescono a risolvere bene se espressi con la faccia ricorsiva**, mentre con la faccia iterativa no. Per questo motivo è utile avere a disposizione la TRO anche in linguaggi che non la supportano nativamente e se necessario bisogna saperla implementare con la tecnica del trampolino.