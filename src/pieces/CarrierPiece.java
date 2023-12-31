package pieces;

import main.GameBoard;

public class CarrierPiece extends Piece {
    public CarrierPiece(GameBoard board, int col, int row, boolean isFirstPlayer) {
        super(board);
        setCol(col);
        setRow(row);
        setFirstPlayer(isFirstPlayer);
        setXCoord(col * board.getTileSize());
        setYCoord(row * board.getTileSize());
        setPieceName("Carrier");
        setShape(new PieceShape(new int[][]{ {1, 1, 1, 1, 1} }));
    }
}
