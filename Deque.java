import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> {
	
	private Node first;
	private Node last;
	private int N;
	
	private class Node {
		Item item;
		Node next;
		Node previous;
	}
	
	public Deque() {
		first = null;
		last = null;
		N = 0;
	}
	public boolean isEmpty() {
		return first == null;
	}
	public int size() {
		return N;
	}
	public void addFirst(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		if(isEmpty()){
			last = first;
			first.next = null;
		}
		else{
			first.next = oldfirst;
			oldfirst.previous = first;
		}
		N++;
	}
	public void addLast(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
			last.previous = null;
		}
		else{
			last.previous = oldlast;
			oldlast.next = last;
		}
		N++;
	}
	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Item item = first.item;
		first = first.next;
		if (isEmpty()) {
			last = first = null;
		}
		else {
			first.previous = null;
		}
		N--;
		return item;
	}
	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		Item item = last.item;
		last = last.previous;
		N--;
		if(isEmpty()){
			first = last = null;
		}
		else{
			last.next = null;
		}
		return item;
	}
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() {
			return current != null;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next() {
			Item item = current.item;
			if (item == null) {
				throw new java.util.NoSuchElementException();
			}
			current = current.next;
			return item;
		}
	}
	public static void main(String[] args) {
		Deque<String> q = new Deque<String>();  
        while (!StdIn.isEmpty()) {  
            String item = StdIn.readString();  
            if (!item.equals("-") && !item.equals("#")){  
                q.addLast(item);  
            //  q.addFirst(item);  
            }  
            else if (item.equals("-")){  
                StdOut.print(q.removeFirst() + " ");  
            }  
            else if(item.equals("#")){  
                StdOut.print(q.removeLast() + " ");  
            }  
        }  
        StdOut.println("(" + q.size() + " left on queue)");  
    }  
	}
