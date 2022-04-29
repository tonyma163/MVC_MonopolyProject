
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ma Man To Tony
 */
public class MModel {
    
    MController controller;
    
    public void setController(MController c) {
        this.controller = c;
    }

    private int boardHeight = 12;
    private int boardWidth = 12;
    int[][] board = new int[boardHeight][boardWidth];
    
    //player status
    HashMap<Integer, MPlayer> playerStatus = new HashMap<Integer, MPlayer>();
    //customSlot
    String defaultSlotFilename = "defaultSlot.csv";
    //slot info
    HashMap<Integer, MSlotInfo> slotInfo = new HashMap<Integer, MSlotInfo>();
    
    //Game elements
    int round = 0;
    int playerTurn = 0;
    int skipRoundCounter = 0;
    
    boolean gameEditorMode = false;
    
    boolean[] rolledPlayerArray = new boolean[4]; 
    
    public void createBoard() {
        
        //initialize the board
        int count=0;
        for (int i=0; i<boardHeight; i++)
            for (int j=0; j<boardWidth; j++) {
                board[i][j]=count++;
            }
        
        //test the board
        /*
        for (int i=0; i<boardHeight; i++) {
            for (int j=0; j<boardWidth; j++) {
                System.out.print(board[i][j]+"  ");
            }
            System.out.println();
        }*/
        
    }

    public void initSlots() {
        loadDefaultSlot(); //load the defaultSlot file
        postUpdate(); //update the board in view
    }
    
    private void loadDefaultSlot() {
        //System.out.println("LOAD DEFAULT SLOT");
        String importFilePath = System.getProperty("user.dir")+"\\"+defaultSlotFilename;
        
        int defaultSlotNum = 0;
        try {
           File myObj = new File(importFilePath);
           Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            //read each line of the file
            String data = myReader.nextLine();
            //split each data by ','
            String[] tempArray = data.split(",");
            //slotId,slotName,slotPrice
            int slotId = Integer.parseInt(tempArray[0]);
            String slotName = tempArray[1];
            double slotPrice = Double.parseDouble(tempArray[2]);
            
            //save it
            //get the boardPos
            int boardPos = calBoardPos(slotId);
            double fee = slotPrice/10;
            
            //create the slotInfo
            boolean isPurchase = true;
            boolean forTrade = false;
            int ownerId = -1;
            
            //Test the data
            //System.out.println("slotId: "+slotId+", boardPos: "+boardPos+", slotName: "+slotName+", slotPrice: "+slotPrice+", isPurchase: "+isPurchase+", forTrade: "+forTrade+", ownerId: "+ownerId+", fee: "+fee);
            slotInfo.put(boardPos, new MSlotInfo(slotId, boardPos, slotName, slotPrice, isPurchase, forTrade, ownerId, fee));
            
            //Check the updated data
            /*
            System.out.println("TESTING");
            System.out.println("slot id: "+slotInfo.get(boardPos).getSlotId()+
                    "\nslot boardPos: "+slotInfo.get(boardPos).getBoardPos()+
                    "\nslot name: "+slotInfo.get(boardPos).getSlotName()+
                    "\nslot price: "+slotInfo.get(boardPos).getSlotPrice()+
                    "\nfor purchase: "+slotInfo.get(boardPos).getForPurchase()+
                    "\nfor trade: "+slotInfo.get(boardPos).getForTrade()+
                    "\nowner id: "+slotInfo.get(boardPos).getSlotOwner()+
                    "\nfee: "+slotInfo.get(boardPos).getFee()+"\n");
            */
            defaultSlotNum++;
        }
            System.out.println(defaultSlotNum+" default slots loaded");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("'"+defaultSlotNum+"' is not found");
        }
    }
    
    public int calBoardPos(int slotPos) {
        //23-32 -> top slot
        //1-10 - > bottom slot
        //12-21 -> left slot
        //34-43 -> right slot
        //22 -> top left corner
        //33 -> top right corner
        //11 -> bottom left corner
        //0 -> bottom right corner
        int boardPos=(boardWidth*boardHeight)-1; //144-1 = 143
        
        if (slotPos<=10 && slotPos>0) { //bottom
            
            boardPos-=slotPos;
            
        } else if (slotPos>=23 && slotPos<33) { //top
            
            boardPos=slotPos-22;
            
        } else if (slotPos>=12 && slotPos<22) { //left
            boardPos-=11;
            boardPos-=(slotPos-11)*12;
        } else if (slotPos>=34 && slotPos<=43) { //right
            boardPos=11;
            boardPos+=(slotPos-33)*12;
        } else if (slotPos==0) { //GO
            boardPos=143;
        } else if (slotPos==11) { //bottom left corner
            boardPos=132;
        } else if (slotPos==22) { //top left corner
            boardPos=0;
        } else if (slotPos==33) { //top right corner
            boardPos=11;
        }
        
        return boardPos;
    }
    
    public int calSlotPos(int boardPos) {
        int slotPos=0;
        
        if (boardPos >= 0 && boardPos <= 11) { //top
            slotPos=22+boardPos;
        } else if (boardPos >= 132 && boardPos <= 143){ //bottom
            slotPos=143-boardPos;
        } else if (boardPos % 12 == 0) { //left
            slotPos=22-(boardPos/12);
        } else if ((boardPos+1)%12 == 0) { //right
            slotPos=33+((boardPos+1)/12)-1;
        }
        
        return slotPos;
    }
    
    public void postUpdate() {
        
        String[] tempArray = new String[22];
        int count = 0;
        
        for (int i=0; i<44; i++) {
            
            if (slotInfo.containsKey(calBoardPos(i))) {
                String slotPos = Integer.toString(i);
                String slotName = slotInfo.get(calBoardPos(i)).getSlotName();
                String slotPrice = Double.toString(slotInfo.get(calBoardPos(i)).getSlotPrice());
                
                int tempInt = slotInfo.get(calBoardPos(i)).getSlotOwner();
                String slotOwner = "";
                if (tempInt==-1)
                    slotOwner = "ForPurchase";
                else
                    slotOwner = "Player"+tempInt;
                
                String line=slotPos+","+slotName+","+slotPrice+","+slotOwner;
                
                tempArray[count]=line;
                count++;
                
            }
        }
        
        controller.viewUpdateSlot(tempArray);
        
    }

    public boolean checkHasSlot(int count) {
        boolean result = false;
        
        if (slotInfo.containsKey(count)) {
            result = true;
        }
        
        return result;
    }

    public String getSlotName(int count) {
        return slotInfo.get(count).getSlotName();
    }

    public String getSlotPrice(int count) {
        return Double.toString(slotInfo.get(count).getSlotPrice());
    }

    public String getSlotOwner(int count) {
        Integer tempInt = slotInfo.get(count).getSlotOwner();
        
        String slotOwner = "";
        if (tempInt!=null) {
        if (tempInt==-1)
            slotOwner = "ForPurchase";
        else
            slotOwner = "Owner: Player"+(tempInt+1);
        } else {
            slotOwner = "Owner: Player null";
        }
        
        return slotOwner;
    }

    public void createPlayer(String player1Name, String player2Name, String player3Name, String player4Name) {
        //i -> playerID
        //tempPlayerName[i] -> playerName
        //0 -> money
        //0 -> landNum
        //true -> isAlived
        //0 -> position
        //player1
        playerStatus.put(0, new MPlayer(0, player1Name, 2000, 0, true, 0));
        //player2
        playerStatus.put(1, new MPlayer(1, player2Name, 2000, 0, true, 0));
        //player3
        playerStatus.put(2, new MPlayer(2, player3Name, 2000, 0, true, 0));
        //player4
        playerStatus.put(3, new MPlayer(3, player4Name, 2000, 0, true, 0));
        
        //Testing
        /*
        String[] tempArray = new String[4];
        for (int i=0; i<4; i++) {
            String playerId = "player"+(i+1);
            String playerName = playerStatus.get(i).getName();
            String playerBalance = Long.toString(playerStatus.get(i).getMoney());
            String playerLandNum = Integer.toString(playerStatus.get(i).getLandNum());
            String playerPos = Integer.toString(playerStatus.get(i).getPosition());
            String playerAlive = String.valueOf(playerStatus.get(i).getIsAlived());
            tempArray[i]=playerId+", name: "+playerName+", $: "+playerBalance+", LandNum: "+playerLandNum+", Pos: "+playerPos+", isAlive? "+playerAlive;
        }
        */
        
        //send the player status to the update
        updateStatusPanel();
        
        //reset the gameStatus
        round = 0;
        playerTurn = 0;
        skipRoundCounter = 0;
        gameEditorMode = false;
    }
    
    public boolean checkHasPlayer(int count) {
        boolean result = false;
        //check the player Pos
        for (int i=0; i<4; i++) {
            int boardPlayerPos = calBoardPos(playerStatus.get(i).getPosition());
            if (boardPlayerPos==count) 
                result=true;
        }
        return result;
    }

    public String[] getAllPlayerID(int count) {
        String[] tempArray = new String[4];
        
        for (int i=0; i<4; i++) {
            
            int boardPlayerPos = calBoardPos(playerStatus.get(i).getPosition());
            if (boardPlayerPos==count) {
                tempArray[i] = Integer.toString(playerStatus.get(i).getId()+1);
            } 
        }
        
        return tempArray;
    }

    public void updatePlayerPos(int playerTurn) {
        int dice = 0;
        
        Random random = new Random();
        dice = random.nextInt(10-1+1)+1;
        
        //move
        int playerPos = playerStatus.get(playerTurn).getPosition();
        playerPos += dice;
        
        playerStatus.get(playerTurn).setPosition(playerPos);
        
        //show the message
        controller.viewShowMessage("You have rolled: "+dice+"!");
        //System.out.println("DICE"+dice);
        //System.out.println("PLAYER POS "+playerPos);
        
        //check if pass the GO again or not
        //get new pos
        playerPos = playerStatus.get(playerTurn).getPosition();
        if (playerPos>43) {
            int newPlayerPos=playerPos-44;
            playerStatus.get(playerTurn).setPosition(newPlayerPos);
            //update the playerPos
            playerPos = playerStatus.get(playerTurn).getPosition();
            
            //Give $2000 to player
            addMoney(playerTurn, 2000);
            controller.viewShowMessage("You got $2000!\n");
        }
        
        //update the slot
        postUpdate();
        
        //update the player status
        updateStatusPanel();
    }
    
    public void addMoney(int playerId, double money) {
        double newMoney = playerStatus.get(playerId).getMoney()+money;
        playerStatus.get(playerId).setMoney(newMoney);
    }
    
    private String[] updatePlayerStatus() {
        String[] tempArray = new String[4];
        
        for (int i=0; i<4; i++) {
            String playerId = "player"+(i+1);
            String playerName = playerStatus.get(i).getName();
            String playerBalance = Double.toString(playerStatus.get(i).getMoney());
            String playerLandNum = Integer.toString(playerStatus.get(i).getLandNum());
            String playerPos = Integer.toString(playerStatus.get(i).getPosition());
            String playerAlive = String.valueOf(playerStatus.get(i).getIsAlived());
            tempArray[i]=playerId+", name: "+playerName+", $: "+playerBalance+", LandNum: "+playerLandNum+", Pos: "+playerPos+", isAlive? "+playerAlive;
        }
        
        return tempArray;
    }

    public int getPlayerTurn() {
        playerTurn = round%4;
        return playerTurn;
    }
    
    public int getAlivedPlayerNum() {
        int alivedPlayerNum = 0;
        for (int i=0; i<4; i++) {
            if (playerStatus.get(i).getIsAlived()) {//isAlive = true
                alivedPlayerNum++;
            }
        }
        
        return alivedPlayerNum;
    }

    public int getCurrentRound() {
        return round;
    }

    public void addRound() {
        //add round and update
        round++;
        playerTurn = round%4;
        
        //check the next player is alive or not
        if (playerStatus.get(playerTurn).getIsAlived())
            updateStatusPanel();
        else {
            addSkipCounter();
            addRound();
        }
    }

    public boolean checkPlayerHasRolled(int playerTurn) {
        return rolledPlayerArray[playerTurn];
    }

    public void setPlayerHasNotRolled(int playerTurn) {
        rolledPlayerArray[playerTurn]=false;
    }

    public void setPlayerHasRolled(int playerTurn) {
        rolledPlayerArray[playerTurn]=true;
    }

    public void updateStatusPanel() {
        controller.viewUpdateStatusPanel(updatePlayerStatus());
    }

    public boolean checkIsLand(int count) {
        boolean isLand = false;
        
        if (calSlotPos(count)!=0) {
            isLand=true;
        }
        
        return isLand;
    }

    int getPlayerBoardPos(int playerTurn) {
        int slotPos = playerStatus.get(playerTurn).getPosition();
        return calBoardPos(slotPos);
    }

    boolean checkHasOwner(int playerPos) {
        boolean hasOwner = false;
        
        if (slotInfo.containsKey(playerPos)) {
            int ownerId = slotInfo.get(playerPos).getSlotOwner();
            
            if (ownerId!=-1) {
                hasOwner = true;
            }
        }
        
        return hasOwner;
    }

    public void purchaseSlot(int currentPlayerId, int currentBoardPos) {
        double slotPrice = slotInfo.get(currentBoardPos).getSlotPrice();
        
        //check the playerHas enough amount
        double playerBalance = getPlayerBalance(currentPlayerId);
        System.out.println("playerBalance: "+playerBalance);
        System.out.println("slotPrice: "+slotPrice);
        if (playerBalance >= slotPrice) {//have enough money
            //decrease the balance
            decreasePlayerBalance(currentPlayerId, slotPrice);
            //set the ownership as playerId
            slotInfo.get(currentBoardPos).setSlotOwner(currentPlayerId);
            
            //add the playerStatus landNum
            playerStatus.get(currentPlayerId).setAddLandNum();
            
            //update the slot
            postUpdate();
        
            //update the player status
            updateStatusPanel();
            //show message
            controller.viewShowMessage("Congrats! You have purchased this slot!");
        } else {
            //show message
            controller.viewShowMessage("You DO NOT have enough money!");
        }
        
    }

    public double getPlayerBalance(int currentPlayerId) {
        return playerStatus.get(currentPlayerId).getMoney();
    }

    public void decreasePlayerBalance(int currentPlayerId, double decreaseAmount) {
        double playerBalance = getPlayerBalance(currentPlayerId);
        double decreasedBalance = playerBalance-decreaseAmount;
        playerStatus.get(currentPlayerId).setMoney(decreasedBalance);
    }

    void playerPayFee(int playerId, int playerPos) {
        //check the fees
        double fee = slotInfo.get(playerPos).getFee();
        
        //get the owner id
        int ownerId = slotInfo.get(playerPos).getSlotOwner();
        
        decreasePlayerBalance(playerId, fee);
        addPlayerBalance(ownerId, fee);
        
        controller.viewShowMessage("It has the owner. You have paid $: "+fee+" to Player "+(ownerId+1));
        
        //check the player is bankrupt or not
        //testing if player is bankrupt
        //playerStatus.get(playerId).setMoney(-1);
        
        //update the player status
        updateStatusPanel();
        
        if (isBankrupt(playerId)) { //is bankdrupt
            controller.viewShowMessage("You are bankrupt\n All your land has released to the free market");
            
            killPlayer(playerId);
            
            //update the slot because some slots may have the playerId
            postUpdate();
            
            //update the player status
            updateStatusPanel();
            
            //check has winner or not
            if (hasWinner()) {//has winner
                gameOver();
            }
        }
    }

    public void addPlayerBalance(int ownerId, double fee) {
        double playerBalance = playerStatus.get(ownerId).getMoney();
        double newPlayerBalance = playerBalance + fee;
        playerStatus.get(ownerId).setMoney(newPlayerBalance);
    }

    public boolean checkPlayerEqualOwner(int playerId, int playerPos) {
        boolean isSame = false;
        
        if (playerId == slotInfo.get(playerPos).getSlotOwner()) { //if same
            isSame = true;
        }
        
        return isSame;
    }

    public boolean isBankrupt(int playerId) {
        boolean isBankrupt = false;
        double playerBalance = playerStatus.get(playerId).getMoney();
        if (playerBalance < 0) { // < 0 -> bankrupt
            isBankrupt = true;
        }
        
        return isBankrupt;
    }

    public void killPlayer(int playerId) {
        //update
        playerStatus.get(playerId).setIsAlived(false);
        
        //the land he owned will be released to the free market
        int landNum = playerStatus.get(playerId).getLandNum();
        
        for (int i=0; i<144; i++) {//loop all the boardPos to find players' lands
            
            //check the land has assigned after the initSlot()
            if (slotInfo.containsKey(i)) { //land has been initialized
                //check the ownerId
                int ownerId = slotInfo.get(i).getSlotOwner();
                
                if (ownerId == playerId) {//find his land
                    //release the land
                    slotInfo.get(i).setSlotOwner(-1); //-1 -> no one own it
                    slotInfo.get(i).setForPurchase(true);
                    slotInfo.get(i).setForTrade(false);
                    
                    landNum--;
                }
            }
            //clear player balance
            playerStatus.get(playerId).clearLandNum();
        }
    }

    public boolean checkIsAlive(int playerId) {
        boolean isAlive = false;
        isAlive = playerStatus.get(playerId).getIsAlived();
        return isAlive;
    }

    public void addSkipCounter() {
        skipRoundCounter++;
    }

    public int getSkipRoundCounter() {
        return skipRoundCounter;
    }
    
    public boolean hasWinner() {
        boolean hasWinner = false;
        
        int playerNotAlive = 0;
        for (int i=0; i<4; i++) {
            if (checkIsAlive(i)==false) {
                playerNotAlive++;
            }
        }
        
        if (playerNotAlive>=3) {//has winner
            hasWinner = true;
        }
        
        return hasWinner;
    }

    public void gameOver() {
        //get the winner id
        int winnerId = -1;
        for (int i=0; i<4; i++) {
            if (checkIsAlive(i)==true) {
                winnerId = i;
            }
        }
        
        controller.viewShowMessage("Player "+(winnerId+1)+": "+playerStatus.get(winnerId).getName()+" has won the game!");
        controller.viewShowMessage("Game Over!");
        
        //back to the main menu
        controller.viewBackMainMenu();
    }
    
    public boolean getGameEditorMode() {
        return gameEditorMode;
    }

    public void setGameEditorMode(boolean mode) {
        gameEditorMode = mode;
    }

    public void modifySlotOwnership(int slotId, int ownerId) {
        int boardPos = calBoardPos(slotId);
        if (slotInfo.containsKey(boardPos)) {
            if (ownerId<=3 && ownerId>=-1) {
                //get the current ownership
                int originalOwner = slotInfo.get(boardPos).getSlotOwner();
                if (originalOwner!=-1) {//need to update the old owner's playerStatus landNum
                    //update the landNum
                    playerStatus.get(originalOwner).setDelLandNum();
                }
                slotInfo.get(boardPos).setSlotOwner(ownerId);
                
                if (ownerId==-1) {
                    controller.viewShowMessage("Updated! Slot: "+slotId+" new ownership is belonging to no one");
                } else {
                    //need to update the new owner's playerStatus landNum
                    playerStatus.get(ownerId).setAddLandNum();
                    controller.viewShowMessage("Updated! Slot: "+slotId+" new ownership is belonging to player "+(ownerId+1));
                }
                
            } else {
                    controller.viewShowMessage("The OwnershipID Invalid!");
            }
        } else {
            controller.viewShowMessage("Do not have this slot!");
        }
        
        //update the slot because some slots may have the playerId
        postUpdate();
            
        //update the player status
        updateStatusPanel();
    }

    public void modifyPlayerBalance(int playerId, double newBalance) {
        //check the playerId is 0-3
        if (playerId >=0 && playerId <=3) {
            //update
            playerStatus.get(playerId).setMoney(newBalance);
            controller.viewShowMessage("Updated! Player "+(playerId+1)+" new balance is $"+playerStatus.get(playerId).getMoney());
        } else {
            controller.viewShowMessage("The playerId is incorrect!");
        }
        
        //update the slot because some slots may have the playerId
        postUpdate();
            
        //update the player status
        updateStatusPanel();
        
        //checking player is bankrupt or not
        if (isBankrupt(playerId)) { //is bankdrupt
            controller.viewShowMessage("You are bankrupt\n All your land has released to the free market");
            
            killPlayer(playerId);
            
            //update the slot because some slots may have the playerId
            postUpdate();
            
            //update the player status
            updateStatusPanel();
            
            //check has winner or not
            if (hasWinner()) {//has winner
                gameOver();
            }
        }
    }

    public void modifyPlayerIsAlive(int playerId, String aliveString) {
        //check the playerId is 0-3
        if (playerId >=0 && playerId <=3) {
            //update
            if (aliveString.equals("true")) {
                playerStatus.get(playerId).setIsAlived(true);
                controller.viewShowMessage("Updated! Player "+(playerId+1)+" isAlived? "+playerStatus.get(playerId).getIsAlived());
            } else if (aliveString.equals("false")) {
                playerStatus.get(playerId).setIsAlived(false);
                controller.viewShowMessage("Updated! Player "+(playerId+1)+" isAlived? "+playerStatus.get(playerId).getIsAlived());
                killPlayer(playerId);
            }  else {
                    controller.viewShowMessage("Please enter a correct boolean choice (true/false)");
            }
        } else {
            controller.viewShowMessage("The playerId is incorrect!");
        }
        
        //update the slot because some slots may have the playerId
        postUpdate();
            
        //update the player status
        updateStatusPanel();
        
        //check has winner or not
        if (hasWinner()) {//has winner
            gameOver();
        }
    }

    public void modifyPlayerPos(int playerId, int newPos) {
        //check the playerId is 0-3
        if (playerId >=0 && playerId <=3) {
            
            //check pos 0-43
            if (newPos>=0 && newPos<=43) {
                //convert to boardPos
                //int boardPos = calBoardPos(newPos);
                
                //update
                playerStatus.get(playerId).setPosition(newPos);
                controller.viewShowMessage("Updated! Player"+(playerId+1)+" is moved to slot "+newPos);
                
            } else {
                controller.viewShowMessage("The location is incorrect(0-43)");
            }
        } else {
            controller.viewShowMessage("The playerId is incorrect!");
        }
        
        //update the slot because some slots may have the playerId
        postUpdate();
            
        //update the player status
        updateStatusPanel();
    }

    public void tradeBuyFunc(int currentPlayerId, int slotPos, double tradeAmount) {
        
        int boardSlotPos = calBoardPos(slotPos);
        //check has owner first
        if (checkHasOwner(boardSlotPos)) {
            
            //check the owner id != player id
            int slotOldOwnerId = slotInfo.get(boardSlotPos).getSlotOwner();
            if (slotOldOwnerId!=currentPlayerId)  {
                //check player balance
                double playerBalance = playerStatus.get(currentPlayerId).getMoney();
                playerBalance-=tradeAmount;
            
                if (playerBalance >= tradeAmount) {//enough balance
                    System.out.println("OwnerId: "+slotInfo.get(boardSlotPos).getSlotOwner());
                    System.out.println("OwnerBalance: "+playerStatus.get(slotOldOwnerId).getMoney());
                    double ownerBalance = playerStatus.get(slotOldOwnerId).getMoney();
                    ownerBalance+=tradeAmount;
                
                    //handling the transaction
                    //oldOwner
                    playerStatus.get(slotOldOwnerId).setMoney(ownerBalance);
                    playerStatus.get(slotOldOwnerId).setDelLandNum();
                    //player
                    playerStatus.get(currentPlayerId).setMoney(playerBalance);
                    playerStatus.get(currentPlayerId).setAddLandNum();
                
                    //swapOwnership
                    slotInfo.get(boardSlotPos).setSlotOwner(currentPlayerId);
                
                    //update the slot because some slots may have the playerId
                    postUpdate();
                
                    //update the player status
                    updateStatusPanel();
                
                    //show message
                    controller.viewShowMessage("Congrats! Your transaction has been executed");
                
                } else {
                    controller.viewShowMessage("You do not have enough balance");
                }
            } else {
                controller.viewShowMessage("You cannot buy the slot which is belonging to you");
            }
        } else {
            controller.viewShowMessage("This slot do not have the owner");
        }
    }

    public void tradeSellFunc(int currentPlayerId, int buyerId, int slotPos, double tradeAmount) {
        int boardSlotPos = calBoardPos(slotPos);
        //check has owner first
        if (checkHasOwner(boardSlotPos)) {
            //check the buyer balance
            double buyerBalance = playerStatus.get(buyerId).getMoney();
            if (buyerBalance>=tradeAmount) {//enough balance
                double playerBalance = playerStatus.get(currentPlayerId).getMoney();
                playerBalance+=tradeAmount;
                buyerBalance-=tradeAmount;
                
                //handing the transaction
                //buyer
                playerStatus.get(buyerId).setMoney(buyerBalance);
                playerStatus.get(buyerId).setAddLandNum();
                
                //player
                playerStatus.get(currentPlayerId).setMoney(playerBalance);
                playerStatus.get(currentPlayerId).setDelLandNum();
                
                //swapOwnership
                slotInfo.get(boardSlotPos).setSlotOwner(buyerId);
                
                //update the slot because some slots may have the playerId
                postUpdate();
                
                //update the player status
                updateStatusPanel();
                
                //show message
                controller.viewShowMessage("Congrats! Your transaction has been executed");
                
            } else {
                controller.viewShowMessage("The buyer do not have enough balance");
            }
        } else {
            controller.viewShowMessage("This slot do not have the owner");
        }
        
        
    }

    String getSlotId(int boardPos) {
        String slotIdString = "";
        
        int slotPos = calSlotPos(boardPos);
        slotIdString = Integer.toString(slotPos);
        
        return slotIdString;
    }
    
}
