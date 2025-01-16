package exp;

public abstract class OpExp extends Exp {
    // i due operandi
    Exp left, right; 

    protected OpExp(Exp l, Exp r){
        left=l;
        right=r;
    }

    abstract String myOp();
    
    public String toString(){
        return left.toString() + myOp() + right.toString();
    }
}