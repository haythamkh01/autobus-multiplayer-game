package autobus_backend.dto;

import autobus_backend.model.Player;
import java.util.List;

public class PlayersUpdatedMessage {

    private String type;
    private String roomCode;
    private List<Player> players;

    public PlayersUpdatedMessage() {
    }

    public PlayersUpdatedMessage(String type, String roomCode, List<Player> players) {
        this.type = type;
        this.roomCode = roomCode;
        this.players = players;
    }

    public String getType() {
        return type;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}