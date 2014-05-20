package codes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class LRUCache {
    
	
	public void go(){
		
		LRUCache lruc = new LRUCache(3);
//		lruc.set(2, 1);
//		lruc.showEvery();
//		System.out.println(" get result : "+lruc.get(2));
//		lruc.showEvery();
//		lruc.set(3,2);
//		lruc.showEvery();
//		System.out.println(" get result : "+lruc.get(2));
//		lruc.showEvery();
//		System.out.println("-----------------------");
//		System.out.println(" get result : "+lruc.get(3));
//		lruc.showEvery();

		
//		set(1,1),set(2,2),set(3,3),set(4,4),get(4),get(3),get(2),get(1),set(5,5),get(1),get(2),get(3),get(4),get(5)
		lruc.set(1, 1);
		lruc.set(2, 2);
		lruc.set(3, 3);
		lruc.set(4, 4);
		lruc.get(4);
		lruc.get(3);
		lruc.get(2);
		lruc.get(1);
		lruc.showEvery();
		lruc.set(5, 5);
		lruc.showEvery();
		
		
		
	}
	
	private void showEvery(){
		System.out.println("====================");
//		showArrayo();
	}
	
	
	// k -> value
    HashMap<Integer,Integer> contentMap = new HashMap<Integer,Integer>();
    HashMap<Integer,Integer> keyPositionMap = new HashMap<Integer,Integer>();
    
    // time - > k
//    HashMap<Long,Integer> timeToKeyMap = new HashMap<Long,Integer>();
    
    
    // key in from of the list are least recently used.
//    ArrayList<Integer> orderedKeyArray = new ArrayList<Integer>();
    int[] orderedKeyArray;
    
    
    // get :   key -> value,  update time, reorder key
    // set : key->value,  update time, same as get
    //       key-> no value,  find the least used from the saved key-order, and maintain the order
    
    
    private int cacheCapacity;
    
    private int positionCapacity;
    private int currentKeyAddPosition = 0;
    private int leastRecentKeySearchFrom;
    public LRUCache(int capacity) {
        init(capacity);
        
    }
    
    private void init(int capacity){
    	cacheCapacity = capacity;
        positionCapacity = capacity*2; // two times.
    	contentMap = new HashMap<Integer,Integer>();
    	keyPositionMap = new HashMap<Integer,Integer>();
    	
    	// array of size cap*2 or cap*n; to reduce key position change.
//    	orderedKeyArray = new ArrayList<Integer>();
//    	System.out.println("p cap: "+positionCapacity);
    	orderedKeyArray = new int[positionCapacity];
    	currentKeyAddPosition = 0;
    }
    
    
    public int get(int key) {
    	System.out.println("function get : "+key);
    	if(contentMap.containsKey(key)){
    		// key used.
    		keyUsed(key);
    		return (int)contentMap.get(key);
    	}
        return -1;
    }
    
    public void set(int key, int value) {
    	System.out.println("function set : "+key);
    	if(contentMap.containsKey(key)){
    		contentMap.put(key, value);
    		//update timestamp.
    		keyUsed(key);
    	}else{
    		System.out.println(" insert new value : "+key);
    		// insert new key/value, we may need delete old one.
    		contentMap.put(key, value);
    		cacheAddNewKey(key);
    	}
//    	showArray();
    }
    
    // return the deleted key.
    // or we do delete it from the map in this function/ ugly.
    private int cacheAddNewKey(int k){
    	int s = keyPositionMap.size();
    	if(s<cacheCapacity){
    		//just add.
    		System.out.println("just add.  map put key: "+k+"  pos:"+currentKeyAddPosition);
    		orderedKeyArray[currentKeyAddPosition] = k; // position to add is s, value of key is k.
    		keyPositionMap.put(k, currentKeyAddPosition);
    		currentKeyAddPosition++;
    		
//    		System.out.println("  array and map size: "+orderedKeyArray.size()+"   "+keyPositionMap.size());
    	}else{
    		// most common condition, cache is full, we delete old one to add new old
    		// old one:  the first key in the orderedKeyArray that not -1
    		//new one : the one added to the end of the list.
    		
    		
    		int old = getLeastRecentKeyPosition();
    		if(old==-1){
    			System.out.println("failed...");
    			return -1;
    		}
    		
    		keyPositionMap.remove(old);
    		contentMap.remove(old);
    		orderedKeyArray[old] = -1;
    		leastRecentKeySearchFrom = old+1;
    		
    		// new key added to this position: currentKeyAddPosition
    		orderedKeyArray[currentKeyAddPosition] = k;
    		keyPositionMap.put(k, currentKeyAddPosition);
    		
    		currentKeyAddPosition++;
    		
    		if(currentKeyAddPosition==positionCapacity){
    			// position full, we need to manage it.
    			managePositionKey();
    		}
//    		showArrayo();
    	}
    	return 0;
    }
    
    private int getLeastRecentKeyPosition(){
    	for(int i=leastRecentKeySearchFrom;i<positionCapacity;++i){
    		if(orderedKeyArray[i]>-1){
    			return i;
    		}
    	}
    	return -1;
    }
    
    
    private int keyPosition(int key){
    	if(keyPositionMap.containsKey(key)){
    		return keyPositionMap.get(key);
    	}
    	return -1;
    	
    }
    
    private void keyUsed(int key){
    	int position = keyPosition(key);
    	System.out.println("keyUsed  key: "+key+"  position: "+position);
//    	showArray();
    	if(position>-1){
    		if(position!=currentKeyAddPosition-1){
    			// now you are at the end of the array, safe now.
    			orderedKeyArray[position] = -1;
    			orderedKeyArray[currentKeyAddPosition] = key;
    			currentKeyAddPosition++;
    			if(currentKeyAddPosition==positionCapacity){
        			// position full, we need to manage it.
        			managePositionKey();
        		}
//    			showArrayo();
    		}
    	}
    	
    }
    
    private void managePositionKey(){
    	
    	keyPositionMap.clear();
    	int insertIndex = 0;
    	for(int keyIndex=0;keyIndex<positionCapacity;++keyIndex){
    		if(orderedKeyArray[keyIndex]>-1){
    			if(keyIndex!=insertIndex){
    				orderedKeyArray[insertIndex] =  orderedKeyArray[keyIndex];
    				keyPositionMap.put(orderedKeyArray[insertIndex], insertIndex);
    			}
    			insertIndex++;
    		}
    	}
    	int numberOfKey = keyPositionMap.size();
    	if(insertIndex!=numberOfKey){
    		System.out.println(" algrithm failed..... not match: "+insertIndex+"     number inm ap: "+numberOfKey);
    		for(int i=0;i<6;++i){
    			if(keyPositionMap.containsKey(i)){
    				System.out.println("in map , key: "+i+" at position : "+keyPositionMap.get(i));
    			}
    		}
    	}
    	leastRecentKeySearchFrom = 0;
    	currentKeyAddPosition = insertIndex;
    	for(int i=insertIndex;i<positionCapacity;++i){
    		orderedKeyArray[i] = -1;
    	}    	
    }
   
    
    private void showArrayb(){
    	System.out.println("  list is : ");
    	for(int i=0;i<positionCapacity;++i){
    		System.out.print(", ["+i+"]:"+orderedKeyArray[i]);
		}
		System.out.println();
    	
//    	for(int i=0;i<orderedKeyArray.size();++i){
//    		System.out.print(" "+orderedKeyArray.get(i));
//    	}
//    	System.out.println("");
//    	System.out.println(" is it math with key position map?");
//    	for(int i=0;i<orderedKeyArray.size();++i){
//    		System.out.print("  "+keyPositionMap.get(orderedKeyArray.get(i)));
//    	}
//    	System.out.println("");
    }
    
    
}