package exp;

import visitor.*;

public class SeqExp extends OpExp {
    public SeqExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return ",";
    }

    public void accept( ExpVisitor v) {
        ((ExpSeqVisitor)v).visit(this);
    }
}