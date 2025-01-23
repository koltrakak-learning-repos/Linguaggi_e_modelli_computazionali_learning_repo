Di fatto, **con LR(0) i conflitti emergono quasi sempre**:
- è un caso troppo semplice, importante didatticamente per comprendere il meccanismo dei contesti, ma inadatto a usi reali
- funziona solo con linguaggi giocattolo 

L'analisi LR(k) opera analogamente all'analisi LR(0), ma **guarda avanti di k symboli** (lookahead symbols)
- tutte le riduzioni sono ritardate di k simboli
- risolvo i conflitti shift-reduce (posso sapere che strada prendere guardando k simboli in avanti )
- Purtroppo, la complessità di questa tecnica fa sì che **LR(1) sia spesso ingestibile** e **richieda semplificazioni**
    - vedi SLR e LALR
- LR(2) o successivi non sono, di fatto, neppure pensabili


procedimento LR(1)

... crazy

**OSS**: come nell'analisi LL(1), situazioni in cui anche con l'analisi LR(1) ci sono dei problemi sono sintomo più che altro di un linguaggio non pensato bene.
- ripensare al linguaggio e non passare ad LR(2) che c'è da spararsi

    