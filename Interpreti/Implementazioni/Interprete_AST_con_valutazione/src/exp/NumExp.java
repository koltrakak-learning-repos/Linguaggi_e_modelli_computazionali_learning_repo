package exp;

import java.io.PrintWriter;

public class NumExp extends Exp {
    int val;
    
    public NumExp(int v) {
        val = v;
    }
    
    public String toString() {
        return "" + val;
    }

    @Override
    public int eval() {
        return val;
    }

    @Override
    public void emit(PrintWriter p) {
        p.println("PUSH " + this.val);
    }
}