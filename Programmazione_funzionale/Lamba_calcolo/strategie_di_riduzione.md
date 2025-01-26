### Strategie di riduzione
Il risultato del lambda calcolo si ottiene riducendo il più possibile una espressione.

L'idea di ridurre "il più possibile" introduce il concetto di strategia di riduzione:
- d'accordo ridurre "il più possibile", ma.. come ?
- la strategia può fare la differenza: scegliendo «male» la strategia, **le sostituzioni potrebbero talora non terminare**.

Due principi ispiratori alternativi:
- **eager evaluation**: privilegiare la valutazione dell'argomento, rispetto all'applicazione dell'argomento alla funzione
    - valuta l'argomento prima possibile (strict evaluation)
- **lazy evaluation**: privilegiare l'applicazione dell'argomento alla funzione, rispetto alla valutazione dell'argomento
    - valuta l'argomento più tardi possibile, "solo quando serve" (non-strict evaluation)

**IMPORTANTE**: Le famiglie di call by name e call by value, nascono da come si sceglie l'ordine di valutazione di una espressione nel lambda calcolo  

### Strategie eager
...

### Strategie lazy
...

### Esempi
...