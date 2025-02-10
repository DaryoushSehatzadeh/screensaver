package classes;

import java.awt.*;

public class Cross extends Shape{

    public Cross(int height, int width, Color color){
        super(height, width, color);

    }

    public Shape duplicate(){
        return new Cross(this.getHeight(), this.getWidth(), this.getColor());
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.getColor());
        // Draw the horizontal line
        g.drawLine(this.getX() - this.getWidth() / 2, this.getY(),
                this.getX() + this.getWidth() / 2, this.getY());

        // Draw the vertical line
        g.drawLine(this.getX(), this.getY() - this.getHeight() / 2,
                this.getX(), this.getY() + this.getHeight() / 2);
    }
}
