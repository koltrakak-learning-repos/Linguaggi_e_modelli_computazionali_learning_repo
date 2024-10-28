package exp;

import visitor.ExpVisitor;

public class MulExp extends OpExp {
    public MulExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return "*";
    }

    public void accept( ExpVisitor v) {
        v.visit(this);
    }
}