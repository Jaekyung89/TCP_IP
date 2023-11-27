package Catchmind.client;

import javax.swing.JFrame;

public class CMClient {

	public CMClient(String title) {
		JFrame Client_Main = new JFrame();
		Client_Main.setTitle(title);
		Client_Main.setBounds(0, 0, 1000, 800);
		Client_Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Client_Main.getContentPane().setLayout(null);
		Client_Main.setLocationRelativeTo(null);
		Client_Main.setResizable(false);
		Client_Main.setVisible(true);
	}
	
	public static void main(String[] args) {
		new CMClient("안녕");
	}
	
}
