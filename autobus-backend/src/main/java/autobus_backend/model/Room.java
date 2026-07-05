package autobus_backend.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {

    private String roomCode;
    private List<Player> players;
    private boolean gameStarted;
    private String currentLetter;

    private List<PlayerAnswers> submittedAnswers = new ArrayList<>();

    private Map<String, Integer> totalScores = new HashMap<>();
    private int currentRound = 1;
    private int maxRounds = 3;

    public Room() {
        players = new ArrayList<>();
    }


    public Room(String roomCode) {
        this.roomCode = roomCode;
        this.players = new ArrayList<>();
        this.gameStarted = false;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<PlayerAnswers> getSubmittedAnswers() {
        return submittedAnswers;
    }

    public void setSubmittedAnswers(List<PlayerAnswers> submittedAnswers) {
        this.submittedAnswers = submittedAnswers;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public String getCurrentLetter() {
        return currentLetter;
    }

    public void setCurrentLetter(String currentLetter) {
        this.currentLetter = currentLetter;
    }

    public Map<String, Integer> getTotalScores() {
        return totalScores;
    }

    public void setTotalScores(Map<String, Integer> totalScores) {
        this.totalScores = totalScores;
    }
    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }
}