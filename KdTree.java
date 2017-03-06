import java.util.*;
import edu.princeton.cs.algs4.*;
import java.lang.*;

public class KdTree {
	
	private int siz;
	private treeNode rootnode;
	private List<Point2D> fdu;
	private double dist;
	private double shortest;
	private treeNode overallbest;
	
	private class treeNode {
		private boolean isvtc;
		private Point2D sjtu;
		private treeNode rightHigh;
		private treeNode leftLow;
		private treeNode prenode;
		
		public treeNode(Point2D ins) {
			isvtc = true;
			sjtu = ins;
			rightHigh = null;
			leftLow = null;
			prenode = null;
		}
	}
	
	
   public KdTree() {
	   
	   siz = 0;
	   
   }// construct an empty set of points 
   
   
   public boolean isEmpty() {
	   return siz == 0;
   }// is the set empty?
   
   
   public int size() {
	   return siz;
   }// number of points in the set
   
   
   public void insert(Point2D p) {
	   if (isEmpty()) {
		   rootnode = new treeNode(p);
		   siz++;
	   }
	   else {
		   treeNode ist = new treeNode(p);
		   treeNode ttt = rootnode;
		   boolean nxt = true;
		   while (nxt) {
			   if (ttt.isvtc) {
				   if (ttt.sjtu.x() < ist.sjtu.x()) {
					   if (ttt.rightHigh == null) {
						   nxt = false;
						   ttt.rightHigh = ist;
						   break;
					   }
					   else {
							ttt = ttt.rightHigh;
					   }
				   }

				   else {
					   if (ttt.sjtu.x() == ist.sjtu.x() && ttt.sjtu.y() == ist.sjtu.y()) {
						   return;
					   }
					   if (ttt.leftLow == null) {
						   ttt.leftLow = ist;
						   nxt = false;
						   break;
					   }
					   else {
							ttt = ttt.leftLow;
					   }
					   //label same x value as smaller and go left
				   }
			   }
			   else {
				   if (ttt.sjtu.y() < ist.sjtu.y()) {
					   if (ttt.rightHigh == null) {
						   nxt = false;
						   ttt.rightHigh = ist;
						   break;
					   }
					   else {
					   ttt = ttt.rightHigh;
					   }
				   }
				   else {
					   if (ttt.sjtu.x() == ist.sjtu.x() && ttt.sjtu.y() == ist.sjtu.y()) {
						   return;
					   }
					   if (ttt.leftLow == null) {
						   nxt = false;
						   ttt.leftLow = ist;
						   break;
					   }
					   else {
							ttt = ttt.leftLow;
					   }
					   //label same y value as smaller and go low
				   }
			   }
		   }
		   if (ttt.isvtc) {
			   ist.isvtc = false;
			   ist.prenode = ttt;
			   siz++;
		   }
		   else {
			   ist.isvtc = true;
			   ist.prenode = ttt;
			   siz++;
		   }
	   }
   }// add the point to the set (if it is not already in the set)
   
   
   
   public boolean contains(Point2D p) {
	   Point2D exp = p;
	   treeNode hhh = rootnode;
	   boolean tr = true;
	   while (tr) {
		   if (hhh.isvtc) {
			   if (hhh.sjtu.x() < exp.x()) {
				   if (hhh.rightHigh == null) {
					   return false;
				   }
				   else {
					   hhh = hhh.rightHigh;
				   }
			   }
			   else if (hhh.sjtu.x() == exp.x()) {
				   if (hhh.sjtu.y() == exp.y()) {
					   return true;
				   }
				   else if (hhh.leftLow == null) {
					   return false;
				   }
				   else {
					   hhh = hhh.leftLow;
				   }
			   }
			   else {
				   if (hhh.leftLow == null) {
					   return false;
				   }
				   else {
					   hhh = hhh.leftLow;
				   }
			   }
		   }
		   else {
			   if (hhh.sjtu.y() < exp.y()) {
				   if (hhh.rightHigh == null) {
					   return false;
				   }
				   else {
					   hhh = hhh.rightHigh;
				   }
			   }
			   else if (hhh.sjtu.y() == exp.y()) {
				   if (hhh.sjtu.x() == exp.x()) {
					   return true;
				   }
				   else if (hhh.leftLow == null) {
					   return false;
				   }
				   else {
					   hhh = hhh.leftLow;
				   }
			   }
			   else {
				   if (hhh.leftLow == null) {
					   return false;
				   }
				   else {
					   hhh = hhh.leftLow;
				   }
			   }
		   }
	   }
	   return false;
   }// does the set contain point p?
   
   
   private void trv(treeNode nd) {
	   treeNode nod = nd;
	   nod.sjtu.draw();
	   if (nod.leftLow != null) {
		   trv(nod.leftLow);
	   }
	   if (nod.rightHigh != null) {
		   trv(nod.rightHigh);
	   }
   }

   
   public void draw() {
	   treeNode startpoint = rootnode;
	   trv(startpoint);
   }// draw all points to standard draw
   
   private void thereis(RectHV hk, treeNode kx) {
	   RectHV jjj = hk;
	   treeNode kkk = kx;
	   if (jjj.contains(kkk.sjtu)) {
		   fdu.add(kkk.sjtu);
	   }
	   if (kkk.isvtc) {
		   if (kkk.sjtu.x() >= jjj.xmax()) {
			   if (kkk.leftLow != null) {
				   thereis(jjj, kkk.leftLow);
			   }
		   }
		   else if (kkk.sjtu.x() < jjj.xmin()) {
			   if (kkk.rightHigh != null) {
				   thereis(jjj, kkk.rightHigh);
			   }
		   }
		   else {
			   if (kkk.rightHigh != null) {
				   thereis(jjj, kkk.rightHigh);
			   }
			   if (kkk.leftLow != null) {
				   thereis(jjj, kkk.leftLow);
			   }
		   }
	   }
	   if (!kkk.isvtc) {
		   if (kkk.sjtu.y() >= jjj.ymax()) {
			   if (kkk.leftLow != null) {
				   thereis(jjj, kkk.leftLow);
			   }
		   }
		   else if (kkk.sjtu.y() < jjj.ymin()) {
			   if (kkk.rightHigh != null) {
				   thereis(jjj, kkk.rightHigh);
			   }
		   }
		   else {
			   if (kkk.rightHigh != null) {
				   thereis(jjj, kkk.rightHigh);
			   }
			   if (kkk.leftLow != null) {
				   thereis(jjj, kkk.leftLow);
			   }
		   }
	   }
   }
   
   public Iterable<Point2D> range(RectHV rect) {
	   RectHV fld = rect;
	   treeNode sch = rootnode;
	   fdu = new ArrayList<Point2D>();
	   fdu.clear();
	   thereis(fld, sch);
	   return fdu;
   }// all points that are inside the rectangle 
   
   private treeNode findit(Point2D mb, treeNode bb) {
	   if (mb == null || bb == nuul) {
		   return;
	   }
	   Point2D tgrt = mb;
	   treeNode bst = bb;
	   boolean cdt = true;
	   while (cdt) {
		   if (bst.isvtc) {
			   if (tgrt.x() < bst.sjtu.x()) {
				   if (bst.leftLow == null) {
					   break;
				   }
				   else {
					   bst = bst.leftLow;
				   }
				   
			   }
			   else {
				   if (bst.rightHigh == null) {
					   break;
				   }
				   else {
					   bst = bst.rightHigh;
				   }
				   
			   }
		   }
		   else {
			   if (tgrt.y() < bst.sjtu.y()) {
				   if (bst.leftLow == null) {
					   break;
				   }
				   else {
					   bst = bst.leftLow;
				   }
			   }
			   else {
				   if (bst.rightHigh == null) {
					   break;
				   }
				   else {
					   bst = bst.rightHigh;
				   }
			   }
		   }
	   }
	   
	   if (bst.sjtu.distanceTo(tgrt) < dist) {
		   dist = bst.sjtu.distanceTo(tgrt);
		   overallbest = bst;
	   }
	   
	   
	   
	   //find the leaf node
	   treeNode cur = bst;
	   treeNode pris = cur.prenode;
	   while (pris != null) {
		   
		   if (pris.isvtc) {
			   double v = Math.abs(tgrt.x() - pris.sjtu.x());
			   if (v <= dist) {
				   if (cur.sjtu.equals(pris.leftLow.sjtu)) {
					   findit(tgrt, pris.rightHigh);
				   }
				   else {
					   findit(tgrt, pris.leftLow);
				   }
			   }
		   }
		   else {
			   double h = Math.abs(tgrt.y() - pris.sjtu.y());
			   if (h <= dist) {
				  if (cur.sjtu.equals(pris.leftLow.sjtu)) {
					   findit(tgrt, pris.rightHigh);
				   }
				   else {
					   findit(tgrt, pris.leftLow);
				   } 
			   }
		   }
		   cur = pris;
		   pris = cur.prenode;
	   }
	   
	   return overallbest;
   }
   
   public Point2D nearest(Point2D p) {
	   Point2D tgrti = p;
	   treeNode ini = rootnode;
	   dist = rootnode.sjtu.distanceTo(tgrti);
	   overallbest = rootnode;
	   
	   treeNode hjh = findit(tgrti, ini);
	   return hjh.sjtu;
   }// a nearest neighbor in the set to point p; null if the set is empty 

   public static void main(String[] args) {
	   
   }// unit testing of the methods (optional) 
}