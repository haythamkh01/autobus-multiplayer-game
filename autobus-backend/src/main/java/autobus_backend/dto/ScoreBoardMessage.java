package autobus_backend.dto;

import java.util.Map;

public class ScoreBoardMessage {

    private String type;
    private String roomCode;
    private Map<String, Integer> totalScores;

    public ScoreBoardMessage() {
    }

    public ScoreBoardMessage(String type, String roomCode, Map<String, Integer> totalScores) {
        this.type = type;
        this.roomCode = roomCode;
        this.totalScores = totalScores;
    }

    public String getType() {
        return type;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public Map<String, Integer> getTotalScores() {
        return totalScores;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setTotalScores(Map<String, Integer> totalScores) {
        this.totalScores = totalScores;
    }
}