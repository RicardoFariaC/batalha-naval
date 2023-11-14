package main;

import game.WhiteInput;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WhiteGameBoard extends JPanel {
    private final boolean isFirstPlayer;
    private int tileSize;
    private final int cols = 16;
    private final int rows = 16;
    private final int IMAGES = 2;
    private ArrayList<Piece> pieces;
    private ImageIcon[] img;
    private WhiteInput wInput = new WhiteInput(this);


    public boolean isFirstPlayer() {
        return isFirstPlayer;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public ImageIcon[] getImg() {
        return img;
    }

    public void setImg(ImageIcon[] img) {
        this.img = img;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
        wInput.setGridPlaces(this.getGridPlaces());
    }

    public WhiteGameBoard(int tileSize, boolean isFirstPlayer) {
        img = new ImageIcon[IMAGES];
        for (int i = 0; i < IMAGES; i++) {
            String path = "src/resources/" + (i+1) + ".png";
            img[i] = new ImageIcon(path);
        }
        this.isFirstPlayer = isFirstPlayer;
        this.setTileSize(tileSize);
        this.setPreferredSize(new Dimension(cols*tileSize+1, rows*tileSize+1));
        this.addMouseListener(wInput);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.setOpaque(false);
    }

    public ArrayList<int[][]> getGridPlaces() {
        ArrayList<int[][]> gridPlaces = new ArrayList<>();

        for (int i = 0; i < this.getPieces().size(); i++) {
                    gridPlaces.add(this.getPieces().get(i).gridPlace());
        }

        return gridPlaces;
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
                    g2d.drawString(isFirstPlayer() ? "P1" : "P2", (tileSize/4), (3*tileSize/4));
                    g2d.setColor(Color.black);
                } else if (i == 0) {
                    g2d.drawString(alphabet[j], (tileSize/4), j*(tileSize)+(3*tileSize/4));
                } else if (j == 0) {
                    g2d.drawString(numbers[i], i*(tileSize)+(tileSize/4), (3*tileSize/4));
                } else {
                    g2d.drawRect(j*tileSize, i*tileSize, tileSize, tileSize);
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(j*tileSize, i*tileSize, tileSize, tileSize);
                    g2d.setColor(Color.BLACK);
                    g2d.setBackground(Color.BLACK);
                }
            }
        }

        g.dispose();
    }

}
