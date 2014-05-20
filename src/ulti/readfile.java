package ulti;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readfile {


	
	
	private static String dir = "D:\\work\\code\\EclipseWS\\Leetcode\\files\\";
	private static String defaultFile = "input.txt";
	
	
	public static int[] readIntArrayFromFile(String fileName){
		String path = null;
		if(fileName==null){
			path = dir+File.separator+defaultFile;
		}else{
			path = dir+File.separator+fileName;
		}
		ArrayList<Integer> al = new ArrayList<Integer>();
		File f = new File(path);
		
//		FileReader fr = new FileReader(f);
//		FileInputStream fis = new FileInputStream(f);
//		BufferedInputStream bis = new BufferedInputStream(f);
//		BufferedReader br = new BufferedReader(f);
		
		System.out.println(" scan begin");
		try {
			Scanner s = new Scanner(f);
			s.useDelimiter(",");
			int i = 0;
			while(s.hasNextInt()){
				al.add(s.nextInt());
//				System.out.print(" "+s.nextInt());
//				if(i==60){
//					System.out.println("");
//					i=0;
//				}
//				i++;
			}
			System.out.println("");
			System.out.println(" size of file : "+al.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		int[] ret = new int[al.size()];
		for(int i=0;i<al.size();++i){
			ret[i] = al.get(i);
			System.out.print(" "+ret[i]);
			if(i%60==0){
				System.out.println("");
			}
		}
		return ret;
	}
	
	

}
