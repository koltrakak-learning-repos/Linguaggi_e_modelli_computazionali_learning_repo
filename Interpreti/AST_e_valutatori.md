L'interprete prodotto fino ad adesso è fin troppo hard-coded, se cambia anche un solo tipo devo rifare tutto. Potrebbe essere più intelligente estrarre le parti invarianti da quelle dipendenti dal caso d'uso.

Idea il parser salva i risultati della sua valutazione in un formato intermedio (albero) che sarà poi processato da vari interpreti (valutatori) distinti.

## Alberi sintattici astratti
Se la valutazione non è immediata, il parser rappresenta le frasi sintatticamente corrette tramite una opportuna __rappresentazione interna__, solitamente ad albero.

Si potrebbe usare l’albero di derivazione, ma in generale esso darebbe luogo a una rappresentazione ridondante che mi alloca tanto spazio in memoria inutilmente.
- l'albero di derivazione illustra tutti i singoli passi di derivazione, ma molti di essi servono solo durante la costruzione dell'albero, per ottenere "proprio quell'albero" e non un altro.
- inoltre, un albero con molti nodi e livelli è complesso da visitare (la visita è ricorsiva), determinando quindi inutili inefficienze.

Conviene adottare un albero più compatto, che contenga solo i nodi indispensabili e possibilmente sia pure binario. Tale albero è detto Abstract Parse Tree (APT) o Abstract Syntax Tree (AST).

idea: se su sottoalbero appartenente all'albero di derivazione degenera in una lista, i nodi intermedi non mi servono in quanto non offrono un contributo effettivo. Se i nodi intermedi avessero delle diramazione quest'ultimi allora offrirebbero dei reali contributi aggregando i due sotto alberi in un unico risultato.

NB: quei nodi intermedi corrispondono effettivamente ad una chiamata di funzione necessaria per la corretta derivazione della frase, ma salvare questo tipo di nodi non è necessario.

Definiamo abstract syntax tree ...

...

vogliamo rendere l'albero binario, al posto di salvare exp dico immediatamente che tipo di exp sei (+; -)

...
ricorda: le parentesi pilotano l'albero di derivazione; adesso però osserviamo che non serve salvarle nell'AST (anche se F ha più di un figlio) in quanto una roba tra parentesi ha lo stesso valore di quella roba senza parentesi.

### Estendiamo il parser facendogli produrre un AST
...

### Sintassi astratta
non è la sintassi vera usata dal parser ma serve a descrivere l'AST. Questo è __l'output del parser__, l'input è la sintassi a strati mostrata prima. In questo caso il problema dell'ambiguità non si pone in quanto chi scrive conosce quello che sta scrivendo non deve capire una cosa che sta ricevendo (lettura).

La sintassi astratta non è unica:
- Poiché descrive una possibile rappresentazione interna, varia al variare di quest’ultima
- __NB__: Sintassi astratte diverse descrivono sistemi software diversi

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


### VALUTATORI
Più valutatori possono produrre output diversi.

    Il valutatore valuta un AST in un dato dominio, secondo la sua funzione di interpretazione.

- deve visitare l'albero applicando in ogni nodo la semantica prevista per quel tipo di nodo
- deve discriminare che tipo di nodo sta visitando

