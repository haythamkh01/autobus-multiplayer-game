package autobus_backend.controller;

import autobus_backend.dto.CreateRoomMessage;
import autobus_backend.dto.CreateRoomResponse;
import autobus_backend.model.Player;
import autobus_backend.model.Room;
import autobus_backend.service.RoomService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/api/rooms")
    public CreateRoomResponse createRoom(
            @RequestBody CreateRoomMessage request
    ) {

        String roomCode = generateRoomCode();

        Room room = roomService.createRoom(roomCode);

        Player host = new Player(
                request.getPlayerName(),
                true
        );

        room.getPlayers().add(host);

        return new CreateRoomResponse(room.getRoomCode());
    }

    private String generateRoomCode() {
        return String.valueOf((int) (1000 + Math.random() * 9000));
    }
}