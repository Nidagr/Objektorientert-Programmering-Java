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

public class FilVelger extends Application{
  GUIRute[][] rektangelArray;
  Labyrint l=null;
    @Override
  public void start(Stage stage){
    BorderPane pane =new BorderPane();

    Button filValg=new Button("Velg fil");
    pane.setTop(filValg);
    stage.setScene(new Scene(pane,500,500));
    stage.show();


//Velger alt om skal skje når vi trykke på velg fil knappen. Velger fil og labyrinten skrives ut i vinduet.

    filValg.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent event){

        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Text filer(labyrint.in)","*.in"); //Filteret gjør at vi kun kan velge filer som slutter på .in, altså filene med labyrinter.
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setTitle("Velg labyrintfil");
        File valgtFil=fileChooser.showOpenDialog(null);
        //Labyrint l=null;
        try{
        l=Labyrint.lesFraFil(valgtFil);
        rektangelArray=new GUIRute[l.hentAntRader()][l.hentAntKolonner()];
        GridPane griddet=new GridPane();
        HBox bunnBox=new HBox();
        Label antL=new Label("Antall loesninger:");
        bunnBox.getChildren().add(antL);
        int rader=l.hentAntRader();
        int kolonner=l.hentAntKolonner();
        for(int rad=0;rad<rader;rad++){
          for(int kol=0;kol<kolonner;kol++){
            if(l.hentRuteArray()[rad][kol] instanceof HvitRute){
              rektangelArray[rad][kol]=new GUIRute(15,15,Color.WHITE,rad,kol,antL);
              griddet.add(rektangelArray[rad][kol],kol,rad);
            }
            if(l.hentRuteArray()[rad][kol] instanceof SortRute){
              rektangelArray[rad][kol]=new GUIRute(15,15,Color.BLACK,rad,kol,antL);
              griddet.add(rektangelArray[rad][kol],kol,rad);
            }
          }
        }
        pane.setCenter(griddet);
        pane.setBottom(bunnBox);
      }
      catch(FileNotFoundException f){
        System.out.println("FEIL");
        }
      }
    });


  }

  private class GUIRute extends Rectangle{
    int rad;
    int kol;
    Label antL;
    boolean merket=false;  //?????????????
    public GUIRute(double bredde,double hoyde,Paint farge,int rad, int kol,Label antL){
      super(bredde,hoyde,farge);
      this.rad=rad;
      this.kol=kol;
      this.antL=antL;

      setOnMouseClicked(new EventHandler<MouseEvent>(){
        public void handle(MouseEvent event){
          OrdnetLenkeliste<String> utveier=l.finnUtveiFra(kol+1,rad+1);
          antL.setText("Antall loesninger: "+utveier.storrelse());
          String forsteUtvei=utveier.fjern();  //Tar ut den første utveien som dukker opp. En string.
          boolean[][] utveiBoolean=losningStringTilTabell(forsteUtvei,l.hentAntKolonner(),l.hentAntRader());
          if(merket){
            for(int rad=0;rad<l.hentAntRader();rad++){
              for(int kol=0;kol<l.hentAntKolonner();kol++){

                if(utveiBoolean[rad][kol]){
                  merket=false; //???????????
                  rektangelArray[rad][kol].setFill(Color.WHITE);
                }
              }
            }
          }else{
          for(int rad=0;rad<l.hentAntRader();rad++){
            for(int kol=0;kol<l.hentAntKolonner();kol++){

              if(utveiBoolean[rad][kol]){
                merket=true; //???????????
                rektangelArray[rad][kol].setFill(Color.DEEPPINK);
              }
            }
          }
        }
        }
      });
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
}

//Lag en knapp som heter last inn. Den bruker LesFraFil i oblig 5 og vi bruker instanceof hvit eller svart rute til å setbackground på gridpane-et.
