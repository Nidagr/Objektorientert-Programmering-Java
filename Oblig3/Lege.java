class Lege implements Comparable<Lege>{
  private Koe<Resept> reseptFraLege=new Koe<Resept>(); //Liste over resptene legen har skrevet ut.
  protected String navn;
  public Lege(String navn){                  //Konstruktøren
    this.navn=navn;
  }
  public String hentNavn() {
    return navn;
  }
  public int compareTo(Lege annenLege) {             //Leger skulle sorteres etter navn(alfabetisk), derfor compareTo(navn).
    return navn.compareTo(annenLege.hentNavn()); //.toLowerCase()
  }
  public void leggTilResept(Resept resept) {   //Koe har en settInn() metode som brukes her.
    reseptFraLege.settInn(resept);             //Setter inn i listen vi lagde over.
  }
  public Koe<Resept> hentReseptliste() {
   return reseptFraLege;
  }
  @Override
  public String toString(){
    return "Lege:"+navn;
  }

}
