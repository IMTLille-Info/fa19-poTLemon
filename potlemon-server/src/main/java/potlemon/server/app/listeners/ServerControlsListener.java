package potlemon.server.app.listeners;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Pierre on 23/03/2017.
 */
public class ServerControlsListener extends MouseAdapter {

    private final JLabel lblStatus;
    private final JButton btnStart;

    public ServerControlsListener(JLabel lblStatus, JButton btnStart) {

        this.lblStatus = lblStatus;
        this.btnStart = btnStart;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("[CONTROL] Switching status...");

        if(btnStart.getText().equals("Start")){
            // have to start
            lblStatus.setText("Starting...");
            btnStart.setText("Starting...");

            // @TODO have to start server


        } else {
            // have to stop
            lblStatus.setText("Stopping...");
            btnStart.setText("Stopping...");

            // @TODO have to stop server
        }
    }


}
