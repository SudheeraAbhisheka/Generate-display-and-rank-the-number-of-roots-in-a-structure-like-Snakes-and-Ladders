import java.util.*;
public class UnitTestEcodeOperations{
    private static DSAGraph graph;
    private static DSAStack stack;
    private static int units;
    private static Object[] heapEntry;
    private static int[] heapPriority;

    private static DSABinarySearchTree nodeTree;
    private static DSABinarySearchTree edgeTree;
    private static DSABinarySearchTree ncodeTree;
    private static DSABinarySearchTree ecodeTree;
    private static String start;
    private static String[] target;
    private static int ncodeCount;
    private static int ecodeCount;
    private static int nodeCount;
    private static int edgeCount;
    private static int trackKey;


    public static void main(String[] args){
            DSAQueue queue;
            DSALinkedList adjacentList;
            DSAGraphVertex object;
            DSAHeap heap;
            int stackCount;
            String fileName;
            String found;
            int intFound;
            NodeOperations nodeOp;
            EdgeOperations edgeOp;
            NcodeEcodeOperations ncodeOp;
            NcodeEcodeOperations ecodeOp;
            WritingToAFile newFile;
            DSAStack rankStack;
            String tempOne;
            String tempTwo;
            String tempThree;
            int tempInt;
            DSAStack newStack;
            DSAGraphVertex w;

            fileName = "gameofcatz.txt";

            importData(fileName);

            parsingToGraph();   

            System.out.println("Ecode operations");
            ecodeOp = new NcodeEcodeOperations(ecodeTree);
            tempOne = "-";
            tempInt = ecodeOp.findCode(tempOne);
            System.out.println("Ecode " + tempOne + " value is " + tempInt);

            tempOne = "-";
            tempInt = 4;
            ecodeOp.updateCode(tempOne, tempInt);
            System.out.println("Update ecode " + tempOne  + " with value " + tempInt);
            System.out.println(" ");

            parsingToGraph();
            graph.advanceSearch();
            System.out.println(" ");
            stack = graph.getStack();
            stackCount = graph.getStackCount();
            heap = new DSAHeap(stackCount);
            for (Object o : stack){
                units = 0;
                edgeOperations((DSAStack)o);
                nodeOperatons((DSAStack)o);
                heap.add(units, o);
            }
    
            heapFunction(stackCount, heap);

            tempOne = "-";
            tempInt = 2;
            ecodeOp.updateCode(tempOne, tempInt);
            System.out.println("Update ecode " + tempOne  + " with value " + tempInt);

            parsingToGraph();
            graph.advanceSearch();
            System.out.println(" ");
            stack = graph.getStack();
            stackCount = graph.getStackCount();
            heap = new DSAHeap(stackCount);
            for (Object o : stack){
                units = 0;
                edgeOperations((DSAStack)o);
                nodeOperatons((DSAStack)o);
                heap.add(units, o);
            }
    
            heapFunction(stackCount, heap);
        System.exit(0);
    }

    private static void importData(String fileName){ 
        ParsingData data;
        String[] tempArray;

        data = new ParsingData(fileName);

        nodeCount = data.getNodeCount();

        start = data.getStart();

        target = new String[data.getTargetCount()];
        for(int i = 0; i < nodeCount; i++){
            if(data.getTarget()[i] != null){
                target[i] = data.getTarget()[i];
            }
        }

        nodeTree = new DSABinarySearchTree();
        for(int i = 0; i < nodeCount; i++){
            nodeTree.insert(data.getNodeArray()[i], data.getTypesArray()[i]);
        }
  
        edgeCount = data.getEdgeCount();
        edgeTree = new DSABinarySearchTree();
        for(int i = 0; i < edgeCount; i++){
            tempArray = new String[3];
            tempArray[0] = data.getEdgeArrayFirst()[i];
            tempArray[1] = data.getEdgeArraySecond()[i];
            tempArray[2] = data.getEdgeType()[i];

            edgeTree.insert(String.valueOf(i), tempArray);
            //System.out.println(String.valueOf(i));
            trackKey = i;
        }

        ncodeCount = data.getNcodeCount();
        ncodeTree = new DSABinarySearchTree();

        for(int i = 0; i < ncodeCount; i++){
            ncodeTree.insert(data.getNcode()[i], data.getNcodeValue()[i]);
            //System.out.println("ncode value " + ncodeValue[i]);
        }

        ecodeCount = data.getEcodeCount();
        ecodeTree = new DSABinarySearchTree();

        for(int i = 0; i < ecodeCount; i++){
            ecodeTree.insert(data.getEcode()[i], data.getEcodeValue()[i]);
            //System.out.println("ecode value " + ecodeValue[i]);
        }
    }

    private static void parsingToGraph(){
        Iterator iterOne, iterTwo, iterThree;
        String c, d, e;
        DSAQueue queueOne, queueTwo;
        DSAQueue queueTwoTwo;
        String[] tempArray;

        graph = new DSAGraph(start, target);

        nodeTree.display();
        queueOne = nodeTree.getQueueOne();
        queueTwo = nodeTree.getQueueTwo();
        
        iterOne = queueOne.iterator();
        iterTwo = queueTwo.iterator();

        while(iterOne.hasNext()){
            c = (String)iterOne.next();
            d = (String)iterTwo.next();
            graph.addVertex(c, d);
            //System.out.println("node - " + c + " " + d);
        }
        
        queueTwoTwo = new DSAQueue();
        edgeTree.display();
        queueTwoTwo = edgeTree.getQueueTwo();

        for (Object o : queueTwoTwo){
            tempArray = (String[])o;
            //System.out.println("edge - " + tempArray[0] + " " + tempArray[1] + " " + tempArray[2]);
            graph.addEdge(tempArray[0], tempArray[1], tempArray[2]);
        }
    }

    private static void nodeOperatons(DSAStack newStack){
        DSAGraphVertex w = null;
        String nodeType;
        int i;
        DSAQueue ncodeQueue;
        DSAQueue ncodeType;
        String ncodeString;
        int ncodeTypeString;
        Iterator iterOne;
        Iterator iterTwo;
        boolean found;

        for (Object o : newStack){
            found = false;
            w = (DSAGraphVertex)o;
            nodeType = (String)w.getValue();

            ncodeTree.display();
            ncodeQueue = ncodeTree.getQueueOne();
            ncodeType = ncodeTree.getQueueTwo();
            iterOne = ncodeQueue.iterator();
            iterTwo = ncodeType.iterator();
            while(iterOne.hasNext()){
                ncodeString = (String)iterOne.next();
                ncodeTypeString = (int)iterTwo.next();
                if(nodeType.equals(ncodeString)){
                    found = true;
                    units += ncodeTypeString;
                }
            }
            if(!found){
                throw new IllegalArgumentException("error");
            }
        }
        
    }

    private static void edgeOperations(DSAStack newStack){
        DSAGraphVertex fromValue = null;
        DSAGraphVertex toValue = null;
        DSAGraphEdge calc;
        DSAGraphVertex d;
        String edgeType;
        DSAQueue queue;
        int i;
        DSAQueue ecodeQueue;
        DSAQueue ecodeType;
        String ecodeString;
        int ecodeTypeString;
        Iterator iterOne;
        Iterator iterTwo;

        queue = new DSAQueue();
        for (Object p : newStack){
            d = (DSAGraphVertex)p;
            queue.enqueue(d);
            //System.out.print(d.getLabel() + " ");
        }

        if(!queue.isEmpty()){
            fromValue = (DSAGraphVertex)queue.dequeue();
            if(!queue.isEmpty()){
                toValue = (DSAGraphVertex)queue.dequeue();
                calc = graph.getEdge(fromValue.getLabel(), toValue.getLabel());
                edgeType = calc.getValue();
                /*i = 0;
                while(!edgeType.equals(ecode[i])){
                    i++;
                }
                units += ecodeValue[i];*/

                ecodeTree.display();
                ecodeQueue = ecodeTree.getQueueOne();
                ecodeType = ecodeTree.getQueueTwo();
                iterOne = ecodeQueue.iterator();
                iterTwo = ecodeType.iterator();
                while(iterOne.hasNext()){
                    ecodeString = (String)iterOne.next();
                    ecodeTypeString = (int)iterTwo.next();
                    if(edgeType.equals(ecodeString)){
                        units += ecodeTypeString;
                    }
                }

            }
        }
        while(!queue.isEmpty()){
            fromValue = toValue;
            toValue = (DSAGraphVertex)queue.dequeue();
            calc = graph.getEdge(fromValue.getLabel(), toValue.getLabel());
            edgeType = calc.getValue();
            i = 0;

            ecodeTree.display();
            ecodeQueue = ecodeTree.getQueueOne();
            ecodeType = ecodeTree.getQueueTwo();
            iterOne = ecodeQueue.iterator();
            iterTwo = ecodeType.iterator();
            while(iterOne.hasNext()){
                ecodeString = (String)iterOne.next();
                ecodeTypeString = (int)iterTwo.next();
                if(edgeType.equals(ecodeString)){
                    units += ecodeTypeString;
                }
            }
        }
    }

    private static void heapFunction(int stackCount, DSAHeap heap){
        DSAStack[] stackArray;
        DSAGraphVertex v;

        heap.heapSort();

        heapEntry = heap.getHeap();
        heapPriority = heap.getHeapPriority();
        stackArray = new DSAStack[stackCount];
        for(int i = 0; i < heapEntry.length; i++){
            stackArray[i] = (DSAStack)heapEntry[i];
        }

        for (Object o : stackArray[heapEntry.length - 1]){
            v = (DSAGraphVertex)o;
            System.out.print(v.getLabel() + " ");
        }
        System.out.print(" rank " + heapPriority[heapEntry.length - 1]);
        System.out.println(" ");
    }

    private static void usageInformation(){
        System.out.println("This is how, you should use the program.");
    }
}