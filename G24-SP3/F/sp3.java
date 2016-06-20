
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 */
public class sp3 {
    
    public static void main (String[] args){
        
        Scanner in;
        try {
            System.out.println("starting project ");
            in = new Scanner(new File("C:\\Spring16\\Impl6301002\\LP0\\1.txt"));
            Graph<EulerVertex,EulerEdge> g = Graph.readGraph(in, false);
            g.printAdjList();
            System.out.println("ended");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
}
