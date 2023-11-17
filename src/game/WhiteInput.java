package game;

import main.MainFrame;
import main.WhiteGameBoard;
import models.FireLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class WhiteInput implements MouseListener {
    private final WhiteGameBoard board;
    private boolean rightAns;
    private ArrayList<int[][]> gridPlaces;
    private JFrame frame;

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public boolean isRightAns() {
        return rightAns;
    }

    public void setRightAns(boolean rightAns) {
        this.rightAns = rightAns;
    }

    public void setGridPlaces(ArrayList<int[][]> gridPlaces) {
        this.gridPlaces = gridPlaces;
    }

    public WhiteInput(WhiteGameBoard board) {
        this.board = board;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(board.isFired()) {
            return;
        }

        this.setRightAns(false);
        int col = e.getY() / board.getTileSize();
        int row = e.getX() / board.getTileSize();

        if((row < 1 || col < 1) || (row >= board.getRows() || col >= board.getCols())) {
            return;
        }

        board.getGraphics().clearRect(row, col, 0, 0);

        for(int[][] gridPlace : gridPlaces) {
            for (int[] ints : gridPlace) {
                if (row == ints[1] && col == ints[0]) {
                    this.setRightAns(true);
                    break;
                }
            }
        }

        board.testFire();
        FireLocation fl = new FireLocation(this.isRightAns() ? board.getImg()[1].getImage() : board.getImg()[0].getImage(), row * board.getTileSize(), col * board.getTileSize(), board.getTileSize(), board.getTileSize(), this.isRightAns());
        boolean add = board.addFireLocation(fl);
        if(add)
            board.getGraphics().drawImage(fl.getImg(), fl.getX(), fl.getY(), fl.getWidth(), fl.getHeight(), null);
        if(board.verifyPiece())
            board.endGame(frame);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
