package exp;

import java.io.PrintWriter;

public abstract class Exp {
    abstract public int eval();
    abstract public void emit(PrintWriter p);
}
