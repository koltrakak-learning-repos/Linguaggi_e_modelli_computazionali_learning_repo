package exp;

public class PowExp extends OpExp {
    public PowExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return "^";
    }
}