import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import krypto.*;

class Kryptograf implements Runnable{
  private Monitor kryptiskMldMonitor;   //Lager to monitorer
  private Monitor dekryptertMldMonitor;
  public Kryptograf(Monitor kryptiskMldMonitor, Monitor dekryptertMldMonitor){  //Kryptograf skal ha tilgang til begge monitorene, hente kryptert meldinger, og deretter lever dekrypterte meldinger.
    this.kryptiskMldMonitor=kryptiskMldMonitor;
    this.dekryptertMldMonitor=dekryptertMldMonitor;
  }
public void run(){
  Melding mld=kryptiskMldMonitor.hentUtMld(); //henter ut de kryptterte meldingene.
  while(mld!=null){
    String dekryptert=Kryptografi.dekrypter(mld.hentString()); //Dekrypterer meldingen den har hentet ut.
    mld.oppdaterMld(dekryptert); //Meldingen består nå av den dekrypterte meldingen.
    dekryptertMldMonitor.sendInnMeld(mld);         //Send den inn i monitor 2.
    mld=kryptiskMldMonitor.hentUtMld();          //Henter ut neste krypterte melding som den skal dekryptere.
  }
  dekryptertMldMonitor.oppdaterTellerHittill(); //Oppdaterer telleren som skal si fra når kryptografene til slutt er ferdige.
  }
}
