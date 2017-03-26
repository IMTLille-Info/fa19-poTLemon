package potlemon.server.app.listeners;

import potlemon.server.app.controlers.ServerController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Pierre on 23/03/2017.
 */
public class ServerControlsListener extends MouseAdapter {

    private final JLabel lblStatus;
    private final JButton btnStart;
    private final JTextField serverPortTCP;
    private final JTextField serverPortUDP;

    public ServerControlsListener(JLabel lblStatus, JButton btnStart, JTextField serverPortTCP, JTextField serverPortUDP) {

        this.lblStatus = lblStatus;
        this.btnStart = btnStart;
        this.serverPortTCP = serverPortTCP;
        this.serverPortUDP = serverPortUDP;
    }

    /**
     * Click event.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("[CONTROL] Switching status...");

        if (btnStart.getText().equals("Start")) {
            // have to start
            lblStatus.setText("Starting...");
            btnStart.setText("Starting...");

            try {
                // have to start server
                ServerController.getInstance().startServer(Integer.parseInt(serverPortTCP.getText()), Integer.parseInt(serverPortUDP.getText()));

            } catch (Exception exception) {
                System.out.println("[StartingServer] " + exception.getMessage());
                resetBtns();
            }

        } else {
            // have to stop
            lblStatus.setText("Stopping...");
            btnStart.setText("Stopping...");

            // have to stop server
            ServerController.getInstance().stopServer();

            resetBtns();
        }
    }


    protected void resetBtns() {
        lblStatus.setText("OFFLINE");
        btnStart.setText("Start");
    }

}
