package exp;

public class DivExp extends OpExp {
    public DivExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return "/";
    }
}