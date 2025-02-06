### TURING-EQUIVALENZA
...

**OCCHIO**: bisogno poter rappresentare la ricorsione -> problema: le funzioni qui sono anonime!

### Numeri naturali
Di nuovo, qua raprresentiamo le costanti come funzioni che però non chiameremo mai.

...

z e s sono argomenti fake...

**TAKEAWAY**: Il valore del lambda calcolo sta nel esporre come semplicemente con i concetti di funzione, chiusura e applicazione di funzione, è possibile esprimere qualunque algoritmo computabile.

...

### Combinatore Y e Z
Per ottenere la ricorsione nel lambda calcolo si utilizza una particolare lambda chiamata Y che quando viene applicata ad una funzione:
- valuta la funzione 
- rigenera se stessa
- YF -> F(YF) == F( F(YF) ) ...

Y è la versione che funziona solo con call-by name Z è la versione che funziona anche con call by value

...

**DOMANDA: ma è vero che serve la call by name?**