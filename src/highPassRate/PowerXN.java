package highPassRate;


public class PowerXN {
    
	public void go(){
		
		double in = 1.00000;
		int n  = -2147483648;
		
		
		in = 34.00515;
		n = -3;
		
	
		double res = pow(in,n);
		
		System.out.println(" res : "+res);
		
	}
	
    /***
     * oj's TLE;  
     * sure, improve it;
     * 
     * x^n =  x^a  * x^ b * x^c  if (a+b+c...) = n;
     * 
     * (x^a)^3 = x^(3a);
     * 
     * 
     * for example .;   n = a*b+c; 
     * 
     * x^n =   (x^a)^b * x^c => f(x,n)= f(f(x,a),b)*f(c);
     * 
     * //how to devide n? try decimal; easy to understand;
     * 
     * say  n = 251347 ;
     * x^n = f(x,n) = f(x,251347) = f(x, 2*10^5+5*10^4+1*10^3+3*10^2+4*10+7)  = f(x,2*10^5) * f(x,5*10^4) *f(x,1*10^3) *f(,3*10^2) *f(x,40) * f(x,7)
     * =  f(f(x,2),100000)  * f(f(x,5),10000) *f(f(x,1),1000) * f(f(x,3),100)   
     * fffff(f(x,2),10)10)10)10).. is the result; too many 10;
     * 
     * 
     * best search, of cause , is binary ;
     * 
     * f(x,n) = f(x,  a^2+b) = f(x,a*a) * f(x,b)  =  f(f(x,a),a)*f(x,b);
     * 
     */
    public double pow(double x, int n) {
        System.out.println(" go : "+x+"    N:"+n);
    	if(n==0){
    		return 1;
    	}
    	if(n<0){
    		
//    		!!!!! i hate int;
    		// if n is -2147483648; 
    		//  -n is still -2147483648 , cause max int is 2147483647;
    		if(n<-10){
    			int n1 = -10; 
    			int n2 = n-n1;
    			double res = pow(x,-n1)*pow(x,-n2);
    			return 1/res;
    		}else{
    		    return 1/pow(x,-n);
    		}
    	}
        
        if(n<11){
            double res = x;
            while(n>1){
                res*=x;
                n--;
            }
            return res;
        }
        
        // n maybe very large;
        
        // devide;
        int sq = (int)Math.sqrt(n);
        int rest = n-sq*sq;
        return pow(pow(x,sq),sq)*pow(x,rest);
        
    }
}
