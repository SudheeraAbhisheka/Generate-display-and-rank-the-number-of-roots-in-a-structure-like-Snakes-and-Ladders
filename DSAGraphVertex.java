import java.util.*;
public class DSAGraphVertex{
    private String lable;
    private Object value;
    private DSALinkedList links;
    private boolean visited;

    public DSAGraphVertex(String inLabel, Object inValue){
        lable = inLabel;
        value = inValue;
        links = new DSALinkedList();
    }
    public String getLabel(){
        return lable;
    }
    public Object getValue(){
        return value;
    }
    public DSALinkedList getAdjacent (){
        return links;
    }
    public void addEdge(DSAGraphVertex vertex){
        links.insertLast(vertex);
    }
    public void setVisited(){
        visited = true;
    }
    public void clearVisited(){
        visited = false;
    }
    public boolean getVisited(){
        return visited;
    }
    /*public String toString(){

    }*/
}