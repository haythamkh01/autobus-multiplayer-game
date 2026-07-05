package autobus_backend.dto;

import autobus_backend.model.PlayerAnswers;
import java.util.List;

public class AnswersUpdatedMessage {

    private String type;
    private String roomCode;
    private List<PlayerAnswers> submittedAnswers;

    public AnswersUpdatedMessage() {
    }

    public AnswersUpdatedMessage(String type, String roomCode, List<PlayerAnswers> submittedAnswers) {
        this.type = type;
        this.roomCode = roomCode;
        this.submittedAnswers = submittedAnswers;
    }

    public String getType() {
        return type;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public List<PlayerAnswers> getSubmittedAnswers() {
        return submittedAnswers;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setSubmittedAnswers(List<PlayerAnswers> submittedAnswers) {
        this.submittedAnswers = submittedAnswers;
    }
}