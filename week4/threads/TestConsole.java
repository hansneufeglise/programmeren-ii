package week4.threads;

public class TestConsole extends Thread {

    final String ONGELDIG_FORMAAT = "De invoer is onjuist geformatteerd.";

    private TestConsole(String naam) {
      super(naam);
    }
    
    public static void main(String args[]) {
      (new TestConsole("Thread A")).start();
      (new TestConsole("Thread B")).start();
    }

    public void run() {
      som();
    }
    
    private void som() {
      int eerste_getal = Console.readInt(this.getName() + ": eerste getal?", this.ONGELDIG_FORMAAT);
      int tweede_getal = Console.readInt(this.getName() + ": tweede getal?", this.ONGELDIG_FORMAAT);
      int som = eerste_getal + tweede_getal;            
      Console.println(this.getName() + ": " + eerste_getal + " + " + tweede_getal + " = " + som);
    }

}
