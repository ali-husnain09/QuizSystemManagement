// dao/QuestionDAO.java
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Question;
import org.bson.Document;
import org.bson.types.ObjectId;
import util.MongoUtil;
import static com.mongodb.client.model.Filters.in;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class QuestionDAO {
    private final MongoCollection<Document> questionCollection;

    public QuestionDAO() {
        MongoDatabase db = MongoUtil.getDatabase();
        questionCollection = db.getCollection("questions");
    }

    public void insertQuestion(Question question) {
        Document doc = new Document("quizId", question.getQuizId())
                .append("questionText", question.getQuestionText())
                .append("options", question.getOptions())
                .append("correctOptionIndex", question.getCorrectOptionIndex());
        questionCollection.insertOne(doc);
    }

    public List<Question> getQuestionsByIds(List<ObjectId> ids) {
        List<Question> list = new ArrayList<>();
        for (Document doc : questionCollection.find(in("_id", ids))) {
            Question q = new Question();
            q.setId(doc.getObjectId("_id"));
            q.setQuizId(doc.getObjectId("quizId")); // Can be null
            q.setQuestionText(doc.getString("questionText"));
            q.setOptions((List<String>) doc.get("options"));
            q.setCorrectOptionIndex(doc.getInteger("correctOptionIndex"));
            list.add(q);
        }
        return list;
    }
}
