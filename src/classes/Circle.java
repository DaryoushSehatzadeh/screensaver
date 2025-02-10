package classes;

import java.awt.*;

public class Circle extends Shape {

    public Circle(int height, int width, Color color){
        super(height, width, color);

    }

    public Shape duplicate(){
        return new Circle(this.getHeight(), this.getWidth(), this.getColor());
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillOval(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }
}
