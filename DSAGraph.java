import java.util.*;
public class DSAGraph{
    private DSALinkedList vertices;
    private int vertexCount = 0, edgeCount = 0;
    private DSAStack stack;
    private DSAQueue queue;
    private DSALinkedList adjacencyList;
    private Iterator iter;
    private DSAGraphVertex retValue;
    private DSAGraphVertex w;
    private boolean stop;
    private int countStack;
    private DSAStack newStack;
    private DSALinkedList edges;
    private DSAGraphEdge graphEdge;
    private String target;
    private String[] targetArray;
    private DSAGraphVertex targetVartex;
    private String start;
    private int overFlowCount;

    public DSAGraph(String st, String[] tar){
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
        targetArray = tar;
        start = st;
    }
    public void addVertex(String label, Object value){
        if(!hasVertex(label)){
            vertices.insertLast(new DSAGraphVertex(label, value));
            vertexCount++;
        }
    }
    public void addEdge(String label1, String label2, String edgeValue){
        Object obj = null;
        
        if(hasVertex(label1) && hasVertex(label2)){
            if(!isAdjacent(label1, label2)){
                getVertex(label1).addEdge( getVertex(label2) );
                graphEdge = new DSAGraphEdge(getVertex(label1), getVertex(label2), edgeValue);
                edges.insertLast(graphEdge);

                edgeCount++;
            }
        }
    }
    public boolean hasVertex(String label){
        boolean bool = true;
        if( getVertex(label) == null )
            bool = false;

        return bool;
    }
    public int getVertexCount(){
        return vertexCount;
    }   
    public int getEdgeCount(){
        return edgeCount;
    }
    public DSAGraphVertex getVertex(String label){
        DSAGraphVertex node = null;
        DSAGraphVertex c = null;
        Iterator iter = vertices.iterator();

        if(iter.hasNext())
        {
            c = (DSAGraphVertex)iter.next();
            if( (c.getLabel()).equals(label) )
                node = c;
        }
        while( iter.hasNext() && !(c.getLabel()).equals(label) ){
            c = (DSAGraphVertex)iter.next();
            if( (c.getLabel()).equals(label) )
                node = c;
        }
        
        return node;
    }
    public DSALinkedList getAdjacent(String label){
        return getVertex(label).getAdjacent();
    }
    public boolean isAdjacent(String label1, String label2){
        boolean bool = false;

        DSAGraphVertex c;
        for (Object o : getAdjacent(label1)){
            c = (DSAGraphVertex)o;
            if( (c.getLabel()).equals(label2) )
                bool = true;
        }

        return bool;
    }

    public DSAGraphEdge getEdge(String lbFrom, String lbTo){
        DSAGraphEdge edge = null;
        DSALinkedList edgeList = null;
        DSAGraphEdge c = null;

        edgeList = getAdjacentE(lbFrom);
        Iterator iter = edgeList.iterator();

        if(iter.hasNext())
        {
            c = (DSAGraphEdge)iter.next();
            if( (c.getTo().getLabel()).equals(lbTo) )
                edge = c;
        }
        while( iter.hasNext() && !(c.getTo().getLabel()).equals(lbTo) ){
            c = (DSAGraphEdge)iter.next();
            if( (c.getTo().getLabel()).equals(lbTo) )
                edge = c;
        }

        return edge;
    }
    private DSALinkedList getAdjacentE(String label){
        DSALinkedList adjacentE = new DSALinkedList();
        DSAGraphEdge c;
        for (Object o : edges){
            c = (DSAGraphEdge)o;
            if( (c.getFrom().getLabel()).equals(label) )
                adjacentE.insertLast(c);
        }
        return adjacentE;
    }
    public void displayAsList(){
        DSAGraphVertex c;
        DSAGraphVertex d;
        for (Object o : vertices){
            c = (DSAGraphVertex)o;
            System.out.print(c.getLabel() + "  ");

            for (Object o2 : getAdjacent(c.getLabel())){
                d = (DSAGraphVertex)o2;
                System.out.print(d.getLabel() + " ");
            }
            System.out.println();
        }
    }
    public String[][] displayAsMatrix(){
        int i;
        String[][] matrixArray = new String[vertexCount + 1][vertexCount + 1];
        String[] theArray = new String[vertexCount + 1];
        DSAGraphEdge edgeDisplay;

        // Filling matrix titles
        i = 1;
        DSAGraphVertex c;
        for (Object o : vertices){
            c = (DSAGraphVertex)o;
            matrixArray[0][i] = c.getLabel();
            matrixArray[i][0] = c.getLabel();
            theArray[i] = c.getLabel();
            i++;
        }

        // Filling the 2d array with 0 and 1
        matrixArray[0][0] = "#";
        for(int l = 1; l < (vertexCount + 1); l++){
            for(int k = 1; k < (vertexCount + 1); k++){
                if( isAdjacent(theArray[l], theArray[k]) ){
                    edgeDisplay = getEdge(theArray[l], theArray[k]);
                    matrixArray[l][k] = edgeDisplay.getValue();
                }
                else
                    matrixArray[l][k] = "0";
            }
        }

        // Printing 2d array
        for(int l = 0; l < matrixArray.length; l++){
            for(int k = 0; k < matrixArray.length; k++){
                System.out.print(String.format("%3s", matrixArray[l][k]));
            }
            System.out.println();
        }

        return matrixArray;
    }
    public void printVertices(){
        DSAGraphVertex c;
        for (Object o : vertices){
            c = (DSAGraphVertex)o;
            System.out.println(c.getLabel() + " " + c.getValue());
        }
    }
    public void iterateOverList(DSAStack iterStack){
        DSAGraphVertex c;
        for (Object o : iterStack){
            c = (DSAGraphVertex)o;
            System.out.println(c.getLabel());
        }
    }
    public void stackCount(){
        int stackC = 0;
        DSAGraphVertex c;
        for (Object o : stack){
            //c = (DSAGraphVertex)o;
            stackC++;
        }
        //System.out.println(stackC);
    }

    public void advanceSearch(){
        DSAGraphVertex startPoint;

        stack = new DSAStack();
        
        
        for(int i = 0; i < targetArray.length; i++){
            target = targetArray[i];

            startPoint = getVertex(start);
            adjacencyList = getAdjacent(start);
            iter = adjacencyList.iterator();
            
            newStack = new DSAStack();
            newStack.push(startPoint);

            targetVartex = getVertex(target);
            stop = false;
            while(!stop){
                overFlowCount = 0;
                advS();
            }
        }
    }

    private void advS(){
        w = null;
        DSAGraphVertex tempOne, tempTwo, v;
        String lbTwo, lbOne;
        DSAStack tempStack;

        //System.out.println(overFlowCount);
        //System.out.print("/");
        //System.out.print("~");
        
        overFlowCount++;
        if(iter.hasNext()){
            w = (DSAGraphVertex)iter.next();
            if(target.equals(w.getLabel())){
                tempStack = copyStack();
                stack.push(tempStack);
                countStack++;
            }
            else if(similerValues(w)){
                advS();
            }
            else{
                newStack.push(w);
                adjacencyList = getAdjacent(w.getLabel());
                iter = adjacencyList.iterator();
                advS();
            }
        }
        else{
            if(newStack.isEmpty()){

            }
            else{
                tempOne = (DSAGraphVertex)newStack.pop();
                if(newStack.isEmpty()){
                    stop = true;
                }
                else{
                    tempTwo = (DSAGraphVertex)newStack.pop();
                    lbOne = tempOne.getLabel();
                    lbTwo = tempTwo.getLabel();
                    newStack.push(tempTwo);

                    adjacencyList = getAdjacent(lbTwo);
                    iter = adjacencyList.iterator();
                    v = (DSAGraphVertex)iter.next();
                    while(!v.getLabel().equals(lbOne)){
                        v = (DSAGraphVertex)iter.next();
                    }
                    advS();
                }
            } 
        }
    }

    private DSAStack copyStack(){
        DSAStack nextStack;
        DSAGraphVertex c;
        
        nextStack = new DSAStack();
        nextStack.push(targetVartex);

        for (Object o : newStack){
            c = (DSAGraphVertex)o;
            nextStack.push(c);
        }
        
        return nextStack;
    }
    
    private boolean similerValues(DSAGraphVertex w){
        DSAGraphVertex c;
        boolean find;
        String wLabel;

        wLabel = w.getLabel();
        find = false;
        for (Object o : newStack){
            c = (DSAGraphVertex)o;
            if(wLabel.equals(c.getLabel())){
                find = true;
            }
        }

        return find;
    }

    public DSAStack getStack(){
        return stack;
    }

    public int getStackCount(){
        return countStack;
    }
}