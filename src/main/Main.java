package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GridBagConstraints gbc = new GridBagConstraints();
        final boolean[] firstPlayer = {true};
        JButton confirm = new JButton("Confirmar peças");
        JButton changePlayer = new JButton("Mudar jogador");
        GameBoard boardP1 = new GameBoard(40, true);
        GameBoard boardP2 = new GameBoard(40, false);
        WhiteGameBoard wBoardP1 = new WhiteGameBoard(40, true);
        WhiteGameBoard wBoardP2 = new WhiteGameBoard(40, false);

        frame.setLayout(new GridBagLayout());
        frame.setTitle("Batalha Naval - P1");
        frame.setMinimumSize(new Dimension(1250, 1000));
        frame.setLocationRelativeTo(null);

        /* Button Logic */
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!firstPlayer[0]) {
                    firstPlayer[0] = true;
                    wBoardP2.setPieces(boardP2.getPieces());
                    frame.remove(boardP2);
                    frame.remove(confirm);
                    frame.add(changePlayer, gbc);
                    wBoardP2.setVisible(true);
                    wBoardP2.revalidate();
                    wBoardP2.repaint();
                    frame.setTitle("Batalha Naval");

                    return;
                }
                firstPlayer[0] = false;

                wBoardP1.setPieces(boardP1.getPieces());
                frame.remove(boardP1);
                boardP2.setVisible(true);
                boardP2.revalidate();
                boardP2.repaint();
                frame.setTitle("Batalha Naval - P2");
            }
        });
        changePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstPlayer[0] && wBoardP2.isFired()) {
                    wBoardP2.setVisible(false);
                    wBoardP2.revalidate();
                    wBoardP2.repaint();
                    wBoardP1.setVisible(true);
                    wBoardP1.revalidate();
                    wBoardP1.repaint();
                } else {
                    wBoardP1.setVisible(false);
                    wBoardP1.revalidate();
                    wBoardP1.repaint();
                    wBoardP2.setVisible(true);
                    wBoardP2.revalidate();
                    wBoardP2.repaint();
                }
            }
        });

        /* GB Constraints */

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = .5;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(4, 4, 4, 4);

        /* Adding Components */

        gbc.fill = GridBagConstraints.BOTH;
        frame.add(boardP1, gbc);
        frame.add(boardP2, gbc);
        frame.add(wBoardP2, gbc);
        frame.add(wBoardP1, gbc);

        gbc.gridx++;
        frame.add(new JLabel("Pressione R para girar a peça selecionada.",SwingConstants.LEADING), gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.gridx=0;
        frame.add(confirm, gbc);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
