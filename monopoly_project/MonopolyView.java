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
        
        //round start
        System.out.println("Round Start!");
        while (!isGameOver) {
            System.out.println("Round: "+(round+1));
            //total 4 players
            playerTurn = round%4;
            System.out.println("Player Turn: Player"+(playerTurn+1));
            
            //show players status
            controller.getAllPlayerStatus();
            
            System.out.println("Please enter integer 1 to roll the dice");
            
            //roll the dice and move
            if (s.nextInt()==1) {
                //roll the dice
                controller.rollDice(playerTurn);
            }
            
            round++;
            
        }
    }
}
