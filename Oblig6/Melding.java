class Melding implements Comparable<Melding>{
 private String innholdet;
 private int egensekvensID;
 private int kanalID;

 public Melding(String innholdet, int kanalID,int egensekvensID){
   this.innholdet=innholdet;
   this.kanalID=kanalID;
   this.egensekvensID=egensekvensID;  //Denne lages i Telegrafist.
 }
 public String hentString(){
   return innholdet;
 }
 public int hentKanalID(){
   return kanalID;
 }
 public int returnerSekvensNR(){
   return egensekvensID;
 }
 public void oppdaterMld(String dekryptertTekst){
   innholdet=dekryptertTekst; //Når melding er ferdig dekryptert, settes den dekrypterte versjonene til å være det nye innholdet.
 }
 @Override
 public int compareTo(Melding melding){       //Vil sammenligne sekvensnumrene, altså hvilken rekkefølge de kom i.
   return this.egensekvensID - melding.returnerSekvensNR();
 }
}
