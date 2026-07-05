package autobus_backend.dto;

import java.util.Map;

public class FinishRoundMessage {

    private String roomCode;
    private Map<String, Map<String, Integer>> scores;

    public FinishRoundMessage() {
    }

    public String getRoomCode() {
        return roomCode;
    }

    public Map<String, Map<String, Integer>> getScores() {
        return scores;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setScores(Map<String, Map<String, Integer>> scores) {
        this.scores = scores;
    }
}