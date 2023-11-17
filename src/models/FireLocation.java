package models;

import java.awt.*;

public class FireLocation {
    private Image img;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isRight;

    public FireLocation(Image img, int x, int y, int width, int height, boolean isRight) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isRight = isRight;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return this.getX() + " / " + this.getY();
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;
        if(obj instanceof FireLocation) {
            equal = this.getX() == ((FireLocation) obj).getX() && this.getY() == ((FireLocation) obj).getY();
        }
        return equal;
    }
}
