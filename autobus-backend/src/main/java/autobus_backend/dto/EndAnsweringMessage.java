package autobus_backend.dto;

public class EndAnsweringMessage {

    private String roomCode;

    public EndAnsweringMessage() {
    }

    public EndAnsweringMessage(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
}