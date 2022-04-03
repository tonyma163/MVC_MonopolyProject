package test;

import java.util.HashMap;
import java.util.Random;

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
            playerStatus.put(i, new MonopolyPlayer(i, tempPlayerName[i], 2000, null, true, 0));
            
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
        defaultSlot();
        
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
            //Test the middle one
            System.out.println("TEST2: ");
            System.out.println("slot id: "+slotInfo.get(1).getSlotId());
            System.out.println("slot boardPos: "+slotInfo.get(1).getBoardPos());
            System.out.println("slot name: "+slotInfo.get(1).getSlotName());
            System.out.println("slot price: "+slotInfo.get(1).getSlotPrice());
            System.out.println("for purchase: "+slotInfo.get(1).getForPurchase());
            System.out.println("for trade: "+slotInfo.get(1).getForTrade());
            System.out.println("owner id: "+slotInfo.get(1).getSlotOwner());
            System.out.println("fee: "+slotInfo.get(1).getFee()+"\n");
            //Test the last one
            System.out.println("TEST3: ");
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
                    boardPos = 136;
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
            result+="Player "+(i+1)+": "+playerStatus.get(i).getName()+", $: "+playerStatus.get(i).getMoney()+", Lands: "+playerStatus.get(i).getLand()+", Position: "+playerStatus.get(i).getPosition()+"\n";
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
    
}
