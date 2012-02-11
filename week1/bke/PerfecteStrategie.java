package week1.bke;

public class PerfecteStrategie implements Strategie {
  
  int ONBEKEND    = 0;
  int VERLIEZEND  = 1;
  int NEUTRAAL    = 2;
  int WINNEND     = 3;
  
  public String getNaam() {
    return "Perfecte";
  }
  
  public int bepaalZet(Bord b, Mark m) {
    return vindBesteZet(b, m)[0];
  }
  
  private int[] vindBesteZet(Bord b, Mark m) {
    Bord bordKopie = b.deepCopy();
    int tgsKwal, besteZet = 0;
    int kwal = this.ONBEKEND;
    int besteKwal = this.ONBEKEND;
    
    // Ga elke mogelijke zet af:
    for (int i = 0; i < Bord.DIM * Bord.DIM; i++) {
      if (bordKopie.isLeegVakje(i)) {
        // 1. Probeer zet
        bordKopie.setVakje(i, m);
        
        // 2. Bepaal kwaliteit
        if (bordKopie.isWinnaar(m)) {
          // (a) Speler wint
          kwal = this.WINNEND;
        } else {
          // (b) Inverse van beste zet tegenstander
          tgsKwal = vindBesteZet(bordKopie, m.other())[1];
          if (tgsKwal == this.WINNEND) {
             kwal = this.VERLIEZEND;
           } else if (tgsKwal == this.VERLIEZEND) {
             kwal = this.WINNEND;
           } else {
             kwal = this.NEUTRAAL;
           }
        }
        
        // 3. Vervang zet indien beter
        if (besteKwal == this.ONBEKEND || kwal + Math.round(Math.random()) > besteKwal) {
          besteKwal = kwal;
          besteZet  = i;
        }
        
        // 4. Reset zet
        bordKopie.setVakje(i, Mark.LEEG);
      }
    }
    
    return (new int[] {besteZet, besteKwal});
  }
  
}