package week3.radix;

class NaarRadix {

  public static void main(String[] args) {
    int getal = Integer.parseInt(args[0]);
    int radix = Integer.parseInt(args[1]);
    try {    
      System.out.println(naarRadix(getal, radix));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public static String naarRadix(int getal, int radix) throws IllegalArgumentException {
    if (getal < 0) {
      throw new IllegalArgumentException("Getal moet positief zijn");
    } else if (getal < radix) {
      return Character.toString(Character.forDigit(getal, radix));
    } else {
      int mod = getal % radix;
      int div = (getal - mod) / radix;
      return naarRadix(div, radix) + Character.forDigit(mod, radix);
    }
  }
}