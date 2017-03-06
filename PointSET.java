import java.util.*;
import edu.princeton.cs.algs4.*;
import java.lang.*;

public class PointSET {
	
	private int sz;
	private TreeSet<Point2D> tst;
	
	private class cptr implements Comparator<Point2D> {
	   public int compare(Point2D ad, Point2D bd) {
		   return ad.compareTo(bd);
	   }
   }
	
   public PointSET() {
	   
	   cptr aaa = new cptr();
	   
	   sz = 0;
	   tst = new TreeSet<Point2D>(aaa);
   }// construct an empty set of points
   
   
   public boolean isEmpty() {
	   return sz == 0;
   }// is the set empty?
   
   
   public int size() {
	   return sz;
   }// number of points in the set 
   
   
   public void insert(Point2D p) {
	   if (p == null) {
		   throw new java.lang.NullPointerException();
	   }
	   Point2D ins = p;
	   if (tst.add(ins)) {
		   sz++;
	   }
   }// add the point to the set (if it is not already in the set)
   
   
   public boolean contains(Point2D p) {
	   Point2D ps = p;
	   return tst.contains(ps);
   }// does the set contain point p? 
   
   
   public void draw() {
	   Iterator<Point2D> sjtu = tst.iterator();
	   while (sjtu.hasNext()) {
		   Point2D sss = sjtu.next();
		   sss.draw();
	   }
   }// draw all points to standard draw
   
   
   public Iterable<Point2D> range(RectHV rect) {
	   List<Point2D> fdu = new ArrayList<>();
	   Iterator<Point2D> zju = tst.iterator();
	   while (zju.hasNext()) {
		   Point2D zkz = zju.next();
		   if (rect.contains(zkz)) {
			   fdu.add(zkz);
		   }
	   }
	   return fdu;
   }// all points that are inside the rectangle
   
   
   public Point2D nearest(Point2D p) {
	   Point2D pc = p;
	   Point2D nsx;
	   Iterator<Point2D> zju = tst.iterator();
	   nsx = null;
	   if (zju.hasNext()) {
		   nsx = zju.next();
	   }
	   while (zju.hasNext()) {
		   Point2D mm = zju.next();
		   if (!mm.equals(pc)) {
			   if (mm.distanceTo(pc) < nsx.distanceTo(pc)) {
				   nsx = mm;
			   }
		   }
		   else {
			   return mm;
		   }
	   }
	   return nsx;
   }// a nearest neighbor in the set to point p; null if the set is empty 

   
   public static void main(String[] args) {
	   
   }// unit testing of the methods (optional) 
}