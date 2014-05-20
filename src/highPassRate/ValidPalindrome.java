package highPassRate;

public class ValidPalindrome {
    /**
     * seems the only fun part is to ignore chars other than alphanumeric characters.
     * 
     */
    public boolean isPalindrome(String s) {
        // s = s.toLowerCase();
        
        char[] ca = s.toCharArray();
        char[] pa = new char[ca.length]; //plainAlphaCharsArray=>pa.
        int ind = 0;
        for(char c:ca){
            if('a'<=c&&c<='z'){
                pa[ind] = c;
                ind++;
            }
            //a=97, A = 65;
            if('A'<=c&&c<='Z'){
                pa[ind] = (char)(c+32);
                ind++;
            }
            if('0'<=c&&c<='9'){
                pa[ind] = c;
                ind++;
            }
        }
        
        // check if palindrome in the plain Alpha Characters Array.
        
        int tail = ind-1;
        int head = 0;
        // boolean isPalindrome = false;
        while(head<tail){
            if(pa[head]==pa[tail]){
                head++;
                tail--;
            }else{
                return false;
            }
        }
        return true;
        
    }
}
