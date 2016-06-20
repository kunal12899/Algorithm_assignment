/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kunal
 */
public class Item {
    long id;
    double price;
    long[] description;
    int size;
    
    public Item(long id, double price, long[] description, int size){
    this.id=id;
    this.price=price;
    this.description = new long[size];
    for(int i=0;i<size;i++)
        this.description[i]=description[i];
    this.size=size;
    }
    
    
    public void set_id(long id){
       
          this.id=id;
       
       }
       
       public long get_id(){
       
          return id;
       
       }
       
        public void set_price(double price){
       
          this.price=price;
       
       }
       
       public double get_price(){
       
          return price;
       
       }
       
        public void set_des(long[] des){
       
          this.description=des;
       
       }
       
       public long[] get_des(){       
          
           return description;      
       
       }
}
