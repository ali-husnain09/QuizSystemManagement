package gui;

import dao.QuizDAO;
import dao.ResultDAO;
import model.Quiz;
import model.Result;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ResultViewerFrame extends JFrame {

    private final ResultDAO resultDAO;
    private final QuizDAO quizDAO;
    private final User loggedInUser;
    private JLabel totalLabel;

    public ResultViewerFrame(User user) {
        this.loggedInUser = user;
        this.resultDAO = new ResultDAO();
        this.quizDAO = new QuizDAO();

        setTitle("Your Quiz Results");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Heading
        JLabel heading = new JLabel("Quiz Attempt Results", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(heading, BorderLayout.NORTH);

        // Table setup
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Quiz Title", "Score", "Submitted At"}, 0);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        // Bottom panel with total and back button
        JPanel bottomPanel = new JPanel(new BorderLayout());

        totalLabel = new JLabel("Total Quizzes Attempted: 0");
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        bottomPanel.add(totalLabel, BorderLayout.WEST);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose(); // Close current frame
            new QuizListFrame(loggedInUser); // Return to home/dashboard (you must have this GUI)
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        loadResults(model);

        setVisible(true);
    }

    private void loadResults(DefaultTableModel model) {
        List<Result> results = resultDAO.getResultsByUserId(loggedInUser.getId());

        for (Result r : results) {
            Quiz quiz = quizDAO.getQuizById(r.getQuizId());
            String quizTitle = (quiz != null) ? quiz.getTitle() : "Unknown Quiz";
            model.addRow(new Object[]{
                    quizTitle,
                    r.getScore(),
                    r.getSubmittedAt().toString()
            });
        }

        totalLabel.setText("Total Quizzes Attempted: " + results.size());
    }
}
