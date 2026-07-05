package autobus_backend.dto;

import java.util.Map;

public class SubmitAnswersMessage {

    private String roomCode;
    private String playerName;
    private Map<String, String> answers;

    public SubmitAnswersMessage() {
    }

    public String getRoomCode() {
        return roomCode;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}