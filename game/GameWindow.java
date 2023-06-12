package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    private JComboBox<String> difficultyComboBox,mode;
    private JButton startButton;
    private GamePanel gamePanel;

    public GameWindow() {
        // Set up the form
        setTitle("Game Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        // Create the difficulty combo box
        difficultyComboBox = new JComboBox<>();
        difficultyComboBox.addItem("Easy");
        difficultyComboBox.addItem("Hard");

        mode = new JComboBox<>();
        mode.addItem("Ai Vs Ai");
        mode.addItem("Human Vs Human");
        mode.addItem("Ai Vs Human");


        // Create the start button
        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String difficulty = (String) difficultyComboBox.getSelectedItem();
                String our_mode = (String) mode.getSelectedItem();

                dispose(); // Close the options form

                // Start the game window with the selected difficulty
                gamePanel  = new GamePanel(difficulty,our_mode);
                createGameFrame();

                //JFrame gameFrame = new JFrame();
                // gameFrame.add(gp);
                // gameFrame.setTitle("Othello Game ");
                // gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // gameFrame.pack();
                // gameFrame.setVisible(true);
            }
        });


        
        // Add components to the form
        add(new JLabel("Difficulty:"));
        add(difficultyComboBox);
        add(mode);

        add(startButton);
    }
    private void createGameFrame() {
        JFrame gameFrame = new JFrame();
        gameFrame.add(gamePanel);
        gameFrame.setTitle("Othello Game");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }
    public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow gameOptionsForm = new GameWindow();
                gameOptionsForm.setVisible(true);
            }
        });   
     }

}
