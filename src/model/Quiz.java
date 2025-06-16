package model;

import org.bson.types.ObjectId;
import java.util.List;

public class Quiz {
    private ObjectId id;
    private String title;
    private int timeLimitInMinutes;
    private List<ObjectId> questionIds;
    private String description; // 👈 Add this field


    public Quiz() {}

    public Quiz(String title, String description, int timeLimitInMinutes, List<ObjectId> questionIds) {
        this.title = title;
        this.timeLimitInMinutes = timeLimitInMinutes;
        this.questionIds = questionIds;
        this.description = description;

    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTimeLimitInMinutes() {
        return timeLimitInMinutes;
    }

    public void setTimeLimitInMinutes(int timeLimitInMinutes) {
        this.timeLimitInMinutes = timeLimitInMinutes;
    }

    public List<ObjectId> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<ObjectId> questionIds) {
        this.questionIds = questionIds;
    }
}
