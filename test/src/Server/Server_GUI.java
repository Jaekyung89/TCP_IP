package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Server_GUI {

    public static void main(String[] args) {
        new ManagerLogin();
    }
}

class ManagerLogin extends JFrame implements ActionListener, KeyListener {
    // 로그인 창
    Server_ChatGUI Server_chat = null;
    JPanel Port_Log = new JPanel();
    JLabel Port_Label = new JLabel("입력을 허용할 포트 번호를 입력하세요.");
    JLabel Port_Warning = new JLabel("(단, 포트 번호는 0 ~ 65535까지)");
    JTextField Port_Text = new JTextField(20);
    JButton Port_Enter = new JButton("접속!");

    public ManagerLogin() {
        setTitle("서버 메니저 창");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 120);
        setVisible(true);
        setResizable(false);
        Port_Enter.addActionListener(this);
        Port_Text.addKeyListener(this);
        Port_Log.add(Port_Label);
        Port_Log.add(Port_Warning);
        Port_Log.add(Port_Text);
        Port_Log.add(Port_Enter);
        add(Port_Log);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            int Port = Integer.parseInt(Port_Text.getText().trim());
            if (e.getSource() == Port_Enter) {
                Server_chat = new Server_ChatGUI(Port);
                setVisible(false);
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다!");
        }
    }

    public void keyPressed(KeyEvent e) {
        try {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int Port = Integer.parseInt(Port_Text.getText().trim());
                Server_chat = new Server_ChatGUI(Port);
                setVisible(false);
            }
        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, "올바르지 않은 입력입니다!");
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}

class Server_ChatGUI extends JFrame implements ActionListener, KeyListener {
    // 서버용 채팅창
    JPanel ServerGUI_Panel = new JPanel();
    JLabel ServerLabel = new JLabel("Main Server");
    JLabel UserLabel = new JLabel("유저 목록");
    JTextField Chat = new JTextField(45);
    JButton Enter = new JButton("전송");
    JTextArea ServerChatList = new JTextArea(30, 50);
    JTextArea UserList = new JTextArea(30, 15);
    Server_Back SB = new Server_Back();

    public Server_ChatGUI(int Port) {
        setTitle("메인 서버");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(750, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ServerChatList.setEditable(false);
        UserList.setEditable(false);
        Chat.addKeyListener(this);
        Enter.addActionListener(this);

        ServerGUI_Panel.add(ServerLabel);
        ServerGUI_Panel.add(new JScrollPane(ServerChatList));
        ServerGUI_Panel.add(UserLabel);
        ServerGUI_Panel.add(new JScrollPane(UserList));
        ServerGUI_Panel.add(Chat);
        ServerGUI_Panel.add(Enter);
        add(ServerGUI_Panel);

        UserList.append("Admin\n");
        SB.setGUI(this);
        SB.Start_Server(Port);
        SB.start();
    }

    public void actionPerformed(ActionEvent e) {
        String Message = Chat.getText().trim();
        if (e.getSource() == Enter && Message.length() > 0) {
            AppendMessage("서버 : " + Message + "\n");
            SB.Transmitall("서버 : " + Message + "\n");
            Chat.setText(null);
        }
    }

    public void keyPressed(KeyEvent e) {
        String Message = Chat.getText().trim();
        if (e.getKeyCode() == KeyEvent.VK_ENTER && Message.length() > 0) {
            AppendMessage("서버 : " + Message + "\n");
            SB.Transmitall("서버 : " + Message + "\n");
            Chat.setText(null);
        }
    }

    public void AppendMessage(String Message) {
        ServerChatList.append(Message);
    }

    public void AppendUserList(ArrayList<String> NickName) {
        for (String name : NickName) {
            UserList.append(name + "\n");
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
