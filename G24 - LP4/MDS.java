/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author  kunal
 */

public class MDS {
    HashMap<Long,ArrayList<Item>> itemListDesc = new HashMap<Long, ArrayList<Item>>();
    HashMap<Long,Item> itemListId = new HashMap<Long, Item>();
    
    int insert(long id, double price, long[] description, int size) {
	
        //if an entry with a same ID exists
        Item tempItem = itemListId.get(id);
        //Item a = new Item(id, price, description, size);
        //Item newTempItem = a;
        ArrayList<Item> itemsList;
        //if ID does not exists in Map, create new entry for product
        if(tempItem==null){
            Item newTempItem = new Item(id, price, description, size);
            //enter into ID walla Map
            itemListId.put(id, newTempItem);
            
            //enter into desc walla Map
            insertIntoDescMap(newTempItem);
            //printHashMap();
            //printItemsHashmap();
            return 1;
        } 
        
        //updating existing item in the hashmap
        else{
            //updating the details into existing Map;
            if(Double.valueOf(price)!=null)
                tempItem.price=price;
            
            //updaing desc of object
            if(size!=0){
            //updating the hashmap first
                updateDescHashmap(tempItem);
                
                //updating the new item to be added
                tempItem.description=description;
                tempItem.size=size;
                //now add the item
                insertIntoDescMap(tempItem);
            }
            //itemListId.put(id, tempItem);
            //printHashMap();
            //printItemsHashmap();
            return 0;
        }
    }
    

    double find(long id) {
        
        if(itemListId.get(id)==null)
            return 0;
        
        Double price = itemListId.get(id).get_price();
        if(price==null)
	return 0;
        else return price;
    }

    long delete(long id) {
        
        //deleted from ID walla Map
        Item resultId = itemListId.remove(id);
        if(resultId==null)
	return 0;
        else {
            
        //returning sum of desc
        long sum=0;
        for(int i=0;i<resultId.size;i++)
            sum+=resultId.description[i];
        
        //deleting from desc walla Map
        Set<Long> set = itemListDesc.keySet();
                        
        for(Long desc: set){
            ArrayList<Item> a = itemListDesc.get(desc);
            if(a==null)
                continue;
            else{
                Iterator itr = a.iterator();
                while(itr.hasNext()){
                    Item i= (Item) itr.next();
                    if(i.id==id)
                        itr.remove();
                }
            }
        }
            //System.out.println("DELETE!!!!!!!!");
        //printItemsHashmap();
        return sum;
        }        
    }

    double findMinPrice(long des) {
        List<Item> resultMin = itemListDesc.get(des);
        if(resultMin==null)
            return 0;
        else{
            double min=Double.MAX_VALUE;
            for(Item i: resultMin){
                if(i.get_price()<=min)
                    min=i.get_price();
            }
        return min;   
        }
    }

    double findMaxPrice(long des) {
	
        List<Item> resultMax = itemListDesc.get(des);
        if(resultMax==null)
            return 0;
        else{
            double max=0;
            for(Item i: resultMax){
                if(i.get_price()>=max)
                    max=i.get_price();
            }
        return max;   
        }
    }

    int findPriceRange(long des, double lowPrice, double highPrice) {
	
          int count=0;
    	  List<Item> resultMax = itemListDesc.get(des);
          if(resultMax==null)
        	  return 0;
          else{
              for(Item i: resultMax){
                  if(i.get_price()>lowPrice && i.get_price()<highPrice)
                     count++;
              }            
          }
          return count;
    }

    double priceHike(long minid, long maxid, double rate) {
          	
    	Set<Long> s = itemListId.keySet();
    	double sumNetHike=0;
        DecimalFormat df = new DecimalFormat("#.##");
    	for(Long id : s){
    		
    		if(id>=minid && id<=maxid){
    			double tempPrice = itemListId.get(id).price;

    			double hikePrice=(tempPrice*rate)/100.0;
                        
                        df.setRoundingMode(RoundingMode.FLOOR);
                        hikePrice = Double.valueOf(df.format(hikePrice));

                        sumNetHike+=hikePrice;
                        //System.out.println("Hike...."+hikePrice);
                        //updating ID Map
                        //itemListId.get(id).price=((tempPrice*rate)/100.0)+tempPrice;
                        //System.out.println("id: "+id+" tempPrice before: "+tempPrice+" hike: "+hikePrice);
                        Item tempItem = itemListId.get(id);
                        tempItem.price=hikePrice+tempPrice;
                        df.setRoundingMode(RoundingMode.CEILING);
                        tempItem.price = Double.valueOf(df.format(tempItem.price));
                        
                        //System.out.println("tempPrice after: "+tempItem.price);
                        
//                        long[] tempDesc=itemListId.get(id).description;
//                        //updating Desc Map
//                        for(long desc: tempDesc){
//                            ArrayList<Item> temp = itemListDesc.get(desc);
//                            if(temp==null)
//                                continue;
//                            for(Item i: temp){
//                                if(i.id==id){
//                                    i.price=((tempPrice*rate)/100.0)+tempPrice;
//                                }
//                            }
//                        }
                        
    		}
    	}
        sumNetHike = Double.valueOf(df.format(sumNetHike));
        return sumNetHike;
	
	
    }

    int range(double lowPrice, double highPrice) {
    	
    	Set<Long> s = itemListId.keySet();
    	int count=0;
    	
    	for(Long id : s){
    		
    	
    			double tempPrice = itemListId.get(id).price;
    			
    			if(tempPrice>=lowPrice && tempPrice<=highPrice){
    			count++;

    		}
    	}
	return count;
    }

    int samesame() {
        
        Set<Long> s = itemListId.keySet();
        int[] descHashedValues = new int[s.size()];
        int i=0;
        for(Long in : s){
            
            if(itemListId.get(in).size>=8){
                long[] temp = Arrays.copyOf(itemListId.get(in).description, itemListId.get(in).size);
                for(int m=0;m<temp.length;m++)
                    //System.out.println(""+temp[m]);
                Arrays.sort(temp);
                descHashedValues[i]=Arrays.hashCode(temp);
                i++;
            } 
            
        }
        
        HashMap<Integer,Integer> countsamesame = new HashMap<>(); 
        for(int l=0;l<descHashedValues.length;l++){
            
            if(countsamesame.containsKey(descHashedValues[l])){
                int temp = countsamesame.get(descHashedValues[l]);
                temp++;
                countsamesame.put(descHashedValues[l],temp);
            }else{
                int count=1;
                countsamesame.put(descHashedValues[l], count);
            }
        }
          int globalcount=0;  
        Set<Integer> s1 = countsamesame.keySet();
        for(Integer in1 : s1){
            if(countsamesame.get(in1)>1 && in1!=0)
                globalcount+=countsamesame.get(in1);
        }
        //System.out.println("Global count"+ globalcount );   
	return globalcount;
    }
    
    
    public void insertIntoDescMap(Item insertItem){
        
        Long mapKey;
//        if(insertItem.size==0){
//            Set<Long> set = itemListDesc.keySet();
//
//        for(Long desc: set){
//            ArrayList<Item> a = itemListDesc.get(desc);
//            if(a==null)
//                continue;
//            Iterator itr = a.iterator();
//                   while(itr.hasNext()){
//                       Item i=(Item) itr.next();
//                       if(i.id==insertItem.id)
//                           itr.remove();
//                }
//            }
//        }
//        else{
        //System.out.println("Inserting into desc hashmap: "+insertItem.size);
            for(int i=0;i<insertItem.size;i++){
                mapKey = insertItem.description[i];

                ArrayList<Item> itemsListDesc;

                // if list does not exist create it
                if( itemListDesc.get(mapKey) == null) {
                    itemsListDesc = new ArrayList<Item>();
                    itemsListDesc.add(insertItem);

                    itemListDesc.put(mapKey, itemsListDesc);
                    //System.out.println("ID here11: "+insertItem.id);
                } else {
                    itemListDesc.get(mapKey).add(insertItem);
                    //System.out.println("ID here22: "+insertItem.id);
                }
            }
       //}
    }
    
    public void updateDescHashmap(Item existingItem){
                if(existingItem.size!=0){
                    //System.out.println("When Item size is not zero....");
            Set<Long> set = itemListDesc.keySet();

       //for(int j=0;j<size;j++){
        for(Long desc: set){
            ArrayList<Item> a = itemListDesc.get(desc);
            if(a==null)
                continue;
            Iterator itr = a.iterator();
                   while(itr.hasNext()){
                       Item i=(Item) itr.next();
                       if(i.id==existingItem.id)
                           itr.remove();
                       //breaking after deleting the item in arraylist 
                        break;
                    }
                   //checking if there exists an arraylist eventually after removing, if not then remove desc from hashmap
//            a = itemListDesc.get(desc);
//            if(a.size()==0)
//            itemListDesc.remove(desc);
            }
      // }
     }
    }
    
    public void printItemsHashmap(){
        
        Set<Long> set = itemListDesc.keySet();
                        
        for(Long desc: set){
            System.out.println("");
            System.out.print("Desc: "+desc+" ");
            ArrayList<Item> a = itemListDesc.get(desc);
                for(Item i: a){
                    System.out.print("Item id: "+i.id+" "+i.price+" ");
                    
                    for(int j=0;j<i.size;j++)
                    System.out.print(i.description[j]+" ");
                }
                System.out.println("");
        }
    }
    
    public void printHashMap(){
//        Set<Long> s= itemListId.keySet();
//        System.out.println("Printing ID Hashmap:");
//        for(Long temp:s){
//            Double tempPrice=itemListId.get(temp).price;
//            System.out.print("id: "+temp+" price: "+tempPrice+" ");
//            System.out.println("");
//        }
//        System.out.println("");
    }
}