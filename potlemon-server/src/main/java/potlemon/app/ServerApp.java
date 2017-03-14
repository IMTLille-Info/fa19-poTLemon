package potlemon.app;

import javax.swing.*;

/**
 * Created by Pierre on 14/03/2017.
 */
public class ServerApp {
    public static void main(String[] args) {


        JFrame frame = new JFrame("MainGUI");
        frame.setContentPane(new MainGUI().getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
