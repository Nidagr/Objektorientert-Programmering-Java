public abstract class Resept{
  protected static int iD=0;   //Lager statisk variabel for ID som en teller.
  protected int denneID;              //Denne opppdateres med 1 når ny resept lages.
  protected Legemiddel legemiddel;
  protected Lege utskrivendeLege;
  protected int pasientId;
  protected int reit;

  public Resept(Legemiddel legemiddel,Lege utskrivendeLege, int pasientId,int reit){
    this.legemiddel=legemiddel;
    this.utskrivendeLege=utskrivendeLege;
    this.pasientId=pasientId;
    this.reit=reit;
    this.denneID=iD; //this.denneID=iD++; //Vi vil gi resepten en id, som blir den statiske varabelen ID hittil.
    iD++;                //Når vi har gitt id-en må den statiske variabelen øke med 1.
  }
  public int  hentId() {
  return denneID;                  //Id på resepten.
  }
  public Legemiddel hentLegemiddel() {
    return legemiddel;
  }
  public Lege hentLege() {
    return utskrivendeLege;
  }
  public int hentPasientId() {
    return pasientId;
  }
  public int hentReit() {
    return reit;
  }
  /**
  * Bruker resepten én gang. Returner false om resepten er
  * oppbrukt, ellers returnerer den true.
  * @return      om resepten kunne brukes
  */
  public boolean bruk() {             //Kan resepten brukes igjen?
    if(reit>0){
      reit--;                      //Hvis negativt tall, ugyldig, hvis 0 ikke flere ganger igjen.
      return true;
    }else{return false;}              //ingen flere ganger igjen, false
  }
  /**
     * Returnerer reseptens farge. Enten "blaa" eller "hvit".
     * @return      reseptens farge
     */
    abstract public String farge(); //Skriver i hver av subklassene.

    /**
     * Returnerer prisen pasienten maa betale.
     * @return      prisen pasienten maa betale
     */
     abstract public double prisAaBetale();   //Skriver denne i hver av subklassene.
    @Override
    public String toString(){
      return "Legemiddel: "+this.legemiddel.hentNavn()+" Antall ganger igjen:"+this.reit;    //Skriver ut informason om resepten.
    }   //Kommer når vi tar [3] hent ut legemiddel på en resept.->velg en av pasientene som kommer opp.
}
