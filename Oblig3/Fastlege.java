class Fastlege extends Lege implements Kommuneavtale{
private int avtalenummer;
  public Fastlege(String navn,int avtalenummer){
    super(navn);
    this.avtalenummer=avtalenummer;
  }
  @Override
  public int hentAvtalenummer(){                  //Helt lik signatur!!!!!
    return avtalenummer;
  }
}
