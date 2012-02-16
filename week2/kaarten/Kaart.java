package week2.kaarten;

import java.io.*; 
import java.util.*;

/**
 * Representatie van speelkaart, met rang en kleur.
 * Aangepast en uitgebreid voor Programmeren 2, week 2.
 * @author  Arend Rensink, Rieks op den Akker en Theo Ruys
 * @version 2006.02.05
 */
public class Kaart implements Serializable { 
    
    // ---- klassenconstanten -----------------------------------
    
    // rangen zijn '2' t/m '9' plus :
    public static final char BOER = 'B';
    public static final char VROUW = 'V';
    public static final char HEER = 'H';
    public static final char AAS = 'A';
    public static final char TIEN = 'T';

    // kleuren zijn:
    public static final char KLAVEREN = 'K';
    public static final char RUITEN = 'R';
    public static final char HARTEN = 'H';
    public static final char SCHOPPEN = 'S';
    
    // some convenient arrays
    private static final char[] rangCharacters = "23456789TBVHA".toCharArray();
    private static final char[] kleurCharacters = { 'K', 'R', 'H', 'S' };
    private static final String[] rangStrings =
    { "2","3","4","5","6","7","8","9","10", "boer", "vrouw", "heer", "aas" };
    private static final String[] kleurStrings =
    { "Klaveren", "Ruiten", "Harten", "Schoppen" };

    // ---- klassenmethoden -------------------------------------

    public static void main(String args[]) throws FileNotFoundException {
      PrintWriter pw = new PrintWriter(System.out, true);
      Kaart kaart = new Kaart('K', '5');
      if (args.length > 0) {
        pw = new PrintWriter(args[0]);
      }
      kaart.schrijf(pw);
    }
    
    /**
     * Translates a char encoding of rang into it's String representation.
     * @return the String repr of rang
     * @param  rang the char encoding a rang
     * @return null if <code>geldigeRang(rang)</code> returns <code>false</code>
     */
    private static String rangChar2String(char rang){
        int i;
        for (i=0; i<13 && rangCharacters[i]!=rang; i++);
        return (i==13) ? null : rangStrings[i];
    }
     
    /**
     * Translates a kleur encoding of rang into its String representation.
     * @return the String repr of kleur
     * @param  rang the char encoding a kleur
     * @return null if <code>geldigeKleur(kleur)</code> returns <code>false</code>
     */
    private static String kleurChar2String(char kleur){
        int i;
        for ( i=0; i<4 && kleurCharacters[i]!=kleur; i++);
        return (i==4) ? null : kleurStrings[i];
    }
     
    /**
     * Translates a String encoding of rang into its character representation.
     * @param  rang the String encoding a rang
     * @return the char encoding of rang
     * @return '?' if <code>geldigeRang(rang)</code> returns <code>false<code>
     */
    private static char rangString2Char(String rang){
        int i;
        for ( i=0; i<13 && !(rangStrings[i].equals(rang)); i++);
        return (i==13) ? '?' : rangCharacters[i];
    }
     
    /**
     * Translates a kleur String into it's character encoding.
     * @param  rang the String representation of a kleur
     * @return the character encoding of kleur
     * @return '?' if <code>geldigeKleur(kleur)</code> returns <code>false</code>
     */
    private static char kleurString2Char(String kleur){
        int i;
        for ( i=0; i<4 && !(kleurStrings[i].equals(kleur)); i++);
        return (i==4) ? '?' : kleurCharacters[i];
    }
     
    /**
     * Bepaalt of een <tt>char</tt> een geldige kleur voorstelt.
     * @return <tt>true</tt> alsla 
     *         <tt>k</tt> in <tt>KLAVEREN | RUITEN | HARTEN | SCHOPPEN</tt>
     */
    public static boolean geldigeKleur(char k) {
        return k == KLAVEREN || k == RUITEN || k == HARTEN || k == SCHOPPEN;
    }

    /**
     * Bepaalt of een <tt>char</tt> een geldige rang voorstelt.
     * @return <tt>true</tt> alsla 
     * <tt>k</tt> in <tt>'2'..'9' | TIEN | BOER | VROUW | HEER | AAS</tt>
     */
    public static boolean geldigeRang(char r) {
        return ('2' <= r && r <= '9') ||
            r == TIEN || r == BOER || r == VROUW || r == HEER || r == AAS;
    }

    /**
     * Test of de ene kleur kleiner is dan de andere
     * volgens KLAVEREN < RUITEN < HARTEN < SCHOPPEN.
     * @require geldigeKleur(k1) && geldigeKleur(k2)
     */
    public static boolean kleurKleinerDan(char k1, char k2) {
        if (k1 == KLAVEREN)
            return k2 != KLAVEREN;
        else if (k1 == RUITEN)
            return k2 != KLAVEREN && k2 != RUITEN;
        else if (k1 == HARTEN)
            return k2 == SCHOPPEN;
        else return false;
    }

    /**
     * Test of de ene rang kleiner is dan de andere
     * volgens '2' < '3' < ... < TIEN < BOER < VROUW < HEER < AAS.
     * @require geldigeRang(r1) && geldigeRang(r2)
     */
    public static boolean rangKleinerDan(char r1, char r2) {
        int i;
        for (i=0; rangCharacters[i]!=r1 && rangCharacters[i]!=r2; i++);
        return rangCharacters[i] == r2 
            ? false
            : rangCharacters[i] == r1;
    }
        
    /**
     * Test of de ene rang direct vooraf gaat aan de andere
     * volgens '2' < '3' < ... < TIEN < BOER < VROUW < HEER < AAS.
     * @require geldigeRang(r1) && geldigeRang(r2)
     */
    public static boolean rangOpvolgend(char r1, char r2) {
        if (r1 == HEER)
            return r2 == AAS;
        else if (r1 == VROUW)
            return r2 == HEER;
        else if (r1 == BOER)
            return r2 == VROUW;
        else if (r1 == TIEN)
            return r2 == BOER;
        else if (r1 == '9')
            return r2 == TIEN;
        else return r2 == r1+1;
    }

    /**
     * Lees een kaart van een DataInput
     * @param   in DataInput de data input.
     */
    public static Kaart lees(DataInput in) throws EOFException {
      char rang;
      char kleur;
      
      try {
        rang   = in.readChar();
        kleur  = in.readChar();      
      } catch (IOException e) {
  			throw new EOFException();
  		}
      
      if (geldigeKleur(kleur) && geldigeRang(rang)) {
			  return new Kaart(kleur, rang);
			}
  		
  		return null;
    }
    
    /**
      * Lees een kaart van een ObjectInput
      * @param   ois ObjectInput de object input.
      */
    public static Kaart lees(ObjectInput ois) throws EOFException {
      Kaart kaart = null;
      
      try {
        Object obj = ois.readObject();          
        kaart = (Kaart)obj;
        
      } catch (ClassNotFoundException e) {
        
      } catch (NoSuchElementException e) {
        
      } catch (IOException e) {
  			throw new EOFException();
      }

      return kaart;
    }

    /**
     * Lees een kaart van een BufferedReader
     * @param   in BufferedReader de buffered reader.
     */
    public static Kaart lees(BufferedReader in) throws EOFException {      
      Scanner scanner;
      Kaart kaart = null;
      Character kleur, rang;

      // Probeer een nieuwe Scanner te maken van de BufferedReader
      try {
        if (in.ready()) {
          scanner = new Scanner(in.readLine());
        
          // Kleur en rang uitlezen
          if (scanner.hasNext()) {
            kleur = kleurString2Char(scanner.next());        		
          }
          
          if (scanner.hasNext()) {
            rang  = rangString2Char(scanner.next());      			
          }
          
        } else {
          throw new EOFException();
        }
  		} catch (IOException e) {
  			throw new EOFException();
  		}
  		  		
			if (geldigeKleur(kleur) && geldigeRang(rang)) {
			  kaart = new Kaart(kleur, rang);
			}
  		
  		return kaart;
    }
    
    // ---- instantievariabelen -----------------------------------

    /**
     * Kleur van deze Kaart.
     * @invariant <tt>geldigeKleur(kleur)</tt>
     */
    private char kleur;
    /**
     * Rang van deze Kaart.
     * @invariant <tt>geldigeRang(rang)</tt>
     */
    private char rang;

    // ---- constructor -----------------------------------

    /** 
     * Maakt nieuwe Kaart met gegeven kleur en rang.
     * @require <tt>geldigeKleur(k) && geldigeRang(r)</tt>
     * @param   k kleur van de nieuwe Kaart.
     * @param   r rang van de nieuwe Kaart.
     */
    public Kaart(char k, char r) {
        assert (geldigeKleur(k) && geldigeRang(r));
        kleur = k;
        rang = r;
    }

    // ---- queries ---------------------------------------

    /**
     * Levert de kleur van deze Kaart op. 
     * @return kleur van deze Kaart.
     * @ensure <tt>geldigeKleur(result)</tt>
     */
    public char getKleur() {
        return kleur;
    }

    /** 
     * Levert de rang van deze Kaart op. 
     * @return rang van deze Kaart.
     * @ensure <tt>geldigeRang(result)</tt>
     */
    public char getRang() {
        return rang;
    }

    /** Levert een String-beschrijving van deze Kaart op. */
    public String toString() {
        String res;
        switch (getKleur()) {
          case KLAVEREN: res = "Klaveren "; break;
          case RUITEN: res = "Ruiten "; break;
          case HARTEN: res = "Harten "; break;
          default: res = "Schoppen "; 
        }

        switch (getRang()) {
          case TIEN: res = res + "10"; break;
          case BOER: res = res + "boer"; break;
          case VROUW: res = res + "vrouw"; break;
          case HEER: res = res + "heer"; break;
          case AAS: res = res + "aas"; break;
          default: res = res + getRang();
        }

        return res;
    }

    /**
     * Test of deze Kaart gelijk is (in kleur en rang) aan een andere.
     * @param  obj Kaart waarmee vergeleken wordt.
     * @return <code>true</code> als kleur en rang van <code>obj</code> 
     *         gelijk zijn aan deze Kaart.
     */
    public boolean equals(Object obj) {
        if ( !(obj instanceof Kaart) ) return false;
        Kaart kaart = (Kaart) obj;
        return this.getKleur() == kaart.getKleur() && 
            this.getRang() == kaart.getRang();
    }
    

    /**
     * Test of deze Kaart kleiner in kleur is dan een andere.
     * Geteste volgorde: kleurKleinerDan.
     * @require <code>kaart != null</code>
     * @param   kaart Kaart waarmee vergeleken wordt.
     * @return  <code>true</code> als kleur van deze Kaart
     *          kleiner is dan van <code>kaart</code>.
     */
    public boolean kdKleur(Kaart kaart) {
        return kleurKleinerDan(this.getKleur(), kaart.getKleur());
    }

    /**
     * Test of deze Kaart kleiner in rang is dan een andere.
     * Geteste volgorde: rangKleinerDan.
     * @require <code>kaart != null</code>
     * @param   kaart Kaart waarmee vergeleken wordt.
     * @return  <code>true</code> als rang van deze Kaart
     *          kleiner is dan van <code>kaart</code>.
     */
    public boolean kdRang(Kaart kaart) {
        return rangKleinerDan(this.getRang(), kaart.getRang());
    }

    /**
     * Test of deze Kaart in rang opgevolgd wordt door een andere.
     * Let niet op kleur.
     * Gebruikte volgorde: rangOpvolgend.
     * @param   kaart Kaart waarmee vergeleken wordt.
     * @require <code>kaart != null</code>
     * @return  <code>true</code> als rang van deze Kaart
     *          voorafgaat aan rang van <code>kaart</code>.
     */
    public boolean gaatInRangVoorafAan(Kaart kaart) {
        return rangOpvolgend(this.getRang(), kaart.getRang());
    }
    
    /**
     * Schrijft een kaart weg naar DataOutput
     * @param   out DataOutput om naar weg te schrijven
     */
    public void schrijf(DataOutput out) throws IOException {
        out.writeChar(this.rang);
        out.writeChar(this.kleur);
    }

    /**
     * Schrijft een kaart weg naar ObjectOutput
     * @param   oos ObjectOutput om naar weg te schrijven
     */
    public void schrijf(ObjectOutput oos) throws IOException {
      oos.writeObject(this);
      oos.close();
    }
    
    
    /*
    
    Voeg aan de klasse Kaart een methode schrijf toe, met als parameter een PrintWri-
    ter, die er voor zorgt dat het object waarop de methode wordt aangeroepen een String-
    beschrijving van zichzelf (verkregen door een aanroep van toString) naar de PrintWri-
    ter schrijft.
        
    */    
    
    /**
     * Schrijft een tekstuele beschrijving van
     * de kaart weg naar een PrintWriter
     * @param   pw PrintWriter om naar weg te schrijven/
     */
    public void schrijf(PrintWriter pw) {
      pw.println(this.toString());
      pw.close();
    }

}
