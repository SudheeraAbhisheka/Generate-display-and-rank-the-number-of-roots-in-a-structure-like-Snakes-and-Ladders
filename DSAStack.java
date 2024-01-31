import java.util.*;
public class DSAStack implements Iterable{
    private DSALinkedList list;

    public DSAStack(){
        list = new DSALinkedList();
    }

    public boolean isEmpty(){
        boolean empty = false;
        if(list.isEmpty()){
            empty = true;
        }

        return empty;
    }

    public void push( Object pValue ){
        list.insertFirst(pValue);
    }

    public Object pop(){
        Object topVal = null;
        topVal = list.removeFirst();
        return topVal;
    }

    public Iterator iterator(){
        return list.iterator();
    }
}