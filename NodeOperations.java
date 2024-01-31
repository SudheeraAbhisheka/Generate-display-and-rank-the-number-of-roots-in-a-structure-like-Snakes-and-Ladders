import java.util.*;
public class NodeOperations{
    private DSABinarySearchTree bst; 
    public NodeOperations(DSABinarySearchTree bstIn){
        bst = bstIn;
    }
    public String findNode(String findNode){
        String found = null;
        found = (String)bst.find(findNode);

        return found;
    }
    public void insertNode(String insertNode, String insertNType){
        bst.insert(insertNode, insertNType);
    }
    public void deleteNode(String deleteNode){
        bst.delete(deleteNode);
    }
    public void updateNode(String node, String type){
        deleteNode(node);
        insertNode(node, type);
    }
}