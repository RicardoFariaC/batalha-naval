package pieces;

import main.GameBoard;

public class BattleShipPiece extends Piece {
    public BattleShipPiece(GameBoard board, int col, int row, boolean isFirstPlayer) {
        super(board);
        setCol(col);
        setRow(row);
        setFirstPlayer(isFirstPlayer);
        setXCoord(col * board.getTileSize());
        setYCoord(row * board.getTileSize());
        setPieceName("Battleship");
        setShape(new PieceShape(new int[][]{ {1, 1, 1, 1} }));
    }
}
