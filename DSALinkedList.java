import java.util.*;
public class DSALinkedList implements Iterable{
    private class DSAListNode{
        private Object m_value;
        private DSAListNode m_next;

        public DSAListNode(Object inValue){
            m_value = inValue;
            m_next = null;
        }
    }

    private DSAListNode head;

    public DSALinkedList(){
        head = null;
    }

    public void insertFirst(Object newValue){
        DSAListNode newNd = new DSAListNode(newValue);
        if(isEmpty()){
            head = newNd;
        }
        else{
            newNd.m_next = head;
            head = newNd;
        }
    }

    public void insertLast(Object newValue){
        DSAListNode newNd = new DSAListNode(newValue);
        DSAListNode currNd;
        if(isEmpty()){
            head = newNd;
        }
        else{
            currNd = head;
            while(currNd.m_next != null){
                currNd = currNd.m_next;
            }
            currNd.m_next = newNd;
        }
    }
    
    public boolean isEmpty(){
        boolean empty = false;
        if(head == null){
            empty = true;
        }   
        return empty;
    }

    public Object peekFirst(){
        Object nodeValue;
        DSAListNode currNd;
        if(isEmpty()){
            throw new IllegalStateException("Empty list");
        }
        else{
            nodeValue = head.m_value;
        }
        return nodeValue;
    }

    public Object peekLast(){
        Object nodeValue;
        DSAListNode currNd;
        if(isEmpty()){
            throw new IllegalStateException("Empty list");
        }
        else{
            currNd = head;
            while(currNd.m_next != null){
                currNd = currNd.m_next;
            }
            nodeValue = currNd.m_value;
        }
        return nodeValue;
    }

    public Object removeFirst(){
        DSAListNode currNd;
        DSAListNode prevNd;
        Object nodeValue;
        if(isEmpty()){
            throw new IllegalStateException("Empty list");
        }
        else{
            nodeValue = head.m_value;
            head = head.m_next;
        }

        return nodeValue;
    }

    public Object removeLast(){
        DSAListNode currNd;
        DSAListNode prevNd;
        Object nodeValue;
        if(isEmpty()){
            throw new IllegalStateException("Empty list");
        }
        else if(head.m_next == null){
            nodeValue = head.m_value;
            head = null;
        }
        else{
            prevNd = null;
            currNd = head;
            while(currNd.m_next != null){
                prevNd = currNd;
                currNd = currNd.m_next;
            }
            prevNd.m_next = null;
            nodeValue = currNd.m_value;
        }

        return nodeValue;
    }

    public Iterator iterator(){
        return new DSALinkedListIterator(this);
    }

    private class DSALinkedListIterator implements Iterator{
        private DSAListNode iterNext;

        public DSALinkedListIterator(DSALinkedList theList){
            iterNext = theList.head;
        }

        public boolean hasNext(){
            return (iterNext != null); 
        }
    
        public Object next(){
            Object value;
            if (!hasNext()){
                value = null;
            }
            else{
                value = iterNext.m_value;
                iterNext = iterNext.m_next;
            }

            return value;
        }

        public void remove(){ 
            throw new UnsupportedOperationException("Not supported"); 
        }
    }
}