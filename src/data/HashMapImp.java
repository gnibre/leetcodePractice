package data;

import java.util.ArrayList;


/**
 * implemnt as hashmap,  test code:   hashmapImpTest.java;
 * @author gnibrE
 *
 * @param <KT>
 * @param <VT>
 */
public class HashMapImp<KT,VT> {
	
	ArrayList<SlotObj> backSlotArray;
	
	public HashMapImp(){
		backSlotArray = new ArrayList<SlotObj>(5); // 5 is capacity, not already add.
		for(int i=0;i<5;++i){
			backSlotArray.add(new SlotObj());
		}
	}
	
	public void status(){
		System.out.println(" slot size: "+backSlotArray.size());
		
		for(SlotObj slots:backSlotArray){
			if(slots.size()>0){
				System.out.println(" this slot have content size: "+slots.size());
			}
		}
		
	}
	
	public void put(KT key, VT value){
		int targetSlotId = hashFunction(key);
		
		System.out.println(" go put it to the right slot, key: "+key+"  value:"+value+"     find slot: "+targetSlotId);
		while(backSlotArray.size()<targetSlotId){
			backSlotArray.add(new SlotObj());
		}
		
		SlotObj targetSlot = backSlotArray.get(targetSlotId);
		backSlotArray.set(targetSlotId, targetSlot);
		targetSlot.addItem(key,value);
		
	}

	public VT get(KT key){
		if(key==null){
			return null;
		}
		int targetSlotId = hashFunction(key);
		SlotObj slot = backSlotArray.get(targetSlotId);
		if(slot==null) return null;
		
		System.out.println(" go get it with key: "+key+"     target slot have content size: "+slot.size());
		VT target = slot.findInLinkedList(key);
		
		if(target==null){
			System.out.println(" no result find with this key");
		}else{
			System.out.println(" find value with this key:   "+target);
		}
		
		return target;
	}
	
	
	private class SlotObj{
		ArrayList<KVPair> kvPairlinkedList= new ArrayList<KVPair>();
		
		public int size(){
			if(kvPairlinkedList!=null){
				return kvPairlinkedList.size();
			}
			return 0;
		}
		public void addItem(KT key,VT value){
			KVPair kvp = new KVPair(key,value);
			kvPairlinkedList.add(kvp);
		}
		
		public VT findInLinkedList(KT key){
			for(KVPair p:kvPairlinkedList){
				if(key.equals(p.k)){
					return p.v;
				}
			}
			return null;
		}
		
		private class KVPair{
			public KVPair(KT ki,VT vi){
				k=ki;
				v = vi;
			}
			KT k;
			VT v;
		}
	}
	
	
	private int hashFunction(KT key){
		// will always put everything to slot 1  -.-
		return 1;
	}
}
