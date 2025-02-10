package classes;

import javax.swing.*;
import java.awt.*;

public abstract class Shape {
    private int height;
    private int width;
    private Color color;
    private boolean isActive = false;
    ImageIcon image;

    //properties for animation
    private int x;
    private int y;
    private int xspeed;
    private int yspeed;

    public Shape(int height, int width, Color color){
        this.height = height;
        this.width = width;
        this.color = color;
    }

    public Shape(int height, int width){
        this.height = height;
        this.width = width;
    }

    public abstract Shape duplicate();

    public abstract void draw(Graphics g);

    //Getters & setters
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getXSpeed() {
        return xspeed;
    }

    public void setXSpeed(int xspeed) {
        this.xspeed = xspeed;
    }

    public int getYSpeed() {
        return yspeed;
    }

    public void setYSpeed(int yspeed) {
        this.yspeed = yspeed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
