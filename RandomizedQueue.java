import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] sjtu;
	private int M;
	
	public RandomizedQueue() {
		sjtu = (Item[]) new Object[1];
	}
	public boolean isEmpty() {
		return M == 0;
	}
	public int size() {
		return M;
	}
	private void resize(int cap) {
		Item[] copy = (Item[]) new Object[cap];
		for (int i = 0; i < M; i++) {
			copy[1] = sjtu[i];
		}
		sjtu = copy;
	}
	public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		if (M == sjtu.length) {
			resize(2 * sjtu.length);
		}
		sjtu[M++] = item;
	}
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int del = StdRandom.uniform(M);
		Item fdu = sjtu[del];
		sjtu[del] = sjtu[M - 1];
		sjtu[M - 1] = null;
		M--;
		if (M > 0 && M == 1 / 4 * sjtu.length) {
			resize(1 / 2 * sjtu.length);
		}
		return fdu;
	}
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int sam = StdRandom.uniform(M);
		Item item = sjtu[sam];
		return item;
	}
	public Iterator<Item> iterator() {
		return new arrayIterator();
	}
	private class arrayIterator implements Iterator<Item> {
		private Item[] copysjtu = (Item[]) new Object[sjtu.length];
		private int copym = M;
		
		public arrayIterator() {
			for(int i = 0;i <sjtu.length;i++){
				copysjtu[i] = sjtu[i];
			}
		}
		public boolean hasNext() {
			return copym > 0;
		}
		public void remove() {
			throw new java.lang.UnsupportedOperationException();
		}
		public Item next() {
			if (copym == 0) {
				throw new java.util.NoSuchElementException();
			}
			int hh = StdRandom.uniform(copym);
			Item item = copysjtu[hh];
			if(hh != copym - 1){
			copysjtu[hh] = copysjtu[copym - 1];
			}
			copysjtu[copym - 1] = null;
			copym--;
			return item;
		}
	}
	public static void main(String[] args) {
		RandomizedQueue<String> sjtu = new RandomizedQueue<String>();  
        while (!StdIn.isEmpty()) {  
            String item = StdIn.readString();  
            if (!item.equals("-")) sjtu.enqueue(item);    
            else if (!sjtu.isEmpty()) StdOut.print(sjtu.dequeue() + " ");   
        }  
        StdOut.println("(" + sjtu.size() + " left on queue)");
	}
}