package pieces;

import main.GameBoard;

public class HidroPlanePiece extends Piece{

    public HidroPlanePiece(GameBoard board, int col, int row, boolean isFirstPlayer) {
        super(board);
        setCol(col);
        setRow(row);
        setFirstPlayer(isFirstPlayer);
        setXCord(col * board.getTileSize());
        setYCord(row * board.getTileSize());
        setPieceName("Hidroplane");
        setShape(new PieceShape(new int[][]{ {0, 1, 0}, {1, 0, 1} }));
    }
}
