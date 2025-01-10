## PROBLEMI (IR)RISOLUBILI
Ricordiamo tesi di Church-Turing: Non esiste un formalismo capace di risolvere una classe più ampia di problemi (ossia, più "potente") della MdT. Perciò, se neanche la macchina di Turing riesce a risolvere un problema, quel problema è IRRISOLUBILE.

Irrisolubilità non significa che ho ottengo una risposta sbagliata (a causa di bug), significa che la macchina non riesce a risolvere il problema in tempo finito, ovvero si inlooppa (loop infinito).

**NB**: NON c'è modo di distinguere una macchina inlooppata da una macchina che ci sta mettendo tanto tempo.

**NB_2**: a volte l'irrisolubilità di un problema si manifesta in base ai dati d'ingresso. I problemi vengono etichettati come irrisolubili anche se si riescono a risolvere in 99 casi su 100 (Bisogna poter contare sulla macchina). 

**DEF. PROBLEMA (IR)RISOLUBILE**:

    Formalizzando meglio, possiamo definire un problema risolubile (dire computabile sarebbe improprio, vedi sotto) come un problema la cui soluzione può essere espressa da una MdT. Analogamente, un problema irrisolubile è un problema la cui soluzione NON può essere espressa con una MdT. 





// qua sotto si ripete un po' la stessa cosa in maniera formale

**OCCHIO**: la macchina di Turing computa funzioni (*), non "problemi". Perché tutto torni, occorre quindi un modo per associare a un problema una funzione. 
-  problemi e funzioni sono cose diverse!

**(*)**: Computare funzioni significa rispondere con l'uscita corretta a qualsiasi ingresso appartenente al dominio della funzione. Questo è quello che fa la MdT.

### FUNZIONE CARATTERISTICA
Dato un problema P e detti:
- l’insieme X dei suoi dati di ingresso
- l’insieme Y delle risposte corrette

Si dice funzione caratteristica del problema P:
    *f_P : X → Y*
la funzione *f_P* che associa a ogni dato d’ingresso *x* appartenente *X* la corrispondente risposta corretta *y* appartenente *Y*.
- In questo modo, "problema (ir-)risolubile" diventa sinonimo di "funzione caratteristica (non) COMPUTABILE", concetto mappabile sul formalismo della Macchina di Turing.

## Quand'è che una funzione è computabile?
Di nuovo, poiché l'arma più potente è la MdT, è logico appoggiare ad essa la definizione:

    Una funzione f: A→B è computabile se esiste una MdT che data sul nastro una rappresentazione di x appartenente A (ingresso QUALSIASI), dopo un numero FINITO di passi, produce sul nastro una rappresentazione della relativa f(x) appartente a B

Purché f NON sia computabile occorre dunque che la MdT non riesca a produrre un risultato in un numero **finito** di passi, ossia che non si fermi anche in UN SOLO CASO.

**CONCLUSIONE**: un problema è risolubile (ovvero risolvibile in tempo finito da una MdT) è un problema la cui funzione caratteristica è computabile da una macchina di turing. 








## FUNZIONI DEFINIBILI vs. FUNZIONI COMPUTABILI
Vogliamo ora capire se tutte le funzioni siano computabili (magari...) o se esistano invece funzioni definibili ma non computabili(speriamo di no...) rispondendo alla domanda 1 che ci eravamo posti nel file precedente. 

Per rispondere, occorre confrontare òe funzioni che possiamo DEFINIRE con le funzioni che possiamo COMPUTARE con una MdT.

'''[premessa]
Per semplificare la trattazione, nel seguito considereremo solo funzioni sui numeri naturali f: N → N, in quanto QUALUNQUE informazione può essere rappresentata con un naturale! 

Limitarsi all'insieme di funzioni definibili considerando solo quelle da N a N **NON è LIMITATIVO** in quanto:
- ogni informazione è necessariamente finita
- come tale, può essere codificata con una collezione di numeri naturali
- la quale a sua volta può essere espressa con un unico numero naturale mediante il procedimento di Gödel (don't care) 
Per questo motivo è possibile limtarsi alla funzioni da N a N nel considerare le funzioni definibili.
'''

è noto dall'analisi matematica che Le funzioni da N a N sono NON numerabili. Per contro, l'insieme delle macchine di turing, che corrisponde alle funzioni computabili, è numerabile (le tabelle sono finite). Di conseguenza **la grande maggioranza delle funzioni definibili non è computabile**. 

COSA? Non solo esistono funzioni definibili ma non computabili, ma esse sono la maggior parte?! Svengo...

In realta, me ne sbatto della maggior parte delle funzioni da N a N (godo!). Le sole funzioni che possiamo esprimere (e che quindi sono definibili e ci interessano) sono quelle **definibili** con un linguaggio basato su un alfabeto FINITO di simboli. Quelle costituiscono un insieme numerabile! **I due insiemi hanno quindi la stessa cardinalità (godo!!)**.

I due insiemi delle funzioni computabili (descritte da MdT) e delle funzioni definibili hanno la stessa cardinalità, coincidono? Purtroppo no :( 

**CONCLUSIONE**: siccome i due insiemi non coincidono stiamo dicendo che esistono funzioni definibili ma non computabili. Questo  **equivale a dire che esistono problemi irrisolubili da una MdT**.

### PROBLEMA DELL'HALT DELLA MACCHINA DI TURING
Per dimostrare la conclusione di sopra basta trovare UN SINGOLO problema definibile ma non computabile.

Stabilire (con una MdT) se una data macchina di Turing T, con un generico ingresso X, si ferma oppure no.
- Questo problema (funzione) può essere perfettamente definito, con un alfabeto finito di simboli
- MA nel caso generale non è computabile: una TM che ipoteticamente lo risolvesse si troverebbe infatti di fronte a un assurdo.
- OSS: stiamo dando in input ad una MdT un'altra MdT... In un certo senso, stiamo rivoltando la potenza della MdT contro se stessa (epico...) 

//Skip dimostrazione.

Siccome questo problema appartiene alla categoria particolare dei problemi di decisione(si/no) si dice che: Decidere se una data Macchina di Turing T, con un generico ingresso, si fermi oppure no è un problema **INDECIDIBILE**.












// questa parte è molto mirata verso i linguaggi e i compilatori e giustifica questa parte sulla teoria della computabilità


## GENERABILITÀ e DECIDIBILITÀ
(simile al concetto di computabilità e definibilità di una funzione)

Riprendiamo le DOMANDE di prima: 
- quali sono i problemi irrisolubili?
- possiamo evitarli?

Per capire come evitare i problemi irrisolubili/indecidibili il prossimo passo è indagare la DECIDIBILITà dei problemi che ci interessano, in particolare, la **decidibilità di un INSIEME**.

**OBIETTIVO**: vogliamo capire se una stringa appartiene o meno ad un insieme valido chiamato **linguaggio**  (analisi sintattica di un compilatore)

Poiché un linguaggio è, come vedremo, un INSIEME di frasi, ci interessa indagare in generale il **problema della generabilità vs. decidibilità di un INSIEME**:
- L’analisi matematica introduce il concetto di insieme numerabile (i cui elementi possono essere contati)
    - cioè, esiste una funzione di corrispondenza coi naturali
- A noi questo non basta: vogliamo che tale funzione sia computabile, in modo che l’insieme sia EFFETTIVAMENTE GENERABILE, elemento per elemento, da una MdT

### Insiemi semidecibili (generabili)
Se la funzione di corrispondenza coi naturali di un insieme è computabile da una MdT, per definizione quell'insieme si dice **SEMIDECIDIBILE (o ricorsivamente enumerabile)**

Dato un insieme semidecidibile, una MdT, computando la funzione di corrispondenza *f* dell'insieme, può **generare** uno ad uno gli elementi dell’insieme. **Insiemi semidecidibili sono quindi effettivamente generabili**, calcolando elemento per elemento la funzione *f*.

**CONCLUSIONE**: Un insieme, per essere generabile, non basta che sia numerabile ma serve anche che sia semidecidibile (ricorsivvamente enumerabile) 

### IL PROBLEMA DI DECIDERE
**ATTENZIONE:** Il fatto che l’insieme S possa essere costruito NON SIGNIFICA che si possa decidere se un certo elemento APPARTIENE o meno all’insieme.

Come potrebbe una MdT decidere se un dato elemento appartiene a un insieme?
- In un insieme ricorsivamente enumerabile, potrebbe generare uno ad uno gli elementi dell’insieme, fermandosi se e quando lo trovi.
- Già, ma.. se non lo trova, perché non appartiene all’insieme?
- La MdT va in loop, non risponderà mai.
- Semi-decidibile indica proprio questo:
    - si riesce a decidere se appartiene (in positivo), ma non se «NON appartiene»

**Esempio dell'insieme dei numeri pari**:
- facilmente generabile
- se però chiedo ad una MdT se 23 è pari essa va avanti all'infinito

**CONCLUSIONE**: il fatto che un insieme sia generabile, NON basta per dire se un elemento appartiene o meno all'insieme (gli elementi fuori dall'insieme causano un loop)

### INSIEMI DECIDIBILI
Occorre dunque un concetto più forte e completo della semi-decidibilità.

    Un insieme S è decidibile (o ricorsivo) se la sua funzione caratteristica è computabile.

La funzione caratteristica di un insieme è una funzione di questo tipo:

            | 1, se x appartiene S
    f (x) = |
            | 0, se x non appartiene S

In altre parole un insieme è decidibile, se esiste una Macchina di Turing capace di rispondere sì o no, senza entrare mai in un ciclo infinito, alla domanda se un certo elemento appartenga all’insieme.

**TEOREMI SU INSIEMI DECIDIBILI**
1. Se un insieme è decidibile è anche semidecidibile ma non viceversa.
2. Un insieme S è decidibile se e solo se: sia S, sia il suo complemento N-S con (N inteso come i naturali) sono semidecidibili.

Il Teorema 2 è la chiave della questione:
- in un insieme ricorsivamente enumerabile si va in loop se l’elemento non è presente perché si continua a generare elementi, sperando (inutilmente) di trovarlo
- Richiedendo che anche il complemento sia semidecidibile, l’elemento dato prima o poi sarà sicuramente generato in uno dei due insiemi, evitando il loop (prima controllo un insieme e poi l'altro)

### INSIEMI & LINGUAGGI
Perché questo è importante?
- I linguaggi di programmazione sono costruiti a partire da un alfabeto **finito**
- Ogni linguaggio è caratterizzato dall’insieme (tipicamente **infinito**) delle sue frasi lecite
- Non basta che tale insieme possa essere generato (semi-decidibilità)
    - ossia, che si possano generare le frasi “lecite” previste
- è indispensabile poter decidere se una frase è giusta o sbagliata senza entrare in ciclo infinito
    - decidibilità

Se così non fosse, il compilatore o interprete potrebbe non rispondere, entrando in un ciclo infinito, davanti a una frase errata mentre ovviamente noi vogliamo che si fermi e segnali l’errore.