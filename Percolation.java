import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.lang.Math;

public class Percolation{
	
	public int map[];
	public int ls;
	public int id[];
	public boolean connected(int h, int j){
		return root(h) == root(j);
	}
	public int root(int k){
		while (id[k] != k) k=id[k];
		return k;
	}
	public void union(int y, int u){
		int ty=root(y);
		int tu=root(u);
		id[tu]=ty;
	}
	public Percolation(int n) throws java.lang.IllegalArgumentException
	{
		if (n<1){
			throw new java.lang.IllegalArgumentException();
		}
		id=new int[n];
		for (int g=0;g<n;g++) id[g]=g;	
		int c=n*n+2;
		ls = n;
		map = new int[c];
		int s=0;
		while (s<c){
			map[s]=0;
			s++;
		}
		int f=1;
		while (f<n+1){
			union(0,f);
			f++;
		}
		int a=n*n-n+1;
		while (a<n*n+1){
			union(a,n*n+1);
			a++;
		}
	}
	public void open(int i, int j) throws java.lang.IndexOutOfBoundsException
	{
		if (i>ls || j>ls){
			throw new java.lang.IndexOutOfBoundsException();
		}
		
		int t=ls*i-ls+j;
		if (i>1){
			int s = ls*i-ls-ls+j;
			if (map[s]==1){
				union(t,s);
			}
		}
		if (i<ls){
			int x = ls*i+j;
			if (map[x]==1){
				union(t,x);
			}
		}
		if (j>1){
			int z = ls*i-ls+j-1;
			if (map[z]==1){
				union(t,z);
			}
		}
		if (j<ls){
			int y = ls*i-ls+j+1;
			if (map[y]==1){
				union(t,y);
			}
		}
		if (map[t]==0){
			map[t]=1;
			
		}
	}
	public boolean isOpen(int i, int j) throws java.lang.IndexOutOfBoundsException
	{
		if (i>ls || j>ls){
			throw new java.lang.IndexOutOfBoundsException();
		}
		
		int m=ls*i-ls+j;
		return map[m] == 1;
	}
	public boolean isFull(int i,int j) throws java.lang.IndexOutOfBoundsException
	{
		if (i>ls || j>ls){
			throw new java.lang.IndexOutOfBoundsException();
		}
		
		int m=ls*i-ls+j;
		return map[m] == 0;
	}
	public boolean percolates(){
		int h=ls*ls+1;
		return connected(0,h);
	}
}