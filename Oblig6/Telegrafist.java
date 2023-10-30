import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import krypto.*;

class Telegrafist implements Runnable{
private final Lock laas=new ReentrantLock(); //lager en lås.
private Monitor kryptiskMonitor; //Monitoren vi skal sende meldingene vi lytter på til.
private Kanal kanal;  //Kanalen meldingene lyttes på.
public Telegrafist(Monitor kryptiskMonitor,Kanal kanal){
  this.kryptiskMonitor=kryptiskMonitor;
  this.kanal=kanal;
}
public void run(){
String meld=kanal.lytt();   //Lytter til kanalen sin.
int sekvens=0;
int melId=kanal.hentId();
while(meld!=null){   //Så lenge det er meldinger igjen på kanalen så skal de lyttes på.
  Melding m=new Melding(meld,melId,sekvens); //Lager Melding objekt av beskjeden vi lytter til.
  kryptiskMonitor.sendInnMeld(m);       //sender de til monitoren.
  sekvens++;                            //øker telleren vår, sekvensen altså etter hvilken rekkefølge vi hører meldingene.
  meld=kanal.lytt();              //lytter til resten av kanalene sine.
    }
  kryptiskMonitor.oppdaterTellerHittill();  //Telleren vi bruker for å si fra når trådene her er ferdig.
  }
}
