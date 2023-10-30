class Legeliste extends OrdnetLenkeliste<Lege> {
/**
* Soeker gjennom listen etter en lege med samme navn som ‘navn‘
* og returnerer legen (uten aa fjerne den fra listen).
* Hvis ingen slik lege finnes, returneres ‘null‘.
* @param   navn    navnet til legen
* @return  legen
*/
public Lege finnLege(String navn) {              //Bruker iteratoren i Nyttig.java.
  for(Lege lege:this){                            //sjekker hver lege i legelisten som er "this"
    if(lege.hentNavn().compareTo(navn)==0){          //Hvis legen er navnet vi ønsker returneres denne legen.
      return lege;
  }
}
return null;          //returnerer null hvis vi ikke finner legen.
}
/**
* Returnerer et String[] med navnene til alle legene i listen
* i samme rekkefoelge som de staar i listen.
* @return array med navn til alle legene
*/
public String[] stringArrayMedNavn() {        //Skal være en String array.
  String[] stringArrayMedNavn=new String[storrelse()]; //Size variabel fra klassen Nyttig.java, OrdnetLenkeliste<T> er en subklasse av Nyttig.java, kan ikke være større enn hvor mange leger vi har i listen.
  int teller=0; //lager teller for å bruke i en for-loop.
  for(Lege lege:this){         //For hver lege i legeliste
    stringArrayMedNavn[teller]=lege.hentNavn();   //Den plassen vi har kommet til i String-arrayet=navnet til legen.
    teller++;                //telleren økes med en
  }
  return stringArrayMedNavn;             //Vil til slutt returnere arrayet av leger.
}
}
