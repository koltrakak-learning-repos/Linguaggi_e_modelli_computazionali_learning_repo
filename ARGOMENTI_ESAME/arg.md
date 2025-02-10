## 1) Teoria della computabilità e decidibilità degli insiemi (linguaggi)
- Computabilità
    - definizione preliminare
    - definizione tramite gerarchia di macchine astratte
        - reti combinatorie
        - ASF
        - MdT
    - tesi di Church Turing
- Problemi irrisolubili
    - Come sono definiti? (tesi di Church Turing)
    - cosa succede alla MdT che opera su un problema irrisolubile?
    - Esistono?
        - insiemi dei problemi definibili e dei problemi computabili hanno la stessa cardinalità?
        - coincidono? No!
    - Cerchiamo un problema definibile ma non computabile
    - Problema dell'halt della MdT
    - Esistono problemi che non siamo mai in grado di far risolvere ad una Macchina... la MdT non ce la fà
- Computabilità e linguaggi
    - definire il concetto di computabilità è necessario per capire quali sono i linguaggi che è possibile riconoscere con un compilatore/interprete
    - problema del decidere se una frase appartiene o meno ad un linguaggio (analisi sintattica)
    - insiemi semidecidibili
    - perchè non bastano
    - insiemi decidibili e teorema

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

Innanzitutto, la tesi di Church-Turing ci dice che se neanche la macchina di Turing riesce a risolvere un problema (anche per un solo ingresso), quel problema è IRRISOLUBILE in quanto essa è la macchina più potente che abbiamo a disposizione. 

**NB**: Irrisolubilità non significa che ho ottengo una risposta sbagliata (a causa di bug), significa che la macchina non riesce a risolvere il problema in **tempo finito**, ovvero si inlooppa (loop infinito).
- NON c'è modo di distinguere una macchina inlooppata da una macchina che ci sta mettendo tanto tempo.
- a volte l'irrisolubilità di un problema si manifesta in base ai dati d'ingresso. I problemi vengono etichettati come irrisolubili anche se si riescono a risolvere in 99 casi su 100 (Bisogna poter contare sulla macchina). 

**OSS**: La capacità di andare in loop è l'ipotesi fondamentale per risolvere la classe maggiore di problemi possibile. Una macchina che non va in loop è per sua natura meno potente.

Abbiamo poi che chiedersi se un problema è irrisolubile è equivalente a chiedersi se la relativa funzione caratteristica è innanzitutto definibile, e inoltre se quest'ultima sia computabile da una MdT.

È dimostrato che le funzioni definibili (con un alfabeto finito di simboli) e le funzioni computabili hanno la stessa cardinalità. La MdT non tralascia alcuna funzione.  A questo punto, chiedersi se esistono dei problemi irrisolubili equivale a cercare almeno un problema definibile che però risulta non computabile, anche per un solo ingresso.

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

Abbiamo che un linguaggio deve essere innanzitutto generabile, ovvero una MdT deve essere in grado di produrne le frasi. Purchè questo sia verò abbiamo che la funzione di corrispondenza con i naturali deve essere computabile. Insiemi di frasi di questo tipo si dicono **generabili** o **semidecidibili**.

Questo non basta dato che non ci basta poter decidere se una stringa appartenga al linguaggio, ma dobbiamo anche riuscire a decidere il contrario, quando una stringa NON appartiene al linguaggio.
- es. riconoscimento di un dispari con una macchina che riconosce solo i pari

Stiamo cioè chiedendo che la funzione di appartenenza all'insieme che definisce il linguaggio sia computabile. Insiemi di questo tipo si dicono **decidibili** e comprendono tutti i linguaggi di nostro interesse.
- disegna prima grafico insiemistico dei linguaggi 

Per capire se un insieme è decidibile o meno abbiamo il seguente teorema:
- Un insieme S è decidibili se e solo se sia S che il suo complemento N-S, sono generabili.

In conclusione, i linguaggi di nostro interesse sono quelli decidibili ovvero quelli per cui si riesce a decidere se una qualsiasi stringa appartenga o meno al linguaggio e per cui bisogna poter saper generare anche l'insieme complementare a quello delle frasi appartenenti al linguaggio. I linguaggi non decidibili sono da evitare in quanto provare a costruire un compilatore/interprete per questa categoria di linguaggi è un causa persa (problema irrisolubile)










## 2) Grammatiche, Classificazione di Chomsky e riconoscibilità dei linguaggi
- Che forma hanno i linguaggi decidibili?
    - Grammatiche Come specificare tutte le frasi appartenenti ad un linguaggio 
        - quadrupla <VT,VN,P,S>
- Classificazione di Chomsky
    - tipo 0
        - forme di frase possono accorciarsi
    - tipo 1
        - non ammette più l'accorciarsi delle forme di frase
        - ammette lo scambio di due metasimboli
        - contempla vicoli ciechi nella derivazione
    - tipo 2
        - teorema self embedding
        - teorema per epsilon
        - un solo metasimbolo a sinista
        - fa crescere due cose contemporaneamente
    - tipo 3
        - fa crescere la frase da una parte sola
    - relazione gerarchica delle grammatiche
    - grammatiche equivalenti
    - tipo di una grammatica e tipo di un linguaggio
- Riconoscibilità dei linguaggi
    - collegamento con computabilità, i linguaggi decidibii sono sicuramente quelli di tipo 1,2 e 3; tipo 0 non sempre decidibili
    - quali macchine riconoscono quale linguaggio?
        - tipo 0: se decidibile, MdT
        - tipo 1: MdT
        - tipo 2: PDA
        - tipo 3: ASF
        - per ottenere efficenza nel riconoscimento bisogno usare almeno linguaggi di tipo 2 (**non ci sono più vicoli ciechi**)
        - per parti estremamente comuni (numeri, identificatori, ... tutti i token insomma), sufficente linguaggio di tipo 3
- Pumping lemma
    - dato un linguaggio e trovata una grammatica che lo descrive come possiamo capire se quest'ultima è la grammatica più econominca?
    - idea del pumping lemma
    - esempio L = {1^n, 2^n, 3^n} 





Abbiamo detto che i linguaggi di nostro interesse (in quanto riconoscibili da un interprete/compilatore) sono quelli decidibili... ok, ma che forma hanno quest'ultimi?

Introduciamo il concetto di grammatica, che ci permette di descrivere le frasi appartenenti ad un linguaggio, e la classificazione di Chomsky che categorizza le grammatiche in varie tipologie.

Una grammatica è una notazione formale che ci permette di descrivere la sintassi di un linguaggio e come se ne possono derivare le frasi.
- Una grammatica è definita dalla quadrupla <alfabeto VT, alfabeto VN, S, Prod>
- Le grammatiche sono utili in quanto un linguaggio è spesso infinito e quindi non se ne possono elencare tutte le frasi.
- fai un es. a caso

Le grammatiche sono poi classificate in ciò che prende il nome di classificazione di Chomsky. Questa categorizzazione distingue vari tipi di grammatica in base alla struttura delle produzioni (che influenza direttamente le caratteristiche dei linguaggi producibili).

Abbiamo in ordine di complessità decrescente:
**Tipo 0**: nessuna restrizione sulle produzioni
- In particolare, le regole possono specificare riscritture che **accorciano la forma di frase** corrente.
    - Regole del tipo: *G → epsilon* che fanno svanire un simbolo non terminale

**Tipo 1 | dipendenti dal contesto**: produzioni vincolate alla forma: *"beta" A "delta"* → *"beta" "alpha" "delta"*. **con *alpha* != *epsilon***
- *A* non terminale, lettere greche appartenenti V* tranne alpha che deve essere != epsilon
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
- con *A, B* appartenenti a *VN*, *delta* appartenente a _VT*_

- le frasi crescono da una parte sola. 
    - è possibile avere un singolo metasimbolo a destra o a sinistra (*delta* è composta da terminali) 
- non posso costruire frasi che crescono contemporaneamente in più punti
**NB**: IMPORTANTE:  produzioni o tutte lineari a destra, o tutte lineari a sinistra - non mischiate.
**NB**: anche qui alpha può essere epsilon ma anche qui esiste una grammatica equivalente con al più *S -> epsilon*.

**fai un esempio per ogni tipo di grammatica**: ...





Abbiamo poi che queste grammatiche rispettano una **relazione gerarchica**, in cui la più generale è quella di tipo 0 e la più specifica è qulla di tipo 3
- una grammatica di tipo 3 è quindi anche di tipo 2/1/0
- una grammatica di tipo 2 è quindi anche di tipo 1/0
- ecc...

**Fai un disegno:**

**NB**: per questo il teorema riguardo le epsilon-rules è importante, fa rimanere valida questa relazione.
- **Come ottenere la grammatica equivalente senza *epsilon-rules*?** Data una grammatica G che contiene epsilon-rules, per generare una grammatica equivalente G', senza epsilon rules, **basta fare delle sostituzioni e semplificazioni**.

Poiché le grammatiche sono in relazione gerarchica, può accadere che un linguaggio possa essere generato da più grammatiche, anche di tipo diverso. Grammatiche di questo tipo si dicono **equivalenti**, dato che che generano lo stesso linguaggio. Tuttavia, una grammatica potrebbe essere preferibile ad un'altra ad esse equivalenti per quanto riguarda il costo di riconoscimento.

Non è detto infatti che la prima grammatica che si trova per generare un dato linguaggio sia necessariamente la migliore (più semplice e meno costosa da riconoscere). Definiamo quindi oltre alle tipologie di grammatiche, le **tipologie di un linguaggio**. Diciamo che un linguaggio è di un certo quando la grammatica più semplice che lo descrive è di quel tipo. 




Torniamo ora alla domanda iniziale, volevamo capire **che forma hanno i linguaggi caratterizzati da un insieme delle frasi valide decidibile**. Essi sono proprio i linguaggi di tipo 1/2/3, i linguaggi di tipo 0 potrebbero (non è detto) invece essere non riconoscibili. 

Abbiamo quindi che per i linguaggi di tipo 0, non è garantita l'esistenza di una MdT capace di decidere se una frase appartiene o meno al linguaggio (decidibilità dell'insieme); per gli altri tipi di linguaggio invece questo è garantito e per questo motivo sono tutti riconoscibili.

Tuttavia, linguaggi di tipo più semplice, sono riconoscibili pagando un costo computazionale più basso, ed utilizzando una macchine più semplici rispetto alla MdT. Abbiamo che:
- tipo 1 -> MdT
- tipo 2 -> PDA
- tipo 3 -> ASF

In particolare i linguaggi di programmazione sono tutti al massimo di tipo 2 (**evitiamo backtracking del tipo 1**), e alcune sottoparti di quest'ultimi molto comuni (numeri, identificatori, keywords; in genere i token) sono di tipo 3 e quindi riconoscibili con particolare efficienza. Questi sono i due tipi di linguaggio che abbiamo imparato a riconoscere durante il corso.




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

**Idea**: In un linguaggio infinito, **OGNI stringa sufficientemente lunga** deve avere una parte che si ripete (regola ricorsiva). Ergo, essa può essere "pompata" un **numero QUALUNQUE** (per ogni i>=0) di volte **ottenendo SEMPRE altre stringhe del linguaggio**!
- nei linguaggi di tipo due la parte pompabile è "bilaterale" in quanto caratteristica fondamentale di questo tipo di linguaggi è il **self embedding** (un non terminale paninato tra due terminali)
- nei linguaggi di tipo uno la parte pompabili sarà "monolatera" in quanto le ricorsive possono far crescere una stringa o solo a destra o solo a sinistra

Se trovata **anche solo una stringa** sufficientemente lunga, che per **anche solo un numero di pompaggio** produce una stringa non appartenente al linguaggio iniziale: allora il pumping lemme non è rispettato è il linguaggio non è di tipo 2/3. 

**Semi-formalmente**:

Considerando una stringa qualunque "lunga sufficientemente" (|z| >= N, con N pumping length) di un linguaggio di tipo che supponiamo essere di tipo 2:
- Essa può essere suddivisa in 5 parti: "abcde" con:
    - *a* ed *e* che rapresentano il prefisso e suffisso della parte centrale pompabile
    - *b* e *d* rappresentano la parte pompabile bilateralmente in cui i due lati crescono insieme
    - *c* rappresentano la parte "paninata" mediante self-embedding (e.g. espressione dentro parentesi)
    - (tutte queste parti possono essere composte da più caratteri)  
    - **NB**: la parte centrale **|bcd| <= N**

Se pompando *b* e *d* un numero ARBITRARIO *i* volte (ab^icd^ie) la stringa che si ottiene NON appartiene al linguaggio (anche per un solo *i*), allora possiamo sicuramente dire che quest'ultimo sicuramente non è di tipo 2.

Analogamente, una stringa sufficientemente lunga di un linguaggio che supponiamo essere di tipo 3 può essere suddivisa in 3 parti: "abc"; con:
- *a* ed *c* che rapresentano il prefisso e suffisso della parte centrale pompabile
- *b* che rappresenta la parte pompabile unilateralmente
- **NB**: in questo caso **è |ab| <= N** 

Se pompando *b* un numero *i* volte (ab^ic) la stringa che si ottiene non appartiene al linguaggio, allora possiamo sicuramente dire che quest'ultimo sicuramente non è di tipo 3.

Alcuni esempi di linguaggi in cui il pumping lemma chiarisce le idee:
- L1 = {a^n b^n c^n } non è di Tipo 2 (quindi è almeno di Tipo 1)
- L2 = {a^p , p pari } non è di Tipo 3 (quindi è almeno di Tipo 2 ma in realtà neanche quello)
- L3 = { (^n a )^n } non è di tipo 3 (si sbilanciano le parentesi) ma sembra essere di tipo 2










## 3) Analisi Ricorsiva discendente e Grammatiche LL(1)
- PDA
    - definizione e confronto con ASF
    - esempio di uso dello stack con esempio su bilanciamento delle parentesi
    - PDA non deterministici
        - costo computazionale
        - distinzione tra linguaggi deterministici e non det. 
        - teoremi su linguaggi di tipo 2 e determinismo
        - che forme hanno le grammatiche dei linguaggi deterministici?
- Analisi ricorsiva discendente
    - Definizione (approccio generativo top-down)
    - esempio semplice bilanciamento delle parentesi
    - esempio più completo parsing di IfStatement
- Grammatiche LL(k) e determinismo
    - Applicabilità analisi ricorsiva discendente
        - forma normale di Greibach
        - (se questa condizione non è vera posso sempre fare backtracking ma non ho più un costo di riconoscimento lineare)
    - Definizione grammatiche LL(k) e LL(1)
        - leggo L to R, Derivo L e guardo di k simboli in avanti
        - ok, ma cosa mi dicono i simboli che guardo?
    - Generalizzazione lookahead set di riscritture distinte di uno stesso metasimbolo sono diversi
        - esempio conflitti dovuti a epsilon e a mascheramenti da parte di metasimboli
        - interi blocchi annullabili che producono conflitti subdoli  
- Copertura dei linguaggi deterministici con grammatiche LL(K)
    - cenni analisi LR(k)
    - esempio ricorsione sinistra
        - in teoria sempre trasformabile in ricorsione destra, ma cambia anche la semantica


Abbiamo detto che i linguaggi che ci interessano sono quelli di tipo 2 e tipo 3 in quanto sono quelli utilizzati effettivamente nei linguaggi di programmazione data la "economicità" del loro riconoscimento. Ora discutiamo di come avviene il riconoscimento effettivo.

In particolare, vorrei parlare del solo riconoscimento dei linguaggio di tipo due in quanto quelli regolari sono riconoscibili in maniera molto semplice. Basta passare alla forma su ASF (che è minimizzabile e in cui si può eliminare il non determismo) leggendo la grammatica top-down/bottom-up, e considerare la tabella delle transizioni di stato ad ogni carattere letto.

Il riconoscimento dei linguaggi di tipo 2 invece è un po' più complicato (d'altronde richiede un PDA che memorizza le sequenze non limitabili a priori). 

Innanzitutto essi richiedono una macchina riconoscitrice più complicata di un semplice ASF; un PDA. Un PDA può essere pensato come ad un ASF + stack, più nel dettaglio, è definito dalla seguente sestupla: <A, S, S_0, sfn, Z, Z_0> con la *sfn*: (A unito epsilon)xSxZ → SxZ*.

Lo stato futuro (e la nuova configurazione dello stack) adesso è  funzione sia del simbolo d’ingresso sia di quello attualmente in cima allo stack (e, chiaramente, dello stato corrente).
- Adesso si può giocare da due parti, posso avere un gran numero di stati e usare poco lo stack o viceversa. Spesso è più conveniente il secondo metodo con uno stato di accumulo e uno stato di decrescita.
- Il PDA fa sempre pop dallo stack ad ogni simbolo letto, (può decidere se e quanti push fare)

L'idea che rende il PDA più potente è che si può superare il limite di memoria finita degli RSF appoggiandosi allo stack (che è illimitatamente espandibile)
- **mostra esempio del bilanciamento delle parentesi**
    - stato di push e stato di pop
    - tabella con 3 input (A, S, Z) e due output (S, Z*)

La parte problematica del riconoscimento dei linguaggi di tipo 2 è che **anche i PDA possono essere non deterministici**, e questo non determismo porta ad un incremento nella complessità computazionale del riconoscimento (N^3, N^2 se non ambigua... comunque sovra-lineare).
- Dato uno stato Q0, con simbolo in cima allo stack Z e ingresso x, un PDA non det. può:
    - portarsi in più stati futuri
        - *sfn(Q0, x, Z) = { (Q1,Z1), (Q2,Z2), … (Qk, Zk) }*
    - oppure, optare se leggere o non leggere il simbolo di ingresso x a causa della presenza di una mossa spontanea 
        - sono definite entrambe: *sfn(Q0, x, Z)* e *sfn(Qi, epsilon, Z)*
            - In tal caso, infatti, l'automa può sia leggere x, sia non farlo (non è importante dove si finisce)

I PDA deterministici invece, riconoscono con costo lineare rispetto alla lunghezza della stringa. **È desiderabile quindi utilizzare sempre un PDA deterministico** per il riconoscimento dei linguaggi di tipo 2

Purtroppo, non esiste un equivalente del teorema del martello per i PDA, inoltre:
```
La classe dei linguaggi riconosciuti da un PDA non-deterministico coincide con la classe dei linguaggi context-free. Qualunque linguaggio context free può sempre essere riconosciuto da un opportuno PDA non-determistico. 
```

Ma è anche dimostrato che 
```
**esistono anche dei linguaggi context-free che possono essere riconosciuti SOLAMENTE tramite un PDA non-deterministico**.
```

Distinguiamo quindi tra linguaggi di tipo 2 deterministici e non deterministici.

Siamo costretti a scegliere se accettare il costo computazionale elevato di un PDA non det., oppure restringere l'insieme dei linguaggi che vogliamo riconoscere a quello dei deterministici. Delle due, noi abbiamo scelto la seconda in quanto i linguaggi non det. abbiamo detto che sono per loro natura "brutti" e di poco interesse pratico.


A questo punto è naturale chiedersi:
```
Che forma deve avere una grammatica di un linguaggio deterministico?
```
Detta subito, sono le grammatiche LL(k), e più in generale, LR(k). Ma procediamo per passi e vediamo prima come si può implementare in codice un riconoscitore di linguaggi di tipo 2 deterministici.

Nel corso il primo algoritmo che abbiamo studiato per il riconoscimento di linguaggi di tipo 2 deterministici è **l'analisi ricorsiva discendente**!

L'idea è quella di pilotare uno stack con il meccanismo delle chiamate (ricorsive) a funzione. Quest'ultime:
- generano un record di attivazione quando invocate (push sullo stack)
- distruggono quest'ultimo quando ritornano (pop dallo stack)
- Queste due operazioni corrispondono agli stati di crescita e calo del nostro PDA

Più nel dettaglio l'algoritmo funziona così:
- si introduce __una funzione per ogni metasimbolo__ della grammatica e la si invoca ogni volta che si incontra quel metasimbolo.
- ogni funzione copre le regole del proprio metasimbolo, ossia **riconosce il sotto-linguaggio corrispondente**:
    - termina normalmente, o restituisce un segno di successo, se incontra simboli coerenti con le proprie regole
    - abortisce, o restituisce un qualche segno di fallimento, se incontra simboli che non corrispondono alle proprie regole.

**es**: mostra pseudocodice parentesi bilanciate S -> ( S ) | c
- evidenzia il comportamento dello stack con le chiamate ricorsive

Notare le parole chiave:
- **discendente/top-down**: si scende dallo scopo alla frase finale da parsare
    - **approccio a generazione** piuttosto che a riduzione (L'analisi LR sarà opposta)
- **ricorsiva**: ogni metasimbolo, per riconoscere il suo sottolinguaggio, presuppone che quello dei metasimboli appartenenti alla sua regola di produzione sia già stato riconosciuto ricorsivamente


**es più completo**: Mostra esempio del linguaggio degli if innestati
- IfStatement := 'if' '(' Expression ')' IfStatement ['else' IfStatement] | 'done'


**NB**: **questo algoritmo non funziona con ogni tipologia di grammatica**. Anche nell'esempio semplice appena mostrato, vi è una assunzione di base: per poter applicare analisi ricorsiva discendente in maniera deterministica (costo di riconoscimento lineare) è necessario che:
```
le parti destre di produzioni relative ad uno stesso metasimbolo, incomincino tutte con un simbolo terminale distinto l'una dalle altre.
```
Questa condizione mi permette di riconoscere la produzione giusta da applicare, e quindi che funzione invocare, in **maniera deterministica** facendomi guidare dalle iniziali delle varie produzioni.
- i simboli iniziali devono essere visibili
- grammatica in forma normale di Greibach

Chiaramente:
- non tutte le grammatiche context-free rispettano questo requisito
- neanche tutte le grammatiche che descrivono un linguaggio deterministico come ci insegna l'analisi LR(k)
- Tuttavia molte grammatiche di linguaggi utili si! 

Identifichiamo quindi una classe ristretta di **grammatiche contextfree, che garantisca il determinismo dell’analisi ricorsiva discendente**. 

Rispondendo alla domanda iniziale, definiamo una prima categoria di grammatiche che descrivono i linguaggio context-free deterministici: le grammatiche LL(k).

**Si definiscono grammatiche LL(k) quelle che sono analizzabili in modo deterministico** (con analisi ricorsiva discendente):
- procedendo Left to right nella lettura dell'input
- applicando la Left-most derivation (sequenzializzazione nel riconoscimento dei sottolinguaggi)
    - costruisce prima il sottoalbero di derivazione più a sinistra
- **guardando avanti nell'input di al più k simboli** per discriminare quale produzione applicare

**OPZIONALE**: Ricordiamo che il determinismo è desiderabile perchè ci permette di ridurre il costo computazionale del riconoscimento del linguaggio. Esso però non è strettamente necessario per un riconoscitore che (seppure lentissimo) funzioni. Puoi fare vedere come esempio-aggiuntivo l'algoritmo di recursive descent non deterministico di questo video: https://youtu.be/ENKT0Z3gldE?si=drAJf970ANFIK3EZ  




In realtà è possibile **generalizzare l'idea di simbolo iniziale** di una produzione in quanto, quest'ultimi potrebbero essere nascosti da:
- **metasimboli**, che vanno quindi sviluppati in una o più derivazioni per capire il simbolo terminale iniziale
- **parti annullabili**, se un metasimbolo è riscrivibile come epsilon, tutti i simboli (terminali e non) che venivano dopo di lui (che pensavano di non essere iniziali) si ritrovano ad essere delle valide iniziali per la produzione e quindi vanno considerati

es:
    A -> Ba | CD        // conflitto dovuto a epsilon
    B -> bB | D         // conflitto dovuto a mascheramento da parte di D
    C -> cC | epsilon
    D -> b

NB: si possono avere anche conflitti più subdoli con le epsilon se un intera riscrittura può annullarsi

Per tenere conto di queste problematiche, una definizione più generale di grammatica LL(1) è quindi una grammatica in cui: **i lookahead-set delle produzioni relative allo stesso metasimbolo sono distinti**.

Con con lookahead set di una produzione *Lookahead-set(A->alpha)* definito come l'**unione** degli insiemi:
- SS(Alpha): l’insieme dei simboli terminali iniziali che si riescono a trovare applicando zero o più produzioni ad alpha.
    - tengono conto dei metasimboli che nascondono l'iniziale di una riscrittura
- FOLLOW(A): l'insieme dei simboli terminali che possono seguire A (se _alpha_ annullabile) **in qualsiasi sequenza di derivazione A PARTIRE DALLO SCOPO**
    - tengono conto dei terminali che seguono un simbolo annullabile

**es che non mi ricorderò mai**:
Produzioni:
    S → A B
    A → P Q | B C
    P → p P | e
    Q → q Q | e 
    B → b B | d
    C → c C | f

La produzione problematica è quella di A in quanto l'intero blocco PQ è annullabile

    SS(P) = {p}
    SS(Q) = {q}
    SS(PQ) = SS(P) U SS(Q) = {p,q}
    SS(BC) = SS(B) = {b,d}

    FIRST(P) = {p,e}
    FIRST(Q) = {q,e}
    FIRST(PQ)= {p,q,e}
    FIRST(BC)= SS(B) = {b,d}

    FOLLOW(A) = {b, d}
    FOLLOW(P) = {q, b, d}   // perchè dopo Q ci può essere una B (S -> AB)
    FOLLOW(Q) = {b, d}      // stesso motivo
    ...


    DS(A → PQ) = SS(PQ) U FOLLOW(A) = {p,q, b,d}            // perché PQ genera e
    DS(A → BC) = SS(BC) = {b,d}                             // perché BC non genera e

**conflitto**:
- quando posso riscrivere A come PQ?
    - Sicuramente quando davanti ho una p od una q, ma anche quando ho una b ed una d (iniziali di B proveniente dalla regola S -> AB con A che si è annullato)
- quanto posso riscrivere A come BC? solamente quando davanti ho una b od una d. Non ho nulla che mi si può annullare davanti? 

**In conclusione**:
```
CONDIZIONE NECESSARIA E SUFFICIENTE perché una grammatica sia LL(1) è che i Director Symbols set relativi a produzioni alternative siano disgiunti (DS(alpha_1) != DS(alpha_2) )
```

**OSS**:
questa generalizzazione potrebbe sembrare non necessaria in quanto ogni grammatica context-free è riscrivibile in **forma normale di Greibach**, tuttavia abbiamo anche imparato che non sempre è possibile riorganizzare la grammatica senza influenzare la semantica del linguaggio!
- grammatica diversa == derivazione diversa == AST diverso

Esempio classico è quello della **ricorsione sinistra**:
- non è mai analizzabile in maniera LL(1)
- ma trasformarla in ricorsione destra **cambia l'associatività** in fase di analisi semantica
- (opzionale) mostra trucco EBNF per espressioni



La necessità di avere simboli iniziali distinti in tutte le produzioni alternative di uno stesso metasimbolo è una **condizione stringente**. È infatti dimostrabile che:
```
esistono linguaggi DETERMINISTICI (e quindi riconoscibili con costo linerare) che tuttavia non sono LL(k) per nessun k.
```

**Conclusione**: con analisi ricorsiva discendente non copriamo l'interezza dei linguaggi di tipo 2 deterministici.

**La copertura maggiore c'è la da l'analisi LR(k)** in cui si ha un approccio opposto e più potente:
- approccio a riduzione in cui dalla frase si cerca di arrivare allo scopo

es ricorsione sinistra:
- con LL(1) le produzioni ricorsive a sinistra, della forma *A ::= A alpha | a*, **danno sempre luogo a starter symbol set identici per le due alternative** e quindi non è possibile l'analisi ricorsiva discendente
- LR(1) analizzerebbe tranquillamente questa grammatica invece.

Mostra relazione insiemistica tra gammatiche LL(k) e LR(k)
- ogni grammatica LL(k) è anche LR(k) -> inclusione
- l'inclusione non vale per le versioni semplificate SLR e LALR

Non parlerò però di questo tipo di analisi dato che voglio, passare ad altri argomenti...









## 4) Modelli iterativi e ricorsivi + TRO
- **Modello computazionale iterativo**
    - opera su di un **accumulatore** (variabile, schermo, buffer driver di rete, file ...) che al termine del ciclo contiene il risultato
    - **assegnamenti distruttivi**
        - non si ha la storia di come si è arrivati al risultato finale
    - terminato prematuramente, da un risultato parziale
    - **computa in avanti**
- **Modello computazionale ricorsivo**
    - **non ha un accumulatore**
    - le chiamate ricorsive generano stack frame distinti -> **singoli assegnamenti**
        - si ha la storia di come si è arrivati al risultato
    - il risultato comincia a venire sintetizzato solamente quando si è arrivati al caso base e le chiamate cominciano a chiudersi e ritornare
        - se le chiamate ricorsive vengono interrotte prematuramente, non si ha alcun risultato parziale
    - **computa all'indietro**
- **NB**: 
    - la macchina iterativa occupa sempre e solo la **memoria necessaria all'accumulatore**
    - la macchina ricorsiva occupa tutta la **memoria necessaria ai vari stack frame**.
        - **Gli stack frame sono tutti necessari e da mantenere in memoria** fino a che non si incomincia ritornare dal caso base (il risultato n-esimo si ottiene solo dopo aver recuperato il [n-1]-esimo)
- **TRO**
    - tipicamente cicli implementano un modello computazionale iterativo e chiamate ricorsive implementano un modello computazionale ricorsivo. **Ma non sono per forza le uniche opzioni**.
    - Con dei cicli (uno per caricare lo stack e l'altro per scaricarlo) ed uno stack posso implementare un modello ricorsivo
    - Con funzioni Tail ricorsive posso implementare un modello iterativo
    - Una funzione **tail ricorsiva** è:
        - una funzione in cui la chiamata ricorsiva è **l'ultima istruzione della funzione**
        - uno degli argomenti della funzione è l'accumulatore in cui viene calcolato il risultato parziale k-esimo
            - il risultato parziale è contenuto nell'accumulatore che viene portato in avanti da chiamata a chiamata
                - Come nel caso del ciclo, se si interrompe l’iterazione al passo k, l’accumulatore contiene il risultato k-esimo
            - arrivati al caso base l'accumulatore contiene già il risultato finale (modello iterativo d'altronde) e quest'ultimo viene semplicemente restituito indietro senza altre computazioni dalle altre chiamate
            - non c'è bisogno di mantere tutti i record di attivazione in memoria (modello iterativo d'altronde)
- es. Fattoriale:
    - Fattoriale ricorsivo normale:
        - int fact(int n) { return n==1 ? 1 : fact(n-1)*n; }
    - Fattoriale tail ricorsivo:
        - int fact(int acc=1, int n) { return n==1 ? acc : fact(acc*n, n-1)}
- **NB**: una funzione tail-ricorsiva, siccome computa in avanti e siccome raggiunto il caso base ha gia in mano il risultato finale, **non ha bisogno di mantenere in memoria i record di attivazione delle chiamate precedenti**.
- La tail recursion può essere quindi ottimizzata (**TRO**) **sovrascrivere il vecchio record di attivazione con il nuovo**. In tal modo, l’occupazione di memoria è identica al caso ciclico!
- Non tutti i linguaggi supportano però la TRO (tipicamente quelli imperativi; dichiarativi, funzionali e blended di solito si)...
    - trasformare la ricorsione in iterazione (non sempre comodo/possibile)
    - implementare la TRO con il TRAMPOLINO!
- **TRAMPOLINO**
    - non voglio modificare troppo la mia funzione tail-ricorsiva perchè, in tal caso, potevo direttamente riscriverla in forma iterativa
    - idea: al posto di restituire il risultato della prossima chiamata ricorsiva, restituisco una funzione (FCE) che mi svolge quest'ultima.
        - **approccio lazy**, simile alla valutazione degli argomenti con call-by-name
        - in questa maniera:
            - la chiamata può terminare subito **il record di attivazione può venire deallocato**  
            - il trampolino, in base a se gli viene restituita una funzione o un valore, decide se invocare la prossima chiamata (risultato della chiamata precedente) o se restituire il risultato finale 
    - **es linguaggio loosely typed**:
        function fact(acc, n) {
            return n==1 ? acc : () => fact(acc*n, n-1)      // notare la chiusura su _n_ e _acc_
        }

        function trampolino(f) {
            while (f && f instanceof Function) {
                f = f()
            }

            return f;   // risultato finale
        }
    - **NB**: in un linguaggio tipato lascamente, posso avere una argomento che in un momento è una funzione e in un altro un valore di un altro tipo. **Nei linguaggi tipati fortemente invece devo definire una architettura di classi/interfacce** per accontentare il type system
        - Interfaccia Trampolinable e classi More/Done

In questo esempio del trampolino ho accennato molti argomenti di cui vorrei parlare in seguito:
- funzioni come FCE
- Lazyness
- Chiusure






## 5) Funzioni come FCE
- Quando si parla di funzioni come FCE si intende **manipolare funzioni come ogni altro tipo di dato**, e quindi:
    - deve poter essere **assegnata a variabili** (di un tipo "funzione")
    - deve poter essere **passata come argomento** a un'altra funzione
    - deve poter essere **restituita** da un'altra funzione
    - deve poter essere **definita e usata "al volo"** come ogni altro valore (literal) di ogni altro tipo
        - magari anche senza avere per forza un nome, (funzioni anonime/lambda expression)
- si rompe la separazione tra codice e dati
    - i nuovi tipi funzione sono tipi di dato analoghi agli altri con in più la caratteristica di poter essere eseguiti
        - tasto rosso che permette di valutare i dati (di tipo funzione) come codice
    - in realtà la distinzione era arbitraria fin dall'inizio, in memoria sia codice che dati sono memorizzati allo stesso modo
        - magari a livello di SO la pagine contenenti codice sono RX, e le pagine contenenti dati sono RW 
- siccome le funzioni diventano un nuovo tipo di dato **bisogna definire meglio questi tipi**. Due approcci:
    - **approccio strutturale**: il tipo della funzione è definito dal numero e tipo degli argomenti, e dal tipo di ritorno
        - es Scala: (String) -> (); (Int, Int) => Int
    - **approccio nominale**: il tipo della funzione è definito da un nome apposito 
        - si distinguono semanticamente tipi strutturalmente identici
        - es Java: Function<Int, Int> == UnaryOperator<Int>; Consumer<Int>; Supplier<Int>
- Disporre di funzioni come FCE apre le porte a molti altri concetti:
- **funzioni di ordine superiore**
    - funzioni di secondo ordine (trampolino) manipolano (accettano/ritornano) funzioni 
    - funzioni di terzo ordine manipolano funzioni di terzo ordine
    - ecc...
- **Chiusure (trampolino)**
- Currying
- **Lazyness (trampolino)**













## 6) Lazyness
- La Lazyness è una tecnica di programmazione in cui **si computa solo se strettamente necessario ed il più tardi possibile** 
- Un approccio lazy porta a vari vantaggi, che descriverò in seguito accompagnando ogni vantaggio con un esempio:
    1. **efficienza**:
        - **si evita lavoro inutile** 
            - vedi **call by name** in cui la valutazione degli argomenti viene fatta solo se necessario
                - argomenti utilizzato all'interno di un ramo, che in una determinata chiamata, non viene mai percorso, non vengono mai valutati  
                - inoltre mi permette di **salvare chiamate di funzioni normalmente fatali** (argomenti che causerebbero  DivByZero, NPE, ...) 
                - eventualmente mostra come implementare la call by name in un linguaggio che non la supporta nativamente
        - il costo delle computazioni strettamente necessarie viene **"spalmato" nei momenti distinti in cui servono**
            - vedi **lazy init** in cui l'inizializzazione/costruzione delle proprietà di un oggetto
                - solamente all'effettivo uso della variabile/proprietà quest'ultima verrà inizializzata
                - se quest'ultime non vengono mai usate (tutte), come prima, si evita del lavoro inutile
                - ma, anche se vengono usate tutte, il costo viene spalmato nei vari momenti d'uso e non si paga tutto upfront (avendo un'alta latenza)
                    - esempio di PAW della catena: cliente -> ordini -> ordine  -> ...;
                    - caricare tutto in maniera eager avrebbe un costo elevato, molti join su db
                    - caricare in maniera lazy, mi evita di caricare se non ne ho mai bisogno, e quando carico, recupero solo quello che mi serve, spalmando il costo nel tempo
            - **allocazione di pagine di memoria lazy**
                - un'altro esempio simile che esemplifica questo concetto
                - in un SO non tutta la memoria che un processo richiede viene utilizzata subito e/o interamente 
                - allocare le pagine in maniera lazy
                    - mi permette di ridurre il memory footprint di un processo alle pagine che usa effettivamente in un dato momento
                    - mi permette di spalmare il costo (potenzialmente elevato) di allocazione di tante pagine nel tempo (e.g. 1GiB ~= 250k pagine)   
    
    2. permette di **generare e gestire insiemi infiniti** senza entrare in loop 
        - Il primo esempio che mi viene in mente è quello degli **stream in Java**
            - essi **gestiscono insiemi infiniti generando un elemento alla volta** fino a rispondere alla richiesta dell'ultimo stadio dello stream. 
            - es: *Stream.iterate(2, n->n+2).filter(x -> x>40).findFirst();*    
            - ragionare allo stesso modo sarebbe impossibile con un approccio eager (mi inloopo subito)

        - Un'esempio un po' più subdolo di insieme infito è quello di **entità in relazione ciclica**
            - **es: *utente <-> amici*** 
                - se Marco è amico di Mirco, e Mirco è amico di Marco, questa relazione ciclica mi porterebbe ad inloopparmi se tentassi di generare tutta la catena di oggetto a partire dall'oggetto Marco con un **fetch eager**.
                - Un **fetch lazy**, gestisce questa situazione tranquillamente, facendo al DB una richiesta alla volta.

    3. permette di fare **scelte deterministiche**
        - Siccome la computazione è ritardata il più possibile, al momento dell'effettiva computazione avrò più informazioni rispetto al momento della richiesta. Questa è la situazione ideale per fare scelte deterministiche
        - **es: Unified Memory in CUDA**
            - Quando si programma una GPU, tipicamente bisogna considerare due memorie diverse
                - memoria lato CPU
                - memoria lato GPU
            - questo implica per il programmatore dover prestare attenzione a:
                - distinguere puntatori CPU da puntatori GPU
                - allocare/liberare la memoria nei due dispositivi
                - eseguire correttamente i trasferimenti di memoria tra i due dispositivi
            - Per semplificare la gestione della memoria in questo ambiente, la piattaforma CUDA introduce questa astrazione chiamata UM in cui
                - lo spazio di indirizzamento è unico (non c'è distinzione tra puntatori host e puntatori device)
                - i trasferimenti di memoria sono automatizzati
                - non bisogna specificare dove si sta allocando/liberando la memoria
            - Problema: **come si fa a decidere dove allocare la memoria se il programmatore non lo specifica?** Importante per le prestazioni
            - Soluzione: **allocazione lazy!**
                - la memoria viene allocata sul lato del primo dispositivo che la usa
                - la logica è che il primo dispostivo che la usa è quello che ci fa le computazioni e quindi ha bisogno di avere quei dati localmente per sfruttare la maggiore bandwith della sua RAM piuttosto che quella del bus PCIe della scheda madre.
            - **Con una allocazione eager si sarebbe dovuto scegliere non deterministicamente**, sparando a caso 
            - **stessa idea delle grammatiche sostanzialmente LL(1)**

## 7) Chiusure
- Definizione
    - def: variabili libere
    - def: chiusura
    - disegno con inglobamento delle variabili di chiusura
    - sinergia con Funzioni come FCE
- Conseguenze sul modello computazionale
    - allocazione nell'heap
    - complicazione dell'interprete
    - attenzione a modificare le variabili di chiusura che hanno riferimenti attivi + es con for e array
- Criteri di risoluzione delle variabili di chiusura
    - catena lessicale
    - catena dinamica
    - es: due funzioni
- Casi d'uso delle chiusure:
    - fanno ricordare alle funzioni il contesto in cui sono state definite
        - stato privato (della funzione generatrice)
        - canali di comunicazione privati tra due o più chiusure che condividono la stessa variabile di chiusura
        - creazione di nuove strutture di controllo
            - es. until js
            - es. until scala con call by name e brace-syntax








- **DEF PRELIMINARE**: una **variabile libera** è una variabile usata dentro ad una funzione ma **non definita nell'ambiente locale di quest'ultima**
- **DEF**: una chiusura è un oggetto di tipo "Funzione" ottenuto **"chiudendo" una definizione di funzione con variabili libere**, dentro a un contesto più esterno (contenente la definizione della variabili libere).
    - Le variabili libere, in quanto non definite localmente, vanno ricercate negli ambienti più esterni rispetto a quello della definizione della funzione. Una volta trovato un ambiente contenente la definizione della/delle variabili libere, la definizione della funzione di partenza può venire completata (**chiusa**) con un riferimento all'ambiente esterno trovato.

- Le chiusure cominciano a diventare interessante solamente quando si dispone di funzioni come FCE e, di conseguenza, quando si possono definire **funzioni di ordine superiore**, ovvero funzioni che che ricevono/creano/restituiscono altre funzioni.
    - Questo perchè altrimenti l'unico ambiente più esterno è quello globale
    - mostra un esempio a caso di chiusura:
        - funzione ff() del secondo ordine che restituisce una funzione f(x) con dentro un riferimento ad una variabile locale di ff()
        - disegna l'esempio con dei cerchi, e mostra come la chiusura f(x) venga completata con il riferimento alla variabile locale di ff()

- La presenza di chiusure ha delle **conseguenze sul modello computazionale** del linguaggio che le adotta!
    - il tempo di vita delle variabili locali di una funzione appartenenti ad una chiusura, non coincide più necessariamente con quello della funzione che le contiene
    - queste variabili di chiusura **non si possono più allocare sullo stack**. In quanto quest'ultimo **viene deallocato al termine della funzione**.
    - il tempo di vita delle variabili locali che fanno parte di una chiusura è pari a quello della chiusura
        - la chiusura ha bisogno di continuare ad avere un riferimento valido anche se la funzione che definisce le variabili libere termina prima
    - perciò **tali variabili sono allocate sull'heap e non sullo stack!**
        - la chiusura poi, mantiene un semplice riferimento a queste variabili
        - l'interprete/compilatore del linguaggio si complica
            - deve identificare le chiusure e capire cosa allocare sullo stack e cosa sull'heap 

- **NB**: Bisogna fare attenzione alle variabili libere all'interno di una chiusura, esse, essendo risolte per riferimento, hanno visibilità di tutte le modifiche fatte **anche dopo la creazione della chiusura**
    - **es**:
        // qua sto salvando 10 chiusure che però puntano alla stessa variabile *i* che a fine ciclo vale 10
        // tutte le chiusure stampano 10!
        // (i cicli non definiscono un nuovo ambiene e quindi la variabile viene semplicemente sovrascritta) 
        for (var i=0; i<10; i++) {
            array.push( () => console.log(i) )
        }

- Occorre stabilire un **criterio su dove e quando vadano risolte le variabili libere di una chiusura**. Due alternative:
    - **catena lessicale**
        - le variabili libere di una chiusura vengono risolte **al momento di VALUTAZIONE della funzione che le contiene** e vengono cercate in **ambienti via via più esterni rispetto quella della funzione** che contiene le variabili libere
        - considero gli ambienti del codice
            - "compile"-time
    - **catena dinamica**
        - le variabili libere di una chiusura vengono risolte **al momento di INVOCAZIONE della funzione che le contiene** e vengono cercate in ambienti via via più esterni rispetto quello dove è stata invocata la funzione
        - considero gli ambienti definiti dall'ordine delle chiamate
            - run-time
    - es:
        var i = 10
        foo(x) {
            return x + i
        }

        bar() {
            var i = -1
            console.log(foo(10))
        }
    - **Caso lessicale**:
        - più predicibile
            - alla definizione della chiusura so chiaramente qual'è il valore della variabile puntata
            - NON devo ricostruire la sequenza delle chiamate
        - compatibile con la costruzione di librerie
            - **non importa chi mi chiama**, le variabili libere hanno il valore che definisco io
        - per lo stesso motivo facilità anche di testing
    - **Caso dinamico**: 
        - più difficile da debuggare
            - per dare valore alle variabili libere **devo considerare la sequenza delle chiamate**
            - il risultato della chiamata a funzione dipende da chi mi chiama
        - incompatibile con la costruzione delle librerie
            - **le variabili libere dipendono da chi mi chiama**
        - impredicibilità anche nel testing
    - Poiché la comprensibilità è cruciale, **praticamente tutti i linguaggi di programmazione adottano il criterio di chiusura lessicale**.

- A che cosa servono le chiusure?
    - fanno ricordare alle funzioni il contesto in cui sono state definite
    - stato privato di oggetti (utile con costruttori/factory)
        - si può ottenere una proprietà privata **mappando lo stato su un argomento della funzione "generatrice"**
    - definizione di nuove strutture di controllo
        -   function until(cond){           // cond è una function variabili di chiusura 
                var fun = function iter(action){    // action è una function
                    if(!cond()) { action(); iter(action); }
                }

                return fun
            }

            // uso
            var i=1;    // variabile di chiusura
            until( () => i>9 )( () => {console.log("ciao"); i++}) 
        
        - **NB**: se avessi **supporto alla call by name come in scala** la lambda sparirebbe
        - def until(condition: =>Boolean)(action: =>Unit) : Unit = {
            if (!condition) {
                action;
                until(condition)(action);
            }
          }

          var i = 0
          until(i == 10) {
            println("hi")
            i = i+1
          }

    - canale di comunicazione privato tra due o più chiusure che condividono la stessa variabile di chiusura