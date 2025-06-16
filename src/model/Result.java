package model;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class Result {
    private ObjectId id;
    private ObjectId userId;
    private ObjectId quizId;
    private int score;
    private LocalDateTime submittedAt;

    public Result() {}

    public Result(ObjectId userId, ObjectId quizId, int score, LocalDateTime submittedAt) {
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.submittedAt = submittedAt;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getQuizId() {
        return quizId;
    }

    public void setQuizId(ObjectId quizId) {
        this.quizId = quizId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
