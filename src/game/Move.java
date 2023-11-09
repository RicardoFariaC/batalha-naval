package game;

import main.GameBoard;
import pieces.Piece;

public class Move {

    private int oldColumn;
    private int oldRow;
    private int newColumn;
    private int newRow;
    Piece piece;

    public int getOldColumn() {
        return oldColumn;
    }

    public void setOldColumn(int oldColumn) {
        this.oldColumn = oldColumn;
    }

    public int getOldRow() {
        return oldRow;
    }

    public void setOldRow(int oldRow) {
        this.oldRow = oldRow;
    }

    public int getNewColumn() {
        return newColumn;
    }

    public void setNewColumn(int newColumn) {
        this.newColumn = newColumn;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getNewRow() {
        return newRow;
    }

    public void setNewRow(int newRow) {
        this.newRow = newRow;
    }

    public Move(GameBoard board, Piece piece, int newColumn, int newRow) {
        this.oldColumn = piece.getCol();
        this.oldRow = piece.getRow();
        this.newColumn = newColumn;
        this.newRow = newRow;
        this.piece = piece;
    }

}
