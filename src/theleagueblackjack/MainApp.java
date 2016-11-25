
package theleagueblackjack;

import base.Table;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
public class MainApp extends Application {

    private Table table;

    @Override
    public void start(Stage stage) throws Exception {

        
        table = Table.createDefaultTable();

        //FXMLLoader fxmlLoader = new FXMLLoader();
        //URL url = getClass().getResource("BlackJackDesign.fxml");
        //fxmlLoader.setLocation(url);
       // Parent root = (Parent) fxmlLoader.load(url.openStream());
        // Parent root = FXMLLoader.load(getClass().getResource("TableFXML.fxml"));
        GameController tableController = new GameController();
        tableController.setTable(table);
        tableController.setStage(stage);
        stage.setTitle("BlackJack Game");
        stage.setResizable(true);
        Scene scene = new Scene(tableController.getMainPain());
        scene.setRoot(tableController.getMainPain());
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
