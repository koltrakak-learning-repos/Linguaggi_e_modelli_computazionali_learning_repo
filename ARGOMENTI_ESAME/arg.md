## 1) Teoria della computabilità e decidibilità degli insiemi (linguaggi)
Una delle prime domande che ci siamo posti in questo corso è stata: "**esistono problemi che un elaboratore NON PUÒ risolvere?**"

Il concetto di problema risolvibile è strettamente legato al concetto di computabilità che possiamo definire inizialmente in questo modo: "**la computabilità è una caratteristica di un problema che lo rende risolvibile da una macchina in un tempo finito**". Chiaramente è utile far risolvere i problemi alla macchine perchè così non dobbiamo risolverli noi esseri umani.

Abbiamo data una definizione più precisa di computabilità attraverso la gerarchia di macchine astratte. L'idea è stata quella di definire macchine via via più "potenti", ovvero in grado di risolvere una gamma di problemi più ampia, fino a quando non si è più riuscito a trovare una macchina più potente dell'ultima trovata.

Una volta trovata la macchina più potente del nostro arsenale, possiamo definire una funzione come computabile (e il relativo problema come risolubile) se e solo se **per ogni ingresso** possibile, la macchina trovata è in grado di produrre l'uscita corretta, **in un numero finito di passi** (tempo finito). Se neanche la macchina più potente che abbiamo a disposizione riesce in questa impresa, allora il problema non è risolubile e la relativa funzione si dice non computabile.

In particolare siamo passati da:
- reti combinatorie
    - limite: non hanno memoria dello stato passato (vedi cassaforte)
- automi a stati finiti
    - limite: hanno una memoria finita che va definita a priori (vedi bilanciamento delle parentesi)
- Macchina di Turing
    - in sostanza un ASF con in più l'idea del nastro (memoria) infinitamente espandibile
        - quando la sequenza di eventi da ricordare non è limitabile a priori è salvabile sul nastro 
    - definita da tre funzioni:
        - mfn()
        - sfn()
        - dfn()
    - è in grado di (queste è il set di istruzioni base di un calcolatore):
        - Leggere un simbolo dal nastro
        - Scrivere sul nastro il simbolo specificato da *mfn()*
        - Transitare in un nuovo stato interno specificato da *sfn()*
        - Spostarsi sul nastro di una posizione nella direzione indicata da *dfn()*

Arrivati alla MdT ci siamo fermati in quanto essa è la macchina pià potente che l'essere umano è riuscito a trovare fino ad oggi. Questo non lo dico io ma lo afferma la **Tesi di Church-Turing**: "Non esiste alcun formalismo capace di risolvere una classe di problemi più ampia di quella risolta dalla Macchina di Turing". La macchina di Turing stabilisce quindi un limite per quanto riguarda che cosa puoi chiedere di risolvere ad una macchina (fino ad oggi).

**CONCLUSIONE**: un problema è risolubile (ovvero risolvibile in tempo finito da una MdT) è un problema la cui funzione caratteristica è computabile da una macchina di turing. Computabilità significa quindi calcolabilità di un output dato un input da parte di una MdT (in tempo finito).

**Concentrandoci ora sui problemi irrisolubili.** Esistono? Se si, sarebbe desiderabile capire quali sono in modo da poterli evitare.

La tesi di Church-Turing ci dice che se neanche la macchina di Turing riesce a risolvere un problema, quel problema è IRRISOLUBILE in quanto essa è la macchina più potente che abbiamo a disposizione. 

**NB**: Irrisolubilità non significa che ho ottengo una risposta sbagliata (a causa di bug), significa che la macchina non riesce a risolvere il problema in **tempo finito**, ovvero si inlooppa (loop infinito).
- NON c'è modo di distinguere una macchina inlooppata da una macchina che ci sta mettendo tanto tempo.
- a volte l'irrisolubilità di un problema si manifesta in base ai dati d'ingresso. I problemi vengono etichettati come irrisolubili anche se si riescono a risolvere in 99 casi su 100 (Bisogna poter contare sulla macchina). 

Abbiamo appurato che le funzioni definibili e le funzioni computabili hanno la stessa cardinalità. A questo punto, chiedersi se esistono dei problemi irrisolubili equivale a cercare almeno un problema definibile che però risulta non computabile, anche per un solo ingresso.

Se trovassimo quindi un problema definibile ma per cui la macchina di Turing va in loop, dimostreremmo che i due insiemi non coincidono e che quindi esistono dei problemi irrisolubili da una MdT. Esistono dei problemi di questo tipo? La risposta a questa domanda è si!

**Problema dell'halt della MdT**:

    Stabilire con una MdT se una seconda MdT T in input, con un generico ingresso X, si ferma oppure no.

Questo problema (funzione) può essere perfettamente definito, con un alfabeto **finito** di simboli. MA nel caso generale non è computabile (assurdo)

pezzi della DIM:
1. **funzione caratteristica del problema**: f_halt(m, x) = {1 se *m* con input *x* si ferma; 0 se *m* con input *x* entra in loop}
    - se Se *f_halt* è computabile, deve esistere una MdT *T_halt* capace di calcolarla 
2. Supponiamo che f_halt sia computabile e definiamo una nuova funzione *g_halt* che sfrutta *f_halt* nella sua definizione:
    - g_halt è funzione soltanto della generica MdT *n* (tutto codificato con dei naturali):
    - g_halt(n) = {1 se *f_halt(n,n) == 0*; *loop* altrimenti}
    - in pratica:
        - g si ferma e risponde 1 se la MdT *n* con ingresso *n* non si ferma
        - g va in loop se la MdT *n* con ingresso *n* si ferma
        - __fa il contrario di quello che dice la funzione *f_halt()*__
3. Consideriamo tra tutti gli ingressi possibili, *n=n_g*; ovvero proprio il numero che rappresenta la MdT *TG* che computa *g_halt*
    - stiamo dando come ingresso a TG la sua stessa descrizione, e in questo modo stiamo generando un assurdo
4. con *n=n_g* Abbiamo il seguente assurdo:
    - g_halt(n_g) = {1 se f_halt(n_g, n_g) == 0; *loop* se f_halt(n_g, n_g) == 1}
    - in pratica:
        - se TG si ferma (perché g_halt(n_g) dà come risultato 1), f_halt dice che TG non si ferma (perché f_halt (ng, ng) vale 0)
        - se TG non si ferma (perché g_halt(n_g) è indefinita), f_halt dice che TG si ferma (perché fHALT (ng ,ng) vale 1)

5. In pratica abbiamo che *g_halt* con ingresso *n=n_g* genera un assurdo, ma allora siccome gli ingressi sono validi, vuol dire che ad avere qualcosa che non va è la definizione stessa di g_halt; in particolare l'ipotesi iniziale che f_halt esista e sia computabile deve essere falsa.

In conclusione: esistono problemi che non siamo mai in grado di risolvere anche con tutto l'impegno del mondo in quanto neanche la MdT (la macchina più potente) ce la fa.

Nel nostro caso tutta questa trattazione ci è stata utile per arrivare al concetto di insiemi generabili (semidecidibili) e insiemi decidibili (simile al concetto di definibilità e computabilità di una funzione), nel contesto delle frasi appartenenti ad un linguaggio.

Noi siamo stati interessati a capire se: data una stringa, essa appartenga o meno all'insieme delle frasi valide di un **linguaggio**. Questo problema è rilevante in quanto ci permette di distinguere tra i linguaggi analizzabili sintatticamente da un compilatore/interprete (linguaggi decidibili) e quelli che non lo sono. Infatti:
- I linguaggi di programmazione sono costruiti a partire da un alfabeto **finito**
- Ogni linguaggio è caratterizzato dall’insieme (tipicamente **infinito**) delle sue frasi lecite
- Non basta quindi, che tale insieme possa essere generato (semi-decidibilità)
    - ossia, che si possano generare le frasi “lecite” previste
- è indispensabile poter decidere se una frase è giusta o sbagliata senza entrare in ciclo infinito
    - decidibilità
Se così non fosse, il compilatore o interprete potrebbe non rispondere, entrando in un ciclo infinito, davanti a una frase errata mentre ovviamente noi vogliamo che si fermi e segnali l’errore.

In conclusione, i linguaggi di nostro interesse sono quelli decidibili ovvero quelli per cui si riesce a decidere se una qualsiasi stringa appartenga o meno al linguaggio e per cui bisogna poter saper generare anche l'insieme complementare a quello delle frasi appartenenti al linguaggio. I linguaggi non decidibili sono da evitare in quanto provare a costruire un compilatore/interprete per questa categoria di linguaggi è un causa persa (problema irrisolubile)










## 2) Grammatiche, Classificazione di Chomsky e riconoscibilità dei linguaggi
Abbiamo detto che i linguaggi di nostro interesse in quanto riconoscibili da un interprete/compilatore, sono quelli decidibili... ok, ma che forma hanno quest'ultimi?

Introduciamo il concetto di grammatica e la classificazione di Chomsky.

Una grammatica è una notazione formale che ci permette di descrivere la sintassi di un linguaggio e come se ne possono derivare le frasi.
- Una grammatica è definita dalla quadrupla <alfabeto VT, alfabeto VN, S, Prod>
- Le grammatiche sono utili in quanto un linguaggio è spesso infinito e quindi non se ne possono elencare tutte le frasi .

Le grammatiche sono poi classificate in ciò che prende il nome di classificazione di Chomsky. Questa categorizzazione distingue vari tipi di grammatica in base alla struttura delle produzioni (che influenza direttamente le caratteristiche dei linguaggi producibili). Abbiamo in ordine di complessità decrescente:
**Tipo 0**: nessuna restrizione sulle produzioni
- In particolare, le regole possono specificare riscritture che **accorciano la forma di frase** corrente.
    - Regole del tipo: *G → epsilon* che fanno svanire un simbolo non terminale

**Tipo 1 | dipendenti dal contesto**: produzioni vincolate alla forma: *"beta" A "delta"* → *"beta" "alpha" "delta"*. **con *alpha* != *epsilon***
- A non terminale, lettere greche appartenenti V* tranne alpha che deve essere != epsilon
- *A* può essere sostituita da *alpha* solo nel contesto *"beta" A "delta"* 
    - **regole chirurgiche**
- Le riscritture **non ACCORCIANO MAI la forma di frase** corrente (*alpha != epsilon*)
- Le produzioni ammettono più di un metasimbolo a sinistra, ed ammettono anche lo scambio di due caratteri (seppure con più passaggi teoricamente)
- **NB**: questo tipo di grammatica **prevede anche vicoli ciechi/rami morti**, ovvero strade in cui non ci sono più regole applicabili. Questo non succede mai nel Tipo 2 e nel Tipo 3

**Tipo 2 | libere dal contesto**: produzioni vincolate alla forma: *A → alpha*; (con alpha che può anche essere epsilon)
- A non terminale, alpha appartente a V*
- Qui *A* può sempre essere sostituita da *alpha*, indipendentemente dal contesto (non esiste più l'idea stessa di contesto).
    - **regole a grana grossa, ogni volta che vedo il metasimbolo sostituisco**
- ho sempre e solo un metasimbolo A a sinistra, *alpha* può contenere più metasimboli in qualsiasi posizione
- **NB**: non c'è più il vincolo *alpha* != *epsilon*. **Si ritorna a poter accorciare le frasi**( a prima vista). 
    - In realtà, esiste un teorema che mi permette di **tirar via gli *epsilon* ottenendo una grammatica equivalente sempre di tipo 2** (vedi dopo). 
- **CASO PARTICOLARE**: se *alpha* ha la forma di *u*, oppure *u B v*, con *u* e *v* appartenenti a _VT*_ (solo sequenze di terminali), la **grammatica si dice lineare**

**Tipo 3 | grammatiche regolari**: produzioni vincolate alle forme lineari destre/sinistre
- lineare a destra 
    - *A → delta*
    - *A → delta B*
- lineare a sinistra
    - *A → delta*
    - *A → B delta*
- le frasi crescono da una parte sola. 
    - è possibile avere un singolo metasimbolo a destra o a sinistra (*delta* è composta da terminali) 
- non posso costruire frasi che crescono contemporaneamente in più punti
**NB**: IMPORTANTE:  produzioni o tutte lineari a destra, o tutte lineari a sinistra - non mischiate.
**NB**: anche qui alpha può essere epsilon ma anche qui esiste una grammatica equivalente con al più *S -> epsilon*.

Abbiamo poi che queste grammatiche rispettano una relazione gerarchica, in cui la più generale è quella di tipo 0 e la più specifica è qulla di tipo 3
- una grammatica di tipo 3 è quindi anche di tipo 2/1/0
- una grammatica di tipo 2 è quindi anche di tipo 1/0
- ecc...

**NB**: per questo il teorema riguardo le epsilon-rules è importante, fa rimanere valida questa relazione.
- **Come ottenere la grammatica equivalente senza *epsilon-rules*?** Data una grammatica G che contiene epsilon-rules, per generare una grammatica equivalente G', senza epsilon rules, **basta fare delle sostituzioni e semplificazioni**.

Torniamo ora alla domanda iniziale, volevamo capire **che forma hanno i linguaggi caratterizzati da un insieme delle frasi valide decidibile**. Essi sono proprio i linguaggi di tipo 1/2/3, i linguaggi di tipo 0 potrebbero (non è detto) invece essere non riconoscibili. 

Abbiamo quindi che per i linguaggi di tipo 0, non è garantita l'esistenza di una MdT capace di decidere se una frase appartiene o meno al linguaggio (decidibilità dell'insieme); per gli altri tipi di linguaggio invece questo è garantito e per questo motivo sono tutti riconoscibili.

Tuttavia, linguaggi di tipo più semplice, sono riconoscibili pagando un costo computazionale più basso, ed utilizzando una macchine più semplici rispetto alla MdT. Abbiamo che:
- tipo 1 -> MdT
- tipo 2 -> PDA
- tipo 3 -> ASF

In particolare i linguaggi di programmazione sono di tipo 2, e alcune sottoparti di quest'ultimi molto comuni (numeri, identificatori, keywords) sono di tipo 3 e quindi riconoscibili con particolare efficienza. Questi sono i due tipi di linguaggio che abbiamo imparato a riconoscere durante il corso.

A questo punto nasce il seguente problema: "dato un linguaggio e trovata una grammatica che lo descrive, mi piacerebbe sapere se la grammatica che ho trovato è quella più "economica" per il mio linguaggio, ovvero quella di tipo più basso". 

Infatti, una grammatica G1 potrebbe essere equivalente a una grammatica G2 se generano lo stesso linguaggio.
- tuttavia, una potrebbe però essere preferibile all’altra ad essa equivalente del costo del riconoscimento 
- se riesco ad esprimere il mio linguaggio con un tipo 3 è molto meglio rispetto ad una grammatica equivalente di tipo 2 (ASF costa di meno rispetto ad un PDA)

Capire se un **linguaggio** (non grammatica) è di Tipo 2 (o di Tipo 3) "solo guardandolo" in generale non è banale.
- Se hai la grammatica è facile, basta controllare il numero e la posizione dei metasimboli.
- Tuttavia, **di solito si parte dal LINGUAGGIO desiderato, non dalla grammatica** e solo successivamente si prova a definire una grammatica che lo genera.

**Come faccio quindi a capire dato un linguaggio e una grammatica esista o meno una grammatica più semplice, e quindi meno costosa?**
Per i soli linguaggi di tipo 2 e 3 (quelli di nostro interesse) un aiuto è dato dal **pumping lemma**!

Il pumping lemma dà una **condizione NECESSARIA, ma non sufficiente, perché un LINGUAGGIO (non grammatica) sia di Tipo 2** (o 3)
- Essendo solo una condizione necessaria, può essere usato per dimostrare "in negativo" che un linguaggio non è di Tipo 2 (o non è di Tipo 3)
- ... ma purtroppo non per affermarlo "in positivo"
    - se pumping lemma: false -> linguaggio non è di tipo 2 (o 3)
    - se pumping lemma: true  -> linguaggio potrebbe essere di tipo 2 (o 3), ma non è detto

**Idea**: In un linguaggio infinito, ogni stringa **sufficientemente lunga** deve avere una parte che si ripete (regola ricorsiva). Ergo, essa può essere "pompata" un qualunque numero di volte **ottenendo sempre altre stringhe del linguaggio**!
- nei linguaggi di tipo due la parte pompabili è "bilaterale" in quanto caratteristica fondamentale di questo tipo di linguaggi è il **self embedding** (un non terminale paninato tra due terminali)
- nei linguaggi di tipo uno la parte pompabili sarà "monolatera" in quanto le ricorsive possono far crescere una stringa o solo a destra o solo a sinistra

Abbiamo quindi che una stringa abbastanza lunga di un linguaggio di tipo 2 può essere suddivisa in 5 parti: "abcde" con:
- *a* ed *e* che rapresentano il prefisso e suffisso della parte centrale pompabile
- *b* e *d* rappresentano la parte pompabile bilateralmente in cui i due lati crescono insieme
- *c* rappresentano la parte paninata mediante self-embedding (e.g. espressione dentro parentesi)
- (tutte queste parti possono essere composte da più caratteri)  

Se pompando *b* e *d* un numero *i* volte (ab^icd^ie) la stringa che si ottiene non appartiene al linguaggio, allora possiamo sicuramente dire che quest'ultimo sicuramente non è di tipo 2 (e quindi neanche di tipo 3).

Analogamente, una stringa abbastanza lunga di tipo 3 può essere suddivisa in 3 parti: "abc" con:
- *a* ed *c* che rapresentano il prefisso e suffisso della parte centrale pompabile
- *b* che rappresenta la parte pompabile unilateralmente

Se pompando *b* un numero *i* volte (ab^ic) la stringa che si ottiene non appartiene al linguaggio, allora possiamo sicuramente dire che quest'ultimo sicuramente non è di tipo 3.

Alcuni esempi di linguaggi in cui il pumping lemma chiarisce le idee:
- L1 = {a^n b^n c^n } non è di Tipo 2 (quindi è almeno di Tipo 1)
- L2 = {a^p , p primo } non è di Tipo 3 (quindi è almeno di Tipo 2 ma in realtà neanche quello)
- L3 = { (^n a )^n } non è di tipo 3 (si sbilanciano le parentesi) ma sembra essere di tipo 2

**NOTA**: nota magari prova anche a scrivere mano a mano un esempio di grammatica di tipo 2/3 in cui mostri le parti che formano il prefisso e suffisso, ed anche la parte pompabili data da una regola ricorsiva 











## 3) Analisi Ricorsiva discendente e Grammatiche LL(1)
- PDA deterministici e non deterministici
- Analisi ricorsiva discendente
    - Approccio generativo top-down
    - Quando è applicabile 
        - forma normale di Greibach
        - più in generale quando i lookahead set di riscritture distinte di uno stesso metasimbolo sono diversi
        - se questa condizione non è vera devo fare backtracking e non ho più un costo di riconoscimento lineare
    - Grammatiche LL(1) e Determinismo

Abbiamo detto che i linguaggi che ci interessano sono quelli di tipo 2 e tipo 3 in quanto sono quelli utilizzati effettivamente nei linguaggi di programmazione data la "economicità" del loro riconoscimento. Ora discutiamo di come avviene il riconoscimento effettivo.

In particolare, vorrei parlare del solo riconoscimento dei linguaggio di tipo due in quanto quelli regolari sono riconoscibili in maniera molto semplice. Basta passare alla forma su ASF (che è minimizzabile e in cui si può eliminare il non determismo) e considerare la tabella delle transizioni di stato ad ogni carattere letto.

Il riconoscimento dei linguaggi di tipo 2 invece è un po' più complicato (d'altronde richiede un PDA che memorizza le sequenze non limitabili a priori). In particolare la parte problematica è che **anche i PDA possono essere non deterministici**, e questo non determismo porta ad un incremento nella complessità computazionale del riconoscimento (N^3, N^2 se non ambigua... comunque sovra-lineare).
- Dato uno stato Q0, con simbolo in cima allo stack Z e ingresso x, un PDA non det. può:
    - portarsi in più stati futuri
        - *sfn(Q0, x, Z) = { (Q1,Z1), (Q2,Z2), … (Qk, Zk) }*
    - oppure, optare se leggere o non leggere il simbolo di ingresso x a causa della presenza di una mossa spontanea 
        - sono definite entrambe: *sfn(Q0, x, Z)* e *sfn(Qi, epsilon, Z)*

I linguaggi deterministici (riconoscibili mediante un PDA deterministico) invece, sono riconoscibili con costo lineare rispetto alla lunghezza della stringa da riconoscere. **È desiderabile quindi utilizzare sempre un PDA deterministico** per il riconoscimento dei linguaggi di tipo 2

Purtroppo, non esiste un equivalente del teorema del martello per i PDA, inoltre:
```
La classe dei linguaggi riconosciuti da un PDA non-deterministico coincide con la classe dei linguaggi context-free. Qualunque linguaggio context free può sempre essere riconosciuto da un opportuno PDA non-determistico. 
```
Ma è anche dimostrato che **esistono anche dei linguaggi context-free che possono essere riconosciuti solamente tramite un PDA non-deterministico**. Siamo quindi costretti a scegliere se accettare il costo computazionale elevato di un PDA non det., oppure restringere l'insieme dei linguaggi che vogliamo riconoscere a quello dei deterministici. Delle due, noi abbiamo scelto la seconda in quanto i linguaggi non det. abbiamo detto che sono per loro natura "brutti" e di poco interesse pratico.




A questo punto è naturale chiedersi:
```
Che forma deve avere una grammatica di un linguaggio deterministico?
```
Detta subito, sono le grammatiche LL(k), e più in generale, LR(k). Ma procediamo per passi.

Nel corso il primo algoritmo che abbiamo studiato per il riconoscimento di linguaggi di tipo 2 deterministici è **l'analisi ricorsiva discendente**!
- si introduce __una funzione per ogni metasimbolo__ della grammatica e la si invoca ogni volta che si incontra quel metasimbolo.
- ogni funzione copre le regole del proprio metasimbolo, ossia **riconosce il sotto-linguaggio corrispondente**:
    - termina normalmente, o restituisce un segno di successo, se incontra simboli coerenti con le proprie regole
    - abortisce, o restituisce un qualche segno di fallimento, se incontra simboli che non corrispondono alle proprie regole.

**es**: mostra pseudocodice parentesi bilanciate S -> ( S ) | c

- evidenzia il comportamento dello stack con le chiamate ricorsive

Notare le parole chiave:
- **discendente/top-down**: si scende dallo scopo alla frase finale da parsare (approccio a generazione piuttosto che a riduzione)
- **ricorsiva**: ogni metasimbolo, per riconoscere il suo sottolinguaggio, presuppone che quello dei metasimboli appartenenti alla sua regola di produzione sia già stato riconosciuto ricorsivamente
- (L'analisi LR invece sarà opposta)

**NB**: **questo algoritmo non funziona con ogni tipologia di grammatica**. Anche nell'esempio semplice appena mostrato, vi è una assunzione di base: per poter applicare analisi ricorsiva discendente è necessario che:
```
le parti destre di produzioni relative ad uno stesso metasimbolo, incomincino tutte con un simbolo terminale distinto l'una dalle altre.
```
Questa condizione mi permette di riconoscere la produzione giusta da applicare, e quindi che funzione invocare, in **maniera deterministica**.
- Puoi fare vedere come esempio-aggiuntivo l'algoritmo di recursive descent non deterministico di questo video: https://youtu.be/ENKT0Z3gldE?si=drAJf970ANFIK3EZ  

Definiamo quindi una prima categoria di grammatica che descrive un linguaggio deterministico: le grammatiche LL(k). **Si definiscono grammatiche LL(k) quelle che sono analizzabili in modo deterministico** (con analisi ricorsiva discendente):
- procedendo Left to right nella lettura dell'input
- applicando la Left-most derivation (sequenzializzazione nel riconoscimento dei sottolinguaggi)
    - costruisce prima il sottoalbero di derivazione più a sinistra
- **guardando avanti nell'input di al più k simboli** per discriminare quale produzione applicare

In realtà è possibile generalizzare l'idea di simbolo iniziale in quanto, quest'ultimi potrebbero essere nascosti da:
- **metasimboli**, che vanno quindi sviluppati in una o più riscritture per capire il simbolo terminale iniziale
- **parti annullabili**, se un metasimbolo è riscrivibile come epsilon, tutti i simboli (terminali e non) che venivano dopo di lui, e che quindi pensavano di non essere iniziali, si ritrovano ad essere delle valide iniziali per la riscrittura e quindi vanno considerate

Una definizione più generale di grammatica LL(1) è quindi una grammatica in cui: **i lookahead set delle rescritture relative allo stesso metasimbolo sono distinti**.

Con Lookahead-set(A->alpha) definito come l'**unione** degli insiemi:
- FIRST(Alpha): l’insieme dei simboli terminali iniziali che si riescono a trovare applicando * produzioni ad alpha.
- FOLLOW(A):    l'insieme dei simboli terminali tutti che possono seguire A (se A annullabile) **in qualsiasi produzione**

**OSS**: questa generalizzazione potrebbe sembrare non necessaria in quanto ogni grammatica context-free è riscrivibile in **forma normale di Greibach**, tuttavia abbiamo anche imparato che non sempre è possibile riorganizzare la grammatica senza influenzare la semantica del linguaggio (grammatica diversa == derivazione diversa == AST diverso; ricorda sempre ricorsione sinistra)

La necessità di poter vedere le iniziali delle produzioni di una grammatica è una **condizione stringente**. È infatti dimostrabile che:
**esistono linguaggi DETERMINISTICI (e quindi riconoscibili con costo linerare) che non sono LL(k) per nessun k**. Quindi con analisi ricorsiva discendente non copriamo l'interezza dei linguaggi di tipo 2 deterministici. **La copertura maggiore c'è la da l'analisi LR(k)** in cui si ha un approccio opposto e più potente:
- approccio a riduzione in cui dalla frase si cerca di arrivare allo scopo

es ricorsione sinistra:
- con LL(1) le produzioni ricorsive a sinistra, della forma *A ::= A alpha | a*, **danno sempre luogo a starter symbol set identici per le due alternative** e quindi non è possibile l'analisi ricorsiva discendente
- LR(1) analizzerebbe tranquillamente questa grammatica invece.

Non parlerò però di questo tipo di analisi dato che voglio, passare ad altri argomenti...







4. Chiusure
    1. Tra le varie cose, puoi far vedere come esempio finale la realizzazione della struttura di controllo until; sia in js che scala


5. Modelli iterativi e ricorsivi + TRO


6. Lazyness
    1. Unified memory con allocazione lazy delle pagine nel posto giusto
