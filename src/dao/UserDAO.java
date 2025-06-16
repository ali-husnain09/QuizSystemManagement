// dao/UserDAO.java
package dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.User;
import org.bson.Document;
import org.bson.types.ObjectId;
import util.MongoUtil;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO {
    private final MongoCollection<Document> userCollection;

    public UserDAO() {
        MongoDatabase db = MongoUtil.getDatabase();
        userCollection = db.getCollection("users");
    }

    // Insert new user
    public void insertUser(User user) {
        Document doc = new Document("name", user.getName())
                .append("email", user.getEmail())
                .append("password", user.getPassword())
                .append("role", user.getRole());
        userCollection.insertOne(doc);
    }

    // Find user by email (for login)
    public User getUserByEmail(String email) {
        Document doc = userCollection.find(eq("email", email)).first();
        if (doc != null) {
            User user = new User();
            user.setId(doc.getObjectId("_id"));
            user.setName(doc.getString("name"));
            user.setEmail(doc.getString("email"));
            user.setPassword(doc.getString("password"));
            user.setRole(doc.getString("role"));
            return user;
        }
        return null;
    }

    // Check if user exists
    public boolean userExists(String email) {
        return userCollection.find(eq("email", email)).first() != null;
    }
}
