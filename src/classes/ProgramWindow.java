package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ProgramWindow extends JFrame {     //The class used as the main window for your GUI screensaver program.

    public ProgramWindow(){
        setBounds(50, 35, 1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);

        ShapeDisplay screensaver = new ShapeDisplay();
        add(screensaver, BorderLayout.CENTER);
    }

}
