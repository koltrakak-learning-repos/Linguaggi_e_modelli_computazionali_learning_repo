### NON DETERMINISMO
Anche un PDA può essere non deterministico: in tal caso, la funzione sfn produce __insiemi__ di elementi di W, ovvero W^k (W sottinsieme finito di SxZ*)

Il non-determinismo dell'automa può emergere sotto
due aspetti:
- l’automa, dallo stato Qi , con simbolo interno in cima allo stack Z, e con ingresso x, può portarsi in più stati futuri:
    - sfn(Qi, x, Z) = { (Q1,Z1), (Q2,Z2), … (Qk, Zk) }

- Un nuovo tipo di non determinismo è dovuto dalla presenza delle __mosse spontanee__. L'automa, dallo stato Qi, con simbolo in cima allo stack Z, e con ingresso x, può leggere o non leggere il simbolo d'ingresso x. Ciò accade se sono definite entrambe le mosse:
    - sfn(Qi, x, Z)
    - sfn(Qi, epsilon, Z)

In tal caso, infatti, l'automa può sia leggere x, sia non farlo. 

Stavolta, purtroppo, il non-determinismo non è risolvibile operando direttamente sull'automa come nel caso delle RSF. Bisogna andare a modificare il linguaggio.

    PDA non deterministico -> sintomo di un linguaggio disgusting -> soluzione: cambiare il linguaggio

### TEOREMA 
La classe dei linguaggi riconosciuti da un __PDA non-deterministico coincide con la classe dei linguaggi context-free__. Perciò qualunque linguaggio context free può sempre essere riconosciuto da un opportuno PDA nondet.

__Problema:__
- la complessità di calcolo del PDA non-deterministico può essere esponenziale con algoritmo non ottimizzato.
- i migliori algoritmi (Earley) hanno complessità dell'ordine del cubo della lunghezza della stringa da riconoscere, che si riduce al quadrato se la grammatica non è ambigua.
- Rimane però sovra-lineare…

#### Si può rinunciare ai PDA non deterministici?
In generale, no:

    TEOREMA: Esistono linguaggi context-free riconoscibili soltanto da PDA non-deterministici.

... ma in molti casi di interesse pratico, sì:

Esistono linguaggi context-free riconoscibili da PDA deterministici (linguaggi context-free deterministici).

.. e allora la cose cambiano, perché la complessità del PDA deterministico è __lineare__ rispetto alla lunghezza della stringa da riconoscere.

#### In altre parole: 
Per riconoscere il GENERICO linguaggio di tipo 2 è necessario un PDA __non-deterministico__
- un PDA deterministico non riconosce alcuni linguaggi di tipo 2 (i più brutti e meno interessanti)
- in molti casi di interesse è sufficiente un PDA deterministico (linguaggi belli e di interesse)
    - inoltre, in questo caso il costo del riconoscimento scende ad una complessità lineare.

### DOMANDA FONDAMENTALE
Che forma deve avere una grammatica di un linguaggio per poter essere riconosciuta da un PDA deterministico?

Sicuramente non deve succedere quanto detto sopra, ovvero che l’automa, in un dato stato Q0 , con simbolo in cima allo stack Z e ingresso x, possa:
- portarsi in più stati futuri, oppure ...
- ... optare se leggere o non leggere il simbolo di ingresso x a causa della presenza di una mossa spontanea 

Dovremo capire come __tradurre questi vincoli sulla grammatica__, in modo da sapere quali regole scrivere (e quali non scrivere) per assicurarsi che il risultato sia un linguaggio (context-free) deterministico.

#### Proprietà linguaggi deterministici
- Mix fra linguaggi deterministici
    - __L'unione, l'intersezione e il concatenamento__ di due linguaggi deterministici __non__ dà necessariamente luogo a un linguaggio __deterministico__.
    - Il __complemento__ di un linguaggio deterministico invece __è deterministico__ (ovvio…).
- Mix fra linguaggi deterministici e regolari
    - Aggiungere ad un linguaggio deterministico dei pezzi di un linguaggio regolare lo lascia deterministico!
    - Se L è un linguaggio deterministico e R un linguaggio regolare, __il linguaggio quoziente L/R__ (ossia l'insieme delle stringhe di L private di un suffisso regolare) è __deterministico__.
    - Se L è un linguaggio deterministico e R un linguaggio regolare, __il concatenamento L.R__ (ossia l'insieme delle stringhe di L con un suffisso regolare) è __deterministico__.