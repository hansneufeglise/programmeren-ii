package week1.getallen;

/**
 * Probeert de methode Integer.parseInt uit.
 * Practicumopgave Programmeren 2.
 * @author Arend Rensink
 * @version 27-11-2001
 */
public class ParseInt {
    public static void main(String[] args) {
      try {
        System.out.println("Waarde: "+Integer.parseInt(args[0])); 
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Gebruik: week1.getallen.ParseInt <parameter>");
      } catch (NumberFormatException e) {
        System.out.println("Parameter onjuist geformatteerd");
      }    
    }
    
}
