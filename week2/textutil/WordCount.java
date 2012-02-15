package week2.textutil;

import java.util.*;
import java.io.*;

/**
 * P2 prac wk2.
 * Programma WordCount voor het tellen van regels, woorden en karakters.
 * @author  Theo Ruys
 * @version 2005.02.08
 */
public class WordCount implements Analyzer {
    /** 
     * Telt het aantal regels, woorden en karakters in reader en
     * schrijft de totalen samen met fname naar de System.out.
     * Woorden worden gescheiden door de gebruikelijke whitespace,
     * d.w.z. spaties en tabs (leestekens worden niet meegenomen).
     * @throws IOException als er iets mis gaat bij het lezen
     */
    public void process(String fname, BufferedReader reader) throws IOException {
    	boolean doorgaan = true;
    	int[] counter = new int[3];
    	
    	System.out.println("Bestandsnaam: " + fname);
                
    	while(doorgaan) {
           	String line = reader.readLine();
           	if(line != null) {
           		counter[2]++; // counts lines
	        	counter[0] += line.length(); // counts characters

           		Scanner s = new Scanner(line);
    	        while(s.hasNext()) {
    	        	s.next(); // moves pointer one ahead
    	        	counter[1]++; // counts words
    	        }
           	} else {
           		doorgaan = false;
           	}
        }

    	System.out.println();
    	System.out.println(String.format("%6s", counter[0]) + "  karakters");
    	System.out.println(String.format("%6s", counter[1]) + "  woorden");
    	System.out.println(String.format("%6s", counter[2]) + "  regels");
    }

    public static void main(String[] args) {
        FilesProcessor fp = new FilesProcessor(new WordCount());
        fp.openAndProcess(args);
    }
}
