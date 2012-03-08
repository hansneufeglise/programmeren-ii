package week4.threads;

public class TestSyncConsole extends Thread {

    final String ONGELDIG_FORMAAT = "De invoer is onjuist geformatteerd.";

    private TestSyncConsole(String naam) {
      super(naam);
    }
    
    public static void main(String args[]) {
      Thread threadA = new TestSyncConsole("Thread A");
      Thread threadB = new TestSyncConsole("Thread B");
      threadA.start();
      threadB.start();
    }

    public void run() {
      synchronized (TestSyncConsole.class) {
        som();
      }
    }
    
    private void som() {
      int eerste_getal = SyncConsole.readInt(this.getName() + ": eerste getal? ", this.ONGELDIG_FORMAAT);
      int tweede_getal = SyncConsole.readInt(this.getName() + ": tweede getal? ", this.ONGELDIG_FORMAAT);
      int som = eerste_getal + tweede_getal;            
      SyncConsole.println(this.getName() + ": " + eerste_getal + " + " + tweede_getal + " = " + som);
    }

}
