package exp;

import java.io.PrintWriter;

public class PowExp extends OpExp {
    public PowExp(Exp l, Exp r) {
        super(l,r);
    }
    
    public String myOp() {
        return "^";
    }

    @Override
    public int eval() {
        int base = left.eval();
        int esponente = right.eval();
        int tmp = base;

        for (int i = esponente; i > 1; i--) {
            tmp = tmp * base;
        }

        return tmp;
    }

    @Override
    public void emit(PrintWriter p) {
        left.emit(p);
        right.emit(p);
        p.println("POW");
    }
}