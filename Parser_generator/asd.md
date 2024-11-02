Date le regole della grammatica un parser generator produce: tutti i metodi di parsing, le classi dell'AST, i visitor ecc...
I visitor chiaramente non sanno che valutazione fare se non glielo si dice e quindi oltre la sintasssi si specifica in qualche modo anche la semantica.

### DSL
Linguaggi studiati per risolvere problemi in una specifica nicchia. Un linguaggio gp risolverebbe questi problemi lo stesso ma risulterebbe troppo largo per il contesto d'uso.

### ANTLR (ANother Tool for Language Recognition)
...
Interessante l'utilizzo di una tecnica di parsing LL(*)

- L'originale algoritmo Adaptive LL(*) va oltre il puro LL(k)
    - accetta ricorsione sinistra diretta (ristrutturata internamente)
    - molto efficiente, prestazioni adattative nel runtime

### roba