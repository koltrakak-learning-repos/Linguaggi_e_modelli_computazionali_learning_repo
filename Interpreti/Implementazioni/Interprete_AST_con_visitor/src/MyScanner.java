class MyScanner extends java.util.StringTokenizer {
    public MyScanner(String txt){
        super(txt);
    }

    public Token getNextToken(){
        try{
            return new Token(nextToken().trim());
        }
        catch (java.util.NoSuchElementException e){
            return null;
        }
    }
 }