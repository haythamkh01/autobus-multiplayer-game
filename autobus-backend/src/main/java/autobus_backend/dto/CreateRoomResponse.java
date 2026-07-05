package autobus_backend.dto;

public class CreateRoomResponse {

    private String roomCode;

    public CreateRoomResponse() {
    }

    public CreateRoomResponse(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
}