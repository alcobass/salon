package gwt.beautySalon.shared;

import java.io.Serializable;

public class PriceList implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int serviceSubtypeId;
    private double cost;
    
    public PriceList() {};
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getServiceSubtypeId() {
        return serviceSubtypeId;
    }
    
    public void setServiceSubtypeId( int serviceSubtypeId) {
        this.serviceSubtypeId = serviceSubtypeId;
    }
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }
}