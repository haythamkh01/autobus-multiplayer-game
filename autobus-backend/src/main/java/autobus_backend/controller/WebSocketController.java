package autobus_backend.controller;

import autobus_backend.dto.*;
import autobus_backend.model.Player;
import autobus_backend.model.PlayerAnswers;
import autobus_backend.model.Room;
import autobus_backend.service.RoomService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RoomService roomService;

    public WebSocketController(SimpMessagingTemplate messagingTemplate, RoomService roomService) {
        this.messagingTemplate = messagingTemplate;
        this.roomService = roomService;
    }

    @MessageMapping("/join-room")
    public void joinRoom(JoinRoomMessage message) {
        String roomCode = message.getRoomCode();
        String playerName = message.getPlayerName();

        Room room = roomService.getRoom(roomCode);

        if (room == null) {
            System.out.println("Room not found: " + roomCode);
            return;
        }

        boolean exists = room.getPlayers()
                .stream()
                .anyMatch(player -> player.getName().equals(playerName));

        if (!exists) {
            Player newPlayer = new Player(playerName, false);
            room.getPlayers().add(newPlayer);
        }


        PlayersUpdatedMessage response = new PlayersUpdatedMessage(
                "PLAYERS_UPDATED",
                roomCode,
                room.getPlayers()
        );

        messagingTemplate.convertAndSend(
                "/topic/room/" + roomCode,
                response
        );
    }
    @MessageMapping("/start-game")
    public void startGame(StartGameMessage message) {

        Room room = roomService.getRoom(message.getRoomCode());

        if (room == null) {
            return;
        }

        room.setGameStarted(true);
        String randomLetter = roomService.generateRandomLetter();

        room.setCurrentLetter(randomLetter);
        GameStartedMessage response = new GameStartedMessage(
                "GAME_STARTED",
                room.getRoomCode(),
                room.getCurrentLetter(),
                room.getCurrentRound(),
                room.getMaxRounds()
        );
        messagingTemplate.convertAndSend(
                "/topic/room/" + room.getRoomCode(),
                response
        );
    }
    @MessageMapping("/submit-answers")
    public void submitAnswers(SubmitAnswersMessage message) {

        Room room = roomService.getRoom(message.getRoomCode());

        if (room == null) {
            return;
        }

        PlayerAnswers playerAnswers = new PlayerAnswers(
                message.getPlayerName(),
                message.getAnswers()
        );

        room.getSubmittedAnswers().add(playerAnswers);

        if (room.getSubmittedAnswers().size() == room.getPlayers().size()) {

            AnswersUpdatedMessage response = new AnswersUpdatedMessage(
                    "ANSWERS_UPDATED",
                    room.getRoomCode(),
                    room.getSubmittedAnswers()
            );

            messagingTemplate.convertAndSend(
                    "/topic/room/" + room.getRoomCode(),
                    response
            );
        }
    }
    @MessageMapping("/finish-round")
    public void finishRound(FinishRoundMessage message) {

        Room room = roomService.getRoom(message.getRoomCode());

        if (room == null) {
            return;
        }

        for (String playerName : message.getScores().keySet()) {

            int roundScore = 0;

            Map<String, Integer> playerScores = message.getScores().get(playerName);

            for (Integer points : playerScores.values()) {
                roundScore += points;
            }

            int currentTotal = room.getTotalScores().getOrDefault(playerName, 0);

            room.getTotalScores().put(
                    playerName,
                    currentTotal + roundScore
            );
        }

        ScoreBoardMessage response = new ScoreBoardMessage(
                "SCOREBOARD_UPDATED",
                room.getRoomCode(),
                room.getTotalScores()
        );
        messagingTemplate.convertAndSend(
                "/topic/room/" + room.getRoomCode(),
                response
        );
    }
    @MessageMapping("/next-round")
    public void nextRound(NextRoundMessage message) {

        Room room = roomService.getRoom(message.getRoomCode());

        if (room == null) {
            return;
        }
        if (room.getCurrentRound() == room.getMaxRounds()) {

            String winnerName = room.getTotalScores()
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("No winner");

            GameFinishedMessage response = new GameFinishedMessage(
                    "GAME_FINISHED",
                    room.getTotalScores(),
                    winnerName
            );

            messagingTemplate.convertAndSend(
                    "/topic/room/" + room.getRoomCode(),
                    response
            );

            return;
        }

        room.getSubmittedAnswers().clear();
        room.setCurrentRound(room.getCurrentRound() + 1);
        String randomLetter = roomService.generateRandomLetter();

        room.setCurrentLetter(randomLetter);
        room.setGameStarted(true);

        GameStartedMessage response = new GameStartedMessage(
                "GAME_STARTED",
                room.getRoomCode(),
                room.getCurrentLetter(),
                room.getCurrentRound(),
                room.getMaxRounds()
        );

        messagingTemplate.convertAndSend(
                "/topic/room/" + room.getRoomCode(),
                response
        );
    }
    @MessageMapping("/end-answering")
    public void endAnswering(EndAnsweringMessage message) {

        Room room = roomService.getRoom(message.getRoomCode());

        if (room == null) {
            return;
        }

        AnswersUpdatedMessage response = new AnswersUpdatedMessage(
                "ANSWERS_UPDATED",
                room.getRoomCode(),
                room.getSubmittedAnswers()
        );

        messagingTemplate.convertAndSend(
                "/topic/room/" + room.getRoomCode(),
                response
        );
    }
}