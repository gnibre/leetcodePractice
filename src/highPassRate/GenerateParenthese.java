package highPassRate;

import java.util.ArrayList;

import ulti.Printer;

public class GenerateParenthese {
    
	public void go(){
		
		int n= 3;
		ArrayList<String> res = generateParenthesis(3);
		Printer.pArray(res);
	}
    
    /***
     * dp? 
     * 1 stack empty,  can only add left parenthese;
     * 2 stack not empty , can add left or right;
     * 3 stack have n parenthese, can only add right;
     * count all the cases;
     * 
     * f(x,y) is count of cases, that x is left parenthese count in the stack;y is right count in the stack;
     *  f(0,y) = f(1,y) , y must also be 0; init case;
     *  f(x,y) = f(x+1,y) +f(x,y+1); normal case;
     * so f(n, ?)=1;  cause can only add right
     *
     * res is f(0,0);
     *  0,0
     *  10 01
     *  20 11 02
     *  30 21 12 03  ;   if we build it up from bot cases,   we need  f(n,0)-f(n,n) till (0,0)  left is case for n=3;
     *     31 22 13 
     *        32 23 
     *           33
     * 
     * apply rule x must <=y;  x<=y ; we get
     * 
     * 0,0
     * 10 _
     * 20 11 _
     * 30 21 _
     *    31 22 _
     *       32 _
     *          33
     * (00)         
     *          
     * 33: ""
     * 32: )
     * 31 ))
     * 30 )))
     * 
     * 23 _
     * 22 (32+23_): (), 
     *         
     *          
     * oshit, we need not only count; we have to save a ArrayList<String> at each res[x,y];
     * 
     * ArrayList<String> al = res[x][y] means that , know we have x left and y right parenthese already in the "pre" string; how many different suffix can that be ( not only count, but also list all)
     * 
     */ 
    public ArrayList<String> generateParenthesis(int n) {
        
       //build from bottom up;
       // use only o(n) space to save res;
       
       // is this right... ArrayList<STring> array of size n;
       ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>(n+1); //add boundary;
       
       ArrayList<String> al;
       // build f(n,i) , bottom row;  n left, i right; already; choice only 1, add all the right ps needed;
       
       for(int i=0;i<n;++i){
           al = new ArrayList<String>();
           String s = "";
           for(int count=i;count<n;++count){
        	   s+=")";
           }
           al.add(s);
           res.add(al);
       }
       res.add(new ArrayList<String>());
       
       Printer.pArrayArray(res);
       
       
       while(res.size()>1){
    	   int size = res.size();
    	   res.set(size-1,new ArrayList<String>());
    	   
    	   for(int i=size-2;i>-1;--i){
    		   ArrayList<String> nr = new ArrayList<String>();
    		   ArrayList<String> old = res.get(i);
    		   String s;
    		   for(String o:old){
                   s = "("+o;
                   nr.add(s);
               }
    		   ArrayList<String> right = res.get(i+1);
    		   for(String r:right){
                   s = ")"+r;
                   nr.add(s);
               }
    		   res.set(i, nr);
    	   }
    	   res.remove(size-1);
       }
       
       return res.get(0);
    }
}