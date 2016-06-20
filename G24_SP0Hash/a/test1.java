
import java.util.Objects;


/**
 *
 * @author:G24
 *  *   
 *         Kunal Krishna
 *        
 */
class test1 {
    String str;
    Integer num;
    
    public test1(String a){
    str = a;
    
    }
    
    public test1(Integer a){
        num = a;    
    }
    
    public test1(String n,Integer x){
        str=n;
        num=x;
    }
    
    @Override
    public int hashCode(){
        
        if(str != null && num!=null){
            //System.out.println("hash2 "+str.hashCode() +num.hashCode());
            return str.hashCode() +num.hashCode();
        }
            
        else if(str==null){
         
            //System.out.println("hashnum "+num.hashCode());
            return num.hashCode();
        }
            
        else{
         
            //System.out.println("hashstr "+str.hashCode());
            return str.hashCode();
        }
            
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final test1 other = (test1) obj;
        if (!Objects.equals(this.str, other.str)) {
            return false;
        }
        return true;
    }
    
}
