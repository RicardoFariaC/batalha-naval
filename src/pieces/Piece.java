package pieces;

import game.Move;
import main.GameBoard;
import main.WhiteGameBoard;
import models.FireLocation;

import java.util.ArrayList;
import java.util.Arrays;

public class Piece {
    private int col, row, xCoord, yCoord;
    private boolean isFirstPlayer;
    private String pieceName;
    private PieceShape shape;
    private final GameBoard board;

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

    public int getXCoord() {
        return xCoord;
    }

    public void setXCoord(int xCord) {
        this.xCoord = xCord;
    }

    public int getYCoord() {
        return yCoord;
    }

    public void setYCoord(int yCord) {
        this.yCoord = yCord;
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

    public void rotateInBoard() {
        setShape(shape.rotate());
    }

    public Piece rotate() {
        Piece rPiece = new Piece(board);
        rPiece.setShape(shape.rotate());
        return rPiece;
    }

    public Piece(GameBoard board) {
        this.board = board;
    }

    public int[][] gridPlace() {
        int[][] shape = this.getShape().getShape();
        int rowSize = shape.length;
        int columnSize = shape[0].length;
        int[][] place = new int[rowSize*columnSize][2];
        int indexRow = 0;

        for(int row = 0; row < rowSize; row++) {
            for (int col = 0; col < columnSize; col++) {
                if (shape[row][col] == 0) {
                    place[indexRow][0] = 0;
                    place[indexRow][1] = 0;
                } else {
                    place[indexRow][0] = this.getRow() + row;
                    place[indexRow][1] = this.getCol() + col;
                }
                indexRow++;
            }
        }
        return place;
    }

    public int[][] gridPlace(Move move) {
        int[][] shape = move.getPiece().getShape().getShape();
        int rowSize = shape.length;
        int columnSize = shape[0].length;
        int[][] place = new int[rowSize*columnSize][2];
        int indexRow = 0;

        for(int row = 0; row < rowSize; row++) {
            for (int col = 0; col < columnSize; col++) {
                if (shape[row][col] == 0) {
                    place[indexRow][0] = 0;
                    place[indexRow][1] = 0;
                } else {
                    place[indexRow][0] = move.getNewRow() + row;
                    place[indexRow][1] = move.getNewColumn() + col;
                }
                indexRow++;
            }
        }
        return place;
    }

    public ArrayList<FireLocation> turnIntoFireLocation(WhiteGameBoard wBoard) {
        ArrayList<FireLocation> fl = new ArrayList<>();
        for (int row = 0; row < this.gridPlace().length; row++) {
            if(!(this.gridPlace()[row][1] == 0) && !(this.gridPlace()[row][0] == 0)) {
                FireLocation newFl = new FireLocation(wBoard.getImg()[0].getImage(), this.gridPlace()[row][1] * board.getTileSize(), this.gridPlace()[row][0] * board.getTileSize(), board.getTileSize(), board.getTileSize(), true);
                fl.add(newFl);
            }
        }
        return fl;
    }

    @Override
    public String toString() {
        return this.pieceName + " / " + Arrays.deepToString(this.gridPlace());
    }
}
