import java.io.*;
public class Qtwo{
	public static int id[];
	public static void setid(){
		id=new int[10];
		for (int g=0;g<10;g++) id[g]=g;			
	}
	
	public static int root(int k){
		while (id[k] != k) k=id[k];
		return k;
	}
	public static void union(int y, int u){
		int ty=root(y);
		int tu=root(u);
		id[tu]=ty;
		System.out.println("union succeed");
	}
	public static boolean connected(int h, int j){
		return root(h) == root(j);
	}
	public static void find(int i){
		int max=0;
		int q=0;
		while (q<10){
			if (connected(q,i)){
				if (q<i){
					max=i;
					q++;
				}else{
					max=q;
					q++;
				}
			}else{
				q++;
			}
		}
		System.out.println(max);
	}
	public static void main(String[] args){
		setid();
		union(1,2);
		union(6,9);
		union(1,6);
		find(1);
	}
}