package autobus_backend.dto;

public class StartGameMessage {

    private String roomCode;
    private String letter;

    public StartGameMessage() {
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
}