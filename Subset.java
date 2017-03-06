import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Subset {
	public static void main(String[] args) {
		RandomizedQueue<String> sjtu = new RandomizedQueue<String>();  
        int k = Integer.valueOf(args[0]);  
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();  
            sjtu.enqueue(item);  
        }  
        while (k > 0) {
            StdOut.println(sjtu.dequeue());
            k--;
        }
	}
}