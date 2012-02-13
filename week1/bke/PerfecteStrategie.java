package week1.bke;

public class PerfecteStrategie implements Strategie {
  
  private static final int ONBEKEND    = 0;
  private static final int VERLIEZEND  = 1;
  private static final int NEUTRAAL    = 2;
  private static final int WINNEND     = 3;
  
  public String getNaam() {
    return "Perfecte";
  }
  
  public int bepaalZet(Bord b, Mark m) {
    Bord bordKopie = b.deepCopy();
    return vindBesteZet(bordKopie, m)[0];
  }
  
  private int[] vindBesteZet(Bord b, Mark m) {
    int tgsKwal, besteZet = 0;
    int kwal = ONBEKEND;
    int besteKwal = ONBEKEND;
    
    // Ga elke mogelijke zet af:
    for (int i = 0; i < Bord.DIM * Bord.DIM; i++) {
      if (b.isLeegVakje(i)) {
        // 1. Probeer zet
        b.setVakje(i, m);
        
        // 2. Bepaal kwaliteit
        if (b.isWinnaar(m)) {
          // (a) Speler wint
          kwal = WINNEND;
        } else {
          // (b) Inverse van beste zet tegenstander
          tgsKwal = vindBesteZet(b, m.other())[1];
          if (tgsKwal == WINNEND) {
             kwal = VERLIEZEND;
           } else if (tgsKwal == VERLIEZEND) {
             kwal = WINNEND;
           } else {
             kwal = NEUTRAAL;
           }
        }
        
        // 3. Vervang zet indien beter
        if (besteKwal == ONBEKEND || kwal + Math.round(Math.random()) > besteKwal) {
          besteKwal = kwal;
          besteZet  = i;
        }
        
        // 4. Reset zet
        b.setVakje(i, Mark.LEEG);
      }
    }
    
    return (new int[] {besteZet, besteKwal});
  }
  
}