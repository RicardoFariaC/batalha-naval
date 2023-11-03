package pieces;

import main.GameBoard;

public class Piece {
    private int col, row, xCord, yCord;
    private boolean isFirstPlayer;
    private String pieceName;
    private PieceShape shape;
    private GameBoard board;

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getXCord() {
        return xCord;
    }

    public void setXCord(int xCord) {
        this.xCord = xCord;
    }

    public int getYCord() {
        return yCord;
    }

    public void setYCord(int yCord) {
        this.yCord = yCord;
    }

    public boolean isFirstPlayer() {
        return isFirstPlayer;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        isFirstPlayer = firstPlayer;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public PieceShape getShape() {
        return shape;
    }

    public void setShape(PieceShape shape) {
        this.shape = shape;
    }

    public void rotate() {
        setShape(shape.rotate());
    }

    public Piece(GameBoard board) {
        this.board = board;
    }
}
