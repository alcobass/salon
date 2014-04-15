package gwt.beautySalon.shared;

import java.io.Serializable;

public class ServiceSubtype implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int serviceTypeId;
    private String name;
    private String time;

    public ServiceSubtype() {
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}