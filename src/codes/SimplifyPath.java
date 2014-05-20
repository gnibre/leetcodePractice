package codes;

import java.io.File;
import java.util.ArrayList;

public class SimplifyPath {
	
	public void go(){
		
		String s = "/abc/...";
		String res = simplifyPath(s);
		System.out.println(" res: "+res);
	}
	
	
    public String simplifyPath(String path) {
        ArrayList<String> dirStack = new ArrayList<String>();
        String[] sep;
        if(path==null||path.length()==0){
            sep = null;
        }else{
            sep = path.split("\\/"); // this char need to add TWO SLASH!!!!!!!!!!! to be a patern.
            for(int i=0;i<sep.length;++i){
                processContent(sep[i],dirStack);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int size = dirStack.size();
        for(int i=0;i<size;++i){
            sb.append("/");
            sb.append(dirStack.remove(0)); // top dir from  bot of the array. (bot of a stack)
        }
        return sb.toString();
    }
    
    
    private void processContent(String s,ArrayList<String> al){
    	System.out.println(" process: "+s);
        if(s.length()==0){
            return ;
        }
        
        if(s.equals(".")){
            return ;
        }
        
        if(s.equals("..")){
            if(al.size()>0){
                al.remove(al.size()-1); //remove end.
                return;
            }
        }
        System.out.println(" add:  "+s);
        al.add(s); //add to end.
    }
    
}