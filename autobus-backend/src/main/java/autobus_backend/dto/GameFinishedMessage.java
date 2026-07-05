package autobus_backend.dto;

import java.util.Map;

public class GameFinishedMessage {

    private String type;
    private Map<String, Integer> totalScores;
    private String winnerName;

    public GameFinishedMessage() {
    }

    public GameFinishedMessage(String type, Map<String, Integer> totalScores, String winnerName) {
        this.type = type;
        this.totalScores = totalScores;
        this.winnerName = winnerName;
    }

    public String getType() {
        return type;
    }

    public Map<String, Integer> getTotalScores() {
        return totalScores;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalScores(Map<String, Integer> totalScores) {
        this.totalScores = totalScores;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }
}