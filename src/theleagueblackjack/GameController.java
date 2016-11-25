
package theleagueblackjack;

import base.Card;
import base.Hand;
import base.HandAction;
import static base.HandAction.DOUBLE;
import static base.HandAction.HIT;
import static base.HandAction.STAND;
import base.Player;
import base.PlayerType;
import base.Table;
import base.Table.GameMode;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.Pos.CENTER_LEFT;
import javafx.scene.CacheHint;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.MenuBar;
import static javafx.scene.control.OverrunStyle.ELLIPSIS;
import static javafx.scene.control.OverrunStyle.WORD_ELLIPSIS;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import static javafx.scene.effect.BlendMode.SRC_OVER;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import resource.gameDatabase;
import resource.Utils;

/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
public class GameController {

    private Table table;
    private Stage stage;
    private float currentPlayerBet = 5f;   // Initial bet
    private boolean isHuman;    // Checks if added player is human or not
    private ObservableList<Label> msgLabelsList;  // Messages Displayed During game 
    private boolean isDealerHidden = true;  // used to hide dealer
    private Utils utils; // Utilities used 
    private gameDatabase database; // Database for the game

    // Game main Pain containing all the other elements
    private AnchorPane mainPain;

    public AnchorPane getMainPain() {
        return mainPain;
    }

    private Button fiveDollarButton;  // Five Dollar button used in placing bet 

    private Button tenDollarButton;   // Ten Dollar button used in placing bet 

    private Button twentyFiveDollarButton; // Twenty Five Dollar button used in placing bet 

    private Button hundredDollarButton;  // Hundred Dollar button used in placing bet 

    private Button placeBetBtn;     // Placing bet button 

    private Button hitButton;      // Hit Button to draw another card

    private Button standButton;   // Stand Button 

    private Button doubleButton;  // Double Button 

    private Button startButton;   // Starting the game button

    private Button splitButton;   // Split the hand if splitable

    private Button addPlayerButton;  // Add Player in the table button

    private Button skipRoundButton;  // Skip the current round for individual player

    private Label currentPlayerNameLabel;  // Current Player name Label in the bet

    private Label currentPlayerMoneyLabel; // Current Player money label in the bet

    private Label currentPlayerBetAmountLabel; // currentPlayer Total Amount Label

    private Label handValueLabel;   // Current Hand value label

    private HBox cardsHBox;       // Hbox containg the cards

    private HBox actionsBox;     // HBox containg action buttons split , hit etc

    private HBox handValueBox;   // Hbox containg the Hand 

    private HBox chipsBox;       // Hbox containg the chips dollar button

    private HBox placingBetsBox; // Hbox containg button to skip and place bet

    private VBox secondaryPlayersVBox;

    private VBox activePlayerInfoBox;

    private VBox messagesVBox;   // Vbox contain Messages list 

    private VBox pastPlayerVbox;

    private VBox currentPlayerVbox;

    private TextField nameTextBox;

    private RadioButton isHumanRadio;

    private RadioButton isCumputerRadio;

    private Pane enteringPlayersPane;

    private Pane dealerPane;

    private Label nameErrorLabel;

    private HBox dealerCardsHBox;

    private Label dealerValueLabel;

    List<Player> pastPlayers;

    public void initialzeScene() {
        mainPain = new AnchorPane();
        mainPain.setPrefHeight(700.0);
        mainPain.setRotate(0.0);
        mainPain.setCache(true);
        mainPain.setMinWidth(1024.0);
        mainPain.setSnapToPixel(true);
        mainPain.getStyleClass().add("pane");
        mainPain.setMinHeight(700.0);
        mainPain.setPickOnBounds(true);
        mainPain.setDisable(false);
        mainPain.setPrefWidth(700.0);
        mainPain.setFocusTraversable(false);
        mainPain.setId("AnchorPane");
        mainPain.setMouseTransparent(false);
        ImageView backgroundImage = new ImageView();
        InputStream imageInputStream = Utils.class.getResourceAsStream("/resource/images/Background.jpg");
        backgroundImage.setImage(new Image(imageInputStream));
        backgroundImage.setRotate(0.0);
        backgroundImage.setCache(false);
        backgroundImage.setVisible(true);
        backgroundImage.setFitWidth(1024.0);
        backgroundImage.setPreserveRatio(false);
        backgroundImage.setLayoutY(27.0);
        backgroundImage.setPickOnBounds(true);
        backgroundImage.setDisable(false);
        backgroundImage.setFitHeight(741.1500244140625);
        backgroundImage.setFocusTraversable(false);
        backgroundImage.setId("TableBackround");
        backgroundImage.setMouseTransparent(false);
        backgroundImage.setSmooth(true);

// Adding child to parent
        mainPain.getChildren().add(backgroundImage);
        actionsBox = new HBox();
        actionsBox.setPrefHeight(57.0);
        actionsBox.setCache(false);
        actionsBox.setLayoutX(190.0);
        actionsBox.setLayoutY(629.0);
        actionsBox.setMinHeight(19.0);
        actionsBox.setCacheHint(CacheHint.DEFAULT);
        actionsBox.setBlendMode(SRC_OVER);
        actionsBox.setDisable(false);
        actionsBox.setPrefWidth(525.0);
        actionsBox.setFillHeight(true);
        actionsBox.setSpacing(5.0);
        actionsBox.setFocusTraversable(true);
        actionsBox.setId("actionsBox");
        actionsBox.setAlignment(CENTER);
        actionsBox.setMouseTransparent(false);
        hitButton = new Button();
        hitButton.setCache(false);
        hitButton.setDefaultButton(false);
        hitButton.setDepthTest(DepthTest.INHERIT);
        hitButton.setMinWidth(Double.NEGATIVE_INFINITY);
        hitButton.setMnemonicParsing(false);
        hitButton.setMinHeight(Double.NEGATIVE_INFINITY);
        hitButton.setOnAction(myHandler);
        hitButton.setCacheHint(CacheHint.DEFAULT);
        hitButton.setCancelButton(false);
        hitButton.setPrefWidth(80.0);
        hitButton.setTextAlignment(TextAlignment.LEFT);
        hitButton.setFocusTraversable(true);
        hitButton.setText("Hit");
        hitButton.setAlignment(CENTER);
        hitButton.setWrapText(false);

// Adding child to parent
        actionsBox.getChildren().add(hitButton);
        standButton = new Button();
        standButton.setOnAction(myHandler);
        standButton.setCache(false);
        standButton.setPrefWidth(80.0);
        standButton.setStyle("-fx-backround-radius: 10px;");
        standButton.setId("standButton");
        standButton.setText("Stand");
        standButton.setPrefWidth(100.0);
        standButton.setMnemonicParsing(false);

// Adding child to parent
        actionsBox.getChildren().add(standButton);
        doubleButton = new Button();
        doubleButton.setOnAction(myHandler);
        doubleButton.setCache(false);
        doubleButton.setUnderline(false);
        doubleButton.setTextOverrun(WORD_ELLIPSIS);
        doubleButton.setStyle("");
        doubleButton.setPrefWidth(100.0);
        doubleButton.setId("doubleButton");
        doubleButton.setText("Double");
//doubleButton.setHBox.margin($x2);
        doubleButton.setMnemonicParsing(false);
//doubleButton.setFont($x1);

// Adding child to parent
        actionsBox.getChildren().add(doubleButton);
        splitButton = new Button();
        splitButton.setOnAction(myHandler);
        splitButton.setCache(false);
        splitButton.setPrefWidth(80.0);
        splitButton.setStyle("");
        splitButton.setId("splitButton");
        splitButton.setText("Split");
        splitButton.setMnemonicParsing(false);

// Adding child to parent
        actionsBox.getChildren().add(splitButton);

// Adding child to parent
        mainPain.getChildren().add(actionsBox);
        MenuBar menuBar7 = new MenuBar();
        menuBar7.setMinHeight(14.0);
        menuBar7.setPrefHeight(27.0);
        menuBar7.setUseSystemMenuBar(false);
        menuBar7.setPrefWidth(1024.0);

// Adding child to parent
        mainPain.getChildren().add(menuBar7);
        chipsBox = new HBox();
        chipsBox.setSpacing(5.0);
        chipsBox.setPrefWidth(267.0);
        chipsBox.setLayoutX(23.0);
        chipsBox.setId("HBox");
        chipsBox.setLayoutY(629.0);
        chipsBox.setAlignment(CENTER);
        fiveDollarButton = new Button();
        fiveDollarButton.setPrefHeight(57.0);
        fiveDollarButton.setUnderline(false);
        fiveDollarButton.setTextOverrun(ELLIPSIS);
        fiveDollarButton.setMinWidth(43.0);
        fiveDollarButton.getStyleClass().add("FiveDollar");
        fiveDollarButton.setMnemonicParsing(false);
        fiveDollarButton.setMinHeight(43.0);
        fiveDollarButton.setOnAction(myHandler);
        fiveDollarButton.setPickOnBounds(false);
        fiveDollarButton.setCancelButton(false);
        fiveDollarButton.setPrefWidth(58.0);
        fiveDollarButton.setTextAlignment(TextAlignment.LEFT);
        fiveDollarButton.setTextFill(BLACK);
        fiveDollarButton.setStyle("");
        fiveDollarButton.setId("fiveDollarButton");
        fiveDollarButton.setText("5$");
        fiveDollarButton.setWrapText(false);

// Adding child to parent
        chipsBox.getChildren().add(fiveDollarButton);
        tenDollarButton = new Button();
        tenDollarButton.setPrefHeight(57.0);
        tenDollarButton.setUnderline(false);
        tenDollarButton.setTextOverrun(ELLIPSIS);
        tenDollarButton.setMinWidth(43.0);
        tenDollarButton.getStyleClass().add("TenDollar");
        tenDollarButton.setMnemonicParsing(false);
        tenDollarButton.setMinHeight(43.0);
        tenDollarButton.setOnAction(myHandler);
        tenDollarButton.setPickOnBounds(false);
        tenDollarButton.setCancelButton(false);
        tenDollarButton.setPrefWidth(58.0);
        tenDollarButton.setTextAlignment(TextAlignment.LEFT);
        tenDollarButton.setTextFill(BLACK);
        tenDollarButton.setStyle("");
        tenDollarButton.setId("tenDollarButton");
        tenDollarButton.setText("10$");
        tenDollarButton.setWrapText(false);

// Adding child to parent
        chipsBox.getChildren().add(tenDollarButton);
        twentyFiveDollarButton = new Button();
        twentyFiveDollarButton.setPrefHeight(57.0);
        twentyFiveDollarButton.setUnderline(false);
        twentyFiveDollarButton.setTextOverrun(ELLIPSIS);
        twentyFiveDollarButton.setMinWidth(43.0);
        twentyFiveDollarButton.getStyleClass().add("TwentyFiveDollar");
        twentyFiveDollarButton.setMnemonicParsing(false);
        twentyFiveDollarButton.setMinHeight(43.0);
        twentyFiveDollarButton.setOnAction(myHandler);
        twentyFiveDollarButton.setPickOnBounds(false);
        twentyFiveDollarButton.setCancelButton(false);
        twentyFiveDollarButton.setPrefWidth(58.0);
        twentyFiveDollarButton.setTextAlignment(TextAlignment.LEFT);
        twentyFiveDollarButton.setTextFill(BLACK);
        twentyFiveDollarButton.setStyle("");
        twentyFiveDollarButton.setId("twentyFiveDollarButton");
        twentyFiveDollarButton.setText("25$");
        twentyFiveDollarButton.setWrapText(false);
//twentyFiveDollarButton.setFont();

// Adding child to parent
        chipsBox.getChildren().add(twentyFiveDollarButton);
        hundredDollarButton = new Button();
        hundredDollarButton.setPrefHeight(57.0);
        hundredDollarButton.setVisible(true);
        hundredDollarButton.setUnderline(false);
        hundredDollarButton.setTextOverrun(ELLIPSIS);
        hundredDollarButton.setMinWidth(43.0);
        hundredDollarButton.getStyleClass().add("OneHundredDollars");
        hundredDollarButton.setMnemonicParsing(false);
        hundredDollarButton.setMinHeight(48.0);
        hundredDollarButton.setOnAction(myHandler);
        hundredDollarButton.setPickOnBounds(false);
        hundredDollarButton.setCancelButton(false);
        hundredDollarButton.setPrefWidth(68.0);
        hundredDollarButton.setTextAlignment(TextAlignment.LEFT);
        hundredDollarButton.setTextFill(BLACK);
        hundredDollarButton.setFocusTraversable(true);
//hundredDollarButton.setStyle();
        hundredDollarButton.setId("hundredDollarButton");
        hundredDollarButton.setText("100$");
        hundredDollarButton.setWrapText(false);
        hundredDollarButton.setMouseTransparent(false);
//hundredDollarButton.setFont($x4);

// Adding child to parent
        chipsBox.getChildren().add(hundredDollarButton);

// Adding child to parent
        mainPain.getChildren().add(chipsBox);
        dealerPane = new Pane();
        dealerPane.setPrefHeight(200.0);
        dealerPane.setPrefWidth(200.0);
        dealerPane.setLayoutX(394.0);
        dealerPane.setId("dealerPane");
        dealerPane.setLayoutY(82.0);
        dealerPane.setSnapToPixel(true);
        dealerValueLabel = new Label();
        dealerValueLabel.setPrefHeight(20.0);
        dealerValueLabel.setGraphicTextGap(6.0);
        dealerValueLabel.setPrefWidth(155.0);
        dealerValueLabel.setTextAlignment(TextAlignment.LEFT);
        dealerValueLabel.setTextFill(WHITE);
        dealerValueLabel.setLayoutX(23.0);
        dealerValueLabel.setContentDisplay(ContentDisplay.CENTER);
        dealerValueLabel.setId("dealerValueLabel");
        dealerValueLabel.setLayoutY(166.0);
        dealerValueLabel.setText("Dealer");
        dealerValueLabel.setAlignment(CENTER);

// Adding child to parent
        dealerPane.getChildren().add(dealerValueLabel);
        dealerCardsHBox = new HBox();
        dealerCardsHBox.setPrefHeight(156.0);
        dealerCardsHBox.setPrefWidth(200.0);
        dealerCardsHBox.setLayoutX(1.0);
        dealerCardsHBox.setId("dealerCardsHBox");
        dealerCardsHBox.setLayoutY(-16.0);

// Adding child to parent
        dealerPane.getChildren().add(dealerCardsHBox);

// Adding child to parent
        mainPain.getChildren().add(dealerPane);
        secondaryPlayersVBox = new VBox();
        secondaryPlayersVBox.setPrefHeight(574.0);
        secondaryPlayersVBox.setPrefWidth(352.0);
        secondaryPlayersVBox.setId("secondaryPlayersVBox");
        secondaryPlayersVBox.setLayoutY(28.0);

// Adding child to parent
        mainPain.getChildren().add(secondaryPlayersVBox);
        placingBetsBox = new HBox();
        placingBetsBox.setPrefHeight(40.0);
        placingBetsBox.setSpacing(10.0);
        placingBetsBox.setPrefWidth(322.0);
        placingBetsBox.setLayoutX(310.0);
        placingBetsBox.setId("placingBetsBox");
        placingBetsBox.setLayoutY(638.0);
        placingBetsBox.setAlignment(CENTER);
        skipRoundButton = new Button();
        skipRoundButton.setOnAction(myHandler);
        skipRoundButton.setId("skipRoundButton");
        skipRoundButton.setText("Skip Round");
        skipRoundButton.setMnemonicParsing(false);
        skipRoundButton.setPrefWidth(140.0);

// Adding child to parent
        placingBetsBox.getChildren().add(skipRoundButton);
        placeBetBtn = new Button();
        placeBetBtn.setOnAction(myHandler);
        placeBetBtn.setId("placeBetBtn");
        placeBetBtn.setText("Place Bet");
        placeBetBtn.setMnemonicParsing(false);
        placeBetBtn.setPrefWidth(140.0);

// Adding child to parent
        placingBetsBox.getChildren().add(placeBetBtn);

// Adding child to parent
        mainPain.getChildren().add(placingBetsBox);
        handValueBox = new HBox();
        handValueBox.setPrefHeight(34.0);
        handValueBox.setSpacing(20.0);
        handValueBox.setVisible(true);
        handValueBox.setDisable(false);
        handValueBox.setPrefWidth(392.0);
        handValueBox.setFocusTraversable(false);
        handValueBox.setLayoutX(277.0);
        handValueBox.setId("handValueBox");
        handValueBox.setLayoutY(575.0);
        handValueBox.setAlignment(CENTER);
        handValueLabel = new Label();
        handValueLabel.setTextFill(WHITE);
        handValueLabel.setId("handValueLeft");
        handValueLabel.setText("Hand Value:");

// Adding child to parent
        handValueBox.getChildren().add(handValueLabel);
        currentPlayerBetAmountLabel = new Label();
        currentPlayerBetAmountLabel.setTextFill(WHITE);
        currentPlayerBetAmountLabel.setId("handValueLeft");
        currentPlayerBetAmountLabel.setText("Bet Amount:");
//label22.setFont($x5);

// Adding child to parent
        handValueBox.getChildren().add(currentPlayerBetAmountLabel);

// Adding child to parent
        mainPain.getChildren().add(handValueBox);
        activePlayerInfoBox = new VBox();
        activePlayerInfoBox.setPrefHeight(78.0);
        activePlayerInfoBox.setSpacing(15.0);
        activePlayerInfoBox.setPrefWidth(282.0);
        activePlayerInfoBox.setLayoutX(674.0);
        activePlayerInfoBox.setId("activePlayerInfoBox");
        activePlayerInfoBox.setLayoutY(602.0);
        activePlayerInfoBox.setAlignment(CENTER);
        HBox actionsBox4 = new HBox();
        actionsBox4.setPrefHeight(27.0);
        actionsBox4.setPrefWidth(267.0);
        actionsBox4.setAlignment(CENTER_LEFT);
        Label label25 = new Label();
        label25.setTextFill(WHITE);
        label25.setText("Name:");
        label25.setAlignment(CENTER);
//label25.setFont($x3);

// Adding child to parent
        actionsBox4.getChildren().add(label25);
        currentPlayerNameLabel = new Label();
        currentPlayerNameLabel.setTextFill(WHITE);
        currentPlayerNameLabel.setId("currentPlayerNameLabel");
        currentPlayerNameLabel.setText("");
        currentPlayerNameLabel.setAlignment(CENTER);
//currentPlayerNameLabel.setFont($x3);

// Adding child to parent
        actionsBox4.getChildren().add(currentPlayerNameLabel);

// Adding child to parent
        activePlayerInfoBox.getChildren().add(actionsBox4);
        HBox actionsBox7 = new HBox();
        actionsBox7.setPrefHeight(27.0);
        actionsBox7.setPrefWidth(267.0);
        actionsBox7.setAlignment(CENTER_LEFT);
        Label moneyLabel = new Label();
        moneyLabel.setTextFill(WHITE);
        moneyLabel.setId("currentNameLabel");
        moneyLabel.setText("Money:");
//currentPlayerMoneyLabel.setFont($x3);

// Adding child to parent
        actionsBox7.getChildren().add(moneyLabel);
        currentPlayerMoneyLabel = new Label();
        currentPlayerMoneyLabel.setTextFill(WHITE);
        currentPlayerMoneyLabel.setId("currentNameLabel");
        currentPlayerMoneyLabel.setText("");
//label29.setFont($x3);

// Adding child to parent
        actionsBox7.getChildren().add(currentPlayerMoneyLabel);

// Adding child to parent
        activePlayerInfoBox.getChildren().add(actionsBox7);

// Adding child to parent
        mainPain.getChildren().add(activePlayerInfoBox);

        enteringPlayersPane = new Pane();
        enteringPlayersPane.setPrefHeight(639.0);
        enteringPlayersPane.setPrefWidth(689.0);
        enteringPlayersPane.setLayoutX(144.0);
        enteringPlayersPane.setId("enteringPlayersPane");
        enteringPlayersPane.setLayoutY(4.0);

        ScrollPane currentPlayersScrollPane = new ScrollPane();
        currentPlayersScrollPane.setPrefHeight(300.0);
        currentPlayersScrollPane.setPrefWidth(320.0);
        currentPlayersScrollPane.setLayoutX(30.0);
        currentPlayersScrollPane.setLayoutY(360.0);

        currentPlayerVbox = new VBox();
        currentPlayerVbox.setPrefHeight(280.0);
        currentPlayerVbox.setPrefWidth(310.0);
        currentPlayerVbox.setLayoutX(30.0);
        currentPlayerVbox.setLayoutY(360.0);

        currentPlayersScrollPane.setContent(currentPlayerVbox);
// Adding child to parent
        enteringPlayersPane.getChildren().add(currentPlayersScrollPane);

        ScrollPane pastPlayersScrollPane = new ScrollPane();
        pastPlayersScrollPane.setPrefHeight(300.0);
        pastPlayersScrollPane.setPrefWidth(320.0);
        pastPlayersScrollPane.setLayoutX(430.0);
        pastPlayersScrollPane.setLayoutY(360.0);

        pastPlayerVbox = new VBox();
        pastPlayerVbox.setPrefHeight(280.0);
        pastPlayerVbox.setPrefWidth(310.0);
        pastPlayerVbox.setLayoutX(430.0);
        pastPlayerVbox.setLayoutY(360.0);

        pastPlayersScrollPane.setContent(pastPlayerVbox);

// Adding child to parent
        enteringPlayersPane.getChildren().add(pastPlayersScrollPane);
        Label label32 = new Label();
        label32.setPrefHeight(27.0);
        label32.setUnderline(true);
        label32.setPrefWidth(148.0);
        label32.setTextAlignment(TextAlignment.LEFT);
        label32.setTextFill(WHITE);
        label32.setLayoutX(83.0);
        label32.setContentDisplay(ContentDisplay.LEFT);
        label32.setLayoutY(181.0);
        label32.setText("New Player Name :");
        label32.setAlignment(CENTER);
//label32.setFont($x3);

// Adding child to parent
        enteringPlayersPane.getChildren().add(label32);
        nameTextBox = new TextField();
        nameTextBox.setPrefHeight(20.9609375);
        nameTextBox.setPrefWidth(212.0);
        nameTextBox.setLayoutX(239.0);
        nameTextBox.setStyle("-fx-background-color: gray;");
        nameTextBox.setId("playerNameTextField");
        nameTextBox.setLayoutY(182.0);
        nameTextBox.setText("");
        nameTextBox.setPromptText("");

// Adding child to parent
        enteringPlayersPane.getChildren().add(nameTextBox);
        addPlayerButton = new Button();
        addPlayerButton.setPrefHeight(20.9609375);
        addPlayerButton.setOnAction(myHandler);
        addPlayerButton.setPrefWidth(120.0);
        addPlayerButton.setPrefHeight(27.0);
        addPlayerButton.setTextFill(WHITE);
        addPlayerButton.setLayoutX(483.0);
        addPlayerButton.setStyle("-fx-background-radius: 10px;");
        addPlayerButton.setId("addPlayerButton");
        addPlayerButton.setLayoutY(181.0);
        addPlayerButton.setText("Add Player");
        addPlayerButton.setMnemonicParsing(false);
//addPlayerButton.setFont($x1);

// Adding child to parent
        enteringPlayersPane.getChildren().add(addPlayerButton);
        isHumanRadio = new RadioButton();
        isHumanRadio.setPrefHeight(16.0);
        isHumanRadio.setOnAction(myHandler);

        isHumanRadio.setPrefWidth(125.0);
        isHumanRadio.setTextFill(WHITE);
        isHumanRadio.setLayoutX(242.0);
        isHumanRadio.setId("isHumanRadioButton");
        isHumanRadio.setLayoutY(239.0);
        isHumanRadio.setText("Human");
        isHumanRadio.setMnemonicParsing(false);
//isHumanRadio.setFont($x1);

// Adding child to parent
        enteringPlayersPane.getChildren().add(isHumanRadio);
        startButton = new Button();
        startButton.setOnAction(myHandler);

        startButton.setTextFill(WHITE);
        startButton.setLayoutX(300.0);
        startButton.setId("continueButton");
        startButton.setLayoutY(281.0);
        startButton.setText("Start Game");
        startButton.setMnemonicParsing(false);
//startButton.setFont($x1);

// Adding child to parent
        enteringPlayersPane.getChildren().add(startButton);
        nameErrorLabel = new Label();

        nameErrorLabel.setPrefWidth(212.0);
        nameErrorLabel.setTextFill(RED);
        nameErrorLabel.setLayoutX(239.0);
        nameErrorLabel.setId("nameErrorLabel");
        nameErrorLabel.setLayoutY(213.0);
        nameErrorLabel.setText("");
        nameErrorLabel.setAlignment(CENTER);
//nameErrorLabel.setFont($x4);

// Adding child to parent
        enteringPlayersPane.getChildren().add(nameErrorLabel);
        Label label38 = new Label();
        label38.setPrefHeight(27.0);
        label38.setUnderline(true);
        label38.setPrefWidth(370.0);
        label38.setTextAlignment(TextAlignment.LEFT);
        label38.setTextFill(WHITE);
        label38.setLayoutX(0);
        label38.setContentDisplay(ContentDisplay.LEFT);
        label38.setLayoutY(320.0);
        label38.setText("Current Players in Table(Double Click To Remove)");
        label38.setAlignment(CENTER);
        enteringPlayersPane.getChildren().add(label38);

        Label label39 = new Label();
        label39.setPrefHeight(27.0);
        label39.setUnderline(true);
        label39.setPrefWidth(350.0);
        label39.setTextAlignment(TextAlignment.LEFT);
        label39.setTextFill(WHITE);
        label39.setLayoutX(420.0);
        label39.setContentDisplay(ContentDisplay.LEFT);
        label39.setLayoutY(320.0);
        label39.setText("Players played before(Double Click To Add)");
        label39.setAlignment(CENTER);
//label39.setFont($x3);

// Adding child to parent
        enteringPlayersPane.getChildren().add(label39);

        isCumputerRadio = new RadioButton();
        isCumputerRadio.setPrefHeight(16.0);
        isCumputerRadio.setOnAction(myHandler);
        isCumputerRadio.setPrefWidth(125.0);
        isCumputerRadio.setTextFill(WHITE);
        isCumputerRadio.setLayoutX(345.0);
        isCumputerRadio.setId("isHumanRadioButton");
        isCumputerRadio.setLayoutY(239.0);
        isCumputerRadio.setText("Computer");
        isCumputerRadio.setSelected(true);
        isCumputerRadio.setMnemonicParsing(false);
//isCumputerRadio.setFont($x1);

// Adding child to parent
        enteringPlayersPane.getChildren().add(isCumputerRadio);
        Label label42 = new Label();
        label42.setPrefHeight(27.0);
        label42.setUnderline(true);
        label42.setPrefWidth(148.0);
        label42.setTextAlignment(TextAlignment.LEFT);
        label42.setTextFill(WHITE);
        label42.setLayoutX(83.0);
        label42.setContentDisplay(ContentDisplay.LEFT);
        label42.setLayoutY(233.0);
        label42.setText("Player Type");
        label42.setAlignment(CENTER);
//label42.setFont($x3);

// Adding child to parent
        enteringPlayersPane.getChildren().add(label42);
        Label mainLabel = new Label();
        mainLabel.setPrefHeight(60.0);
        mainLabel.setUnderline(true);
        mainLabel.setPrefWidth(300.0);
        mainLabel.setTextAlignment(TextAlignment.LEFT);
        mainLabel.setTextFill(WHITE);
        mainLabel.setLayoutX(220.0);
        mainLabel.setContentDisplay(ContentDisplay.LEFT);
        mainLabel.setLayoutY(83.0);
        mainLabel.setText("Black Jack Game");
        mainLabel.setId("mainLabel");
        mainLabel.setAlignment(CENTER);
//mainLabel.setFont($x3);

// Adding child to parent
        enteringPlayersPane.getChildren().add(mainLabel);

// Adding child to parent
        mainPain.getChildren().add(enteringPlayersPane);
        Pane pane41 = new Pane();
        pane41.setPrefHeight(399.0);
        pane41.setPrefWidth(297.0);
        pane41.setLayoutX(721.0);
        pane41.setId("messagesPane");
        pane41.setLayoutY(30.0);
        messagesVBox = new VBox();
        messagesVBox.setPrefHeight(166.0);
        messagesVBox.setPrefWidth(293.0);
        messagesVBox.setLayoutX(3.0);
        messagesVBox.setId("messagesVBox");
        messagesVBox.setLayoutY(9.0);

// Adding child to parent
        pane41.getChildren().add(messagesVBox);

// Adding child to parent
        mainPain.getChildren().add(pane41);
        cardsHBox = new HBox();
        cardsHBox.setMinHeight(Double.NEGATIVE_INFINITY);
        cardsHBox.setPrefHeight(128.0);
        cardsHBox.setSpacing(2.0);
        cardsHBox.setMaxHeight(Double.NEGATIVE_INFINITY);
        cardsHBox.setPrefWidth(380.0);
        cardsHBox.setLayoutX(338.0);
        cardsHBox.setMinWidth(Double.NEGATIVE_INFINITY);
        cardsHBox.setId("cardsHBox");
        cardsHBox.setLayoutY(447.0);
        cardsHBox.setMaxWidth(Double.NEGATIVE_INFINITY);

// Adding child to parent
        mainPain.getChildren().add(cardsHBox);
    }

    final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>() {

        @Override
        public void handle(final ActionEvent event) {

            if (event.getSource() == startButton) {
                onStartGameClick();
            } else if (event.getSource() == addPlayerButton) {
                onAddPlayerClick();
            } else if (event.getSource() == isHumanRadio) {
                onHumanClick();
            } else if (event.getSource() == isCumputerRadio) {
                onComputerClick();
            }else if (event.getSource() == fiveDollarButton) {
                onFiveClick();
            } else if (event.getSource() == tenDollarButton) {
                onTenClick();
            } else if (event.getSource() == twentyFiveDollarButton) {
                onTwentyFiveClick();
            } else if (event.getSource() == hundredDollarButton) {
                onHundredClick();
            } else if (event.getSource() == placeBetBtn) {
                doPlaceBet();
            } else if (event.getSource() == hitButton) {
                doHit();
            } else if (event.getSource() == standButton) {
                doStand();
            } else if (event.getSource() == doubleButton) {
                doDouble();
            } else if (event.getSource() == splitButton) {
                doSplit();
            } else if (event.getSource() == skipRoundButton) {
                doSkipRound();
            }

        }
    };

    private void onAddPlayerClick() {
        PlayerType playerType = isHuman ? PlayerType.HUMAN : PlayerType.COMPUTER;
        if (table.getPlayer(nameTextBox.getText()) != null) {
            showNameError();
        } else if (table.getPlayers().size() >= 6) {
            showMaxPlayersError();
        } else if (nameTextBox.getText() == null || nameTextBox.getText().trim().isEmpty()) {
            showNameError();
        } else {

            table.addPlayer(nameTextBox.getText(), playerType);
            nameErrorLabel.setText("Player " + nameTextBox.getText() + " added");
            nameErrorLabel.setTextFill(Color.BLACK);
            nameErrorLabel.setVisible(true);

            FadeTransition animation = new FadeTransition(Duration.seconds(0.3));
            animation.setNode(nameErrorLabel);
            animation.setFromValue(0.0);
            animation.setToValue(1.0);
            final Label player = new Label(nameTextBox.getText() + "  Type: " + playerType + "  Funds: $500 ");
            player.setId("player");

            player.setOnMousePressed(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        String[] split = player.getText().split(" ");
                        currentPlayerVbox.getChildren().remove(player);
                        table.removePlayer(split[0]);
                    }
                }
            });
            currentPlayerVbox.getChildren().add(player);
            animation.play();
            nameTextBox.setText("");
        }

    }

    private void createNewGame() {
        table = Table.createDefaultTable();
        msgLabelsList.clear();
        messagesVBox.getChildren().clear();
        pastPlayerVbox.getChildren().clear();
        populatePastPlayersVbox();
        currentPlayerVbox.getChildren().clear();
        updateView();
    }

    private void onStartGameClick() {

        if (table.getPlayers().isEmpty()) {
            nameErrorLabel.setText("No one in the Table !! Add Players");
            nameErrorLabel.setTextFill(Color.BLACK);
            nameErrorLabel.setVisible(true);
            FadeTransition animation = new FadeTransition(Duration.seconds(0.3));
            animation.setNode(nameErrorLabel);
            animation.setFromValue(0.0);
            animation.setToValue(1.0);
            animation.play();
        } else {
            table.setMode(GameMode.PLACING_BETS);
            updateView();
        }
    }

    private void onHumanClick() {
        isHuman = isHumanRadio.isSelected();
        isCumputerRadio.setSelected(false);
    }
    
    private void onComputerClick() {
        isHuman = false;
        isHumanRadio.setSelected(false);
    }

    private void onHitClick() {
        doHit();
    }

    private void doHit() {

        activePlayer().doHit();
        addMessage(activePlayer().getName() + " is HITTING");

        if (activeHand().isBusted()) {

            addMessage(activePlayer().getName() + " is BUSTED! :(");
            activePlayer().doStand();
            onDoneTurn();
        } else {
            updateView();
        }

    }

    private void onStandClick() {
        doStand();
    }

    private void doStand() {
        activePlayer().doStand();
        addMessage(activePlayer().getName() + " is STANDING");

        if (activeHand().isBusted()) {

            addMessage(activePlayer().getName() + " is BUSTED! :(");
        }

        onDoneTurn();
    }

    private void onDoubleClick(ActionEvent event) {
        doDouble();
    }

    private void doDouble() {

        activePlayer().doDouble();
        addMessage(activePlayer().getName() + " DOUBLES his bet");
        onDoneTurn();
        updateView();
        if (activeHand().isBusted()) {

            addMessage(activePlayer().getName() + " is BUSTED! :(");
            activePlayer().doStand();
            onDoneTurn();
        }
    }

    private void onSplitClick(ActionEvent event) {
        doSplit();
    }

    private void doSplit() {

        activePlayer().doSplit();
        addMessage(activePlayer().getName() + " is SPLITTING his hand");
        updateView();
    }

    private void createNewRound() {
        isDealerHidden = true;

        table.setMode(GameMode.PLACING_BETS);
        msgLabelsList.clear();
        messagesVBox.getChildren().clear();
        dealerPane.setVisible(false);
        dealerCardsHBox.getChildren().clear();
        cardsHBox.getChildren().clear();
        updateView();
    }

    public void initialize() {
        utils = new Utils();
        initTooltips();
        messagesVBox.setAlignment(Pos.TOP_RIGHT);

        msgLabelsList = FXCollections.observableArrayList();
        msgLabelsList.addListener(new ListChangeListener<Label>() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                if (!msgLabelsList.isEmpty()) {
                    Label msgLabel = msgLabelsList.get(msgLabelsList.size() - 1);
                    messagesVBox.getChildren().add(msgLabel);
                    final FadeTransition animation = new FadeTransition(Duration.seconds(0.3));
                    animation.setNode(msgLabel);
                    animation.setFromValue(0.0);
                    animation.setToValue(1.0);
                    animation.play();
                }
            }
        });

    }

    private void onFiveClick() {
        this.currentPlayerBet = 5f;
        updateView();
    }

    private void onTenClick() {
        this.currentPlayerBet = 10f;
        updateView();
    }

    private void onTwentyFiveClick() {
        this.currentPlayerBet = 25f;
        updateView();
    }

    private void onHundredClick() {
        this.currentPlayerBet = 100f;
        updateView();
    }

    private void onPlaceBetClick() {
        doPlaceBet();
    }

    private void doPlaceBet() {

        activePlayer().placeInitialBet(currentPlayerBet);
        addMessage(activePlayer().getName() + " placed a bet of " + currentPlayerBet + "$");

        if (table.hasNextPlayer()) {
            table.moveToNextPlayer();
            updateView();
        } else {
            table.setMode(GameMode.ROUND);
            //  saveGameIfSelected();
            addMessage("Starting ROUND...");
            updateView();
        }
    }

    private void onSkipRoundButtonClick(ActionEvent event) {
        doSkipRound();
    }

    private void doSkipRound() {
        activePlayer().setOutOfRound(true);
        addMessage(activePlayer().getName() + " IS NOT playing this round");

        if (table.hasNextPlayer()) {
            table.moveToNextPlayer();
            updateView();
        } else {
            table.setMode(GameMode.ROUND);
            // saveGameIfSelected();
            addMessage("Starting round...");
            updateView();
        }
    }

    private void quitApp() {
        Platform.exit();
    }

    private void initTooltips() {
        Tooltip hitTooltip = new Tooltip("Draw a card");
        Tooltip.install(hitButton, hitTooltip);

        Tooltip standTooltip = new Tooltip("Finish your turn");
        Tooltip.install(standButton, standTooltip);

        Tooltip doubleTooltip = new Tooltip("Double the bet amount");
        Tooltip.install(doubleButton, doubleTooltip);

        Tooltip splitTooltip = new Tooltip("Split your hand to two different bets");
        Tooltip.install(splitButton, splitTooltip);

    }

    private void updateCardsView() {
        cardsHBox.getChildren().clear();
        for (Card c : activeHand().getCards()) {
            ImageView cardView = utils.getCardImageView(c.toString());
            cardsHBox.getChildren().add(cardView);
            FadeTransition animation = new FadeTransition(Duration.seconds(0.3));
            animation.setNode(cardView);
            animation.setFromValue(0.0);
            animation.setToValue(1.0);
            animation.play();
        }
        handValueLabel.setText("Hand Value: " + activeHand().cardsValue());
        currentPlayerBetAmountLabel.setText("Bet Amount: " + String.valueOf(activeHand().getBetAmount()) + "$");

        dealerCardsHBox.getChildren().clear();
        for (Card c : dealer().getCards()) {
            if (isDealerHidden == true && dealer().getCards().get(0) == c) {
                ImageView cardView = utils.getHiddenCardImageView();
                dealerValueLabel.setText("Dealer: Unknown");
                dealerCardsHBox.getChildren().add(cardView);
            } else if (isDealerHidden == true) {
                ImageView cardView = utils.getCardImageView(c.toString());
                dealerValueLabel.setText("Dealer: Unknown");
                dealerCardsHBox.getChildren().add(cardView);
            } else {
                ImageView cardView = utils.getCardImageView(c.toString());
                dealerCardsHBox.getChildren().add(cardView);
                dealerValueLabel.setText("Dealer: " + dealer().cardsValue());
            }
        }

    }

    public void setTable(Table table) {
        this.table = table;
        database = new gameDatabase();
        database.createUser();
        database.createDB();
        initialzeScene();
        initialize();
        populatePastPlayersVbox();
        updateView();
    }

    public void populatePastPlayersVbox() {
        pastPlayers = new ArrayList<>();
        pastPlayers = database.getPastPlayers();
        for (int i = 0; i < pastPlayers.size(); i++) {
            final Label player = new Label(pastPlayers.get(i).getName() + "  Type: " + pastPlayers.get(i).getType() + "  Funds: " + pastPlayers.get(i).getFunds());
            player.setId("player");
            player.setUserData((int) i);
            pastPlayerVbox.getChildren().add(player);

            player.setOnMousePressed(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        pastPlayerVbox.getChildren().remove(player);
                        int index = (int) player.getUserData();
                        table.addPastPlayer(pastPlayers.get(index));
                        currentPlayerVbox.getChildren().add(player);
                        player.setOnMousePressed(new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getClickCount() == 2) {
                                    String[] split = player.getText().split(" ");
                                    currentPlayerVbox.getChildren().remove(player);
                                    table.removePlayer(split[0]);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void updateView() {
        if (table.getMode() == GameMode.PLACING_BETS) {
            // show
            chipsBox.setVisible(true);
            updateChipsBoxButtons();
            placingBetsBox.setVisible(true);
            activePlayerInfoBox.setVisible(true);
            updatePlayerInfoBoxView();

            // hide
            secondaryPlayersVBox.setVisible(false);
            enteringPlayersPane.setVisible(false);
            actionsBox.setVisible(false);
            handValueBox.setVisible(false);
        }

        if (table.getMode() == GameMode.ROUND) {
            placingBetsBox.setVisible(false);
            enteringPlayersPane.setVisible(false);
            chipsBox.setVisible(false);

            activePlayerInfoBox.setVisible(true);
            secondaryPlayersVBox.setVisible(true);
            cardsHBox.setVisible(true);
            dealerPane.setVisible(true);
            handValueBox.setVisible(true);
            actionsBox.setVisible(true);
            updatePlayersListView();
            updateActionsBoxView();
            updatePlayerInfoBoxView();
            updateCardsView();

        }

        if (table.getMode() == GameMode.READING_PLAYERS) {
            secondaryPlayersVBox.setVisible(false);
            cardsHBox.setVisible(false);
            placingBetsBox.setVisible(false);
            handValueBox.setVisible(false);
            actionsBox.setVisible(false);
            dealerPane.setVisible(false);
            chipsBox.setVisible(false);
            activePlayerInfoBox.setVisible(false);

            enteringPlayersPane.setVisible(true);
        }

        if (activePlayer() != null) {
            if (activePlayer().getType() == PlayerType.COMPUTER) {
                handleComputerPlayer();
            }
        }
    }

    private void checkForBlackJack() {
        if (activeHand().isBlackJack()) {
            final float blackJackWinFactor = 2.5f;
            final float bjWinValue = activeHand().getBetAmount() * blackJackWinFactor;
            addMessage(activePlayer().getName() + " has BLACKJACK! :)");
            addMessage(activePlayer().getName() + " gets " + bjWinValue + "$");
            onDoneTurn();
        }
    }

    private void handleComputerPlayer() {
        if (table.getMode() == GameMode.ROUND) {
            handleComputerAction();
        }

        if (table.getMode() == GameMode.PLACING_BETS) {
            handleComputerBet();
        }
    }

    private void handleComputerBet() {
        float computerFunds = activePlayer().getFunds();
        if (computerFunds >= 100) {
            currentPlayerBet = 100;
            doPlaceBet();
        } else if (computerFunds >= 25) {
            currentPlayerBet = 25;
            doPlaceBet();
        } else if (computerFunds >= 10) {
            currentPlayerBet = 10;
            doPlaceBet();
        } else if (computerFunds >= 5) {
            currentPlayerBet = 5;
            doPlaceBet();
        } else {
            onSkipRoundButtonClick(null);
        }
    }

    private void handleComputerAction() {
        final List<HandAction> possibleActions = activeHand().getLegalActions(currentPlayerBet);
        HandAction action;

        if (activeHand().cardsValue() == 11 && possibleActions.contains(HandAction.DOUBLE)) {
            action = HandAction.DOUBLE;
        } else if (activeHand().cardsValue() < 15 && possibleActions.contains(HandAction.HIT)) {
            action = HandAction.HIT;
        } else {
            action = HandAction.STAND;
        }

        doAction(action);
        updateActionsBoxView();
    }

    private void doAction(HandAction action) {
        switch (action) {
            case HIT:
                doHit();
                break;
            case STAND:
                doStand();
                break;
            case DOUBLE:
                doDouble();
                break;
        }
    }

    private void updateChipsBoxButtons() {
        float playerFunds = activePlayer().getFunds();

        fiveDollarButton.setDisable(playerFunds < 5);
        tenDollarButton.setDisable(playerFunds < 10);
        twentyFiveDollarButton.setDisable(playerFunds < 25);
        hundredDollarButton.setDisable(playerFunds < 100);
        placeBetBtn.setDisable(playerFunds < 5);
    }

    private void changeMode(GameMode gameMode) {
        updateView();
    }

    private void addMessage(String message) {
        String cssString = "-fx-font: regular 14px \"Arial\"; -fx-text-fill: white;";
        Label msgLabel = new Label(message);
        msgLabel.setStyle(cssString);
        if (messagesVBox.getChildren().size() >= 35) {
            messagesVBox.getChildren().remove(0);
        }

        msgLabelsList.add(msgLabel);

    }

    private void updatePlayerInfoBoxView() {
        currentPlayerNameLabel.setText(table.getCurrentPlayer().getName());
        currentPlayerMoneyLabel.setText(String.valueOf(table.getCurrentPlayer().getFunds()) + "$");
    }

    private void onDoneTurn() {
        // move to next hand if the player has more than one hand
        if (activePlayer().hasMoreHands()) {
            activePlayer().switchHands();
            updateView();
        } // move to next player
        else if (table.hasNextPlayer()) {
            table.moveToNextPlayer();
            updateView();
        } // everyone done playing
        else {
            doDealerMove();
            computeResults();
            updateCardsView();
            updatePlayersListView();

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    optionPane();
                }
            });

            database.insertAndUpdatePlayers(table.getPlayers(), pastPlayers);
            System.out.println("Game Over");
        }
    }

    private void optionPane() {

        String[] options = new String[]{"Next Round", "New Game", "Exit"};
        int response = JOptionPane.showOptionDialog(null, "Chooose the Following Option", "Select",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (response == 0) {
            createNewRound();
        } else if (response == 2) {
            quitApp();
        } else {
            createNewGame();
        }
    }

    private void computeResults() {
        for (Player p : table.getPlayers()) {
            for (Hand hand : p.getHands()) {
                if (!hand.isBusted()) {
                    if (hand.isBlackJack()) {
                        final float blackjackFactor = 2.5f;
                        p.setFunds(p.getFunds() + hand.getBetAmount() * blackjackFactor);
                        addMessage(p.getName() + " has BLACKJACK!");
                        addMessage(p.getName() + " now has " + p.getFunds() + "$");
                        p.addWins();
                    }
                    if (hand.cardsValue() < dealer().cardsValue() && !dealer().isBusted()) {
                        addMessage(p.getName() + " LOST the round");
                        addMessage(p.getName() + " now has " + p.getFunds() + "$");
                        p.addLosses();
                    }
                    if (hand.cardsValue() == dealer().cardsValue() && !dealer().isBusted()) {
                        p.setFunds(p.getFunds() + hand.getBetAmount());
                        addMessage(p.getName() + " GETS his money back: " + hand.getBetAmount() + "$");
                        addMessage(p.getName() + " now has " + p.getFunds() + "$");
                        p.addWins();
                    }
                    if (hand.cardsValue() > dealer().cardsValue() || dealer().isBusted()) {
                        final float normalWinFactor = 2.0f;
                        final float winAmount = hand.getBetAmount() * normalWinFactor;
                        p.setFunds(p.getFunds() + winAmount);
                        addMessage(p.getName() + " WINS " + winAmount + "$");
                        addMessage(p.getName() + " now has " + p.getFunds() + "$");
                        p.addWins();
                    }

                }
            }
        }
        addMessage("Round Over");

        for (Node node : actionsBox.getChildren()) {
            node.setDisable(true);
        }

    }

    private void doDealerMove() {
        isDealerHidden = false;
        final int dealerMax = 17;
        while (dealer().cardsValue() < dealerMax) {
            dealer().drawCardFrom(table.getDeck());
            addMessage("Dealer is Hitting");
        }
        if (dealer().isBusted()) {
            addMessage("Dealer is BUSTED!");
        }
        updateCardsView();
    }

    private void showNameError() {
        if (nameTextBox.getText().trim().isEmpty()) {
            nameErrorLabel.setText("Name can't be empty");
        } else {
            nameErrorLabel.setText("Name " + nameTextBox.getText() + " already exists");
        }
        nameErrorLabel.setTextFill(Color.BLACK);
        FadeTransition animation = new FadeTransition(Duration.seconds(0.3));
        animation.setNode(nameErrorLabel);
        animation.setFromValue(0.0);
        animation.setToValue(1.0);
        animation.play();
    }

    private void hideNameError() {
        if (nameErrorLabel != null) {
            FadeTransition animation = new FadeTransition(Duration.seconds(0.3));
            animation.setNode(nameErrorLabel);
            animation.setFromValue(0.0);
            animation.setToValue(1.0);
            animation.play();
        }
    }

    private void updateActionsBoxView() {
        for (Node node : actionsBox.getChildren()) {
            node.setDisable(true);
        }

        List<HandAction> possibleActions = activeHand().getLegalActions(table.getCurrentPlayer().getFunds());
        for (HandAction handAction : possibleActions) {
            if (handAction == HandAction.DOUBLE) {
                doubleButton.setDisable(false);
            }
            if (handAction == HandAction.STAND) {
                standButton.setDisable(false);
            }
            if (handAction == HandAction.HIT) {
                hitButton.setDisable(false);
            }
            if (handAction == HandAction.SPLIT) {
                splitButton.setDisable(false);
            }
        }

    }

    private void updatePlayersListView() {

        secondaryPlayersVBox.getChildren().clear();
        for (Player p : table.getPlayers()) {
            for (Hand h : p.getHands()) {
                HBox playerBox = utils.getHandHBox(p, h, activePlayer(), activeHand());
                secondaryPlayersVBox.getChildren().add(playerBox);
            }
        }

    }

    private void showMaxPlayersError() {
        nameErrorLabel.setText("You already have six players");
        nameErrorLabel.setTextFill(Color.BLACK);
        nameErrorLabel.setTextFill(Color.BLACK);
        FadeTransition animation = new FadeTransition(Duration.seconds(0.3));
        animation.setNode(nameErrorLabel);
        animation.setFromValue(0.0);
        animation.setToValue(1.0);
        animation.play();
    }

    private Player activePlayer() {
        return table.getCurrentPlayer();
    }

    private Hand activeHand() {
        return activePlayer().getCurrentHand();
    }

    private Hand dealer() {
        return table.getDealer();
    }

}
