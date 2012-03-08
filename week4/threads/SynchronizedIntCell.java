package week4.threads;

/**
 * Correcte communicatie tussen IntProducer en IntConsumer.
 * Practicumopdracht Programmeren 2.
 */
public class SynchronizedIntCell implements IntCell {
    private int value = 0;
    private Boolean beschikbaar = false;

    public synchronized void setValue(int value) {
        while (beschikbaar) {
          try {
            wait();
          } catch (InterruptedException e) { }
        }
        this.value = value;
        beschikbaar = true;
        notifyAll();
    }

    public synchronized int getValue() {
        while (!beschikbaar) {
          try {
            wait();
          } catch (InterruptedException e) { }
        }
        beschikbaar = false;
        notifyAll();
        return value;
    }
    
    
}
