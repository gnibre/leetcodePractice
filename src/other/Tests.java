package other;

import java.util.ArrayList;

public class Tests {

	
	
	
	
	public void testSplit(){
		
		
		String in = "---abc-ded-Shiny-is-an-a--";
		in = "------------";
		
		String[] splitLib = in.split("-");
		
		ArrayList<String> ml = new ArrayList<String>();
		
		
		char[] ca = in.toCharArray();
		
		int l = ca.length;
		char c;
		char splitChar = '-';
		int from =0;
		int to=0;
		for(int i=0;i<l;++i){
			c = ca[i];
			if(c==splitChar){
				to = i-1;
				if(from>to){
					// no content between
					ml.add("");
				}else{
					int len = to-from+1;
					String toAdd = String.valueOf(ca,from,len);
					
					System.out.println(" from :"+from+"  to: "+to+"         str:"+toAdd);
					ml.add(toAdd);
				}
				from = i+1;
			}
		}
		
		for(int i=ml.size()-1;i>-1;--i){
			if(ml.get(i).equals("")){
				ml.remove(i);
			}
		}
		
		String[] res = new String[ml.size()];
		for(int i=0;i<res.length;++i){
			res[i] = ml.get(i);
		}
		ml = null;
		//compare
		
		if(splitLib.length==res.length){
			System.out.println("res same length :"+res.length);
			for(int i=0;i<res.length;++i){
				System.out.println(splitLib[i]+"  --  "+res[i]);
			}
		}else{
			System.out.println("res NOT same length :"+res.length+"  "+splitLib.length);
			for(int i=0;i<res.length-1;++i){
				System.out.println(splitLib[i]+"  --  "+res[i]);
			}
		}
		
	}

}
