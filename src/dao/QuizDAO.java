// dao/QuizDAO.java
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Quiz;
import org.bson.Document;
import org.bson.types.ObjectId;
import util.MongoUtil;

import java.util.ArrayList;
import java.util.List;

public class QuizDAO {
    private final MongoCollection<Document> quizCollection;

    public QuizDAO() {
        MongoDatabase db = MongoUtil.getDatabase();
        quizCollection = db.getCollection("quizzes");
    }

    public void insertQuiz(Quiz quiz) {
        Document doc = new Document("title", quiz.getTitle())
                .append("timeLimitInMinutes", quiz.getTimeLimitInMinutes())
                .append("questionIds", quiz.getQuestionIds());
        quizCollection.insertOne(doc);
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> list = new ArrayList<>();
        for (Document doc : quizCollection.find()) {
            Quiz quiz = new Quiz();
            quiz.setId(doc.getObjectId("_id"));
            quiz.setTitle(doc.getString("title"));
            quiz.setDescription(doc.getString("description"));
            quiz.setTimeLimitInMinutes(doc.getInteger("timeLimitInMinutes"));
            quiz.setQuestionIds((List<ObjectId>) doc.get("questionIds"));
            list.add(quiz);
        }
        return list;
    }

    public Quiz getQuizById(ObjectId id) {
        Document doc = quizCollection.find(new Document("_id", id)).first();
        if (doc == null) return null;

        Quiz quiz = new Quiz();
        quiz.setId(doc.getObjectId("_id"));
        quiz.setTitle(doc.getString("title"));
        quiz.setDescription(doc.getString("description"));
        quiz.setTimeLimitInMinutes(doc.getInteger("timeLimitInMinutes"));
        quiz.setQuestionIds((List<ObjectId>) doc.get("questionIds"));
        return quiz;
    }
}
