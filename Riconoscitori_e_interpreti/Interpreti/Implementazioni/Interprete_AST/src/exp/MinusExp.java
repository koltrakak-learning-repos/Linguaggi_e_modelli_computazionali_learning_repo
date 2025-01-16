package exp;

public class MinusExp extends OpExp {
    public MinusExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return "-";
    }
}