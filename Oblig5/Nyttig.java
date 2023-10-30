import java.util.NoSuchElementException;
import java.util.Iterator;

abstract class Nyttig<T> implements Liste<T>{
  protected Node head;    //Lager en head og en tail av type node. Vet ikke hva slags objekter vi vil faa inn.
  protected Node tail;           //Derfor leger jeg en privat klasse som har data t.
  protected int size=0;
  protected class Node{
    Node(T t){
      data=t;
    }
    T data;
    Node next;    //Burde lage en previous peker
    //Node previous;
  }
@Override
public boolean erTom(){
  return head==null;
}
//SettInn(T element) saeregen og skrevet i hver av subklassen Stabel<T>, Koe<T> og OrdnetLenkeliste<T>.
  //Fjern metoden lik i alle, siden OrdnetLenkeliste har minste element foerst i listen, og skal fjerne foerste element.
public T fjern(){
  if(erTom()){
    return null;}  //Hvis tom listen ingenting returneres
  T temp=head.data;      //Lagrer data i foerste element i lenkelisten i en variabel temp.
  head=head.next;        //Sier at elementet etter er naa den nye head, foreste element i listen.
  //if(size==1){tail=null;} //Hvis kun ett element, saa er tail null.
  size--;        //Size en mindre siden foerste element fjernes.
  return temp;      //dataen til elementet som ble fjernet.
}

@Override
public int storrelse(){   //Stoerrelse er antall elementer i lenkelisten vaar.
  return size;
  }
  public Iterator<T> iterator(){
    return new MinIterator();
  }
  private class MinIterator implements Iterator<T>{
    Node current=head;      //Begynner på foerste element i listen.
    public boolean hasNext(){
      return current!=null;   //Denne booleanen er bare true hvis det elementet vi ser på er foskjellig fra null.
    }
    public T next(){
      T tempo=current.data;     //Lagrer dataen i elementet i variabel tempo av type T.
      current=current.next;     //Pekeren current flytter seg til neste element i listen.
      return tempo;            //returnere dataen etter elementet har blitt sett paa.
    }
    public void remove(){}
  }
}
