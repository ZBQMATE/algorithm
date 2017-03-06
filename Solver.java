import java.util.*;
import edu.princeton.cs.algs4.MinPQ;
import java.lang.*;

public class Solver {
	
	private List<Board> trvl;
	private stepNode finalpoint;
	
	private class stepNode {
		
		private int moves;
		private Board sjtu;
		private stepNode prevsn;
		
		private stepNode(Board ins) {
			moves = 0;
			sjtu = ins;
			prevsn = null;
		}
	}
	
	
	private class pod implements Comparator<stepNode> {
		public int compare(stepNode sna, stepNode snb) {
			int astara = sna.sjtu.manhattan() + sna.moves;
			int astarb = snb.sjtu.manhattan() + snb.moves;
			if (astara < astarb) {
				return -1;
			}
			else if (astara > astarb) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
	
	
    public Solver(Board initial) {
		pod poda = new pod();
		
		pod podacp = new pod();
		
		MinPQ<stepNode> qua = new MinPQ<stepNode>(poda);
		
		MinPQ<stepNode> quacp = new MinPQ<stepNode>(podacp);
		
		stepNode nda = new stepNode(initial);
		
		stepNode ndacp = new stepNode(initial.twin());
		
		qua.insert(nda);
		
		quacp.insert(ndacp);
		
		stepNode minnode = qua.delMin();
		
		stepNode minnodecp = quacp.delMin();
		
		while (!minnode.sjtu.isGoal() && !minnodecp.sjtu.isGoal()) {
			
			for (Board zju : minnode.sjtu.neighbors()) {
				if (minnode.prevsn == null || !zju.equals(minnode.prevsn.sjtu)) {
					stepNode nxt = new stepNode(zju);
					nxt.moves = minnode.moves + 1;
					nxt.prevsn = minnode;
					qua.insert(nxt);
				}
			}
			
			for (Board zjucp : minnodecp.sjtu.neighbors()) {
				if (minnodecp.prevsn == null || !zjucp.equals(minnodecp.prevsn.sjtu)) {
					stepNode nxtcp = new stepNode(zjucp);
					nxtcp.moves = minnodecp.moves + 1;
					nxtcp.prevsn = minnodecp;
					quacp.insert(nxtcp);
				}
			}
			
			minnode = qua.delMin();
			
			minnodecp = quacp.delMin();
			
		}
		if (minnode.sjtu.isGoal()) {
			finalpoint = minnode;
		}
		else {
			finalpoint = null;
		}
	}
	
	
    public boolean isSolvable() {
		return finalpoint != null;
	}
	
	
    public int moves() {
		if (finalpoint == null) {
			return -1;
		}
		else {
			return finalpoint.moves;
		}
	}
	
	
    public Iterable<Board> solution() {
		if (finalpoint == null) {
			return null;
		}
		Stack<Board> pth = new Stack<>();
		stepNode fdp = finalpoint;
		pth.push(fdp.sjtu);
		while (fdp.prevsn != null) {
			pth.push(fdp.prevsn.sjtu);
			fdp = fdp.prevsn;
		}
		return pth;
	}
	
	
    public static void main(String[] args) {
		
	}
}