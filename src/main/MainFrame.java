package main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private GridBagConstraints gbc;

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public void init() {
        GridBagConstraints gbc = new GridBagConstraints();
        final boolean[] firstPlayer = {true};

        JLabel text = new JLabel("Pressione R para girar a peça selecionada.",SwingConstants.LEADING);
        JButton confirm = new JButton("Confirmar peças");
        JButton changePlayer = new JButton("Mudar jogador");
        GameBoard boardP1 = new GameBoard(40, true);
        GameBoard boardP2 = new GameBoard(40, false);
        WhiteGameBoard wBoardP1 = new WhiteGameBoard(40, true, this);
        WhiteGameBoard wBoardP2 = new WhiteGameBoard(40, false, this);

        this.setLayout(new GridBagLayout());
        this.setTitle("Batalha Naval - P1");
        this.setMinimumSize(new Dimension(1250, 1000));
        this.setLocationRelativeTo(null);

        confirm.addActionListener(e -> {
            if(!firstPlayer[0]) {
                firstPlayer[0] = true;
                wBoardP2.setPieces(boardP2.getPieces());
                this.remove(boardP2);
                this.remove(confirm);
                this.add(changePlayer, gbc);
                wBoardP2.setVisible(true);
                wBoardP2.revalidate();
                wBoardP2.repaint();
                text.setText("Ao errar, aperte o botão de mudar jogador para passar a vez.");
                this.setTitle("Batalha Naval - Turno do P1");

                return;
            }
            firstPlayer[0] = false;

            wBoardP1.setPieces(boardP1.getPieces());
            this.remove(boardP1);
            boardP2.setVisible(true);
            boardP2.revalidate();
            boardP2.repaint();
            this.setTitle("Batalha Naval - P2");
        });
        changePlayer.addActionListener(e -> {
            if(firstPlayer[0] && wBoardP2.isFired()) {
                firstPlayer[0] = false;
                wBoardP2.setFired(false);

                wBoardP2.setVisible(false);
                wBoardP2.revalidate();
                wBoardP2.repaint();

                wBoardP1.setVisible(true);
                wBoardP1.revalidate();
                wBoardP1.repaint();
                this.setTitle("Batalha Naval - Turno do P2");

            } else if(!firstPlayer[0] && wBoardP1.isFired()) {
                firstPlayer[0] = true;
                wBoardP1.setFired(false);

                wBoardP1.setVisible(false);
                wBoardP1.revalidate();
                wBoardP1.repaint();

                wBoardP2.setVisible(true);
                wBoardP2.revalidate();
                wBoardP2.repaint();
                this.setTitle("Batalha Naval - Turno do P1");
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
        this.add(boardP1, gbc);
        this.add(boardP2, gbc);
        this.add(wBoardP2, gbc);
        this.add(wBoardP1, gbc);

        gbc.gridx++;
        this.add(text, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.gridx=0;
        this.add(confirm, gbc);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
