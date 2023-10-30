class Stabel<T> extends Nyttig<T>{
  @Override
  public void settInn(T element){  //Skal sette inn paa starten av listen.
   Node n=new Node(element);
   size++;
   if(erTom()){
     head=n;
     tail=n;
   }
   else{
     n.next=head;
     head=n;

   }
  }
}
