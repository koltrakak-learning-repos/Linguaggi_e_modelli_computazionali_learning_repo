package visitor;

import exp.*;
import java.io.PrintWriter;
import java.io.File;


public class BytecodeVisitor implements ExpVisitor {
    private PrintWriter p;

    public BytecodeVisitor(String path) throws Exception{
        this.p = new PrintWriter(new File(path));
    }
    
    public void visit(PlusExp e) {
        e.left().accept(this);
        e.right().accept(this);
        this.p.println("ADD");
    }

    public void visit(MinusExp e) {
        e.left().accept(this);
        e.right().accept(this);
        this.p.println("SUB");
    }

    public void visit(MulExp e) {
        e.left().accept(this);
        e.right().accept(this);
        this.p.println("MUL");
    }

    public void visit(DivExp e) {
        e.left().accept(this);
        e.right().accept(this);
        this.p.println("DIV");
    }

    public void visit(PowExp e) {
        e.left().accept(this);
        e.right().accept(this);
        this.p.println("POW");
    }

    public void visit(NumExp e) {
        this.p.println("PUSH " + e.getValue());
    }    
}