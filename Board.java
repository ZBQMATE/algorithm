import java.util.*;
import edu.princeton.cs.algs4.MinPQ;
import java.lang.*;

public class Board {
	
	private int n;
	private int[][] sjtu;
	private int[][] nbsjtu;
	
	public Board(int[][] blocks) {
		int lsa = blocks.length;
		int lsb = blocks[0].length;
		sjtu = new int[lsa][lsb];
		n = lsa;
		for (int wa = 0; wa < lsa; wa++) {
			for (int wb = 0; wb < lsb; wb++) {
				sjtu[wa][wb] = blocks[wa][wb];
			}
		}
	}
	
	
	public int dimension() {
		return sjtu.length;
	}
	
	
	public int hamming() {
		int incc = 0;
		for (int ea = 0; ea < n; ea++) {
			for (int eb = 0; eb < n; eb++) {
				if (sjtu[ea][eb] != ea * n + eb + 1 && sjtu[ea][eb] != 0) {
					incc++;
				}
			}
		}
		return incc;
	}
	
	
	public int manhattan() {
		int mht = 0;
		for (int fa = 0; fa < sjtu.length; fa++) {
			for (int fb = 0; fb < sjtu[fa].length; fb++) {
				if (sjtu[fa][fb] != fa * sjtu.length + fb + 1 && sjtu[fa][fb] != 0) {
					int tgt = sjtu[fa][fb];
					tgt = tgt - 1;
					int tgtrow = tgt / dimension();
					int tgtcol = tgt % dimension();
					int sgm = Math.abs(tgtrow - fa) + Math.abs(tgtcol - fb);
					mht = mht + sgm;
				}
			}
		}
		return mht;
	}
	
	
	public boolean isGoal() {
		return hamming() == 0;
	}
	
	
	public Board twin() {
		if (n <= 1) {
			return null;
		}
		Board fdu;
		fdu = new Board(sjtu);
		if (sjtu[0][0] == 0) {
			int tmpa = fdu.sjtu[0][1];
			fdu.sjtu[0][1] = fdu.sjtu[1][0];
			fdu.sjtu[1][0] = tmpa;
		}
		else if (sjtu[0][1] == 0) {
			int tmpb = fdu.sjtu[0][0];
			fdu.sjtu[0][0] = fdu.sjtu[1][0];
			fdu.sjtu[1][0] = tmpb;
		}
		else {
			int tmpc = fdu.sjtu[0][0];
			fdu.sjtu[0][0] = fdu.sjtu[0][1];
			fdu.sjtu[0][1] = tmpc;
		}
		return fdu;
	}
	
	
	public boolean equals(Object y) {
		if (y == null) {
			return false;
		}
		if (getClass() != y.getClass()) {
			return false;
		}
		if (this == y) {
			return true;
		}
		Board xl = (Board) y;
		if (this.sjtu.length != xl.sjtu.length) {
			return false;
		}
		for (int va = 0; va < sjtu.length; va++) {
			if (this.sjtu[va].length != xl.sjtu[va].length) {
				return false;
			}
			for (int vb = 0; vb < sjtu.length; vb++) {
				if (this.sjtu[va][vb] != xl.sjtu[va][vb]) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	public Iterable<Board> neighbors() {
		List<Board> newnb = new ArrayList<>();
		nbsjtu = new int[sjtu.length][sjtu[0].length];
		int xlr = 0;
		int xlc = 0;
		int lr = 0;
		int lc = 0;
		while (lr < dimension()) {
			lc = 0;
			
			while (lc < dimension()) {
				
				if (sjtu[lr][lc] == 0) {
					//break;
					xlr = lr;
					xlc = lc;
				}
				lc++;
			}
			lr++;
		}

		if (xlr > 0) {
			for (int la = 0; la < sjtu.length; la++) {
				for (int lb = 0; lb < sjtu[la].length; lb++) {
					nbsjtu[la][lb] = sjtu[la][lb];
				}
			}
			nbsjtu[xlr][xlc] = nbsjtu[xlr - 1][xlc];
			nbsjtu[xlr - 1][xlc] = 0;
			
			newnb.add(new Board(nbsjtu));
		}
		if (xlr < dimension() - 1) {
			for (int la = 0; la < sjtu.length; la++) {
				for (int lb = 0; lb < sjtu[la].length; lb++) {
					nbsjtu[la][lb] = sjtu[la][lb];
				}
			}
			nbsjtu[xlr][xlc] = nbsjtu[xlr + 1][xlc];
			nbsjtu[xlr + 1][xlc] = 0;
			
			newnb.add(new Board(nbsjtu));
		}
		if (xlc > 0) {
			for (int la = 0; la < sjtu.length; la++) {
				for (int lb = 0; lb < sjtu[la].length; lb++) {
					nbsjtu[la][lb] = sjtu[la][lb];
				}
			}
			nbsjtu[xlr][xlc] = nbsjtu[xlr][xlc - 1];
			nbsjtu[xlr][xlc - 1] = 0;
			
			newnb.add(new Board(nbsjtu));
		}
		if (xlc < dimension() - 1) {
			for (int la = 0; la < sjtu.length; la++) {
				for (int lb = 0; lb < sjtu[la].length; lb++) {
					nbsjtu[la][lb] = sjtu[la][lb];
				}
			}
			nbsjtu[xlr][xlc] = nbsjtu[xlr][xlc + 1];
			nbsjtu[xlr][xlc + 1] = 0;
			
			newnb.add(new Board(nbsjtu));
		}
		return newnb;
	}
	
	
	public String toString() {
		StringBuilder boardStringBuilder = new StringBuilder(sjtu.length + "\n");

        for (int[] row : sjtu) {
            for (int block : row) {
                boardStringBuilder.append(" ");
                boardStringBuilder.append(block);
            }
            boardStringBuilder.append("\n");
        }

		return boardStringBuilder.toString();
	}
	public static void main(String[] args) {}
}