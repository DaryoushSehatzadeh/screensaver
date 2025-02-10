package classes;

import javax.swing.*;
import java.util.ArrayList;

public class HelperFunctions {

    public static void MoveShape(Shape shape){
        shape.setPosition(shape.getX()+shape.getXSpeed(),shape.getY()+shape.getYSpeed());
    }

    public static void CheckWallCollision(Shape shape, JPanel window){
        if (shape.getX() < 0 || shape.getX() + shape.getWidth() > window.getWidth()){
            shape.setXSpeed(shape.getXSpeed() * -1);
        }
        if (shape.getY() < 0 || shape.getY() + shape.getHeight() > window.getHeight()){
            shape.setYSpeed(shape.getYSpeed() * -1);
        }
    }

    public static boolean ShapesOverlap(Shape shape, Shape shape2){
        return (shape.getX() + shape.getWidth() + shape.getXSpeed() > shape2.getX() + shape2.getXSpeed() && shape.getX() + shape.getXSpeed() < shape2.getX() + shape2.getWidth() + shape2.getXSpeed()) &&
                (shape.getY() + shape.getHeight() + shape. getYSpeed() > shape2.getY() + shape2.getYSpeed() && shape.getY() + shape.getYSpeed() < shape2.getY() + shape2.getHeight() + shape2.getYSpeed());
    }

    public static void GeoffHandler(Shape shape, Shape shape2, ArrayList<Shape> ShapesToRemove){
        if (shape instanceof Geoff && !(shape2 instanceof Geoff)){
            ShapesToRemove.add(shape2);
            shape.setHeight(shape.getHeight()+1);
            shape.setWidth(shape.getWidth()+1);
        } else if (shape2 instanceof Geoff && !(shape instanceof Geoff)) {
            ShapesToRemove.add(shape);
            shape2.setHeight(shape2.getHeight()+1);
            shape2.setWidth(shape2.getWidth()+1);
        } else if (shape instanceof Geoff && shape2 instanceof Geoff) {
            //Geoff can only be defeated by a smaller Geoff
            if (shape.getHeight() > shape2.getHeight()){
                ShapesToRemove.add(shape);
            } else if (shape2.getHeight() > shape.getHeight()){
                ShapesToRemove.add(shape2);
            } else {
                ShapesToRemove.add(shape);
                ShapesToRemove.add(shape2);
            }
        }
    }

    public static void ShapesCollide(Shape shape, Shape shape2, ArrayList<Shape> ShapesToRemove){
        //Check for overlap
        if (HelperFunctions.ShapesOverlap(shape,shape2)) {
            //Geoff supremacy
            HelperFunctions.GeoffHandler(shape,shape2,ShapesToRemove);

            //Check which axis the collision occurred on
            int leftIntersect = (shape2.getX() + shape2.getWidth()) - shape.getX();
            int rightIntersect = (shape.getX() + shape.getWidth()) - shape2.getX();
            int topIntersect = (shape2.getY() + shape2.getHeight()) - shape.getY();
            int btmIntersect = (shape.getY() + shape.getHeight()) - shape2.getY();

            //Readjust position on collision to avoid collision lock
            if (Math.max(leftIntersect, rightIntersect) > Math.max(topIntersect, btmIntersect)) {
                if (leftIntersect < rightIntersect) {
                    // Adjust positions along X-axis
                    shape.setX(shape.getX() - leftIntersect / 2);
                    shape2.setX(shape2.getX() + leftIntersect / 2);
                } else {
                    shape.setX(shape.getX() + rightIntersect / 2);
                    shape2.setX(shape2.getX() - rightIntersect / 2);
                }
                // Reverse X velocities
                shape.setXSpeed(-shape.getXSpeed());
                shape2.setXSpeed(-shape2.getXSpeed());
            } else {
                if (topIntersect < btmIntersect) {
                    // Adjust positions along Y-axis
                    shape.setY(shape.getY() - topIntersect / 2);
                    shape2.setY(shape2.getY() + topIntersect / 2);
                } else {
                    shape.setY(shape.getY() + btmIntersect / 2);
                    shape2.setY(shape2.getY() - btmIntersect / 2);
                }
                // Reverse Y velocities
                shape.setYSpeed(-shape.getYSpeed());
                shape2.setYSpeed(-shape2.getYSpeed());
            }

            // Recheck for overlap and nudge apart if still colliding
            // Full disclosure, ChatGPT had to help with this.
            // I had collisions working well with walls and shapes but there was some erratic behavior
            // I knew I needed to recheck collisions after resolution but AI helped with the actual math
            if ((shape.getX() + shape.getWidth() > shape2.getX() && shape.getX() < shape2.getX() + shape2.getWidth()) &&
                    (shape.getY() + shape.getHeight() > shape2.getY() && shape.getY() < shape2.getY() + shape2.getHeight())) {

                // Calculate precise overlaps
                int overlapX = Math.min(shape.getX() + shape.getWidth() - shape2.getX(), shape2.getX() + shape2.getWidth() - shape.getX());
                int overlapY = Math.min(shape.getY() + shape.getHeight() - shape2.getY(), shape2.getY() + shape2.getHeight() - shape.getY());

                // Nudge apart along the axis with the smallest overlap
                if (overlapX < overlapY) {
                    // Nudge along X-axis
                    if (shape.getX() < shape2.getX()) {
                        shape.setX(shape.getX() - (overlapX / 2 + 1));
                        shape2.setX(shape2.getX() + (overlapX / 2 + 1));
                    } else {
                        shape.setX(shape.getX() + (overlapX / 2 + 1));
                        shape2.setX(shape2.getX() - (overlapX / 2 + 1));
                    }
                } else {
                    // Nudge along Y-axis
                    if (shape.getY() < shape2.getY()) {
                        shape.setY(shape.getY() - (overlapY / 2 + 1));
                        shape2.setY(shape2.getY() + (overlapY / 2 + 1));
                    } else {
                        shape.setY(shape.getY() + (overlapY / 2 + 1));
                        shape2.setY(shape2.getY() - (overlapY / 2 + 1));
                    }
                }
            }

        }
    }
}
