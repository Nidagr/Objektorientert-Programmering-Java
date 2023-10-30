import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import krypto.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;

class Operasjonsleder implements Runnable{   //SKal lage tråd av denne klassen, må derfor implementere Runnable.
  private Monitor dekryptertMonitor;
  private int antKanaler;
  private StatiskTabell<OrdnetLenkeliste<Melding>> samletListe;
  public Operasjonsleder(Monitor dekryptertMonitor,int antKanaler){
    this.dekryptertMonitor=dekryptertMonitor;
    this.antKanaler=antKanaler;
    samletListe=new StatiskTabell<OrdnetLenkeliste<Melding>>(antKanaler); //Setter inn en ordnetlenkeliste for hver kanal i den statiske tabellen.
    for(int i=0;i<antKanaler;i++){
      samletListe.settInn(new OrdnetLenkeliste<Melding>());
    }
  }
 public void run(){
   Melding melding=dekryptertMonitor.hentUtMld(); //Henter ut dekrypterte meldinger.
   while(melding!=null){
     samletListe.hentFraPlass(melding.hentKanalID()-1).settInn(melding); //Henter en ordnetlenkeliste på plass kanalId-1(den starter på 1), og setter inn melding i den ordnede lenkelisten.
     melding=dekryptertMonitor.hentUtMld(); //Fortsetter å hente ut dekryprterte meldinger.
   }
   //Sjekk om kryptografene er ferdig med å sende inn til operasjonsleder.
   if(dekryptertMonitor.henterTraadFerdig()){   //Når alle trådene er ferdige skal vi skrive ut til filene.
     //Lager tre forskjellige printWriters til hver kanal.
     try{
      PrintWriter out=new PrintWriter("1.txt","utf-8");
       try{
         for(Melding m : samletListe.hentFraPlass(0)){
           out.println(m.hentString()+"\n\n");
         }
       }
         finally{
           out.close();
         }
       }
       catch(IOException exception){}
         try{
          PrintWriter out=new PrintWriter("2.txt","utf-8");
           try{
             for(Melding m : samletListe.hentFraPlass(1)){
               out.println(m.hentString()+"\n\n");
             }
           }
             finally{
               out.close();
             }
           }
           catch(IOException exception){}
             try{
              PrintWriter out=new PrintWriter("3.txt","utf-8");
               try{
                 for(Melding m : samletListe.hentFraPlass(2)){
                   out.println(m.hentString()+"\n\n");
                 }
               }
                 finally{
                   out.close();
                 }
               }
               catch(IOException exception){}

   }
 }
}
