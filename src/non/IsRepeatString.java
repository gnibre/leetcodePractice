package non;

import java.util.ArrayList;

public class IsRepeatString {

	
	public void go(){
		
		String s = "abcabc";
		
		
		System.out.println(s+"  res is : "+isRepeatString(s));
		s = "abaaba";
		
		System.out.println(s+"  res is : "+isRepeatString(s));
		
		s = "abcdeabcde";
		System.out.println(s+"  res is : "+isRepeatString(s));
		
		
		s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		
		System.out.println(s+"  res is : "+isRepeatString(s));
		
	}
	
	
	
	/**
	 *  see if a string s is of format substring*n;        substring.length()>1;
	 * @param input
	 * @return
	 * 
	 * 
	 * 01234567
	 * aaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
	 *  
	 * 2x6,3x6,5,
	 * 2x7,3x7,5
	 * 
	 * 
	 * axa,axa,axa,axa,axa
	 * axa ax,aaxaa,xaaxa   finally , all a
	 * 
	 * a0a1a2a3
	 * 
	 * a0a1a2a3a4a5a6a7
	 * 
	 * a8 is a1 and a2 => a1=a2;
	 * 
	 * aaabaaa aaabaaa aaabaaa baaa
	 * 
	 * len is x, y
	 *  so       if y>x, say        y=x*z+w , w>0
	 *  y+1 => in x x(w+1) = x(1)
	 *  y+2 => in x x(w+2) = x(2) ;   w not 0;       same char every where. if they both exist;;
	 * 
	 */
	private boolean isRepeatString(String input){
		int len = input.length();
		ArrayList<Integer> posList = new ArrayList<Integer>();
		
		
		int p = 2;
		char fc = input.charAt(0);
		
		//scan;
		for(;p<len;++p){
			char t = input.charAt(p);
			int size = posList.size();
			System.out.println(" pos :"+p+"  comparing possible token: "+size);
			for(int k=size-1;k>-1;--k){
				int tokenLen = posList.get(k);
				if(input.charAt(p-tokenLen)!=t){
					//shall remove this token len;
					posList.remove(k);
				}
			}
			
			if(t==fc&&len%p==0){
				// possible len is p;
//					ababac 2 fail
//					ababac 6? is 2 not fail. we will not add 6(ababab, if 6works, 2 works.), if 2 fails, we need to add 6;
					// so each time, check current existing posList before add is enough;
					boolean noAdd = false;
					for(int posLen : posList){
						if(posLen%p==0){
							noAdd = true;
							break;
						}
					}
					if(!noAdd){
						posList.add(p);
					}
				
			}
		}
		
		int posSize = posList.size();
		System.out.println(" res possible size: "+posSize);
		for(int posLen : posList){
			System.out.println("  len: "+posLen);
		}
		return (posSize>0);
	}

}
