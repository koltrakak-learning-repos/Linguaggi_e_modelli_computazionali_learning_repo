package exp;

public class MulExp extends OpExp {
    public MulExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return "*";
    }
}