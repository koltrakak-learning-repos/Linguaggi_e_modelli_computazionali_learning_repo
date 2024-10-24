### Assegnamenti

    x = x + 1 è un obrobrio.

Uguale non vuol dire uguale e il simbolo x significa due cose diverse a sinistra o a destra dell'uguale.

Questo lavoro non è LL(1) manco a piangere, richiede LL(2) in quanto dopo x devi andare a controllare se c'è anche un uguale.

Altro grande tema è capire se x=x+1 è una cosa accettabile o meno, ovvero se è lecito o meno avere un __assegnamento distruttivo__. In questo caso, per capire che valore è associato ad una variabile in un dato punto, l'unica cosa possibile è simulare il comportamento del programma fino a quel punto (debug).
    -> essere umano si abbassa al livello della macchina.

L'idea del riscrivere una variabile dopo che le hai gia dato un valore è tipica del modello imperativo (ispirato dalla macchina di turing).

Una alternativa è quella dei linguaggi logichi. Le variabili possono subire un solo assegnamento e in seguito non possono venire più modificate. Si computa sintetizzando nuovi valori e non modificando variabili.

Con questo approccio si ha __trasparenza referenziale__ un simbolo mantiene il suo valore in qualsiasi punto di un programma (come in una dimostrazione matematica).

NB: assegnamenti distruttivi bloccati non significa costante! Con una costante si conosce il valore a priori. Variabili a singolo assegnamento sono "variabili" nel senso che a runtime possono assumere valori diversi però non si contempla la assegnazione distrutttiva. 

### Environment
tabella a due colonne che mantiene la associazioni simbolo-valore. Se il linguaggio è a singolo assegnamento non si può modificare una riga gia presente. Nei linguaggi imperativi invece questo è possibile. 

Adesso possiamo dare significato alla parola assegnamento: modifica dell'environment ... effetto collaterale (chiedi a chatgpt) ... 

il mondo prima dell'assegnamento è quello dopo sono diversi!

### Environment multipli e blocchi
...

### Assegnamenti multipli
differenza tra espressioni
- ritornano qualcosa e non modificano l'ambiente
e istruzioni
- non ritornano niente ma il loro obiettivo e produrre un effetto collaterale, cioè modficare l'ambiente

assegnamento singolo = istruzione ; modifico l'ambiente ma non denoto un valore (non ritorno nulla)
assegnamento multiplo = espressione; ritorno il valore che sto assegnando -> y = 8 ritorna 8;

OSS: per permettere l'assegnamento multiplo l'assegnamento va definito come una espressione al posto di una istruzione. uhm...

## Estensione dell'interprete