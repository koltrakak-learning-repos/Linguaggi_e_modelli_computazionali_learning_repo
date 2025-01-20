L'interprete prodotto fino ad adesso è fin troppo hard-coded, se cambia anche un solo tipo devo rifare tutto. Potrebbe essere più intelligente estrarre le parti invarianti da quelle dipendenti dal caso d'uso.

**Idea**: il parser salva i risultati della sua valutazione in un formato intermedio (albero) che sarà poi processato da vari interpreti (valutatori) distinti.

## Alberi sintattici astratti
La grammatica descrive la struttura effettiva delle frasi, ossia la **sintassi concreta** del linguaggio.
- Tale grammatica è studiata in modo che il linguaggio risulti non solo ben definito, ma anche **chiaro per chi legge**
- A tal fine **la sintassi concreta include spesso elementi non strettamente necessari** (punteggiatura, parole chiave, ..), ma utili per migliorare la chiarezza e la leggibilità delle frasi.

Se la valutazione non è immediata, il parser preferisce rappresentare le frasi sintatticamente corrette tramite una opportuna __rappresentazione interna__, solitamente ad albero.

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











### Architettura globale di un interprete
...
il vantaggio di usare AST è che se vogliamo, ad esempio, cambiare dominio di valutazione, basta scrivere un nuovo valutatore del AST e non riscrivere l'intero interprete da capo.

## Valutazione degli alberi
notazione prefissa  = visita pre-order
notazione postfissa = visita post-order

notazione infissa   = visita in-order ; questo tipo di visita non rende evidente l'ordine delle operazioni

...

NB: La notazione postfissa, che sembra quella più brutta, è in realta quella più naturale per una macchina che prima di eseguire una operazione ha bisogno dei dati su cui quella operazione va fatta.

    piccolo discorso epico pre pausa: Nel costruire il nostro processore virtuale (valutatore) piuttosto che appoggiarsi ad un numero finito di registri e gestire le complicazioni dovute a questo, meglio usare una macchina a stack dato che non ci interessa il costo di accesso alla memoria (processore VIRTUALE). Questo è esattaente come funzionano la JVM e CLR. **mic drop**

OSS: la grammatica a strati e la ricorsione sinistra, opportunamente tradotto nel parser, ha dentro di se i concetti di associativita e priorità come abbiamo gia vista. Per questo motivo l'AST prodotto ha gia dentro nella sua struttura questi concetti. In altre parole, se una espressione viene valutata con una associatività diversa, l'AST prodotto avrè collegati i nodi al suo interno i nodi collegati in modo altrettanto diverso. A questo punto la macchina esecutrice (valutatore) non si deve più preoccupare di questi concetti e può fare una valutazione sequenziale con una visita post-order dell'albero.

## VALUTATORI
Più valutatori possono produrre output diversi.

    Il valutatore valuta un AST in un dato dominio, secondo la sua funzione di interpretazione.

- deve visitare l'albero applicando in ogni nodo la semantica prevista per quel tipo di nodo
- deve discriminare che tipo di nodo sta visitando

### IL VISITOR COME INTERPRETE
Il visitor realizza la logica di interpretazione in modo coerente all'approccio a oggetti
- cattura UNA logica di interpretazione
    - si scrivono tanti visitor quante le interpretazioni richieste possibile organizzarle in una TASSONOMIA
- la logica di interpretazione è concentrata in UN unico luogo, ma non ha più la forma di una "old style function": è suddivisa, all'interno del visitor, in tante implementazioni del metodo visit quante sono le classi della tassonomia (overloading)

Il visitor incorpora la logica di visita
- metodi visit (uno per ogni tipo di espressione)
L'espressione accetta la visita del visitor
- metodo accept

Trucco: lo fa rimpallando l'azione sul visitor stesso, passandogli se stessa come oggetto da visitare
- "accetto la tua visita" = "ti ordino di visitarmi"
- Tecnica di Double Dispatch

### Double dispatch
- Si attiva la valutazione chiedendo all'espressione di accettare la visita di un certo visitor 
    - expression.accept(visitor)
- L'espressione serve le richiesta rimpallando l'azione sul visitor e passando se stessa come oggetto da visitare, ossia invocando:
    - visitor.visit(this)
- In questo "BOTTA & RISPOSTA" __la risoluzione dell'overloading__ seleziona automaticamente la visit appropriata senza bisogno di instanceof 
    - cast dinamico