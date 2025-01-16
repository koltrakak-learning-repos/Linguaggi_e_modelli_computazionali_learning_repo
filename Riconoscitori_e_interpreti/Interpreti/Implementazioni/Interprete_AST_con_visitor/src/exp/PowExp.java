package exp;

import visitor.ExpVisitor;

public class PowExp extends OpExp {
    public PowExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return "^";
    }

    public void accept( ExpVisitor v) {
        v.visit(this);
    }
}