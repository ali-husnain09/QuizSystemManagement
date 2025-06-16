package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Result;
import org.bson.Document;
import org.bson.types.ObjectId;
import util.MongoUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ResultDAO {
    private final MongoCollection<Document> resultCollection;

    public ResultDAO() {
        MongoDatabase db = MongoUtil.getDatabase();
        resultCollection = db.getCollection("results");
    }

    public void insertResult(Result result) {
        Document doc = new Document("userId", result.getUserId())
                .append("quizId", result.getQuizId())
                .append("score", result.getScore())
                .append("submittedAt", result.getSubmittedAt().toString());
        resultCollection.insertOne(doc);
    }

    public List<Result> getResultsByUserId(ObjectId userId) {
        List<Result> list = new ArrayList<>();
        for (Document doc : resultCollection.find(eq("userId", userId))) {
            Result result = new Result();
            result.setId(doc.getObjectId("_id"));
            result.setUserId(doc.getObjectId("userId"));
            result.setQuizId(doc.getObjectId("quizId"));
            result.setScore(doc.getInteger("score"));
            result.setSubmittedAt(LocalDateTime.parse(doc.getString("submittedAt")));
            list.add(result);
        }
        return list;
    }
}
