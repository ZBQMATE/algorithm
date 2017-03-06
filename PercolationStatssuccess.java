import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.*;
public class PercolationStats {
	private double mean;
	private double stddev;
	private double conflow;
	private double confhigh;
	private int[] times;
	private int sgm;
	private double sgmt;
	
	public PercolationStats(int n, int trail) {
		if (n < 1 || trail < 1) {
			throw new java.lang.IllegalArgumentException();
		}
		times = new int[trail];
		int a = 0;
		sgm = 0;
		while (a < trail) {
		Percolation perc = new Percolation(n);
		int b = 0;
		while (!perc.percolates()) {
				int pnt = (int)(StdRandom.uniform() * n * n);
				int rows = (pnt / n + 1);
				int cols = (pnt % n + 1);
				if (!perc.isOpen(rows, cols)) {
					perc.open(rows, cols);
					b++;
				}
			}
			times[a] = b;
			sgm += b;
			a++;
		}
	}
	public double mean() {
		mean = sgm/times.length;
		return mean;
	}
	public double stddev() {
		int c = 0;
		sgmt = 0.0;
		while (c < times.length) {
			sgmt += (times[c] - mean) * (times[c] - mean);
			c++;
		}
		stddev = Math.sqrt(sgmt / times.length);
		return stddev;
	}
	public double confidenceLo() {
		conflow = mean - (1.96 * stddev) / (Math.sqrt(times.length));
		return conflow;
	}
	public double confidenceHi() {
		confhigh = mean + (1.96 * stddev ) / (Math.sqrt(times.length));
		return confhigh;
	}
	
	public static void main(String[] args) {
		int n = StdIn.readInt();
		int trail = StdIn.readInt();
		PercolationStats percst = new PercolationStats(n , trail);
		StdOut.println("mean=" + percst.mean);
		StdOut.println("stddev=" + percst.stddev);
		StdOut.println("95% confidence interval =" + percst.confhigh + percst.conflow);
	}
}