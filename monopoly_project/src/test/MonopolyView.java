package test;

import java.util.Scanner;

/**
 *
 * @author Ma Man To Tony
 */
public class MonopolyView {
    
    private MonopolyController controller;
    //create the scanner as s
    private Scanner s = new Scanner(System.in);
    
    public void setController(MonopolyController c) {
        this.controller = c;
    }
    
    public void processCommand() {
        //System.out.println("Hello MVC");
        
        boolean isEnd = false;
        System.out.println("1) Start the game \n2) Exit");
        while (!isEnd) {
            System.out.print(">");
            int choice = s.nextInt();
            
            switch (choice) {
                case 1:
                    gameStart();
                    break;
                case 2:
                    isEnd = true;
                    
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
        
        //outside the while loop represents players wanna leave
        System.out.println("Bye Bye");
    }
    
    public void showMessage(String s) {
        System.out.println(s);
    }
    
    private void gameStart() {
        System.out.println("GAME START");
        
        //require players to enter names in order to create players
        createPlayer();
        
        //initBoard
        initBoard();
        
        //initSlot
        initSlot();
        
        //game start
        gameProcess();
    }
    
    private void createPlayer() {
        String[] tempPlayerName = new String[4];
        
        s.nextLine();
        for (int i=0; i<4; i++) {
            System.out.println("Please enter player "+(i+1)+"'s name");
            tempPlayerName[i] = s.nextLine();
        }
        
        //pass the array to the controller
        controller.createPlayer(tempPlayerName);
    }
    
    private void initBoard() {
        controller.createBoard();
    }

    private void initSlot() {
        controller.initSlot();
    }

    private void gameProcess() {
        boolean isGameOver = false;
        
        int round = 0;
        int playerTurn = 0;
        
        int skipRoundCounter = 0;
        
        boolean editorModeOn = false;
        
        //round start
        System.out.println("Round Start!");
        while (!isGameOver) {
            
            //total 4 players
            playerTurn = round%4;
            
            //check the player is alive or not. If player is dead, it will skip his round
            if (controller.checkPlayerIsAlived(playerTurn)) {
                //System.out.println("ISALIVED");
                
            System.out.println("Round: "+((round-skipRoundCounter)+1));
                
            System.out.println("Player Turn: Player"+(playerTurn+1));
            
            //print board
            controller.printBoard();
            
            //print slot ownership
            controller.printSlotOwnership();
            
            //show players status
            controller.getAllPlayerStatus();
            
            System.out.println("Please enter integer 1 to roll the dice");
            int rollChoice = s.nextInt();
            //roll the dice and move
            if (rollChoice==1) {
                //roll the dice
                controller.rollDice(playerTurn);
            } else if (rollChoice==2) {
                editorModeOn=true;
            }
            System.out.println("GameEditorMode: "+editorModeOn);
            //if editorModeOn
            while (editorModeOn) {
                //4 options
                System.out.println("Game Editor Mode");
                System.out.println("1) Modify slot ownership");
                System.out.println("2) Modify player's balance");
                System.out.println("3) Modify player's location");
                System.out.println("4) Modify player's alive status");
                System.out.println("0) End Game Editor Mode");
                
                int modifyChoice = s.nextInt();
                switch (modifyChoice) {
                    case 1:
                        //modify slot ownership
                        modifySlotOwnership();
                        break;
                    case 2:
                        //modify player's balance
                        break;
                    case 3:
                        //modify player's location
                        break;
                    case 4:
                        //modify player's alive status
                        break;
                    case 0:
                        //end the game editor mode
                        editorModeOn=false;
                        break;
                }
                
            }
            
            //After player rolled the dice, it should check the newPosition slot isForPurchase or isForTrade
            if (controller.checkSlot(playerTurn)) { //isPurchase = true
                //ask player to buy the slot or not
                System.out.println("This slot is available to purchase. Would you buy it? (enter '1' the BUY / enter '2' NOT BUY)");
                int choice = s.nextInt();
                
                if (choice==1) {
                    controller.purchaseSlot(playerTurn);
                } else if (choice==2) {
                    //player not purchase the slot, nothing will need to do
                }
                
            } else {
                //player should need to pay fee because it represents he stepped on someone's land
                controller.payFee(playerTurn);
            }
            round++;
            } else {//skip this round
                //System.out.println("IS NOT ALIVED");
                skipRoundCounter++;
                round++;
            }
            
            //check winner
            if (controller.checkWinner()) { //if win
                isGameOver=true;
            }
        }
        //game over!
        System.out.println("Game Over!");
    }

    private void modifySlotOwnership() {
        //require the slotId and the new ownershipId
        System.out.println("Please enter the slot position: ");
        int slotId = s.nextInt();
        System.out.println("Please enter the new ownership(0 represent no one own it, 1-4 represent playerId): ");
        int ownershipId = (s.nextInt()-1);
        controller.modifySlotOwnership(slotId, ownershipId);
    }
}
