package codes;

import java.util.ArrayList;

public class TextJustification {
	
	public void go(){
		
//		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		String[] words = {""};
		int L = 0;
		
		ArrayList<String> res = fullJustify(words,L);
		
		for(int i=0;i<res.size();++i){
			System.out.println(res.get(i));
		}
		
	}
	
    public ArrayList<String> fullJustify(String[] words, int L) {
        //1: count words, and group them up for each line
        ArrayList<ArrayList<String>> lines = getEachLine(words,L);
        
        ArrayList<String> res = new ArrayList<String>();
        for(int i=0;i<lines.size()-1;++i){
            res.add(formatLine(lines.get(i),false,L)); // not last line.
        }
        if(lines.size()>0){
            res.add(formatLine(lines.get(lines.size()-1),true,L)); //last line.
        }
        //2: for eachline, print them as requested.
        return res;
    }
    
    
    private ArrayList<ArrayList<String>> getEachLine(String[] words,int L){
    	System.out.println("geteachline: "+words.length);
        int wc = words.length;
        int index =0; // the word index we are focusing to add.
        int count = 0;
        int lenSum =0;
        int lenWillBe = 0;
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        ArrayList<String> oneLine;
        while(index<wc){
        	System.out.println(" index : "+index);
            oneLine = new ArrayList<String>();
            lenSum = words[index].length();  //at least add one word. as guaranteed from input.
            oneLine.add(words[index]);
            index++;
            while(index<wc&&lenSum<L-1){ // why L-1?  space and another word takes at least space of 2. 
                lenWillBe = lenSum+1+words[index].length();
                if(lenWillBe<=L){
                    //add it.
                    lenSum = lenWillBe;
                    oneLine.add(words[index]);
                    index++;
                }else{
                	break;
                }
            }
            //oneLine got
            res.add(oneLine);
        }
        
        return res;
    }
    
    
    private String formatLine(ArrayList<String> al,boolean isLastLine,int L){
    	System.out.println(" format line:   "+al.get(0));
        int wc = al.size();
        
        if(wc==1||isLastLine){
            return formatLineLeftJustified(al,L);
        }
        
        
        int lenSum =0;
        for(int i=0;i<wc;++i){
            lenSum+=al.get(i).length();
        }
        int spaceLeft = L-lenSum;
        int baseSpace = spaceLeft/(wc-1); // space between words is this much. if baseSpace is 2, the space between words can be 2 or 3. 
        int noBaseCount =spaceLeft-(baseSpace*(wc-1)); // count for space between words is (baseSpace+1)

        // go format it.
        int spaceCount = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<al.size()-1;++i){
            sb.append(al.get(i));
            if(i<noBaseCount){
            	spaceCount = baseSpace+1;
            }else{
            	spaceCount = baseSpace;
            }
            for(int j=0;j<spaceCount;++j){
            	sb.append(" ");
            }
        }
        sb.append(al.get(al.size()-1));//last line.
        return sb.toString();
        
    }
    
    private String formatLineLeftJustified(ArrayList<String> al,int L){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<al.size()-1;++i){
            sb.append(al.get(i));
            sb.append(" ");
        }
        sb.append(al.get(al.size()-1));
        while(sb.length()<L){
        	sb.append(" ");
        }
        return sb.toString();
    }
    
    
}