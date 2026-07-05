package autobus_backend.dto;

public class GameStartedMessage {

    private String type;
    private String roomCode;
    private String letter;
    private int currentRound;
    private int maxRounds;

    public GameStartedMessage() {
    }

    public GameStartedMessage(
            String type,
            String roomCode,
            String letter,
            int currentRound,
            int maxRounds
    ) {
        this.type = type;
        this.roomCode = roomCode;
        this.letter = letter;
        this.currentRound = currentRound;
        this.maxRounds = maxRounds;
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

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
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