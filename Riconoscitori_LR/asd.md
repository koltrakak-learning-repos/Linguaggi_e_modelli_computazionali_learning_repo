### PARSING LR vs PARSING LL
La debolezza dell'analisi LL sta nella sua semplicità. Costruendo l’albero (di derivazione) __top-down__, deve poter identificare la produzione giusta da usare avendo visto solo i primi k simboli della parte destra.

L'analisi LR invece __costruisce l’albero bottom-up__
- Parte dalle foglie, ma aspetta di saperne abbastanza per decidere dove metterle 
    - costruisce i nodi padre bottom-up, a colpo sicuro
- Meno naturale dell'analisi LL, ma superiore dal punto di vista teorico
    - il parser LR è la __macchina più potente__ per grammatiche di tipo 2
- Ogni grammatica LL(k) è anche LR(k)

Sfortunatamente, l'analisi LR è complessa da progettare. Già il caso LR(1), si rivela spesso ingestibile per le grammatiche dei tipici linguaggi di programmazione. Per questo sono state sviluppate tecniche semplificate che riducono la complessità:
- SLR (Simple LR)
- LALR(1) (Look-Ahead LR)
Si fa comunque sempre uso di strumenti automatici. La costruzione manuale è per casi «giocattolo», a fini didattici

### Tecniche LR
L'analisi LL opera "TOP DOWN":
- parte dal simbolo iniziale S, poi scende cercando di coprire la frase

Viceversa, l'analisi LR opera "BOTTOM UP":
- parte dalla frase da riconoscere e cerca di ridurla allo scopo S
A tal fine, a ogni passaggio bisogna decidere se:
- proseguire la lettura dall'input, senza fare altro        → __SHIFT__ 
- oppure costruire un pezzo di albero senza leggere input   → __REDUCE__
Per prendere queste decisioni si necessita di __informazioni di CONTESTO__.

Inoltre, il parser LR richiede concettualmente la presenza al suo interno di un __ORACOLO__. Un componente che, in base al contesto corrente, gli dica in ogni istante se:
- proseguire la lettura dall'input, senza fare altro → SHIFT
- o costruire un pezzo di albero senza leggere input → REDUCE

Dunque, un parser LR è costituito da:
- un oracolo, che gli dice se fare SHIFT o REDUCE
- uno stack in cui conservare lo stato corrente (input + albero)
- un controller "orchestratore" che governa il tutto.

__NB__: La sequenza di riduzioni non è casuale, è una __derivazione canonica destra usata a rovescio__, per risalire dalla frase allo scopo. Per questo LR.

Domanda: come fa l’oracolo a suggerire sempre la mossa giusta da fare? 
Risposta: decide con cognizione di causa in base al contesto.

L'oracolo è appunto un __RICONOSCITORE DI CONTESTI__
- si calcolano le informazioni di contesto di ogni produzione
- l'oracolo riconosce il contesto attuale e in base a ciò risponde SHIFT o REDUCE
- quando riconosce un contesto di riduzione, ordina la REDUCE giusta per costruire a colpo sicuro quel certo pezzo di albero

### ANALISI LR(0)
Prima di capire che cos'è un contesto e come si può calcolare ... esempio derivazione LR(0) 

Passi:
- Calcolare il contesto LR(0) di ciascuna produzione (ognuno può comprendere infinite stringhe di terminali e non-terminali)
- Ci sono collisioni fra contesti di produzioni diverse ?
    - COLLISIONE: quando una stringa appartenente a un contesto è un __prefisso proprio__ di una stringa di un altro contesto
    - PREFISSO PROPRIO: una stringa è prefisso di un’altra __e ciò che segue è un terminale__ (non un metasimbolo)
- Se non ci sono collisioni, si possono usare i contesti LR(0) per guidare l’analisi → la grammatica è LR(0)
- se sfortunatamente ci fossero collisioni, i contesti LR(0) non basterebbero per avere un parser deterministico → tentare con LR(1)

### CALCOLO DEI CONTESTI LR(0)
I contesti sono definiti da un'opportuna __grammatica__.

    Proprietà essenziale:
    la grammatica che definisce i contesti LR(0) è sempre REGOLARE (a sinistra)

Conseguenze:
- il __riconoscimento__ del contesto corrente può essere svolto da un automa a stati finiti
- INCREDIBILE: il "potentissimo oracolo" è un semplice automa a stati finiti! Infatti l'oracolo continua a shiftare finchè non __riconosce__ un contesto e a quel punto riduce. 
    - L'attivià cruciale dell'oracolo è quella di riconoscimento del contesto corrente.
    - Oracolo è riconoscitore di contesti diventano quindi sinonimi

#### CONTESTI LR(0): DEFINIZIONE
... guarda la slide

#### Calcolo dei contesti sinistri | frase ambigua chiarimento
La frase dice che per calcolare i prefissi che possono apparire prima di A (ossia, i contesti sinistri di A), dobbiamo considerare i prefissi che potrebbero essere davanti a B (ossia il contesto sinistro di B), e aggiungere ad essi la stringa γ.

In altre parole: Se davanti a B può esserci un certo contesto (indicato come leftctx(B)), Allora davanti ad A, in questa produzione, ci sarà lo stesso contesto, seguito dalla stringa γ (leftctx(A)).

Si parla di un primo contributo perché altri contributi potranno emergere analizzando le altre regole di produzione. Nel complesso, leftctx(A) si ottiene perciò unendo tutti i contributi di tutte le produzioni in cui A compaia nella parte destra.

### Condizione sufficiente per analisi LR(0) di una grammatica
Condizione sufficiente perché una grammatica sia LR(0) è che ogni __stato di riduzione__(finale) dell'automa ausiliario sia etichettato da una
__produzione unica__ AND non abbia __archi di uscita etichettati da terminali__.

### Teorema:
una grammatica LR(0) non è mai ambigua.