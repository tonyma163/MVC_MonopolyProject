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
        view.showMessage("players created.");
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

    public boolean checkSlot(int playerTurn) {
        //check is purchase
        boolean isPurchase = model.checkSlotIsPurchase(playerTurn);
        
        return isPurchase;
    }

    public void purchaseSlot(int playerTurn) {
        view.showMessage(model.purchaseLand(playerTurn));
    }

    public void payFee(int playerTurn) {
        //if not purchase, need to check if there are the owner
        boolean hasOwner = model.checkSlotHasOwner(playerTurn);
        
        if (hasOwner) {
            //Create a new function that force the player to pay the fee
            view.showMessage(model.payFee(playerTurn));
        } else {
             //nothing because it represent some error when loading the slot. It is impossible a slot cannot be purchased can without a owner
        }
        
        //before ending the function we need to check player status is bankruptcy or not
        if (model.checkBankruptcy(playerTurn)==true) {
            //System.out.println("KILLING");
            //player isBankruptcy
            //set playerAlive = false;
            System.out.println(model.killPlayer(playerTurn));
        }
    }

    public boolean checkPlayerIsAlived(int playerId) {
        
        boolean isAlived=model.checkPlayerIsAlived(playerId);
        
        return isAlived;
    }

    public void printBoard() {
        view.showMessage(model.printBoard());
    }

    public boolean checkWinner() {
        boolean hasWinner = false;
        
        //check how many players are alive
        if (model.playerAliveNum()>1) {
            hasWinner = false;
        } else {
            hasWinner=true;
        }
        
        
        return hasWinner;
    }

    public void printSlotOwnership() {
        view.showMessage(model.printSlotOwnership());
    }

    public void modifySlotOwnership(int slotId, int ownershipId) {
        view.showMessage(model.modifySlotOwnership(slotId, ownershipId));
    }
    
}
