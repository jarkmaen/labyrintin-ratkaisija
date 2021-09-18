package mazesolver.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import mazesolver.logic.Maze;
import mazesolver.util.List;
import mazesolver.util.Pair;

/**
 * Ohjelman käyttöliittymästä vastaava luokka
 */
public class MazeSolverUI extends Application {

    private List<Pair<Integer, Integer>> polku;
    private GridPane ruudukko;
    private Timeline aikajana;
    
    private int labyrintinKoko;
    private int kierros;

    private Parent luoPaanakyma() {
        labyrintinKoko = 20;
        int ruudunKoko = 30;
        
        Maze labyrintti = new Maze(labyrintinKoko);
        polku = labyrintti.haePolku();
        
        HBox pohja = new HBox();
        pohja.getChildren().addAll(luoOhjauspaaneli(), luoRuudukko(labyrintinKoko, ruudunKoko));

        return pohja;
    }
    
    private VBox luoOhjauspaaneli() {
        VBox ohjauspaneeli = new VBox();

        ohjauspaneeli.setStyle("-fx-background-color: #f5f5f5");
        ohjauspaneeli.setPrefWidth(150);

        return ohjauspaneeli;
    }
    
    private GridPane luoRuudukko(int labyrintinKoko, int ruudunKoko) {
        ruudukko = new GridPane();
        ruudukko.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ruudukko.setPrefSize(labyrintinKoko * ruudunKoko, labyrintinKoko * ruudunKoko);
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

    private void piirraLabyrintti(int i) {
        int x1 = polku.hae(i).haeAvain();
        int y1 = polku.hae(i).haeArvo();
        int x2 = polku.hae(i + 1).haeAvain();
        int y2 = polku.hae(i + 1).haeArvo();

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
    
    private void animoi() {
        kierros = 0;
        
        aikajana = new Timeline(new KeyFrame(Duration.millis(50), (ActionEvent event) -> {
            piirraLabyrintti(kierros++);
        }));
        aikajana.setCycleCount(polku.koko() - 1);
        aikajana.play();
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(luoPaanakyma()));
        stage.setTitle("LABYRINTIN RATKAISIJA");
        stage.setResizable(false);
        stage.show();
        animoi();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
