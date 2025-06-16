// gui/QuizAttemptFrame.java
package gui;

import model.Question;
import model.Quiz;
import model.User;
import service.QuizService;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class QuizAttemptFrame extends JFrame {
    private final QuizService quizService;

    private final Map<Question, ButtonGroup> answerMap = new HashMap<>();

    public QuizAttemptFrame(User user, Quiz quiz) {
        this.quizService = new QuizService();

        setTitle("Attempt Quiz - " + quiz.getTitle());
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<Question> questions = quizService.getQuestionsForQuiz(quiz.getId());

        for (Question q : questions) {
            JLabel qLabel = new JLabel("Q: " + q.getQuestionText());
            panel.add(qLabel);

            ButtonGroup bg = new ButtonGroup();
            for (String opt : q.getOptions()) {
                JRadioButton btn = new JRadioButton(opt);
                bg.add(btn);
                panel.add(btn);
            }
            answerMap.put(q, bg);
            panel.add(Box.createVerticalStrut(10));
        }

        JButton submitBtn = new JButton("Submit Quiz");
        submitBtn.addActionListener(e -> {
            int score = 0;
            for (Map.Entry<Question, ButtonGroup> entry : answerMap.entrySet()) {
                Question q = entry.getKey();
                ButtonGroup bg = entry.getValue();

                Enumeration<AbstractButton> buttons = bg.getElements();
                while (buttons.hasMoreElements()) {
                    AbstractButton btn = buttons.nextElement();
                    if (btn.isSelected() && btn.getText().equals(q.getOptions().get(q.getCorrectOptionIndex()))) {
                        score++;
                        break;
                    }
                }
            }

            quizService.submitQuizResult(user, quiz, score);
            JOptionPane.showMessageDialog(this, "Quiz Completed!\nYour Score: " + score);
            dispose();
            new QuizListFrame(user); // Go back to quiz list or dashboard
        });

        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        add(submitBtn, BorderLayout.SOUTH);

        setVisible(true);
    }
}
