package pieces;

import javax.swing.*;
import java.awt.*;

public class PieceShape extends JPanel {
    private final int[][] shape;

    public int[][] getShape() {
        return shape;
    }

    public PieceShape rotate() {
        int[][] flippedShape = new int[shape.length][shape[0].length];
        int[][] newShape = new int[shape[0].length][shape.length];

        /* Flipping */
        for(int i = 0; i < shape.length; i++) {
            for(int j = 0; j < shape[0].length; j++) {
                flippedShape[i][j] = shape[i][shape[0].length - 1 - j];
            }
        }

        /* Transposing */
        for (int i = 0; i < shape[0].length; i++) {
            for (int j = 0; j < shape.length; j++) {
                newShape[i][j] = flippedShape[j][i];
            }
        }

        return new PieceShape(newShape);
    }
    public PieceShape(int[][] shape) {
        this.shape = shape;
    }
}
