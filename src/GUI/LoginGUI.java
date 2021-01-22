package GUI;

import javax.swing.*;
import java.awt.event.*;
import bookrateback.*;

public class LoginGUI extends JFrame {
    private JPanel panelLogin;
    private JButton logInButton;
    private JTextField nickTX;
    private JPasswordField passwordTX;
    private JButton signUpButton;
    private JTextArea messageTx;


    public LoginGUI(ActiveUser activeUser) {
        setContentPane(panelLogin);
        DataBase dataBase = new DataBase();
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dataBase.userLogin(nickTX.getText(), new String(passwordTX.getPassword()))) {
                    activeUser.setUser(dataBase.getUser(nickTX.getText()));
                    dispose();
                }
                else if(dataBase.getUser(nickTX.getText()) == null){
                    messageTx.setText("WARNING: THERE IS NO USER WITH THAT NICK IN DATABASE!");
                }
                else{
                    messageTx.setText("WARNING: WRONG PASSWORD!");
                }
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dataBase.isNickTaken(nickTX.getText()) && nickTX.getText().length() >= 1 && new String(passwordTX.getPassword()).length() >= 1) {
                    User user = new User(nickTX.getText(), new String(passwordTX.getPassword()));
                    activeUser.setUser(user);
                    dataBase.addUser(user);
                    dispose();
                } else {
                    messageTx.setText("WARNING: NICK IS TAKEN!");
                }
            }
        });
        nickTX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(nickTX.getText().equals("USERNAME"))
                {
                    nickTX.setText("");
                    repaint();
                    revalidate();
                }
            }
        });
        passwordTX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(new String(passwordTX.getPassword()).equals("PASSWORD"))
                {
                    passwordTX.setText("");
                    repaint();
                    revalidate();
                }
            }
        });
    }

}
