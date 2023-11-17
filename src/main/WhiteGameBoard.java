package main;

import game.WhiteInput;
import models.FireLocation;
import pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WhiteGameBoard extends JPanel {
    private final boolean isFirstPlayer;
    private int tileSize;
    private final int cols = 16;
    private final int rows = 16;
    private final int IMAGES = 2;
    private ArrayList<Piece> pieces;
    private ImageIcon[] img;
    private final WhiteInput wInput = new WhiteInput(this);
    private boolean fired = false;
    private ArrayList<FireLocation> fireLocations = new ArrayList<FireLocation>();
    private final ArrayList<FireLocation> finalFireLocations = new ArrayList<>();

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

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    public ArrayList<FireLocation> getFireLocations() {
        return fireLocations;
    }

    public boolean addFireLocation(FireLocation fireLocation) {
        if(fireLocations.stream().noneMatch(
                fl -> {
                    boolean a = fl.getX() == fireLocation.getX();
                    boolean b = fl.getY() == fireLocation.getY();
                    return a&&b;
                }
        )) {
            this.fireLocations.add(fireLocation);
            return true;
        }
        return false;
    }

    public void setFireLocations(ArrayList<FireLocation> fireLocations) {
        this.fireLocations = fireLocations;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
        wInput.setGridPlaces(this.getGridPlaces());
        for (Piece piece: pieces) {
            finalFireLocations.addAll(piece.turnIntoFireLocation(this));
        }
    }

    public WhiteGameBoard(int tileSize, boolean isFirstPlayer, JFrame frame) {
        img = new ImageIcon[IMAGES];
        for (int i = 0; i < IMAGES; i++) {
            String path = "src/resources/" + (i+1) + ".png";
            img[i] = new ImageIcon(path);
        }

        this.wInput.setFrame(frame);
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

    public void testFire() {
        setFired(!wInput.isRightAns());
    }

    public boolean verifyPiece() {
        return fireLocations.containsAll(finalFireLocations);
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

        if(fireLocations!=null) {
            for (FireLocation fireLocation : fireLocations) {
                g2d.drawImage(fireLocation.getImg(), fireLocation.getX(), fireLocation.getY(), fireLocation.getWidth(), fireLocation.getHeight(), null);
            }
        }

        g.dispose();
    }

    public void endGame(JFrame frame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;

        JButton close = new JButton("Fechar");
        JDialog dialog = new JDialog(
                frame, "Fim de jogo"
        );
        dialog.setLayout(new GridBagLayout());
        dialog.add(
                new JLabel("O jogador " + (isFirstPlayer ? "P2" : "P1") + " venceu!"), gbc
        );

        gbc.gridy++;
        dialog.add(
                close, gbc
        );

        close.addActionListener(
                e -> {
                    frame.dispose();
                }
        );

        dialog.setSize(500, 250);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
