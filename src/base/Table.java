package base;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 Group: Edsel Rudy, Giancarlo Soriano, Jasmine Santos, Paprawin Boonyakida

 */
public class Table {

    private final int maxPlayers;             // Maximum players in the blackjack table 
    private final float defaultFunds;         // default funds for all the new players 
    private final String tableName;           // table name
    private final Deck deck;                  // Deck used on the table 
    private List<Player> players;             // Total players present on the table 
    private List<Player> currentRoundPlayers; // Total players in a round 
    private List<Player> currentPlayersList;  // Total players currently playing 
    private Hand dealer;                      // Dealers Hand on the table 
    private GameMode gameMode;                // Current game Mode
    private Player currentPlayer;             // Player whose turn is on the table 

    // Contructor 
    public Table(int maxPlayers, float defaultFunds, String name) {
        this.maxPlayers = maxPlayers;
        this.defaultFunds = defaultFunds;
        this.tableName = name;
        this.deck = new Deck();
        this.dealer = null;
        this.players = new LinkedList<>();
        this.currentRoundPlayers = null;
        this.gameMode = GameMode.READING_PLAYERS;
        setMode(gameMode);
        this.currentPlayer = null;
    }

    // Return a default table 
    public static Table createDefaultTable() {
        Table retTable = new Table(6, 500, "Table of 500");
        return retTable;
    }
    
    // get the current player
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    // Move to next player in the table
    public void moveToNextPlayer() {
        int idx = currentPlayersList.indexOf(currentPlayer);
        currentPlayer = currentPlayersList.get(++idx);
    }
    
    // Checking if there is any player to play in the round 
    public boolean hasNextPlayer() {
        int idx = currentPlayersList.indexOf(currentPlayer);
        return idx < currentPlayersList.size() - 1;
    }
    
    // Creaing new hand for the dealer 
    public void createNewDealer() {
        this.dealer = Hand.newEmptyHand(0);
    }

    // Set the game current mode and initializing according to it 
    public final void setMode(GameMode gameMode) {
        this.gameMode = gameMode;
        initMode(gameMode); // Initialize according to game mode
    }
   
    
    // Current Game mode 
    public enum GameMode {
        READING_PLAYERS,
        PLACING_BETS,
        ROUND;
    }
    
    // get dealer hand 
    public Hand getDealer() {
        return this.dealer;
    }

    // Get current game mode
    public GameMode getMode() {
        return this.gameMode;
    }

    // Get Deck of the table 
    public Deck getDeck() {
        return this.deck;
    }
    
    // Add player in the table 
    public void addPlayer(String playerName, PlayerType playerType) {
        this.players.add(new Player(playerName, playerType, this));
    }
    
    public void addPastPlayer(Player player)
    {
        player.setTable(this);
        this.players.add(player);
        
    }
    
    // Remove player from the table by name 
    public void removePlayer(String playerName) {
        for (Player p : this.players) {
            if (p.getName().equals(playerName)) {
                int idx = this.players.indexOf(p);
                this.players.remove(idx);
            }
        }
    }
    
    // Get player by name from the table 
    public Player getPlayer(String name) {
        for (Player p : this.players) {
            if (p.getName().equals(name))
                return p;
        }
        return null;
    }
    
    public Object checkPastPlayer(String name , List<Player> pastPlayer) {

        for(int i = 0 ; i < pastPlayer.size() ; i++){
            if (pastPlayer.get(i).getName().equals(name))
            {
               final int  index1 = i;
               final Player player1 = pastPlayer.get(i);
               return  new Object(){ public int index = index1; public Player player = player1; };
                
            }
        }
        return null;
    }
    
    // get player list 
    public List<Player> getPlayers() {
        if (this.gameMode == GameMode.READING_PLAYERS)
            return this.players;
        else
            return this.currentPlayersList;
    }

    // Inititalize the game modes 
    private void initMode(GameMode gameMode) {
        switch (gameMode) {
            case PLACING_BETS:
                initPlacingBetsMode();
                break;
            case ROUND:
                initRoundMode();
                break;
            case READING_PLAYERS:
                initReadingPlayersMode();
                break;
        }

    }
    
    private void updateCurrentRoundPlayers() {
        currentRoundPlayers = new LinkedList<>();
        
        for (Player p : players) {
            if (!p.isOutOfRound())
                currentRoundPlayers.add(p);
        }
    }
    
    private void initPlacingBetsMode() {
        if (currentRoundPlayers != null) {
            currentRoundPlayers.clear();
        }
        else {
            currentRoundPlayers = new ArrayList<>();
        }
        for (Player p : players) {
            p.dropHands();
        }
        this.dealer = Hand.newEmptyHand(0);
        this.currentPlayersList = this.players;
        this.currentPlayer = currentPlayersList.get(0);
    }

    private void initRoundMode() {
        updateCurrentRoundPlayers();
        for (Player p : currentRoundPlayers) {
            for (int i = 0; i < 2; ++i) 
                p.getCurrentHand().drawCardFrom(deck);
        }
        for (int i = 0; i < 2; ++i)
                dealer.drawCardFrom(deck);
        
        currentPlayersList = currentRoundPlayers;
        currentPlayer = currentPlayersList.get(0);
    }
    
    
    private void initReadingPlayersMode() {
        currentPlayersList = null;
        players = new LinkedList<>();
        currentRoundPlayers = null;
        dealer = null;
        currentPlayer = null;
        deck.shuffle();
    }
    
    private void clearTable() {
        this.dealer = null;
        this.players = new LinkedList<>();
        this.currentRoundPlayers = null;
        this.gameMode = GameMode.READING_PLAYERS;
        setMode(gameMode);
        this.currentPlayer = null;
    }
    
}
    
    


