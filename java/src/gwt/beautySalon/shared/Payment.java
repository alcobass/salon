package gwt.beautySalon.shared;

import java.io.Serializable;

public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int paymentTypeId;
    private int registrarionId;
    private int paymentMethodId;
    private double sum;

    public Payment() {
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(int paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }
    
    public int getRegistrarionId() {
        return registrarionId;
    }

    public void setRegistrarionId(int registrarionId) {
        this.registrarionId = registrarionId;
    }
    
    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
    
    public double getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}