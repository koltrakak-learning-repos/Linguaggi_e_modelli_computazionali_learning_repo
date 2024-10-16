fino ad adesso per l'implementazione del PDA avevamo chiare dalle regole della grammatica il simbolo iniziale della frase.

a volte però queste iniziali non sono sempre disponibili fin da subito ma sono nascoste da dei simboli non terminali

in questi casi bisogna andare ad esplorare più regole...

Questo ci porta ad una generalizzazione del concetto di "iniziare per":

### Generalizzazione di simbolo iniziale

Starter simbol set di alpha = tutti i simboli iniziali terminali che riesci ad ottenere sviluppando in tutti i modi possibili con le regole della grammatica alpha.

Attenzione: non bisogna limitarsi a guardare il primo simbolo nei casi in cui la grammatica contenga delle produzioni in cui il simbolo iniziale possa scomparire.

### Generalizzazione per l'analisi ricorsiva discendente
... starter symbols disgiunti ...

... gli epsilon possono venire eliminati però la grammatica risultante diventerà più complicata

gli starter set sono una definizione troppo semplicistica che mi obbliga a complicare la grammatica per gestire le epsilon. C'è bisogno di una definizione aggiuntiva.

c’è una via più rapida per capire se una grammatica è LL(1), senza dover eliminare le -rules?
    
Starter symbol set che tiene anche conto delle stringhe vuote, in modo da non dovere complicare la grammatica

    uguale allo starter set di prima unito ai simboli che seguono lo parte che si può annullare

oppure

Parsing table con blocchi annullabili

Algoritmi che ti calcono i simboli di follow, ...

### Il problema della ricorsione sinistra
la ricorsione sinistra può in teoria essere sostituita con una ricorsione destra, tuttavia questa trasoformazione cambia l'ordine di derivazione e quindi cambia anche la semantica.

## Se la grammatica non è ll(1) che cosa si fa?
incrementare k non aiuta, esistono linguaggi context-free che non sono ll(k) per nessun k

### esistono anche grammatiche LR(k), più potenti rispetto a quelle LL(k)

### dangling else problem

    regole ambigue? NO!!!
