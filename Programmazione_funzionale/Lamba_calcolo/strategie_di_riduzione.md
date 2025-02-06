### FORME NORMALIZZATE
Un lambda termine è in forma normale se non si possono applicare altre riduzioni
- **NB**: la raggiungibilità della forma normale può dipendere dall'ordine con cui si riduce

Si dice che un Lambda termine è:
- fortemente normalizzabile, se qualunque sequenza di riduzioni porta a una forma normale
- debolmente normalizzabile, se esiste almeno una sequenza di riduzioni che porta a una forma normale
- non normalizzabile se non si arriva mai a una forma normale

### La computazione del lamba calcolo termina o no?
Anche qua possiamo andare in loop (d'altronde siamo turing equivalenti). Ad es. funzione notevole W

**Teorema di Church-Rosser**:
Ogni lambda ha al più una forma normale
- essa può quindi essere interpretata come risultato
- il lambda-calcolo è deterministico: i grafi aciclici hanno una e una sola foglia

Ma.. siamo sicuri di raggiungerla?
- sì, se il termine è fortemente normalizzabile
- no, se il termine è solo debolmente normalizzabile

In tal caso è cruciale scegliere il "giusto" **ordine di valutazione/riduzione** della lambda per non incappare in un loop di derivazione infinito
- **importanza della strategia di valutazione**

**Interpretazione**:
- Lambda termini fortemente normalizzabili rappresentano computazioni che terminano sempre
    - esempio: la computazione f(2,4) con int f(int x,int y){ return x+y+1; }

- Lambda termini debolmente normalizzabili rappresentano computazioni che terminano solo se si sceglie il "giusto" ordine di valutazione
    - esempio: la computazione f(0) con int f(int x){ return x==0 ? 0 : f(x); }
    - se cerco di risolvere la funzione più interna mi inlooppo

- Lambda termini non normalizzabili rappresentano computazioni che non terminano in alcun caso
    - esempio: la computazione f(2) con int f(int x){ return f(x); }




### Strategie di riduzione
Il risultato del lambda calcolo si ottiene riducendo il più possibile una espressione.

L'idea di ridurre "il più possibile" introduce il concetto di strategia di riduzione:
- d'accordo ridurre "il più possibile", ma.. come ?
- la strategia può fare la differenza: scegliendo «male» la strategia, **le sostituzioni potrebbero talora non terminare**.

Due principi ispiratori alternativi:
- **eager evaluation**:
    - **privilegiare la valutazione dell'argomento**, rispetto all'applicazione dell'argomento alla funzione
    - valuta l'argomento prima possibile (strict evaluation)
- **lazy evaluation**:
    - **privilegiare l'applicazione dell'argomento alla funzione**, rispetto alla valutazione dell'argomento
    - valuta l'argomento più tardi possibile, "solo quando serve" (non-strict evaluation)

Adottare una strategia o l'altra può fare la differenza:
- sostituzioni infinite → non avere un risultato
- sostituzioni finite → arrivare a un risultato

**IMPORTANTE**: Le famiglie di call by name e call by value, nascono da come si sceglie l'ordine di valutazione di una espressione nel lambda calcolo  

**Strategie eager | Call by value (applicative order)**
- prima riduce gli argomenti, poi li applica alle funzioni
    - **rightmost innermost**
- non è una strategia "normalizzante": potrebbe non trovare la forma normale in presenza di termini debolmente normalizzabili
    
**Strategie lazy | Call by name (normal order)**
- riduce PRIMA la lambda PIÙ A SINISTRA, considerando come corpo l'intero blocco che compare alla sua destra
    - **valuta dall'esterno verso l'interno**
    - **leftmost outermost**
- "normale" perché se esiste una forma normale, la trova

// esempi
...

