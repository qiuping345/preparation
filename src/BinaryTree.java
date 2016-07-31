import java.util.LinkedList;
import java.util.List;
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
        
        Stack<Node> stack = new Stack<Node>();
        Node curr = getRoot();
        while (!stack.empty() || curr != null) {
            if(curr == null) {
                curr = stack.pop();
                System.out.print(" " + curr.getValue());
                curr = curr.getRight();
            } else {
                stack.push(curr);
                curr = curr.getLeft();
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
        
        while(!stack.empty() || node != null) {
            if(node == null) {
                
                
            } else {
                
                
            }
        }
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
    
    public int getHeight(){
        return getHeight(getRoot());
    }
    
    protected int getHeight(Node node) {
        if(node == null) {
            return -1;
        } else {
            return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
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
    
    public static BinaryTree buildBinarySearchTree() {
        Node node23 = new Node(23, null, null);
        Node node81 = new Node(81, null, null);
        Node node94 = new Node(94, null, null);
        Node node45 = new Node(45, node23, null);
        Node node9 = new Node(9, null, null);
        Node node17 = new Node(17, node9, node45);
        Node node65 = new Node(65, null, null);
        Node node87 = new Node(87, node81, node94);
        Node node78 = new Node(78, node65, node87);
        Node node53 = new Node(53, node17, node78);   
        return new BinaryTree(node53);
    }
    
    public Node binarySearch(int val) {
        return binarySearch(getRoot(), val);
    }
    
    public Node binarySearch(Node node, int val) {
        if(node == null) {
            return null;
        } else if(node.getValue() == val) {
            return node;
        } else if (node.getValue() > val) {
            return binarySearch(node.getLeft(), val);
        } else {
            return binarySearch(node.getRight(), val);
        }
    }
    
    public Node iterativeBinarySearch(int val) {
        Node curr = getRoot();
        while(curr != null) {
            if(curr.getValue() > val) {
                curr = curr.getLeft();
            } else if(curr.getValue() < val) {
                curr = curr.getRight();
            } else {
                return curr;
            }
            
        }
        return null;
    }
    
    public Node bsAdd(int val) {
        Node newNode = new Node(val, null, null);
        if(getRoot() == null) {
            root = newNode;
            return newNode;
        }

        Node curr = getRoot();
        while(curr != null) {
            if(curr.getValue() > val) {
                if(curr.getLeft() != null) {
                    curr = curr.getLeft();
                } else {
                    curr.setLeft(newNode);
                }
            } else if(curr.getValue() < val) {
                if(curr.getRight() != null) {
                    curr = curr.getRight();
                } else {
                    curr.setRight(newNode);
                }
            } else {
                return null;
            }
        }
        return newNode;
    }
    
    public Node bsDelete(int val) {
        Node parent = null;
        Node curr = getRoot();
        while(curr != null) {
            Node next = null;
            if (val == curr.getValue()) {
                break;
            } else if(curr.getValue() > val) {
                next = curr.getLeft();
            } else {
                next = curr.getRight();
            }
            
            if (next != null) {
                parent = curr;
            }
            curr = next;
        }
        
        if(curr == null) {
            //not found
            return null;
        }

        if(curr.getLeft() == null) {
            if(curr == parent.getLeft()) {
                parent.setLeft(curr.getRight());
            } else {
                parent.setRight(curr.getRight());
            }
        } else if(curr.getRight() == null) {
            if(curr == parent.getLeft()) {
                parent.setLeft(curr.getLeft());
            } else {
                parent.setRight(curr.getLeft());
            }            
        } else {
            Node min = getMin(curr.getRight());
            bsDelete(min.getValue());
            curr.setValue(min.getValue());
        }
        
        return curr;
    }
    
    public boolean isBst() {
        return isBst(getRoot());
    }
    
    public boolean isBst(Node node) {
        if(node == null) {
            return true;
        }
        
        return (node.getLeft() == null ? true : node.getValue() > node.getLeft().getValue() && isBst(node.getLeft()))
               && (node.getRight() == null ? true : node.getValue() < node.getRight().getValue() && isBst(node.getRight()));
    }
    
    public Node getMin(Node root) {
        Node result = null;
        while(root != null) {
            result = root;
            root = root.getLeft();
        }
        return result;
    }
    
    public LinkedList<Integer> toList() {
        LinkedList<Integer> result = new LinkedList<Integer>();
        toList(getRoot(), result);
        return result;
    }
    
    public void toList(Node node, List<Integer> list) {
        if(node == null || list == null) {
            return;
        }
        
        
        if(node.getLeft() != null) {
            toList(node.getLeft(), list);
        }
        list.add(new Integer(node.getValue()));
        if(node.getRight() != null) {
            toList(node.getRight(), list);
        }
    }

    public void printList(List<Integer> list) {
        for (Integer i : list) {
            System.out.print( " " + i);
        }
        System.out.println( " ");
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        BinaryTree tree = BinaryTree.buildTree();
        BinaryTree tree2 = BinaryTree.buildTree2();
        System.out.println("tree isBst : " + tree.isBst());
        System.out.println("tree2 isBst : " + tree2.isBst());

//        System.out.println("height of tree : " + tree.getHeight());
//        System.out.println( "equals : " + tree.equals(tree2));
        
        BinaryTree bst = BinaryTree.buildBinarySearchTree();
        System.out.println("bst isBst : " + bst.isBst());
        Node node = bst.iterativeBinarySearch(77);
        System.out.println("node: " + (node != null ? node.getValue() : "not found") );
        
        System.out.println("before");
        LinkedList<Integer> list = bst.toList();
        bst.printList(list);
        System.out.println("after");
        
        BinaryTree tree3 = new BinaryTree();
        tree3.bsAdd(53);
        tree3.bsAdd(78);
        tree3.bsAdd(65);
        tree3.bsAdd(17);
        tree3.bsAdd(87);
        tree3.bsAdd(9);
        tree3.bsAdd(81);
        tree3.bsAdd(45);
        tree3.bsAdd(23);
        tree3.bsAdd(94);

        tree3.preOrderIter();
        System.out.println(" ");
        tree3.inOrderIter();
        System.out.println(" ");
        
        System.out.println("tree3 is BST: " + tree3.isBst());
        
        Node n = tree3.iterativeBinarySearch(9);
        System.out.println("found value: " + (n != null? "found" : "not found") + ", " + n.getValue());
        tree3.bsDelete(9);
        
        tree3.preOrderIter();
        System.out.println(" ");
        tree3.inOrderIter();
        System.out.println(" ");

        Node n2 = tree3.iterativeBinarySearch(45);
        System.out.println("found value: " + (n2 != null? "found" : "not found") + ", " + n2.getValue());
        tree3.bsDelete(45);
        
        tree3.preOrderIter();
        System.out.println(" ");
        tree3.inOrderIter();
        System.out.println(" ");
        
        
        Node n3 = tree3.iterativeBinarySearch(78);
        System.out.println("found value: " + (n3 != null? "found" : "not found") + ", " + n3.getValue());
        tree3.bsDelete(78);
        
        tree3.preOrderIter();
        System.out.println(" ");
        tree3.inOrderIter();
        System.out.println(" ");
        

        
        tree.preOrder(tree.getRoot());
        System.out.println(" ");
        tree.preOrderIter();
        System.out.println(" ");
        
        tree.inOrder(tree.getRoot());
        System.out.println(" ");
        
        tree.inOrderIter();
        System.out.println(" ");
        
        
        tree.postOrder(tree.getRoot());
        System.out.println(" ");

        tree.postOrderIter();
        System.out.println(" ");

        
        tree.bfs();
        System.out.println(" ");
        
    }
    
    

}
