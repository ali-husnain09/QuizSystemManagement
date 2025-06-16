// Main.java
import gui.AuthFrame;
import model.User;
import gui.QuizListFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Set system look and feel (optional for a native OS look)
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                UIManager.put("control", new Color(245, 248, 255));
                UIManager.put("info", new Color(232, 242, 254));
                UIManager.put("nimbusBase", new Color(51, 102, 153));
                UIManager.put("nimbusBlueGrey", new Color(190, 210, 245));
                UIManager.put("nimbusLightBackground", new Color(255, 255, 255));
                UIManager.put("text", new Color(40, 40, 40));
                UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));
        } catch (Exception ex) {
            System.out.println("Default look and feel will be used.");
        }


        // Launch login/signup frame
        SwingUtilities.invokeLater(() -> {
            AuthFrame authFrame = new AuthFrame();

            // Listen for successful login (via a callback)
            authFrame.setLoginSuccessListener(user -> {
                // After login, open quiz list
                new QuizListFrame(user);
            });
        });
    }
}
