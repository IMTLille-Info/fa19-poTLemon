package potlemon.server.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

public class MainGUI {

	private JFrame frmPotlemonServer;


	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
		
		frmPotlemonServer.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPotlemonServer = new JFrame();
		frmPotlemonServer.setTitle("poTLemon SERVER");
		frmPotlemonServer.setResizable(false);
		frmPotlemonServer.setBounds(100, 100, 1001, 626);
		frmPotlemonServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPotlemonServer.getContentPane().setLayout(null);
		
		JPanel serverInfos = new JPanel();
		serverInfos.setBounds(657, 59, 309, 284);
		frmPotlemonServer.getContentPane().add(serverInfos);
		serverInfos.setLayout(null);
		
		JLabel lblInfos = new JLabel("Informations");
		lblInfos.setBounds(81, 5, 138, 26);
		lblInfos.setFont(new Font("Tahoma", Font.BOLD, 21));
		serverInfos.add(lblInfos);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(26, 42, 273, 231);
		serverInfos.add(textArea);
		
		JPanel serverControls = new JPanel();
		serverControls.setBounds(29, 65, 533, 179);
		frmPotlemonServer.getContentPane().add(serverControls);
		serverControls.setLayout(null);
		
		JLabel lblServerControls = new JLabel("Server controls");
		lblServerControls.setBounds(173, 11, 158, 26);
		serverControls.add(lblServerControls);
		lblServerControls.setFont(new Font("Tahoma", Font.BOLD, 21));
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(135, 60, 252, 49);
		serverControls.add(btnStart);
		
		JLabel lblNewLabel = new JLabel("OFFLINE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(202, 127, 137, 25);
		serverControls.add(lblNewLabel);
		
		JScrollPane logs_scroll = new JScrollPane();
		logs_scroll.setBounds(0, 408, 995, 189);
		frmPotlemonServer.getContentPane().add(logs_scroll);
		
		JTextArea logs = new JTextArea();
		logs.setEditable(false);
		logs.setText("***.***.***.** - - [19/Dec/2010:19:47:47 +0100] \"\\x99\\x91g\\xca\\xa8\" 501 1054\r\n**.***.***.** - - [19/Dec/2010:20:14:58 +0100] \"<}\\xdbe\\x86E\\x18\\xe7\\x8b\" 501 1054\r\n**.**.***.*** - - [21/Dec/2010:15:28:14 +0100] \"J\\xaa\\x9f\\xa3\\xdd\\x9c\\x81\\\\\\xbd\\xb3\\xbe\\xf7\\xa6A\\x92g'\\x039\\x97\\xac,vC\\x8d\\x12\\xec\\x80\\x06\\x10\\x8e\\xab7e\\xa9\\x98\\x10\\xa7\" 501 1054\r\n***.***.***.** - - [19/Dec/2010:19:47:47 +0100] \"\\x99\\x91g\\xca\\xa8\" 501 1054\r\n**.***.***.** - - [19/Dec/2010:20:14:58 +0100] \"<}\\xdbe\\x86E\\x18\\xe7\\x8b\" 501 1054\r\n**.**.***.*** - - [21/Dec/2010:15:28:14 +0100] \"Jdqsdffsdfsd fsdf zef zef ze fez fezf kezlf kzefzef \r\nzefgezlf\r\nez f\r\nze flzemflzem fzemfl mzelf ezf lze\r\nfzelfzelf\r\n zefzef");
		logs_scroll.setViewportView(logs);
		
		JLabel lblLogs = new JLabel("LOGS");
		lblLogs.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblLogs.setBounds(10, 369, 169, 38);
		frmPotlemonServer.getContentPane().add(lblLogs);
		
		JMenuBar menuBar = new JMenuBar();
		frmPotlemonServer.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHowDoesIt = new JMenuItem("How does it work?");
		mnHelp.add(mntmHowDoesIt);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}
}
