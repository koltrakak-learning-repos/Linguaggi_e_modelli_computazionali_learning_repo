### Il problema della ricorsione sinistra
![alt text](immagini/problema_ricorsione_sinistra.png)

Ricorda: la ricorsione sinistra può in teoria sempre essere sostituita con una ricorsione destra, tuttavia questa trasoformazione cambia l'ordine di derivazione e quindi cambia anche la semantica.

## Se la grammatica non è LL(1) che cosa si fa?
Varie strategie:
- si può cercare di riorganizzarla con sostituzioni, raccoglimenti, etc.
- oppure, si può cercare di modificare qualche regola "fastidiosa"
- oppure ancora, si può passare al caso LL(k), con k>1
    - le definizioni di FIRST, FOLLOW, etc. si estendono facilmente al caso LL(k) con k>1 → FIRSTk, FOLLOWk, etc.

Ma tutto ciò potrebbe non bastare. Non tutti i linguaggi context-free possiedono una grammatica LL(k)

    Esistono linguaggi DETERMINISTICI che non sono LL(k) per nessun k

Inoltre

    In generale non si può sapere se per un linguaggio ammetta una grammatica LL(1): stabilire se un linguaggio sia LL(1) è un problema INDECIDIBILE

Al contrario, ovviamente, stabilire se una grammatica sia LL(1) è un problema decidibile -> basta usare i Director Symbols

Come fare? __Esistono però tecniche più potenti dell'analisi LL!__

Le grammatiche LR(k) consentono l’analisi deterministica delle frasi Left to right, con Right-most derivation, usando k simboli di lookahead.
- L'analisi LR è meno naturale dell'analisi LL ma è superiore dal punto di vista teorico: «arriva dove l’LL non arriva»
- Infatti, vi sono linguaggi context-free deterministici non analizzabili in modo deterministico con tecniche LL, ma riconoscibili in modo deterministico con tecniche LR 
    - le vedremo più avanti ☺

### dangling else problem

    regole ambigue? NO!!!