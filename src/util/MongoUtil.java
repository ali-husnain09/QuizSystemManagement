// util/MongoUtil.java
package util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static MongoClient mongoClient = null;
    private static MongoDatabase database = null;

    private static final String CONNECTION_STRING = "mongodb://localhost:27017"; // Change if hosted
    private static final String DB_NAME = "quiz_db";

    public static MongoDatabase getDatabase() {
        if (database == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DB_NAME);
        }
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
