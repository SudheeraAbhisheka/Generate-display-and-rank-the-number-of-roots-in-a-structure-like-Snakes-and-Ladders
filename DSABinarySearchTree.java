import java.util.*;
import java.lang.*;
public class DSABinarySearchTree {
    private class TreeNode
    {
        private String m_key;
        private Object m_value;
        private TreeNode m_leftChild;
        private TreeNode m_rightChild;
        public TreeNode(String inKey, Object inVal) {
            if (inKey == null)
                throw new IllegalArgumentException("Key cannot be null");
            m_key = inKey;
            m_value = inVal;
            m_rightChild = null;
            m_leftChild = null;
        }
        public String getKey() {
            return m_key; 
        }
        public Object getValue() { 
            return m_value; 
        }
        public TreeNode getLeft() { 
            return m_leftChild; 
        }
        public void setLeft(TreeNode newLeft) { 
            m_leftChild = newLeft; 
        }
        public TreeNode getRight() { 
            return m_rightChild; 
        }
        public void setRight(TreeNode newRight) { 
            m_rightChild = newRight; 
        }
    }

    private DSAQueue queueOne;
    private DSAQueue queueTwo;
    private TreeNode m_root;
    private int count;
    public DSABinarySearchTree() {
        m_root = null;
    }

    public Object find(String key) {
        return findRec(key, m_root);
    }
    private Object findRec(String key, TreeNode currNode){
        Object value = null;
        if (currNode == null)
            throw new NoSuchElementException("Key " + key + " not found");
        else if (key.equals(currNode.getKey()))
            value = currNode.getValue();
        else if (key.compareTo(currNode.getKey()) < 0)
            value = findRec(key, currNode.getLeft());
        else 
            value = findRec(key, currNode.getRight());
        return value;
    }
    public void insert(String key, Object data){
        m_root = insertRec(key, data, m_root);
    }
    private TreeNode insertRec(String key, Object data, TreeNode currNode){
        TreeNode updateNode;
        updateNode = currNode;
        if(currNode == null){
            TreeNode newNode = new TreeNode(key, data);
            updateNode = newNode;
        }
        else if(key.equals(currNode.getKey())){
            throw new IllegalArgumentException(key);
        }
        else if(key.compareTo(currNode.getKey()) < 0){
            currNode.setLeft( insertRec(key, data, currNode.getLeft()) );
        }
        else{
            currNode.setRight( insertRec(key, data, currNode.getRight()) );
        }
        return updateNode;
    }
    public void delete(String key){
        m_root = deleteRec(key, m_root);
    }
    private TreeNode deleteRec(String key, TreeNode currNode){
        TreeNode updateNode;
        updateNode = currNode;
        if(currNode == null){
            throw new NoSuchElementException("Key " + key + " not found");
        }
        else if(key.equals(currNode.getKey())){
            updateNode = deleteNode(key, currNode);
        }
        else if(key.compareTo(currNode.getKey()) < 0){
            currNode.setLeft( deleteRec(key, currNode.getLeft()) );
        }
        else{
            currNode.setRight( deleteRec(key, currNode.getRight()) );
        }
        return updateNode;
    }
    private TreeNode deleteNode(String key, TreeNode delNode){
        TreeNode updateNode = null;
        if(delNode.getLeft() == null && delNode.getRight() == null){
            updateNode = null;
        }
        else if(delNode.getLeft() != null && delNode.getRight() == null){
            updateNode = delNode.getLeft();
        }
        else if(delNode.getLeft() == null && delNode.getRight() != null){
            updateNode = delNode.getRight();
        }
        else{
            updateNode = promoteSuccessor(delNode.getRight());
            if(updateNode != delNode.getRight()){
                updateNode.setRight(delNode.getRight());
            }
            updateNode.setLeft(delNode.getLeft());
        }
        return updateNode;
    }
    private TreeNode promoteSuccessor(TreeNode cur){
        TreeNode successor;
        successor = cur;
        if(cur.getLeft() != null){
            successor = promoteSuccessor(cur.getLeft());
            if(successor == cur.getLeft()){
                cur.setLeft(successor.getRight());
            }
        }
        return successor;
    }
    public void display(){
        queueOne = new DSAQueue();
        queueTwo = new DSAQueue();
        traverseTreeRec(m_root);
    }
    private void traverseTreeRec(TreeNode curNode){
        if(curNode != null){
            traverseTreeRec(curNode.getLeft());
            queueOne.enqueue(curNode.getKey());
            queueTwo.enqueue(curNode.getValue());
            traverseTreeRec(curNode.getRight());
        }
    }
    public DSAQueue getQueueOne(){
        return queueOne;
    }
    public DSAQueue getQueueTwo(){
        return queueTwo;
    }
    public String getCount(){
        count = 0;
        traverseTreeRecCount(m_root);
        return Integer.toString(count);
    }
    public int getCountInt(){
        count = 0;
        traverseTreeRecCount(m_root);
        return count;
    }
    private void traverseTreeRecCount(TreeNode curNode){
        if(curNode != null){
            traverseTreeRecCount(curNode.getLeft());
            count++;
            traverseTreeRecCount(curNode.getRight());
        }
    }
}