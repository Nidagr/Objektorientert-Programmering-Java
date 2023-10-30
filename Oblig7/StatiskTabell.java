import java.util.NoSuchElementException;
import java.util.Iterator;
class StatiskTabell<T> implements Tabell<T>{
  private T[] array;    //Lage array av type statisk tabell med lengden kapasitet.
  private int kapasitet;
  private int antallElementer=0;     //Lager en teller som starter på o, skal oppdateres hver gang nytt objekt legges til.
  public StatiskTabell(int kapasitet){           //Konstruktoer hvor kapasitet sendes inn som eneste parameter.
    this.kapasitet=kapasitet;
    array=(T[]) new Object[kapasitet];
  }

@Override
public boolean erTom(){            //Overrider metoden i Tabell.java og sjekker om listen er tom.
  return storrelse()==0;  //return array==null???
}

@Override
public void settInn(T element){
  if (antallElementer==kapasitet){          //Sjekker om arrayen er full.
    throw new FullTabellUnntak(antallElementer);               //Kaster unntaket.
  }else{
    array[antallElementer]=element;   //Setter inn elementet som sendes inn på foerste ledig plass.
    antallElementer++;         //Telleren oeker med en når et element settes inn.
  }
}
public int storrelse(){
  return antallElementer;
}
public T hentFraPlass(int plass){
  if(plass>=antallElementer || plass<0){  //Hvis plassen er stoerre enn stoerrelsen saa er vi paa en tom plass! Stoerrelsen kan ikke bli stoere enn kapasiteten siden vi har laget et unntak som kastes hvis den blir for stor.
    throw new UgyldigPlassUnntak(plass, antallElementer);  //Kan ikke ha negativ indeks!
  }
  return array[plass];             //returnere elementet hvis testen bestaar.
}
public Iterator<T> iterator(){
  return new EgenIterator();
}
private class EgenIterator implements Iterator<T>{    //Lage egen iterator klasse som itererer gjennom arrayen.
int teller=0;
public boolean hasNext(){
  return storrelse()>teller;
}
public T next(){
  if(!hasNext()){ throw new NoSuchElementException();}
  T tmp=array[teller];
  teller++;
  return tmp;
}
public void remove(){}

}
}
