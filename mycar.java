public class mycar{
	private int[] ipt;
	private int target;
	private string rst;
	public void main(String[] args){
		int ls = ipt.length;
		int max = ls;
		int min = 0;
		while (max != min){
			int mid = (max - min)/2;
			if (ipt[mid] > ipt[mid+1] && ipt[mid] < target){
				max = mid;
			}
			if (ipt[mid] > ipt[mid + 1] && ipt[mid] > target){
				if (find(mid,max)){
					rst = "find";
					return;
				}
				else{
					max = mid;
					while (max != min){
						mid = (max + min)/2;
						if (ipt[mid] > ipt[mid+1] && ipt[mid] > target){
							max = mid;
						}
						if (ipt[mid] < ipt[mid+1] && ipt[mid] > target){
							if (find(min,mid)){
								rst = "find";
								return;
							}
							else{
								rst = "not find";
								return;
							}
						}
						else{
							min = mid;
						}
					}
				}
			}
			if (ipt[mid] < ipt[mid+1] && ipt[mid] < target){
				min = mid;
			}
			if (ipt[mit] < ipt[mid+1] && ipt[mid] > target){
				if (find(min,mid)){
					rst = "find";
					return;
				}
				else{
					min = mid;
					while (max != min){
						mid = (max + min)/2;
						if (ipt[mid] < ipt[mid+1] && ipt[mid] > target){
							min = mid;
						}
						if (ipt[mid] > ipt[mid+1] && ipt[mid] > target){
							if (find(mid,max)){
								rst = "find";
								return;
							}
							else{
								rst = "not find";
								return;
							}
						}
						else{
							max = mid;
						}
					}
				}
			}
		}
	}
	
	
	public static boolean find(int a , int b){
		int x = a;
		int d = b;
		int mean = (x+d)/2;
		if (ipt[x] < ipt[d]){
			while(x < d){
				if (ipt[mean] > target){
					d = mean;
				}
				else{
					x = mean;
				}
			}
		}
		else{
			while(x < d){
				if (ipt[mean] < target){
					d = mean;
				}
				else{
					x = mean;
				}
			}
		}
		return x == d;
	}
}
*****************************
public class mycar{
	public void main(String[] args){
		int count = 0;
		int a = 0;
		while (a<n){
			int b = a;
			while (b<n){
				int c = b;
				if (a + b + c == SUM){
					count++;
				}
			}
		}
	}
}
********************************
