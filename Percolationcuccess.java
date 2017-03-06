import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.*;
public class Percolation {
	private boolean[] map;
	private WeightedQuickUnionUF equf;
	private WeightedQuickUnionUF equfbw;
	private int ls;
	private int pe;
	
	public Percolation(int n) {
		if (n < 1) {
			throw new java.lang.IllegalArgumentException();
		}
		ls = n*n;
		pe = n;
		map = new boolean[ls+2];
		int a = 0;
		while (a < ls + 2) {
			map[a] = false;
			a++;
		}
		equf = new WeightedQuickUnionUF(ls + 2);
		equfbw = new WeightedQuickUnionUF(ls + 1);
		int xl = 1;
		while (xl < pe + 1) {
			equf.union(0 , xl);
			equfbw.union(0,xl);
			equf.union(ls+1 , ls - pe + xl);
			xl++;
		}
	}
	
	public void open(int s, int d) {
		if (s < 1 || s > pe || d < 1 || d > pe) {
			throw new java.lang.IndexOutOfBoundsException();
		}
		int hjh = s * pe - pe + d;
		if (map[hjh]) {
			return;
		}
		map[hjh] = true;
		int sf = hjh - pe;
		if (s > 1 && map[sf]) {
			equf.union(sf , hjh);
			equfbw.union(sf , hjh);
		}
		int xf = hjh + pe;
		if (s < pe && map[xf]) {
			equf.union(xf , hjh);
			equfbw.union(xf , hjh);
		}
		int zf = hjh - 1;
		if (d > 1 && map[zf]) {
			equf.union(zf , hjh);
			equfbw.union(zf , hjh);
		}
		int yf = hjh + 1;
		if (d < pe && map[yf]) {
			equf.union(yf , hjh);
			equfbw.union(yf , hjh);
		}
	}
	
	public boolean isOpen(int a, int b) {
		if (a < 1 || a > pe || b < 1 || b > pe){
			throw new java.lang.IndexOutOfBoundsException();
		}
		return map[a * pe - pe + b];
	}
	
	public boolean isFull(int a, int b) {
		if (a < 1 || a > pe || b < 1 || b > pe){
			throw new java.lang.IndexOutOfBoundsException();
		}
		int pls = a * pe - pe + b;
		if (pls < pe + 1) {
			return isOpen(1 , b);
		}
		else {
			return equfbw.connected(pls , 0);	
		}
		
	}
	
	public boolean percolates() {
		if (pe == 1){
			return map[1];
		}
		return equf.connected(0, ls + 1);
	}
}