import java.util.*;
public class main{
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
    private static WritingToAFile newFile;;
    private static int stackCount;
    private static DSAHeap heap;
    private static ParsingData data;


    public static void main(String[] args){
        if(args.length == 0){
            usageInformation();
        }
        else if(!(args[0].equals("-i") || args[0].equals("-s"))){
            throw new IllegalArgumentException(args[0] + " isn't a valid input");
        }
        else{
            DSAQueue queue;
            DSALinkedList adjacentList;
            DSAGraphVertex object;
            String fileName;
            String found;
            int intFound;
            NodeOperations nodeOp;
            EdgeOperations edgeOp;
            NcodeEcodeOperations ncodeOp;
            DSAStack rankStack;

            newFile = new WritingToAFile();

            if(args[0].equals("-i")){
                if(args.length != 1){
                    throw new IllegalArgumentException("Invalid amount of araguments.");
                }
                menu();
            }
            else if(args[0].equals("-s")){

                if(args.length != 3){
                    throw new IllegalArgumentException("Invalid amount of araguments.");
                }
                fileName = args[1];

                importData(fileName);

                parsingToGraph();

                graph.advanceSearch();

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
                System.out.println("Number of paths = " + stackCount);
                
                newFile.writingAFile(args[2], heapEntry, heapPriority);

                System.exit(0);
            }
        }
    }

    private static void menu(){
        int i;
        String fileName;
        String tempOne;
        String tempTwo;
        String tempThree;
        int tempInt;
        String[][] matrixArray;
        String[][] worldInformation;
        String[][] saveNetwork;
        int saveCount;
        int track;
        DSAQueue saveNodes, saveNodeValues, saveEdges, saveEdgeLabel1, saveEdgeLabel2;
        DSAQueue saveEdgeValue, saveNcodes, saveNcodeValues, saveEcodes, saveEcodeValues;
        Iterator iterOne, iterTwo, iterThree;
        String[] tempArray;
        
        System.out.println(" ");
        System.out.println("(1) Load input file");
        System.out.println("(2) Node operations");
        System.out.println("(3) Edge operations");
        System.out.println("(4) Parameter tweaks");
        System.out.println("(5) Display graph");
        System.out.println("(6) Display world");
        System.out.println("(7) Generate routes");
        System.out.println("(8) Display routes");
        System.out.println("(9) Save network");

        Scanner sc = new Scanner(System.in);
        i = sc.nextInt();
        sc.nextLine();
        if(i == 1){
            System.out.print("Enter the file name: ");
            fileName = sc.nextLine();
            importData(fileName);
            menu();
        }
        else if(i == 2){
            NodeOperations nodeOp;
            nodeOp = new NodeOperations(nodeTree);
            System.out.println("1. find");
            System.out.println("2. insert");
            System.out.println("3. delete");
            System.out.println("4. update");
            i = sc.nextInt();
            sc.nextLine();
            
            if(i == 1){

                System.out.print("Node: ");
                tempOne = sc.nextLine();
                tempTwo = nodeOp.findNode(tempOne);
                System.out.println("Found node = " + tempTwo);
                menu();

            }
            else if(i == 2){

                System.out.print("Node: ");
                tempOne = sc.nextLine();
                System.out.print("Node code: ");
                tempTwo = sc.nextLine();
                nodeOp.insertNode(tempOne, tempTwo);
                menu();

            }
            else if(i == 3){

                System.out.print("Node: ");
                tempOne = sc.nextLine();
                nodeOp.deleteNode(tempOne);
                menu();

            }
            else if(i == 4){

                System.out.print("Node: ");
                tempOne = sc.nextLine();
                System.out.print("Node code: ");
                tempTwo = sc.nextLine();
                nodeOp.updateNode(tempOne, tempTwo);
                menu();

            }
            else{
                throw new IllegalArgumentException(Integer.toString(i) + " is not a valid input");
            }  
        } 
        else if(i == 3){
            EdgeOperations edgeOp;
            edgeOp = new EdgeOperations(edgeTree);
            System.out.println("1. find");
            System.out.println("2. insert");
            System.out.println("3. delete");
            System.out.println("4. update");
            i = sc.nextInt();
            sc.nextLine();
            if(i == 1){

                System.out.print("First node: ");
                tempOne = sc.nextLine();
                System.out.print("Second node: ");
                tempTwo = sc.nextLine();
                tempThree = edgeOp.findEdge(tempOne, tempTwo);
                System.out.println("Edge weight: " + tempThree);
                menu();

            }
            else if(i == 2){

                System.out.print("First node: ");
                tempOne = sc.nextLine();
                System.out.print("Second node: ");
                tempTwo = sc.nextLine();
                System.out.print("Edge weight: ");
                tempThree = sc.nextLine();
                trackKey = edgeOp.insertEdge(tempOne, tempTwo, tempThree, trackKey);
                menu();

            }
            else if(i == 3){

                System.out.print("First node: ");
                tempOne = sc.nextLine();
                System.out.print("Second node: ");
                tempTwo = sc.nextLine();
                edgeOp.deleteEdge(tempOne, tempTwo);
                menu();

            }
            else if(i == 4){

                System.out.print("First node: ");
                tempOne = sc.nextLine();
                System.out.print("Second node: ");
                tempTwo = sc.nextLine();
                System.out.print("Edge weight: ");
                tempThree = sc.nextLine();
                trackKey = edgeOp.updateEdge(tempOne, tempTwo, tempThree, trackKey);
                menu();

            }
            else{
                throw new IllegalArgumentException(Integer.toString(i) + " is not a valid input");
            }  
        }
        else if(i == 4){
            System.out.println("1. Ncode operations");
            System.out.println("2. Ecode operations");
            i = sc.nextInt();
            sc.nextLine();
            if(i == 1){
                NcodeEcodeOperations ncodeOp;
                ncodeOp = new NcodeEcodeOperations(ncodeTree);
                System.out.println("1. find");
                System.out.println("2. insert");
                System.out.println("3. delete");
                System.out.println("4. update");
                i = sc.nextInt();
                sc.nextLine();
            
                if(i == 1){

                    System.out.print("Ncode: ");
                    tempOne = sc.nextLine();
                    tempInt = ncodeOp.findCode(tempOne);
                    System.out.println("Found ncode = " + tempInt);
                    menu();

                }
                else if(i == 2){

                    System.out.print("Ncode: ");
                    tempOne = sc.nextLine();
                    System.out.print("Ncode value: ");
                    tempInt = sc.nextInt();
                    sc.nextLine();
                    ncodeOp.insertCode(tempOne, tempInt);
                    menu();

                }
                else if(i == 3){

                    System.out.print("Ncode: ");
                    tempOne = sc.nextLine();
                    ncodeOp.deleteCode(tempOne);
                    menu();

                }
                else if(i == 4){

                    System.out.print("Ncode: ");
                    tempOne = sc.nextLine();
                    System.out.print("Ncode value: ");
                    tempInt = sc.nextInt();
                    sc.nextLine();
                    ncodeOp.updateCode(tempOne, tempInt);
                    menu();

                }
                else{
                    throw new IllegalArgumentException(Integer.toString(i) + " is not a valid input");
                }  
            }
            if(i == 2){
                NcodeEcodeOperations ecodeOp;
                ecodeOp = new NcodeEcodeOperations(ecodeTree);
                System.out.println("1. find");
                System.out.println("2. insert");
                System.out.println("3. delete");
                System.out.println("4. update");
                i = sc.nextInt();
                sc.nextLine();
            
                if(i == 1){

                    System.out.print("Ecode: ");
                    tempOne = sc.nextLine();
                    tempInt = ecodeOp.findCode(tempOne);
                    System.out.println("Found ecode = " + tempInt);
                    menu();

                }
                else if(i == 2){

                    System.out.print("Ecode: ");
                    tempOne = sc.nextLine();
                    System.out.print("Ecode value: ");
                    tempInt = sc.nextInt();
                    sc.nextLine();
                    ecodeOp.insertCode(tempOne, tempInt);
                    menu();

                }
                else if(i == 3){

                    System.out.print("Ecode: ");
                    tempOne = sc.nextLine();
                    ecodeOp.deleteCode(tempOne);
                    menu();

                }
                else if(i == 4){

                    System.out.print("Ecode: ");
                    tempOne = sc.nextLine();
                    System.out.print("Ecode value: ");
                    tempInt = sc.nextInt();
                    sc.nextLine();
                    ecodeOp.updateCode(tempOne, tempInt);
                    menu();

                }
                else{
                    throw new IllegalArgumentException(Integer.toString(i) + " is not a valid input");
                }  
            }
            else{
                throw new IllegalArgumentException(Integer.toString(i) + " is not a valid input");
            }  
        }
        else if(i == 5){
            parsingToGraph();
            matrixArray = graph.displayAsMatrix();
            System.out.println("1. Save to a file");
            System.out.println("2. Menu");
            i = sc.nextInt();
            sc.nextLine();
            if(i == 1){
                System.out.print("Enter a file name to save: ");
                tempOne = sc.nextLine();
                newFile.saveMatrix(tempOne, matrixArray);
                System.out.println(tempOne + " was created.");
                menu();
            }
            else if(i == 2){
                menu();
            }
            else{
                throw new IllegalArgumentException(Integer.toString(i) + " is not a valid input");
            }  
        }
        else if(i == 6){
            worldInformation = new String[2][2];
            tempOne = nodeTree.getCount();
            tempTwo = edgeTree.getCount();
            worldInformation[0][0] = "Number of nodes: ";
            worldInformation[1][0] = "Number of edges: ";
            worldInformation[0][1] = tempOne;
            worldInformation[1][1] = tempTwo;

            for(int l = 0; l < worldInformation.length; l++){
                for(int k = 0; k < worldInformation.length; k++){
                    System.out.print(worldInformation[l][k] + " ");
                }
                System.out.println();
            }

            System.out.println("1. Save to a file");
            System.out.println("2. Menu");
            i = sc.nextInt();
            sc.nextLine();
            if(i == 1){
                System.out.print("Enter a file name to save: ");
                tempOne = sc.nextLine();
                newFile.saveMatrix(tempOne, worldInformation);
                System.out.println(tempOne + " was created.");
                menu();
            }
            else if(i == 2){
                menu();
            }
            else{
                throw new IllegalArgumentException(Integer.toString(i) + " is not a valid input");
            }  
        }
        else if(i == 7){

            parsingToGraph();
            graph.advanceSearch();

            stack = graph.getStack();
            stackCount = graph.getStackCount();

            System.out.println(stackCount + " routes have been created.");

            menu();
        }
        else if(i == 8){
            heap = new DSAHeap(stackCount);

            for (Object o : stack){
                units = 0;
                edgeOperations((DSAStack)o);
                nodeOperatons((DSAStack)o);
                heap.add(units, o);
            }
            heapFunction(stackCount, heap);

            System.out.println("1. Save to a file");
            System.out.println("2. Menu");
            i = sc.nextInt();
            sc.nextLine();
            if(i == 1){
                System.out.print("Enter a file name to save: ");
                tempOne = sc.nextLine();
                newFile.writingAFile(tempOne, heapEntry, heapPriority);
                System.out.println(tempOne + " was created.");
                menu();
            }
            else if(i == 2){
                menu();
            }
            else{
                throw new IllegalArgumentException(Integer.toString(i) + " is not a valid input");
            }  
        }
        else if(i == 9){
            saveCount = 1; // Start
            saveCount += data.getTargetCount();
            saveCount += ncodeTree.getCountInt();
            saveCount += nodeTree.getCountInt();
            saveCount += ecodeTree.getCountInt();
            saveCount += edgeTree.getCountInt();
            System.out.println("Save count " + saveCount);

            ncodeTree.display();
            ecodeTree.display();
            nodeTree.display();
            edgeTree.display();

            saveNetwork = new String[saveCount][4];
            for(int l = 0; l < saveNetwork.length; l++){
                for(int k = 0; k < saveNetwork[0].length; k++){
                    saveNetwork[l][k] = " ";
                }
            }


            saveEdgeLabel1 = new DSAQueue();
            saveEdgeLabel2 = new DSAQueue();
            saveEdgeValue = new DSAQueue();
            saveNodes = nodeTree.getQueueOne();
            saveNodeValues = nodeTree.getQueueTwo();
            saveEdges = edgeTree.getQueueTwo();

            for(Object o : saveEdges){
                tempArray = (String[])o;
                saveEdgeLabel1.enqueue(tempArray[0]);
                saveEdgeLabel2.enqueue(tempArray[1]);
                saveEdgeValue.enqueue(tempArray[2]);
            }

            saveNcodes = ncodeTree.getQueueOne();
            saveNcodeValues = ncodeTree.getQueueTwo();
            saveEcodes = ecodeTree.getQueueOne();
            saveEcodeValues = ecodeTree.getQueueTwo();

            track = 0;
            iterOne = saveNodes.iterator();
            iterTwo = saveNodeValues.iterator();
            while(iterOne.hasNext()){
                saveNetwork[track][0] = "Node";
                saveNetwork[track][1] = (String)iterOne.next();
                saveNetwork[track][2] = (String)iterTwo.next();
                track++;
            }

            iterOne = saveEdgeLabel1.iterator();
            iterTwo = saveEdgeLabel2.iterator();
            iterThree = saveEdgeValue.iterator();
            while(iterOne.hasNext()){
                saveNetwork[track][0] = "Edge"; 
                saveNetwork[track][1] = (String)iterOne.next();
                saveNetwork[track][2] = (String)iterTwo.next();
                saveNetwork[track][3] = (String)iterThree.next();
                track++;
            }

            iterOne = saveNcodes.iterator();
            iterTwo = saveNcodeValues.iterator();
            while(iterOne.hasNext()){
                saveNetwork[track][0] = "Ncode";
                saveNetwork[track][1] = (String)iterOne.next();
                tempInt = (int)iterTwo.next();
                saveNetwork[track][2] = Integer.toString(tempInt);
                track++;
            }

            iterOne = saveEcodes.iterator();
            iterTwo = saveEcodeValues.iterator();
            while(iterOne.hasNext()){
                saveNetwork[track][0] = "Ecode";
                saveNetwork[track][1] = (String)iterOne.next();
                tempInt = (int)iterTwo.next();
                saveNetwork[track][2] = Integer.toString(tempInt);
                track++;
            }

            saveNetwork[track][0] = "Start";
            saveNetwork[track][1] = start;
            track++;

            for(int j = 0; j < target.length; j++){
                saveNetwork[track][0] = "Target";
                saveNetwork[track][1] = target[j];
                track++;
            }
            System.out.print("Enter a file name to save: ");
            tempOne = sc.nextLine();
            newFile.saveNetwork(tempOne, saveNetwork);
            System.out.println(tempOne + " was created.");
        }
    }

    private static void importData(String fileName){ 
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
            trackKey = i;
        }

        ncodeCount = data.getNcodeCount();
        ncodeTree = new DSABinarySearchTree();

        for(int i = 0; i < ncodeCount; i++){
            ncodeTree.insert(data.getNcode()[i], data.getNcodeValue()[i]);
        }

        ecodeCount = data.getEcodeCount();
        ecodeTree = new DSABinarySearchTree();

        for(int i = 0; i < ecodeCount; i++){
            ecodeTree.insert(data.getEcode()[i], data.getEcodeValue()[i]);
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
        }
        
        queueTwoTwo = new DSAQueue();
        edgeTree.display();
        queueTwoTwo = edgeTree.getQueueTwo();

        for (Object o : queueTwoTwo){
            tempArray = (String[])o;
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
        }

        if(!queue.isEmpty()){
            fromValue = (DSAGraphVertex)queue.dequeue();
            if(!queue.isEmpty()){
                toValue = (DSAGraphVertex)queue.dequeue();
                calc = graph.getEdge(fromValue.getLabel(), toValue.getLabel());
                edgeType = calc.getValue();

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

        for(int i = 0; i < heapEntry.length; i++){
            System.out.print("Rank: " + i + "   ");
            for (Object o : stackArray[i]){
                v = (DSAGraphVertex)o;
                System.out.print(v.getLabel() + " ");
            }
            System.out.print("  Units = " + heapPriority[i]);
            System.out.println(" ");
        }
    }

    private static void usageInformation(){
        System.out.println("main -i ");
        System.out.println("Interactive testing environment.\n");
        System.out.println("main -s inFile saveFile  ");
        System.out.println("Simulation mode.\n");
    }
}