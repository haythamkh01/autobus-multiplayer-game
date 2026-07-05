package autobus_backend.dto;

public class CreateRoomMessage {

    private String playerName;

    public CreateRoomMessage() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}