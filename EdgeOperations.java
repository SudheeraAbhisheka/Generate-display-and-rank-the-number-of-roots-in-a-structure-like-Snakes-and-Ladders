import java.util.*;
public class EdgeOperations{
    private DSABinarySearchTree bst;
    public EdgeOperations(DSABinarySearchTree bstIn){
        bst = bstIn;
    }
    private String findIndex(String label1, String label2){
        String key = null;
        int indx;
        int count = 0;
        int i = 0;
        String[][] theArray;
        String[] tempArray;
        DSAQueue queueTwo;
        DSAQueue queueOne;
        Iterator iterOne;
        Iterator iterTwo;
        
        bst.display();
        queueOne = bst.getQueueOne();
        queueTwo = bst.getQueueTwo();

        for (Object o : queueOne){
            count++;
        }

        theArray = new String[count][4];

        iterOne = queueOne.iterator();
        iterTwo = queueTwo.iterator();

        while(iterOne.hasNext()){
            tempArray = (String[])iterTwo.next();
            theArray[i][0] = (String)iterOne.next(); //key
            theArray[i][1] = tempArray[0]; //label1
            theArray[i][2] = tempArray[1]; //label2
            theArray[i][3] = tempArray[2]; //value
            i++;
        }

        indx = indexFunction(theArray, label1, label2);
        //System.out.println("indx " + indx);
        if(indx != -1){
            key = theArray[indx][0];
        }

        return key;
    }

    private int indexFunction(String[][] theArray, String label1, String label2){
        int indx = -1;
        DSAQueue id;

        id = new DSAQueue();
        for(int j = 0; j < theArray.length; j++){
            if(label1.equals(theArray[j][1])){
                id.enqueue(j);
            }
        }

        for(Object o : id){
            if(label2.equals(theArray[(int)o][2])){
                indx = (int)o;
            }    
        }

        return indx;
    }

    public String findEdge(String label1, String label2){
        String found = null;
        String key;
        String[] theArray;

        key = findIndex(label1, label2);
        if(key == null){
            System.out.println("Can't find the edge");
        }
        else{
            theArray = (String[])bst.find(key);
            found = theArray[2];
        }

        return found;
    }

    public int insertEdge(String label1, String label2, String edgeValue, int trackKey){
        String[] insertArray;
        String key;

        insertArray = new String[3];
        insertArray[0] = label1;
        insertArray[1] = label2;
        insertArray[2] = edgeValue;

        key = Integer.toString(trackKey + 1);

        bst.insert(key, insertArray);

        return (trackKey + 1);
    }

    public void deleteEdge(String label1, String label2){
        String key;

        key = findIndex(label1, label2);
        if(key == null){
            System.out.println("Can't find the edge");
        }
        else{
            bst.delete(key);
        }
    }

    public int updateEdge(String label1, String label2, String edgeValue, int trackKey){
        int updatedTrackKey;
        
        deleteEdge(label1, label2);
        updatedTrackKey = insertEdge(label1, label2, edgeValue, trackKey);

        return updatedTrackKey;
    }
}