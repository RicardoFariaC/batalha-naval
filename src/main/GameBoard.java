package main;

import game.Input;
import game.Move;
import game.RotatePieceInput;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard extends JPanel {
    private int tileSize;
    private final int cols = 16;
    private final int rows = 16;
    private final ArrayList<Piece> pieces = new ArrayList<>();
    private Piece selectedPiece;
    private boolean isFirstPlayer;
    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

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
        this.revalidate();
        this.repaint();
    }

    public boolean isFirstPlayer() {
        return isFirstPlayer;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        isFirstPlayer = firstPlayer;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public GameBoard(int tileSize, boolean isFirstPlayer) {
        this.isFirstPlayer = isFirstPlayer;
        this.setTileSize(tileSize);
        this.setPreferredSize(new Dimension(cols*tileSize+1, rows*tileSize+1));
        Input input = new Input(this);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        RotatePieceInput rInput = new RotatePieceInput(this);
        this.addKeyListener(rInput);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.setOpaque(false);
        this.setVisible(isFirstPlayer);

        addPieces();
    }

    public Piece getPiece(int col, int row) {
        int[][] moveGridPlace;
        for (Piece piece : pieces) {
            int index = 0;
            moveGridPlace = piece.gridPlace();
            for (int pieceR = 0; pieceR < piece.getShape().getShape().length; pieceR++) {
                for (int pieceC = 0; pieceC < piece.getShape().getShape()[0].length; pieceC++) {
                    if(moveGridPlace[index][1] == col && moveGridPlace[index][0] == row) {
                        return piece;
                    }
                index++;
                }
            }
//            if(piece.getCol() == col && piece.getRow() == row) {
//                return piece;
//            }
        }

        return null;
    }

    public void addPieces() {
        addCruiser();
        addBattleShip();
        addCarrier();
        addHidroPlane();
        addSubmarine();
    }
    
    private void addCruiser() {
        for (int i = 1; i <= 3*2+2; i++) {
            CruiserPiece cruiser = new CruiserPiece(this, i, 1, false);
            pieces.add(cruiser);
            i += cruiser.getShape().getShape()[0].length;
        }
    }

    private void addBattleShip() {
        for (int i = 1; i <= new BattleShipPiece(this, i, 1, false).getShape().getShape()[0].length * 2 + 1; i++) {
            BattleShipPiece battleShip = new BattleShipPiece(this, i, 3, false);
            pieces.add(battleShip);
            i += battleShip.getShape().getShape()[0].length;
        }
    }

    private void addCarrier() {
        for (int i = 1; i <= new CarrierPiece(this, i, 1, false).getShape().getShape()[0].length; i++) {
            CarrierPiece carrier = new CarrierPiece(this, i, 5, false);
            pieces.add(carrier);
            i += carrier.getShape().getShape()[0].length;
        }
    }

    private void addHidroPlane() {
        for (int i = 1; i <= new HidroPlanePiece(this, i, 1, false).getShape().getShape()[0].length * 3 + 2; i++) {
            HidroPlanePiece hidroPlane = new HidroPlanePiece(this, i, 7, false);
            pieces.add(hidroPlane);
            i += hidroPlane.getShape().getShape()[0].length;
        }
    }

    private void addSubmarine() {
        for (int i = 1; i <= new SubmarinePiece(this, i, 1, false).getShape().getShape()[0].length * 4 + 3; i++) {
            SubmarinePiece submarine = new SubmarinePiece(this, i, 10, false);
            pieces.add(submarine);
            i += submarine.getShape().getShape()[0].length;
        }
    }

    public void makeMove(Move move) {
        move.getPiece().setCol(move.getNewColumn());
        move.getPiece().setRow(move.getNewRow());
        move.getPiece().setXCoord(move.getNewColumn() * tileSize);
        move.getPiece().setYCoord(move.getNewRow() * tileSize);
    }

    public boolean isValidMove(Move move) {
        if(move.getNewColumn() < 1 || move.getNewRow() < 1) {
            return false;
        } else if (move.getNewColumn() > cols - move.getPiece().getShape().getShape()[0].length || move.getNewRow() > rows - move.getPiece().getShape().getShape().length) {
            return false;
        }

        int[][] moveGridPlace = move.getPiece().gridPlace(move);
        int[] zeroArray = {0,0};

        for(Piece piece : pieces) {
            int index = 0;
            for(int[] coordinate : piece.gridPlace()) {
                for(int[] gridPlace : moveGridPlace) {
                    if(move.getPiece() != piece && Arrays.equals(coordinate, gridPlace) && !Arrays.equals(gridPlace, zeroArray)) {
                        return false;
                    }
                }
            }
        }


        return true;
        // return move.getNewColumn() <= cols - move.getPiece().getShape().getShape()[0].length && move.getNewRow() <= rows - move.getPiece().getShape().getShape().length;
    }


    private void drawPiece(Graphics g, Piece piece) {
        for(int row = 0; row < piece.getShape().getShape().length; row++) {
            for(int col = 0; col < piece.getShape().getShape()[0].length; col++) {
                if(piece.getShape().getShape()[row][col] == 1) {
                    int pieceX = piece.getXCoord() + (col * tileSize);
                    int pieceY = piece.getYCoord() + (row * tileSize);

                    g.drawRect(pieceX, pieceY, tileSize, tileSize);
                    g.setColor(Color.green);
                    g.fillRect(pieceX, pieceY, tileSize, tileSize);
                    g.setColor(Color.black);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, tileSize / 2));
        String[] alphabet = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"};
        String[] numbers = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                g2d.setStroke(new BasicStroke(2));
                if(i == 0 && j == 0) {
                    g2d.fillRect(1, 1, tileSize, tileSize);
                    g2d.setColor(Color.white);
                    g2d.drawString(isFirstPlayer() ? "P1" : "P2", (tileSize/4), (3*tileSize/4));
                    g2d.setColor(Color.BLACK);
                } else if (i == 0) {
                    g2d.drawString(alphabet[j], (tileSize/4), j*(tileSize)+(3*tileSize/4));
                } else if (j == 0) {
                    g2d.drawString(numbers[i], i*(tileSize)+(tileSize/4), (3*tileSize/4));
                } else {
                    g2d.drawRect(j*tileSize, i*tileSize, tileSize, tileSize);
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
                    g2d.setColor(Color.BLACK);
                    g2d.setBackground(Color.WHITE);
                }
            }
        }

        for (Piece piecesPlaced : pieces) {
            drawPiece(g2d, piecesPlaced);
        }


        g.dispose();
    }
}
