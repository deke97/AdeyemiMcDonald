/*  Name: Abby McDonald and Esther Adeyemi
 *  PennKeys: abigailm and oadeyemi
 *  Recitation: 202
 *
 *  Execution: java LinkedList
 *
 */

public class LinkedList<T> implements List<T> {
    private Node head; //assuming will identify head 
    
    public LinkedList() {  //constructor
        head = null; //starts null because empty
    }
    /*
     * Description: creates a Node class
     * Input: Point
     * Output: node
     */
    private class Node { //needs an element of type T and a Node next
        private T t; //instead of Private Point a like in Tour
        private Node next;
    }
    
    /*
     * Description: inserts Node
     * Input: node
     * Output: node
     */
    private void insertNode(Node n, T x) {
        Node inserted = new Node(); //prior = node before point of insertion
        inserted.t = x;
        inserted.next = n.next;
        n.next = inserted;
    }
    
    
    
    
    /**
     * Adds the object x to the end of the list.
     * @param x the element to be added to this list
     * @return true
     */
    public boolean add(T x) { //should always return true
        
        Node last = new Node();
        last.t = x;
        //System.out.println(last.t);
        if (head == null) {
            head = last;
            return true;   
        } 
        else {
            Node current;
            current = head;
            while (current.next != null) {
                current = current.next; 
            }   
            current.next = last;
            return true;
        }
        
    }
    
    /**
     * Adds the object x at the specified position
     * @param index the position to add the element
     * @param x the element to be added to the list
     * @return true if the operation succeeded, false otherwise
     */
    //should work correctly if 0 <= index <= size(), which allows 
    //for add(0, element) on an 
    //empty list and have it insert element as the new head (and tail) element
    
    public boolean add(int index, T x) { 
        Node current = head;
        if (index > size() || index < 0) {
            throw new RuntimeException("ERROR: Attempting to" +
                                       " add at invalid index");
        }
        
        Node newNode = new Node();
        newNode.t = x;
        
        if (head == null) { //beginning of empty list
            head = newNode;
            System.out.print(newNode.t);
            //return true;   
        } 
        
        else if (index == 0) { //insert at beginning 
            head = head.next;
            for (current = head; current != null; current = current.next) {
                System.out.print(current.t); 
            }
        }
        
        else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            
            insertNode(current, x);
            for (current = head; current != null; current = current.next) {
                System.out.print(current.t);
            }
        }
        return true;  
    }
    
    
    
    
    /**
     * Returns the number of elements in this list
     * @return the number of elements in this list
     */
    public int size() {
        int listSize = 0;
        for (Node current = head; current != null; current = current.next) { 
            listSize++;
        }
        //System.out.println("listsize " + listSize);
        return listSize;
    }
    
    
    
    /**
     * Returns the element with the specified position in this list
     * @param index the position of the element
     * @return the element at the specified position in this list
     */
//take in index and return an object
//if index is invalid, throw IllegalArgumentException (with a detailed
//message
    
    
    public T get(int index) {
        Node current = head;
        
        if (index < 0 || index >= size()) {
            throw new RuntimeException("ERROR: Attempting to" +
                                       " get invalid index");
        }
        
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }
        
        System.out.println(current.t);
        return current.t;
    }
    
    
    
    /**
     * Replaces the object at the specified position
     * @param index the position to replace
     * @param x the element to be stored
     * @return the previous value of the element at index
     */
//node must already exist at specified index, so can't be used to add
//new nodes to a list
    public T set(int index, T x) { //index = 1
        Node current = head;
        //Node replacedValue = head;
        
                if (index < 0 || index >= size()) {// if index does not exist             
                   throw new RuntimeException("ERROR: Attempting to" + 
                                              " get invalid index");         
                } 
                
        for (int i = 0; i < index; i++) {
            current = current.next;         
        }          
        
        T returnData = current.t;
        current.t = x;         
        for (current = head; current != null; current = current.next) {
            System.out.print(current.t);         
        }   
        
        return returnData;  
//return replaced object? does this mean the new value or old one?     
    } 
    
    
    
    /**
     * Removes the object at the specified position
     * @param index the position to remove
     * @return the object that was removed
     */
    public T remove(int index) { //returning the deleted one
        Node current = head;
        T returnValue = null;
        if (index < 0 || index >= size()) {
            throw new RuntimeException("ERROR: Attempting to" +
                                       " get invalid index");
        }
        
        else if (index == 0) {
            T data = head.t;
            head = head.next;
            for (current = head; current != null; current = current.next) {
                System.out.print(current.t);
                data = returnValue;
            }
        }
        
        else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            
            Node toReturn = current.next;
            current.next = current.next.next;
            for (current = head; current != null; current = current.next) {
                System.out.print(current.t);
            }
            toReturn.t = returnValue; //want the node of the index
        }
        return returnValue;
    }
    
    
//current.next = current.next.next
    
    /**
     * Tests if this list has no elements.
     * @return  <tt>true</tt> if this list has no elements;
     *          <tt>false</tt> otherwise.
     */
    public boolean isEmpty() {
        if (size() == 0) {
            System.out.println("true");
            return true;
        }
        else {
            System.out.println("false");
            return false;
            
        }
        
    }
    
    
    
    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * @param element element whose presence in this List is to be tested.
     * @return  <code>true</code> if the specified element is present;
     *  <code>false</code> otherwise.
     */
    public boolean contains(T element) {
        Node current = head;
        boolean statement = false;
        while (current != null) {
            if (current.t == element) { //if node has the element, exit loop
                //System.out.println("List contains element");
                statement = true;
            }
            else {
                current = current.next;
                //System.out.println("List does not contain element");
                statement = false;
            }
        }
        return statement;
    }
    
    
    /** 
     * Returns the index of the specified element
     *
     * @param element the element we're looking for
     * @return the index of the element in the list, or -1 
     * if it is not contained within the list
     */
    public int indexOf(T element) { 
        Node current = head;
        int returnValue = -1;
        int i = 0;
        
        for (i = 0; i < size(); i++) {
            System.out.println(current.t);
            if (current.t == element) {
                returnValue = i;
                break;
            }
            
            current = current.next;
        }
        System.out.println(returnValue);
        return returnValue;
    }
    
    public static void main(String[] args) {
        LinkedList<String> test = new LinkedList<String>();
//        
        String a = new String("Insert");
        String b = new String("Words");
        String d = new String("Here");
        String e = new String("Random");
        String f = new String("Esther");
        String c = new String("please");
        test.add(a); //adding String
        test.add(b);
        test.add(d);
        test.add(e);
        test.add(f);
        //test.add(c);
        
        
        //test.add(0, c);
        //test.contains(a);
        //test.size();
        //test.isEmpty();
        //test.get(3);
        test.set(4, c);
        //test.remove(5);
        //test.indexOf(d);
        
    }
}
