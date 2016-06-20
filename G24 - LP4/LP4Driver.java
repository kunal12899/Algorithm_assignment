/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 *
 * @author  kunal
 */
public class LP4Driver {
    static long[] description;
    static final int DLENGTH = 100000;

    public static void main(String[] args)  throws FileNotFoundException {
	Scanner in;
	if(args.length >0) {
            in = new Scanner(new File(args[0]));
        } else {
	   in = new Scanner(System.in);
       //    in = new Scanner(new File("lp4-t2.txt"));
	}
	String s;
	double rv = 0,a=0;
	description = new long[DLENGTH];

	//Timer timer = new Timer();
	MDS mds = new MDS();
        long startTime = System.currentTimeMillis();
	while(in.hasNext()) {
	    s = in.next();
	    if(s.charAt(0) == '#') {
		s = in.nextLine();
		continue;
	    }
	    if(s.equals("Insert")) {
		long id = in.nextLong();
		double price = in.nextDouble();
		long des = in.nextLong();
		int index = 0;
		while(des != 0) {
		    description[index++] = des;
		    des = in.nextInt();
		}
		description[index] = 0;
		rv += mds.insert(id, price, description, index);
//                a = mds.insert(id, price, description, index);
//                rv+=a;
                //System.out.println("Insert: "+a);
	    } else if(s.equals("Find")) {
		long id = in.nextLong();
		rv += mds.find(id);
                //System.out.println("Find: "+mds.find(id));
	    } else if(s.equals("Delete")) {
		long id = in.nextLong();
		rv += mds.delete(id);
//                a= mds.delete(id);
//                rv+=a;
                //System.out.println("Delete: "+a);
	    } else if(s.equals("FindMinPrice")) {
		long des = in.nextLong();
		rv += mds.findMinPrice(des);
                //System.out.println("FindMinPrice: "+mds.findMinPrice(des));
	    } else if(s.equals("FindMaxPrice")) {
		long des = in.nextLong();
		rv += mds.findMaxPrice(des);
                //System.out.println("FindMaxPrice: "+mds.findMaxPrice(des));
	    } else if(s.equals("FindPriceRange")) {
		long des = in.nextLong();
		double lowPrice = in.nextDouble();
		double highPrice = in.nextDouble();
		rv += mds.findPriceRange(des, lowPrice, highPrice);
//                a= mds.findPriceRange(des, lowPrice, highPrice);
//                rv+=a;
//                System.out.println("FindPriceRange: "+a);
	    } else if(s.equals("PriceHike")) {
		long minid = in.nextLong();
		long maxid = in.nextLong();
		double rate = in.nextDouble();
		rv += mds.priceHike(minid, maxid, rate);
//                a = mds.priceHike(minid, maxid, rate);
//                System.out.println("PriceHike: "+a);
//                rv+=a;
	    } else if(s.equals("Range")) {
		double lowPrice = in.nextDouble();
		double highPrice = in.nextDouble();
		rv += mds.range(lowPrice, highPrice);
//                a= mds.range(lowPrice, highPrice);
//                rv+=a;
//                System.out.println("Range: "+a);
	    } else if(s.equals("SameSame")) {
		rv += mds.samesame();
//                a= mds.samesame();
//                rv+=a;
//                System.out.println("Samesame: "+a);
	    } else if(s.equals("End")) {
		break;
	    } else {
		System.out.println("Houston, we have a problem.\nUnexpected line in input: "+ s);
		System.exit(0);
	    }
	}
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        rv = Double.valueOf(df.format(rv));
	System.out.println(rv);
        
        long end = System.currentTimeMillis();
        long diff = end-startTime;
        System.out.println("Time taken : "+diff+" ms");
    }
}