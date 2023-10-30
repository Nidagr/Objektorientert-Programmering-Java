class Pasient{
  protected static int iD=0; //statisk, denne skal endres ettersom nytt objekt lages.
  private String navn;
  private long fodselsnummer;
  private String gateadresse;
  private int postnummer;
  private int denneID;  //Id-en på det objektet jeg lager akkurat nå, ikke statisk fordi avhengig av objekt.
  Stabel<Resept> resepter=new Stabel<Resept>(); //Liste over reseptene de har skrevet ut.(hver pasient en egen)
  public Pasient(String navn, long fodselsnummer, String gateadresse,int postnummer) {    //Konstruktøren
    this.navn=navn;
    this.fodselsnummer=fodselsnummer;
    this.gateadresse=gateadresse;
    this.postnummer=postnummer;
    this.denneID=iD;              //id-en til denne pasienten.
    iD++;                        //Den statiske variabelen legges til en når nytt objekt lages. Objekt=Pasient.
  }
  public int hentId() {
  return denneID;                 //Id til et bestemt objekt.
  }
  public String hentNavn() {
  return navn;
  }
  public long hentFodselsnummer() {
  return fodselsnummer;
  }
  public String hentGateadresse() {
  return gateadresse;
  }
  public int hentPostnummer() {
  return postnummer;
  }
  public void leggTilResept(Resept resept) {       //Sender med en resept av type resept, se klassen.
    resepter.settInn(resept);                            //Lagde .settInn() i oblig 3
  }
  public Stabel<Resept> hentReseptliste() {   //Må være av typen Stabel<Resept>, returnerer listen vi lagde tidligere.
  return resepter;
  }
  @Override
  public String toString(){
    return "Pasient:"+navn+"("+fodselsnummer+")";
  }//Når vi kjører programmet. [4]Skriv ut statistikk.-> [1]Skriv ut alle blå respter tilhørende en pasient.
}
