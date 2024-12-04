...

### Chiusure in javascript

1. Rappresentare uno stato privato e nascosto
    - Utile per realizzare factory di oggetti
    - Utile per realizzare un canale di comunicazione passando degli oggetti

2. Realizzare nuove strutture di controllo

...

Attenzione però ai riferimenti...

## Modello a prototipi

### Definizione di oggetti

...

con Funzione costruttrice e operatore new (di fatto si chiama la funzione costruttrice con argomento nascosto uguale alla memoria necessaria)

**ATTENZIONE a THIS: Negli esempi è fondamentale**

**Il qualificatore private non è necessario**; basta usare delle chiusure

...

Le classi fissano la struttura


## Prototipi di oggetti
ereditarietà tra oggetti e non tra classi. Qua è tutto più fluido, non ho la (conveniente) rigidità delle classi

I costruttori predispongono un campo \_\_proto\_\_ che popolono con i prototipi degli oggetti che creano

**perchè ricorsione**?
anche nel prototipo dei literal

![alt text](image.png)