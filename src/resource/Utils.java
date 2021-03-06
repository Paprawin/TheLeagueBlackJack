/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
package resource;

import base.Card;
import base.Hand;
import base.Player;
import base.PlayerType;
import static base.PlayerType.COMPUTER;
import static base.PlayerType.HUMAN;
import java.io.InputStream;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Utils {

    public final String RESOURCES_FOLDER = "/resource/";
    public final String IMAGE_FOLDER = RESOURCES_FOLDER + "images/";
    public final String CARD_FOLDER = "Card/";

    public Image getImage(String imageName) {
        InputStream imageInputStream = Utils.class.getResourceAsStream(IMAGE_FOLDER + imageName);
        return new Image(imageInputStream);
    }

    public ImageView getImageView(String imageName) {
        return new ImageView(getImage(imageName));
    }

    public ImageView getCardImageView(String imageName) {
        return new ImageView(getImage(CARD_FOLDER + imageName.toLowerCase() + ".png"));
    }

    public ImageView getHiddenCardImageView() {
        return new ImageView(getImage(CARD_FOLDER + "Backside.png"));
    }

    public ImageView getIconImageView(PlayerType playerType) {
        switch (playerType) {
            case HUMAN:
                return new ImageView(getImage("human.jpg"));
            case COMPUTER:
                return new ImageView(getImage("robot.png"));
            default:
                return new ImageView(getImage("human.jpg"));
        }
    }

    public HBox getHandHBox(Player p, Hand h, Player activePlayer, Hand activeHand) {
        // default config for all players
        final HBox playerBox = new HBox(8);
        playerBox.setPadding(new Insets(5, 5, 5, 5));
        playerBox.setMaxHeight(110);
        playerBox.setPrefWidth(322);
        ImageView icon = getIconImageView(p.getType());
        icon.setFitHeight(42);
        icon.setPreserveRatio(true);
        Label nameLbl = new Label(p.getName());
        nameLbl.setAlignment(Pos.CENTER_LEFT);
        nameLbl.setPrefHeight(playerBox.getHeight());
        nameLbl.setStyle("-fx-font: regular 14px \"Arial\"; -fx-text-fill: white;");
        Label valueLabel = new Label("Hand: " + String.valueOf(h.cardsValue()));
        valueLabel.setStyle("-fx-font: regular 14px \"Arial\";");
        valueLabel.setAlignment(Pos.CENTER_RIGHT);
        valueLabel.setPrefHeight(playerBox.getHeight());
        playerBox.getChildren().addAll(icon, nameLbl, valueLabel);

        // set color according to hand value
        Color color = Color.RED;
        if (p.getCurrentHand().cardsValue() > 21) {
            color = Color.RED;
        }
        if (p.getCurrentHand().cardsValue() == 21) {
            color = Color.GREEN;
        }
        if (p.getCurrentHand().cardsValue() < 21) {
            color = Color.YELLOW;
        }
        valueLabel.setTextFill(color);

        // add cards images
        for (Card card : h.getCards()) {
            final ImageView smallCard;
            smallCard = getCardImageView(card.toString().toLowerCase());
            smallCard.setFitHeight(42);
            smallCard.setPreserveRatio(true);

            smallCard.setOnMouseEntered(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    //smallCard.setFitHeight(80);
                    ScaleTransition st = new ScaleTransition(Duration.millis(500), smallCard);
                    st.setToX(1.8);
                    st.setToY(1.8);
                    st.play();
                }
            });

            smallCard.setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    //smallCard.setFitHeight(42);
                    ScaleTransition st = new ScaleTransition(Duration.millis(500), smallCard);
                    st.setToX(1);
                    st.setToY(1);
                    st.play();
                }
            });
            playerBox.getChildren().add(smallCard);
        }

        // install tooltips
        Tooltip tt = new Tooltip("Money: " + p.getFunds() + "$" + "\nBet: " + h.getBetAmount() + "$");
        Tooltip.install(playerBox, tt);

        // add blinking if this is the active hand in the main view
        if (activePlayer.getName().equals(p.getName()) && h == activeHand) {
            nameLbl.setStyle("-fx-font: regular 14px \"Arial\"; -fx-text-fill: black;");
            final FadeTransition animation = new FadeTransition(Duration.millis(1400));
            animation.setNode(playerBox);
            animation.setFromValue(0.0);
            animation.setToValue(1.0);
            animation.setCycleCount(Animation.INDEFINITE);
            animation.setAutoReverse(true);
            animation.setToValue(1.0);
            animation.play();
        }

        playerBox.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ScaleTransition st = new ScaleTransition(Duration.millis(500), playerBox);
                st.setToX(1.3);
                st.setToY(1.3);
                st.play();
            }
        });

        playerBox.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ScaleTransition st = new ScaleTransition(Duration.millis(500), playerBox);
                st.setToX(1);
                st.setToY(1);
                st.play();
            }
        });

        return playerBox;
    }

}
