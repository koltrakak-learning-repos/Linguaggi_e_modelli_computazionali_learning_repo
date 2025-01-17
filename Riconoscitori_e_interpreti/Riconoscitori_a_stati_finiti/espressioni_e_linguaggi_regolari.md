**TEOREMA**
```
l'insieme dei linguaggi riconosciuti da un ASF COINCIDE con l'insieme dei LINGUAGGI REGOLARI, ossia quelli descritti da
espressioni regolari.
```

- **Regexp e ASF** sono metodi descrittivi appartenenti a due differenti categorie ma **entrambi  descrivono linguaggi regolari**
    - Gli automi a stati sono un metodo di **descrizione operazionale,** in quanto **evidenziano i passi computazionali da compiere per riconoscere le frasi**
        - che quindi sono descritte tramite le operazioni necessarie a riconoscerle.
    - Le espressioni regolari sono invece un metodo di **descrizione denotazionale**, in cui un’entità è specificata tramite operatori (funzioni) di tipo matematico.

**CONCLUSIONE**: gli automi a stati finiti diventano un **terzo punto di vista con cui osservare linguaggi regolari**, insieme a grammatiche e espressioni regolari.

- **Grammatiche**: ci dicono come vengono generate le frasi di un linguaggio
- **Regexp**: ci dicono che frasi possono venire generate
- **RSF**: ci dicono quali sono i **passaggi** per riconoscere una certa frase 

**OSS**: per ottenere l'espressione regolare più semplice possibile, è utile passare dalla rappresentazione mediante ASF, minimizzarla, e ritornare alla rappresentazione con reg exp.

### VARI ESEMPI NEL FILE
guarda bene file di esempio...

sopratutto quello con le epsilon-moves
- tante frecce in uscita dallo stato con la epsilon-rule, tante quante quelle che escono dallo stato puntato dalla epsilon-rule
- successiva minimizzazione