import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;
import java.io.*;
import java.util.Random;
public class PercolationStats extends Percolation
{
	public static double mean;
	public static double stddev;
	public static double confidentLo;
	public static double confidentHi;
	public int rst[];
	public int uniform(int a, int b){
		int max=b;
        int min=a;
        Random random = new Random();

        int s = random.nextInt(max)%(max-min+1) + min;
		return s;
	}
	public PercolationStats(int n,int trails) throws java.lang.IllegalArgumentException
	{
		if (!(n>0 && trails>0)){
			throw new java.lang.IllegalArgumentException();
		}
		rst=new int[trails];
		int jh=0;
		while (jh<trails){
		
		Percolation(n);
		
		int yu=0;
		while (percolates()==false){
			int b=uniform(1,n);
			int c=uniform(1,n);
			if (isFull(b,c)){
				open(b,c);
				yu++;
			}
		}
		rst[jh]=yu;
		jh++;
	}
	}
	public double mean(){
		int a =0;
		int sgm=0;
		while(a<rst.length){
			sgm += rst[a];
			a++;
		}
		mean=sgm/rst.length;
		return mean;
	}
	public double stddev(){
		int b=0;
		int he=0;
		while (b<rst.length){
			he +=(rst[b]-mean)*(rst[b]-mean);
			b++;
		}
		stddev=Math.sqrt(he/rst.length);
		return stddev;
	}
	public double confidentLo(){
		confidentLo=mean-(1.96*stddev)/Math.sqrt(rst.length);
		return confidentLo;
	}
	public double confidentHi(){
		confidentHi=mean+(1.96*stddev)/Math.sqrt(rst.length);
		return confidentHi;
	}
	public static void main(String[] args){
		PercolationStats(100,200);
		System.out.println(mean);
		System.out.println(stddev);
		System.out.println(confidentLo,confidentHi);
	}
}