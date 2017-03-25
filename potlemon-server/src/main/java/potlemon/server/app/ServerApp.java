package potlemon.server.app;

import potlemon.server.app.tools.Logger;

import java.awt.EventQueue;

import javax.swing.*;

/**
 * Created by Pierre on 14/03/2017.
 */
public class ServerApp {
	
	public static void main(String[] args) {

    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
                    Logger.log(this.getClass().toString(), "GUI started");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    }


}


