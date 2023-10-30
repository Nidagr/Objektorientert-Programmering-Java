class HvitResept extends Resept{
  public HvitResept(Legemiddel legemiddel,Lege utskrivendeLege, int pasientId,int reit){
    super(legemiddel,utskrivendeLege,pasientId,reit);
 }
 @Override
 public String farge(){
   return "Hvit resept";
}
@Override
public double prisAaBetale(){
  return legemiddel.hentPris();     //Legemiddelet opplyses om i Resept.java klassen, må derfor bruke metoden for å få prisen.
  }
}
