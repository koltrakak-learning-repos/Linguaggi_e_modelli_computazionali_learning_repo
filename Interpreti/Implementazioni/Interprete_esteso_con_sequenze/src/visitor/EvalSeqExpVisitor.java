package visitor;

import exp.*;

public class EvalSeqExpVisitor extends EvalExpAssignVisitor implements ExpSeqVisitor {
    public void visit( SeqExp e ) {
        // figlio sx → va solo valutato per causare i side effect
        e.left().accept(this); 
        // figlio dx → va valutato e anche restituito
        e.right().accept(this); 
        value = getEvaluation();  
    }
}
