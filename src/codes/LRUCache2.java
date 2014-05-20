package codes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class LRUCache2 {
    
	
	public void go(){
		
		LRUCache2 lruc = new LRUCache2(3);		
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
		lruc.showSize();
		
		
	}
	
	private void showSize(){
		System.out.println("contentMap size: "+contentMap.size());
		System.out.println("keyToTimeStampMap size: "+keyToTimeStampMap.size());
		System.out.println("timestampToKeyMap size: "+timestampToKeyMap.size());
	}
	private void showEvery(){
		for(int i=1;i<6;++i){
			if(keyToTimeStampMap.containsKey(i)){
				System.out.println("  key: "+i+"  last op time: "+keyToTimeStampMap.get(i));
			}
		}
	}
	
	
	// k -> value
    HashMap<Integer,Integer> contentMap = new HashMap<Integer,Integer>();
    HashMap<Integer,Integer> keyToTimeStampMap = new HashMap<Integer,Integer>();
    
    // last operation timestamp ->key
    // time - > k
    HashMap<Integer,Integer> timestampToKeyMap = new HashMap<Integer,Integer>();
    
    
    private int cacheCapacity;
    private int timestamp = 1;
    private int lastDeleteTimestamp = 0;
    
    public LRUCache2(int capacity) {
        init(capacity);
    }
    
    private void init(int capacity){
    	cacheCapacity = capacity;
    	contentMap = new HashMap<Integer,Integer>();
    	keyToTimeStampMap = new HashMap<Integer,Integer>();
    	timestampToKeyMap = new HashMap<Integer,Integer>();
    }
    
    
    public int get(int key) {
    	System.out.println("function get : "+key);
    	if(contentMap.containsKey(key)){
    		timestamp++;
    		// key used.
    		keyUsed(key);
    		return (int)contentMap.get(key);
    	}
        return -1;
    }
    
    public void set(int key, int value) {
    	timestamp++;
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
    }
    
    // return the deleted key.
    // or we do delete it from the map in this function/ ugly.
    private int cacheAddNewKey(int k){
    	int s = keyToTimeStampMap.size();
    	if(s<cacheCapacity){
    		//just add.
    		keyToTimeStampMap.put(k, timestamp);
    		timestampToKeyMap.put(timestamp, k);
    	}else{
    		// most common condition, cache is full, we delete old one to add new old
    		System.out.println("lastDeleteTimestamp "+lastDeleteTimestamp);
    		int toDeletekey = getLeastRecentKey();
    		System.out.println("lastDeleteTimestamp "+lastDeleteTimestamp);
    		if(toDeletekey!=k){
    			keyToTimeStampMap.remove(toDeletekey);
    			timestampToKeyMap.remove(lastDeleteTimestamp);
    			contentMap.remove(toDeletekey);
    		}else{
    			// same key, just update time.
    			timestampToKeyMap.remove(lastDeleteTimestamp);
    		}
    		//add new
    		keyToTimeStampMap.put(k, timestamp);
    		timestampToKeyMap.put(timestamp, k);
    	}
    	return 0;
    }
    
    private int getLeastRecentKey(){
    	for(int i =lastDeleteTimestamp;i<timestamp;++i){
    		if(timestampToKeyMap.containsKey(i)){
    			lastDeleteTimestamp = i;
    			return timestampToKeyMap.get(i);
    		}
    	}
    	return -1;
    }
    
    private void keyUsed(int key){
    	if(keyToTimeStampMap.containsKey(key)){
    		int oldTime = keyToTimeStampMap.get(key);
    		if(timestampToKeyMap.containsKey(oldTime)){
    			timestampToKeyMap.remove(oldTime);
    		}
    	}
    	keyToTimeStampMap.put(key, timestamp);
    	timestampToKeyMap.put(timestamp, key);
    }
}