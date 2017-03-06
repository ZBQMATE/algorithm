import java.io.*
public class Qone {
	
	private int id[];
	private int sz[];
	public setid(){
		id =new int id[n];
		sz =new int sz[n];
		int i = 0;
		while (i<n){
			id[i]=i;
			sz[i]=1;
			i++;
		}
	}
	
	public root(int s ){
		while(s != id[s]){
			s = id[s];
		}
		return s;
	}
	
	public void union(int p, int q, string e){
		int h = root(p);
		int j = root(q);
		if (h == j){
			return;
		}
		if (sz[h]<sz[j]){
			id[h]=j;
			sz[j] +=sz[h];
			int max = sz[j];
		}else{
			id[j]=h;
			sz[h] +=sz[j];
			int max = sz[h];
		}
		if (max=n){
			system.out.println("compeleted!" + e);
		}
	}
	
	public static void main(String[] args){
		
		setid();
		
		FileInputStream xxx = new FileInputStream("abc.log");
		BufferedReader bufferedreader = new BufferedReader(read);
		string st=null;
		while((st=bufferedreader.readLine())!=null){
			string[] parts = string.split(" ");
			string tme = parts[0];
			int tmp = parts[1];
			int tmq = parts[2];
			
			union(tmp,tmq,tme);
			
		}
		
	}
}