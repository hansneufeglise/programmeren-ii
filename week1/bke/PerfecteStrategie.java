package week1.bke;

public class PerfecteStrategie implements Strategie {
  
  int ONBEKEND    = 0;
  int VERLIEZEND  = 1;
  int NEUTRAAL    = 2;
  int WINNEND     = 3;
  
  public String getNaam() {
    return "Perfecte";
  }
  

  /*
  We beschrijven een recursief algoritme voor een spelstrategie die altijd leidt tot winst als het spel gewonnen kan worden, 
  en nooit leidt tot verlies als er een remisemogelijkheid bestaat. Het algoritme gaat uit van de volgende begrippen:
  
    ï¿¼í¯¿í°€ Een zet is winnend (voor de speler die aan zet is) als na de zet e Ìe Ìn van de volgende gevallen geldt:
        â€“ De speler heeft gewonnen;
        â€“ Als de tegenstander nog een zet kan doen, dan zijn alle mogelijke zetten van deze 
          tegenstander (voor deze tegenstander) verliezend.
     í¯¿í°€ Een zet is verliezend (voor de speler die aan zet is) als na de zet de tegenstander een (voor
       de tegenstander) winnende zet heeft.
     í¯¿í°€ In alle andere gevallen is de zet neutraal.
  
  Op basis van deze begrippen kunnen we een algoritme schrijven dat gegeven een stand en een speler die aan de
  beurt is de beste zet voor die speler oplevert, tezamen met een inschatting van de kwaliteit van die beste zet
  (winnend, neutraal of verliezend), en daarbij als volgt te werk gaat:
    
    â€¢ De tot nog toe beste zet en de kwaliteit van die zet worden bijgehouden in hulpvariabelen, bijvoorbeeld
      besteZet en besteKwal;
    â€¢ Voor elke mogelijke zet, zeg zet, gebeurt het volgende:
    
      1. De speler die aan de beurt is doet de zet (bij wijze van uitproberen).
      2. Vervolgens gaan we de kwaliteit kwal van zet bepalen.
      
        (a) Als de speler die aan de beurt is nu gewonnen heeft, wordt kwal â€œwinnendâ€.
        (b) Als de speler die aan de beurt is niet gewonnen heeft, wordt de beste zet van de tegenstander bepaald
            door een recursieve aanroep van het algoritme. We noemen de kwaliteit van deze beste zet van de
            tegenstander tgsKwal. â€“ als tgsKwal â€œwinnendâ€ is, dan wordt kwal â€œverliezendâ€; â€“ als tgsKwal â€œverliezendâ€ is,
            dan wordt kwal â€œwinnendâ€; â€“ anders wordt kwal â€œneutraal.â€
            
      3. Indien kwal beter is dan de huidige waarde van besteKwal, dan wordt de waarde van besteKwal vervangen door kwal
         en de waarde van besteZet wordt vervangen door zet.
      4. Tenslotte wordt de uitgeprobeerde zet zet teruggenomen, zodat de resterende zetten geprobeerd kunnen worden.
  
        â€¢ Het resultaat van het algoritme bestaat uit de waarden van besteZet en besteKwal.
  
  Dit algoritme kan enigszins niet-deterministisch worden gemaakt (d.w.z., zodat het in dezelfde situatie niet altijd
  dezelfde zet oplevert) door aan stap 4 toe te voegen dat besteZet soms ook de waarde van zet krijgt als kwal en besteKwal
  gelijk zijn.
  */
  
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
          // (b) Bepaal beste zet tegenstander
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
        if (kwal + Math.round(Math.random()) > besteKwal) {
          besteKwal = kwal;
          besteZet  = i;
        }
        
        // 4. Reset zet
        bordKopie.setVakje(i, Mark.LEEG);
      }
    }
    
    int beste[] = {besteZet, besteKwal};    
    return beste;
  }
  
}