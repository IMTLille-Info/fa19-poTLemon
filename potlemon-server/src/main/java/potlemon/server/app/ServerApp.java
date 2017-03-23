package potlemon.server.app;

import java.awt.EventQueue;

import javax.swing.*;

import org.slf4j.Logger;

import potlemon.server.gui.MainGUI;

/**
 * Created by Pierre on 14/03/2017.
 */
public class ServerApp {
	
	public static void main(String[] args) {

    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					
					System.out.println("GUI started");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

    }
}


