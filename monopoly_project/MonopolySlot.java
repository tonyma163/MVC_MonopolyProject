package test;

/**
 *
 * @author Ma Man To Tony
 */
public class MonopolySlot {
    
    private int slotId;
    private int boardPos;
    private String slotName;
    private long slotPrice;
    private int slotOwner;
    
    private boolean forPurchase;
    private boolean forTrade;
    
    private long fee;
    
    public MonopolySlot(int slotId, int boardPos, String slotName, long slotPrice, boolean forPurchase, boolean forTrade, int slotOwner, long fee) {
        this.slotId = slotId;
        this.boardPos = boardPos;
        this.slotName = slotName;
        this.slotPrice = slotPrice;
        this.forPurchase = forPurchase;
        this.forTrade = forTrade;
        this.slotOwner = slotOwner;
        this.fee = fee;
    }
    
    public int getSlotId() {
        return slotId;
    }
    
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
    
    public int getBoardPos() {
        return boardPos;
    }
    
    public void setBoardPos(int boardPos) {
        this.boardPos = boardPos;
    }
    
    public String getSlotName() {
        return slotName;
    }
    
    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }
    
    public long getSlotPrice() {
        return slotPrice;
    }
    
    public void setSlotPrice(long slotPrice) {
        this.slotPrice = slotPrice;
    }
    
    public boolean getForPurchase() {
        return forPurchase;
    }
    
    public void setForPurchase(boolean forPurchase) {
        this.forPurchase = forPurchase;
    }
    
    public boolean getForTrade() {
        return forTrade;
    }
    
    public void setForTrade(boolean forTrade) {
        this.forTrade = forTrade;
    }
    
    public int getSlotOwner() {
        return slotOwner;
    }
    
    public void setSlotOwner(int slotOwner) {
        this.slotOwner = slotOwner;
    }
    
    public long getFee() {
        return slotId;
    }
    
    public void setFee(long fee) {
        this.fee = fee;
    }
    
}
