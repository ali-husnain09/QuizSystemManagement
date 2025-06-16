// gui/QuizListFrame.java
package gui;

import model.Quiz;
import model.User;
import service.QuizService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class QuizListFrame extends JFrame {
    private final User currentUser;

    public QuizListFrame(User user) {
        this.currentUser = user;
        QuizService quizService = new QuizService();
        JLabel title = new JLabel("Available Quizzes");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
//        setTitle("Available Quizzes");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        List<Quiz> quizList = quizService.getAllQuizzes();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> jList = new JList<>(listModel);
        jList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jList.setSelectionBackground(new Color(200, 220, 255));
        jList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Quiz quiz : quizList) {
            listModel.addElement(quiz.getTitle() + " - " + quiz.getDescription());
        }

        // Start Quiz Button
        JButton startButton = new JButton("Start Selected Quiz");
        startButton.addActionListener(e -> {
            int index = jList.getSelectedIndex();
            if (index != -1) {
                Quiz selectedQuiz = quizList.get(index);
                new QuizAttemptFrame(currentUser, selectedQuiz);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a quiz first.");
            }
        });

        // View Results Button
        JButton viewResultsButton = new JButton("View My Results");
        viewResultsButton.addActionListener(e -> {
            new ResultViewerFrame(currentUser);  // Open results GUI
        });

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(startButton);
        buttonPanel.add(viewResultsButton);

        add(new JScrollPane(jList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
