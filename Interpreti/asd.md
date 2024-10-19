fino ad ora abbiamo considerato solamente dei puri riconoscitori
- solo sintassi
ma noi vogliamo degli interpreti, ovvero automi che oltre a riconoscere una frase faccia anche qualcosa
- anche semantica

NB: adesso dobbiamo anche stare attenti all'ordine di derivazione in quanto esso definisce della semantica

### STRUTTURA
- scanner
    - parti regolari del linguaggio
    - RSF
    - produce token che da in pasto al parser
- parser

...

separazione delle parole chiave dagli identificatori __a livello semantico e non sintattico__.

## Il caso di studio classico: LE ESPRESSIONI ARITMETICHE

OSS: 'num' è una sorta di simbolo non terminale placeholder

### PROBLEMI DELLA PRIMA GRAMMATICA
- è ambigua per ogni espressione non banale (pensa ad n + n + n). 
- non c'è niente nella grammatica che definisca l'idea di priorità delle operazioni

    piccola digressione, il meno dei numeri negativi (meno unario) è diverso rispetto al meno delle sottrazioni (meno binario)

### GRAMMATICHE A STRATI
idea: aggiungere delle regole per esprimere dei vincoli che non lasciano spazio all'ambiguità

per dare significato ad una espressione, bisogna dare significato prima ai termini, ma ancora prima i fattori

è come avere tre grammatiche/linguaggi separati
- per lo strato azzurro è come se term fosse un simbolo terminale
    - term inizio di un sotto linguaggio 
- stesso discorso per term e factor
- nello strato rosso invece l'exp tra parentesi è allo stesso livello del num
    - di nuovo exp è come se fosse un terminale per la grammatica rossa

Con una grammatica a strati si riesce ad esprimere al livello di grammatica i concetti di priorità e associatività
- il livello più basso è quello con più priorità viceversa il livello più alto è con la priorità più bassa
- i livelli blu e gialli hanno una ricorsione sinistra e di conseguenza producono una derivazione sinistra

__NB__: Inoltre, scrivere grammatiche in questo modo elimina la disambiguità.

ogni livello ha la sua responsabilità

gli strati superiori aggregano cose dei livelli inferiori che considera come terminali

    flash: le parentesi pilotano l'albero

---

ricorsione sinistra non è mai LL(1) però ci serve perchè abbiamo bisogno di associatività a sinistra, or do we?
 
### vogliamo rendere la grammatica LL(1)

    trasformiamo una ricorsione in un ciclo

al posto di tradurre ogni metasimbolo con una funzione e di usare la ricorsione (seguendo pari pari la grammatica), possiamo cercare di capire cosa ci sta dicendo la grammatica, e utilizzare un processo diverso (iterazione) che ci permette di ottenere una grammatica ll(1) che mantiene la semantica del linguaggio e salva lo stack.x 

## Come faccio a dare semantica?
solo dopo aver riconosciuto la frase (dominio) posso attivare la fase di valutazione (funzione di interpretazione)

...

per dare significato ad un nuovo simbolo  devo utilizzare il significato di simboli che gia conosci

    affiancare ad ogni regola sintaticca una regola semantica!