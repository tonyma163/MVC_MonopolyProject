import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Ma Man To Tony
 */
public class MView extends JFrame implements KeyListener{
    
    private MController controller;
    
    public void setController(MController c) {
        this.controller = c;
    }
    
    //GUI frame
    JFrame frame;
    
    //GUI panels
    JPanel panelMenu = new JPanel(new BorderLayout());
    JPanel panelMenuBar = new JPanel(new GridLayout(1,3,10,10));
    JPanel panelImage = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
    JPanel panelBoard = new JPanel(new GridLayout(12,12,0,0));
    JPanel panelControl = new JPanel(new GridLayout(6,1,0,0));
    JPanel panelStatus = new JPanel(new GridLayout(4,1,5,5));
    JPanel panelGameEditor = new JPanel(new GridLayout(5,1,0,0));
    //bonus part
    JPanel panelTrade = new JPanel(new GridLayout(5,1,0,0));
    
    //panelMenu GUI elements
    ImageIcon bgImg;
    JLabel lb_bgImage;
    
    //panelStatus GUI elements
    JLabel lb_round = new JLabel("Round");
    JLabel lb_turn = new JLabel("Turn");
    JLabel lb_player1Status = new JLabel("Player1 status");
    JLabel lb_player2Status = new JLabel("Player2 status");
    JLabel lb_player3Status = new JLabel("Player3 status");
    JLabel lb_player4Status = new JLabel("Player4 status");
    
    //other elements
    int count;
    boolean isGameStart = false;
    
    public MView() {
        //creating the JFrame object
        frame = new JFrame("COM3101 Monopoly");
        
        //bg image
        String path="images/monopoly_bg.png";
        //createImageIcon(path);
        bgImg = new ImageIcon(this.getClass().getResource(path));
        //lb_bgImage = new JLabel("this is the image");
        lb_bgImage = new JLabel(bgImg);
        lb_bgImage.setBounds(0, 0, 1540, 1040);
        
        //setting the whole boardlayout
        frame.setLayout(new BorderLayout());
        
        //initializing the label and menu buttons
        JLabel lb_menu = new JLabel("   Monopoly Game           Welcome!");
        lb_menu.setForeground(Color.RED);
        
        JButton bt_startGame = new JButton("Start Game");
        JButton bt_exit = new JButton("Exit");
        
        //edit the buttons in the menu
        bt_startGame.setFocusable(false);
        bt_startGame.setBackground(new Color(59, 89, 182));
        bt_startGame.setForeground(Color.WHITE);
        bt_startGame.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        bt_exit.setFocusable(false);
        bt_exit.setBackground(new Color(59, 89, 182));
        bt_exit.setForeground(Color.WHITE);
        bt_exit.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        panelMenuBar.add(lb_menu);
        panelMenuBar.add(bt_startGame);
        panelMenuBar.add(bt_exit);
        
        panelMenu.add(panelMenuBar, BorderLayout.NORTH);
        //adding the bg image to the panel
        panelImage.add(lb_bgImage);
        panelMenu.add(panelImage, BorderLayout.SOUTH);
        
        //initializing the game editor panel
        JLabel lb_gameEditor = new JLabel("         Game Editor Panel");
        lb_gameEditor.setForeground(Color.RED);
        lb_gameEditor.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        JButton bt_modifyOwnership = new JButton("Modify Ownership");
        JButton bt_modifyBalance = new JButton("Modify balance");
        JButton bt_modifyIsAlive = new JButton("Modify isAlive");
        JButton bt_modifyPlayerPos = new JButton("Modify player position");
        
        //edit the game editor buttons
        bt_modifyOwnership.setFocusable(false);
        bt_modifyOwnership.setBackground(new Color(59, 89, 182));
        bt_modifyOwnership.setForeground(Color.WHITE);
        bt_modifyOwnership.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        bt_modifyBalance.setFocusable(false);
        bt_modifyBalance.setBackground(new Color(59, 89, 182));
        bt_modifyBalance.setForeground(Color.WHITE);
        bt_modifyBalance.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        bt_modifyIsAlive.setFocusable(false);
        bt_modifyIsAlive.setBackground(new Color(59, 89, 182));
        bt_modifyIsAlive.setForeground(Color.WHITE);
        bt_modifyIsAlive.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        bt_modifyPlayerPos.setFocusable(false);
        bt_modifyPlayerPos.setBackground(new Color(59, 89, 182));
        bt_modifyPlayerPos.setForeground(Color.WHITE);
        bt_modifyPlayerPos.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        panelGameEditor.add(lb_gameEditor);
        panelGameEditor.add(bt_modifyOwnership);
        panelGameEditor.add(bt_modifyBalance);
        panelGameEditor.add(bt_modifyIsAlive);
        panelGameEditor.add(bt_modifyPlayerPos);
        
        //initializing the control panel
        JLabel lb_controlPanel = new JLabel("  Control Panel");
        lb_controlPanel.setForeground(Color.RED);
        lb_controlPanel.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        JButton bt_roll = new JButton("ROLL");
        JButton bt_purchase = new JButton("PURCHASE");
        //bonus part
        JButton bt_trade = new JButton("TRADE");
        JButton bt_endCurrentTurn = new JButton("END TURN");
        JButton bt_back = new JButton("BACK");
        
        //edit control panel buttons
        bt_roll.setFocusable(false);
        bt_roll.setBackground(new Color(59, 89, 182));
        bt_roll.setForeground(Color.WHITE);
        bt_roll.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        bt_purchase.setFocusable(false);
        bt_purchase.setBackground(new Color(59, 89, 182));
        bt_purchase.setForeground(Color.WHITE);
        bt_purchase.setFont(new Font("Tahoma", Font.BOLD, 12));
        //bonus part
        bt_trade.setFocusable(false);
        bt_trade.setBackground(new Color(59, 89, 182));
        bt_trade.setForeground(Color.WHITE);
        bt_trade.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        bt_endCurrentTurn.setFocusable(false);
        bt_endCurrentTurn.setBackground(new Color(59, 89, 182));
        bt_endCurrentTurn.setForeground(Color.WHITE);
        bt_endCurrentTurn.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        bt_back.setFocusable(false);
        bt_back.setBackground(new Color(59, 89, 182));
        bt_back.setForeground(Color.WHITE);
        bt_back.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        //add the elements into control panel
        panelControl.add(lb_controlPanel);
        panelControl.add(bt_roll);
        panelControl.add(bt_purchase);
        panelControl.add(bt_trade);
        panelControl.add(bt_endCurrentTurn);
        panelControl.add(bt_back);
        
        //initializing the status panel
        panelStatus.add(lb_round);
        panelStatus.add(lb_turn);
        panelStatus.add(lb_player1Status);
        panelStatus.add(lb_player2Status);
        panelStatus.add(lb_player3Status);
        panelStatus.add(lb_player4Status);
        
        //adding the panels to the borderLayout frame
        frame.add(panelMenu, BorderLayout.NORTH);
        frame.add(panelBoard, BorderLayout.CENTER);
        frame.add(panelControl, BorderLayout.EAST);
        frame.add(panelStatus, BorderLayout.SOUTH);
        frame.add(panelGameEditor, BorderLayout.WEST);
        
        //game editor function
        //pressing 'G' key to turn on the mode and 'E' key to off
        frame.addKeyListener(this);
        
        //the initial non-visible panels
        panelBoard.setVisible(false);
        panelControl.setVisible(false);
        panelStatus.setVisible(false);
        panelGameEditor.setVisible(false);
        
        //bonus part
        //Trade function
        JRadioButton rbt_buy = new JRadioButton("Buy");
        JRadioButton rbt_sell = new JRadioButton("Sell");
        rbt_buy.setFocusable(false);
        rbt_sell.setFocusable(false);
        
        JLabel lb_slotPos = new JLabel("slot position (0-43):");
        JLabel lb_tradeAmount = new JLabel("trade amount $:");
        
        JTextField tf_slotPos = new JTextField();
        JTextField tf_tradeAmount = new JTextField();
        
        //group the radio buttons together
        ButtonGroup rbt_group = new ButtonGroup();
        rbt_group.add(rbt_buy);
        rbt_group.add(rbt_sell);
        
        //adding them into the trade panel
        panelTrade.add(rbt_buy);
        panelTrade.add(rbt_sell);
        panelTrade.add(lb_slotPos);
        panelTrade.add(tf_slotPos);
        panelTrade.add(lb_tradeAmount);
        panelTrade.add(tf_tradeAmount);
        
        //Button listeners
        bt_startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] Start the game");
                //non-visiable panels
                panelMenu.setVisible(false);
                
                //get player names
                initialGame();
                
                //visible panels
                panelBoard.setVisible(true);
                panelControl.setVisible(true);
                panelStatus.setVisible(true);
                
                //update the Status
            }
        });
        
        bt_back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] Back to the main menu");
                //non-visiable panels
                panelBoard.setVisible(false);
                panelControl.setVisible(false);
                panelStatus.setVisible(false);
                
                //visible panels
                panelMenu.setVisible(true);
                
                //close the game editor
                //set game editor mode off
                controller.setGameEditorMode(false);
                //close the game editor panel
                panelGameEditor.setVisible(false);
            }
        });
        
        bt_roll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] ROLL");
                //get playerTurn
                //total 4 players
                
                int turn = controller.getPlayerTurn();
                int r = controller.getCurrentRound();
                
                //check the player has rolled or not
                System.out.println(controller.checkPlayerHasRolled(turn));
                if (!controller.checkPlayerHasRolled(turn)) {
                
                    System.out.println("ROUND: "+(r+1));
                    System.out.println("PLAYER TURN: "+(turn+1));
                
                    //update the player pos
                    controller.updatePlayerPos(turn);
                    
                    //get playerPos
                    int playerPos = controller.getPlayerBoardPos(turn);
                    //check it is slot or not
                    if (controller.checkHasSlot(playerPos)) {
                        
                        if (controller.checkHasOwner(playerPos)) {//has
                            
                            //check the owner is not equal the player
                            if (!controller.checkPlayerEqualOwner(turn, playerPos)) {
                                //pay the fee
                                controller.playerPayFee(turn, playerPos);
                            }
                            
                        }
                    }
                    
                    //set the player has rolled
                    controller.setPlayerHasRolled(controller.getPlayerTurn());
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "You has rolled before!");
                }
            }
        });
        
        bt_endCurrentTurn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] END TURN");
                //Check the current player has rolled or not
                if (controller.checkPlayerHasRolled(controller.getPlayerTurn())) {//if rolled, the turn can end
                    
                    //reset the rolledPlayerArray
                    controller.setPlayerHasNotRolled(controller.getPlayerTurn());
                    
                    //after the roll round++
                    controller.addRound();
                    
                    //check the next player is alived or not
                    /*
                    if (!controller.checkIsAlive(controller.getPlayerTurn())) { // not alive
                        System.out.println("Skipcounter++");
                        controller.addSkipCounter();
                        //add round after skip
                        controller.addRound();
                    }
                    */
                    
                } else {
                    JOptionPane.showMessageDialog(frame, "Please roll the dice before you end the turn!");
                }
            }
        });
        
        bt_purchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] PURCHASE");
                
                //check has rolled or not
                if (controller.checkPlayerHasRolled(controller.getPlayerTurn())) {//if rolled, it allowed to purchase
                
                int currentPlayerId = controller.getPlayerTurn();
                int currentBoardPos = controller.getPlayerBoardPos(currentPlayerId);
                
                //check the player pos is a slot
                if (controller.checkHasSlot(currentBoardPos)) {
                    //check the land ownership
                    if (!controller.checkHasOwner(currentBoardPos)) {
                        //start purchase function
                        controller.purchaseSlot(currentPlayerId, currentBoardPos);
                        
                    } else {//has owner
                        showMessage("You cannot purchase because it has the owner");
                    }
                } else
                    showMessage("You cannot purchase because it is not a slot");
                
                } else {
                    JOptionPane.showMessageDialog(frame, "You cannot purchase before you rolled the dice");
                }
            }
        });
        
        bt_trade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] Trade");
                
                //check has rolled or not
                if (!controller.checkPlayerHasRolled(controller.getPlayerTurn())) {//if not rolled, it allowed to trade
                    JOptionPane.showMessageDialog(null, panelTrade);
                    
                    int choice = 0;
                    if (rbt_buy.isSelected()) {
                        choice=1;
                    } else if (rbt_sell.isSelected()) {
                        choice=2;
                    } else {
                        showMessage("Please select BUY or SELL");
                    }
                    
                    if (choice==1 || choice==2) {
                        if (!tf_slotPos.getText().isBlank()) {
                            //check the slotPos should be 0-43
                            try {
                                int slotPos = Integer.parseInt(tf_slotPos.getText());
                                if (slotPos>=0 && slotPos<=43) {
                                    //check the tradeAmount text field
                                    if (!tf_tradeAmount.getText().isBlank()) {
                                        //everything is fine
                                        try {
                                            double tradeAmount = Double.parseDouble(tf_tradeAmount.getText());
                                            System.out.println("Choice: "+choice+", SlotPos: "+slotPos+", Trade  amount: "+tradeAmount);
                                            //buy function
                                            if (choice==1) {
                                                System.out.println("BUY");
                                                int currentPlayerId = controller.getPlayerTurn();
                                                
                                                controller.tradeBuyFunc(currentPlayerId, slotPos, tradeAmount);

                                            } else if (choice==2) {
                                                System.out.println("SELL");
                                                int buyerId  = (Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter buyer playerId (1-4): "))-1);
                                                int currentPlayerId = controller.getPlayerTurn();
                                                
                                                //check the buyer id not equal to the player turn id
                                                if (buyerId!=currentPlayerId) {
                                                    controller.tradeSellFunc(currentPlayerId, buyerId, slotPos, tradeAmount);
                                                } else {
                                                    showMessage("You cannot sell the slot to yourself");
                                                }
                                            }
                                        
                                        } catch (NumberFormatException omg) {
                                            showMessage("Please enter a valid trade amount");
                                        }
                                    } else {
                                        showMessage("Please enter the trade amount");
                                    }
                            
                                } else {
                                    showMessage("Please enter the valid Slot Pos");
                                }
                            } catch (NumberFormatException omg) {
                                showMessage("Please enter the valid Slot Pos");
                            }
                        
                        } else {
                            showMessage("Please enter the slotPos");
                        }
                    }
                } else {
                    showMessage("You cannot trade after you rolled the dice");
                }
            }
        });        
                
        bt_modifyOwnership.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] Modify ownership");
                int slotId  = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter which slot: "));
                int ownerId  = (Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the playerId (1-4): "))-1);
                
                //modify
                controller.modifySlotOwnership(slotId, ownerId);
            }
        });
        
        bt_modifyBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] Modify balance");
                int playerId  = (Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter playerId (1-4): "))-1);
                double newBalance  = Double.parseDouble(JOptionPane.showInputDialog(null, "Please enter the new balance: "));
                
                //modify
                controller.modifyPlayerBalance(playerId, newBalance);
            }
        });
                
        bt_modifyIsAlive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] Modify isAlive");
                int playerId  = (Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter playerId (1-4): "))-1);
                String aliveString  = JOptionPane.showInputDialog(null, "Please enter player alive is 'true' or 'false': ").trim();
                
                //modify
                controller.modifyPlayerIsAlive(playerId, aliveString);
            }
        });
                
        bt_modifyPlayerPos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] Modify player position");
                int playerId  = (Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter playerId (1-4): "))-1);
                int newPos  = (Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter new player position (0-43): ")));
                
                //modify
                controller.modifyPlayerPos(playerId, newPos);
                
                //check it is slot or not
                newPos = controller.getPlayerBoardPos(playerId);
                if (controller.checkHasSlot(newPos)) {
                    if (controller.checkHasOwner(newPos)) {//has
                        //check the owner is not equal the player
                        if (!controller.checkPlayerEqualOwner(playerId, newPos)) {
                            //pay the fee
                            controller.playerPayFee(playerId, newPos);
                        }
                    }
                }
            }
        });
        
        //exit
        bt_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button Clicked] Exit");
                showMessage("See you!");
                System.exit(0);
            }
        });
        //****************************************************************SPLIT*******************************************************************
        //setting while users close the window, it will stop running
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting the window's height & width
        frame.setSize(1540, 1040);
        //setting the window cannot be resized
        frame.setResizable(false);
        //setting the window to be visible
        frame.setVisible(true);
    }
    
    public void initialGame() {
        isGameStart = true;
        //Getting player names
        String player1Name = "";
        String player2Name = "";
        String player3Name = "";
        String player4Name = "";
        
        for (int i=0; i<4; i++) {
            String name = JOptionPane.showInputDialog(this, "Please enter player "+(i+1)+"'s name:");
            switch (i) {
                case 0:
                    if (name==null || name.isBlank())
                        player1Name="Player 1";
                    else
                        player1Name = name;
                    break;
                case 1:
                    if (name==null || name.isBlank())
                        player2Name="Player 2";
                    else
                        player2Name = name;
                    break;
                case 2:
                    if (name==null || name.isBlank())
                        player3Name="Player 3";
                    else
                        player3Name = name;
                    break;
                case 3:
                    if (name==null || name.isBlank())
                        player4Name="Player 4";
                    else
                        player4Name = name;
                    break;
            }
        }
        
        //createPlayer
        controller.createPlayer(player1Name, player2Name, player3Name, player4Name);
        
        //setting they have not rolled the dice at the beginning stage
        controller.setPlayerHasNotRolled(0);
        controller.setPlayerHasNotRolled(1);
        controller.setPlayerHasNotRolled(2);
        controller.setPlayerHasNotRolled(3);
        
        //Testing the player input names
        //System.out.println("Name: "+player1Name+"\nName: "+player2Name+"\nName: "+player3Name+"\nName: "+player4Name);
        
        //initBoard
        controller.createBoard();
        
        //initSlot
        controller.initSlots();
        
        //initStatusPanel
        controller.updateStatusPanel();
    }

    public void updateSlot(String[] tempArray) {
        //update the GUI board
        
        //testing the transfered slot info
        /*
        for (int i=0; i<tempArray.length; i++) {
            System.out.println(tempArray[i]+"\n");
        } */
        
        //clear the previous board
        System.out.println("Update the board");
        panelBoard.removeAll();
        
        count = 0;
        for (int row = 0; row < 12; row++) {
            for (int col = 0; col < 12; col++) {
                JPanel cell = new JPanel(new GridLayout(8, 1, 0, 0));
                cell.setSize(80, 120);
                cell.setBorder(BorderFactory.createLineBorder(Color.black));
                cell.setBackground(new Color(240, 240, 128));
                
                if (controller.checkHasSlot(count)) {
                    //get Slot id
                    String slotId = controller.getSlotId(count);
                    //get slot name
                    String slotName = controller.getSlotName(count);
                    //get slot price
                    String slotPrice = controller.getSlotPrice(count);
                    //get slot owner
                    String slotOwner = controller.getSlotOwner(count);
                    
                    cell.add(new JLabel(slotId));
                    cell.add(new JLabel(slotName));
                    cell.add(new JLabel("$"+slotPrice));
                    cell.add(new JLabel(slotOwner));
                    
                    //set the background
                    cell.setBackground(Color.ORANGE);
                    
                    //checkPlayerPos
                    if (controller.checkHasPlayer(count)) {
                        //start to add player 
                        //get the ALL playerID
                        String[] tempArray2 = new String[4];
                        
                        tempArray2 = controller.getAllPlayerID(count);
                        
                        for (int i=0; i<4; i++) {
                            cell.add(new JLabel(tempArray2[i]));
                        }
                    }
                    
                } else if (count==143) {
                    //get Slot id
                    String slotId = controller.getSlotId(count);
                    cell.add(new JLabel("0"));
                    cell.add(new JLabel("GO"));
                    //checkPlayerPos
                    if (controller.checkHasPlayer(count)) {
                        //start to add player 
                        //get the ALL playerID
                        String[] tempArray2 = new String[4];
                        
                        tempArray2 = controller.getAllPlayerID(count);
                        
                        for (int i=0; i<4; i++) {
                            cell.add(new JLabel(tempArray2[i]));
                        }
                    }
                } else if (controller.checkIsLand(count)){
                    //normal slot
                    //get Slot id
                    String slotId = controller.getSlotId(count);
                    cell.add(new JLabel(slotId));
                    cell.add(new JLabel(""));
                    cell.add(new JLabel(""));
                    cell.add(new JLabel(""));
                    
                    //checkPlayerPos
                    if (controller.checkHasPlayer(count)) {
                        //start to add player 
                        //get the ALL playerID
                        String[] tempArray2 = new String[4];
                        
                        tempArray2 = controller.getAllPlayerID(count);
                        
                        for (int i=0; i<4; i++) {
                            cell.add(new JLabel(tempArray2[i]));
                        }
                    }
                } else { //normal cell
                    //check again
                    if (!controller.checkHasPlayer(count)) {
                        cell.setBackground(Color.WHITE);
                    }
                }
                
                count++;
                panelBoard.add(cell);
            }
        }
    }

    public void updateStatusPanel(String[] tempArray) {
        //update the status panel
        
        //round, turn status
        //get skipCounter skipRoundCounter
        int calculatedRound = controller.getCurrentRound() - controller.getSkipRoundCounter();
        lb_round.setText("Round: "+(calculatedRound+1));
        lb_turn.setText("Player Turn: "+(controller.getPlayerTurn()+1));
        
        //update playerstatus
        lb_player1Status.setText(tempArray[0]);
        lb_player2Status.setText(tempArray[1]);
        lb_player3Status.setText(tempArray[2]);
        lb_player4Status.setText(tempArray[3]);
    }
    
    public void backMainMenu() {
        //back to the main menu
        //normally it will be used after the game is over
        System.out.println("[GAME OVER] Back to the main menu");
        //non-visiable panels
        panelBoard.setVisible(false);
        panelControl.setVisible(false);
        panelStatus.setVisible(false);
                
        //visible panels
        panelMenu.setVisible(true);
    }
    
    public void showMessage(String string) {
        JOptionPane.showMessageDialog(frame, string);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    //game editor function
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_G) {
            
            //check the mode has on or not
            if (!controller.getGameEditorMode() && isGameStart) {
                System.out.println("[G] GAME EDITOR ON");
                //set game editor mode on
                controller.setGameEditorMode(true);
                
                //open the game editor panel
                panelGameEditor.setVisible(true);
            }
            
        }
        
        if (e.getKeyCode() == KeyEvent.VK_E) {
            
            //check the mode has on or not
            if (controller.getGameEditorMode() && isGameStart) {
                System.out.println("[E] GAME EDITOR OFF");
                //set game editor mode off
                controller.setGameEditorMode(false);
                
                //close the game editor panel
                panelGameEditor.setVisible(false);
            }
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
