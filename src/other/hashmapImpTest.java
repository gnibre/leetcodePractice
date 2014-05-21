package other;

import data.HashMapImp;

public class hashmapImpTest {
	
	
	public void gotest(){
		
		HashMapImp<Integer,String> myHM = new HashMapImp<Integer,String>();
		
		
		myHM.put(1, "aaa");
		myHM.put(2, "bbb");
		myHM.put(3, "ccc");
		
//		myHM.status();
		myHM.get(4);
		
//		myHM.status();
		myHM.get(2);
		
		
		
		
		HashMapImp<String,String> myHM2 = new HashMapImp<String,String>();
		
		
		myHM2.put("abc","efg");
		myHM2.put("abcd","kkk");
		myHM2.put("ok","asdf");
		myHM2.put("a","asdf");
		myHM2.put("never","ever");
		myHM2.put("ever","never");
		
		myHM2.get("abc");
		myHM2.get("abcd");
		myHM2.get("abcde");
		myHM2.get(null);
		
	}

}
