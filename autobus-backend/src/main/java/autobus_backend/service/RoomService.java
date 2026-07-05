package autobus_backend.service;

import autobus_backend.model.Room;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class RoomService {

    private final Map<String, Room> rooms = new HashMap<>();

    public Room createRoom(String roomCode) {

        Room room = new Room(roomCode);

        rooms.put(roomCode, room);

        return room;
    }

    public Room getRoom(String roomCode) {
        return rooms.get(roomCode);
    }
    private final String[] letters = {
            "א","ב","ג","ד","ה","ו","ז","ח","ט","י",
            "כ","ל","מ","נ","ס","ע","פ","צ","ק","ר","ש","ת"
    };

    private final Random random = new Random();

    public String generateRandomLetter() {
        return letters[random.nextInt(letters.length)];
    }

}