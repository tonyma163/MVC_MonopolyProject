package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ma Man To Tony
 */
public class MonopolyModel {
    
    MonopolyController controller;
    
    //import the MonopolyPlayer Class
    //MonopolyPlayer player = new MonopolyPlayer();
    
    //player status
    HashMap<Integer, MonopolyPlayer> playerStatus = new HashMap<Integer, MonopolyPlayer>();
    
    //board
    int boardWidth = 12;
    int boardHeight = 12;
    int[][] board = new int[boardHeight][boardWidth];
    
    //slot info
    HashMap<Integer, MonopolySlot> slotInfo = new HashMap<Integer, MonopolySlot>();
    
    //defaultSlot
    String customSlotFilename = "customSlot.csv";
    
    //customSlot
    String defaultSlotFilename = "defaultSlot.csv";
    
    public void setController(MonopolyController c) {
        this.controller = c;
    }
    
    public void createPlayer(String[] tempPlayerName) {
        for (int i=0; i<tempPlayerName.length; i++) {
            //i -> playerID
            //tempPlayerName[i] -> playerName
            //0 -> money
            //0 -> landNum
            //true -> isAlived
            //0 -> position
            playerStatus.put(i, new MonopolyPlayer(i, tempPlayerName[i], 2000, 0, true, 0));
            
        }
        //Testing the data
        /*
        for (int j=0; j<playerStatus.size();j++) {
            System.out.println("player name: "+playerStatus.get(j).getName());
            System.out.println("player "+(playerStatus.get(j).getId()+1)+"is alived?: "+playerStatus.get(j).getIsAlived());
        }
        */
    }
    
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
        }
        */
    }

    public void initSlot() {
        //default the slot
        //defaultSlot(); //stupid way to add
        loadDefaultSlot(); //load the defaultSlot file
        
        //load the csv_file so the customName, customPrice... will replace to default slot information
        loadCustomSlot();
        
        //Testing initSlot function
        /*
        int slotId = 1;
        int boardPos = 142;
        String slotName = "TESTING";
        long slotPrice = 1;
        boolean forPurchase = true;
        boolean forTrade = false;
        int slotOwner = -1;
        long slotFee = slotPrice*(long)0.1;
        slotInfo.put(slotId, new MonopolySlot(slotId, boardPos, slotName, slotPrice, forPurchase, forTrade, slotOwner, slotFee));
        */
        
        //Testing slot
        /*
        System.out.println("slot size: "+slotInfo.size());
        
        System.out.println("slot id: "+slotInfo.get(1).getSlotId());
        System.out.println("slot boardPos: "+slotInfo.get(1).getBoardPos());
        System.out.println("slot name: "+slotInfo.get(1).getSlotName());
        System.out.println("slot price: "+slotInfo.get(1).getSlotPrice());
        System.out.println("for purchase: "+slotInfo.get(1).getForPurchase());
        System.out.println("for trade: "+slotInfo.get(1).getForTrade());
        System.out.println("owner id: "+slotInfo.get(1).getSlotOwner());
        System.out.println("fee: "+slotInfo.get(1).getFee()+"\n");
        */
        
        /*
        //Testing the defaultSlot()
        System.out.println("SlotInfo SIZE: "+slotInfo.size());
            //Test the first one
            System.out.println("TEST1: ");
            System.out.println("slot id: "+slotInfo.get(142).getSlotId());
            System.out.println("slot boardPos: "+slotInfo.get(142).getBoardPos());
            System.out.println("slot name: "+slotInfo.get(142).getSlotName());
            System.out.println("slot price: "+slotInfo.get(142).getSlotPrice());
            System.out.println("for purchase: "+slotInfo.get(142).getForPurchase());
            System.out.println("for trade: "+slotInfo.get(142).getForTrade());
            System.out.println("owner id: "+slotInfo.get(142).getSlotOwner());
            System.out.println("fee: "+slotInfo.get(142).getFee()+"\n");
            //Test the second one
            System.out.println("TEST2: ");
            System.out.println("slot id: "+slotInfo.get(140).getSlotId());
            System.out.println("slot boardPos: "+slotInfo.get(140).getBoardPos());
            System.out.println("slot name: "+slotInfo.get(140).getSlotName());
            System.out.println("slot price: "+slotInfo.get(140).getSlotPrice());
            System.out.println("for purchase: "+slotInfo.get(140).getForPurchase());
            System.out.println("for trade: "+slotInfo.get(140).getForTrade());
            System.out.println("owner id: "+slotInfo.get(140).getSlotOwner());
            System.out.println("fee: "+slotInfo.get(140).getFee()+"\n");
            //Test the middle one
            System.out.println("TEST3: ");
            System.out.println("slot id: "+slotInfo.get(1).getSlotId());
            System.out.println("slot boardPos: "+slotInfo.get(1).getBoardPos());
            System.out.println("slot name: "+slotInfo.get(1).getSlotName());
            System.out.println("slot price: "+slotInfo.get(1).getSlotPrice());
            System.out.println("for purchase: "+slotInfo.get(1).getForPurchase());
            System.out.println("for trade: "+slotInfo.get(1).getForTrade());
            System.out.println("owner id: "+slotInfo.get(1).getSlotOwner());
            System.out.println("fee: "+slotInfo.get(1).getFee()+"\n");
            //Test the last one
            System.out.println("TEST4: ");
            System.out.println("slot id: "+slotInfo.get(119).getSlotId());
            System.out.println("slot boardPos: "+slotInfo.get(119).getBoardPos());
            System.out.println("slot name: "+slotInfo.get(119).getSlotName());
            System.out.println("slot price: "+slotInfo.get(119).getSlotPrice());
            System.out.println("for purchase: "+slotInfo.get(119).getForPurchase());
            System.out.println("for trade: "+slotInfo.get(119).getForTrade());
            System.out.println("owner id: "+slotInfo.get(119).getSlotOwner());
            System.out.println("fee: "+slotInfo.get(119).getFee()+"\n");
            */
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
            int slotPrice = Integer.parseInt(tempArray[2]);
            
            //save it
            //get the boardPos
            int boardPos = calBoardPos(slotId);
            long fee = slotPrice*(long)0.1;
            
            //create the slotInfo
            boolean isPurchase = true;
            boolean forTrade = false;
            int ownerId = -1;
            //Test the data
            //System.out.println("slotId: "+slotId+", boardPos: "+boardPos+", slotName: "+slotName+", slotPrice: "+slotPrice+", isPurchase: "+isPurchase+", forTrade: "+forTrade+", ownerId: "+ownerId+", fee: "+fee);
            slotInfo.put(boardPos, new MonopolySlot(slotId, boardPos, slotName, slotPrice, isPurchase, forTrade, ownerId, fee));
            
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
    
    private void loadCustomSlot() {
        //System.out.println("LOAD CUSTOM SLOT");
        String importFilePath = System.getProperty("user.dir")+"\\"+customSlotFilename;
        
        int customSlotNum = 0;
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
            int slotPrice = Integer.parseInt(tempArray[2]);
            
            //save it
            //get the boardPos
            int boardPos = calBoardPos(slotId);
            long fee = slotPrice*(long)0.1;
            
            //update the slotInfo
            slotInfo.get(boardPos).setSlotName(slotName);
            slotInfo.get(boardPos).setSlotPrice(slotPrice);
            slotInfo.get(boardPos).setFee(fee);
            
            customSlotNum++;
        }
            System.out.println(customSlotNum+" custom slots loaded");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("'"+customSlotFilename+"' is not found");
        }
    }
    
    private void defaultSlot() {
        //KEY VALUES:
        // int slotId, int boardPos, String slotName, long slotPrice, boolean forPurchase, boolean forTrade, int slotOwner, long fee
        int boardPos = 0;
        String slotName = "DEFAULT";
        long price = 0;
        long fee = 0;
        boolean isPurchase = true;
        boolean forTrade = false;
        int ownerId = -1; //-1 ->playerId will not be -1
                    
        //total has 44 slots
        for (int i=0; i<44; i++) {
            switch(i) {
                
                //slot1
                case 1:
                    boardPos = 142;
                    slotName = "SLOT-A";
                    price = 1000;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot2
                case 3:
                    boardPos = 140;
                    slotName = "SLOT-B";
                    price = 1200;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot3
                case 6:
                    boardPos = 137;
                    slotName = "SLOT-C";
                    price = 1400;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot4
                case 8:
                    boardPos = 135;
                    slotName = "SLOT-D";
                    price = 1600;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot5
                case 9:
                    boardPos = 134;
                    slotName = "SLOT-E";
                    price = 1600;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot6
                case 12:
                    boardPos = 120;
                    slotName = "SLOT-F";
                    price = 1600;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot7
                case 14:
                    boardPos = 96;
                    slotName = "SLOT-G";
                    price = 2000;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot8
                case 15:
                    boardPos = 84;
                    slotName = "SLOT-H";
                    price = 2000;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot9
                case 17:
                    boardPos = 60;
                    slotName = "SLOT-I";
                    price = 2400;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot10
                case 19:
                    boardPos = 36;
                    slotName = "SLOT-J";
                    price = 2800;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot11
                case 23:
                    boardPos = 1;
                    slotName = "SLOT-K";
                    price = 3400;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot12
                case 25:
                    boardPos = 3;
                    slotName = "SLOT-L";
                    price = 3600;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot13
                case 26:
                    boardPos = 4;
                    slotName = "SLOT-M";
                    price = 3600;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot14
                case 28:
                    boardPos = 6;
                    slotName = "SLOT-N";
                    price = 4000;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot15
                case 30:
                    boardPos = 8;
                    slotName = "SLOT-O";
                    price = 4400;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot16
                case 31:
                    boardPos = 9;
                    slotName = "SLOT-P";
                    price = 4400;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot17
                case 34:
                    boardPos = 23;
                    slotName = "SLOT-Q";
                    price = 5000;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot18
                case 36:
                    boardPos = 47;
                    slotName = "SLOT-R";
                    price = 5400;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot19
                case 37:
                    boardPos = 59;
                    slotName = "SLOT-S";
                    price = 5400;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot20
                case 39:
                    boardPos = 83;
                    slotName = "SLOT-T";
                    price = 5800;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot21
                case 41:
                    boardPos = 107;
                    slotName = "SLOT-U";
                    price = 6600;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                
                //slot22
                case 42:
                    boardPos = 119;
                    slotName = "SLOT-V";
                    price = 8000;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                /*
                default:
                    //if slot is not allowed to trade/purchase
                    boardPos = 140;
                    slotName = "LAND";
                    price = 1000;
                    fee = price*(long)0.1;
                    isPurchase = true;
                    forTrade = false;
                    ownerId = -1; //-1 ->playerId will not be -1
                
                    slotInfo.put(boardPos, new MonopolySlot(i, boardPos, slotName, price, isPurchase, forTrade, ownerId, fee));
                    break;
                */
            }
        }
    }

    public String getAllPlayerStatus() {
        String result ="Player Status:\n";
        
        //for each player loop
        for (int i=0; i<4; i++) {
            result+="Player "+(i+1)+": "+playerStatus.get(i).getName()+
                    ", $: "+playerStatus.get(i).getMoney()+
                    ", Lands: "+playerStatus.get(i).getLandNum()+
                    ", Position: "+playerStatus.get(i).getPosition()+
                    ", Is alived? "+playerStatus.get(i).getIsAlived()+"\n";
        }
        
        return result;
    }
    
    public String playerMovement(int playerTurn) {
        String result="";
        int dice = 0;
        
        Random random = new Random();
        dice = random.nextInt(10-1+1)+1;
        
        //move
        int playerPos = playerStatus.get(playerTurn).getPosition();
        playerPos += dice;
        
        playerStatus.get(playerTurn).setPosition(playerPos);
        
        //get new pos
        playerPos = playerStatus.get(playerTurn).getPosition();
        
        //check if pass the GO again or not
        if (playerPos>43) {
            int newPlayerPos=playerPos-43;
            playerStatus.get(playerTurn).setPosition(newPlayerPos);
            //Give $2000 to player
            addMoney(playerTurn, 2000);
            result+="You got $2000!\n";
        }
        
        result += "You have rolled '"+dice+"' !"+"\nYou have moved to Slot: "+playerPos+"!";
        return result;
    }
    
    public void addMoney(int playerId, long money) {
        long newMoney = playerStatus.get(playerId).getMoney()+money;
        playerStatus.get(playerId).setMoney(newMoney);
    }

    public boolean checkSlotIsPurchase(int playerTurn) {
        boolean result = false;
        //check the slot isForPurchase
        
        //get the newBoardPosition on board
        int slotPos = playerStatus.get(playerTurn).getPosition();
        //calculate the slotPos is representing which boardPos
        int currentBoardPos = calBoardPos(slotPos);
        //System.out.println("Board Position: "+currentBoardPos);
        
        if (slotInfo.containsKey(currentBoardPos)) {
            boolean forPurchase = slotInfo.get(currentBoardPos).getForPurchase();
            result = forPurchase;
        }
        /*
        if (forPurchase) { //if the slot is allowing player to purchase
            //Ask user to purchase
            
            
        } else { //if forPurchase=false, it represents that someone has purchased it so player need to pay the fee
            
            //get the currentMoney
            long money = playerStatus.get(playerTurn).getMoney();
            long fee = slotInfo.get(currentBoardPos).getFee();
            long newMoney = money-fee;
            
            //get the slotOwner id
            int slowOwner = slotInfo.get(currentBoardPos).getSlotOwner();
            //The owner will receive the fee which player has paid
            long ownerMoney = playerStatus.get(slowOwner).getMoney();
            long newOwnerMoney = ownerMoney+fee;
            
            //update the players status
            playerStatus.get(playerTurn).setMoney(newMoney); //player who paid
            
            playerStatus.get(slowOwner).setMoney(newOwnerMoney); //slot owner player
            
            String ownerPlayerName = playerStatus.get(slowOwner).getName();
            
            //add to the result text
            result+="You have paid $"+fee+" to "+ownerPlayerName+".\nYour new balance: $"+playerStatus.get(playerTurn).getMoney()+"\n"+ownerPlayerName+" new balance: $"+newOwnerMoney;
        }
        */
        
        return result;
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

    public boolean checkSlotHasOwner(int playerTurn) {
        boolean result=false;
        
        //get the current slot pos
        int slotPos = playerStatus.get(playerTurn).getPosition();
        //convert to the boardPos
        int boardPos = calBoardPos(slotPos);
        
        if (slotInfo.containsKey(boardPos)) {
            //check the slot has owner or not
            if (slotInfo.get(boardPos).getSlotOwner()>=0) { //-1 -> without owner
                result=true;
            }
        } else {
            //something wrong because it is impossible to be not purchased and without a owner
        }
        return result;
    }

    public String payFee(int playerTurn) {
        String result ="";
        
        //get the player balance
        long playerMoney = playerStatus.get(playerTurn).getMoney();
        
        //Testing playerMoney = 0
        //long playerMoney = 0;
        
        //get the player position in order to get the ownerID -> get the owner balance
        int playerSlotPos = playerStatus.get(playerTurn).getPosition();
        //convert to boardPos
        int boardPos = calBoardPos(playerSlotPos);
        System.out.println("Checking Board POS: "+boardPos);
        //check has the KEY boardPos exist
        if (slotInfo.containsKey(boardPos)) {
            //get the ownerId
            int ownerId = slotInfo.get(boardPos).getSlotOwner();
            //get the owner balance
            long ownerMoney = playerStatus.get(ownerId).getMoney();
            
            //get the slot fee
            long fee = slotInfo.get(boardPos).getFee();
            
            //handle the player balance
            long newMoney = playerMoney-fee;
            playerStatus.get(playerTurn).setMoney(newMoney);
            
            //handle the owner balance
            long newOwnerMoney = ownerMoney+fee;
            playerStatus.get(ownerId).setMoney(newOwnerMoney);
            
            //get the owner name
            String ownerName = playerStatus.get(ownerId).getName();
        
            result+="You has paid $"+fee+" fees to "+ownerName+
                    "\nYour updated balance: $"+newMoney+
                    "\n"+ownerName+" updated balance: $"+newOwnerMoney;
        } else {
            //something wrong because it is impossible to be not purchased and without a owner
            
        }
        return result;
    }

    public String purchaseLand(int playerTurn) {
        String result="";
        
        //get the playerMoney first
        long playerMoney = playerStatus.get(playerTurn).getMoney();
        
        //get the slot price after getting the boardPos by converting slotPos to boardPos
        int slotPos = playerStatus.get(playerTurn).getPosition();
        int boardPos = calBoardPos(slotPos);
        long slotPrice = slotInfo.get(boardPos).getSlotPrice();
        
        /*
        System.out.println("TESTING");
        System.out.println("The slotPos: "+slotPos);
        System.out.println("The boardPos: "+boardPos);
        */
        
        //check player have enough money
        if (playerMoney>=slotPrice) { //execute the transaction
            //update the data in slotInfo and playerStatus
            
            //playerStatus
            long newPlayerMoney = playerMoney-slotPrice;
            //update
            playerStatus.get(playerTurn).setMoney(newPlayerMoney);
            playerStatus.get(playerTurn).setLandNum();
            
            
            //slotInfo
            String ownerName = playerStatus.get(playerTurn).getName();
            //update
            slotInfo.get(boardPos).setForPurchase(false);
            slotInfo.get(boardPos).setSlotOwner(playerTurn);
            slotInfo.get(boardPos).setSlotName(ownerName);
            slotInfo.get(boardPos).setForTrade(true);
            
            //update the output text
            result+="Congrats! You have spent: "+slotPrice+" to buy this slot!"+
                    "\nYour updated balance: "+newPlayerMoney;
            
        } else {
            result+="Sorry! You do not have enough money!";
        }
        
        return result;
    }
    
    public boolean checkBankruptcy(int playerId) {
        boolean isBankruptcy = false;
        
        //check player balance <= 0
        //System.out.println("CHECKING BALANCE" +playerStatus.get(playerId).getMoney());
        if (playerStatus.get(playerId).getMoney()<0) {
            //System.out.println("IT IS BELOW ZERO!! " +playerStatus.get(playerId).getMoney());
            isBankruptcy = true;
        }
        
        return isBankruptcy;
    }

    public String killPlayer(int playerId) {//when player is bankruptcy
        String result="";
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
        
        result+="Player "+(playerId+1)+": "+playerStatus.get(playerId).getName()+" is being BANKRUPTCY !!"
                + "\nALL LANDS HAS BEEN RELEASED.";
        
        return result;
    }

    public boolean checkPlayerIsAlived(int playerId) {
        boolean isAlived=playerStatus.get(playerId).getIsAlived();
        
        return isAlived;
    }
    
    
}
