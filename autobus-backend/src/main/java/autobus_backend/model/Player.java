package autobus_backend.model;

public class Player {

    private String name;
    private boolean host;

    public Player() {
    }

    public Player(String name, boolean host) {
        this.name = name;
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public boolean isHost() {
        return host;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHost(boolean host) {
        this.host = host;
    }
}