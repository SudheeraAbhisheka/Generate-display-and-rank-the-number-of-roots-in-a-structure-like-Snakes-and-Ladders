public class DSAHeap{
    private class DSAHeapEntry{
        private int priority;
        private Object value;

        private DSAHeapEntry(int inPriority, Object inValue){
            priority = inPriority;
            value = inValue;
        }
    }

    private DSAHeapEntry[] heap;
    private int count;

    public DSAHeap(int maxSize){
        heap = new DSAHeapEntry[maxSize];
        count = 0;
    }

    public void add(int priority, Object value){
        heap[count] = new DSAHeapEntry(priority, value);
        trickleUp(count);
        count++;
    }

    public Object remove(){
        Object highestPri;

        highestPri = heap[0].value;
        heap[0] = heap[count - 1]; 
        trickleDown(0, count);
        count--;

        return highestPri;
    }

    public void heapSort(){
        for(int i = (count/2) - 1; i >= 0; i--){
            trickleDown(i, count);
        }
        for(int i = count - 1; i > 0; i--){
            swap(0, i);
            trickleDown(0, i);
        }
    }
    
    private void trickleUp(int curIdx){
        int parentIdx;
        DSAHeapEntry temp;

        parentIdx = (curIdx - 1) / 2;
        if(curIdx > 0){
            if(heap[curIdx].priority > heap[parentIdx].priority){
                swap(parentIdx, curIdx);
                trickleUp(parentIdx);
            }
        }
    }

    private void trickleDown(int curIdx, int numItems){
        int lChildIdx, rChildIdx;
        int largeIdx;
        DSAHeapEntry temp;
        
        lChildIdx = curIdx * 2 + 1;
        rChildIdx = lChildIdx + 1;
        if(lChildIdx < numItems){
            largeIdx = lChildIdx;
            if(rChildIdx < numItems){
                if(heap[lChildIdx].priority < heap[rChildIdx].priority){
                    largeIdx = rChildIdx;
                }
                if(heap[largeIdx].priority > heap[curIdx].priority){
                    swap(largeIdx, curIdx);
                    trickleDown(largeIdx, numItems);
                }
            }
        }
    }

    private void swap(int indexOne, int indexTwo){
        DSAHeapEntry temp;

        temp = heap[indexOne];
        heap[indexOne] = heap[indexTwo];
        heap[indexTwo] = temp;
    }

    public Object[] getHeap(){
        Object[] heapObject;

        heapObject = new Object[count];
        for(int i = 0; i < count; i++){
            heapObject[i] = heap[i].value;
        }

        return heapObject;
    }

    public int[] getHeapPriority(){
        int[] heapPriority;

        heapPriority = new int[count];
        for(int i = 0; i < count; i++){
            heapPriority[i] = heap[i].priority;
        }

        return heapPriority;
    }
}