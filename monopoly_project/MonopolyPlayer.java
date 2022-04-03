package test;

import java.util.ArrayList;

/**
 *
 * @author Ma Man To Tony
 */
public class MonopolyPlayer {
    
    private int id;
    private String name;
    private long money;
    private ArrayList<Integer> land = new ArrayList<Integer>();
    private boolean isAlived;
    private int position;
    
    public MonopolyPlayer(int id, String name, long money, ArrayList land, boolean isAlived, int position) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.land = land;
        this.isAlived = isAlived;
        this.position = position;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public long getMoney() {
        return money;
    }
    
    public void setMoney(long money) {
        this.money = money;
    }
    
    public ArrayList getLand() {
        return land;
    }
    
    public void setLand(ArrayList land) {
        this.land = land;
    }
    
    public boolean getIsAlived() {
        return isAlived;
    }
    
    public void setIsAlived(boolean isAlived) {
        this.isAlived = isAlived;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
}
