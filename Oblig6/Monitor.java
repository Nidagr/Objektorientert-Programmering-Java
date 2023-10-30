import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import krypto.*;

public class Monitor{
  private int maksAntTraader;
  private int teller=0;
  private boolean traadFerdig=false;
  private Koe<Melding> meldinger=new Koe<Melding>();
  private final Lock laas=new ReentrantLock();
  private final Condition kanSetteInn=laas.newCondition(); //Telegraf må vente.
  private final Condition ikkeTom=laas.newCondition();  //Kryptør må vente.
  private final Condition kanHenteMeld=laas.newCondition();  //Operasjonsleder må vente.
  public Monitor(int maksAntTraader){
    this.maksAntTraader=maksAntTraader;
    }
  public Koe<Melding> hentMeldingArray(){
    return meldinger;
    }
  public void oppdaterTellerHittill(){
    laas.lock();             //Låser sånn at ikke flere tråder kan oppdatere telleren hittil.
    try{
      teller++;
    if(teller==maksAntTraader){   //Hvis vi har fått inn alle meldinger av alle telegrafister så settes booleanen true.
      traadFerdig=true;
      }
    System.out.println(teller); //tester
    System.out.println(traadFerdig);
    }finally{
      laas.unlock();
    }
  }
  public boolean henterTraadFerdig(){
    return traadFerdig;
  }

  public void sendInnMeld(Melding mld){
    laas.lock(); //Skal kun ta inn en tråd om gangen.
    try{
      meldinger.settInn(mld);           //Legger til alle meldingnene sine i Køen.
      ikkeTom.signalAll();             //signaliserer, nå kan kryptografene komme og hente meldinger.
    }
    finally{
      laas.unlock();
    }
  }
  public Melding hentUtMld(){
    laas.lock();
    try{
      while(meldinger.erTom()){
        ikkeTom.await();         //Så lenge listen er tom må kryptøren vente. Denne conditionen skal vente.
      }
      if(traadFerdig){
        return null;
      }
      Melding m= meldinger.fjern();           //fjern() tar ut først element i Koe.
      return m;
    }catch(InterruptedException e){
      System.out.println("Interrupted!");
    }finally{
      laas.unlock();
    }
    return null;
  }


}
