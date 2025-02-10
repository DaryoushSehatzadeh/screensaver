package classes;

import java.awt.*;

public class Triangle extends Shape{
    int[] xPoints, yPoints;

    public Triangle(int height, int width, Color color){
        super(height, width, color);

    }

    public Shape duplicate(){
        return new Triangle(this.getHeight(), this.getWidth(), this.getColor());
    }

    @Override
    public void setPosition(int x , int y) {
        super.setX(x);
        super.setY(y);
        xPoints = new int[] {x + (this.getWidth() / 2), x, x + this.getWidth()};
        yPoints = new int[] {y, y + this.getHeight(), y + this.getHeight()};

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillPolygon(xPoints,yPoints,3);
    }
}
