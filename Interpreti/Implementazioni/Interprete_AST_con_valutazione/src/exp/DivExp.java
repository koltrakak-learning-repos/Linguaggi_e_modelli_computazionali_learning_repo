package exp;

import java.io.PrintWriter;

public class DivExp extends OpExp {
    public DivExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return "/";
    }

    @Override
    public int eval() {
        return left.eval() / right.eval();
    }

    @Override
    public void emit(PrintWriter p) {
        left.emit(p);
        right.emit(p);
        p.println("DIV");
    }
}