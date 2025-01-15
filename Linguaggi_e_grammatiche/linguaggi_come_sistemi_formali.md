## Che cos'è un linguaggio?
Dice il dizionario:
    
    “Un linguaggio è un insieme di parole e di metodi di combinazione delle parole usate e comprese da una comunità di persone.”
    
- insieme FINITO di parole
- metodi di COMBINAZIONE di parole
- compreso da comunità di persone (un linguaggio serve a comunicare con ALTRI)

NON BASTA! È una definizione poco precisa:
- non evita le ambiguità dei linguaggi naturali
- non si presta a descrivere processi computazionali meccanizzabili
- non aiuta a stabilire proprietà

Occorre una definizione di linguaggio meno evocativa e più precisa.

```
LINGUAGGIO COME SISTEMA FORMALE!
```

che consenta di risponde a domande come:
- quali sono le frasi lecite?
    - il problema che abbiamo visto nella intro alla teoria della computabilità, si può stabilire se una frase appartiene al linguaggio?
- come si stabilisce il **SIGNIFICATO** di una frase?
- quali elementi linguistici primitivi?

## Definizioni preliminari | sintassi e semantica; interpreti e compilatori

### Sintassi e semantica
- Sintassi: l’insieme di regole formali per la scrittura di frasi corrette (appartenenti) in un linguaggio.
- Semantica: l’insieme dei significati da attribuire alle frasi (sintatticamente corrette) del linguaggio.

**NB**: Una frase può essere sintatticamente corretta e tuttavia non avere significato!

La sintassi è solitamente espressa tramite notazioni formali come
- BNF, EBNF
- diagrammi sintattici

La semantica è esprimibile:
- a parole (poco precisa e ambigua)
- mediante azioni ...
- mediante formule logiche ...
- **mediante funzioni matematiche -> semantica denotazionale**

### Interpretazione e compilazione
Un interprete per un linguaggio L:
- accetta in ingresso le singole frasi di L
- e le esegue una per volta.
- Il risultato è la valutazione della frase (si sottintende che le frasi siano corte).

Un compilatore per un linguaggio L, invece:
- accetta in ingresso un intero programma scritto in L
- e lo riscrive in un altro linguaggio (più semplice).
- Il risultato è dunque una riscrittura del programma ("macro-frase")

**NB**: In realtà la differenza è sottile; Ad esempio, un intero programma potrebbe essere considerato una singola frase lunga. In generale, un interprete produce direttamente il risultato finale "che ci si aspetta". Un compilatore produce un passaggio intermedio (ad esempio codice macchina) che va di nuovo interpretato/compilato. La differenza tuttavia, è nell'occhio di chi osserva, se ci si apetta dell'assembly, anche un compilatore potrebbe essere considerato un interprete. 




## Come si analizzano le frasi di un linguaggio? Analisi lessicale, sintattica e semantica
L’analisi lessicale consiste nella **individuazione delle singole parole** (token) di una frase.
- Un lessico è corretto se ogni parola (token) fa parte del vocabolario.
- L'analizzatore lessicale (detto scanner o lexer), data una sequenza di caratteri, li aggrega in token di opportune categorie (nomi, parole chiave, simboli di punteggiatura, etc.)
- Se il codice passa la fase di analisi lessicale, si ottiene  una serie di Token validi per il linguaggio (magari anche organizzati per categoria)

L’analisi sintattica consiste nella verifica che la frase (intesa come sequenza di token) rispetti le regole grammaticali del linguaggio. **Una sintassi è corretta se la combinazione dei token è corretta!**(token giusto al posto giusto; e.g. <id> = <exp> e non <exp> = <id>) 
- Ad esempio, la frase "Il gatta mangio" possiede tutti i token correti ma combinati in modo sbagliato.
- L'analizzatore sintattico (detto parser), data la sequenza di token prodotta dallo scanner, genera una rappresentazione interna della frase (solitamente sotto forma di opportuno albero), più facilmente visitabile e valutabile per la prossima fase di analisi semantica.
 
L’analisi semantica consiste nel **determinare il significato di una frase**
- L'analizzatore semantico, data la rappresentazione intermedia prodotta dal parser, controlla la coerenza logica della frase
    - se le variabili sono usate solo dopo essere state definite
    - se sono rispettate le regole di compatibilità in tipo
    - …
- (Può anche trasformare ulteriormente la rappresentazione delle frasi in una forma più adatta alla generazione finale di codice)

**DOMDANDA FONDAMENTALE**: Ma che cosa significa dare significato ad una frase? 
- Risposta: associare a quella frase un concetto.

In base alla nostra cultura la frase può evocare o meno una esperienza passata. Per farlo, nella nostra mente deve evidentemente esserci una **FUNZIONE DI MAPPING** che associa a **ogni frase**, cioè ogni sequenza di token lecita nel linguaggio, **un concetto**, cioè un elemento di un qualche dominio.

Tale funzione deve quindi dare significato:
- prima a ogni simbolo (carattere dell'alfabeto)
- poi a ogni parola (sequenza lecita di caratteri)
- infine a ogni frase (sequenza lecita di parole).




### Definizioni riguardanti i linguaggi:
- **Alfabeto**: un alfabeto A è un insieme finito e non vuoto di simboli atomici. Esempio: A = { a, b }
- **Stringa**: un stringa è una sequenza di simboli, ossia un elemento del prodotto cartesiano A^n. 
    - Esempi: a ab aba bb …
    - lunghezza di una stringa:  il numero di simboli che la compongono.
    - stringa vuota: stringa di lunghezza zero. (fastidiosa...ma necessaria)
- **Linguaggio L su un alfabeto A**: un linguaggio L è un insieme di stringhe su A.
    - frase di un linguaggio: una stringa appartenente a quel linguaggio
    - cardinalità di un linguaggio: il numero delle frasi del linguaggio
        - linguaggio finito: ha cardinalità finita
        - linguaggio infinito: ha cardinalità infinita
        - **NB**: i linguaggi utili sono tipicamente con cardinalità infinita. Non avrai mai in mano un programma con infinite keyword, ma avrai infiniti modi di combinare quest'ultime.
- **Chiusura A* di un alfabeto A** (o ling. universale su A): È l’insieme **infinito** di tutte le stringhe composte con simboli di A:
    - A* = A^0 (= epsilon) unito A^1 unito …
- **Chiusura positiva A+ di un alfabeto A**: È l’insieme infinito di tutte le stringhe NON NULLE composte con simboli di A
    - A+ = A* - { epsilon }

### SPECIFICA DI UN LINGUAGGIO
**Problema**: come specificare il sotto-insieme di A* che definisce uno specifico linguaggio?
- per specificare un linguaggio finito, basta ovviamente elencarne tutte le frasi
- per specificare un linguaggio infinito, invece, serve una qualche notazione capace di descrivere in modo finito un insieme infinito di elementi.

Per i linguaggi con cardinalità infinita, nasce a questo scopo la nozione di **GRAMMATICA FORMALE**!

