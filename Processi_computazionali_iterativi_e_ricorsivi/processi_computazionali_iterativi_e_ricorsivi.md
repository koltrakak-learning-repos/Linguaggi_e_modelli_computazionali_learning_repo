osservazione iniziale: per dire se si sta adottando un modello computazionale iterativo o ricorsivo, basta guardare solo se c'è un ciclo oppure se una funzione chiama se stessa? NO

### Macchine iterative
Quello che si fa dentro ad ogni ciclo è modificare una variabile accumulatore. L'accumulatore può essere una variabile, lo schermo, qualsiasi cosa.
Senza l'accumulatore il ciclo non serve a niente.

I cicli sintentizzano mano a mano un risultato ad ogni iterazione. Se invece di fermarsi alla iterazione 6 ci si ferma alla iterazione 3 si ha comunque in mano un risultato parziale.

Ogni passo si ha un'assegnamento distruttivo.

### Macchine ricorsive
Qua non si accumula, si hanno dei __singoli assegnamenti__ su delle variabili distinte in posti diversi dello stack, NO assegnamenti distruttivi su di una unica variabile. (pensa alla matematica in cui si ha t_i con i appartenente [1,n] )

Si ha la storia di come si è arrivato al risultato.

Qua si computa all'indietro solo dopo essere arrivati al caso limite e si incomincia a ritornare (mentre prima si computa in avanti).

### ALTRE OSS:

la macchina iterativa occupa sempre e solo la memoria necessaria all'accumulatore, la macchina ricorsiva occupa tutta la memoria dei vari stack frame.

### Punto chiave: l'uso di ricorsione non porta necessariamente ad un processo computazionale ricorsivo
si può usare l'uno per ottenere l'altro -> TAIL RECURSION

### TAIL RECURSION
...

sostituisce il concetto di ciclo nei linguaggi funzionali e logici 

...

TRO (TailRecursionOptimization)

### Perchè c'è bisogno della Tail recursion è utile?
Alcuni problemi si riescono a risolvere bene se espressi con la faccia ricorsiva mentre con la faccia iterativa no. Per questo motivo è utile avere a disposizione la TRO anche in linguaggi che non la supportano nativamente e se necessario bisogna saperla implementare con la tecnica del trampolino.