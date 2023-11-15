package game;

import main.GameBoard;
import main.WhiteGameBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class WhiteInput implements MouseListener {
    private WhiteGameBoard board;
    private boolean rightAns;
    private ArrayList<int[][]> gridPlaces;

    public boolean isRightAns() {
        return rightAns;
    }

    public void setRightAns(boolean rightAns) {
        this.rightAns = rightAns;
    }

    public WhiteInput(WhiteGameBoard board) {
        this.board = board;
    }

    public void setGridPlaces(ArrayList<int[][]> gridPlaces) {
        this.gridPlaces = gridPlaces;
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

        System.out.println("COL>" + col);
        System.out.println("ROW>" + row);
        for(int[][] gridPlace : gridPlaces) {
            for (int[] ints : gridPlace) {
                System.out.println("GCOL>" + ints[1]);
                System.out.println("GROW>" + ints[0]);
                System.out.println(row==ints[0]);
                System.out.println(col==ints[1]);
                if (row == ints[1] && col == ints[0]) {
                    this.setRightAns(true);
                    break;
                }
            }
        }

        board.testFire();
        if(this.isRightAns())
            board.getGraphics().drawImage(board.getImg()[1].getImage(), row * board.getTileSize(), col * board.getTileSize(), board.getTileSize(), board.getTileSize(), null);
        else
            board.getGraphics().drawImage(board.getImg()[0].getImage(), row * board.getTileSize(), col * board.getTileSize(), board.getTileSize(), board.getTileSize(), null);



        // Right Ans Image
//        board.getGraphics().drawImage(board.getImg()[1].getImage(), row * board.getTileSize(), col * board.getTileSize(), board.getTileSize(), board.getTileSize(), null);
        // Wrong Ans Image
//        board.getGraphics().drawImage(board.getImg()[0].getImage(), row * board.getTileSize(), col * board.getTileSize(), board.getTileSize(), board.getTileSize(), null);
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
