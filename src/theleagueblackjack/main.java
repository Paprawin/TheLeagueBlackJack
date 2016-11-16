/*
Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
package theleagueblackjack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author EdselR
 */
public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        gameDatabase database = new gameDatabase();
        
        database.createUser();
        database.createDB();
        
        
        System.out.println("All Suits Aces : ");
       Card [] aces  = new Card[4];
       aces[0] = new Card(Suit.CLUBS , Rank.ACE);
       aces[1] = new Card(Suit.DIAMONDS , Rank.ACE);
       aces[2] = new Card(Suit.HEARTS , Rank.ACE);
       aces[3] = new Card(Suit.SPADES , Rank.ACE);
       
       System.out.println("Suit Rank");
       for(int i = 0 ; i<4 ; i++)
       {
           System.out.println(aces[i].getSuit() + " " + aces[i].getRank());
       }
        
        Pane root = new Pane();
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
