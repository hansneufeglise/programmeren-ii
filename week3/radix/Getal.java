package week3.radix;

import java.util.Observable;

/**
 * Observeerbaar getal.
 * Practicumopgave Programmeren 2.
 * @author Rieks op den Akker en Arend Rensink
 * @version 2.0
 */
public class Getal extends Observable {
    // ---- Instantievariabelen ------------------------------

    int waarde;

    // ---- Constructoren ------------------------------------------

    /**
     * Construeert een <code>Getal</code> met beginwaarde 0.
     * @ensure this.getWaarde() == 0
     */
    public Getal() {
        this(0);
    }

    /**
     * Construeert een <code>Getal</code> met een gegeven beginwaarde.
     * @ensure this.getWaarde() == waarde
     */
    public Getal(int waarde) {
        setWaarde(waarde);
    }

    // ---- Queries ------------------------------------------

    public int getWaarde(){
        return waarde;
    }   

    // ---- Commando's ---------------------------------------

    /**
     * Geeft dit <code>Getal</code> een nieuwe waarde, en 
     * licht de observers in.
     * @ensure this.getWaarde() == waarde
     */
    protected void setWaarde(int waarde){
        this.waarde = waarde;
        this.setChanged();
        this.notifyObservers(waarde);
    }
} // end Getal
