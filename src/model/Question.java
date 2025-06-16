package model;

import org.bson.types.ObjectId;
import java.util.List;

public class Question {
    private ObjectId id;
    private ObjectId quizId;
    private String questionText;
    private List<String> options;
    private int correctOptionIndex;

    public Question() {}

    public Question(ObjectId quizId, String questionText, List<String> options, int correctOptionIndex) {
        this.quizId = quizId;
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getQuizId() {
        return quizId;
    }

    public void setQuizId(ObjectId quizId) {
        this.quizId = quizId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }


}
