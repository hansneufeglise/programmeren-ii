package week1.getallen;

public class Faculteit {
  
  public static void main(String[] args) {
    try {
      int n = Integer.parseInt(args[0]);
      try {
        System.out.println("Faculteit " + n + ": " + faculteit(n)); 
      } catch (FaculteitException e) {
        //System.out.println(e.message);
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Gebruik: week1.getallen.ParseInt <parameter>");
    } catch (NumberFormatException e) {
      System.out.println("Parameter onjuist geformatteerd");
    }    
  }
  
  private static int faculteit(int n) throws FaculteitException {
    int result;
    if (n < 0) {
      throw new FaculteitException("Faculteit van " + n +" niet gedefinieerd");    
    } else if (n == 0) {
      result = 1;
    } else {
      if (n * faculteit(n - 1) / n != faculteit(n - 1)) {
        throw new FaculteitException("Resultaat te groot");
      } else {
        result = n * faculteit(n - 1);
      }
    }
    return result;
  }
  
}


