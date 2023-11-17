package game;

import main.GameBoard;
import pieces.Piece;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RotatePieceInput implements KeyListener {
    private final GameBoard board;

    public RotatePieceInput(GameBoard board) {
        this.board = board;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int id = e.getKeyChar();
        if(board.getSelectedPiece() != null) {
            if(id == 'r') {
                board.getSelectedPiece().rotateInBoard();
                board.repaint();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
