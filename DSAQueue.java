import java.util.*;
public class DSAQueue implements Iterable{
    
    private DSALinkedList list;

    public DSAQueue(){
        list = new DSALinkedList();
    }
    
    public void enqueue(Object pValue){
        list.insertLast(pValue);
    }

    public boolean isEmpty(){
        boolean empty = false;
        if(list.isEmpty()){
            empty = true;
        }

        return empty;
    }

    public Object dequeue(){
        Object frontVal;
        frontVal = list.removeFirst();
        return frontVal;
    }

    public Object peek(){
        Object frontVal;
        frontVal = list.peekFirst();
        return frontVal;
    }

    public Iterator iterator(){
        return list.iterator();
    }
}