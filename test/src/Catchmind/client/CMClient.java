package Catchmind.client;

import java.awt.*;
import javax.swing.*;

public class CMClient {

	public CMClient(String title) {
		JFrame Client_Main = new JFrame();
		Client_Main.setTitle(title);
		Client_Main.setBounds(0, 0, 1000, 840);
		Client_Main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Client_Main.setLayout(null);
		Client_Main.setLocationRelativeTo(null);
		Client_Main.setResizable(false);
		Client_Main.setVisible(true);
		
		JPanel Client_Panel = new JPanel();
		Client_Panel.setLayout(null);
		Client_Panel.setBounds(0, 0, 1000, 840);
		Client_Main.add(Client_Panel);
		
		word(Client_Panel);	
		canvas(Client_Panel);
		member(Client_Panel);
		chat(Client_Panel);
		skip(Client_Panel);
		
	}
	
	private void word(JPanel Client_Panel) {
		JPanel Client_Word = new JPanel();
		Client_Word.setLayout(null);
		Client_Word.setBackground(Color.BLUE);
		Client_Word.setBounds(0, 0, 700, 100);
		Client_Panel.add(Client_Word);
	}

	private void canvas(JPanel Client_Panel) {
		JPanel Client_Canvas = new JPanel();
		Client_Canvas.setLayout(null);
		Client_Canvas.setBackground(Color.BLACK);
		Client_Canvas.setBounds(0, 100, 700, 700);
		Client_Panel.add(Client_Canvas);
	}
	
	
	
	private void member(JPanel Client_Panel) {
		JPanel Client_Member = new JPanel();
		Client_Member.setLayout(null);
		Client_Member.setBackground(Color.GREEN);
		Client_Member.setBounds(700, 0, 300, 200);
		Client_Panel.add(Client_Member);
	}
	
	private void chat(JPanel Client_Panel) {
		JPanel Client_Chat = new JPanel();
		Client_Chat.setLayout(null);
		Client_Chat.setBackground(Color.RED);
		Client_Chat.setBounds(700, 200, 300, 400);
		Client_Panel.add(Client_Chat);
	}
	
	private void skip(JPanel Client_Panel) {
		JPanel Client_Skip = new JPanel();
		Client_Skip.setLayout(null);
		Client_Skip.setBackground(Color.YELLOW);
		Client_Skip.setBounds(700, 600, 300, 200);
		Client_Panel.add(Client_Skip);
	}
	
	public static void main(String[] args) {
		new CMClient("안녕");
	}
	
}
