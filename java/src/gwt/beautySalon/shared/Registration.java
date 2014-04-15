package gwt.beautySalon.shared;

import java.io.Serializable;

public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Client client;
    private int serviseSubtypeId;
    private String date;
    private String time;

    Registration() {
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getServiseSubtypeId() {
        return serviseSubtypeId;
    }

    public void setServiseSubtypeId(int serviseSubtypeId) {
        this.serviseSubtypeId = serviseSubtypeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}