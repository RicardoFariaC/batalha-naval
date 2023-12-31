package pieces;

import main.GameBoard;

public class CruiserPiece extends Piece {
    public CruiserPiece(GameBoard board, int col, int row, boolean isFirstPlayer) {
        super(board);
        setCol(col);
        setRow(row);
        setFirstPlayer(isFirstPlayer);
        setXCoord(getCol() * board.getTileSize());
        setYCoord(getRow() * board.getTileSize());
        setPieceName("Cruiser");
        setShape(new PieceShape(new int[][]{ {1, 1} }));
    }
}

