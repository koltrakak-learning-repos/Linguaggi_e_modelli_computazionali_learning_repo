package exp;

public class NumExp extends Exp {
    int val;
    
    public NumExp(int v) {
        val = v;
    }
    
    public String toString() {
        return "" + val;
    }
    
    public int getValue() {
        return val;
    }
}