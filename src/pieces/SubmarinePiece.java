package pieces;

import main.GameBoard;

public class SubmarinePiece extends Piece{
    public SubmarinePiece(GameBoard board, int col, int row, boolean isFirstPlayer) {
        super(board);
        setCol(col);
        setRow(row);
        setFirstPlayer(isFirstPlayer);
        setXCoord(col * board.getTileSize());
        setYCoord(row * board.getTileSize());
        setPieceName("Submarine");
        setShape(new PieceShape(new int[][]{ {1} }));
    }
}
