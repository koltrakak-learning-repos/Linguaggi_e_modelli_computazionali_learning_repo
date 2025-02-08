## Modello a prototipi
A differenza degli altri linguaggi, JavaScript adotta fin dalle origini un modello diverso, object-based ma non object-oriented. In cui **il concetto di classe viene sostituito dal concetto di prototipo** (e da quello di costruttore).

**Ad ogni oggetto viene associato** dal suo costruttore (oppure si usa un default) **un "oggetto padre"**, il suo prototipo appunto, di cui eredita le proprietà
- si parla perciò di **prototype-based inheritance**.

### Definizione di oggetti
Nel modello a prototipi, un oggetto può essere definito sostanzialmente in due modi:
- tramite un **object literal**, che introduce un singleton
    - non stabilisce relazioni con altri oggetti, né «parentele» fra essi
    - un oggetto di questo tipo ha come prototipo l'oggetto padre
- tramite un **costruttore**, ossia una funzione particolare, che inizializza l’oggetto ed è invocata per via indiretta, tramite **l’operatore new**


### Costruttori vs classi
Nel modello a prototipi i costruttori sostituiscono le classi, catturando le categorie di oggetti da rappresentare
- sono «tutto ciò che resta» delle classi

MA: mentre le classi fissano la struttura (campi dati) e il comportamento (metodi) delle future istanze, i costruttori stabiliscono solo lo **stato iniziale** degli oggetti futuri, che potranno poi evolvere indipendentemente.
- le classi fissano una struttura immutabile, i prototipi invece forniscono una struttura iniziale mutabile
- l'oggetto, nel frattempo, potrebbe essere diventato irriconoscibile rispetto alla struttura iniziale datagli dal costruttore

**NB**: Per questo motivo, __ogni oggetto tiene traccia del costruttore che lo ha creato__ nella sua proprietà **_constructor_**






## Prototipi di oggetti
Nel modello a prototipi, ogni oggetto è associato a un "oggetto padre", detto prototipo, di cui __eredita le proprietà__
- un prototipo è quindi innanzitutto un normale oggetto
- tale prototipo è referenziato internamente dagli oggetti figli, tramite una proprietà nascosta, chiamata **\_\_proto\_\_**

Poiché tale prototipo è a sua volta un oggetto, anch'esso avrà a sua volta un prototipo "padre"
- nasce una **gerarchia di ereditarietà prototipale**

Ogni oggetto possiede quindi sia proprietà proprie, sia tutte quelle ereditate dal padre e da tutti i suoi antenati
- si ha ereditarietà tra oggetti e non tra classi. Qua è tutto più fluido, non ho la (conveniente) rigidità delle classi

**NB**: Gli oggetti costruiti dallo **stesso costruttore condividono lo stesso prototipo**, assegnato loro dal costruttore
- sono i costruttori a creare (implicitamente) il campo \_\_proto\_\_ degli oggetti che creano che popolono con i relativi prototipi 

Anche **gli object literal condividono lo stesso prototipo**, assegnato loro di default dal sistema dato che non hanno un costruttore
- i literal appartengono quindi a un’unica grande «categoria», non ulteriormente specificata

### Ereditarietà prototipale    
Tutti i tipi di dati (predefiniti e non) in JavaScript hanno il loro prototipo «progenitore»
- gli object literal puntano al **prototipo capostipite** della gerarchia
- tutti i numeri fanno riferimento al prototipo dei numeri
- tutte le funzioni fanno riferimento al prototipo delle funzioni
- tutti gli array fanno riferimento al prototipo degli array
- … e così via

**Ma come sono fatti questi prototipi?**
- il prototipo dei numeri è esso stesso un numero
- il prototipo delle funzioni è esso stesso una funzione
- il prototipo degli array è esso stesso un array
- … e così via

In pratica, ogni prototipo progenitore è il **primo oggetto del suo tipo** (vuoto per i tipi definiti dall'utente, le proprietà del definite nel costruttore sono assegnate all'oggetto).

**OSS**: le categorie di oggetti definite in questo modo sono **uniformi** 
- non esistono tipi primitivi
- i tipi definiti dall'utente sono indistinguibili da quelli built-in (sintomo di una bella idea eseguita altrettanto bene)

Abbiamo poi che i prototipi delle singole categorie hanno, per costruzione, un antenato comune, che coincide anche con il prototipo
di tutti i literal: è **il Prototipo capostipite**
- ogni prototipo (normale oggetto che ha quindi un suo prototipo) ha il prototipo capostipite come prototipo
    - quest'ultimo è l'unico oggetto senza un prototipo, campo \_\_proto\_\_ null
    - in pratica, «circa» l’equivalente di Object in un mondo a classi
- esso è antenato diretto (padre) di ogni object literal e antenato indiretto di ogni altro oggetto

**OSS_2**: il costruttore dei literal, **Object**, non è l'oggetto capostipite, è solo una funzione come lo sono le altre (infatti ha come prototipo il prototipo delle funzioni).




### Come controllare le catene di eredità prototipale?
Dato che i prototipi forniscono il fondamento su cui si basano le proprietà degli oggetti creati, **il campo \_\_proto\_\_ non andrebbe toccato direttamente, infatti è spesso hidden o non modificabile**.

Poiché è il costruttore che definisce la categoria di ogni oggetto, **ogni costruttore ha una proprietà _prototype_ che punta all’oggetto prototipo della «sua» categoria**:
- in pratica, prototype punta all’oggetto prototipo che il costruttore «affibbierà» agli oggetti che costruirà
    - un costruttore popola i campi \_\_proto\_\_ degli oggetti creati
- quindi, per definizione, se obj è un oggetto di categoria C, vale sempre l’identità obj.__proto__ == C.prototype
- per i nostri Point: **p.\_\_proto\_\_ == Point.prototype**

è tramite il campo *prototype* dei costruttori che si referenziano i prototipi «progenitori» delle varie categorie, che per questa ragione non hanno un nome «proprio» diretto (si distinguono in base al costruttore).
- in questo modo si ha un unico entrypoint piuttosto che quello multiplo fornito dai campi \_\_proto\_\_ dei vari oggetti (ricoda che ognuno ne ha uno)
- in pratica, per ogni oggetto obj: **obj.constructor.prototype == obj.\_\_proto\_\_**






## Differenze eredità prototipale in JS ed eredità classica
Con le catene di prototipi, JavaScript mantiene informazioni «analoghe» a quelle che, nei linguaggi con classi, vengono
mappate sulle relazioni fra oggetti e classi

Ma c’è una cruciale differenza:
- nei linguaggi con classi, tali relazioni sono gestite dal compilatore (prima) e dal runtime (poi) in modo a noi trasparente e inaccessibile
- in JavaScript, invece, tali relazioni sono esplicite, visibili e reificate a run-time tramite proprietà **pubbliche e modificabili**
- Tale reificazione rende possibile alterare dinamicamente, a run-time, le relazioni fra oggetti!
- ogni modifica è immediatamente reificata «live» nel sistema

**THIS IS CRAZY**: In javascript è possibile modificare **A RUNTIME** i prototipi e quindi modificare che cosa viene ereditato
- infatti i prototipi sono normali oggetti e come sappiamo le proprietà di quest'ultimi, in js, possono venire modificate a runtime 

### Type augmenting e sostituzione dei prototipi
In particolare è possibile fare due cose, con diverse conseguenze:
- aggiungere/togliere proprietà a un prototipo in uso
- sostituire un oggetto-prototipo con un altro diverso

**La possibilità di aggiungere/togliere proprietà a un prototipo già in uso si chiama Type Aumenting**
- ha **effetto immediato** su tutti gli oggetti che usano quel prototipo
- è un **effetto «retroattivo»**, che coinvolge oggetti già esistenti,
- MA non altera le relazioni di ereditarietà padre-figlio

La possibilità di sostituire un oggetto-prototipo con un altro diverso **riguarda invece il futuro**
- **non ha effetto retroattivo**, perché non tocca gli oggetti già creati (mantengono lo stesso prototipo nel loro campo \_\_proto__)
- MA **modifica le catene di ereditarietà «padre-figlio» future**
- L'effetto è cambiare le regole di costruzione da qui in poi, alterando le relazioni di ereditarietà prototipale per i futuri oggetti
- ciò crea una **discontinuità**: gli oggetti creati «prima» sono diversi, non più «imparentati», con quelli che saranno creati «dopo», **pur dallo stesso costruttore!**  

L'approccio precedente consente di definire **ereditarietà, che però è fra oggetti, non fra classi** come nei linguaggi classici.

Ciò comporta interessanti differenze:
- nei linguaggi con classi, la relazione di ereditarietà fra classi si riflette in un’analoga relazione fra le loro istanze, che vale per tutte le istanze di tali classi e non è (ovviamente) revocabile
- in un approccio a prototipi, invece, **la relazione di ereditarietà può riguardare anche solo singole coppie di oggetti**, senza riflettersi necessariamente su altri oggetti «simili» (vedi modifico il campo \_\_proto\_\_ di un singolo oggetto)
- per simulare gli effetti dell’ereditarietà fra classi occorre agire su quei particolari oggetti che sono i costruttori, «programmandoli» affinché impostino le «giuste» catene di prototipi negli oggetti che costruiranno.






**Conclusione**: il messaggio di javascript è che non ti serve necessariamente il concetto di classe! Ti basta l'ereditarietà tra oggetti.
