abstract class Rute{
  protected int radKord;
  protected int kolKord;
  protected Labyrint hvilkenLabyrint;
  protected Rute nord; //Naborute nord for ruten vi står i.
  protected Rute sor; //Naborute sør for ruten vi står i.
  protected Rute ost;  //Naborute øst for ruten vi står i.
  protected Rute vest;  //Naborute vest for ruten vi står i.

  private boolean vartHer=false;    //Skal gjøre den om til true etter hvert som vi flytter oss.
  public boolean harVartHer(){
    return vartHer;
  }
 Rute(int radKord,int kolKord,Labyrint hvilkenLabyrint){           //Konstruktør med kordinatene til ruten, og hvilken labyrint den ligger i.
   this.radKord=radKord;
   this.kolKord=kolKord;
   this.hvilkenLabyrint=hvilkenLabyrint;
 }
  abstract char tilTegn();
  public void naboRuter(){
    Rute[][] henta=hvilkenLabyrint.hentRuteArray();
    if(radKord>0){
      nord=henta[radKord-1][kolKord];
    }
    if(radKord<henta.length-1){
      sor=henta[radKord+1][kolKord];
    }
    if(kolKord<henta[0].length-1){    //length-1 fordi indeksering starter på 0.
      ost=henta[radKord][kolKord+1];
    }
    if(kolKord>0){
      vest=henta[radKord][kolKord-1];
    }
  }
  public void gaa(String veiHittil){
    String denneRuten=String.format("(%d,%d)",kolKord+1,radKord+1);//Må plusse på 1 siden vi starter i 0,0 men oppgaven starter i 1,1.
    veiHittil=veiHittil+denneRuten;
    if(this instanceof Aapning){
      hvilkenLabyrint.utveier.settInn(veiHittil+"\n");
    }

    vartHer=true;
    if(nord instanceof HvitRute && !nord.harVartHer()){
      nord.gaa(veiHittil+"-->");
    }
    if(sor instanceof HvitRute && !sor.harVartHer()){
      sor.gaa(veiHittil+"-->");
    }
    if(vest instanceof HvitRute && !vest.harVartHer()){
      vest.gaa(veiHittil+"-->");
    }
    if(ost instanceof HvitRute && !ost.harVartHer()){
      ost.gaa(veiHittil+"-->");
    }

    vartHer=false;   //Vi har funnet en utvei, nå skal rutene markeres som ikke besøkt og prøver å finne andre utveier.
  }
  public void finnUtvei(){
    gaa("");
  }


}
