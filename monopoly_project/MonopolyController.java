package test;

/**
 *
 * @author Ma Man To Tony
 */
public class MonopolyController {
    
    private MonopolyView view;
    private MonopolyModel model;
    
    public void setView(MonopolyView v) {
        this.view = v;        
    }
    
    public void setModel(MonopolyModel m) {
        this.model = m;        
    }
    
    public void createPlayer(String[] tempPlayerName) {
        model.createPlayer(tempPlayerName);
        view.showMessage("player created.");
    }
    
    public void createBoard() {
        model.createBoard();
        view.showMessage("board initialized.");
    }

    public void initSlot() {
        model.initSlot();
        view.showMessage("initialized slots.");
    }

    public void getAllPlayerStatus() {
        view.showMessage(model.getAllPlayerStatus());
    }

    public void rollDice(int playerTurn) {
        view.showMessage(model.playerMovement(playerTurn));
    }
    
}
