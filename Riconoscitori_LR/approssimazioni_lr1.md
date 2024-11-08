### Contesto del SLR 
il contesto dell'LR(0) non ce la fa e concatena l'insieme dei follow

NB: in questo caso si considera una informazione sovrabbondante in quanto l'insieme dei follow non considera solamente i caratteri che seguono A nelle produzioni A -> alpha, ma l'insieme dei caratteri che seguono A in generale. Invece, LR(1) è più preciso e chirurgico.

Il contesto LR(1) per questo motivo è un sottoinsieme del contesto SLR(1), ma in generale coincidono o non differiscono di troppo per linguaggi pensati bene.

NB: a causa dei caratteri spuri potrebbero saltare dei conflitti che in realtà nell'analisi LR(1) precisa non si presenterebbero. Se però non si ottengono dei conflitti. Si riesce a fare il parsing semplificando molto LR.

...


### PARSER SLR
... Spazzatura dei conflitti presenti in LR(0) con SLR ...
LR(0) diventa una sorta di passaggio intermedio. In seguito, l'insieme dei follow bisogna calcolarselo per forza ed è questo insieme che consente di disambiguare ed elimare i conflitti.

__CONSIGLIO DI DENTI__: l'importante è capire l'idea e non tanto guardarsi tutti gli esempi meticolosamente

### PARSER LALR
idea accorpare insieme gli stati che seppur accorpati non producono conflitti. 

LR(0) accorpa tutto indiscriminatamente e quindi produce conflitti
LR(1) separa troppo chirurgicamente e produce mille stati che se anche accorpati insieme non produrrebbero conflitti 


### DOMANDE DA FARE
1) perchè c'è bisogno di LR? è solo una alternativa? 
2) la distinzione non è tra grammatiche deterministiche e non deterministiche? che cosa centra l'ambiguità?