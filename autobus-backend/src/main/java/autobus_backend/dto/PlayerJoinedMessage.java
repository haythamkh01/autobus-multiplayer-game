package autobus_backend.dto;

public class PlayerJoinedMessage {

    private String type;
    private String roomCode;
    private String playerName;

    public PlayerJoinedMessage() {
    }

    public PlayerJoinedMessage(String type, String roomCode, String playerName) {
        this.type = type;
        this.roomCode = roomCode;
        this.playerName = playerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}