// service/QuizService.java
package service;

import dao.QuestionDAO;
import dao.QuizDAO;
import dao.ResultDAO;
import model.Question;
import model.Quiz;
import model.Result;
import model.User;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public class QuizService {
    private final QuizDAO quizDAO;
    private final QuestionDAO questionDAO;
    private final ResultDAO resultDAO;

    public QuizService() {
        this.quizDAO = new QuizDAO();
        this.questionDAO = new QuestionDAO();
        this.resultDAO = new ResultDAO();
    }

    // ----------------------------
    // 1. Fetch all quizzes
    // ----------------------------
    public List<Quiz> getAllQuizzes() {
        return quizDAO.getAllQuizzes();
    }

    // ----------------------------
    // 2. Fetch a quiz by ID
    // ----------------------------
    public Quiz getQuizById(ObjectId id) {
        return quizDAO.getQuizById(id);
    }

    // ----------------------------
    // 3. Get questions for a quiz
    // ----------------------------
    public List<Question> getQuestionsForQuiz(ObjectId quizId) {
        Quiz quiz = quizDAO.getQuizById(quizId);
        return questionDAO.getQuestionsByIds(quiz.getQuestionIds());
    }


    // ----------------------------
    // 4. Submit result
    // ----------------------------
    public void submitQuizResult(User user, Quiz quiz, int score) {
        Result result = new Result();
        result.setUserId(user.getId());
        result.setQuizId(quiz.getId());
        result.setScore(score);
        result.setSubmittedAt(LocalDateTime.now());

        resultDAO.insertResult(result);
        System.out.println("Result submitted successfully.");
    }
}
