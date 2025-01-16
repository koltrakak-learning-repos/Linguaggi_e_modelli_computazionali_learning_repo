package exp;

public class PlusExp extends OpExp {
    public PlusExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return "+";
    }
}
