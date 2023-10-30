class BlaaResept extends Resept{
  public BlaaResept(Legemiddel legemiddel,Lege utskrivendeLege, int pasientId,int reit){ //reit=antall ganger igjen med resept.
    super(legemiddel,utskrivendeLege,pasientId,reit);
  }
  @Override
  public String farge(){
    return "blaa";
  }
  @Override
  public double prisAaBetale(){
    return legemiddel.hentPris()*0.25;                 //75% rabatt på blå resepter.
  }
}
//hentPris() inne i en abstract klasse, som den ikke arver fra, har dette noe å si?
