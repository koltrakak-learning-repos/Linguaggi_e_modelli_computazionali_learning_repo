```
Le espressioni regolari sono un formalismo alternativo alle grammatiche per definire linguaggi (regolari).
```

Le espressioni regolari sono tutte e sole le espressioni ottenibili tramite le seguenti regole:
- la stringa vuota *epsilon* è una espressione regolare
- dato un alfabeto *A*, ogni elemento *a* appartenente *A*, è una espressione regolare
- se X e Y sono espressioni regolari, lo sono anche:
    - X+Y = { x | x appartenente X oppure x appartenente Y }        (unione)
        - operatore meno prioritario
    - XoY = { x | x = ab, a appartenente X e b appartenente Y }     (concatenazione)
        - associativa ma non commutativa
    - X*  =  X^0 unito X^1 unito X^2 unito …                        (chiusura)
        - dove X^0 = epsilon e X^k = X^(k-1) o X
        - operatore più prioritario

**ESEMPI**
    X1      = {00, 11}
    X2      = {01, 10}
    X1 + X2 = {00, 11, 01, 10}
    X1 o X2 = {0001, 1101, 0010, 1110}
    X2 o X1 = {0100, 0111, 1000, 1011}
    X1*     = {epsilon , 00, 11, 0000, 0011, 1100, 1111, 000000, 000011, 001100, 001111, 110000, 110011, 111100, 111111, … }

**NB**: analogamente alle grammatiche, uno stesso linguaggio può essere descritto da molte espressioni regolari diverse!

### Relazione tra ESPRESSIONI REGOLARI e LINGUAGGI
```
    TEOREMA: i linguaggi generati da grammatiche regolari coincidono con i linguaggi descritti da espressioni regolari.
```

**Grammatiche ed espressioni regolari sono quindi due rappresentazioni diverse della stessa realtà!**
Grammatica = **formalismo costruttivo**, esprime le regole per capire COME derivare le frasi, ma non quali frasi si ottengono (COSA)
- S → a S | b

Espressioni regolari = **formalismo descrittivo**, esprime quali frasi si ottengono (COSA), ma non COME si arriva ad esse 
- L = { a* b }

Grammatiche ed espressioni regolari sono quindi due rappresentazioni diverse della stessa realtà
- Formalismi ortogonali, usandoli entrambi abbiamo sia **come** una frase può venire derivata, sia **cosa** (quali frasi) si può derivare.

Si puo passare da un formalismo all'altro?

### PASSAGGIO TRA RAPPRESENTAZIONI 
Dalla grammatica all'espressione regolare
- si risolvono le cosiddette **equazioni sintattiche**

Dall'espressione regolare alla grammatica
- si interpretano gli operatori dell'espressione regolare in base alla loro semantica (sequenza, ripetizione, alternativa) mappandoli in opportune regole

**EQUAZIONI SINTATTICHE**
Per passare dalla grammatica all'espressione regolare si interpretano le produzioni come equazioni sintattiche, in cui:
- i simboli terminali sono i termini noti,
- **i linguaggi** generati da ogni simbolo non terminale **sono le incognite** e si risolvono con le **normali regole algebriche**.

Le equazioni sintattiche si risolvono tramite un algoritmo, che esiste in due versioni:
- per grammatiche regolari a destra
- per grammatiche regolari a sinistra

Le due versioni differiscono però solo per un raccoglimento a fattor comune, in cui l'elemento raccolto:
- nelle grammatiche regolari a destra, è raccolto a destra
- nelle grammatiche regolari a sinistra, è raccolto a sinistra
e nella conseguente posizione dei termini nell'espressione risultante.

// skip dell'esempio...

**NB**: in base all'ordine di applicazione delle regole algebriche si potrebbero ottenere espressioni regolari piu o meno complesse tutte però equivalenti (denotano lo stesso linguaggio come con le grammatiche). Bisogna fare pratica per diventare bravi ad ottenere l'espressione più semplice possibile.

**IMPORTANTE | SEMPLIFICAZIONE TRAMITE CAMBIO DI RAPPRESENTAZIONE**
Data una espressione regolare complicata, essa si può trasformare per ottenere una forma più semplice? La risposta è ni, in generale è difficile operando direttamente sull'espressione di partenza (bisogna avere fantasia). 

Tuttavia, **sia le grammatiche che le espressioni regolari si possono mappare su un automa a stati finiti** di cui si puo ottenere l'automa minimo. A questo punto si può tornare indietro e si riesce a recuperare l'espressione minima. (cambio di dominio)

**DALL'ESPRESSIONE REGOLARE ALLA GRAMMATICA**
Per passare dall'espressione regolare alla grammatica si interpretano gli operatori in base alla loro semantica
- sequenza      → simboli accostati nella grammatica
- operatore +   → simbolo '|' di alternativa nella grammatica (regole distinte)
- operatore *   → regola ricorsiva nella grammatica (ciclo)

// skip dell'esempio...