import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree {
    private Node root;
    
    public BinaryTree() {
        // empty
    }
    
    public BinaryTree(Node root) {
        this.root = root;
    }
    
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public static class Node {
        protected int value;
        protected Node left;
        protected Node right;
        
        public Node(){
            // empty
        }
        
        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        
        public int getValue() {
            return value;
        }
        public void setValue(int value) {
            this.value = value;
        }
        public Node getLeft() {
            return left;
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public Node getRight() {
            return right;
        }
        public void setRight(Node right) {
            this.right = right;
        }
    }
    
    public void preOrder(Node node){
        if(node == null) {
            return;
        }
        
        System.out.print(" " + node.getValue());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }
    
    public void preOrderIter(){
        if(getRoot() == null) {
            return;
        }
        
        Stack<Node> stack = new Stack<Node>();
        stack.push(getRoot());
        while(!stack.empty()) {
            Node n = stack.pop();
            System.out.print(" " + n.getValue());

            if(n.getRight() != null) {
                stack.push(n.getRight());
            }
            if(n.getLeft() != null) {
                stack.push(n.getLeft());
            }
        }
    }
    
    public void inOrder(Node node){
        if(node == null) {
            return;
        }
        
        inOrder(node.getLeft());
        System.out.print(" " + node.getValue());
        inOrder(node.getRight());
    }

    public void inOrderIter() {
        if(getRoot() == null) {
            return;
        }
        
        Node node = getRoot();
        Stack<Node> stack = new Stack<Node>();
        
        while (!stack.empty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.getLeft();   
            } else {
                node = stack.pop();
                System.out.print(" " + node.getValue());
                node = node.right;
            }   
        }
    }
    
    public void postOrder(Node node){
        if(node == null) {
            return;
        }

        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(" " + node.getValue());
    }
    
    public void postOrderIter() {
        if(getRoot() == null) {
            return;
        }
        
        Node node = getRoot();
        Stack<Node> stack = new Stack<Node>();
        
        //while(!stack.empty() && )
        
        
    }
    
    public void bfs(){
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(getRoot());
        
        while(!queue.isEmpty()) {
            Node n = queue.remove();
            System.out.print(" " + n.getValue());
            if(n.getLeft() != null) {
                queue.add(n.getLeft());
            }
            if(n.getRight() != null) {
                queue.add(n.getRight());
            }
        }
    }
    
    public boolean equals(BinaryTree tree) {
        return equals(getRoot(), tree.getRoot());
    }
    
    private static boolean equals(Node thisTreeNode, Node otherTreeNode) {
        if(thisTreeNode == null && otherTreeNode == null) {
            return true;
        } else {            
            return thisTreeNode != null && otherTreeNode != null
                   && thisTreeNode.getValue() == otherTreeNode.getValue()
                   && equals(thisTreeNode.getLeft(), otherTreeNode.getLeft())
                   && equals(thisTreeNode.getRight(), otherTreeNode.getRight());
        }
    }
    
    public static BinaryTree buildTree() {
        Node node7 = new Node(7, null, null);
        Node node8 = new Node(8, null, null);
        Node node9 = new Node(9, null, null);
        Node node4 = new Node(4, node7, null);
        Node node5 = new Node(5, null, node8);
        Node node6 = new Node(6, null, node9);
        Node node2 = new Node(2, node4, node5);
        Node node3 = new Node(3, node6, null);
        Node node1 = new Node(1, node2, node3);
        BinaryTree tree = new BinaryTree(node1);
        return tree;
    }
    
    public static BinaryTree buildTree2() {
        Node node7 = new Node(11, null, null);
        Node node8 = new Node(8, null, null);
        Node node9 = new Node(9, null, null);
        Node node4 = new Node(4, node7, null);
        Node node5 = new Node(5, null, node8);
        Node node6 = new Node(6, null, node9);
        Node node2 = new Node(2, node4, node5);
        Node node3 = new Node(3, node6, null);
        Node node1 = new Node(1, node2, node3);
        BinaryTree tree = new BinaryTree(node1);
        return tree;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        BinaryTree tree = BinaryTree.buildTree();
        BinaryTree tree2 = BinaryTree.buildTree2();
        System.out.println( "equals : " + tree.equals(tree2));

        tree.preOrder(tree.getRoot());
        System.out.println(" ");

        tree.inOrder(tree.getRoot());
        System.out.println(" ");
        
        tree.postOrder(tree.getRoot());
        System.out.println(" ");

        tree.preOrderIter();
        System.out.println(" ");
        
        tree.bfs();
        System.out.println(" ");
        
        tree.inOrderIter();
        System.out.println(" ");
        
    }
    
    

}
