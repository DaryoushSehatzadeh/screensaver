package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ShapeDisplay extends JPanel {

    //List of predefined shape instances
    private final ArrayList<Shape> ShapeList = new ArrayList<Shape>();
    {
        ShapeList.add(new Circle(50, 50, Color.RED));
        ShapeList.add(new Circle(25, 25, Color.BLUE));
        ShapeList.add(new Circle(75, 75, Color.BLACK));

        ShapeList.add(new Square(50, 50, Color.RED));
        ShapeList.add(new Square(20, 20, Color.BLUE));
        ShapeList.add(new Square(30, 30, Color.BLACK));

        ShapeList.add(new Triangle(50, 50, Color.RED));
        ShapeList.add(new Triangle(10, 10, Color.BLUE));
        ShapeList.add(new Triangle(30, 30, Color.BLACK));

        ShapeList.add(new Cross(50, 50, Color.RED));
        ShapeList.add(new Cross(15, 15, Color.BLUE));
        ShapeList.add(new Cross(30, 30, Color.BLACK));

        ShapeList.add(new Geoff(10,10));

    }

    //List to handle shapes shown on screen
    private final ArrayList<Shape> ActiveShapes = new ArrayList<Shape>();

    //Timer for repaints
    private Timer timer;

    public ShapeDisplay(){
        //Window params
        setBounds(0, 0, 1200, 570); //Account for JFrame title bar
        setVisible(true);
        setLayout(null);
        setBackground(Color.WHITE);

        //Set and start timer
        timer = new Timer(1, e -> repaint());
        timer.start();

        //Add mouse listener to handle user clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //On click pick a random shape and get user cursor position
                int randomShape = (int) (Math.random() * (ShapeList.size() - 1));
                Shape currentShape = ShapeList.get(randomShape).duplicate();
                currentShape.setPosition(e.getX(),e.getY());

                //Set random speed
                int randomXSpeed = (int) (Math.random() * 4) - 2;
                int randomYSpeed = (int) (Math.random() * 4) - 2;

                //If both speeds are 0, unleash the Geoff
                if (randomXSpeed == 0 && randomYSpeed == 0){
                    currentShape = ShapeList.getLast().duplicate();
                    currentShape.setPosition(e.getX(),e.getY());
                    currentShape.setXSpeed((int) (Math.random() * 4) - 2);
                    currentShape.setYSpeed((int) (Math.random() * 4) - 2);
                } else {
                    currentShape.setXSpeed(randomXSpeed);
                    currentShape.setYSpeed(randomYSpeed);
                }

                //Add shape to active shapes and paint it
                ActiveShapes.add(currentShape);
                repaint();
            }
        });
    }

    //Handle re-render on timer tick
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Temp array for holding shapes that succumb to Geoff's wrath
        ArrayList<Shape> ShapesToRemove = new ArrayList<>();

        //Collisions with walls
        for (Shape shape : ActiveShapes){

            //Move to next position
            HelperFunctions.MoveShape(shape);

            //Check for and resolve wall collisions
            HelperFunctions.CheckWallCollision(shape, this);

            //Collisions with other shapes
            for (Shape shape2 : ActiveShapes) {
                //Skip double comparison
                if (shape == shape2) {
                    continue;
                }

                //Detect and handle collisions between 2 shapes
                HelperFunctions.ShapesCollide(shape,shape2,ShapesToRemove);

            }
            shape.draw(g);
        }
        //Remove all defeated shapes
        ActiveShapes.removeAll(ShapesToRemove);

    }

}
