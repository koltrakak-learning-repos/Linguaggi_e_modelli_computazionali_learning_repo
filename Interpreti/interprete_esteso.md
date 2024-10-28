## Variabili e assegnamenti

    x = x + 1 è un obrobrio. 

= non vuol dire uguale, e il simbolo x significa due cose diverse a sinistra o a destra dell'uguale.

- La peculiarità dell'assegnamento è che il simbolo di __variabile ha significato diverso a destra__ e a sinistra dell’operatore =
- Il simbolo di variabile è overloaded
    - il suo significato dipende dalla posizione rispetto all’operatore
    - a sinistra, indica il contenitore a destra, il contenuto

La semantica informale può quindi essere così riassunta:
1. Prendere il valore della variabile a destra dell’operatore =
2. Usarlo per valutare il valore dell’espressione a destra dell’ =
3. Porre il risultato nella variabile specificata a sinistra dell’ =

Il simbolo di variabile significa quindi:
- "la variabile in quanto tale", quando compare a sinistra (L-value)
- "il contenuto di quella variabile", quando compare a destra (R-value)

__OSS__: Questo tipo di sintassi non è LL(1) manco a piangere, richiede LL(2) in quanto dopo ogni variabile bisogno anche controllare se c'è anche un uguale dopo. Per questo motivo è utile __distinguere sintatticamente__ L-value da R-value. Ad esempi, nei linguaggi di shell: x = $x+1

### Assegnamenti distruttivi
Altro grande tema è capire se x=x+1 è una cosa accettabile o meno, ovvero se è lecito o meno cambiare il valore associato in precedenza a un simbolo di variabile, ovvere se è lecito o meno avere un __assegnamento distruttivo__. 

Se questo fosse il caso, l'unico modo possibile per capire che valore è associato ad una variabile in un dato punto del programma è simulare il l'evoluzione del programma fino a lì (debug).
- essere umano si abbassa al livello della macchina :(.

L'idea del riscrivere una variabile dopo che le hai gia dato un valore è tipica del modello imperativo (ispirato dalla macchina di turing).

Una alternativa è quella dei linguaggi logichi. Le variabili possono subire un solo assegnamento e in seguito non possono venire più modificate.

    In questo modello si computa sintetizzando nuovi valori e non modificando variabili.

Con questo approccio si ottiene __trasparenza referenziale__, ovvero ogni simbolo mantiene il suo valore in qualsiasi punto del programma (come in una dimostrazione matematica).

__NB__: assegnamenti distruttivi non permessi non significa che le variabili diventano costanti! Con una costante si conosce il valore desiderato a priori. Variabili a singolo assegnamento invece, sono "variabili" nel senso che __a runtime possono assumere valori diversi__, l'unica cosa che cambia è che non si contempla la assegnazione distrutttiva. 

### Environment
Per esprimere la semantica dell’assegnamento occorre introdurre il concetto di environment inteso come tabella (mappa) a due colonne che mantiene la associazioni simbolo-valore. __L’assegnamento modifica l’environment__ causando un __effetto collaterale__ (__boh, chiedi a denti qual'è l'effetto principale dell'assegnamento allora__).

    Il mondo prima dell'assegnamento è quello dopo sono diversi!

NB: Se il linguaggio è a singolo assegnamento non si può modificare una riga dell'environment gia settata (restituzione di un errore). Nei linguaggi imperativi invece questo è possibile. 

### Environment multipli
L’environment è spesso suddiviso in sotto-ambienti, di norma collegati al tempo di vita delle strutture run-time
- ENVIRONMENT GLOBALE: contiene le coppie il cui tempo di vita è associato con l’intero programma
- ENVIRONMENT LOCALI: contengono coppie il cui tempo di vita non coincide con l’intero programma; tipicamente legati all’attivazione di funzioni o altre strutture run-time (blocchi).

__NB__: Ogni modello computazionale deve specificare il campo di visibilità dei suoi simboli (scope), ossia quali environment sono visibili in quali punti della struttura fisica del programma.

### Assegnamenti multipli
differenza tra espressioni
- ritornano qualcosa e non modificano l'ambiente
e istruzioni
- non ritornano niente ma il loro obiettivo e produrre un effetto collaterale, cioè modficare l'ambiente

assegnamento singolo = istruzione ; modifico l'ambiente ma non denoto un valore (non ritorno nulla)
assegnamento multiplo = espressione; ritorno il valore che sto assegnando -> y = 8 ritorna 8;

OSS: per permettere l'assegnamento multiplo l'assegnamento va definito come una espressione al posto di una istruzione. uhm...

## Estensione dell'interprete
Per introdurre l’assegnamento in un linguaggio occorre stabilire:
- la sintassi dei nomi delle variabili
- se l’assegnamento sia distruttivo o meno
- se la scrittura di assegnamento x=valore sia un’__istruzione__ o una __espressione__
    - ISTRUZIONE: effettua un’azione ma non denota un valore
    - ESPRESSIONE: effettua un’azione e denota anche un valore che costituisce il «risultato» dell’espressione
- Quest’ultima scelta dipende dal fatto che si voglia o meno supportare l’assegnamento multiplo.

### Espressioni sequenza
COSA SONO: sequenze di espressioni separate da virgola (,)
- IPOTESI 1: la prima espressione è sempre un assegnamento
- IPOTESI 2: il valore complessivo è quello dell'exp più a destra

Sintassi astratta:

    EXP ::= ASSIGN , EXP // SeqExp

__NB__: con questa estensione ci si avvicina sempre più alla sintassi e ai costrutti di un "vero" linguaggio di programmazione, in cui di solito:
- prima si inizializzano le variabili
- poi si computa su di esse

#### CHIEDI A DENTI COME MAI EFFETTO COLLATERALE