package visitor;

import java.util.HashMap;
import java.util.Map;

import exp.*;

public class EvalExpAssignVisitor extends EvalExpVisitor implements ExpAssignVisitor {
    Map<String, Integer> environment = new HashMap<>();

    public String getEnv() {
        return "Environment: " + environment;
    }

    public void visit(AssignExp e) {
        // a sinistra c’è una LIdentExp
        e.left().accept(this); 
        // a destra c’è una Exp qualsiasi che però denota sempre un valore intero da mettere nell'environment
        String id = ((LIdentExp)e.left()).getName();
        e.right().accept(this);
        
        // recuperiamo il valore ottenuto dalla valutazione del visitor e a aggiorniamo l'environment
        value = getEvaluation(); 
        environment.put(id, (Integer)value); 
    }

    public void visit(LIdentExp e) {
        // valutare LIndentExp = restituire la chiave (il nome)
        value = e.getName();    //non mi sembra che serva a qualcosa
    }     
    
    public void visit(RIdentExp e) {
        // valutare RIndentValExp = restituire il valore corrisp.
        String id = e.getName();
        Integer val = environment.get(id); // recuperiamo il valore

        if (val != null)
            value = val; 
        else 
            throw new RuntimeException("definizione dell'identificatore mancante");
    }
}