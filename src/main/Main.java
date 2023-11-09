package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        GameBoard board = new GameBoard(40);
//        GameBoard board1 = new GameBoard(20);

        frame.setBackground(Color.WHITE);
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(1250, 1000));
        frame.setLocationRelativeTo(null);

        /* GB Constraints */

//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1;
//        gbc.weighty = 0.5;
//        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
//        gbc.insets = new Insets(4, 4, 4, 4);

        /* Adding Components */

//        gbc.fill = GridBagConstraints.BOTH;
        frame.add(board);
//        gbc.gridx++;
//        gbc.gridy = 0;
//        frame.add(board1, gbc);
//        gbc.gridy++;

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
