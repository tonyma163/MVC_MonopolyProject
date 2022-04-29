/**
 *
 * @author Ma Man To Tony
 */
public class MPlayer {
    private int id;
    private String name;
    private double money;
    //private ArrayList<Integer> land = new ArrayList<Integer>();
    private int landNum;
    private boolean isAlived;
    private int position;
    
    public MPlayer(int id, String name, double money, int landNum, boolean isAlived, int position) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.landNum = landNum;
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
    
    public double getMoney() {
        return money;
    }
    
    public void setMoney(double money) {
        this.money = money;
    }
    
    public int getLandNum() {
        return landNum;
    }
    
    public void setAddLandNum() {
        this.landNum++;
    }
    
    public void setDelLandNum() {
        this.landNum--;
    }
    
    public void clearLandNum() {
        this.landNum=0;
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
