## Interpreti
Finora abbiamo considerato puri riconoscitori, che accettano in ingresso una stringa di caratteri e riconoscono se essa appartiene al linguaggio.

La risposta è del tipo “sì o no”:
- **non ha importanza come si arriva a stabilire se la frase è lecita**
- in particolare, si può sempre sostituire una grammatica con una equivalente, perché al fine del riconoscimento il risultato è identico
- solo sintassi, la sequenza di derivazione non ha importanza

**Ma noi vogliamo anche semantica**, ovvero automi che oltre a riconoscere una frase facciano anche qualcosa
- degli interpreti

Un interprete è più di un puro riconoscitore:
- non solo riconosce se una stringa appartiene al linguaggio..
- ..ma esegue azioni in base al significato (semantica) della frase
    - può svolgerle direttamente -> **valutazione immediata** 
    - o costruire **strutture dati** per permettere di svolgerle in un **secondo tempo**.

Conseguenze:
- **la sequenza di derivazione diventa importante perché contribuisce a definire il significato della frase (semantica)**
- non è detto di poter sostituire una grammatica con una equivalente perché l'equivalenza è tale solo "ai morsetti"

### Struttura degli interpreti
- **Scanner**, l'analizzatore lessicale
    - analizza le parti regolari del linguaggio
    - RSF
    - produce token che da in pasto al parser
- **Parser**, l’analizzatore sintattico-semantico
    - analizza le parti context-free del linguaggio
    - PDA
    - considera come elementi terminali del linguaggio i token prodotti dallo scanner, non i singoli caratteri della frase
    - **valuta la correttezza della sequenza dei token**

### Analisi lessicale**
L’analisi lessicale consiste nella **individuazione delle singole parole (token) che compongono una frase**
- Ciò viene fatto raggruppando i singoli caratteri dell’input secondo le produzioni regolari associate alle diverse possibili categorie lessicali (identificatore, numero, keyword, ecc...)
- L’analizzatore lessicale può **categorizzare i token** mentre li analizza semplicemente **osservando in quale stato finale** del suo RSF si viene a trovare

**NB**: I linguaggi hanno anche token di diverse categorie però riconosciuti con le stesse regole grammaticali (e.g. keyword e identificatori). Per non dover realizzare un RSF complicatissimo, si conviene di fare come in seguito:
- categorizzare in **prima battuta** le parole chiave come identificatori
    - grammatica e struttura RSF molto semplici
- .. salvo poi ri-categorizzarle correttamente in **seconda battuta, consultando opportune tabelle** che incapsulano la conoscenza di dettaglio del linguaggio
    - tabella delle parole-chiave del linguaggio
    - tabella dei simboli speciali del linguaggio
- Spostando, in pratica, la decisione da un piano puramente sintattico a uno sintattico-semantico

### (IMPORTANTE) Analisi sintattica top-down
Con una grammatica LL(1), l'analisi ricorsiva discendente permette di costruire facilmente un riconoscitore. Negli esempi visti, ogni funzione restituiva un boolean.

**Per passare da un puro riconoscitore a un interprete occorre propagare qualcosa di più di un bool**:
- **un valore**, se l'obiettivo è la **valutazione immediata** (interprete) in un qualche dominio
- **un albero**, se l'obiettivo è la **valutazione differita** (compilatore o interprete a più fasi)
    - la vera valutazione nel dominio di interesse avviene più avanti.





## Il caso di studio classico: LE ESPRESSIONI ARITMETICHE
... guarda slide

Nozioni di **priorità** e **associatività**:
- priorità fra operatori diversi
    - gli operatori moltiplicativi sono di solito prioritari su quelli additivi
    - 3+4*5 denota 23, non 35
- associatività fra operatori equiprioritari
    - solitamente si associa a sinistra
    - 9-4-1 denota quattro (9-4)-1, non sei 9-(4-1)
 

### PROBLEMI DELLA PRIMA GRAMMATICA
EXP ::= EXP + EXP
EXP ::= EXP - EXP
EXP ::= EXP * EXP
EXP ::= EXP : EXP
EXP ::= num

- **Ambigua** per ogni espressione non banale (pensa ad n + n + n). 
- **non c'è niente nella grammatica che definisca l'idea di priorità e associatività delle operazioni**

Sarebbe meglio cablare nella grammatica questi concetti in modo da __facilitare l'analisi semantica (?)__ e magari nel contempo eliminare la ambiguità.

Digressione: il meno dei numeri negativi (meno unario) è simbolo con una semantica diversa rispetto al meno delle sottrazioni (meno binario)

## GRAMMATICHE A STRATI
Si può dare una **struttura gerarchica alle espressioni, esprimendo così intrinsecamente priorità e associatività degli operatori**, eliminando allo stsso tempo anche l'ambiguità.

**Idea**: per dare significato ad una espressione, bisogna dare significato prima ai termini, allo stesso modo, per dar significato ai termini bisogna prima considerare i fattori.

È come avere **tre grammatiche/linguaggi separati**:
- **Ogni strato considera terminali gli elementi linguistici definiti in altri strati**
    - per lo strato azzurro è come se *term* fosse un simbolo terminale
    - stesso discorso per *factor* nello strato giallo e per *exp* nello strato grigio

- Ogni strato **definisce quindi un suo sotto-linguaggio che usa quei "terminali"**
    - L(EXP) = TERM ± TERM ± TERM …
    - L(TERM) = FACTOR *: FACTOR *: FACTOR …
    - L(FACTOR) = num | (EXP)

- **La grammatica non è più ambigua** perché ogni strato può produrre solo certi operatori (??? boh, fidati)

- **gli strati superiori aggregano cose dei livelli inferiori** che considerano come simboli terminali
    - somme e sottrazioni (formano EXP) aggregano termini
    - moltiplicazioni e divisioni (formano TERM) aggregano fattori
    - i fattori sono entità atomiche (semplici o composte)

- **La stratificazione induce priorità fra gli operatori**, perché le entità di strati bassi devono essere sintetizzate per prime
    - sono il "caso base della ricorsione", il primo caso in cui riesco a recuperare un valore che viene poi ulteriormente elaborato dai livelli superiori
    - MAX priorità: procurarsi i fattori
    - MED priorità: aggregare i fattori in termini
    - MIN prorità: aggregare i termini in espressioni

- Entro ogni strato, **la ricorsione** (se presente) **stabilisce come si aggregano entità di pari livello**
    - Questo sempre per la ricorsività dell'analisi che effettivamente comincia a generare risultati solo dopo essere arrivati al caso base per poi cominciare a risalire le chiamate (lo stack è LIFO quindi l'ultima riscrittura è quella analizzata per prima) 
    - Ricorsione SINISTRA = associatività operatori A SINISTRA
    - Ricorsione DESTRA = associatività operatori A DESTRA
    - Nessuna ricorsione = operatori NON ASSOCIATIVI






Con una grammatica a strati si riesce ad esprimere al livello di grammatica i concetti di priorità e associatività:
- il livello più basso è quello con più priorità
    - da considerare prima
        - MAX priorità: procurarsi i fattori
        - MED priorità: aggregare i fattori in termini
        - MIN prorità: aggregare i termini in espressioni
    - la fase di risalita nella valutazione parte da li (cima dello stack)
- dentro ogni strato, la ricorsione (se presente) stabilisce come si aggregano __entità di pari livello__
    - Ricorsione SINISTRA = associatività operatori A SINISTRA
    - Ricorsione DESTRA = associatività operatori A DESTRA
    - Nessuna ricorsione = operatori NON ASSOCIATIVI
- i livelli blu e gialli hanno una ricorsione sinistra e di conseguenza producono una derivazione sinistra
    - associatività a sinistra

flash: le parentesi pilotano l'albero di derivazione

__NB__: scrivere grammatiche in questo modo elimina l'ambiguità.

## Problema ricorsione sinistra
Abbiamo appena visto che per rappresentare l'associatività a sinistra degli operatori, bisogna introdurre una ricorsione sinistra nella grammatica. La ricorsione sinistra però sappiamo che produce una grammatica che non è LL(1) e quindi incompatibile con l'analisi ricorsiva discendente.

Non possiamo cambiare l'associatività degli operatori, come fare?
 
## Come rendere la grammatica LL(1)
Il problema di fondo è che abbiamo bisogno della ricorsione sinistra (che non è mai LL(1)) per rappresentare nella grammatica il concetto di associatività a sinistra degli operatori. Ma è proprio vero che non possiamo fare a meno della ricorsione sinistra (***Vsauce music***)?

    IDEA: trasformiamo la ricorsione in un ciclo

    OBIETTIVO: trasformare la grammatica con ricorsione sinistra in modo tale da renderla LL(1)

### Primo passo
Riconsideriamo i sotto-linguaggi generati dai diversi strati:

    L(EXP)      = TERM ± TERM ± TERM …
    L(TERM)     = FACTOR */: FACTOR */: FACTOR …
    L(FACTOR)   = num | (EXP)

Osserviamo che i primi due sono regolari, tanto da essere facilmente descrivibili da espressioni regolari:

    L(EXP)  = TERM (± TERM)*
    L(TERM) = FACTOR (*/: FACTOR)*

In effetti, le rispettive produzioni erano regolari a sinistra.

### Secondo passo
Ricordiamo che la notazione Extended BNF offre un modo per esprimere la ripetizione senza far uso diretto di ricorsioni, tramite la notazione { ... }

Da qui l'idea
    
    mappare le espressioni regolari che descrivono i due sotto-linguaggi su regole Extended BNF

    EXP     ::=     TERM { ( + | - ) TERM }
    TERM    ::=     FACTOR { ( * | : ) FACTOR }

Queste regole sintattiche:
- non presentano più ricorsione esplicita
- descrivono un processo computazionale __iterativo__, implementabile anche senza far uso di ricorsione.

__Risultato__: la grammatica così ottenuta è analizzabile con tecnica ricorsiva discendente, senza rischiare l'esplosione dello stack -> LL(1)
- leggi le slide per capire meglio il perchè. 