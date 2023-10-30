import krypto.*;
import java.util.concurrent.*;

class Oblig6{
  public static void main(String[] args) {
    int antKryptografer=200;
    Operasjonssentral ops = new Operasjonssentral(3);  //Oppretter ny operasjonssentral. Velger random tall,maks antall kanaler.
    int antKanaler=ops.hentAntallKanaler();
    Kanal[] kanaler = ops.hentKanalArray();                              //Hente ut kanalene.
    Monitor kryptiskMonitor=new Monitor(antKanaler);
    Monitor dekryptertMonitor=new Monitor(antKryptografer); //Velger random tall.
    for(int i=0;i<antKanaler;i++){
      new Thread(new Telegrafist(kryptiskMonitor,kanaler[i])).start();
      System.out.println("Telegrafist traad starter");
    }
    for(int i=0;i<antKryptografer;i++){
      new Thread(new Kryptograf(kryptiskMonitor,dekryptertMonitor)).start();
      System.out.println("kryptograf traad starter");
    }
    Thread t=new Thread(new Operasjonsleder(dekryptertMonitor,antKanaler));
    t.start();


  }
}
