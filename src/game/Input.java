package game;

import main.GameBoard;
import pieces.Piece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input extends MouseAdapter {
    GameBoard board;

    public Input(GameBoard board) {
        this.board = board;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX() / board.getTileSize();
        int row = e.getY() / board.getTileSize();

        Piece pieceXY = board.getPiece(col, row);

        if(pieceXY != null) {
            board.setSelectedPiece(pieceXY);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX() / board.getTileSize();
        int row = e.getY() / board.getTileSize();

        if(board.getSelectedPiece() != null) {
            Move move = new Move(board, board.getSelectedPiece(), col, row);

            if(board.isValidMove(move))
                board.makeMove(move);
            else {
                board.getSelectedPiece().setXCoord(board.getSelectedPiece().getCol() * board.getTileSize());
                board.getSelectedPiece().setYCoord(board.getSelectedPiece().getRow() * board.getTileSize());
            }
        }

        board.setSelectedPiece(null);
        board.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.getSelectedPiece() != null) {
            board.getSelectedPiece().setXCoord(e.getX() - board.getTileSize() / 2);
            board.getSelectedPiece().setYCoord(e.getY() - board.getTileSize() / 2);

            board.repaint();
        }
    }
}
