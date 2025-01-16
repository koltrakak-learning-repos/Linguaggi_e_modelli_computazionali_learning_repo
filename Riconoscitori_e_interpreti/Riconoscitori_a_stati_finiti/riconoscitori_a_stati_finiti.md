### ASF e RSF
Un linguaggio regolare (tipo 3) è riconoscibile da un Automa a Stati Finiti (ASF).

**Un Riconoscitore a Stati Finiti (RSF) è una specializzazione di ASF**. Elenchiamo le differenze:
- unico alfabeto A per input e output
- caratterizzato da uno stato iniziale *s0* e un insieme di stati finali *F*
    - **Non c'è una uscita esplicita** ma si ha un insieme di stati finali (e quindi si ha bisogno di un unico alfabeto)
    - Si dice **frase accettata da un RSF** una frase x appartenente A* che porta il riconoscitore, a partire dallo stato iniziale, in uno
       stato finale; ovvero: sfn*(x,s0) appartente F
- La funzione _sfn*_ non considera un singolo ingresso alla volta ma direttamente **intere frasi del linguaggio**:
    - definisce l’evoluzione dell’automa a partire dallo stato iniziale s0 in corrispondenza a ogni **sequenza** di ingresso _x appartenente a A*_
        - **idea**: cambiare stato *n* volte, carattere per carattere, è equivalente a ricevere in input la relativa stringa di lunghezza *n* e saltare direttamente allo stato *Sn*
    - è definita in termini della funzione più classica funzione *sfn: AxS→S* 
        - _sfn*_ tratta non più il singolo simbolo dell’alfabeto A, ma una sequenza di simboli (cioè una stringa) di A
        - data la stringa *x-a* e stato *s*, si considera prima la **sottostringa** *x*, poi si applica la funzione *sfn* al simbolo * e allo stato _s’=sfn*(x,s)_


### TEOREMI PER DECIDERE SE UN LINGUAGGIO REGOLARE è VUOTO o FINITO (non ho ben capito l'utilità)
**OSS**: Il linguaggio L(R) accettato dal riconoscitore R è infinito se la rappresentazione grafica presenta cicli; finito se non ci sono cicli.

```
TEOREMA 1: Un linguaggio L(R) è non vuoto se e solo se il riconoscitore R accetta una stringa x di lunghezza Lx minore del numero di stati N dell’automa
```

In parole povere: Il teorema afferma che un linguaggio accettato da un automa (chiamato  L(R)) non è vuoto (cioè contiene almeno una stringa), se e solo se **l’automa può accettare una stringa di lunghezza minore del numero dei suoi stati**.

In altre parole, se l’automa ha N stati, il teorema ci dice che se esso accetta qualche stringa, allora esiste una stringa con una lunghezza inferiore a N che l’automa può accettare. Se non esistesse una stringa di questo tipo, il linguaggio L(R) sarebbe vuoto, ovvero l’automa non accetterebbe alcuna stringa ma questo condurrebbe ad un assurdo.


```
TEOREMA 2: Un linguaggio L(R) è infinito se e solo se il riconoscitore R accetta una stringa x di lunghezza N <= Lx < 2N, con N numero di stati dell’automa.
```

**CONSEGUENZE**:
In conseguenza di questi due teoremi, decidere se un linguaggio regolare sia vuoto o infinito è un problema risolubile!
- nel primo caso, basta esaminare se esiste una stringa accettata di lunghezza minore di N
- nel secondo caso, basta verificare se esiste una stringa accettata fra quelle di lunghezza compresa fra N (incluso) e 2N (escluso).

Come si vedrà più avanti, tali proprietà sono decidibili anche nel Tipo 2, mentre non lo sono nel Tipo 1 (e 0).





## MAPPING FRA GRAMMATICHE & RICONOSCITORI
Riconsiderando l'automa riconoscitore precedente, Se sostituiamo alla parola “accettare” la parola “generare” otteniamo un nuovo insieme di affermazioni:
- nello stato I l’automa genera a e si porta in A (per poi proseguire)
- nello stato A l’automa genera a e si riporta in A (per poi proseguire)
- nello stato A l’automa genera b e si ferma (F è uno stato finale)

**NB: Queste frasi sono interpretabili come PRODUZIONI di una qualche grammatica (regolare)!**

Si può definire un **mapping**:
- stati         -> simboli non terminali
- transizioni   -> produzioni
- scopo         -> uno stato particolare

**CONCLUSIONE**: Si può dunque costruire un RSF a partire dalla grammatica, o, viceversa, risalire a una grammatica dato un RSF (prevedibile siccome  grammatiche e RSF sono modi diversi di descrivere lo stesso linguaggio regolare). 

**DOMANDA FONDAMENTALE**
```
come si fa guardando un RSF a recuperare la relativa grammatica regolare?
```

Due approcci: si immagini un osservatore che, stando in ogni stato, guardi, rispettivamente:
- In avanti. Da ogni stato, vede dove si va:
    - stato di partenza                 = simbolo non terminale sinistro
    - la freccia col simbolo terminale  = primo simbolo a destra nella produzione
    - lo stato successivo               = successivo nuovo simbolo non terminale a destra
    - **NB**: lo stato finale non va scritto in quanto l'ultimo passo non ha un successivo (e quindi non ha una produzione)
    - **NB**: in questa maniera si ottiene una **grammatica regolare destra**

- All'indietro. Da ogni stato, vede da dove viene:
    - stato di partenza                 = simbolo non terminale sinistro
    - lo stato precedente               = nuovo simbolo non terminale a destra nella produzione
        - stavolta scrivo prima il non terminale (stato precedente) e poi il terminale (arco)
    - la freccia col simbolo terminale  = simbolo terminale a destra finale
    - **NB**: non c'è una regola per lo stato iniziale in quanto: il primo passo non ha un precedente
    - **NB**: in questa maniera si ottiene una **grammatica regolare sinistra**

**OSS**: Lo schema appena descritto evidenzia come una **grammatica regolare destra** si traduce nel percorrere il relativo RSF nel  **in avanti**; al contrario, una **grammatica regolare sinistra** si traduce nel percorrere l'RSF **a ritroso**. 

Il mapping fra automa riconoscitore e grammatica appena descritto presenta alcuni gradi di libertà, data una grammatica:
- se essa è regolare a destra, si ottiene un automa riconoscitore “top down”
- se essa è regolare a sinistra, si ottiene un automa riconoscitore “bottom up”

Viceversa, **dallo stesso RSF si possono recuperare due grammatiche**:
- una grammatica regolare a destra, interpretandolo top-down
- una grammatica regolare a sinistra, interpretandolo bottom-up

**In conclusione**, dato un automa riconoscitore, se ne possono trarre:
- una grammatica regolare a destra, interpretandolo top-down
    - **approccio a generazione**, si cerca di produrre la frase
    - per ogni regola del tipo *X → x Y*; l’automa, con ingresso x, si porta dallo stato X allo stato Y
    - per ogni regola del tipo *X → x*; l’automa, con ingresso x, si porta dallo stato X allo stato finale F
- una grammatica regolare a sinistra, interpretandolo bottom-up
    - **approccio a riduzione**, dalla frase si cerca di raggiungere lo scopo
    - per ogni regola del tipo *X → Y x* l’automa, con ingresso x, **riduce lo stato Y allo stato X**
    - per ogni regola del tipo *X → x* l’automa, con ingresso x, riduce lo stato iniziale I allo stato X.

- Il caso dello stato iniziale è particolare a nell’analisi bottom-up;
- il caso dello stato finale è particolare nell’analisi top-down

### STATI INIZIALI/FINALI DI TRANSITO
Esistono anche degli RSF in cui gli stati iniziali e finali hanno una **doppia natura** (e.g. quando presentano cicli).
- Non sono "puri" stati iniziale/finale, sono anche **stati di transito**. 
    - Sono stati da cui si parte/termina
    - Ma sono anche dei normali stati, analoghi a tutti gli altri, in cui si transita
- Nella grammatica ci saranno anche produzioni che comprendono gli stati iniziali/finali di transito (ma non quelli iniziali/finali per davvero)






NB_2: Non sempre però le cose vanno lisce:
    - l’analisi bottom-up può non essere immediata in presenza di più stati finali, che corrisponderebbero a scopi multipli, o in
      presenza di archi entranti nello stato iniziale
    - l’analisi top-down richiede attenzione se vi sono archi uscenti da stati finali.

Nel primo caso, per uscire dal dilemma, è utile esprimere il linguaggio riconosciuto o generato come UNIONE di due linguaggi, ciascuno
denotato da una sua propria grammatica che prende come scopo uno dei vari stati finali.
Attenzione: rimappando una grammatica ottenuta in questo modo in un automa, esso potrebbe risultare non-deterministico.
