class Legemiddel{
  protected static int iD=0;  //=0
  protected String navn;
  protected double pris;
  protected double virkestoff;
  protected int denneID;
  public Legemiddel(String navn,double pris,double virkestoff){
    this.navn=navn;
    this.pris=pris;
    this.virkestoff=virkestoff;
    this.denneID=iD;    //id.en for dette spesifikke legemiddelet.
    iD++;                 //den statiske variabelen økes etter hvert resept objekt som lages.

  }
  public int hentId() {
    return denneID;
}
public String hentNavn() {
  return navn;
}
public double hentPris() {
  return pris;
}
public double hentVirkestoff() {
  return virkestoff;
}
public String toString(){
  return this.navn+" "+this.virkestoff+" "+this.pris;
}
}
