/**
 *
 * @author Ma Man To Tony
 */
public class MController {
    
    private MView view;
    private MModel model;
    
    public void setView(MView v) {
        this.view = v;        
    }
    
    public void setModel(MModel m) {
        this.model = m;        
    }

    public void createBoard() {
        model.createBoard();
    }

    public void initSlots() {
        model.initSlots();
    }

    public void viewUpdateSlot(String[] tempArray) {
        view.updateSlot(tempArray);
    }

    public boolean checkHasSlot(int count) {
        return model.checkHasSlot(count);
    }

    public String getSlotName(int count) {
        return model.getSlotName(count);
    }

    public String getSlotPrice(int count) {
        return model.getSlotPrice(count);
    }

    public String getSlotOwner(int count) {
        return model.getSlotOwner(count);
    }

    public void createPlayer(String player1Name, String player2Name, String player3Name, String player4Name) {
        model.createPlayer(player1Name, player2Name, player3Name, player4Name);
    }

    public void viewUpdateStatusPanel(String[] tempArray) {
        view.updateStatusPanel(tempArray);
    }

    public boolean checkHasPlayer(int count) {
        return model.checkHasPlayer(count);
    }

    String[] getAllPlayerID(int count) {
        return model.getAllPlayerID(count);
    }

    public void updatePlayerPos(int playerTurn) {
        model.updatePlayerPos(playerTurn);
    }

    public int getPlayerTurn() {
        return model.getPlayerTurn();
    }

    public int getCurrentRound() {
        return model.getCurrentRound();
    }

    public void addRound() {
        model.addRound();
    }

    public boolean checkPlayerHasRolled(int playerTurn) {
        return model.checkPlayerHasRolled(playerTurn);
    }

    public void setPlayerHasNotRolled(int playerTurn) {
        model.setPlayerHasNotRolled(playerTurn);
    }

    public void setPlayerHasRolled(int playerTurn) {
        model.setPlayerHasRolled(playerTurn);
    }

    public void updateStatusPanel() {
        model.updateStatusPanel();
    }

    public boolean checkIsLand(int count) {
        return model.checkIsLand(count);
    }

    public int getPlayerBoardPos(int playerTurn) {
        return model.getPlayerBoardPos(playerTurn);
    }

    boolean checkHasOwner(int playerPos) {
        return model.checkHasOwner(playerPos);
    }

    public void purchaseSlot(int currentPlayerId, int currentBoardPos) {
        model.purchaseSlot(currentPlayerId, currentBoardPos);
    }

    public void playerPayFee(int playerId, int playerPos) {
        model.playerPayFee(playerId, playerPos);
    }

    public boolean checkPlayerEqualOwner(int playerId, int playerPos) {
        return model.checkPlayerEqualOwner(playerId, playerPos);
    }

    public boolean checkIsAlive(int playerId) {
        return model.checkIsAlive(playerId);
    }

    public void addSkipCounter() {
        model.addSkipCounter();
    }

    public int getSkipRoundCounter() {
        return model.getSkipRoundCounter();
    }

    public void viewBackMainMenu() {
        view.backMainMenu();
    }

    public boolean getGameEditorMode() {
        return model.getGameEditorMode();
    }

    public void setGameEditorMode(boolean mode) {
        model.setGameEditorMode(mode);
    }

    public void modifySlotOwnership(int slotId, int ownerId) {
        model.modifySlotOwnership(slotId, ownerId);
    }

    public void modifyPlayerBalance(int playerId, long newBalance) {
        model.modifyPlayerBalance(playerId, newBalance);
    }

    public void modifyPlayerIsAlive(int playerId, String aliveString) {
        model.modifyPlayerIsAlive(playerId, aliveString);
    }

    public void modifyPlayerPos(int playerId, int newPos) {
        model.modifyPlayerPos(playerId, newPos);
    }

    public void tradeBuyFunc(int currentPlayerId, int slotPos, long tradeAmount) {
        model.tradeBuyFunc(currentPlayerId, slotPos, tradeAmount);
    }

    public void tradeSellFunc(int currentPlayerId, int buyerId, int slotPos, long tradeAmount) {
        model.tradeSellFunc(currentPlayerId, buyerId, slotPos, tradeAmount);
    }

    public String getSlotId(int boardPos) {
        return model.getSlotId(boardPos);
    }
    
    public void viewShowMessage(String string) {
        view.showMessage(string);
    }
}
