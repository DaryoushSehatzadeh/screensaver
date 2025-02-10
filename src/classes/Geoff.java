package classes;

import javax.swing.*;
import java.awt.*;

public class Geoff extends Shape{

    public Geoff(int height, int width){
        super(height, width);
        this.setImage(new ImageIcon("src/img/GeoffGillespie.png"));
    }

    public Shape duplicate(){
        return new Geoff(this.getHeight(), this.getWidth());
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image.getImage(),this.getX(),this.getY(),this.getWidth(),this.getHeight(),null);
    }
}
