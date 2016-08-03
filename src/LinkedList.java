import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;


public class LinkedList<T> implements java.util.List, Queue {

    static class Node<T>{
        private T value;
        private Node<T> prev;
        private Node<T> next;
        public Node(T t){
            value = t;
        }

        public T getValue() {
            return value;
        }
        public void setValue(T value) {
            this.value = value;
        }
        public Node<T> getPrev() {
            return prev;
        }
        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }
        public Node<T> getNext() {
            return next;
        }
        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
    
    protected Node<T> header;
    protected int size = 0;
    
    public LinkedList(){
        header = new Node<T>(null);
        header.prev = header;
        header.next = header;
    }

    private Node<T> begin(){
        return header.next;
    }
    
    private Node<T> end(){
        return header;
    }

    public boolean add(Object arg0) {
        return addBefore(arg0, end());
    }

    public void addFirst(Object obj) {
        addBefore(obj, begin());
    }
    
    public void addLast(Object obj) {
        addBefore(obj, end());
    }
    
    private boolean addBefore(Object obj, Node n) {
        Node node = new Node(obj);
        node.prev = n.prev;
        node.next = n;
        n.prev.next = node;
        n.prev = node;
        size++;
        return true;
    }
    public void add(int index, Object obj) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node<T> node = getNode(index);
        addBefore(obj, node);
    }

    public boolean addAll(Collection arg0) {
        for(Object o : arg0) {
            add(o);
        }
        return false;
    }

    public boolean addAll(int index, Collection collection) {
        return true;
    }

    public void clear() {
        Node<T> node = begin();
        while(node != null) {
            remove(0);
        }
    }

    public boolean contains(Object arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean containsAll(Collection arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        return getNode(index).getValue();
    }

    public Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = header;
        for(int i = 0; i < index; i++){
            node = node.next;
        }
        return node.next;
    }
    
    public int indexOf(Object arg0) {
        Node<T> node = begin();
        int index = 0;
        while(node != end()) {
            if(node.value.equals(arg0)) {
                return index;
            } else {
                node = node.next;
                index++;
            }
        }
        
        return -1;
    }

    public boolean isEmpty() {
        return header.getNext() == header;
    }

    public Iterator iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    public int lastIndexOf(Object arg0) {
        Node<T> node = header.prev;
        int index = 0;
        while(node != header) {
            if(node.value.equals(arg0)) {
                return index;
            } else {
                node = node.prev;
                index--;
            }
        }
        
        return -1;
    }

    public ListIterator listIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    public ListIterator listIterator(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean remove(Object arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public Object remove(int index) {
        if (index >= 0 && index < size) {
            Node<T> node = begin();
            while(index > 0) {
                node = node.next;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
            return node;
        }
        return null;
    }

    public boolean removeAll(Collection arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean retainAll(Collection arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public Object set(int index, Object obj) {
        Node<T> n = getNode(index);
        Object oldObj = n.getValue();
        n.setValue((T)obj);
        return oldObj;
    }

    public int size() {
        // TODO Auto-generated method stub
        return size;
    }

    public List subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        return null;
    }

    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object[] toArray(Object[] arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public void printList(){
        Node<T> node = header.next;
        while(node != header) {
            System.out.print(" " + node.getValue());
            node = node.next;
        }
        System.out.println("");
    }
    
    public Iterator descendingIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object element() {
        // TODO Auto-generated method stub
        return getFirst();
    }

    public Object getFirst() {
        if(size == 0) {
            throw new NoSuchElementException();
        }
        return begin().getValue();
    }

    public Object getLast() {
        if(size == 0) {
            throw new NoSuchElementException();
        }
        return end().prev.getValue();
    }


    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(2);
        list.add(5);
        list.add(8);
        list.add(12);
        
        list.printList();
        
        
    }

    public boolean offer(Object arg0) {
        return add(arg0);
    }

    public Object peek() {
        if(size == 0) {
            return null;
        }
        return getFirst();
    }

    public Object poll() {
        return remove(0);
    }

    public Object remove() {
        return remove(0);
    }

}
