package visitor;

import exp.*;

public interface ExpAssignVisitor extends ExpVisitor {
    public abstract void visit(AssignExp e);
    public abstract void visit(LIdentExp e);
    public abstract void visit(RIdentExp e);
}