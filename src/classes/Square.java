package classes;

import java.awt.*;

public class Square extends Shape{

    public Square(int height, int width, Color color){
        super(height, width, color);

    }

    public Shape duplicate(){
        return new Square(this.getHeight(), this.getWidth(), this.getColor());
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }
}
