package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameBoard extends JPanel {
    private int tileSize;
    private final int cols = 16;
    private final int rows = 16;
    private final ArrayList<Piece> pieces = new ArrayList<>();

    public int getTileSize() {
        return tileSize;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public GameBoard(int tileSize) {
        this.setTileSize(tileSize);
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(cols*tileSize, rows*tileSize));
        addPieces();
    }

    public void addPieces() {
        pieces.add(new CruiserPiece(this, 2, 1, false));
        pieces.add(new BattleShipPiece(this, 7, 2, false));
        pieces.add(new CarrierPiece(this, 9, 4, false));
        pieces.add(new HidroPlanePiece(this, 10, 8, false));
        pieces.add(new SubmarinePiece(this, 2, 11, false));
    }

    private void drawPiece(Graphics g, Piece piece) {
        for(int row = 0; row < piece.getShape().getShape().length; row++) {
            for(int col = 0; col < piece.getShape().getShape()[0].length; col++) {
                if(piece.getShape().getShape()[row][col] == 1) {
                    int pieceX = piece.getXCord() + (col * tileSize);
                    int pieceY = piece.getYCord() + (row * tileSize);

                    g.setColor(Color.green);
                    g.fillRect(pieceX, pieceY, tileSize, tileSize);
                    g.drawRect(pieceX, pieceY, tileSize, tileSize);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, tileSize / 2));
        String[] alphabet = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                g2d.setStroke(new BasicStroke(2));
                if(i == 0 && j == 0) {
                    g2d.fillRect(1, 1, tileSize, tileSize);
                    g2d.setColor(Color.BLACK);
                } else if (i == 0) {
                    g2d.drawString(alphabet[j], (tileSize/4), j*(tileSize)+(3*tileSize/4));
                } else if (j == 0) {
                    g2d.drawString(alphabet[i], i*(tileSize)+(tileSize/4), (3*tileSize/4));
                } else {
                    g2d.drawRect(j*tileSize, i*tileSize, tileSize, tileSize);
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
                    g2d.setColor(Color.BLACK);
                }
            }
        }

        for (Piece piecesPlaced : pieces) {
            drawPiece(g2d, piecesPlaced);
        }
    }


}
