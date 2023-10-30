import java.io.File;
import java.io.FileNotFoundException; //Når jeg skal skrive ut koordinatene vi gikk, husk å plusse på 1 siden de starter på (1,1) og ikke (0,0).
import java.util.Scanner;
import java.lang.Integer;
class Labyrint{
  private int rader;
  private int kolonner;
  private Rute[][] ruteneILabyrinten;//skal være inne i konstruktøren.     //skal være to-dimensjonelt.
  public Labyrint(int rader,int kolonner,char[][] charArray){
    this.rader=rader;
    this.kolonner=kolonner;
    this.ruteneILabyrinten=new Rute[rader][kolonner];  //Må ha med alle instansvariabler i konstruktøren for at de skal bli laget, men mindre de er static.
    for(int rad=0;rad<rader;rad++){   //Mens det er en neste linje i filen(rad), vil løkken fortsette.
        for(int kol=0;kol<kolonner;kol++){      //I rad 0 går vi gjennom alle rutene bortover(kolonner), deretter gjentas dette for hver rad.
          if (charArray[rad][kol]=='#'){
          ruteneILabyrinten[rad][kol]=new SortRute(rad,kol,this);  //Starter telling på 1, derfor kordinatene +1.
          }
          if (charArray[rad][kol]=='.'){
            if(rad==0 || rad==rader-1 || kol==0 || kol==kolonner-1){ //Hvis den hvite ruten er på kanten av labyrinten er det en åpning.
            ruteneILabyrinten[rad][kol]=new Aapning(rad,kol,this);
            }else{
              ruteneILabyrinten[rad][kol]=new HvitRute(rad,kol,this); //Sender med this for å få beskjed om hvilken labyrint vi er inne i.
            }
        }
      }
  }for(int rad=0;rad<rader;rad++){
    for(int kol=0;kol<kolonner;kol++){
      ruteneILabyrinten[rad][kol].naboRuter(); //Vi setter inn hva naborutene er etter vi har laget arrayen. Må til for at naboRute() metoden i Rute.java skal funke.
    }
  }
}
  @Override
  public String toString(){
    String streng="";
    for(int rad=0;rad<rader;rad++){
      for(int kol=0;kol<kolonner;kol++){
        char tegn=ruteneILabyrinten[rad][kol].tilTegn();
         streng=streng+String.valueOf(tegn);
      }streng=streng+"\n";
    }
    //Skriv ut labyrinten i terminalen.Hvite ruter som mellomrom ikke punktum.
    return streng;
  }
  public Rute[][] hentRuteArray(){
    return ruteneILabyrinten;
  }
public static Labyrint lesFraFil(File fil) throws FileNotFoundException{
  char[][] charArray;
  try{
    Scanner laby=new Scanner(fil); //Scanner som skal ta imot fil som sendes med.
    String linje=laby.nextLine(); //Går til neste linje men returnerer det som stod på første linje.
    String[] radKolonne=linje.split(" ");  //Deler opp ved mellomrom.
    int rader=Integer.parseInt(radKolonne[0]);             //Gjør den som står først om til en integer og tilordner rader variabelen.
    int kolonner=Integer.parseInt(radKolonne[1]);
    charArray=new char[rader][kolonner];
    int rad=0; //lager teller som skal endres for hver gang vi har gått igjennom en hel rad.
    while(laby.hasNextLine()){   //Mens det er en neste linje i filen(rad), vil løkken fortsette.
      String line=laby.nextLine();
       //begynner med rad 0,deretter 1, osv.
      for(int kol=0;kol<kolonner;kol++){      //I rad 0 går vi gjennom alle rutene bortover(kolonner), deretter gjentas dette for hver rad.
        charArray[rad][kol]=line.charAt(kol);

      }rad++;
  }
  Labyrint labyrint=new Labyrint(rader,kolonner,charArray); //Kaller på konstruktøren.
  return labyrint;     //returnere labyrinten som vi lager utifra filen.
}catch(FileNotFoundException e){
    System.out.println("Filen finnes ikke");
  }
  return null;
}
  OrdnetLenkeliste<String> utveier;

  public OrdnetLenkeliste<String> finnUtveiFra(int kol, int rad){
    utveier=new OrdnetLenkeliste<String>();             //Vi må lage denne listen her inne, sånn a tvi lagrer alle utveiene for en bestemt rute i listen, og ikke alle utveier for alle ruter vi har funnet.
    int enKord=rad-1; //Vi har begynt å telle på 0.
    int toKord=kol-1;
    Rute hentet = ruteneILabyrinten[enKord][toKord]; //ruten vi starter i.

    if(hentet instanceof SortRute){             //Kan ikke begynne å gå fra en svart rute.
      System.out.println("Kan ikke starte i sort rute.");
    }else{
      hentet.finnUtvei();
    }
    return utveier;                    //returnere listen over utveiene.
  }
  public void settMinimalUtskrift(){}
public int hentAntKolonner(){
  return kolonner;
}
public int hentAntRader(){
  return rader;
}
}
