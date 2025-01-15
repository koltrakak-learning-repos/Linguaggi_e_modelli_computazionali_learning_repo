## ALCUNE DOMANDE FONDAMENTALI
- che istruzioni può eseguire un elaboratore?
- perchè proprio quelle?
- **quali problemi si possono risolvere?**
- cosa vuol dire che un elaboratore è potente? 
- conta il linguaggio di programmazione in tutto questo?

## DEFINIZIONI PRELIMINARI

### ALGORITMI E PROGRAMMI
- Un'algoritmo è una **sequenza FINITA di mosse** che risolve in un **tempo FINITO** una classe di problemi.
- La codifica di un algoritmo è una descrizione tramite un insieme ordinato di frasi (istruzioni) di un **linguaggio di programmazione**, che specificano le azioni da svolgere.
- Un programma è un testo scritto in accordo alla sintassi e alla semantica di un linguaggio di programmazione.

**NB**: un programma può essere **NON finito** 
- un programma NON è necessariamente un algoritmo (vedi server, sistemi operativi)
- Quando parliamo di problemi da risolvere parliamo di algoritmi. Ci aspettiamo una risposta! (in un tempo finito)

### AUTOMA ESECUTORE
- L'algoritmo esprime la soluzione a un problema.
- Un programma è la formulazione testuale di un algoritmo in un dato LINGUAGGIO di programmazione
- L'ESECUZIONE delle azioni specificate dall'algoritmo, nell'ordine da esso specificato, porta a ottenere, a partire dai dati di ingresso, la soluzione del problema.

Deve esistere qualcosa che è in grado di eseguire delle azioni (fornite da qualcun'altro) che in tempo finito, a partire dai dati d'ingresso, restituisce la soluzione del problema. In altri termini deve esistere qualcosa capace di eseguire le azioni dell’algoritmo. L'idea di fondo è che non voglio eseguire io, essere umano, i passi dell'algoritmo a mano.

    Automa esecutore: sinonimo di macchina ASTRATTA capace di eseguire le azioni dell'algoritmo (tipo Von Neumann).

Che caratteristiche deve avere tale automa?
1. deve ricevere dall’esterno una descrizione dell’algoritmo. (programma)
2. Ma poiché questa descrizione è una frase di un LINGUAGGIO
3. l'automa esecutore deve essere capace di INTERPRETARE un LINGUAGGIO! -> LINGUAGGIO MACCHINA!

## Computabilità
Formalizzando cosa sia l'automa esecutore si arriva a definire il concetto stesso di computabilità.

    In pratica, computabilità == sinonimo di risoluzione/calcolo/elaborazione di un problema. Non da parte di un essere umano, ma da parte di una macchina.

Vari approcci per questa definizione, noi useremo l'approccio a gerarchia di macchine astratte. Macchine sempre più complicate fino a che non sbatto contro l'upper bound dei problemi non risolvibili (dalle macchine).

### GERARCHIA DI MACCHINE ASTRATTE
Perché definire una gerarchia ?
- Macchine diverse potrebbero avere (e in effetti hanno..) diversa capacità di risolvere problemi
    - e noi vogliamo scegliere quella più adatta
- Se neanche la macchina più "potente" riesce a risolvere un dato problema, esso potrebbe essere non risolubile/computabile
    - e se è così, vogliamo saperlo!

**MACCHINA BASE (RETE COMBINATORIA)**
Definita dalla tripla <I, O, mfn>. Con:
    - I = insieme FINITO dei simboli in ingresso
    - O = insieme FINITO dei simboli in uscita
    - mfn:I→O = (machine function) tabella di verità 

**Limite computazionale**: molti problemi hanno bisogno di sapere della storia passata (stato). Pensa a cassaforte con rotellina per aprirla, mi devo ricordare dei caratteri immessi prima.

**AUTOMA A STATI FINITI**
Il modo più semplice per introdurre un'idea di "memoria" è definire un automa con un numero FINITO di stati interni. 

Un automa a stati finiti è definito dalla quintupla <I, O, S, mfn, sfn>:
    - I = insieme finito dei simboli di ingresso
    - O = insieme finito dei simboli di uscita
    - S = insieme finito degli stati
    - mfn: IxS → O (funzione di macchina)
    - sfn: IxS → S (funzione di stato)

Poiché lo stato funge da memoria interna, le risposte possono essere diverse anche a parità di dati d’ingresso.

**Limite computazionale**: è un dispositivo a memoria finita e come tale inadatto a problemi che non consentano di limitare a priori la lunghezza delle sequenze da ricordare. In altri termini: 
- stati FINITI. Il numero di stati è congelato una volta definito l'automa. Alcuni problemi hanno bisogno di più dinamicità.
- esempio: bilanciamento delle parentesi; il numero di stati viene definito in base ad ogni ingresso. 
- Vanno bene per i problemi in cui riesco a trovare il numero massimo di stati necessario.

## MACCHINA DI TURING
Sequenze di lunghezza non definibile a priori non ci stanno dentro la macchina, mettiamole fuori!
- Idea di nastro infinitamente espandibile come supporto per la memoria
- La macchina si può muovere sul nastro

La macchina di Turing è quindi definita dalla quintupla: <A, S, mfn, sfn, dfn> dove:
    - A = insieme finito dei simboli di ingresso e uscita
    - S = insieme finito degli stati (uno dei quali è HALT)
    - mfn: AxS → A (funzione di macchina)
    - sfn: AxS → S (funzione di stato)
    - dfn: AxS → D = {Left,Right,None} (funz. di direzione)
**OSS**: Input e output hanno lo stesso alfabeto (A) dato che quello che scrivi lo vuoi anche saper leggere e viceversa.

### FUNZIONAMENTO DELLA MACCHINA DI TURING
La macchina di turing è, in sostanza, una macchina a STATI FINITI con in aggiunta il nastro. Il nastro illimitatamente espandibile rappresenta il deposito dei dati (memoria). La macchina è munita di una testina di lettura / scrittura e può:
- Leggere un simbolo dal nastro
- Scrivere sul nastro il simbolo specificato da *mfn()*
- Transitare in un nuovo stato interno specificato da *sfn()*
- Spostarsi sul nastro di una posizione nella direzione indicata da *dfn()*

**OSS**: da questa lista di operazioni possibili si deduce che le funzionalità minime di una CPU per essere *turing-complete* sono quelle qua sopra (oppurtunamente adattate -> stati = configurazione del register file; simboli = bit)

**IPOTESI**: quando raggiunge lo stato HALT, la macchina si ferma.

Risolvere un problema (programmare) con questa macchina richiede di:
1. definire la rappresentazione dei dati di partenza da porre inizialmente sul nastro e di quelli di uscita che ci si aspetta di trovare alla fine sul nastro.
2. definire il comportamento, ossia le tre funzioni: *mfn()*, *sfn()* e *dfn()*, concepite in modo da lasciare sul nastro la soluzione quando alla fine la macchina si ferma.

**NB**: La macchina di Turing ha una capacità di movimento senza vincoli, posso andare dove voglio 
- Nastro = RAM, memoria ad accesso casuale.

### MdT e problemi risolubili
La definizione dice che quando alla fine la macchina si ferma, sul nastro c'è la soluzione del problema, altrimenti, c'è stato un errore di programmazione. MA... è certo che prima o poi si fermi?

In realtà, NO! Come vedremo, è possibile che il comportamento definito dalle tre funzioni causi un ciclo infinito! Non fermarsi, entrando in loop, implica che la macchina potrebbe non rispondere mai più. Cosa significa? vedremo che **un problema non risolvibile è un problema per cui la macchina di turing va in loop**.

    La capacità di andare in loop è l'ipotesi fondamentale per risolvere la classe maggiore di problemi possibile. Una macchina che non va in loop è per sua natura meno potente.

**DOMANDE FONDAMENTALI**
1. Esiste sempre una opportuna Macchina di Turing capace di risolvere qualunque problema? Oppure esistono problemi che nessuna MdT può risolvere?
2. Esistono macchine “più potenti” della MdT?
    - se esistono, vorremmo trovarle!
    - se non esistono, dobbiamo sapere "fin dove ci si può spingere" con ciò di cui disponiamo

**TESI DI CHURCH-TURING** (risposta alla domanda 2)
Non esiste alcun formalismo capace di risolvere una classe di problemi più ampia di quella risolta dalla Macchina di Turing. (non è un teorema dato che non è mai stata dimostrata, ma non è neanche mai stata confutata; teorema di fatto)

Dunque, la MdT è l'arma più potente del nostro arsenale! La macchina di Turing definisce i limiti INTRINSECI di ciò che puoi chiedere ad una macchina (a meno che non venga scoperta una macchina più potente)

Ma non sempre serve l'arma finale…

### PDA (PUSH DOWN AUTOMATON)
Non sempre è necessaria una Macchina di Turing
- per alcune categorie di problemi di grande interesse pratico può bastare un modello di memoria più limitato
- il Push Down Automaton (PDA), ad esempio, prevede un "nastro" espandibile da una parte sola, col vincolo di poter accedere solo alla cella "al top". (stack)
    
Il PDA porta ad un modo di lavorare efficente, ma non tutti i problemi si possono risolvere con questo automa. Quelli che ci riescono però guadagnano molto di efficienza. 




## MACCHINA DI TURING UNIVERSALE
La macchina di Turing vista fino ad adesso è cablata su uno specifico problema (le tre tabelle vengono definite e congelate alla creazione della macchina). In altri termini, una macchina di Turing definita in questo modo è specifica per un determinato problema.

Se si desiderasse risolvere *n* problemi, si avrebbe bisogno di *n* MdT distinte :(
- Sarebbe possibile costruire una Macchina di Turing UNIVERSALE?

La risposta è si, bisogna tuttavia fare delle modifiche:
- è  necessario che l'algoritmo (le tre tabelle) non sia più cablato nella macchina ma che esso risieda nel nastro (mondo esterno)
    - in questo modo le tre tabelle non cambiano quando cambia il problema da risolvere 
- la parte hardware congelata della mdt (le 3 tabelle) diventa quella che recupera l'algoritmo sul nastro (caricamento del programma == algoritmo loader).

Creare una UTM richiede sapere descrivere l'algoritmo richiesto in modo da poterlo porre sul nastro.
- occorre un LINGUAGGIO
- e una macchina (l'hardware cablato) che lo INTERPRETI 
- **Conclusione: LA UTM è UN INTERPRETE DI UN LINGUAGGIO!!!**
    - ritorniamo all'idea iniziale di automa esecutore!

**OSS**: La UTM definisce anche il concetto di programmazione e di software, ovvero:
- Il software è una descrizione in un linguaggio di una determinata MdT che risolve un problema specifico
- Programmare una UTM significa darle in pasto un determinato software/programma in modo tale da farle simulare il comportamento della macchina specifica: U(desc(M))() === M() 

### PARALLELO UTM e MACCHINA DI VON NEUMANN
La Macchina di Turing Universale modella il concetto di elaboratore di uso generale:
- una macchina che va a cercare le “istruzioni” da svolgere…    -> fetch
- .. le interpreta...                                           -> decode
- .. e le esegue.                                               -> execute

Ha quindi senso confrontare la UTM con la Macchina di Von Neumann, che esprime architettura di base di un elaboratore di uso generale.

In pratica:                                             
- leggere/scrivere un simbolo dal/sul nastro; Corrisponde a: lettura/scrittura dalla/sulla memoria RAM/ROM
- transitare in un nuovo stato interno; Corrisponde a: nuova configurazione dei registri della CPU
- spostarsi sul nastro di una (o più) posizioni; Corrisponde a: scelta della cella di memoria su cui operare (indirizzo contenuto nell’Address Register)

La UTM, però, NON cattura TUTTI gli aspetti dell'architettura di Von Neumann. 

    Nella macchina di Turing NON c'è I/O, essa non concepisce il mondo esterno.

Concepisce solo la computazione dei dati per raggiungere il risultato (che salva sul nastro (memoria interna)). Come i dati siano finiti sul nastro non si sa, L'obiettivo della MDT era solo rappresentare la computazione.

In altre parole La UTM è pura computazione: non modella la dimensione dell’interazione, che invece esiste nella macchina di Von Neumann. Infatti, La macchina di Von Neumann possiede istruzioni di I/O, la UTM no.

### COMPUTAZIONE E INTERAZIONE
Pensandoci bene, Computazione e Interazione sono dimensioni ortogonali, espresse (potenzialmente) da due LINGUAGGI distinti.

Un linguaggio di programmazione comprende in verità (almeno) tre linguaggi
- linguaggio di computazione (macchina di turing):          definisce le primitive per esprimere l'elaborazione dei dati.
- linguaggio di coordinazione (macchina di von neumann):    definisce COME si legge/scrive verso l'esterno.
    - linguaggio di comunicazione:                          dice che COSA leggo/scrivo verso l'esterno

**CONCLUSIONE**: l'I/O è una dimensione diversa dalla computazione.