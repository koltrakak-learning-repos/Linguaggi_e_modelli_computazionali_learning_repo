## Alberi sintattici astratti
La grammatica descrive la struttura effettiva delle frasi, ossia la **sintassi concreta** del linguaggio.
- Tale grammatica è studiata in modo che il linguaggio risulti non solo ben definito, ma anche **chiaro per chi legge**
- A tal fine **la sintassi concreta include spesso elementi non strettamente necessari** (punteggiatura, parole chiave, ..), ma utili per migliorare la chiarezza e la leggibilità delle frasi.

Se la valutazione non è immediata, **il parser preferisce rappresentare le frasi sintatticamente corrette tramite una opportuna rappresentazione interna**, solitamente ad albero.

**Perchè?** 
- **Estendibilità**
    - Un interprete che valuta immediatamente è fin troppo hard-coded, se cambia anche un solo tipo devo rifare tutto. 
    - Potrebbe essere più intelligente estrarre in un AST le parti che descrivono le relazioni tra le entità descritte dalla grammatica (invarianti) da quelle dipendenti dal caso d'uso (valori dentro a dominio e immagine della funzione di interpretazioni).
    - il parser salva i risultati della sua valutazione in un formato intermedio (albero) che sarà **poi processato da vari valutatori distinti**, uno per funzione di interpretazione desiderata.

Si potrebbe usare l’**albero di derivazione**, ma in generale esso darebbe luogo a una rappresentazione **ridondante** che mi alloca tanto spazio in memoria inutilmente.
- l'albero di derivazione illustra tutti i singoli passi di derivazione, ma molti di essi servono solo durante la costruzione dell'albero, "per ottenere proprio quell'albero" e non un altro.
- inoltre, un albero con molti nodi e livelli è complesso da visitare (la visita è ricorsiva), determinando quindi inutili inefficienze.

Conviene adottare un albero più compatto, che contenga solo i nodi indispensabili e possibilmente sia pure binario in modo da essere visitato facilemente. Tale albero è detto Abstract Parse Tree (APT) o **Abstract Syntax Tree (AST)**.

**Idea**: se su sottoalbero appartenente all'albero di derivazione degenera in una lista, i nodi intermedi non mi servono in quanto non offrono un contributo effettivo. Se i nodi intermedi avessero invece delle diramazione, allora offrirebbero dei reali contributi **aggregando i due sotto alberi in un unico risultato**.

**Quali sono i nodi non indispensabili?**
- i nodi terminali (foglie) non legati ad alcunché di significativo sul piano semantico
    - segni di punteggiatura, zucchero sintattico in genere
    - nel caso delle espressioni non ne abbiamo
- i nodi terminali (foglie) che, pur utili durante la costruzione dell'albero per ottenere "quell'albero e non un altro", esauriscono con ciò la loro funzione
    - nel caso delle espressioni: le parentesi
- i nodi non terminali che hanno un unico nodo figlio
    - nel caso delle espressioni: sequenze Exp -> Term -> Factor

**Come rendere eventualmente l'albero binario?**
inserendo informazioni come proprietà del nodo-padre anziché come sotto-nodo figlio
- nelle espressioni invece di salvare solo il nodo *exp*, salvo anche il tipo e quindi avrò nodi come *SumExp, MulExp, ...*


**NB**: la struttura dell'**albero fornisce intrinsecamente l'ordine corretto di valutazione**
- è impossibile valutare un nodo senza disporre prima dei due figli
    - quindi, occorre PER FORZA valutare prima la parte "in basso"
- si risale fino a valutare la radice, che fornisce il risultato

### Sintassi astratta
Una sintassi astratta **serve a descrivere l'AST**, non è la sintassi vera analizzata dal parser. 

- Una sintassi astratta descrive __l'output del parser__ (AST), l'input viene descritto dalla sintassi a strati mostrata prima.
    -  descrive i possibili «tipi di nodi» dell’AST
- In questo contesto, il problema dell'ambiguità non si pone in quanto "chi scrive conosce quello che sta scrivendo". Nel caso di chi legge invece, è necessario che le frasi possano essere lette (derivate) in un solo modo, altrimenti si potrebbero interpretare la stessa frase in due modi diversi. 

**es. sintassi astratta AST delle espressioni**:
    EXP ::= EXP + EXP   // plusexp
    EXP ::= EXP - EXP   // minusexp
    EXP ::= EXP * EXP   // timesexp
    EXP ::= EXP : EXP   // divexp
    EXP ::= num         // numexp

Ambigua! Ma non è un problema

### Estendiamo il parser facendogli produrre un AST
...











## Valutazione degli AST
**Ricorda**: il vantaggio di usare AST è che se, ad esempio, è necessario cambiare dominio della valutazione, basta scrivere un nuovo valutatore del AST e non riscrivere l'intero parser da capo.

**Come si valuta un AST?**
Come qualsiasi altro albero:
- **Pre-order**: radice, figli (da sinistra a destra)
- **Post-order**: figli (da sinistra a destra), radice
- **In-order**: figlio sinistro, radice, figlio destro
    - Attenzione: la in-order saltella fra i livelli!

Occorre capire cosa producono i diversi tipi di visita:
- Pre-order:
    - operatore, 1° operando, 2° operando
    - notazione prefissa
- Post-order:
    - 1° operando, 2° operando, operatore
    - notazione postfissa
- In-order:
    - 1° operando, operatore, 2° operando
    - notazione infissa

**NB**: la notazione **infissa non rende evidenti l'ordine con cui vengono eseguite le operazioni** (bisogna conoscere le convenzioni su priorità e associatività). Al contrario, la notazione pre/post-fissa eliminano questa necessità.

**NB**: La notazione postfissa, che sembra quella più brutta, è in realta quella più naturale per una macchina che **prima di eseguire una operazione ha bisogno dei dati** su cui quella operazione va fatta. **Infatti, i nostri valutatori utilizzeranno una visita in post-order**.

**OSS**: come abbiamo gia visto, la grammatica a strati e la ricorsione sinistra (opportunamente tradotte nel parser) hanno già dentro di se i concetti di associativita e priorità. Per questo motivo l'AST prodotto tiene già conto nella sua struttura di questi concetti.

In altre parole, se una espressione viene valutata con una determinata associatività, l'AST prodotto avrà collegato i nodi al suo interno in maniera tale da tenere conto di questa associatività (in particolare operazioni da eseguire prima sono più in basso nell'ast). **A questo punto la macchina esecutrice (valutatore) non si deve più preoccupare di questi concetti e può fare una valutazione con una visita post-order dell'albero.**

### compilatori, VM e bytecode
È chiaro che il codice prodotto da compilatore per un processore reale deve utilizzare il più possibile i registri quando può, ma questo causa complicazioni:
- se i registri non bastano?
- come associare registri e operandi?

È molto più comodo utilizzare una **macchina virtuale** e un valutatore che mi produce del bytecode per quest'ultima. In particolare la nostra macchina virtuale può essere un **macchina a stack** e allora le cose diventano semplici dato che non ci interessa il costo di accesso alla memoria (non stiamo controllando una macchina fisica):
- ogni nodo-valore carica un valore sullo stack (PUSH)
- ogni nodo-operatore causa il prelievo di due valori dallo stack (POP) e il collocamento sullo stack del risultato (PUSH)
- alla fine si preleva il risultato dallo stack (POP)

Sarà il compilatore del linguaggio con cui ho scritto la mia VM a doversi preoccupare delle ottimizzazioni per la macchina fisica, io posso ignorarle ed affidarmi agli scrittori dei compilatori.

### VALUTATORI
Un valutatore **valuta un AST in un dato dominio**, secondo la sua funzione di interpretazione.
- deve visitare l'albero applicando in ogni nodo **la semantica prevista per quel tipo di nodo**
- fondamentale per il valutatore **discriminare che tipo di nodo sta visitando**

**possibili implementazioni**:
1. Una semplice funzione che implementa la funzione di interpretazione per ogni tipo di nodo:
    - brutto, dentro la funzione bisogna distinguere tutte le tipologie di nodo con switch/lunghe catene di if/else
    - **racchiude un tipo di valutazione in un solo posto** 
        - Se avessi bisogno di un nuovo valutatore mi basterebbe scrivere una nuova funzione che lo implemente; **tutte le funzioni valutatrici precedenti non cambierebbero**
    - Se volessi modificare la grammatica aggiungendo qualche nuova produzione (e quindi un nuovo tipo di nodo) **occorrerebbe modificare il codice di tutte le funzioni di interpretazione** 
        - nuova produzione significa nuovo tipo di nodo, di cui tenere conto ovunque con un altro *case*

2. Un metodo per ogni nodo dell'AST che specifica come quel nodo va valutato:
    - sfrutta il polimorfismo per interpretare correttamente ogni tipo di nodo dell'AST durante la sua visita
        - ogni nodo dice come deve essere valutato
        - facile introdurre nuovi tipi di nodi (nuove produzioni): la funzione d'interpretazione di quelli vecchi rimane la stessa, il nuovo nodo definisce come deve venire valutato
    - ma la **funzione di valutazione è "sparsa nell'AST"**
        - se volessi una nuova funzione di interpretazione dovrei aggiungere un metodo nuovo per ogni tipologia di nodo del mio AST.


Uhm... sarebbe bello poter introdurre nuove tipologie di nodo e nuove funzioni di interpretazione, unendo i vantaggi dei due approcci, senza dover andare a modificare troppo codice. Questo è quello che ci permette di fare il pattern visitor.

### IL VISITOR COME INTERPRETE
Il visitor realizza la logica di interpretazione in modo coerente all'approccio a oggetti:
- **cattura UNA logica di interpretazione**
    - si scrivono tanti visitor quante le interpretazioni richieste (possibile organizzarle in una TASSONOMIA)
    - la logica di interpretazione è concentrata in UN unico luogo, ma non ha più la forma di una "old style function"; è suddivisa all'interno del visitor in **tante implementazioni del metodo *visit* tante quante sono le tipologie di nodo da visitare**.

Il visitor incorpora la logica di visita:
- metodi *visit* (uno per ogni tipo di nodo)
- I nodi dell'AST accettano la visita del visitor con il metodo *accept*
- Trucco: lo fanno rimpallando l'azione sul visitor, passandogli loro stessi come oggetto da visitare
    - "accetto la tua visita" = "ti ordino di visitarmi"
    - exp.accept(Visitor v) { // polimorfismo nel tipo di visitor che i nodi accettano
        v.visit(this);        // polimorfismo nel tipo di nodo che i vistor possono visitare
      }
    - Tecnica di Double Dispatch

**Double dispatch**
- Si attiva la valutazione chiedendo all'espressione di accettare la visita di un certo visitor 
    - expression.accept(visitor)
- L'espressione serve le richiesta rimpallando l'azione sul visitor e passando se stessa come oggetto da visitare, ossia invocando:
    - visitor.visit(this)
- In questo "BOTTA & RISPOSTA" __la risoluzione dell'overloading__ seleziona automaticamente la **visit** appropriata senza bisogno di instanceof 
    - cast dinamico

Utilizzando questo pattern abbiamo che:
- Descrizione: Con il pattern Visitor, puoi aggiungere nuove operazioni all'AST semplicemente creando un nuovo Visitor, senza toccare le classi che rappresentano i nodi. logica in un posto solo 
- doppio dispatch mi permette di distinguere la tipologia di nodo che sto visitando senza controlli espliciti