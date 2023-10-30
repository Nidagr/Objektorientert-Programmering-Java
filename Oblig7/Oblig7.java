import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.FileNotFoundException;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;

public class Oblig7 extends Application{
  GUIRute[][] rektangelArray;  //Dobbeltarray for å oppbevare rutene i labyrinten
  Labyrint l=null;     //Setter labyrinten til å være null først.
    @Override
  public void start(Stage stage){
    BorderPane pane =new BorderPane();
    Button filValg=new Button("Velg fil");
    pane.setTop(filValg);    //Setter knappen til å velge fil øverst i vinduet.
    stage.setScene(new Scene(pane,100,100));  //Størrelsen på vinduet
    stage.show();

    filValg.setOnAction(new EventHandler<ActionEvent>(){ //Velger hva som skjer når man trykker på "velg fil" knappen.
      @Override
      public void handle(ActionEvent event){
        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Text filer(labyrint.in)","*.in"); //Filteret gjør at vi kun kan velge filer som slutter på .in, altså filene med labyrinter.
        fileChooser.getExtensionFilters().add(filter); //Sørge for at filteret blir brukt i filechooseren.
        fileChooser.setTitle("Velg labyrintfil");
        File valgtFil=fileChooser.showOpenDialog(null);
        Label antL= new Label("Antall Loesninger: "); //Når vi
        pane.setCenter(lagGridPane(valgtFil,antL)); //Metoden tegner opp labyrinten vår og får opp labelen Antall løsninger.
        pane.setBottom(antL); //Setter labelen nederst i vinduet.

      }
    });
  }

  private class GUIRute extends Rectangle{
    int rad;
    int kol;
    Label antL; //antall løsninger
    boolean merket=false;
    Paint ogFarge; //Original farge, svart eller hvit.

    public GUIRute(double bredde,double hoyde,Paint farge,int rad, int kol,Label antL){
      super(bredde,hoyde,farge);
      this.rad=rad;
      this.kol=kol;
      this.antL=antL;
      this.ogFarge=farge;  //Huske på den gamle fargen.

      setOnMouseClicked(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent event){
          fargUtvei(rad,kol,antL); //Farge utveien fra valgt rute.
        }
      });
    }
    public Paint hentOpprinneligFarge(){
      return ogFarge;
    }
  }
  /**
 * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
 * av losningstien.
 * @param losningString String-representasjon av utveien
 * @param bredde        bredde til labyrinten
 * @param hoyde         hoyde til labyrinten
 * @return              2D-representasjon av rutene der true indikerer at
 *                      ruten er en del av utveien.
 */
static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
    boolean[][] losning = new boolean[hoyde][bredde];
    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
    while(m.find()) {
        int x = Integer.parseInt(m.group(1))-1;
        int y = Integer.parseInt(m.group(2))-1;
        losning[y][x] = true;
    }
    return losning;
  }
  public GridPane lagGridPane(File valgtFil,Label antL){ //Tegner opp labyrintne vår i vinduet.
    GridPane griddet=null;
    try{
      l=Labyrint.lesFraFil(valgtFil);//Leser inn filen.
      rektangelArray=new GUIRute[l.hentAntRader()][l.hentAntKolonner()];//Angir størrelsen på dobbeltarrayen. hentAntRader() ligger i labyrint.java
      griddet=new GridPane();
      int rader=l.hentAntRader();
      int kolonner=l.hentAntKolonner();
      for(int rad=0;rad<rader;rad++){
        for(int kol=0;kol<kolonner;kol++){
          if(l.hentRuteArray()[rad][kol] instanceof HvitRute){ //For alle hvite ruter, lag rektangel med hvit farge.
            rektangelArray[rad][kol]=new GUIRute(15,15,Color.WHITE,rad,kol,antL);
            griddet.add(rektangelArray[rad][kol],kol,rad);
          }
          if(l.hentRuteArray()[rad][kol] instanceof SortRute){ //For alle sorte ruter, lag rektangel med sort fyll.
            rektangelArray[rad][kol]=new GUIRute(15,15,Color.BLACK,rad,kol,antL);
            griddet.add(rektangelArray[rad][kol],kol,rad);
          }
        }
      }
    }catch(FileNotFoundException f){
      System.out.println("FEIL");
      }
      return griddet; //returnerer den ferdige fargede labyrinten.
  }
public void fargUtvei(int rad,int kol,Label antL){

  cleanLabyrint(); //Metoden skal farge labyrinten som den opprinnelig var før vi fant utvei.
  //Gjør dette for at når vi trykker på nytt så skal den utveien vises, og de gamle ikke vises.
  OrdnetLenkeliste<String> utveier=l.finnUtveiFra(kol+1,rad+1); //Finner først utveien.
  antL.setText("Antall loesninger: "+utveier.storrelse());
  String forsteUtvei=utveier.fjern();  //Tar ut den første utveien som dukker opp. En string.
  boolean[][] utveiBoolean=losningStringTilTabell(forsteUtvei,l.hentAntKolonner(),l.hentAntRader()); //Gjør om stringen av utveien til en boolean array.

    for(int r=0;r<l.hentAntRader();r++){
      for(int k=0;k<l.hentAntKolonner();k++){
        if(utveiBoolean[r][k]){ //Hvis ruten er en del av utveien vi skal vise, farg den rosa.
          rektangelArray[r][k].setFill(Color.DEEPPINK);
        }
      }
    }
}
public void cleanLabyrint(){
  for(int i=0;i<l.hentAntRader();i++){
    for(int j=0;j<l.hentAntKolonner();j++){
      Paint fyll=rektangelArray[i][j].hentOpprinneligFarge(); //Går gjennom alle rutene i Labyrinten og henter ut hva den originale fargen var (svart/hvit)
      rektangelArray[i][j].setFill(fyll); //Setter fargen i ruten til å være den originale fargen.
    }
  }
}
}
