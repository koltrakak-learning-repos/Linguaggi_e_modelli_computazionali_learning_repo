package visitor;

import exp.*;

public interface ExpVisitor {
    void visit( PlusExp e );
    void visit( MinusExp e );
    void visit( MulExp e );
    void visit( DivExp e );
    void visit( NumExp e );
    void visit( PowExp e );
}
