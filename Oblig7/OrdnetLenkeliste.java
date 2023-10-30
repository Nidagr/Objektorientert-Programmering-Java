class OrdnetLenkeliste<T extends Comparable> extends Nyttig<T>{


  public void settInn(T element){
    Node ny = new Node(element);

    if(head == null){
      head = ny;
      size++;
      return;
    }

    if(head.data.compareTo(ny.data) > 0){
      ny.next = head;
      head = ny;
      size++;
      return ;
    }

    Node t = head;

    while(t.next != null){
      if(t.next.data.compareTo(ny.data) > 0){
        ny.next = t.next;
        t.next= ny;
        size++;
        return ;
      }
      t = t.next;
    }

    t.next = ny;
    size++;

  }
}
