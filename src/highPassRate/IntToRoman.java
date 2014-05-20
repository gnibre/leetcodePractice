package highPassRate;

public class IntToRoman {
    public String intToRoman(int num) {
        // String[] nToR = {"","I","II","III","IV","V","VI","VII","VIII","IIX","IX","X"};
        //                1   10  100 1000
        String[] rBase = { "I","X","C","M"};
        String[] rFives = {"V","L","D"};
        //                 5   50  500
        
        StringBuilder romanBuilder = new StringBuilder();
        
        // case 1xxx ~ 3999
        if(num>999){
            int b = num/1000;
            for(int i=0;i<b;++i){
                romanBuilder.append("M");
            }
            int rest = num-b*1000;
            romanBuilder.append(intToRoman(rest));
            return romanBuilder.toString();
        }
        
        // case 1xx ~ 999 ;  C and D.    borrow M   rBase[2]
        // case 10 ~ 99 ;   X and L    borrow C     rBase[1]
        // case 1~9      ; I and V    borrow X;     rBase[0]
        
        for( int time = 2; time>-1;--time){
            int threshold = 1;
            for( int i=0;i<time;++i){
                threshold *=10;
            }
            
            // for example,  time =2; threshold = 100;  num is 100~999;  use "C","D"
            // when time = 0; threshold =1; for 1 to 9; use I and V
            if(num>=threshold){
                int b = num/threshold; // get first number;
                if(b<4){
                     for(int i=0;i<b;++i){
                        romanBuilder.append(rBase[time]);  // "C" or "X" or "I"
                     }
                }else if(b<5){
                    // just ==4; 
                    romanBuilder.append(rBase[time]);
                    romanBuilder.append(rFives[time]);   // IV = 4,  XL = 40,  CD=400
                }else if(b<9){
                    // 5,6,7,8
                    romanBuilder.append(rFives[time]);     //  "V", "L", "D" for 5,50,500
                    for(int i=5;i<b;++i){
                        romanBuilder.append(rBase[time]);
                    }
                }else{
                    // 9
                    romanBuilder.append(rBase[time]);
                    romanBuilder.append(rBase[time+1]); // borrow from higher
                }
            
                int rest = num-b*threshold;
                
                num = rest;
                // romanBuilder.append(intToRoman(rest));
                // return romanbuilder.toString();
            }
        }
        return romanBuilder.toString();
    }
    
    
    
}