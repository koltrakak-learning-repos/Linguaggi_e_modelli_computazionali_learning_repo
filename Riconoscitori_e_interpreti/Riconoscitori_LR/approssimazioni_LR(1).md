L’esperienza conferma che **è praticamente impossibile costruire un parser LR(1) per un “vero” linguaggio**, poiché le sue dimensioni sarebbero intrattabili.

Per questo si introducono **versioni semplificate**:
- SLR (Simple LR)
- LALR(1) (Look-Ahead LR)

Non altrettanto potenti quanto il “vero LR(1)”, ma, in pratica, adatte a moltissimi casi concreti.

### Contesto SLR(k) 
Il contesto LR(0) non è sufficiente nel caso di conflitti shift/reduce. Idea: **concatena l'insieme dei follow**

Il contesto SLR(k) di una produzione *A → alpha* è così definito:
- *SLR(k)ctx(A → alpha) = LR(0)ctx(A → alpha) • FOLLOW_k(A)*

**NB**: il contesto SLR(k) considera una **informazione sovrabbondante** in quanto l'insieme dei FOLLOW non considera solamente i caratteri che seguono A nelle produzioni A -> alpha, ma l'insieme dei caratteri che seguono A in generale. LR(1) è più preciso.

Infatti, è possibile dimostrare che *SLR(k)ctx(A → alpha)* **contiene** *LR(k)ctx(A → alpha)*, ovvero **il contesto SLR è un po’ più grande, e quindi più esposto a potenziali conflitti, del contesto LR**. In generale i due contesti **coincidono o non differiscono di troppo** per linguaggi pensati bene.

A causa del contesto sovrabbondante, potrebbero saltare dei conflitti che in realtà nell'analisi LR(1) precisa, non si presenterebbero. **L'idea è di provare a calcolare i contesti SLR e vedere se presentano conflitti: se non ce ne sono, si può usare SLR al posto di LR(1) completo.** Semplificando di molto la costruzione del riconoscitore.


### PARSER SLR

... aggiungendo l'informazione degli insiemi dei follow possiamo scegliere deterministicamente in presenza di quelli che prima erano dei conflitti SHIFT/REDUCE.

**LR(0) diventa una sorta di passaggio intermedio**. In seguito, l'insieme dei follow bisogna calcolarselo per forza ed è questo insieme che consente di disambiguare ed elimare i conflitti.

__CONSIGLIO DI DENTI__: l'importante è capire l'idea e non tanto guardarsi tutti gli esempi meticolosamente




### PARSER LALR
E quando SLR non funziona?
- Un approccio alternativo consiste nel collassare in un solo stato quegli stati che, nel parser LR(1) completo, sono identici a meno dei lookahead set.

Si parla allora di LookAhead LR – in breve, LALR
- PRO: è una trasformazione sempre possibile, spesso molto conveniente perché il parser LALR ha molti meno stati dell'LR
- CONTRO: **possono apparire conflitti reduce / reduce, tipicamente gestibili**.
- Può essere implementato calcolando **prima LR(0) e poi aggiungendo i lookahead set**, calcolati separatamente

Idea accorpare insieme gli stati che seppur accorpati non producono conflitti. 
- LR(0) accorpa tutto indiscriminatamente e quindi produce conflitti
- LR(1) separa troppo chirurgicamente e produce mille stati che se anche accorpati insieme non produrrebbero conflitti 


