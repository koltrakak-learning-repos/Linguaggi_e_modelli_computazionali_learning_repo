
... 

partiamo da slide 150

...

perchè immutabilità?
- è più funzionale
- computa per sintesi e non per accumulo
- si eliminano data hazard con i thread

spesso la gente confonde i meccanismi con espressività
- tutto al mondo è turing equivalente e quindi posso costruirmi tutto quello che voglio con i meccanismi a mia disposizione
- l'espressività è data dal linguaggio che supporta nativamente la composizione di meccanismi che dovresti fare te
- se il linguaggio non ti viene in contro, la bellezza e la manutenibilità di quello che scriverai sarà ben diverso

...

### slide 223 | perchè c'è stato bisogno del bottom type nothing?
il fatto che tutto sia una espressione implica che tutto abbia anche un tipo! Ed anche il fatto che questi tipi siano compatibili.

Con le istruzioni è facile perchè non denotano nessun valore e quindi nessuno tipo. Però infatti, non sono montabili! 

...

### Tratti sono praticamente una evoluzione delle interfacce classiche