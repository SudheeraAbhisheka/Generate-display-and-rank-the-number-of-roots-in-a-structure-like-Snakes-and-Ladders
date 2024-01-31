import java.util.*;
public class DSAGraphEdge{
    String value;
    DSAGraphVertex from = null, to = null;
    public DSAGraphEdge(DSAGraphVertex formVertex, DSAGraphVertex toVertex, String inValue){
        from = formVertex;
        to = toVertex;
        value = inValue;
    }
    public String getValue(){
        return value;
    }
    public DSAGraphVertex getFrom(){
        return from;
    }
    public DSAGraphVertex getTo(){
        return to;
    }
    /*public boolean isDirected(){
        boolean bool = false;
        return bool;
    }
    public String toString(){
        return
    }*/




}