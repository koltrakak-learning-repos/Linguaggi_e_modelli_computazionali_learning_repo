package visitor;

import exp.*;

public interface ExpSeqVisitor extends ExpAssignVisitor {
    public abstract void visit( SeqExp e );
}
