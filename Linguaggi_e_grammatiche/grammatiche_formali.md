## Definizione Grammatica
Una Grammatica è una notazione formale con cui esprimere in modo rigoroso la sintassi di un linguaggio. 
- **un linguaggio** (sottoinsieme della chiusura del suo alfabeto) **si specifica con una grammatica**

Una grammatica è una quadrupla <VT,VN,P,S> dove:
- VT è un insieme finito di simboli terminali (elementi dell'alfabeto)
- VN è un insieme finito di simboli non terminali
    - I simboli non terminali sono dei meta-simboli che servono a scrivere le regole di produzione
- P è un insieme finito di produzioni
    - regole di riscrittura a −> b dove a e b sono stringhe: a appartenente V+, b appartenente V*
    - ogni regola esprime una trasformazione lecita che permette di scrivere, nel contesto di una frase data, una stringa b al posto di un’altra stringa a.
- S è un particolare simbolo non-terminale detto simbolo iniziale o scopo della grammatica.

**NB**: Gli insiemi VT e VN devono essere disgiunti: VT intersecato VN = 0. L’unione di VT e VN si dice vocabolario della grammatica.

**Nomenclatura**:
- FORMA DI FRASE(SENTENTIAL FORM) = frase in divenire
    - una qualsiasi stringa comprendente sia simboli terminali sia meta-simboli, ottenibile dallo scopo applicando una o più regole di produzione.
- FRASE = frase finale
    - una forma di frase comprendente solo simboli terminali.
- SEQUENZA DI DERIVAZIONE (freccia spessa)
    - la sequenza di passi che producono una forma di frase sigma dallo scopo S.
    - una sequenza di derivazione può essere:
        - diretta (freccia senza niente): una sola applicazione di una regola, un solo passo
        - con +: uno o più applicazioni di produzioni
        - con *: zero o più passi

### Linguaggi generati da una grammatica
Data una grammatica G, si definisce il Linguaggio L_G generato da G come l’insieme delle frasi:
- derivabili dal simbolo iniziale S
- applicando le produzioni P

### Grammatiche equivalenti
Una grammatica G1 è equivalente a una grammatica G2 se generano lo stesso linguaggio.
- una grammatica potrebbe però essere preferibile a un’altra ad essa equivalente dal punto di vista dell’analisi sintattica
- se riesco ad esprimere il mio linguaggio con un tipo 3 è molto meglio rispetto ad una grammatica equivalente di tipo 2 

Purtroppo però, in GENERALE, stabilire se una grammatica è equivalente ad un'altra è un problema indecidibile.
- le faccenda cambia se ci si restringe a tipi particolari di grammatiche, aventi regole di produzione "sufficientemente semplici"

**Equivalenza del risultato prodotto ma non nel costo di produzione**: Grammatiche di diversa struttura comportano linguaggi con diverse proprietà e implicano automi di diversa “potenza computazionale” per riconoscere tali linguaggi. Per questo motivo è importante scegliere la grammatica equivalente "più economica" per descrivere il linguaggio desiderato.





## CLASSIFICAZIONE DI CHOMSKY
Le grammatiche sono classificate in 4 tipi in base alla struttura delle produzioni. In ordine di complessità decrescente, abbiamo:

**Tipo 0**: nessuna restrizione sulle produzioni
- In particolare, le regole possono specificare riscritture che accorciano la forma di frase corrente.
- Regole del tipo: G → epsilon che fanno svanire un simbolo non terminale

**Tipo 1 | dipendenti dal contesto**: produzioni vincolate alla forma: *"beta" A "delta"* → *"beta" "alpha" "delta"*. **con *alpha* != *epsilon***
- *A* può essere sostituita da *alpha* solo nel contesto *"beta" A "delta"* 
    - **regole chirurgiche**
- Le riscritture non ACCORCIANO MAI la forma di frase corrente
    -  Una definizione ALTERNATIVA EQUIVALENTE prevede infatti produzioni della forma: *alpha → beta* con |beta| >= |alpha|
- si può trasformare un metasimbolo per volta (A), lasciando intatto ciò che gli sta intorno

**NB**: questo tipo di grammatica **prevede anche vicoli ciechi/rami morti**, ovvero strade in cui non ci sono più regole applicabili. Questo non succede mai nel Tipo 2 e nel Tipo 3

**Tipo 2 | libere dal contesto**: produzioni vincolate alla forma: *A → alpha*; (con alpha che può anche essere epsilon)
- Qui *A* può sempre essere sostituita da *alpha*, indipendentemente dal contesto (non esiste più l'idea stessa di contesto).
    - regole a grana grossa, ogni volta che vedo il metasimbolo sostituisco
- *alpha* può contenere più metasimboli in qualsiasi posizione
- **NB**: non c'è più il vincolo *alpha* != *epsilon*. **Si ritorna a poter accorciare le frasi**, cosa? Qua me lo posso permettere perchè esiste un teorema che mi permette di tirar via gli *epsilon* ottenendo una grammatica equivalente sempre di tipo 2 (vedi dopo). 
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
**NB**: anche qui alpha può essere epsilon.

### Relazione gerarchica
Le grammatiche sono in relazione gerarchica:
- una grammatica regolare (Tipo 3) è un caso particolare di grammatica context-free (Tipo 2),
- che a sua volta è un caso particolare di grammatica context-dependent (Tipo 1),
- che a sua volta è - ovviamente - un caso particolare di grammatica qualsiasi (Tipo 0).

**OSS**: poiché le grammatiche di tipo 2 (e quindi di tipo 3) possono generare la stringa vuota *epsilon*, la relazione di inclusione vale solo se si conviene di estendere le grammatiche di tipo 1 anche la produzione *S → epsilon*.

### CLASSIFICAZIONE DI CHOMSKY IL PROBLEMA DELLA STRINGA VUOTA
Nella classificazione di Chomsky, Le grammatiche di Tipo 1 non ammettono la stringa vuota nel lato destro delle produzioni. Viceversa, le grammatiche di Tipo 2 e 3 la ammettono. Come è possibile che le grammatiche siano in relazione gerarchica tra loro, ma al contempo la stringa vuota non sia ammessa nel Tipo 1 e sia invece ammessa nei Tipi 2 e 3? Sembrerebbe esserci una evidente contraddizione. 

L'assenza di contraddizione è dovuta al seguente teorema

**TEOREMA STRINGA VUOTA**
Le produzioni di grammatiche di Tipo 2 (e quindi anche 3) **possono sempre essere riscritte in modo da evitare la stringa vuota**. Al più, possono contenere la regola *S → epsilon*. 

Il teorema assicura che la sola differenza fra una grammatica context-free con o senza epsilon-rules è che il linguaggio generato dalla prima include la stringa vuota. 

**OSS**: I linguaggi di programmazione (tipo 2) hanno spesso produzioni che ammettono la stringa vuota, di solito per **descrivere parti opzionali** (e.g. le keyword: public, static, void, ecc.). Il teorema ci garantisce che le grammatiche dei linguaggi di tipo 2 di questo tipo potrebbero essere riscritte in una grammatica equivalente senza epsilon-rules mantenendo in questa maniera la relazioni di inclusione. Tuttavia diventerebbe più difficile comprendere le produzioni e quindi spesso si preferisce non eliminare quest'ultime. In sostanza, **la stringa vuota è utile perchè evita di dover specificare ogni combinazione di keyword quando si hanno delle parti opzionali**. 

**Come ottenere la grammatica equivalente senza *epsilon-rules*?**
Data una grammatica G che contiene epsilon-rules, per generare una grammatica equivalente G', senza epsilon rules, **basta fare delle sostituzioni e semplificazioni**. 

![alt text](immagini/es_eliminazione_epsilon_rules.png)


### TIPO DI UNA GRAMMATICA E TIPO DI UN LINGUAGGIO
Poiché le grammatiche sono in relazione gerarchica, può accadere che un linguaggio possa essere generato da più grammatiche (equivalenti), anche di tipo diverso. Non è detto però che la prima grammatica che si trova per generare un dato linguaggio sia necessariamente la migliore (più semplice e meno computazionalmente costosa da riconoscere).

Ora che abbiamo introdotto la classificazione di Chomsky formalizziamo meglio quanto detto sopra parlando di *"Equivalenza del risultato prodotto ma non nel costo di produzione"*. Abbiamo che:
- **Il tipo del linguaggio può non coincidere col tipo della grammatica che lo genera**
    - con tipologia di un linguaggio si intende **il tipo della grammatica più semplice in grado di generarlo**
    - il linguaggio generato potrebbe essere di un tipo più semplice della prima grammatica che si è trovato per descriverlo
    (un linguaggio di Tipo 3 potrebbe in realtà essere generato anche da grammatiche di Tipo 2, Tipo 1, Tipo 0)
- ci si potrebbe chiedere se ci sia un modo generale per capire se, trovata una grammatica che descrive il linguaggio desiderato, esista o meno una grammatica più semplice ... e magari trovarla (risposta dopo, **pumping lemma**)

**TEOREMA STRINGA VUOTA 2**
Dato un linguaggio L di tipo 0, 1, 2, o 3 -> I linguaggi L unito {epsilon} e L - {epsilon} sono dello stesso tipo.
- in sostanza avere la stringa vuota o meno non cambia la tipologia di un linguaggio


## Aspetti caratterizzanti delle varie tipologie di linguaggio
Come accennato di sopra, **nelle grammatiche di Tipo 1 non è garantito che qualunque sequenza di derivazione porti a una frase**
- Può succedere di trovarsi in una strada chiusa, impossibilitati a proseguire perché si arriva ad una forma di frase per cui **non ci sono regole di produzione applicabili**
- Questo non succede mai nel Tipo 2 e nel Tipo 3

è utile quindi poter distinguere agevolmente i vari tipi di linguaggio osservando semplicemente la relativa grammatica in modo da poter subito capire che cosa può o non succedere. Abbiamo che:

### Differenza tra linguaggi di tipo 1 e tipo 2**:
Un linguaggio è sicuramente di tipo 1 se è necessario scambiare qualcosa, questa è la sua caratteristica cruciale dato che lo scambio implica due simboli a sinistra nella produzione; le regole del tipo 2 hanno solamente un unico metasimbolo in quella posizione.
- il Tipo 1 ammette produzioni della forma *sigma A delta → a*;
    - ed in particolare produzioni di scambio: *BC → CB* (più propriamente sarebbero più regole con dei metasimboli di appoggio)
- il Tipo 2: invece ammette solo produzioni della forma *A → a* con cui è impossibile realizzare degli scambi

**Differenza tra linguaggi di tipo 2 e tipo 3**:
La caratterisca che distingue il tipo 2 dal tipo 3 è il **self embedding**.
- Nel Tipo 2, produzioni della forma *A → alpha*; dove a può contenere **più metasimboli, in qualsiasi posizione (in particolare anche in mezzo)**
- Nel Tipo 3, produzioni lineari dove ci può essere **un solo metasimbolo, o in testa o in coda** 
    - *A → delta* o *A → delta B* (a destra) oppure
    - *A → delta* o *A → B delta* (a sinistra)

**OSS**: Nel Tipo 2, i meta-simboli possono trovarsi in mezzo alla forma di frase; nel Tipo 3, no

### SELF EMBEDDING
Una grammatica contiene self-embedding (anche non diretto) quando una o più produzioni hanno la forma: A =>* alpha1 A alpha2 (STESSO metasimbolo in mezzo alla frase).

**IMPORTANTE**: Il ruolo del self-embedding è introdurre una ricorsione in cui si aggiungono contemporaneamente simboli a sinistra e a destra, garantendo di procedere "di pari passo". È essenziale per definire linguaggi le cui frasi devono contenere **simboli bilanciati, come ad esempio le parentesi**.

```
TEOREMA: una grammatica di Tipo 2 che non contenga self-embedding genera un linguaggio REGOLARE.
```

Dunque, è il self-embedding la caratteristica cruciale di una grammatica di Tipo 2, che la differenzia da una di Tipo 3.

Più nel dettaglio il **self-embedding è una condizione sufficiente** affinche una grammatica sia di tipo 2, ovvero:
- Se non c'è self-embedding in nessuna produzione (come dice il teorema) esiste una grammatica equivalente di Tipo 3. Quindi il linguaggio è regolare.
- Non vale necessariamente il viceversa, **una grammatica con self-embedding potrebbe comunque generare un linguaggio regolare**, se il self-embedding è "finto" (ovvero, "disattivato" da altre regole)


## RICONOSCIBILITÀ DEI LINGUAGGI
Riprendendo l'ultimo argomento fatto in teoria della computabilità, Abbiamo che i linguaggi generati da grammatiche di Tipo 0 possono, **in generale**, non essere riconoscibili (decidibili)
- Non è garantita l'esistenza di una MdT capace di decidere se una frase appartiene o meno al linguaggio
    - non è possibile generare (per ogni linguaggio di tipo 0) l'insieme complementare a quello delle frasi valide del linguaggio

Al contrario, i linguaggi generati da grammatiche di Tipo 1 (e quindi di Tipo 2 e 3) SONO riconoscibili
- Esiste **sempre** una MdT capace di decidere se una frase appartiene o meno al linguaggio
- L'efficienza del processo di riconoscimento, però, è un'altra faccenda…

Per ottenere un traduttore efficiente occorre adottare linguaggi generati da (classi speciali di) grammatiche di tipo 2 (quelle di nostro interesse)
- Tutti i linguaggi di programmazione sono infatti context free

Per ottenere particolare efficienza in sotto-parti di uso estremamente frequente (numeri e identificatori di variabili), si adottano spesso per esse linguaggi generati da grammatiche di Tipo 3

Chi riconosce i diversi tipi di linguaggi?
GRAMMATICHE                 AUTOMI RICONOSCITORI
Tipo 0 ->                   Se L(G) è decidibile, serve una Macchina di Turing
Tipo 1 ->                   Macchina di Turing 
Tipo 2 (context-free) ->    Push-Down Automaton (PDA) (cioè ASF + stack)
Tipo 3 (regolari) ->        Automa a Stati Finiti (ASF)

















--- BNF & EBNF
Simile alla notazione delle grammatiche utilizzato fino ad adesso
    - per le regole di produzione ::= al posto di ->
    - per i metasimboli <...> al posto di lettera maiuscola

inoltre, con la versione estesa
    - X ::= [a]B per indicare una ripetizione da 0 a 1 volta  (opzionale)
        -> BNF equivalente: X ::= B | aB
    - X ::= {a}^nB per indicare una ripetizione da 0 a n volta 
        -> BNF equivalente: X ::= B | aB | …| a^nB
    - X ::= {a}B   per indicare una ripetizione da 0 a un numero indefinito di volte
        -> BNF equivalente -> X ::= B | aX -> ricorsiva a destra
    - X ::= (a | b) D | c   per raccoglimenti
        -> BNF equivalente -> X ::= a D | b D | c

--- ALBERI DI DERIVAZIONE e AMBIGUITà di una GRAMMATICA
Per le sole grammatiche di Tipo 2 si introduce il concetto di albero di derivazione. Una notazione ad albero per esprimere come
una frase è stata derivata da una grammatica di almeno tipo 2.

NB: notazione esclusiva a tipo 2, perchè queste grammatiche ammettono solo un padre a sinistra. L'albero di derivazione non
può esistere per grammatiche di Tipo 1 e 0 perché in esse il lato sinistro delle produzioni ha più di un simbolo e dunque i nodi
figli avrebbero più di un padre (ergo non si otterrebbe più un albero, ma un generico grafo) 

Questi alberi sono facilmente producibili dagli umani in quanto ci si può far guidare dalla frase e dal ragionamento, le macchine
invece hanno piu difficolta...

DERIVAZIONI CANONICHE
Derivazione “left-most” (deriv. canonica sinistra)
    - A partire dallo scopo della grammatica, si riscrive sempre il simbolo non-terminale più a sinistra.
Derivazione “right-most (deriv. canonica destra)
    - A partire dallo scopo della grammatica, si riscrive sempre il simbolo non-terminale più a destra.

AMBIGUITA
Una grammatica è ambigua se esiste almeno una frase del relativo linguaggio che si riesce ad ottenere in più modi applicando in modi diversi le regole della grammatica
    -> almeno due alberi di derivazione distinti portano alla stessa frase
es:
    A ::= A + A
    A ::= a
La frase "a+a+a" è ambigua!

NB: non centra il verso di derivazione!  

L'ambiguità è il male! Incasina le macchine incrementando l'ordine di complessità degli algoritmi riconoscitori. Possiamo accorgerci se una grammatica è ambigua?
Purtroppo, stabilire se una grammatica di Tipo 2 sia ambigua è un problema indecidibile
    - però, in pratica, un certo numero di derivazioni è spesso sufficiente per "convincersi" della (non per dimostrare la) ambiguità di G

NB: Se una grammatica è ambigua, spesso se ne può trovare un'altra equivalente che non lo sia (ma non sempre).

--- FORME NORMALI
Un linguaggio di tipo 2 non vuoto può essere sempre generato da una grammatica di tipo 2 in cui:
    - ogni simbolo, terminale o non terminale, compare nella derivazione di qualche frase di L
        -> ossia, non esistono simboli o meta-simboli inutili
    - non ci sono produzioni della forma A → B con A,B appartenenti VN
        -> ossia non esistono produzioni che “cambiano solo nome” a un meta-simbolo
    - se il linguaggio non comprende la stringa vuota allora non ci sono produzioni della forma A → epsilon.

In particolare si può fare in modo che tutte le produzioni abbiano una forma ben precisa:
    - forma normale di Chomsky                              -> weak
      produzioni della forma A → BC | a;    con A,B,C appartenenti VN, a appartenente VT unito epsilon
    - forma normale di Greibach (per linguaggi privi di epsilon)  -> goated  
      produzioni della forma A → a"alpha";  con A appartenente VN, a appartenente VT, alpha appartenente VN*
NB: La forma normale di Greibach facilita, come si vedrà, la costruzione di riconoscitori; è quella che ci interressa

TRASFORMAZIONI
Per facilitare la costruzione dei riconoscitori, è spesso rilevante poter trasformare la struttura delle regole di produzione per renderle più adatte allo scopo. Queste
trasformazioni sono utili per arrivare alla forma di Greibach per le regole di una grammatica.

In particolare
    - Sostituzione 
    - Raccoglimento a fattore comune
        -> fondamentale, se non si raccoglie si tira a caso e ti puoi sfracellare -> ambiguità
        -> la soluzione è guadagnare tempo, intanto ti occupi del primo simbolo. Nei passi successivi avrai in ingresso nuovi simboli e saprà decidere senza andare a caso.
            -> è un problema di non determinismo: adesso con le informazioni che hai non sai prendere un scelta certa. Soluzione: aspetta e recupera nuove informazioni
               per fare una scelta certa.
    - Eliminazine della ricorsione sinistra
        -> sempre possibile, ma cambia l'ordine di derivazione dato che cambia le regole della grammatica. A volte non ce lo si può permettere di farlo
            -> ha conseguenze

IL PROBLEMA DELLA RICORSIONE SINISTRA
Perchè è desiderabile eliminare la ricorsione sinistra?

    X → X a c | p

La ricorsione sinistra nasconde l’iniziale delle frasi prodotte, che si può determinare solo guardando altre regole. Nell’esempio sopra, tutte le frasi
iniziano per p, ma questo non si vede dalla regola ricorsiva. Non è così nella ricorsione destra, che invece evidenzia proprio l’iniziale delle produzioni:
    X → p | p Z
    Z → a c | a c Z

La buona notizia è che, tecnicamente, si può sempre sostituire la ricorsione sinistra con una destra (vedi sopra). La cattiva notizia è che spesso non ci potremo
permettere il lusso di farlo, a causa delle conseguenze.

    //algoritmo di eliminazione skip, guardatelo sulla slide 91

Perché, dunque, potremmo non voler eliminare la ricorsione sinistra? Sostituendo la ricorsione sinistra con una destra, si generano le stesse frasi, ma con
regole diverse.
    -> Ergo, se interessa solo il risultato finale «ai morsetti», rimpiazzare la ricorsione sinistra con una destra è lecito e privo di conseguenze
    -> se, invece, interessa anche come ci si è arrivati (ossia, la sequenza di derivazione), allora il rimpiazzo non è lecito perché cambiando le
       regole cambia anche la sequenza di derivazione.
In un puro riconoscitore, che deve solo dire «sì o no», eliminare la ricorsione sinistra è fattibile senza conseguenze. In un vero parser, che deve anche dare
significato alle frasi (lecite), regole diverse tipicamente implicano significato diverso per alcune frasi, con ciò alterando il liguaggio.
    -> guarda esempio operazioni matematiche slide 93

--- PUMPING LEMMA, COME CAPIRE SE UN LINGUAGGIO è DI TIPO 2/3 o MENO? 
Capire se un linguaggio è di Tipo 2 (o di Tipo 3) "solo guardandolo" in generale non è banale. Se hai la grammatica è facile. Tuttavia di solito si parte dal
LINGUAGGIO desiderato, non dalla grammatica; successivamente si prova a definire una grammatica che lo genera.

A questo punto molte domande -> la grammatica è semplice/corretta/ambigua?
Ma in primo luogo, posso capire la tipologia del linguaggio ancora prima di definire la grammatica?
    
PUMPING LEMMA
Il pumping lemma dà una condizione NECESSARIA, ma non sufficiente, perché un linguaggio sia di Tipo 2 (o 3)
    - può quindi essere usato per dimostrare "in negativo" che un linguaggio non è di Tipo 2 (o di Tipo 3)…
    - ... ma purtroppo non per affermarlo "in positivo

idea: in un linguaggio infinito, ogni stringa sufficientemente lunga deve avere una parte che si ripete (regola ricorsiva). ergo, essa può essere "pompata"
un qualunque numero di volte ottenendo sempre altre stringhe del linguaggio.

La formulazione è leggermente diversa a seconda che si tratti di linguaggi di Tipo 2 o 3, ma la sostanza non cambia.
    - nei linguaggi di tipo 2, la stringa ha questa forma: z = uvwxy; con v e x sottoparti pompabili    -> u(v^i)w(x^i)y    appartenente al linguaggio
    - nei linguaggi di tipo 3, la stringa ha questa forma: z = xyw; con v sottoparte pompabile          -> x(y^i)W          appartenente al linguaggio

pumping lemma per tipo 2: due cose crescono insieme
pumping lemma per tipo 3: c'è una crescita o solo dietro o solo davanti

-> DOMANDA DI DENTI come si fa a trovare la grammatica adatta per un linguaggio? Una prima analisi è possibile applicando il pumping lemma. Possiamo capire se il linguaggio desiderato NON è di tipo 2 o 3.
