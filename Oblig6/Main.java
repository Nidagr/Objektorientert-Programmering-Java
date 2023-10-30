import krypto.*;

public class Main{
  public static void main(String[] args) {
    Operasjonssentral op=new Operasjonssentral(3);
    int ant=op.hentAntallKanaler();
    System.out.println(ant);
  }
}
