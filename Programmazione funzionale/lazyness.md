La call-by-name è l’essenza del «pensare in modo lazy»

    Lazyness = computare solo al bisogno, non a priori

Vantaggi:
- **si evita lavoro inutile**

- sintesi e naturalezza nell’esprimere composizione di algoritmi (GUARDATI IL PACCHETTO DI SLIDE SUGLI STREAM DI FONDAMENTI 2)
    - Separazione tra definizione e valutazione: con la lazyness, possiamo definire strutture dati o operazioni senza preoccuparci immediatamente della loro valutazione. Questo permette di comporre operazioni come se stessimo descrivendo __cosa fare, anziché come farlo__.
    - Composizione modulare: poiché i dati vengono valutati solo al bisogno, possiamo costruire pipeline e catene di trasformazioni senza caricarci del lavoro di __esecuzione intermedia__. Ogni step "descrive" un'operazione, e il sistema si occupa di eseguire il tutto solo quando e dove è necessario.

- possibilità di gestire flussi/collezioni di dati «idealmente» infiniti

Quando la «pigrizia» conta:
- in fase di valutazione di funzioni (**call by name**)
- in fase di **generazione e gestione di insiemi «infiniti» di dati**
    - bello l'esempio di filtraggio delle linee di errore da un file di errore molto grande (evita il caricamento dell'intero file in memoria)
- in fase di inizializzazione/creazione di oggetti, si fa __solo al bisogno__
    - spesso tramite factory, che tipicamente memorizzano anche l’istanza creata (facendo caching)


### Lazyness nelle collezioni infinite
...

Gli stream in java sono un idea tipicamente funzionale, ed implementa l'idea di lazyness; gestiscono collezioni in teoria infinite senza problemi dato che fanno solo il lavoro necessario per rispondere alle richieste dell'__ultima operazione nello stream__.

### Lazy initialization
Per lazy initialization s’intende la possibilità di ritardare l’effettiva inizializzazione di una variabile al suo primo uso

**Motivo**: efficienza, evitare lavoro inutile (spec. se pesante; pensa a delle richieste di rete tipo query in un DB)
- **OSS**: Potrebbe essere interessante mostrare al prof all'esame l'esempio del caricamento da un DB di un utente senza tutta la lista dei suoi ordini (e senza tutti join degli ordini)

... esempi nei vari linguaggi ...

Occhio: dietro alla keyword lazy inizialization c’è una complessità che ha il suo costo!
- Internamente viene generata una classe che contiene lo stato, che viene computato la prima volta che si accede
- MA per essere thread-safe l’accesso è sincronizzato (__sezione critica__). è questo che potrebbe causare un rallentamento

**Può sempre essere simulata tramite opportune lambda**.

### Contesto interessante in cui si può applicare lazyness | allocazione di memoria
The combination of page tables and page faults opens up a wide range of interesting possibilities in addition to COW fork. Another widely-used feature is called **lazy allocation**, which has two parts.

- First, when an application asks for more memory by calling sbrk, the kernel notes the increase in size, but does not allocate physical memory and does not create PTEs for the new range of virtual addresses.

- Second, on a page fault on one of those new addresses, the kernel allocates a page of physical memory and maps it into the page table. Like COW fork, the kernel can implement lazy allocation transparently to applications.

Since applications often ask for more memory than they need, lazy allocation is a win: the kernel doesn’t have to do any work at all for pages that the application never uses. Furthermore, if the application is asking to grow the address space by a lot, then sbrk without lazy allocation is expensive: if an application asks for a gigabyte of memory, the kernel has to allocate and zero 262,144 4kB pages. Lazy allocation allows this cost to be **spread over time**. 
