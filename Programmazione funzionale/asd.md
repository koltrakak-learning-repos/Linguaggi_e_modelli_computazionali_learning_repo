### Introduzione argomenti
1. Separazione variabili valori
    - variabile -> assegnamenti multipli distruttivi
    - valori -> assegnamento singolo

2. Differenza tra espressioni e istruzioni / funzioni e procedure
    - ...
    - ...

3. Il paradigma funzionale preferisce le funzioni, nell'ottica di favori la composizionalita
    - se ritorno qualcosa espongo un gancio a quelli che si vogliono attaccare con me
    - Tutto diventa una funzione/espressione anche costrutti come if e eccezioni

4. Anche le strutture dati diventano a singolo assegnamento
    - spariscono le corse critiche e problemi di sincronizzazioni

5. **Funzioni come first-class entities**
    - vedere le funzioni come un dato di tipo function
    
6. Lazy evaluation vs eager evaluation
    - il modo di chiamare le funzioni "tradizionale" non è l'unico, spesso va bene, ma esistono alternative.

7. Distinguere tra operatori e funzioni è arbitrario

8. Abolizione dei tipi primitivi

## Carrellata

### Uniformità, tutto è un oggetto
...
don't be static

...

### Costrutti come espressione

### Collezioni e oggetti immodificabili
compute by sinthesis

...

### Funzioni come first class entities
...
funzioni di ordine superiore, ovvero funzioni che hanno come argomento altre funzioni

di che tipo sono le funzioni?

#### Funzioni come tipi
due approcci:
- nominale: vince la semantica; se due cose hanno nomi diversi allora sono diversi anche se hanno la stessa struttura
    - pro: semantica
    - contro: producono un puttanaio di nomi
- strutturale
    - dice come è fatta la funzione
    - risparmio nomi

#### Funzioni di ordine più alto
...
si privilegia la facilità del chiamante rispetto a quello del chiamato