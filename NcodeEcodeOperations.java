import java.util.*;
public class NcodeEcodeOperations{
    private DSABinarySearchTree bst; 
    public NcodeEcodeOperations(DSABinarySearchTree bstIn){
        bst = bstIn;
    }
    public int findCode(String findNcode){
        int found = 0;
        found = (int)bst.find(findNcode);

        return found;
    }
    public void insertCode(String insertNcode, int insertNType){
        bst.insert(insertNcode, insertNType);
    }
    public void deleteCode(String deleteNcode){
        bst.delete(deleteNcode);
    }
    public void updateCode(String ncode, int type){
        deleteCode(ncode);
        insertCode(ncode, type);
    }
}