class Koe<T> extends Nyttig<T>{
  @Override
  public void settInn(T element){  //Skal sette inn paa slutten av listen.
   Node n=new Node(element);
   if(erTom()){
     head=n;
     tail=n;
   }
   else{
     tail.next=n;
     tail=n;
   }
   size++;
  }
}//Kunne brukt iterator for aa utfoere denne metoden!!!!
