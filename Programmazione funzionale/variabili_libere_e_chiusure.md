### Variabile libera
Una variabile usata dentro ad una funzione che però non è dichiarata al suo interno.

Rientrano in questa categoria variabili appartenenti ad ambienti più esterni rispetto a quello della funzione in considerazione.

Nei linguaggi tradizionali, praticamente solamente l'ambiente globale.

### Chiusure
Per dare significato ad una variabile libera, bisogna trovare l'ambiente esterno che contiene la sua definizione. L'atto di trovare l'ambiente con la definizione e "racchiuderci" dentro la variabile libera prende il nome di __chiusura__.

    Una chiusura è innanzitutto un normale oggetto funzione!

...

__OSS IMPORTANTE__: i linguaggi che fanno lo "sforzo" di avere le funzioni come FCE lo fanno per avere concetti come le chiusure e di poter combinare agevolmente funzioni tra loro. Senza questi concetti non ci sarebbe tutto questo guadagno con le FCE functions.

#### Tempo di vita delle variabili
In presenza di variabili libere e del concetto di chiusura. la maniera classica di invocare funzioni tramite creazione e __distruzione__ del record di attivazione sullo stack non basta più (magari utilizziamo l'heap).

...

__NB__: si sarebbe potuto anche effettuare un passaggio per copia ma a quel punto eventuali modifiche degli argomenti da altre funzioni che hanno un riferimento non vengono viste.

### Chiusura lessicale e chiusura dinamica
Occorre stabilire un criterio sul __come e dove cercare una definizione per le variabili libere da chiudere__. In presenza di funzioni innestate, si apre una questione:
- da un lato, il testo del programma contiene fisicamente una catena di ambienti di definizione innestati (catena lessicale)
- dall'altro, l'attivazione delle funzioni crea a run-time una catena di ambienti attivi (catena dinamica) che riflette l'ordine delle chiamate
Le due catene sono in generale diverse, quindi bisogna scegliere quale seguire

Caso lessicale:
- più predicibile; alla definizione della chiusura so chiaramente qual'è la variabile puntata
- compatibile con la costruzione di librerie

Caso dinamico: 
- per dare valore alle variabili libere aspetto di vedere chi mi chiama
- il risultato della chiamata a funzione dipende da chi mi chiama
- incompatibile con la costruzione delle librerie
- impredicibilità anche nel testing

### A che cosa servono le chiusure
Permettono di modellare cose carine in linguaggi che non hanno il concetto di oggetto o qualificatori private ecc...
- stato privato
- oppuredue funzioni che codividono una variabile di una funzione esterne ottengono un canale di comunicazione
    - una sorta di sistema ad oggetti
- modo per costruirsi nuovi costrutti che non esistono nel linguaggio (pensa tipo all'esempio della funzione loop())

...

idea di cedere il controllo alle strutture dati e non di lasciarle passive: lista.iterate( () => comportamento )
ci si concentra su cosa si vuole e non sul controllo totale  -> di nuovo l'idea di non avere l'ossessione del controllo