### LIMITI DEI RICONOSCITORI A STATI FINITI
Un RSF __non può riconoscere un linguaggio di Tipo 2__. Un RSF ha infatti un limite __intrinseco, strutturale e prefissato__, alla propria capacità di memorizzazione (il numero di stati è congelato al momento di definizione della macchina).

Ergo, non riesce a riconoscere frasi che richiedano, per la loro verifica, di memorizzare una parte di lunghezza non nota a priori

ESEMPIO: il bilanciamento delle parentesi

Problema nel dare un upper bound al numero degli stati appropriato
- numero di stati spropositato -> spreco di risorse
- numero di stati contenuto -> facile eccedere
- non si riesce a determinare un numero di stati a priori

NB: tutto cambierebbe se si potesse definire un limite a priori, in quel caso un RSF è sufficiente.

## PUSH-DOWN AUTOMATON (PDA)
Per riconoscere un linguaggio di Tipo 2 serve un nuovo tipo di automa, che superi il limite di memoria finita (limitata a priori) del RSF.

Tale automa dovrà necessariamente appoggiarsi a una struttura dati esterna ad esso, espandibile quanto serve – ad esempio, uno STACK.

Alla fine della fiera se non riesco a determinare il numero di stati a priori, li metto fuori.
- supporto di memoria ulteriore rispetto agli stati -> stack 
- PDA = RSF + STACK

### DEFINIZIONE PDA
![alt text](immagini/definizione_PDA.png)

- Come un RSF, un PDA legge un simbolo d’ingresso e transita in un nuovo stato

- In più, a ogni passo __altera lo stack__. Lo stato futuro e la nuova configurazione dello stack sono  __funzione__ sia del __simbolo d’ingresso__ sia di quello attualmente in __cima allo stack__ (e, chiaramente, dello stato corrente).

- Adesso si può giocare da due parti, posso avere un gran numero di stati e usare poco lo stack o viceversa. Spesso è più conveniente il secondo metodo. 
    
- **NB**: Il minimo numero di stati è due (un singolo stato è uguale a non avere stati)

- Un PDA può o meno prevedere __epsilone-mosse__, una sorta di transizioni “spontanee” che manipolano lo stack senza consumare simboli di ingresso.
    - ha senso tenere le epsilon-moves, per permettere al PDA  di dare una risistemata allo stack -> **Mosse spontanee**

![alt text](immagini/sfn_PDA.png)

#### STACK
Formalmente, lo stack è definito come sequenza di simboli, definita in modo tale che si possa operare solo su quello più a destra (quello "in cima" alla pila).

- stack implementato come __stringa__.

Dentro allo stack non risiede lo stesso alfabeto dei simboli di input in quanto una rappresentazione interna dei dati (comoda per il programmatore) è utile.
- alfabeto Z dei simboli interni.

Inoltre, è furbo imporre che lo stack abbia un contenuto iniziale (esattamente come esiste uno stato iniziale)

**NB**: per sapere che cosa c'è al top dello stack bisogna fare una pop, se non c'è niente la macchina si rompe. Ad ogni passo del riconoscimento si fanno quindi **una pop ed una o più push** (se non si vuol distruggere il simbolo in cima, si rifà push del simbolo appena poppato)

#### Linguaggio accettato da un PDA
è definibile in 2 modi equivalenti:

- **Criterio dello stato finale**: come in un RSF, il linguaggio accettato è l’insieme di tutte le stringhe di ingresso che portano il PDA in uno degli stati finali.

- **Criterio dello stack vuoto**: appoggiandosi al nuovo concetto di stack, il linguaggio accettato è definito come **l'insieme di tutte le stringhe di ingresso che portano il PDA nella configurazione di stack vuoto** (useremo questo).

#### Takeaway dell'esempio utile
- Un design tipico di un PDA comprende due stati:
    - **fase ascendente** = accumulo
    - **fase discendente** = verifica/svuotamento 
    - due stati = minimo necessario. Alla memorizzazione ci pensa lo stack!
- In Z* bisogna ricordarsi di ripushare anche quello che si è consumato (se necessario)



## PDA NON DETERMINISTICI
Anche un PDA può essere non deterministico: in tal caso, la funzione *sfn* produce __insiemi di elementi di W__; ovvero, più coppie [stato futuro; configurazione stack] W^k (con W sottinsieme finito di SxZ*) possibili per la stessa tripla in ingresso [stato corrente, cima stack, simbolo in input].

Il non-determinismo dell'automa può emergere sotto due aspetti:
1. l’automa, dallo stato Qi , con simbolo interno in cima allo stack Z, e con ingresso x, può portarsi in più stati futuri:
    - sfn(Qi, x, Z) = { (Q1,Z1), (Q2,Z2), … (Qk, Zk) }

2. Un nuovo tipo di non determinismo è dovuto dalla presenza delle __mosse spontanee__. L'automa, dallo stato Qi, con simbolo in cima allo stack Z, e con ingresso x, **può leggere o non leggere il simbolo d'ingresso x**. Ciò accade se sono definite entrambe le mosse:
    - sfn(Qi, x, Z)
    - sfn(Qi, epsilon, Z)

In tal caso, infatti, l'automa può sia leggere x, sia non farlo. 

### Riconoscimento dei linguaggi context-free
Stavolta, purtroppo, **il non-determinismo non è risolvibile operando direttamente sull'automa come nel caso delle RSF**.
- Bisogna andare a modificare il linguaggio...
- Questa però non è una sconfitta troppo amara in quanto: PDA non deterministico -> sintomo di un **linguaggio disgusting** -> soluzione: cambiare il linguaggio

TEOREMA PDA e Linguaggi Context-free
``` 
La classe dei linguaggi riconosciuti da un PDA non-deterministico coincide con la classe dei linguaggi context-free. Perciò qualunque linguaggio context free può sempre essere riconosciuto da un opportuno PDA non-determistico.
```

__Problema:__
- la complessità di calcolo del PDA non-deterministico può essere esponenziale con algoritmo non ottimizzato.
    - devo provare a prendere strade multiple e nel caso sbagli devo fare backtracking sprecando computazioni
- i migliori algoritmi (Earley) hanno complessità dell'ordine del cubo della lunghezza della stringa da riconoscere, che si riduce al quadrato **se la grammatica non è ambigua**.
- Rimane però sovra-lineare…

**Si può rinunciare ai PDA non deterministici?**

In generale no:

TEOREMA:
```
Esistono linguaggi context-free riconoscibili SOLTANTO da PDA non-deterministici.
```

Ma in molti casi di interesse pratico, sì: Esiste un sottoinsieme dei linguaggi context-free riconoscibile da PDA deterministici (linguaggi context-free deterministici).

In pratica, i linguaggi context-free riconoscibili solamente con un PDA nondet. sono quelli più "brutti" e di meno interesse. È quindi possibile restringersi al cercare di riconoscere solamente i linguaggi context-free deterministici senza perdere troppa espressività nei linguaggi che si possono produrre. 

**NB**: E allora la cose cambiano, perché la complessità del PDA deterministico è __lineare__ rispetto alla lunghezza della stringa da riconoscere.

**RIASSUMENDO**: 
Per riconoscere il GENERICO linguaggio di tipo 2 è necessario un PDA __non-deterministico__
- un PDA deterministico **non** riconosce alcuni linguaggi di tipo 2 (i più brutti e meno interessanti)
- in molti casi di interesse è sufficiente un PDA deterministico (linguaggi belli e di interesse)
    - inoltre, in questo caso il costo del riconoscimento scende ad una complessità lineare in quanto non c'è bisogno di valutare strade diverse e di fare backtracking.




### DOMANDA FONDAMENTALE (qua ci devi tornare)
```
Che forma deve avere una grammatica di un linguaggio per poter essere riconosciuta da un PDA deterministico?
```

Sicuramente NON deve succedere quanto detto sopra; ovvero che l’automa, in un dato stato Q0, con simbolo in cima allo stack Z e ingresso x, possa:
- portarsi in più stati futuri, oppure ...
- ... optare se leggere o non leggere il simbolo di ingresso x a causa della presenza di una mossa spontanea 

Dovremo capire come __tradurre questi vincoli sulla grammatica__, in modo da sapere quali regole scrivere (e quali non scrivere) per assicurarsi che il risultato sia un linguaggio context-free deterministico.

(anticipazione: sono le grammatiche LL(k) e più in generale LR(k))

### Proprietà linguaggi deterministici (vabbè...)
- Mix fra linguaggi deterministici
    - __L'unione, l'intersezione e il concatenamento__ di due linguaggi deterministici __non__ dà necessariamente luogo a un linguaggio __deterministico__.
    - Il __complemento__ di un linguaggio deterministico invece __è deterministico__ (ovvio…).

- Mix fra linguaggi deterministici e regolari
    - Aggiungere ad un linguaggio deterministico dei pezzi di un linguaggio regolare lo lascia deterministico!
    - Se L è un linguaggio deterministico e R un linguaggio regolare, __il linguaggio quoziente L/R__ (ossia l'insieme delle stringhe di L private di un suffisso regolare) è __deterministico__.
    - Se L è un linguaggio deterministico e R un linguaggio regolare, __il concatenamento L.R__ (ossia l'insieme delle stringhe di L con un suffisso regolare) è __deterministico__.