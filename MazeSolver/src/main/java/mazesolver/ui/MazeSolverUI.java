package mazesolver.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import mazesolver.logic.DeadEndFilling;
import mazesolver.logic.Maze;
import mazesolver.logic.WallFollower;
import mazesolver.util.List;
import mazesolver.util.Pair;

/**
 * Ohjelman käyttöliittymästä vastaava luokka
 */
public class MazeSolverUI extends Application {

    private List<Pair<Integer, Integer>> generointiPolku;
    private List<Integer> wallFollowerPolku;
    private List<Integer> deadEndFillingPolku;
    private Timeline aikajana;
    private GridPane ruudukko;
    
    private Maze labyrintti;
    private WallFollower wallFollower;
    private DeadEndFilling deadEndFilling;

    private int labyrintinKoko;
    private int kierros;

    private Parent luoPaanakyma() {
        labyrintinKoko = 20;
        int ruudunKoko = 30;

        labyrintti = new Maze(labyrintinKoko);

        HBox pohja = new HBox(luoOhjauspaaneli(), luoRuudukko(labyrintinKoko, ruudunKoko));
        pohja.setStyle("-fx-background-color: white");

        return pohja;
    }

    private VBox luoOhjauspaaneli() {
        VBox ohjauspaneeli = new VBox();

        CheckBox valintaruutu = new CheckBox("Animoi");
        
        Button luoLabyrinttiNappi = new Button("Luo labyrintti");
        luoLabyrinttiNappi.setPrefWidth(140);
        luoLabyrinttiNappi.setOnAction(value -> {
            if (aikajana != null) aikajana.stop();
            alustaSeinat();
            alustaVarit();
            labyrintti.luoLabyrintti();
            generointiPolku = labyrintti.haePolku();
            
            if (valintaruutu.isSelected()) {
                animoi("DFS", generointiPolku.koko() - 1);
            } else {
                for (int i = 0; i < generointiPolku.koko() - 1; i++) piirraLabyrintti(i);
                alustaVarit();
            }
        });
        
        Button wallFollowerNappi = new Button("Wall follower");
        wallFollowerNappi.setPrefWidth(140);
        wallFollowerNappi.setOnAction(value -> {
            if (aikajana != null) aikajana.stop();
            alustaVarit();
            wallFollower = new WallFollower(labyrintti.haeVerkko(), labyrintinKoko);
            wallFollower.ratkaise();
            wallFollowerPolku = wallFollower.haePolku();
            
            if (valintaruutu.isSelected()) {
                animoi("WallFollower", wallFollowerPolku.koko());
            } else {
                for (int i = 0; i < wallFollowerPolku.koko(); i++) piirraWallFollower(i);
            }
        });
        
        Button deadEndFillingNappi = new Button("Dead end filling");
        deadEndFillingNappi.setPrefWidth(140);
        deadEndFillingNappi.setOnAction(value -> {
            if (aikajana != null) aikajana.stop();
            alustaVarit();
            deadEndFilling = new DeadEndFilling(labyrintti.haeVerkko(), labyrintinKoko);
            deadEndFilling.ratkaise();
            deadEndFillingPolku = deadEndFilling.haePolku();
            
            if (valintaruutu.isSelected()) {
                animoi("DeadEndFilling", deadEndFillingPolku.koko());
            } else {
                for (int i = 0; i < deadEndFillingPolku.koko(); i++) piirraDeadEndFilling(i);
            }
        });
        
        ohjauspaneeli.getChildren().addAll(luoLabyrinttiNappi, wallFollowerNappi, deadEndFillingNappi, valintaruutu);
        ohjauspaneeli.setStyle("-fx-background-color: #f5f5f5");
        ohjauspaneeli.setPadding(new Insets(5));
        ohjauspaneeli.setPrefWidth(150);
        ohjauspaneeli.setSpacing(5);

        return ohjauspaneeli;
    }

    private GridPane luoRuudukko(int labyrintinKoko, int ruudunKoko) {
        ruudukko = new GridPane();
        ruudukko.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        HBox.setMargin(ruudukko, new Insets(ruudunKoko));

        for (int y = 0; y < labyrintinKoko; y++) {
            for (int x = 0; x < labyrintinKoko; x++) {
                Region ruutu = new Region();
                ruutu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                ruutu.setPrefSize(ruudunKoko, ruudunKoko);
                ruudukko.add(ruutu, x, y);
            }
        }

        return ruudukko;
    }
    
    private void alustaSeinat() {
        for (int i = 0; i < labyrintinKoko * labyrintinKoko; i++) {
            Region ruutu = (Region) ruudukko.getChildren().get(i);
            ruutu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        }
    }
    
    private void alustaVarit() {
        for (int i = 0; i < labyrintinKoko * labyrintinKoko; i++) {
            Region ruutu = (Region) ruudukko.getChildren().get(i);
            ruutu.setStyle("-fx-background-color: white");
        }
    }

    private void piirraLabyrintti(int i) {
        int x1 = generointiPolku.hae(i).haeAvain();
        int y1 = generointiPolku.hae(i).haeArvo();
        int x2 = generointiPolku.hae(i + 1).haeAvain();
        int y2 = generointiPolku.hae(i + 1).haeArvo();

        Region nykyinenRuutu = (Region) ruudukko.getChildren().get(x1 + labyrintinKoko * y1);
        nykyinenRuutu.setStyle("-fx-background-color: white");
        BorderStroke reuna1 = nykyinenRuutu.getBorder().getStrokes().get(0);

        Region seuraavaRuutu = (Region) ruudukko.getChildren().get(x2 + labyrintinKoko * y2);
        seuraavaRuutu.setStyle("-fx-background-color: gray");
        BorderStroke reuna2 = seuraavaRuutu.getBorder().getStrokes().get(0);

        if (x1 == x2) {
            if (y1 > y2) {
                nykyinenRuutu.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.NONE, reuna1.getRightStyle(), reuna1.getBottomStyle(), reuna1.getLeftStyle(), reuna1.getRadii(), reuna1.getWidths(), reuna1.getInsets())));
                seuraavaRuutu.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, reuna2.getTopStyle(), reuna2.getRightStyle(), BorderStrokeStyle.NONE, reuna2.getLeftStyle(), reuna2.getRadii(), reuna2.getWidths(), reuna2.getInsets())));
            } else {
                nykyinenRuutu.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, reuna1.getTopStyle(), reuna1.getRightStyle(), BorderStrokeStyle.NONE, reuna1.getLeftStyle(), reuna1.getRadii(), reuna1.getWidths(), reuna1.getInsets())));
                seuraavaRuutu.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, BorderStrokeStyle.NONE, reuna2.getRightStyle(), reuna2.getBottomStyle(), reuna2.getLeftStyle(), reuna2.getRadii(), reuna2.getWidths(), reuna2.getInsets())));
            }
        } else if (y1 == y2) {
            if (x1 > x2) {
                nykyinenRuutu.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, reuna1.getTopStyle(), reuna1.getRightStyle(), reuna1.getBottomStyle(), BorderStrokeStyle.NONE, reuna1.getRadii(), reuna1.getWidths(), reuna1.getInsets())));
                seuraavaRuutu.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, reuna2.getTopStyle(), BorderStrokeStyle.NONE, reuna2.getBottomStyle(), reuna2.getLeftStyle(), reuna2.getRadii(), reuna2.getWidths(), reuna2.getInsets())));
            } else {
                nykyinenRuutu.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, reuna1.getTopStyle(), BorderStrokeStyle.NONE, reuna1.getBottomStyle(), reuna1.getLeftStyle(), reuna1.getRadii(), reuna1.getWidths(), reuna1.getInsets())));
                seuraavaRuutu.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, reuna2.getTopStyle(), reuna2.getRightStyle(), reuna2.getBottomStyle(), BorderStrokeStyle.NONE, reuna2.getRadii(), reuna2.getWidths(), reuna2.getInsets())));
            }
        }
    }

    private void piirraWallFollower(int i) {
        Region ruutu = (Region) ruudukko.getChildren().get(wallFollowerPolku.hae(i));
        ruutu.setStyle("-fx-background-color: #ffcccb");
    }
    
    private void piirraDeadEndFilling(int i) {
        Region ruutu = (Region) ruudukko.getChildren().get(deadEndFillingPolku.hae(i));
        ruutu.setStyle("-fx-background-color: gray");
    }

    private void animoi(String algoritmi, int kierrosMaara) {
        kierros = 0;

        aikajana = new Timeline(new KeyFrame(Duration.millis(25), (ActionEvent event) -> {
            if (algoritmi.equals("DFS")) {
                piirraLabyrintti(kierros++);
            } else if (algoritmi.equals("WallFollower")) {
                piirraWallFollower(kierros++);
            } else if (algoritmi.equals("DeadEndFilling")) {
                piirraDeadEndFilling(kierros++);
            }
        }));
        aikajana.setCycleCount(kierrosMaara);
        aikajana.play();
        if (algoritmi.equals("DFS")) aikajana.setOnFinished(event -> alustaVarit());
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(luoPaanakyma()));
        stage.setTitle("LABYRINTIN RATKAISIJA");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
