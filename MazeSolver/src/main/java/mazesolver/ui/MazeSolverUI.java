package mazesolver.ui;

import javafx.application.Application;
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

public class MazeSolverUI extends Application {

    private Parent luoPaanakyma() {
        HBox pohja = new HBox();
        VBox ohjauspaneeli = new VBox();
        GridPane ruudukko = new GridPane();

        int labyrintinKoko = 10;
        int ruudunKoko = 40;

        for (int y = 0; y < labyrintinKoko; y++) {
            for (int x = 0; x < labyrintinKoko; x++) {
                Region ruutu = new Region();
                ruutu.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                ruutu.setPrefSize(ruudunKoko, ruudunKoko);
                ruutu.setTranslateX(x * ruudunKoko - x);
                ruutu.setTranslateY(y * ruudunKoko - y);
                ruudukko.getChildren().add(ruutu);
            }
        }

        int labyrinttiIkkunanKoko = labyrintinKoko * ruudunKoko - labyrintinKoko + ruudunKoko * 2 + 1;
        ruudukko.setPrefSize(labyrinttiIkkunanKoko, labyrinttiIkkunanKoko);
        ruudukko.setPadding(new Insets(ruudunKoko));

        ohjauspaneeli.setStyle("-fx-background-color: #f5f5f5");
        ohjauspaneeli.setPrefWidth(150);
        pohja.getChildren().addAll(ohjauspaneeli, ruudukko);

        return pohja;
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(luoPaanakyma()));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
