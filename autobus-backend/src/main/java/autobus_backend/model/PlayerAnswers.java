package autobus_backend.model;

import java.util.Map;

public class PlayerAnswers {

    private String playerName;
    private Map<String, String> answers;

    public PlayerAnswers() {
    }

    public PlayerAnswers(String playerName, Map<String, String> answers) {
        this.playerName = playerName;
        this.answers = answers;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}